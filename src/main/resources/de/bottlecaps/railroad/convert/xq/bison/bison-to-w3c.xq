module namespace x="de/bottlecaps/railroad/convert/xq/bison/bison-to-w3c.xq";

import module namespace t="de/bottlecaps/railroad/convert/xq/to-w3c.xq" at "../to-w3c.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function x:rewrite($alternatives as element(rhs)+) as element()*
{
  let $choice :=
    for $alternative in $alternatives
    let $rewrite :=
      for $symbol in $alternative/symbol
      where not($symbol/preceding-sibling::*[1]/self::TOKEN[. = "%prec"])
      return
        if ($symbol/id/ID) then
          <g:ref name="{$symbol/id/ID}"/>
        else if ($symbol/id/CHAR_LITERAL) then
          t:split-string(substring($symbol/id/CHAR_LITERAL, 2, string-length($symbol/id/CHAR_LITERAL) - 2))
        else if ($symbol/string_as_id/STRING) then
          t:split-string(substring($symbol/string_as_id/STRING, 2, string-length($symbol/string_as_id/STRING) - 2))
(:        <g:ref name="{substring($symbol/string_as_id/STRING, 2, string-length($symbol/string_as_id/STRING) - 2)}"/>  :)
        else
          error(xs:QName("x:rewrite"), string-join(("unsupported item:", $symbol/*/local-name()), " "))
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

declare function x:bison-to-w3c($parse-tree as element(input)) as element(g:grammar)
{
  t:remove-right-recursion
  (
    t:remove-left-recursion
    (
      element g:grammar
      {
        let $rules := $parse-tree//rules
        for $id in distinct-values($rules/id/ID)
        let $rule := $rules[id/ID = $id]
        order by min($rule/index-of($rules, .))
        return
          element g:production
          {
            attribute name {$id},
            x:rewrite($rule/rhs)
          }
      }
    )
  )
};
