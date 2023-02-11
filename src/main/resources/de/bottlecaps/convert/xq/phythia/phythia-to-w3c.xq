module namespace x="de/bottlecaps/convert/xq/phythia/phythia-to-w3c.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function x:rewrite($alternatives as element(Sequence)+) as element()*
{
  let $choice :=
    for $alternative in $alternatives
    let $rewrite :=
      for $item in $alternative/Item
      where empty($item/Sem)
      return
        if ($item/Name) then
          <g:ref name="{$item/Name}"/>
        else if ($item/StringLiteral) then
          element g:string {substring($item/StringLiteral, 2, string-length($item/StringLiteral) - 2)}
        else if ($item/TOKEN = '<*') then
          let $lhs := x:rewrite($item/Choice[1]/Sequence)
          let $rhs := x:rewrite($item/Choice[2]/Sequence)
          return ($lhs, element g:zeroOrMore {$rhs, $lhs})
        else if ($item/TOKEN = '<+') then
          let $lhs := x:rewrite($item/Choice[1]/Sequence)
          let $rhs := x:rewrite($item/Choice[2]/Sequence)
          return element g:optional {$lhs, element g:zeroOrMore {$rhs, $lhs}}
        else if ($item/TOKEN = '{*') then
          element g:oneOrMore {x:rewrite($item/Choice/Sequence)}
        else if ($item/TOKEN = '{+') then
          element g:zeroOrMore {x:rewrite($item/Choice/Sequence)}
        else if ($item/TOKEN = '[') then
          element g:optional {x:rewrite($item/Choice/Sequence)}
        else if ($item/TOKEN = '(') then
          x:rewrite($item/Choice/Sequence)
        else
          error(xs:QName("x:rewrite"), string-join(("unsupported item:", $item/*/local-name()), " "))
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

declare function x:phythia-to-w3c($parse-tree as element(Grammar)) as element(g:grammar)
{
  element g:grammar
  {
    for $p in $parse-tree/Production
    return element g:production {attribute name {$p/Name[2]}, x:rewrite($p/Choice/Sequence)}
  }
};
