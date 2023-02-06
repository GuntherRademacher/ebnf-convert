xquery version "1.0" encoding "UTF-8";

(: This file was generated on Wed Dec 21, 2022 23:04 (UTC+01) by REx v5.56 which is Copyright (c) 1979-2022 by Gunther Rademacher <grd@gmx.net> :)
(: REx command line: -lalr 4 -tree -a none -xquery -name de/bottlecaps/railroad/convert/xq/instaparse/instaparse.xquery ..\..\..\..\..\..\..\..\main\java\de\bottlecaps\railroad\convert\instaparse\instaparse.ebnf :)

(:~
 : The parser that was generated for the de/bottlecaps/railroad/convert/xq/instaparse/instaparse.xquery grammar.
 :)
module namespace p="de/bottlecaps/railroad/convert/xq/instaparse/instaparse.xquery";
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
 : The index of the lexer state for accessing the code of the
 : level-4-lookahead token.
 :)
declare variable $p:l4 as xs:integer := 13;

(:~
 : The index of the lexer state for accessing the position in the
 : input string of the begin of the level-4-lookahead token.
 :)
declare variable $p:b4 as xs:integer := 14;

(:~
 : The index of the lexer state for accessing the position in the
 : input string of the end of the level-4-lookahead token.
 :)
declare variable $p:e4 as xs:integer := 15;

(:~
 : The index of the lexer state for accessing the token code that
 : was expected when an error was found.
 :)
declare variable $p:error as xs:integer := 16;

(:~
 : The index of the lexer state that points to the first entry
 : used for collecting action results.
 :)
declare variable $p:result as xs:integer := 17;

(:~
 : The codepoint to charclass mapping for 7 bit codepoints.
 :)
declare variable $p:MAP0 as xs:integer+ :=
(
  41, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 5, 6,
  7, 8, 9, 10, 11, 1, 5, 12, 13, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 14, 15, 16, 17, 18, 19, 5, 5, 5, 5, 5, 20, 5, 5, 5, 21,
  5, 5, 22, 5, 23, 24, 25, 5, 5, 26, 5, 5, 5, 5, 5, 5, 5, 27, 28, 29, 5, 5, 5, 5, 5, 5, 5, 30, 5, 5, 5, 31, 5, 5, 32, 5,
  33, 34, 35, 5, 5, 36, 5, 5, 5, 5, 5, 5, 5, 37, 38, 39, 5, 5
);

(:~
 : The codepoint to charclass mapping for codepoints below the surrogate block.
 :)
declare variable $p:MAP1 as xs:integer+ :=
(
  27, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 74, 138,
  200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 211, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200,
  200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 41, 0,
  0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 5, 6, 7, 8,
  9, 10, 11, 1, 5, 12, 13, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 14, 15, 16, 17, 18, 19, 5, 5, 5, 5, 5, 20, 5, 5, 5, 21, 5, 5,
  22, 5, 23, 24, 25, 5, 5, 26, 5, 5, 5, 5, 5, 5, 5, 27, 28, 29, 5, 5, 5, 5, 5, 5, 5, 30, 5, 5, 5, 31, 5, 5, 32, 5, 33,
  34, 35, 5, 5, 36, 5, 5, 5, 5, 5, 5, 5, 37, 38, 39, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
  5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
  5, 5, 5, 40, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5
);

(:~
 : The codepoint to charclass mapping for codepoints above the surrogate block.
 :)
declare variable $p:MAP2 as xs:integer+ :=
(
  57344, 65536, 65533, 1114111, 5, 5
);

(:~
 : The token-set-id to DFA-initial-state mapping.
 :)
declare variable $p:INITIAL as xs:integer+ :=
(
  1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
);

(:~
 : The DFA transition table.
 :)
