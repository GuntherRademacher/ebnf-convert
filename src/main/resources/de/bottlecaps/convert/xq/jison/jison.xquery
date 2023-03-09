xquery version "1.0" encoding "UTF-8";

(: This file was generated on Thu Mar 9, 2023 13:03 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net> :)
(: REx command line: -q -tree -a none -xquery -name de/bottlecaps/convert/xq/jison/jison.xquery ../../../../../../../main/java/de/bottlecaps/convert/jison/jison.ebnf :)

(:~
 : The parser that was generated for the de/bottlecaps/convert/xq/jison/jison.xquery grammar.
 :)
module namespace p="de/bottlecaps/convert/xq/jison/jison.xquery";
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
  46, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 4, 3, 3, 5, 3,
  6, 3, 3, 7, 8, 9, 10, 11, 12, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 16, 3, 3, 17, 3, 3, 18, 18, 18, 18, 19, 18,
  20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 22, 23, 3, 24, 3, 25, 26, 27, 18,
  28, 29, 30, 31, 32, 20, 33, 34, 20, 35, 36, 37, 20, 38, 39, 40, 41, 20, 20, 42, 20, 20, 43, 44, 45, 3, 3
);

(:~
 : The codepoint to charclass mapping for codepoints below the surrogate block.
 :)
declare variable $p:MAP1 as xs:integer+ :=
(
  54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
  58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 90, 122, 153, 185,
  215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215,
  215, 215, 215, 215, 215, 215, 215, 215, 215, 46, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 4, 3, 3, 5, 3, 6, 3, 3, 7, 8, 9, 10, 11, 12, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15,
  16, 3, 3, 17, 3, 18, 18, 18, 18, 19, 18, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
  20, 21, 22, 23, 3, 24, 3, 25, 26, 27, 18, 28, 29, 30, 31, 32, 20, 33, 34, 20, 35, 36, 37, 20, 38, 39, 40, 41, 20, 20,
  42, 20, 20, 43, 44, 45, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
  3
);

(:~
 : The codepoint to charclass mapping for codepoints above the surrogate block.
 :)
declare variable $p:MAP2 as xs:integer+ :=
(
  57344, 65536, 65533, 1114111, 3, 3
);

(:~
 : The token-set-id to DFA-initial-state mapping.
 :)
declare variable $p:INITIAL as xs:integer+ :=
(
  1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26
);

(:~
 : The DFA transition table.
 :)
