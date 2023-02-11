(:~
 : Utilities used while converting foreign parse trees to W3C format.
 :)
module namespace x="de/bottlecaps/convert/xq/to-w3c.xq";

import module namespace n="de/bottlecaps/railroad/xq/normalize-ast.xq" at "../../railroad/xq/normalize-ast.xq";
import module namespace b="de/bottlecaps/railroad/xq/ast-to-ebnf.xq" at "../../railroad/xq/ast-to-ebnf.xq";
declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

(:~
 : The maximum number of repetitions, that is unfolded explicitly.
 :)
declare variable $x:repeat-limit := 8;

(:~
 : The maximum number of alternatives, that is allowed in factorization.
 :)
declare variable $x:alternative-limit := 64;

(:~ The Unicode code plane used for representing invalid 16-bit codepoints
 :)
declare variable $x:plane := 15;

(:~
 : Decode string representation of an integer to xs:integer. This
 : is a tail-recursive helper for the 2-argument function of the
 : same name.
 :
 : @param $base the numeral system base: 2, 8, 10, or 16.
 : @param $base the unconverted string.
 : @param $base the value converted in preceding recursion levels.
 : @return the resulting integer value.
 :)
declare function x:int-value($base as xs:integer,
                             $codepoint as xs:string,
                             $value as xs:integer) as xs:integer
{
  if ($codepoint = "") then
    $value
  else
    let $digit := string-to-codepoints(substring($codepoint, 1, 1))
    let $value :=  $base * $value + ($digit - (0, 0, 48, 55, 0, 87)[$digit idiv 16])
    let $codepoint := substring($codepoint, 2)
    return x:int-value($base, $codepoint, $value)
};

(:~
 : Decode string representation of an integer to xs:integer.
 :
 : @param $base the numeral system base: 2, 8, 10, or 16.
 : @param $base the string to be converted.
 : @return the resulting integer value.
 :)
declare function x:int-value($base as xs:integer, $codepoint as xs:string) as xs:integer
{
  x:int-value($base, $codepoint, 0)
};

(:~
 : Convert an integer value to string representation. This is a
 : tail-recursive helper for the 2-argument function of the same
 : name.
 :
 : @param $base the numeral system base: 2, 8, 10, or 16.
 : @param $todo the uncoverted integer.
 : @param $done the converted string as accumulated in previous
 : recursion levels.
 : @return the string representation.
 :)
declare function x:to-string($base as xs:integer, $todo as xs:integer, $done as xs:string?) as xs:string
{
  if ($todo eq 0) then
    ($done, "0")[1]
  else
    let $done := concat(substring("0123456789ABCDEF", 1 + $todo mod $base, 1), $done)
    return x:to-string($base, $todo idiv $base, $done)
};

(:~
 : Convert an integer value to string representation.
 :
 : @param $base the numeral system base: 2, 8, 10, or 16.
 : @param $todo the uncoverted integer.
 : @return the string representation.
 :)
declare function x:to-string($base as xs:integer, $todo as xs:integer) as xs:string
{
  x:to-string($base, $todo, ())
};

(:~
 : Verify whether a given codepoint represents a valid XML character, i.e.
 : mathces this production from the XML recommendation:
 :
 :   Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
 :
 : @param $codepoint the codepoint integer value.
 : @return true, if the codepoint corresponds to a valid XML character.
 :)
declare function x:is-valid-char($codepoint as xs:integer) as xs:boolean
{
     $codepoint ge 9 and $codepoint le 10
  or $codepoint eq 13
  or $codepoint ge 32 and $codepoint le 55295
  or $codepoint ge 57344 and $codepoint le 65533
  or $codepoint ge 65536 and $codepoint le 1114111
};

(:~
 : Convert a codepoint provided as an xs:integer to an xs:string. While
 : the conversion could in fact be done by fn:codepoints-to-string, this
 : function maps 16-bit codepoints that are invalid XML characters into
 : the Unicode plane 1. This is because some foreign grammars use non-XML
 : control characters (e.g. 0x08, 0x0C), but at the same time do not
 : support characters beyond the basic multilingual plane. We thus move
 : them up for representing them as XDM string or node content, for later
 : representing them by their numeric escape sequences, e.g. when serializing
 : to EBNF.
 :
 : @param $codepoint the actual codepoint as an integer.
 : @return the string representation, i.e. a single character, with unsupported
 : codepoints mapped to Unicode plane 1.
 :)
declare function x:codepoint-to-string($codepoint as xs:integer) as xs:string
{
  if (x:is-valid-char($codepoint)) then
    codepoints-to-string($codepoint)
  else
    codepoints-to-string($x:plane * 65536 + $codepoint)
};

declare function x:string-to-codepoint($string as xs:string) as xs:integer
{
  if (string-length($string) ne 1) then
    error(xs:QName("x:string-to-codepoint"), concat("invalid argument: ", $string))
  else
    let $codepoint := string-to-codepoints($string)
    return
      if ($codepoint ge $x:plane * 65536
      and $codepoint lt ($x:plane + 1) * 65536
      and not(x:is-valid-char($codepoint - $x:plane * 65536))) then
        $codepoint - $x:plane * 65536
      else
        $codepoint
};

declare function x:to-charCode($string as xs:string) as xs:string
{
  x:to-string(16, x:string-to-codepoint($string))
};

(:~
 : Create a nested sequence of g:optional elements, to serve as a
 : replacement for the unsupported repetition counts.
 :
 : @param $n the number of times to repeat.
 : @param $fragment the fragment to be repeated.
 : @return the nested sequence of g:optional elements
 :)
declare function x:optional($n as xs:integer, $fragment as element()*) as element()?
{
  if ($n = 0) then
    ()
  else
    element g:optional {$fragment, x:optional($n - 1, $fragment)}
};