declare variable $p:TRANSITION as xs:integer+ :=
(
  690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 1060, 1064, 1066, 672, 978, 879, 1239,
  1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 690, 682, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690,
  690, 690, 689, 674, 734, 701, 699, 709, 722, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 714, 742, 691, 978,
  879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1116, 762, 690, 690,
  690, 690, 690, 690, 690, 689, 690, 783, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 974,
  804, 691, 1192, 824, 1188, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 844, 847, 837, 691, 978, 879, 1239, 1355,
  690, 690, 690, 690, 690, 690, 690, 690, 811, 954, 816, 855, 892, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690,
  690, 912, 690, 905, 924, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 690, 938, 691, 978, 879,
  1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 1225, 962, 691, 978, 879, 1239, 1355, 690, 690, 690, 690,
  690, 690, 690, 690, 689, 1249, 988, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 950, 829,
  1359, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 1020, 1008, 691, 978, 879, 1239, 1355, 690,
  690, 690, 690, 690, 690, 690, 690, 689, 1028, 1032, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690,
  689, 1245, 897, 916, 978, 1040, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 790, 884, 796, 691, 978, 879,
  1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 690, 1053, 691, 978, 879, 1239, 1355, 690, 690, 690, 690,
  690, 690, 690, 690, 995, 1074, 1081, 728, 770, 861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758,
  1120, 728, 770, 861, 1089, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1104,
  762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1116, 867, 690, 690, 690, 690, 690, 690,
  690, 969, 758, 1120, 728, 770, 861, 1110, 1160, 1128, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 873, 1154,
  861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 1168, 1140, 1110, 1116, 762, 690, 690,
  690, 690, 690, 690, 690, 689, 1045, 1202, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 690,
  1212, 980, 1210, 1220, 1233, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 749, 750, 1257, 691, 978, 879, 1239, 1355,
  690, 690, 690, 690, 690, 690, 690, 690, 1015, 1265, 1272, 728, 770, 861, 1110, 1116, 762, 690, 690, 690, 690, 690,
  690, 690, 969, 758, 1120, 728, 770, 861, 1096, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770,
  861, 1110, 1175, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1116, 775, 690, 690,
  690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1146, 1182, 690, 690, 690, 690, 690, 690, 690, 969, 758,
  1120, 1134, 1280, 861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 1288, 930, 1110, 1116,
  762, 690, 690, 690, 690, 690, 690, 690, 689, 1000, 1296, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690,
  690, 689, 1194, 1304, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 1312, 1319, 691, 978,
  879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 945, 1327, 1334, 728, 770, 861, 1110, 1116, 762, 690, 690,
  690, 690, 690, 690, 690, 690, 1342, 1349, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 0, 1050, 0,
  0, 0, 0, 0, 32, 0, 0, 0, 1152, 1152, 1152, 1152, 1152, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 32, 43, 34, 0, 0, 0, 0, 0, 0, 0,
  512, 0, 563, 768, 44, 34, 0, 0, 0, 0, 0, 33, 0, 0, 0, 0, 512, 43, 44, 54, 0, 0, 0, 4124, 4124, 4124, 0, 32, 32, 32,
  32, 32, 1, 1, 0, 33, 33, 33, 33, 33, 1, 1, 0, 0, 0, 0, 0, 0, 3712, 3712, 0, 4124, 4124, 0, 4124, 4124, 4124, 4124,
  4124, 4124, 0, 0, 0, 34, 4124, 4124, 0, 4124, 4124, 4124, 28, 4124, 284, 0, 0, 0, 1280, 1280, 1280, 1280, 1280, 1, 1,
  0, 0, 0, 0, 0, 3456, 0, 3456, 3456, 3456, 3456, 1, 1, 0, 34, 34, 34, 34, 34, 1, 1, 0, 0, 0, 0, 2560, 0, 0, 2560, 2560,
  2560, 1, 17280, 0, 32, 43, 640, 438, 0, 0, 0, 0, 0, 1695, 1, 1, 27, 1435, 1435, 1435, 1435, 1435, 23, 23, 0, 27, 27,
  27, 27, 27, 27, 1435, 27, 27, 2816, 0, 0, 0, 0, 0, 0, 32, 43, 44, 34, 4124, 4124, 4124, 28, 4124, 284, 4124, 0, 0, 0,
  4124, 4134, 4124, 0, 32, 43, 44, 34, 0, 0, 0, 0, 0, 0, 3456, 3456, 0, 34, 0, 0, 17280, 0, 0, 0, 0, 0, 2048, 1, 1, 0,
  0, 2688, 0, 2688, 2688, 17317, 24, 25, 0, 25, 0, 0, 0, 0, 0, 0, 1920, 32, 0, 0, 1536, 0, 0, 0, 0, 32, 43, 44, 34,
  4124, 4152, 312, 0, 0, 2944, 0, 2944, 2944, 1, 1, 0, 0, 0, 28, 0, 0, 0, 1695, 0, 0, 0, 0, 0, 0, 2560, 2560, 3072, 0,
  0, 3072, 3072, 3072, 1, 1, 0, 0, 0, 4124, 0, 0, 0, 0, 0, 34, 0, 0, 0, 0, 0, 0, 0, 42, 3200, 0, 3200, 3200, 3200, 3200,
  1, 1, 0, 0, 0, 4125, 0, 0, 0, 0, 0, 2304, 0, 0, 3328, 0, 0, 3328, 3328, 3328, 1, 1, 0, 0, 0, 4126, 0, 0, 0, 0, 3328,
  0, 3328, 3328, 0, 4224, 4224, 0, 4224, 4224, 4224, 4224, 4224, 4224, 1, 1, 1792, 32, 43, 44, 34, 0, 0, 0, 0, 0, 2176,
  0, 0, 0, 0, 3584, 0, 3584, 3584, 1, 1, 0, 1050, 0, 1050, 1050, 1050, 1050, 1050, 1050, 1050, 1050, 1, 1, 0, 4125,
  4125, 0, 4125, 4131, 4125, 4125, 4131, 4131, 4131, 4131, 4131, 1, 1, 4153, 4124, 51, 43, 44, 54, 4157, 4124, 4154, 51,
  43, 44, 54, 4124, 4158, 4159, 4124, 51, 54, 4161, 4124, 4124, 4124, 51, 43, 44, 54, 4124, 4124, 51, 54, 4124, 4124,
  4124, 4124, 4124, 4124, 1, 1, 4165, 4124, 4124, 4124, 4124, 4124, 0, 0, 0, 4124, 4135, 4136, 0, 32, 43, 44, 34, 4151,
  4124, 4124, 51, 54, 4124, 4124, 4124, 4164, 0, 34, 4142, 4124, 0, 4124, 4124, 4124, 51, 54, 4124, 4124, 4163, 4124, 0,
  34, 4124, 4124, 0, 4145, 4124, 4124, 4160, 51, 54, 4124, 4162, 4124, 4124, 4166, 4124, 4124, 4124, 4124, 0, 0, 51, 43,
  44, 384, 0, 0, 0, 0, 0, 0, 0, 3840, 0, 2176, 2176, 2176, 2176, 2176, 1, 1, 0, 45, 0, 0, 0, 0, 0, 0, 1, 1, 0, 42, 52,
  53, 45, 0, 0, 0, 0, 3072, 0, 3072, 3072, 0, 0, 59, 43, 44, 60, 0, 0, 51, 43, 44, 54, 0, 0, 0, 2048, 0, 0, 0, 0, 0, 0,
  3200, 0, 3712, 0, 0, 3712, 3712, 3712, 1, 1, 0, 4126, 4126, 0, 4126, 4132, 4126, 4126, 4132, 4132, 4132, 4132, 4132,
  1, 1, 0, 34, 4143, 4144, 0, 4124, 4124, 4124, 0, 34, 4124, 4124, 0, 4124, 4146, 50, 0, 2304, 2304, 2304, 2304, 2304,
  1, 1, 3840, 0, 3840, 3840, 3840, 3840, 1, 1, 3968, 0, 0, 0, 0, 0, 3968, 3968, 0, 0, 3968, 3968, 3968, 1, 1, 0, 28, 28,
  0, 28, 284, 28, 28, 284, 284, 284, 284, 284, 1, 1, 0, 0, 2432, 0, 2432, 0, 2432, 2432, 0, 0, 2432, 2432, 2432, 0, 0,
  51, 54, 0, 0, 0, 0, 0, 0, 41, 32
);

