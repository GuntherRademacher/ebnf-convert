xquery version "1.0" encoding "UTF-8";

(: This file was generated on Thu Mar 9, 2023 13:03 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net> :)
(: REx command line: -q -tree -a none -xquery -name de/bottlecaps/convert/xq/regexp/regexp.xquery ../../../../../../../main/java/de/bottlecaps/convert/regexp/regexp.ebnf :)

(:~
 : The parser that was generated for the de/bottlecaps/convert/xq/regexp/regexp.xquery grammar.
 :)
module namespace p="de/bottlecaps/convert/xq/regexp/regexp.xquery";
declare default function namespace "http://www.w3.org/2005/xpath-functions";

(:~
 : The index of the lexer state for accessing the combined
 : (i.e. level > 1) lookahead code.
 :)
declare variable $p:lk as xs:integer := 1;

(:~
 : The index of the lexer state for accessing the position in the
 : input string of the begin of the token that has been consumed.
 :)
declare variable $p:b0 as xs:integer := 2;

(:~
 : The index of the lexer state for accessing the position in the
 : input string of the end of the token that has been consumed.
 :)
declare variable $p:e0 as xs:integer := 3;

(:~
 : The index of the lexer state for accessing the code of the
 : level-1-lookahead token.
 :)
declare variable $p:l1 as xs:integer := 4;

(:~
 : The index of the lexer state for accessing the position in the
 : input string of the begin of the level-1-lookahead token.
 :)
declare variable $p:b1 as xs:integer := 5;

(:~
 : The index of the lexer state for accessing the position in the
 : input string of the end of the level-1-lookahead token.
 :)
declare variable $p:e1 as xs:integer := 6;

(:~
 : The index of the lexer state for accessing the token code that
 : was expected when an error was found.
 :)
declare variable $p:error as xs:integer := 7;

(:~
 : The index of the lexer state that points to the first entry
 : used for collecting action results.
 :)
declare variable $p:result as xs:integer := 8;

(:~
 : The codepoint to charclass mapping for 7 bit codepoints.
 :)
declare variable $p:MAP0 as xs:integer+ :=
(
  17, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1,
  1, 2, 3, 4, 5, 1, 6, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 1, 1, 9, 1, 10, 1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
  11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 13, 14, 15, 1, 1, 11, 11, 11, 11, 11, 11, 11, 11,
  11, 11, 11, 11, 11, 1, 11, 11, 11, 1, 1, 1, 11, 11, 11, 11, 11, 11, 1, 16, 1, 1, 1
);

(:~
 : The codepoint to charclass mapping for codepoints below the surrogate block.
 :)
declare variable $p:MAP1 as xs:integer+ :=
(
  54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
  58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 90, 206, 122, 153,
  182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182,
  182, 182, 182, 182, 182, 182, 182, 182, 182, 17, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11,
  11, 11, 11, 12, 13, 14, 15, 1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 1, 11, 11, 11, 1, 1, 1, 11, 11, 11,
  11, 11, 11, 1, 16, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2,
  3, 4, 5, 1, 6, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 1, 1, 9, 1, 10
);

(:~
 : The codepoint to charclass mapping for codepoints above the surrogate block.
 :)
declare variable $p:MAP2 as xs:integer+ :=
(
  57344, 65536, 65533, 1114111, 1, 1
);

(:~
 : The token-set-id to DFA-initial-state mapping.
 :)
declare variable $p:INITIAL as xs:integer+ :=
(
  1, 2, 3, 4, 5, 6, 7
);

(:~
 : The DFA transition table.
 :)
