module namespace x="de/bottlecaps/railroad/convert/xq/xtext/xtext-to-w3c.xq";

import module namespace n="de/bottlecaps/railroad/xq/normalize-ast.xq" at "../../../xq/normalize-ast.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function x:codepoint-to-string($codepoint as xs:integer) as xs:string
{
  (: [2] Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF] :)
  if ($codepoint ge 9 and $codepoint le 10
   or $codepoint eq 13
   or $codepoint ge 32 and $codepoint le 55295
   or $codepoint ge 57344 and $codepoint le 65533) then
    codepoints-to-string($codepoint)
  else
    codepoints-to-string(65536 + $codepoint)
};

declare function x:unoct($codepoint as xs:integer*, $value as xs:integer) as xs:integer
{
  if (empty($codepoint)) then
    $value
  else
    x:unoct($codepoint[position() > 1], $codepoint[1] - 48 + $value * 8)
};

declare function x:unoct($oct as xs:string) as xs:string
{
  x:codepoint-to-string(x:unoct(string-to-codepoints($oct), 0))
};

declare function x:unhex($codepoint as xs:integer*, $value as xs:integer) as xs:integer
{
  if (empty($codepoint)) then
    $value
  else
    x:unhex
    (
      $codepoint[position() > 1],
      $codepoint[1] - (0, 0, 48, 55, 0, 87)[$codepoint[1] idiv 16] + $value * 16
    )
};

declare function x:unhex($hex as xs:string) as xs:string
{
  x:codepoint-to-string(x:unhex(string-to-codepoints($hex), 0))
};

