xquery version "1.0" encoding "UTF-8";

(: This file was generated on Thu Jan 30, 2025 13:23 (UTC+01) by REx v6.1 which is Copyright (c) 1979-2025 by Gunther Rademacher <grd@gmx.net> :)
(: REx command line: -q -tree -a none -xquery -name de/bottlecaps/convert/xq/xtext/xtext.xquery ../../../../../../../main/java/de/bottlecaps/convert/xtext/xtext.ebnf :)

(:~
 : The parser that was generated for the de/bottlecaps/convert/xq/xtext/xtext.xquery grammar.
 :)
module namespace p="de/bottlecaps/convert/xq/xtext/xtext.xquery";
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
 : The index of the lexer state that points to the first entry
 : used for collecting action results.
 :)
declare variable $p:result as xs:integer := 14;

(:~
 : The codepoint to charclass mapping for 7 bit codepoints.
 :)
declare variable $p:MAP0 as xs:integer+ :=
(
  57, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 6, 6, 6, 7,
  8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 19, 20, 21, 22, 23, 24, 25, 25, 25, 25,
  26, 27, 28, 28, 28, 28, 28, 28, 28, 28, 29, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 30, 31, 32, 33, 28, 6, 34, 35,
  36, 37, 38, 39, 40, 41, 42, 28, 28, 43, 44, 45, 46, 47, 28, 48, 49, 50, 51, 52, 53, 28, 28, 28, 54, 55, 56, 6, 6
);

(:~
 : The codepoint to charclass mapping for codepoints below the surrogate block.
 :)
declare variable $p:MAP1 as xs:integer+ :=
(
  54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
  58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 90, 122, 154, 186,
  216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216,
  216, 216, 216, 216, 216, 216, 216, 216, 216, 57, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 6, 6, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
  18, 19, 20, 21, 22, 23, 24, 25, 25, 25, 25, 26, 27, 28, 28, 28, 28, 28, 28, 28, 28, 29, 28, 28, 28, 28, 28, 28, 28,
  28, 28, 28, 28, 30, 31, 32, 33, 28, 6, 34, 35, 36, 37, 38, 39, 40, 41, 42, 28, 28, 43, 44, 45, 46, 47, 28, 48, 49, 50,
  51, 52, 53, 28, 28, 28, 54, 55, 56, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
  6, 6, 6, 6, 6
);

(:~
 : The codepoint to charclass mapping for codepoints above the surrogate block.
 :)
declare variable $p:MAP2 as xs:integer+ :=
(
  57344, 65536, 65533, 1114111, 6, 6
);

(:~
 : The token-set-id to DFA-initial-state mapping.
 :)
declare variable $p:INITIAL as xs:integer+ :=
(
  1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32,
  33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49
);

(:~
 : The DFA transition table.
 :)