declare variable $p:TRANSITION as xs:integer+ :=
(
  138, 138, 138, 138, 75, 72, 80, 136, 75, 90, 80, 136, 75, 97, 80, 136, 75, 101, 80, 136, 75, 105, 80, 136, 76, 72, 80,
  136, 75, 109, 80, 136, 75, 72, 80, 133, 75, 72, 80, 130, 75, 113, 117, 121, 75, 127, 137, 138, 75, 142, 80, 136, 146,
  150, 154, 136, 86, 72, 123, 136, 93, 72, 80, 136, 75, 158, 80, 136, 138, 83, 138, 138, 48, 48, 48, 64, 64, 64, 64, 73,
  1760, 0, 32, 0, 80, 80, 0, 288, 0, 288, 106, 106, 106, 64, 64, 304, 64, 48, 144, 144, 64, 48, 48, 172, 64, 48, 48,
  205, 64, 240, 240, 240, 64, 48, 48, 256, 64, 1760, 15, 32, 176, 208, 1760, 0, 0, 32, 0, 48, 48, 48, 0, 1760, 128, 0,
  1760, 112, 0, 1760, 0, 0, 0, 0, 272, 272, 272, 64, 8, 8, 8, 8, 59, 59, 59, 64, 14, 0, 32, 0, 48, 320, 320, 64
);

(:~
 : The DFA-state to expected-token-set mapping.
 :)
declare variable $p:EXPECTED as xs:integer+ :=
(
  8, 131080, 262152, 139272, 82150, 606710, 647158, 8, 8192, 192, 2, 1024, 4096, 8192, 192
);

(:~
 : The token-string table.
 :)
declare variable $p:TOKEN as xs:string+ :=
(
  "(0)",
  "escaped-character",
  "plain-character",
  "range-character",
  "EOF",
  "'('",
  "'(?:'",
  "'(?='",
  "')'",
  "'*'",
  "'*?'",
  "'+'",
  "'+?'",
  "'-'",
  "'.'",
  "'?'",
  "'['",
  "']'",
  "'^'",
  "'|'"
);

(:~
 : Match next token in input string, starting at given index, using
 : the DFA entry state for the set of tokens that are expected in
 : the current context.
 :
 : @param $input the input string.
 : @param $begin the index where to start in input string.
 : @param $token-set the expected token set id.
 : @return a sequence of three: the token code of the result token,
 : with input string begin and end positions. If there is no valid
 : token, return the negative id of the DFA state that failed, along
 : with begin and end positions of the longest viable prefix.
 :)
declare function p:match($input as xs:string,
                         $begin as xs:integer,
                         $token-set as xs:integer) as xs:integer+
{
  let $result := $p:INITIAL[1 + $token-set]
  return p:transition($input,
                      $begin,
                      $begin,
                      $begin,
                      $result,
                      $result mod 16,
                      0)
};

(:~
 : The DFA state transition function. If we are in a valid DFA state, save
 : it's result annotation, consume one input codepoint, calculate the next
 : state, and use tail recursion to do the same again. Otherwise, return
 : any valid result or a negative DFA state id in case of an error.
 :
 : @param $input the input string.
 : @param $begin the begin index of the current token in the input string.
 : @param $current the index of the current position in the input string.
 : @param $end the end index of the result in the input string.
 : @param $result the result code.
 : @param $current-state the current DFA state.
 : @param $previous-state the  previous DFA state.
 : @return a sequence of three: the token code of the result token,
 : with input string begin and end positions. If there is no valid
 : token, return the negative id of the DFA state that failed, along
 : with begin and end positions of the longest viable prefix.
 :)
declare function p:transition($input as xs:string,
                              $begin as xs:integer,
                              $current as xs:integer,
                              $end as xs:integer,
                              $result as xs:integer,
                              $current-state as xs:integer,
                              $previous-state as xs:integer)
{
  if ($current-state eq 0) then
    let $result := $result idiv 16
    let $end :=
      if ($result idiv 32 mod 2 eq 1) then
        $begin + $result idiv 64
      else
        $end - $result idiv 64
    let $end := if ($end gt string-length($input)) then string-length($input) + 1 else $end
    return
      if ($result ne 0) then
      (
        $result mod 32 - 1,
        $begin,
        $end
      )
      else
      (
        - $previous-state,
        $begin,
        $current - 1
      )
  else
    let $c0 := (string-to-codepoints(substring($input, $current, 1)), 0)[1]
    let $c1 :=
      if ($c0 < 128) then
        $p:MAP0[1 + $c0]
      else if ($c0 < 55296) then
        let $c1 := $c0 idiv 32
        let $c2 := $c1 idiv 32
        return $p:MAP1[1 + $c0 mod 32 + $p:MAP1[1 + $c1 mod 32 + $p:MAP1[1 + $c2]]]
      else
        p:map2($c0, 1, 2)
    let $current := $current + 1
    let $i0 := 16 * $c1 + $current-state - 1
    let $i1 := $i0 idiv 4
    let $next-state := $p:TRANSITION[$i0 mod 4 + $p:TRANSITION[$i1 + 1] + 1]
    return
      if ($next-state > 15) then
        p:transition($input, $begin, $current, $current, $next-state, $next-state mod 16, $current-state)
      else
        p:transition($input, $begin, $current, $end, $result, $next-state, $current-state)
};