declare function x:unescape($done as xs:string, $todo as xs:string) as xs:string
{
  if (not(contains($todo, "\"))) then
    concat($done, $todo)
  else if (not(starts-with($todo, "\"))) then
    x:unescape(concat($done, substring-before($todo, "\")), concat("\", substring-after($todo, "\")))
  else
    let $c2 := substring($todo, 2, 1)
    return
      if ($c2 = "'") then x:unescape(concat($done, "'"    ), substring($todo, 3)) else
      if ($c2 = '"') then x:unescape(concat($done, '"'    ), substring($todo, 3)) else
      if ($c2 = "\") then x:unescape(concat($done, "\"    ), substring($todo, 3)) else
      if ($c2 = "b") then x:unescape(concat($done, "&#x10008;" ), substring($todo, 3)) else
      if ($c2 = "f") then x:unescape(concat($done, "&#x1000C;" ), substring($todo, 3)) else
      if ($c2 = "n") then x:unescape(concat($done, "&#10;"), substring($todo, 3)) else
      if ($c2 = "r") then x:unescape(concat($done, "&#13;"), substring($todo, 3)) else
      if ($c2 = "t") then x:unescape(concat($done, "&#9;" ), substring($todo, 3)) else
      if ($c2 = "v") then x:unescape(concat($done, "&#x1000B;" ), substring($todo, 3)) else
      if (contains("01234567", $c2)) then
        let $octal := replace(substring($todo, 2), "^([0-7]+)", "$1")
        return x:unescape(concat($done, x:unoct($octal)), substring($todo, 2 + string-length($octal)))
      else
      if ($c2 = "x") then x:unescape(concat($done, x:unhex(substring($todo, 3, 2))), substring($todo, 5)) else
      if ($c2 = "u") then x:unescape(concat($done, x:unhex(substring($todo, 3, 4))), substring($todo, 7)) else
      x:unescape(concat($done, substring($todo, 2, 1)), substring($todo, 3))
};

declare function x:unquote($literal as xs:string) as xs:string
{
  let $unquoted := substring($literal, 2, string-length($literal) - 2)
  return x:unescape("", $unquoted)
};

declare function x:rewrite-item($symbol as element()) as element()*
{
  typeswitch ($symbol)
  case element(Keyword) return
    element g:string {x:unquote($symbol/STRING)}
  case element(PredicatedKeyword) return
    element g:string {x:unquote($symbol/STRING)}
  case element(RuleCall) return
    <g:ref name="{x:name($symbol)}"/>
  case element(PredicatedRuleCall) return
    <g:ref name="{x:name($symbol/RuleID)}"/>
  case element(TerminalRuleCall) return
    <g:ref name="{x:name($symbol)}"/>
  case element(ParenthesizedElement) return
    x:rewrite-ConditionalBranch($symbol/Alternatives/ConditionalBranch)
  case element(PredicatedGroup) return
    x:rewrite-ConditionalBranch($symbol/Alternatives/ConditionalBranch)
  case element(ParenthesizedAssignableElement) return
    n:choice
    (
      for $alternative in $symbol/AssignableAlternatives/AssignableTerminal/*
      return x:rewrite-item($alternative)
    )
  case element(CrossReference) return
    element g:ref {attribute name {x:name(($symbol/CrossReferenceableTerminal, "ID")[1])}}
  case element(CharacterRange) return
    let $value := x:unquote($symbol/Keyword[1])
    return
      if (string-length($value) ne 1) then
        element g:string {x:unquote($symbol)}
      else
        let $min := x:to-char($value)
        return
          if (empty($symbol/Keyword[2])) then
            if ($min/self::g:char) then
              <g:char>{data($min)}</g:char>
            else
              let $min := if ($min/self::g:charCode) then $min/@value else x:hex("", string-to-codepoints($min))
              return <g:charClass><g:charCode value="{$min}"/></g:charClass>
          else
            element g:charClass
            {
              let $max := x:to-char(x:unquote($symbol/Keyword[2]))
              return
                if ($min/self::g:char and $max/self::g:char) then
                  <g:charRange minChar="{data($min)}" maxChar="{data($max)}"/>
                else
                  let $min := if ($min/self::g:charCode) then $min/@value else x:hex("", string-to-codepoints($min))
                  let $max := if ($max/self::g:charCode) then $max/@value else x:hex("", string-to-codepoints($max))
                  return <g:charCodeRange minValue="{$min}" maxValue="{$max}"/>
            }
  case element(ParenthesizedTerminalElement) return
    x:rewrite-TerminalGroup($symbol/TerminalAlternatives/TerminalGroup)
  case element(AbstractNegatedToken) return
    if ($symbol/UntilToken) then
    (
      element g:subtract
      {
        element g:zeroOrMore {element g:ref {attribute name {"."}}},
        element g:sequence
        {
          element g:zeroOrMore {element g:ref {attribute name {"."}}},
          x:rewrite-item($symbol/UntilToken/TerminalTokenElement/*),
          element g:zeroOrMore {element g:ref {attribute name {"."}}}
        }
      },
      x:rewrite-item($symbol/UntilToken/TerminalTokenElement/*)
    )
    else
      element g:complement {element g:charClass
      {
        let $alternatives := $symbol/NegatedToken/TerminalTokenElement/*
        for $alternative in $alternatives
        let $alternative :=
          if (count($alternative/self::TerminalGroup/TerminalToken) eq 1) then
            $alternative/TerminalToken
          else
            $alternative
        let $alternative :=
          if ($alternative/self::TerminalToken/empty(TOKEN)) then
            $alternative/TerminalTokenElement/*
          else
            $alternative
        return x:render-charClass($alternative)
      }}
  case element(TOKEN) return
    if ($symbol eq '.') then
      <g:ref name="."/>
    else if ($symbol eq 'EOF') then
      <g:eof/>
    else
      error(xs:QName("x:rewrite-item"), concat("invalid node type: ", local-name($symbol), "(", xs:string($symbol), ")"))
  default return
    error(xs:QName("x:rewrite-item"), concat("invalid node type: ", local-name($symbol), "(", xs:string($symbol), ")"))
};

declare function x:to-char($string as xs:string)
{
  (: mimic ANTLR behaviour, accepting longer strings from fragments/token refs.
   : the ANTLR restriction to single chars appears to be implemented in the syntax.
   :)
  let $string := substring($string, 1, 1)
  return

    if (string-length($string) ne 1) then
      error(xs:QName("x:to-char"), concat("unsupported string of length ", string(string-length($string)), ": ", $string))
    else if (matches($string, "[&#x20;-&#x7e;]")) then
      <g:char>{$string}</g:char>
    else
      <g:charCode value="{x:hex("", string-to-codepoints($string))}"/>
};

declare function x:to-charClass($nodes)
{
  for $node in $nodes
  return
    typeswitch ($node)
    case element(g:charClass) return
      x:to-charClass($node/*)
    case element(g:choice) return
      x:to-charClass($node/*)
    case element(g:string) return
      x:to-char(data($node))
    case element(g:char) return
      $node
    case element(g:charRange) return
      $node
    case element(g:charCode) return
      $node
    case element(g:charCodeRange) return
      $node
    case element(g:ref) return
      $node
    default return
      error(xs:QName("x:to-charClass"), concat("unsupported operand type: ", local-name($node)))
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

declare function x:render-charClass($component)
{
  if ($component/self::RuleCall) then
    <g:ref name="{x:name($component/self::RuleCall)}"/>
  else if ($component/(self::ParenthesizedTerminalElement, self::CharacterRange)) then
    x:to-charClass(x:rewrite-item($component))
  else
    error(xs:QName("x:render-charClass"), concat("unsupported charclass component: ", local-name($component)))
};

declare function x:rewrite-AbstractTokenWithCardinality($done as element()*, $todo as element(AbstractTokenWithCardinality)*) as element()*
{
  if (empty($todo)) then
    $done
  else
    let $first := $todo[1]
    return
      let $symbol := $first/(Assignment/AssignableTerminal, AbstractTerminal)/*[not(self::TOKEN)]
      let $rewrite := x:rewrite-item($symbol)
      let $rewrite :=
        if ($first/TOKEN = "?") then
          <g:optional>{$rewrite}</g:optional>
        else if ($first/TOKEN = "+") then
          <g:oneOrMore>{$rewrite}</g:oneOrMore>
        else if ($first/TOKEN = "*") then
          <g:zeroOrMore>{$rewrite}</g:zeroOrMore>
        else
          $rewrite
    return x:rewrite-AbstractTokenWithCardinality(($done, $rewrite), subsequence($todo, 2))
};

declare function x:rewrite-TerminalToken($done as element()*, $todo as element(TerminalToken)*) as element()*
{
  if (empty($todo)) then
    $done
  else
    let $first := $todo[1]
    return
      let $symbol := $first/TerminalTokenElement/*
      let $rewrite := x:rewrite-item($symbol)
      let $rewrite :=
        if ($first/TOKEN = "?") then
          <g:optional>{$rewrite}</g:optional>
        else if ($first/TOKEN = "+") then
          <g:oneOrMore>{$rewrite}</g:oneOrMore>
        else if ($first/TOKEN = "*") then
          <g:zeroOrMore>{$rewrite}</g:zeroOrMore>
        else
          $rewrite
      return x:rewrite-TerminalToken(($done, $rewrite), subsequence($todo, 2))
};

declare function x:rewrite-ConditionalBranch($alternatives as element(ConditionalBranch)+) as element()*
{
  n:choice
  ((
    for $alternative in $alternatives
    return
      n:wrap-sequence
      (
        if ($alternative/Disjunction) then
          n:wrap-sequence(x:rewrite-AbstractTokenWithCardinality((), $alternative/AbstractToken/AbstractTokenWithCardinality))
        else if ($alternative/UnorderedGroup/TOKEN = "&amp;") then
          let $groups :=
            for $group in $alternative/UnorderedGroup/Group
            return n:wrap-sequence(x:rewrite-AbstractTokenWithCardinality((), $group/AbstractToken/AbstractTokenWithCardinality))
          return
            if ($groups/self::g:optional) then
              element g:zeroOrMore
              {
                n:choice
                (
                  for $group in $groups
                  return if ($group/self::g:optional) then n:wrap-sequence($group/*) else $group
                )
              }
            else
              element g:oneOrMore {n:choice($groups)}
        else
          n:wrap-sequence(x:rewrite-AbstractTokenWithCardinality((), $alternative/UnorderedGroup/Group/AbstractToken/AbstractTokenWithCardinality))
      )
  ))
};

declare function x:rewrite-TerminalGroup($alternatives as element(TerminalGroup)+) as element()*
{
  n:choice
  (
    for $alternative in $alternatives
    return n:wrap-sequence(x:rewrite-TerminalToken((), $alternative/TerminalToken))
  )
};

declare function x:rewrite-EnumLiteralDeclaration($alternatives as element(EnumLiteralDeclaration)+) as element()*
{
  n:choice
  (
    for $alternative in $alternatives
    return
      if ($alternative/Keyword) then
        element g:string {x:unquote($alternative/Keyword/STRING)}
      else
        element g:string {string($alternative/ValidID)}
  )
};

declare function x:name($name)
{
  replace($name, "^\^", "_")
};

declare function x:xtext-to-w3c($parse-tree as element(Grammar)) as element(g:grammar)
{
  element g:grammar
  {
    for $p in $parse-tree/AbstractRule/ParserRule
    return <g:production name="{x:name($p/RuleNameAndParams/ValidID)}">{x:rewrite-ConditionalBranch($p/Alternatives/ConditionalBranch)}</g:production>,

    processing-instruction TOKENS{},

    for $p in $parse-tree/AbstractRule/(TerminalRule|EnumRule)
    return
      <g:production name="{x:name($p/ValidID)}">{
        if ($p/self::TerminalRule) then
          x:rewrite-TerminalGroup($p/TerminalAlternatives/TerminalGroup)
        else
          x:rewrite-EnumLiteralDeclaration($p/EnumLiterals/EnumLiteralDeclaration)
      }</g:production>
  }
};
