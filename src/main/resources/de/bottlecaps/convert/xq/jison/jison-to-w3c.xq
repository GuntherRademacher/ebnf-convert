module namespace x="de/bottlecaps/convert/xq/jison/jison-to-w3c.xq";

import module namespace t="de/bottlecaps/convert/xq/to-w3c.xq" at "../to-w3c.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function x:rewrite($alternatives as element(handle_action)+) as element()*
{
  let $choice :=
    for $alternative in $alternatives
    let $rewrite :=
      for $symbol in $alternative/handle/symbol
      return
        if ($symbol/id) then
          <g:ref name="{$symbol/id/ID}"/>
        else if ($symbol/STRING) then
          t:split-string(substring($symbol/STRING, 2, string-length($symbol/STRING) - 2))
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

declare function x:tokenize($string as xs:string*) as xs:string*
{
  for $string in tokenize($string, "\s")
    let $t1 := tokenize($string, "[-.:\C]+")
    let $t2 := tokenize($string, "[^-.:\C]+")
    return
    (
      if ($t1[1] eq "") then
        for $t at $i in ($t1)
        return ($t1[$i], $t2[$i])[.]
      else
        for $t at $i in ($t2)
        return ($t2[$i], $t1[$i])[.]
     )
};

declare function x:jison-to-w3c($parse-tree as element(jison)) as element(g:grammar)
{
  element g:grammar
  {
    for $p in $parse-tree/spec/grammar/production_list/production
    return element g:production {attribute name {$p/id/ID}, x:rewrite($p/handle_list/handle_action)},

    for $p in $parse-tree/json/bnf-pair/rule-pair
    let $id := $p/string
    return
      element g:production
      {
        attribute name {substring($id, 2, string-length($id) - 2)},
        x:rewrite
        (
        for $s in $p/alternative/string
        let $symbols := x:tokenize(substring($s, 2, string-length($s) - 2))
        return
          if (empty($symbols)) then
            <handle_action/>
          else
            element handle_action
            {
              for $t in $symbols
              return
                element handle
                {
                  element symbol
                  {
                    if (matches($t, "^\p{Lu}") or $parse-tree/json/bnf-pair/rule-pair/string = concat("""", $t, """")) then
                      element id {element ID {$t}}
                    else
                      element STRING {concat('"', $t, '"')}
                  }
                }
            }
        )
      }
  }
};
