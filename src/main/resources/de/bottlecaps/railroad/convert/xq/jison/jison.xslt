<?xml version="1.0" encoding="UTF-8"?>
<!-- This file was generated on Wed Dec 21, 2022 23:14 (UTC+01) by REx v5.56 which is Copyright (c) 1979-2022 by Gunther Rademacher <grd@gmx.net> -->
<!-- REx command line: -tree -a none -xslt -name de/bottlecaps/railroad/convert/xq/jison/jison.xslt ..\..\..\..\..\..\..\..\main\java\de\bottlecaps\railroad\convert\jison\jison.ebnf -->

<xsl:stylesheet version="2.0"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="de/bottlecaps/railroad/convert/xq/jison/jison.xslt">
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
    46, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 4, 3, 3, 5, 3, 6, 3, 3, 7, 8, 9, 10, 11, 12, 13, 14,
    14, 14, 14, 14, 14, 14, 14, 14, 15, 16, 3, 3, 17, 3, 3, 18, 18, 18, 18, 19, 18, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
    20, 21, 22, 23, 3, 24, 3, 25, 26, 27, 18, 28, 29, 30, 31, 32, 20, 33, 34, 20, 35, 36, 37, 20, 38, 39, 40, 41, 20, 20, 42, 20, 20, 43, 44, 45, 3, 3
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints below the surrogate block.
  -->
  <xsl:variable name="p:MAP1" as="xs:integer+" select="
    54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 90, 122, 153, 185, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215,
    215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 46, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 4, 3, 3, 5, 3, 6, 3, 3, 7, 8, 9, 10, 11, 12, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 16, 3, 3, 17, 3, 18, 18, 18, 18,
    19, 18, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 22, 23, 3, 24, 3, 25, 26, 27, 18, 28, 29, 30, 31, 32, 20, 33,
    34, 20, 35, 36, 37, 20, 38, 39, 40, 41, 20, 20, 42, 20, 20, 43, 44, 45, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
    3, 3, 3, 3
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints above the surrogate block.
  -->
  <xsl:variable name="p:MAP2" as="xs:integer+" select="
    57344, 65536, 65533, 1114111, 3, 3
  "/>

  <!--~
   ! The token-set-id to DFA-initial-state mapping.
  -->
  <xsl:variable name="p:INITIAL" as="xs:integer+" select="
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26
  "/>

  <!--~
   ! The DFA transition table.
  -->
  <xsl:variable name="p:TRANSITION" as="xs:integer+" select="
    820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 754, 752, 762, 767, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555,
    1580, 820, 754, 752, 762, 767, 775, 820, 1339, 815, 820, 835, 819, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1490, 775, 820, 1339, 790, 820, 835, 805,
    821, 1344, 1555, 1580, 820, 847, 829, 989, 1423, 872, 820, 1457, 887, 820, 1268, 805, 820, 907, 1555, 1580, 820, 820, 1679, 1480, 934, 949, 820, 969, 982,
    820, 835, 997, 821, 1344, 1555, 1580, 820, 820, 1007, 956, 961, 1018, 820, 1339, 1043, 820, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1349,
    1068, 820, 1430, 790, 820, 797, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1490, 775, 820, 1339, 790, 1083, 835, 805, 821, 1344, 1555, 1580, 820,
    820, 1092, 1487, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1151, 1719, 775, 820, 1339, 790, 1083, 835, 805, 821,
    1344, 1555, 1580, 820, 820, 1679, 1487, 1490, 775, 912, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 1102, 1100, 1110, 1116, 775, 820, 1325, 790,
    820, 1222, 805, 821, 1575, 1664, 1304, 820, 820, 1679, 1025, 1719, 775, 1030, 1339, 1124, 1135, 1707, 1132, 1247, 1344, 894, 1262, 820, 820, 1679, 1050,
    1719, 775, 1055, 1339, 1124, 1135, 1707, 1132, 1247, 1344, 894, 1262, 820, 999, 1679, 1487, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580,
    820, 820, 1679, 1144, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1490, 775, 1159, 1339, 790, 820, 835, 805, 821,
    1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 1707, 805, 1247, 1344, 894, 1262, 820, 1170, 1168, 1179, 1184, 775, 917, 1339, 790,
    820, 1707, 1192, 1247, 1344, 894, 1262, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 1171, 1203, 1075, 1490,
    775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1010, 1215, 820, 1392, 1230, 820, 853, 805, 1084, 859, 1555, 1580, 820,
    820, 1255, 941, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1719, 775, 820, 1339, 790, 820, 835, 805, 821, 1344,
    1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 1195, 1339, 790, 820, 1707, 805, 879, 1378, 894, 1262, 820, 1170, 1168, 1179, 1276, 1284, 820, 1325, 790, 820,
    1707, 805, 1247, 1344, 894, 1262, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 1707, 805, 1247, 1344, 1299, 1291, 820, 1170, 1168, 1179, 1184,
    775, 917, 1339, 790, 1207, 1707, 1312, 1652, 841, 1333, 1319, 820, 1170, 1168, 1357, 1184, 775, 820, 1325, 790, 820, 782, 1365, 1247, 1344, 894, 1262, 820,
    1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 835, 805, 1635, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 835, 805, 821,
    1435, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 1681, 835, 805, 821, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790,
    820, 835, 805, 864, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1373, 790, 1602, 835, 1386, 821, 1344, 1693, 1580, 820, 1170, 1168, 1400, 1184,
    775, 820, 1408, 1416, 820, 835, 805, 1443, 1344, 1555, 1451, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 1605, 1465, 805, 821, 1344, 1555, 1035, 820,
    1170, 1168, 1179, 1184, 775, 820, 1570, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 1136, 1473, 790, 820, 1620, 805, 821,
    974, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1498, 790, 820, 835, 1511, 821, 1344, 1544, 1060, 820, 1170, 1168, 1525, 1184, 775, 820, 1533, 790,
    1160, 835, 805, 821, 1503, 926, 1580, 820, 1170, 1168, 1179, 1184, 775, 807, 1517, 790, 922, 835, 805, 821, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184,
    775, 820, 1339, 790, 820, 835, 1552, 821, 1344, 1555, 899, 820, 820, 1244, 1237, 1563, 1588, 1540, 1595, 1613, 820, 835, 805, 821, 1344, 1555, 1580, 820,
    820, 1679, 1628, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1643, 1648, 1490, 1660, 820, 1339, 1672, 820, 835, 1689, 821,
    1344, 1555, 1580, 820, 1701, 1715, 1715, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 2075, 289, 2075, 2075, 2075, 2075, 2075, 2075,
    2075, 2075, 2075, 2075, 1830, 2075, 2075, 2075, 2075, 2075, 0, 0, 30, 53, 0, 289, 57, 58, 53, 0, 1830, 1830, 0, 0, 51, 94, 95, 96, 97, 98, 57, 58, 53, 1852,
    61, 1726, 63, 0, 0, 75, 0, 0, 53, 0, 53, 61, 63, 0, 0, 0, 0, 0, 0, 67, 0, 57, 58, 53, 1852, 61, 0, 0, 0, 0, 0, 0, 0, 0, 53, 0, 289, 34, 30, 30, 36, 0, 0,
    51, 0, 0, 53, 0, 53, 3712, 0, 101, 0, 0, 0, 30, 31, 0, 32, 0, 0, 51, 0, 0, 54, 0, 54, 0, 0, 101, 0, 0, 0, 105, 0, 0, 0, 53, 289, 0, 58, 2432, 0, 1830, 1830,
    0, 0, 104, 0, 0, 107, 108, 53, 1408, 58, 2432, 1852, 61, 1726, 63, 0, 0, 114, 115, 101, 0, 0, 0, 101, 0, 512, 0, 0, 2816, 2816, 0, 0, 101, 0, 0, 0, 64, 64,
    0, 0, 0, 65, 65, 0, 0, 0, 87, 0, 0, 0, 0, 101, 0, 896, 384, 49, 49, 0, 0, 0, 30, 53, 0, 0, 1830, 0, 0, 0, 0, 3584, 289, 57, 58, 53, 2944, 1830, 1830, 0, 0,
    1830, 0, 0, 0, 35, 0, 0, 0, 30, 53, 0, 2944, 0, 51, 0, 53, 0, 53, 0, 0, 101, 0, 0, 112, 57, 58, 53, 1852, 81, 1726, 63, 0, 0, 1830, 0, 30, 0, 34, 30, 81,
    63, 0, 0, 0, 0, 0, 0, 3200, 0, 0, 289, 35, 0, 0, 0, 0, 0, 52, 54, 0, 289, 57, 0, 53, 0, 1830, 1830, 0, 0, 1830, 0, 2604, 0, 0, 2604, 0, 2605, 0, 0, 0, 101,
    0, 101, 120, 0, 57, 1408, 53, 1852, 61, 1726, 63, 0, 0, 1830, 0, 2605, 0, 0, 2605, 0, 2605, 0, 0, 0, 101, 119, 101, 0, 0, 289, 57, 58, 53, 0, 1830, 1852, 0,
    0, 1830, 0, 3456, 0, 0, 3456, 84, 0, 0, 0, 0, 0, 0, 0, 54, 0, 289, 0, 0, 0, 0, 3072, 3072, 28, 289, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 1831, 28, 28,
    28, 28, 28, 0, 0, 0, 30, 53, 0, 57, 58, 53, 1852, 61, 1726, 63, 2643, 61, 63, 2643, 2644, 0, 0, 0, 0, 0, 0, 0, 68, 3328, 0, 1830, 3328, 0, 0, 3328, 0, 0,
    1830, 41, 43, 0, 41, 43, 1726, 0, 0, 0, 0, 0, 0, 0, 91, 0, 289, 1565, 0, 0, 0, 0, 0, 0, 0, 3456, 0, 1565, 1830, 0, 0, 0, 1565, 0, 0, 1565, 30, 53, 0, 61,
    63, 65, 0, 0, 0, 0, 0, 66, 0, 0, 0, 289, 0, 3456, 0, 0, 0, 0, 88, 0, 0, 0, 289, 57, 58, 54, 0, 1830, 1830, 0, 0, 2176, 0, 0, 53, 0, 53, 57, 58, 54, 1852,
    61, 1726, 63, 0, 0, 4096, 4138, 4096, 42, 4138, 4096, 289, 0, 0, 0, 0, 0, 0, 107, 108, 53, 0, 289, 0, 0, 0, 0, 3584, 0, 30, 53, 101, 0, 101, 0, 0, 51, 0, 0,
    2432, 0, 2432, 0, 1565, 0, 0, 1565, 30, 55, 56, 289, 57, 58, 59, 0, 1830, 1830, 0, 30, 53, 101, 0, 101, 0, 1024, 0, 1280, 114, 115, 101, 0, 0, 0, 109, 0,
    109, 0, 0, 61, 63, 65, 0, 0, 0, 3968, 0, 30, 53, 118, 0, 101, 0, 0, 51, 30, 53, 53, 53, 0, 113, 0, 114, 115, 101, 0, 0, 0, 51, 0, 53, 0, 53, 0, 0, 101, 0,
    0, 0, 51, 0, 30, 53, 0, 0, 1565, 1830, 0, 46, 0, 1565, 46, 61, 63, 0, 0, 0, 0, 0, 100, 69, 0, 51, 0, 53, 0, 53, 0, 0, 101, 110, 0, 0, 61, 63, 0, 0, 0, 3840,
    0, 0, 51, 30, 54, 53, 54, 0, 0, 1565, 1830, 0, 47, 0, 1565, 47, 70, 0, 51, 30, 53, 53, 78, 79, 57, 58, 80, 1852, 61, 1726, 63, 0, 34, 0, 0, 0, 2304, 2432,
    0, 0, 75, 0, 53, 0, 53, 0, 0, 101, 0, 111, 0, 102, 0, 0, 0, 0, 0, 0, 53, 1152, 0, 0, 101, 0, 101, 0, 0, 51, 30, 2432, 53, 2432, 0, 92, 0, 51, 0, 0, 53, 0,
    53, 71, 0, 51, 30, 53, 53, 53, 0, 37, 1830, 40, 0, 49, 50, 0, 0, 1830, 0, 0, 0, 0, 0, 30, 53, 0, 72, 0, 51, 0, 53, 0, 53, 0, 768, 101, 0, 0, 0, 61, 63, 0,
    0, 99, 0, 0, 0, 51, 76, 53, 77, 53, 0, 0, 1565, 1830, 0, 48, 0, 1565, 48, 73, 0, 51, 30, 53, 53, 53, 0, 63, 0, 0, 0, 0, 0, 0, 101, 117, 0, 0, 61, 63, 0, 0,
    0, 0, 0, 101, 0, 0, 0, 4138, 42, 0, 0, 0, 30, 53, 0, 74, 51, 0, 53, 0, 53, 0, 0, 109, 0, 0, 0, 101, 0, 101, 0, 0, 289, 57, 58, 53, 0, 0, 0, 61, 61, 51, 0,
    53, 0, 53, 0, 85, 86, 0, 0, 0, 0, 0, 89, 0, 0, 57, 58, 53, 51, 61, 1726, 63, 0, 93, 51, 0, 0, 53, 0, 53, 4224, 0, 1830, 4224, 0, 0, 4224, 0, 103, 0, 0, 0,
    0, 0, 53, 0, 289, 0, 0, 4352, 0, 0, 4352, 0, 0, 0, 0, 0, 106, 107, 108, 53, 289, 57, 58, 53, 0, 0, 0, 0, 109, 0, 0, 0, 57, 58, 53, 51, 61, 1726, 82, 0, 289,
    0, 0, 0, 0, 0, 0, 90, 0, 640, 640, 0, 0, 0, 0, 0, 0, 116, 0, 0, 0, 0, 1920, 0, 0, 2688, 0, 0, 0, 51, 94, 95, 53, 0, 53, 0, 1920, 0, 0, 0, 0, 0, 0, 1565, 30,
    53, 0
  "/>

  <!--~
   ! The DFA-state to expected-token-set mapping.
  -->
  <xsl:variable name="p:EXPECTED" as="xs:integer+" select="
    4, 20, 35, 39, 51, 55, 59, 63, 67, 71, 75, 79, 147, 86, 107, 92, 97, 101, 148, 106, 92, 111, 116, 102, 89, 93, 112, 121, 82, 128, 120, 128, 125, 132, 136,
    136, 144, 140, 138, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 100352, 114688, 229376, 360448, 1146880, 2195456, 16875520, 67207168,
    -2147385344, 16386, 101376, 67338240, 229376, 2457600, 142704640, 8486912, 33652736, 4311040, -2147409920, -2113826800, -200572928, 4293116, -2113823216,
    -66355200, -2143190532, 4296188, 32768, 65536, 2048, 131072, 262144, 2097152, 2359296, 268435456, 32, 4194304, 8192, 73728, 16, 16, 524288, 524288,
    268435456, 536870912, 1073741824, 40, 4194812, 528, 65536, 131072, 262144, 262144, 262144, 2097152, 2359296, 16, 4096, 16, 524288, 524288, 40, 128, 64, 4,
    256, 256, 512, 65536, 131072, 256, 512, 131072, 262144, 262144, 256, 131072, 262144, 8, 128, 64, 4, 128, 8, 128, 128, 0, 0, 0, 0, 1, 0, 2, 1, 2, 0, 0, 2,
    1024, 1024, 2359296, 73728
  "/>

  <!--~
   ! The token-string table.
  -->
  <xsl:variable name="p:TOKEN" as="xs:string+" select="
    '(0)',
    'CODE',
    &quot;'%start'&quot;,
    'LEX_BLOCK',
    'ACTION',
    &quot;'%left'&quot;,
    &quot;'%right'&quot;,
    &quot;'%nonassoc'&quot;,
    &quot;'%token'&quot;,
    &quot;'%prec'&quot;,
    'STRING',
    'ID',
    'ARROW_ACTION',
    'ACTION_BODY',
    'EOF',
    'whitespace',
    'comment',
    'string',
    'non-bnf-string',
    'number',
    'eof',
    &quot;'&quot;&quot;bnf&quot;&quot;'&quot;,
    &quot;'%%'&quot;,
    &quot;','&quot;,
    &quot;':'&quot;,
    &quot;';'&quot;,
    &quot;'['&quot;,
    &quot;']'&quot;,
    &quot;'false'&quot;,
    &quot;'null'&quot;,
    &quot;'true'&quot;,
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
    <xsl:sequence select="p:transition($input, $begin, $begin, $begin, $result, $result mod 128, 0)"/>
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
        <xsl:variable name="result" select="$result idiv 128"/>
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
        <xsl:variable name="i0" select="128 * $c1 + $current-state - 1"/>
        <xsl:variable name="i1" select="$i0 idiv 8"/>
        <xsl:variable name="next-state" select="$p:TRANSITION[$i0 mod 8 + $p:TRANSITION[$i1 + 1] + 1]"/>
        <xsl:sequence select="
          if ($next-state &gt; 127) then
            p:transition($input, $begin, $current, $current, $next-state, $next-state mod 128, $current-state)
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
        <xsl:variable name="i0" select=". * 120 + $state - 1"/>
        <xsl:variable name="i1" select="$i0 idiv 4"/>
        <xsl:variable name="i2" select="$i1 idiv 16"/>
        <xsl:sequence select="p:token((), $p:EXPECTED[$i0 mod 4 + $p:EXPECTED[$i1 mod 16 + $p:EXPECTED[$i2 + 1] + 1] + 1], . * 32 + 1)"/>
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
   ! Parse trailing-non-bnf-pairs.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-trailing-non-bnf-pairs" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 23">                                        <!-- ',' -->
          <xsl:variable name="state" select="p:consume(23, $input, $state)"/>       <!-- ',' -->
          <xsl:variable name="state" select="p:lookahead1W(3, $input, $state)"/>    <!-- whitespace | comment | non-bnf-string -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-non-bnf-pairs($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'trailing-non-bnf-pairs', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production alternative (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-alternative-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(14, $input, $state)"/>     <!-- whitespace | comment | ',' | ']' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 23">                                     <!-- ',' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(23, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(20, $input, $state)"/> <!-- whitespace | comment | string | number | '[' | 'false' | 'null' |
                                                                                         'true' | '{' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-value($input, $state)
            "/>
            <xsl:sequence select="p:parse-alternative-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse alternative.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-alternative" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 26">                                        <!-- '[' -->
          <xsl:variable name="state" select="p:consume(26, $input, $state)"/>       <!-- '[' -->
          <xsl:variable name="state" select="p:lookahead1W(2, $input, $state)"/>    <!-- whitespace | comment | string -->
          <xsl:variable name="state" select="p:consume(17, $input, $state)"/>       <!-- string -->
          <xsl:variable name="state" select="p:parse-alternative-1($input, $state)"/>
          <xsl:variable name="state" select="p:consume(27, $input, $state)"/>       <!-- ']' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(17, $input, $state)"/>       <!-- string -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'alternative', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production rule-pair (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-rule-pair-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(14, $input, $state)"/>     <!-- whitespace | comment | ',' | ']' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 23">                                     <!-- ',' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(23, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(11, $input, $state)"/> <!-- whitespace | comment | string | '[' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-alternative($input, $state)
            "/>
            <xsl:sequence select="p:parse-rule-pair-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse rule-pair.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-rule-pair" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(17, $input, $state)"/>             <!-- string -->
    <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>          <!-- whitespace | comment | ':' -->
    <xsl:variable name="state" select="p:consume(24, $input, $state)"/>             <!-- ':' -->
    <xsl:variable name="state" select="p:lookahead1W(7, $input, $state)"/>          <!-- whitespace | comment | '[' -->
    <xsl:variable name="state" select="p:consume(26, $input, $state)"/>             <!-- '[' -->
    <xsl:variable name="state" select="p:lookahead1W(11, $input, $state)"/>         <!-- whitespace | comment | string | '[' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-alternative($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-rule-pair-1($input, $state)"/>
    <xsl:variable name="state" select="p:consume(27, $input, $state)"/>             <!-- ']' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'rule-pair', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production bnf-pair (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-bnf-pair-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>     <!-- whitespace | comment | ',' | '}' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 23">                                     <!-- ',' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(23, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(2, $input, $state)"/>  <!-- whitespace | comment | string -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-rule-pair($input, $state)
            "/>
            <xsl:sequence select="p:parse-bnf-pair-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse bnf-pair.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-bnf-pair" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(21, $input, $state)"/>             <!-- '"bnf"' -->
    <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>          <!-- whitespace | comment | ':' -->
    <xsl:variable name="state" select="p:consume(24, $input, $state)"/>             <!-- ':' -->
    <xsl:variable name="state" select="p:lookahead1W(8, $input, $state)"/>          <!-- whitespace | comment | '{' -->
    <xsl:variable name="state" select="p:consume(31, $input, $state)"/>             <!-- '{' -->
    <xsl:variable name="state" select="p:lookahead1W(12, $input, $state)"/>         <!-- whitespace | comment | string | '}' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 17">                                        <!-- string -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-rule-pair($input, $state)
          "/>
          <xsl:variable name="state" select="p:parse-bnf-pair-1($input, $state)"/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:consume(33, $input, $state)"/>             <!-- '}' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'bnf-pair', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production array (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-array-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(14, $input, $state)"/>     <!-- whitespace | comment | ',' | ']' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 23">                                     <!-- ',' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(23, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(20, $input, $state)"/> <!-- whitespace | comment | string | number | '[' | 'false' | 'null' |
                                                                                         'true' | '{' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-value($input, $state)
            "/>
            <xsl:sequence select="p:parse-array-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse array.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-array" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(26, $input, $state)"/>             <!-- '[' -->
    <xsl:variable name="state" select="p:lookahead1W(23, $input, $state)"/>         <!-- whitespace | comment | string | number | '[' | ']' | 'false' | 'null' |
                                                                                         'true' | '{' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] != 27">                                       <!-- ']' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-value($input, $state)
          "/>
          <xsl:variable name="state" select="p:parse-array-1($input, $state)"/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:consume(27, $input, $state)"/>             <!-- ']' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'array', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse pair.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-pair" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(17, $input, $state)"/>             <!-- string -->
    <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>          <!-- whitespace | comment | ':' -->
    <xsl:variable name="state" select="p:consume(24, $input, $state)"/>             <!-- ':' -->
    <xsl:variable name="state" select="p:lookahead1W(20, $input, $state)"/>         <!-- whitespace | comment | string | number | '[' | 'false' | 'null' |
                                                                                         'true' | '{' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-value($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'pair', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production object (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-object-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>     <!-- whitespace | comment | ',' | '}' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 23">                                     <!-- ',' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(23, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(2, $input, $state)"/>  <!-- whitespace | comment | string -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-pair($input, $state)
            "/>
            <xsl:sequence select="p:parse-object-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse object.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-object" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(31, $input, $state)"/>             <!-- '{' -->
    <xsl:variable name="state" select="p:lookahead1W(12, $input, $state)"/>         <!-- whitespace | comment | string | '}' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 17">                                        <!-- string -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-pair($input, $state)
          "/>
          <xsl:variable name="state" select="p:parse-object-1($input, $state)"/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:consume(33, $input, $state)"/>             <!-- '}' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'object', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse value.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-value" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 17">                                        <!-- string -->
          <xsl:variable name="state" select="p:consume(17, $input, $state)"/>       <!-- string -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 19">                                        <!-- number -->
          <xsl:variable name="state" select="p:consume(19, $input, $state)"/>       <!-- number -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 31">                                        <!-- '{' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-object($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 26">                                        <!-- '[' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-array($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 30">                                        <!-- 'true' -->
          <xsl:variable name="state" select="p:consume(30, $input, $state)"/>       <!-- 'true' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 28">                                        <!-- 'false' -->
          <xsl:variable name="state" select="p:consume(28, $input, $state)"/>       <!-- 'false' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(29, $input, $state)"/>       <!-- 'null' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'value', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse non-bnf-pair.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-non-bnf-pair" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(18, $input, $state)"/>             <!-- non-bnf-string -->
    <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>          <!-- whitespace | comment | ':' -->
    <xsl:variable name="state" select="p:consume(24, $input, $state)"/>             <!-- ':' -->
    <xsl:variable name="state" select="p:lookahead1W(20, $input, $state)"/>         <!-- whitespace | comment | string | number | '[' | 'false' | 'null' |
                                                                                         'true' | '{' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-value($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'non-bnf-pair', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production non-bnf-pairs (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-non-bnf-pairs-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>     <!-- whitespace | comment | ',' | '}' -->
        <xsl:variable name="state" as="item()+">
          <xsl:choose>
            <xsl:when test="$state[$p:l1] eq 23">                                   <!-- ',' -->
              <xsl:variable name="state" select="p:lookahead2W(13, $input, $state)"/> <!-- whitespace | comment | non-bnf-string | '"bnf"' -->
              <xsl:sequence select="$state"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="$state[$p:l1], subsequence($state, $p:lk + 1)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:choose>
          <xsl:when test="$state[$p:lk] != 1175">                                   <!-- ',' non-bnf-string -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(23, $input, $state)"/>     <!-- ',' -->
            <xsl:variable name="state" select="p:lookahead1W(3, $input, $state)"/>  <!-- whitespace | comment | non-bnf-string -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-non-bnf-pair($input, $state)
            "/>
            <xsl:sequence select="p:parse-non-bnf-pairs-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse non-bnf-pairs.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-non-bnf-pairs" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-non-bnf-pair($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-non-bnf-pairs-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'non-bnf-pairs', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse leading-non-bnf-pairs.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-leading-non-bnf-pairs" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 18">                                        <!-- non-bnf-string -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-non-bnf-pairs($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(23, $input, $state)"/>       <!-- ',' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'leading-non-bnf-pairs', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse json.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-json" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(31, $input, $state)"/>             <!-- '{' -->
    <xsl:variable name="state" select="p:lookahead1W(13, $input, $state)"/>         <!-- whitespace | comment | non-bnf-string | '"bnf"' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-leading-non-bnf-pairs($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(5, $input, $state)"/>          <!-- whitespace | comment | '"bnf"' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-bnf-pair($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>         <!-- whitespace | comment | ',' | '}' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-trailing-non-bnf-pairs($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(33, $input, $state)"/>             <!-- '}' -->
    <xsl:variable name="state" select="p:lookahead1W(4, $input, $state)"/>          <!-- whitespace | comment | eof -->
    <xsl:variable name="state" select="p:consume(20, $input, $state)"/>             <!-- eof -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'json', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production action_body (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-action_body-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(18, $input, $state)"/>     <!-- ACTION_BODY | whitespace | comment | '{' | '}' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 33">                                      <!-- '}' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" as="item()+">
              <xsl:choose>
                <xsl:when test="$state[$p:error]">
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:when test="$state[$p:l1] = 13">                                <!-- ACTION_BODY -->
                  <xsl:variable name="state" select="p:consume(13, $input, $state)"/> <!-- ACTION_BODY -->
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:variable name="state" select="p:whitespace($input, $state)"/>
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:parse-action_body($input, $state)
                  "/>
                  <xsl:sequence select="$state"/>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:variable>
            <xsl:sequence select="p:parse-action_body-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse action_body.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-action_body" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(31, $input, $state)"/>             <!-- '{' -->
    <xsl:variable name="state" select="p:parse-action_body-1($input, $state)"/>
    <xsl:variable name="state" select="p:consume(33, $input, $state)"/>             <!-- '}' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'action_body', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse action.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-action" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 31">                                        <!-- '{' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-action_body($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 4">                                         <!-- ACTION -->
          <xsl:variable name="state" select="p:consume(4, $input, $state)"/>        <!-- ACTION -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 12">                                        <!-- ARROW_ACTION -->
          <xsl:variable name="state" select="p:consume(12, $input, $state)"/>       <!-- ARROW_ACTION -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'action', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse prec.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-prec" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 9">                                         <!-- PREC -->
          <xsl:variable name="state" select="p:consume(9, $input, $state)"/>        <!-- PREC -->
          <xsl:variable name="state" select="p:lookahead1W(10, $input, $state)"/>   <!-- STRING | ID | whitespace | comment -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-symbol($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'prec', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production handle (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-handle-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(22, $input, $state)"/>     <!-- ACTION | PREC | STRING | ID | ARROW_ACTION | whitespace | comment |
                                                                                         ';' | '{' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 10                                         (: STRING :)
                      and $state[$p:l1] != 11">                                     <!-- ID -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-symbol($input, $state)
            "/>
            <xsl:sequence select="p:parse-handle-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse handle.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-handle" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-handle-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'handle', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse handle_action.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-handle_action" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-handle($input, $state)
    "/>
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-prec($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(19, $input, $state)"/>         <!-- ACTION | ARROW_ACTION | whitespace | comment | ';' | '{' | '|' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-action($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'handle_action', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production handle_list (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-handle_list-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(16, $input, $state)"/>     <!-- whitespace | comment | ';' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 32">                                     <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(32, $input, $state)"/>     <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(22, $input, $state)"/> <!-- ACTION | PREC | STRING | ID | ARROW_ACTION | whitespace | comment |
                                                                                         ';' | '{' | '|' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-handle_action($input, $state)
            "/>
            <xsl:sequence select="p:parse-handle_list-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse handle_list.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-handle_list" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-handle_action($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-handle_list-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'handle_list', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse production.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-production" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-id($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>          <!-- whitespace | comment | ':' -->
    <xsl:variable name="state" select="p:consume(24, $input, $state)"/>             <!-- ':' -->
    <xsl:variable name="state" select="p:lookahead1W(22, $input, $state)"/>         <!-- ACTION | PREC | STRING | ID | ARROW_ACTION | whitespace | comment |
                                                                                         ';' | '{' | '|' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-handle_list($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(25, $input, $state)"/>             <!-- ';' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'production', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production production_list (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-production_list-1">
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
            p:parse-production($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(17, $input, $state)"/>     <!-- ID | EOF | whitespace | comment | '%%' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 11">                                     <!-- ID -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-production_list-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse production_list.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-production_list" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-production_list-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'production_list', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse grammar.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-grammar" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-production_list($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'grammar', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse symbol.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-symbol" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 11">                                        <!-- ID -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-id($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(10, $input, $state)"/>       <!-- STRING -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'symbol', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production token_list (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-token_list-1">
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
            p:parse-symbol($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(25, $input, $state)"/>     <!-- START | LEX_BLOCK | ACTION | LEFT | RIGHT | NONASSOC | TOKEN | STRING |
                                                                                         ID | whitespace | comment | '%%' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 10                                         (: STRING :)
                      and $state[$p:l1] != 11">                                     <!-- ID -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-token_list-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse token_list.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-token_list" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-token_list-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'token_list', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse associativity.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-associativity" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 5">                                         <!-- LEFT -->
          <xsl:variable name="state" select="p:consume(5, $input, $state)"/>        <!-- LEFT -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 6">                                         <!-- RIGHT -->
          <xsl:variable name="state" select="p:consume(6, $input, $state)"/>        <!-- RIGHT -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 7">                                         <!-- NONASSOC -->
          <xsl:variable name="state" select="p:consume(7, $input, $state)"/>        <!-- NONASSOC -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(8, $input, $state)"/>        <!-- TOKEN -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'associativity', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse operator.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-operator" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-associativity($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(10, $input, $state)"/>         <!-- STRING | ID | whitespace | comment -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-token_list($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'operator', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse id.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-id" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(11, $input, $state)"/>             <!-- ID -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'id', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse declaration.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-declaration" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 2">                                         <!-- START -->
          <xsl:variable name="state" select="p:consume(2, $input, $state)"/>        <!-- START -->
          <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/>    <!-- ID | whitespace | comment -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-id($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- LEX_BLOCK -->
          <xsl:variable name="state" select="p:consume(3, $input, $state)"/>        <!-- LEX_BLOCK -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 4">                                         <!-- ACTION -->
          <xsl:variable name="state" select="p:consume(4, $input, $state)"/>        <!-- ACTION -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-operator($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'declaration', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production declaration_list (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-declaration_list-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(21, $input, $state)"/>     <!-- START | LEX_BLOCK | ACTION | LEFT | RIGHT | NONASSOC | TOKEN |
                                                                                         whitespace | comment | '%%' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 22">                                      <!-- '%%' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-declaration($input, $state)
            "/>
            <xsl:sequence select="p:parse-declaration_list-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse declaration_list.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-declaration_list" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-declaration_list-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'declaration_list', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse spec.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-spec" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-declaration_list($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(22, $input, $state)"/>             <!-- '%%' -->
    <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/>          <!-- ID | whitespace | comment -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-grammar($input, $state)
    "/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 22">                                        <!-- '%%' -->
          <xsl:variable name="state" select="p:consume(22, $input, $state)"/>       <!-- '%%' -->
          <xsl:variable name="state" select="p:lookahead1W(9, $input, $state)"/>    <!-- CODE | EOF | whitespace | comment -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 1">                                   <!-- CODE -->
                <xsl:variable name="state" select="p:consume(1, $input, $state)"/>  <!-- CODE -->
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
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(1, $input, $state)"/>          <!-- EOF | whitespace | comment -->
    <xsl:variable name="state" select="p:consume(14, $input, $state)"/>             <!-- EOF -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'spec', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse jison.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-jison" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:lookahead1W(24, $input, $state)"/>         <!-- START | LEX_BLOCK | ACTION | LEFT | RIGHT | NONASSOC | TOKEN |
                                                                                         whitespace | comment | '%%' | '{' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 31">                                        <!-- '{' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-json($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-spec($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'jison', $count, $begin, $end)"/>
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
      <xsl:when test="$match[1] = 15                                                  (: whitespace :)
                   or $match[1] = 16">                                              <!-- comment -->
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
      $match[1] * 64 + $state[$p:l1],
      subsequence($state, $p:b0, 5),
      $match,
      subsequence($state, 10)
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
   ! Parse start symbol jison from given string.
   !
   ! @param $s the string to be parsed.
   ! @return the result as generated by parser actions.
  -->
  <xsl:function name="p:parse-jison" as="item()*">
    <xsl:param name="s" as="xs:string"/>

    <xsl:variable name="state" select="0, 1, 1, 0, 0, 0, 0, 0, 0, false()"/>
    <xsl:variable name="state" select="p:parse-jison($s, $state)"/>
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