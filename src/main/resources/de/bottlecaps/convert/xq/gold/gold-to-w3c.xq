module namespace p="de/bottlecaps/convert/xq/gold/gold-to-w3c.xq";

(:~ TODO:

  - check why

        <Op Assign>  ::= <Op If> '='   <Op Assign>
                     | <Op If> '+='  <Op Assign>
                     | <Op If> '-='  <Op Assign>
                     | <Op If> '*='  <Op Assign>
                     | <Op If> '/='  <Op Assign>
                     | <Op If> '^='  <Op Assign>
                     | <Op If> '&='  <Op Assign>
                     | <Op If> '|='  <Op Assign>
                     | <Op If> '>>=' <Op Assign>
                     | <Op If> '<<=' <Op Assign>
                     | <Op If>

    translates to

        Op_Assign
               ::= Op_If ( Op_If '=' | Op_If '+=' | Op_If '-=' | Op_If '*=' | Op_If '/=' | Op_If '^=' | Op_If '&=' | Op_If '|=' | Op_If '>>=' | Op_If '<<=' )*

  - verify we can parse

        int test_inc1(void a)
        {
          int t;
          t = 2 + 2;
          return 0;
        }

  - possibly without the 'a'

  - maybe add EOF

  - add WS like this: _        ::= Comment | WS
                      WS ::= [ #x9#xA#xD]+

:)

import module namespace q="de/bottlecaps/convert/xq/gold/gold.xquery" at "gold.xquery";
import module namespace t="de/bottlecaps/convert/xq/to-w3c.xq" at "../to-w3c.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare variable $p:predefined := <gold><![CDATA[
! Common Characters

{HT} = {#09}
{LF} = {#10}
{VT} = {#11}
{FF} = {#12}
{CR} = {#13}
{Space} = {#32}
{NBSP} = {#160}
{Euro Sign} = {#8364}

! Common Character Sets

{Number} = [0123456789]
{Digit} = [0123456789]
{Letter} = [abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]
{AlphaNumeric} = {Letter} + {Number}
{Printable} = {#32..#126} + {NBSP}
{Letter Extended} = {&C0 .. &FF} - {&D7} - {&F7}
{Printable Extended} = {&A1 .. &FF}
{Whitespace} = {Space} + {HT} + {LF} + {VT} + {FF} + {CR} + {NBSP}

! Language Sets

{Latin Extended} = {#256 .. #687}
{Greek} = {#880 .. #1023}
{Cyrillic} = {#1024 .. #1279}
{Cyrillic Supplementary} = {#1280 .. #1327}
{Armenian} = {#1328 .. #1423}
{Hebrew} = {#1424 .. #1535}
{Arabic} = {#1536 .. #1791}
{Syriac} = {#1792 .. #1871}
{Thaana} = {#1920 .. #1983}
{Devanagari} = {#2304 .. #2431}
{Bengali} = {#2432 .. #2559}
{Gurmukhi} = {#2560 .. #2687}
{Gujarati} = {#2688 .. #2815}
{Oriya} = {#2816 .. #2943}
{Tamil} = {#2944 .. #3071}
{Telugu} = {#3072 .. #3199}
{Kannada} = {#3200 .. #3327}
{Malayalam} = {#3328 .. #3455}
{Sinhala} = {#3456 .. #3583}
{Thai} = {#3584 .. #3711}
{Lao} = {#3712 .. #3839}
{Tibetan} = {#3840 .. #4095}
{Myanmar} = {#4096 .. #4255}
{Georgian} = {#4256 .. #4351}
{Hangul Jamo} = {#4352 .. #4607}
{Ethiopic} = {#4608 .. #4991}
{Cherokee} = {#5024 .. #5119}
{Ogham} = {#5760 .. #5791}
{Runic} = {#5792 .. #5887}
{Tagalog} = {#5888 .. #5919}
{Hanunoo} = {#5920 .. #5951}
{Buhid} = {#5952 .. #5983}
{Tagbanwa} = {#5984 .. #6015}
{Khmer} = {#6016 .. #6143}
{Mongolian} = {#6144 .. #6319}
{Latin Extended Additional} = {#7680 .. #7935}
{Greek Extended} = {#7936 .. #8191}
{Hiragana} = {#12352 .. #12447}
{Katakana} = {#12448 .. #12543}
{Bopomofo} = {#12544 .. #12591}
{Kanbun} = {#12688 .. #12703}
{Bopomofo Extended} = {#12704 .. #12735}

! Miscellaneous Character Sets

{All Valid} = {&1 .. &D7FF} + {&DC00 .. &FFEF}
{ANSI Mapped} = {#128 .. #159}
{ANSI Printable} = {Printable} + {Printable Extended} + {ANSI Mapped}
{Control Codes} = {#1 .. #31} + {#127 .. #159}
]]></gold>;

declare function p:to-char($string as xs:string) as element()
{
  if (string-length($string) ne 1) then
    error(xs:QName("p:to-char"), concat("unsupported string of length ", string(string-length($string)), ": ", $string))
  else if (matches($string, "[&#x20;-&#x7e;]")) then
    <g:char>{$string}</g:char>
  else
    <g:charCode value="{p:hex("", string-to-codepoints($string))}"/>
};

declare function p:to-charRange($min as element(), $max as element()) as element()
{
  if ($min/self::g:char and $max/self::g:char) then
    <g:charRange minChar="{data($min)}" maxChar="{data($max)}"/>
  else
    let $min := if ($min/self::g:charCode) then $min/@value else p:hex("", string-to-codepoints($min))
    let $max := if ($max/self::g:charCode) then $max/@value else p:hex("", string-to-codepoints($max))
    return <g:charCodeRange minValue="{$min}" maxValue="{$max}"/>
};

declare function p:codepoint-to-string($codepoint as xs:integer) as xs:string
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

declare function p:unhex($hex as xs:string) as xs:string
{
  let $codepoint := p:unhex(string-to-codepoints($hex), 0)
  return p:codepoint-to-string($codepoint)
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

declare function p:rewrite-Set_Exp($done as element()?, $todo as element()*) as element()?
{
  if (empty($todo)) then
    $done
  else
    p:rewrite-Set_Exp
    (
      let $item := $todo[1]
      let $rewrite := p:rewrite-item($item/*)
      return
        if (empty($done)) then
          $rewrite
        else
          let $operator := data($item/preceding-sibling::TOKEN)
          return
            if ($operator = "-") then
              <g:subtract>{$done, $rewrite}</g:subtract>
            else if ($operator != "+") then
              error()
            else if ($done/self::g:choice) then
              <g:choice>{$done/*, $rewrite}</g:choice>
            else
              <g:choice>{$done, $rewrite}</g:choice>,
      $todo[position() > 1]
    )
};

declare function p:rewrite-SetLiteral($literal as element()) as element(g:charClass)
{
  <g:charClass>
  {
    let $set := replace($literal, "^\[(.*)\]$", "$1", "s")
    let $set := replace($set, "'([^']+)'", "$1")
    let $set := replace($set, "''", "'")
    for $codepoint in string-to-codepoints($set)
    return <g:char>{codepoints-to-string($codepoint)}</g:char>
  }
  </g:charClass>
};

declare function p:set-constant-char($c as xs:string) as xs:string?
{

  if (starts-with($c, "#")) then
    p:codepoint-to-string(xs:integer(substring($c, 2)))
  else if (starts-with($c, "&amp;")) then
    p:unhex(substring($c, 2))
  else
    ()
};

declare function p:rewrite-set-ref($set-name as element(SetName)) as element()
{
  let $set-constant-pattern := "^\{\s*(#[0-9]+|&amp;[0-9A-Fa-f]+)\s*(\.\.\s*(#[0-9]+|&amp;[0-9A-Fa-f]+)\s*)?\}$"
  return
    if (matches($set-name, $set-constant-pattern)) then
      <g:charClass>
      {
        let $c1 := p:set-constant-char(replace($set-name, $set-constant-pattern, "$1"))
        let $c2 := p:set-constant-char(replace($set-name, $set-constant-pattern, "$3"))
        return if ($c2) then p:to-charRange(p:to-char($c1), p:to-char($c2)) else p:to-char($c1)
      }
      </g:charClass>
    else
      <g:ref name="{{{p:unbrace($set-name)}}}"/>
};

declare function p:rewrite-item($item as element())
{
  if ($item/self::Nonterminal) then
    <g:ref name="{p:unangle($item)}"/>
  else if ($item/self::Terminal) then
    if (starts-with($item, "'")) then
      <g:string>{data(p:rewrite-SetLiteral($item))}</g:string>
    else if (root($item)//Terminal_Decl/Terminal_Name/Terminal[lower-case(.) = lower-case($item)]) then
      <g:ref name="{$item}"/>
    else
      <g:string>{$item}</g:string>
  else if ($item/self::SetLiteral) then
    p:rewrite-SetLiteral($item)
  else if ($item/self::SetName) then
    p:rewrite-set-ref($item)
  else if ($item/self::Reg_Exp_2) then
    p:rewrite-choice($item/Reg_Exp_Seq)
  else
    error(xs:QName("p:rewrite-item"), concat("unsupported item: ", local-name($item)))
};

declare function p:rewrite-unit($e as element(Reg_Exp_Item))
{
  let $item := $e/(SetLiteral | SetName | Terminal | Reg_Exp_2)
  let $rewrite := if ($item) then p:rewrite-item($item) else ()
  return
    if ($e/Kleene_Opt/TOKEN = "?") then
      <g:optional>{$rewrite}</g:optional>
    else if ($e/Kleene_Opt/TOKEN = "+") then
      <g:oneOrMore>{$rewrite}</g:oneOrMore>
    else if ($e/Kleene_Opt/TOKEN = "*") then
      <g:zeroOrMore>{$rewrite}</g:zeroOrMore>
    else
      $rewrite
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
          if ($alternative/self::Reg_Exp_Seq) then
            let $units := $alternative/Reg_Exp_Item
            for $u in $units return p:rewrite-unit($u)
          else if ($alternative/self::Handle) then
            let $units := $alternative/Symbol
            for $u in $units return
            p:rewrite-item($u/(Nonterminal | Terminal))
          else
            error()
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

declare function p:definition($ref)
{
  let $context := $ref/ancestor::g:production
  let $grammar := $context/..
  let $separator := $grammar/processing-instruction()[local-name() = ("TOKENS", "TERMINALS")]
  return
    if ($ref >> $separator) then
      $grammar/g:production[. >> $separator and @name = $ref/@name]
    else
      $grammar/g:production[not(. >> $separator) and @name = $ref/@name and empty($ref/@context)]
};

declare function p:is-recursive($p as element(g:production), $on-stack as xs:string+) as xs:boolean
{
  some $ref in $p//g:ref[empty(@context)] satisfies
       $ref/@name = $on-stack
    or (
         some $definition in p:definition($ref) satisfies
         p:is-recursive($definition, ($on-stack, $ref/@name))
       )
};

declare function p:is-recursive($p as element(g:production)) as xs:boolean
{
  p:is-recursive($p, $p/@name)
};

declare function p:is-finite($p as element()) as xs:boolean
{
  if ($p/self::g:string) then
    true()
  else
        empty($p//(g:oneOrMore | g:zeroOrMore))
    and not(p:is-recursive($p))
    and (
          every $ref in $p//g:ref[empty(@context)] satisfies
            every $definition in p:definition($ref) satisfies
              p:is-finite($definition)
        )
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
  case element(g:string) return
    data($g)
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

declare function p:unangle($s as xs:string) as xs:string
{
  normalize-space(replace($s, "^<(.*)>$", "$1", "s"))
};

declare function p:unbrace($s as xs:string) as xs:string
{
  normalize-space(replace($s, "^\{(.*)\}$", "$1", "s"))
};

declare function p:production($grammar-fragment as node()*, $name as xs:string) as element(g:production)?
{
  ($grammar-fragment/descendant-or-self::g:production[lower-case(@name) = lower-case($name)])[1]
};

declare function p:nonterminal-name($grammar as element(g:grammar), $name as xs:string)
{
  if (not(starts-with($name, "{"))) then
    let $definition := p:production($grammar, $name)
    return attribute name {p:unbrace(($definition/@name, $name)[1])}
  else
    let $unbraced := p:unbrace($name)
    return
      if (exists(p:production($grammar, $unbraced))) then
        p:nonterminal-name($grammar, concat("{", $unbraced, "_set}"))
      else
        let $definition := p:production($grammar, $name)
        return attribute name {p:unbrace(($definition/@name, $name)[1])}
};

declare function p:case-insensitive-refs($node as node()) as node()
{
  typeswitch ($node)
  case element(g:production) return
    element g:production
    {
      attribute name {p:nonterminal-name(root($node), $node/@name)},
      $node/@*[not(node-name(.) = xs:QName("name"))],
      for $c in $node/node() return p:case-insensitive-refs($c)
    }
  case element(g:ref) return
    element g:ref
    {
      attribute name {p:nonterminal-name(root($node), $node/@name)}
    }
  case element() return
    element {node-name($node)}
    {
      $node/@*,
      for $c in $node/node() return p:case-insensitive-refs($c)
    }
  default return
    $node
};

declare function p:set-name-equal($n1 as element(SetName), $n2 as element(SetName)*) as xs:boolean
{
                          lower-case(p:unbrace($n1))
  = (for $n in $n2 return lower-case(p:unbrace($n )))
};

declare function p:predefined-defs($defs, $undefined-set-refs, $predefined-parse-tree)
{
  let $new-defs := $predefined-parse-tree//Set_Decl[p:set-name-equal(SetName, $undefined-set-refs)]
  return
    if (empty($new-defs)) then
      $defs
    else
      let $defs := ($defs, $new-defs)
      let $undefined-set-refs := $new-defs/*//SetName[not(p:set-name-equal(., $defs/SetName))]
      return p:predefined-defs($defs, $undefined-set-refs, $predefined-parse-tree)
};

declare function p:get-predefined($parse-tree as element(Grammar)) as element(Grammar)
{
  let $set-defs := $parse-tree//Set_Decl/SetName
  let $undefined-set-refs := $parse-tree//SetName[not(p:set-name-equal(., $set-defs))]
  return
    if (empty($undefined-set-refs)) then
      $parse-tree
    else
      let $predefined-parse-tree := q:parse-Grammar($p:predefined)
      let $predefined-parse-tree := if ($predefined-parse-tree/self::Grammar) then $predefined-parse-tree else error()
      let $predefined-set-defs := p:predefined-defs((), $undefined-set-refs, $predefined-parse-tree)
      return element Grammar {$parse-tree/@*, $parse-tree/node(), $predefined-set-defs}
};

declare function p:gold-to-w3c($parse-tree as element(Grammar)) as element(g:grammar)
{
  let $syntax-productions :=
  (
    for $p in $parse-tree//Rule_Decl
    return element g:production
    {
      attribute name {p:unangle($p/Nonterminal)},
      p:rewrite-choice($p/Handles/Handle)
    }
  )
  let $parse-tree := p:get-predefined($parse-tree)
  let $tokens-productions :=
  (
    for $p in $parse-tree//(Set_Decl | Terminal_Decl)
    return
      if ($p/self::Set_Decl) then
        element g:production
        {
          attribute name {concat("{", p:unbrace($p/SetName), "}")},
          p:rewrite-Set_Exp((), $p/Set_Exp/Set_Item)
        }
      else (: $p/self::Terminal_Decl :)
        element g:production
        {
          attribute name {string-join($p/Terminal_Name/Terminal, " ")},
          p:rewrite-choice($p/Reg_Exp/Reg_Exp_Seq)
        }
  )
  let $productions := ($syntax-productions, $tokens-productions)
  let $comment-start := p:production($productions, "Comment Start")
  let $comment-end := p:production($productions, "Comment End")
  let $comment-line := p:production($productions, "Comment Line")
  let $ignore-production :=
    let $ignore :=
    (
      let $whitespace := p:production($productions, "Whitespace")
      where $whitespace
      return <g:ref name="{$whitespace/@name}"/>,

      if ($comment-start or $comment-end or $comment-line) then
        <g:ref name="Comment"/>
      else
        ()
    )
    where exists($ignore)
    return
    (
      <g:production name="_" whitespace-spec="definition">
      {
        if (count($ignore) eq 1) then
          $ignore
        else
          <g:choice>{$ignore}</g:choice>
      }
      </g:production>,
      if ($comment-start or $comment-end or $comment-line) then
        <g:production name="Comment" nongreedy="true">
        {
          let $rules :=
          (
            if ($comment-line) then
              <g:sequence>
                <g:context>
                  <g:sequence>
                    <g:ref name="Comment Line"/>
                    <g:zeroOrMore>
                      <g:ref name="."/>
                    </g:zeroOrMore>
                  </g:sequence>
                  <g:charClass>
                    <g:charCode value="A"/>
                    <g:charCode value="D"/>
                  </g:charClass>
                </g:context>
              </g:sequence>
            else
              (),
            if ($comment-start or $comment-end) then
              <g:sequence>
                {element g:ref {$comment-start/@name}[$comment-start]}
                <g:zeroOrMore>
                  <g:ref name="."/>
                </g:zeroOrMore>
                {element g:ref {$comment-end/@name}[$comment-end]}
              </g:sequence>
            else
              ()
          )
          return
            if (count($rules) = 1) then
              for $d in $rules/* return if ($d/self::g:sequence) then $d/* else $d
            else
              <g:choice>{$rules}</g:choice>
        }
        </g:production>
      else
        ()
    )
  let $preferences :=
    let $strings := for $d in distinct-values($syntax-productions//g:string) return <g:string>{$d}</g:string>
    let $nonterminals := data($syntax-productions/@name)
    let $token-refs := distinct-values($syntax-productions//g:ref/@name)[not(. = $nonterminals)]
    let $tokens := data($tokens-productions/@name)[. = $token-refs]
    for $t at $i in $tokens
    let $ti := $tokens-productions[@name = $t]
    let $is-finite := p:is-finite($ti)
    return
    (
      if (not($is-finite)) then
        for $s in $strings return p:preference($ti, $s, $tokens-productions)
      else
        (),
      for $j in 1 to ($i - 1)
      let $tj := $tokens-productions[@name = $tokens[$j]]
      let $tj-is-finite := p:is-finite($tj)
      return
        if ($is-finite and not($tj-is-finite)) then
          p:preference($tj, $ti, $tokens-productions)
        else if (not($is-finite) and $tj-is-finite) then
          p:preference($ti, $tj, $tokens-productions)
        else
          ()
    )
  let $eof-production :=
(:
    if (exists($syntax-productions//g:ref[@name = "EOF"]) and empty($productions[@name = "EOF"])
     or exists($tokens-productions//g:ref[@name = "EOF"]) and empty($tokens-productions[@name = "EOF"])) then
      <g:production name="EOF"><g:ref name="$"/></g:production>
    else
:)
      ()
  return
    p:case-insensitive-refs
    (
      <g:grammar>
        {$syntax-productions, $ignore-production[@whitespace-spec]}
        {processing-instruction TOKENS{}}
        {$tokens-productions, $ignore-production[empty(@whitespace-spec)], $eof-production}
        {$preferences}
      </g:grammar>
    )
};