(:~
 : The DFA-state to expected-token-set mapping.
 :)
declare variable $p:EXPECTED as xs:integer+ :=
(
  3, 18, 31, 34, 38, 42, 46, 50, 54, 63, 66, 70, 57, 75, 71, 82, 78, 59, 80, 86, 88, 89, 93, 95, 95, 88, 88, 88, 88, 88,
  88, 88, 88, 88, 64, 2097152, 2176, 2099200, -2147481472, 526464, 67111040, 268437632, 1073744000, -2147481472,
  -2147219328, 63616, -2105276288, -2147283778, -678688640, -158594944, -141817728, -2147283010, -1387065410,
  -141619266, -2158658, -2097218, 64, 64, -2147483648, -2147483648, -2147483648, 8, 4, 2097152, 128, 2048, -2147483648,
  -2147483648, 24576, 8, 48, 4, -2147483646, -2147483646, -2147483646, 8192, 8, 32, 16, 4, -2147483646, -2147483646,
  -2147483648, -2147483648, 8, 32, -2147483646, -2147483646, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1
);

(:~
 : The lookahead enlargement map. Maps lookahead and token to new lookahead code.
 :)
declare variable $p:LOOKAHEAD as xs:integer+ :=
(
  43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 44, 42, 52, 63, 55, 43, 43, 43, 43, 43, 43, 43, 43, 43,
  43, 43, 43, 43, 68, 43, 43, 43, 43, 43, 73, 43, 63, 84, 108, 108, 108, 108, 108, 108, 108, 108, 109, 108, 103, 94,
  108, 108, 108, 108, 108, 97, 91, 108, 108, 108, 108, 87, 81, 108, 108, 108, 108, 101, 108, 108, 108, 108, 108, 108,
  107, 108, 0, 0, 41, 0, 0, 39, 0, 0, 0, 37, 0, 0, 42, 0, 0, 40, 0, 0, 0, 38, 0, 34, 0, 0, 0, 36, 33, 0, 0, 0, 0, 35
);

