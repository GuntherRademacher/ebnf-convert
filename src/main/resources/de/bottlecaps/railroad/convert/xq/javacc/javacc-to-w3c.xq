module namespace p="de/bottlecaps/railroad/convert/xq/javacc/javacc-to-w3c.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

import module namespace b="de/bottlecaps/railroad/xq/ast-to-ebnf.xq" at "../../../xq/ast-to-ebnf.xq";
import module namespace t="de/bottlecaps/railroad/convert/xq/to-w3c.xq" at "../to-w3c.xq";

declare function p:to-char($string as xs:string) as element()
{
  if (string-length($string) ne 1) then
    error(xs:QName("p:to-char"), concat("unsupported string of length ", string(string-length($string)), ": ", $string))
  else if (matches($string, "[&#x20;-&#x7e;]")) then
    <g:char>{$string}</g:char>
  else
    <g:charCode value="{t:to-charCode($string)}"/>
};

declare function p:to-charRange($min as element(), $max as element()) as element()
{
  if ($min/self::g:char and $max/self::g:char) then
    <g:charRange minChar="{data($min)}" maxChar="{data($max)}"/>
  else
    let $min := if ($min/self::g:charCode) then $min/@value else t:to-charCode($min)
    let $max := if ($max/self::g:charCode) then $max/@value else t:to-charCode($max)
    return <g:charCodeRange minValue="{$min}" maxValue="{$max}"/>
};

