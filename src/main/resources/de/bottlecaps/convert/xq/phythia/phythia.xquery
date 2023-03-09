xquery version "1.0" encoding "UTF-8";

(: This file was generated on Thu Mar 9, 2023 13:03 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net> :)
(: REx command line: -q -tree -a none -xquery -name de/bottlecaps/convert/xq/phythia/phythia.xquery ../../../../../../../main/java/de/bottlecaps/convert/phythia/phythia.ebnf :)

(:~
 : The parser that was generated for the de/bottlecaps/convert/xq/phythia/phythia.xquery grammar.
 :)
module namespace p="de/bottlecaps/convert/xq/phythia/phythia.xquery";
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
 : The index of the lexer state for accessing the token code that
 : was expected when an error was found.
 :)
declare variable $p:error as xs:integer := 7;

(:~
 : The index of the lexer state that points to the first entry
 : used for collecting action results.
 :)
declare variable $p:result as xs:integer := 8;

(:~
 : The codepoint to charclass mapping for 7 bit codepoints.
 :)
declare variable $p:MAP0 as xs:integer+ :=
(
  42, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 2, 2, 2, 2,
  4, 5, 6, 7, 8, 2, 9, 10, 11, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 12, 13, 14, 15, 16, 2, 2, 17, 18, 19, 20, 21, 22, 23, 24,
  25, 24, 24, 26, 27, 28, 29, 24, 24, 30, 31, 32, 33, 24, 24, 34, 35, 24, 36, 2, 37, 2, 38, 2, 17, 18, 19, 20, 21, 22,
  23, 24, 25, 24, 24, 26, 27, 28, 29, 24, 24, 30, 31, 32, 33, 24, 24, 34, 35, 24, 39, 40, 41, 2, 2
);

(:~
 : The codepoint to charclass mapping for codepoints below the surrogate block.
 :)
