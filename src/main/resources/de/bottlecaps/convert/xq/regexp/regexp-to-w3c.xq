module namespace x="de/bottlecaps/convert/xq/regexp/regexp-to-w3c.xq";

declare namespace g="http://www.w3.org/2001/03/XPath/grammar";

declare function x:rewrite-charClass($done, $todo)
{
  if (empty($todo)) then
    $done
  else
    let $item := $todo[1]
    let $result :=
      let $rewrite1 := x:rewrite-character($item/range-character[1], true())
      return
        if (not($item/range-character[2])) then
          $rewrite1
        else
          let $rewrite2 := x:rewrite-character($item/range-character[2], true())
          return
            if (count($rewrite1) eq 1 and count($rewrite2) eq 1) then
              element g:charRange {attribute minChar {$rewrite1}, attribute maxChar {$rewrite2}}
            else
              error(xs:QName("x:rewrite-charClass"), concat("found regexp with invalid character range: ", $item))
    return x:rewrite-charClass(($done, $result), subsequence($todo, 2))
};

declare function x:rewrite-character($string, $is-charclass)
{
  let $character := substring($string, string-length($string))
  let $rewritten :=
    if (string-length($string) eq 1) then
      <g:char>{$character}</g:char>
    else if ($character eq 's') then
    (
      <g:char>&#x20;</g:char>,
      <g:charCode value="9"/>,
      <g:charCode value="A"/>,
      <g:charCode value="B"/>,
      <g:charCode value="C"/>,
      <g:charCode value="D"/>
    )
    else if ($character eq 't') then
      <g:charCode value="9"/>
    else if ($character eq 'r') then
      <g:charCode value="D"/>
    else if ($character eq 'n') then
      <g:charCode value="A"/>
    else
      <g:char>{$character}</g:char>
  return
    if ($is-charclass) then
      $rewritten
    else if (count($rewritten) eq 1 and $rewritten/self::g:char) then
      <g:string>{string($character)}</g:string  >
    else
      element g:charClass {$rewritten}
};

declare function x:rewrite-primary($primary)
{
  let $item := $primary/*
  return
    if ($item/self::TOKEN[. eq '.']) then
      element g:ref {attribute name {'.'}}
    else if ($item/self::character) then
      x:rewrite-character($item, false())
    else if ($item/self::charset) then
      if ($item/TOKEN[. eq '^']) then
        element g:complement {element g:charClass {x:rewrite-charClass((), $item/range)}}
      else
        element g:charClass {x:rewrite-charClass((), $item/range)}
    else if ($item/self::TOKEN[. = ('(', '(?:')]) then
      x:rewrite-alternatives($item/self::alternatives)
    else if ($item/self::TOKEN[. eq '(?=']) then
      ()
    else
      error(xs:QName("x:rewrite-primary"), concat("unsupported item: ", local-name($item), '(', $item, ')'))
};

declare function x:rewrite-sequence($done, $todo)
{
  if (empty($todo)) then
    $done
  else
    let $item := $todo[1]
    let $result :=
      let $rewritten := x:rewrite-primary($item/primary)
      let $occurrence := $item/occurrence
      return
        if (empty($rewritten) or empty($occurrence)) then
          $rewritten
        else if ($occurrence = ("*", "*?")) then
          element g:zeroOrMore {$rewritten}
        else if ($occurrence = ("+", "+?")) then
          element g:oneOrMore {$rewritten}
        else if ($occurrence eq "?") then
          element g:optional {$rewritten}
        else
          error()
    return x:rewrite-sequence(($done, $result), subsequence($todo, 2))
};

declare function x:combine-strings($done, $todo)
{
  if (empty($todo)) then
    $done
  else
    let $last := $done[last()]
    let $item := $todo[1]
    return
      if (not($item/self::g:string and $last/self::g:string) or
          contains($last, '"') and contains($item, "'") or
          contains($last, "'") and contains($item, '"')) then
        x:combine-strings(($done, $item), subsequence($todo, 2))
      else
        let $done := subsequence($done, 1, count($done) - 1)
        let $item := element g:string {concat($last, $item)}
        return x:combine-strings(($done, $item), subsequence($todo, 2))
};

declare function x:rewrite-alternatives($alternatives as element(alternatives))
{
  let $rewritten :=
    for $sequence in $alternatives/sequence
    let $r := x:rewrite-sequence((), $sequence/item)
    let $r := x:combine-strings((), $r)
    return
      if (count($r) eq 1) then
        $r
      else
        element g:sequence {$r}
  return
    if (count($rewritten) eq 1) then
      if ($rewritten/self::g:sequence) then $rewritten/* else $rewritten
    else
      element g:choice {$rewritten}
};

declare function x:regexp-to-w3c($parse-tree as element(regexp))
{
  x:rewrite-alternatives($parse-tree/alternatives)
};

(:
let $regexps :=
(
  <regexp>[a-zA-Z0-9_]+\??</regexp>,
  <regexp>\".*?\"(?=\.|\s)</regexp>,
  <regexp>match\(.*?\)</regexp>,
  <regexp>[^\s\.\"]+</regexp>,
  <regexp>(?:[^"\\]|\\.)*</regexp>,
  <regexp>(?:[^\'\\]|\\.)*</regexp>,
  <regexp>[0-9]+</regexp>,
  <regexp>\s+</regexp>,
  <regexp>AB(CD)EF</regexp>
)
for $regexp in $regexps
return concat(b:render(x:regexp-to-w3c(p:parse-regexp($regexp))), '&#xA;')
:)