declare variable $p:TRANSITION as xs:integer+ :=
(
  2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 928, 928, 928, 942,
  2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 928, 928, 928, 942, 1071, 1069, 2117, 2117,
  2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 928, 928, 928, 942, 2780, 990, 2117, 2117, 2117, 2117, 2117, 2117,
  2117, 2117, 2117, 2117, 2117, 1212, 1219, 965, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
  988, 1902, 1006, 1021, 3108, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1560,
  2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 949, 1265, 1044, 2915, 990, 2117, 2117,
  2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1067, 1943, 1087, 1102, 3108, 990, 2117, 2117, 2117, 2117, 2117, 2117,
  2117, 2117, 2117, 2117, 1125, 1303, 1144, 1159, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
  1367, 1861, 2012, 1182, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1530, 972, 1205,
  1590, 1691, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1189, 2776, 1028, 1235, 2915, 990, 2117, 2117,
  2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1567, 1051, 1568, 1258, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117,
  2117, 2117, 2117, 2117, 2117, 2117, 1281, 1296, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
  1128, 2117, 1319, 1360, 1242, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1383, 1383, 1383, 1398,
  2915, 2439, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1421, 1807, 1974, 1450,
  1466, 2960, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2247, 2236, 1508, 1523, 1546, 990, 2117, 2117, 2117, 2117, 2117,
  2117, 2117, 2117, 2117, 2117, 2117, 1584, 1753, 1606, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
  2117, 2117, 3088, 1989, 2004, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1638, 1629, 1654,
  1670, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1613, 1109, 2117, 1560, 1707, 990, 2117,
  2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2911, 1166, 1745, 2915, 990, 2117, 2117, 2117, 2117, 2117,
  2117, 2117, 2117, 2117, 2117, 2117, 2117, 1769, 1560, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
  2117, 2107, 1797, 1434, 2294, 1807, 1974, 1450, 1466, 2960, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797,
  2698, 1823, 1807, 1974, 1450, 1466, 2960, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 1807,
  2073, 1450, 1466, 2960, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 1807, 1974, 3017, 2976,
  2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 2148, 1974, 3017, 2976, 2970, 2116, 2117,
  2117, 2117, 2117, 2117, 2117, 2117, 2118, 2117, 1560, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
  2117, 2117, 2117, 2117, 1721, 3108, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1685, 1689, 1689,
  1852, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1892, 1877, 1918, 1933, 2915, 990, 2117,
  2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1836, 2530, 1807, 1959, 1450, 2028, 2134, 2116,
  2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 1492, 1974, 1450, 1466, 2960, 2116, 2117, 2117, 2117,
  2117, 2117, 2117, 2098, 1797, 1434, 2294, 1807, 1974, 1450, 1466, 2960, 2116, 2117, 2117, 2117, 2117, 2117, 2117,
  2107, 1797, 1434, 2294, 1807, 2191, 2263, 1466, 2960, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 2279,
  2561, 2323, 1974, 2353, 2369, 2411, 2437, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 2455, 2499, 2470, 1492, 1974,
  1450, 1466, 2960, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2629, 1797, 2574, 2294, 1807, 1974, 1479, 2976, 2970,
  2116, 2117, 2117, 2117, 2117, 2117, 2117, 1344, 2515, 2546, 2294, 1807, 1974, 3070, 2976, 2970, 2116, 2117, 2117,
  2117, 2117, 2117, 2117, 2107, 1797, 2483, 2685, 2603, 1974, 3017, 1334, 2970, 2116, 2117, 2117, 2117, 2117, 2117,
  2117, 2107, 1797, 1434, 2294, 1807, 2221, 3017, 2976, 2421, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797,
  1434, 2294, 2175, 1974, 2590, 2619, 2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 2654,
  2058, 3017, 2337, 2670, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 1807, 1974, 1781, 2976,
  2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 1807, 2043, 3017, 2976, 2970, 2116, 2117,
  2117, 2117, 2117, 2117, 2117, 2638, 2714, 1434, 2729, 2745, 2761, 2796, 2088, 2854, 2116, 2117, 2117, 2117, 2117,
  2117, 2117, 2107, 1797, 1434, 2294, 2809, 1974, 2382, 2976, 2864, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107,
  2825, 2880, 2840, 1492, 2896, 3017, 2976, 2931, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2946,
  2395, 2206, 2162, 2976, 2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 1492, 1974, 3017,
  2976, 2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 2307, 2294, 1807, 1974, 3017, 2976, 2970, 2116,
  2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2992, 3007, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
  2117, 2117, 1405, 3047, 3033, 3060, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1729, 2117,
  3086, 1560, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 3104, 2117, 2117, 2117,
  2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562,
  562, 562, 562, 562, 562, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1792, 1792, 0, 0, 1536, 0, 0, 0, 0, 54,
  55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2560, 2560, 2560, 2560, 0, 54, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77,
  0, 0, 0, 54, 0, 54, 54, 0, 0, 54, 54, 54, 0, 54, 54, 54, 54, 0, 0, 0, 0, 1024, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  2816, 2816, 2816, 2874, 1792, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3328, 3328, 0, 0, 0, 55, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 512, 0, 0, 0, 0, 55, 0, 55, 55, 0, 0, 55, 55, 55, 0, 55, 55, 55, 55, 0, 0, 0,
  0, 54, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5888, 5888, 0, 0, 0, 0, 2048, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  3840, 0, 0, 0, 0, 2048, 0, 2048, 2048, 0, 0, 2048, 2048, 2048, 0, 2048, 2048, 2048, 2048, 0, 0, 0, 0, 54, 55, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 6144, 6144, 6144, 6207, 2304, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 58, 0,
  0, 0, 0, 2560, 0, 77, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1536, 0, 0, 0, 0, 1536, 0, 0, 1536, 0, 1536, 0,
  2816, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4096, 77, 590, 0, 0, 3328, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 1792, 0, 0, 1792, 0, 1792, 0, 0, 68, 0, 68, 68, 0, 0, 68, 68, 68, 0, 68, 68, 68, 68, 0, 0, 0, 0, 54,
  55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 2048, 0, 0, 0, 2048, 0, 3840, 0, 0, 3840, 0, 0, 0, 0, 3840, 0, 3840, 3840, 0,
  3916, 0, 0, 0, 0, 0, 820, 820, 899, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 0, 820, 0, 0, 0, 0, 59, 0, 3840, 0, 0, 0, 0,
  54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2304, 0, 0, 0, 0, 0, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51,
  51, 0, 590, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11264, 0, 0, 0, 0, 0, 0, 0, 0, 820, 0, 54, 55, 0, 0, 0, 0, 0,
  820, 820, 0, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 112, 113, 0, 0, 0, 0, 820, 820,
  820, 820, 820, 820, 820, 820, 820, 124, 125, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 135, 136, 0, 0, 0, 0, 0, 0,
  820, 820, 887, 820, 820, 820, 820, 820, 820, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 54, 55, 0, 4419, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2560, 0, 0, 0, 0, 2560, 0, 0, 0,
  4608, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 590, 0, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3328, 0, 0, 0, 0,
  4864, 0, 0, 0, 0, 4864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 95, 590, 0, 0, 4864, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 5888, 0, 0, 5888, 0, 0, 0, 0, 0, 0, 5376, 0, 5376, 0, 0, 0, 0, 0, 0, 0, 5376, 0, 0, 0, 0, 0, 0, 5376, 0, 0,
  0, 0, 0, 0, 69, 0, 0, 69, 0, 0, 69, 0, 69, 0, 0, 69, 0, 5445, 69, 0, 0, 0, 0, 54, 55, 0, 0, 3072, 0, 0, 0, 0, 6400, 0,
  0, 0, 0, 0, 7424, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 95, 0, 0, 0, 0, 3584, 5632, 0, 0, 0, 0, 0, 0, 0, 77, 590,
  0, 0, 0, 0, 0, 79, 80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11520, 0, 0, 0, 0, 11520, 0, 0, 6144, 0, 0, 0, 0, 54, 55, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 4864, 4864, 4864, 0, 4864, 4864, 4864, 4864, 6656, 0, 0, 6656, 0, 0, 6656, 6656, 0, 0, 0, 6656, 0,
  0, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 890, 820, 820, 0, 0, 0, 820, 0, 0, 0, 820, 820, 820, 0, 820, 820, 0, 0,
  0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 0, 0, 838, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820,
  820, 820, 820, 841, 820, 820, 820, 820, 820, 820, 820, 820, 7424, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2304,
  0, 0, 0, 0, 2304, 0, 0, 0, 0, 0, 53, 0, 0, 0, 53, 53, 53, 0, 53, 53, 0, 0, 0, 53, 0, 0, 0, 0, 0, 0, 0, 0, 53, 0, 0, 0,
  0, 0, 0, 54, 0, 0, 0, 54, 0, 0, 0, 0, 54, 53, 0, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 0, 0, 0, 0,
  54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 55, 0, 0, 0, 55, 0, 0, 0, 0, 55, 0, 100, 0, 0, 820, 820, 873, 820, 820, 820, 820,
  820, 820, 820, 77, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 77, 0, 0, 0, 0, 0, 5120, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 5120, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2304, 2304, 2304, 0, 2304, 2304, 2304, 2304, 125,
  0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 135, 136, 0, 138, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 877, 820,
  820, 77, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 876, 820, 820, 820, 77, 0, 0, 0, 0, 820, 820, 820, 820, 820, 6964,
  820, 820, 820, 820, 77, 0, 0, 0, 0, 129, 820, 820, 820, 900, 901, 820, 0, 0, 0, 0, 0, 56, 0, 0, 820, 0, 0, 0, 0, 0, 0,
  0, 0, 820, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7168, 0, 820, 820, 910, 820, 820, 54, 55, 0, 0, 0, 820,
  912, 820, 820, 820, 0, 0, 0, 858, 820, 820, 820, 820, 820, 0, 77, 590, 0, 0, 0, 0, 0, 117, 820, 820, 820, 820, 820,
  820, 820, 820, 820, 0, 0, 0, 820, 820, 860, 820, 820, 820, 0, 77, 590, 0, 0, 0, 0, 101, 0, 820, 820, 820, 820, 820,
  820, 820, 820, 878, 820, 77, 0, 0, 0, 0, 820, 872, 820, 874, 820, 820, 820, 820, 820, 820, 77, 0, 0, 0, 0, 871, 820,
  820, 820, 820, 820, 820, 820, 820, 820, 77, 0, 0, 0, 0, 4352, 0, 0, 0, 0, 4352, 0, 0, 0, 0, 4352, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 4352, 4352, 112, 113, 0, 0, 116, 0, 820, 820, 820, 820, 820, 820, 820, 891, 820, 124, 833, 0, 820, 833, 820,
  820, 833, 833, 820, 820, 820, 833, 820, 820, 820, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820,
  820, 820, 820, 820, 820, 820, 820, 843, 820, 820, 820, 820, 820, 857, 0, 0, 0, 820, 859, 820, 820, 820, 820, 0, 77,
  590, 0, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 0, 137, 0, 9472, 112, 113, 0, 0, 0, 0, 820, 10548, 820, 820, 820,
  889, 820, 820, 820, 124, 125, 126, 0, 128, 0, 8500, 820, 820, 820, 820, 902, 135, 136, 0, 0, 0, 0, 0, 0, 886, 820,
  820, 820, 820, 820, 820, 820, 820, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 97, 98, 0, 908, 820, 820, 820,
  820, 54, 55, 0, 0, 0, 820, 820, 820, 820, 820, 0, 0, 0, 0, 0, 820, 820, 820, 820, 10292, 9012, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 512, 0, 0, 0, 829, 0, 0, 0, 829, 829, 832, 0, 829, 829, 0, 0, 0, 829, 0, 0, 820, 820, 54, 55, 0,
  0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 840, 840, 820, 820, 820, 840, 820, 820, 820, 820, 832, 0, 829, 832, 829,
  829, 832, 832, 829, 829, 829, 832, 829, 829, 829, 829, 0, 0, 820, 0, 59, 0, 820, 820, 820, 59, 820, 820, 0, 0, 59,
  820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 853, 820, 0, 853, 820, 59, 820, 820, 820, 820, 820, 820, 820, 820, 820,
  842, 820, 820, 820, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 84, 820, 820, 0, 820, 820, 820, 820, 839, 839, 820, 820,
  820, 839, 820, 820, 820, 820, 0, 0, 0, 115, 0, 0, 820, 820, 820, 8244, 888, 820, 820, 820, 820, 0, 0, 0, 820, 820,
  820, 820, 861, 862, 0, 77, 590, 0, 0, 0, 0, 127, 0, 0, 820, 898, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 57, 0, 820, 0,
  0, 0, 0, 0, 0, 0, 0, 820, 0, 0, 0, 0, 0, 60, 856, 820, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 54, 55, 139,
  820, 909, 820, 820, 9524, 0, 0, 0, 0, 0, 911, 820, 820, 820, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 83, 0, 820, 820, 0,
  820, 820, 838, 820, 820, 820, 820, 838, 820, 820, 838, 820, 838, 820, 0, 0, 820, 0, 60, 0, 820, 820, 820, 60, 820,
  820, 0, 0, 60, 820, 0, 0, 820, 820, 54, 55, 0, 82, 0, 0, 0, 820, 854, 0, 855, 820, 854, 0, 0, 0, 820, 820, 820, 820,
  820, 820, 0, 77, 590, 54, 55, 99, 0, 0, 0, 820, 820, 820, 820, 875, 820, 820, 820, 820, 820, 77, 0, 0, 0, 58, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 96, 0, 0, 0, 0, 114, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 820, 820, 0, 0, 0,
  820, 820, 820, 7732, 820, 820, 0, 77, 590, 0, 0, 0, 0, 830, 0, 0, 0, 830, 830, 830, 0, 830, 830, 0, 0, 0, 830, 0, 0,
  820, 820, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820, 0, 0, 0, 9216, 0, 820, 820, 820, 820, 820, 0,
  0, 0, 0, 9984, 820, 820, 820, 820, 820, 834, 0, 830, 834, 830, 830, 834, 834, 830, 830, 830, 834, 830, 830, 830, 830,
  0, 0, 0, 102, 820, 820, 820, 820, 820, 820, 820, 820, 820, 879, 77, 0, 0, 0, 63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  77, 590, 0, 0, 0, 820, 820, 820, 9780, 820, 0, 0, 7936, 0, 0, 820, 820, 913, 8756, 820, 0, 0, 820, 820, 54, 55, 81, 0,
  0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820, 54, 55, 0, 0, 0, 820, 820, 820, 820, 820, 0, 0, 0, 0, 0, 820, 820, 820,
  820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 11008, 0, 0, 11008, 0, 0, 11008, 0, 11008, 0, 0, 11008, 0, 11008, 0, 0, 0, 0, 54,
  55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 820, 820, 0, 0, 11264, 0, 0, 0, 0, 0, 0, 11264,
  11264, 11264, 0, 11264, 11264, 11264, 11264, 0, 0, 0, 11264, 0, 0, 0, 0, 0, 0, 11264, 11264, 0, 0, 0, 0, 54, 55, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 820, 10804, 0, 0, 11520, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 5120, 0, 0, 0, 0, 1280, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 590, 54, 55
);

