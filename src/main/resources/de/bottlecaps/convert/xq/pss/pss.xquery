xquery version "1.0" encoding "UTF-8";

(: This file was generated on Fri Feb 10, 2023 18:44 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net> :)
(: REx command line: -tree -a none -xquery -name de/bottlecaps/convert/xq/pss/pss.xquery ../../../../../../../main/java/de/bottlecaps/convert/pss/pss.ebnf :)

(:~
 : The parser that was generated for the de/bottlecaps/convert/xq/pss/pss.xquery grammar.
 :)
module namespace p="de/bottlecaps/convert/xq/pss/pss.xquery";
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
 : The index of the lexer state for accessing the token code that
 : was expected when an error was found.
 :)
declare variable $p:error as xs:integer := 10;

(:~
 : The index of the lexer state that points to the first entry
 : used for collecting action results.
 :)
declare variable $p:result as xs:integer := 11;

(:~
 : The codepoint to charclass mapping for 7 bit codepoints.
 :)
declare variable $p:MAP0 as xs:integer+ :=
(
  24, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 6, 4, 4, 4,
  7, 8, 9, 10, 11, 4, 12, 12, 4, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 14, 4, 15, 4, 16, 4, 17, 17, 17, 17, 17,
  17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 4, 19, 4, 17, 4, 17, 17, 17,
  17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 20, 21, 22, 4, 4
);

(:~
 : The codepoint to charclass mapping for codepoints below the surrogate block.
 :)
declare variable $p:MAP1 as xs:integer+ :=
(
  108, 124, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 156, 181, 181, 181, 181, 181, 214,
  215, 213, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214,
  214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214,
  214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214,
  214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 247, 261, 277, 391, 315, 293, 315,
  332, 368, 368, 368, 360, 316, 308, 316, 308, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316,
  316, 316, 385, 385, 385, 385, 385, 385, 385, 413, 316, 316, 316, 407, 316, 316, 316, 316, 346, 368, 368, 369, 367,
  368, 368, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 368, 368, 368,
  368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368,
  368, 368, 368, 368, 368, 368, 315, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316,
  316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 368, 24, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2,
  0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 6, 4, 4, 4, 7, 8, 9, 10, 11, 4, 12, 12, 4, 17, 17,
  17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 4, 19, 4, 17, 17, 17, 17, 17, 17, 17, 4, 17, 17, 17, 17, 17, 17, 17, 17, 17,
  17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 20, 21, 22, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
  4, 17, 17, 4, 4, 4, 4, 4, 4, 4, 12, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 12, 12, 12, 12, 12, 12, 12, 12,
  12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 14, 4, 15, 4, 16, 17, 17, 17, 17, 17, 23, 17, 17, 17, 17, 17, 17, 17, 17, 17,
  17, 17, 17, 17, 17, 4, 17
);

(:~
 : The codepoint to charclass mapping for codepoints above the surrogate block.
 :)
declare variable $p:MAP2 as xs:integer+ :=
(
  57344, 63744, 64976, 65008, 65536, 983040, 63743, 64975, 65007, 65533, 983039, 1114111, 4, 17, 4, 17, 17, 4
);

(:~
 : The token-set-id to DFA-initial-state mapping.
 :)
declare variable $p:INITIAL as xs:integer+ :=
(
  1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
);

(:~
 : The DFA transition table.
 :)