(:~
 : The match-code to case-id map. Maps decision point and lookahead to next action code.
 :)
declare variable $p:CASEID as xs:integer+ :=
(
  99, 108, 112, 122, 161, 121, 99, 127, 112, 122, 161, 121, 122, 161, 131, 147, 122, 122, 122, 136, 131, 132, 122, 122,
  122, 161, 122, 115, 122, 122, 99, 140, 121, 104, 150, 122, 99, 140, 121, 99, 140, 121, 99, 140, 121, 99, 140, 121,
  144, 140, 121, 144, 140, 121, 99, 140, 121, 99, 140, 121, 122, 152, 122, 122, 102, 122, 122, 156, 160, 122, 165, 169,
  177, 122, 122, 122, 117, 122, 122, 174, 122, 122, 170, 122, 122, 123, 122, 181, 185, 189, 193, 197, 201, 122, 205,
  169, 122, 209, 213, 217, 221, 227, 260, 239, 260, 260, 317, 260, 245, 223, 255, 247, 251, 254, 255, 260, 241, 260,
  260, 230, 260, 259, 260, 260, 260, 260, 236, 245, 265, 268, 247, 272, 260, 260, 260, 278, 284, 260, 260, 308, 282,
  260, 260, 308, 217, 221, 233, 260, 261, 317, 260, 288, 260, 260, 290, 260, 274, 301, 294, 273, 300, 260, 260, 260,
  308, 326, 325, 305, 312, 307, 260, 260, 260, 316, 359, 260, 260, 260, 296, 260, 260, 321, 323, 322, 260, 334, 331,
  335, 334, 324, 260, 260, 260, 339, 341, 340, 260, 351, 348, 352, 351, 342, 260, 260, 260, 326, 325, 327, 312, 344,
  343, 356, 363, 358, 260, 260, 260, 0, 43016, 28680, 28680, 30728, 30728, 0, 0, 0, 27094, 34834, 32786, 26642, 0, 0,
  24616, 0, 0, 26642, 0, 0, 34856, 0, 2088, 0, 0, 22658, 0, 28690, 30738, 27094, 27094, 27094, 43, 35, 37, 43, 27094,
  27094, 27094, 27094, 0, 24594, 0, 0, 0, 0, 6152, 34840, 0, 36888, 27094, 27094, 27094, 32792, 16402, 0, 0, 0, 10292,
  0, 8200, 8200, 8200, 8200, 28690, 30738, 0, 0, 90, 0, 0, 4262, 0, 0, 14376, 0, 0, 10296, 0, 0, 22738, 0, 10292, 0, 0,
  0, 10296, 39314, 18726, 18726, 0, 0, 0, 12296, 18726, 37170, 18726, 18726, 32808, 0, 0, 0, 18450, 0, 38948, 38948,
  38948, 0, 0, 0, 18726, 18726, 0, 34840, 0, 36888, 38948, 38948, 38948, 38948, 32792, 0, 40996, 40996, 40996, 0, 0, 0,
  20870, 20870, 34840, 0, 36888, 40996, 40996, 40996, 40996, 32792, 39314, 20870, 20870, 0, 0, 0, 22568, 20870, 0,
  20870, 20870
);

(:~
 : The parser tokenset table. Maps state to lookahead tokenset code.
 :)
declare variable $p:TOKENSET as xs:integer+ :=
(
  19, 0, 20, 0, 9, 2, 10, 11, 4, 0, 17, 3, 17, 17, 17, 17, 13, 13, 17, 17, 6, 1, 12, 16, 0, 6, 5, 7, 8, 20, 20, 15, 14
);

(:~
 : The parser lookback table. Maps lookback code and itemset id to next lookback code.
 :)
