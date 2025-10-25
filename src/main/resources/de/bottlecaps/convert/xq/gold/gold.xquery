xquery version "1.0" encoding "UTF-8";

(: This file was generated on Sat Oct 25, 2025 17:36 (UTC+02) by REx v6.2-SNAPSHOT which is Copyright (c) 1979-2025 by Gunther Rademacher <grd@gmx.net> :)
(: REx command line: -q -backtrack -tree -a none -xquery -name de/bottlecaps/convert/xq/gold/gold.xquery ../../../../../../../main/java/de/bottlecaps/convert/gold/gold.ebnf :)

(:~
 : The parser that was generated for the de/bottlecaps/convert/xq/gold/gold.xquery grammar.
 :)
module namespace p="de/bottlecaps/convert/xq/gold/gold.xquery";
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
 : The index of the lexer state for accessing the code of the
 : level-2-lookahead token.
 :)
declare variable $p:l2 as xs:integer := 7;

(:~
 : The index of the lexer state for accessing the position in the
 : input string of the begin of the level-2-lookahead token.
 :)
declare variable $p:b2 as xs:integer := 8;

(:~
 : The index of the lexer state for accessing the position in the
 : input string of the end of the level-2-lookahead token.
 :)
declare variable $p:e2 as xs:integer := 9;

(:~
 : The index of the lexer state for accessing the code of the
 : level-3-lookahead token.
 :)
declare variable $p:l3 as xs:integer := 10;

(:~
 : The index of the lexer state for accessing the position in the
 : input string of the begin of the level-3-lookahead token.
 :)
declare variable $p:b3 as xs:integer := 11;

(:~
 : The index of the lexer state for accessing the position in the
 : input string of the end of the level-3-lookahead token.
 :)
declare variable $p:e3 as xs:integer := 12;

(:~
 : The index of the lexer state for accessing the token code that
 : was expected when an error was found.
 :)
declare variable $p:error as xs:integer := 13;

(:~
 : The index of the lexer state for accessing the memoization
 : of backtracking results.
 :)
declare variable $p:memo as xs:integer := 14;

(:~
 : The index of the lexer state that points to the first entry
 : used for collecting action results.
 :)
declare variable $p:result as xs:integer := 15;

(:~
 : The codepoint to charclass mapping for 7 bit codepoints.
 :)
declare variable $p:MAP0 as xs:integer+ :=
(
  28, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 7, 8, 8, 8, 8,
  9, 10, 11, 12, 13, 8, 14, 15, 8, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 8, 17, 18, 19, 20, 8, 15, 15, 15, 15, 15,
  15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 21, 8, 22, 8, 15, 8, 15, 15, 15,
  15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 23, 24, 25, 8, 8
);

(:~
 : The codepoint to charclass mapping for codepoints below the surrogate block.
 :)
declare variable $p:MAP1 as xs:integer+ :=
(
  54, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65,
  65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 129, 143, 159,
  195, 174, 179, 174, 211, 228, 228, 227, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228,
  228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228,
  228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228,
  228, 228, 228, 28, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 7, 8,
  8, 8, 8, 9, 10, 11, 12, 13, 8, 14, 15, 8, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 21, 8, 22, 8,
  15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 8, 17, 18, 19, 20, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 23, 24,
  25, 8, 8, 27, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26
);

(:~
 : The codepoint to charclass mapping for codepoints above the surrogate block.
 :)
declare variable $p:MAP2 as xs:integer+ :=
(
  57344, 65536, 65533, 1114111, 26, 26
);

(:~
 : The token-set-id to DFA-initial-state mapping.
 :)
declare variable $p:INITIAL as xs:integer+ :=
(
  1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
);

(:~
 : The DFA transition table.
 :)
