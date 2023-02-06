module namespace p="de/bottlecaps/railroad/convert/xq/antlr_4/antlr_4-to-w3c.xq";

import module namespace t="de/bottlecaps/railroad/convert/xq/to-w3c.xq" at "../to-w3c.xq";
import module namespace b="de/bottlecaps/railroad/xq/ast-to-ebnf.xq" at "../../../xq/ast-to-ebnf.xq";
declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function p:to-char($string as xs:string)
{
  (: mimic ANTLR behaviour, accepting longer strings from fragments/token refs.
   : the ANTLR restriction to single chars appears to be implemented in the syntax.
   :)
  if (starts-with($string, "\t")) then
    <g:charCode value="9"/>
  else if (starts-with($string, "\r")) then
    <g:charCode value="d"/>
  else if (starts-with($string, "\n")) then
    <g:charCode value="a"/>
  else if (starts-with($string, "\f")) then
    <g:charCode value="c"/>
  else if (starts-with($string, "\p") or starts-with($string, "\P")) then
    error(xs:QName("p:to-char"), concat("unsupported character: ", $string))
  else if (starts-with($string, "\u{")) then
    <g:charCode value="{substring($string, 4, string-length($string) - 4)}"/>
  else if (starts-with($string, "\u")) then
    <g:charCode value="{substring($string, 3)}"/>
  else if (starts-with($string, "\")) then
    let $char := substring($string, 2, 1)
    return
      if (matches($char, "[a-zA-Z]")) then
        error(xs:QName("p:to-char"), concat("unsupported character: ", $string))
      else
        <g:char>{$char}</g:char>
  else
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

declare function p:render-charClass($component as element())
{
  if ($component/self::STRING_LITERAL) then
    p:to-char(p:unquote($component))
  else if ($component/TOKEN_REF) then
    <g:ref name="{$component}"/>
  else if ($component/self::range) then
    let $min := p:to-char(p:unquote($component/STRING_LITERAL[1]))
    let $max := p:to-char(p:unquote($component/STRING_LITERAL[2]))
    return
      if ($min/self::g:char and $max/self::g:char) then
        <g:charRange minChar="{data($min)}" maxChar="{data($max)}"/>
      else
        let $min := if ($min/self::g:charCode) then $min/@value else p:hex("", string-to-codepoints($min))
        let $max := if ($max/self::g:charCode) then $max/@value else p:hex("", string-to-codepoints($max))
        return <g:charCodeRange minValue="{$min}" maxValue="{$max}"/>
  else if ($component/self::LEXER_CHAR_SET) then
    $component/LEXER_CHAR_RANGE!p:rewrite-item(.)
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
    p:rewrite-choice($item/altList/alternative)
  else if ($item/self::lexerBlock) then
    p:rewrite-lexer-choice($item/lexerAltList/lexerAlt/lexerElements)
  else if ($item/self::atom/terminal or $item/self::lexerAtom/terminal) then
    p:rewrite-item($item/terminal)
  else if ($item/self::terminal) then
    p:rewrite-item($item/*[1])
  else if ($item/self::TOKEN_REF) then
    <g:ref name="{data($item)}"/>
  else if ($item/self::STRING_LITERAL) then
    p:split-string((), p:unquote($item))
  else if ($item/self::atom/ruleref/RULE_REF) then
    <g:ref name="{data($item/self::atom/ruleref/RULE_REF)}"/>
  else if ($item/self::lexerAtom/RULE_REF) then
    <g:ref name="{data($item/RULE_REF)}"/>
  else if ($item/self::atom/range or $item/self::lexerAtom/range) then
    <g:charClass>{p:render-charClass($item/range)}</g:charClass>
  else if ($item/self::atom/notSet) then
    <g:complement>{p:rewrite-setElement-choice(($item/notSet/setElement, $item/notSet/blockSet/setElement))}</g:complement>
  else if ($item/self::lexerAtom/notSet) then
    <g:complement>{<g:charClass>{($item/notSet/setElement, $item/notSet/blockSet/setElement)/*!p:render-charClass(.)}</g:charClass>}</g:complement>
  else if ($item/self::lexerAtom/wildcard) then
    <g:ref name="."/>
  else if ($item/self::atom/wildcard) then
    <g:ref name="_any_token"/>
  else if ($item/self::lexerAtom/LEXER_CHAR_SET) then
    <g:charClass>{$item/LEXER_CHAR_SET/LEXER_CHAR_RANGE!p:rewrite-item(.)}</g:charClass>
  else if ($item/self::LEXER_CHAR_RANGE) then
    let $chars := $item/LEXER_CHAR
    return
      if (empty($chars[2])) then
        p:to-char($item)
      else
        let $min := p:to-char($chars[1])
        let $max := p:to-char($chars[2])
        return
          if ($min/self::g:char and $max/self::g:char) then
            <g:charRange minChar="{data($min)}" maxChar="{data($max)}"/>
          else
            let $min := if ($min/self::g:charCode) then $min/@value else p:hex("", string-to-codepoints($min))
            let $max := if ($max/self::g:charCode) then $max/@value else p:hex("", string-to-codepoints($max))
            return <g:charCodeRange minValue="{$min}" maxValue="{$max}"/>
  else
    error(xs:QName("p:rewrite-item"), concat("unsupported item: ", string-join((local-name($item), local-name($item/*[1])), "/")))
};

declare function p:rule-name($rule) as xs:string
{
  data($rule/(RULE_REF, TOKEN_REF))
};

declare function p:is-parser-rule($node as node()) as xs:boolean
{
  exists($node/ancestor::parserRule)
};

declare function p:rewrite-element($e as element(element))
{
  let $suffix := if ($e/ebnf) then $e/ebnf/blockSuffix/ebnfSuffix/TOKEN[1] else $e/ebnfSuffix/TOKEN[1]
  let $item := if ($e/ebnf) then $e/ebnf/block else ($e/atom, $e/labeledElement/atom, $e/labeledElement/block)
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

declare function p:rewrite-lexer-element($e as element(lexerElement))
{
  let $suffix := $e/ebnfSuffix/TOKEN[1]
  let $item := if ($e/lexerBlock) then $e/lexerBlock else ($e/lexerAtom, $e/labeledLexerElement/lexerAtom, $e/labeledLexerElement/lexerBlock)
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

declare function p:rewrite-setElement-choice($alt as element(setElement)+)
{
  let $choice :=
    <g:choice>
    {
      for $a in $alt
      let $rewrite := <g:sequence>{$a/*!p:rewrite-item(.)}</g:sequence>
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

declare function p:rewrite-choice($alt as element(alternative)*)
{
  let $choice :=
    <g:choice>
    {
      for $a in $alt
      let $element := $a/element
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

declare function p:rewrite-lexer-choice($alt as element(lexerElements)+)
{
  let $choice :=
    <g:choice>
    {
      for $a in $alt
      let $element := $a/lexerElement
      let $rewrite := <g:sequence>{for $e in $element return p:rewrite-lexer-element($e)}</g:sequence>
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

declare function p:isToken($name)
{
  let $initial := substring($name, 1, 1)
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

declare function p:antlr_4-to-w3c($parse-tree)
{
  let $productions :=
    p:flatten-charsets
    (
      <g:grammar>
      {
        for $rule in $parse-tree//(parserRule, lexerRule)
        let $name := p:rule-name($rule)
        let $body :=
          if ($rule/self::parserRule) then
            p:rewrite-choice($rule/ruleBlock/ruleAltList/labeledAlt/alternative)
          else
            p:rewrite-lexer-choice($rule/lexerRuleBlock/lexerAltList/lexerAlt/lexerElements)
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
          <g:production name="{$name}">
            {attribute nongreedy {true()}[$non-greedy]}
            {$body}
          </g:production>
        ),
        for $tokenSpec in $parse-tree//v3tokenSpec
        where $tokenSpec/TOKEN = "="
        return
          <g:production name="{$tokenSpec/id}">
            <g:string>{p:unquote($tokenSpec/STRING_LITERAL)}</g:string>
          </g:production>
      }
      </g:grammar>
    )/g:production
  let $syntax-productions := $productions[not(p:isToken(@name))]
  let $tokens-productions := $productions[    p:isToken(@name) ]

  let $ignore-production :=
    let $whitespace :=
      for $rule in $parse-tree//(parserRule, lexerRule)
      where $rule//lexerCommand[translate(., " &#x9;&#xa;&#xd;", "") = ("skip", "channel(HIDDEN)")]
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

  let $tokens :=
    for $t in distinct-values($syntax-productions//g:ref/@name)[p:isToken(.) and not(. = ("_any_token", "EOF"))]
    order by $t
    return <g:production name="{$t}"></g:production>
  let $strings :=
    for $d in distinct-values($syntax-productions//g:string)
    order by $d
    return <g:string>{$d}</g:string>

  let $syntax-productions :=
    if (exists($syntax-productions//g:ref[@name = "_any_token"])) then
      ($syntax-productions, <g:production name="_any_token"><g:complement/></g:production>)
    else
      $syntax-productions

  let $syntax-productions := p:translate-token-complements($syntax-productions, ($strings, $tokens))

  let $tokens-productions :=
    if (exists($syntax-productions//g:ref[@name = "EOF"]) and empty($tokens-productions[@name = "EOF"])
     or exists($tokens-productions//g:ref[@name = "EOF"]) and empty($tokens-productions[@name = "EOF"])) then
      ($tokens-productions, <g:production name="EOF"><g:ref name="$"/></g:production>)
    else
      $tokens-productions

  return
    element g:grammar
    {
      $syntax-productions,
      processing-instruction TOKENS{},
      $tokens-productions
    }
};
