<?xml version="1.0" encoding="UTF-8"?>
<!-- This file was generated on Thu Mar 9, 2023 13:03 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net> -->
<!-- REx command line: -q -tree -a none -xslt -name de/bottlecaps/convert/xq/xtext/xtext.xslt ../../../../../../../main/java/de/bottlecaps/convert/xtext/xtext.ebnf -->

<xsl:stylesheet version="2.0"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="de/bottlecaps/convert/xq/xtext/xtext.xslt">
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
   ! The index of the lexer state that points to the first entry
   ! used for collecting action results.
  -->
  <xsl:variable name="p:result" as="xs:integer" select="14"/>

  <!--~
   ! The codepoint to charclass mapping for 7 bit codepoints.
  -->
  <xsl:variable name="p:MAP0" as="xs:integer+" select="
    55, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 6, 6, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
    17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 19, 20, 21, 22, 23, 24, 25, 25, 25, 25, 26, 27, 25, 25, 25, 25, 25, 25, 25, 25, 28, 25, 25, 25, 25, 25, 25, 25, 25,
    25, 25, 25, 29, 30, 31, 32, 25, 6, 33, 34, 35, 36, 37, 38, 39, 40, 41, 25, 25, 42, 43, 44, 45, 46, 25, 47, 48, 49, 50, 25, 51, 25, 25, 25, 52, 53, 54, 6, 6
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints below the surrogate block.
  -->
  <xsl:variable name="p:MAP1" as="xs:integer+" select="
    54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 90, 122, 154, 186, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216,
    216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 55, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 6, 6, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 19, 20, 21, 22, 23, 24, 25,
    25, 25, 25, 26, 27, 25, 25, 25, 25, 25, 25, 25, 25, 28, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 29, 30, 31, 32, 25, 6, 33, 34, 35, 36, 37, 38, 39, 40,
    41, 25, 25, 42, 43, 44, 45, 46, 25, 47, 48, 49, 50, 25, 51, 25, 25, 25, 52, 53, 54, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
    6, 6, 6, 6, 6, 6, 6, 6
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints above the surrogate block.
  -->
  <xsl:variable name="p:MAP2" as="xs:integer+" select="
    57344, 65536, 65533, 1114111, 6, 6
  "/>

  <!--~
   ! The token-set-id to DFA-initial-state mapping.
  -->
  <xsl:variable name="p:INITIAL" as="xs:integer+" select="
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
    42, 43, 44, 45, 46, 47, 48, 49
  "/>

  <!--~
   ! The DFA transition table.
  -->
  <xsl:variable name="p:TRANSITION" as="xs:integer+" select="
    958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 896, 896, 896, 910, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958,
    958, 896, 896, 896, 910, 2893, 2891, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 896, 896, 896, 910, 2997, 1038, 958, 958, 958, 958, 958, 958, 958,
    958, 958, 958, 958, 1226, 1233, 933, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 956, 1862, 975, 990, 917, 1038, 958, 958, 958, 958, 958,
    958, 958, 958, 958, 958, 958, 958, 958, 2706, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 997, 1325, 1013, 1506, 1038, 958, 958, 958,
    958, 958, 958, 958, 958, 958, 958, 1036, 2511, 1054, 1069, 917, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1093, 1363, 1112, 1127, 1506, 1038,
    958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1454, 2213, 2748, 1150, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1552, 2713,
    1173, 940, 2977, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1203, 2042, 1020, 1196, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958,
    1076, 1134, 1077, 1219, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1249, 1264, 1506, 1038, 958, 958, 958, 958, 958, 958, 958,
    958, 958, 958, 2430, 958, 1287, 1318, 1271, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1341, 1341, 1341, 1356, 1506, 1096, 958, 958, 958, 958,
    958, 958, 958, 958, 958, 958, 958, 958, 958, 1379, 1768, 2371, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1943, 1932, 1432, 1447, 1470, 1038, 958,
    958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1500, 1821, 1522, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 2046, 2843, 1545,
    1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1577, 1568, 1593, 1609, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1714,
    1157, 958, 2706, 1666, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 2426, 1180, 1707, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958,
    958, 958, 958, 958, 1730, 2706, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 1768, 2371, 2800, 2793, 1408, 958,
    958, 958, 958, 958, 958, 958, 1416, 1758, 2317, 1784, 1768, 2371, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 1768, 1484,
    2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 2629, 2371, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 958, 959, 958,
    2706, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 2740, 917, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 2885,
    2889, 2889, 1813, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1852, 1837, 1878, 1893, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958,
    958, 958, 1416, 1758, 1797, 2114, 1768, 1917, 2800, 1959, 2003, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 1691, 2371, 2800, 2793, 1408,
    958, 958, 958, 958, 958, 958, 958, 2074, 1758, 1392, 1974, 1768, 2371, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 1768,
    2027, 2062, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 2099, 2175, 2130, 2371, 1302, 2160, 2204, 958, 958, 958, 958, 958, 958, 958, 1416,
    2229, 2273, 2244, 1691, 2371, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 2083, 1758, 2188, 1974, 1768, 2371, 1680, 2793, 1408, 958, 958, 958, 958,
    958, 958, 958, 2395, 2289, 2333, 1974, 1768, 2371, 2453, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 2257, 2304, 2959, 2371, 2800, 2364,
    1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 1768, 1624, 2800, 2793, 2387, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974,
    1650, 2371, 2411, 2446, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 2469, 1742, 2800, 2485, 2501, 958, 958, 958, 958, 958, 958, 958,
    1416, 1758, 1392, 1974, 1768, 2371, 2948, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 1768, 2679, 2800, 2793, 1408, 958, 958,
    958, 958, 958, 958, 958, 2011, 2527, 1392, 2348, 2572, 2588, 2645, 2672, 2695, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 2656, 2371, 1639,
    2793, 2729, 958, 958, 958, 958, 958, 958, 958, 1416, 2764, 2816, 2779, 1691, 2603, 2800, 2556, 2832, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392,
    2542, 1768, 2144, 2618, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1987, 1974, 1768, 2371, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958,
    958, 958, 958, 2859, 2874, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1529, 2923, 2909, 2936, 1506, 1038, 958, 958, 958, 958, 958, 958,
    958, 958, 958, 958, 1901, 958, 2975, 2706, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 2993, 958, 958, 958, 958, 958, 958, 958,
    958, 958, 958, 958, 958, 958, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 77, 590, 54, 55, 1536, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 95, 590, 0, 0, 0, 54, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    7168, 0, 0, 54, 0, 54, 54, 0, 0, 54, 54, 54, 0, 54, 54, 54, 54, 0, 0, 0, 0, 1024, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1792, 1792, 0, 0, 1792, 0, 0, 0,
    0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2816, 2816, 2816, 2874, 0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 0, 0, 0, 55, 0, 55, 55, 0, 0,
    55, 55, 55, 0, 55, 55, 55, 55, 0, 0, 0, 0, 54, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3328, 0, 0, 0, 0, 0, 0, 2048, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 512, 0, 0, 0, 2048, 0, 2048, 2048, 0, 0, 2048, 2048, 2048, 0, 2048, 2048, 2048, 2048, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3328,
    3328, 0, 0, 2304, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5888, 5888, 0, 0, 2560, 0, 77, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    6144, 6144, 6144, 6207, 2816, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 58, 0, 0, 0, 0, 3328, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 1536, 0, 0, 0, 0, 1536, 0, 0, 1536, 0, 1536, 0, 0, 0, 68, 0, 68, 68, 0, 0, 68, 68, 68, 0, 68, 68, 68, 68, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 4096, 77, 590, 0, 0, 0, 3840, 0, 0, 3840, 0, 0, 0, 0, 3840, 0, 3840, 3840, 0, 3916, 0, 0, 0, 0, 820, 10548, 820, 820, 820, 885, 820, 820, 820, 120, 0,
    122, 3840, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1792, 0, 0, 1792, 0, 1792, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 0,
    590, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 2048, 0, 0, 0, 2048, 0, 0, 0, 820, 0, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820,
    820, 820, 820, 820, 820, 820, 820, 820, 820, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 0, 820, 0, 0, 0, 0, 0, 0, 0, 4419, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 67, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2304, 0, 0, 0, 0, 0, 0, 0, 4608, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 590, 0, 0, 0,
    0, 820, 820, 820, 820, 820, 6964, 820, 820, 820, 820, 77, 0, 4864, 0, 0, 0, 0, 4864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 590, 0, 0, 4864, 0, 0, 0, 0,
    54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11264, 0, 0, 0, 0, 0, 5120, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2560, 0, 0, 0, 0, 2560, 0, 0, 0, 0, 5376,
    0, 5376, 0, 0, 0, 0, 0, 0, 0, 5376, 0, 0, 0, 0, 0, 0, 5376, 0, 0, 0, 0, 0, 0, 69, 0, 0, 69, 0, 0, 69, 0, 69, 0, 0, 69, 0, 5445, 69, 0, 0, 0, 0, 54, 55, 0,
    0, 3072, 0, 0, 0, 0, 6400, 0, 0, 0, 0, 869, 820, 820, 820, 820, 820, 820, 820, 820, 820, 77, 0, 0, 0, 0, 882, 820, 820, 820, 820, 820, 820, 820, 820, 0, 0,
    0, 820, 820, 860, 820, 820, 820, 0, 77, 590, 0, 0, 0, 0, 0, 3584, 5632, 0, 0, 0, 0, 0, 0, 0, 77, 590, 0, 0, 0, 0, 820, 820, 883, 820, 820, 820, 820, 820,
    820, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 54, 55, 6144, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5888, 0, 0, 5888, 0, 0, 0, 6656, 0, 0,
    6656, 0, 0, 6656, 6656, 0, 0, 0, 6656, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 874, 820, 820, 820, 77, 0, 0, 0, 820, 0, 0, 0, 820, 820, 820, 0, 820, 820,
    0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 0, 0, 838, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820, 841, 820, 820,
    820, 820, 820, 820, 820, 820, 7424, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4864, 4864, 4864, 0, 4864, 4864, 4864, 4864, 0, 0, 53, 0, 0, 0, 53, 53,
    53, 0, 53, 53, 0, 0, 0, 53, 0, 0, 0, 0, 0, 0, 0, 0, 53, 0, 0, 0, 0, 0, 0, 54, 0, 0, 0, 54, 0, 0, 0, 0, 54, 53, 0, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53,
    53, 53, 53, 53, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11520, 0, 0, 0, 0, 11520, 0, 0, 0, 98, 0, 0, 820, 820, 871, 820, 820, 820, 820, 820, 820,
    820, 77, 0, 0, 0, 0, 4352, 0, 0, 0, 0, 4352, 0, 0, 0, 0, 4352, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4352, 4352, 0, 820, 820, 820, 820, 820, 820, 0, 130, 0, 0, 820,
    820, 902, 820, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820, 820, 820, 820, 820, 843, 820, 820, 820, 820, 0, 0, 0, 820,
    904, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 0, 820, 0, 0, 0, 0, 0, 60, 0, 0, 99, 0, 820, 820, 820, 820, 820, 820, 820, 820, 876, 820, 77, 0, 0, 0, 58, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5120, 0, 0, 0, 112, 0, 820, 820, 820, 820, 820, 820, 820, 887, 820, 0, 0, 0, 0, 0, 56, 0, 0, 820, 0, 0, 0, 0, 0, 0,
    57, 0, 820, 0, 0, 0, 0, 0, 0, 833, 0, 820, 833, 820, 820, 833, 833, 820, 820, 820, 833, 820, 820, 820, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 853, 820,
    0, 853, 820, 857, 0, 0, 0, 820, 859, 820, 820, 820, 820, 0, 77, 590, 0, 0, 0, 0, 820, 870, 820, 872, 820, 820, 820, 820, 820, 820, 77, 0, 0, 8500, 820, 820,
    820, 820, 896, 0, 0, 0, 0, 900, 820, 820, 820, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 84, 820, 820, 0, 820, 820, 820, 820, 839, 839, 820, 820, 820, 839,
    820, 820, 820, 820, 0, 0, 0, 820, 820, 820, 820, 820, 9012, 0, 0, 0, 0, 0, 0, 0, 2304, 0, 0, 0, 0, 2304, 0, 0, 0, 0, 0, 829, 0, 0, 0, 829, 829, 832, 0, 829,
    829, 0, 0, 0, 829, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 840, 840, 820, 820, 820, 840, 820, 820, 820, 820, 832, 0, 829,
    832, 829, 829, 832, 832, 829, 829, 829, 832, 829, 829, 829, 829, 0, 0, 820, 0, 59, 0, 820, 820, 820, 59, 820, 820, 0, 0, 59, 820, 0, 0, 820, 820, 54, 55, 0,
    0, 0, 83, 0, 820, 820, 0, 820, 820, 838, 820, 820, 820, 820, 838, 820, 820, 838, 820, 838, 820, 820, 59, 820, 820, 820, 820, 820, 820, 820, 820, 820, 842,
    820, 820, 820, 820, 0, 0, 820, 820, 54, 55, 0, 82, 0, 0, 0, 820, 854, 0, 855, 0, 820, 820, 893, 820, 820, 820, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820,
    820, 820, 820, 820, 77, 0, 0, 0, 0, 820, 820, 820, 820, 10292, 820, 0, 0, 0, 0, 0, 0, 0, 0, 820, 0, 0, 0, 0, 59, 0, 0, 111, 0, 0, 820, 820, 820, 8244, 884,
    820, 820, 820, 820, 0, 121, 0, 0, 0, 63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3840, 0, 0, 0, 820, 892, 820, 820, 820, 820, 0, 0, 0, 0, 820, 820, 820, 820,
    820, 820, 820, 820, 10804, 0, 0, 0, 856, 820, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 54, 55, 0, 820, 820, 820, 820, 820, 820, 129, 0, 9472, 131,
    820, 901, 820, 820, 9524, 0, 0, 0, 903, 820, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 55, 0, 0, 0, 55, 0, 0, 0, 0, 55, 0, 0, 820, 0, 60, 0, 820, 820, 820,
    60, 820, 820, 0, 0, 60, 820, 0, 0, 820, 820, 54, 55, 81, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820, 820, 0, 0, 0, 0, 820, 820, 820, 9780, 820, 820,
    854, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 54, 55, 97, 0, 0, 0, 820, 820, 820, 820, 873, 820, 820, 820, 820, 820, 77, 0, 0, 0, 100, 820, 820,
    820, 820, 820, 820, 820, 820, 820, 877, 77, 0, 0, 0, 113, 820, 820, 820, 820, 820, 820, 820, 820, 820, 0, 0, 0, 858, 820, 820, 820, 820, 820, 0, 77, 590, 0,
    0, 110, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 820, 820, 0, 0, 0, 820, 820, 820, 7732, 820, 820, 0, 77, 590, 0, 0, 123, 820, 820, 820, 894, 895, 820,
    0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 875, 820, 820, 77, 0, 0, 9216, 0, 820, 820, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 2560, 2560, 2560, 2560, 0, 0, 9984, 820, 820, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 79, 80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2304, 2304,
    2304, 0, 2304, 2304, 2304, 2304, 0, 0, 830, 0, 0, 0, 830, 830, 830, 0, 830, 830, 0, 0, 0, 830, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820,
    820, 820, 820, 820, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 820, 820, 0, 0, 0, 834, 0, 830, 834, 830, 830, 834, 834, 830, 830, 830, 834, 830, 830,
    830, 830, 7936, 0, 0, 820, 820, 905, 8756, 820, 820, 0, 0, 0, 0, 0, 0, 0, 5120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5120, 0, 0, 11008, 0, 0, 11008, 0, 0, 11008, 0,
    11008, 0, 0, 11008, 0, 11008, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7424, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 512, 0, 0, 0, 11264, 0, 0,
    0, 0, 0, 0, 11264, 11264, 11264, 0, 11264, 11264, 11264, 11264, 0, 0, 0, 11264, 0, 0, 0, 0, 0, 0, 11264, 11264, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 820, 820, 820, 820, 820, 820, 886, 820, 820, 0, 0, 0, 820, 820, 820, 820, 861, 862, 0, 77, 590, 0, 0, 0, 11520, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    95, 0, 0, 0, 0, 1280, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 96, 0, 0
  "/>

  <!--~
   ! The DFA-state to expected-token-set mapping.
  -->
  <xsl:variable name="p:EXPECTED" as="xs:integer+" select="
    69, 76, 228, 89, 93, 72, 97, 101, 105, 109, 113, 117, 121, 214, 159, 211, 130, 134, 142, 138, 151, 221, 146, 136, 151, 221, 220, 150, 219, 148, 153, 220,
    217, 220, 124, 152, 157, 126, 166, 194, 169, 197, 172, 176, 179, 201, 182, 152, 185, 191, 205, 153, 85, 152, 161, 225, 208, 232, 161, 225, 237, 233, 242,
    238, 162, 82, 188, 79, 238, 6, 10, 130, 65538, 1310722, 14, 262, 1048578, 268435458, 1073741826, 2, 128, 4, 2, 128, 4, 32, 0, 16, 512, 4198402, 16386,
    65538, 65538, 262146, 268435458, 6, 17827842, 6, 66050, 142, 166, 4198722, 5247042, 590338, 134217870, -2113929210, 268648450, 2105486, -2113929194,
    67133614, 2629774, -2113929210, -1577058298, 2367886, 67396014, 2367950, -2113908730, 75786158, 10758094, 75818926, 29241294, 346978286, 2, 2, 4, 0, 0, 0,
    4096, 16, -2147483644, 4, 131072, 8192, 2097152, 67108868, 4, 4, 2, 2, 8, 8, 536870916, 4, 4, 32768, 4, 67108868, 4, 4, 4, 1073741824, 0, 0, 0, 0, 4, 8,
    4096, 0, 2048, 0, 0, 8, 16, 64, 64, 2048, 2048, 257, 259, 80, 257, 387, 6160, 1281, 387, 257, 1281, 423, 3329, 2305, 3329, 3329, 0, 0, 8, 0, 16, 0, 8, 64,
    1, 256, 0, 80, 2048, 257, 2048, 2048, 80, 951, 2305, 3329, 2305, 3, 0, 384, 0, 128, 0, 4, 4, 16777216, 4, 8, 8, 1073741824, 0, 0, 4, 4, 4, 4, -2147483644,
    64, 1, 256, 2, 4194310, 258, 1050626, 32, 16, 512, 0, 8, 0, 128, 4, 32, 16, 16, 64, 1, 2
  "/>

  <!--~
   ! The token-string table.
  -->
  <xsl:variable name="p:TOKEN" as="xs:string+" select="
    '(0)',
    '_',
    'ID',
    'STRING',
    'EOF',
    &quot;'!'&quot;,
    &quot;'&amp;'&quot;,
    &quot;'('&quot;,
    &quot;')'&quot;,
    &quot;'*'&quot;,
    &quot;'+'&quot;,
    &quot;'+='&quot;,
    &quot;','&quot;,
    &quot;'-&gt;'&quot;,
    &quot;'.'&quot;,
    &quot;'..'&quot;,
    &quot;':'&quot;,
    &quot;'::'&quot;,
    &quot;';'&quot;,
    &quot;'&lt;'&quot;,
    &quot;'='&quot;,
    &quot;'=&gt;'&quot;,
    &quot;'&gt;'&quot;,
    &quot;'?'&quot;,
    &quot;'?='&quot;,
    &quot;'@'&quot;,
    &quot;'EOF'&quot;,
    &quot;'['&quot;,
    &quot;']'&quot;,
    &quot;'as'&quot;,
    &quot;'current'&quot;,
    &quot;'enum'&quot;,
    &quot;'false'&quot;,
    &quot;'fragment'&quot;,
    &quot;'generate'&quot;,
    &quot;'grammar'&quot;,
    &quot;'hidden'&quot;,
    &quot;'import'&quot;,
    &quot;'returns'&quot;,
    &quot;'terminal'&quot;,
    &quot;'true'&quot;,
    &quot;'with'&quot;,
    &quot;'{'&quot;,
    &quot;'|'&quot;,
    &quot;'}'&quot;
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
    <xsl:sequence select="p:transition($input, $begin, $begin, $begin, $result, $result mod 256, 0)"/>
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
        <xsl:variable name="result" select="$result idiv 256"/>
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
              <xsl:sequence select="p:map2($c0, 1, 2)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:variable name="current" select="$current + 1"/>
        <xsl:variable name="i0" select="256 * $c1 + $current-state - 1"/>
        <xsl:variable name="i1" select="$i0 idiv 16"/>
        <xsl:variable name="next-state" select="$p:TRANSITION[$i0 mod 16 + $p:TRANSITION[$i1 + 1] + 1]"/>
        <xsl:sequence select="
          if ($next-state &gt; 255) then
            p:transition($input, $begin, $current, $current, $next-state, $next-state mod 256, $current-state)
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
      <xsl:for-each select="0 to 1">
        <xsl:variable name="i0" select=". * 137 + $state - 1"/>
        <xsl:variable name="i1" select="$i0 idiv 4"/>
        <xsl:sequence select="p:token((), $p:EXPECTED[$i0 mod 4 + $p:EXPECTED[$i1 + 1] + 1], . * 32 + 1)"/>
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
   ! Parse EnumLiteralDeclaration.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-EnumLiteralDeclaration" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-ValidID($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(21, $input, $state)"/>         <!-- _ | ';' | '=' | '|' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 20">                                        <!-- '=' -->
          <xsl:variable name="state" select="p:consume(20, $input, $state)"/>       <!-- '=' -->
          <xsl:variable name="state" select="p:lookahead1W(1, $input, $state)"/>    <!-- _ | STRING -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Keyword($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'EnumLiteralDeclaration', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production EnumLiterals (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-EnumLiterals-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(16, $input, $state)"/>     <!-- _ | ';' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 43">                                     <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(43, $input, $state)"/>     <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/> <!-- _ | ID | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-EnumLiteralDeclaration($input, $state)
            "/>
            <xsl:sequence select="p:parse-EnumLiterals-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse EnumLiterals.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-EnumLiterals" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-EnumLiteralDeclaration($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-EnumLiterals-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'EnumLiterals', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse EnumRule.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-EnumRule" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(31, $input, $state)"/>             <!-- 'enum' -->
    <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>         <!-- _ | ID | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-ValidID($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>         <!-- _ | ':' | 'returns' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 38">                                        <!-- 'returns' -->
          <xsl:variable name="state" select="p:consume(38, $input, $state)"/>       <!-- 'returns' -->
          <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/>    <!-- _ | ID -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-TypeRef($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(3, $input, $state)"/>          <!-- _ | ':' -->
    <xsl:variable name="state" select="p:consume(16, $input, $state)"/>             <!-- ':' -->
    <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>         <!-- _ | ID | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-EnumLiterals($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(18, $input, $state)"/>             <!-- ';' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'EnumRule', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse UntilToken.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-UntilToken" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(13, $input, $state)"/>             <!-- '->' -->
    <xsl:variable name="state" select="p:lookahead1W(36, $input, $state)"/>         <!-- _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-TerminalTokenElement($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'UntilToken', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse NegatedToken.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-NegatedToken" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(5, $input, $state)"/>              <!-- '!' -->
    <xsl:variable name="state" select="p:lookahead1W(36, $input, $state)"/>         <!-- _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-TerminalTokenElement($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'NegatedToken', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse AbstractNegatedToken.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-AbstractNegatedToken" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 5">                                         <!-- '!' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-NegatedToken($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-UntilToken($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'AbstractNegatedToken', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse ParenthesizedTerminalElement.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ParenthesizedTerminalElement" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(7, $input, $state)"/>              <!-- '(' -->
    <xsl:variable name="state" select="p:lookahead1W(36, $input, $state)"/>         <!-- _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-TerminalAlternatives($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(8, $input, $state)"/>              <!-- ')' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'ParenthesizedTerminalElement', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse TerminalRuleCall.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-TerminalRuleCall" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-RuleID($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'TerminalRuleCall', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse CharacterRange.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-CharacterRange" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Keyword($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(46, $input, $state)"/>         <!-- _ | ID | STRING | '!' | '(' | ')' | '*' | '+' | '->' | '.' | '..' |
                                                                                         ';' | '?' | 'EOF' | 'false' | 'true' | '|' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 15">                                        <!-- '..' -->
          <xsl:variable name="state" select="p:consume(15, $input, $state)"/>       <!-- '..' -->
          <xsl:variable name="state" select="p:lookahead1W(1, $input, $state)"/>    <!-- _ | STRING -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Keyword($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'CharacterRange', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse TerminalTokenElement.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-TerminalTokenElement" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- STRING -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-CharacterRange($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 7">                                         <!-- '(' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ParenthesizedTerminalElement($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 5                                             (: '!' :)
                     or $state[$p:l1] = 13">                                        <!-- '->' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-AbstractNegatedToken($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 14">                                        <!-- '.' -->
          <xsl:variable name="state" select="p:consume(14, $input, $state)"/>       <!-- '.' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 26">                                        <!-- 'EOF' -->
          <xsl:variable name="state" select="p:consume(26, $input, $state)"/>       <!-- 'EOF' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-TerminalRuleCall($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'TerminalTokenElement', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse TerminalToken.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-TerminalToken" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-TerminalTokenElement($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(44, $input, $state)"/>         <!-- _ | ID | STRING | '!' | '(' | ')' | '*' | '+' | '->' | '.' | ';' |
                                                                                         '?' | 'EOF' | 'false' | 'true' | '|' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 9                                             (: '*' :)
                     or $state[$p:l1] = 10                                            (: '+' :)
                     or $state[$p:l1] = 23">                                        <!-- '?' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 23">                                  <!-- '?' -->
                <xsl:variable name="state" select="p:consume(23, $input, $state)"/> <!-- '?' -->
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 9">                                   <!-- '*' -->
                <xsl:variable name="state" select="p:consume(9, $input, $state)"/>  <!-- '*' -->
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:variable name="state" select="p:consume(10, $input, $state)"/> <!-- '+' -->
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
    <xsl:sequence select="p:reduce($state, 'TerminalToken', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production TerminalGroup (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-TerminalGroup-1">
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
            p:parse-TerminalToken($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(41, $input, $state)"/>     <!-- _ | ID | STRING | '!' | '(' | ')' | '->' | '.' | ';' | 'EOF' |
                                                                                         'false' | 'true' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 8                                           (: ')' :)
                       or $state[$p:l1] = 18                                          (: ';' :)
                       or $state[$p:l1] = 43">                                      <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-TerminalGroup-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse TerminalGroup.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-TerminalGroup" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-TerminalGroup-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'TerminalGroup', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production TerminalAlternatives (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-TerminalAlternatives-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 43">                                     <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(43, $input, $state)"/>     <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(36, $input, $state)"/> <!-- _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-TerminalGroup($input, $state)
            "/>
            <xsl:sequence select="p:parse-TerminalAlternatives-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse TerminalAlternatives.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-TerminalAlternatives" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-TerminalGroup($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-TerminalAlternatives-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'TerminalAlternatives', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse TerminalRule.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-TerminalRule" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(39, $input, $state)"/>             <!-- 'terminal' -->
    <xsl:variable name="state" select="p:lookahead1W(24, $input, $state)"/>         <!-- _ | ID | 'false' | 'fragment' | 'true' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 33">                                        <!-- 'fragment' -->
          <xsl:variable name="state" select="p:consume(33, $input, $state)"/>       <!-- 'fragment' -->
          <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>   <!-- _ | ID | 'false' | 'true' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ValidID($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ValidID($input, $state)
          "/>
          <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>   <!-- _ | ':' | 'returns' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 38">                                  <!-- 'returns' -->
                <xsl:variable name="state" select="p:consume(38, $input, $state)"/> <!-- 'returns' -->
                <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/> <!-- _ | ID -->
                <xsl:variable name="state" select="p:whitespace($input, $state)"/>
                <xsl:variable name="state" select="
                  if ($state[$p:error]) then
                    $state
                  else
                    p:parse-TypeRef($input, $state)
                "/>
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:sequence select="$state"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(3, $input, $state)"/>          <!-- _ | ':' -->
    <xsl:variable name="state" select="p:consume(16, $input, $state)"/>             <!-- ':' -->
    <xsl:variable name="state" select="p:lookahead1W(36, $input, $state)"/>         <!-- _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-TerminalAlternatives($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(18, $input, $state)"/>             <!-- ';' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'TerminalRule', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Action.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Action" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(42, $input, $state)"/>             <!-- '{' -->
    <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/>          <!-- _ | ID -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-TypeRef($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(13, $input, $state)"/>         <!-- _ | '.' | '}' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 14">                                        <!-- '.' -->
          <xsl:variable name="state" select="p:consume(14, $input, $state)"/>       <!-- '.' -->
          <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>   <!-- _ | ID | 'false' | 'true' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ValidID($input, $state)
          "/>
          <xsl:variable name="state" select="p:lookahead1W(11, $input, $state)"/>   <!-- _ | '+=' | '=' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 20">                                  <!-- '=' -->
                <xsl:variable name="state" select="p:consume(20, $input, $state)"/> <!-- '=' -->
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:variable name="state" select="p:consume(11, $input, $state)"/> <!-- '+=' -->
                <xsl:sequence select="$state"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>    <!-- _ | 'current' -->
          <xsl:variable name="state" select="p:consume(30, $input, $state)"/>       <!-- 'current' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(8, $input, $state)"/>          <!-- _ | '}' -->
    <xsl:variable name="state" select="p:consume(44, $input, $state)"/>             <!-- '}' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Action', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse PredicatedGroup.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-PredicatedGroup" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 21">                                        <!-- '=>' -->
          <xsl:variable name="state" select="p:consume(21, $input, $state)"/>       <!-- '=>' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(13, $input, $state)"/>       <!-- '->' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(2, $input, $state)"/>          <!-- _ | '(' -->
    <xsl:variable name="state" select="p:consume(7, $input, $state)"/>              <!-- '(' -->
    <xsl:variable name="state" select="p:lookahead1W(37, $input, $state)"/>         <!-- _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' | 'true' | '{' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Alternatives($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(8, $input, $state)"/>              <!-- ')' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'PredicatedGroup', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production PredicatedRuleCall (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-PredicatedRuleCall-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 12">                                     <!-- ',' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(12, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(27, $input, $state)"/> <!-- _ | ID | '!' | '(' | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-NamedArgument($input, $state)
            "/>
            <xsl:sequence select="p:parse-PredicatedRuleCall-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse PredicatedRuleCall.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-PredicatedRuleCall" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 21">                                        <!-- '=>' -->
          <xsl:variable name="state" select="p:consume(21, $input, $state)"/>       <!-- '=>' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(13, $input, $state)"/>       <!-- '->' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>         <!-- _ | ID | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-RuleID($input, $state)
    "/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 19">                                        <!-- '<' -->
          <xsl:variable name="state" select="p:consume(19, $input, $state)"/>       <!-- '<' -->
          <xsl:variable name="state" select="p:lookahead1W(27, $input, $state)"/>   <!-- _ | ID | '!' | '(' | 'false' | 'true' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-NamedArgument($input, $state)
          "/>
          <xsl:variable name="state" select="p:parse-PredicatedRuleCall-1($input, $state)"/>
          <xsl:variable name="state" select="p:consume(22, $input, $state)"/>       <!-- '>' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'PredicatedRuleCall', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse PredicatedKeyword.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-PredicatedKeyword" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 21">                                        <!-- '=>' -->
          <xsl:variable name="state" select="p:consume(21, $input, $state)"/>       <!-- '=>' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(13, $input, $state)"/>       <!-- '->' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(1, $input, $state)"/>          <!-- _ | STRING -->
    <xsl:variable name="state" select="p:consume(3, $input, $state)"/>              <!-- STRING -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'PredicatedKeyword', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse ParenthesizedElement.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ParenthesizedElement" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(7, $input, $state)"/>              <!-- '(' -->
    <xsl:variable name="state" select="p:lookahead1W(37, $input, $state)"/>         <!-- _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' | 'true' | '{' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Alternatives($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(8, $input, $state)"/>              <!-- ')' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'ParenthesizedElement', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse AbstractTerminal.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-AbstractTerminal" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:l1] = (13,                                          (: '->' :)
                                         21)">                                      <!-- '=>' -->
          <xsl:variable name="state" select="p:lookahead2W(26, $input, $state)"/>   <!-- _ | ID | STRING | '(' | 'false' | 'true' -->
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
        <xsl:when test="$state[$p:lk] = 3">                                         <!-- STRING -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Keyword($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:lk] = 2                                             (: ID :)
                     or $state[$p:lk] = 32                                            (: 'false' :)
                     or $state[$p:lk] = 40">                                        <!-- 'true' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-RuleCall($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:lk] = 7">                                         <!-- '(' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ParenthesizedElement($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:lk] = 205                                           (: '->' STRING :)
                     or $state[$p:lk] = 213">                                       <!-- '=>' STRING -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-PredicatedKeyword($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:lk] = 461                                           (: '->' '(' :)
                     or $state[$p:lk] = 469">                                       <!-- '=>' '(' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-PredicatedGroup($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-PredicatedRuleCall($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'AbstractTerminal', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse CrossReferenceableTerminal.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-CrossReferenceableTerminal" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- STRING -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Keyword($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-RuleCall($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'CrossReferenceableTerminal', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse CrossReference.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-CrossReference" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(27, $input, $state)"/>             <!-- '[' -->
    <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/>          <!-- _ | ID -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-TypeRef($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(17, $input, $state)"/>         <!-- _ | ']' | '|' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 43">                                        <!-- '|' -->
          <xsl:variable name="state" select="p:consume(43, $input, $state)"/>       <!-- '|' -->
          <xsl:variable name="state" select="p:lookahead1W(22, $input, $state)"/>   <!-- _ | ID | STRING | 'false' | 'true' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-CrossReferenceableTerminal($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(5, $input, $state)"/>          <!-- _ | ']' -->
    <xsl:variable name="state" select="p:consume(28, $input, $state)"/>             <!-- ']' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'CrossReference', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production AssignableAlternatives (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-AssignableAlternatives-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(10, $input, $state)"/>     <!-- _ | ')' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 43">                                     <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(43, $input, $state)"/>     <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(31, $input, $state)"/> <!-- _ | ID | STRING | '(' | '[' | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-AssignableTerminal($input, $state)
            "/>
            <xsl:sequence select="p:parse-AssignableAlternatives-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse AssignableAlternatives.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-AssignableAlternatives" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-AssignableTerminal($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-AssignableAlternatives-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'AssignableAlternatives', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse ParenthesizedAssignableElement.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ParenthesizedAssignableElement" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(7, $input, $state)"/>              <!-- '(' -->
    <xsl:variable name="state" select="p:lookahead1W(31, $input, $state)"/>         <!-- _ | ID | STRING | '(' | '[' | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-AssignableAlternatives($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(8, $input, $state)"/>              <!-- ')' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'ParenthesizedAssignableElement', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse LiteralCondition.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-LiteralCondition" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 40">                                        <!-- 'true' -->
          <xsl:variable name="state" select="p:consume(40, $input, $state)"/>       <!-- 'true' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(32, $input, $state)"/>       <!-- 'false' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'LiteralCondition', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse ParenthesizedCondition.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ParenthesizedCondition" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(7, $input, $state)"/>              <!-- '(' -->
    <xsl:variable name="state" select="p:lookahead1W(27, $input, $state)"/>         <!-- _ | ID | '!' | '(' | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Disjunction($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(8, $input, $state)"/>              <!-- ')' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'ParenthesizedCondition', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse ParameterReference.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ParameterReference" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(2, $input, $state)"/>              <!-- ID -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'ParameterReference', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Atom.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Atom" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 2">                                         <!-- ID -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ParameterReference($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 7">                                         <!-- '(' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ParenthesizedCondition($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-LiteralCondition($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Atom', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Negation (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Negation-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(27, $input, $state)"/>     <!-- _ | ID | '!' | '(' | 'false' | 'true' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 5">                                      <!-- '!' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(5, $input, $state)"/>      <!-- '!' -->
            <xsl:sequence select="p:parse-Negation-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Negation.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Negation" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-Negation-1($input, $state)"/>
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Atom($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Negation', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Conjunction (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Conjunction-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(28, $input, $state)"/>     <!-- _ | '&' | ')' | ',' | '>' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 6">                                      <!-- '&' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(6, $input, $state)"/>      <!-- '&' -->
            <xsl:variable name="state" select="p:lookahead1W(27, $input, $state)"/> <!-- _ | ID | '!' | '(' | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Negation($input, $state)
            "/>
            <xsl:sequence select="p:parse-Conjunction-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Conjunction.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Conjunction" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Negation($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-Conjunction-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Conjunction', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Disjunction (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Disjunction-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 43">                                     <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(43, $input, $state)"/>     <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(27, $input, $state)"/> <!-- _ | ID | '!' | '(' | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Conjunction($input, $state)
            "/>
            <xsl:sequence select="p:parse-Disjunction-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Disjunction.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Disjunction" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Conjunction($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-Disjunction-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Disjunction', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse NamedArgument.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-NamedArgument" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:l1] eq 2">                                        <!-- ID -->
          <xsl:variable name="state" select="p:lookahead2W(29, $input, $state)"/>   <!-- _ | '&' | ',' | '=' | '>' | '|' -->
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
        <xsl:when test="$state[$p:lk] = 1282">                                      <!-- ID '=' -->
          <xsl:variable name="state" select="p:consume(2, $input, $state)"/>        <!-- ID -->
          <xsl:variable name="state" select="p:lookahead1W(4, $input, $state)"/>    <!-- _ | '=' -->
          <xsl:variable name="state" select="p:consume(20, $input, $state)"/>       <!-- '=' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(27, $input, $state)"/>         <!-- _ | ID | '!' | '(' | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Disjunction($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'NamedArgument', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production RuleCall (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-RuleCall-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 12">                                     <!-- ',' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(12, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(27, $input, $state)"/> <!-- _ | ID | '!' | '(' | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-NamedArgument($input, $state)
            "/>
            <xsl:sequence select="p:parse-RuleCall-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse RuleCall.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-RuleCall" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-RuleID($input, $state)
    "/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 19">                                        <!-- '<' -->
          <xsl:variable name="state" select="p:consume(19, $input, $state)"/>       <!-- '<' -->
          <xsl:variable name="state" select="p:lookahead1W(27, $input, $state)"/>   <!-- _ | ID | '!' | '(' | 'false' | 'true' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-NamedArgument($input, $state)
          "/>
          <xsl:variable name="state" select="p:parse-RuleCall-1($input, $state)"/>
          <xsl:variable name="state" select="p:consume(22, $input, $state)"/>       <!-- '>' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'RuleCall', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Keyword.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Keyword" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(3, $input, $state)"/>              <!-- STRING -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Keyword', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse AssignableTerminal.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-AssignableTerminal" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- STRING -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Keyword($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 7">                                         <!-- '(' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ParenthesizedAssignableElement($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 27">                                        <!-- '[' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-CrossReference($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-RuleCall($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'AssignableTerminal', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Assignment.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Assignment" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 13                                            (: '->' :)
                     or $state[$p:l1] = 21">                                        <!-- '=>' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 21">                                  <!-- '=>' -->
                <xsl:variable name="state" select="p:consume(21, $input, $state)"/> <!-- '=>' -->
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:variable name="state" select="p:consume(13, $input, $state)"/> <!-- '->' -->
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
    <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>         <!-- _ | ID | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-ValidID($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(19, $input, $state)"/>         <!-- _ | '+=' | '=' | '?=' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 11">                                        <!-- '+=' -->
          <xsl:variable name="state" select="p:consume(11, $input, $state)"/>       <!-- '+=' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 20">                                        <!-- '=' -->
          <xsl:variable name="state" select="p:consume(20, $input, $state)"/>       <!-- '=' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(24, $input, $state)"/>       <!-- '?=' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(31, $input, $state)"/>         <!-- _ | ID | STRING | '(' | '[' | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-AssignableTerminal($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Assignment', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse AbstractTokenWithCardinality.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-AbstractTokenWithCardinality" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:l1] = (13,                                          (: '->' :)
                                         21)">                                      <!-- '=>' -->
          <xsl:variable name="state" select="p:lookahead2W(26, $input, $state)"/>   <!-- _ | ID | STRING | '(' | 'false' | 'true' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:lk] = (141,                                   (: '->' ID :)
                                               2061,                                  (: '->' 'false' :)
                                               2573,                                  (: '->' 'true' :)
                                               149,                                   (: '=>' ID :)
                                               2069,                                  (: '=>' 'false' :)
                                               2581)">                              <!-- '=>' 'true' -->
                <xsl:variable name="state" select="p:lookahead3W(47, $input, $state)"/> <!-- _ | ID | STRING | '&' | '(' | ')' | '*' | '+' | '+=' | '->' |
                                                                                             '::' | ';' | '<' | '=' | '=>' | '?' | '?=' | 'false' | 'true' |
                                                                                             '{' | '|' -->
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:sequence select="$state"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = (2,                                           (: ID :)
                                         32,                                          (: 'false' :)
                                         40)">                                      <!-- 'true' -->
          <xsl:variable name="state" select="p:lookahead2W(47, $input, $state)"/>   <!-- _ | ID | STRING | '&' | '(' | ')' | '*' | '+' | '+=' | '->' | '::' |
                                                                                         ';' | '<' | '=' | '=>' | '?' | '?=' | 'false' | 'true' | '{' | '|' -->
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
        <xsl:when test="$state[$p:lk] = 706                                           (: ID '+=' :)
                     or $state[$p:lk] = 736                                           (: 'false' '+=' :)
                     or $state[$p:lk] = 744                                           (: 'true' '+=' :)
                     or $state[$p:lk] = 1282                                          (: ID '=' :)
                     or $state[$p:lk] = 1312                                          (: 'false' '=' :)
                     or $state[$p:lk] = 1320                                          (: 'true' '=' :)
                     or $state[$p:lk] = 1538                                          (: ID '?=' :)
                     or $state[$p:lk] = 1568                                          (: 'false' '?=' :)
                     or $state[$p:lk] = 1576                                          (: 'true' '?=' :)
                     or $state[$p:lk] = 45197                                         (: '->' ID '+=' :)
                     or $state[$p:lk] = 45205                                         (: '=>' ID '+=' :)
                     or $state[$p:lk] = 47117                                         (: '->' 'false' '+=' :)
                     or $state[$p:lk] = 47125                                         (: '=>' 'false' '+=' :)
                     or $state[$p:lk] = 47629                                         (: '->' 'true' '+=' :)
                     or $state[$p:lk] = 47637                                         (: '=>' 'true' '+=' :)
                     or $state[$p:lk] = 82061                                         (: '->' ID '=' :)
                     or $state[$p:lk] = 82069                                         (: '=>' ID '=' :)
                     or $state[$p:lk] = 83981                                         (: '->' 'false' '=' :)
                     or $state[$p:lk] = 83989                                         (: '=>' 'false' '=' :)
                     or $state[$p:lk] = 84493                                         (: '->' 'true' '=' :)
                     or $state[$p:lk] = 84501                                         (: '=>' 'true' '=' :)
                     or $state[$p:lk] = 98445                                         (: '->' ID '?=' :)
                     or $state[$p:lk] = 98453                                         (: '=>' ID '?=' :)
                     or $state[$p:lk] = 100365                                        (: '->' 'false' '?=' :)
                     or $state[$p:lk] = 100373                                        (: '=>' 'false' '?=' :)
                     or $state[$p:lk] = 100877                                        (: '->' 'true' '?=' :)
                     or $state[$p:lk] = 100885">                                    <!-- '=>' 'true' '?=' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Assignment($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-AbstractTerminal($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(45, $input, $state)"/>         <!-- _ | ID | STRING | '&' | '(' | ')' | '*' | '+' | '->' | ';' | '=>' |
                                                                                         '?' | 'false' | 'true' | '{' | '|' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 9                                             (: '*' :)
                     or $state[$p:l1] = 10                                            (: '+' :)
                     or $state[$p:l1] = 23">                                        <!-- '?' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 23">                                  <!-- '?' -->
                <xsl:variable name="state" select="p:consume(23, $input, $state)"/> <!-- '?' -->
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 9">                                   <!-- '*' -->
                <xsl:variable name="state" select="p:consume(9, $input, $state)"/>  <!-- '*' -->
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:variable name="state" select="p:consume(10, $input, $state)"/> <!-- '+' -->
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
    <xsl:sequence select="p:reduce($state, 'AbstractTokenWithCardinality', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse AbstractToken.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-AbstractToken" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 42">                                        <!-- '{' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Action($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-AbstractTokenWithCardinality($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'AbstractToken', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Group (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Group-1">
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
            p:parse-AbstractToken($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(42, $input, $state)"/>     <!-- _ | ID | STRING | '&' | '(' | ')' | '->' | ';' | '=>' | 'false' |
                                                                                         'true' | '{' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 6                                           (: '&amp;' :)
                       or $state[$p:l1] = 8                                           (: ')' :)
                       or $state[$p:l1] = 18                                          (: ';' :)
                       or $state[$p:l1] = 43">                                      <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-Group-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Group.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Group" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-Group-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Group', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production UnorderedGroup (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-UnorderedGroup-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 6">                                      <!-- '&' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(6, $input, $state)"/>      <!-- '&' -->
            <xsl:variable name="state" select="p:lookahead1W(34, $input, $state)"/> <!-- _ | ID | STRING | '(' | '->' | '=>' | 'false' | 'true' | '{' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Group($input, $state)
            "/>
            <xsl:sequence select="p:parse-UnorderedGroup-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse UnorderedGroup.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-UnorderedGroup" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Group($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-UnorderedGroup-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'UnorderedGroup', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production ConditionalBranch (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ConditionalBranch-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(34, $input, $state)"/>     <!-- _ | ID | STRING | '(' | '->' | '=>' | 'false' | 'true' | '{' -->
        <xsl:variable name="state" select="p:whitespace($input, $state)"/>
        <xsl:variable name="state" select="
          if ($state[$p:error]) then
            $state
          else
            p:parse-AbstractToken($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(40, $input, $state)"/>     <!-- _ | ID | STRING | '(' | ')' | '->' | ';' | '=>' | 'false' | 'true' |
                                                                                         '{' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 8                                           (: ')' :)
                       or $state[$p:l1] = 18                                          (: ';' :)
                       or $state[$p:l1] = 43">                                      <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-ConditionalBranch-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse ConditionalBranch.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ConditionalBranch" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 19">                                        <!-- '<' -->
          <xsl:variable name="state" select="p:consume(19, $input, $state)"/>       <!-- '<' -->
          <xsl:variable name="state" select="p:lookahead1W(27, $input, $state)"/>   <!-- _ | ID | '!' | '(' | 'false' | 'true' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Disjunction($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(22, $input, $state)"/>       <!-- '>' -->
          <xsl:variable name="state" select="p:parse-ConditionalBranch-1($input, $state)"/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-UnorderedGroup($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'ConditionalBranch', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Alternatives (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Alternatives-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 43">                                     <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(43, $input, $state)"/>     <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(37, $input, $state)"/> <!-- _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' | 'true' | '{' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-ConditionalBranch($input, $state)
            "/>
            <xsl:sequence select="p:parse-Alternatives-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Alternatives.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Alternatives" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-ConditionalBranch($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-Alternatives-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Alternatives', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse TypeRef.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-TypeRef" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(2, $input, $state)"/>              <!-- ID -->
    <xsl:variable name="state" select="p:lookahead1W(33, $input, $state)"/>         <!-- _ | '.' | ':' | '::' | ']' | 'hidden' | '|' | '}' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 17">                                        <!-- '::' -->
          <xsl:variable name="state" select="p:consume(17, $input, $state)"/>       <!-- '::' -->
          <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/>    <!-- _ | ID -->
          <xsl:variable name="state" select="p:consume(2, $input, $state)"/>        <!-- ID -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'TypeRef', $count, $begin, $end)"/>
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
    <xsl:variable name="state" select="p:consume(2, $input, $state)"/>              <!-- ID -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Parameter', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production RuleNameAndParams (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-RuleNameAndParams-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(12, $input, $state)"/>     <!-- _ | ',' | '>' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 12">                                     <!-- ',' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(12, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/>  <!-- _ | ID -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Parameter($input, $state)
            "/>
            <xsl:sequence select="p:parse-RuleNameAndParams-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse RuleNameAndParams.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-RuleNameAndParams" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-ValidID($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(30, $input, $state)"/>         <!-- _ | '*' | ':' | '<' | 'hidden' | 'returns' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 19">                                        <!-- '<' -->
          <xsl:variable name="state" select="p:consume(19, $input, $state)"/>       <!-- '<' -->
          <xsl:variable name="state" select="p:lookahead1W(9, $input, $state)"/>    <!-- _ | ID | '>' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 2">                                   <!-- ID -->
                <xsl:variable name="state" select="p:whitespace($input, $state)"/>
                <xsl:variable name="state" select="
                  if ($state[$p:error]) then
                    $state
                  else
                    p:parse-Parameter($input, $state)
                "/>
                <xsl:variable name="state" select="p:parse-RuleNameAndParams-1($input, $state)"/>
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:sequence select="$state"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:variable name="state" select="p:consume(22, $input, $state)"/>       <!-- '>' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'RuleNameAndParams', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production ParserRule (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ParserRule-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 12">                                     <!-- ',' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(12, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/> <!-- _ | ID | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-RuleID($input, $state)
            "/>
            <xsl:sequence select="p:parse-ParserRule-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse ParserRule.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ParserRule" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 33">                                        <!-- 'fragment' -->
          <xsl:variable name="state" select="p:consume(33, $input, $state)"/>       <!-- 'fragment' -->
          <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>   <!-- _ | ID | 'false' | 'true' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-RuleNameAndParams($input, $state)
          "/>
          <xsl:variable name="state" select="p:lookahead1W(25, $input, $state)"/>   <!-- _ | '*' | ':' | 'hidden' | 'returns' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 9                                       (: '*' :)
                           or $state[$p:l1] = 38">                                  <!-- 'returns' -->
                <xsl:variable name="state" as="item()+">
                  <xsl:choose>
                    <xsl:when test="$state[$p:error]">
                      <xsl:sequence select="$state"/>
                    </xsl:when>
                    <xsl:when test="$state[$p:l1] = 9">                             <!-- '*' -->
                      <xsl:variable name="state" select="p:consume(9, $input, $state)"/> <!-- '*' -->
                      <xsl:sequence select="$state"/>
                    </xsl:when>
                    <xsl:otherwise>
                      <xsl:variable name="state" select="p:consume(38, $input, $state)"/> <!-- 'returns' -->
                      <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/> <!-- _ | ID -->
                      <xsl:variable name="state" select="p:whitespace($input, $state)"/>
                      <xsl:variable name="state" select="
                        if ($state[$p:error]) then
                          $state
                        else
                          p:parse-TypeRef($input, $state)
                      "/>
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
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-RuleNameAndParams($input, $state)
          "/>
          <xsl:variable name="state" select="p:lookahead1W(20, $input, $state)"/>   <!-- _ | ':' | 'hidden' | 'returns' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 38">                                  <!-- 'returns' -->
                <xsl:variable name="state" select="p:consume(38, $input, $state)"/> <!-- 'returns' -->
                <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/> <!-- _ | ID -->
                <xsl:variable name="state" select="p:whitespace($input, $state)"/>
                <xsl:variable name="state" select="
                  if ($state[$p:error]) then
                    $state
                  else
                    p:parse-TypeRef($input, $state)
                "/>
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:sequence select="$state"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(14, $input, $state)"/>         <!-- _ | ':' | 'hidden' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 36">                                        <!-- 'hidden' -->
          <xsl:variable name="state" select="p:consume(36, $input, $state)"/>       <!-- 'hidden' -->
          <xsl:variable name="state" select="p:lookahead1W(2, $input, $state)"/>    <!-- _ | '(' -->
          <xsl:variable name="state" select="p:consume(7, $input, $state)"/>        <!-- '(' -->
          <xsl:variable name="state" select="p:lookahead1W(23, $input, $state)"/>   <!-- _ | ID | ')' | 'false' | 'true' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] != 8">                                  <!-- ')' -->
                <xsl:variable name="state" select="p:whitespace($input, $state)"/>
                <xsl:variable name="state" select="
                  if ($state[$p:error]) then
                    $state
                  else
                    p:parse-RuleID($input, $state)
                "/>
                <xsl:variable name="state" select="p:parse-ParserRule-1($input, $state)"/>
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:sequence select="$state"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:variable name="state" select="p:consume(8, $input, $state)"/>        <!-- ')' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(3, $input, $state)"/>          <!-- _ | ':' -->
    <xsl:variable name="state" select="p:consume(16, $input, $state)"/>             <!-- ':' -->
    <xsl:variable name="state" select="p:lookahead1W(37, $input, $state)"/>         <!-- _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' | 'true' | '{' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Alternatives($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(18, $input, $state)"/>             <!-- ';' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'ParserRule', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Annotation.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Annotation" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(25, $input, $state)"/>             <!-- '@' -->
    <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/>          <!-- _ | ID -->
    <xsl:variable name="state" select="p:consume(2, $input, $state)"/>              <!-- ID -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Annotation', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production AbstractRule (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-AbstractRule-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(32, $input, $state)"/>     <!-- _ | ID | '@' | 'enum' | 'false' | 'fragment' | 'terminal' | 'true' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 25">                                     <!-- '@' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Annotation($input, $state)
            "/>
            <xsl:sequence select="p:parse-AbstractRule-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse AbstractRule.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-AbstractRule" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-AbstractRule-1($input, $state)"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 39">                                        <!-- 'terminal' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-TerminalRule($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 31">                                        <!-- 'enum' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-EnumRule($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ParserRule($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'AbstractRule', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse ReferencedMetamodel.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ReferencedMetamodel" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(37, $input, $state)"/>             <!-- 'import' -->
    <xsl:variable name="state" select="p:lookahead1W(1, $input, $state)"/>          <!-- _ | STRING -->
    <xsl:variable name="state" select="p:consume(3, $input, $state)"/>              <!-- STRING -->
    <xsl:variable name="state" select="p:lookahead1W(39, $input, $state)"/>         <!-- _ | ID | '@' | 'as' | 'enum' | 'false' | 'fragment' | 'generate' |
                                                                                         'import' | 'terminal' | 'true' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 29">                                        <!-- 'as' -->
          <xsl:variable name="state" select="p:consume(29, $input, $state)"/>       <!-- 'as' -->
          <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>   <!-- _ | ID | 'false' | 'true' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ValidID($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'ReferencedMetamodel', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse GeneratedMetamodel.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-GeneratedMetamodel" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(34, $input, $state)"/>             <!-- 'generate' -->
    <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>         <!-- _ | ID | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-ValidID($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(1, $input, $state)"/>          <!-- _ | STRING -->
    <xsl:variable name="state" select="p:consume(3, $input, $state)"/>              <!-- STRING -->
    <xsl:variable name="state" select="p:lookahead1W(39, $input, $state)"/>         <!-- _ | ID | '@' | 'as' | 'enum' | 'false' | 'fragment' | 'generate' |
                                                                                         'import' | 'terminal' | 'true' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 29">                                        <!-- 'as' -->
          <xsl:variable name="state" select="p:consume(29, $input, $state)"/>       <!-- 'as' -->
          <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>   <!-- _ | ID | 'false' | 'true' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ValidID($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'GeneratedMetamodel', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse AbstractMetamodelDeclaration.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-AbstractMetamodelDeclaration" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 34">                                        <!-- 'generate' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-GeneratedMetamodel($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ReferencedMetamodel($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'AbstractMetamodelDeclaration', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production RuleID (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-RuleID-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(48, $input, $state)"/>     <!-- _ | ID | STRING | '!' | '&' | '(' | ')' | '*' | '+' | ',' | '->' |
                                                                                         '.' | '::' | ';' | '<' | '=>' | '?' | 'EOF' | ']' | 'false' | 'true' |
                                                                                         '{' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 17">                                     <!-- '::' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(17, $input, $state)"/>     <!-- '::' -->
            <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/> <!-- _ | ID | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-ValidID($input, $state)
            "/>
            <xsl:sequence select="p:parse-RuleID-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse RuleID.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-RuleID" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-ValidID($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-RuleID-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'RuleID', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse ValidID.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ValidID" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 2">                                         <!-- ID -->
          <xsl:variable name="state" select="p:consume(2, $input, $state)"/>        <!-- ID -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 40">                                        <!-- 'true' -->
          <xsl:variable name="state" select="p:consume(40, $input, $state)"/>       <!-- 'true' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(32, $input, $state)"/>       <!-- 'false' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'ValidID', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production GrammarID (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-GrammarID-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(43, $input, $state)"/>     <!-- _ | ID | ',' | '.' | '@' | 'enum' | 'false' | 'fragment' | 'generate' |
                                                                                         'hidden' | 'import' | 'terminal' | 'true' | 'with' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 14">                                     <!-- '.' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(14, $input, $state)"/>     <!-- '.' -->
            <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/> <!-- _ | ID | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-ValidID($input, $state)
            "/>
            <xsl:sequence select="p:parse-GrammarID-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse GrammarID.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-GrammarID" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-ValidID($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-GrammarID-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'GrammarID', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Grammar (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Grammar-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 12">                                     <!-- ',' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(12, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/> <!-- _ | ID | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-GrammarID($input, $state)
            "/>
            <xsl:sequence select="p:parse-Grammar-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse the 2nd loop of production Grammar (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Grammar-2">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 12">                                     <!-- ',' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(12, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/> <!-- _ | ID | 'false' | 'true' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-RuleID($input, $state)
            "/>
            <xsl:sequence select="p:parse-Grammar-2($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse the 3rd loop of production Grammar (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Grammar-3">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(38, $input, $state)"/>     <!-- _ | ID | '@' | 'enum' | 'false' | 'fragment' | 'generate' | 'import' |
                                                                                         'terminal' | 'true' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 34                                         (: 'generate' :)
                      and $state[$p:l1] != 37">                                     <!-- 'import' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-AbstractMetamodelDeclaration($input, $state)
            "/>
            <xsl:sequence select="p:parse-Grammar-3($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse the 4th loop of production Grammar (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Grammar-4">
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
            p:parse-AbstractRule($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(35, $input, $state)"/>     <!-- _ | ID | EOF | '@' | 'enum' | 'false' | 'fragment' | 'terminal' |
                                                                                         'true' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 4">                                       <!-- EOF -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-Grammar-4($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
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
    <xsl:variable name="state" select="p:lookahead1W(7, $input, $state)"/>          <!-- _ | 'grammar' -->
    <xsl:variable name="state" select="p:consume(35, $input, $state)"/>             <!-- 'grammar' -->
    <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>         <!-- _ | ID | 'false' | 'true' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-GrammarID($input, $state)
    "/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 41">                                        <!-- 'with' -->
          <xsl:variable name="state" select="p:consume(41, $input, $state)"/>       <!-- 'with' -->
          <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>   <!-- _ | ID | 'false' | 'true' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-GrammarID($input, $state)
          "/>
          <xsl:variable name="state" select="p:parse-Grammar-1($input, $state)"/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 36">                                        <!-- 'hidden' -->
          <xsl:variable name="state" select="p:consume(36, $input, $state)"/>       <!-- 'hidden' -->
          <xsl:variable name="state" select="p:lookahead1W(2, $input, $state)"/>    <!-- _ | '(' -->
          <xsl:variable name="state" select="p:consume(7, $input, $state)"/>        <!-- '(' -->
          <xsl:variable name="state" select="p:lookahead1W(23, $input, $state)"/>   <!-- _ | ID | ')' | 'false' | 'true' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] != 8">                                  <!-- ')' -->
                <xsl:variable name="state" select="p:whitespace($input, $state)"/>
                <xsl:variable name="state" select="
                  if ($state[$p:error]) then
                    $state
                  else
                    p:parse-RuleID($input, $state)
                "/>
                <xsl:variable name="state" select="p:parse-Grammar-2($input, $state)"/>
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:sequence select="$state"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:variable name="state" select="p:consume(8, $input, $state)"/>        <!-- ')' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:parse-Grammar-3($input, $state)"/>
    <xsl:variable name="state" select="p:parse-Grammar-4($input, $state)"/>
    <xsl:variable name="state" select="p:consume(4, $input, $state)"/>              <!-- EOF -->
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
      <xsl:when test="$match[1] = 1">                                               <!-- _ -->
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
      $match[1] * 64 + $state[$p:l1],
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
      $match[1] * 4096 + $state[$p:lk],
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
   ! Parse start symbol Grammar from given string.
   !
   ! @param $s the string to be parsed.
   ! @return the result as generated by parser actions.
  -->
  <xsl:function name="p:parse-Grammar" as="item()*">
    <xsl:param name="s" as="xs:string"/>

    <xsl:variable name="state" select="0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, false()"/>
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