declare variable $p:MAP1 as xs:integer+ :=
(
  216, 291, 323, 383, 415, 908, 351, 815, 815, 447, 479, 511, 543, 575, 621, 882, 589, 681, 815, 815, 815, 815, 815,
  815, 815, 815, 815, 815, 815, 815, 713, 745, 821, 649, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815,
  815, 815, 777, 809, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815,
  815, 815, 815, 815, 815, 815, 815, 815, 815, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
  247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
  247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
  247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 259, 815,
  815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
  247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
  247, 247, 247, 247, 247, 247, 247, 247, 853, 940, 949, 941, 941, 957, 965, 1057, 973, 999, 1007, 1015, 1036, 999,
  1007, 1015, 1044, 1258, 1258, 1258, 1258, 1258, 1258, 1259, 1258, 1250, 1250, 1251, 1250, 1250, 1250, 1251, 1250,
  1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
  1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1252, 1258, 1258, 1258, 1258, 1258, 1258,
  1258, 1258, 1258, 1258, 1258, 1250, 1250, 1250, 1250, 1250, 1250, 1343, 1251, 1249, 1248, 1250, 1250, 1250, 1250,
  1250, 1251, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1254, 988, 1250, 1250, 1250, 1250, 1194, 991, 1250, 1250,
  1250, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
  1257, 1258, 990, 1256, 1258, 1063, 1258, 1258, 1258, 1258, 1258, 1249, 1250, 1250, 1255, 1117, 1309, 1062, 1258, 1057,
  1063, 1117, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1345, 1250, 1251, 1128, 1057, 1146, 1025, 1057, 1063,
  1057, 1057, 1057, 1057, 1057, 1057, 1057, 1057, 1059, 1258, 1258, 1258, 1063, 1258, 1258, 1258, 1368, 1227, 1250,
  1250, 1247, 1250, 1250, 1250, 1250, 1251, 1251, 1387, 1248, 1250, 1254, 1258, 1249, 1071, 1250, 1250, 1250, 1250,
  1250, 1250, 1250, 1250, 1249, 1071, 1250, 1250, 1250, 1250, 1080, 1258, 1250, 1250, 1250, 1250, 1250, 1250, 1097,
  1106, 1250, 1250, 1250, 1098, 1252, 1256, 1412, 1250, 1250, 1250, 1250, 1250, 1250, 1165, 1057, 1059, 1026, 1250,
  1151, 1057, 1258, 1258, 1412, 1097, 1344, 1250, 1250, 1248, 1192, 1021, 1137, 1154, 1259, 1203, 1151, 1057, 1256,
  1258, 1214, 1237, 1344, 1250, 1250, 1248, 1377, 1021, 1157, 1154, 1258, 1225, 1260, 1057, 1235, 1258, 1412, 1226,
  1247, 1250, 1250, 1248, 1245, 1165, 1297, 1089, 1258, 1258, 1176, 1057, 1258, 1258, 1412, 1097, 1344, 1250, 1250,
  1248, 1341, 1165, 1027, 1154, 1260, 1203, 1109, 1057, 1258, 1258, 1184, 1269, 1285, 1281, 1195, 1269, 1119, 1109,
  1028, 1025, 1259, 1258, 1259, 1057, 1258, 1258, 1412, 1071, 1248, 1250, 1250, 1248, 1072, 1109, 1298, 1025, 1261,
  1258, 1109, 1057, 1258, 1258, 1184, 1071, 1248, 1250, 1250, 1248, 1072, 1109, 1298, 1025, 1261, 1401, 1109, 1057,
  1258, 1258, 1184, 1071, 1248, 1250, 1250, 1248, 1250, 1109, 1138, 1025, 1259, 1258, 1109, 1057, 1258, 1258, 1258,
  1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1250, 1250,
  1250, 1250, 1252, 1258, 1250, 1250, 1250, 1250, 1251, 1258, 1249, 1250, 1250, 1250, 1250, 1251, 1293, 1062, 1306,
  1058, 1057, 1063, 1258, 1258, 1258, 1258, 1206, 1318, 989, 1249, 1328, 1338, 1293, 1171, 1353, 1059, 1057, 1063, 1258,
  1258, 1258, 1258, 1401, 1273, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1255, 1258, 1258, 1258,
  1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1245, 1376, 1255, 1258, 1258, 1258, 1258,
  1385, 1257, 1385, 1194, 986, 1330, 1193, 1205, 1258, 1258, 1258, 1258, 1401, 1258, 1320, 1400, 1283, 1255, 1258, 1258,
  1258, 1258, 1396, 1257, 1398, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
  1250, 1250, 1250, 1250, 1250, 1254, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1256, 1250,
  1250, 1252, 1252, 1250, 1250, 1250, 1250, 1252, 1252, 1250, 1388, 1250, 1250, 1250, 1252, 1250, 1250, 1250, 1250,
  1250, 1250, 1071, 1120, 1217, 1253, 1098, 1254, 1250, 1253, 1217, 1253, 980, 1258, 1258, 1258, 1249, 1310, 1136, 1258,
  1249, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1253, 1181, 1249, 1250, 1250, 1250, 1250, 1250, 1250,
  1250, 1250, 1250, 1250, 1409, 988, 1250, 1250, 1250, 1250, 1253, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258,
  1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258,
  1258, 1258, 1258, 1258, 1057, 1060, 1366, 1258, 1258, 1258, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
  1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1254, 1258, 1258, 1258, 1258, 1258, 1258, 1258,
  1258, 1258, 1258, 1258, 1063, 1057, 1063, 1050, 1358, 1250, 1249, 1250, 1250, 1250, 1256, 1056, 1057, 1298, 1061,
  1297, 1056, 1057, 1059, 1056, 1366, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1249, 1250, 1250, 1250, 1251,
  1398, 1249, 1250, 1250, 1250, 1251, 1258, 1056, 1057, 1134, 1057, 1057, 1085, 1363, 1258, 1250, 1250, 1250, 1255,
  1255, 1258, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 2, 3, 2, 2, 2, 2, 4, 5, 6, 7, 8, 2, 9, 10, 11, 9,
  9, 12, 13, 14, 15, 16, 2, 2, 2, 2, 2, 9, 2, 24, 2, 2, 2, 2, 2, 24, 24, 24, 24, 24, 24, 2, 17, 18, 19, 20, 21, 22, 23,
  24, 25, 24, 24, 26, 27, 28, 29, 24, 24, 30, 31, 32, 33, 24, 24, 2, 2, 9, 2, 9, 9, 9, 9, 2, 2, 2, 9, 9, 34, 35, 24, 36,
  2, 37, 2, 38, 34, 35, 24, 39, 40, 41, 2, 2, 2, 2, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 2, 2, 2, 2, 2, 2, 24, 24, 24, 24,
  24, 2, 24, 24, 24, 24, 24, 2, 9, 9, 9, 9, 2, 9, 9, 9, 2, 9, 9, 9, 2, 2, 24, 24, 24, 24, 24, 2, 2, 24, 24, 24, 2, 2,
  24, 24, 2, 2, 2, 2, 9, 9, 9, 24, 24, 24, 24, 24, 24, 24, 2, 24, 2, 24, 24, 24, 24, 2, 24, 9, 9, 2, 9, 9, 9, 9, 9, 2,
  2, 9, 9, 9, 9, 9, 9, 9, 24, 24, 9, 9, 2, 2, 9, 9, 9, 2, 2, 2, 2, 9, 24, 24, 2, 2, 9, 24, 9, 9, 2, 9, 9, 24, 2, 2, 2,
  2, 2, 9, 9, 2, 2, 9, 9, 2, 24, 24, 24, 24, 2, 24, 2, 2, 2, 24, 24, 2, 2, 2, 2, 2, 2, 2, 24, 24, 2, 24, 2, 2, 24, 2, 2,
  9, 2, 2, 24, 24, 24, 2, 24, 24, 2, 24, 24, 24, 24, 2, 24, 2, 24, 24, 9, 9, 24, 24, 24, 2, 2, 2, 2, 24, 24, 2, 24, 24,
  2, 24, 24, 24, 24, 24, 24, 24, 24, 2, 2, 2, 2, 2, 2, 2, 2, 9, 9, 2, 24, 24, 24, 2, 2, 2, 24, 24, 2, 2, 24, 2, 2, 24,
  24, 2, 24, 2, 24, 24, 24, 24, 2, 2, 24, 9, 24, 24, 9, 9, 9, 9, 9, 9, 2, 9, 9, 24, 24, 24, 24, 24, 24, 9, 9, 9, 9, 9,
  9, 24, 2, 24, 2, 2, 24, 2, 2, 24, 24, 2, 24, 24, 24, 2, 24, 2, 24, 2, 24, 2, 2, 24, 24, 2, 24, 24, 2, 2, 24, 24, 24,
  24, 24, 2, 24, 24, 24, 24, 24, 2, 9, 2, 2, 2, 2, 9, 9, 2, 9, 2, 2, 2, 2, 2, 2, 24, 9, 2, 24, 2, 24, 24, 2, 24, 24, 2,
  2, 2, 2, 2, 24, 2, 24, 2, 24, 2, 24, 2, 2, 2, 24, 2, 2, 2, 2, 2, 2, 2, 24, 2, 24, 24, 24, 2, 9, 9, 9, 2, 24, 24, 24
);