declare variable $p:TRANSITION as xs:integer+ :=
(
  240, 240, 240, 240, 240, 240, 240, 240, 232, 232, 249, 620, 464, 240, 240, 240, 259, 278, 571, 334, 464, 240, 240,
  240, 232, 232, 239, 240, 240, 240, 240, 240, 286, 300, 240, 334, 464, 240, 240, 240, 232, 232, 308, 346, 361, 240,
  240, 240, 373, 373, 381, 491, 405, 240, 240, 240, 241, 398, 381, 315, 567, 240, 240, 240, 240, 240, 381, 491, 498,
  240, 240, 240, 596, 417, 425, 322, 464, 240, 240, 240, 624, 626, 381, 491, 498, 240, 240, 240, 240, 502, 381, 491,
  498, 240, 240, 240, 240, 251, 444, 491, 432, 240, 240, 240, 436, 365, 381, 491, 498, 240, 240, 240, 265, 270, 476,
  346, 361, 240, 240, 240, 530, 270, 476, 346, 361, 240, 240, 240, 388, 606, 484, 491, 498, 240, 240, 240, 338, 510,
  381, 491, 498, 240, 240, 240, 329, 468, 381, 517, 498, 240, 240, 240, 240, 240, 381, 491, 525, 240, 240, 240, 240,
  390, 381, 491, 498, 240, 240, 240, 538, 292, 546, 451, 498, 240, 240, 240, 240, 240, 546, 560, 498, 240, 240, 240,
  579, 585, 593, 458, 498, 240, 240, 240, 409, 353, 381, 491, 498, 240, 240, 240, 240, 240, 593, 553, 498, 240, 240,
  240, 240, 240, 604, 620, 464, 240, 240, 240, 232, 232, 308, 491, 498, 240, 240, 240, 240, 614, 240, 240, 240, 240,
  240, 240, 465, 465, 465, 465, 465, 465, 465, 465, 0, 0, 0, 0, 0, 0, 0, 0, 26, 465, 603, 0, 0, 0, 0, 0, 0, 0, 832, 0,
  512, 512, 512, 512, 512, 0, 0, 0, 280, 960, 280, 280, 280, 280, 280, 280, 280, 280, 512, 512, 512, 512, 512, 512, 512,
  512, 0, 533, 533, 533, 533, 533, 0, 0, 19, 0, 19, 0, 0, 19, 533, 533, 533, 533, 533, 533, 533, 533, 465, 603, 29, 31,
  0, 0, 23, 0, 0, 603, 604, 29, 30, 31, 0, 0, 603, 604, 30, 29, 31, 0, 0, 1088, 1088, 0, 0, 0, 0, 35, 0, 0, 0, 0, 0, 25,
  0, 25, 33, 34, 603, 604, 29, 30, 31, 0, 0, 1216, 1216, 1216, 0, 1216, 1216, 33, 34, 35, 35, 0, 0, 0, 0, 0, 896, 896,
  896, 594, 594, 594, 594, 594, 594, 594, 594, 0, 603, 29, 31, 0, 0, 23, 0, 22, 0, 0, 0, 0, 0, 0, 0, 1152, 26, 26, 26,
  26, 0, 26, 26, 0, 34, 35, 611, 0, 0, 0, 0, 0, 1216, 0, 0, 23, 23, 23, 23, 23, 23, 23, 23, 0, 603, 30, 31, 0, 0, 256,
  0, 34, 36, 36, 0, 0, 0, 0, 896, 0, 0, 0, 0, 604, 29, 31, 0, 0, 23, 0, 34, 603, 604, 0, 30, 31, 0, 34, 603, 604, 29,
  30, 0, 0, 35, 35, 0, 0, 0, 0, 0, 0, 1088, 0, 0, 603, 29, 31, 0, 0, 23, 280, 0, 603, 29, 31, 0, 32, 23, 0, 34, 603,
  604, 29, 30, 31, 0, 34, 35, 35, 0, 0, 0, 0, 768, 0, 0, 768, 25, 25, 25, 25, 0, 25, 25, 0, 34, 603, 604, 29, 30, 31,
  1024, 192, 34, 35, 35, 0, 0, 0, 0, 280, 0, 280, 280, 280, 19, 0, 0, 0, 0, 0, 19, 19, 0, 603, 0, 31, 0, 0, 23, 0, 34,
  603, 604, 29, 30, 384, 0, 34, 603, 604, 320, 30, 31, 0, 128, 35, 35, 0, 0, 0, 0, 512, 0, 0, 0, 20, 0, 0, 0, 0, 0, 20,
  20, 20, 20, 20, 20, 20, 20, 0, 603, 29, 0, 0, 0, 23, 0, 23, 23, 23, 0, 603, 0, 0, 0, 0, 0, 0, 22, 0, 0, 640, 0, 640,
  0, 640, 0, 0, 603, 604, 0, 0, 0, 0, 0, 0, 704, 0, 0, 704
);

(:~
 : The DFA-state to expected-token-set mapping.
 :)
declare variable $p:EXPECTED as xs:integer+ :=
(
  368, 33216, 65984, 65992, 25024, 262604, 1400, 382, 494, 1006, 262654, 263150, 265720, 9198, 369134, 409080, 64, 256,
  16, 32, 128, 32768, 8, 8, 4, 2, 256, 256, 16, 16, 32, 32768, 4, 2, 256, 256
);

(:~
 : The token-string table.
 :)