declare variable $p:TRANSITION as xs:integer+ :=
(
  820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 754, 752, 762, 767, 775, 820, 1339,
  790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 754, 752, 762, 767, 775, 820, 1339, 815, 820, 835, 819, 821, 1344,
  1555, 1580, 820, 820, 1679, 1487, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 847, 829, 989,
  1423, 872, 820, 1457, 887, 820, 1268, 805, 820, 907, 1555, 1580, 820, 820, 1679, 1480, 934, 949, 820, 969, 982, 820,
  835, 997, 821, 1344, 1555, 1580, 820, 820, 1007, 956, 961, 1018, 820, 1339, 1043, 820, 835, 805, 821, 1344, 1555,
  1580, 820, 820, 1679, 1487, 1349, 1068, 820, 1430, 790, 820, 797, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1487,
  1490, 775, 820, 1339, 790, 1083, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1092, 1487, 1490, 775, 820, 1339, 790,
  820, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1151, 1719, 775, 820, 1339, 790, 1083, 835, 805, 821, 1344,
  1555, 1580, 820, 820, 1679, 1487, 1490, 775, 912, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 1102, 1100,
  1110, 1116, 775, 820, 1325, 790, 820, 1222, 805, 821, 1575, 1664, 1304, 820, 820, 1679, 1025, 1719, 775, 1030, 1339,
  1124, 1135, 1707, 1132, 1247, 1344, 894, 1262, 820, 820, 1679, 1050, 1719, 775, 1055, 1339, 1124, 1135, 1707, 1132,
  1247, 1344, 894, 1262, 820, 999, 1679, 1487, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820,
  820, 1679, 1144, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1490, 775,
  1159, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 1707,
  805, 1247, 1344, 894, 1262, 820, 1170, 1168, 1179, 1184, 775, 917, 1339, 790, 820, 1707, 1192, 1247, 1344, 894, 1262,
  820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 1171, 1203, 1075, 1490,
  775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1010, 1215, 820, 1392, 1230, 820,
  853, 805, 1084, 859, 1555, 1580, 820, 820, 1255, 941, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580,
  820, 820, 1679, 1487, 1719, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184,
  775, 1195, 1339, 790, 820, 1707, 805, 879, 1378, 894, 1262, 820, 1170, 1168, 1179, 1276, 1284, 820, 1325, 790, 820,
  1707, 805, 1247, 1344, 894, 1262, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 1707, 805, 1247, 1344, 1299,
  1291, 820, 1170, 1168, 1179, 1184, 775, 917, 1339, 790, 1207, 1707, 1312, 1652, 841, 1333, 1319, 820, 1170, 1168,
  1357, 1184, 775, 820, 1325, 790, 820, 782, 1365, 1247, 1344, 894, 1262, 820, 1170, 1168, 1179, 1184, 775, 820, 1339,
  790, 820, 835, 805, 1635, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 835, 805, 821,
  1435, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 1681, 835, 805, 821, 1344, 1555, 1580, 820, 1170,
  1168, 1179, 1184, 775, 820, 1339, 790, 820, 835, 805, 864, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820,
  1373, 790, 1602, 835, 1386, 821, 1344, 1693, 1580, 820, 1170, 1168, 1400, 1184, 775, 820, 1408, 1416, 820, 835, 805,
  1443, 1344, 1555, 1451, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 1605, 1465, 805, 821, 1344, 1555, 1035, 820,
  1170, 1168, 1179, 1184, 775, 820, 1570, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775,
  1136, 1473, 790, 820, 1620, 805, 821, 974, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1498, 790, 820, 835,
  1511, 821, 1344, 1544, 1060, 820, 1170, 1168, 1525, 1184, 775, 820, 1533, 790, 1160, 835, 805, 821, 1503, 926, 1580,
  820, 1170, 1168, 1179, 1184, 775, 807, 1517, 790, 922, 835, 805, 821, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184,
  775, 820, 1339, 790, 820, 835, 1552, 821, 1344, 1555, 899, 820, 820, 1244, 1237, 1563, 1588, 1540, 1595, 1613, 820,
  835, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1628, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555,
  1580, 820, 820, 1643, 1648, 1490, 1660, 820, 1339, 1672, 820, 835, 1689, 821, 1344, 1555, 1580, 820, 1701, 1715, 1715,
  820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 2075, 289, 2075, 2075, 2075, 2075, 2075, 2075, 2075,
  2075, 2075, 2075, 1830, 2075, 2075, 2075, 2075, 2075, 0, 0, 30, 53, 0, 289, 57, 58, 53, 0, 1830, 1830, 0, 0, 51, 94,
  95, 96, 97, 98, 57, 58, 53, 1852, 61, 1726, 63, 0, 0, 75, 0, 0, 53, 0, 53, 61, 63, 0, 0, 0, 0, 0, 0, 67, 0, 57, 58,
  53, 1852, 61, 0, 0, 0, 0, 0, 0, 0, 0, 53, 0, 289, 34, 30, 30, 36, 0, 0, 51, 0, 0, 53, 0, 53, 3712, 0, 101, 0, 0, 0,
  30, 31, 0, 32, 0, 0, 51, 0, 0, 54, 0, 54, 0, 0, 101, 0, 0, 0, 105, 0, 0, 0, 53, 289, 0, 58, 2432, 0, 1830, 1830, 0, 0,
  104, 0, 0, 107, 108, 53, 1408, 58, 2432, 1852, 61, 1726, 63, 0, 0, 114, 115, 101, 0, 0, 0, 101, 0, 512, 0, 0, 2816,
  2816, 0, 0, 101, 0, 0, 0, 64, 64, 0, 0, 0, 65, 65, 0, 0, 0, 87, 0, 0, 0, 0, 101, 0, 896, 384, 49, 49, 0, 0, 0, 30, 53,
  0, 0, 1830, 0, 0, 0, 0, 3584, 289, 57, 58, 53, 2944, 1830, 1830, 0, 0, 1830, 0, 0, 0, 35, 0, 0, 0, 30, 53, 0, 2944, 0,
  51, 0, 53, 0, 53, 0, 0, 101, 0, 0, 112, 57, 58, 53, 1852, 81, 1726, 63, 0, 0, 1830, 0, 30, 0, 34, 30, 81, 63, 0, 0, 0,
  0, 0, 0, 3200, 0, 0, 289, 35, 0, 0, 0, 0, 0, 52, 54, 0, 289, 57, 0, 53, 0, 1830, 1830, 0, 0, 1830, 0, 2604, 0, 0,
  2604, 0, 2605, 0, 0, 0, 101, 0, 101, 120, 0, 57, 1408, 53, 1852, 61, 1726, 63, 0, 0, 1830, 0, 2605, 0, 0, 2605, 0,
  2605, 0, 0, 0, 101, 119, 101, 0, 0, 289, 57, 58, 53, 0, 1830, 1852, 0, 0, 1830, 0, 3456, 0, 0, 3456, 84, 0, 0, 0, 0,
  0, 0, 0, 54, 0, 289, 0, 0, 0, 0, 3072, 3072, 28, 289, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 1831, 28, 28, 28, 28,
  28, 0, 0, 0, 30, 53, 0, 57, 58, 53, 1852, 61, 1726, 63, 2643, 61, 63, 2643, 2644, 0, 0, 0, 0, 0, 0, 0, 68, 3328, 0,
  1830, 3328, 0, 0, 3328, 0, 0, 1830, 41, 43, 0, 41, 43, 1726, 0, 0, 0, 0, 0, 0, 0, 91, 0, 289, 1565, 0, 0, 0, 0, 0, 0,
  0, 3456, 0, 1565, 1830, 0, 0, 0, 1565, 0, 0, 1565, 30, 53, 0, 61, 63, 65, 0, 0, 0, 0, 0, 66, 0, 0, 0, 289, 0, 3456, 0,
  0, 0, 0, 88, 0, 0, 0, 289, 57, 58, 54, 0, 1830, 1830, 0, 0, 2176, 0, 0, 53, 0, 53, 57, 58, 54, 1852, 61, 1726, 63, 0,
  0, 4096, 4138, 4096, 42, 4138, 4096, 289, 0, 0, 0, 0, 0, 0, 107, 108, 53, 0, 289, 0, 0, 0, 0, 3584, 0, 30, 53, 101, 0,
  101, 0, 0, 51, 0, 0, 2432, 0, 2432, 0, 1565, 0, 0, 1565, 30, 55, 56, 289, 57, 58, 59, 0, 1830, 1830, 0, 30, 53, 101,
  0, 101, 0, 1024, 0, 1280, 114, 115, 101, 0, 0, 0, 109, 0, 109, 0, 0, 61, 63, 65, 0, 0, 0, 3968, 0, 30, 53, 118, 0,
  101, 0, 0, 51, 30, 53, 53, 53, 0, 113, 0, 114, 115, 101, 0, 0, 0, 51, 0, 53, 0, 53, 0, 0, 101, 0, 0, 0, 51, 0, 30, 53,
  0, 0, 1565, 1830, 0, 46, 0, 1565, 46, 61, 63, 0, 0, 0, 0, 0, 100, 69, 0, 51, 0, 53, 0, 53, 0, 0, 101, 110, 0, 0, 61,
  63, 0, 0, 0, 3840, 0, 0, 51, 30, 54, 53, 54, 0, 0, 1565, 1830, 0, 47, 0, 1565, 47, 70, 0, 51, 30, 53, 53, 78, 79, 57,
  58, 80, 1852, 61, 1726, 63, 0, 34, 0, 0, 0, 2304, 2432, 0, 0, 75, 0, 53, 0, 53, 0, 0, 101, 0, 111, 0, 102, 0, 0, 0, 0,
  0, 0, 53, 1152, 0, 0, 101, 0, 101, 0, 0, 51, 30, 2432, 53, 2432, 0, 92, 0, 51, 0, 0, 53, 0, 53, 71, 0, 51, 30, 53, 53,
  53, 0, 37, 1830, 40, 0, 49, 50, 0, 0, 1830, 0, 0, 0, 0, 0, 30, 53, 0, 72, 0, 51, 0, 53, 0, 53, 0, 768, 101, 0, 0, 0,
  61, 63, 0, 0, 99, 0, 0, 0, 51, 76, 53, 77, 53, 0, 0, 1565, 1830, 0, 48, 0, 1565, 48, 73, 0, 51, 30, 53, 53, 53, 0, 63,
  0, 0, 0, 0, 0, 0, 101, 117, 0, 0, 61, 63, 0, 0, 0, 0, 0, 101, 0, 0, 0, 4138, 42, 0, 0, 0, 30, 53, 0, 74, 51, 0, 53, 0,
  53, 0, 0, 109, 0, 0, 0, 101, 0, 101, 0, 0, 289, 57, 58, 53, 0, 0, 0, 61, 61, 51, 0, 53, 0, 53, 0, 85, 86, 0, 0, 0, 0,
  0, 89, 0, 0, 57, 58, 53, 51, 61, 1726, 63, 0, 93, 51, 0, 0, 53, 0, 53, 4224, 0, 1830, 4224, 0, 0, 4224, 0, 103, 0, 0,
  0, 0, 0, 53, 0, 289, 0, 0, 4352, 0, 0, 4352, 0, 0, 0, 0, 0, 106, 107, 108, 53, 289, 57, 58, 53, 0, 0, 0, 0, 109, 0, 0,
  0, 57, 58, 53, 51, 61, 1726, 82, 0, 289, 0, 0, 0, 0, 0, 0, 90, 0, 640, 640, 0, 0, 0, 0, 0, 0, 116, 0, 0, 0, 0, 1920,
  0, 0, 2688, 0, 0, 0, 51, 94, 95, 53, 0, 53, 0, 1920, 0, 0, 0, 0, 0, 0, 1565, 30, 53, 0
);