declare function p:unescape($done, $todo)
{
  if (not(contains($todo, "\"))) then
    concat($done, $todo)
  else if (not(starts-with($todo, "\"))) then
    p:unescape(concat($done, substring-before($todo, "\")), concat("\", substring-after($todo, "\")))
  else
    let $c2 := substring($todo, 2, 1)
    return
      if ($c2 = "n") then p:unescape(concat($done, "&#10;"), substring($todo, 3)) else
      if ($c2 = "r") then p:unescape(concat($done, "&#13;"), substring($todo, 3)) else
      if ($c2 = "t") then p:unescape(concat($done, "&#9;" ), substring($todo, 3)) else
      if ($c2 = "b") then p:unescape(concat($done, t:codepoint-to-string( 8)), substring($todo, 3)) else
      if ($c2 = "f") then p:unescape(concat($done, t:codepoint-to-string(12)), substring($todo, 3)) else
      if ($c2 = '"') then p:unescape(concat($done, '"'    ), substring($todo, 3)) else
      if ($c2 = "'") then p:unescape(concat($done, "'"    ), substring($todo, 3)) else
      if ($c2 = "\") then p:unescape(concat($done, "\"    ), substring($todo, 3)) else
      if ($c2 = ">") then error() else
      if ($c2 = "u") then p:unescape(concat($done, t:codepoint-to-string(t:int-value(16, substring($todo, 3, 4)))), substring($todo, 7)) else
      p:unescape(concat($done, substring($todo, 2, 1)), substring($todo, 3))
};

declare function p:unquote($literal)
{
  let $unquoted := substring($literal, 2, string-length($literal) - 2)
  return p:unescape("", $unquoted)
};

declare function p:rewrite-regular_expression($r as element(regular_expression))
{
  if ($r/identifier) then
    p:rewrite-item($r/identifier)
  else if ($r/complex_regular_expression_choices) then
    p:rewrite-item($r/complex_regular_expression_choices)
  else if ($r/TOKEN = "EOF") then
    <g:ref name="EOF"/>
  else
    p:rewrite-item($r/StringLiteral)
};

declare function p:rewrite-character_list($cl as element(character_list))
{
  let $charClass :=
    <g:charClass>
    {
      for $cd in $cl/character_descriptor
      let $c1 := p:to-char(p:unquote($cd/StringLiteral[1]/STRING_LITERAL))
      return
        if (not($cd/TOKEN = "-")) then
          $c1
        else
          p:to-charRange($c1, p:to-char(p:unquote($cd/StringLiteral[2]/STRING_LITERAL)))
    }
    </g:charClass>
  return
    if (not($cl/TOKEN = "~")) then
      $charClass
    else
      <g:complement>{$charClass}</g:complement>
};

declare function p:rewrite-item($item as element())
{
  if ($item/self::expansion_choices) then
    p:rewrite-choice($item/expansion)
  else if ($item/self::complex_regular_expression_choices) then
    p:rewrite-choice($item/complex_regular_expression)
  else if ($item/self::identifier) then
    <g:ref name="{$item/IDENTIFIER}"/>
  else if ($item/self::regular_expression) then
    p:rewrite-regular_expression($item)
  else if ($item/self::StringLiteral) then
    t:split-string(p:unquote($item/STRING_LITERAL))
  else if ($item/self::identifier) then
    <g:ref name="{$item/IDENTIFIER}"/>
  else if ($item/self::character_list) then
    p:rewrite-character_list($item)
  else
    error(xs:QName("p:rewrite-item"), concat("unsupported item: ", local-name($item)))
};

declare function p:rewrite-unit($e as element())
{
  let $item := $e/(expansion_choices | identifier | regular_expression | StringLiteral | character_list | complex_regular_expression_choices)

  return if (local-name($e) != "expansion_unit" and count($item) = 0) then error(xs:QName("p:rewrite-unit"), concat("no item found in ", local-name($e))) else

  let $rewrite := if ($item) then p:rewrite-item($item) else ()
  return
    if ($e/TOKEN = ("?", "]")) then
      <g:optional>{$rewrite}</g:optional>
    else if ($e/TOKEN = "+") then
      <g:oneOrMore>{$rewrite}</g:oneOrMore>
    else if ($e/TOKEN = "*") then
      <g:zeroOrMore>{$rewrite}</g:zeroOrMore>
    else if ($e/TOKEN = "{") then
      let $i1 := p:integer-literal-value($e/IntegerLiteral[1]/INTEGER_LITERAL)
      let $i2 := p:integer-literal-value($e/IntegerLiteral[2]/INTEGER_LITERAL)
      return
        if (exists($i2)) then
          t:repeat($rewrite, $i1, $i2)
        else if ($e/TOKEN = ",") then
          t:repeat($rewrite, $i1, 2147483647)
        else if (exists($i1)) then
          t:repeat($rewrite, $i1, $i1)
        else
          $rewrite
    else
      $rewrite
};

declare function p:integer-literal-value($literal as element(INTEGER_LITERAL)?) as xs:integer?
{
  if (empty($literal)) then
    ()
  else
    let $literal := replace($literal, "[lL]$", "")
    return
      if (matches($literal, "^0[xX]")) then
        t:int-value(16, substring($literal, 3))
      else if (starts-with($literal, "0")) then
        t:int-value(8, $literal)
      else
        t:int-value(10, $literal)
};

declare function p:rewrite-choice($alternatives as element()+)
{
  let $choice :=
    <g:choice>
    {
      for $alternative in $alternatives
      let $rewrite :=
        <g:sequence>
        {
          let $units := $alternative/*[ends-with(local-name(), "_unit")]

          return if (count($units) = 0) then error(xs:QName("p:rewrite-choice"), concat("no units found in ", local-name($alternative))) else

          for $u in $units return p:rewrite-unit($u)
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

declare function p:constant($g, $productions)
{
  typeswitch ($g)
  case empty-sequence() return
    ()
  case element(g:ref) return
    if ($g/@name = "$") then
      ""
    else
      p:constant($productions[@name = $g/@name], $productions)
  case element(g:string) return data($g)
  case element (g:production) return
    let $children := for $c in $g/* return p:constant($c, $productions)
    where count($children) = count($g/*)
    return string-join($children, "")
  default return ()
};

declare function p:prefix($g, $productions)
{
  let $c := p:constant($g, $productions)
  return
    if (exists($c)) then
      $c
    else
      typeswitch ($g)
      case empty-sequence() return
        ()
      case element(g:ref) return
        if ($g/@name = "$") then
          ""
        else
          p:constant($productions[@name = $g/@name], $productions)
      case element(g:string) return data($g)
      case element (g:production) return
        let $prefix-end :=
          (for $c at $i in $g/* where empty(p:constant($c, $productions)) return $i)[1]
        where exists($prefix-end)
        return string-join(for $c in $g/*[position() < $prefix-end] return p:constant($c, $productions), "")
      default return ()
};

declare function p:prefix-mismatch($a, $b)
{
  let $la := string-length($a)
  let $lb := string-length($b)
  let $min := min(($la, $lb))
  return $la > 0 and $lb > 0 and substring($a, 1, $min) != substring($b, 1, $min)
};

declare function p:preference($nonpreferred, $preferred, $productions)
{
  let $c1 := p:constant($nonpreferred, $productions)
  let $c2 := p:constant(   $preferred, $productions)
  where $c1 = $c2
     or not($c1 != $c2) and not(p:prefix-mismatch(p:prefix($nonpreferred, $productions), p:prefix($preferred, $productions)))
  return
    element g:preference
    {
      if ($nonpreferred/self::g:production) then element g:ref {$nonpreferred/@name} else $nonpreferred,
      if (   $preferred/self::g:production) then element g:ref {   $preferred/@name} else    $preferred
    }
};

declare function p:javacc-to-w3c($parse-tree)
{
  let $syntax-productions :=
  (
    for $p in $parse-tree//bnf_production
    return element g:production {attribute name {$p/identifier/IDENTIFIER}, p:rewrite-choice($p/expansion_choices/expansion)}
  )
  let $tokens-productions :=
  (
    for $p in $parse-tree//regular_expression[identifier and complex_regular_expression_choices]
    return element g:production {attribute name {$p/identifier/IDENTIFIER}, p:rewrite-item($p/complex_regular_expression_choices)},
    let $ws :=
      for $regexp in
        $parse-tree//regular_expr_production[IDENTIFIER = "DEFAULT" and regexpr_kind/TOKEN = ("SKIP", "SPECIAL_TOKEN", "MORE")]
        /regexpr_spec/regular_expression
      return
        if ($regexp/StringLiteral) then
          let $string := t:split-string(p:unquote($regexp/StringLiteral/STRING_LITERAL))
          return
            if (count($string) eq 1) then
              $string
            else
              <g:sequence>{$string}</g:sequence>
        else if ($regexp/identifier/IDENTIFIER) then
          <g:ref name="{$regexp/identifier/IDENTIFIER}"/>
        else if ($regexp/TOKEN = "EOF") then
          <g:ref name="EOF"/>
        else if ($regexp/complex_regular_expression_choices) then
          let $rewrite := p:rewrite-item($regexp/complex_regular_expression_choices)
          return
            if (count($rewrite) eq 1) then
              $rewrite
            else
              <g:sequence>{$rewrite}</g:sequence>
        else
          error(xs:QName("p:javaCC-to-xml"), concat("unsupported SKIP regular expression: ", string($regexp)))
    where $ws
    return
      element g:production
      {
        attribute name {"_"},
        attribute whitespace-spec {"definition"},
        if (count($ws) ne 1) then
          element g:choice
          {
            for $alternative in $ws
            return if ($alternative/self::g:choice) then $alternative/* else $alternative
          }
        else if ($ws/self::g:sequence) then
          $ws/*
        else
          $ws
      }
  )
  let $productions := ($syntax-productions, $tokens-productions)
  let $ignore-production :=
    let $whitespace :=
      for $rule in $parse-tree//rule
      where $rule//ACTION[translate(., " &#9;&#10;&#13;", "")[contains(., "skip();") or contains(., "$channel=HIDDEN;")]]
      return data($rule/id/*)
    where exists($whitespace)
    return
      <g:production name="_" whitespace-spec="definition">
      {
        if (count($whitespace) eq 1) then
          <g:ref name="{$whitespace}"/>
        else
          <g:choice>{for $w in $whitespace return <g:ref name="{$w}"/>}</g:choice>
      }
      </g:production>
  let $preferences :=
(:
    let $strings := for $d in distinct-values($syntax-productions//g:string) return <g:string>{$d}</g:string>
    let $nonterminals := data($syntax-productions/@name)
    let $token-refs := distinct-values($syntax-productions//g:ref/@name)[not(. = $nonterminals)]
    let $tokens := data($tokens-productions/@name)[. = $token-refs]
    for $t at $i in $tokens
    return
    (
      for $s in $strings      return p:preference($tokens-productions[@name = $t], $s, $tokens-productions),
      for $j in 1 to ($i - 1) return
      p:preference($tokens-productions[@name = $t], $tokens-productions[@name = $tokens[$j]], $tokens-productions)
    )
:)()
  let $eof-production :=
    if (exists($syntax-productions//g:ref[@name = "EOF"]) and empty($productions[@name = "EOF"])
     or exists($tokens-productions//g:ref[@name = "EOF"]) and empty($tokens-productions[@name = "EOF"])) then
      <g:production name="EOF"><g:ref name="$"/></g:production>
    else
      ()
  return
    <g:grammar>
      {$syntax-productions, $ignore-production}
      {processing-instruction TOKENS{}}
      {$tokens-productions, $eof-production}
      {$preferences}
    </g:grammar>
};
