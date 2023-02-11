module namespace x="de/bottlecaps/convert/xq/pegjs/pegjs-to-w3c.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function x:to-char($string as xs:string) as element()
{
  if (string-length($string) ne 1) then
    error(xs:QName("x:to-char"), concat("unsupported string of length ", string(string-length($string)), ": ", $string))
  else if (matches($string, "[&#x20;-&#x7e;]")) then
    <g:char>{$string}</g:char>
  else
    <g:charCode value="{x:hex("", string-to-codepoints($string))}"/>
};

declare function x:to-charRange($min as element(), $max as element()) as element()
{
  if ($min/self::g:char and $max/self::g:char) then
    <g:charRange minChar="{data($min)}" maxChar="{data($max)}"/>
  else
    let $min := if ($min/self::g:charCode) then $min/@value else x:hex("", string-to-codepoints($min))
    let $max := if ($max/self::g:charCode) then $max/@value else x:hex("", string-to-codepoints($max))
    return <g:charCodeRange minValue="{$min}" maxValue="{$max}"/>
};

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

declare function x:hex($done as xs:string?, $todo as xs:integer) as xs:string
{
  if ($todo ne 0) then
    x:hex(concat(substring("0123456789ABCDEF", 1 + $todo mod 16, 1), $done), $todo idiv 16)
  else if (matches($done, "^1....$")) then
    replace($done, "^10{0,3}", "")
  else
    $done
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
  let $literal :=
    if (ends-with($literal, "i")) then
      substring($literal, 1, string-length($literal) - 1)
    else
      $literal
  let $unquoted := substring($literal, 2, string-length($literal) - 2)
  return x:unescape("", $unquoted)
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

declare function x:rewrite-CharacterSet($cl as element(CharacterSet)) as element()
{
  let $charClass :=
    <g:charClass>
    {
      for $cd in $cl/CharacterRange
      let $c1 := x:to-char(x:unescape("", $cd/Character[1]))
      return
        if (not($cd/TOKEN = "-")) then
          $c1
        else
          x:to-charRange($c1, x:to-char(x:unescape("", $cd/Character[2])))
    }
    </g:charClass>
  return
    if (not($cl/TOKEN = "^")) then
      $charClass
    else
      <g:complement>{$charClass}</g:complement>
};

declare function x:rewrite-Primary($item as element(Primary)) as element()*
{
  if ($item/StringLiteral) then
    x:split-string((), x:unquote($item/StringLiteral))
  else if ($item/TOKEN = ".") then
    <g:ref name="."/>
  else if ($item/CharacterSet) then
    x:rewrite-CharacterSet($item/CharacterSet)
  else if ($item/Name) then
    <g:ref name="{$item/Name/IdentifierName}"/>
  else if ($item/ParsingExpression) then
    x:rewrite-Alternatives($item/ParsingExpression/Alternative)
  else if ($item/TOKEN = ("<", ">")) then
    ()
  else
    error(xs:QName("x:rewrite-Primary"), concat("unsupported item: ", local-name($item)))
};

declare function x:rewrite-Expression($e as element(Expression)) as element()*
{
  let $rewrite := x:rewrite-Primary($e/Primary)
  return
    if ($e/TOKEN = "?") then
      <g:optional>{$rewrite}</g:optional>
    else if ($e/TOKEN = "+") then
      <g:oneOrMore>{$rewrite}</g:oneOrMore>
    else if ($e/TOKEN = "*") then
      <g:zeroOrMore>{$rewrite}</g:zeroOrMore>
    else
      $rewrite
};

declare function x:max-length($elements as element()*, $nonterminals) as xs:integer
{
  let $max-lengths :=
    if (empty($elements)) then
      -1
    else
      for $e in $elements
      return
        typeswitch ($e)
        case element(g:choice) return
          let $max-lengths := $e/*/x:max-length(., $nonterminals)
          return if ($max-lengths = -1) then -1 else xs:integer(max($max-lengths))
        case element(g:oneOrMore) return
          -1
        case element(g:zeroOrMore) return
          -1
        case element(g:optional) return
          x:max-length($e/*, $nonterminals)
        case element(g:production) return
          x:max-length($e/*, $nonterminals)
        case element(g:sequence) return
          x:max-length($e/*, $nonterminals)
        case element(g:subtract) return
          x:max-length($e/*[1], $nonterminals)
        case element(g:string) return
          string-length($e)
        case element(g:ref) return
          if ($e/@name = ".") then
            1
          else if ($e/@name = $nonterminals) then
            -1
          else
            x:max-length($e/ancestor::g:grammar/g:production[@name = $e/@name], ($e/@name, $nonterminals))
        case element(g:charClass) return
          1
        case element(g:charCode) return
          1
        case element(g:complement) return
          1
        default return
          error()
  return if ($max-lengths = -1) then -1 else xs:integer(sum($max-lengths))
};

declare function x:max-length($elements as element()*) as xs:integer
{
  x:max-length($elements, ())
};

declare function x:rewrite-items($done as element()*, $todo as element()*) as element()*
{
  if (empty($todo)) then
    $done
  else
    let $first := $todo[1]
    return
      if ($first/self::Expression) then
        x:rewrite-items(($done, x:rewrite-Expression($first)), $todo[position() > 1])
      else if ($first/self::Predicate[TOKEN = "!" and Expression]) then
      (
        $done,
        element g:subtract
        {
          let $remainder := element g:sequence {x:rewrite-items((), $todo[position() > 1])}
          return if (count($remainder/*) = 1) then $remainder/* else $remainder,
          let $predicate := element g:sequence {x:rewrite-Expression($first/Expression)}
          return if (count($predicate/*) = 1) then $predicate/* else $predicate
        }
      )
      else
        error()
};

declare function x:rewrite-Alternatives($alternatives as element(Alternative)+) as element()*
{
  let $choice :=
    <g:choice>
    {
      for $alternative in $alternatives
      let $rewrite :=
        <g:sequence>
        {
          let $items := $alternative/(Expression | Predicate[TOKEN = "!" and Expression])
          return x:rewrite-items((), $items)
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

declare function x:rewrite-subtract($nodes as node()*) as node()*
{
  for $e in $nodes
  return
    if ($e/self::g:subtract) then
      let $lhs-max := x:max-length($e/*[1])
      let $rhs-max := x:max-length($e/*[2])
      return
      (
        if ($lhs-max > 0 and $lhs-max = $rhs-max) then
          element g:subtract
          {
            for $c in $e/*
            let $rewrite := x:rewrite-subtract($c)
            return if (count($rewrite) = 1) then $rewrite else element g:sequence {$rewrite}
          }
        else if ($rhs-max > 0 and $lhs-max > $rhs-max) then
          element g:subtract
          {
            let $rewrite := x:rewrite-subtract($e/*[1])
            return if (count($rewrite) = 1) then $rewrite else element g:sequence {$rewrite},
            element g:sequence
            {
              x:rewrite-subtract($e/*[2]),
              element g:zeroOrMore
              {
                element g:ref
                {
                  attribute name {"."}
                }
              }
            }
          }
        else
          x:rewrite-subtract($e/*[1])
      )
    else if ($e/self::g:sequence) then
      x:rewrite-subtract($e/node())
    else if ($e/self::g:choice) then
      element g:choice
      {
        for $c in $e/*
        let $rewrite := x:rewrite-subtract($c)
        return if (count($rewrite) = 1) then $rewrite else element g:sequence {$rewrite}
      }
    else if ($e/self::element()) then
      element {node-name($e)} {$e/@*, x:rewrite-subtract($e/node())}
    else
      $e
};

declare function x:pegjs-to-w3c($parse-tree as element(Grammar)) as element(g:grammar)
{
  let $grammar :=
    x:rewrite-subtract
    (
      <g:grammar>
      {
        for $p in $parse-tree/Rule
        return element g:production {attribute name {$p/Name/IdentifierName}, x:rewrite-Alternatives($p/ParsingExpression/Alternative)}
      }
      </g:grammar>
    )
  let $tokens-productions := $grammar/*[.//g:charClass or .//g:charCode or .//g:ref/@name = "."]
  let $syntax-productions := $grammar/* except $tokens-productions
  return
    <g:grammar>
      {$syntax-productions}
      {processing-instruction TOKENS{}}
      {$tokens-productions}
    </g:grammar>
};