(:~
 : The DFA-state to expected-token-set mapping.
 :)
declare variable $p:EXPECTED as xs:integer+ :=
(
  4, 20, 35, 39, 51, 55, 59, 63, 67, 71, 75, 79, 147, 86, 107, 92, 97, 101, 148, 106, 92, 111, 116, 102, 89, 93, 112,
  121, 82, 128, 120, 128, 125, 132, 136, 136, 144, 140, 138, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136,
  100352, 114688, 229376, 360448, 1146880, 2195456, 16875520, 67207168, -2147385344, 16386, 101376, 67338240, 229376,
  2457600, 142704640, 8486912, 33652736, 4311040, -2147409920, -2113826800, -200572928, 4293116, -2113823216, -66355200,
  -2143190532, 4296188, 32768, 65536, 2048, 131072, 262144, 2097152, 2359296, 268435456, 32, 4194304, 8192, 73728, 16,
  16, 524288, 524288, 268435456, 536870912, 1073741824, 40, 4194812, 528, 65536, 131072, 262144, 262144, 262144,
  2097152, 2359296, 16, 4096, 16, 524288, 524288, 40, 128, 64, 4, 256, 256, 512, 65536, 131072, 256, 512, 131072,
  262144, 262144, 256, 131072, 262144, 8, 128, 64, 4, 128, 8, 128, 128, 0, 0, 0, 0, 1, 0, 2, 1, 2, 0, 0, 2, 1024, 1024,
  2359296, 73728
);

