<?xml version="1.0" encoding="UTF-8"?>
<!-- This file was generated on Thu Mar 9, 2023 13:03 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net> -->
<!-- REx command line: -q -lalr 4 -tree -a none -xslt -name de/bottlecaps/convert/xq/instaparse/instaparse.xslt ../../../../../../../main/java/de/bottlecaps/convert/instaparse/instaparse.ebnf -->

<xsl:stylesheet version="2.0"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:p="de/bottlecaps/convert/xq/instaparse/instaparse.xslt">
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
   ! The index of the lexer state for accessing the code of the
   ! level-4-lookahead token.
  -->
  <xsl:variable name="p:l4" as="xs:integer" select="13"/>

  <!--~
   ! The index of the lexer state for accessing the position in the
   ! input string of the begin of the level-4-lookahead token.
  -->
  <xsl:variable name="p:b4" as="xs:integer" select="14"/>

  <!--~
   ! The index of the lexer state for accessing the position in the
   ! input string of the end of the level-4-lookahead token.
  -->
  <xsl:variable name="p:e4" as="xs:integer" select="15"/>

  <!--~
   ! The index of the lexer state for accessing the token code that
   ! was expected when an error was found.
  -->
  <xsl:variable name="p:error" as="xs:integer" select="16"/>

  <!--~
   ! The index of the lexer state that points to the first entry
   ! used for collecting action results.
  -->
  <xsl:variable name="p:result" as="xs:integer" select="17"/>

  <!--~
   ! The codepoint to charclass mapping for 7 bit codepoints.
  -->
  <xsl:variable name="p:MAP0" as="xs:integer+" select="
    41, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 11, 1, 5, 12, 13, 5, 5,
    5, 5, 5, 5, 5, 5, 5, 5, 14, 15, 16, 17, 18, 19, 5, 5, 5, 5, 5, 20, 5, 5, 5, 21, 5, 5, 22, 5, 23, 24, 25, 5, 5, 26, 5, 5, 5, 5, 5, 5, 5, 27, 28, 29, 5, 5, 5,
    5, 5, 5, 5, 30, 5, 5, 5, 31, 5, 5, 32, 5, 33, 34, 35, 5, 5, 36, 5, 5, 5, 5, 5, 5, 5, 37, 38, 39, 5, 5
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints below the surrogate block.
  -->
  <xsl:variable name="p:MAP1" as="xs:integer+" select="
    27, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 74, 138, 200, 200, 200, 200, 200, 200, 200, 200,
    200, 200, 200, 200, 211, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200,
    200, 200, 200, 200, 200, 200, 41, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9,
    10, 11, 1, 5, 12, 13, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 14, 15, 16, 17, 18, 19, 5, 5, 5, 5, 5, 20, 5, 5, 5, 21, 5, 5, 22, 5, 23, 24, 25, 5, 5, 26, 5, 5, 5, 5,
    5, 5, 5, 27, 28, 29, 5, 5, 5, 5, 5, 5, 5, 30, 5, 5, 5, 31, 5, 5, 32, 5, 33, 34, 35, 5, 5, 36, 5, 5, 5, 5, 5, 5, 5, 37, 38, 39, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
    5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
    5, 5, 40, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5
  "/>

  <!--~
   ! The codepoint to charclass mapping for codepoints above the surrogate block.
  -->
  <xsl:variable name="p:MAP2" as="xs:integer+" select="
    57344, 65536, 65533, 1114111, 5, 5
  "/>

  <!--~
   ! The token-set-id to DFA-initial-state mapping.
  -->
  <xsl:variable name="p:INITIAL" as="xs:integer+" select="
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
  "/>

  <!--~
   ! The DFA transition table.
  -->
  <xsl:variable name="p:TRANSITION" as="xs:integer+" select="
    690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 1060, 1064, 1066, 672, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690,
    690, 690, 689, 690, 682, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 674, 734, 701, 699, 709, 722, 1355, 690, 690, 690, 690,
    690, 690, 690, 690, 689, 714, 742, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1116, 762, 690,
    690, 690, 690, 690, 690, 690, 689, 690, 783, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 974, 804, 691, 1192, 824, 1188, 1355,
    690, 690, 690, 690, 690, 690, 690, 690, 844, 847, 837, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 811, 954, 816, 855, 892, 879,
    1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 912, 690, 905, 924, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 690, 938, 691,
    978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 1225, 962, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 1249,
    988, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 950, 829, 1359, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690,
    689, 1020, 1008, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 1028, 1032, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690,
    690, 690, 690, 689, 1245, 897, 916, 978, 1040, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 790, 884, 796, 691, 978, 879, 1239, 1355, 690, 690, 690,
    690, 690, 690, 690, 690, 689, 690, 1053, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 995, 1074, 1081, 728, 770, 861, 1110, 1116, 762,
    690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1089, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110,
    1104, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1116, 867, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770,
    861, 1110, 1160, 1128, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 873, 1154, 861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758,
    1120, 728, 1168, 1140, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 689, 1045, 1202, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690,
    690, 689, 690, 1212, 980, 1210, 1220, 1233, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 749, 750, 1257, 691, 978, 879, 1239, 1355, 690, 690, 690, 690,
    690, 690, 690, 690, 1015, 1265, 1272, 728, 770, 861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1096, 1116, 762,
    690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1175, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110,
    1116, 775, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1146, 1182, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 1134,
    1280, 861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 1288, 930, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 689,
    1000, 1296, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 1194, 1304, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690,
    690, 690, 689, 1312, 1319, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 945, 1327, 1334, 728, 770, 861, 1110, 1116, 762, 690, 690,
    690, 690, 690, 690, 690, 690, 1342, 1349, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 0, 1050, 0, 0, 0, 0, 0, 32, 0, 0, 0, 1152, 1152,
    1152, 1152, 1152, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 32, 43, 34, 0, 0, 0, 0, 0, 0, 0, 512, 0, 563, 768, 44, 34, 0, 0, 0, 0, 0, 33, 0, 0, 0, 0, 512, 43, 44, 54,
    0, 0, 0, 4124, 4124, 4124, 0, 32, 32, 32, 32, 32, 1, 1, 0, 33, 33, 33, 33, 33, 1, 1, 0, 0, 0, 0, 0, 0, 3712, 3712, 0, 4124, 4124, 0, 4124, 4124, 4124, 4124,
    4124, 4124, 0, 0, 0, 34, 4124, 4124, 0, 4124, 4124, 4124, 28, 4124, 284, 0, 0, 0, 1280, 1280, 1280, 1280, 1280, 1, 1, 0, 0, 0, 0, 0, 3456, 0, 3456, 3456,
    3456, 3456, 1, 1, 0, 34, 34, 34, 34, 34, 1, 1, 0, 0, 0, 0, 2560, 0, 0, 2560, 2560, 2560, 1, 17280, 0, 32, 43, 640, 438, 0, 0, 0, 0, 0, 1695, 1, 1, 27, 1435,
    1435, 1435, 1435, 1435, 23, 23, 0, 27, 27, 27, 27, 27, 27, 1435, 27, 27, 2816, 0, 0, 0, 0, 0, 0, 32, 43, 44, 34, 4124, 4124, 4124, 28, 4124, 284, 4124, 0,
    0, 0, 4124, 4134, 4124, 0, 32, 43, 44, 34, 0, 0, 0, 0, 0, 0, 3456, 3456, 0, 34, 0, 0, 17280, 0, 0, 0, 0, 0, 2048, 1, 1, 0, 0, 2688, 0, 2688, 2688, 17317,
    24, 25, 0, 25, 0, 0, 0, 0, 0, 0, 1920, 32, 0, 0, 1536, 0, 0, 0, 0, 32, 43, 44, 34, 4124, 4152, 312, 0, 0, 2944, 0, 2944, 2944, 1, 1, 0, 0, 0, 28, 0, 0, 0,
    1695, 0, 0, 0, 0, 0, 0, 2560, 2560, 3072, 0, 0, 3072, 3072, 3072, 1, 1, 0, 0, 0, 4124, 0, 0, 0, 0, 0, 34, 0, 0, 0, 0, 0, 0, 0, 42, 3200, 0, 3200, 3200,
    3200, 3200, 1, 1, 0, 0, 0, 4125, 0, 0, 0, 0, 0, 2304, 0, 0, 3328, 0, 0, 3328, 3328, 3328, 1, 1, 0, 0, 0, 4126, 0, 0, 0, 0, 3328, 0, 3328, 3328, 0, 4224,
    4224, 0, 4224, 4224, 4224, 4224, 4224, 4224, 1, 1, 1792, 32, 43, 44, 34, 0, 0, 0, 0, 0, 2176, 0, 0, 0, 0, 3584, 0, 3584, 3584, 1, 1, 0, 1050, 0, 1050, 1050,
    1050, 1050, 1050, 1050, 1050, 1050, 1, 1, 0, 4125, 4125, 0, 4125, 4131, 4125, 4125, 4131, 4131, 4131, 4131, 4131, 1, 1, 4153, 4124, 51, 43, 44, 54, 4157,
    4124, 4154, 51, 43, 44, 54, 4124, 4158, 4159, 4124, 51, 54, 4161, 4124, 4124, 4124, 51, 43, 44, 54, 4124, 4124, 51, 54, 4124, 4124, 4124, 4124, 4124, 4124,
    1, 1, 4165, 4124, 4124, 4124, 4124, 4124, 0, 0, 0, 4124, 4135, 4136, 0, 32, 43, 44, 34, 4151, 4124, 4124, 51, 54, 4124, 4124, 4124, 4164, 0, 34, 4142, 4124,
    0, 4124, 4124, 4124, 51, 54, 4124, 4124, 4163, 4124, 0, 34, 4124, 4124, 0, 4145, 4124, 4124, 4160, 51, 54, 4124, 4162, 4124, 4124, 4166, 4124, 4124, 4124,
    4124, 0, 0, 51, 43, 44, 384, 0, 0, 0, 0, 0, 0, 0, 3840, 0, 2176, 2176, 2176, 2176, 2176, 1, 1, 0, 45, 0, 0, 0, 0, 0, 0, 1, 1, 0, 42, 52, 53, 45, 0, 0, 0, 0,
    3072, 0, 3072, 3072, 0, 0, 59, 43, 44, 60, 0, 0, 51, 43, 44, 54, 0, 0, 0, 2048, 0, 0, 0, 0, 0, 0, 3200, 0, 3712, 0, 0, 3712, 3712, 3712, 1, 1, 0, 4126,
    4126, 0, 4126, 4132, 4126, 4126, 4132, 4132, 4132, 4132, 4132, 1, 1, 0, 34, 4143, 4144, 0, 4124, 4124, 4124, 0, 34, 4124, 4124, 0, 4124, 4146, 50, 0, 2304,
    2304, 2304, 2304, 2304, 1, 1, 3840, 0, 3840, 3840, 3840, 3840, 1, 1, 3968, 0, 0, 0, 0, 0, 3968, 3968, 0, 0, 3968, 3968, 3968, 1, 1, 0, 28, 28, 0, 28, 284,
    28, 28, 284, 284, 284, 284, 284, 1, 1, 0, 0, 2432, 0, 2432, 0, 2432, 2432, 0, 0, 2432, 2432, 2432, 0, 0, 51, 54, 0, 0, 0, 0, 0, 0, 41, 32
  "/>

  <!--~
   ! The DFA-state to expected-token-set mapping.
  -->
  <xsl:variable name="p:EXPECTED" as="xs:integer+" select="
    3, 18, 31, 34, 38, 42, 46, 50, 54, 63, 66, 70, 57, 75, 71, 82, 78, 59, 80, 86, 88, 89, 93, 95, 95, 88, 88, 88, 88, 88, 88, 88, 88, 88, 64, 2097152, 2176,
    2099200, -2147481472, 526464, 67111040, 268437632, 1073744000, -2147481472, -2147219328, 63616, -2105276288, -2147283778, -678688640, -158594944,
    -141817728, -2147283010, -1387065410, -141619266, -2158658, -2097218, 64, 64, -2147483648, -2147483648, -2147483648, 8, 4, 2097152, 128, 2048, -2147483648,
    -2147483648, 24576, 8, 48, 4, -2147483646, -2147483646, -2147483646, 8192, 8, 32, 16, 4, -2147483646, -2147483646, -2147483648, -2147483648, 8, 32,
    -2147483646, -2147483646, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1
  "/>

  <!--~
   ! The lookahead enlargement map. Maps lookahead and token to new lookahead code.
  -->
  <xsl:variable name="p:LOOKAHEAD" as="xs:integer+" select="
    43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 44, 42, 52, 63, 55, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 68, 43, 43, 43, 43, 43,
    73, 43, 63, 84, 108, 108, 108, 108, 108, 108, 108, 108, 109, 108, 103, 94, 108, 108, 108, 108, 108, 97, 91, 108, 108, 108, 108, 87, 81, 108, 108, 108, 108,
    101, 108, 108, 108, 108, 108, 108, 107, 108, 0, 0, 41, 0, 0, 39, 0, 0, 0, 37, 0, 0, 42, 0, 0, 40, 0, 0, 0, 38, 0, 34, 0, 0, 0, 36, 33, 0, 0, 0, 0, 35
  "/>

  <!--~
   ! The match-code to case-id map. Maps decision point and lookahead to next action code.
  -->
  <xsl:variable name="p:CASEID" as="xs:integer+" select="
    99, 108, 112, 122, 161, 121, 99, 127, 112, 122, 161, 121, 122, 161, 131, 147, 122, 122, 122, 136, 131, 132, 122, 122, 122, 161, 122, 115, 122, 122, 99, 140,
    121, 104, 150, 122, 99, 140, 121, 99, 140, 121, 99, 140, 121, 99, 140, 121, 144, 140, 121, 144, 140, 121, 99, 140, 121, 99, 140, 121, 122, 152, 122, 122,
    102, 122, 122, 156, 160, 122, 165, 169, 177, 122, 122, 122, 117, 122, 122, 174, 122, 122, 170, 122, 122, 123, 122, 181, 185, 189, 193, 197, 201, 122, 205,
    169, 122, 209, 213, 217, 221, 227, 260, 239, 260, 260, 317, 260, 245, 223, 255, 247, 251, 254, 255, 260, 241, 260, 260, 230, 260, 259, 260, 260, 260, 260,
    236, 245, 265, 268, 247, 272, 260, 260, 260, 278, 284, 260, 260, 308, 282, 260, 260, 308, 217, 221, 233, 260, 261, 317, 260, 288, 260, 260, 290, 260, 274,
    301, 294, 273, 300, 260, 260, 260, 308, 326, 325, 305, 312, 307, 260, 260, 260, 316, 359, 260, 260, 260, 296, 260, 260, 321, 323, 322, 260, 334, 331, 335,
    334, 324, 260, 260, 260, 339, 341, 340, 260, 351, 348, 352, 351, 342, 260, 260, 260, 326, 325, 327, 312, 344, 343, 356, 363, 358, 260, 260, 260, 0, 43016,
    28680, 28680, 30728, 30728, 0, 0, 0, 27094, 34834, 32786, 26642, 0, 0, 24616, 0, 0, 26642, 0, 0, 34856, 0, 2088, 0, 0, 22658, 0, 28690, 30738, 27094, 27094,
    27094, 43, 35, 37, 43, 27094, 27094, 27094, 27094, 0, 24594, 0, 0, 0, 0, 6152, 34840, 0, 36888, 27094, 27094, 27094, 32792, 16402, 0, 0, 0, 10292, 0, 8200,
    8200, 8200, 8200, 28690, 30738, 0, 0, 90, 0, 0, 4262, 0, 0, 14376, 0, 0, 10296, 0, 0, 22738, 0, 10292, 0, 0, 0, 10296, 39314, 18726, 18726, 0, 0, 0, 12296,
    18726, 37170, 18726, 18726, 32808, 0, 0, 0, 18450, 0, 38948, 38948, 38948, 0, 0, 0, 18726, 18726, 0, 34840, 0, 36888, 38948, 38948, 38948, 38948, 32792, 0,
    40996, 40996, 40996, 0, 0, 0, 20870, 20870, 34840, 0, 36888, 40996, 40996, 40996, 40996, 32792, 39314, 20870, 20870, 0, 0, 0, 22568, 20870, 0, 20870, 20870
  "/>

  <!--~
   ! The parser tokenset table. Maps state to lookahead tokenset code.
  -->
  <xsl:variable name="p:TOKENSET" as="xs:integer+" select="
    19, 0, 20, 0, 9, 2, 10, 11, 4, 0, 17, 3, 17, 17, 17, 17, 13, 13, 17, 17, 6, 1, 12, 16, 0, 6, 5, 7, 8, 20, 20, 15, 14
  "/>

  <!--~
   ! The parser lookback table. Maps lookback code and itemset id to next lookback code.
  -->
  <xsl:variable name="p:LOOKBACK" as="xs:integer+" select="
    47, 47, 45, 45, 45, 48, 51, 47, 47, 47, 54, 59, 64, 64, 64, 47, 47, 47, 67, 72, 77, 77, 77, 47, 88, 80, 85, 85, 85, 91, 98, 98, 98, 98, 98, 105, 105, 105,
    105, 105, 112, 112, 112, 112, 112, 3, 2, 0, 3, 4, 0, 5, 5, 0, 13, 12, 8, 7, 0, 13, 14, 8, 9, 0, 11, 11, 0, 21, 20, 16, 15, 0, 21, 22, 16, 17, 0, 19, 19, 0,
    27, 28, 16, 23, 0, 25, 25, 0, 27, 26, 0, 41, 40, 36, 35, 31, 30, 0, 41, 42, 36, 37, 31, 32, 0, 41, 43, 36, 38, 31, 33, 0, 41, 44, 36, 39, 31, 34, 0
  "/>

  <!--~
   ! The parser goto table. Maps state and nonterminal to next action code.
  -->
  <xsl:variable name="p:GOTO" as="xs:integer+" select="
    70, 55, 57, 70, 55, 57, 30, 38, 57, 36, 58, 57, 42, 32, 57, 45, 84, 57, 49, 68, 53, 62, 68, 66, 70, 76, 72, 78, 68, 82, 94, 146, 95, 95, 103, 95, 95, 146,
    95, 95, 156, 95, 95, 90, 95, 95, 97, 107, 119, 95, 97, 116, 119, 125, 119, 121, 88, 95, 95, 95, 95, 101, 95, 97, 135, 119, 139, 119, 121, 88, 95, 97, 96,
    110, 112, 150, 112, 143, 95, 97, 96, 128, 96, 131, 121, 88, 153, 95, 329, 2297, 0, 0, 20489, 0, 6, 0, 0, 0, 0, 21508, 0, 10249, 0, 0, 0, 21513, 0, 22537,
    8196, 8196, 21508, 0, 21508, 21508, 21508, 0, 25609, 8196, 8196, 21508, 23681, 21508, 21508, 21508, 289, 26633, 8196, 8196, 21508, 31913, 21508, 21508,
    32985, 21508, 21508, 27657, 8196, 8196, 21508, 28681, 8196, 8196, 21508, 0, 29705, 0, 0, 6169, 7177, 7177, 0, 30729, 0, 0, 24665, 0, 0, 3076, 0, 3078
  "/>

  <!--~
   ! The token-string table.
  -->
  <xsl:variable name="p:TOKEN" as="xs:string+" select="
    '(0)',
    'epsilon',
    'single-quoted-string',
    'double-quoted-string',
    'single-quoted-regexp',
    'double-quoted-regexp',
    'comment-content',
    'ws',
    &quot;'!'&quot;,
    &quot;'&amp;'&quot;,
    &quot;'('&quot;,
    &quot;'(*'&quot;,
    &quot;':'&quot;,
    &quot;'::='&quot;,
    &quot;':='&quot;,
    &quot;'='&quot;,
    &quot;'['&quot;,
    &quot;'{'&quot;,
    'EOF',
    &quot;')'&quot;,
    &quot;'*'&quot;,
    &quot;'*)'&quot;,
    &quot;'+'&quot;,
    &quot;'.'&quot;,
    &quot;'/'&quot;,
    &quot;';'&quot;,
    &quot;'&gt;'&quot;,
    &quot;'?'&quot;,
    &quot;']'&quot;,
    &quot;'|'&quot;,
    &quot;'}'&quot;,
    'non-epsilon',
    &quot;'&lt;'&quot;
  "/>

  <!--~
   ! The nonterminal name table.
  -->
  <xsl:variable name="p:NONTERMINAL" as="xs:string+" select="
    'rules',
    'comment',
    'inside-comment',
    'opt-whitespace',
    'rule-separator',
    'rule',
    'nt',
    'hide-nt',
    'alt-or-ord',
    'alt',
    'ord',
    'paren',
    'hide',
    'cat',
    'string',
    'regexp',
    'opt',
    'star',
    'plus',
    'look',
    'neg',
    'factor'
  "/>

  <!--~
   ! Predict the decision for a given decision point based on current
   ! lookahead.
   !
   ! @param $input the input string.
   ! @param $state the parser state.
   ! @param $dpi the decision point index.
   ! @return the updated parser state.
  -->
  <xsl:function name="p:predict" as="item()+">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>
    <xsl:param name="dpi" as="xs:integer"/>

    <xsl:variable name="state" select="p:lookahead1W($p:TOKENSET[$dpi + 1], $input, $state)"/>
    <xsl:choose>
      <xsl:when test="$state[$p:l1] lt 0">
        <xsl:variable name="node">
          <xsl:element name="error">
            <xsl:attribute name="b" select="$state[$p:b1]"/>
            <xsl:attribute name="e" select="$state[$p:e1]"/>
            <xsl:attribute name="s" select="- $state[$p:l1]"/>
          </xsl:element>
        </xsl:variable>
        <xsl:sequence select="
          0,
          subsequence($state, $p:lk + 1, $p:error - $p:lk - 1),
          $node/node(),
          subsequence($state, $p:error + 1)
        "/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="j10" select="48 * $dpi + $state[$p:l1]"/>
        <xsl:variable name="j11" select="$j10 idiv 4"/>
        <xsl:variable name="j12" select="$j11 idiv 4"/>
        <xsl:variable name="action" select="$p:CASEID[$j10 mod 4 + $p:CASEID[$j11 mod 4 + $p:CASEID[$j12 + 1] + 1] + 1]"/>
        <xsl:choose>
          <xsl:when test="$action mod 2 eq 0">
            <xsl:sequence select="$action idiv 2, subsequence($state, $p:lk + 1)"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="state" select="p:lookahead2W($action idiv 2, $input, $state)"/>
            <xsl:choose>
              <xsl:when test="$state[$p:l2] lt 0">
                <xsl:sequence select="0, subsequence($state, $p:lk + 1)"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:variable name="i20" select="40 * $state[$p:l2] + $state[$p:l1]"/>
                <xsl:variable name="i21" select="$i20 idiv 4"/>
                <xsl:variable name="i22" select="$i21 idiv 8"/>
                <xsl:variable name="lk" select="$p:LOOKAHEAD[$i20 mod 4 + $p:LOOKAHEAD[$i21 mod 8 + $p:LOOKAHEAD[$i22 + 1] + 1] + 1]"/>
                <xsl:choose>
                  <xsl:when test="$lk eq 0">
                    <xsl:sequence select="p:predict($input, $state, $dpi + 1)"/>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:variable name="j20" select="48 * $dpi + $lk"/>
                    <xsl:variable name="j21" select="$j20 idiv 4"/>
                    <xsl:variable name="j22" select="$j21 idiv 4"/>
                    <xsl:variable name="action" select="$p:CASEID[$j20 mod 4 + $p:CASEID[$j21 mod 4 + $p:CASEID[$j22 + 1] + 1] + 1]"/>
                    <xsl:choose>
                      <xsl:when test="$action mod 2 eq 0">
                        <xsl:sequence select="$action idiv 2, subsequence($state, $p:lk + 1)"/>
                      </xsl:when>
                      <xsl:otherwise>
                        <xsl:variable name="state" select="p:lookahead3W($action idiv 2, $input, $state)"/>
                        <xsl:choose>
                          <xsl:when test="$state[$p:l3] lt 0">
                            <xsl:sequence select="0, subsequence($state, $p:lk + 1)"/>
                          </xsl:when>
                          <xsl:otherwise>
                            <xsl:variable name="i30" select="40 * $state[$p:l3] + $lk"/>
                            <xsl:variable name="i31" select="$i30 idiv 4"/>
                            <xsl:variable name="i32" select="$i31 idiv 8"/>
                            <xsl:variable name="lk" select="$p:LOOKAHEAD[$i30 mod 4 + $p:LOOKAHEAD[$i31 mod 8 + $p:LOOKAHEAD[$i32 + 1] + 1] + 1]"/>
                            <xsl:choose>
                              <xsl:when test="$lk eq 0">
                                <xsl:sequence select="p:predict($input, $state, $dpi + 1)"/>
                              </xsl:when>
                              <xsl:otherwise>
                                <xsl:variable name="j30" select="48 * $dpi + $lk"/>
                                <xsl:variable name="j31" select="$j30 idiv 4"/>
                                <xsl:variable name="j32" select="$j31 idiv 4"/>
                                <xsl:variable name="action" select="$p:CASEID[$j30 mod 4 + $p:CASEID[$j31 mod 4 + $p:CASEID[$j32 + 1] + 1] + 1]"/>
                                <xsl:choose>
                                  <xsl:when test="$action mod 2 eq 0">
                                    <xsl:sequence select="$action idiv 2, subsequence($state, $p:lk + 1)"/>
                                  </xsl:when>
                                  <xsl:otherwise>
                                    <xsl:variable name="state" select="p:lookahead4W($action idiv 2, $input, $state)"/>
                                    <xsl:choose>
                                      <xsl:when test="$state[$p:l4] lt 0">
                                        <xsl:sequence select="0, subsequence($state, $p:lk + 1)"/>
                                      </xsl:when>
                                      <xsl:otherwise>
                                        <xsl:variable name="i40" select="40 * $state[$p:l4] + $lk"/>
                                        <xsl:variable name="i41" select="$i40 idiv 4"/>
                                        <xsl:variable name="i42" select="$i41 idiv 8"/>
                                        <xsl:variable name="lk" select="$p:LOOKAHEAD[$i40 mod 4 + $p:LOOKAHEAD[$i41 mod 8 + $p:LOOKAHEAD[$i42 + 1] + 1] + 1]"/>
                                        <xsl:choose>
                                          <xsl:when test="$lk eq 0">
                                            <xsl:sequence select="p:predict($input, $state, $dpi + 1)"/>
                                          </xsl:when>
                                          <xsl:otherwise>
                                            <xsl:variable name="j40" select="48 * $dpi + $lk"/>
                                            <xsl:variable name="j41" select="$j40 idiv 4"/>
                                            <xsl:variable name="j42" select="$j41 idiv 4"/>
                                            <xsl:variable name="action" select="$p:CASEID[$j40 mod 4 + $p:CASEID[$j41 mod 4 + $p:CASEID[$j42 + 1] + 1] + 1]"/>
                                            <xsl:sequence select="
                                              if ($action ne 0) then
                                                ($action idiv 2, subsequence($state, $p:lk + 1))
                                              else
                                                p:predict($input, $state, $dpi + 1)
                                            "/>
                                          </xsl:otherwise>
                                        </xsl:choose>
                                      </xsl:otherwise>
                                    </xsl:choose>
                                  </xsl:otherwise>
                                </xsl:choose>
                              </xsl:otherwise>
                            </xsl:choose>
                          </xsl:otherwise>
                        </xsl:choose>
                      </xsl:otherwise>
                    </xsl:choose>
                  </xsl:otherwise>
                </xsl:choose>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

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
        <xsl:variable name="end" select="$end - $result idiv 64"/>
        <xsl:variable name="end" select="if ($end gt string-length($input)) then string-length($input) + 1 else $end"/>
        <xsl:sequence select="
          if ($result ne 0) then
          (
            $result mod 64 - 1,
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
              <xsl:variable name="c1" select="$c0 idiv 64"/>
              <xsl:variable name="c2" select="$c1 idiv 32"/>
              <xsl:sequence select="$p:MAP1[1 + $c0 mod 64 + $p:MAP1[1 + $c1 mod 32 + $p:MAP1[1 + $c2]]]"/>
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
   ! Get GOTO table entry for given nonterminal and parser state.
   !
   ! @param $nonterminal the nonterminal.
   ! @param $state the LR parser state.
   ! @return the GOTO table entry.
  -->
  <xsl:function name="p:goto" as="xs:integer">
    <xsl:param name="nonterminal" as="xs:integer"/>
    <xsl:param name="state" as="xs:integer"/>

    <xsl:variable name="i0" select="24 * $state + $nonterminal"/>
    <xsl:variable name="i1" select="$i0 idiv 4"/>
    <xsl:variable name="i2" select="$i1 idiv 4"/>
    <xsl:sequence select="$p:GOTO[$i0 mod 4 + $p:GOTO[$i1 mod 4 + $p:GOTO[$i2 + 1] + 1] + 1]"/>
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
        <xsl:variable name="i0" select=". * 70 + $state - 1"/>
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
   ! Compare a lookback code to a sorted, zero-terminated list of pairs at
   ! the given index into the LOOKBACK table. A matching first code in a
   ! pair will cause its second code to be returned. The list is sorted in
   ! descending order of first codes, so it is safe to stop when the first
   ! code is less than what is searched for.
   !
   ! @param $x the lookback code to search for.
   ! @param $i the index into the LOOKBACK table.
   ! @return the new lookback code as the second code from a pair with a
   ! matching first code.
  -->
  <xsl:function name="p:lookback">
    <xsl:param name="x" as="xs:integer"/>
    <xsl:param name="i" as="xs:integer"/>

    <xsl:variable name="l" select="$p:LOOKBACK[$i + 1]"/>
    <xsl:sequence select="
      if ($l gt $x) then
        p:lookback($x, $i + 2)
      else if ($l eq $x) then
        $p:LOOKBACK[$i + 2]
      else
        0
    "/>
  </xsl:function>

  <!--~
   ! Calculate number of symbols to remove from LR stack for reduction by
   ! walking through lookback codes of reduction and stack entries. A single
   ! invocation combines two of those, more are handled in tail recursion.
   !
   ! @param $code the reduction lookback code.
   ! @param $count the initial count value.
   ! @param $stack the LR stack.
   ! @param $t the stack running index.
   ! @return the initial count value, increased by the number of calculations
   ! yielding a non-zero lookback code.
  -->
  <xsl:function name="p:count">
    <xsl:param name="code" as="xs:integer"/>
    <xsl:param name="count" as="xs:integer"/>
    <xsl:param name="stack" as="xs:integer*"/>
    <xsl:param name="t" as="xs:integer"/>

    <xsl:choose>
      <xsl:when test="$stack[$t] lt 0">
        <xsl:sequence select="$count"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="code" select="p:lookback($stack[$t + 1], $p:LOOKBACK[$code + 1])"/>
        <xsl:sequence select="
          if ($code eq 0) then
            $count
          else
            p:count($code, $count + 1, $stack, $t - 3)
        "/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Parse input for given target symbol using LR tables. Each invocation
   ! handles one parsing action (shift, reduce, shift+reduce, accept).
   ! Subsequent actions are handled by tail-recursion.
   !
   ! @param $input the input string.
   ! @param $target the target symbol code.
   ! @param $state the LR parser state number.
   ! @param $action the action code.
   ! @param $nonterminal current nonterminal, -1 if processing a terminal.
   ! @param $bw the whitespace begin input index.
   ! @param $bs the symbol begin input index.
   ! @param $es the symbol end input index.
   ! @param $stack the LR stack.
   ! @param $lexer-state lexer state, error indicator, and result stack.
   ! @return the updated state.
  -->
  <xsl:function name="p:parse">
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="target" as="xs:integer"/>
    <xsl:param name="state" as="xs:integer"/>
    <xsl:param name="action" as="xs:integer"/>
    <xsl:param name="nonterminal" as="xs:integer"/>
    <xsl:param name="bw" as="xs:integer"/>
    <xsl:param name="bs" as="xs:integer"/>
    <xsl:param name="es" as="xs:integer"/>
    <xsl:param name="stack" as="xs:integer*"/>
    <xsl:param name="lexer-state" as="item()+"/>

    <xsl:choose>
      <xsl:when test="$lexer-state[$p:error]">
        <xsl:sequence select="$lexer-state"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="argument" select="$action idiv 1024"/>
        <xsl:variable name="lookback" select="($action idiv 8) mod 128"/>
        <xsl:variable name="action" select="$action mod 8"/>
        <xsl:choose>
          <xsl:when test="$action eq 6"> <!-- SHIFT+ACCEPT -->
            <xsl:sequence select="$lexer-state"/>
          </xsl:when>
          <xsl:otherwise>
            <xsl:variable name="shift-reduce-symbols" select="
              if ($action eq 1) then (: SHIFT :)
                ($argument, -1, -1)
              else if ($action eq 2) then (: REDUCE :)
                (-1, $argument, $lookback)
              else if ($action eq 3) then (: REDUCE+LOOKBACK :)
                (-1, $argument, p:count($lookback, 0, $stack, count($stack) - 1))
              else if ($action eq 4) then (: SHIFT+REDUCE :)
                ($state, $argument, $lookback + 1)
              else if ($action eq 5) then (: SHIFT+REDUCE+LOOKBACK :)
                ($state, $argument, p:count($lookback, 1, $stack, count($stack) - 1))
              else (: ERROR :)
                (-1, -1, -1)
            "/>
            <xsl:variable name="shift" select="$shift-reduce-symbols[1]"/>
            <xsl:variable name="reduce" select="$shift-reduce-symbols[2]"/>
            <xsl:variable name="symbols" select="$shift-reduce-symbols[3]"/>
            <xsl:variable name="es" select="if ($shift lt 0 or $nonterminal ge 0) then $es else $lexer-state[$p:e1]"/>
            <xsl:variable name="lexer-state" select="
              if ($shift lt 0 or $nonterminal ge 0) then
                $lexer-state
              else
                p:consume
                (
                  $lexer-state[$p:l1],
                  $input,
                  $lexer-state
                )
            "/>
            <xsl:variable name="stack" select="
              if ($shift lt 0) then
                $stack
              else
                ($stack, if ($nonterminal lt 0) then $lexer-state[$p:b0] else $bs, $state, $lookback)
            "/>
            <xsl:variable name="state" select="if ($shift lt 0) then $state else $shift"/>
            <xsl:choose>
              <xsl:when test="$reduce lt 0">
                <xsl:choose>
                  <xsl:when test="$shift lt 0">
                    <xsl:variable name="node">
                      <xsl:element name="error">
                        <xsl:attribute name="b" select="$lexer-state[$p:b1]"/>
                        <xsl:attribute name="e" select="$lexer-state[$p:e1]"/>
                        <xsl:if test="$lexer-state[$p:l1] gt 0">
                          <xsl:attribute name="o" select="$lexer-state[$p:l1]"/>
                        </xsl:if>
                        <xsl:attribute name="s" select="$p:TOKENSET[$state + 1] + 1"/>
                      </xsl:element>
                    </xsl:variable>
                    <xsl:sequence select="
                      subsequence($lexer-state, 1, $p:error - 1),
                      $node/node(),
                      subsequence($lexer-state, $p:error + 1)
                    "/>
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:variable name="lexer-state" select="p:predict($input, $lexer-state, $state)"/>
                    <xsl:sequence select="p:parse($input, $target, $state, $lexer-state[$p:lk], -1, $bw, $bs, $es, $stack, $lexer-state)"/>
                  </xsl:otherwise>
                </xsl:choose>
              </xsl:when>
              <xsl:otherwise>
                <xsl:variable name="state" select="if ($symbols gt 0) then $stack[last() - 3 * $symbols + 2] else $state"/>
                <xsl:variable name="bs" select="if ($symbols gt 0) then $stack[last() - 3 * $symbols + 1] else $lexer-state[$p:b1]"/>
                <xsl:variable name="es" select="if ($symbols gt 0) then $es else $bs"/>
                <xsl:variable name="stack" select="if ($symbols gt 0) then subsequence($stack, 1, count($stack) - 3 * $symbols) else $stack"/>
                <xsl:variable name="lexer-state" select="if ($symbols gt 0) then $lexer-state else p:whitespace($input, $lexer-state)"/>
                <xsl:variable name="accept" select="$reduce eq $target and count($stack) eq 3"/>
                <xsl:variable name="bs" select="if ($accept) then $bw else $bs"/>
                <xsl:variable name="es" select="if ($accept) then $lexer-state[$p:b1] else $es"/>
                <xsl:variable name="bw" select="if ($accept) then $es else $bw"/>
                <xsl:variable name="index" select="if ($accept) then $p:result else p:first-child-node-index($lexer-state, count($lexer-state) + 1, $symbols)"/>
                <xsl:variable name="node">
                  <xsl:element name="{$p:NONTERMINAL[$reduce + 1]}">
                    <xsl:sequence select="subsequence($lexer-state, $index)"/>
                  </xsl:element>
                </xsl:variable>
                <xsl:variable name="lexer-state" select="subsequence($lexer-state, 1, $index - 1), $node/node()"/>
                <xsl:variable name="nonterminal" select="$reduce"/>
                <xsl:sequence select="p:parse($input, $target, $state, p:goto($nonterminal, $state), $nonterminal, $bw, $bs, $es, $stack, $lexer-state)"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:function>

  <!--~
   ! Decrement given index by the given number of elements on the result
   ! stack, skipping any non-element nodes.
   !
   ! @param $state lexer state, error indicator, and result stack.
   ! @param $index the index into the result stack.
   ! @param $element-count the number of elements to be handled.
   ! @return the decremented index.
  -->
  <xsl:function name="p:first-child-node-index">
    <xsl:param name="state" as="item()+"/>
    <xsl:param name="index" as="xs:integer"/>
    <xsl:param name="element-count" as="xs:integer"/>

    <xsl:choose>
      <xsl:when test="$element-count eq 0">
        <xsl:sequence select="$index"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:variable name="index" select="$index - 1"/>
        <xsl:variable name="element-count" select="$element-count - (if ($state[$index] instance of element()) then 1 else 0)"/>
        <xsl:sequence select="p:first-child-node-index($state, $index, $element-count)"/>
      </xsl:otherwise>
    </xsl:choose>
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
          subsequence($state, $p:l1, 12),
          0, 0, 0,
          subsequence($state, 16),
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
      <xsl:when test="$match[1] = 7">                                               <!-- ws -->
        <xsl:sequence select="p:matchW($input, $match[3], $token-set)"/>
      </xsl:when>
      <xsl:when test="$match[1] = 11">                                              <!-- '(*' -->
        <xsl:variable name="state" select="0, $begin, $begin, $match, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false()"/>
        <xsl:variable name="state" select="p:predict($input, $state, 5)"/>
        <xsl:variable name="e0" select="$state[$p:e0]"/>
        <xsl:variable name="state" select="p:parse($input, -1, 5, $state[$p:lk], -1, $e0, $e0, $e0, (1, -1, 0), $state)"/>
        <xsl:sequence select="p:matchW($input, $state[$p:e0], $token-set)"/>
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
        subsequence($state, $p:l3, 6)
      else
      (
        p:matchW($input, $state[$p:e2], $set),
        0, 0, 0
      )
    "/>
    <xsl:sequence select="
      $match[1] * 4096 + $state[$p:lk],
      subsequence($state, $p:b0, 8),
      $match,
      subsequence($state, 16)
    "/>
  </xsl:function>

  <!--~
   ! Lookahead one token on level 4 with whitespace skipping.
   !
   ! @param $set the code of the DFA entry state for the set of valid tokens.
   ! @param $input the input string.
   ! @param $state lexer state, error indicator, and result stack.
   ! @return the updated state.
  -->
  <xsl:function name="p:lookahead4W" as="item()+">
    <xsl:param name="set" as="xs:integer"/>
    <xsl:param name="input" as="xs:string"/>
    <xsl:param name="state" as="item()+"/>

    <xsl:variable name="match" select="
      if ($state[$p:l4] ne 0) then
        subsequence($state, $p:l4, 3)
      else
        p:matchW($input, $state[$p:e3], $set)
    "/>
    <xsl:sequence select="
      $match[1] * 262144 + $state[$p:lk],
      subsequence($state, $p:b0, 11),
      $match,
      subsequence($state, 16)
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
   ! Parse start symbol rules from given string.
   !
   ! @param $s the string to be parsed.
   ! @return the result as generated by parser actions.
  -->
  <xsl:function name="p:parse-rules" as="item()*">
    <xsl:param name="s" as="xs:string"/>

    <xsl:variable name="state" select="0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false()"/>
    <xsl:variable name="state" select="p:predict($s, $state, 4)"/>
    <xsl:variable name="state" select="p:parse($s, 0, 4, $state[$p:lk], -1, 1, 1, 1, (1, -1, 0), $state)"/>
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