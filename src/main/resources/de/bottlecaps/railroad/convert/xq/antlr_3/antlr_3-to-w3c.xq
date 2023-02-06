module namespace p="de/bottlecaps/railroad/convert/xq/antlr_3/antlr_3-to-w3c.xq";

import module namespace t="de/bottlecaps/railroad/convert/xq/to-w3c.xq" at "../to-w3c.xq";
declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function p:to-char($string as xs:string)
{
  (: mimic ANTLR behaviour, accepting longer strings from fragments/token refs.
   : the ANTLR restriction to single chars appears to be implemented in the syntax.
   :)
  let $string := substring($string, 1, 1)
  return

    if (string-length($string) ne 1) then
      error(xs:QName("p:to-char"), concat("unsupported string of length ", string(string-length($string)), ": ", $string))
    else if (matches($string, "[&#x20;-&#x7e;]")) then
      <g:char>{$string}</g:char>
    else
      <g:charCode value="{p:hex("", string-to-codepoints($string))}"/>
};

declare function p:to-charClass($nodes)
{
  for $node in $nodes
  return
    typeswitch ($node)
    case element(g:charClass) return
      p:to-charClass($node/*)
    case element(g:choice) return
      p:to-charClass($node/*)
    case element(g:string) return
      p:to-char(data($node))
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
      error(xs:QName("p:to-charClass"), concat("unsupported operand type: ", local-name($node)))
};

declare function p:render-charClass($component)
{
  if ($component/self::notTerminal/CHAR_LITERAL) then
    p:to-char(p:unquote($component/self::notTerminal/CHAR_LITERAL))
  else if ($component/self::notTerminal/STRING_LITERAL) then
    p:to-char(p:unquote($component/self::notTerminal/STRING_LITERAL))
  else if ($component/self::notTerminal/TOKEN_REF) then
    <g:ref name="{$component/self::notTerminal/TOKEN_REF}"/>
  else if ($component/self::block) then
    p:to-charClass(p:rewrite-item($component))
  else if ($component/self::range) then
    let $min := p:to-char(p:unquote($component/CHAR_LITERAL[1]))
    let $max := p:to-char(p:unquote($component/CHAR_LITERAL[2]))
    return
      if ($min/self::g:char and $max/self::g:char) then
        <g:charRange minChar="{data($min)}" maxChar="{data($max)}"/>
      else
        let $min := if ($min/self::g:charCode) then $min/@value else p:hex("", string-to-codepoints($min))
        let $max := if ($max/self::g:charCode) then $max/@value else p:hex("", string-to-codepoints($max))
        return <g:charCodeRange minValue="{$min}" maxValue="{$max}"/>
  else
    error(xs:QName("p:render-charClass"), concat("unsupported charclass component: ", local-name($component)))
};

declare function p:unhex($codepoint as xs:integer*, $value as xs:integer) as xs:integer
{
  if (empty($codepoint)) then
    $value
  else
    p:unhex
    (
      $codepoint[position() > 1],
      $codepoint[1] - (0, 0, 48, 55, 0, 87)[$codepoint[1] idiv 16] + $value * 16
    )
};

declare function p:hex($done, $todo as xs:integer)
{
  if ($todo ne 0) then
    p:hex(concat(substring("0123456789ABCDEF", 1 + $todo mod 16, 1), $done), $todo idiv 16)
  else if (matches($done, "^1....$")) then
    replace($done, "^10{0,3}", "")
  else
    $done
};

declare function p:unhex($hex as xs:string) as xs:string
{
  let $codepoint := p:unhex(string-to-codepoints($hex), 0)
  return
  (: [2] Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF] :)
    if ($codepoint ge 9 and $codepoint le 10
     or $codepoint eq 13
     or $codepoint ge 32 and $codepoint le 55295
     or $codepoint ge 57344 and $codepoint le 65533) then
      codepoints-to-string($codepoint)
    else
      codepoints-to-string(65536 + $codepoint)
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
      if ($c2 = "b") then p:unescape(concat($done, "&#x10008;" ), substring($todo, 3)) else
      if ($c2 = "f") then p:unescape(concat($done, "&#x1000C;" ), substring($todo, 3)) else
      if ($c2 = '"') then p:unescape(concat($done, '"'    ), substring($todo, 3)) else
      if ($c2 = "'") then p:unescape(concat($done, "'"    ), substring($todo, 3)) else
      if ($c2 = "\") then p:unescape(concat($done, "\"    ), substring($todo, 3)) else
      if ($c2 = ">") then error() else
      if ($c2 = "u") then p:unescape(concat($done, p:unhex(substring($todo, 3, 4))), substring($todo, 7)) else
      p:unescape(concat($done, substring($todo, 2, 1)), substring($todo, 3))
};

declare function p:unquote($literal)
{
  let $unquoted := substring($literal, 2, string-length($literal) - 2)
  return p:unescape("", $unquoted)
};

declare function p:split-string($done as node()*, $todo as xs:string)
{
  if ($todo = "") then
    $done
  else
    let $ascii := replace($todo, "[^&#x20;-&#x7e;].*$", "", "s")
    return
      if ($ascii = "") then
        p:split-string(($done, <g:charCode value="{p:hex("", string-to-codepoints(substring($todo, 1, 1)))}"/>), substring($todo, 2))
      else
        p:split-string(($done, <g:string>{$ascii}</g:string>), substring-after($todo, $ascii))
};

declare function p:rewrite-item($item as element())
{
  if ($item/self::block) then
    p:rewrite-choice($item/alternative)
  else if ($item/self::terminal) then
    p:rewrite-item($item/*)
  else if ($item/self::notTerminal) then
    p:rewrite-item($item/*)
  else if ($item/self::atom/ruleref/RULE_REF) then
    <g:ref name="{data($item/self::atom/ruleref/RULE_REF)}"/>
  else if ($item/self::TOKEN_REF) then
    <g:ref name="{data($item)}"/>
  else if ($item/self::CHAR_LITERAL) then
    p:split-string((), p:unquote($item))
  else if ($item/self::STRING_LITERAL) then
    p:split-string((), p:unquote($item))
  else if ($item/self::atom/range) then
    <g:charClass>{p:render-charClass($item/self::atom/range)}</g:charClass>
  else if ($item/self::atom/terminal) then
    p:rewrite-item($item/terminal/*[1])
  else if ($item/self::atom/notSet) then
    <g:complement>
    {
      if (p:is-parser-rule($item)) then
        p:rewrite-item($item/self::atom/notSet/(notTerminal | block))
      else
        <g:charClass>{p:render-charClass($item/self::atom/notSet/(notTerminal | block))}</g:charClass>
    }
    </g:complement>
  else if ($item/self::TOKEN eq ".") then
    <g:ref name="."/>
  else if ($item/self::TOKEN_REF) then
    <g:ref name="{data($item)}"/>
  else
    error(xs:QName("p:rewrite-item"), concat("unsupported item: ", local-name($item)))
};

declare function p:rule-name($rule as element(rule)) as xs:string
{
  data($rule/id/*)
};

declare function p:is-parser-rule($node as node()) as xs:boolean
{
  let $rule := $node/ancestor::rule
  let $initial := substring(p:rule-name($rule), 1, 1)
  return $initial != upper-case($initial)
};

declare function p:rewrite-element($e as element(elementNoOptionSpec))
{
  let $suffix := if ($e/ebnf) then $e/ebnf/TOKEN else $e/ebnfSuffix/TOKEN
  let $item := if ($e/ebnf) then $e/ebnf/block else ($e/atom, $e/block)
  return
    if ($suffix = "=>") then
      ()
    else if ($suffix = "?") then
      <g:optional>{p:rewrite-item($item)}</g:optional>
    else if ($suffix = "+") then
      <g:oneOrMore>{p:rewrite-item($item)}</g:oneOrMore>
    else if ($suffix = "*") then
      <g:zeroOrMore>{p:rewrite-item($item)}</g:zeroOrMore>
    else if ($item) then
      p:rewrite-item($item)
    else
      ()
};

declare function p:rewrite-choice($alt as element(alternative)*)
{
  let $choice :=
    <g:choice>
    {
      for $a in $alt
      let $element := $a/element/elementNoOptionSpec
      let $rewrite := <g:sequence>{for $e in $element return p:rewrite-element($e)}</g:sequence>
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

declare function p:isToken($p)
{
  let $initial := substring($p/@name, 1, 1)
  return $initial eq upper-case($initial)
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

declare function p:flatten-charsets($nodes as element()*, $stack as element(g:ref)*) as node()*
{
  for $node in $nodes
  return
    typeswitch ($node)
    case element(g:charClass) return
      p:flatten-charsets($node/*, $stack)
    case element(g:choice) return
      p:flatten-charsets($node/*, $stack)
    case element(g:string) return
      p:to-char(data($node))
    case element(g:char) return
      $node
    case element(g:charRange) return
      $node
    case element(g:charCode) return
      $node
    case element(g:charCodeRange) return
      $node
    case element(g:ref) return
      if ($node/@name = $stack) then
        error(xs:QName("p:flatten-charsets"), concat("character set contains recursive reference: ", $node/@name))
      else
        let $productions := $node/ancestor::g:grammar/g:production[@name = $node/@name]
        return
          if (empty($productions)) then
            error(xs:QName("p:flatten-charsets"), concat("failed to find definition of ", $node/@name))
          else
            for $production in $productions
            return
              if (count($production/*) = 1) then
                p:flatten-charsets($production/*, ($stack, $node))
              else
                error(xs:QName("p:flatten-charsets"), concat("cannot use ", $node/@name, " in a character set"))
    case element() return
      element {node-name($node)} {$node/@*, p:flatten-charsets($node/node(), $stack)}
    default return
      error(xs:QName("p:flatten-charsets"), concat("character set contains invalid construct: ", local-name($node)))
};

declare function p:flatten-charsets($nodes as node()*) as node()*
{
  for $node in $nodes
  return
    typeswitch ($node)
    case element(g:charClass) return
      element g:charClass
      {
        $node/@*, p:flatten-charsets($node/node(), ())
      }
    case element() return
      element {node-name($node)} {$node/@*, p:flatten-charsets($node/node())}
    default return
      $node
};

declare function p:translate-token-complements($nodes as node()*, $tokens as element()*) as node()*
{
  for $node in $nodes
  return
    if ($node/self::g:complement[empty(g:charClass)]) then
      let $cases :=
        for $t in $tokens
        return
          if ($t/self::g:production) then
            element g:ref {$t/@name}[not(@name = $node//g:ref/@name)]
          else
            $t[not(. = $node//g:string)]
      where $cases
      return element g:choice {$cases}
    else if ($node/self::element()) then
      element {node-name($node)}
      {
        $node/@*,
        for $n in $node/node() return p:translate-token-complements($n, $tokens)
      }
    else
      $node
};

declare function p:antlr_3-to-w3c($parse-tree)
{
  let $productions :=
    p:flatten-charsets
    (
      <g:grammar>
      {
        for $tokenSpec in $parse-tree//tokenSpec
        where $tokenSpec/TOKEN = "="
        return
          <g:production name="{$tokenSpec/TOKEN_REF}">
            <g:string>{p:unquote($tokenSpec/(CHAR_LITERAL|STRING_LITERAL))}</g:string>
          </g:production>,
        for $rule in $parse-tree//rule
        let $body := p:rewrite-choice($rule/ruleAltList/alternative)
        let $non-greedy :=
          exists($rule//option[translate(., " &#9;&#10;&#13;", "") = "greedy=false"]) or
          exists($body/descendant-or-self::g:zeroOrMore/g:ref[@name = "." and count(../*) = 1]) or
          exists($body/descendant-or-self::g:oneOrMore/g:ref[@name = "." and count(../*) = 1])
        let $doc-comment := $rule/DOC_COMMENT
        return
        (
          if (exists($doc-comment)) then
            comment
            {
              concat
              (
                "~&#xA;",
                substring(replace($doc-comment, "&#xA;\s*\*", ""), 4, string-length($doc-comment) - 5),
                "&#xA;"
              )
            }
          else
            (),
          <g:production name="{p:rule-name($rule)}">
            {attribute nongreedy {true()}[$non-greedy]}
            {$body}
          </g:production>
        )
      }
      </g:grammar>
    )/g:production
  let $syntax-productions := $productions[not(p:isToken(.))]
  let $tokens-productions := $productions[    p:isToken(.) ]

  let $ignore-production :=
    let $whitespace :=
      for $rule in $parse-tree//rule
      where $rule//ACTION[translate(., " &#9;&#10;&#13;", "")[contains(., "skip();") or contains(., "$channel=HIDDEN;")]]
      return p:rule-name($rule)
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

  let $syntax-productions := ($syntax-productions, $ignore-production)
  let $productions := ($syntax-productions, $tokens-productions)

  let $strings :=
    for $d in distinct-values($syntax-productions//g:string)
    return <g:string>{$d}</g:string>

  let $nonterminals := data($syntax-productions/@name)
  let $token-refs := distinct-values($syntax-productions//g:ref/@name)[not(. = $nonterminals)]
  let $tokens := data($tokens-productions/@name)[. = $token-refs]
  let $eof-production :=
    if (exists($syntax-productions//g:ref[@name = "EOF"]) and empty($productions[@name = "EOF"])
     or exists($tokens-productions//g:ref[@name = "EOF"]) and empty($tokens-productions[@name = "EOF"])) then
      <g:production name="EOF"><g:ref name="$"/></g:production>
    else
      ()

  let $syntax-productions := p:translate-token-complements($syntax-productions, ($strings, $tokens-productions[@name = $tokens]))
  let $tokens-productions := ($tokens-productions, $eof-production)
  let $productions := ($syntax-productions, $tokens-productions)

  return
    element g:grammar
    {
      $syntax-productions,
      processing-instruction TOKENS{},
      $tokens-productions
    }
};