(:~
 : The token-string table.
 :)
declare variable $p:TOKEN as xs:string+ :=
(
  "(0)",
  "CODE",
  "'%start'",
  "LEX_BLOCK",
  "ACTION",
  "'%left'",
  "'%right'",
  "'%nonassoc'",
  "'%token'",
  "'%prec'",
  "STRING",
  "ID",
  "ARROW_ACTION",
  "ACTION_BODY",
  "EOF",
  "whitespace",
  "comment",
  "string",
  "non-bnf-string",
  "number",
  "eof",
  "'""bnf""'",
  "'%%'",
  "','",
  "':'",
  "';'",
  "'['",
  "']'",
  "'false'",
  "'null'",
  "'true'",
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
                      $result mod 128,
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
    let $result := $result idiv 128
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
    let $i0 := 128 * $c1 + $current-state - 1
    let $i1 := $i0 idiv 8
    let $next-state := $p:TRANSITION[$i0 mod 8 + $p:TRANSITION[$i1 + 1] + 1]
    return
      if ($next-state > 127) then
        p:transition($input, $begin, $current, $current, $next-state, $next-state mod 128, $current-state)
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
    let $i0 := $t * 120 + $state - 1
    let $i1 := $i0 idiv 4
    let $i2 := $i1 idiv 16
    return p:token((), $p:EXPECTED[$i0 mod 4 + $p:EXPECTED[$i1 mod 16 + $p:EXPECTED[$i2 + 1] + 1] + 1], $t * 32 + 1)
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
 : Parse trailing-non-bnf-pairs.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-trailing-non-bnf-pairs($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 23) then                       (: ',' :)
      let $state := p:consume(23, $input, $state)           (: ',' :)
      let $state := p:lookahead1W(3, $input, $state)        (: whitespace | comment | non-bnf-string :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-non-bnf-pairs($input, $state)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "trailing-non-bnf-pairs", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production alternative (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-alternative-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(14, $input, $state)         (: whitespace | comment | ',' | ']' :)
    return
      if ($state[$p:l1] != 23) then                         (: ',' :)
        $state
      else
        let $state := p:consume(23, $input, $state)         (: ',' :)
        let $state := p:lookahead1W(20, $input, $state)     (: whitespace | comment | string | number | '[' | 'false' |
                                                               'null' | 'true' | '{' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-value($input, $state)
        return p:parse-alternative-1($input, $state)
};

(:~
 : Parse alternative.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-alternative($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 26) then                       (: '[' :)
      let $state := p:consume(26, $input, $state)           (: '[' :)
      let $state := p:lookahead1W(2, $input, $state)        (: whitespace | comment | string :)
      let $state := p:consume(17, $input, $state)           (: string :)
      let $state := p:parse-alternative-1($input, $state)
      let $state := p:consume(27, $input, $state)           (: ']' :)
      return $state
    else
      let $state := p:consume(17, $input, $state)           (: string :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "alternative", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production rule-pair (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-rule-pair-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(14, $input, $state)         (: whitespace | comment | ',' | ']' :)
    return
      if ($state[$p:l1] != 23) then                         (: ',' :)
        $state
      else
        let $state := p:consume(23, $input, $state)         (: ',' :)
        let $state := p:lookahead1W(11, $input, $state)     (: whitespace | comment | string | '[' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-alternative($input, $state)
        return p:parse-rule-pair-1($input, $state)
};

(:~
 : Parse rule-pair.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-rule-pair($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(17, $input, $state)               (: string :)
  let $state := p:lookahead1W(6, $input, $state)            (: whitespace | comment | ':' :)
  let $state := p:consume(24, $input, $state)               (: ':' :)
  let $state := p:lookahead1W(7, $input, $state)            (: whitespace | comment | '[' :)
  let $state := p:consume(26, $input, $state)               (: '[' :)
  let $state := p:lookahead1W(11, $input, $state)           (: whitespace | comment | string | '[' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-alternative($input, $state)
  let $state := p:parse-rule-pair-1($input, $state)
  let $state := p:consume(27, $input, $state)               (: ']' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "rule-pair", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production bnf-pair (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-bnf-pair-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(15, $input, $state)         (: whitespace | comment | ',' | '}' :)
    return
      if ($state[$p:l1] != 23) then                         (: ',' :)
        $state
      else
        let $state := p:consume(23, $input, $state)         (: ',' :)
        let $state := p:lookahead1W(2, $input, $state)      (: whitespace | comment | string :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-rule-pair($input, $state)
        return p:parse-bnf-pair-1($input, $state)
};

(:~
 : Parse bnf-pair.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-bnf-pair($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(21, $input, $state)               (: '"bnf"' :)
  let $state := p:lookahead1W(6, $input, $state)            (: whitespace | comment | ':' :)
  let $state := p:consume(24, $input, $state)               (: ':' :)
  let $state := p:lookahead1W(8, $input, $state)            (: whitespace | comment | '{' :)
  let $state := p:consume(31, $input, $state)               (: '{' :)
  let $state := p:lookahead1W(12, $input, $state)           (: whitespace | comment | string | '}' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 17) then                       (: string :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-rule-pair($input, $state)
      let $state := p:parse-bnf-pair-1($input, $state)
      return $state
    else
      $state
  let $state := p:consume(33, $input, $state)               (: '}' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "bnf-pair", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production array (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-array-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(14, $input, $state)         (: whitespace | comment | ',' | ']' :)
    return
      if ($state[$p:l1] != 23) then                         (: ',' :)
        $state
      else
        let $state := p:consume(23, $input, $state)         (: ',' :)
        let $state := p:lookahead1W(20, $input, $state)     (: whitespace | comment | string | number | '[' | 'false' |
                                                               'null' | 'true' | '{' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-value($input, $state)
        return p:parse-array-1($input, $state)
};

(:~
 : Parse array.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-array($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(26, $input, $state)               (: '[' :)
  let $state := p:lookahead1W(23, $input, $state)           (: whitespace | comment | string | number | '[' | ']' |
                                                               'false' | 'null' | 'true' | '{' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] != 27) then                      (: ']' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-value($input, $state)
      let $state := p:parse-array-1($input, $state)
      return $state
    else
      $state
  let $state := p:consume(27, $input, $state)               (: ']' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "array", $count, $begin, $end)
};

(:~
 : Parse pair.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-pair($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(17, $input, $state)               (: string :)
  let $state := p:lookahead1W(6, $input, $state)            (: whitespace | comment | ':' :)
  let $state := p:consume(24, $input, $state)               (: ':' :)
  let $state := p:lookahead1W(20, $input, $state)           (: whitespace | comment | string | number | '[' | 'false' |
                                                               'null' | 'true' | '{' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-value($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "pair", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production object (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-object-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(15, $input, $state)         (: whitespace | comment | ',' | '}' :)
    return
      if ($state[$p:l1] != 23) then                         (: ',' :)
        $state
      else
        let $state := p:consume(23, $input, $state)         (: ',' :)
        let $state := p:lookahead1W(2, $input, $state)      (: whitespace | comment | string :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-pair($input, $state)
        return p:parse-object-1($input, $state)
};

(:~
 : Parse object.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-object($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(31, $input, $state)               (: '{' :)
  let $state := p:lookahead1W(12, $input, $state)           (: whitespace | comment | string | '}' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 17) then                       (: string :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-pair($input, $state)
      let $state := p:parse-object-1($input, $state)
      return $state
    else
      $state
  let $state := p:consume(33, $input, $state)               (: '}' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "object", $count, $begin, $end)
};

(:~
 : Parse value.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-value($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 17) then                       (: string :)
      let $state := p:consume(17, $input, $state)           (: string :)
      return $state
    else if ($state[$p:l1] = 19) then                       (: number :)
      let $state := p:consume(19, $input, $state)           (: number :)
      return $state
    else if ($state[$p:l1] = 31) then                       (: '{' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-object($input, $state)
      return $state
    else if ($state[$p:l1] = 26) then                       (: '[' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-array($input, $state)
      return $state
    else if ($state[$p:l1] = 30) then                       (: 'true' :)
      let $state := p:consume(30, $input, $state)           (: 'true' :)
      return $state
    else if ($state[$p:l1] = 28) then                       (: 'false' :)
      let $state := p:consume(28, $input, $state)           (: 'false' :)
      return $state
    else
      let $state := p:consume(29, $input, $state)           (: 'null' :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "value", $count, $begin, $end)
};

(:~
 : Parse non-bnf-pair.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-non-bnf-pair($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(18, $input, $state)               (: non-bnf-string :)
  let $state := p:lookahead1W(6, $input, $state)            (: whitespace | comment | ':' :)
  let $state := p:consume(24, $input, $state)               (: ':' :)
  let $state := p:lookahead1W(20, $input, $state)           (: whitespace | comment | string | number | '[' | 'false' |
                                                               'null' | 'true' | '{' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-value($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "non-bnf-pair", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production non-bnf-pairs (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-non-bnf-pairs-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(15, $input, $state)         (: whitespace | comment | ',' | '}' :)
    let $state :=
      if ($state[$p:l1] eq 23) then                         (: ',' :)
        let $state := p:lookahead2W(13, $input, $state)     (: whitespace | comment | non-bnf-string | '"bnf"' :)
        return $state
      else
        ($state[$p:l1], subsequence($state, $p:lk + 1))
    return
      if ($state[$p:lk] != 1175) then                       (: ',' non-bnf-string :)
        $state
      else
        let $state := p:consume(23, $input, $state)         (: ',' :)
        let $state := p:lookahead1W(3, $input, $state)      (: whitespace | comment | non-bnf-string :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-non-bnf-pair($input, $state)
        return p:parse-non-bnf-pairs-1($input, $state)
};

(:~
 : Parse non-bnf-pairs.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-non-bnf-pairs($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-non-bnf-pair($input, $state)
  let $state := p:parse-non-bnf-pairs-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "non-bnf-pairs", $count, $begin, $end)
};

(:~
 : Parse leading-non-bnf-pairs.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-leading-non-bnf-pairs($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 18) then                       (: non-bnf-string :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-non-bnf-pairs($input, $state)
      let $state := p:consume(23, $input, $state)           (: ',' :)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "leading-non-bnf-pairs", $count, $begin, $end)
};

(:~
 : Parse json.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-json($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(31, $input, $state)               (: '{' :)
  let $state := p:lookahead1W(13, $input, $state)           (: whitespace | comment | non-bnf-string | '"bnf"' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-leading-non-bnf-pairs($input, $state)
  let $state := p:lookahead1W(5, $input, $state)            (: whitespace | comment | '"bnf"' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-bnf-pair($input, $state)
  let $state := p:lookahead1W(15, $input, $state)           (: whitespace | comment | ',' | '}' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-trailing-non-bnf-pairs($input, $state)
  let $state := p:consume(33, $input, $state)               (: '}' :)
  let $state := p:lookahead1W(4, $input, $state)            (: whitespace | comment | eof :)
  let $state := p:consume(20, $input, $state)               (: eof :)
  let $end := $state[$p:e0]
  return p:reduce($state, "json", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production action_body (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-action_body-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(18, $input, $state)         (: ACTION_BODY | whitespace | comment | '{' | '}' :)
    return
      if ($state[$p:l1] = 33) then                          (: '}' :)
        $state
      else
        let $state :=
          if ($state[$p:error]) then
            $state
          else if ($state[$p:l1] = 13) then                 (: ACTION_BODY :)
            let $state := p:consume(13, $input, $state)     (: ACTION_BODY :)
            return $state
          else
            let $state := p:whitespace($input, $state)
            let $state :=
              if ($state[$p:error]) then
                $state
              else
                p:parse-action_body($input, $state)
            return $state
        return p:parse-action_body-1($input, $state)
};

(:~
 : Parse action_body.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-action_body($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(31, $input, $state)               (: '{' :)
  let $state := p:parse-action_body-1($input, $state)
  let $state := p:consume(33, $input, $state)               (: '}' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "action_body", $count, $begin, $end)
};

(:~
 : Parse action.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-action($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 31) then                       (: '{' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-action_body($input, $state)
      return $state
    else if ($state[$p:l1] = 4) then                        (: ACTION :)
      let $state := p:consume(4, $input, $state)            (: ACTION :)
      return $state
    else if ($state[$p:l1] = 12) then                       (: ARROW_ACTION :)
      let $state := p:consume(12, $input, $state)           (: ARROW_ACTION :)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "action", $count, $begin, $end)
};

(:~
 : Parse prec.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-prec($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 9) then                        (: PREC :)
      let $state := p:consume(9, $input, $state)            (: PREC :)
      let $state := p:lookahead1W(10, $input, $state)       (: STRING | ID | whitespace | comment :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-symbol($input, $state)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "prec", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production handle (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-handle-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(22, $input, $state)         (: ACTION | PREC | STRING | ID | ARROW_ACTION | whitespace |
                                                               comment | ';' | '{' | '|' :)
    return
      if ($state[$p:l1] != 10                               (: STRING :)
      and $state[$p:l1] != 11) then                         (: ID :)
        $state
      else
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-symbol($input, $state)
        return p:parse-handle-1($input, $state)
};

(:~
 : Parse handle.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-handle($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-handle-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "handle", $count, $begin, $end)
};

(:~
 : Parse handle_action.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-handle_action($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-handle($input, $state)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-prec($input, $state)
  let $state := p:lookahead1W(19, $input, $state)           (: ACTION | ARROW_ACTION | whitespace | comment | ';' |
                                                               '{' | '|' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-action($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "handle_action", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production handle_list (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-handle_list-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(16, $input, $state)         (: whitespace | comment | ';' | '|' :)
    return
      if ($state[$p:l1] != 32) then                         (: '|' :)
        $state
      else
        let $state := p:consume(32, $input, $state)         (: '|' :)
        let $state := p:lookahead1W(22, $input, $state)     (: ACTION | PREC | STRING | ID | ARROW_ACTION | whitespace |
                                                               comment | ';' | '{' | '|' :)
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-handle_action($input, $state)
        return p:parse-handle_list-1($input, $state)
};

(:~
 : Parse handle_list.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-handle_list($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-handle_action($input, $state)
  let $state := p:parse-handle_list-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "handle_list", $count, $begin, $end)
};

(:~
 : Parse production.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-production($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-id($input, $state)
  let $state := p:lookahead1W(6, $input, $state)            (: whitespace | comment | ':' :)
  let $state := p:consume(24, $input, $state)               (: ':' :)
  let $state := p:lookahead1W(22, $input, $state)           (: ACTION | PREC | STRING | ID | ARROW_ACTION | whitespace |
                                                               comment | ';' | '{' | '|' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-handle_list($input, $state)
  let $state := p:consume(25, $input, $state)               (: ';' :)
  let $end := $state[$p:e0]
  return p:reduce($state, "production", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production production_list (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-production_list-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-production($input, $state)
    let $state := p:lookahead1W(17, $input, $state)         (: ID | EOF | whitespace | comment | '%%' :)
    return
      if ($state[$p:l1] != 11) then                         (: ID :)
        $state
      else
        p:parse-production_list-1($input, $state)
};

(:~
 : Parse production_list.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-production_list($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-production_list-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "production_list", $count, $begin, $end)
};

(:~
 : Parse grammar.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-grammar($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-production_list($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "grammar", $count, $begin, $end)
};

(:~
 : Parse symbol.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-symbol($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 11) then                       (: ID :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-id($input, $state)
      return $state
    else
      let $state := p:consume(10, $input, $state)           (: STRING :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "symbol", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production token_list (one or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-token_list-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-symbol($input, $state)
    let $state := p:lookahead1W(25, $input, $state)         (: START | LEX_BLOCK | ACTION | LEFT | RIGHT | NONASSOC |
                                                               TOKEN | STRING | ID | whitespace | comment | '%%' :)
    return
      if ($state[$p:l1] != 10                               (: STRING :)
      and $state[$p:l1] != 11) then                         (: ID :)
        $state
      else
        p:parse-token_list-1($input, $state)
};

(:~
 : Parse token_list.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-token_list($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-token_list-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "token_list", $count, $begin, $end)
};

(:~
 : Parse associativity.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-associativity($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 5) then                        (: LEFT :)
      let $state := p:consume(5, $input, $state)            (: LEFT :)
      return $state
    else if ($state[$p:l1] = 6) then                        (: RIGHT :)
      let $state := p:consume(6, $input, $state)            (: RIGHT :)
      return $state
    else if ($state[$p:l1] = 7) then                        (: NONASSOC :)
      let $state := p:consume(7, $input, $state)            (: NONASSOC :)
      return $state
    else
      let $state := p:consume(8, $input, $state)            (: TOKEN :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "associativity", $count, $begin, $end)
};

(:~
 : Parse operator.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-operator($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-associativity($input, $state)
  let $state := p:lookahead1W(10, $input, $state)           (: STRING | ID | whitespace | comment :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-token_list($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "operator", $count, $begin, $end)
};

(:~
 : Parse id.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-id($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(11, $input, $state)               (: ID :)
  let $end := $state[$p:e0]
  return p:reduce($state, "id", $count, $begin, $end)
};

(:~
 : Parse declaration.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-declaration($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 2) then                        (: START :)
      let $state := p:consume(2, $input, $state)            (: START :)
      let $state := p:lookahead1W(0, $input, $state)        (: ID | whitespace | comment :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-id($input, $state)
      return $state
    else if ($state[$p:l1] = 3) then                        (: LEX_BLOCK :)
      let $state := p:consume(3, $input, $state)            (: LEX_BLOCK :)
      return $state
    else if ($state[$p:l1] = 4) then                        (: ACTION :)
      let $state := p:consume(4, $input, $state)            (: ACTION :)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-operator($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "declaration", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production declaration_list (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-declaration_list-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(21, $input, $state)         (: START | LEX_BLOCK | ACTION | LEFT | RIGHT | NONASSOC |
                                                               TOKEN | whitespace | comment | '%%' :)
    return
      if ($state[$p:l1] = 22) then                          (: '%%' :)
        $state
      else
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-declaration($input, $state)
        return p:parse-declaration_list-1($input, $state)
};

(:~
 : Parse declaration_list.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-declaration_list($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-declaration_list-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "declaration_list", $count, $begin, $end)
};

(:~
 : Parse spec.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-spec($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-declaration_list($input, $state)
  let $state := p:consume(22, $input, $state)               (: '%%' :)
  let $state := p:lookahead1W(0, $input, $state)            (: ID | whitespace | comment :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-grammar($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 22) then                       (: '%%' :)
      let $state := p:consume(22, $input, $state)           (: '%%' :)
      let $state := p:lookahead1W(9, $input, $state)        (: CODE | EOF | whitespace | comment :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else if ($state[$p:l1] = 1) then                    (: CODE :)
          let $state := p:consume(1, $input, $state)        (: CODE :)
          return $state
        else
          $state
      return $state
    else
      $state
  let $state := p:lookahead1W(1, $input, $state)            (: EOF | whitespace | comment :)
  let $state := p:consume(14, $input, $state)               (: EOF :)
  let $end := $state[$p:e0]
  return p:reduce($state, "spec", $count, $begin, $end)
};

(:~
 : Parse jison.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-jison($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:lookahead1W(24, $input, $state)           (: START | LEX_BLOCK | ACTION | LEFT | RIGHT | NONASSOC |
                                                               TOKEN | whitespace | comment | '%%' | '{' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 31) then                       (: '{' :)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-json($input, $state)
      return $state
    else
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-spec($input, $state)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "jison", $count, $begin, $end)
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
    if ($match[1] = 15                                      (: whitespace :)
     or $match[1] = 16) then                                (: comment :)
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
    $match[1] * 64 + $state[$p:l1],
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
 : Parse start symbol jison from given string.
 :
 : @param $s the string to be parsed.
 : @return the result as generated by parser actions.
 :)
declare function p:parse-jison($s as xs:string) as item()*
{
  let $state := (0, 1, 1, 0, 0, 0, 0, 0, 0, false())
  let $state := p:parse-jison($s, $state)
  let $error := $state[$p:error]
  return
    if ($error) then
      element ERROR {$error/@*, p:error-message($s, $error)}
    else
      subsequence($state, $p:result)
};

(: End :)
