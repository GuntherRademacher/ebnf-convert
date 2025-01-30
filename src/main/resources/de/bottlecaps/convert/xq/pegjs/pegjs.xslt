<?xml version="1.0" encoding="UTF-8"?>
<!-- This file was generated on Thu Jan 30, 2025 13:23 (UTC+01) by REx v6.1 which is Copyright (c) 1979-2025 by Gunther Rademacher <grd@gmx.net> -->
<!-- REx command line: -q -tree -a none -xslt -name de/bottlecaps/convert/xq/pegjs/pegjs.xslt ../../../../../../../main/java/de/bottlecaps/convert/pegjs/pegjs.ebnf -->

<xsl:stylesheet version="2.0"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="de/bottlecaps/convert/xq/pegjs/pegjs.xslt">
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
    38, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 8, 15, 16, 17, 18,
    18, 18, 18, 19, 19, 19, 19, 20, 20, 21, 22, 23, 24, 25, 26, 8, 27, 27, 27, 27, 27, 27, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 28, 29,
    30, 31, 7, 8, 27, 27, 27, 27, 27, 27, 7, 7, 32, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 33, 7, 7, 34, 7, 7, 35, 8, 36, 8, 8
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints below the surrogate block.
  -->
  <xsl:variable name="p:MAP1" as="xs:integer+" select="
    216, 279, 311, 399, 464, 727, 367, 432, 432, 496, 528, 560, 592, 624, 672, 701, 899, 759, 432, 432, 432, 432, 640, 432, 430, 432, 432, 432, 432, 432, 791,
    247, 823, 339, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 855, 887, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432,
    432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931,
    931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931,
    931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931,
    931, 931, 931, 931, 975, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931,
    931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 943, 1007,
    1016, 1008, 1008, 1024, 1032, 1040, 1048, 1056, 1339, 1339, 1073, 1056, 1105, 1117, 1137, 1214, 1211, 1211, 1211, 1152, 1211, 1370, 1211, 1339, 1339, 1340,
    1339, 1339, 1339, 1340, 1339, 1339, 1107, 1107, 1339, 1339, 1339, 1339, 1107, 1107, 1339, 1434, 1339, 1339, 1339, 1107, 1339, 1339, 1339, 1339, 1339, 1339,
    1342, 1123, 1318, 1063, 1109, 1064, 1339, 1063, 1318, 1063, 1339, 1339, 1339, 1339, 1339, 1339, 1335, 1340, 1338, 1322, 1339, 1339, 1339, 1339, 1339, 1340,
    1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1064, 1446, 1339, 1339, 1339, 1339, 1281, 1337, 1339, 1339, 1339, 1211, 1211, 1211, 1211, 1211, 1211, 1211,
    1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1210, 1211, 1448, 1209, 1211, 1097, 1211, 1211, 1211, 1211, 1211, 1154, 1483, 1211, 1211,
    1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1208, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1338, 1339,
    1339, 1208, 1421, 1222, 1096, 1211, 1091, 1097, 1421, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1449, 1339, 1340, 1231, 1091, 1396, 1294, 1091, 1097,
    1091, 1091, 1091, 1091, 1091, 1091, 1091, 1091, 1093, 1211, 1211, 1211, 1097, 1211, 1211, 1211, 1188, 1505, 1339, 1339, 1321, 1339, 1339, 1339, 1339, 1340,
    1340, 1433, 1322, 1339, 1064, 1211, 1213, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211,
    1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1338, 1342, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1338, 1342, 1339,
    1339, 1339, 1339, 1162, 1211, 1339, 1339, 1339, 1339, 1339, 1339, 1108, 1175, 1339, 1339, 1339, 1109, 1107, 1209, 1516, 1339, 1339, 1339, 1339, 1339, 1339,
    1247, 1091, 1093, 1295, 1339, 1265, 1091, 1211, 1211, 1516, 1108, 1336, 1339, 1339, 1322, 1279, 1290, 1199, 1268, 1370, 1305, 1265, 1091, 1209, 1211, 1315,
    1065, 1336, 1339, 1339, 1322, 1330, 1290, 1271, 1268, 1211, 1503, 1371, 1091, 1237, 1211, 1516, 1504, 1321, 1339, 1339, 1322, 1489, 1247, 1351, 1167, 1211,
    1211, 1360, 1091, 1211, 1211, 1516, 1108, 1336, 1339, 1339, 1322, 1333, 1247, 1296, 1268, 1371, 1305, 1178, 1091, 1211, 1211, 1475, 1239, 1384, 1380, 1282,
    1239, 1341, 1178, 1297, 1294, 1370, 1211, 1370, 1091, 1211, 1211, 1516, 1342, 1322, 1339, 1339, 1322, 1343, 1178, 1352, 1294, 1372, 1211, 1178, 1091, 1211,
    1211, 1475, 1342, 1322, 1339, 1339, 1322, 1343, 1178, 1352, 1294, 1372, 1154, 1178, 1091, 1211, 1211, 1475, 1342, 1322, 1339, 1339, 1322, 1339, 1178, 1200,
    1294, 1370, 1211, 1178, 1091, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1152, 1211, 1211,
    1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1338, 1339, 1339, 1339, 1339, 1340, 1392, 1096, 1423, 1092, 1091, 1097, 1211,
    1211, 1211, 1211, 1144, 1127, 1447, 1338, 1404, 1307, 1392, 1257, 1080, 1093, 1091, 1097, 1211, 1211, 1211, 1211, 1097, 1091, 1097, 1414, 1085, 1339, 1338,
    1339, 1339, 1339, 1209, 1090, 1091, 1352, 1095, 1351, 1090, 1091, 1093, 1090, 1368, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1338, 1339, 1339, 1339,
    1340, 1186, 1338, 1339, 1339, 1339, 1340, 1211, 1090, 1091, 1196, 1091, 1091, 1253, 1365, 1211, 1339, 1339, 1339, 1208, 1208, 1211, 1489, 1488, 1208, 1211,
    1211, 1211, 1211, 1431, 1210, 1431, 1281, 1444, 1406, 1280, 1143, 1211, 1211, 1211, 1211, 1154, 1211, 1129, 1153, 1382, 1208, 1211, 1211, 1211, 1211, 1442,
    1210, 1186, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1064, 1339, 1339, 1339, 1339,
    1339, 1339, 1339, 1339, 1339, 1339, 1339, 1209, 1464, 1469, 1211, 1211, 1211, 1457, 1211, 1211, 1211, 1211, 1211, 1212, 1211, 1211, 1211, 1211, 1211, 1211,
    1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1091, 1094, 1368, 1211, 1211, 1211, 1497, 1211, 1211, 1211, 1338, 1223, 1198, 1211, 1338, 1339, 1339, 1339,
    1339, 1339, 1339, 1339, 1339, 1339, 1063, 1472, 1338, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1513, 1446, 1339, 1339, 1339, 1339, 1063,
    1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211,
    1339, 1339, 1339, 1339, 1107, 1211, 1339, 1339, 1339, 1339, 1340, 1211, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339,
    1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1064, 1211, 1211, 1211, 1211, 1211, 1211, 1211,
    1211, 1211, 1211, 1211, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1107, 1211,
    1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 3, 2, 0, 0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 8,
    15, 16, 17, 18, 18, 18, 18, 19, 19, 19, 19, 20, 20, 21, 22, 23, 24, 25, 26, 8, 27, 27, 27, 27, 27, 27, 7, 7, 7, 7, 7, 8, 8, 8, 8, 7, 7, 7, 7, 28, 29, 30,
    31, 7, 7, 7, 7, 7, 8, 37, 8, 8, 8, 8, 37, 37, 37, 37, 37, 37, 37, 37, 8, 8, 8, 8, 8, 8, 7, 32, 7, 7, 7, 7, 7, 7, 8, 8, 7, 7, 7, 7, 7, 7, 7, 33, 7, 7, 7, 7,
    7, 8, 7, 8, 8, 7, 8, 8, 7, 7, 34, 7, 7, 35, 8, 36, 8, 8, 7, 7, 8, 7, 8, 8, 7, 1, 8, 8, 8, 8, 8, 8, 8, 7, 8, 7, 7, 8, 37, 37, 37, 37, 8, 37, 37, 37, 8, 8, 7,
    8, 8, 7, 7, 8, 8, 8, 8, 37, 37, 8, 7, 8, 8, 8, 8, 8, 8, 7, 37, 37, 37, 8, 37, 37, 37, 37, 37, 8, 8, 37, 37, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 1, 8, 8, 7, 7,
    7, 37, 37, 37, 37, 37, 37, 7, 7, 7, 7, 8, 7, 37, 37, 7, 7, 7, 8, 8, 8, 7, 7, 7, 7, 8, 8, 37, 7, 37, 37, 8, 37, 37, 37, 8, 37, 37, 7, 8, 8, 7, 7, 37, 37, 8,
    8, 37, 37, 37, 8, 8, 8, 8, 37, 7, 8, 7, 8, 8, 8, 7, 7, 8, 8, 8, 7, 7, 8, 8, 37, 8, 37, 37, 37, 37, 8, 8, 8, 37, 37, 8, 8, 8, 8, 7, 7, 8, 7, 7, 8, 8, 8, 37,
    8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 8, 7, 7, 8, 8, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 37, 37, 37, 37, 37, 37, 8, 37, 37, 7, 8, 8, 8, 8, 8, 37,
    37, 8, 37, 8, 8, 8, 8, 8, 8, 8, 37, 37, 8, 8, 7, 7, 8, 7, 8, 7, 7, 7, 7, 8, 8, 7, 37, 7, 7, 37, 37, 37, 37, 37, 7, 7, 37, 8, 7, 7, 7, 8, 7, 8, 7, 8, 7, 8,
    8, 8, 8, 8, 37, 8, 37, 7, 7, 7, 7, 7, 7, 7, 37, 37, 8, 8, 8, 8, 7, 8, 7, 8, 7, 8, 7, 8, 8, 8, 7, 8, 8, 8, 8, 8, 7, 7, 7, 7, 7, 8, 2, 2, 8, 8, 8, 8, 8, 1, 1,
    1, 1, 1, 1, 1, 1, 8, 37, 37, 8, 8, 37, 37, 8, 7, 7, 7, 8, 8, 7, 7, 8, 8, 7, 8, 7, 7, 8, 7, 7, 7, 1, 8, 8, 8, 8, 37, 8, 7, 7, 7, 7, 8, 7, 8, 7, 7, 7, 7, 7,
    8, 37, 37, 37, 8, 7, 7, 7
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints above the surrogate block.
  -->
  <xsl:variable name="p:MAP2" as="xs:integer+" select="
    57344, 65279, 65280, 65536, 65278, 65279, 65533, 1114111, 8, 1, 8, 8
  "/>

  <!--~
   ! The token-set-id to DFA-initial-state mapping.
  -->
  <xsl:variable name="p:INITIAL" as="xs:integer+" select="
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
  "/>

  <!--~
   ! The DFA transition table.
  -->
  <xsl:variable name="p:TRANSITION" as="xs:integer+" select="
    347, 347, 347, 347, 347, 347, 347, 347, 312, 316, 862, 477, 517, 562, 347, 347, 327, 331, 334, 555, 560, 562, 347, 347, 857, 331, 319, 347, 347, 347, 347,
    347, 342, 821, 355, 477, 517, 562, 347, 347, 342, 363, 367, 850, 517, 562, 347, 347, 375, 379, 382, 477, 517, 562, 347, 347, 390, 404, 415, 423, 517, 562,
    347, 347, 342, 346, 673, 477, 517, 562, 347, 347, 342, 902, 438, 477, 517, 562, 347, 347, 342, 446, 450, 548, 517, 562, 347, 347, 342, 458, 462, 477, 517,
    562, 347, 347, 342, 470, 525, 477, 517, 562, 347, 347, 342, 346, 533, 541, 498, 572, 347, 347, 342, 346, 582, 477, 517, 562, 347, 347, 342, 590, 673, 836,
    517, 562, 347, 347, 342, 601, 605, 477, 517, 562, 347, 347, 613, 621, 627, 890, 517, 635, 347, 347, 342, 346, 742, 646, 654, 662, 670, 347, 342, 346, 797,
    646, 654, 662, 670, 347, 342, 346, 574, 423, 681, 662, 670, 347, 689, 346, 703, 477, 517, 562, 347, 347, 342, 711, 718, 477, 517, 562, 347, 347, 342, 726,
    731, 477, 517, 562, 347, 347, 342, 739, 430, 477, 517, 562, 347, 347, 342, 750, 754, 477, 517, 562, 347, 347, 342, 346, 762, 477, 517, 562, 347, 347, 390,
    404, 415, 423, 770, 662, 670, 347, 342, 778, 782, 477, 517, 562, 347, 347, 790, 794, 673, 843, 517, 562, 347, 347, 805, 809, 673, 593, 517, 562, 347, 347,
    817, 346, 673, 477, 517, 562, 347, 347, 390, 404, 415, 829, 491, 562, 347, 347, 390, 404, 407, 423, 695, 562, 347, 347, 390, 404, 396, 423, 484, 562, 347,
    347, 870, 878, 884, 477, 517, 562, 347, 347, 898, 346, 638, 477, 517, 562, 347, 347, 342, 346, 673, 423, 517, 562, 347, 347, 564, 505, 512, 347, 347, 347,
    347, 347, 384, 384, 215, 512, 384, 512, 512, 512, 512, 512, 512, 512, 0, 0, 0, 0, 0, 215, 512, 0, 512, 512, 512, 512, 512, 512, 512, 0, 215, 0, 384, 384,
    215, 0, 384, 0, 0, 0, 0, 0, 0, 0, 0, 640, 640, 640, 640, 640, 384, 215, 600, 384, 0, 30, 0, 30, 30, 30, 30, 30, 384, 215, 600, 384, 384, 215, 600, 384, 600,
    600, 600, 600, 600, 600, 600, 384, 215, 600, 384, 384, 215, 281, 384, 0, 281, 281, 281, 281, 281, 35, 215, 600, 384, 0, 0, 281, 281, 281, 281, 281, 34, 215,
    600, 281, 281, 281, 281, 281, 384, 215, 600, 281, 0, 0, 2496, 0, 30, 31, 0, 0, 0, 1408, 1408, 384, 215, 600, 704, 704, 704, 704, 704, 384, 215, 600, 384, 0,
    31, 0, 31, 31, 31, 31, 31, 384, 215, 600, 384, 0, 0, 0, 768, 768, 768, 768, 768, 384, 215, 600, 384, 0, 0, 832, 0, 0, 832, 0, 0, 0, 2496, 0, 30, 31, 0, 0,
    0, 36, 0, 44, 46, 0, 0, 0, 36, 320, 30, 31, 0, 0, 0, 42, 0, 30, 31, 0, 0, 0, 128, 0, 0, 0, 128, 128, 128, 128, 128, 0, 0, 0, 36, 0, 30, 31, 0, 832, 832,
    832, 832, 832, 384, 215, 600, 0, 896, 896, 896, 896, 384, 215, 600, 0, 36, 0, 2496, 0, 30, 31, 0, 0, 0, 2496, 0, 30, 357, 0, 0, 0, 2496, 0, 0, 0, 0, 36, 0,
    0, 0, 0, 0, 0, 128, 0, 0, 42, 0, 0, 0, 0, 0, 0, 215, 600, 0, 960, 960, 960, 960, 384, 215, 600, 412, 0, 0, 0, 0, 0, 0, 0, 30, 31, 0, 384, 0, 0, 0, 1024,
    1024, 1024, 1024, 1024, 384, 215, 600, 384, 384, 215, 26, 384, 26, 26, 26, 384, 26, 26, 1114, 26, 26, 1114, 1114, 1114, 1114, 1114, 384, 215, 600, 0, 576,
    0, 0, 0, 0, 0, 0, 384, 0, 600, 281, 0, 0, 2496, 0, 30, 31, 417, 384, 40, 41, 36, 0, 30, 31, 35, 384, 36, 47, 48, 49, 50, 44, 30, 46, 31, 0, 0, 0, 0, 0, 0,
    384, 215, 600, 0, 40, 41, 36, 0, 0, 0, 35, 384, 384, 215, 0, 384, 1152, 0, 0, 0, 36, 0, 43, 45, 0, 0, 0, 1152, 0, 1152, 384, 215, 600, 384, 0, 0, 1216, 0,
    0, 0, 1216, 1216, 1216, 1216, 1216, 384, 215, 600, 384, 29, 29, 0, 1280, 1280, 1280, 1280, 1309, 1309, 384, 215, 600, 384, 1408, 1408, 0, 0, 0, 0, 0, 416,
    215, 600, 384, 0, 0, 0, 1472, 1472, 1472, 1472, 1472, 384, 215, 600, 0, 1536, 1536, 1536, 1536, 384, 215, 600, 0, 40, 41, 36, 0, 30, 31, 35, 384, 0, 0, 0,
    1600, 1600, 1600, 1600, 1600, 384, 215, 600, 22, 22, 215, 0, 22, 0, 0, 0, 0, 0, 0, 0, 417, 215, 600, 0, 0, 215, 0, 1691, 0, 0, 0, 0, 0, 0, 0, 384, 1792,
    215, 0, 384, 0, 0, 0, 0, 640, 640, 640, 281, 0, 1728, 2496, 0, 30, 31, 0, 0, 0, 2496, 1344, 30, 31, 0, 0, 0, 2496, 0, 38, 39, 0, 0, 0, 2496, 0, 357, 31, 0,
    0, 0, 512, 0, 512, 512, 512, 512, 512, 384, 215, 600, 384, 384, 1856, 0, 384, 0, 0, 1856, 384, 0, 0, 0, 1856, 0, 1856, 1856, 1856, 1856, 1856, 384, 0, 600,
    0, 2496, 0, 30, 31, 0, 384, 384, 1920, 0, 384, 0, 0, 0, 0, 704, 704, 704
  "/>

  <!--~
   ! The DFA-state to expected-token-set mapping.
  -->
  <xsl:variable name="p:EXPECTED" as="xs:integer+" select="
    32, 134217760, 805306372, 392, 100663328, 131456, 394, 268435848, 100663392, 3146112, 3146128, 332170, 289966488, 21532568, 290037656, 290295706, 290299802,
    298712986, 298844058, 301858714, 301989786, 32, 4, 256, 8, 256, 67108864, 64, 1048576, 16, 16, 32, 32, 32, 32, 256, 16, 16, 16, 32, 32, 256, 16, 16, 16, 16,
    16, 16, 16, 16
  "/>

  <!--~
   ! The token-string table.
  -->
  <xsl:variable name="p:TOKEN" as="xs:string+" select="
    '%ERROR',
    'EOF',
    'NonBraceChars',
    'IdentifierName',
    'StringLiteral',
    'Character',
    &quot;'-'&quot;,
    'WhiteSpace',
    'Comment',
    &quot;'!'&quot;,
    &quot;'&amp;'&quot;,
    &quot;'('&quot;,
    &quot;')'&quot;,
    &quot;'*'&quot;,
    &quot;'+'&quot;,
    &quot;'.'&quot;,
    &quot;'/'&quot;,
    &quot;':'&quot;,
    &quot;';'&quot;,
    &quot;'&lt;'&quot;,
    &quot;'&lt;-'&quot;,
    &quot;'='&quot;,
    &quot;'&gt;'&quot;,
    &quot;'?'&quot;,
    &quot;'['&quot;,
    &quot;']'&quot;,
    &quot;']i'&quot;,
    &quot;'^'&quot;,
    &quot;'{'&quot;,
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
        <xsl:variable name="end" select="$end - $result idiv 32"/>
        <xsl:variable name="end" select="if ($end gt string-length($input)) then string-length($input) + 1 else $end"/>
        <xsl:sequence select="
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
              <xsl:variable name="c1" select="$c0 idiv 8"/>
              <xsl:variable name="c2" select="$c1 idiv 32"/>
              <xsl:sequence select="$p:MAP1[1 + $c0 mod 8 + $p:MAP1[1 + $c1 mod 32 + $p:MAP1[1 + $c2]]]"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="p:map2($c0, 1, 4)"/>
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
        <xsl:variable name="i0" select=". * 50 + $state - 1"/>
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
      <xsl:when test="$p:MAP2[4 + $m] &lt; $c">
        <xsl:sequence select="p:map2($c, $m + 1, $hi)"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:sequence select="$p:MAP2[8 + $m]"/>
      </xsl:otherwise>
    </xsl:choose>
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
    <xsl:variable name="state" select="p:lookahead1(0, $input, $state)"/>           <!-- Character -->
    <xsl:variable name="state" select="p:consume(5, $input, $state)"/>              <!-- Character -->
    <xsl:variable name="state" select="p:lookahead1(8, $input, $state)"/>           <!-- Character | Minus | ']' | ']i' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 6">                                         <!-- Minus -->
          <xsl:variable name="state" select="p:consume(6, $input, $state)"/>        <!-- Minus -->
          <xsl:variable name="state" select="p:lookahead1(0, $input, $state)"/>     <!-- Character -->
          <xsl:variable name="state" select="p:consume(5, $input, $state)"/>        <!-- Character -->
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
   ! Parse the 1st loop of production CharacterSet (one or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-CharacterSet-1">
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
            p:parse-CharacterRange($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1(4, $input, $state)"/>       <!-- Character | ']' | ']i' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 5">                                      <!-- Character -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-CharacterSet-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse CharacterSet.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-CharacterSet" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(24, $input, $state)"/>             <!-- '[' -->
    <xsl:variable name="state" select="p:lookahead1(1, $input, $state)"/>           <!-- Character | '^' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 27">                                        <!-- '^' -->
          <xsl:variable name="state" select="p:consume(27, $input, $state)"/>       <!-- '^' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:parse-CharacterSet-1($input, $state)"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 25">                                        <!-- ']' -->
          <xsl:variable name="state" select="p:consume(25, $input, $state)"/>       <!-- ']' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(26, $input, $state)"/>       <!-- ']i' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'CharacterSet', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Primary.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Primary" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 4">                                         <!-- StringLiteral -->
          <xsl:variable name="state" select="p:consume(4, $input, $state)"/>        <!-- StringLiteral -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 15">                                        <!-- '.' -->
          <xsl:variable name="state" select="p:consume(15, $input, $state)"/>       <!-- '.' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 24">                                        <!-- '[' -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-CharacterSet($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 3">                                         <!-- IdentifierName -->
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Name($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 11">                                        <!-- '(' -->
          <xsl:variable name="state" select="p:consume(11, $input, $state)"/>       <!-- '(' -->
          <xsl:variable name="state" select="p:lookahead1W(14, $input, $state)"/>   <!-- IdentifierName | StringLiteral | WhiteSpace | Comment | '!' | '&' |
                                                                                         '(' | ')' | '.' | '/' | '<' | '>' | '[' | '{' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-ParsingExpression($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(12, $input, $state)"/>       <!-- ')' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 19">                                        <!-- '<' -->
          <xsl:variable name="state" select="p:consume(19, $input, $state)"/>       <!-- '<' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(22, $input, $state)"/>       <!-- '>' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Primary', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Expression.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Expression" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Primary($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(17, $input, $state)"/>         <!-- EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' |
                                                                                         '&' | '(' | ')' | '*' | '+' | '.' | '/' | ';' | '<' | '>' | '?' | '[' |
                                                                                         '{' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 13                                            (: '*' :)
                     or $state[$p:l1] = 14                                            (: '+' :)
                     or $state[$p:l1] = 23">                                        <!-- '?' -->
          <xsl:variable name="state" as="item()+">
            <xsl:choose>
              <xsl:when test="$state[$p:error]">
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 13">                                  <!-- '*' -->
                <xsl:variable name="state" select="p:consume(13, $input, $state)"/> <!-- '*' -->
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:when test="$state[$p:l1] = 14">                                  <!-- '+' -->
                <xsl:variable name="state" select="p:consume(14, $input, $state)"/> <!-- '+' -->
                <xsl:sequence select="$state"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:variable name="state" select="p:consume(23, $input, $state)"/> <!-- '?' -->
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
    <xsl:sequence select="p:reduce($state, 'Expression', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Predicate.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Predicate" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 10">                                        <!-- '&' -->
          <xsl:variable name="state" select="p:consume(10, $input, $state)"/>       <!-- '&' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(9, $input, $state)"/>        <!-- '!' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(12, $input, $state)"/>         <!-- IdentifierName | StringLiteral | WhiteSpace | Comment | '(' | '.' |
                                                                                         '<' | '>' | '[' | '{' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 28">                                        <!-- '{' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-JavaScriptCode($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Expression($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Predicate', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Label.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Label" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(3, $input, $state)"/>              <!-- IdentifierName -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Label', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Alternative (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Alternative-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(16, $input, $state)"/>     <!-- EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' |
                                                                                         '&' | '(' | ')' | '.' | '/' | ';' | '<' | '>' | '[' | '{' -->
        <xsl:variable name="state" as="item()+">
          <xsl:choose>
            <xsl:when test="$state[$p:l1] eq 3">                                    <!-- IdentifierName -->
              <xsl:variable name="state" select="p:lookahead2W(20, $input, $state)"/> <!-- EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' |
                                                                                           '&' | '(' | ')' | '*' | '+' | '.' | '/' | ':' | ';' | '<' | '<-' |
                                                                                           '=' | '>' | '?' | '[' | '{' -->
              <xsl:variable name="state" as="item()+">
                <xsl:choose>
                  <xsl:when test="$state[$p:lk] eq 259">                            <!-- IdentifierName StringLiteral -->
                    <xsl:variable name="state" select="p:lookahead3W(19, $input, $state)"/> <!-- EOF | IdentifierName | StringLiteral | WhiteSpace | Comment |
                                                                                                 '!' | '&' | '(' | ')' | '*' | '+' | '.' | '/' | ';' | '<' |
                                                                                                 '<-' | '=' | '>' | '?' | '[' | '{' -->
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
        <xsl:choose>
          <xsl:when test="$state[$p:lk] = 1                                           (: EOF :)
                       or $state[$p:lk] = 12                                          (: ')' :)
                       or $state[$p:lk] = 16                                          (: '/' :)
                       or $state[$p:lk] = 18                                          (: ';' :)
                       or $state[$p:lk] = 28                                          (: '{' :)
                       or $state[$p:lk] = 1283                                        (: IdentifierName '&lt;-' :)
                       or $state[$p:lk] = 1347                                        (: IdentifierName '=' :)
                       or $state[$p:lk] = 82179                                       (: IdentifierName StringLiteral '&lt;-' :)
                       or $state[$p:lk] = 86275">                                   <!-- IdentifierName StringLiteral '=' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" as="item()+">
              <xsl:choose>
                <xsl:when test="$state[$p:l1] eq 3">                                <!-- IdentifierName -->
                  <xsl:variable name="state" select="p:lookahead2W(18, $input, $state)"/> <!-- EOF | IdentifierName | StringLiteral | WhiteSpace | Comment |
                                                                                               '!' | '&' | '(' | ')' | '*' | '+' | '.' | '/' | ':' | ';' | '<' |
                                                                                               '>' | '?' | '[' | '{' -->
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
                <xsl:when test="$state[$p:lk] = 1091">                              <!-- IdentifierName ':' -->
                  <xsl:variable name="state" select="p:whitespace($input, $state)"/>
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:parse-Label($input, $state)
                  "/>
                  <xsl:variable name="state" select="p:lookahead1W(5, $input, $state)"/> <!-- WhiteSpace | Comment | ':' -->
                  <xsl:variable name="state" select="p:consume(17, $input, $state)"/> <!-- ':' -->
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:sequence select="$state"/>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:variable>
            <xsl:variable name="state" select="p:lookahead1W(13, $input, $state)"/> <!-- IdentifierName | StringLiteral | WhiteSpace | Comment | '!' | '&' |
                                                                                         '(' | '.' | '<' | '>' | '[' -->
            <xsl:variable name="state" as="item()+">
              <xsl:choose>
                <xsl:when test="$state[$p:error]">
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:when test="$state[$p:l1] = 9                                     (: '!' :)
                             or $state[$p:l1] = 10">                                <!-- '&' -->
                  <xsl:variable name="state" select="p:whitespace($input, $state)"/>
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:parse-Predicate($input, $state)
                  "/>
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:variable name="state" select="p:whitespace($input, $state)"/>
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:parse-Expression($input, $state)
                  "/>
                  <xsl:sequence select="$state"/>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:variable>
            <xsl:sequence select="p:parse-Alternative-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Alternative.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Alternative" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-Alternative-1($input, $state)"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 28">                                        <!-- '{' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-JavaScriptCode($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Alternative', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production ParsingExpression (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ParsingExpression-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(11, $input, $state)"/>     <!-- EOF | IdentifierName | WhiteSpace | Comment | ')' | '/' | ';' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 16">                                     <!-- '/' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(16, $input, $state)"/>     <!-- '/' -->
            <xsl:variable name="state" select="p:lookahead1W(16, $input, $state)"/> <!-- EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' |
                                                                                         '&' | '(' | ')' | '.' | '/' | ';' | '<' | '>' | '[' | '{' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Alternative($input, $state)
            "/>
            <xsl:sequence select="p:parse-ParsingExpression-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse ParsingExpression.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-ParsingExpression" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Alternative($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-ParsingExpression-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'ParsingExpression', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse HumanReadableName.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-HumanReadableName" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(4, $input, $state)"/>              <!-- StringLiteral -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'HumanReadableName', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Name.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Name" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(3, $input, $state)"/>              <!-- IdentifierName -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Name', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Rule.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Rule" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Name($input, $state)
    "/>
    <xsl:variable name="state" select="p:lookahead1W(10, $input, $state)"/>         <!-- StringLiteral | WhiteSpace | Comment | '<-' | '=' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 4">                                         <!-- StringLiteral -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-HumanReadableName($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(9, $input, $state)"/>          <!-- WhiteSpace | Comment | '<-' | '=' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 21">                                        <!-- '=' -->
          <xsl:variable name="state" select="p:consume(21, $input, $state)"/>       <!-- '=' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(20, $input, $state)"/>       <!-- '<-' -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>         <!-- EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' |
                                                                                         '&' | '(' | '.' | '/' | ';' | '<' | '>' | '[' | '{' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-ParsingExpression($input, $state)
    "/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 18">                                        <!-- ';' -->
          <xsl:variable name="state" select="p:consume(18, $input, $state)"/>       <!-- ';' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Rule', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production JavaScriptCode (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-JavaScriptCode-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1(2, $input, $state)"/>       <!-- NonBraceChars | '{' | '}' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] = 29">                                      <!-- '}' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" as="item()+">
              <xsl:choose>
                <xsl:when test="$state[$p:error]">
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:when test="$state[$p:l1] = 2">                                 <!-- NonBraceChars -->
                  <xsl:variable name="state" select="p:consume(2, $input, $state)"/> <!-- NonBraceChars -->
                  <xsl:sequence select="$state"/>
                </xsl:when>
                <xsl:otherwise>
                  <xsl:variable name="state" select="
                    if ($state[$p:error]) then
                      $state
                    else
                      p:parse-JavaScriptCode($input, $state)
                  "/>
                  <xsl:sequence select="$state"/>
                </xsl:otherwise>
              </xsl:choose>
            </xsl:variable>
            <xsl:sequence select="p:parse-JavaScriptCode-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse JavaScriptCode.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-JavaScriptCode" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(28, $input, $state)"/>             <!-- '{' -->
    <xsl:variable name="state" select="p:parse-JavaScriptCode-1($input, $state)"/>
    <xsl:variable name="state" select="p:consume(29, $input, $state)"/>             <!-- '}' -->
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'JavaScriptCode', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse Initializer.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Initializer" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-JavaScriptCode($input, $state)
    "/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Initializer', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Grammar (one or more). Use
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
        <xsl:variable name="state" select="p:lookahead1W(3, $input, $state)"/>      <!-- IdentifierName | WhiteSpace | Comment -->
        <xsl:variable name="state" select="p:whitespace($input, $state)"/>
        <xsl:variable name="state" select="
          if ($state[$p:error]) then
            $state
          else
            p:parse-Rule($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>      <!-- EOF | IdentifierName | WhiteSpace | Comment -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 3">                                      <!-- IdentifierName -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:sequence select="p:parse-Grammar-1($input, $state)"/>
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
    <xsl:variable name="state" select="p:lookahead1W(7, $input, $state)"/>          <!-- IdentifierName | WhiteSpace | Comment | '{' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 28">                                        <!-- '{' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Initializer($input, $state)
          "/>
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:parse-Grammar-1($input, $state)"/>
    <xsl:variable name="state" select="p:consume(1, $input, $state)"/>              <!-- EOF -->
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
      <xsl:when test="$match[1] = 7                                                   (: WhiteSpace :)
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