(:~
 : Create a replacement sequence for a repetition of a generated
 : fragment.
 :
 : @param $fragment the fragment to be repeated.
 : @param $min the minimum number of repetitions.
 : @param $max the maximum number of repetitions.
 : @return the replacement sequence.
 :)
declare function x:repeat($fragment as element()*,
                          $min as xs:integer,
                          $max as xs:integer) as element()*
{
  if ($max gt $x:repeat-limit) then
    if ($min = 0) then
      element g:zeroOrMore {$fragment}
    else
    (
      for $i in 1 to min(($min, $x:repeat-limit)) - 1 return $fragment,
      element g:oneOrMore {$fragment}
    )
  else if ($min le $max) then
  (
    for $i in 1 to $min return $fragment,
    x:optional($max - $min, $fragment)
  )
  else
    error()
};

(:~
 : Split a string into a sequence of g:string and g:charCode elements,
 : such that the g:charCode elements are used for all content that is
 : either non-printable or not representable in 7 bits. This is a
 : tail-recursive helper for the 1-argument function of the same name.
 :
 : @param $todo the string to be processed.
 : @param $done the result from previous recursion levels.
 : @return the sequence of g:string and g:charCode elements.
 :)
declare function x:split-string($todo as xs:string, $done as element()*) as element()*
{
  if ($todo = "") then
    $done
  else
    let $ascii := replace($todo, "[^&#x20;-&#x7e;](.|[&#xA;&#xD;])*$", "")
    return
      if ($ascii = "") then
        x:split-string(substring($todo, 2), ($done, <g:charCode value="{x:to-charCode(substring($todo, 1, 1))}"/>))
      else
        x:split-string(substring-after($todo, $ascii), ($done, <g:string>{$ascii}</g:string>))
};

(:~
 : Split a string into a sequence of g:string and g:charCode elements,
 : such that the g:charCode elements are used for all content that is
 : either non-printable or not representable in 7 bits.
 :
 : @param $todo the string to be processed.
 : @return the sequence of g:string and g:charCode elements.
 :)
declare function x:split-string($todo as xs:string) as element()*
{
  x:split-string($todo, ())
};

declare variable $x:enable := false();
declare variable $x:direct-recursion-only := true();

(:~
 : Check whether we are facing an indirectly recursive g:ref node.
 :
 : @param $node the candidate node.
 : @return true, if it is an indirectly recursive reference.
 :)
declare function x:is-indirectly-recursive($production-name as xs:string,
                                           $node as element()?,
                                           $parser-productions as element(g:production)*,
                                           $epsilon-nonterminals as xs:string*) as xs:boolean
{
      $node/self::g:ref[empty(@context)]
  and ($node/parent::g:choice or x:is-epsilon-sequence($node/preceding-sibling::*, $epsilon-nonterminals))
  and $node/@name != $production-name
  and $parser-productions/@name = $node/@name
  and $production-name = x:left-nonterminals($node/@name, (), $epsilon-nonterminals, $parser-productions)

  and $x:enable
  and (not($x:direct-recursion-only) or matches($node/@name, concat("^", $production-name, "_+[1-9][0-9]*$")))
};

(:~
 : Remove left-recursion from production.
 :
 : @param $p the production.
 : @return the production, with left-recursion removed.
 :)
declare function x:remove-left-recursion($p0 as element(g:production),
                                         $p as element(g:production),
                                         $parser-productions as element(g:production)*,
                                         $epsilon-nonterminals as xs:string*) as element(g:production)
{
  let $name := data($p/@name)
  let $recursions := $p//g:ref[x:is-indirectly-recursive($name, ., $parser-productions, $epsilon-nonterminals)]
  return
    if (empty($recursions)) then
      $p
    else
      let $alternatives := x:bnf-alternatives($p)
      let $rewritten-alternatives :=
        for $alternative in $alternatives
        let $items := n:unwrap-sequence($alternative)
        let $ref := ($items/self::g:ref[x:is-indirectly-recursive($name, .,  $parser-productions, $epsilon-nonterminals)])[1]
        return
          if (empty($ref)) then
            $alternative
          else
            for $replacement in x:bnf-alternatives($parser-productions[@name = $ref/@name])
            return
              n:wrap-sequence
              ((
                $items[. << $ref],
                n:unwrap-sequence($replacement),
                $items[. >> $ref]
              ))
      let $rewritten-production :=
        element g:production
        {
          $p/@*,
          if (count($rewritten-alternatives) <= 1) then
          n:unwrap-sequence($rewritten-alternatives)
          else
            element g:choice{$rewritten-alternatives}
        }
      let $rewritten-productions := ($parser-productions[@name != $name], $rewritten-production)
      return
        if (count(x:left-nonterminals($name, (), $epsilon-nonterminals, $rewritten-productions))
         >= count(x:left-nonterminals($name, (), $epsilon-nonterminals, $parser-productions))) then
          $p0
        else
          x:remove-left-recursion($p0, $rewritten-production, $rewritten-productions, $epsilon-nonterminals)
};

(:~
 : Normalize a grammar to EBNF, by rewriting into a normalized grammar.
 :
 : @param $grammar the grammar.
 : @param $generated-name-introducer the generated name introducer.
 : @return the normalized grammar.
 :)