declare variable $p:LOOKBACK as xs:integer+ :=
(
  47, 47, 45, 45, 45, 48, 51, 47, 47, 47, 54, 59, 64, 64, 64, 47, 47, 47, 67, 72, 77, 77, 77, 47, 88, 80, 85, 85, 85,
  91, 98, 98, 98, 98, 98, 105, 105, 105, 105, 105, 112, 112, 112, 112, 112, 3, 2, 0, 3, 4, 0, 5, 5, 0, 13, 12, 8, 7, 0,
  13, 14, 8, 9, 0, 11, 11, 0, 21, 20, 16, 15, 0, 21, 22, 16, 17, 0, 19, 19, 0, 27, 28, 16, 23, 0, 25, 25, 0, 27, 26, 0,
  41, 40, 36, 35, 31, 30, 0, 41, 42, 36, 37, 31, 32, 0, 41, 43, 36, 38, 31, 33, 0, 41, 44, 36, 39, 31, 34, 0
);

(:~
 : The parser goto table. Maps state and nonterminal to next action code.
 :)
declare variable $p:GOTO as xs:integer+ :=
(
  70, 55, 57, 70, 55, 57, 30, 38, 57, 36, 58, 57, 42, 32, 57, 45, 84, 57, 49, 68, 53, 62, 68, 66, 70, 76, 72, 78, 68,
  82, 94, 146, 95, 95, 103, 95, 95, 146, 95, 95, 156, 95, 95, 90, 95, 95, 97, 107, 119, 95, 97, 116, 119, 125, 119, 121,
  88, 95, 95, 95, 95, 101, 95, 97, 135, 119, 139, 119, 121, 88, 95, 97, 96, 110, 112, 150, 112, 143, 95, 97, 96, 128,
  96, 131, 121, 88, 153, 95, 329, 2297, 0, 0, 20489, 0, 6, 0, 0, 0, 0, 21508, 0, 10249, 0, 0, 0, 21513, 0, 22537, 8196,
  8196, 21508, 0, 21508, 21508, 21508, 0, 25609, 8196, 8196, 21508, 23681, 21508, 21508, 21508, 289, 26633, 8196, 8196,
  21508, 31913, 21508, 21508, 32985, 21508, 21508, 27657, 8196, 8196, 21508, 28681, 8196, 8196, 21508, 0, 29705, 0, 0,
  6169, 7177, 7177, 0, 30729, 0, 0, 24665, 0, 0, 3076, 0, 3078
);

(:~
 : The token-string table.
 :)
declare variable $p:TOKEN as xs:string+ :=
(
  "(0)",
  "epsilon",
  "single-quoted-string",
  "double-quoted-string",
  "single-quoted-regexp",
  "double-quoted-regexp",
  "comment-content",
  "ws",
  "'!'",
  "'&amp;'",
  "'('",
  "'(*'",
  "':'",
  "'::='",
  "':='",
  "'='",
  "'['",
  "'{'",
  "EOF",
  "')'",
  "'*'",
  "'*)'",
  "'+'",
  "'.'",
  "'/'",
  "';'",
  "'>'",
  "'?'",
  "']'",
  "'|'",
  "'}'",
  "non-epsilon",
  "'<'"
);

(:~
 : The nonterminal name table.
 :)
declare variable $p:NONTERMINAL as xs:string+ :=
(
  "rules",
  "comment",
  "inside-comment",
  "opt-whitespace",
  "rule-separator",
  "rule",
  "nt",
  "hide-nt",
  "alt-or-ord",
  "alt",
  "ord",
  "paren",
  "hide",
  "cat",
  "string",
  "regexp",
  "opt",
  "star",
  "plus",
  "look",
  "neg",
  "factor"
);

(:~
 : Predict the decision for a given decision point based on current
 : lookahead.
 :
 : @param $input the input string.
 : @param $state the parser state.
 : @param $dpi the decision point index.
 : @return the updated parser state.
 :)