declare variable $p:TRANSITION as xs:integer+ :=
(
  204, 204, 204, 204, 204, 204, 204, 204, 200, 200, 200, 209, 226, 204, 204, 204, 200, 200, 200, 203, 204, 204, 204,
  204, 200, 200, 200, 209, 204, 204, 204, 204, 204, 204, 204, 216, 226, 204, 204, 204, 204, 223, 223, 216, 232, 204,
  204, 204, 242, 242, 242, 216, 226, 204, 204, 204, 204, 246, 246, 216, 250, 204, 204, 204, 204, 257, 257, 216, 226,
  204, 204, 204, 265, 264, 261, 216, 226, 204, 204, 204, 204, 204, 272, 216, 226, 204, 204, 204, 204, 204, 287, 216,
  226, 204, 204, 204, 204, 204, 204, 269, 226, 204, 204, 204, 212, 204, 211, 276, 226, 204, 204, 204, 228, 228, 280,
  216, 226, 204, 204, 204, 219, 204, 218, 216, 284, 204, 204, 204, 204, 204, 291, 216, 226, 204, 204, 204, 297, 295,
  295, 269, 226, 204, 204, 204, 204, 301, 301, 216, 226, 204, 204, 204, 234, 236, 305, 216, 226, 204, 204, 204, 204,
  309, 309, 216, 226, 204, 204, 204, 238, 313, 313, 216, 226, 204, 204, 204, 252, 253, 317, 216, 226, 204, 204, 204,
  327, 321, 325, 269, 226, 204, 204, 204, 331, 205, 334, 204, 204, 204, 204, 204, 77, 77, 77, 77, 0, 0, 0, 0, 160, 77,
  78, 0, 0, 0, 336, 0, 0, 78, 0, 0, 0, 416, 0, 17, 17, 17, 17, 18, 0, 0, 0, 384, 128, 18, 0, 0, 0, 512, 0, 0, 0, 576,
  78, 78, 78, 78, 18, 18, 18, 18, 17, 128, 0, 0, 0, 608, 0, 192, 192, 192, 192, 224, 224, 224, 224, 0, 0, 0, 224, 0, 78,
  111, 0, 0, 256, 256, 0, 78, 0, 19, 384, 384, 384, 384, 17, 18, 352, 0, 0, 288, 288, 0, 0, 448, 448, 111, 111, 111,
  111, 0, 111, 480, 480, 480, 480, 512, 512, 512, 512, 544, 544, 544, 544, 576, 576, 576, 576, 608, 608, 608, 608, 655,
  655, 655, 655, 15, 655, 15, 15, 0, 15, 0, 160, 0, 160, 160, 160, 160
);

(:~
 : The DFA-state to expected-token-set mapping.
 :)
declare variable $p:EXPECTED as xs:integer+ :=
(
  6, 22, 5634, 428118, 737390, 770094, 999470, 739390, 510078, 1034366, 518654, 524286, 2, 2, 4, 1024, 8, 8, 1024
);

(:~
 : The token-string table.
 :)
declare variable $p:TOKEN as xs:string+ :=
(
  "(0)",
  "Whitespace",
  "Nonterminal",
  "Terminal",
  "EOF",
  "'('",
  "')'",
  "'*'",
  "'+'",
  "':'",
  "'::='",
  "';'",
  "'='",
  "'?'",
  "'['",
  "']'",
  "'{'",
  "'|'",
  "'}'",
  "'ε'"
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
                      $result mod 32,
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
    let $result := $result idiv 32
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
        let $c2 := $c1 idiv 32
        return $p:MAP1[1 + $c0 mod 16 + $p:MAP1[1 + $c1 mod 32 + $p:MAP1[1 + $c2]]]
      else
        p:map2($c0, 1, 6)
    let $current := $current + 1
    let $i0 := 32 * $c1 + $current-state - 1
    let $i1 := $i0 idiv 4
    let $next-state := $p:TRANSITION[$i0 mod 4 + $p:TRANSITION[$i1 + 1] + 1]
    return
      if ($next-state > 31) then
        p:transition($input, $begin, $current, $current, $next-state, $next-state mod 32, $current-state)
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
    let $i0 := $t * 19 + $state - 1
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
      else if ($p:MAP2[6 + $m] < $c) then
        p:map2($c, $m + 1, $hi)
      else
        $p:MAP2[12 + $m]
};