(:~
 : The DFA-state to expected-token-set mapping.
 :)
declare variable $p:EXPECTED as xs:integer+ :=
(
  73, 80, 83, 87, 91, 76, 95, 99, 103, 107, 111, 115, 119, 149, 172, 124, 128, 132, 140, 136, 152, 157, 144, 134, 150,
  155, 122, 147, 151, 156, 147, 151, 156, 148, 161, 146, 164, 153, 170, 196, 178, 219, 181, 222, 184, 188, 191, 200,
  194, 153, 207, 204, 216, 154, 226, 153, 166, 230, 234, 244, 153, 238, 232, 243, 165, 239, 248, 165, 210, 249, 174,
  212, 248, 6, 10, 130, 65538, 1310722, 14, 262, 1048578, 268435458, 1073741826, 2, 4194310, 258, 1050626, 4198402,
  16386, 65538, 65538, 262146, 268435458, 6, 17827842, 6, 66050, 142, 166, 4198722, 5247042, 590338, 134217870,
  -2113929210, 268648450, 2105486, -2113929194, 67133614, 2629774, -2113929210, -1577058298, 2367886, 67396014, 2367950,
  -2113908730, 75786158, 10758094, 75818926, 29241294, 346978286, 2, 2, 4, -2147483644, 4, 4, 16777216, 4, -2147483644,
  4, 131072, 8192, 2097152, 67108868, 4, 4, 2, 2, 8, 8, 536870916, 4, 4, 32768, 4, 67108868, 4, 4, 4, 4, 8, 8,
  1073741824, 0, 0, 0, 0, 4, 4, 4, -2147483644, 1073741824, 0, 0, 4, 0, 0, 0, 8, 16, 8, 4096, 0, 2048, 0, 0, 8, 64, 64,
  2048, 2048, 257, 259, 80, 257, 387, 6160, 1281, 387, 257, 1281, 423, 3329, 2305, 3329, 3329, 0, 0, 4096, 16, 951,
  2305, 3329, 2305, 64, 1, 256, 0, 8, 0, 16, 64, 2, 128, 4, 2, 3, 0, 384, 0, 80, 2048, 257, 2048, 2048, 80, 32, 0, 16,
  512, 64, 1, 256, 2, 0, 128, 0, 4, 8, 16, 64, 1, 2, 4, 32, 16, 512, 0, 128, 4, 32, 16, 0
);

(:~
 : The token-string table.
 :)