declare function x:generated-name-introducer($grammar as element(g:grammar), $proposal as xs:string) as xs:string
{
  if (not($proposal != "")) then
    x:generated-name-introducer($grammar, "_")
  else if (some $name in ($grammar//g:production/@name, $grammar//g:ref/@name)
           satisfies x:is-generated-name($name, $proposal)) then
    x:generated-name-introducer($grammar, concat($proposal, $proposal))
  else
    $proposal
};

declare function x:is-generated-name($name as xs:string, $generated-name-introducer as xs:string) as xs:boolean
{
  matches($name, concat($generated-name-introducer, "[1-9][0-9]*$"))
};

(:~
 :)
declare function x:is-epsilon-sequence($nodes as node()*, $epsilon-nonterminals) as xs:boolean
{
  every $node in $nodes
  satisfies $node/self::g:ref[empty(@context) and @name = $epsilon-nonterminals]
};

(:~
 :)
declare function x:is-epsilon-production($p as element(g:production), $epsilon-nonterminals) as xs:boolean
{
  if ($p/g:choice) then
    some $c in $p/g:choice/*
    satisfies
      if ($c/self::g:sequence) then
        x:is-epsilon-sequence($c/*, $epsilon-nonterminals)
      else
        x:is-epsilon-sequence($c, $epsilon-nonterminals)
  else
    x:is-epsilon-sequence($p/*, $epsilon-nonterminals)
};

(:~
 :)
declare function x:epsilon-nonterminals($result as xs:string*,
                                        $parser-productions as element(g:production)*) as xs:string*
{
  let $new-result :=
    for $p in $parser-productions[not(@name = $result)]
    where x:is-epsilon-production($p, $result)
    return data($p/@name)
  return
    if (empty($new-result)) then
      $result
    else
      x:epsilon-nonterminals(($result, $new-result), $parser-productions)
};

(:~
 :)
declare function x:epsilon-nonterminals($parser-productions as element(g:production)*) as xs:string*
{
  x:epsilon-nonterminals((), $parser-productions)
};

(:~
 : Remove left-recursion from grammar.
 :
 : @param $ast the grammar.
 : @return the grammar, without left-recursive productions
 : transformed to equivalent EBNF constructs.
 :)
declare function x:remove-left-recursion($ast as element(g:grammar)) as element(g:grammar)
{
  let $parser-productions := x:parser-productions($ast)
  let $inline :=
    for $p in $parser-productions
    let $nt := data($p/@name)
    let $other-ref := $parser-productions[@name ne $nt]//g:ref[empty(@context) and @name = $nt]
    let $self-ref  := $parser-productions[@name eq $nt]//g:ref[empty(@context) and @name = $nt]
    let $left-recursive-ref := x:left-recursive-refs($p)
    where count($other-ref) eq 1
      and exists($left-recursive-ref)
      and count($left-recursive-ref) eq count($self-ref)
    return $nt

  let $generated-name-introducer := x:generated-name-introducer($ast, "_")
  let $bnf := if (x:is-bnf($ast)) then $ast else x:bnf($ast, $generated-name-introducer)
  let $parser-productions := x:parser-productions($bnf)
  let $epsilon-nonterminals := x:epsilon-nonterminals($parser-productions)
  let $ebnf := x:ebnf($bnf, $generated-name-introducer)
  let $inline-productions :=
    for $p in x:parser-productions($ebnf)
    where empty($p/@context)
      and $p/@name = $inline
      and empty(x:left-recursive-refs($p))
    return $p

  let $ebnf := x:inline($ebnf, $inline-productions)
  return $ebnf
};

(:~
 : Remove direct right-recursion from grammar.
 :
 : @param $ast the grammar.
 : @return the grammar, without left- and right-recursive productions
 : transformed to equivalent EBNF constructs.
 :)
declare function x:remove-right-recursion($ast as node()) as node()
{
  let $reversed := n:reverse($ast)
  let $removed := x:remove-left-recursion($reversed)
  return
    if (deep-equal($reversed, $removed)) then
      $ast
    else
      n:reverse($removed)
};

(:~
 : Apply factoring to grammar.
 :
 : @param $grammar the grammar.
 : @param $factoring the factoring options, i.e. "none", "left-only", "full-left", "right-only", "full-right"
 : "left-right", or "right-left".
 : @return the transformed grammar.
 :)
declare function x:factorize($grammar as element(g:grammar),
                             $factoring as xs:string) as element(g:grammar)
{
  if ($factoring = "none") then
    $grammar
  else
    let $g3 := n:normalize($grammar)
    let $g4 :=
      n:denormalize
      (
        n:introduce-separators
        (
          if ($factoring = "left-only") then
            x:left-factorize($g3)
          else if ($factoring = "right-only") then
            x:right-factorize($g3)
          else if ($factoring = "full-left") then
            x:left-factorize(x:right-factorize(x:left-factorize($g3)))
          else if ($factoring = "full-right") then
            x:right-factorize(x:left-factorize(x:right-factorize($g3)))
          else
            error(xs:QName("x:factorize"), concat("invalid argument: $factoring: ", $factoring))
        )
      )
    return
      if (deep-equal($grammar, $g4)) then
        $grammar
      else
        x:factorize($g4, $factoring)
};

(:~
 : Remove recursion from grammar.
 :
 : @param $grammar the grammar.
 : @param $recursion-removal the removal options, i.e. "left" and/or "right".
 : @return the transformed grammar.
 :)
declare function x:remove-recursion($grammar as element(g:grammar),
                                    $recursion-removal as xs:string*) as element(g:grammar)
{
  if (empty($recursion-removal[not(. = ("left", "right"))])) then
    let $g1 := if ($recursion-removal = "left") then x:remove-left-recursion($grammar) else $grammar
    let $g2 := if ($recursion-removal = "right") then x:remove-right-recursion($g1) else $g1
    return $g2
  else
    error(xs:QName("x:remove-recursion"), concat("invalid argument: $recursion-removal: ", string-join($recursion-removal, ", ")))
};

(:~
 : Normalize a sequence of nodes to BNF, by rewriting the sequence. This
 : overload is for everything below g:production.
 :
 : @param $done the result of previous recursion levels.
 : @param $todo the sequence of nodes to be normalized to BNF.
 : @param $subrules the sequence of all subrule elements of the parent
 : production.
 : @param $generated-name-introducer the generated name introducer.
 : @return the grammar, rewritten to BNF.
 :)
declare function x:bnf($done as node()*, $todo as node()*, $subrules as node()*, $generated-name-introducer as xs:string) as node()*
{
  if (empty($todo)) then
    $done
  else
    let $node := $todo[1]
    return
      x:bnf
      (
        (
          $done,
          if (not($node instance of element(g:optional)) and
              not($node instance of element(g:choice)) and
              not($node instance of element(g:orderedChoice)) and
              not($node instance of element(g:zeroOrMore)) and
              not($node instance of element(g:oneOrMore)) and
              not($node instance of element(g:subtract))) then
            $node
          else if ($subrules[. is $node]) then
            element g:ref {x:subrule-name($node, $generated-name-introducer, n:index-of-node($subrules, $node))}
          else
            x:subrule-body($node/ancestor::g:production/@name, $node, $subrules, $generated-name-introducer)
        ),
        $todo[position() > 1],
        $subrules,
        $generated-name-introducer
      )
};

(:~
 : Normalize a sequence of nodes to BNF, by rewriting the sequence. This
 : overload is for everything above and including g:production.
 :
 : @param $nodes the sequence of nodes to be normalized to BNF.
 : @param $generated-name-introducer the generated name introducer.
 : @return the normalized nodes.
 :)
declare function x:bnf($nodes as node()*, $generated-name-introducer as xs:string) as node()*
{
  for $node in $nodes
  return
    typeswitch ($node)
    case element(g:grammar) return
      let $grammar := n:group-productions-by-nonterminal($node)
      return
        element g:grammar
        {
          $grammar/@*,
          let $end := $grammar/processing-instruction()[local-name(.) = ("TOKENS", "ENCORE") and string(.) eq ""][1]
          return
          (
            x:bnf(n:children($grammar)[not(. is $end or . >> $end)], $generated-name-introducer),
            $end,
            $grammar/node()[. >> $end]
          )
        }
    case element(g:production) return
      let $subrules :=
        for $subrule in
          $node//(g:optional[empty(parent::g:choice)],
                  g:choice[not(deep-equal(., n:children(parent::g:optional)))],
                  g:orderedChoice,
                  g:oneOrMore,
                  g:zeroOrMore,
                  g:subtract)
        where not(deep-equal($subrule, n:children($subrule/parent::g:production)))
        return $subrule
      return
      (
        element g:production {$node/@*, x:bnf((), n:children($node), $subrules, $generated-name-introducer)},
        for $subrule at $i in $subrules
        let $name := x:subrule-name($subrule, $generated-name-introducer, $i)
        return
          element g:production
          {
            attribute whitespace-spec {"explicit"}[$subrule/ancestor::g:production/@whitespace-spec = ("explicit", "definition")],
            $name,
            x:subrule-body($name, $subrule, $subrules, $generated-name-introducer)
          }
      )
    case processing-instruction() return
      $node
    default return
      error(xs:QName("x:bnf"), string-join(("invalid node type", string(node-name($node)), string($node)), " "))
};

(:~
 : Construct a temporary name for a nonterminal. The name is created from
 : the production name surrounding the given element, the generated name
 : introducer, and a sequence number.
 :
 : @param $node the node causing subrule generation.
 : @param $generated-name-introducer the generated name introducer.
 : @param $i the sequence number.
 : @return the constructed subrule name.
 :)
declare function x:subrule-name($node as element(), $generated-name-introducer as xs:string, $i as xs:integer) as attribute(name)
{
  attribute name
  {
    concat
    (
      $node/ancestor-or-self::g:production/@name,
      $generated-name-introducer,
      string($i)
    )
  }
};

(:~
 : Create a sequence of productions from complex elements moved out
 : of a parent production during BNF normalization.
 :
 : @param $name the production name.
 : @param $subrule the current to-be-extracted operator.
 : @param $subrules the sequence of all extracted operators.
 : @param $generated-name-introducer the generated name introducer.
 : @return the sequence of productions.
 :)
declare function x:subrule-body($name as attribute(name),
                                $subrule as element(),
                                $subrules as element()*,
                                $generated-name-introducer as xs:string) as element()
{
  if ($subrule/self::g:optional) then
    element g:choice
    {
      element g:sequence {},
      let $children := n:children($subrule)
      return
        if (deep-equal($children, $subrule/g:choice)) then
          for $case in n:children($children)
          return n:wrap-sequence(x:bnf((), n:unwrap-sequence($case), $subrules, $generated-name-introducer))
        else
          n:wrap-sequence(x:bnf((), $children, $subrules, $generated-name-introducer))
    }
  else if ($subrule/self::g:choice) then
    element g:choice
    {
      $subrule/@*,
      for $case in n:children($subrule)
      return
        if ($case/self::g:optional) then
        (
          element g:sequence {},
          n:wrap-sequence(x:bnf((), n:children($case), $subrules, $generated-name-introducer))
        )
        else
          n:wrap-sequence(x:bnf((), n:unwrap-sequence($case), $subrules, $generated-name-introducer))
    }
  else if ($subrule/self::g:subtract or $subrule/self::g:orderedChoice) then
    element {node-name($subrule)}
    {
      $subrule/@*,
      for $child in n:children($subrule)
      return n:wrap-sequence(x:bnf((), n:unwrap-sequence($child), $subrules, $generated-name-introducer))
    }
  else if ($subrule/self::g:zeroOrMore) then
    element g:choice
    {
      element g:sequence {},
      n:wrap-sequence
      ((
        element g:ref {$name},
        x:bnf((), n:children($subrule), $subrules, $generated-name-introducer)
      ))
    }
  else if ($subrule/self::g:oneOrMore) then
    let $bnf := x:bnf((), n:children($subrule), $subrules, $generated-name-introducer)
    return
      element g:choice
      {
        n:wrap-sequence($bnf),
        n:wrap-sequence((element g:ref {$name}, $bnf))
      }
  else
    error(xs:QName("x:subrule-body"), string-join(("invalid node type", string(node-name($subrule)), string($subrule)), " "))
};

(:~
 : Get alternatives from a sequence of production (of one nonterminal).
 :
 : @param $productions the productions.
 : @return the sequence of alternatives.
 :)
declare function x:bnf-alternatives($productions as element(g:production)*)
{
  for $p in $productions
  return
    if ($p/*[last() = 1]/self::g:choice) then
      $p/*/*
    else
      n:wrap-sequence($p/*)
};

(:~
 : Normalize a sequence of nodes to EBNF, by rewriting into a normalized
 : node sequence. This overload is for everything below g:grammar.
 :
 : @param $nodes the sequence of nodes.
 : @param $single-reference-nonterminals the set of nonterminals productions
 : that will be inlined to their reference context.
 : @param $self-reference the reference currently being integrated. This serves
 : for suppressing recursive calls, as they become Kleene operators in the
 : reference context.
 : @return the normalized sequence.
 :)
declare function x:ebnf($nodes as node()*,
                        $single-reference-nonterminals as element(g:production)*,
                        $self-reference as element(g:ref)*
                       ) as node()*
{
  for $node in $nodes
  return
    typeswitch ($node)
    case element(g:production) return
      if ($node/@name = $single-reference-nonterminals/@name) then
        ()
      else
        element g:production
        {
          $node/@*,
          let $left-recursive-ref :=
            x:left-recursive-refs($node)
            [parent::g:sequence/parent::g:choice/parent::g:production]
          let $self-ref := $node//g:ref[empty(@context) and @name eq $node/@name and
            empty(ancestor::*[. >> $node and not(parent::g:choice) and following-sibling::*])]
          return
            x:ebnf
            (
              n:children($node),
              $single-reference-nonterminals,
              $self-ref[count($left-recursive-ref) eq count($self-ref)]
            )
        }
    case element(g:choice) return
      let $children := n:children($node)
      let $empty := $children/self::g:sequence[empty(n:children(.))]
      return
        if (empty($self-reference)) then
          if (exists($empty)) then
            element g:optional
            {
              if (count($children) - count($empty) eq 1) then
                for $child in $children
                where empty($empty[. is $child])
                return x:ebnf(n:unwrap-sequence($child), $single-reference-nonterminals, ())
              else
                n:choice
                ((
                  for $child in $children
                  where empty($empty[. is $child])
                  return n:wrap-sequence(x:ebnf(n:unwrap-sequence($child), $single-reference-nonterminals, ()))
                ))
            }
          else
            element g:choice
            {
              $node/@*,
              for $child in $children
              return n:wrap-sequence(x:ebnf(n:unwrap-sequence($child), $single-reference-nonterminals, $self-reference))
            }
        else
          let $anchor    := $children[not(.//g:ref[empty(@context) and @name = $self-reference/@name])]
          let $recursive := $children[    .//g:ref[empty(@context) and @name = $self-reference/@name] ]
          let $seed :=
            if (count($anchor) eq 1) then
              x:ebnf(n:unwrap-sequence($anchor), $single-reference-nonterminals, $self-reference)
            else
              element g:choice
              {
                $node/@*,
                for $child in $anchor
                return n:wrap-sequence(x:ebnf(n:unwrap-sequence($child), $single-reference-nonterminals, $self-reference))
              }
          let $loop :=
            if (count($recursive) eq 1) then
              x:ebnf(n:unwrap-sequence($recursive), $single-reference-nonterminals, $self-reference)
            else
              element g:choice
              {
                $node/@*,
                for $child in $recursive
                return n:wrap-sequence(x:ebnf(n:unwrap-sequence($child), $single-reference-nonterminals, $self-reference))
              }
          return
            if (deep-equal($seed, $loop)) then
              element g:oneOrMore {$loop}
            else
              ($seed, element g:zeroOrMore {$loop})
    case element(g:orderedChoice) return
      element {node-name($node)}
      {
        $node/@*,
        for $child in n:children($node)
        let $sequence := n:unwrap-sequence($child)
        return n:wrap-sequence(x:ebnf($sequence, $single-reference-nonterminals, $self-reference))
      }
    case element(g:ref) return
      if ($node/@name = $self-reference/@name) then
        ()
      else if ($node/@context or not($node/@name = $single-reference-nonterminals/@name)) then
        $node
      else
        let $definition := $single-reference-nonterminals[@name eq $node/@name]
        let $self-reference := $definition//g:ref[empty(@context) and @name eq $node/@name]
        return x:ebnf(n:children($definition), $single-reference-nonterminals, $self-reference)
    case element() return
      element {node-name($node)}
      {
        $node/@*,
        x:ebnf($node/node(), $single-reference-nonterminals, $self-reference)
      }
    default return
      $node
};

(:~
 : Normalize a grammar to EBNF, by rewriting into a normalized grammar.
 :
 : @param $grammar the grammar.
 : @param $generated-name-introducer the generated name introducer.
 : @return the normalized grammar.
 :)
declare function x:ebnf($grammar as element(g:grammar), $generated-name-introducer as xs:string) as element(g:grammar)
{
  let $nonterminals := x:parser-productions($grammar)
  let $single-reference-nonterminals := $nonterminals[x:is-generated-name(@name, $generated-name-introducer)]
  let $end := n:syntax-end($grammar)
  let $ebnf :=
    x:combine-optional-oneOrMore
    (
      x:establish-oneOrMore
      (
        element g:grammar
        {
          $grammar/@*,
          x:ebnf
          (
            n:children($grammar)[not(. is $end or . >> $end)],
            $single-reference-nonterminals,
            ()
          ),
          $end,
          $grammar/node()[. >> $end]
        }
      )
    )
  return
    if (deep-equal($ebnf, $grammar)) then
      $grammar
    else
      x:ebnf($ebnf, $generated-name-introducer)
};

(:~
 : Expand $result by any nonterminal names that occur at the left hand side
 : of productions that have names matching $nonterminal. $result is an
 : accumulator variable, thus initial calls should pass an empty sequence.
 :
 : @param $nonterminal the nonterminals
 : @param $result the result accumulator.
 : @param $empty the empty flag accumulator.
 : @param $parser-productions the complete set of available syntax productions.
 : @return the sequence of nonterminal names occurring at the very left
 : of corresponding productions.
 :)
declare function x:left-nonterminals($nonterminal as xs:string*,
                                     $result as xs:string*,
                                     $empty as xs:string*,
                                     $parser-productions as element(g:production)*) as xs:string*
{
  let $left := x:direct-left-nonterminals($nonterminal, $empty, $parser-productions)[not(. = $result)]
  return
    if (empty($left)) then
      $result
    else
      x:left-nonterminals($left, ($result, $left), $empty, $parser-productions)
};

(:~
 : Apply single-production right-factoring transformation to node $ast.
 :
 : @param $ast the grammar to be transformed.
 : @return the transformed grammar.
 :)
declare function x:right-factorize($ast as node()) as node()
{
  let $reversed := n:reverse($ast)
  let $right-factored := x:left-factorize($reversed)
  return
    if (deep-equal($reversed, $right-factored)) then
      $ast
    else
      n:reverse($right-factored)
};

(:~
 :
 :)
declare function x:direct-left-nonterminals($nonterminal as xs:string*,
                                            $empty as xs:string*,
                                            $parser-productions as element(g:production)*) as xs:string*
{
  distinct-values
  (
    for $n in $nonterminal
    let $p := $parser-productions[@name eq $n]
    for $r in
    (
      $p/g:ref[empty(preceding-sibling::*[not(self::g:ref) or not(@name = $empty)])],
      $p/g:choice/g:ref,
      $p/g:choice/g:sequence/g:ref[empty(preceding-sibling::*[not(self::g:ref) or not(@name = $empty)])]
    )
    return $r/@name[. = $parser-productions/@name and empty($r/@context)]
  )
};

(:~
 : Apply single-production left-factoring transformation to nodes $todo.
 :
 : @param $todo the nodes to be transformed.
 : @param $done the intermediate result, that was calculated in preceding
 : recursion levels of this tail-recursive transformation.
 : @return the transformed nodes.
 :)
declare function x:left-factorize($todo as node()*, $done as node()*) as node()*
{
  if (empty($todo)) then
    $done
  else if ($todo[1]/self::g:choice and count($todo[1]/*) gt $x:alternative-limit) then
    x:left-factorize($todo[position() gt 1], ($done, $todo[1]))
  else
    let $node := $todo[1]
    let $children := n:children($node)
    let $left-factor :=
      if (not($node/self::g:choice)) then
        ()
      else
        (
          for $c in $children
          let $case := n:unwrap-sequence($c)[1]
          where
            some $d in $children[. << $c]
            satisfies deep-equal($case, n:unwrap-sequence($d)[1])
          return $case
        )[1]
    let $left-factor-choice :=
      if ($left-factor or not($node/self::g:choice)) then
        ()
      else
        (
          for $c in $children
          let $case := n:unwrap-sequence($c)[1]
          where $case/self::g:choice
            and
            (
              every $subcase in n:children($case)
              satisfies
                some $d in $children
                satisfies deep-equal($subcase, $d)
            )
          return $case
        )[1]

(:
    (: A |  B+    D | (B+|) E | F
    => A | (B+|)B D | (B+|) E | F
    :)

    find prefix of:            B+
    use it to create this:    (B+|)
    and verify prefix exists: (B+|)
    if so, replace             B+
    by                        (B+|)B

    do not flatten or the like, and have the left-factor rule catch
    it in the next step.
:)
    let $left-factor-oom :=
      if ($left-factor or $left-factor-choice or not($node/self::g:choice)) then
        ()
      else
        (
          for $c in $children
          let $oneOrMore := n:unwrap-sequence($c)[1]

          where $oneOrMore/self::g:oneOrMore
          return
            let $hs := n:children($oneOrMore)
            let $choice :=
              element g:choice
              {
                $oneOrMore,
                n:wrap-sequence(())
              }
            where
              some $d in $children
              satisfies deep-equal($choice, n:unwrap-sequence($d)[1])
              return ($oneOrMore, n:wrap-sequence(($choice, $hs)))
        )[position() le 2]

    let $single-child := if (count($children) eq 1) then $children else ()
    let $empty := $children/self::g:sequence[empty(n:children(.))]
    let $non-empty := $children[not(. is $empty)]
    return
      if (exists($left-factor)) then

        (: (A|B C|B D|E) => (A|B(C|D)|E):)

        let $choice :=
          let $factored :=
            for $c at $i in $children
            let $elements := n:unwrap-sequence($c)
            where deep-equal($left-factor, $elements[1])
            return $i
          let $cases :=
          (
            $children[position() lt $factored[1]],
            n:wrap-sequence
            ((
              $left-factor,
              n:choice
              (
                for $c at $i in $children[position() = $factored]
                return n:wrap-sequence(n:unwrap-sequence($c)[position() gt 1])
              )
            )),
            for $c at $i in $children
            where not($i = $factored) and $i gt $factored[1]
            return $c
          )
          return n:choice($cases)
        return
          x:left-factorize(($choice, $todo[position() gt 1]), $done)

      else if (exists($left-factor-choice)) then

        (: (A|(B|C)D|B|C|E) => (A|(B|C)D|(B|C)|E) :)

        let $choice :=
          let $factored :=
            for $c at $i in $children
            where some $d in n:children($left-factor-choice) satisfies deep-equal($d, $c)
            return $i
          let $cases :=
          (
            $children[position() lt $factored[1]],
            $left-factor-choice,
            $children[not(position() = $factored) and position() gt $factored[1]]
          )
          return element g:choice {$cases} (: no flattening here :)
        return
          x:left-factorize(($choice, $todo[position() gt 1]), $done)

      else if (exists($left-factor-oom)) then

        let $choice :=
          element g:choice (: no flattening here :)
          {
            for $c at $i in $children
            let $elements := n:unwrap-sequence($c)
            return
              if (deep-equal($left-factor-oom[1], $elements[1])) then
                n:wrap-sequence((n:unwrap-sequence($left-factor-oom[2]), $elements[position() gt 1]))
              else
                $c
          }
        return x:left-factorize(($choice, $todo[position() gt 1]), $done)

      else if (count($children) eq 2
           and $node/self::g:choice
           and exists($empty)
           and n:is-sequence-item($node)
           and exists(n:unwrap-sequence($non-empty))
           and deep-equal(n:unwrap-sequence($non-empty)[1], $todo[2])) then

        (: (A B|) A => A (B A|) :)

        x:left-factorize
        (
          (
            $todo[2],
            n:choice
            ((
              $empty[. << $non-empty],
              n:wrap-sequence
              ((
                n:unwrap-sequence($non-empty)[position() gt 1],
                $todo[2]
              )),
              $empty[. >> $non-empty]
            )),
            $todo[position() gt 2]
          ),
          $done
        )

      else if ($node/self::g:oneOrMore
           and n:is-sequence-item($node)
           and exists($children)
           and deep-equal($children[1], $todo[2])) then

        (: (A B)+ A => A (B A)+ :)

        x:left-factorize
        (
          (
            $todo[2],
            element g:oneOrMore
            {
              $children[position() gt 1],
              $todo[2]
            },
            $todo[position() gt 2]
          ),
          $done
        )

      else if ($node/self::g:choice
           and $children[1]/self::g:oneOrMore
           and $children[2]/self::g:sequence[empty(n:children(.))]
           and n:is-sequence-item($node)
           and exists(n:children($children[1]))
           and deep-equal(n:children($children[1])[1], $todo[2])) then

        (: ((A B)+|) A => A ((B A)+|) :)

        x:left-factorize
        (
          (
            $todo[2],
            element g:choice
            {
              element g:oneOrMore
              {
                n:children($children[1])[position() gt 1],
                $todo[2]
              },
              $children[2]
            },
            $todo[position() gt 2]
          ),
          $done
        )

      else if (empty($children)) then

        x:left-factorize($todo[position() gt 1], ($done, $node))

      else

        x:left-factorize
        (
          $todo[position() gt 1],
          (
            $done,
            element {node-name($node)}
            {
              $node/@*,
              if (n:is-sequence-item($children)) then
                x:left-factorize($children, ())
              else
                for $c in $children
                return n:wrap-sequence(x:left-factorize(n:unwrap-sequence($c), ()))
            }
          )
        )
};

(:~
 : Apply single-production left-factoring transformation to node $ast.
 :
 : @param $ast the grammar to be transformed.
 : @return the transformed grammar.
 :)
declare function x:left-factorize($ast as node()) as node()
{
  let $left-factored := x:left-factorize($ast, ())
  return
    if (deep-equal($ast, $left-factored)) then
      $ast
    else
      x:left-factorize($left-factored)
};

(:~
 : Create g:oneOrMore operators from a matching g:zeroOrMore, i.e. replace
 : A A* or A* A by A+. Used during EBNF normalization.
 :
 : @param $node the node to be rewritten.
 : @return the rewritten node.
 :)
declare function x:establish-oneOrMore($node as node()) as node()*
{
  let $children := n:children($node)
  let $zeroOrMore := ($children/self::g:zeroOrMore)[1]
  let $zeroOrMore-index := if (empty($zeroOrMore)) then () else n:index-of-node($children, $zeroOrMore)
  let $zeroOrMore-children := n:children($zeroOrMore)
  let $zeroOrMore-children-count := count($zeroOrMore-children)
  return
    if (empty($children)) then
      $node
    else if (exists($zeroOrMore)
         and deep-equal($zeroOrMore-children,
                        $children[    position() >= $zeroOrMore-index - $zeroOrMore-children-count
                                  and position() < $zeroOrMore-index])) then
      x:establish-oneOrMore
      (
        element {node-name($node)}
        {
          $node/@*,
          for $node in $children[position() < $zeroOrMore-index - $zeroOrMore-children-count]
          return x:establish-oneOrMore($node),
          x:establish-oneOrMore(element g:oneOrMore{$zeroOrMore-children}),
          for $node in $children[position() > $zeroOrMore-index]
          return x:establish-oneOrMore($node)
        }
      )
    else if (exists($zeroOrMore)
         and deep-equal($zeroOrMore-children,
                        $children[    position() > $zeroOrMore-index
                                  and position() <= $zeroOrMore-index + $zeroOrMore-children-count])) then
      x:establish-oneOrMore
      (
        element {node-name($node)}
        {
          $node/@*,
          for $node in $children[position() lt $zeroOrMore-index]
          return x:establish-oneOrMore($node),
          x:establish-oneOrMore(element g:oneOrMore{$zeroOrMore-children}),
          for $node in $children[position() gt $zeroOrMore-index + $zeroOrMore-children-count]
          return x:establish-oneOrMore($node)
        }
      )
    else
      element {node-name($node)}
      {
        $node/@*,
        for $node in $children
        return x:establish-oneOrMore($node)
      }
};

(:~
 : Create g:zeroOrMore operators from a matching g:optional with a single
 : g:zeroOrMore child, i.e. replace
 : (A+)? by A*. Used during EBNF normalization.
 :
 : @param $node the node to be rewritten.
 : @return the rewritten node.
 :)
declare function x:combine-optional-oneOrMore($node as node()) as node()*
{
  if ($node/self::g:optional and $node/g:oneOrMore and count(n:children($node)) eq 1) then
    element g:zeroOrMore
    {
      for $child in n:children(n:children($node)) return x:combine-optional-oneOrMore($child)
    }
  else if ($node/self::element()) then
    element {node-name($node)}
    {
      $node/@*,
      for $child in $node/node() return x:combine-optional-oneOrMore($child)
    }
  else $node
};

(:~
 : Determine sequence of left-recursive references within a grammar fragment.
 :
 : @param $nodes the grammar fragment.
 : @return the sequence of left-recursive references.
 :)
declare function x:left-recursive-refs($nodes as element()*) as element(g:ref)*
{
  let $node := n:unwrap-sequence($nodes)[1]
  return
    typeswitch ($node)
    case element(g:production) return
      if (count($nodes) ne 1) then
        error(xs:QName("n:left-recursive-refs"), string-join(("too many arguments:", $nodes/@name), " "))
      else
        x:left-recursive-refs(n:children($node))
    case element(g:optional) return
      x:left-recursive-refs(n:children($node))
    case element(g:oneOrMore) return
      x:left-recursive-refs(n:children($node))
    case element(g:zeroOrMore) return
      x:left-recursive-refs(n:children($node))
    case element(g:choice) return
      for $case in n:children($node)
      return x:left-recursive-refs(n:unwrap-sequence($case))
    case element(g:ref) return
      $node[empty(@context) and @name eq ancestor::g:production/@name]
    default return
      ()
};
(:~
 : Recursively calculate the set of nonterminals that can be reached
 : from a set of start symbols.
 :
 : @param $nonterminals the set of all nonterminal productions.
 : @param $start-symbols the set of start symbols.
 : @return the subset of $nonterminals that can be reached from
 : $start-symbols.
 :)
declare function x:ref($nonterminals as element(g:production)*,
                       $start-symbols as element(g:production)*
                      ) as element(g:production)*
{
  let $new := $nonterminals[@name = $start-symbols//g:ref[empty(@context)]/@name]
  return
    if (empty($new[not(@name = $start-symbols/@name)])) then
      $start-symbols
    else
      x:ref($nonterminals, ($start-symbols, $new)/.)
};

(:~
 : Get the sequence of parser productions from a grammar.
 :
 : @param $grammar the grammar.
 : @return the sequence of parser productions.
 :)
declare function x:parser-productions($grammar as element(g:grammar)) as element(g:production)*
{
  let $end := n:syntax-end($grammar)
  let $parser-rules := n:children($grammar)[not(. is $end or . >> $end)]
  return $parser-rules/self::g:production
};

(:~
 : Get the whitepace definition from a grammar.
 :
 : @param $grammar the grammar.
 : @return the whitespace definition production, if the grammar contains one.
 :)
declare function x:whitespace($grammar as element(g:grammar)) as element(g:production)?
{
  x:parser-productions($grammar)[@whitespace-spec eq "definition"]
};

(:~
 : Get the start symbols from a grammar.
 :
 : @param $grammar the grammar.
 : @return the set of start symbols. Empty, if none could be identified.
 :)
declare function x:start-symbols($grammar as element(g:grammar)) as element(g:production)*
{
  let $parser-productions := x:parser-productions($grammar)
  for $s in $parser-productions[not(@whitespace-spec eq "definition")]
  where empty($parser-productions//g:ref[@name eq $s/@name and empty(@context)])
  return $s
};

(:~
 : Check whether a grammar fragment is in BNF, as opposed to
 : EBNF.
 :
 : @param $nodes the grammar fragment as a sequence of nodes.
 : @return true, if it does not contain non-BNF constructs.
 :)
declare function x:is-bnf($nodes as node()*) as xs:boolean
{
  every $node in $nodes
  satisfies
    typeswitch ($node)
    case element(g:grammar) return
      every $p in x:parser-productions($node) satisfies x:is-bnf($p)
    case element(g:production) return
      let $children := n:children($node)
      return
        if ($node/g:choice and count($children) eq 1) then
          every $c in $children/*/n:unwrap-sequence(.) satisfies(x:is-bnf($c))
        else
          x:is-bnf($children)
    case element(g:ref) return
      true()
    case element(g:string) return
      true()
    case processing-instruction() return
      true()
    default return
      false()
};

(:~
 : Rewrite a grammar fragment, while inlining some nonterminal references. Drop
 : the inlined productions from the result.
 :
 : @param $nodes the grammar fragment.
 : @param $inline-nonterminals the set of nonterminal productions
 : that will be inlined to their reference context.
 : @return the rewritten grammar fragment.
 :)
declare function x:inline($nodes as node()*,
                          $inline-nonterminals as element(g:production)*) as node()*
{
  for $node in $nodes
  return
    typeswitch ($node)
    case element(g:production) return
      if ($node/@name = $inline-nonterminals/@name) then
        ()
      else
        element g:production
        {
          $node/@*,
          x:inline($node/node(), $inline-nonterminals)
        }
    case element(g:ref) return
      if ($node/@context or not($node/@name = $inline-nonterminals/@name)) then
        $node
      else
        let $definition := $inline-nonterminals[@name eq $node/@name]
        return x:inline(n:children($definition), $inline-nonterminals)
    case element() return
      element {node-name($node)}
      {
        $node/@*,
        let $children := n:children($node)
        return
          if (empty($children)) then
            $node/node()
          else
            for $c in n:children($node)
            return
              if (n:is-sequence-item($c)) then
                x:inline($c, $inline-nonterminals)
              else
                n:wrap-sequence(x:inline(n:unwrap-sequence($c), $inline-nonterminals))
      }
    default return
      $node
};
