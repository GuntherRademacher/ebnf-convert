<?xml version="1.0" encoding="UTF-8"?>
<!-- This file was generated on Thu Jan 30, 2025 13:23 (UTC+01) by REx v6.1 which is Copyright (c) 1979-2025 by Gunther Rademacher <grd@gmx.net> -->
<!-- REx command line: -q -tree -a none -xslt -name de/bottlecaps/convert/xq/abnf/abnf.xslt ../../../../../../../main/java/de/bottlecaps/convert/abnf/abnf.ebnf -->

<xsl:stylesheet version="2.0"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="de/bottlecaps/convert/xq/abnf/abnf.xslt">
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
   ! The index of the lexer state for accessing the token code that
   ! was expected when an error was found.
  -->
  <xsl:variable name="p:error" as="xs:integer" select="10"/>

  <!--~
   ! The index of the lexer state that points to the first entry
   ! used for collecting action results.
  -->
  <xsl:variable name="p:result" as="xs:integer" select="11"/>

  <!--~
   ! The codepoint to charclass mapping for 7 bit codepoints.
  -->
  <xsl:variable name="p:MAP0" as="xs:integer+" select="
    32, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 6, 5, 5, 7, 5, 5, 8, 9, 10, 5, 5, 11, 12, 13, 14, 14,
    15, 15, 15, 15, 15, 15, 15, 15, 16, 17, 18, 19, 20, 5, 5, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
    22, 22, 23, 5, 24, 5, 25, 5, 21, 26, 21, 27, 21, 21, 22, 22, 28, 22, 22, 22, 22, 22, 22, 22, 22, 22, 29, 22, 22, 22, 22, 30, 22, 22, 5, 31, 5, 5, 0
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints below the surrogate block.
  -->
  <xsl:variable name="p:MAP1" as="xs:integer+" select="
    54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 90, 136, 167, 199, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104,
    104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 32, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 6, 5, 5, 7, 5, 5, 8, 9, 10, 5, 5, 11, 12, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15,
    15, 16, 17, 18, 19, 20, 5, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 5, 24, 5, 25, 5, 21,
    26, 21, 27, 21, 21, 22, 22, 28, 22, 22, 22, 22, 22, 22, 22, 22, 22, 29, 22, 22, 22, 22, 30, 22, 22, 5, 31, 5, 5, 0
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
    355, 355, 355, 355, 355, 355, 355, 355, 355, 264, 267, 267, 269, 355, 289, 355, 355, 326, 324, 324, 322, 355, 319, 355, 355, 275, 273, 273, 279, 355, 424,
    355, 355, 264, 267, 267, 269, 422, 393, 355, 355, 355, 355, 355, 356, 422, 393, 355, 355, 422, 283, 283, 356, 287, 393, 355, 355, 355, 293, 293, 356, 422,
    393, 355, 355, 355, 297, 297, 356, 422, 393, 355, 355, 355, 355, 301, 356, 422, 393, 355, 311, 355, 312, 305, 356, 422, 393, 355, 355, 355, 355, 343, 356,
    309, 393, 355, 355, 355, 355, 316, 356, 422, 393, 355, 355, 355, 355, 330, 356, 386, 393, 355, 337, 355, 416, 334, 356, 309, 393, 355, 419, 355, 416, 334,
    356, 309, 393, 355, 355, 368, 355, 368, 356, 383, 393, 355, 355, 401, 399, 399, 396, 422, 393, 355, 355, 355, 350, 350, 356, 422, 393, 355, 355, 346, 355,
    346, 356, 422, 393, 354, 355, 355, 355, 355, 356, 422, 360, 355, 340, 367, 364, 364, 356, 309, 393, 355, 355, 367, 364, 364, 356, 309, 393, 355, 355, 355,
    372, 372, 356, 422, 393, 355, 355, 355, 355, 376, 356, 422, 393, 355, 355, 355, 355, 355, 356, 309, 393, 355, 340, 380, 364, 364, 356, 309, 393, 355, 340,
    390, 364, 364, 356, 309, 393, 355, 355, 367, 364, 364, 356, 309, 405, 355, 355, 367, 364, 364, 356, 309, 409, 355, 355, 413, 364, 364, 356, 309, 393, 355,
    355, 355, 355, 428, 356, 422, 393, 355, 355, 355, 355, 432, 355, 355, 355, 355, 81, 81, 0, 81, 81, 81, 81, 0, 20, 83, 83, 83, 83, 0, 83, 27, 0, 0, 19, 22,
    22, 22, 22, 0, 160, 0, 0, 0, 28, 345, 345, 345, 345, 416, 416, 416, 416, 448, 448, 448, 448, 480, 480, 480, 480, 117, 22, 0, 0, 0, 480, 480, 0, 544, 544, 0,
    0, 18, 18, 0, 82, 82, 82, 82, 0, 82, 576, 576, 576, 576, 129, 129, 129, 129, 194, 227, 0, 0, 227, 0, 0, 512, 0, 0, 0, 664, 26, 26, 26, 26, 608, 0, 0, 0, 0,
    20, 0, 256, 0, 28, 117, 117, 117, 117, 0, 0, 0, 23, 704, 704, 704, 704, 736, 736, 736, 736, 117, 0, 768, 0, 22, 29, 0, 22, 0, 672, 117, 0, 800, 0, 26, 0,
    28, 0, 0, 20, 20, 20, 20, 0, 20, 352, 26, 0, 28, 384, 26, 0, 28, 117, 0, 832, 0, 129, 0, 129, 0, 227, 0, 22, 0, 0, 0, 27, 864, 864, 864, 864, 288, 288, 288,
    288
  "/>

  <!--~
   ! The DFA-state to expected-token-set mapping.
  -->
  <xsl:variable name="p:EXPECTED" as="xs:integer+" select="
    8, 32, 64, 16384, 6, 18, 58720256, 1835010, 2104982, 2104990, 2121366, 2121374, 73564062, 73629598, 73662366, 75399070, 2, 2, 2, 2, 4, 16, 262144, 1048576,
    3072, 128, 2, 2, 262144
  "/>

  <!--~
   ! The token-string table.
  -->
  <xsl:variable name="p:TOKEN" as="xs:string+" select="
    '%ERROR',
    'whitespace',
    'rulename',
    'integer',
    'quoted-string',
    'bits',
    'hexdigs',
    'prose-val',
    'EOF',
    &quot;'%'&quot;,
    &quot;'%i'&quot;,
    &quot;'%s'&quot;,
    &quot;'('&quot;,
    &quot;')'&quot;,
    &quot;'*'&quot;,
    &quot;'-'&quot;,
    &quot;'.'&quot;,
    &quot;'/'&quot;,
    &quot;'::='&quot;,
    &quot;'='&quot;,
    &quot;'=/'&quot;,
    &quot;'['&quot;,
    &quot;']'&quot;,
    &quot;'b'&quot;,
    &quot;'d'&quot;,
    &quot;'x'&quot;,
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
    <xsl:sequence select="p:transition($input, $begin, $begin, $begin, $result, $result mod 32, 0)"/>
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
        <xsl:variable name="result" select="$result idiv 32"/>
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
              <xsl:variable name="c1" select="$c0 idiv 32"/>
              <xsl:variable name="c2" select="$c1 idiv 32"/>
              <xsl:sequence select="$p:MAP1[1 + $c0 mod 32 + $p:MAP1[1 + $c1 mod 32 + $p:MAP1[1 + $c2]]]"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="0"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:variable name="current" select="$current + 1"/>
        <xsl:variable name="i0" select="32 * $c1 + $current-state - 1"/>
        <xsl:variable name="i1" select="$i0 idiv 4"/>
        <xsl:variable name="next-state" select="$p:TRANSITION[$i0 mod 4 + $p:TRANSITION[$i1 + 1] + 1]"/>
        <xsl:sequence select="
          if ($next-state &gt; 31) then
            p:transition($input, $begin, $current, $current, $next-state, $next-state mod 32, $current-state)
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
        <xsl:variable name="i0" select=". * 29 + $state - 1"/>
        <xsl:sequence select="p:token((), $p:EXPECTED[$i0 + 1], . * 32 + 1)"/>
      </xsl:for-each>
    </xsl:if>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production hex-val (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-hex-val-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:consume(16, $input, $state)"/>         <!-- '.' -->
        <xsl:variable name="state" select="p:lookahead1(2, $input, $state)"/>       <!-- hexdigs -->
        <xsl:variable name="state" select="p:consume(6, $input, $state)"/>          <!-- hexdigs -->
        <xsl:variable name="state" select="p:lookahead1W(13, $input, $state)"/>     <!-- whitespace | rulename | integer | quoted-string | prose-val | EOF |
                                                                                         '%' | '%i' | '%s' | '(' | ')' | '*' | '.' | '/' | '[' | ']' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 16">                                     <!-- '.' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-hex-val-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse hex-val.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-hex-val" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(25, $input, $state)"/>             <!-- 'x' -->
    <xsl:variable name="state" select="p:lookahead1(2, $input, $state)"/>           <!-- hexdigs -->
    <xsl:variable name="state" select="p:consume(6, $input, $state)"/>              <!-- hexdigs -->
    <xsl:variable name="state" select="p:lookahead1W(14, $input, $state)"/>         <!-- whitespace | rulename | integer | quoted-string | prose-val | EOF |
                                                                                         '%' | '%i' | '%s' | '(' | ')' | '*' | '-' | '.' | '/' | '[' | ']' |
                                                                                         '|' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 15                                            (: '-' :)
                     or $state[$p:l1] = 16">                                        <!-- '.' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 16">                                  <!-- '.' -->
                <xsl:variable name="state" select="p:parse-hex-val-1($input, $state)"/>
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:variable name="state" select="p:consume(15, $input, $state)"/> <!-- '-' -->
                <xsl:variable name="state" select="p:lookahead1(2, $input, $state)"/> <!-- hexdigs -->
                <xsl:variable name="state" select="p:consume(6, $input, $state)"/>  <!-- hexdigs -->
                <xsl:sequence select="$state"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'hex-val', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production dec-val (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-dec-val-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:consume(16, $input, $state)"/>         <!-- '.' -->
        <xsl:variable name="state" select="p:lookahead1(0, $input, $state)"/>       <!-- integer -->
        <xsl:variable name="state" select="p:consume(3, $input, $state)"/>          <!-- integer -->
        <xsl:variable name="state" select="p:lookahead1W(13, $input, $state)"/>     <!-- whitespace | rulename | integer | quoted-string | prose-val | EOF |
                                                                                         '%' | '%i' | '%s' | '(' | ')' | '*' | '.' | '/' | '[' | ']' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 16">                                     <!-- '.' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-dec-val-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse dec-val.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-dec-val" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(24, $input, $state)"/>             <!-- 'd' -->
    <xsl:variable name="state" select="p:lookahead1(0, $input, $state)"/>           <!-- integer -->
    <xsl:variable name="state" select="p:consume(3, $input, $state)"/>              <!-- integer -->
    <xsl:variable name="state" select="p:lookahead1W(14, $input, $state)"/>         <!-- whitespace | rulename | integer | quoted-string | prose-val | EOF |
                                                                                         '%' | '%i' | '%s' | '(' | ')' | '*' | '-' | '.' | '/' | '[' | ']' |
                                                                                         '|' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 15                                            (: '-' :)
                     or $state[$p:l1] = 16">                                        <!-- '.' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 16">                                  <!-- '.' -->
                <xsl:variable name="state" select="p:parse-dec-val-1($input, $state)"/>
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:variable name="state" select="p:consume(15, $input, $state)"/> <!-- '-' -->
                <xsl:variable name="state" select="p:lookahead1(0, $input, $state)"/> <!-- integer -->
                <xsl:variable name="state" select="p:consume(3, $input, $state)"/>  <!-- integer -->
                <xsl:sequence select="$state"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'dec-val', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production bin-val (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-bin-val-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:consume(16, $input, $state)"/>         <!-- '.' -->
        <xsl:variable name="state" select="p:lookahead1(1, $input, $state)"/>       <!-- bits -->
        <xsl:variable name="state" select="p:consume(5, $input, $state)"/>          <!-- bits -->
        <xsl:variable name="state" select="p:lookahead1W(13, $input, $state)"/>     <!-- whitespace | rulename | integer | quoted-string | prose-val | EOF |
                                                                                         '%' | '%i' | '%s' | '(' | ')' | '*' | '.' | '/' | '[' | ']' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 16">                                     <!-- '.' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-bin-val-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse bin-val.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-bin-val" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(23, $input, $state)"/>             <!-- 'b' -->
    <xsl:variable name="state" select="p:lookahead1(1, $input, $state)"/>           <!-- bits -->
    <xsl:variable name="state" select="p:consume(5, $input, $state)"/>              <!-- bits -->
    <xsl:variable name="state" select="p:lookahead1W(14, $input, $state)"/>         <!-- whitespace | rulename | integer | quoted-string | prose-val | EOF |
                                                                                         '%' | '%i' | '%s' | '(' | ')' | '*' | '-' | '.' | '/' | '[' | ']' |
                                                                                         '|' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 15                                            (: '-' :)
                     or $state[$p:l1] = 16">                                        <!-- '.' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 16">                                  <!-- '.' -->
                <xsl:variable name="state" select="p:parse-bin-val-1($input, $state)"/>
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:variable name="state" select="p:consume(15, $input, $state)"/> <!-- '-' -->
                <xsl:variable name="state" select="p:lookahead1(1, $input, $state)"/> <!-- bits -->
                <xsl:variable name="state" select="p:consume(5, $input, $state)"/>  <!-- bits -->
                <xsl:sequence select="$state"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'bin-val', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse num-val.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-num-val" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(9, $input, $state)"/>              <!-- '%' -->
    <xsl:variable name="state" select="p:lookahead1(6, $input, $state)"/>           <!-- 'b' | 'd' | 'x' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 23">                                        <!-- 'b' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-bin-val($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 24">                                        <!-- 'd' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-dec-val($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-hex-val($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'num-val', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse case-sensitive-string.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-case-sensitive-string" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(11, $input, $state)"/>             <!-- '%s' -->
    <xsl:variable name="state" select="p:lookahead1W(5, $input, $state)"/>          <!-- whitespace | quoted-string -->
    <xsl:variable name="state" select="p:consume(4, $input, $state)"/>              <!-- quoted-string -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'case-sensitive-string', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse case-insensitive-string.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-case-insensitive-string" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 10">                                        <!-- '%i' -->
          <xsl:variable name="state" select="p:consume(10, $input, $state)"/>       <!-- '%i' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(5, $input, $state)"/>          <!-- whitespace | quoted-string -->
    <xsl:variable name="state" select="p:consume(4, $input, $state)"/>              <!-- quoted-string -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'case-insensitive-string', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse char-val.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-char-val" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 11">                                        <!-- '%s' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-case-sensitive-string($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-case-insensitive-string($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'char-val', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse option.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-option" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(21, $input, $state)"/>             <!-- '[' -->
    <xsl:variable name="state" select="p:lookahead1W(11, $input, $state)"/>         <!-- whitespace | rulename | integer | quoted-string | prose-val | '%' |
                                                                                         '%i' | '%s' | '(' | '*' | '[' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-alternation($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(22, $input, $state)"/>             <!-- ']' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'option', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse group.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-group" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(12, $input, $state)"/>             <!-- '(' -->
    <xsl:variable name="state" select="p:lookahead1W(11, $input, $state)"/>         <!-- whitespace | rulename | integer | quoted-string | prose-val | '%' |
                                                                                         '%i' | '%s' | '(' | '*' | '[' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-alternation($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(13, $input, $state)"/>             <!-- ')' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'group', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse element.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-element" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:lookahead1W(8, $input, $state)"/>          <!-- whitespace | rulename | quoted-string | prose-val | '%' | '%i' | '%s' |
                                                                                         '(' | '[' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 2">                                         <!-- rulename -->
          <xsl:variable name="state" select="p:consume(2, $input, $state)"/>        <!-- rulename -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 12">                                        <!-- '(' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-group($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 21">                                        <!-- '[' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-option($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 9">                                         <!-- '%' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-num-val($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 7">                                         <!-- prose-val -->
          <xsl:variable name="state" select="p:consume(7, $input, $state)"/>        <!-- prose-val -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-char-val($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'element', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse repeat.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-repeat" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:l1] eq 3">                                        <!-- integer -->
          <xsl:variable name="state" select="p:lookahead2W(10, $input, $state)"/>   <!-- whitespace | rulename | quoted-string | prose-val | '%' | '%i' | '%s' |
                                                                                         '(' | '*' | '[' -->
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
        <xsl:when test="$state[$p:lk] = 14                                            (: '*' :)
                     or $state[$p:lk] = 451">                                       <!-- integer '*' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 3">                                   <!-- integer -->
                <xsl:variable name="state" select="p:consume(3, $input, $state)"/>  <!-- integer -->
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:sequence select="$state"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:variable name="state" select="p:lookahead1(3, $input, $state)"/>     <!-- '*' -->
          <xsl:variable name="state" select="p:consume(14, $input, $state)"/>       <!-- '*' -->
          <xsl:variable name="state" select="p:lookahead1W(9, $input, $state)"/>    <!-- whitespace | rulename | integer | quoted-string | prose-val | '%' |
                                                                                         '%i' | '%s' | '(' | '[' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 3">                                   <!-- integer -->
                <xsl:variable name="state" select="p:consume(3, $input, $state)"/>  <!-- integer -->
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
          <xsl:variable name="state" select="p:consume(3, $input, $state)"/>        <!-- integer -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'repeat', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse repetition.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-repetition" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3                                             (: integer :)
                     or $state[$p:l1] = 14">                                        <!-- '*' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-repeat($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-element($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'repetition', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production concatenation (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-concatenation-1">
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
            p:parse-repetition($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(12, $input, $state)"/>     <!-- whitespace | rulename | integer | quoted-string | prose-val | EOF |
                                                                                         '%' | '%i' | '%s' | '(' | ')' | '*' | '/' | '[' | ']' | '|' -->
        <xsl:variable name="state" as="item()+">
          <xsl:choose>
            <xsl:when test="$state[$p:l1] eq 2">                                    <!-- rulename -->
              <xsl:variable name="state" select="p:lookahead2W(15, $input, $state)"/> <!-- whitespace | rulename | integer | quoted-string | prose-val | EOF |
                                                                                           '%' | '%i' | '%s' | '(' | ')' | '*' | '/' | '::=' | '=' | '=/' |
                                                                                           '[' | ']' | '|' -->
              <xsl:sequence select="$state"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="$state[$p:l1], subsequence($state, $p:lk + 1)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:choose>
          <xsl:when test="$state[$p:lk] = 8                                           (: EOF :)
                       or $state[$p:lk] = 13                                          (: ')' :)
                       or $state[$p:lk] = 17                                          (: '/' :)
                       or $state[$p:lk] = 22                                          (: ']' :)
                       or $state[$p:lk] = 26                                          (: '|' :)
                       or $state[$p:lk] = 578                                         (: rulename '::=' :)
                       or $state[$p:lk] = 610                                         (: rulename '=' :)
                       or $state[$p:lk] = 642">                                     <!-- rulename '=/' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-concatenation-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse concatenation.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-concatenation" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-concatenation-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'concatenation', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production alternation (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-alternation-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 17                                         (: '/' :)
                      and $state[$p:l1] != 26">                                     <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" as="item()+">
              <xsl:choose>
                <xsl:when test="$state[$p:error]">
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:when test="$state[$p:l1] = 17">                                <!-- '/' -->
                  <xsl:variable name="state" select="p:consume(17, $input, $state)"/> <!-- '/' -->
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:variable name="state" select="p:consume(26, $input, $state)"/> <!-- '|' -->
                  <xsl:sequence select="$state"/>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:variable>
            <xsl:variable name="state" select="p:lookahead1W(11, $input, $state)"/> <!-- whitespace | rulename | integer | quoted-string | prose-val | '%' |
                                                                                         '%i' | '%s' | '(' | '*' | '[' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-concatenation($input, $state)
            "/>
            <xsl:sequence select="p:parse-alternation-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse alternation.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-alternation" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-concatenation($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-alternation-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'alternation', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse elements.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-elements" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-alternation($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'elements', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse defined-as.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-defined-as" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 19">                                        <!-- '=' -->
          <xsl:variable name="state" select="p:consume(19, $input, $state)"/>       <!-- '=' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 20">                                        <!-- '=/' -->
          <xsl:variable name="state" select="p:consume(20, $input, $state)"/>       <!-- '=/' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(18, $input, $state)"/>       <!-- '::=' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'defined-as', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse rule.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-rule" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(2, $input, $state)"/>              <!-- rulename -->
    <xsl:variable name="state" select="p:lookahead1W(7, $input, $state)"/>          <!-- whitespace | '::=' | '=' | '=/' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-defined-as($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(11, $input, $state)"/>         <!-- whitespace | rulename | integer | quoted-string | prose-val | '%' |
                                                                                         '%i' | '%s' | '(' | '*' | '[' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-elements($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'rule', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production rulelist (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-rulelist-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(4, $input, $state)"/>      <!-- whitespace | rulename -->
        <xsl:variable name="state" select="p:whitespace($input, $state)"/>
        <xsl:variable name="state" select="
          if ($state[$p:error]) then
            $state
          else
            p:parse-rule($input, $state)
        "/>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 2">                                      <!-- rulename -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-rulelist-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse rulelist.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-rulelist" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-rulelist-1($input, $state)"/>
    <xsl:variable name="state" select="p:consume(8, $input, $state)"/>              <!-- EOF -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'rulelist', $count, $begin, $end)"/>
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
          subsequence($state, $p:l1, 6),
          0, 0, 0,
          subsequence($state, 10),
          $whitespace/node(),
          $node/node()
        "/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="error">
          <xsl:element name="error">
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
      <xsl:when test="$match[1] = 1">                                               <!-- whitespace -->
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
        subsequence($state, $p:l2, 3)
      else
        p:matchW($input, $state[$p:e1], $set)
    "/>
    <xsl:sequence select="
      $match[1] * 32 + $state[$p:l1],
      subsequence($state, $p:b0, 5),
      $match,
      subsequence($state, 10)
    "/>
  </xsl:function>

  <!--~
   ! Lookahead one token on level 1.
   !
   ! @param $set the code of the DFA entry state for the set of valid tokens.
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result stack.
   ! @return the updated state.
  -->
  <xsl:function name="p:lookahead1" as="item()+">
    <xsl:param name="set" as="xs:integer"/>
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:l1] ne 0">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="match" select="
          p:match($input, $state[$p:e0], $set),
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
   ! Parse start symbol rulelist from given string.
   !
   ! @param $s the string to be parsed.
   ! @return the result as generated by parser actions.
  -->
  <xsl:function name="p:parse-rulelist" as="item()*">
    <xsl:param name="s" as="xs:string"/>

    <xsl:variable name="state" select="0, 1, 1, 0, 0, 0, 0, 0, 0, false()"/>
    <xsl:variable name="state" select="p:parse-rulelist($s, $state)"/>
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