declare variable $p:TOKEN as xs:string+ :=
(
  "%ERROR",
  "ParameterName",
  "Nonterminal",
  "Terminal",
  "SetLiteral",
  "SetName",
  "Whitespace_Ch",
  "NewLine",
  "Comment",
  "EOF",
  "'('",
  "')'",
  "'*'",
  "'+'",
  "'-'",
  "'::='",
  "'='",
  "'?'",
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
                      $result mod 64,
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
    let $result := $result idiv 64
    let $end := if ($end gt string-length($input)) then string-length($input) + 1 else $end
    return
      if ($result ne 0) then
      (
        $result - 1,
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
        let $c1 := $c0 idiv 16
        let $c2 := $c1 idiv 64
        return $p:MAP1[1 + $c0 mod 16 + $p:MAP1[1 + $c1 mod 64 + $p:MAP1[1 + $c2]]]
      else
        p:map2($c0, 1, 2)
    let $current := $current + 1
    let $i0 := 64 * $c1 + $current-state - 1
    let $i1 := $i0 idiv 8
    let $next-state := $p:TRANSITION[$i0 mod 8 + $p:TRANSITION[$i1 + 1] + 1]
    return
      if ($next-state > 63) then
        p:transition($input, $begin, $current, $current, $next-state, $next-state mod 64, $current-state)
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
    let $i0 := $t * 36 + $state - 1
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
 : Parse Symbol.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Symbol($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 3) then                        (: Terminal :)
      let $state := p:consume(3, $input, $state)            (: Terminal :)
      return $state
    else
      let $state := p:consume(2, $input, $state)            (: Nonterminal :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Symbol", $count, $begin, $end)
};

(:~
 : Try parsing Symbol.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Symbol($input as xs:string, $state as item()+) as item()+
{
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 3) then                        (: Terminal :)
      let $state := p:consumeT(3, $input, $state)           (: Terminal :)
      return $state
    else
      let $state := p:consumeT(2, $input, $state)           (: Nonterminal :)
      return $state
  return $state
};

(:~
 : Parse the 1st loop of production Handle (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Handle-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(5, $input, $state)          (: Nonterminal | Terminal | Whitespace_Ch | NewLine |
                                                               Comment | '|' :)
    return
      if ($state[$p:l1] != 2                                (: Nonterminal :)
      and $state[$p:l1] != 3) then                          (: Terminal :)
        $state
      else
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-Symbol($input, $state)
        return p:parse-Handle-1($input, $state)
};

(:~
 : Try parsing the 1st loop of production Handle (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Handle-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(5, $input, $state)          (: Nonterminal | Terminal | Whitespace_Ch | NewLine |
                                                               Comment | '|' :)
    return
      if ($state[$p:l1] != 2                                (: Nonterminal :)
      and $state[$p:l1] != 3) then                          (: Terminal :)
        $state
      else
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:try-Symbol($input, $state)
        return p:try-Handle-1($input, $state)
};

(:~
 : Parse Handle.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Handle($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-Handle-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Handle", $count, $begin, $end)
};

(:~
 : Try parsing Handle.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Handle($input as xs:string, $state as item()+) as item()+
{
  let $state := p:try-Handle-1($input, $state)
  return $state
};

(:~
 : Parse the 1st loop of production Handles (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Handles-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state :=
      if ($state[$p:l1] eq 7) then                          (: NewLine :)
        let $state := p:lookahead2W(11, $input, $state)     (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment | EOF | '|' :)
        let $state :=
          if ($state[$p:lk] eq 231) then                    (: NewLine NewLine :)
            let $state := p:lookahead3W(11, $input, $state) (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment | EOF | '|' :)
            return $state
          else
            $state
        return $state
      else
        ($state[$p:l1], subsequence($state, $p:lk + 1))
    let $state :=
      if ($state[$p:error]) then
        $state
      else if ($state[$p:lk] = 7399) then                   (: NewLine NewLine NewLine :)
        let $state := p:memoized($state, 3)
        return
          if ($state[$p:lk] != 0) then
            $state
          else
            let $backtrack := $state
            let $state := p:strip-result($state)
            let $state :=
              if ($state[$p:error]) then
                $state
              else
                p:try-nl_opt($input, $state)
            let $state := p:consumeT(18, $input, $state)    (: '|' :)
            let $state := p:lookahead1W(5, $input, $state)  (: Nonterminal | Terminal | Whitespace_Ch | NewLine |
                                                               Comment | '|' :)
            let $state :=
              if ($state[$p:error]) then
                $state
              else
                p:try-Handle($input, $state)
            return
              if (not($state[$p:error])) then
                p:memoize($backtrack, $state, 3, $backtrack[$p:e0], -1, -1)
              else
                p:memoize($backtrack, $state, 3, $backtrack[$p:e0], -2, -2)
      else
        $state
    return
      if ($state[$p:lk] != -1
      and $state[$p:lk] != 18                               (: '|' :)
      and $state[$p:lk] != 583                              (: NewLine '|' :)
      and $state[$p:lk] != 18663) then                      (: NewLine NewLine '|' :)
        $state
      else
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-nl_opt($input, $state)
        let $state := p:consume(18, $input, $state)         (: '|' :)
        let $state := p:lookahead1W(5, $input, $state)      (: Nonterminal | Terminal | Whitespace_Ch | NewLine |
                                                               Comment | '|' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-Handle($input, $state)
        return p:parse-Handles-1($input, $state)
};

(:~
 : Parse Handles.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Handles($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Handle($input, $state)
  let $state := p:parse-Handles-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Handles", $count, $begin, $end)
};

(:~
 : Parse Rule_Decl.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Rule_Decl($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(2, $input, $state)                (: Nonterminal :)
  let $state := p:lookahead1W(1, $input, $state)            (: Whitespace_Ch | NewLine | Comment | '::=' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-nl_opt($input, $state)
  let $state := p:consume(15, $input, $state)               (: '::=' :)
  let $state := p:lookahead1W(5, $input, $state)            (: Nonterminal | Terminal | Whitespace_Ch | NewLine |
                                                               Comment | '|' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Handles($input, $state)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-nl($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Rule_Decl", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Reg_Exp_2 (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Reg_Exp_2-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 18) then                           (: '|' :)
      $state
    else
      let $state := p:consume(18, $input, $state)           (: '|' :)
      let $state := p:lookahead1W(6, $input, $state)        (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               Comment | '(' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Reg_Exp_Seq($input, $state)
      return p:parse-Reg_Exp_2-1($input, $state)
};

(:~
 : Try parsing the 1st loop of production Reg_Exp_2 (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Reg_Exp_2-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 18) then                           (: '|' :)
      $state
    else
      let $state := p:consumeT(18, $input, $state)          (: '|' :)
      let $state := p:lookahead1W(6, $input, $state)        (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               Comment | '(' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:try-Reg_Exp_Seq($input, $state)
      return p:try-Reg_Exp_2-1($input, $state)
};

(:~
 : Parse Reg_Exp_2.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Reg_Exp_2($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Reg_Exp_Seq($input, $state)
  let $state := p:parse-Reg_Exp_2-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Reg_Exp_2", $count, $begin, $end)
};

(:~
 : Try parsing Reg_Exp_2.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Reg_Exp_2($input as xs:string, $state as item()+) as item()+
{
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:try-Reg_Exp_Seq($input, $state)
  let $state := p:try-Reg_Exp_2-1($input, $state)
  return $state
};

(:~
 : Parse Kleene_Opt.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Kleene_Opt($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 13) then                       (: '+' :)
      let $state := p:consume(13, $input, $state)           (: '+' :)
      return $state
    else if ($state[$p:l1] = 17) then                       (: '?' :)
      let $state := p:consume(17, $input, $state)           (: '?' :)
      return $state
    else if ($state[$p:l1] = 12) then                       (: '*' :)
      let $state := p:consume(12, $input, $state)           (: '*' :)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Kleene_Opt", $count, $begin, $end)
};

(:~
 : Try parsing Kleene_Opt.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Kleene_Opt($input as xs:string, $state as item()+) as item()+
{
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 13) then                       (: '+' :)
      let $state := p:consumeT(13, $input, $state)          (: '+' :)
      return $state
    else if ($state[$p:l1] = 17) then                       (: '?' :)
      let $state := p:consumeT(17, $input, $state)          (: '?' :)
      return $state
    else if ($state[$p:l1] = 12) then                       (: '*' :)
      let $state := p:consumeT(12, $input, $state)          (: '*' :)
      return $state
    else
      $state
  return $state
};

(:~
 : Parse Reg_Exp_Item.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Reg_Exp_Item($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 4) then                        (: SetLiteral :)
      let $state := p:consume(4, $input, $state)            (: SetLiteral :)
      let $state := p:lookahead1W(15, $input, $state)       (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               NewLine | Comment | '(' | ')' | '*' | '+' | '?' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Kleene_Opt($input, $state)
      return $state
    else if ($state[$p:l1] = 5) then                        (: SetName :)
      let $state := p:consume(5, $input, $state)            (: SetName :)
      let $state := p:lookahead1W(15, $input, $state)       (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               NewLine | Comment | '(' | ')' | '*' | '+' | '?' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Kleene_Opt($input, $state)
      return $state
    else if ($state[$p:l1] = 3) then                        (: Terminal :)
      let $state := p:consume(3, $input, $state)            (: Terminal :)
      let $state := p:lookahead1W(15, $input, $state)       (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               NewLine | Comment | '(' | ')' | '*' | '+' | '?' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Kleene_Opt($input, $state)
      return $state
    else
      let $state := p:consume(10, $input, $state)           (: '(' :)
      let $state := p:lookahead1W(6, $input, $state)        (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               Comment | '(' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Reg_Exp_2($input, $state)
      let $state := p:consume(11, $input, $state)           (: ')' :)
      let $state := p:lookahead1W(15, $input, $state)       (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               NewLine | Comment | '(' | ')' | '*' | '+' | '?' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Kleene_Opt($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Reg_Exp_Item", $count, $begin, $end)
};

(:~
 : Try parsing Reg_Exp_Item.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Reg_Exp_Item($input as xs:string, $state as item()+) as item()+
{
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 4) then                        (: SetLiteral :)
      let $state := p:consumeT(4, $input, $state)           (: SetLiteral :)
      let $state := p:lookahead1W(15, $input, $state)       (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               NewLine | Comment | '(' | ')' | '*' | '+' | '?' | '|' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:try-Kleene_Opt($input, $state)
      return $state
    else if ($state[$p:l1] = 5) then                        (: SetName :)
      let $state := p:consumeT(5, $input, $state)           (: SetName :)
      let $state := p:lookahead1W(15, $input, $state)       (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               NewLine | Comment | '(' | ')' | '*' | '+' | '?' | '|' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:try-Kleene_Opt($input, $state)
      return $state
    else if ($state[$p:l1] = 3) then                        (: Terminal :)
      let $state := p:consumeT(3, $input, $state)           (: Terminal :)
      let $state := p:lookahead1W(15, $input, $state)       (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               NewLine | Comment | '(' | ')' | '*' | '+' | '?' | '|' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:try-Kleene_Opt($input, $state)
      return $state
    else
      let $state := p:consumeT(10, $input, $state)          (: '(' :)
      let $state := p:lookahead1W(6, $input, $state)        (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               Comment | '(' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:try-Reg_Exp_2($input, $state)
      let $state := p:consumeT(11, $input, $state)          (: ')' :)
      let $state := p:lookahead1W(15, $input, $state)       (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               NewLine | Comment | '(' | ')' | '*' | '+' | '?' | '|' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:try-Kleene_Opt($input, $state)
      return $state
  return $state
};

(:~
 : Parse the 1st loop of production Reg_Exp_Seq (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Reg_Exp_Seq-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-Reg_Exp_Item($input, $state)
    let $state := p:lookahead1W(12, $input, $state)         (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               NewLine | Comment | '(' | ')' | '|' :)
    return
      if ($state[$p:l1] = 7                                 (: NewLine :)
       or $state[$p:l1] = 11                                (: ')' :)
       or $state[$p:l1] = 18) then                          (: '|' :)
        $state
      else
        p:parse-Reg_Exp_Seq-1($input, $state)
};

(:~
 : Try parsing the 1st loop of production Reg_Exp_Seq (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Reg_Exp_Seq-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:try-Reg_Exp_Item($input, $state)
    let $state := p:lookahead1W(12, $input, $state)         (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               NewLine | Comment | '(' | ')' | '|' :)
    return
      if ($state[$p:l1] = 7                                 (: NewLine :)
       or $state[$p:l1] = 11                                (: ')' :)
       or $state[$p:l1] = 18) then                          (: '|' :)
        $state
      else
        p:try-Reg_Exp_Seq-1($input, $state)
};

(:~
 : Parse Reg_Exp_Seq.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Reg_Exp_Seq($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-Reg_Exp_Seq-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Reg_Exp_Seq", $count, $begin, $end)
};

(:~
 : Try parsing Reg_Exp_Seq.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Reg_Exp_Seq($input as xs:string, $state as item()+) as item()+
{
  let $state := p:try-Reg_Exp_Seq-1($input, $state)
  return $state
};

(:~
 : Parse the 1st loop of production Reg_Exp (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Reg_Exp-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state :=
      if ($state[$p:l1] eq 7) then                          (: NewLine :)
        let $state := p:lookahead2W(11, $input, $state)     (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment | EOF | '|' :)
        let $state :=
          if ($state[$p:lk] eq 231) then                    (: NewLine NewLine :)
            let $state := p:lookahead3W(11, $input, $state) (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment | EOF | '|' :)
            return $state
          else
            $state
        return $state
      else
        ($state[$p:l1], subsequence($state, $p:lk + 1))
    let $state :=
      if ($state[$p:error]) then
        $state
      else if ($state[$p:lk] = 7399) then                   (: NewLine NewLine NewLine :)
        let $state := p:memoized($state, 2)
        return
          if ($state[$p:lk] != 0) then
            $state
          else
            let $backtrack := $state
            let $state := p:strip-result($state)
            let $state :=
              if ($state[$p:error]) then
                $state
              else
                p:try-nl_opt($input, $state)
            let $state := p:consumeT(18, $input, $state)    (: '|' :)
            let $state := p:lookahead1W(6, $input, $state)  (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               Comment | '(' :)
            let $state :=
              if ($state[$p:error]) then
                $state
              else
                p:try-Reg_Exp_Seq($input, $state)
            return
              if (not($state[$p:error])) then
                p:memoize($backtrack, $state, 2, $backtrack[$p:e0], -1, -1)
              else
                p:memoize($backtrack, $state, 2, $backtrack[$p:e0], -2, -2)
      else
        $state
    return
      if ($state[$p:lk] != -1
      and $state[$p:lk] != 18                               (: '|' :)
      and $state[$p:lk] != 583                              (: NewLine '|' :)
      and $state[$p:lk] != 18663) then                      (: NewLine NewLine '|' :)
        $state
      else
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-nl_opt($input, $state)
        let $state := p:consume(18, $input, $state)         (: '|' :)
        let $state := p:lookahead1W(6, $input, $state)      (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               Comment | '(' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-Reg_Exp_Seq($input, $state)
        return p:parse-Reg_Exp-1($input, $state)
};

(:~
 : Parse Reg_Exp.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Reg_Exp($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Reg_Exp_Seq($input, $state)
  let $state := p:parse-Reg_Exp-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Reg_Exp", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Terminal_Name (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Terminal_Name-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:consume(3, $input, $state)              (: Terminal :)
    let $state := p:lookahead1W(3, $input, $state)          (: Terminal | Whitespace_Ch | NewLine | Comment | '=' :)
    return
      if ($state[$p:l1] != 3) then                          (: Terminal :)
        $state
      else
        p:parse-Terminal_Name-1($input, $state)
};

(:~
 : Parse Terminal_Name.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Terminal_Name($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-Terminal_Name-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Terminal_Name", $count, $begin, $end)
};

(:~
 : Parse Terminal_Decl.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Terminal_Decl($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Terminal_Name($input, $state)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-nl_opt($input, $state)
  let $state := p:consume(16, $input, $state)               (: '=' :)
  let $state := p:lookahead1W(6, $input, $state)            (: Terminal | SetLiteral | SetName | Whitespace_Ch |
                                                               Comment | '(' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Reg_Exp($input, $state)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-nl($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Terminal_Decl", $count, $begin, $end)
};

(:~
 : Parse Set_Item.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Set_Item($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 4) then                        (: SetLiteral :)
      let $state := p:consume(4, $input, $state)            (: SetLiteral :)
      return $state
    else
      let $state := p:consume(5, $input, $state)            (: SetName :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Set_Item", $count, $begin, $end)
};

(:~
 : Try parsing Set_Item.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Set_Item($input as xs:string, $state as item()+) as item()+
{
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 4) then                        (: SetLiteral :)
      let $state := p:consumeT(4, $input, $state)           (: SetLiteral :)
      return $state
    else
      let $state := p:consumeT(5, $input, $state)           (: SetName :)
      return $state
  return $state
};

(:~
 : Parse the 1st loop of production Set_Exp (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Set_Exp-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(4, $input, $state)          (: Whitespace_Ch | NewLine | Comment | '+' | '-' :)
    let $state :=
      if ($state[$p:l1] eq 7) then                          (: NewLine :)
        let $state := p:lookahead2W(13, $input, $state)     (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment | EOF | '+' | '-' :)
        let $state :=
          if ($state[$p:lk] eq 231) then                    (: NewLine NewLine :)
            let $state := p:lookahead3W(13, $input, $state) (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment | EOF | '+' | '-' :)
            return $state
          else
            $state
        return $state
      else
        ($state[$p:l1], subsequence($state, $p:lk + 1))
    let $state :=
      if ($state[$p:error]) then
        $state
      else if ($state[$p:lk] = 7399) then                   (: NewLine NewLine NewLine :)
        let $state := p:memoized($state, 1)
        return
          if ($state[$p:lk] != 0) then
            $state
          else
            let $backtrack := $state
            let $state := p:strip-result($state)
            let $state :=
              if ($state[$p:error]) then
                $state
              else
                p:try-nl_opt($input, $state)
            let $state :=
              if ($state[$p:error]) then
                $state
              else if ($state[$p:l1] = 13) then             (: '+' :)
                let $state := p:consumeT(13, $input, $state) (: '+' :)
                return $state
              else
                let $state := p:consumeT(14, $input, $state) (: '-' :)
                return $state
            let $state := p:lookahead1W(0, $input, $state)  (: SetLiteral | SetName | Whitespace_Ch | Comment :)
            let $state :=
              if ($state[$p:error]) then
                $state
              else
                p:try-Set_Item($input, $state)
            return
              if (not($state[$p:error])) then
                p:memoize($backtrack, $state, 1, $backtrack[$p:e0], -1, -1)
              else
                p:memoize($backtrack, $state, 1, $backtrack[$p:e0], -2, -2)
      else
        $state
    return
      if ($state[$p:lk] != -1
      and $state[$p:lk] != 13                               (: '+' :)
      and $state[$p:lk] != 14                               (: '-' :)
      and $state[$p:lk] != 423                              (: NewLine '+' :)
      and $state[$p:lk] != 455                              (: NewLine '-' :)
      and $state[$p:lk] != 13543                            (: NewLine NewLine '+' :)
      and $state[$p:lk] != 14567) then                      (: NewLine NewLine '-' :)
        $state
      else
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-nl_opt($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else if ($state[$p:l1] = 13) then                 (: '+' :)
            let $state := p:consume(13, $input, $state)     (: '+' :)
            return $state
          else
            let $state := p:consume(14, $input, $state)     (: '-' :)
            return $state
        let $state := p:lookahead1W(0, $input, $state)      (: SetLiteral | SetName | Whitespace_Ch | Comment :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-Set_Item($input, $state)
        return p:parse-Set_Exp-1($input, $state)
};

(:~
 : Parse Set_Exp.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Set_Exp($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Set_Item($input, $state)
  let $state := p:parse-Set_Exp-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Set_Exp", $count, $begin, $end)
};

(:~
 : Parse Set_Decl.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Set_Decl($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(5, $input, $state)                (: SetName :)
  let $state := p:lookahead1W(2, $input, $state)            (: Whitespace_Ch | NewLine | Comment | '=' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-nl_opt($input, $state)
  let $state := p:consume(16, $input, $state)               (: '=' :)
  let $state := p:lookahead1W(0, $input, $state)            (: SetLiteral | SetName | Whitespace_Ch | Comment :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Set_Exp($input, $state)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-nl($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Set_Decl", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production nl (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-nl-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:consume(7, $input, $state)              (: NewLine :)
    let $state := p:lookahead1W(9, $input, $state)          (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment | EOF :)
    return
      if ($state[$p:l1] != 7) then                          (: NewLine :)
        $state
      else
        p:parse-nl-1($input, $state)
};

(:~
 : Parse nl.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-nl($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-nl-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "nl", $count, $begin, $end)
};

(:~
 : Parse Parameter_Item.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Parameter_Item($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 1) then                        (: ParameterName :)
      let $state := p:consume(1, $input, $state)            (: ParameterName :)
      return $state
    else if ($state[$p:l1] = 3) then                        (: Terminal :)
      let $state := p:consume(3, $input, $state)            (: Terminal :)
      return $state
    else if ($state[$p:l1] = 4) then                        (: SetLiteral :)
      let $state := p:consume(4, $input, $state)            (: SetLiteral :)
      return $state
    else if ($state[$p:l1] = 5) then                        (: SetName :)
      let $state := p:consume(5, $input, $state)            (: SetName :)
      return $state
    else
      let $state := p:consume(2, $input, $state)            (: Nonterminal :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Parameter_Item", $count, $begin, $end)
};

(:~
 : Try parsing Parameter_Item.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Parameter_Item($input as xs:string, $state as item()+) as item()+
{
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 1) then                        (: ParameterName :)
      let $state := p:consumeT(1, $input, $state)           (: ParameterName :)
      return $state
    else if ($state[$p:l1] = 3) then                        (: Terminal :)
      let $state := p:consumeT(3, $input, $state)           (: Terminal :)
      return $state
    else if ($state[$p:l1] = 4) then                        (: SetLiteral :)
      let $state := p:consumeT(4, $input, $state)           (: SetLiteral :)
      return $state
    else if ($state[$p:l1] = 5) then                        (: SetName :)
      let $state := p:consumeT(5, $input, $state)           (: SetName :)
      return $state
    else
      let $state := p:consumeT(2, $input, $state)           (: Nonterminal :)
      return $state
  return $state
};

(:~
 : Parse the 1st loop of production Parameter_Items (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Parameter_Items-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-Parameter_Item($input, $state)
    let $state := p:lookahead1W(10, $input, $state)         (: ParameterName | Nonterminal | Terminal | SetLiteral |
                                                               SetName | Whitespace_Ch | NewLine | Comment | '|' :)
    return
      if ($state[$p:l1] = 7                                 (: NewLine :)
       or $state[$p:l1] = 18) then                          (: '|' :)
        $state
      else
        p:parse-Parameter_Items-1($input, $state)
};

(:~
 : Try parsing the 1st loop of production Parameter_Items (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Parameter_Items-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:try-Parameter_Item($input, $state)
    let $state := p:lookahead1W(10, $input, $state)         (: ParameterName | Nonterminal | Terminal | SetLiteral |
                                                               SetName | Whitespace_Ch | NewLine | Comment | '|' :)
    return
      if ($state[$p:l1] = 7                                 (: NewLine :)
       or $state[$p:l1] = 18) then                          (: '|' :)
        $state
      else
        p:try-Parameter_Items-1($input, $state)
};

(:~
 : Parse Parameter_Items.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Parameter_Items($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-Parameter_Items-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Parameter_Items", $count, $begin, $end)
};

(:~
 : Try parsing Parameter_Items.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-Parameter_Items($input as xs:string, $state as item()+) as item()+
{
  let $state := p:try-Parameter_Items-1($input, $state)
  return $state
};

(:~
 : Parse the 1st loop of production Parameter_Body (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Parameter_Body-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state :=
      if ($state[$p:l1] eq 7) then                          (: NewLine :)
        let $state := p:lookahead2W(11, $input, $state)     (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment | EOF | '|' :)
        let $state :=
          if ($state[$p:lk] eq 231) then                    (: NewLine NewLine :)
            let $state := p:lookahead3W(11, $input, $state) (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment | EOF | '|' :)
            return $state
          else
            $state
        return $state
      else
        ($state[$p:l1], subsequence($state, $p:lk + 1))
    let $state :=
      if ($state[$p:error]) then
        $state
      else if ($state[$p:lk] = 7399) then                   (: NewLine NewLine NewLine :)
        let $state := p:memoized($state, 0)
        return
          if ($state[$p:lk] != 0) then
            $state
          else
            let $backtrack := $state
            let $state := p:strip-result($state)
            let $state :=
              if ($state[$p:error]) then
                $state
              else
                p:try-nl_opt($input, $state)
            let $state := p:consumeT(18, $input, $state)    (: '|' :)
            let $state := p:lookahead1W(7, $input, $state)  (: ParameterName | Nonterminal | Terminal | SetLiteral |
                                                               SetName | Whitespace_Ch | Comment :)
            let $state :=
              if ($state[$p:error]) then
                $state
              else
                p:try-Parameter_Items($input, $state)
            return
              if (not($state[$p:error])) then
                p:memoize($backtrack, $state, 0, $backtrack[$p:e0], -1, -1)
              else
                p:memoize($backtrack, $state, 0, $backtrack[$p:e0], -2, -2)
      else
        $state
    return
      if ($state[$p:lk] != -1
      and $state[$p:lk] != 18                               (: '|' :)
      and $state[$p:lk] != 583                              (: NewLine '|' :)
      and $state[$p:lk] != 18663) then                      (: NewLine NewLine '|' :)
        $state
      else
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-nl_opt($input, $state)
        let $state := p:consume(18, $input, $state)         (: '|' :)
        let $state := p:lookahead1W(7, $input, $state)      (: ParameterName | Nonterminal | Terminal | SetLiteral |
                                                               SetName | Whitespace_Ch | Comment :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-Parameter_Items($input, $state)
        return p:parse-Parameter_Body-1($input, $state)
};

(:~
 : Parse Parameter_Body.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Parameter_Body($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Parameter_Items($input, $state)
  let $state := p:parse-Parameter_Body-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Parameter_Body", $count, $begin, $end)
};

(:~
 : Parse Parameter.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Parameter($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(1, $input, $state)                (: ParameterName :)
  let $state := p:lookahead1W(2, $input, $state)            (: Whitespace_Ch | NewLine | Comment | '=' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-nl_opt($input, $state)
  let $state := p:consume(16, $input, $state)               (: '=' :)
  let $state := p:lookahead1W(7, $input, $state)            (: ParameterName | Nonterminal | Terminal | SetLiteral |
                                                               SetName | Whitespace_Ch | Comment :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Parameter_Body($input, $state)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-nl($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Parameter", $count, $begin, $end)
};

(:~
 : Parse Definition.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Definition($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 1) then                        (: ParameterName :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Parameter($input, $state)
      return $state
    else if ($state[$p:l1] = 5) then                        (: SetName :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Set_Decl($input, $state)
      return $state
    else if ($state[$p:l1] = 3) then                        (: Terminal :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Terminal_Decl($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Rule_Decl($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Definition", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Content (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Content-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-Definition($input, $state)
    return
      if ($state[$p:l1] = 9) then                           (: EOF :)
        $state
      else
        p:parse-Content-1($input, $state)
};

(:~
 : Parse Content.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Content($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-Content-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Content", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production nl_opt (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-nl_opt-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(14, $input, $state)         (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment | '+' | '-' | '::=' |
                                                               '=' | '|' :)
    return
      if ($state[$p:l1] != 7) then                          (: NewLine :)
        $state
      else
        let $state := p:consume(7, $input, $state)          (: NewLine :)
        return p:parse-nl_opt-1($input, $state)
};

(:~
 : Try parsing the 1st loop of production nl_opt (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-nl_opt-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(14, $input, $state)         (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment | '+' | '-' | '::=' |
                                                               '=' | '|' :)
    return
      if ($state[$p:l1] != 7) then                          (: NewLine :)
        $state
      else
        let $state := p:consumeT(7, $input, $state)         (: NewLine :)
        return p:try-nl_opt-1($input, $state)
};

(:~
 : Parse nl_opt.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-nl_opt($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-nl_opt-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "nl_opt", $count, $begin, $end)
};

(:~
 : Try parsing nl_opt.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:try-nl_opt($input as xs:string, $state as item()+) as item()+
{
  let $state := p:try-nl_opt-1($input, $state)
  return $state
};

(:~
 : Parse Grammar.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Grammar($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:lookahead1W(8, $input, $state)            (: ParameterName | Nonterminal | Terminal | SetName |
                                                               Whitespace_Ch | NewLine | Comment :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-nl_opt($input, $state)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Content($input, $state)
  let $state := p:consume(9, $input, $state)                (: EOF :)
  let $end := $state[$p:e0]
  return p:reduce($state, "Grammar", $count, $begin, $end)
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
    subsequence($state, $p:l1, 9),
    0, 0, 0,
    subsequence($state, 13),
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
      if ($state[$p:e1] < $state[$p:memo]/@e) then
        $state[$p:memo]/@*
      else
      (
        attribute b {$state[$p:b1]},
        attribute e {$state[$p:e1]},
        if ($state[$p:l1] lt 0) then
          attribute s {- $state[$p:l1]}
        else
          (attribute o {$state[$p:l1]}, attribute x {$code})
      )
    },
    subsequence($state, $p:error + 1)
  )
};

(:~
 : Consume one token, i.e. compare lookahead token 1 with expected
 : token and in case of a match, shift lookahead tokens down such that
 : l1 becomes the current token, and higher lookahead tokens move down.
 : When lookahead token 1 does not match the expected token, raise an
 : error by saving the expected token code in the error field of the
 : lexer state. In contrast to p:consume, do not create any output.
 :
 : @param $code the expected token.
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:consumeT($code as xs:integer, $input as xs:string, $state as item()+) as item()+
{
  if ($state[$p:error]) then
    $state
  else if ($state[$p:l1] eq $code) then
  (
    subsequence($state, $p:l1, 9),
    0, 0, 0,
    subsequence($state, 13)
  )
  else
  (
    subsequence($state, 1, $p:error - 1),
    element error
    {
      if ($state[$p:e1] < $state[$p:memo]/@e) then
        $state[$p:memo]/@*
      else
      (
        attribute b {$state[$p:b1]},
        attribute e {$state[$p:e1]},
        if ($state[$p:l1] lt 0) then
          attribute s {- $state[$p:l1]}
        else
          (attribute o {$state[$p:l1]}, attribute x {$code})
      )
    },
    subsequence($state, $p:error + 1)
  )
};

(:~
 : Consume whitespace.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:whitespace($input as xs:string,
                              $state as item()+) as item()+
{
  if ($state[$p:e0] = $state[$p:b1]) then
    $state
  else
    let $begin := $state[$p:e0]
    let $end := $state[$p:b1]
    return
    (
      0,
      $state[$p:b0],
      $end,
      subsequence($state, $p:e0 + 1),
      text
      {
        substring($input, $begin, $end - $begin)
      }
    )
};

(:~
 : Use p:match to fetch the next token, but skip any leading
 : whitespace.
 :
 : @param $input the input string.
 : @param $begin the index where to start.
 : @param $token-set the valid token set id.
 : @return a sequence of three values: the token code of the result
 : token, with input string positions of token begin and end.
 :)
declare function p:matchW($input as xs:string,
                          $begin as xs:integer,
                          $token-set as xs:integer)
{
  let $match := p:match($input, $begin, $token-set)
  return
    if ($match[1] = 6                                       (: Whitespace_Ch :)
     or $match[1] = 8) then                                 (: Comment :)
      p:matchW($input, $match[3], $token-set)
    else
      $match
};

(:~
 : Lookahead one token on level 1 with whitespace skipping.
 :
 : @param $set the code of the DFA entry state for the set of valid tokens.
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:lookahead1W($set as xs:integer, $input as xs:string, $state as item()+) as item()+
{
  if ($state[$p:l1] ne 0) then
    $state
  else
    let $match :=
      (
        p:matchW($input, $state[$p:e0], $set),
        0, 0, 0
      )
    return
    (
      $match[1],
      subsequence($state, $p:b0, 2),
      $match,
      subsequence($state, 10)
    )
};

(:~
 : Lookahead one token on level 2 with whitespace skipping.
 :
 : @param $set the code of the DFA entry state for the set of valid tokens.
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:lookahead2W($set as xs:integer, $input as xs:string, $state as item()+) as item()+
{
  let $match :=
    if ($state[$p:l2] ne 0) then
      subsequence($state, $p:l2, 6)
    else
    (
      p:matchW($input, $state[$p:e1], $set),
      0, 0, 0
    )
  return
  (
    $match[1] * 32 + $state[$p:l1],
    subsequence($state, $p:b0, 5),
    $match,
    subsequence($state, 13)
  )
};

(:~
 : Lookahead one token on level 3 with whitespace skipping.
 :
 : @param $set the code of the DFA entry state for the set of valid tokens.
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:lookahead3W($set as xs:integer, $input as xs:string, $state as item()+) as item()+
{
  let $match :=
    if ($state[$p:l3] ne 0) then
      subsequence($state, $p:l3, 3)
    else
      p:matchW($input, $state[$p:e2], $set)
  return
  (
    $match[1] * 1024 + $state[$p:lk],
    subsequence($state, $p:b0, 8),
    $match,
    subsequence($state, 13)
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
 : Strip result from lexer state, in order to avoid carrying it while
 : backtracking.
 :
 : @param $state the lexer state after an alternative failed.
 : @return the updated state.
 :)
declare function p:strip-result($state as item()+) as item()+
{
  subsequence($state, 1, $p:memo)
};

(:~
 : Memoize the backtracking result that was computed at decision point
 : $dpi for input position $e0. Reconstruct state from the parameters.
 :
 : @param $state the lexer state to be restored.
 : @param $update the lexer state containing updates.
 : @param $dpi the decision point id.
 : @param $e0 the input position.
 : @param $v the id of the successful alternative.
 : @param $lk the new lookahead code.
 : @return the reconstructed state.
 :)
declare function p:memoize($state as item()+,
                           $update as item()+,
                           $dpi as xs:integer,
                           $e0 as xs:integer,
                           $v as xs:integer,
                           $lk as xs:integer) as item()+
{
  $lk,
  subsequence($state, $p:b0, $p:memo - $p:b0),
  let $memo := $update[$p:memo]
  let $errors := ($memo, $update[$p:error])[.]
  return
    element memo
    {
      $errors[@e = max($errors/xs:integer(@e))][last()]/@*,
      $memo/value,
      <value key='{$e0 * 4 + $dpi}'>{$v}</value>
    },
  subsequence($state, $p:memo + 1)
};

(:~
 : Retrieve memoized backtracking result for decision point $dpi
 : and input position $state[$p:e0] into $state[$p:lk].
 :
 : @param $state lexer state, error indicator, and result stack.
 : @param $dpi the decision point id.
 : @return the updated state.
 :)
declare function p:memoized($state as item()+, $dpi as xs:integer) as item()+
{
  let $value := data($state[$p:memo]/value[@key = $state[$p:e0] * 4 + $dpi])
  return
  (
    if ($value) then $value else 0,
    subsequence($state, $p:lk + 1)
  )
};

(:~
 : Parse start symbol Grammar from given string.
 :
 : @param $s the string to be parsed.
 : @return the result as generated by parser actions.
 :)
declare function p:parse-Grammar($s as xs:string) as item()*
{
  let $state := (0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, false(), <memo/>)
  let $state := p:parse-Grammar($s, $state)
  let $error := $state[$p:error]
  return
    if ($error) then
      element ERROR {$error/@*, p:error-message($s, $error)}
    else
      subsequence($state, $p:result)
};

(: End :)