declare function p:predict($input as xs:string,
                           $state as item()+,
                           $dpi as xs:integer) as item()+
{
  let $state := p:lookahead1W($p:TOKENSET[$dpi + 1], $input, $state)
  return
    if ($state[$p:l1] lt 0) then
    (
      0,
      subsequence($state, $p:lk + 1, $p:error - $p:lk - 1),
      element error
      {
        attribute b {$state[$p:b1]},
        attribute e {$state[$p:e1]},
        attribute s {- $state[$p:l1]}
      },
      subsequence($state, $p:error + 1)
    )
    else
      let $j10 := 48 * $dpi + $state[$p:l1]
      let $j11 := $j10 idiv 4
      let $j12 := $j11 idiv 4
      let $action := $p:CASEID[$j10 mod 4 + $p:CASEID[$j11 mod 4 + $p:CASEID[$j12 + 1] + 1] + 1]
      return
        if ($action mod 2 eq 0) then
          ($action idiv 2, subsequence($state, $p:lk + 1))
        else
          let $state := p:lookahead2W($action idiv 2, $input, $state)
          return
            if ($state[$p:l2] lt 0) then
              (0, subsequence($state, $p:lk + 1))
            else
              let $i20 := 40 * $state[$p:l2] + $state[$p:l1]
              let $i21 := $i20 idiv 4
              let $i22 := $i21 idiv 8
              let $lk := $p:LOOKAHEAD[$i20 mod 4 + $p:LOOKAHEAD[$i21 mod 8 + $p:LOOKAHEAD[$i22 + 1] + 1] + 1]
              return
                if ($lk eq 0) then
                  p:predict($input, $state, $dpi + 1)
                else
                  let $j20 := 48 * $dpi + $lk
                  let $j21 := $j20 idiv 4
                  let $j22 := $j21 idiv 4
                  let $action := $p:CASEID[$j20 mod 4 + $p:CASEID[$j21 mod 4 + $p:CASEID[$j22 + 1] + 1] + 1]
                  return
                    if ($action mod 2 eq 0) then
                      ($action idiv 2, subsequence($state, $p:lk + 1))
                    else
                      let $state := p:lookahead3W($action idiv 2, $input, $state)
                      return
                        if ($state[$p:l3] lt 0) then
                          (0, subsequence($state, $p:lk + 1))
                        else
                          let $i30 := 40 * $state[$p:l3] + $lk
                          let $i31 := $i30 idiv 4
                          let $i32 := $i31 idiv 8
                          let $lk := $p:LOOKAHEAD[$i30 mod 4 + $p:LOOKAHEAD[$i31 mod 8 + $p:LOOKAHEAD[$i32 + 1] + 1] + 1]
                          return
                            if ($lk eq 0) then
                              p:predict($input, $state, $dpi + 1)
                            else
                              let $j30 := 48 * $dpi + $lk
                              let $j31 := $j30 idiv 4
                              let $j32 := $j31 idiv 4
                              let $action := $p:CASEID[$j30 mod 4 + $p:CASEID[$j31 mod 4 + $p:CASEID[$j32 + 1] + 1] + 1]
                              return
                                if ($action mod 2 eq 0) then
                                  ($action idiv 2, subsequence($state, $p:lk + 1))
                                else
                                  let $state := p:lookahead4W($action idiv 2, $input, $state)
                                  return
                                    if ($state[$p:l4] lt 0) then
                                      (0, subsequence($state, $p:lk + 1))
                                    else
                                      let $i40 := 40 * $state[$p:l4] + $lk
                                      let $i41 := $i40 idiv 4
                                      let $i42 := $i41 idiv 8
                                      let $lk := $p:LOOKAHEAD[$i40 mod 4 + $p:LOOKAHEAD[$i41 mod 8 + $p:LOOKAHEAD[$i42 + 1] + 1] + 1]
                                      return
                                        if ($lk eq 0) then
                                          p:predict($input, $state, $dpi + 1)
                                        else
                                          let $j40 := 48 * $dpi + $lk
                                          let $j41 := $j40 idiv 4
                                          let $j42 := $j41 idiv 4
                                          let $action := $p:CASEID[$j40 mod 4 + $p:CASEID[$j41 mod 4 + $p:CASEID[$j42 + 1] + 1] + 1]
                                          return
                                            if ($action ne 0) then
                                              ($action idiv 2, subsequence($state, $p:lk + 1))
                                            else
                                              p:predict($input, $state, $dpi + 1)
};

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
    let $end := $end - $result idiv 64
    let $end := if ($end gt string-length($input)) then string-length($input) + 1 else $end
    return
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
  else
    let $c0 := (string-to-codepoints(substring($input, $current, 1)), 0)[1]
    let $c1 :=
      if ($c0 < 128) then
        $p:MAP0[1 + $c0]
      else if ($c0 < 55296) then
        let $c1 := $c0 idiv 64
        let $c2 := $c1 idiv 32
        return $p:MAP1[1 + $c0 mod 64 + $p:MAP1[1 + $c1 mod 32 + $p:MAP1[1 + $c2]]]
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
 : Get GOTO table entry for given nonterminal and parser state.
 :
 : @param $nonterminal the nonterminal.
 : @param $state the LR parser state.
 : @return the GOTO table entry.
 :)