(:~
 : The codepoint to charclass mapping for codepoints above the surrogate block.
 :)
declare variable $p:MAP2 as xs:integer+ :=
(
  57344, 65536, 65533, 1114111, 2, 2
);

(:~
 : The token-set-id to DFA-initial-state mapping.
 :)
declare variable $p:INITIAL as xs:integer+ :=
(
  1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18
);

(:~
 : The DFA transition table.
 :)
declare variable $p:TRANSITION as xs:integer+ :=
(
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1376, 1376, 1381, 1574, 1546, 1545,
  1392, 1394, 1533, 2366, 1402, 1404, 1781, 1414, 1434, 1421, 1445, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404,
  1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1463, 1469, 2289, 1546, 1545, 1589, 1472, 1520, 1522, 1480, 1482, 1484, 1847, 1437, 1436, 2112, 2148,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1492, 1498, 1679, 1546,
  1545, 1706, 1708, 1501, 1503, 1932, 1934, 1936, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1511, 1517, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991,
  1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1530, 1530, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112,
  2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2372, 1541, 1639,
  1554, 1545, 1561, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1691, 1570, 1661, 1582, 1545, 1544, 1590, 1591, 2271,
  1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1649, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436,
  2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1599, 1604,
  1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 1616, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1629, 1629, 1635, 1574, 1546, 1545, 1647, 1590, 1591,
  2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 2376, 1545, 1562, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437,
  1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2405,
  1657, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1669, 1675, 1574, 1546, 1545, 1544, 1590,
  1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1574, 1546, 1687, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847,
  1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1574, 1699, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 1716,
  1739, 1591, 2271, 1991, 2340, 1406, 1844, 2090, 1451, 1748, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 1740, 1807, 2308, 2310, 2312,
  1763, 1755, 1754, 1769, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 1591, 1989, 1991, 1404, 1406, 1847, 1437, 1436, 1426, 2148, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 2153,
  2175, 1590, 1591, 2271, 1777, 1404, 1789, 1847, 1455, 1436, 2112, 1802, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 2196, 1815, 1728, 1960, 1546, 1648, 1829, 1590, 1837, 1918, 2240, 2346,
  1858, 1863, 1871, 1903, 1905, 1913, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 2334, 2271, 1991, 1404, 1406, 1794, 1437, 1946, 2112, 2148,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546,
  1648, 2175, 1590, 1591, 2271, 1926, 1404, 1406, 1847, 1944, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 1991,
  1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 1591, 1954, 1991, 1968, 1406, 1847, 1437, 1436, 1878,
  2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608,
  1546, 1978, 2175, 1986, 1591, 2271, 1991, 1999, 1406, 1847, 1437, 1436, 2112, 2011, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2179, 1722, 1728, 1608, 1546, 1384, 2175, 1590, 1591, 2271,
  1991, 1404, 2029, 1847, 1437, 2037, 1895, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1731, 1722, 1728, 2056, 1546, 2064, 2175, 2016, 1591, 2261, 1991, 2072, 1406, 1847, 2044, 2080,
  2112, 2231, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728,
  2098, 1546, 1648, 2175, 2303, 1591, 2271, 1991, 1404, 1406, 2106, 1437, 1890, 2048, 2148, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2120, 2127, 1728, 1608, 1546, 1648, 2175, 1590, 1591,
  1621, 2208, 1404, 1406, 2109, 1885, 1436, 2141, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 2161, 2187, 2193, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 2003, 2204, 1406, 1847, 2087,
  1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722,
  1728, 1608, 1546, 1648, 2216, 1590, 2236, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2224, 2148, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 2133, 1608, 1546, 1648, 2248, 1590,
  1591, 2271, 2021, 1404, 1406, 1847, 1437, 1436, 2112, 2256, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 2269, 2271, 1991, 1404, 1406, 1847,
  1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731,
  1722, 1821, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2279, 2285, 1574, 1546, 1545, 1544,
  1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2297, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406,
  1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1731, 1722, 1728, 1608, 1546, 1648, 2320, 1590, 1591, 2328, 1991, 1404, 1970, 1850, 1437, 1436, 2112, 2148, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2354, 2360, 1574, 1546, 1545,
  1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2384, 2390, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404,
  1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1574, 2168, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2396, 2404, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
  1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 531, 531, 531, 531, 531, 531, 531, 531, 0, 0, 0, 0, 0, 54, 0, 3384,
  40, 0, 0, 0, 0, 0, 61, 62, 63, 64, 81, 63, 64, 72, 0, 86, 0, 63, 64, 72, 86, 0, 105, 86, 0, 72, 86, 0, 114, 86, 125,
  72, 86, 125, 72, 86, 113, 72, 86, 113, 2560, 113, 113, 114, 86, 113, 72, 86, 113, 72, 86, 113, 72, 72, 86, 134, 72,
  86, 134, 86, 113, 72, 127, 113, 72, 86, 113, 72, 86, 122, 72, 0, 29, 29, 29, 29, 29, 29, 29, 0, 0, 0, 0, 0, 0, 63, 70,
  64, 0, 70, 64, 72, 0, 86, 0, 70, 64, 72, 86, 0, 0, 30, 30, 30, 30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 63, 71, 72, 0, 0, 0,
  3584, 3584, 3584, 3584, 3584, 3584, 3584, 0, 0, 0, 0, 0, 0, 70, 64, 72, 0, 0, 0, 3840, 0, 0, 0, 0, 0, 0, 70, 71, 72,
  0, 38, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 40, 6912, 0, 0, 0, 0, 0, 0, 49, 0, 0, 0, 0, 0, 0, 0, 43, 0, 39, 0, 0, 0, 0, 0,
  0, 29, 30, 0, 0, 7168, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 63, 64, 72, 5120, 0, 0, 0, 0, 0, 5120, 0, 0, 0, 0, 0,
  0, 29, 30, 0, 3351, 113, 113, 113, 113, 2929, 0, 0, 0, 63, 64, 72, 85, 0, 20, 20, 20, 20, 20, 20, 20, 20, 0, 0, 0, 0,
  0, 0, 29, 30, 5888, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 3351, 0, 0, 5632, 0, 0, 0, 0, 0, 0, 29, 30, 6144, 0, 0, 31, 31, 31,
  31, 31, 31, 31, 0, 0, 0, 0, 0, 0, 29, 768, 0, 0, 0, 0, 5376, 0, 0, 0, 0, 0, 36, 37, 0, 0, 0, 4096, 0, 4608, 0, 4096,
  4608, 40, 0, 0, 0, 0, 0, 0, 64, 63, 71, 40, 0, 0, 0, 0, 60, 0, 3351, 3351, 3351, 3351, 3351, 3351, 3351, 0, 0, 0, 0,
  3351, 0, 0, 0, 0, 65, 0, 0, 0, 0, 0, 63, 64, 78, 132, 86, 113, 72, 86, 113, 86, 113, 78, 86, 113, 78, 86, 113, 78, 78,
  86, 0, 78, 86, 0, 78, 86, 113, 78, 86, 113, 86, 113, 0, 88, 89, 72, 0, 86, 0, 63, 64, 105, 86, 0, 0, 103, 0, 63, 64,
  72, 86, 0, 111, 112, 0, 72, 86, 138, 113, 113, 113, 113, 0, 0, 0, 63, 64, 78, 0, 0, 28, 3351, 3351, 3351, 3351, 3351,
  3351, 3351, 0, 0, 0, 42, 3351, 0, 40, 1536, 0, 0, 0, 0, 0, 3351, 0, 72, 0, 0, 0, 76, 77, 72, 86, 110, 72, 86, 0, 72,
  86, 0, 72, 86, 0, 72, 115, 2048, 92, 0, 63, 64, 72, 92, 0, 72, 92, 0, 72, 92, 116, 72, 92, 116, 72, 121, 116, 72, 86,
  113, 72, 135, 113, 86, 113, 72, 86, 113, 120, 86, 113, 72, 86, 128, 72, 86, 113, 72, 86, 136, 86, 113, 92, 116, 72,
  92, 116, 72, 92, 116, 92, 116, 116, 116, 116, 141, 116, 0, 0, 0, 63, 64, 84, 0, 0, 87, 63, 64, 90, 0, 86, 0, 63, 71,
  72, 0, 86, 0, 63, 71, 72, 86, 0, 113, 117, 86, 113, 72, 86, 113, 72, 86, 131, 0, 80, 0, 63, 64, 72, 0, 0, 46, 0, 29,
  30, 0, 3376, 64, 96, 0, 86, 0, 63, 64, 72, 106, 0, 50, 0, 0, 0, 0, 0, 0, 3351, 0, 66, 0, 0, 0, 0, 63, 64, 72, 0, 86,
  0, 63, 64, 72, 97, 86, 0, 63, 64, 72, 0, 86, 0, 94, 113, 113, 140, 113, 113, 0, 0, 0, 68, 0, 0, 63, 64, 72, 91, 86, 0,
  63, 0, 86, 104, 2304, 2304, 72, 86, 107, 124, 113, 126, 86, 113, 129, 86, 113, 72, 86, 119, 72, 86, 113, 72, 86, 113,
  86, 137, 0, 45, 0, 47, 29, 30, 0, 3351, 0, 51, 0, 0, 0, 0, 0, 3351, 64, 72, 0, 98, 0, 63, 64, 102, 98, 113, 72, 86,
  113, 72, 130, 113, 72, 118, 113, 72, 86, 113, 72, 86, 113, 123, 44, 0, 0, 0, 29, 30, 0, 3351, 108, 109, 0, 72, 86, 0,
  72, 86, 113, 72, 86, 113, 86, 113, 21, 0, 0, 3351, 0, 21, 0, 21, 3351, 3351, 3351, 3351, 3351, 3351, 3351, 0, 0, 41,
  0, 3351, 0, 72, 86, 113, 1280, 86, 113, 86, 113, 113, 113, 113, 113, 0, 0, 0, 52, 53, 0, 55, 3351, 0, 22, 0, 3351, 0,
  0, 27, 0, 0, 4352, 0, 4864, 4352, 4864, 40, 0, 0, 0, 0, 0, 0, 3351, 0, 25, 0, 0, 0, 3360, 3360, 3360, 3360, 3360,
  3360, 3360, 0, 0, 0, 0, 3351, 0, 0, 26, 28, 95, 72, 0, 86, 0, 63, 64, 72, 0, 86, 93, 63, 40, 0, 57, 0, 0, 0, 0, 3351,
  72, 133, 113, 72, 86, 113, 86, 113, 113, 113, 113, 119, 0, 0, 0, 74, 0, 63, 64, 72, 0, 92, 0, 63, 40, 0, 0, 58, 0, 0,
  0, 3351, 113, 139, 113, 113, 113, 0, 0, 0, 82, 83, 72, 0, 0, 1792, 0, 0, 0, 0, 63, 64, 72, 0, 0, 0, 6400, 6400, 6400,
  6400, 6400, 6400, 6400, 0, 0, 0, 0, 0, 0, 768, 30, 0, 0, 6656, 6656, 0, 0, 0, 0, 0, 0, 67, 0, 69, 0, 63, 64, 78, 0,
  86, 0, 63, 64, 78, 86, 0, 40, 0, 0, 0, 59, 0, 0, 3351, 79, 0, 0, 63, 64, 72, 0, 0, 73, 0, 75, 63, 64, 72, 0, 86, 99,
  63, 64, 72, 0, 92, 0, 100, 101, 72, 0, 33, 33, 33, 33, 33, 33, 33, 0, 0, 0, 0, 0, 0, 81, 63, 64, 72, 0, 0, 34, 35, 0,
  0, 0, 0, 24, 0, 0, 0, 0, 7424, 7424, 7424, 7424, 7424, 7424, 7424, 0, 0, 0, 0, 0, 0, 3072, 0, 0, 0, 0, 3072, 3072, 0,
  0, 0, 0, 0, 0, 0, 5632
);

