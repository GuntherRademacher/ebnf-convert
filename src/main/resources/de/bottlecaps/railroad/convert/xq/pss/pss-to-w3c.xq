module namespace x="de/bottlecaps/railroad/convert/xq/pss/pss-to-w3c.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function x:hex($done as xs:string?, $todo as xs:integer) as xs:string
{
  if ($todo ne 0) then
    x:hex(concat(substring("0123456789ABCDEF", 1 + $todo mod 16, 1), $done), $todo idiv 16)
  else if (matches($done, "^1....$")) then
    replace($done, "^10{0,3}", "")
  else
    $done
};

declare function x:unquote($literal as xs:string) as xs:string
{
  let $literal :=
    if (ends-with($literal, "i")) then
      substring($literal, 1, string-length($literal) - 1)
    else
      $literal
  return substring($literal, 2, string-length($literal) - 2)
};

declare function x:split-string($done as element()*, $todo as xs:string) as element()*
{
  if ($todo = "") then
    $done
  else
    let $ascii := replace($todo, "[^&#x20;-&#x7e;].*$", "", "s")
    return
      if ($ascii = "") then
        x:split-string(($done, <g:charCode value="{x:hex("", string-to-codepoints(substring($todo, 1, 1)))}"/>), substring($todo, 2))
      else
        x:split-string(($done, <g:string>{$ascii}</g:string>), substring-after($todo, $ascii))
};

declare function x:rewrite-item($item as element(Item)) as element()*
{
  let $result :=
    if ($item/Nonterminal) then
      <g:ref name="{$item/Nonterminal}"/>
    else if ($item/Terminal) then
      x:split-string((), x:unquote($item/Terminal))
    else if ($item/TOKEN = "[") then
      <g:optional>{x:rewrite-alternatives($item/Alternatives/Alternative)}</g:optional>
    else if ($item/TOKEN = "{") then
      <g:zeroOrMore>{x:rewrite-alternatives($item/Alternatives/Alternative)}</g:zeroOrMore>
    else if ($item/TOKEN = "(") then
      <g:sequence>{x:rewrite-alternatives($item/Alternatives/Alternative)}</g:sequence>
    else
      error(xs:QName("x:rewrite-item"), concat("unsupported item: ", string($item)))
  return
    if ($item/TOKEN = "*") then
      <g:zeroOrMore>{$result}</g:zeroOrMore>
    else if ($item/TOKEN = "+") then
      <g:oneOrMore>{$result}</g:oneOrMore>
    else if ($item/TOKEN = "?") then
      <g:optional>{$result}</g:optional>
    else
      $result
};

declare function x:rewrite-items($done as element()*, $todo as element()*) as element()*
{
  if (empty($todo)) then
    $done
  else
    x:rewrite-items(($done, x:rewrite-item($todo[1])), $todo[position() > 1])
};

declare function x:rewrite-alternatives($alternatives as element(Alternative)+) as element()*
{
  let $choice :=
    <g:choice>
    {
      for $alternative in $alternatives
      let $rewrite :=
        <g:sequence>
        {
          if (exists($alternative/Item)) then
            x:rewrite-items((), $alternative/Item)
          else
            ()
        }
        </g:sequence>
      return if (count($rewrite/*) = 1) then $rewrite/* else $rewrite
    }
    </g:choice>
  return
    if (count($choice/*) ne 1) then
      $choice
    else if ($choice/g:sequence) then
      $choice/g:sequence/*
    else
      $choice/*
};

declare function x:pss-to-w3c($parse-tree as element(PSS-Grammar)) as element(g:grammar)
{
  <g:grammar>
  {
    for $p in $parse-tree/Rule
    return element g:production {attribute name {$p/Nonterminal}, x:rewrite-alternatives($p/Alternatives/Alternative)}
  }
  </g:grammar>
};
