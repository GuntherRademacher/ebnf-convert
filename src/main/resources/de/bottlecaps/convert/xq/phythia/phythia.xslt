<?xml version="1.0" encoding="UTF-8"?>
<!-- This file was generated on Fri Feb 10, 2023 18:44 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net> -->
<!-- REx command line: -tree -a none -xslt -name de/bottlecaps/convert/xq/phythia/phythia.xslt ../../../../../../../main/java/de/bottlecaps/convert/phythia/phythia.ebnf -->

<xsl:stylesheet version="2.0"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="de/bottlecaps/convert/xq/phythia/phythia.xslt">
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
   ! The index of the lexer state for accessing the token code that
   ! was expected when an error was found.
  -->
  <xsl:variable name="p:error" as="xs:integer" select="7"/>

  <!--~
   ! The index of the lexer state that points to the first entry
   ! used for collecting action results.
  -->
  <xsl:variable name="p:result" as="xs:integer" select="8"/>

  <!--~
   ! The codepoint to charclass mapping for 7 bit codepoints.
  -->
  <xsl:variable name="p:MAP0" as="xs:integer+" select="
    42, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 2, 2, 2, 2, 4, 5, 6, 7, 8, 2, 9, 10, 11, 9, 9, 9,
    9, 9, 9, 9, 9, 9, 9, 12, 13, 14, 15, 16, 2, 2, 17, 18, 19, 20, 21, 22, 23, 24, 25, 24, 24, 26, 27, 28, 29, 24, 24, 30, 31, 32, 33, 24, 24, 34, 35, 24, 36,
    2, 37, 2, 38, 2, 17, 18, 19, 20, 21, 22, 23, 24, 25, 24, 24, 26, 27, 28, 29, 24, 24, 30, 31, 32, 33, 24, 24, 34, 35, 24, 39, 40, 41, 2, 2
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints below the surrogate block.
  -->
  <xsl:variable name="p:MAP1" as="xs:integer+" select="
    216, 291, 323, 383, 415, 908, 351, 815, 815, 447, 479, 511, 543, 575, 621, 882, 589, 681, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 713,
    745, 821, 649, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 777, 809, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815,
    815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    247, 247, 247, 247, 259, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 853, 940,
    949, 941, 941, 957, 965, 1057, 973, 999, 1007, 1015, 1036, 999, 1007, 1015, 1044, 1258, 1258, 1258, 1258, 1258, 1258, 1259, 1258, 1250, 1250, 1251, 1250,
    1250, 1250, 1251, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
    1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1252, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1250, 1250, 1250, 1250, 1250,
    1250, 1343, 1251, 1249, 1248, 1250, 1250, 1250, 1250, 1250, 1251, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1254, 988, 1250, 1250, 1250, 1250, 1194,
    991, 1250, 1250, 1250, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1257, 1258, 990, 1256,
    1258, 1063, 1258, 1258, 1258, 1258, 1258, 1249, 1250, 1250, 1255, 1117, 1309, 1062, 1258, 1057, 1063, 1117, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
    1345, 1250, 1251, 1128, 1057, 1146, 1025, 1057, 1063, 1057, 1057, 1057, 1057, 1057, 1057, 1057, 1057, 1059, 1258, 1258, 1258, 1063, 1258, 1258, 1258, 1368,
    1227, 1250, 1250, 1247, 1250, 1250, 1250, 1250, 1251, 1251, 1387, 1248, 1250, 1254, 1258, 1249, 1071, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1249,
    1071, 1250, 1250, 1250, 1250, 1080, 1258, 1250, 1250, 1250, 1250, 1250, 1250, 1097, 1106, 1250, 1250, 1250, 1098, 1252, 1256, 1412, 1250, 1250, 1250, 1250,
    1250, 1250, 1165, 1057, 1059, 1026, 1250, 1151, 1057, 1258, 1258, 1412, 1097, 1344, 1250, 1250, 1248, 1192, 1021, 1137, 1154, 1259, 1203, 1151, 1057, 1256,
    1258, 1214, 1237, 1344, 1250, 1250, 1248, 1377, 1021, 1157, 1154, 1258, 1225, 1260, 1057, 1235, 1258, 1412, 1226, 1247, 1250, 1250, 1248, 1245, 1165, 1297,
    1089, 1258, 1258, 1176, 1057, 1258, 1258, 1412, 1097, 1344, 1250, 1250, 1248, 1341, 1165, 1027, 1154, 1260, 1203, 1109, 1057, 1258, 1258, 1184, 1269, 1285,
    1281, 1195, 1269, 1119, 1109, 1028, 1025, 1259, 1258, 1259, 1057, 1258, 1258, 1412, 1071, 1248, 1250, 1250, 1248, 1072, 1109, 1298, 1025, 1261, 1258, 1109,
    1057, 1258, 1258, 1184, 1071, 1248, 1250, 1250, 1248, 1072, 1109, 1298, 1025, 1261, 1401, 1109, 1057, 1258, 1258, 1184, 1071, 1248, 1250, 1250, 1248, 1250,
    1109, 1138, 1025, 1259, 1258, 1109, 1057, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258,
    1258, 1250, 1250, 1250, 1250, 1252, 1258, 1250, 1250, 1250, 1250, 1251, 1258, 1249, 1250, 1250, 1250, 1250, 1251, 1293, 1062, 1306, 1058, 1057, 1063, 1258,
    1258, 1258, 1258, 1206, 1318, 989, 1249, 1328, 1338, 1293, 1171, 1353, 1059, 1057, 1063, 1258, 1258, 1258, 1258, 1401, 1273, 1258, 1258, 1258, 1258, 1258,
    1258, 1258, 1258, 1258, 1258, 1255, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1245, 1376, 1255, 1258, 1258,
    1258, 1258, 1385, 1257, 1385, 1194, 986, 1330, 1193, 1205, 1258, 1258, 1258, 1258, 1401, 1258, 1320, 1400, 1283, 1255, 1258, 1258, 1258, 1258, 1396, 1257,
    1398, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1254, 1250, 1250, 1250, 1250, 1250,
    1250, 1250, 1250, 1250, 1250, 1250, 1256, 1250, 1250, 1252, 1252, 1250, 1250, 1250, 1250, 1252, 1252, 1250, 1388, 1250, 1250, 1250, 1252, 1250, 1250, 1250,
    1250, 1250, 1250, 1071, 1120, 1217, 1253, 1098, 1254, 1250, 1253, 1217, 1253, 980, 1258, 1258, 1258, 1249, 1310, 1136, 1258, 1249, 1250, 1250, 1250, 1250,
    1250, 1250, 1250, 1250, 1250, 1253, 1181, 1249, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1409, 988, 1250, 1250, 1250, 1250, 1253, 1258,
    1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258,
    1258, 1258, 1258, 1258, 1258, 1057, 1060, 1366, 1258, 1258, 1258, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
    1250, 1250, 1250, 1250, 1250, 1254, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1063, 1057, 1063, 1050, 1358, 1250, 1249, 1250, 1250,
    1250, 1256, 1056, 1057, 1298, 1061, 1297, 1056, 1057, 1059, 1056, 1366, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1249, 1250, 1250, 1250, 1251, 1398,
    1249, 1250, 1250, 1250, 1251, 1258, 1056, 1057, 1134, 1057, 1057, 1085, 1363, 1258, 1250, 1250, 1250, 1255, 1255, 1258, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,
    0, 0, 1, 0, 0, 1, 2, 3, 2, 2, 2, 2, 4, 5, 6, 7, 8, 2, 9, 10, 11, 9, 9, 12, 13, 14, 15, 16, 2, 2, 2, 2, 2, 9, 2, 24, 2, 2, 2, 2, 2, 24, 24, 24, 24, 24, 24,
    2, 17, 18, 19, 20, 21, 22, 23, 24, 25, 24, 24, 26, 27, 28, 29, 24, 24, 30, 31, 32, 33, 24, 24, 2, 2, 9, 2, 9, 9, 9, 9, 2, 2, 2, 9, 9, 34, 35, 24, 36, 2, 37,
    2, 38, 34, 35, 24, 39, 40, 41, 2, 2, 2, 2, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 2, 2, 2, 2, 2, 2, 24, 24, 24, 24, 24, 2, 24, 24, 24, 24, 24, 2, 9, 9, 9, 9, 2,
    9, 9, 9, 2, 9, 9, 9, 2, 2, 24, 24, 24, 24, 24, 2, 2, 24, 24, 24, 2, 2, 24, 24, 2, 2, 2, 2, 9, 9, 9, 24, 24, 24, 24, 24, 24, 24, 2, 24, 2, 24, 24, 24, 24, 2,
    24, 9, 9, 2, 9, 9, 9, 9, 9, 2, 2, 9, 9, 9, 9, 9, 9, 9, 24, 24, 9, 9, 2, 2, 9, 9, 9, 2, 2, 2, 2, 9, 24, 24, 2, 2, 9, 24, 9, 9, 2, 9, 9, 24, 2, 2, 2, 2, 2, 9,
    9, 2, 2, 9, 9, 2, 24, 24, 24, 24, 2, 24, 2, 2, 2, 24, 24, 2, 2, 2, 2, 2, 2, 2, 24, 24, 2, 24, 2, 2, 24, 2, 2, 9, 2, 2, 24, 24, 24, 2, 24, 24, 2, 24, 24, 24,
    24, 2, 24, 2, 24, 24, 9, 9, 24, 24, 24, 2, 2, 2, 2, 24, 24, 2, 24, 24, 2, 24, 24, 24, 24, 24, 24, 24, 24, 2, 2, 2, 2, 2, 2, 2, 2, 9, 9, 2, 24, 24, 24, 2, 2,
    2, 24, 24, 2, 2, 24, 2, 2, 24, 24, 2, 24, 2, 24, 24, 24, 24, 2, 2, 24, 9, 24, 24, 9, 9, 9, 9, 9, 9, 2, 9, 9, 24, 24, 24, 24, 24, 24, 9, 9, 9, 9, 9, 9, 24,
    2, 24, 2, 2, 24, 2, 2, 24, 24, 2, 24, 24, 24, 2, 24, 2, 24, 2, 24, 2, 2, 24, 24, 2, 24, 24, 2, 2, 24, 24, 24, 24, 24, 2, 24, 24, 24, 24, 24, 2, 9, 2, 2, 2,
    2, 9, 9, 2, 9, 2, 2, 2, 2, 2, 2, 24, 9, 2, 24, 2, 24, 24, 2, 24, 24, 2, 2, 2, 2, 2, 24, 2, 24, 2, 24, 2, 24, 2, 2, 2, 24, 2, 2, 2, 2, 2, 2, 2, 24, 2, 24,
    24, 24, 2, 9, 9, 9, 2, 24, 24, 24
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints above the surrogate block.
  -->
  <xsl:variable name="p:MAP2" as="xs:integer+" select="
    57344, 65536, 65533, 1114111, 2, 2
  "/>

  <!--~
   ! The token-set-id to DFA-initial-state mapping.
  -->
  <xsl:variable name="p:INITIAL" as="xs:integer+" select="
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18
  "/>

  <!--~
   ! The DFA transition table.
  -->
  <xsl:variable name="p:TRANSITION" as="xs:integer+" select="
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1376, 1376, 1381, 1574, 1546, 1545, 1392, 1394, 1533, 2366, 1402, 1404, 1781, 1414, 1434, 1421, 1445, 2148, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847,
    1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1463, 1469, 2289, 1546, 1545, 1589, 1472,
    1520, 1522, 1480, 1482, 1484, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1492,
    1498, 1679, 1546, 1545, 1706, 1708, 1501, 1503, 1932, 1934, 1936, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1511, 1517, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1530, 1530, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436,
    2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2372, 1541, 1639, 1554, 1545, 1561, 1590, 1591, 2271,
    1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1691, 1570, 1661,
    1582, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1649, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1599, 1604, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 1616,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1629, 1629, 1635, 1574, 1546, 1545, 1647, 1590, 1591, 2271, 1991, 1404,
    1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2376, 1545, 1562, 1574, 1546, 1545,
    1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 2405, 1657, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1669, 1675, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1574, 1546, 1687, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847,
    1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1574, 1699, 1545, 1544, 1590,
    1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722,
    1728, 1608, 1546, 1648, 1716, 1739, 1591, 2271, 1991, 2340, 1406, 1844, 2090, 1451, 1748, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 1740, 1807, 2308, 2310, 2312, 1763, 1755, 1754, 1769, 2148, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 1591, 1989, 1991, 1404, 1406, 1847, 1437, 1436,
    1426, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 2153, 2175, 1590, 1591, 2271,
    1777, 1404, 1789, 1847, 1455, 1436, 2112, 1802, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2196, 1815, 1728, 1960,
    1546, 1648, 1829, 1590, 1837, 1918, 2240, 2346, 1858, 1863, 1871, 1903, 1905, 1913, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 2334, 2271, 1991, 1404, 1406, 1794, 1437, 1946, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 1926, 1404, 1406, 1847, 1944, 1436, 2112, 2148,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 1991, 1404,
    1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648,
    2175, 1590, 1591, 1954, 1991, 1968, 1406, 1847, 1437, 1436, 1878, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1731, 1722, 1728, 1608, 1546, 1978, 2175, 1986, 1591, 2271, 1991, 1999, 1406, 1847, 1437, 1436, 2112, 2011, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 2179, 1722, 1728, 1608, 1546, 1384, 2175, 1590, 1591, 2271, 1991, 1404, 2029, 1847, 1437, 2037, 1895, 2148, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 2056, 1546, 2064, 2175, 2016, 1591, 2261, 1991, 2072, 1406, 1847,
    2044, 2080, 2112, 2231, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 2098, 1546, 1648, 2175, 2303,
    1591, 2271, 1991, 1404, 1406, 2106, 1437, 1890, 2048, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2120, 2127,
    1728, 1608, 1546, 1648, 2175, 1590, 1591, 1621, 2208, 1404, 1406, 2109, 1885, 1436, 2141, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 2161, 2187, 2193, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 2003, 2204, 1406, 1847, 2087, 1436, 2112, 2148, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2216, 1590, 2236, 2271, 1991, 1404, 1406, 1847, 1437, 1436,
    2224, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 2133, 1608, 1546, 1648, 2248, 1590, 1591, 2271,
    2021, 1404, 1406, 1847, 1437, 1436, 2112, 2256, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608,
    1546, 1648, 2175, 1590, 2269, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1731, 1722, 1821, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2279, 2285, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2297, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404,
    1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648,
    2320, 1590, 1591, 2328, 1991, 1404, 1970, 1850, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 2354, 2360, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 2384, 2390, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1574, 2168, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847,
    1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2396, 2404, 1545, 1545, 1545, 1545, 1545, 1545,
    1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 531, 531,
    531, 531, 531, 531, 531, 531, 0, 0, 0, 0, 0, 54, 0, 3384, 40, 0, 0, 0, 0, 0, 61, 62, 63, 64, 81, 63, 64, 72, 0, 86, 0, 63, 64, 72, 86, 0, 105, 86, 0, 72,
    86, 0, 114, 86, 125, 72, 86, 125, 72, 86, 113, 72, 86, 113, 2560, 113, 113, 114, 86, 113, 72, 86, 113, 72, 86, 113, 72, 72, 86, 134, 72, 86, 134, 86, 113,
    72, 127, 113, 72, 86, 113, 72, 86, 122, 72, 0, 29, 29, 29, 29, 29, 29, 29, 0, 0, 0, 0, 0, 0, 63, 70, 64, 0, 70, 64, 72, 0, 86, 0, 70, 64, 72, 86, 0, 0, 30,
    30, 30, 30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 63, 71, 72, 0, 0, 0, 3584, 3584, 3584, 3584, 3584, 3584, 3584, 0, 0, 0, 0, 0, 0, 70, 64, 72, 0, 0, 0, 3840, 0, 0,
    0, 0, 0, 0, 70, 71, 72, 0, 38, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 40, 6912, 0, 0, 0, 0, 0, 0, 49, 0, 0, 0, 0, 0, 0, 0, 43, 0, 39, 0, 0, 0, 0, 0, 0, 29, 30, 0,
    0, 7168, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 63, 64, 72, 5120, 0, 0, 0, 0, 0, 5120, 0, 0, 0, 0, 0, 0, 29, 30, 0, 3351, 113, 113, 113, 113, 2929, 0, 0,
    0, 63, 64, 72, 85, 0, 20, 20, 20, 20, 20, 20, 20, 20, 0, 0, 0, 0, 0, 0, 29, 30, 5888, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 3351, 0, 0, 5632, 0, 0, 0, 0, 0, 0, 29,
    30, 6144, 0, 0, 31, 31, 31, 31, 31, 31, 31, 0, 0, 0, 0, 0, 0, 29, 768, 0, 0, 0, 0, 5376, 0, 0, 0, 0, 0, 36, 37, 0, 0, 0, 4096, 0, 4608, 0, 4096, 4608, 40,
    0, 0, 0, 0, 0, 0, 64, 63, 71, 40, 0, 0, 0, 0, 60, 0, 3351, 3351, 3351, 3351, 3351, 3351, 3351, 0, 0, 0, 0, 3351, 0, 0, 0, 0, 65, 0, 0, 0, 0, 0, 63, 64, 78,
    132, 86, 113, 72, 86, 113, 86, 113, 78, 86, 113, 78, 86, 113, 78, 78, 86, 0, 78, 86, 0, 78, 86, 113, 78, 86, 113, 86, 113, 0, 88, 89, 72, 0, 86, 0, 63, 64,
    105, 86, 0, 0, 103, 0, 63, 64, 72, 86, 0, 111, 112, 0, 72, 86, 138, 113, 113, 113, 113, 0, 0, 0, 63, 64, 78, 0, 0, 28, 3351, 3351, 3351, 3351, 3351, 3351,
    3351, 0, 0, 0, 42, 3351, 0, 40, 1536, 0, 0, 0, 0, 0, 3351, 0, 72, 0, 0, 0, 76, 77, 72, 86, 110, 72, 86, 0, 72, 86, 0, 72, 86, 0, 72, 115, 2048, 92, 0, 63,
    64, 72, 92, 0, 72, 92, 0, 72, 92, 116, 72, 92, 116, 72, 121, 116, 72, 86, 113, 72, 135, 113, 86, 113, 72, 86, 113, 120, 86, 113, 72, 86, 128, 72, 86, 113,
    72, 86, 136, 86, 113, 92, 116, 72, 92, 116, 72, 92, 116, 92, 116, 116, 116, 116, 141, 116, 0, 0, 0, 63, 64, 84, 0, 0, 87, 63, 64, 90, 0, 86, 0, 63, 71, 72,
    0, 86, 0, 63, 71, 72, 86, 0, 113, 117, 86, 113, 72, 86, 113, 72, 86, 131, 0, 80, 0, 63, 64, 72, 0, 0, 46, 0, 29, 30, 0, 3376, 64, 96, 0, 86, 0, 63, 64, 72,
    106, 0, 50, 0, 0, 0, 0, 0, 0, 3351, 0, 66, 0, 0, 0, 0, 63, 64, 72, 0, 86, 0, 63, 64, 72, 97, 86, 0, 63, 64, 72, 0, 86, 0, 94, 113, 113, 140, 113, 113, 0, 0,
    0, 68, 0, 0, 63, 64, 72, 91, 86, 0, 63, 0, 86, 104, 2304, 2304, 72, 86, 107, 124, 113, 126, 86, 113, 129, 86, 113, 72, 86, 119, 72, 86, 113, 72, 86, 113,
    86, 137, 0, 45, 0, 47, 29, 30, 0, 3351, 0, 51, 0, 0, 0, 0, 0, 3351, 64, 72, 0, 98, 0, 63, 64, 102, 98, 113, 72, 86, 113, 72, 130, 113, 72, 118, 113, 72, 86,
    113, 72, 86, 113, 123, 44, 0, 0, 0, 29, 30, 0, 3351, 108, 109, 0, 72, 86, 0, 72, 86, 113, 72, 86, 113, 86, 113, 21, 0, 0, 3351, 0, 21, 0, 21, 3351, 3351,
    3351, 3351, 3351, 3351, 3351, 0, 0, 41, 0, 3351, 0, 72, 86, 113, 1280, 86, 113, 86, 113, 113, 113, 113, 113, 0, 0, 0, 52, 53, 0, 55, 3351, 0, 22, 0, 3351,
    0, 0, 27, 0, 0, 4352, 0, 4864, 4352, 4864, 40, 0, 0, 0, 0, 0, 0, 3351, 0, 25, 0, 0, 0, 3360, 3360, 3360, 3360, 3360, 3360, 3360, 0, 0, 0, 0, 3351, 0, 0, 26,
    28, 95, 72, 0, 86, 0, 63, 64, 72, 0, 86, 93, 63, 40, 0, 57, 0, 0, 0, 0, 3351, 72, 133, 113, 72, 86, 113, 86, 113, 113, 113, 113, 119, 0, 0, 0, 74, 0, 63,
    64, 72, 0, 92, 0, 63, 40, 0, 0, 58, 0, 0, 0, 3351, 113, 139, 113, 113, 113, 0, 0, 0, 82, 83, 72, 0, 0, 1792, 0, 0, 0, 0, 63, 64, 72, 0, 0, 0, 6400, 6400,
    6400, 6400, 6400, 6400, 6400, 0, 0, 0, 0, 0, 0, 768, 30, 0, 0, 6656, 6656, 0, 0, 0, 0, 0, 0, 67, 0, 69, 0, 63, 64, 78, 0, 86, 0, 63, 64, 78, 86, 0, 40, 0,
    0, 0, 59, 0, 0, 3351, 79, 0, 0, 63, 64, 72, 0, 0, 73, 0, 75, 63, 64, 72, 0, 86, 99, 63, 64, 72, 0, 92, 0, 100, 101, 72, 0, 33, 33, 33, 33, 33, 33, 33, 0, 0,
    0, 0, 0, 0, 81, 63, 64, 72, 0, 0, 34, 35, 0, 0, 0, 0, 24, 0, 0, 0, 0, 7424, 7424, 7424, 7424, 7424, 7424, 7424, 0, 0, 0, 0, 0, 0, 3072, 0, 0, 0, 0, 3072,
    3072, 0, 0, 0, 0, 0, 0, 0, 5632
  "/>

  <!--~
   ! The DFA-state to expected-token-set mapping.
  -->
  <xsl:variable name="p:EXPECTED" as="xs:integer+" select="
    36, 40, 44, 48, 52, 63, 58, 67, 71, 75, 79, 90, 55, 90, 57, 97, 57, 86, 59, 88, 86, 59, 88, 86, 59, 100, 101, 102, 103, 101, 102, 103, 101, 83, 94, 103, 42,
    74, 2058, 4106, 1048586, 58, 650, 3114, 527402, 499151118, 499167502, 499200270, 499265806, 499396878, 499659022, 501231886, 532689166, 535818510, 2, 8, 32,
    64, 16, 128, 512, 1024, 256, 32, 64, 4096, 1048576, 4, 4, 12582912, 4352, 201326592, 32768, 65536, 131072, 262144, 98304, 393216, 8, 32, 64, 1048576, 16,
    512, 1024, 512, 1024, 256, 256, 16, 128, 512, 1024, 4352, 1024, 1024, 1024, 1024, 256, 256, 256, 16, 512, 1024, 16, 512, 1024
  "/>

  <!--~
   ! The token-string table.
  -->
  <xsl:variable name="p:TOKEN" as="xs:string+" select="
    '(0)',
    'Space',
    'StringLiteral',
    'Comment',
    'BeginOfGrammar',
    'RULE',
    'SYNTAX',
    'END_OF_RULE',
    'Sem',
    'Semantic',
    'EndOfGrammar',
    'EOF',
    'Name',
    &quot;'('&quot;,
    &quot;')'&quot;,
    &quot;'*&gt;'&quot;,
    &quot;'*}'&quot;,
    &quot;'+&gt;'&quot;,
    &quot;'+}'&quot;,
    &quot;'.'&quot;,
    &quot;'::='&quot;,
    &quot;';'&quot;,
    &quot;'&lt;*'&quot;,
    &quot;'&lt;+'&quot;,
    &quot;'['&quot;,
    &quot;']'&quot;,
    &quot;'{*'&quot;,
    &quot;'{+'&quot;,
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
              <xsl:variable name="c1" select="$c0 idiv 8"/>
              <xsl:variable name="c2" select="$c1 idiv 32"/>
              <xsl:sequence select="$p:MAP1[1 + $c0 mod 8 + $p:MAP1[1 + $c1 mod 32 + $p:MAP1[1 + $c2]]]"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:sequence select="p:map2($c0, 1, 2)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>
        <xsl:variable name="current" select="$current + 1"/>
        <xsl:variable name="i0" select="256 * $c1 + $current-state - 1"/>
        <xsl:variable name="i1" select="$i0 idiv 8"/>
        <xsl:variable name="next-state" select="$p:TRANSITION[$i0 mod 8 + $p:TRANSITION[$i1 + 1] + 1]"/>
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
      <xsl:for-each select="0 to 0">
        <xsl:variable name="i0" select=". * 141 + $state - 1"/>
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
   ! Parse Item.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Item" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 12">                                        <!-- Name -->
          <xsl:variable name="state" select="p:consume(12, $input, $state)"/>       <!-- Name -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 2">                                         <!-- StringLiteral -->
          <xsl:variable name="state" select="p:consume(2, $input, $state)"/>        <!-- StringLiteral -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 22">                                        <!-- '<*' -->
          <xsl:variable name="state" select="p:consume(22, $input, $state)"/>       <!-- '<*' -->
          <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>   <!-- Space | StringLiteral | Comment | Sem | Name | '(' | ';' | '<*' |
                                                                                         '<+' | '[' | '{*' | '{+' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Choice($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(21, $input, $state)"/>       <!-- ';' -->
          <xsl:variable name="state" select="p:lookahead1W(10, $input, $state)"/>   <!-- Space | StringLiteral | Comment | Sem | Name | '(' | '*>' | '<*' |
                                                                                         '<+' | '[' | '{*' | '{+' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Choice($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(15, $input, $state)"/>       <!-- '*>' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 23">                                        <!-- '<+' -->
          <xsl:variable name="state" select="p:consume(23, $input, $state)"/>       <!-- '<+' -->
          <xsl:variable name="state" select="p:lookahead1W(15, $input, $state)"/>   <!-- Space | StringLiteral | Comment | Sem | Name | '(' | ';' | '<*' |
                                                                                         '<+' | '[' | '{*' | '{+' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Choice($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(21, $input, $state)"/>       <!-- ';' -->
          <xsl:variable name="state" select="p:lookahead1W(12, $input, $state)"/>   <!-- Space | StringLiteral | Comment | Sem | Name | '(' | '+>' | '<*' |
                                                                                         '<+' | '[' | '{*' | '{+' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Choice($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(17, $input, $state)"/>       <!-- '+>' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 26">                                        <!-- '{*' -->
          <xsl:variable name="state" select="p:consume(26, $input, $state)"/>       <!-- '{*' -->
          <xsl:variable name="state" select="p:lookahead1W(11, $input, $state)"/>   <!-- Space | StringLiteral | Comment | Sem | Name | '(' | '*}' | '<*' |
                                                                                         '<+' | '[' | '{*' | '{+' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Choice($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(16, $input, $state)"/>       <!-- '*}' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 27">                                        <!-- '{+' -->
          <xsl:variable name="state" select="p:consume(27, $input, $state)"/>       <!-- '{+' -->
          <xsl:variable name="state" select="p:lookahead1W(13, $input, $state)"/>   <!-- Space | StringLiteral | Comment | Sem | Name | '(' | '+}' | '<*' |
                                                                                         '<+' | '[' | '{*' | '{+' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Choice($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(18, $input, $state)"/>       <!-- '+}' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 24">                                        <!-- '[' -->
          <xsl:variable name="state" select="p:consume(24, $input, $state)"/>       <!-- '[' -->
          <xsl:variable name="state" select="p:lookahead1W(16, $input, $state)"/>   <!-- Space | StringLiteral | Comment | Sem | Name | '(' | '<*' | '<+' |
                                                                                         '[' | ']' | '{*' | '{+' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Choice($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(25, $input, $state)"/>       <!-- ']' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 13">                                        <!-- '(' -->
          <xsl:variable name="state" select="p:consume(13, $input, $state)"/>       <!-- '(' -->
          <xsl:variable name="state" select="p:lookahead1W(9, $input, $state)"/>    <!-- Space | StringLiteral | Comment | Sem | Name | '(' | ')' | '<*' |
                                                                                         '<+' | '[' | '{*' | '{+' | '|' -->
          <xsl:variable name="state" select="p:whitespace($input, $state)"/>
          <xsl:variable name="state" select="
            if ($state[$p:error]) then
              $state
            else
              p:parse-Choice($input, $state)
          "/>
          <xsl:variable name="state" select="p:consume(14, $input, $state)"/>       <!-- ')' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="state" select="p:consume(8, $input, $state)"/>        <!-- Sem -->
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Item', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Sequence (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Sequence-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(17, $input, $state)"/>     <!-- Space | StringLiteral | Comment | Sem | Name | '(' | ')' | '*>' |
                                                                                         '*}' | '+>' | '+}' | '.' | ';' | '<*' | '<+' | '[' | ']' | '{*' |
                                                                                         '{+' | '|' -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 2                                          (: StringLiteral :)
                      and $state[$p:l1] != 8                                          (: Sem :)
                      and $state[$p:l1] != 12                                         (: Name :)
                      and $state[$p:l1] != 13                                         (: '(' :)
                      and $state[$p:l1] != 22                                         (: '&lt;*' :)
                      and $state[$p:l1] != 23                                         (: '&lt;+' :)
                      and $state[$p:l1] != 24                                         (: '[' :)
                      and $state[$p:l1] != 26                                         (: '{*' :)
                      and $state[$p:l1] != 27">                                     <!-- '{+' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Item($input, $state)
            "/>
            <xsl:sequence select="p:parse-Sequence-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Sequence.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Sequence" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:parse-Sequence-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Sequence', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Choice (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Choice-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 28">                                     <!-- '|' -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(28, $input, $state)"/>     <!-- '|' -->
            <xsl:variable name="state" select="p:lookahead1W(17, $input, $state)"/> <!-- Space | StringLiteral | Comment | Sem | Name | '(' | ')' | '*>' |
                                                                                         '*}' | '+>' | '+}' | '.' | ';' | '<*' | '<+' | '[' | ']' | '{*' |
                                                                                         '{+' | '|' -->
            <xsl:variable name="state" select="p:whitespace($input, $state)"/>
            <xsl:variable name="state" select="
              if ($state[$p:error]) then
                $state
              else
                p:parse-Sequence($input, $state)
            "/>
            <xsl:sequence select="p:parse-Choice-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Choice.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Choice" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Sequence($input, $state)
    "/>
    <xsl:variable name="state" select="p:parse-Choice-1($input, $state)"/>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Choice', $count, $begin, $end)"/>
  </xsl:function>

  <!--~
   ! Parse the 1st loop of production Production (zero or more). Use
   ! tail recursion for iteratively updating the lexer state.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Production-1">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$state[$p:error]">
        <xsl:sequence select="$state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="state" select="p:lookahead1W(6, $input, $state)"/>      <!-- Space | Comment | END_OF_RULE | Semantic -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 9">                                      <!-- Semantic -->
            <xsl:sequence select="$state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:consume(9, $input, $state)"/>      <!-- Semantic -->
            <xsl:sequence select="p:parse-Production-1($input, $state)"/>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse Production.
   !
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse-Production" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="count" select="count($state)"/>
    <xsl:variable name="begin" select="$state[$p:e0]"/>
    <xsl:variable name="state" select="p:consume(5, $input, $state)"/>              <!-- RULE -->
    <xsl:variable name="state" select="p:lookahead1W(3, $input, $state)"/>          <!-- Space | Comment | Name -->
    <xsl:variable name="state" select="p:consume(12, $input, $state)"/>             <!-- Name -->
    <xsl:variable name="state" select="p:lookahead1W(1, $input, $state)"/>          <!-- Space | Comment | SYNTAX -->
    <xsl:variable name="state" select="p:consume(6, $input, $state)"/>              <!-- SYNTAX -->
    <xsl:variable name="state" select="p:lookahead1W(3, $input, $state)"/>          <!-- Space | Comment | Name -->
    <xsl:variable name="state" select="p:consume(12, $input, $state)"/>             <!-- Name -->
    <xsl:variable name="state" select="p:lookahead1W(4, $input, $state)"/>          <!-- Space | Comment | '::=' -->
    <xsl:variable name="state" select="p:consume(20, $input, $state)"/>             <!-- '::=' -->
    <xsl:variable name="state" select="p:lookahead1W(14, $input, $state)"/>         <!-- Space | StringLiteral | Comment | Sem | Name | '(' | '.' | '<*' |
                                                                                         '<+' | '[' | '{*' | '{+' | '|' -->
    <xsl:variable name="state" select="p:whitespace($input, $state)"/>
    <xsl:variable name="state" select="
      if ($state[$p:error]) then
        $state
      else
        p:parse-Choice($input, $state)
    "/>
    <xsl:variable name="state" select="p:consume(19, $input, $state)"/>             <!-- '.' -->
    <xsl:variable name="state" select="p:parse-Production-1($input, $state)"/>
    <xsl:variable name="state" select="p:consume(7, $input, $state)"/>              <!-- END_OF_RULE -->
    <xsl:variable name="state" select="p:lookahead1W(3, $input, $state)"/>          <!-- Space | Comment | Name -->
    <xsl:variable name="state" select="p:consume(12, $input, $state)"/>             <!-- Name -->
    <xsl:variable name="state" select="p:lookahead1W(8, $input, $state)"/>          <!-- Space | Comment | RULE | EndOfGrammar | EOF | '.' -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 19">                                        <!-- '.' -->
          <xsl:variable name="state" select="p:consume(19, $input, $state)"/>       <!-- '.' -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="end" select="$state[$p:e0]"/>
    <xsl:sequence select="p:reduce($state, 'Production', $count, $begin, $end)"/>
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
        <xsl:variable name="state" select="p:lookahead1W(0, $input, $state)"/>      <!-- Space | Comment | RULE -->
        <xsl:variable name="state" select="p:whitespace($input, $state)"/>
        <xsl:variable name="state" select="
          if ($state[$p:error]) then
            $state
          else
            p:parse-Production($input, $state)
        "/>
        <xsl:variable name="state" select="p:lookahead1W(7, $input, $state)"/>      <!-- Space | Comment | RULE | EndOfGrammar | EOF -->
        <xsl:choose>
          <xsl:when test="$state[$p:l1] != 5">                                      <!-- RULE -->
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
    <xsl:variable name="state" select="p:lookahead1W(5, $input, $state)"/>          <!-- Space | Comment | BeginOfGrammar | RULE -->
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 4">                                         <!-- BeginOfGrammar -->
          <xsl:variable name="state" select="p:consume(4, $input, $state)"/>        <!-- BeginOfGrammar -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:parse-Grammar-1($input, $state)"/>
    <xsl:variable name="state" as="item()+">
      <xsl:choose>
        <xsl:when test="$state[$p:error]">
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:when test="$state[$p:l1] = 10">                                        <!-- EndOfGrammar -->
          <xsl:variable name="state" select="p:consume(10, $input, $state)"/>       <!-- EndOfGrammar -->
          <xsl:sequence select="$state"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:sequence select="$state"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:variable>
    <xsl:variable name="state" select="p:lookahead1W(2, $input, $state)"/>          <!-- Space | Comment | EOF -->
    <xsl:variable name="state" select="p:consume(11, $input, $state)"/>             <!-- EOF -->
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
          subsequence($state, $p:l1, 3),
          0, 0, 0,
          subsequence($state, 7),
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
      <xsl:when test="$match[1] = 1                                                   (: Space :)
                   or $match[1] = 3">                                               <!-- Comment -->
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
          p:matchW($input, $state[$p:e0], $set)
        "/>
        <xsl:sequence select="
          $match[1],
          subsequence($state, $p:b0, 2),
          $match,
          subsequence($state, 7)
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

    <xsl:variable name="state" select="0, 1, 1, 0, 0, 0, false()"/>
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