declare function p:goto($nonterminal as xs:integer, $state as xs:integer) as xs:integer
{
  let $i0 := 24 * $state + $nonterminal
  let $i1 := $i0 idiv 4
  let $i2 := $i1 idiv 4
  return $p:GOTO[$i0 mod 4 + $p:GOTO[$i1 mod 4 + $p:GOTO[$i2 + 1] + 1] + 1]
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
    let $i0 := $t * 70 + $state - 1
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
 : Compare a lookback code to a sorted, zero-terminated list of pairs at
 : the given index into the LOOKBACK table. A matching first code in a
 : pair will cause its second code to be returned. The list is sorted in
 : descending order of first codes, so it is safe to stop when the first
 : code is less than what is searched for.
 :
 : @param $x the lookback code to search for.
 : @param $i the index into the LOOKBACK table.
 : @return the new lookback code as the second code from a pair with a
 : matching first code.
 :)
declare function p:lookback($x as xs:integer, $i as xs:integer)
{
  let $l := $p:LOOKBACK[$i + 1]
  return
    if ($l gt $x) then
      p:lookback($x, $i + 2)
    else if ($l eq $x) then
      $p:LOOKBACK[$i + 2]
    else
      0
};

(:~
 : Calculate number of symbols to remove from LR stack for reduction by
 : walking through lookback codes of reduction and stack entries. A single
 : invocation combines two of those, more are handled in tail recursion.
 :
 : @param $code the reduction lookback code.
 : @param $count the initial count value.
 : @param $stack the LR stack.
 : @param $t the stack running index.
 : @return the initial count value, increased by the number of calculations
 : yielding a non-zero lookback code.
 :)
declare function p:count($code as xs:integer, $count as xs:integer, $stack as xs:integer*, $t as xs:integer)
{
  if ($stack[$t] lt 0) then
    $count
  else
    let $code := p:lookback($stack[$t + 1], $p:LOOKBACK[$code + 1])
    return
      if ($code eq 0) then
        $count
      else
        p:count($code, $count + 1, $stack, $t - 3)
};