(:~
 : Parse Item.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Item($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 2) then                        (: Nonterminal :)
      let $state := p:consume(2, $input, $state)            (: Nonterminal :)
      return $state
    else if ($state[$p:l1] = 3) then                        (: Terminal :)
      let $state := p:consume(3, $input, $state)            (: Terminal :)
      return $state
    else if ($state[$p:l1] = 14) then                       (: '[' :)
      let $state := p:consume(14, $input, $state)           (: '[' :)
      let $state := p:lookahead1W(5, $input, $state)        (: Whitespace | Nonterminal | Terminal | '(' | '[' | ']' |
                                                               '{' | '|' | 'ε' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Alternatives($input, $state)
      let $state := p:consume(15, $input, $state)           (: ']' :)
      return $state
    else if ($state[$p:l1] = 16) then                       (: '{' :)
      let $state := p:consume(16, $input, $state)           (: '{' :)
      let $state := p:lookahead1W(6, $input, $state)        (: Whitespace | Nonterminal | Terminal | '(' | '[' | '{' |
                                                               '|' | '}' | 'ε' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Alternatives($input, $state)
      let $state := p:consume(18, $input, $state)           (: '}' :)
      return $state
    else
      let $state := p:consume(5, $input, $state)            (: '(' :)
      let $state := p:lookahead1W(4, $input, $state)        (: Whitespace | Nonterminal | Terminal | '(' | ')' | '[' |
                                                               '{' | '|' | 'ε' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Alternatives($input, $state)
      let $state := p:consume(6, $input, $state)            (: ')' :)
      return $state
  let $state := p:lookahead1W(10, $input, $state)           (: Whitespace | Nonterminal | Terminal | EOF | '(' | ')' |
                                                               '*' | '+' | ';' | '?' | '[' | ']' | '{' | '|' | '}' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 7                              (: '*' :)
          or $state[$p:l1] = 8                              (: '+' :)
          or $state[$p:l1] = 13) then                       (: '?' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 7) then                    (: '*' :)
          let $state := p:consume(7, $input, $state)        (: '*' :)
          return $state
        else if ($state[$p:l1] = 8) then                    (: '+' :)
          let $state := p:consume(8, $input, $state)        (: '+' :)
          return $state
        else
          let $state := p:consume(13, $input, $state)       (: '?' :)
          return $state
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Item", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Alternative (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Alternative-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-Item($input, $state)
    let $state := p:lookahead1W(8, $input, $state)          (: Whitespace | Nonterminal | Terminal | EOF | '(' | ')' |
                                                               ';' | '[' | ']' | '{' | '|' | '}' :)
    let $state :=
      if ($state[$p:l1] eq 2) then                          (: Nonterminal :)
        let $state := p:lookahead2W(11, $input, $state)     (: Whitespace | Nonterminal | Terminal | EOF | '(' | ')' |
                                                               '*' | '+' | ':' | '::=' | ';' | '=' | '?' | '[' | ']' |
                                                               '{' | '|' | '}' :)
        return $state
      else
        ($state[$p:l1], subsequence($state, $p:lk + 1))
    return
      if ($state[$p:lk] = 4                                 (: EOF :)
       or $state[$p:lk] = 6                                 (: ')' :)
       or $state[$p:lk] = 11                                (: ';' :)
       or $state[$p:lk] = 15                                (: ']' :)
       or $state[$p:lk] = 17                                (: '|' :)
       or $state[$p:lk] = 18                                (: '}' :)
       or $state[$p:lk] = 290                               (: Nonterminal ':' :)
       or $state[$p:lk] = 322                               (: Nonterminal '::=' :)
       or $state[$p:lk] = 386) then                         (: Nonterminal '=' :)
        $state
      else
        p:parse-Alternative-1($input, $state)
};

(:~
 : Parse Alternative.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Alternative($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:l1] eq 2) then                            (: Nonterminal :)
      let $state := p:lookahead2W(11, $input, $state)       (: Whitespace | Nonterminal | Terminal | EOF | '(' | ')' |
                                                               '*' | '+' | ':' | '::=' | ';' | '=' | '?' | '[' | ']' |
                                                               '{' | '|' | '}' :)
      return $state
    else
      ($state[$p:l1], subsequence($state, $p:lk + 1))
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:lk] = 4                              (: EOF :)
          or $state[$p:lk] = 6                              (: ')' :)
          or $state[$p:lk] = 11                             (: ';' :)
          or $state[$p:lk] = 15                             (: ']' :)
          or $state[$p:lk] = 17                             (: '|' :)
          or $state[$p:lk] = 18                             (: '}' :)
          or $state[$p:lk] = 19                             (: 'ε' :)
          or $state[$p:lk] = 290                            (: Nonterminal ':' :)
          or $state[$p:lk] = 322                            (: Nonterminal '::=' :)
          or $state[$p:lk] = 386) then                      (: Nonterminal '=' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 19) then                   (: 'ε' :)
          let $state := p:consume(19, $input, $state)       (: 'ε' :)
          return $state
        else
          $state
      return $state
    else
      let $state := p:parse-Alternative-1($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Alternative", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Alternatives (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Alternatives-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(3, $input, $state)          (: Whitespace | Nonterminal | EOF | ')' | ';' | ']' | '|' |
                                                               '}' :)
    return
      if ($state[$p:l1] != 17) then                         (: '|' :)
        $state
      else
        let $state := p:consume(17, $input, $state)         (: '|' :)
        let $state := p:lookahead1W(9, $input, $state)      (: Whitespace | Nonterminal | Terminal | EOF | '(' | ')' |
                                                               ';' | '[' | ']' | '{' | '|' | '}' | 'ε' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-Alternative($input, $state)
        return p:parse-Alternatives-1($input, $state)
};

(:~
 : Parse Alternatives.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Alternatives($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Alternative($input, $state)
  let $state := p:parse-Alternatives-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Alternatives", $count, $begin, $end)
};

(:~
 : Parse Rule.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Rule($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(2, $input, $state)                (: Nonterminal :)
  let $state := p:lookahead1W(2, $input, $state)            (: Whitespace | ':' | '::=' | '=' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 12) then                       (: '=' :)
      let $state := p:consume(12, $input, $state)           (: '=' :)
      return $state
    else if ($state[$p:l1] = 9) then                        (: ':' :)
      let $state := p:consume(9, $input, $state)            (: ':' :)
      return $state
    else
      let $state := p:consume(10, $input, $state)           (: '::=' :)
      return $state
  let $state := p:lookahead1W(7, $input, $state)            (: Whitespace | Nonterminal | Terminal | EOF | '(' | ';' |
                                                               '[' | '{' | '|' | 'ε' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Alternatives($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 11) then                       (: ';' :)
      let $state := p:consume(11, $input, $state)           (: ';' :)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Rule", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production PSS-Grammar (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-PSS-Grammar-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(0, $input, $state)          (: Whitespace | Nonterminal :)
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-Rule($input, $state)
    let $state := p:lookahead1W(1, $input, $state)          (: Whitespace | Nonterminal | EOF :)
    return
      if ($state[$p:l1] != 2) then                          (: Nonterminal :)
        $state
      else
        p:parse-PSS-Grammar-1($input, $state)
};

(:~
 : Parse PSS-Grammar.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-PSS-Grammar($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-PSS-Grammar-1($input, $state)
  let $state := p:consume(4, $input, $state)                (: EOF :)
  let $end := $state[$p:e0]
  return p:reduce($state, "PSS-Grammar", $count, $begin, $end)
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
    subsequence($state, $p:l1, 6),
    0, 0, 0,
    subsequence($state, 10),
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
    if ($match[1] = 1) then                                 (: Whitespace :)
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
      subsequence($state, $p:l2, 3)
    else
      p:matchW($input, $state[$p:e1], $set)
  return
  (
    $match[1] * 32 + $state[$p:l1],
    subsequence($state, $p:b0, 5),
    $match,
    subsequence($state, 10)
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
 : Parse start symbol PSS-Grammar from given string.
 :
 : @param $s the string to be parsed.
 : @return the result as generated by parser actions.
 :)
declare function p:parse-PSS-Grammar($s as xs:string) as item()*
{
  let $state := (0, 1, 1, 0, 0, 0, 0, 0, 0, false())
  let $state := p:parse-PSS-Grammar($s, $state)
  let $error := $state[$p:error]
  return
    if ($error) then
      element ERROR {$error/@*, p:error-message($s, $error)}
    else
      subsequence($state, $p:result)
};

(: End :)