(:~
 : Recursively translate one 32-bit chunk of an expected token bitset
 : to the corresponding sequence of token strings.
 :
 : @param $result the result of previous recursion levels.
 : @param $chunk the 32-bit chunk of the expected token bitset.
 : @param $base-token-code the token code of bit 0 in the current chunk.
 : @return the set of token strings.
 :)
declare function p:token($result as xs:string*,
                         $chunk as xs:integer,
                         $base-token-code as xs:integer)
{
  if ($chunk = 0) then
    $result
  else
    p:token
    (
      ($result, if ($chunk mod 2 != 0) then $p:TOKEN[$base-token-code] else ()),
      if ($chunk < 0) then $chunk idiv 2 + 2147483648 else $chunk idiv 2,
      $base-token-code + 1
    )
};

(:~
 : Calculate expected token set for a given DFA state as a sequence
 : of strings.
 :
 : @param $state the DFA state.
 : @return the set of token strings.
 :)
declare function p:expected-token-set($state as xs:integer) as xs:string*
{
  if ($state > 0) then
    for $t in 0 to 0
    let $i0 := $t * 15 + $state - 1
    return p:token((), $p:EXPECTED[$i0 + 1], $t * 32 + 1)
  else
    ()
};

(:~
 : Classify codepoint by doing a tail recursive binary search for a
 : matching codepoint range entry in MAP2, the codepoint to charclass
 : map for codepoints above the surrogate block.
 :
 : @param $c the codepoint.
 : @param $lo the binary search lower bound map index.
 : @param $hi the binary search upper bound map index.
 : @return the character class.
 :)
declare function p:map2($c as xs:integer, $lo as xs:integer, $hi as xs:integer) as xs:integer
{
  if ($lo > $hi) then
    0
  else
    let $m := ($hi + $lo) idiv 2
    return
      if ($p:MAP2[$m] > $c) then
        p:map2($c, $lo, $m - 1)
      else if ($p:MAP2[2 + $m] < $c) then
        p:map2($c, $m + 1, $hi)
      else
        $p:MAP2[4 + $m]
};

(:~
 : Parse occurrence.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-occurrence($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 15) then                       (: '?' :)
      let $state := p:consume(15, $input, $state)           (: '?' :)
      return $state
    else if ($state[$p:l1] = 9) then                        (: '*' :)
      let $state := p:consume(9, $input, $state)            (: '*' :)
      return $state
    else if ($state[$p:l1] = 11) then                       (: '+' :)
      let $state := p:consume(11, $input, $state)           (: '+' :)
      return $state
    else if ($state[$p:l1] = 10) then                       (: '*?' :)
      let $state := p:consume(10, $input, $state)           (: '*?' :)
      return $state
    else
      let $state := p:consume(12, $input, $state)           (: '+?' :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "occurrence", $count, $begin, $end)
};