(:~
 : Parse input for given target symbol using LR tables. Each invocation
 : handles one parsing action (shift, reduce, shift+reduce, accept).
 : Subsequent actions are handled by tail-recursion.
 :
 : @param $input the input string.
 : @param $target the target symbol code.
 : @param $state the LR parser state number.
 : @param $action the action code.
 : @param $nonterminal current nonterminal, -1 if processing a terminal.
 : @param $bw the whitespace begin input index.
 : @param $bs the symbol begin input index.
 : @param $es the symbol end input index.
 : @param $stack the LR stack.
 : @param $lexer-state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:parse($input as xs:string,
                         $target as xs:integer,
                         $state as xs:integer,
                         $action as xs:integer,
                         $nonterminal as xs:integer,
                         $bw as xs:integer,
                         $bs as xs:integer,
                         $es as xs:integer,
                         $stack as xs:integer*,
                         $lexer-state as item()+)
{
  if ($lexer-state[$p:error]) then
    $lexer-state
  else
    let $argument := $action idiv 1024
    let $lookback := ($action idiv 8) mod 128
    let $action := $action mod 8
    return
      if ($action eq 6) then (: SHIFT+ACCEPT :)
        $lexer-state
      else
        let $shift-reduce-symbols :=
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
        let $shift := $shift-reduce-symbols[1]
        let $reduce := $shift-reduce-symbols[2]
        let $symbols := $shift-reduce-symbols[3]
        let $es := if ($shift lt 0 or $nonterminal ge 0) then $es else $lexer-state[$p:e1]
        let $lexer-state :=
          if ($shift lt 0 or $nonterminal ge 0) then
            $lexer-state
          else
            p:consume
            (
              $lexer-state[$p:l1],
              $input,
              $lexer-state
            )
        let $stack :=
          if ($shift lt 0) then
            $stack
          else
            ($stack, if ($nonterminal lt 0) then $lexer-state[$p:b0] else $bs, $state, $lookback)
        let $state := if ($shift lt 0) then $state else $shift
        return
          if ($reduce lt 0) then
            if ($shift lt 0) then
            (
              subsequence($lexer-state, 1, $p:error - 1),
              element error
              {
                attribute b {$lexer-state[$p:b1]},
                attribute e {$lexer-state[$p:e1]},
                attribute o {$lexer-state[$p:l1]}[. > 0],
                attribute s {$p:TOKENSET[$state + 1] + 1}
              },
              subsequence($lexer-state, $p:error + 1)
            )
            else
              let $lexer-state := p:predict($input, $lexer-state, $state)
              return p:parse($input, $target, $state, $lexer-state[$p:lk], -1, $bw, $bs, $es, $stack, $lexer-state)
          else
            let $state := if ($symbols gt 0) then $stack[last() - 3 * $symbols + 2] else $state
            let $bs := if ($symbols gt 0) then $stack[last() - 3 * $symbols + 1] else $lexer-state[$p:b1]
            let $es := if ($symbols gt 0) then $es else $bs
            let $stack := if ($symbols gt 0) then subsequence($stack, 1, count($stack) - 3 * $symbols) else $stack
            let $lexer-state := if ($symbols gt 0) then $lexer-state else p:whitespace($input, $lexer-state)
            let $accept := $reduce eq $target and count($stack) eq 3
            let $bs := if ($accept) then $bw else $bs
            let $es := if ($accept) then $lexer-state[$p:b1] else $es
            let $bw := if ($accept) then $es else $bw
            let $index := if ($accept) then $p:result else p:first-child-node-index($lexer-state, count($lexer-state) + 1, $symbols)
            let $lexer-state :=
            (
              subsequence($lexer-state, 1, $index - 1),
              element {$p:NONTERMINAL[$reduce + 1]}
              {
                (: bs, es :)
                subsequence($lexer-state, $index)
              }
            )
            let $nonterminal := $reduce
            return p:parse($input, $target, $state, p:goto($nonterminal, $state), $nonterminal, $bw, $bs, $es, $stack, $lexer-state)
};

(:~
 : Decrement given index by the given number of elements on the result
 : stack, skipping any non-element nodes.
 :
 : @param $state lexer state, error indicator, and result stack.
 : @param $index the index into the result stack.
 : @param $element-count the number of elements to be handled.
 : @return the decremented index.
 :)
declare function p:first-child-node-index($state as item()+,
                                          $index as xs:integer,
                                          $element-count as xs:integer)
{
  if ($element-count eq 0) then
    $index
  else
    let $index := $index - 1
    let $element-count := $element-count - (if ($state[$index] instance of element()) then 1 else 0)
    return p:first-child-node-index($state, $index, $element-count)
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
    subsequence($state, $p:l1, 12),
    0, 0, 0,
    subsequence($state, 16),
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
    if ($match[1] = 7) then                                 (: ws :)
      p:matchW($input, $match[3], $token-set)
    else if ($match[1] = 11) then                           (: '(*' :)
      let $state := (0, $begin, $begin, $match, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false())
      let $state := p:predict($input, $state, 5)
      let $e0 := $state[$p:e0]
      let $state := p:parse($input, -1, 5, $state[$p:lk], -1, $e0, $e0, $e0, (1, -1, 0), $state)
      return p:matchW($input, $state[$p:e0], $token-set)
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
      subsequence($state, $p:l3, 6)
    else
    (
      p:matchW($input, $state[$p:e2], $set),
      0, 0, 0
    )
  return
  (
    $match[1] * 4096 + $state[$p:lk],
    subsequence($state, $p:b0, 8),
    $match,
    subsequence($state, 16)
  )
};

(:~
 : Lookahead one token on level 4 with whitespace skipping.
 :
 : @param $set the code of the DFA entry state for the set of valid tokens.
 : @param $input the input string.
 : @param $state lexer state, error indicator, and result stack.
 : @return the updated state.
 :)
declare function p:lookahead4W($set as xs:integer, $input as xs:string, $state as item()+) as item()+
{
  let $match :=
    if ($state[$p:l4] ne 0) then
      subsequence($state, $p:l4, 3)
    else
      p:matchW($input, $state[$p:e3], $set)
  return
  (
    $match[1] * 262144 + $state[$p:lk],
    subsequence($state, $p:b0, 11),
    $match,
    subsequence($state, 16)
  )
};

(:~
 : Parse start symbol rules from given string.
 :
 : @param $s the string to be parsed.
 : @return the result as generated by parser actions.
 :)
declare function p:parse-rules($s as xs:string) as item()*
{
  let $state := (0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false())
  let $state := p:predict($s, $state, 4)
  let $state := p:parse($s, 0, 4, $state[$p:lk], -1, 1, 1, 1, (1, -1, 0), $state)
  let $error := $state[$p:error]
  return
    if ($error) then
      element ERROR {$error/@*, p:error-message($s, $error)}
    else
      subsequence($state, $p:result)
};

(: End :)
