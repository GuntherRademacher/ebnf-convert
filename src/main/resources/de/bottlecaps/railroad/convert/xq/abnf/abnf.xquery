xquery version "1.0" encoding "UTF-8";

(: This file was generated on Wed Dec 21, 2022 23:45 (UTC+01) by REx v5.56 which is Copyright (c) 1979-2022 by Gunther Rademacher <grd@gmx.net> :)
(: REx command line: -tree -a none -xquery -name de/bottlecaps/railroad/convert/xq/abnf/abnf.xquery ..\..\..\..\..\..\..\..\main\java\de\bottlecaps\railroad\convert\abnf\abnf.ebnf :)

(:~
 : The parser that was generated for the de/bottlecaps/railroad/convert/xq/abnf/abnf.xquery grammar.
 :)
module namespace p="de/bottlecaps/railroad/convert/xq/abnf/abnf.xquery";
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
  30, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 6, 5, 5, 7, 5,
  5, 8, 9, 10, 5, 5, 11, 12, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 16, 17, 18, 19, 20, 5, 5, 21, 21, 21, 21, 21,
  21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 5, 24, 5, 25, 5, 21, 26, 21,
  27, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 28, 22, 22, 5, 29, 5, 5, 0
);

(:~
 : The codepoint to charclass mapping for codepoints below the surrogate block.
 :)
declare variable $p:MAP1 as xs:integer+ :=
(
  54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
  58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 90, 136, 167, 199,
  104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104,
  104, 104, 104, 104, 104, 104, 104, 104, 104, 30, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 6, 5, 5, 7, 5, 5, 8, 9, 10, 5, 5, 11, 12, 13,
  14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 16, 17, 18, 19, 20, 5, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22,
  22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 5, 24, 5, 25, 5, 21, 26, 21, 27, 21, 21, 22, 22, 22, 22, 22, 22,
  22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 28, 22, 22, 5, 29, 5, 5, 0
);

(:~
 : The token-set-id to DFA-initial-state mapping.
 :)
declare variable $p:INITIAL as xs:integer+ :=
(
  1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
);

(:~
 : The DFA transition table.
 :)
declare variable $p:TRANSITION as xs:integer+ :=
(
  258, 258, 258, 258, 258, 258, 258, 258, 258, 248, 423, 425, 250, 258, 256, 258, 258, 264, 442, 280, 443, 258, 283,
  258, 258, 289, 306, 308, 360, 258, 312, 258, 258, 248, 423, 425, 250, 260, 256, 258, 258, 258, 258, 258, 375, 260,
  256, 258, 258, 259, 318, 319, 375, 323, 256, 258, 258, 343, 331, 332, 375, 260, 256, 258, 258, 349, 336, 337, 375,
  260, 256, 258, 258, 258, 267, 270, 375, 260, 256, 258, 273, 258, 275, 276, 375, 260, 256, 258, 258, 258, 258, 341,
  402, 260, 256, 258, 258, 258, 258, 347, 375, 260, 256, 258, 258, 258, 292, 295, 375, 353, 256, 258, 357, 258, 384,
  386, 402, 260, 256, 258, 388, 258, 384, 386, 402, 260, 256, 258, 258, 399, 258, 399, 375, 392, 256, 258, 258, 252,
  378, 380, 375, 260, 256, 258, 258, 285, 326, 327, 375, 260, 256, 258, 258, 396, 258, 396, 375, 260, 413, 258, 258,
  258, 258, 258, 375, 416, 256, 258, 420, 405, 408, 409, 402, 260, 256, 258, 258, 405, 408, 409, 402, 260, 256, 258,
  258, 298, 301, 302, 375, 260, 256, 258, 258, 258, 363, 366, 375, 260, 256, 258, 258, 258, 258, 258, 402, 260, 256,
  258, 420, 429, 408, 409, 402, 260, 256, 258, 420, 432, 408, 409, 402, 260, 256, 258, 258, 435, 408, 409, 402, 260,
  256, 258, 258, 258, 369, 372, 375, 260, 256, 258, 258, 258, 314, 439, 258, 258, 258, 258, 80, 0, 80, 0, 19, 0, 19, 0,
  0, 26, 0, 0, 0, 0, 23, 24, 81, 0, 81, 0, 0, 0, 384, 384, 384, 0, 0, 0, 416, 416, 416, 0, 81, 81, 81, 17, 17, 0, 0, 0,
  24, 82, 0, 82, 0, 0, 0, 512, 512, 512, 0, 0, 0, 640, 640, 640, 640, 0, 0, 0, 82, 82, 82, 25, 0, 25, 0, 0, 0, 288, 23,
  23, 23, 23, 0, 0, 0, 160, 24, 24, 24, 24, 0, 320, 320, 320, 320, 0, 352, 352, 352, 352, 0, 0, 448, 0, 0, 0, 320, 480,
  480, 0, 0, 0, 352, 0, 608, 23, 24, 129, 194, 227, 0, 0, 18, 0, 0, 0, 672, 672, 672, 0, 0, 0, 800, 800, 800, 0, 0, 19,
  0, 0, 19, 19, 19, 26, 129, 0, 129, 129, 129, 0, 227, 0, 27, 0, 23, 24, 0, 0, 598, 0, 0, 21, 0, 0, 19, 116, 0, 0, 116,
  116, 116, 116, 0, 0, 26, 544, 0, 0, 23, 256, 0, 0, 227, 0, 0, 80, 80, 80, 80, 116, 704, 0, 116, 736, 0, 116, 768, 0,
  116, 288, 288, 288, 0, 0, 81, 81, 0
);