declare variable $p:TOKEN as xs:string+ :=
(
  "%ERROR",
  "_",
  "ID",
  "STRING",
  "EOF",
  "'!'",
  "'&amp;'",
  "'('",
  "')'",
  "'*'",
  "'+'",
  "'+='",
  "','",
  "'->'",
  "'.'",
  "'..'",
  "':'",
  "'::'",
  "';'",
  "'<'",
  "'='",
  "'=>'",
  "'>'",
  "'?'",
  "'?='",
  "'@'",
  "'EOF'",
  "'['",
  "']'",
  "'as'",
  "'current'",
  "'enum'",
  "'false'",
  "'fragment'",
  "'generate'",
  "'grammar'",
  "'hidden'",
  "'import'",
  "'returns'",
  "'terminal'",
  "'true'",
  "'with'",
  "'{'",
  "'|'",
  "'}'"
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
                      $result mod 256,
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
    let $result := $result idiv 256
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
        p:map2($c0, 1, 2)
    let $current := $current + 1
    let $i0 := 256 * $c1 + $current-state - 1
    let $i1 := $i0 idiv 16
    let $next-state := $p:TRANSITION[$i0 mod 16 + $p:TRANSITION[$i1 + 1] + 1]
    return
      if ($next-state > 255) then
        p:transition($input, $begin, $current, $current, $next-state, $next-state mod 256, $current-state)
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
    for $t in 0 to 1
    let $i0 := $t * 145 + $state - 1
    let $i1 := $i0 idiv 4
    return p:token((), $p:EXPECTED[$i0 mod 4 + $p:EXPECTED[$i1 + 1] + 1], $t * 32 + 1)
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
 : Parse EnumLiteralDeclaration.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-EnumLiteralDeclaration($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-ValidID($input, $state)
  let $state := p:lookahead1W(21, $input, $state)           (: _ | ';' | '=' | '|' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 20) then                       (: '=' :)
      let $state := p:consume(20, $input, $state)           (: '=' :)
      let $state := p:lookahead1W(1, $input, $state)        (: _ | STRING :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Keyword($input, $state)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "EnumLiteralDeclaration", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production EnumLiterals (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-EnumLiterals-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(16, $input, $state)         (: _ | ';' | '|' :)
    return
      if ($state[$p:l1] != 43) then                         (: '|' :)
        $state
      else
        let $state := p:consume(43, $input, $state)         (: '|' :)
        let $state := p:lookahead1W(18, $input, $state)     (: _ | ID | 'false' | 'true' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-EnumLiteralDeclaration($input, $state)
        return p:parse-EnumLiterals-1($input, $state)
};

(:~
 : Parse EnumLiterals.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-EnumLiterals($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-EnumLiteralDeclaration($input, $state)
  let $state := p:parse-EnumLiterals-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "EnumLiterals", $count, $begin, $end)
};

(:~
 : Parse EnumRule.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-EnumRule($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(31, $input, $state)               (: 'enum' :)
  let $state := p:lookahead1W(18, $input, $state)           (: _ | ID | 'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-ValidID($input, $state)
  let $state := p:lookahead1W(15, $input, $state)           (: _ | ':' | 'returns' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 38) then                       (: 'returns' :)
      let $state := p:consume(38, $input, $state)           (: 'returns' :)
      let $state := p:lookahead1W(0, $input, $state)        (: _ | ID :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-TypeRef($input, $state)
      return $state
    else
      $state
  let $state := p:lookahead1W(3, $input, $state)            (: _ | ':' :)
  let $state := p:consume(16, $input, $state)               (: ':' :)
  let $state := p:lookahead1W(18, $input, $state)           (: _ | ID | 'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-EnumLiterals($input, $state)
  let $state := p:consume(18, $input, $state)               (: ';' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "EnumRule", $count, $begin, $end)
};

(:~
 : Parse UntilToken.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-UntilToken($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(13, $input, $state)               (: '->' :)
  let $state := p:lookahead1W(36, $input, $state)           (: _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' |
                                                               'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-TerminalTokenElement($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "UntilToken", $count, $begin, $end)
};

(:~
 : Parse NegatedToken.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-NegatedToken($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(5, $input, $state)                (: '!' :)
  let $state := p:lookahead1W(36, $input, $state)           (: _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' |
                                                               'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-TerminalTokenElement($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "NegatedToken", $count, $begin, $end)
};

(:~
 : Parse AbstractNegatedToken.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-AbstractNegatedToken($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 5) then                        (: '!' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-NegatedToken($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-UntilToken($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "AbstractNegatedToken", $count, $begin, $end)
};

(:~
 : Parse ParenthesizedTerminalElement.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-ParenthesizedTerminalElement($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(7, $input, $state)                (: '(' :)
  let $state := p:lookahead1W(36, $input, $state)           (: _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' |
                                                               'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-TerminalAlternatives($input, $state)
  let $state := p:consume(8, $input, $state)                (: ')' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "ParenthesizedTerminalElement", $count, $begin, $end)
};

(:~
 : Parse TerminalRuleCall.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-TerminalRuleCall($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-RuleID($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "TerminalRuleCall", $count, $begin, $end)
};

(:~
 : Parse CharacterRange.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-CharacterRange($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Keyword($input, $state)
  let $state := p:lookahead1W(46, $input, $state)           (: _ | ID | STRING | '!' | '(' | ')' | '*' | '+' | '->' |
                                                               '.' | '..' | ';' | '?' | 'EOF' | 'false' | 'true' | '|' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 15) then                       (: '..' :)
      let $state := p:consume(15, $input, $state)           (: '..' :)
      let $state := p:lookahead1W(1, $input, $state)        (: _ | STRING :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Keyword($input, $state)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "CharacterRange", $count, $begin, $end)
};

(:~
 : Parse TerminalTokenElement.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-TerminalTokenElement($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 3) then                        (: STRING :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-CharacterRange($input, $state)
      return $state
    else if ($state[$p:l1] = 7) then                        (: '(' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ParenthesizedTerminalElement($input, $state)
      return $state
    else if ($state[$p:l1] = 5                              (: '!' :)
          or $state[$p:l1] = 13) then                       (: '->' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-AbstractNegatedToken($input, $state)
      return $state
    else if ($state[$p:l1] = 14) then                       (: '.' :)
      let $state := p:consume(14, $input, $state)           (: '.' :)
      return $state
    else if ($state[$p:l1] = 26) then                       (: 'EOF' :)
      let $state := p:consume(26, $input, $state)           (: 'EOF' :)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-TerminalRuleCall($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "TerminalTokenElement", $count, $begin, $end)
};

(:~
 : Parse TerminalToken.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-TerminalToken($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-TerminalTokenElement($input, $state)
  let $state := p:lookahead1W(44, $input, $state)           (: _ | ID | STRING | '!' | '(' | ')' | '*' | '+' | '->' |
                                                               '.' | ';' | '?' | 'EOF' | 'false' | 'true' | '|' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 9                              (: '*' :)
          or $state[$p:l1] = 10                             (: '+' :)
          or $state[$p:l1] = 23) then                       (: '?' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 23) then                   (: '?' :)
          let $state := p:consume(23, $input, $state)       (: '?' :)
          return $state
        else if ($state[$p:l1] = 9) then                    (: '*' :)
          let $state := p:consume(9, $input, $state)        (: '*' :)
          return $state
        else
          let $state := p:consume(10, $input, $state)       (: '+' :)
          return $state
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "TerminalToken", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production TerminalGroup (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-TerminalGroup-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-TerminalToken($input, $state)
    let $state := p:lookahead1W(41, $input, $state)         (: _ | ID | STRING | '!' | '(' | ')' | '->' | '.' | ';' |
                                                               'EOF' | 'false' | 'true' | '|' :)
    return
      if ($state[$p:l1] = 8                                 (: ')' :)
       or $state[$p:l1] = 18                                (: ';' :)
       or $state[$p:l1] = 43) then                          (: '|' :)
        $state
      else
        p:parse-TerminalGroup-1($input, $state)
};

(:~
 : Parse TerminalGroup.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-TerminalGroup($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-TerminalGroup-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "TerminalGroup", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production TerminalAlternatives (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-TerminalAlternatives-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 43) then                           (: '|' :)
      $state
    else
      let $state := p:consume(43, $input, $state)           (: '|' :)
      let $state := p:lookahead1W(36, $input, $state)       (: _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' |
                                                               'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-TerminalGroup($input, $state)
      return p:parse-TerminalAlternatives-1($input, $state)
};

(:~
 : Parse TerminalAlternatives.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-TerminalAlternatives($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-TerminalGroup($input, $state)
  let $state := p:parse-TerminalAlternatives-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "TerminalAlternatives", $count, $begin, $end)
};

(:~
 : Parse TerminalRule.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-TerminalRule($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(39, $input, $state)               (: 'terminal' :)
  let $state := p:lookahead1W(24, $input, $state)           (: _ | ID | 'false' | 'fragment' | 'true' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 33) then                       (: 'fragment' :)
      let $state := p:consume(33, $input, $state)           (: 'fragment' :)
      let $state := p:lookahead1W(18, $input, $state)       (: _ | ID | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ValidID($input, $state)
      return $state
    else
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ValidID($input, $state)
      let $state := p:lookahead1W(15, $input, $state)       (: _ | ':' | 'returns' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 38) then                   (: 'returns' :)
          let $state := p:consume(38, $input, $state)       (: 'returns' :)
          let $state := p:lookahead1W(0, $input, $state)    (: _ | ID :)
          let $state := p:whitespace($input, $state)
          let $state :=
            if ($state[$p:error]) then
              $state
            else
              p:parse-TypeRef($input, $state)
          return $state
        else
          $state
      return $state
  let $state := p:lookahead1W(3, $input, $state)            (: _ | ':' :)
  let $state := p:consume(16, $input, $state)               (: ':' :)
  let $state := p:lookahead1W(36, $input, $state)           (: _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' |
                                                               'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-TerminalAlternatives($input, $state)
  let $state := p:consume(18, $input, $state)               (: ';' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "TerminalRule", $count, $begin, $end)
};

(:~
 : Parse Action.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Action($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(42, $input, $state)               (: '{' :)
  let $state := p:lookahead1W(0, $input, $state)            (: _ | ID :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-TypeRef($input, $state)
  let $state := p:lookahead1W(13, $input, $state)           (: _ | '.' | '}' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 14) then                       (: '.' :)
      let $state := p:consume(14, $input, $state)           (: '.' :)
      let $state := p:lookahead1W(18, $input, $state)       (: _ | ID | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ValidID($input, $state)
      let $state := p:lookahead1W(11, $input, $state)       (: _ | '+=' | '=' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 20) then                   (: '=' :)
          let $state := p:consume(20, $input, $state)       (: '=' :)
          return $state
        else
          let $state := p:consume(11, $input, $state)       (: '+=' :)
          return $state
      let $state := p:lookahead1W(6, $input, $state)        (: _ | 'current' :)
      let $state := p:consume(30, $input, $state)           (: 'current' :)
      return $state
    else
      $state
  let $state := p:lookahead1W(8, $input, $state)            (: _ | '}' :)
  let $state := p:consume(44, $input, $state)               (: '}' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "Action", $count, $begin, $end)
};

(:~
 : Parse PredicatedGroup.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-PredicatedGroup($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 21) then                       (: '=>' :)
      let $state := p:consume(21, $input, $state)           (: '=>' :)
      return $state
    else
      let $state := p:consume(13, $input, $state)           (: '->' :)
      return $state
  let $state := p:lookahead1W(2, $input, $state)            (: _ | '(' :)
  let $state := p:consume(7, $input, $state)                (: '(' :)
  let $state := p:lookahead1W(37, $input, $state)           (: _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' |
                                                               'true' | '{' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Alternatives($input, $state)
  let $state := p:consume(8, $input, $state)                (: ')' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "PredicatedGroup", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production PredicatedRuleCall (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-PredicatedRuleCall-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 12) then                           (: ',' :)
      $state
    else
      let $state := p:consume(12, $input, $state)           (: ',' :)
      let $state := p:lookahead1W(27, $input, $state)       (: _ | ID | '!' | '(' | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-NamedArgument($input, $state)
      return p:parse-PredicatedRuleCall-1($input, $state)
};

(:~
 : Parse PredicatedRuleCall.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-PredicatedRuleCall($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 21) then                       (: '=>' :)
      let $state := p:consume(21, $input, $state)           (: '=>' :)
      return $state
    else
      let $state := p:consume(13, $input, $state)           (: '->' :)
      return $state
  let $state := p:lookahead1W(18, $input, $state)           (: _ | ID | 'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-RuleID($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 19) then                       (: '<' :)
      let $state := p:consume(19, $input, $state)           (: '<' :)
      let $state := p:lookahead1W(27, $input, $state)       (: _ | ID | '!' | '(' | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-NamedArgument($input, $state)
      let $state := p:parse-PredicatedRuleCall-1($input, $state)
      let $state := p:consume(22, $input, $state)           (: '>' :)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "PredicatedRuleCall", $count, $begin, $end)
};

(:~
 : Parse PredicatedKeyword.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-PredicatedKeyword($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 21) then                       (: '=>' :)
      let $state := p:consume(21, $input, $state)           (: '=>' :)
      return $state
    else
      let $state := p:consume(13, $input, $state)           (: '->' :)
      return $state
  let $state := p:lookahead1W(1, $input, $state)            (: _ | STRING :)
  let $state := p:consume(3, $input, $state)                (: STRING :)
  let $end := $state[$p:e0]
  return p:reduce($state, "PredicatedKeyword", $count, $begin, $end)
};

(:~
 : Parse ParenthesizedElement.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-ParenthesizedElement($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(7, $input, $state)                (: '(' :)
  let $state := p:lookahead1W(37, $input, $state)           (: _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' |
                                                               'true' | '{' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Alternatives($input, $state)
  let $state := p:consume(8, $input, $state)                (: ')' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "ParenthesizedElement", $count, $begin, $end)
};

(:~
 : Parse AbstractTerminal.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-AbstractTerminal($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:l1] = (13,                                (: '->' :)
                         21)) then                          (: '=>' :)
      let $state := p:lookahead2W(26, $input, $state)       (: _ | ID | STRING | '(' | 'false' | 'true' :)
      return $state
    else
      ($state[$p:l1], subsequence($state, $p:lk + 1))
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:lk] = 3) then                        (: STRING :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Keyword($input, $state)
      return $state
    else if ($state[$p:lk] = 2                              (: ID :)
          or $state[$p:lk] = 32                             (: 'false' :)
          or $state[$p:lk] = 40) then                       (: 'true' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-RuleCall($input, $state)
      return $state
    else if ($state[$p:lk] = 7) then                        (: '(' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ParenthesizedElement($input, $state)
      return $state
    else if ($state[$p:lk] = 205                            (: '->' STRING :)
          or $state[$p:lk] = 213) then                      (: '=>' STRING :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-PredicatedKeyword($input, $state)
      return $state
    else if ($state[$p:lk] = 461                            (: '->' '(' :)
          or $state[$p:lk] = 469) then                      (: '=>' '(' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-PredicatedGroup($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-PredicatedRuleCall($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "AbstractTerminal", $count, $begin, $end)
};

(:~
 : Parse CrossReferenceableTerminal.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-CrossReferenceableTerminal($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 3) then                        (: STRING :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Keyword($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-RuleCall($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "CrossReferenceableTerminal", $count, $begin, $end)
};

(:~
 : Parse CrossReference.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-CrossReference($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(27, $input, $state)               (: '[' :)
  let $state := p:lookahead1W(0, $input, $state)            (: _ | ID :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-TypeRef($input, $state)
  let $state := p:lookahead1W(17, $input, $state)           (: _ | ']' | '|' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 43) then                       (: '|' :)
      let $state := p:consume(43, $input, $state)           (: '|' :)
      let $state := p:lookahead1W(22, $input, $state)       (: _ | ID | STRING | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-CrossReferenceableTerminal($input, $state)
      return $state
    else
      $state
  let $state := p:lookahead1W(5, $input, $state)            (: _ | ']' :)
  let $state := p:consume(28, $input, $state)               (: ']' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "CrossReference", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production AssignableAlternatives (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-AssignableAlternatives-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(10, $input, $state)         (: _ | ')' | '|' :)
    return
      if ($state[$p:l1] != 43) then                         (: '|' :)
        $state
      else
        let $state := p:consume(43, $input, $state)         (: '|' :)
        let $state := p:lookahead1W(31, $input, $state)     (: _ | ID | STRING | '(' | '[' | 'false' | 'true' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-AssignableTerminal($input, $state)
        return p:parse-AssignableAlternatives-1($input, $state)
};

(:~
 : Parse AssignableAlternatives.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-AssignableAlternatives($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-AssignableTerminal($input, $state)
  let $state := p:parse-AssignableAlternatives-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "AssignableAlternatives", $count, $begin, $end)
};

(:~
 : Parse ParenthesizedAssignableElement.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-ParenthesizedAssignableElement($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(7, $input, $state)                (: '(' :)
  let $state := p:lookahead1W(31, $input, $state)           (: _ | ID | STRING | '(' | '[' | 'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-AssignableAlternatives($input, $state)
  let $state := p:consume(8, $input, $state)                (: ')' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "ParenthesizedAssignableElement", $count, $begin, $end)
};

(:~
 : Parse LiteralCondition.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-LiteralCondition($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 40) then                       (: 'true' :)
      let $state := p:consume(40, $input, $state)           (: 'true' :)
      return $state
    else
      let $state := p:consume(32, $input, $state)           (: 'false' :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "LiteralCondition", $count, $begin, $end)
};

(:~
 : Parse ParenthesizedCondition.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-ParenthesizedCondition($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(7, $input, $state)                (: '(' :)
  let $state := p:lookahead1W(27, $input, $state)           (: _ | ID | '!' | '(' | 'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Disjunction($input, $state)
  let $state := p:consume(8, $input, $state)                (: ')' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "ParenthesizedCondition", $count, $begin, $end)
};

(:~
 : Parse ParameterReference.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-ParameterReference($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(2, $input, $state)                (: ID :)
  let $end := $state[$p:e0]
  return p:reduce($state, "ParameterReference", $count, $begin, $end)
};

(:~
 : Parse Atom.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Atom($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 2) then                        (: ID :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ParameterReference($input, $state)
      return $state
    else if ($state[$p:l1] = 7) then                        (: '(' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ParenthesizedCondition($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-LiteralCondition($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Atom", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Negation (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Negation-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(27, $input, $state)         (: _ | ID | '!' | '(' | 'false' | 'true' :)
    return
      if ($state[$p:l1] != 5) then                          (: '!' :)
        $state
      else
        let $state := p:consume(5, $input, $state)          (: '!' :)
        return p:parse-Negation-1($input, $state)
};

(:~
 : Parse Negation.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Negation($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-Negation-1($input, $state)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Atom($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Negation", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Conjunction (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Conjunction-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(28, $input, $state)         (: _ | '&' | ')' | ',' | '>' | '|' :)
    return
      if ($state[$p:l1] != 6) then                          (: '&' :)
        $state
      else
        let $state := p:consume(6, $input, $state)          (: '&' :)
        let $state := p:lookahead1W(27, $input, $state)     (: _ | ID | '!' | '(' | 'false' | 'true' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-Negation($input, $state)
        return p:parse-Conjunction-1($input, $state)
};

(:~
 : Parse Conjunction.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Conjunction($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Negation($input, $state)
  let $state := p:parse-Conjunction-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Conjunction", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Disjunction (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Disjunction-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 43) then                           (: '|' :)
      $state
    else
      let $state := p:consume(43, $input, $state)           (: '|' :)
      let $state := p:lookahead1W(27, $input, $state)       (: _ | ID | '!' | '(' | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Conjunction($input, $state)
      return p:parse-Disjunction-1($input, $state)
};

(:~
 : Parse Disjunction.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Disjunction($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Conjunction($input, $state)
  let $state := p:parse-Disjunction-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Disjunction", $count, $begin, $end)
};

(:~
 : Parse NamedArgument.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-NamedArgument($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:l1] eq 2) then                            (: ID :)
      let $state := p:lookahead2W(29, $input, $state)       (: _ | '&' | ',' | '=' | '>' | '|' :)
      return $state
    else
      ($state[$p:l1], subsequence($state, $p:lk + 1))
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:lk] = 1282) then                     (: ID '=' :)
      let $state := p:consume(2, $input, $state)            (: ID :)
      let $state := p:lookahead1W(4, $input, $state)        (: _ | '=' :)
      let $state := p:consume(20, $input, $state)           (: '=' :)
      return $state
    else
      $state
  let $state := p:lookahead1W(27, $input, $state)           (: _ | ID | '!' | '(' | 'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Disjunction($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "NamedArgument", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production RuleCall (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-RuleCall-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 12) then                           (: ',' :)
      $state
    else
      let $state := p:consume(12, $input, $state)           (: ',' :)
      let $state := p:lookahead1W(27, $input, $state)       (: _ | ID | '!' | '(' | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-NamedArgument($input, $state)
      return p:parse-RuleCall-1($input, $state)
};

(:~
 : Parse RuleCall.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-RuleCall($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-RuleID($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 19) then                       (: '<' :)
      let $state := p:consume(19, $input, $state)           (: '<' :)
      let $state := p:lookahead1W(27, $input, $state)       (: _ | ID | '!' | '(' | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-NamedArgument($input, $state)
      let $state := p:parse-RuleCall-1($input, $state)
      let $state := p:consume(22, $input, $state)           (: '>' :)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "RuleCall", $count, $begin, $end)
};

(:~
 : Parse Keyword.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Keyword($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(3, $input, $state)                (: STRING :)
  let $end := $state[$p:e0]
  return p:reduce($state, "Keyword", $count, $begin, $end)
};

(:~
 : Parse AssignableTerminal.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-AssignableTerminal($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 3) then                        (: STRING :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Keyword($input, $state)
      return $state
    else if ($state[$p:l1] = 7) then                        (: '(' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ParenthesizedAssignableElement($input, $state)
      return $state
    else if ($state[$p:l1] = 27) then                       (: '[' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-CrossReference($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-RuleCall($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "AssignableTerminal", $count, $begin, $end)
};

(:~
 : Parse Assignment.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Assignment($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 13                             (: '->' :)
          or $state[$p:l1] = 21) then                       (: '=>' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 21) then                   (: '=>' :)
          let $state := p:consume(21, $input, $state)       (: '=>' :)
          return $state
        else
          let $state := p:consume(13, $input, $state)       (: '->' :)
          return $state
      return $state
    else
      $state
  let $state := p:lookahead1W(18, $input, $state)           (: _ | ID | 'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-ValidID($input, $state)
  let $state := p:lookahead1W(19, $input, $state)           (: _ | '+=' | '=' | '?=' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 11) then                       (: '+=' :)
      let $state := p:consume(11, $input, $state)           (: '+=' :)
      return $state
    else if ($state[$p:l1] = 20) then                       (: '=' :)
      let $state := p:consume(20, $input, $state)           (: '=' :)
      return $state
    else
      let $state := p:consume(24, $input, $state)           (: '?=' :)
      return $state
  let $state := p:lookahead1W(31, $input, $state)           (: _ | ID | STRING | '(' | '[' | 'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-AssignableTerminal($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Assignment", $count, $begin, $end)
};

(:~
 : Parse AbstractTokenWithCardinality.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-AbstractTokenWithCardinality($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:l1] = (13,                                (: '->' :)
                         21)) then                          (: '=>' :)
      let $state := p:lookahead2W(26, $input, $state)       (: _ | ID | STRING | '(' | 'false' | 'true' :)
      let $state :=
        if ($state[$p:lk] = (141,                           (: '->' ID :)
                             2061,                          (: '->' 'false' :)
                             2573,                          (: '->' 'true' :)
                             149,                           (: '=>' ID :)
                             2069,                          (: '=>' 'false' :)
                             2581)) then                    (: '=>' 'true' :)
          let $state := p:lookahead3W(47, $input, $state)   (: _ | ID | STRING | '&' | '(' | ')' | '*' | '+' | '+=' |
                                                               '->' | '::' | ';' | '<' | '=' | '=>' | '?' | '?=' |
                                                               'false' | 'true' | '{' | '|' :)
          return $state
        else
          $state
      return $state
    else if ($state[$p:l1] = (2,                            (: ID :)
                              32,                           (: 'false' :)
                              40)) then                     (: 'true' :)
      let $state := p:lookahead2W(47, $input, $state)       (: _ | ID | STRING | '&' | '(' | ')' | '*' | '+' | '+=' |
                                                               '->' | '::' | ';' | '<' | '=' | '=>' | '?' | '?=' |
                                                               'false' | 'true' | '{' | '|' :)
      return $state
    else
      ($state[$p:l1], subsequence($state, $p:lk + 1))
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:lk] = 706                            (: ID '+=' :)
          or $state[$p:lk] = 736                            (: 'false' '+=' :)
          or $state[$p:lk] = 744                            (: 'true' '+=' :)
          or $state[$p:lk] = 1282                           (: ID '=' :)
          or $state[$p:lk] = 1312                           (: 'false' '=' :)
          or $state[$p:lk] = 1320                           (: 'true' '=' :)
          or $state[$p:lk] = 1538                           (: ID '?=' :)
          or $state[$p:lk] = 1568                           (: 'false' '?=' :)
          or $state[$p:lk] = 1576                           (: 'true' '?=' :)
          or $state[$p:lk] = 45197                          (: '->' ID '+=' :)
          or $state[$p:lk] = 45205                          (: '=>' ID '+=' :)
          or $state[$p:lk] = 47117                          (: '->' 'false' '+=' :)
          or $state[$p:lk] = 47125                          (: '=>' 'false' '+=' :)
          or $state[$p:lk] = 47629                          (: '->' 'true' '+=' :)
          or $state[$p:lk] = 47637                          (: '=>' 'true' '+=' :)
          or $state[$p:lk] = 82061                          (: '->' ID '=' :)
          or $state[$p:lk] = 82069                          (: '=>' ID '=' :)
          or $state[$p:lk] = 83981                          (: '->' 'false' '=' :)
          or $state[$p:lk] = 83989                          (: '=>' 'false' '=' :)
          or $state[$p:lk] = 84493                          (: '->' 'true' '=' :)
          or $state[$p:lk] = 84501                          (: '=>' 'true' '=' :)
          or $state[$p:lk] = 98445                          (: '->' ID '?=' :)
          or $state[$p:lk] = 98453                          (: '=>' ID '?=' :)
          or $state[$p:lk] = 100365                         (: '->' 'false' '?=' :)
          or $state[$p:lk] = 100373                         (: '=>' 'false' '?=' :)
          or $state[$p:lk] = 100877                         (: '->' 'true' '?=' :)
          or $state[$p:lk] = 100885) then                   (: '=>' 'true' '?=' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Assignment($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-AbstractTerminal($input, $state)
      return $state
  let $state := p:lookahead1W(45, $input, $state)           (: _ | ID | STRING | '&' | '(' | ')' | '*' | '+' | '->' |
                                                               ';' | '=>' | '?' | 'false' | 'true' | '{' | '|' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 9                              (: '*' :)
          or $state[$p:l1] = 10                             (: '+' :)
          or $state[$p:l1] = 23) then                       (: '?' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 23) then                   (: '?' :)
          let $state := p:consume(23, $input, $state)       (: '?' :)
          return $state
        else if ($state[$p:l1] = 9) then                    (: '*' :)
          let $state := p:consume(9, $input, $state)        (: '*' :)
          return $state
        else
          let $state := p:consume(10, $input, $state)       (: '+' :)
          return $state
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "AbstractTokenWithCardinality", $count, $begin, $end)
};

(:~
 : Parse AbstractToken.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-AbstractToken($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 42) then                       (: '{' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Action($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-AbstractTokenWithCardinality($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "AbstractToken", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Group (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Group-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-AbstractToken($input, $state)
    let $state := p:lookahead1W(42, $input, $state)         (: _ | ID | STRING | '&' | '(' | ')' | '->' | ';' | '=>' |
                                                               'false' | 'true' | '{' | '|' :)
    return
      if ($state[$p:l1] = 6                                 (: '&' :)
       or $state[$p:l1] = 8                                 (: ')' :)
       or $state[$p:l1] = 18                                (: ';' :)
       or $state[$p:l1] = 43) then                          (: '|' :)
        $state
      else
        p:parse-Group-1($input, $state)
};

(:~
 : Parse Group.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Group($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-Group-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Group", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production UnorderedGroup (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-UnorderedGroup-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 6) then                            (: '&' :)
      $state
    else
      let $state := p:consume(6, $input, $state)            (: '&' :)
      let $state := p:lookahead1W(34, $input, $state)       (: _ | ID | STRING | '(' | '->' | '=>' | 'false' | 'true' |
                                                               '{' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Group($input, $state)
      return p:parse-UnorderedGroup-1($input, $state)
};

(:~
 : Parse UnorderedGroup.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-UnorderedGroup($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Group($input, $state)
  let $state := p:parse-UnorderedGroup-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "UnorderedGroup", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production ConditionalBranch (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-ConditionalBranch-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(34, $input, $state)         (: _ | ID | STRING | '(' | '->' | '=>' | 'false' | 'true' |
                                                               '{' :)
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-AbstractToken($input, $state)
    let $state := p:lookahead1W(40, $input, $state)         (: _ | ID | STRING | '(' | ')' | '->' | ';' | '=>' |
                                                               'false' | 'true' | '{' | '|' :)
    return
      if ($state[$p:l1] = 8                                 (: ')' :)
       or $state[$p:l1] = 18                                (: ';' :)
       or $state[$p:l1] = 43) then                          (: '|' :)
        $state
      else
        p:parse-ConditionalBranch-1($input, $state)
};

(:~
 : Parse ConditionalBranch.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-ConditionalBranch($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 19) then                       (: '<' :)
      let $state := p:consume(19, $input, $state)           (: '<' :)
      let $state := p:lookahead1W(27, $input, $state)       (: _ | ID | '!' | '(' | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Disjunction($input, $state)
      let $state := p:consume(22, $input, $state)           (: '>' :)
      let $state := p:parse-ConditionalBranch-1($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-UnorderedGroup($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "ConditionalBranch", $count, $begin, $end)
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
    if ($state[$p:l1] != 43) then                           (: '|' :)
      $state
    else
      let $state := p:consume(43, $input, $state)           (: '|' :)
      let $state := p:lookahead1W(37, $input, $state)       (: _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' |
                                                               'true' | '{' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ConditionalBranch($input, $state)
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
      p:parse-ConditionalBranch($input, $state)
  let $state := p:parse-Alternatives-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Alternatives", $count, $begin, $end)
};

(:~
 : Parse TypeRef.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-TypeRef($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(2, $input, $state)                (: ID :)
  let $state := p:lookahead1W(33, $input, $state)           (: _ | '.' | ':' | '::' | ']' | 'hidden' | '|' | '}' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 17) then                       (: '::' :)
      let $state := p:consume(17, $input, $state)           (: '::' :)
      let $state := p:lookahead1W(0, $input, $state)        (: _ | ID :)
      let $state := p:consume(2, $input, $state)            (: ID :)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "TypeRef", $count, $begin, $end)
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
  let $state := p:consume(2, $input, $state)                (: ID :)
  let $end := $state[$p:e0]
  return p:reduce($state, "Parameter", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production RuleNameAndParams (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-RuleNameAndParams-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(12, $input, $state)         (: _ | ',' | '>' :)
    return
      if ($state[$p:l1] != 12) then                         (: ',' :)
        $state
      else
        let $state := p:consume(12, $input, $state)         (: ',' :)
        let $state := p:lookahead1W(0, $input, $state)      (: _ | ID :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-Parameter($input, $state)
        return p:parse-RuleNameAndParams-1($input, $state)
};

(:~
 : Parse RuleNameAndParams.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-RuleNameAndParams($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-ValidID($input, $state)
  let $state := p:lookahead1W(30, $input, $state)           (: _ | '*' | ':' | '<' | 'hidden' | 'returns' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 19) then                       (: '<' :)
      let $state := p:consume(19, $input, $state)           (: '<' :)
      let $state := p:lookahead1W(9, $input, $state)        (: _ | ID | '>' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 2) then                    (: ID :)
          let $state := p:whitespace($input, $state)
          let $state :=
            if ($state[$p:error]) then
              $state
            else
              p:parse-Parameter($input, $state)
          let $state := p:parse-RuleNameAndParams-1($input, $state)
          return $state
        else
          $state
      let $state := p:consume(22, $input, $state)           (: '>' :)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "RuleNameAndParams", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production ParserRule (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-ParserRule-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 12) then                           (: ',' :)
      $state
    else
      let $state := p:consume(12, $input, $state)           (: ',' :)
      let $state := p:lookahead1W(18, $input, $state)       (: _ | ID | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-RuleID($input, $state)
      return p:parse-ParserRule-1($input, $state)
};

(:~
 : Parse ParserRule.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-ParserRule($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 33) then                       (: 'fragment' :)
      let $state := p:consume(33, $input, $state)           (: 'fragment' :)
      let $state := p:lookahead1W(18, $input, $state)       (: _ | ID | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-RuleNameAndParams($input, $state)
      let $state := p:lookahead1W(25, $input, $state)       (: _ | '*' | ':' | 'hidden' | 'returns' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 9                          (: '*' :)
              or $state[$p:l1] = 38) then                   (: 'returns' :)
          let $state :=
            if ($state[$p:error]) then
              $state
            else if ($state[$p:l1] = 9) then                (: '*' :)
              let $state := p:consume(9, $input, $state)    (: '*' :)
              return $state
            else
              let $state := p:consume(38, $input, $state)   (: 'returns' :)
              let $state := p:lookahead1W(0, $input, $state) (: _ | ID :)
              let $state := p:whitespace($input, $state)
              let $state :=
                if ($state[$p:error]) then
                  $state
                else
                  p:parse-TypeRef($input, $state)
              return $state
          return $state
        else
          $state
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-RuleNameAndParams($input, $state)
      let $state := p:lookahead1W(20, $input, $state)       (: _ | ':' | 'hidden' | 'returns' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 38) then                   (: 'returns' :)
          let $state := p:consume(38, $input, $state)       (: 'returns' :)
          let $state := p:lookahead1W(0, $input, $state)    (: _ | ID :)
          let $state := p:whitespace($input, $state)
          let $state :=
            if ($state[$p:error]) then
              $state
            else
              p:parse-TypeRef($input, $state)
          return $state
        else
          $state
      return $state
  let $state := p:lookahead1W(14, $input, $state)           (: _ | ':' | 'hidden' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 36) then                       (: 'hidden' :)
      let $state := p:consume(36, $input, $state)           (: 'hidden' :)
      let $state := p:lookahead1W(2, $input, $state)        (: _ | '(' :)
      let $state := p:consume(7, $input, $state)            (: '(' :)
      let $state := p:lookahead1W(23, $input, $state)       (: _ | ID | ')' | 'false' | 'true' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] != 8) then                   (: ')' :)
          let $state := p:whitespace($input, $state)
          let $state :=
            if ($state[$p:error]) then
              $state
            else
              p:parse-RuleID($input, $state)
          let $state := p:parse-ParserRule-1($input, $state)
          return $state
        else
          $state
      let $state := p:consume(8, $input, $state)            (: ')' :)
      return $state
    else
      $state
  let $state := p:lookahead1W(3, $input, $state)            (: _ | ':' :)
  let $state := p:consume(16, $input, $state)               (: ':' :)
  let $state := p:lookahead1W(37, $input, $state)           (: _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' |
                                                               'true' | '{' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Alternatives($input, $state)
  let $state := p:consume(18, $input, $state)               (: ';' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "ParserRule", $count, $begin, $end)
};

(:~
 : Parse Annotation.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Annotation($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(25, $input, $state)               (: '@' :)
  let $state := p:lookahead1W(0, $input, $state)            (: _ | ID :)
  let $state := p:consume(2, $input, $state)                (: ID :)
  let $end := $state[$p:e0]
  return p:reduce($state, "Annotation", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production AbstractRule (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-AbstractRule-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(32, $input, $state)         (: _ | ID | '@' | 'enum' | 'false' | 'fragment' |
                                                               'terminal' | 'true' :)
    return
      if ($state[$p:l1] != 25) then                         (: '@' :)
        $state
      else
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-Annotation($input, $state)
        return p:parse-AbstractRule-1($input, $state)
};

(:~
 : Parse AbstractRule.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-AbstractRule($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-AbstractRule-1($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 39) then                       (: 'terminal' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-TerminalRule($input, $state)
      return $state
    else if ($state[$p:l1] = 31) then                       (: 'enum' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-EnumRule($input, $state)
      return $state
    else
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ParserRule($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "AbstractRule", $count, $begin, $end)
};

(:~
 : Parse ReferencedMetamodel.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-ReferencedMetamodel($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(37, $input, $state)               (: 'import' :)
  let $state := p:lookahead1W(1, $input, $state)            (: _ | STRING :)
  let $state := p:consume(3, $input, $state)                (: STRING :)
  let $state := p:lookahead1W(39, $input, $state)           (: _ | ID | '@' | 'as' | 'enum' | 'false' | 'fragment' |
                                                               'generate' | 'import' | 'terminal' | 'true' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 29) then                       (: 'as' :)
      let $state := p:consume(29, $input, $state)           (: 'as' :)
      let $state := p:lookahead1W(18, $input, $state)       (: _ | ID | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ValidID($input, $state)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "ReferencedMetamodel", $count, $begin, $end)
};

(:~
 : Parse GeneratedMetamodel.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-GeneratedMetamodel($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(34, $input, $state)               (: 'generate' :)
  let $state := p:lookahead1W(18, $input, $state)           (: _ | ID | 'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-ValidID($input, $state)
  let $state := p:lookahead1W(1, $input, $state)            (: _ | STRING :)
  let $state := p:consume(3, $input, $state)                (: STRING :)
  let $state := p:lookahead1W(39, $input, $state)           (: _ | ID | '@' | 'as' | 'enum' | 'false' | 'fragment' |
                                                               'generate' | 'import' | 'terminal' | 'true' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 29) then                       (: 'as' :)
      let $state := p:consume(29, $input, $state)           (: 'as' :)
      let $state := p:lookahead1W(18, $input, $state)       (: _ | ID | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ValidID($input, $state)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "GeneratedMetamodel", $count, $begin, $end)
};

(:~
 : Parse AbstractMetamodelDeclaration.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-AbstractMetamodelDeclaration($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 34) then                       (: 'generate' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-GeneratedMetamodel($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-ReferencedMetamodel($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "AbstractMetamodelDeclaration", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production RuleID (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-RuleID-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(48, $input, $state)         (: _ | ID | STRING | '!' | '&' | '(' | ')' | '*' | '+' |
                                                               ',' | '->' | '.' | '::' | ';' | '<' | '=>' | '?' |
                                                               'EOF' | ']' | 'false' | 'true' | '{' | '|' :)
    return
      if ($state[$p:l1] != 17) then                         (: '::' :)
        $state
      else
        let $state := p:consume(17, $input, $state)         (: '::' :)
        let $state := p:lookahead1W(18, $input, $state)     (: _ | ID | 'false' | 'true' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-ValidID($input, $state)
        return p:parse-RuleID-1($input, $state)
};

(:~
 : Parse RuleID.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-RuleID($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-ValidID($input, $state)
  let $state := p:parse-RuleID-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "RuleID", $count, $begin, $end)
};

(:~
 : Parse ValidID.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-ValidID($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 2) then                        (: ID :)
      let $state := p:consume(2, $input, $state)            (: ID :)
      return $state
    else if ($state[$p:l1] = 40) then                       (: 'true' :)
      let $state := p:consume(40, $input, $state)           (: 'true' :)
      return $state
    else
      let $state := p:consume(32, $input, $state)           (: 'false' :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "ValidID", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production GrammarID (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-GrammarID-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(43, $input, $state)         (: _ | ID | ',' | '.' | '@' | 'enum' | 'false' |
                                                               'fragment' | 'generate' | 'hidden' | 'import' |
                                                               'terminal' | 'true' | 'with' :)
    return
      if ($state[$p:l1] != 14) then                         (: '.' :)
        $state
      else
        let $state := p:consume(14, $input, $state)         (: '.' :)
        let $state := p:lookahead1W(18, $input, $state)     (: _ | ID | 'false' | 'true' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-ValidID($input, $state)
        return p:parse-GrammarID-1($input, $state)
};

(:~
 : Parse GrammarID.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-GrammarID($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-ValidID($input, $state)
  let $state := p:parse-GrammarID-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "GrammarID", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Grammar (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Grammar-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 12) then                           (: ',' :)
      $state
    else
      let $state := p:consume(12, $input, $state)           (: ',' :)
      let $state := p:lookahead1W(18, $input, $state)       (: _ | ID | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-GrammarID($input, $state)
      return p:parse-Grammar-1($input, $state)
};

(:~
 : Parse the 2nd loop of production Grammar (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Grammar-2($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 12) then                           (: ',' :)
      $state
    else
      let $state := p:consume(12, $input, $state)           (: ',' :)
      let $state := p:lookahead1W(18, $input, $state)       (: _ | ID | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-RuleID($input, $state)
      return p:parse-Grammar-2($input, $state)
};

(:~
 : Parse the 3rd loop of production Grammar (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Grammar-3($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(38, $input, $state)         (: _ | ID | '@' | 'enum' | 'false' | 'fragment' |
                                                               'generate' | 'import' | 'terminal' | 'true' :)
    return
      if ($state[$p:l1] != 34                               (: 'generate' :)
      and $state[$p:l1] != 37) then                         (: 'import' :)
        $state
      else
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-AbstractMetamodelDeclaration($input, $state)
        return p:parse-Grammar-3($input, $state)
};

(:~
 : Parse the 4th loop of production Grammar (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Grammar-4($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-AbstractRule($input, $state)
    let $state := p:lookahead1W(35, $input, $state)         (: _ | ID | EOF | '@' | 'enum' | 'false' | 'fragment' |
                                                               'terminal' | 'true' :)
    return
      if ($state[$p:l1] = 4) then                           (: EOF :)
        $state
      else
        p:parse-Grammar-4($input, $state)
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
  let $state := p:lookahead1W(7, $input, $state)            (: _ | 'grammar' :)
  let $state := p:consume(35, $input, $state)               (: 'grammar' :)
  let $state := p:lookahead1W(18, $input, $state)           (: _ | ID | 'false' | 'true' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-GrammarID($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 41) then                       (: 'with' :)
      let $state := p:consume(41, $input, $state)           (: 'with' :)
      let $state := p:lookahead1W(18, $input, $state)       (: _ | ID | 'false' | 'true' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-GrammarID($input, $state)
      let $state := p:parse-Grammar-1($input, $state)
      return $state
    else
      $state
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 36) then                       (: 'hidden' :)
      let $state := p:consume(36, $input, $state)           (: 'hidden' :)
      let $state := p:lookahead1W(2, $input, $state)        (: _ | '(' :)
      let $state := p:consume(7, $input, $state)            (: '(' :)
      let $state := p:lookahead1W(23, $input, $state)       (: _ | ID | ')' | 'false' | 'true' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] != 8) then                   (: ')' :)
          let $state := p:whitespace($input, $state)
          let $state :=
            if ($state[$p:error]) then
              $state
            else
              p:parse-RuleID($input, $state)
          let $state := p:parse-Grammar-2($input, $state)
          return $state
        else
          $state
      let $state := p:consume(8, $input, $state)            (: ')' :)
      return $state
    else
      $state
  let $state := p:parse-Grammar-3($input, $state)
  let $state := p:parse-Grammar-4($input, $state)
  let $state := p:consume(4, $input, $state)                (: EOF :)
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
    if ($match[1] = 1) then                                 (: _ :)
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
    $match[1] * 64 + $state[$p:l1],
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
    $match[1] * 4096 + $state[$p:lk],
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
 : Parse start symbol Grammar from given string.
 :
 : @param $s the string to be parsed.
 : @return the result as generated by parser actions.
 :)
declare function p:parse-Grammar($s as xs:string) as item()*
{
  let $state := (0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, false())
  let $state := p:parse-Grammar($s, $state)
  let $error := $state[$p:error]
  return
    if ($error) then
      element ERROR {$error/@*, p:error-message($s, $error)}
    else
      subsequence($state, $p:result)
};

(: End :)