(:~
 : Parse range.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-range($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:lookahead1(0, $input, $state)             (: range-character :)
  let $state := p:consume(3, $input, $state)                (: range-character :)
  let $state := p:lookahead1(3, $input, $state)             (: range-character | '-' | ']' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 13) then                       (: '-' :)
      let $state := p:consume(13, $input, $state)           (: '-' :)
      let $state := p:lookahead1(0, $input, $state)         (: range-character :)
      let $state := p:consume(3, $input, $state)            (: range-character :)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "range", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production charset (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-charset-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-range($input, $state)
    let $state := p:lookahead1(1, $input, $state)           (: range-character | ']' :)
    return
      if ($state[$p:l1] != 3) then                          (: range-character :)
        $state
      else
        p:parse-charset-1($input, $state)
};

(:~
 : Parse charset.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-charset($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(16, $input, $state)               (: '[' :)
  let $state := p:lookahead1(2, $input, $state)             (: range-character | '^' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 18) then                       (: '^' :)
      let $state := p:consume(18, $input, $state)           (: '^' :)
      return $state
    else
      $state
  let $state := p:parse-charset-1($input, $state)
  let $state := p:consume(17, $input, $state)               (: ']' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "charset", $count, $begin, $end)
};

(:~
 : Parse character.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-character($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 1) then                        (: escaped-character :)
      let $state := p:consume(1, $input, $state)            (: escaped-character :)
      return $state
    else
      let $state := p:consume(2, $input, $state)            (: plain-character :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "character", $count, $begin, $end)
};

(:~
 : Parse primary.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-primary($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:lookahead1(4, $input, $state)             (: escaped-character | plain-character | '(' | '(?:' |
                                                               '(?=' | '.' | '[' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 14) then                       (: '.' :)
      let $state := p:consume(14, $input, $state)           (: '.' :)
      return $state
    else if ($state[$p:l1] = 16) then                       (: '[' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-charset($input, $state)
      return $state
    else if ($state[$p:l1] = 5) then                        (: '(' :)
      let $state := p:consume(5, $input, $state)            (: '(' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-alternatives($input, $state)
      let $state := p:consume(8, $input, $state)            (: ')' :)
      return $state
    else if ($state[$p:l1] = 6) then                        (: '(?:' :)
      let $state := p:consume(6, $input, $state)            (: '(?:' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-alternatives($input, $state)
      let $state := p:consume(8, $input, $state)            (: ')' :)
      return $state
    else if ($state[$p:l1] = 7) then                        (: '(?=' :)
      let $state := p:consume(7, $input, $state)            (: '(?=' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-alternatives($input, $state)
      let $state := p:consume(8, $input, $state)            (: ')' :)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-character($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "primary", $count, $begin, $end)
};

(:~
 : Parse item.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-item($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-primary($input, $state)
  let $state := p:lookahead1(6, $input, $state)             (: escaped-character | plain-character | EOF | '(' | '(?:' |
                                                               '(?=' | ')' | '*' | '*?' | '+' | '+?' | '.' | '?' | '[' |
                                                               '|' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 9                              (: '*' :)
          or $state[$p:l1] = 10                             (: '*?' :)
          or $state[$p:l1] = 11                             (: '+' :)
          or $state[$p:l1] = 12                             (: '+?' :)
          or $state[$p:l1] = 15) then                       (: '?' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-occurrence($input, $state)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "item", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production sequence (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-sequence-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-item($input, $state)
    let $state := p:lookahead1(5, $input, $state)           (: escaped-character | plain-character | EOF | '(' | '(?:' |
                                                               '(?=' | ')' | '.' | '[' | '|' :)
    return
      if ($state[$p:l1] = 4                                 (: EOF :)
       or $state[$p:l1] = 8                                 (: ')' :)
       or $state[$p:l1] = 19) then                          (: '|' :)
        $state
      else
        p:parse-sequence-1($input, $state)
};

(:~
 : Parse sequence.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-sequence($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-sequence-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "sequence", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production alternatives (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-alternatives-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 19) then                           (: '|' :)
      $state
    else
      let $state := p:consume(19, $input, $state)           (: '|' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-sequence($input, $state)
      return p:parse-alternatives-1($input, $state)
};

(:~
 : Parse alternatives.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-alternatives($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-sequence($input, $state)
  let $state := p:parse-alternatives-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "alternatives", $count, $begin, $end)
};

(:~
 : Parse regexp.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-regexp($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-alternatives($input, $state)
  let $state := p:consume(4, $input, $state)                (: EOF :)
  let $end := $state[$p:e0]
  return p:reduce($state, "regexp", $count, $begin, $end)
};

(:~
 : Create a textual error message from a parsing error.
 :
 : @param $input the input string.
 : @param $error the parsing error descriptor.
 : @return the error message.
 :)
declare function p:error-message($input as xs:string, $error as element(error)) as xs:string
{
  let $begin := xs:integer($error/@b)
  let $context := string-to-codepoints(substring($input, 1, $begin - 1))
  let $linefeeds := index-of($context, 10)
  let $line := count($linefeeds) + 1
  let $column := ($begin - $linefeeds[last()], $begin)[1]
  return
    string-join
    (
      (
        if ($error/@o) then
          ("syntax error, found ", $p:TOKEN[$error/@o + 1])
        else
          "lexical analysis failed",
        "&#10;",
        "while expecting ",
        if ($error/@x) then
          $p:TOKEN[$error/@x + 1]
        else
          let $expected := p:expected-token-set($error/@s)
          return
          (
            "["[exists($expected[2])],
            string-join($expected, ", "),
            "]"[exists($expected[2])]
          ),
        "&#10;",
        if ($error/@o or $error/@e = $begin) then
          ()
        else
          ("after successfully scanning ", string($error/@e - $begin), " characters beginning "),
        "at line ", string($line), ", column ", string($column), ":&#10;",
        "...", substring($input, $begin, 64), "..."
      ),
      ""
    )
};

(:~
 : Consume one token, i.e. compare lookahead token 1 with expected
 : token and in case of a match, shift lookahead tokens down such that
 : l1 becomes the current token, and higher lookahead tokens move down.
 : When lookahead token 1 does not match the expected token, raise an
 : error by saving the expected token code in the error field of the
 : lexer state.
 :
 : @param $code the expected token.
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:consume($code as xs:integer, $input as xs:string, $state as item()+) as item()+
{
  if ($state[$p:error]) then
    $state
  else if ($state[$p:l1] eq $code) then
  (
    subsequence($state, $p:l1, 3),
    0, 0, 0,
    subsequence($state, 7),
    let $begin := $state[$p:e0]
    let $end := $state[$p:b1]
    where $begin ne $end
    return
      text
      {
        substring($input, $begin, $end - $begin)
      },
    let $token := $p:TOKEN[1 + $state[$p:l1]]
    let $name := if (starts-with($token, "'")) then "TOKEN" else $token
    let $begin := $state[$p:b1]
    let $end := $state[$p:e1]
    return
      element {$name}
      {
        substring($input, $begin, $end - $begin)
      }
  )
  else
  (
    subsequence($state, 1, $p:error - 1),
    element error
    {
      attribute b {$state[$p:b1]},
      attribute e {$state[$p:e1]},
      if ($state[$p:l1] lt 0) then
        attribute s {- $state[$p:l1]}
      else
        (attribute o {$state[$p:l1]}, attribute x {$code})
    },
    subsequence($state, $p:error + 1)
  )
};

(:~
 : Lookahead one token on level 1.
 :
 : @param $set the code of the DFA entry state for the set of valid tokens.
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:lookahead1($set as xs:integer, $input as xs:string, $state as item()+) as item()+
{
  if ($state[$p:l1] ne 0) then
    $state
  else
    let $match :=
        p:match($input, $state[$p:e0], $set)
    return
    (
      $match[1],
      subsequence($state, $p:b0, 2),
      $match,
      subsequence($state, 7)
    )
};

(:~
 : Reduce the result stack, creating a nonterminal element. Pop
 : $count elements off the stack, wrap them in a new element
 : named $name, and push the new element.
 :
 : @param $state lexer state, error indicator, and result stack.
 : @param $name the name of the result node.
 : @param $count the number of child nodes.
 : @param $begin the input index where the nonterminal begins.
 : @param $end the input index where the nonterminal ends.
 : @return the updated state.
 :)
declare function p:reduce($state as item()+, $name as xs:string, $count as xs:integer, $begin as xs:integer, $end as xs:integer) as item()+
{
  subsequence($state, 1, $count),
  element {$name}
  {
    subsequence($state, $count + 1)
  }
};

(:~
 : Parse start symbol regexp from given string.
 :
 : @param $s the string to be parsed.
 : @return the result as generated by parser actions.
 :)
declare function p:parse-regexp($s as xs:string) as item()*
{
  let $state := (0, 1, 1, 0, 0, 0, false())
  let $state := p:parse-regexp($s, $state)
  let $error := $state[$p:error]
  return
    if ($error) then
      element ERROR {$error/@*, p:error-message($s, $error)}
    else
      subsequence($state, $p:result)
};

(: End :)