(:~
 : The DFA-state to expected-token-set mapping.
 :)
declare variable $p:EXPECTED as xs:integer+ :=
(
  8, 32, 64, 4096, 6, 14680064, 458754, 525972, 525980, 530068, 530078, 18390942, 18407326, 18415518, 18849694, 2, 2, 2,
  2, 4, 65536, 262144, 16, 128, 2, 2, 65536
);

(:~
 : The token-string table.
 :)
declare variable $p:TOKEN as xs:string+ :=
(
  "(0)",
  "whitespace",
  "rulename",
  "integer",
  "char-val",
  "bits",
  "hexdigs",
  "prose-val",
  "EOF",
  "'%'",
  "'('",
  "')'",
  "'*'",
  "'-'",
  "'.'",
  "'/'",
  "'::='",
  "'='",
  "'=/'",
  "'['",
  "']'",
  "'b'",
  "'d'",
  "'x'",
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
        let $c1 := $c0 idiv 32
        let $c2 := $c1 idiv 32
        return $p:MAP1[1 + $c0 mod 32 + $p:MAP1[1 + $c1 mod 32 + $p:MAP1[1 + $c2]]]
      else
        0
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
    let $i0 := $t * 27 + $state - 1
    return p:token((), $p:EXPECTED[$i0 + 1], $t * 32 + 1)
  else
    ()
};

