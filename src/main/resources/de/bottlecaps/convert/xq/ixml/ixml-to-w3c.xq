module namespace x="de/bottlecaps/convert/xq/ixml/ixml-to-w3c.xq";

declare namespace f="de/bottlecaps/railroad/core/ExtensionFunctions";

import module namespace n="de/bottlecaps/railroad/xq/normalize-ast.xq" at "../../../railroad/xq/normalize-ast.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function x:rewrite-string($string as element(string)) as element()*
{
  if ($string/dchar) then
    <g:string>{replace(string-join($string/dchar), '""', '"')}</g:string>
  else
    <g:string>{replace(string-join($string/schar), "''", "'")}</g:string>
};

declare function x:rewrite-literal($literal as element(literal)) as element()*
{
  if ($literal/encoded) then
    <g:charCode value="{$literal/encoded/hex}"/>
  else
    x:rewrite-string($literal/quoted/string)
};

declare function x:rewrite-character($character as element(literal)) as element()*
{
  if ($character/dchar) then
    <g:char>{replace($character/dchar, '""', '"')}</g:char>
  else if ($character/schar) then
    <g:char>{replace($character/schar, "''", "'")}</g:char>
  else
    <g:charCode value="{$character/encoded/hex}"/>
};

declare function x:rewrite-factor($factor as element(factor)) as element()*
{
  if ($factor/alts) then
    x:rewrite-alts($factor/alts/alt)
  else if ($factor/nonterminal) then
    <g:ref name="{$factor/nonterminal/name}"/>
  else if ($factor/terminal/literal) then
    x:rewrite-literal($factor/terminal/literal)
  else
    let $charclass :=
      <g:charClass>{
        for $member in $factor/terminal/charset/*/set/member
        return
          if ($member/string) then
            let $r := x:rewrite-string($member/string)
            return
              if ($r/self::g:string) then
                for $c in string-to-codepoints($r)
                return <g:char>{codepoints-to-string($c)}</g:char>
              else
                $r
          else if ($member/hex) then
            <g:charCode value="{$member/hex}"/>
          else if ($member/range/*/character/hex) then
            <g:charCodeRange minValue="{x:character-to-hex($member/range/from/character)}"
                             maxValue="{x:character-to-hex($member/range/to  /character)}"/>
          else if ($member/range) then
            <g:charRange minChar="{$member/range/from/character/(dchar, schar)}"
                         maxChar="{$member/range/to  /character/(dchar, schar)}"/>
          else
            f:unicode-class(string($member/class/code))/g:charClass/*
      }</g:charClass>
    return
      if ($factor/terminal/charset/exclusion) then
        <g:complement>{$charclass}</g:complement>
      else
        $charclass
};

declare function x:hex($done, $todo as xs:integer)
{
  if ($todo ne 0) then
    x:hex(concat(substring("0123456789ABCDEF", 1 + $todo mod 16, 1), $done), $todo idiv 16)
  else if (matches($done, "^1....$")) then
    replace($done, "^10{0,3}", "")
  else
    $done
};

declare function x:character-to-hex($character as element(character)) as xs:string
{
  if ($character/hex) then
    $character/hex
  else
    x:hex("", string-to-codepoints($character/(dchar, schar)))
};

declare function x:rewrite-alts($alternatives as element(alt)+) as element()*
{
  n:choice
  ((
    for $alternative in $alternatives
    return
      n:wrap-sequence(
        for $term in $alternative/term
        return
          if ($term/factor) then
            x:rewrite-factor($term/factor)
          else if ($term/option) then
            <g:optional>{x:rewrite-factor($term/option/factor)}</g:optional>
          else if ($term/repeat0) then
            let $factor := x:rewrite-factor($term/repeat0/factor)
            let $sep := if ($term/repeat0/sep/factor) then x:rewrite-factor($term/repeat0/sep/factor) else ()
            return
              if ($sep) then
                <g:optional>{$factor, <g:zeroOrMore>{$sep, $factor}</g:zeroOrMore>}</g:optional>
              else
                <g:zeroOrMore>{$factor}</g:zeroOrMore>
          else if ($term/repeat1) then
            let $factor := x:rewrite-factor($term/repeat1/factor)
            let $sep := if ($term/repeat1/sep/factor) then x:rewrite-factor($term/repeat1/sep/factor) else ()
            return
              if ($sep) then
                ($factor, <g:zeroOrMore>{$sep, $factor}</g:zeroOrMore>)
              else
                <g:oneOrMore>{$factor}</g:oneOrMore>
          else
            error()
      )
  ))
};

declare function x:ixml-to-w3c($parse-tree as element(ixml)) as element(g:grammar)
{
  element g:grammar
  {
    for $p in $parse-tree/rule
    return <g:production name="{$p/name}">{x:rewrite-alts($p/alts/alt)}</g:production>
  }
};