(:~
 : The DFA-state to expected-token-set mapping.
 :)
declare variable $p:EXPECTED as xs:integer+ :=
(
  36, 40, 44, 48, 52, 63, 58, 67, 71, 75, 79, 90, 55, 90, 57, 97, 57, 86, 59, 88, 86, 59, 88, 86, 59, 100, 101, 102,
  103, 101, 102, 103, 101, 83, 94, 103, 42, 74, 2058, 4106, 1048586, 58, 650, 3114, 527402, 499151118, 499167502,
  499200270, 499265806, 499396878, 499659022, 501231886, 532689166, 535818510, 2, 8, 32, 64, 16, 128, 512, 1024, 256,
  32, 64, 4096, 1048576, 4, 4, 12582912, 4352, 201326592, 32768, 65536, 131072, 262144, 98304, 393216, 8, 32, 64,
  1048576, 16, 512, 1024, 512, 1024, 256, 256, 16, 128, 512, 1024, 4352, 1024, 1024, 1024, 1024, 256, 256, 256, 16, 512,
  1024, 16, 512, 1024
);

(:~
 : The token-string table.
 :)
declare variable $p:TOKEN as xs:string+ :=
(
  "(0)",
  "Space",
  "StringLiteral",
  "Comment",
  "BeginOfGrammar",
  "RULE",
  "SYNTAX",
  "END_OF_RULE",
  "Sem",
  "Semantic",
  "EndOfGrammar",
  "EOF",
  "Name",
  "'('",
  "')'",
  "'*>'",
  "'*}'",
  "'+>'",
  "'+}'",
  "'.'",
  "'::='",
  "';'",
  "'<*'",
  "'<+'",
  "'['",
  "']'",
  "'{*'",
  "'{+'",
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
        let $c1 := $c0 idiv 8
        let $c2 := $c1 idiv 32
        return $p:MAP1[1 + $c0 mod 8 + $p:MAP1[1 + $c1 mod 32 + $p:MAP1[1 + $c2]]]
      else
        p:map2($c0, 1, 2)
    let $current := $current + 1
    let $i0 := 256 * $c1 + $current-state - 1
    let $i1 := $i0 idiv 8
    let $next-state := $p:TRANSITION[$i0 mod 8 + $p:TRANSITION[$i1 + 1] + 1]
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
    for $t in 0 to 0
    let $i0 := $t * 141 + $state - 1
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
    else if ($state[$p:l1] = 12) then                       (: Name :)
      let $state := p:consume(12, $input, $state)           (: Name :)
      return $state
    else if ($state[$p:l1] = 2) then                        (: StringLiteral :)
      let $state := p:consume(2, $input, $state)            (: StringLiteral :)
      return $state
    else if ($state[$p:l1] = 22) then                       (: '<*' :)
      let $state := p:consume(22, $input, $state)           (: '<*' :)
      let $state := p:lookahead1W(15, $input, $state)       (: Space | StringLiteral | Comment | Sem | Name | '(' |
                                                               ';' | '<*' | '<+' | '[' | '{*' | '{+' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Choice($input, $state)
      let $state := p:consume(21, $input, $state)           (: ';' :)
      let $state := p:lookahead1W(10, $input, $state)       (: Space | StringLiteral | Comment | Sem | Name | '(' |
                                                               '*>' | '<*' | '<+' | '[' | '{*' | '{+' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Choice($input, $state)
      let $state := p:consume(15, $input, $state)           (: '*>' :)
      return $state
    else if ($state[$p:l1] = 23) then                       (: '<+' :)
      let $state := p:consume(23, $input, $state)           (: '<+' :)
      let $state := p:lookahead1W(15, $input, $state)       (: Space | StringLiteral | Comment | Sem | Name | '(' |
                                                               ';' | '<*' | '<+' | '[' | '{*' | '{+' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Choice($input, $state)
      let $state := p:consume(21, $input, $state)           (: ';' :)
      let $state := p:lookahead1W(12, $input, $state)       (: Space | StringLiteral | Comment | Sem | Name | '(' |
                                                               '+>' | '<*' | '<+' | '[' | '{*' | '{+' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Choice($input, $state)
      let $state := p:consume(17, $input, $state)           (: '+>' :)
      return $state
    else if ($state[$p:l1] = 26) then                       (: '{*' :)
      let $state := p:consume(26, $input, $state)           (: '{*' :)
      let $state := p:lookahead1W(11, $input, $state)       (: Space | StringLiteral | Comment | Sem | Name | '(' |
                                                               '*}' | '<*' | '<+' | '[' | '{*' | '{+' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Choice($input, $state)
      let $state := p:consume(16, $input, $state)           (: '*}' :)
      return $state
    else if ($state[$p:l1] = 27) then                       (: '{+' :)
      let $state := p:consume(27, $input, $state)           (: '{+' :)
      let $state := p:lookahead1W(13, $input, $state)       (: Space | StringLiteral | Comment | Sem | Name | '(' |
                                                               '+}' | '<*' | '<+' | '[' | '{*' | '{+' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Choice($input, $state)
      let $state := p:consume(18, $input, $state)           (: '+}' :)
      return $state
    else if ($state[$p:l1] = 24) then                       (: '[' :)
      let $state := p:consume(24, $input, $state)           (: '[' :)
      let $state := p:lookahead1W(16, $input, $state)       (: Space | StringLiteral | Comment | Sem | Name | '(' |
                                                               '<*' | '<+' | '[' | ']' | '{*' | '{+' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Choice($input, $state)
      let $state := p:consume(25, $input, $state)           (: ']' :)
      return $state
    else if ($state[$p:l1] = 13) then                       (: '(' :)
      let $state := p:consume(13, $input, $state)           (: '(' :)
      let $state := p:lookahead1W(9, $input, $state)        (: Space | StringLiteral | Comment | Sem | Name | '(' |
                                                               ')' | '<*' | '<+' | '[' | '{*' | '{+' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Choice($input, $state)
      let $state := p:consume(14, $input, $state)           (: ')' :)
      return $state
    else
      let $state := p:consume(8, $input, $state)            (: Sem :)
      return $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Item", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Sequence (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Sequence-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(17, $input, $state)         (: Space | StringLiteral | Comment | Sem | Name | '(' |
                                                               ')' | '*>' | '*}' | '+>' | '+}' | '.' | ';' | '<*' |
                                                               '<+' | '[' | ']' | '{*' | '{+' | '|' :)
    return
      if ($state[$p:l1] != 2                                (: StringLiteral :)
      and $state[$p:l1] != 8                                (: Sem :)
      and $state[$p:l1] != 12                               (: Name :)
      and $state[$p:l1] != 13                               (: '(' :)
      and $state[$p:l1] != 22                               (: '<*' :)
      and $state[$p:l1] != 23                               (: '<+' :)
      and $state[$p:l1] != 24                               (: '[' :)
      and $state[$p:l1] != 26                               (: '{*' :)
      and $state[$p:l1] != 27) then                         (: '{+' :)
        $state
      else
        let $state := p:whitespace($input, $state)
        let $state :=
          if ($state[$p:error]) then
            $state
          else
            p:parse-Item($input, $state)
        return p:parse-Sequence-1($input, $state)
};

(:~
 : Parse Sequence.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Sequence($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:parse-Sequence-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Sequence", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Choice (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Choice-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    if ($state[$p:l1] != 28) then                           (: '|' :)
      $state
    else
      let $state := p:consume(28, $input, $state)           (: '|' :)
      let $state := p:lookahead1W(17, $input, $state)       (: Space | StringLiteral | Comment | Sem | Name | '(' |
                                                               ')' | '*>' | '*}' | '+>' | '+}' | '.' | ';' | '<*' |
                                                               '<+' | '[' | ']' | '{*' | '{+' | '|' :)
      let $state := p:whitespace($input, $state)
      let $state :=
        if ($state[$p:error]) then
          $state
        else
          p:parse-Sequence($input, $state)
      return p:parse-Choice-1($input, $state)
};

(:~
 : Parse Choice.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Choice($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Sequence($input, $state)
  let $state := p:parse-Choice-1($input, $state)
  let $end := $state[$p:e0]
  return p:reduce($state, "Choice", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Production (zero or more). Use
 : tail recursion for iteratively updating the lexer state.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Production-1($input as xs:string, $state as item()+)
{
  if ($state[$p:error]) then
    $state
  else
    let $state := p:lookahead1W(6, $input, $state)          (: Space | Comment | END_OF_RULE | Semantic :)
    return
      if ($state[$p:l1] != 9) then                          (: Semantic :)
        $state
      else
        let $state := p:consume(9, $input, $state)          (: Semantic :)
        return p:parse-Production-1($input, $state)
};

(:~
 : Parse Production.
 :
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse-Production($input as xs:string, $state as item()+) as item()+
{
  let $count := count($state)
  let $begin := $state[$p:e0]
  let $state := p:consume(5, $input, $state)                (: RULE :)
  let $state := p:lookahead1W(3, $input, $state)            (: Space | Comment | Name :)
  let $state := p:consume(12, $input, $state)               (: Name :)
  let $state := p:lookahead1W(1, $input, $state)            (: Space | Comment | SYNTAX :)
  let $state := p:consume(6, $input, $state)                (: SYNTAX :)
  let $state := p:lookahead1W(3, $input, $state)            (: Space | Comment | Name :)
  let $state := p:consume(12, $input, $state)               (: Name :)
  let $state := p:lookahead1W(4, $input, $state)            (: Space | Comment | '::=' :)
  let $state := p:consume(20, $input, $state)               (: '::=' :)
  let $state := p:lookahead1W(14, $input, $state)           (: Space | StringLiteral | Comment | Sem | Name | '(' |
                                                               '.' | '<*' | '<+' | '[' | '{*' | '{+' | '|' :)
  let $state := p:whitespace($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else
      p:parse-Choice($input, $state)
  let $state := p:consume(19, $input, $state)               (: '.' :)
  let $state := p:parse-Production-1($input, $state)
  let $state := p:consume(7, $input, $state)                (: END_OF_RULE :)
  let $state := p:lookahead1W(3, $input, $state)            (: Space | Comment | Name :)
  let $state := p:consume(12, $input, $state)               (: Name :)
  let $state := p:lookahead1W(8, $input, $state)            (: Space | Comment | RULE | EndOfGrammar | EOF | '.' :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 19) then                       (: '.' :)
      let $state := p:consume(19, $input, $state)           (: '.' :)
      return $state
    else
      $state
  let $end := $state[$p:e0]
  return p:reduce($state, "Production", $count, $begin, $end)
};

(:~
 : Parse the 1st loop of production Grammar (one or more). Use
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
    let $state := p:lookahead1W(0, $input, $state)          (: Space | Comment | RULE :)
    let $state := p:whitespace($input, $state)
    let $state :=
      if ($state[$p:error]) then
        $state
      else
        p:parse-Production($input, $state)
    let $state := p:lookahead1W(7, $input, $state)          (: Space | Comment | RULE | EndOfGrammar | EOF :)
    return
      if ($state[$p:l1] != 5) then                          (: RULE :)
        $state
      else
        p:parse-Grammar-1($input, $state)
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
  let $state := p:lookahead1W(5, $input, $state)            (: Space | Comment | BeginOfGrammar | RULE :)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 4) then                        (: BeginOfGrammar :)
      let $state := p:consume(4, $input, $state)            (: BeginOfGrammar :)
      return $state
    else
      $state
  let $state := p:parse-Grammar-1($input, $state)
  let $state :=
    if ($state[$p:error]) then
      $state
    else if ($state[$p:l1] = 10) then                       (: EndOfGrammar :)
      let $state := p:consume(10, $input, $state)           (: EndOfGrammar :)
      return $state
    else
      $state
  let $state := p:lookahead1W(2, $input, $state)            (: Space | Comment | EOF :)
  let $state := p:consume(11, $input, $state)               (: EOF :)
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
    subsequence($state, $p:l1, 3),
    0, 0, 0,
    subsequence($state, 7),
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
    if ($match[1] = 1                                       (: Space :)
     or $match[1] = 3) then                                 (: Comment :)
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
        p:matchW($input, $state[$p:e0], $set)
    return
    (
      $match[1],
      subsequence($state, $p:b0, 2),
      $match,
      subsequence($state, 7)
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
  let $state := (0, 1, 1, 0, 0, 0, false())
  let $state := p:parse-Grammar($s, $state)
  let $error := $state[$p:error]
  return
    if ($error) then
      element ERROR {$error/@*, p:error-message($s, $error)}
    else
      subsequence($state, $p:result)
};

(: End :)