(:~
 : Parse the 1st loop of production hex-val (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-hex-val-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:consume(14, $input, $state)             (: '.' :)
    let $state := p:lookahead1(2, $input, $state)           (: hexdigs :)
    let $state := p:consume(6, $input, $state)              (: hexdigs :)
    let $state := p:lookahead1W(12, $input, $state)         (: whitespace | rulename | integer | char-val | prose-val |
                                                               EOF | '%' | '(' | ')' | '*' | '.' | '/' | '[' | ']' | '|' :)
    return
      if ($state[$p:l1] != 14) then                         (: '.' :)
        $state
      else
        p:parse-hex-val-1($input, $state)
};

(:~
 : Parse hex-val.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-hex-val($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(23, $input, $state)               (: 'x' :)
  let $state := p:lookahead1(2, $input, $state)             (: hexdigs :)
  let $state := p:consume(6, $input, $state)                (: hexdigs :)
  let $state := p:lookahead1W(13, $input, $state)           (: whitespace | rulename | integer | char-val | prose-val |
                                                               EOF | '%' | '(' | ')' | '*' | '-' | '.' | '/' | '[' |
                                                               ']' | '|' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 13                             (: '-' :)
          or $state[$p:l1] = 14) then                       (: '.' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 14) then                   (: '.' :)
          let $state := p:parse-hex-val-1($input, $state)
          return $state
        else
          let $state := p:consume(13, $input, $state)       (: '-' :)
          let $state := p:lookahead1(2, $input, $state)     (: hexdigs :)
          let $state := p:consume(6, $input, $state)        (: hexdigs :)
          return $state
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "hex-val", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production dec-val (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-dec-val-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:consume(14, $input, $state)             (: '.' :)
    let $state := p:lookahead1(0, $input, $state)           (: integer :)
    let $state := p:consume(3, $input, $state)              (: integer :)
    let $state := p:lookahead1W(12, $input, $state)         (: whitespace | rulename | integer | char-val | prose-val |
                                                               EOF | '%' | '(' | ')' | '*' | '.' | '/' | '[' | ']' | '|' :)
    return
      if ($state[$p:l1] != 14) then                         (: '.' :)
        $state
      else
        p:parse-dec-val-1($input, $state)
};

(:~
 : Parse dec-val.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-dec-val($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(22, $input, $state)               (: 'd' :)
  let $state := p:lookahead1(0, $input, $state)             (: integer :)
  let $state := p:consume(3, $input, $state)                (: integer :)
  let $state := p:lookahead1W(13, $input, $state)           (: whitespace | rulename | integer | char-val | prose-val |
                                                               EOF | '%' | '(' | ')' | '*' | '-' | '.' | '/' | '[' |
                                                               ']' | '|' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 13                             (: '-' :)
          or $state[$p:l1] = 14) then                       (: '.' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 14) then                   (: '.' :)
          let $state := p:parse-dec-val-1($input, $state)
          return $state
        else
          let $state := p:consume(13, $input, $state)       (: '-' :)
          let $state := p:lookahead1(0, $input, $state)     (: integer :)
          let $state := p:consume(3, $input, $state)        (: integer :)
          return $state
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "dec-val", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production bin-val (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-bin-val-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:consume(14, $input, $state)             (: '.' :)
    let $state := p:lookahead1(1, $input, $state)           (: bits :)
    let $state := p:consume(5, $input, $state)              (: bits :)
    let $state := p:lookahead1W(12, $input, $state)         (: whitespace | rulename | integer | char-val | prose-val |
                                                               EOF | '%' | '(' | ')' | '*' | '.' | '/' | '[' | ']' | '|' :)
    return
      if ($state[$p:l1] != 14) then                         (: '.' :)
        $state
      else
        p:parse-bin-val-1($input, $state)
};

(:~
 : Parse bin-val.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-bin-val($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(21, $input, $state)               (: 'b' :)
  let $state := p:lookahead1(1, $input, $state)             (: bits :)
  let $state := p:consume(5, $input, $state)                (: bits :)
  let $state := p:lookahead1W(13, $input, $state)           (: whitespace | rulename | integer | char-val | prose-val |
                                                               EOF | '%' | '(' | ')' | '*' | '-' | '.' | '/' | '[' |
                                                               ']' | '|' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 13                             (: '-' :)
          or $state[$p:l1] = 14) then                       (: '.' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 14) then                   (: '.' :)
          let $state := p:parse-bin-val-1($input, $state)
          return $state
        else
          let $state := p:consume(13, $input, $state)       (: '-' :)
          let $state := p:lookahead1(1, $input, $state)     (: bits :)
          let $state := p:consume(5, $input, $state)        (: bits :)
          return $state
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "bin-val", $count, $begin, $end)
};

(:~
 : Parse num-val.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-num-val($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(9, $input, $state)                (: '%' :)
  let $state := p:lookahead1(5, $input, $state)             (: 'b' | 'd' | 'x' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 21) then                       (: 'b' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-bin-val($input, $state)
      return $state
    else if ($state[$p:l1] = 22) then                       (: 'd' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-dec-val($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-hex-val($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "num-val", $count, $begin, $end)
};

(:~
 : Parse option.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-option($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(19, $input, $state)               (: '[' :)
  let $state := p:lookahead1W(10, $input, $state)           (: whitespace | rulename | integer | char-val | prose-val |
                                                               '%' | '(' | '*' | '[' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-alternation($input, $state)
  let $state := p:consume(20, $input, $state)               (: ']' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "option", $count, $begin, $end)
};

(:~
 : Parse group.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-group($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(10, $input, $state)               (: '(' :)
  let $state := p:lookahead1W(10, $input, $state)           (: whitespace | rulename | integer | char-val | prose-val |
                                                               '%' | '(' | '*' | '[' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-alternation($input, $state)
  let $state := p:consume(11, $input, $state)               (: ')' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "group", $count, $begin, $end)
};

(:~
 : Parse element.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-element($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:lookahead1(7, $input, $state)             (: rulename | char-val | prose-val | '%' | '(' | '[' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 2) then                        (: rulename :)
      let $state := p:consume(2, $input, $state)            (: rulename :)
      return $state
    else if ($state[$p:l1] = 10) then                       (: '(' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-group($input, $state)
      return $state
    else if ($state[$p:l1] = 19) then                       (: '[' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-option($input, $state)
      return $state
    else if ($state[$p:l1] = 4) then                        (: char-val :)
      let $state := p:consume(4, $input, $state)            (: char-val :)
      return $state
    else if ($state[$p:l1] = 9) then                        (: '%' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-num-val($input, $state)
      return $state
    else
      let $state := p:consume(7, $input, $state)            (: prose-val :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "element", $count, $begin, $end)
};

(:~
 : Parse repeat.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-repeat($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:l1] eq 3) then                            (: integer :)
      let $state := p:lookahead2(9, $input, $state)         (: rulename | char-val | prose-val | '%' | '(' | '*' | '[' :)
      return $state
    else
      ($state[$p:l1], subsequence($state, $p:lk + 1))
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:lk] = 12                             (: '*' :)
          or $state[$p:lk] = 387) then                      (: integer '*' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 3) then                    (: integer :)
          let $state := p:consume(3, $input, $state)        (: integer :)
          return $state
        else
          $state
      let $state := p:lookahead1(3, $input, $state)         (: '*' :)
      let $state := p:consume(12, $input, $state)           (: '*' :)
      let $state := p:lookahead1(8, $input, $state)         (: rulename | integer | char-val | prose-val | '%' | '(' |
                                                               '[' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 3) then                    (: integer :)
          let $state := p:consume(3, $input, $state)        (: integer :)
          return $state
        else
          $state
      return $state
    else
      let $state := p:consume(3, $input, $state)            (: integer :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "repeat", $count, $begin, $end)
};

(:~
 : Parse repetition.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-repetition($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 3                              (: integer :)
          or $state[$p:l1] = 12) then                       (: '*' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-repeat($input, $state)
      return $state
    else
      $state
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-element($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "repetition", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production concatenation (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-concatenation-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-repetition($input, $state)
    let $state := p:lookahead1W(11, $input, $state)         (: whitespace | rulename | integer | char-val | prose-val |
                                                               EOF | '%' | '(' | ')' | '*' | '/' | '[' | ']' | '|' :)
    let $state :=
      if ($state[$p:l1] eq 2) then                          (: rulename :)
        let $state := p:lookahead2W(14, $input, $state)     (: whitespace | rulename | integer | char-val | prose-val |
                                                               EOF | '%' | '(' | ')' | '*' | '/' | '::=' | '=' | '=/' |
                                                               '[' | ']' | '|' :)
        return $state
      else
        ($state[$p:l1], subsequence($state, $p:lk + 1))
    return
      if ($state[$p:lk] = 8                                 (: EOF :)
       or $state[$p:lk] = 11                                (: ')' :)
       or $state[$p:lk] = 15                                (: '/' :)
       or $state[$p:lk] = 20                                (: ']' :)
       or $state[$p:lk] = 24                                (: '|' :)
       or $state[$p:lk] = 514                               (: rulename '::=' :)
       or $state[$p:lk] = 546                               (: rulename '=' :)
       or $state[$p:lk] = 578) then                         (: rulename '=/' :)
        $state
      else
        p:parse-concatenation-1($input, $state)
};

(:~
 : Parse concatenation.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-concatenation($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-concatenation-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "concatenation", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production alternation (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-alternation-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 15                                 (: '/' :)
    and $state[$p:l1] != 24) then                           (: '|' :)
      $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 15) then                   (: '/' :)
          let $state := p:consume(15, $input, $state)       (: '/' :)
          return $state
        else
          let $state := p:consume(24, $input, $state)       (: '|' :)
          return $state
      let $state := p:lookahead1W(10, $input, $state)       (: whitespace | rulename | integer | char-val | prose-val |
                                                               '%' | '(' | '*' | '[' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-concatenation($input, $state)
      return p:parse-alternation-1($input, $state)
};

(:~
 : Parse alternation.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-alternation($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-concatenation($input, $state)
  let $state := p:parse-alternation-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "alternation", $count, $begin, $end)
};

(:~
 : Parse elements.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-elements($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-alternation($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "elements", $count, $begin, $end)
};

(:~
 : Parse defined-as.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-defined-as($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 17) then                       (: '=' :)
      let $state := p:consume(17, $input, $state)           (: '=' :)
      return $state
    else if ($state[$p:l1] = 18) then                       (: '=/' :)
      let $state := p:consume(18, $input, $state)           (: '=/' :)
      return $state
    else
      let $state := p:consume(16, $input, $state)           (: '::=' :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "defined-as", $count, $begin, $end)
};

(:~
 : Parse rule.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-rule($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(2, $input, $state)                (: rulename :)
  let $state := p:lookahead1W(6, $input, $state)            (: whitespace | '::=' | '=' | '=/' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-defined-as($input, $state)
  let $state := p:lookahead1W(10, $input, $state)           (: whitespace | rulename | integer | char-val | prose-val |
                                                               '%' | '(' | '*' | '[' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-elements($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "rule", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production rulelist (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-rulelist-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(4, $input, $state)          (: whitespace | rulename :)
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-rule($input, $state)
    return
      if ($state[$p:l1] != 2) then                          (: rulename :)
        $state
      else
        p:parse-rulelist-1($input, $state)
};

(:~
 : Parse rulelist.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-rulelist($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-rulelist-1($input, $state)
  let $state := p:consume(8, $input, $state)                (: EOF :)
  let $end := $state[$p:e0]
  return p:reduce($state, "rulelist", $count, $begin, $end)
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
    if ($match[1] = 1) then                                 (: whitespace :)
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
      (
        p:match($input, $state[$p:e0], $set),
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
 : Lookahead one token on level 2.
 :
 : @param $set the code of the DFA entry state for the set of valid tokens.
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:lookahead2($set as xs:integer, $input as xs:string, $state as item()+) as item()+
{
  let $match :=
    if ($state[$p:l2] ne 0) then
      subsequence($state, $p:l2, 3)
    else
      p:match($input, $state[$p:e1], $set)
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
 : Parse start symbol rulelist from given string.
 :
 : @param $s the string to be parsed.
 : @return the result as generated by parser actions.
 :)
declare function p:parse-rulelist($s as xs:string) as item()*
{
  let $state := (0, 1, 1, 0, 0, 0, 0, 0, 0, false())
  let $state := p:parse-rulelist($s, $state)
  let $error := $state[$p:error]
  return
    if ($error) then
      element ERROR {$error/@*, p:error-message($s, $error)}
    else
      subsequence($state, $p:result)
};

(: End :)