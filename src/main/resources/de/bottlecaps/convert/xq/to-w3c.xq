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
