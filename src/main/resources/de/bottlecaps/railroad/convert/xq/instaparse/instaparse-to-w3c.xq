module namespace x="de/bottlecaps/railroad/convert/xq/instaparse/instaparse-to-w3c.xq";

import module namespace q="de/bottlecaps/railroad/convert/xq/regexp/regexp.xquery" at "../regexp/regexp.xquery";
import module namespace y="de/bottlecaps/railroad/convert/xq/regexp/regexp-to-w3c.xq" at "../regexp/regexp-to-w3c.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function x:unescape($done as xs:string, $todo as xs:string) as xs:string
{
  if (not(contains($todo, "\"))) then
    concat($done, $todo)
  else if (not(starts-with($todo, "\"))) then
    x:unescape(concat($done, substring-before($todo, "\")), concat("\", substring-after($todo, "\")))
  else
    x:unescape(concat($done, substring($todo, 2, 1)), substring($todo, 3))
};

declare function x:rewrite-factor($factor)
{
  let $item := $factor/*
  return
    typeswitch ($item)
    case element(nt) return
      element g:ref {attribute name {$item//non-epsilon}}
    case element(string) return
      element g:string {x:unescape("", substring($item, 2, string-length($item) - 2))}
    case element(opt) return
      let $body := if ($item/factor) then x:rewrite-factor($item/factor) else x:rewrite-alternatives($item/alt-or-ord)
      return
        if (count($body) ne 1 or $body/self::g:optional) then
          element g:optional{$body}
        else if ($body/self::g:oneOrMore or $body/self::g:zeroOrMore) then
          element g:zeroOrMore{$body/*}
        else
          element g:optional {$body}
    case element(star) return
      let $body := if ($item/factor) then x:rewrite-factor($item/factor) else x:rewrite-alternatives($item/alt-or-ord)
      return
        if (count($body) ne 1) then
          element g:zeroOrMore{$body}
        else if ($body/self::g:optional or $body/self::g:oneOrMore or $body/self::g:zeroOrMore) then
          element g:zeroOrMore{$body/*}
        else
          element g:zeroOrMore{$body}
    case element(plus) return
      let $body := if ($item/factor) then x:rewrite-factor($item/factor) else x:rewrite-alternatives($item/alt-or-ord)
      return
        if (count($body) ne 1 or $body/self::g:oneOrMore) then
          element g:oneOrMore{$body}
        else if ($body/self::g:optional or $body/self::g:zeroOrMore) then
          element g:zeroOrMore{$body/*}
        else
          element g:oneOrMore{$body}
    case element(paren) return
      x:rewrite-alternatives($item/alt-or-ord)
    case element(hide) return
      x:rewrite-alternatives($item/alt-or-ord)
    case element(epsilon) return
      ()
    case element(regexp) return
      let $parse-tree := q:parse-regexp(substring($item, 3, string-length($item) - 3))
      return
        if ($parse-tree/self::ERROR) then
          let $prefix := string-join(root($item)//text()[. << $item], "")
          let $lines := tokenize($prefix, "&#xA;")
          let $line := count($lines)
          let $column := string-length($lines[last()]) + 1
          return
            error(xs:QName("x:rewrite-factor"), concat("unsupported regexp in line ", $line, ", column ", $column, ". Failed after ", $parse-tree/@b + 1, " characters of ", $item))
        else
          y:regexp-to-w3c($parse-tree)
    default return
      error(xs:QName("x:rewrite-factor"), concat("unsupported item: ", local-name($item)))
};

declare function x:rewrite-items($done as element()*, $todo as element()*) as element()*
{
  if (empty($todo)) then
    if (count($done) eq 1) then $done else element g:sequence {$done}
  else
    let $first := $todo[1]
    let $result := if ($first/self::factor) then x:rewrite-factor($first) else ()
    return
      x:rewrite-items(($done, $result), subsequence($todo, 2))
};

declare function x:rewrite-cat($cat as element(cat)*)
{
  x:rewrite-items((), $cat/*)
};

declare function x:rewrite-alternatives($alt-or-ord as element(alt-or-ord))
{
  let $alt := $alt-or-ord/*
  let $ordered :=
    if ($alt/self::alt) then false() else
    if ($alt/self::alt) then true() else error()
  let $alternatives :=
    for $cat in $alt/cat
    return x:rewrite-cat($cat)
  return
    if (count($alternatives) eq 1) then
      if ($alternatives/self::g:sequence) then $alternatives/* else $alternatives
    else if ($ordered) then
      element g:orderedChoice {$alternatives}
    else
      element g:choice {$alternatives}
};

declare function x:instaparse-to-w3c($parse-tree as element(rules)) as element(g:grammar)
{
  let $grammar :=
    <g:grammar>
    {
      for $p in $parse-tree/rule
      return element g:production {attribute name {$p/(hide-nt, nt)//non-epsilon}, x:rewrite-alternatives($p/alt-or-ord)}
    }
    </g:grammar>
  let $tokens-productions := $grammar/*[.//g:charClass or .//g:charCode or .//g:ref/@name = "."]
  let $syntax-productions := $grammar/* except $tokens-productions
  return
    <g:grammar>
      {$syntax-productions}
      {processing-instruction TOKENS{}}
      {$tokens-productions}
    </g:grammar>
};
