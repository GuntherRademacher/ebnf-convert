module namespace x="de/bottlecaps/railroad/convert/xq/abnf/abnf-to-w3c.xq";

import module namespace t="de/bottlecaps/railroad/convert/xq/to-w3c.xq" at "../to-w3c.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function x:to-charRange($min as element(), $max as element()) as element()
{
  if ($min/self::g:char and matches($min, "[ -~]") and
      $max/self::g:char and matches($max, "[ -~]")) then
    <g:charRange minChar="{data($min)}" maxChar="{data($max)}"/>
  else
    let $min := if ($min/self::g:charCode) then $min/@value else t:to-charCode($min)
    let $max := if ($max/self::g:charCode) then $max/@value else t:to-charCode($max)
    return <g:charCodeRange minValue="{$min}" maxValue="{$max}"/>
};

declare function x:num-val($base as xs:integer, $range as xs:boolean, $number as xs:string+) as element()
{
  let $chars :=
    for $n in $number return t:codepoint-to-string(t:int-value($base, $n))
  return
    if (not($range)) then
      t:split-string(string-join($chars, ""))
    else if (count($chars) = 2) then
      element g:charClass {x:to-charRange(element g:char {$chars[1]}, element g:char {$chars[2]})}
    else
      error()
};

declare function x:num-val($n as element(num-val)) as element()
{
  if ($n/bin-val) then
    x:num-val(2, $n/bin-val/TOKEN = "-", $n/bin-val/bits)
  else if ($n/dec-val) then
    x:num-val(10, $n/dec-val/TOKEN = "-", $n/dec-val/integer)
  else if ($n/hex-val) then
    x:num-val(16, $n/hex-val/TOKEN = "-", $n/hex-val/hexdigs)
  else
    error(xs:QName("x:num-val"), concat("invalid argument: ", string-join($n/descendant-or-self::*/local-name(), ", ")))
};

declare function x:rewrite($alternatives as element(concatenation)+) as element()*
{
  let $choice :=
    for $alternative in $alternatives
    let $rewrite :=
      for $item in $alternative/repetition
      let $primary := $item/element
      let $rewrite :=
        if ($primary/rulename) then
          <g:ref name="{$primary/rulename}"/>
        else if ($primary/char-val) then
          t:split-string(substring($primary/char-val, 2, string-length($primary/char-val) - 2))
        else if ($primary/option) then
          element g:optional {x:rewrite($primary/option/alternation/concatenation)}
        else if ($primary/group) then
          x:rewrite($primary/group/alternation/concatenation)
        else if ($primary/num-val) then
          x:num-val($primary/num-val)
        else if ($primary/prose-val) then
          trace(<!-- prose -->, "todo:")
        else
          error(xs:QName("x:rewrite"), string-join(("unsupported item:", $primary/*/local-name()), " "))
      return
        let $star := $item/repeat/TOKEN[. = "*"]
        return
          if ($star) then
            let $min := (xs:integer($item/repeat/integer[. << $star]), 0)[1]
            let $max := (xs:integer($item/repeat/integer[. >> $star]), 2174836487)[1]
            return t:repeat($rewrite, $min, $max)
          else if ($item/repeat/integer) then
            let $count := xs:integer($item/repeat/integer)
            return t:repeat($rewrite, $count, $count)
          else
            $rewrite
    return
      if (count($rewrite) ne 1) then
        element g:sequence {$rewrite}
      else
        $rewrite
  return
    if (count($choice) ne 1) then
      element g:choice {$choice}
    else if ($choice/self::g:sequence) then
      $choice/*
    else
      $choice
};

declare function x:case-sensitive-rule-names($nodes, $names)
{
  for $node in $nodes
  return
    typeswitch($node)
    case element(rulename) return
      ($names[lower-case(.) = lower-case($node)], $node)[1]
    case element() return
      element {node-name($node)} {$node/@*, x:case-sensitive-rule-names($node/node(), $names)}
    default return
      $node
};

declare function x:abnf-to-w3c($parse-tree as element(rulelist)) as element(g:grammar)
{
  element g:grammar
  {
    for $p in x:case-sensitive-rule-names($parse-tree/rule, $parse-tree/rule/rulename)
    return element g:production {attribute name {$p/rulename}, x:rewrite($p/elements/alternation/concatenation)}
  }
};
