<?xml version="1.0" encoding="UTF-8"?>
<!-- This file was generated on Thu Jan 30, 2025 13:23 (UTC+01) by REx v6.1 which is Copyright (c) 1979-2025 by Gunther Rademacher <grd@gmx.net> -->
<!-- REx command line: -q -backtrack -tree -a none -xslt -name de/bottlecaps/convert/xq/gold/gold.xslt ../../../../../../../main/java/de/bottlecaps/convert/gold/gold.ebnf -->

<xsl:stylesheet version="2.0"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="de/bottlecaps/convert/xq/gold/gold.xslt">
  <!--~
   ! The index of the lexer state for accessing the combined
   ! (i.e. level > 1) lookahead code.
  -->
  <xsl:variable name="p:lk" as="xs:integer" select="1"/>

  <!--~
   ! The index of the lexer state for accessing the position in the
   ! input string of the begin of the token that has been consumed.
  -->
  <xsl:variable name="p:b0" as="xs:integer" select="2"/>

  <!--~
   ! The index of the lexer state for accessing the position in the
   ! input string of the end of the token that has been consumed.
  -->
  <xsl:variable name="p:e0" as="xs:integer" select="3"/>

  <!--~
   ! The index of the lexer state for accessing the code of the
   ! level-1-lookahead token.
  -->
  <xsl:variable name="p:l1" as="xs:integer" select="4"/>

  <!--~
   ! The index of the lexer state for accessing the position in the
   ! input string of the begin of the level-1-lookahead token.
  -->
  <xsl:variable name="p:b1" as="xs:integer" select="5"/>

  <!--~
   ! The index of the lexer state for accessing the position in the
   ! input string of the end of the level-1-lookahead token.
  -->
  <xsl:variable name="p:e1" as="xs:integer" select="6"/>

  <!--~
   ! The index of the lexer state for accessing the code of the
   ! level-2-lookahead token.
  -->
  <xsl:variable name="p:l2" as="xs:integer" select="7"/>

  <!--~
   ! The index of the lexer state for accessing the position in the
   ! input string of the begin of the level-2-lookahead token.
  -->
  <xsl:variable name="p:b2" as="xs:integer" select="8"/>

  <!--~
   ! The index of the lexer state for accessing the position in the
   ! input string of the end of the level-2-lookahead token.
  -->
  <xsl:variable name="p:e2" as="xs:integer" select="9"/>

  <!--~
   ! The index of the lexer state for accessing the code of the
   ! level-3-lookahead token.
  -->
  <xsl:variable name="p:l3" as="xs:integer" select="10"/>

  <!--~
   ! The index of the lexer state for accessing the position in the
   ! input string of the begin of the level-3-lookahead token.
  -->
  <xsl:variable name="p:b3" as="xs:integer" select="11"/>

  <!--~
   ! The index of the lexer state for accessing the position in the
   ! input string of the end of the level-3-lookahead token.
  -->
  <xsl:variable name="p:e3" as="xs:integer" select="12"/>

  <!--~
   ! The index of the lexer state for accessing the token code that
   ! was expected when an error was found.
  -->
  <xsl:variable name="p:error" as="xs:integer" select="13"/>

  <!--~
   ! The index of the lexer state for accessing the memoization
   ! of backtracking results.
  -->
  <xsl:variable name="p:memo" as="xs:integer" select="14"/>

  <!--~
   ! The index of the lexer state that points to the first entry
   ! used for collecting action results.
  -->
  <xsl:variable name="p:result" as="xs:integer" select="15"/>

  <!--~
   ! The codepoint to charclass mapping for 7 bit codepoints.
  -->
  <xsl:variable name="p:MAP0" as="xs:integer+" select="
    28, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 7, 8, 8, 8, 8, 9, 10, 11, 12, 13, 8, 14, 15, 8, 15,
    15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 8, 17, 18, 19, 20, 8, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
    15, 15, 15, 21, 8, 22, 8, 15, 8, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 23, 24, 25, 8, 8
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints below the surrogate block.
  -->
  <xsl:variable name="p:MAP1" as="xs:integer+" select="
    54, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65,
    65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 129, 143, 159, 195, 174, 179, 174, 211, 228, 228, 227, 228, 228, 228, 228, 228, 228, 228, 228,
    228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228,
    228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 28, 0, 0, 0, 0, 0, 0, 0, 0, 1,
    2, 3, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 7, 8, 8, 8, 8, 9, 10, 11, 12, 13, 8, 14, 15, 8, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
    15, 15, 15, 15, 15, 21, 8, 22, 8, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 8, 17, 18, 19, 20, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 23, 24, 25,
    8, 8, 27, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints above the surrogate block.
  -->
  <xsl:variable name="p:MAP2" as="xs:integer+" select="
    57344, 65536, 65533, 1114111, 26, 26
  "/>

  <!--~
   ! The token-set-id to DFA-initial-state mapping.
  -->
  <xsl:variable name="p:INITIAL" as="xs:integer+" select="
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
  "/>

  <!--~
   ! The DFA transition table.
  -->
  <xsl:variable name="p:TRANSITION" as="xs:integer+" select="
    240, 240, 240, 240, 240, 240, 240, 240, 232, 232, 249, 620, 464, 240, 240, 240, 259, 278, 571, 334, 464, 240, 240, 240, 232, 232, 239, 240, 240, 240, 240,
    240, 286, 300, 240, 334, 464, 240, 240, 240, 232, 232, 308, 346, 361, 240, 240, 240, 373, 373, 381, 491, 405, 240, 240, 240, 241, 398, 381, 315, 567, 240,
    240, 240, 240, 240, 381, 491, 498, 240, 240, 240, 596, 417, 425, 322, 464, 240, 240, 240, 624, 626, 381, 491, 498, 240, 240, 240, 240, 502, 381, 491, 498,
    240, 240, 240, 240, 251, 444, 491, 432, 240, 240, 240, 436, 365, 381, 491, 498, 240, 240, 240, 265, 270, 476, 346, 361, 240, 240, 240, 530, 270, 476, 346,
    361, 240, 240, 240, 388, 606, 484, 491, 498, 240, 240, 240, 338, 510, 381, 491, 498, 240, 240, 240, 329, 468, 381, 517, 498, 240, 240, 240, 240, 240, 381,
    491, 525, 240, 240, 240, 240, 390, 381, 491, 498, 240, 240, 240, 538, 292, 546, 451, 498, 240, 240, 240, 240, 240, 546, 560, 498, 240, 240, 240, 579, 585,
    593, 458, 498, 240, 240, 240, 409, 353, 381, 491, 498, 240, 240, 240, 240, 240, 593, 553, 498, 240, 240, 240, 240, 240, 604, 620, 464, 240, 240, 240, 232,
    232, 308, 491, 498, 240, 240, 240, 240, 614, 240, 240, 240, 240, 240, 240, 465, 465, 465, 465, 465, 465, 465, 465, 0, 0, 0, 0, 0, 0, 0, 0, 26, 465, 603, 0,
    0, 0, 0, 0, 0, 0, 832, 0, 512, 512, 512, 512, 512, 0, 0, 0, 280, 960, 280, 280, 280, 280, 280, 280, 280, 280, 512, 512, 512, 512, 512, 512, 512, 512, 0,
    533, 533, 533, 533, 533, 0, 0, 19, 0, 19, 0, 0, 19, 533, 533, 533, 533, 533, 533, 533, 533, 465, 603, 29, 31, 0, 0, 23, 0, 0, 603, 604, 29, 30, 31, 0, 0,
    603, 604, 30, 29, 31, 0, 0, 1088, 1088, 0, 0, 0, 0, 35, 0, 0, 0, 0, 0, 25, 0, 25, 33, 34, 603, 604, 29, 30, 31, 0, 0, 1216, 1216, 1216, 0, 1216, 1216, 33,
    34, 35, 35, 0, 0, 0, 0, 0, 896, 896, 896, 594, 594, 594, 594, 594, 594, 594, 594, 0, 603, 29, 31, 0, 0, 23, 0, 22, 0, 0, 0, 0, 0, 0, 0, 1152, 26, 26, 26,
    26, 0, 26, 26, 0, 34, 35, 611, 0, 0, 0, 0, 0, 1216, 0, 0, 23, 23, 23, 23, 23, 23, 23, 23, 0, 603, 30, 31, 0, 0, 256, 0, 34, 36, 36, 0, 0, 0, 0, 896, 0, 0,
    0, 0, 604, 29, 31, 0, 0, 23, 0, 34, 603, 604, 0, 30, 31, 0, 34, 603, 604, 29, 30, 0, 0, 35, 35, 0, 0, 0, 0, 0, 0, 1088, 0, 0, 603, 29, 31, 0, 0, 23, 280, 0,
    603, 29, 31, 0, 32, 23, 0, 34, 603, 604, 29, 30, 31, 0, 34, 35, 35, 0, 0, 0, 0, 768, 0, 0, 768, 25, 25, 25, 25, 0, 25, 25, 0, 34, 603, 604, 29, 30, 31,
    1024, 192, 34, 35, 35, 0, 0, 0, 0, 280, 0, 280, 280, 280, 19, 0, 0, 0, 0, 0, 19, 19, 0, 603, 0, 31, 0, 0, 23, 0, 34, 603, 604, 29, 30, 384, 0, 34, 603, 604,
    320, 30, 31, 0, 128, 35, 35, 0, 0, 0, 0, 512, 0, 0, 0, 20, 0, 0, 0, 0, 0, 20, 20, 20, 20, 20, 20, 20, 20, 0, 603, 29, 0, 0, 0, 23, 0, 23, 23, 23, 0, 603, 0,
    0, 0, 0, 0, 0, 22, 0, 0, 640, 0, 640, 0, 640, 0, 0, 603, 604, 0, 0, 0, 0, 0, 0, 704, 0, 0, 704
  "/>

  <!--~
   ! The DFA-state to expected-token-set mapping.
  -->
  <xsl:variable name="p:EXPECTED" as="xs:integer+" select="
    368, 33216, 65984, 65992, 25024, 262604, 1400, 382, 494, 1006, 262654, 263150, 265720, 9198, 369134, 409080, 64, 256, 16, 32, 128, 32768, 8, 8, 4, 2, 256,
    256, 16, 16, 32, 32768, 4, 2, 256, 256
  "/>

  <!--~
   ! The token-string table.
  -->
  <xsl:variable name="p:TOKEN" as="xs:string+" select="
    '%ERROR',
    'ParameterName',
    'Nonterminal',
    'Terminal',
    'SetLiteral',
    'SetName',
    'Whitespace_Ch',
    'NewLine',
    'Comment',
    'EOF',
    &quot;'('&quot;,
    &quot;')'&quot;,
    &quot;'*'&quot;,
    &quot;'+'&quot;,
    &quot;'-'&quot;,
    &quot;'::='&quot;,
    &quot;'='&quot;,
    &quot;'?'&quot;,
    &quot;'|'&quot;
  "/>

  <!--~
   ! Match next token in input string, starting at given index, using
   ! the DFA entry state for the set of tokens that are expected in
   ! the current context.
   !
   ! @param $input the input string.
   ! @param $begin the index where to start in input string.
   ! @param $token-set the expected token set id.
   ! @return a sequence of three: the token code of the result token,
   ! with input string begin and end positions. If there is no valid
   ! token, return the negative id of the DFA state that failed, along
   ! with begin and end positions of the longest viable prefix.
  -->
  <xsl:function name="p:match" as="xs:integer+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="begin" as="xs:integer"/>
    <xsl:param name="token-set" as="xs:integer"/>

    <xsl:variable name="result" select="$p:INITIAL[1 + $token-set]"/>
    <xsl:sequence select="p:transition($input, $begin, $begin, $begin, $result, $result mod 64, 0)"/>
  </xsl:function>

  <!--~
   ! The DFA state transition function. If we are in a valid DFA state, save
   ! it's result annotation, consume one input codepoint, calculate the next
   ! state, and use tail recursion to do the same again. Otherwise, return
   ! any valid result or a negative DFA state id in case of an error.
   !
   ! @param $input the input string.
   ! @param $begin the begin index of the current token in the input string.
   ! @param $current the index of the current position in the input string.
   ! @param $end the end index of the result in the input string.
   ! @param $result the result code.
   ! @param $current-state the current DFA state.
   ! @param $previous-state the  previous DFA state.
   ! @return a sequence of three: the token code of the result token,
   ! with input string begin and end positions. If there is no valid
   ! token, return the negative id of the DFA state that failed, along
   ! with begin and end positions of the longest viable prefix.
  -->
  <xsl:function name="p:transition">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="begin" as="xs:integer"/>
    <xsl:param name="current" as="xs:integer"/>
    <xsl:param name="end" as="xs:integer"/>
    <xsl:param name="result" as="xs:integer"/>
    <xsl:param name="current-state" as="xs:integer"/>
    <xsl:param name="previous-state" as="xs:integer"/>

    <xsl:choose>
      <xsl:when test="$current-state eq 0">
        <xsl:variable name="result" select="$result idiv 64"/>
        <xsl:variable name="end" select="if ($end gt string-length($input)) then string-length($input) + 1 else $end"/>
        <xsl:sequence select="
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
        "/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="c0" select="(string-to-codepoints(substring($input, $current, 1)), 0)[1]"/>
        <xsl:variable name="c1" as="xs:integer">
          <xsl:choose>
            <xsl:when test="$c0 &lt; 128">
              <xsl:sequence select="$p:MAP0[1 + $c0]"/>
            </xsl:when>
            <xsl:when test="$c0 &lt; 55296">
              <xsl:variable name="c1" select="$c0 idiv 16"/>
              <xsl:variable name="c2" select="$c1 idiv 64"/>
              <xsl:sequence select="$p:MAP1[1 + $c0 mod 16 + $p:MAP1[1 + $c1 mod 64 + $p:MAP1[1 + $c2]]]"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="p:map2($c0, 1, 2)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:variable name="current" select="$current + 1"/>
        <xsl:variable name="i0" select="64 * $c1 + $current-state - 1"/>
        <xsl:variable name="i1" select="$i0 idiv 8"/>
        <xsl:variable name="next-state" select="$p:TRANSITION[$i0 mod 8 + $p:TRANSITION[$i1 + 1] + 1]"/>
        <xsl:sequence select="
          if ($next-state &gt; 63) then
            p:transition($input, $begin, $current, $current, $next-state, $next-state mod 64, $current-state)
          else
            p:transition($input, $begin, $current, $end, $result, $next-state, $current-state)
        "/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Recursively translate one 32-bit chunk of an expected token bitset
   ! to the corresponding sequence of token strings.
   !
   ! @param $result the result of previous recursion levels.
   ! @param $chunk the 32-bit chunk of the expected token bitset.
   ! @param $base-token-code the token code of bit 0 in the current chunk.
   ! @return the set of token strings.
  -->
  <xsl:function name="p:token">
    <xsl:param name="result" as="xs:string*"/>
    <xsl:param name="chunk" as="xs:integer"/>
    <xsl:param name="base-token-code" as="xs:integer"/>

    <xsl:sequence select="
      if ($chunk = 0) then
        $result
      else
        p:token
        (
          ($result, if ($chunk mod 2 != 0) then $p:TOKEN[$base-token-code] else ()),
          if ($chunk &lt; 0) then $chunk idiv 2 + 2147483648 else $chunk idiv 2,
          $base-token-code + 1
        )
    "/>
  </xsl:function>

  <!--~
   ! Calculate expected token set for a given DFA state as a sequence
   ! of strings.
   !
   ! @param $state the DFA state.
   ! @return the set of token strings
  -->
  <xsl:function name="p:expected-token-set" as="xs:string*">
    <xsl:param name="state" as="xs:integer"/>

    <xsl:if test="$state > 0">
      <xsl:for-each select="0 to 0">
        <xsl:variable name="i0" select=". * 36 + $state - 1"/>
        <xsl:sequence select="p:token((), $p:EXPECTED[$i0 + 1], . * 32 + 1)"/>
      </xsl:for-each>
    </xsl:if>
  </xsl:function>

  <!--~
   ! Classify codepoint by doing a tail recursive binary search for a
   ! matching codepoint range entry in MAP2, the codepoint to charclass
   ! map for codepoints above the surrogate block.
   !
   ! @param $c the codepoint.
   ! @param $lo the binary search lower bound map index.
   ! @param $hi the binary search upper bound map index.
   ! @return the character class.
  -->
  <xsl:function name="p:map2" as="xs:integer">
    <xsl:param name="c" as="xs:integer"/>
    <xsl:param name="lo" as="xs:integer"/>
    <xsl:param name="hi" as="xs:integer"/>

    <xsl:variable name="m" select="($hi + $lo) idiv 2"/>
    <xsl:choose>
      <xsl:when test="$lo &gt; $hi">
        <xsl:sequence select="0"/>
      </xsl:when>
      <xsl:when test="$p:MAP2[$m] &gt; $c">
        <xsl:sequence select="p:map2($c, $lo, $m - 1)"/>
      </xsl:when>
      <xsl:when test="$p:MAP2[2 + $m] &lt; $c">
        <xsl:sequence select="p:map2($c, $m + 1, $hi)"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:sequence select="$p:MAP2[4 + $m]"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Symbol.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Symbol" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- Terminal -->
          <xsl:variable name="state" select="p:consume(3, $input, $state)"/>        <!-- Terminal -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(2, $input, $state)"/>        <!-- Nonterminal -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Symbol', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Try parsing Symbol.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Symbol" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- Terminal -->
          <xsl:variable name="state" select="p:consumeT(3, $input, $state)"/>       <!-- Terminal -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consumeT(2, $input, $state)"/>       <!-- Nonterminal -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:sequence select="$state"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Handle (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Handle-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(5, $input, $state)"/>      <!-- Nonterminal | Terminal | Whitespace_Ch | NewLine | Comment | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 2                                          (: Nonterminal :)
                      and $state[$p:l1] != 3">                                      <!-- Terminal -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Symbol($input, $state)
            "/>
            <xsl:sequence select="p:parse-Handle-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Try parsing the 1st loop of production Handle (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Handle-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(5, $input, $state)"/>      <!-- Nonterminal | Terminal | Whitespace_Ch | NewLine | Comment | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 2                                          (: Nonterminal :)
                      and $state[$p:l1] != 3">                                      <!-- Terminal -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:try-Symbol($input, $state)
            "/>
            <xsl:sequence select="p:try-Handle-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Handle.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Handle" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-Handle-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Handle', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Try parsing Handle.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Handle" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="state" select="p:try-Handle-1($input, $state)"/>
    <xsl:sequence select="$state"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Handles (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Handles-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" as="item()+">
          <xsl:choose>
            <xsl:when test="$state[$p:l1] eq 7">                                    <!-- NewLine -->
              <xsl:variable name="state" select="p:lookahead2W(11, $input, $state)"/> <!-- ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch |
                                                                                           NewLine | Comment | EOF | '|' -->
              <xsl:variable name="state" as="item()+">
                <xsl:choose>
                  <xsl:when test="$state[$p:lk] eq 231">                            <!-- NewLine NewLine -->
                    <xsl:variable name="state" select="p:lookahead3W(11, $input, $state)"/> <!-- ParameterName | Nonterminal | Terminal | SetName |
                                                                                                 Whitespace_Ch | NewLine | Comment | EOF | '|' -->
                    <xsl:sequence select="$state"/>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:sequence select="$state"/>
                  </xsl:otherwise>
                </xsl:choose>
              </xsl:variable>
              <xsl:sequence select="$state"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="$state[$p:l1], subsequence($state, $p:lk + 1)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:variable name="state" as="item()+">
          <xsl:choose>
            <xsl:when test="$state[$p:error]">
              <xsl:sequence select="$state"/>
            </xsl:when>
            <xsl:when test="$state[$p:lk] = 7399">                                  <!-- NewLine NewLine NewLine -->
              <xsl:variable name="state" select="p:memoized($state, 3)"/>
              <xsl:choose>
                <xsl:when test="$state[$p:lk] != 0">
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:variable name="backtrack" select="$state"/>
                  <xsl:variable name="state" select="p:strip-result($state)"/>
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:try-nl_opt($input, $state)
                  "/>
                  <xsl:variable name="state" select="p:consumeT(18, $input, $state)"/> <!-- '|' -->
                  <xsl:variable name="state" select="p:lookahead1W(5, $input, $state)"/> <!-- Nonterminal | Terminal | Whitespace_Ch | NewLine | Comment |
                                                                                              '|' -->
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:try-Handle($input, $state)
                  "/>
                  <xsl:choose>
                    <xsl:when test="not($state[$p:error])">
                      <xsl:sequence select="p:memoize($backtrack, $state, 3, $backtrack[$p:e0], -1, -1)"/>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:sequence select="p:memoize($backtrack, $state, 3, $backtrack[$p:e0], -2, -2)"/>
                    </xsl:otherwise>
                  </xsl:choose>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="$state"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:choose>
          <xsl:when test="$state[$p:lk] != -1
                      and $state[$p:lk] != 18                                         (: '|' :)
                      and $state[$p:lk] != 583                                        (: NewLine '|' :)
                      and $state[$p:lk] != 18663">                                  <!-- NewLine NewLine '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-nl_opt($input, $state)
            "/>
            <xsl:variable name="state" select="p:consume(18, $input, $state)"/>     <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(5, $input, $state)"/>  <!-- Nonterminal | Terminal | Whitespace_Ch | NewLine | Comment | '|' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Handle($input, $state)
            "/>
            <xsl:sequence select="p:parse-Handles-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Handles.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Handles" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Handle($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-Handles-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Handles', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Rule_Decl.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Rule_Decl" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(2, $input, $state)"/>              <!-- Nonterminal -->
    <xsl:variable name="state" select="p:lookahead1W(1, $input, $state)"/>          <!-- Whitespace_Ch | NewLine | Comment | '::=' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-nl_opt($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(15, $input, $state)"/>             <!-- '::=' -->
    <xsl:variable name="state" select="p:lookahead1W(5, $input, $state)"/>          <!-- Nonterminal | Terminal | Whitespace_Ch | NewLine | Comment | '|' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Handles($input, $state)
    "/>
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-nl($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Rule_Decl', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Reg_Exp_2 (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Reg_Exp_2-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 18">                                     <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(18, $input, $state)"/>     <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>  <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '(' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Reg_Exp_Seq($input, $state)
            "/>
            <xsl:sequence select="p:parse-Reg_Exp_2-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Try parsing the 1st loop of production Reg_Exp_2 (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Reg_Exp_2-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 18">                                     <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consumeT(18, $input, $state)"/>    <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>  <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '(' -->
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:try-Reg_Exp_Seq($input, $state)
            "/>
            <xsl:sequence select="p:try-Reg_Exp_2-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Reg_Exp_2.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Reg_Exp_2" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Reg_Exp_Seq($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-Reg_Exp_2-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Reg_Exp_2', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Try parsing Reg_Exp_2.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Reg_Exp_2" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:try-Reg_Exp_Seq($input, $state)
    "/>
    <xsl:variable name="state" select="p:try-Reg_Exp_2-1($input, $state)"/>
    <xsl:sequence select="$state"/>
  </xsl:function>

  <!--~
   ! Parse Kleene_Opt.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Kleene_Opt" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 13">                                        <!-- '+' -->
          <xsl:variable name="state" select="p:consume(13, $input, $state)"/>       <!-- '+' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 17">                                        <!-- '?' -->
          <xsl:variable name="state" select="p:consume(17, $input, $state)"/>       <!-- '?' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 12">                                        <!-- '*' -->
          <xsl:variable name="state" select="p:consume(12, $input, $state)"/>       <!-- '*' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Kleene_Opt', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Try parsing Kleene_Opt.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Kleene_Opt" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 13">                                        <!-- '+' -->
          <xsl:variable name="state" select="p:consumeT(13, $input, $state)"/>      <!-- '+' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 17">                                        <!-- '?' -->
          <xsl:variable name="state" select="p:consumeT(17, $input, $state)"/>      <!-- '?' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 12">                                        <!-- '*' -->
          <xsl:variable name="state" select="p:consumeT(12, $input, $state)"/>      <!-- '*' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:sequence select="$state"/>
  </xsl:function>

  <!--~
   ! Parse Reg_Exp_Item.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Reg_Exp_Item" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 4">                                         <!-- SetLiteral -->
          <xsl:variable name="state" select="p:consume(4, $input, $state)"/>        <!-- SetLiteral -->
          <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>   <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment |
                                                                                         '(' | ')' | '*' | '+' | '?' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Kleene_Opt($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 5">                                         <!-- SetName -->
          <xsl:variable name="state" select="p:consume(5, $input, $state)"/>        <!-- SetName -->
          <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>   <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment |
                                                                                         '(' | ')' | '*' | '+' | '?' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Kleene_Opt($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- Terminal -->
          <xsl:variable name="state" select="p:consume(3, $input, $state)"/>        <!-- Terminal -->
          <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>   <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment |
                                                                                         '(' | ')' | '*' | '+' | '?' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Kleene_Opt($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(10, $input, $state)"/>       <!-- '(' -->
          <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>    <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '(' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Reg_Exp_2($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(11, $input, $state)"/>       <!-- ')' -->
          <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>   <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment |
                                                                                         '(' | ')' | '*' | '+' | '?' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Kleene_Opt($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Reg_Exp_Item', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Try parsing Reg_Exp_Item.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Reg_Exp_Item" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 4">                                         <!-- SetLiteral -->
          <xsl:variable name="state" select="p:consumeT(4, $input, $state)"/>       <!-- SetLiteral -->
          <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>   <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment |
                                                                                         '(' | ')' | '*' | '+' | '?' | '|' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:try-Kleene_Opt($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 5">                                         <!-- SetName -->
          <xsl:variable name="state" select="p:consumeT(5, $input, $state)"/>       <!-- SetName -->
          <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>   <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment |
                                                                                         '(' | ')' | '*' | '+' | '?' | '|' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:try-Kleene_Opt($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- Terminal -->
          <xsl:variable name="state" select="p:consumeT(3, $input, $state)"/>       <!-- Terminal -->
          <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>   <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment |
                                                                                         '(' | ')' | '*' | '+' | '?' | '|' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:try-Kleene_Opt($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consumeT(10, $input, $state)"/>      <!-- '(' -->
          <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>    <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '(' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:try-Reg_Exp_2($input, $state)
          "/>
          <xsl:variable name="state" select="p:consumeT(11, $input, $state)"/>      <!-- ')' -->
          <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>   <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment |
                                                                                         '(' | ')' | '*' | '+' | '?' | '|' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:try-Kleene_Opt($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:sequence select="$state"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Reg_Exp_Seq (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Reg_Exp_Seq-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:whitespace($input, $state)"/>
        <xsl:variable name="state" select="
          if ($state[$p:error]) then
            $state
          else
            p:parse-Reg_Exp_Item($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(12, $input, $state)"/>     <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment |
                                                                                         '(' | ')' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 7                                           (: NewLine :)
                       or $state[$p:l1] = 11                                          (: ')' :)
                       or $state[$p:l1] = 18">                                      <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-Reg_Exp_Seq-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Try parsing the 1st loop of production Reg_Exp_Seq (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Reg_Exp_Seq-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="
          if ($state[$p:error]) then
            $state
          else
            p:try-Reg_Exp_Item($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(12, $input, $state)"/>     <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment |
                                                                                         '(' | ')' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 7                                           (: NewLine :)
                       or $state[$p:l1] = 11                                          (: ')' :)
                       or $state[$p:l1] = 18">                                      <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:try-Reg_Exp_Seq-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Reg_Exp_Seq.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Reg_Exp_Seq" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-Reg_Exp_Seq-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Reg_Exp_Seq', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Try parsing Reg_Exp_Seq.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Reg_Exp_Seq" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="state" select="p:try-Reg_Exp_Seq-1($input, $state)"/>
    <xsl:sequence select="$state"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Reg_Exp (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Reg_Exp-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" as="item()+">
          <xsl:choose>
            <xsl:when test="$state[$p:l1] eq 7">                                    <!-- NewLine -->
              <xsl:variable name="state" select="p:lookahead2W(11, $input, $state)"/> <!-- ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch |
                                                                                           NewLine | Comment | EOF | '|' -->
              <xsl:variable name="state" as="item()+">
                <xsl:choose>
                  <xsl:when test="$state[$p:lk] eq 231">                            <!-- NewLine NewLine -->
                    <xsl:variable name="state" select="p:lookahead3W(11, $input, $state)"/> <!-- ParameterName | Nonterminal | Terminal | SetName |
                                                                                                 Whitespace_Ch | NewLine | Comment | EOF | '|' -->
                    <xsl:sequence select="$state"/>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:sequence select="$state"/>
                  </xsl:otherwise>
                </xsl:choose>
              </xsl:variable>
              <xsl:sequence select="$state"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="$state[$p:l1], subsequence($state, $p:lk + 1)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:variable name="state" as="item()+">
          <xsl:choose>
            <xsl:when test="$state[$p:error]">
              <xsl:sequence select="$state"/>
            </xsl:when>
            <xsl:when test="$state[$p:lk] = 7399">                                  <!-- NewLine NewLine NewLine -->
              <xsl:variable name="state" select="p:memoized($state, 2)"/>
              <xsl:choose>
                <xsl:when test="$state[$p:lk] != 0">
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:variable name="backtrack" select="$state"/>
                  <xsl:variable name="state" select="p:strip-result($state)"/>
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:try-nl_opt($input, $state)
                  "/>
                  <xsl:variable name="state" select="p:consumeT(18, $input, $state)"/> <!-- '|' -->
                  <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/> <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | Comment |
                                                                                              '(' -->
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:try-Reg_Exp_Seq($input, $state)
                  "/>
                  <xsl:choose>
                    <xsl:when test="not($state[$p:error])">
                      <xsl:sequence select="p:memoize($backtrack, $state, 2, $backtrack[$p:e0], -1, -1)"/>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:sequence select="p:memoize($backtrack, $state, 2, $backtrack[$p:e0], -2, -2)"/>
                    </xsl:otherwise>
                  </xsl:choose>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="$state"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:choose>
          <xsl:when test="$state[$p:lk] != -1
                      and $state[$p:lk] != 18                                         (: '|' :)
                      and $state[$p:lk] != 583                                        (: NewLine '|' :)
                      and $state[$p:lk] != 18663">                                  <!-- NewLine NewLine '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-nl_opt($input, $state)
            "/>
            <xsl:variable name="state" select="p:consume(18, $input, $state)"/>     <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>  <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '(' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Reg_Exp_Seq($input, $state)
            "/>
            <xsl:sequence select="p:parse-Reg_Exp-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Reg_Exp.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Reg_Exp" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Reg_Exp_Seq($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-Reg_Exp-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Reg_Exp', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Terminal_Name (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Terminal_Name-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:consume(3, $input, $state)"/>          <!-- Terminal -->
        <xsl:variable name="state" select="p:lookahead1W(3, $input, $state)"/>      <!-- Terminal | Whitespace_Ch | NewLine | Comment | '=' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 3">                                      <!-- Terminal -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-Terminal_Name-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Terminal_Name.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Terminal_Name" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-Terminal_Name-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Terminal_Name', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Terminal_Decl.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Terminal_Decl" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Terminal_Name($input, $state)
    "/>
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-nl_opt($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(16, $input, $state)"/>             <!-- '=' -->
    <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>          <!-- Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '(' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Reg_Exp($input, $state)
    "/>
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-nl($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Terminal_Decl', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Set_Item.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Set_Item" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 4">                                         <!-- SetLiteral -->
          <xsl:variable name="state" select="p:consume(4, $input, $state)"/>        <!-- SetLiteral -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(5, $input, $state)"/>        <!-- SetName -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Set_Item', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Try parsing Set_Item.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Set_Item" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 4">                                         <!-- SetLiteral -->
          <xsl:variable name="state" select="p:consumeT(4, $input, $state)"/>       <!-- SetLiteral -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consumeT(5, $input, $state)"/>       <!-- SetName -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:sequence select="$state"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Set_Exp (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Set_Exp-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(4, $input, $state)"/>      <!-- Whitespace_Ch | NewLine | Comment | '+' | '-' -->
        <xsl:variable name="state" as="item()+">
          <xsl:choose>
            <xsl:when test="$state[$p:l1] eq 7">                                    <!-- NewLine -->
              <xsl:variable name="state" select="p:lookahead2W(13, $input, $state)"/> <!-- ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch |
                                                                                           NewLine | Comment | EOF | '+' | '-' -->
              <xsl:variable name="state" as="item()+">
                <xsl:choose>
                  <xsl:when test="$state[$p:lk] eq 231">                            <!-- NewLine NewLine -->
                    <xsl:variable name="state" select="p:lookahead3W(13, $input, $state)"/> <!-- ParameterName | Nonterminal | Terminal | SetName |
                                                                                                 Whitespace_Ch | NewLine | Comment | EOF | '+' | '-' -->
                    <xsl:sequence select="$state"/>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:sequence select="$state"/>
                  </xsl:otherwise>
                </xsl:choose>
              </xsl:variable>
              <xsl:sequence select="$state"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="$state[$p:l1], subsequence($state, $p:lk + 1)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:variable name="state" as="item()+">
          <xsl:choose>
            <xsl:when test="$state[$p:error]">
              <xsl:sequence select="$state"/>
            </xsl:when>
            <xsl:when test="$state[$p:lk] = 7399">                                  <!-- NewLine NewLine NewLine -->
              <xsl:variable name="state" select="p:memoized($state, 1)"/>
              <xsl:choose>
                <xsl:when test="$state[$p:lk] != 0">
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:variable name="backtrack" select="$state"/>
                  <xsl:variable name="state" select="p:strip-result($state)"/>
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:try-nl_opt($input, $state)
                  "/>
                  <xsl:variable name="state" as="item()+">
                    <xsl:choose>
                      <xsl:when test="$state[$p:error]">
                        <xsl:sequence select="$state"/>
                      </xsl:when>
                      <xsl:when test="$state[$p:l1] = 13">                          <!-- '+' -->
                        <xsl:variable name="state" select="p:consumeT(13, $input, $state)"/> <!-- '+' -->
                        <xsl:sequence select="$state"/>
                      </xsl:when>
                      <xsl:otherwise>
                        <xsl:variable name="state" select="p:consumeT(14, $input, $state)"/> <!-- '-' -->
                        <xsl:sequence select="$state"/>
                      </xsl:otherwise>
                    </xsl:choose>
                  </xsl:variable>
                  <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/> <!-- SetLiteral | SetName | Whitespace_Ch | Comment -->
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:try-Set_Item($input, $state)
                  "/>
                  <xsl:choose>
                    <xsl:when test="not($state[$p:error])">
                      <xsl:sequence select="p:memoize($backtrack, $state, 1, $backtrack[$p:e0], -1, -1)"/>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:sequence select="p:memoize($backtrack, $state, 1, $backtrack[$p:e0], -2, -2)"/>
                    </xsl:otherwise>
                  </xsl:choose>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="$state"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:choose>
          <xsl:when test="$state[$p:lk] != -1
                      and $state[$p:lk] != 13                                         (: '+' :)
                      and $state[$p:lk] != 14                                         (: '-' :)
                      and $state[$p:lk] != 423                                        (: NewLine '+' :)
                      and $state[$p:lk] != 455                                        (: NewLine '-' :)
                      and $state[$p:lk] != 13543                                      (: NewLine NewLine '+' :)
                      and $state[$p:lk] != 14567">                                  <!-- NewLine NewLine '-' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-nl_opt($input, $state)
            "/>
            <xsl:variable name="state" as="item()+">
              <xsl:choose>
                <xsl:when test="$state[$p:error]">
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:when test="$state[$p:l1] = 13">                                <!-- '+' -->
                  <xsl:variable name="state" select="p:consume(13, $input, $state)"/> <!-- '+' -->
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:variable name="state" select="p:consume(14, $input, $state)"/> <!-- '-' -->
                  <xsl:sequence select="$state"/>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:variable>
            <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/>  <!-- SetLiteral | SetName | Whitespace_Ch | Comment -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Set_Item($input, $state)
            "/>
            <xsl:sequence select="p:parse-Set_Exp-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Set_Exp.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Set_Exp" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Set_Item($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-Set_Exp-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Set_Exp', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Set_Decl.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Set_Decl" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(5, $input, $state)"/>              <!-- SetName -->
    <xsl:variable name="state" select="p:lookahead1W(2, $input, $state)"/>          <!-- Whitespace_Ch | NewLine | Comment | '=' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-nl_opt($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(16, $input, $state)"/>             <!-- '=' -->
    <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/>          <!-- SetLiteral | SetName | Whitespace_Ch | Comment -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Set_Exp($input, $state)
    "/>
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-nl($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Set_Decl', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production nl (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-nl-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:consume(7, $input, $state)"/>          <!-- NewLine -->
        <xsl:variable name="state" select="p:lookahead1W(9, $input, $state)"/>      <!-- ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch |
                                                                                         NewLine | Comment | EOF -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 7">                                      <!-- NewLine -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-nl-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse nl.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-nl" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-nl-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'nl', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Parameter_Item.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Parameter_Item" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 1">                                         <!-- ParameterName -->
          <xsl:variable name="state" select="p:consume(1, $input, $state)"/>        <!-- ParameterName -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- Terminal -->
          <xsl:variable name="state" select="p:consume(3, $input, $state)"/>        <!-- Terminal -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 4">                                         <!-- SetLiteral -->
          <xsl:variable name="state" select="p:consume(4, $input, $state)"/>        <!-- SetLiteral -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 5">                                         <!-- SetName -->
          <xsl:variable name="state" select="p:consume(5, $input, $state)"/>        <!-- SetName -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(2, $input, $state)"/>        <!-- Nonterminal -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Parameter_Item', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Try parsing Parameter_Item.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Parameter_Item" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 1">                                         <!-- ParameterName -->
          <xsl:variable name="state" select="p:consumeT(1, $input, $state)"/>       <!-- ParameterName -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- Terminal -->
          <xsl:variable name="state" select="p:consumeT(3, $input, $state)"/>       <!-- Terminal -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 4">                                         <!-- SetLiteral -->
          <xsl:variable name="state" select="p:consumeT(4, $input, $state)"/>       <!-- SetLiteral -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 5">                                         <!-- SetName -->
          <xsl:variable name="state" select="p:consumeT(5, $input, $state)"/>       <!-- SetName -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consumeT(2, $input, $state)"/>       <!-- Nonterminal -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:sequence select="$state"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Parameter_Items (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Parameter_Items-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:whitespace($input, $state)"/>
        <xsl:variable name="state" select="
          if ($state[$p:error]) then
            $state
          else
            p:parse-Parameter_Item($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(10, $input, $state)"/>     <!-- ParameterName | Nonterminal | Terminal | SetLiteral | SetName |
                                                                                         Whitespace_Ch | NewLine | Comment | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 7                                           (: NewLine :)
                       or $state[$p:l1] = 18">                                      <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-Parameter_Items-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Try parsing the 1st loop of production Parameter_Items (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Parameter_Items-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="
          if ($state[$p:error]) then
            $state
          else
            p:try-Parameter_Item($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(10, $input, $state)"/>     <!-- ParameterName | Nonterminal | Terminal | SetLiteral | SetName |
                                                                                         Whitespace_Ch | NewLine | Comment | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 7                                           (: NewLine :)
                       or $state[$p:l1] = 18">                                      <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:try-Parameter_Items-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Parameter_Items.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Parameter_Items" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-Parameter_Items-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Parameter_Items', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Try parsing Parameter_Items.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-Parameter_Items" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="state" select="p:try-Parameter_Items-1($input, $state)"/>
    <xsl:sequence select="$state"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Parameter_Body (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Parameter_Body-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" as="item()+">
          <xsl:choose>
            <xsl:when test="$state[$p:l1] eq 7">                                    <!-- NewLine -->
              <xsl:variable name="state" select="p:lookahead2W(11, $input, $state)"/> <!-- ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch |
                                                                                           NewLine | Comment | EOF | '|' -->
              <xsl:variable name="state" as="item()+">
                <xsl:choose>
                  <xsl:when test="$state[$p:lk] eq 231">                            <!-- NewLine NewLine -->
                    <xsl:variable name="state" select="p:lookahead3W(11, $input, $state)"/> <!-- ParameterName | Nonterminal | Terminal | SetName |
                                                                                                 Whitespace_Ch | NewLine | Comment | EOF | '|' -->
                    <xsl:sequence select="$state"/>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:sequence select="$state"/>
                  </xsl:otherwise>
                </xsl:choose>
              </xsl:variable>
              <xsl:sequence select="$state"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="$state[$p:l1], subsequence($state, $p:lk + 1)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:variable name="state" as="item()+">
          <xsl:choose>
            <xsl:when test="$state[$p:error]">
              <xsl:sequence select="$state"/>
            </xsl:when>
            <xsl:when test="$state[$p:lk] = 7399">                                  <!-- NewLine NewLine NewLine -->
              <xsl:variable name="state" select="p:memoized($state, 0)"/>
              <xsl:choose>
                <xsl:when test="$state[$p:lk] != 0">
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:variable name="backtrack" select="$state"/>
                  <xsl:variable name="state" select="p:strip-result($state)"/>
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:try-nl_opt($input, $state)
                  "/>
                  <xsl:variable name="state" select="p:consumeT(18, $input, $state)"/> <!-- '|' -->
                  <xsl:variable name="state" select="p:lookahead1W(7, $input, $state)"/> <!-- ParameterName | Nonterminal | Terminal | SetLiteral | SetName |
                                                                                              Whitespace_Ch | Comment -->
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:try-Parameter_Items($input, $state)
                  "/>
                  <xsl:choose>
                    <xsl:when test="not($state[$p:error])">
                      <xsl:sequence select="p:memoize($backtrack, $state, 0, $backtrack[$p:e0], -1, -1)"/>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:sequence select="p:memoize($backtrack, $state, 0, $backtrack[$p:e0], -2, -2)"/>
                    </xsl:otherwise>
                  </xsl:choose>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="$state"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:choose>
          <xsl:when test="$state[$p:lk] != -1
                      and $state[$p:lk] != 18                                         (: '|' :)
                      and $state[$p:lk] != 583                                        (: NewLine '|' :)
                      and $state[$p:lk] != 18663">                                  <!-- NewLine NewLine '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-nl_opt($input, $state)
            "/>
            <xsl:variable name="state" select="p:consume(18, $input, $state)"/>     <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(7, $input, $state)"/>  <!-- ParameterName | Nonterminal | Terminal | SetLiteral | SetName |
                                                                                         Whitespace_Ch | Comment -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Parameter_Items($input, $state)
            "/>
            <xsl:sequence select="p:parse-Parameter_Body-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Parameter_Body.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Parameter_Body" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Parameter_Items($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-Parameter_Body-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Parameter_Body', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Parameter.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Parameter" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(1, $input, $state)"/>              <!-- ParameterName -->
    <xsl:variable name="state" select="p:lookahead1W(2, $input, $state)"/>          <!-- Whitespace_Ch | NewLine | Comment | '=' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-nl_opt($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(16, $input, $state)"/>             <!-- '=' -->
    <xsl:variable name="state" select="p:lookahead1W(7, $input, $state)"/>          <!-- ParameterName | Nonterminal | Terminal | SetLiteral | SetName |
                                                                                         Whitespace_Ch | Comment -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Parameter_Body($input, $state)
    "/>
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-nl($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Parameter', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Definition.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Definition" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 1">                                         <!-- ParameterName -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Parameter($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 5">                                         <!-- SetName -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Set_Decl($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- Terminal -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Terminal_Decl($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Rule_Decl($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Definition', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Content (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Content-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:whitespace($input, $state)"/>
        <xsl:variable name="state" select="
          if ($state[$p:error]) then
            $state
          else
            p:parse-Definition($input, $state)
        "/>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 9">                                       <!-- EOF -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-Content-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Content.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Content" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-Content-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Content', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production nl_opt (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-nl_opt-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(14, $input, $state)"/>     <!-- ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch |
                                                                                         NewLine | Comment | '+' | '-' | '::=' | '=' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 7">                                      <!-- NewLine -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(7, $input, $state)"/>      <!-- NewLine -->
            <xsl:sequence select="p:parse-nl_opt-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Try parsing the 1st loop of production nl_opt (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-nl_opt-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(14, $input, $state)"/>     <!-- ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch |
                                                                                         NewLine | Comment | '+' | '-' | '::=' | '=' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 7">                                      <!-- NewLine -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consumeT(7, $input, $state)"/>     <!-- NewLine -->
            <xsl:sequence select="p:try-nl_opt-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse nl_opt.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-nl_opt" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-nl_opt-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'nl_opt', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Try parsing nl_opt.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:try-nl_opt" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="state" select="p:try-nl_opt-1($input, $state)"/>
    <xsl:sequence select="$state"/>
  </xsl:function>

  <!--~
   ! Parse Grammar.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Grammar" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:lookahead1W(8, $input, $state)"/>          <!-- ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch |
                                                                                         NewLine | Comment -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-nl_opt($input, $state)
    "/>
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Content($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(9, $input, $state)"/>              <!-- EOF -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Grammar', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Create a textual error message from a parsing error.
   !
   ! @param $input the input string.
   ! @param $error the parsing error descriptor.
   ! @return the error message.
  -->
  <xsl:function name="p:error-message" as="xs:string">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="error" as="element(error)"/>

    <xsl:variable name="begin" select="xs:integer($error/@b)"/>
    <xsl:variable name="context" select="string-to-codepoints(substring($input, 1, $begin - 1))"/>
    <xsl:variable name="linefeeds" select="index-of($context, 10)"/>
    <xsl:variable name="line" select="count($linefeeds) + 1"/>
    <xsl:variable name="column" select="($begin - $linefeeds[last()], $begin)[1]"/>
    <xsl:variable name="expected" select="if ($error/@x or $error/@ambiguous-input) then () else p:expected-token-set($error/@s)"/>
    <xsl:sequence select="
      string-join
      (
        (
          if ($error/@o) then
            ('syntax error, found ', $p:TOKEN[$error/@o + 1])
          else
            'lexical analysis failed',
          '&#10;',
          'while expecting ',
          if ($error/@x) then
            $p:TOKEN[$error/@x + 1]
          else
          (
            '['[exists($expected[2])],
            string-join($expected, ', '),
            ']'[exists($expected[2])]
          ),
          '&#10;',
          if ($error/@o or $error/@e = $begin) then
            ()
          else
            ('after successfully scanning ', string($error/@e - $begin), ' characters beginning '),
          'at line ', string($line), ', column ', string($column), ':&#10;',
          '...', substring($input, $begin, 64), '...'
        ),
        ''
      )
    "/>
  </xsl:function>

  <!--~
   ! Consume one token, i.e. compare lookahead token 1 with expected
   ! token and in case of a match, shift lookahead tokens down such that
   ! l1 becomes the current token, and higher lookahead tokens move down.
   ! When lookahead token 1 does not match the expected token, raise an
   ! error by saving the expected token code in the error field of the
   ! lexer state.
   !
   ! @param $code the expected token.
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:consume" as="item()+">
    <xsl:param name="code" as="xs:integer"/>
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:when test="$state[$p:l1] eq $code">
        <xsl:variable name="begin" select="$state[$p:e0]"/>
        <xsl:variable name="end" select="$state[$p:b1]"/>
        <xsl:variable name="whitespace">
          <xsl:if test="$begin ne $end">
            <xsl:value-of select="substring($input, $begin, $end - $begin)"/>
          </xsl:if>
        </xsl:variable>
        <xsl:variable name="token" select="$p:TOKEN[1 + $state[$p:l1]]"/>
        <xsl:variable name="name" select="if (starts-with($token, &quot;'&quot;)) then 'TOKEN' else $token"/>
        <xsl:variable name="begin" select="$state[$p:b1]"/>
        <xsl:variable name="end" select="$state[$p:e1]"/>
        <xsl:variable name="node">
          <xsl:element name="{$name}">
            <xsl:sequence select="substring($input, $begin, $end - $begin)"/>
          </xsl:element>
        </xsl:variable>
        <xsl:sequence select="
          subsequence($state, $p:l1, 9),
          0, 0, 0,
          subsequence($state, 13),
          $whitespace/node(),
          $node/node()
        "/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="error">
          <xsl:element name="error">
            <xsl:choose>
              <xsl:when test="$state[$p:e1] &lt; $state[$p:memo]/@e">
                <xsl:sequence select="$state[$p:memo]/@*"/>
              </xsl:when>
              <xsl:otherwise>
              <xsl:attribute name="b" select="$state[$p:b1]"/>
              <xsl:attribute name="e" select="$state[$p:e1]"/>
              <xsl:choose>
                <xsl:when test="$state[$p:l1] lt 0">
                  <xsl:attribute name="s" select="- $state[$p:l1]"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:attribute name="o" select="$state[$p:l1]"/>
                  <xsl:attribute name="x" select="$code"/>
                </xsl:otherwise>
              </xsl:choose>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:element>
        </xsl:variable>
        <xsl:sequence select="
          subsequence($state, 1, $p:error - 1),
          $error/node(),
          subsequence($state, $p:error + 1)
        "/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Consume one token, i.e. compare lookahead token 1 with expected
   ! token and in case of a match, shift lookahead tokens down such that
   ! l1 becomes the current token, and higher lookahead tokens move down.
   ! When lookahead token 1 does not match the expected token, raise an
   ! error by saving the expected token code in the error field of the
   ! lexer state. In contrast to p:consume, do not create any output.
   !
   ! @param $code the expected token.
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:consumeT" as="item()+">
    <xsl:param name="code" as="xs:integer"/>
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:when test="$state[$p:l1] eq $code">
        <xsl:sequence select="
          subsequence($state, $p:l1, 9),
          0, 0, 0,
          subsequence($state, 13)
        "/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="error">
          <xsl:element name="error">
            <xsl:choose>
              <xsl:when test="$state[$p:e1] &lt; $state[$p:memo]/@e">
                <xsl:sequence select="$state[$p:memo]/@*"/>
              </xsl:when>
              <xsl:otherwise>
              <xsl:attribute name="b" select="$state[$p:b1]"/>
              <xsl:attribute name="e" select="$state[$p:e1]"/>
              <xsl:choose>
                <xsl:when test="$state[$p:l1] lt 0">
                  <xsl:attribute name="s" select="- $state[$p:l1]"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:attribute name="o" select="$state[$p:l1]"/>
                  <xsl:attribute name="x" select="$code"/>
                </xsl:otherwise>
              </xsl:choose>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:element>
        </xsl:variable>
        <xsl:sequence select="
          subsequence($state, 1, $p:error - 1),
          $error/node(),
          subsequence($state, $p:error + 1)
        "/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Consume whitespace.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:whitespace" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="end" select="$state[$p:b1]"/>
    <xsl:choose>
      <xsl:when test="$begin eq $end">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="whitespace">
          <xsl:value-of select="substring($input, $begin, $end - $begin)"/>
        </xsl:variable>
        <xsl:sequence select="
          0,
          $state[$p:b0],
          $end,
          subsequence($state, $p:e0 + 1),
          $whitespace/node()
        "/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Use p:match to fetch the next token, but skip any leading
   ! whitespace.
   !
   ! @param $input the input string.
   ! @param $begin the index where to start.
   ! @param $token-set the valid token set id.
   ! @return a sequence of three values: the token code of the result
   ! token, with input string positions of token begin and end.
  -->
  <xsl:function name="p:matchW">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="begin" as="xs:integer"/>
    <xsl:param name="token-set" as="xs:integer"/>

    <xsl:variable name="match" select="p:match($input, $begin, $token-set)"/>
    <xsl:choose>
      <xsl:when test="$match[1] = 6                                                   (: Whitespace_Ch :)
                   or $match[1] = 8">                                               <!-- Comment -->
        <xsl:sequence select="p:matchW($input, $match[3], $token-set)"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:sequence select="$match"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Lookahead one token on level 1 with whitespace skipping.
   !
   ! @param $set the code of the DFA entry state for the set of valid tokens.
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result stack.
   ! @return the updated state.
  -->
  <xsl:function name="p:lookahead1W" as="item()+">
    <xsl:param name="set" as="xs:integer"/>
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:l1] ne 0">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="match" select="
          p:matchW($input, $state[$p:e0], $set),
          0, 0, 0
        "/>
        <xsl:sequence select="
          $match[1],
          subsequence($state, $p:b0, 2),
          $match,
          subsequence($state, 10)
        "/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Lookahead one token on level 2 with whitespace skipping.
   !
   ! @param $set the code of the DFA entry state for the set of valid tokens.
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result stack.
   ! @return the updated state.
  -->
  <xsl:function name="p:lookahead2W" as="item()+">
    <xsl:param name="set" as="xs:integer"/>
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="match" select="
      if ($state[$p:l2] ne 0) then
        subsequence($state, $p:l2, 6)
      else
      (
        p:matchW($input, $state[$p:e1], $set),
        0, 0, 0
      )
    "/>
    <xsl:sequence select="
      $match[1] * 32 + $state[$p:l1],
      subsequence($state, $p:b0, 5),
      $match,
      subsequence($state, 13)
    "/>
  </xsl:function>

  <!--~
   ! Lookahead one token on level 3 with whitespace skipping.
   !
   ! @param $set the code of the DFA entry state for the set of valid tokens.
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result stack.
   ! @return the updated state.
  -->
  <xsl:function name="p:lookahead3W" as="item()+">
    <xsl:param name="set" as="xs:integer"/>
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="match" select="
      if ($state[$p:l3] ne 0) then
        subsequence($state, $p:l3, 3)
      else
        p:matchW($input, $state[$p:e2], $set)
    "/>
    <xsl:sequence select="
      $match[1] * 1024 + $state[$p:lk],
      subsequence($state, $p:b0, 8),
      $match,
      subsequence($state, 13)
    "/>
  </xsl:function>

  <!--~
   ! Reduce the result stack, creating a nonterminal element. Pop
   ! $count elements off the stack, wrap them in a new element
   ! named $name, and push the new element.
   !
   ! @param $state lexer state, error indicator, and result.
   ! @param $name the name of the result node.
   ! @param $count the number of child nodes.
   ! @param $begin the input index where the nonterminal begins.
   ! @param $end the input index where the nonterminal ends.
   ! @return the updated state.
  -->
  <xsl:function name="p:reduce" as="item()+">
    <xsl:param name="state" as="item()+"/>
    <xsl:param name="name" as="xs:string"/>
    <xsl:param name="count" as="xs:integer"/>
    <xsl:param name="begin" as="xs:integer"/>
    <xsl:param name="end" as="xs:integer"/>

    <xsl:variable name="node">
      <xsl:element name="{$name}">
        <xsl:sequence select="subsequence($state, $count + 1)"/>
      </xsl:element>
    </xsl:variable>
    <xsl:sequence select="subsequence($state, 1, $count), $node/node()"/>
  </xsl:function>

  <!--~
   ! Strip result from lexer state, in order to avoid carrying it while
   ! backtracking.
   !
   ! @param $state the lexer state after an alternative failed.
   ! @return the updated state.
  -->
  <xsl:function name="p:strip-result" as="item()+">
    <xsl:param name="state" as="item()+"/>

    <xsl:sequence select="subsequence($state, 1, $p:memo)"/>
  </xsl:function>

  <!--~
   ! Memoize the backtracking result that was computed at decision point
   ! $dpi for input position $e0. Reconstruct state from the parameters.
   !
   ! @param $state the lexer state to be restored.
   ! @param $update the lexer state containing updates.
   ! @param $dpi the decision point id.
   ! @param $e0 the input position.
   ! @param $v the id of the successful alternative.
   ! @param $lk the new lookahead code.
   ! @return the reconstructed state.
  -->
  <xsl:function name="p:memoize" as="item()+">
    <xsl:param name="state" as="item()+"/>
    <xsl:param name="update" as="item()+"/>
    <xsl:param name="dpi" as="xs:integer"/>
    <xsl:param name="e0" as="xs:integer"/>
    <xsl:param name="v" as="xs:integer"/>
    <xsl:param name="lk" as="xs:integer"/>

    <xsl:variable name="memo" select="$update[$p:memo]"/>
    <xsl:variable name="errors" select="($memo, $update[$p:error])[.]"/>
    <xsl:variable name="memo">
      <xsl:element name="memo">
        <xsl:sequence select="$errors[@e = max($errors/xs:integer(@e))][last()]/@*, $memo/value"/>
        <xsl:element name="value">
          <xsl:attribute name="key" select="$e0 * 4 + $dpi"/>
          <xsl:sequence select="$v"/>
        </xsl:element>
      </xsl:element>
    </xsl:variable>
    <xsl:sequence select="
      $lk,
      subsequence($state, $p:b0, $p:memo - $p:b0),
      $memo/node(),
      subsequence($state, $p:memo + 1)
    "/>
  </xsl:function>

  <!--~
   ! Retrieve memoized backtracking result for decision point $dpi
   ! and input position $state[$p:e0] into $state[$p:lk].
   !
   ! @param $state lexer state, error indicator, and result.
   ! @param $dpi the decision point id.
   ! @return the updated state.
  -->
  <xsl:function name="p:memoized" as="item()+">
    <xsl:param name="state" as="item()+"/>
    <xsl:param name="dpi" as="xs:integer"/>

    <xsl:variable name="value" select="data($state[$p:memo]/value[@key = $state[$p:e0] * 4 + $dpi])"/>
    <xsl:sequence select="
      if ($value) then $value else 0,
      subsequence($state, $p:lk + 1)
    "/>
  </xsl:function>

  <!--~
   ! Parse start symbol Grammar from given string.
   !
   ! @param $s the string to be parsed.
   ! @return the result as generated by parser actions.
  -->
  <xsl:function name="p:parse-Grammar" as="item()*">
    <xsl:param name="s" as="xs:string"/>

    <xsl:variable name="memo">
      <xsl:element name="memo"/>
    </xsl:variable>
    <xsl:variable name="state" select="0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, false(), $memo/node()"/>
    <xsl:variable name="state" select="p:parse-Grammar($s, $state)"/>
    <xsl:variable name="error" select="$state[$p:error]"/>
    <xsl:choose>
      <xsl:when test="$error">
        <xsl:variable name="ERROR">
          <xsl:element name="ERROR">
            <xsl:sequence select="$error/@*, p:error-message($s, $error)"/>
          </xsl:element>
        </xsl:variable>
        <xsl:sequence select="$ERROR/node()"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:sequence select="subsequence($state, $p:result)"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

</xsl:stylesheet>