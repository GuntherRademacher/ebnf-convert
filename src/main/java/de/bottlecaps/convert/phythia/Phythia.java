// This file was generated on Thu Jan 30, 2025 13:23 (UTC+01) by REx v6.1 which is Copyright (c) 1979-2025 by Gunther Rademacher <grd@gmx.net>
// REx command line: -q -tree -a none -java -interface de.bottlecaps.convert.Parser -name de.bottlecaps.convert.phythia.Phythia phythia.ebnf

package de.bottlecaps.convert.phythia;


public class Phythia implements de.bottlecaps.convert.Parser
{
  public Phythia(CharSequence string, EventHandler t)
  {
    initialize(string, t);
  }

  public void initialize(CharSequence source, EventHandler parsingEventHandler)
  {
    eventHandler = parsingEventHandler;
    input = source;
    size = source.length();
    reset(0, 0, 0);
  }

  public CharSequence getInput()
  {
    return input;
  }

  public int getTokenOffset()
  {
    return b0;
  }

  public int getTokenEnd()
  {
    return e0;
  }

  public final void reset(int l, int b, int e)
  {
            b0 = b; e0 = b;
    l1 = l; b1 = b; e1 = e;
    end = e;
    eventHandler.reset(input);
  }

  public void reset()
  {
    reset(0, 0, 0);
  }

  public static String getOffendingToken(ParseException e)
  {
    return e.getOffending() < 0 ? null : TOKEN[e.getOffending()];
  }

  public static String[] getExpectedTokenSet(ParseException e)
  {
    String[] expected;
    if (e.getExpected() >= 0)
    {
      expected = new String[]{TOKEN[e.getExpected()]};
    }
    else
    {
      expected = getTokenSet(- e.getState());
    }
    return expected;
  }

  public String getErrorMessage(ParseException e)
  {
    String message = e.getMessage();
    String[] tokenSet = getExpectedTokenSet(e);
    String found = getOffendingToken(e);
    int size = e.getEnd() - e.getBegin();
    message += (found == null ? "" : ", found " + found)
            + "\nwhile expecting "
            + (tokenSet.length == 1 ? tokenSet[0] : java.util.Arrays.toString(tokenSet))
            + "\n"
            + (size == 0 || found != null ? "" : "after successfully scanning " + size + " characters beginning ");
    String prefix = input.subSequence(0, e.getBegin()).toString();
    int line = prefix.replaceAll("[^\n]", "").length() + 1;
    int column = prefix.length() - prefix.lastIndexOf('\n');
    return message
         + "at line " + line + ", column " + column + ":\n..."
         + input.subSequence(e.getBegin(), Math.min(input.length(), e.getBegin() + 64))
         + "...";
  }

  public void parse()
  {
    parse_Grammar();
  }

  public void parse_Grammar()
  {
    eventHandler.startNonterminal("Grammar", e0);
    lookahead1W(5);                 // Space | Comment | BeginOfGrammar | RULE
    if (l1 == 4)                    // BeginOfGrammar
    {
      consume(4);                   // BeginOfGrammar
    }
    for (;;)
    {
      lookahead1W(0);               // Space | Comment | RULE
      whitespace();
      parse_Production();
      lookahead1W(7);               // Space | Comment | RULE | EndOfGrammar | EOF
      if (l1 != 5)                  // RULE
      {
        break;
      }
    }
    if (l1 == 10)                   // EndOfGrammar
    {
      consume(10);                  // EndOfGrammar
    }
    lookahead1W(2);                 // Space | Comment | EOF
    consume(11);                    // EOF
    eventHandler.endNonterminal("Grammar", e0);
  }

  private void parse_Production()
  {
    eventHandler.startNonterminal("Production", e0);
    consume(5);                     // RULE
    lookahead1W(3);                 // Space | Comment | Name
    consume(12);                    // Name
    lookahead1W(1);                 // Space | Comment | SYNTAX
    consume(6);                     // SYNTAX
    lookahead1W(3);                 // Space | Comment | Name
    consume(12);                    // Name
    lookahead1W(4);                 // Space | Comment | '::='
    consume(20);                    // '::='
    lookahead1W(14);                // Space | StringLiteral | Comment | Sem | Name | '(' | '.' | '<*' | '<+' | '[' |
                                    // '{*' | '{+' | '|'
    whitespace();
    parse_Choice();
    consume(19);                    // '.'
    for (;;)
    {
      lookahead1W(6);               // Space | Comment | END_OF_RULE | Semantic
      if (l1 != 9)                  // Semantic
      {
        break;
      }
      consume(9);                   // Semantic
    }
    consume(7);                     // END_OF_RULE
    lookahead1W(3);                 // Space | Comment | Name
    consume(12);                    // Name
    lookahead1W(8);                 // Space | Comment | RULE | EndOfGrammar | EOF | '.'
    if (l1 == 19)                   // '.'
    {
      consume(19);                  // '.'
    }
    eventHandler.endNonterminal("Production", e0);
  }

  private void parse_Choice()
  {
    eventHandler.startNonterminal("Choice", e0);
    parse_Sequence();
    for (;;)
    {
      if (l1 != 28)                 // '|'
      {
        break;
      }
      consume(28);                  // '|'
      lookahead1W(17);              // Space | StringLiteral | Comment | Sem | Name | '(' | ')' | '*>' | '*}' | '+>' |
                                    // '+}' | '.' | ';' | '<*' | '<+' | '[' | ']' | '{*' | '{+' | '|'
      whitespace();
      parse_Sequence();
    }
    eventHandler.endNonterminal("Choice", e0);
  }

  private void parse_Sequence()
  {
    eventHandler.startNonterminal("Sequence", e0);
    for (;;)
    {
      lookahead1W(17);              // Space | StringLiteral | Comment | Sem | Name | '(' | ')' | '*>' | '*}' | '+>' |
                                    // '+}' | '.' | ';' | '<*' | '<+' | '[' | ']' | '{*' | '{+' | '|'
      if (l1 != 2                   // StringLiteral
       && l1 != 8                   // Sem
       && l1 != 12                  // Name
       && l1 != 13                  // '('
       && l1 != 22                  // '<*'
       && l1 != 23                  // '<+'
       && l1 != 24                  // '['
       && l1 != 26                  // '{*'
       && l1 != 27)                 // '{+'
      {
        break;
      }
      whitespace();
      parse_Item();
    }
    eventHandler.endNonterminal("Sequence", e0);
  }

  private void parse_Item()
  {
    eventHandler.startNonterminal("Item", e0);
    switch (l1)
    {
    case 12:                        // Name
      consume(12);                  // Name
      break;
    case 2:                         // StringLiteral
      consume(2);                   // StringLiteral
      break;
    case 22:                        // '<*'
      consume(22);                  // '<*'
      lookahead1W(15);              // Space | StringLiteral | Comment | Sem | Name | '(' | ';' | '<*' | '<+' | '[' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_Choice();
      consume(21);                  // ';'
      lookahead1W(10);              // Space | StringLiteral | Comment | Sem | Name | '(' | '*>' | '<*' | '<+' | '[' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_Choice();
      consume(15);                  // '*>'
      break;
    case 23:                        // '<+'
      consume(23);                  // '<+'
      lookahead1W(15);              // Space | StringLiteral | Comment | Sem | Name | '(' | ';' | '<*' | '<+' | '[' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_Choice();
      consume(21);                  // ';'
      lookahead1W(12);              // Space | StringLiteral | Comment | Sem | Name | '(' | '+>' | '<*' | '<+' | '[' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_Choice();
      consume(17);                  // '+>'
      break;
    case 26:                        // '{*'
      consume(26);                  // '{*'
      lookahead1W(11);              // Space | StringLiteral | Comment | Sem | Name | '(' | '*}' | '<*' | '<+' | '[' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_Choice();
      consume(16);                  // '*}'
      break;
    case 27:                        // '{+'
      consume(27);                  // '{+'
      lookahead1W(13);              // Space | StringLiteral | Comment | Sem | Name | '(' | '+}' | '<*' | '<+' | '[' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_Choice();
      consume(18);                  // '+}'
      break;
    case 24:                        // '['
      consume(24);                  // '['
      lookahead1W(16);              // Space | StringLiteral | Comment | Sem | Name | '(' | '<*' | '<+' | '[' | ']' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_Choice();
      consume(25);                  // ']'
      break;
    case 13:                        // '('
      consume(13);                  // '('
      lookahead1W(9);               // Space | StringLiteral | Comment | Sem | Name | '(' | ')' | '<*' | '<+' | '[' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_Choice();
      consume(14);                  // ')'
      break;
    default:
      consume(8);                   // Sem
    }
    eventHandler.endNonterminal("Item", e0);
  }

  private void consume(int t)
  {
    if (l1 == t)
    {
      whitespace();
      eventHandler.terminal(TOKEN[l1], b1, e1);
      b0 = b1; e0 = e1; l1 = 0;
    }
    else
    {
      error(b1, e1, 0, l1, t);
    }
  }

  private void whitespace()
  {
    if (e0 != b1)
    {
      eventHandler.whitespace(e0, b1);
      e0 = b1;
    }
  }

  private int matchW(int tokenSetId)
  {
    int code;
    for (;;)
    {
      code = match(tokenSetId);
      if (code != 1                 // Space
       && code != 3)                // Comment
      {
        break;
      }
    }
    return code;
  }

  private void lookahead1W(int tokenSetId)
  {
    if (l1 == 0)
    {
      l1 = matchW(tokenSetId);
      b1 = begin;
      e1 = end;
    }
  }

  private int error(int b, int e, int s, int l, int t)
  {
    throw new ParseException(b, e, s, l, t);
  }

  private int     b0, e0;
  private int l1, b1, e1;
  private EventHandler eventHandler = null;
  private CharSequence input = null;
  private int size = 0;
  private int begin = 0;
  private int end = 0;

  private int match(int tokenSetId)
  {
    begin = end;
    int current = end;
    int result = INITIAL[tokenSetId];
    int state = 0;

    for (int code = result & 255; code != 0; )
    {
      int charclass;
      int c0 = current < size ? input.charAt(current) : 0;
      ++current;
      if (c0 < 0x80)
      {
        charclass = MAP0[c0];
      }
      else if (c0 < 0xd800)
      {
        int c1 = c0 >> 3;
        charclass = MAP1[(c0 & 7) + MAP1[(c1 & 31) + MAP1[c1 >> 5]]];
      }
      else
      {
        if (c0 < 0xdc00)
        {
          int c1 = current < size ? input.charAt(current) : 0;
          if (c1 >= 0xdc00 && c1 < 0xe000)
          {
            ++current;
            c0 = ((c0 & 0x3ff) << 10) + (c1 & 0x3ff) + 0x10000;
          }
        }

        int lo = 0, hi = 1;
        for (int m = 1; ; m = (hi + lo) >> 1)
        {
          if (MAP2[m] > c0) {hi = m - 1;}
          else if (MAP2[2 + m] < c0) {lo = m + 1;}
          else {charclass = MAP2[4 + m]; break;}
          if (lo > hi) {charclass = 0; break;}
        }
      }

      state = code;
      int i0 = (charclass << 8) + code - 1;
      code = TRANSITION[(i0 & 7) + TRANSITION[i0 >> 3]];

      if (code > 255)
      {
        result = code;
        code &= 255;
        end = current;
      }
    }

    result >>= 8;
    if (result == 0)
    {
      end = current - 1;
      int c1 = end < size ? input.charAt(end) : 0;
      if (c1 >= 0xdc00 && c1 < 0xe000)
      {
        --end;
      }
      return error(begin, end, state, -1, -1);
    }

    if (end > size) end = size;
    return (result & 31) - 1;
  }

  private static String[] getTokenSet(int tokenSetId)
  {
    java.util.ArrayList<String> expected = new java.util.ArrayList<>();
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 255;
    for (int i = 0; i < 29; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 141 + s - 1;
      int f = EXPECTED[(i0 & 3) + EXPECTED[i0 >> 2]];
      for ( ; f != 0; f >>>= 1, ++j)
      {
        if ((f & 1) != 0)
        {
          expected.add(TOKEN[j]);
        }
      }
    }
    return expected.toArray(new String[]{});
  }

  private static final int[] MAP0 =
  {
    /*   0 */ 42, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3,
    /*  35 */ 2, 2, 2, 2, 4, 5, 6, 7, 8, 2, 9, 10, 11, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 12, 13, 14, 15, 16, 2, 2, 17, 18,
    /*  67 */ 19, 20, 21, 22, 23, 24, 25, 24, 24, 26, 27, 28, 29, 24, 24, 30, 31, 32, 33, 24, 24, 34, 35, 24, 36, 2, 37,
    /*  94 */ 2, 38, 2, 17, 18, 19, 20, 21, 22, 23, 24, 25, 24, 24, 26, 27, 28, 29, 24, 24, 30, 31, 32, 33, 24, 24, 34,
    /* 121 */ 35, 24, 39, 40, 41, 2, 2
  };

  private static final int[] MAP1 =
  {
    /*    0 */ 216, 291, 323, 383, 415, 908, 351, 815, 815, 447, 479, 511, 543, 575, 621, 882, 589, 681, 815, 815, 815,
    /*   21 */ 815, 815, 815, 815, 815, 815, 815, 815, 815, 713, 745, 821, 649, 815, 815, 815, 815, 815, 815, 815, 815,
    /*   42 */ 815, 815, 815, 815, 815, 815, 777, 809, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815,
    /*   63 */ 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 247, 247, 247, 247, 247, 247,
    /*   84 */ 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    /*  105 */ 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    /*  126 */ 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    /*  147 */ 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 259, 815, 815, 815, 815, 815, 815, 815, 815,
    /*  168 */ 815, 815, 815, 815, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    /*  189 */ 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    /*  210 */ 247, 247, 247, 247, 247, 853, 940, 949, 941, 941, 957, 965, 1057, 973, 999, 1007, 1015, 1036, 999, 1007,
    /*  230 */ 1015, 1044, 1258, 1258, 1258, 1258, 1258, 1258, 1259, 1258, 1250, 1250, 1251, 1250, 1250, 1250, 1251,
    /*  247 */ 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
    /*  264 */ 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1252, 1258,
    /*  281 */ 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1250, 1250, 1250, 1250, 1250, 1250, 1343,
    /*  298 */ 1251, 1249, 1248, 1250, 1250, 1250, 1250, 1250, 1251, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
    /*  315 */ 1254, 988, 1250, 1250, 1250, 1250, 1194, 991, 1250, 1250, 1250, 1258, 1258, 1258, 1258, 1258, 1258, 1258,
    /*  333 */ 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1257, 1258, 990, 1256, 1258, 1063,
    /*  350 */ 1258, 1258, 1258, 1258, 1258, 1249, 1250, 1250, 1255, 1117, 1309, 1062, 1258, 1057, 1063, 1117, 1250,
    /*  367 */ 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1345, 1250, 1251, 1128, 1057, 1146, 1025, 1057, 1063, 1057,
    /*  384 */ 1057, 1057, 1057, 1057, 1057, 1057, 1057, 1059, 1258, 1258, 1258, 1063, 1258, 1258, 1258, 1368, 1227,
    /*  401 */ 1250, 1250, 1247, 1250, 1250, 1250, 1250, 1251, 1251, 1387, 1248, 1250, 1254, 1258, 1249, 1071, 1250,
    /*  418 */ 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1249, 1071, 1250, 1250, 1250, 1250, 1080, 1258, 1250, 1250,
    /*  435 */ 1250, 1250, 1250, 1250, 1097, 1106, 1250, 1250, 1250, 1098, 1252, 1256, 1412, 1250, 1250, 1250, 1250,
    /*  452 */ 1250, 1250, 1165, 1057, 1059, 1026, 1250, 1151, 1057, 1258, 1258, 1412, 1097, 1344, 1250, 1250, 1248,
    /*  469 */ 1192, 1021, 1137, 1154, 1259, 1203, 1151, 1057, 1256, 1258, 1214, 1237, 1344, 1250, 1250, 1248, 1377,
    /*  486 */ 1021, 1157, 1154, 1258, 1225, 1260, 1057, 1235, 1258, 1412, 1226, 1247, 1250, 1250, 1248, 1245, 1165,
    /*  503 */ 1297, 1089, 1258, 1258, 1176, 1057, 1258, 1258, 1412, 1097, 1344, 1250, 1250, 1248, 1341, 1165, 1027,
    /*  520 */ 1154, 1260, 1203, 1109, 1057, 1258, 1258, 1184, 1269, 1285, 1281, 1195, 1269, 1119, 1109, 1028, 1025,
    /*  537 */ 1259, 1258, 1259, 1057, 1258, 1258, 1412, 1071, 1248, 1250, 1250, 1248, 1072, 1109, 1298, 1025, 1261,
    /*  554 */ 1258, 1109, 1057, 1258, 1258, 1184, 1071, 1248, 1250, 1250, 1248, 1072, 1109, 1298, 1025, 1261, 1401,
    /*  571 */ 1109, 1057, 1258, 1258, 1184, 1071, 1248, 1250, 1250, 1248, 1250, 1109, 1138, 1025, 1259, 1258, 1109,
    /*  588 */ 1057, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258,
    /*  605 */ 1258, 1258, 1258, 1258, 1250, 1250, 1250, 1250, 1252, 1258, 1250, 1250, 1250, 1250, 1251, 1258, 1249,
    /*  622 */ 1250, 1250, 1250, 1250, 1251, 1293, 1062, 1306, 1058, 1057, 1063, 1258, 1258, 1258, 1258, 1206, 1318,
    /*  639 */ 989, 1249, 1328, 1338, 1293, 1171, 1353, 1059, 1057, 1063, 1258, 1258, 1258, 1258, 1401, 1273, 1258,
    /*  656 */ 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1255, 1258, 1258, 1258, 1258, 1258, 1258, 1258,
    /*  673 */ 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1245, 1376, 1255, 1258, 1258, 1258, 1258, 1385, 1257,
    /*  690 */ 1385, 1194, 986, 1330, 1193, 1205, 1258, 1258, 1258, 1258, 1401, 1258, 1320, 1400, 1283, 1255, 1258,
    /*  707 */ 1258, 1258, 1258, 1396, 1257, 1398, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
    /*  724 */ 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1254, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
    /*  741 */ 1250, 1250, 1250, 1256, 1250, 1250, 1252, 1252, 1250, 1250, 1250, 1250, 1252, 1252, 1250, 1388, 1250,
    /*  758 */ 1250, 1250, 1252, 1250, 1250, 1250, 1250, 1250, 1250, 1071, 1120, 1217, 1253, 1098, 1254, 1250, 1253,
    /*  775 */ 1217, 1253, 980, 1258, 1258, 1258, 1249, 1310, 1136, 1258, 1249, 1250, 1250, 1250, 1250, 1250, 1250,
    /*  792 */ 1250, 1250, 1250, 1253, 1181, 1249, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1409,
    /*  809 */ 988, 1250, 1250, 1250, 1250, 1253, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258,
    /*  826 */ 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258,
    /*  843 */ 1258, 1258, 1258, 1258, 1057, 1060, 1366, 1258, 1258, 1258, 1250, 1250, 1250, 1250, 1250, 1250, 1250,
    /*  860 */ 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1250, 1254, 1258, 1258, 1258,
    /*  877 */ 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1258, 1063, 1057, 1063, 1050, 1358, 1250, 1249, 1250, 1250,
    /*  894 */ 1250, 1256, 1056, 1057, 1298, 1061, 1297, 1056, 1057, 1059, 1056, 1366, 1258, 1258, 1258, 1258, 1258,
    /*  911 */ 1258, 1258, 1258, 1249, 1250, 1250, 1250, 1251, 1398, 1249, 1250, 1250, 1250, 1251, 1258, 1056, 1057,
    /*  928 */ 1134, 1057, 1057, 1085, 1363, 1258, 1250, 1250, 1250, 1255, 1255, 1258, 42, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
    /*  951 */ 1, 0, 0, 1, 0, 0, 1, 2, 3, 2, 2, 2, 2, 4, 5, 6, 7, 8, 2, 9, 10, 11, 9, 9, 12, 13, 14, 15, 16, 2, 2, 2, 2,
    /*  984 */ 2, 9, 2, 24, 2, 2, 2, 2, 2, 24, 24, 24, 24, 24, 24, 2, 17, 18, 19, 20, 21, 22, 23, 24, 25, 24, 24, 26,
    /* 1012 */ 27, 28, 29, 24, 24, 30, 31, 32, 33, 24, 24, 2, 2, 9, 2, 9, 9, 9, 9, 2, 2, 2, 9, 9, 34, 35, 24, 36, 2, 37,
    /* 1042 */ 2, 38, 34, 35, 24, 39, 40, 41, 2, 2, 2, 2, 2, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 2, 2, 2, 2, 2, 2, 24, 24, 24,
    /* 1074 */ 24, 24, 2, 24, 24, 24, 24, 24, 2, 9, 9, 9, 9, 2, 9, 9, 9, 2, 9, 9, 9, 2, 2, 24, 24, 24, 24, 24, 2, 2, 24,
    /* 1105 */ 24, 24, 2, 2, 24, 24, 2, 2, 2, 2, 9, 9, 9, 24, 24, 24, 24, 24, 24, 24, 2, 24, 2, 24, 24, 24, 24, 2, 24,
    /* 1134 */ 9, 9, 2, 9, 9, 9, 9, 9, 2, 2, 9, 9, 9, 9, 9, 9, 9, 24, 24, 9, 9, 2, 2, 9, 9, 9, 2, 2, 2, 2, 9, 24, 24, 2,
    /* 1168 */ 2, 9, 24, 9, 9, 2, 9, 9, 24, 2, 2, 2, 2, 2, 9, 9, 2, 2, 9, 9, 2, 24, 24, 24, 24, 2, 24, 2, 2, 2, 24, 24,
    /* 1200 */ 2, 2, 2, 2, 2, 2, 2, 24, 24, 2, 24, 2, 2, 24, 2, 2, 9, 2, 2, 24, 24, 24, 2, 24, 24, 2, 24, 24, 24, 24, 2,
    /* 1231 */ 24, 2, 24, 24, 9, 9, 24, 24, 24, 2, 2, 2, 2, 24, 24, 2, 24, 24, 2, 24, 24, 24, 24, 24, 24, 24, 24, 2, 2,
    /* 1260 */ 2, 2, 2, 2, 2, 2, 9, 9, 2, 24, 24, 24, 2, 2, 2, 24, 24, 2, 2, 24, 2, 2, 24, 24, 2, 24, 2, 24, 24, 24, 24,
    /* 1291 */ 2, 2, 24, 9, 24, 24, 9, 9, 9, 9, 9, 9, 2, 9, 9, 24, 24, 24, 24, 24, 24, 9, 9, 9, 9, 9, 9, 24, 2, 24, 2,
    /* 1322 */ 2, 24, 2, 2, 24, 24, 2, 24, 24, 24, 2, 24, 2, 24, 2, 24, 2, 2, 24, 24, 2, 24, 24, 2, 2, 24, 24, 24, 24,
    /* 1351 */ 24, 2, 24, 24, 24, 24, 24, 2, 9, 2, 2, 2, 2, 9, 9, 2, 9, 2, 2, 2, 2, 2, 2, 24, 9, 2, 24, 2, 24, 24, 2,
    /* 1382 */ 24, 24, 2, 2, 2, 2, 2, 24, 2, 24, 2, 24, 2, 24, 2, 2, 2, 24, 2, 2, 2, 2, 2, 2, 2, 24, 2, 24, 24, 24, 2,
    /* 1413 */ 9, 9, 9, 2, 24, 24, 24
  };

  private static final int[] MAP2 =
  {
    /* 0 */ 57344, 65536, 65533, 1114111, 2, 2
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18
  };

  private static final int[] TRANSITION =
  {
    /*    0 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*   17 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1376, 1376,
    /*   34 */ 1381, 1574, 1546, 1545, 1392, 1394, 1533, 2366, 1402, 1404, 1781, 1414, 1434, 1421, 1445, 2148, 1545,
    /*   51 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1574,
    /*   68 */ 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545,
    /*   85 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1463, 1469, 2289, 1546, 1545,
    /*  102 */ 1589, 1472, 1520, 1522, 1480, 1482, 1484, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545,
    /*  119 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1492, 1498, 1679, 1546, 1545, 1706, 1708,
    /*  136 */ 1501, 1503, 1932, 1934, 1936, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  153 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1511, 1517, 1574, 1546, 1545, 1544, 1590, 1591, 2271,
    /*  170 */ 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  187 */ 1545, 1545, 1545, 1545, 1545, 1545, 1530, 1530, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404,
    /*  204 */ 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  221 */ 1545, 1545, 1545, 1545, 2372, 1541, 1639, 1554, 1545, 1561, 1590, 1591, 2271, 1991, 1404, 1406, 1847,
    /*  238 */ 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  255 */ 1545, 1545, 1691, 1570, 1661, 1582, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436,
    /*  272 */ 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  289 */ 1545, 1649, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148,
    /*  306 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1599, 1604,
    /*  323 */ 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 1616, 1545, 1545,
    /*  340 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1629, 1629, 1635, 1574, 1546,
    /*  357 */ 1545, 1647, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545,
    /*  374 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2376, 1545, 1562, 1574, 1546, 1545, 1544,
    /*  391 */ 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  408 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2405, 1657, 1574, 1546, 1545, 1544, 1590, 1591,
    /*  425 */ 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  442 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1669, 1675, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991,
    /*  459 */ 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  476 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1574, 1546, 1687, 1544, 1590, 1591, 2271, 1991, 1404, 1406,
    /*  493 */ 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  510 */ 1545, 1545, 1545, 1545, 1545, 1574, 1699, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437,
    /*  527 */ 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  544 */ 1731, 1722, 1728, 1608, 1546, 1648, 1716, 1739, 1591, 2271, 1991, 2340, 1406, 1844, 2090, 1451, 1748,
    /*  561 */ 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722,
    /*  578 */ 1728, 1608, 1546, 1648, 2175, 1590, 1740, 1807, 2308, 2310, 2312, 1763, 1755, 1754, 1769, 2148, 1545,
    /*  595 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608,
    /*  612 */ 1546, 1648, 2175, 1590, 1591, 1989, 1991, 1404, 1406, 1847, 1437, 1436, 1426, 2148, 1545, 1545, 1545,
    /*  629 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 2153,
    /*  646 */ 2175, 1590, 1591, 2271, 1777, 1404, 1789, 1847, 1455, 1436, 2112, 1802, 1545, 1545, 1545, 1545, 1545,
    /*  663 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2196, 1815, 1728, 1960, 1546, 1648, 1829, 1590,
    /*  680 */ 1837, 1918, 2240, 2346, 1858, 1863, 1871, 1903, 1905, 1913, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  697 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 2334, 2271,
    /*  714 */ 1991, 1404, 1406, 1794, 1437, 1946, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  731 */ 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 1926, 1404,
    /*  748 */ 1406, 1847, 1944, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  765 */ 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 1991, 1404, 1406, 1847,
    /*  782 */ 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  799 */ 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 1591, 1954, 1991, 1968, 1406, 1847, 1437, 1436,
    /*  816 */ 1878, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731,
    /*  833 */ 1722, 1728, 1608, 1546, 1978, 2175, 1986, 1591, 2271, 1991, 1999, 1406, 1847, 1437, 1436, 2112, 2011,
    /*  850 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2179, 1722, 1728,
    /*  867 */ 1608, 1546, 1384, 2175, 1590, 1591, 2271, 1991, 1404, 2029, 1847, 1437, 2037, 1895, 2148, 1545, 1545,
    /*  884 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 2056, 1546,
    /*  901 */ 2064, 2175, 2016, 1591, 2261, 1991, 2072, 1406, 1847, 2044, 2080, 2112, 2231, 1545, 1545, 1545, 1545,
    /*  918 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 2098, 1546, 1648, 2175,
    /*  935 */ 2303, 1591, 2271, 1991, 1404, 1406, 2106, 1437, 1890, 2048, 2148, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  952 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2120, 2127, 1728, 1608, 1546, 1648, 2175, 1590, 1591,
    /*  969 */ 1621, 2208, 1404, 1406, 2109, 1885, 1436, 2141, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /*  986 */ 1545, 1545, 1545, 1545, 1545, 1545, 2161, 2187, 2193, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 2003,
    /* 1003 */ 2204, 1406, 1847, 2087, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /* 1020 */ 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2216, 1590, 2236, 2271, 1991, 1404, 1406,
    /* 1037 */ 1847, 1437, 1436, 2224, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /* 1054 */ 1545, 1545, 1731, 1722, 2133, 1608, 1546, 1648, 2248, 1590, 1591, 2271, 2021, 1404, 1406, 1847, 1437,
    /* 1071 */ 1436, 2112, 2256, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /* 1088 */ 1731, 1722, 1728, 1608, 1546, 1648, 2175, 1590, 2269, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112,
    /* 1105 */ 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722,
    /* 1122 */ 1821, 1608, 1546, 1648, 2175, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545,
    /* 1139 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2279, 2285, 1574,
    /* 1156 */ 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545,
    /* 1173 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2297, 1574, 1546, 1545,
    /* 1190 */ 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545,
    /* 1207 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1731, 1722, 1728, 1608, 1546, 1648, 2320, 1590,
    /* 1224 */ 1591, 2328, 1991, 1404, 1970, 1850, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /* 1241 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 2354, 2360, 1574, 1546, 1545, 1544, 1590, 1591, 2271,
    /* 1258 */ 1991, 1404, 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /* 1275 */ 1545, 1545, 1545, 1545, 1545, 1545, 2384, 2390, 1574, 1546, 1545, 1544, 1590, 1591, 2271, 1991, 1404,
    /* 1292 */ 1406, 1847, 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /* 1309 */ 1545, 1545, 1545, 1545, 1545, 1545, 1574, 2168, 1545, 1544, 1590, 1591, 2271, 1991, 1404, 1406, 1847,
    /* 1326 */ 1437, 1436, 2112, 2148, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /* 1343 */ 1545, 2396, 2404, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545,
    /* 1360 */ 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 1545, 531, 531,
    /* 1378 */ 531, 531, 531, 531, 531, 531, 0, 0, 0, 0, 0, 54, 0, 3384, 40, 0, 0, 0, 0, 0, 61, 62, 63, 64, 81, 63, 64,
    /* 1405 */ 72, 0, 86, 0, 63, 64, 72, 86, 0, 105, 86, 0, 72, 86, 0, 114, 86, 125, 72, 86, 125, 72, 86, 113, 72, 86,
    /* 1431 */ 113, 2560, 113, 113, 114, 86, 113, 72, 86, 113, 72, 86, 113, 72, 72, 86, 134, 72, 86, 134, 86, 113, 72,
    /* 1454 */ 127, 113, 72, 86, 113, 72, 86, 122, 72, 0, 29, 29, 29, 29, 29, 29, 29, 0, 0, 0, 0, 0, 0, 63, 70, 64, 0,
    /* 1481 */ 70, 64, 72, 0, 86, 0, 70, 64, 72, 86, 0, 0, 30, 30, 30, 30, 30, 30, 30, 0, 0, 0, 0, 0, 0, 63, 71, 72, 0,
    /* 1510 */ 0, 0, 3584, 3584, 3584, 3584, 3584, 3584, 3584, 0, 0, 0, 0, 0, 0, 70, 64, 72, 0, 0, 0, 3840, 0, 0, 0, 0,
    /* 1536 */ 0, 0, 70, 71, 72, 0, 38, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 40, 6912, 0, 0, 0, 0, 0, 0, 49, 0, 0, 0, 0, 0, 0,
    /* 1568 */ 0, 43, 0, 39, 0, 0, 0, 0, 0, 0, 29, 30, 0, 0, 7168, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 63, 64, 72,
    /* 1599 */ 5120, 0, 0, 0, 0, 0, 5120, 0, 0, 0, 0, 0, 0, 29, 30, 0, 3351, 113, 113, 113, 113, 2929, 0, 0, 0, 63, 64,
    /* 1626 */ 72, 85, 0, 20, 20, 20, 20, 20, 20, 20, 20, 0, 0, 0, 0, 0, 0, 29, 30, 5888, 0, 1024, 0, 0, 0, 0, 0, 0, 0,
    /* 1655 */ 3351, 0, 0, 5632, 0, 0, 0, 0, 0, 0, 29, 30, 6144, 0, 0, 31, 31, 31, 31, 31, 31, 31, 0, 0, 0, 0, 0, 0, 29,
    /* 1684 */ 768, 0, 0, 0, 0, 5376, 0, 0, 0, 0, 0, 36, 37, 0, 0, 0, 4096, 0, 4608, 0, 4096, 4608, 40, 0, 0, 0, 0, 0,
    /* 1712 */ 0, 64, 63, 71, 40, 0, 0, 0, 0, 60, 0, 3351, 3351, 3351, 3351, 3351, 3351, 3351, 0, 0, 0, 0, 3351, 0, 0,
    /* 1737 */ 0, 0, 65, 0, 0, 0, 0, 0, 63, 64, 78, 132, 86, 113, 72, 86, 113, 86, 113, 78, 86, 113, 78, 86, 113, 78,
    /* 1763 */ 78, 86, 0, 78, 86, 0, 78, 86, 113, 78, 86, 113, 86, 113, 0, 88, 89, 72, 0, 86, 0, 63, 64, 105, 86, 0, 0,
    /* 1790 */ 103, 0, 63, 64, 72, 86, 0, 111, 112, 0, 72, 86, 138, 113, 113, 113, 113, 0, 0, 0, 63, 64, 78, 0, 0, 28,
    /* 1816 */ 3351, 3351, 3351, 3351, 3351, 3351, 3351, 0, 0, 0, 42, 3351, 0, 40, 1536, 0, 0, 0, 0, 0, 3351, 0, 72, 0,
    /* 1840 */ 0, 0, 76, 77, 72, 86, 110, 72, 86, 0, 72, 86, 0, 72, 86, 0, 72, 115, 2048, 92, 0, 63, 64, 72, 92, 0, 72,
    /* 1867 */ 92, 0, 72, 92, 116, 72, 92, 116, 72, 121, 116, 72, 86, 113, 72, 135, 113, 86, 113, 72, 86, 113, 120, 86,
    /* 1891 */ 113, 72, 86, 128, 72, 86, 113, 72, 86, 136, 86, 113, 92, 116, 72, 92, 116, 72, 92, 116, 92, 116, 116,
    /* 1914 */ 116, 116, 141, 116, 0, 0, 0, 63, 64, 84, 0, 0, 87, 63, 64, 90, 0, 86, 0, 63, 71, 72, 0, 86, 0, 63, 71,
    /* 1941 */ 72, 86, 0, 113, 117, 86, 113, 72, 86, 113, 72, 86, 131, 0, 80, 0, 63, 64, 72, 0, 0, 46, 0, 29, 30, 0,
    /* 1967 */ 3376, 64, 96, 0, 86, 0, 63, 64, 72, 106, 0, 50, 0, 0, 0, 0, 0, 0, 3351, 0, 66, 0, 0, 0, 0, 63, 64, 72, 0,
    /* 1996 */ 86, 0, 63, 64, 72, 97, 86, 0, 63, 64, 72, 0, 86, 0, 94, 113, 113, 140, 113, 113, 0, 0, 0, 68, 0, 0, 63,
    /* 2023 */ 64, 72, 91, 86, 0, 63, 0, 86, 104, 2304, 2304, 72, 86, 107, 124, 113, 126, 86, 113, 129, 86, 113, 72, 86,
    /* 2047 */ 119, 72, 86, 113, 72, 86, 113, 86, 137, 0, 45, 0, 47, 29, 30, 0, 3351, 0, 51, 0, 0, 0, 0, 0, 3351, 64,
    /* 2073 */ 72, 0, 98, 0, 63, 64, 102, 98, 113, 72, 86, 113, 72, 130, 113, 72, 118, 113, 72, 86, 113, 72, 86, 113,
    /* 2097 */ 123, 44, 0, 0, 0, 29, 30, 0, 3351, 108, 109, 0, 72, 86, 0, 72, 86, 113, 72, 86, 113, 86, 113, 21, 0, 0,
    /* 2123 */ 3351, 0, 21, 0, 21, 3351, 3351, 3351, 3351, 3351, 3351, 3351, 0, 0, 41, 0, 3351, 0, 72, 86, 113, 1280,
    /* 2145 */ 86, 113, 86, 113, 113, 113, 113, 113, 0, 0, 0, 52, 53, 0, 55, 3351, 0, 22, 0, 3351, 0, 0, 27, 0, 0, 4352,
    /* 2171 */ 0, 4864, 4352, 4864, 40, 0, 0, 0, 0, 0, 0, 3351, 0, 25, 0, 0, 0, 3360, 3360, 3360, 3360, 3360, 3360,
    /* 2194 */ 3360, 0, 0, 0, 0, 3351, 0, 0, 26, 28, 95, 72, 0, 86, 0, 63, 64, 72, 0, 86, 93, 63, 40, 0, 57, 0, 0, 0, 0,
    /* 2223 */ 3351, 72, 133, 113, 72, 86, 113, 86, 113, 113, 113, 113, 119, 0, 0, 0, 74, 0, 63, 64, 72, 0, 92, 0, 63,
    /* 2248 */ 40, 0, 0, 58, 0, 0, 0, 3351, 113, 139, 113, 113, 113, 0, 0, 0, 82, 83, 72, 0, 0, 1792, 0, 0, 0, 0, 63,
    /* 2275 */ 64, 72, 0, 0, 0, 6400, 6400, 6400, 6400, 6400, 6400, 6400, 0, 0, 0, 0, 0, 0, 768, 30, 0, 0, 6656, 6656,
    /* 2299 */ 0, 0, 0, 0, 0, 0, 67, 0, 69, 0, 63, 64, 78, 0, 86, 0, 63, 64, 78, 86, 0, 40, 0, 0, 0, 59, 0, 0, 3351, 79,
    /* 2329 */ 0, 0, 63, 64, 72, 0, 0, 73, 0, 75, 63, 64, 72, 0, 86, 99, 63, 64, 72, 0, 92, 0, 100, 101, 72, 0, 33, 33,
    /* 2357 */ 33, 33, 33, 33, 33, 0, 0, 0, 0, 0, 0, 81, 63, 64, 72, 0, 0, 34, 35, 0, 0, 0, 0, 24, 0, 0, 0, 0, 7424,
    /* 2386 */ 7424, 7424, 7424, 7424, 7424, 7424, 0, 0, 0, 0, 0, 0, 3072, 0, 0, 0, 0, 3072, 3072, 0, 0, 0, 0, 0, 0, 0,
    /* 2412 */ 5632
  };

  private static final int[] EXPECTED =
  {
    /*   0 */ 36, 40, 44, 48, 52, 63, 58, 67, 71, 75, 79, 90, 55, 90, 57, 97, 57, 86, 59, 88, 86, 59, 88, 86, 59, 100,
    /*  26 */ 101, 102, 103, 101, 102, 103, 101, 83, 94, 103, 42, 74, 2058, 4106, 1048586, 58, 650, 3114, 527402,
    /*  45 */ 499151118, 499167502, 499200270, 499265806, 499396878, 499659022, 501231886, 532689166, 535818510, 2, 8,
    /*  56 */ 32, 64, 16, 128, 512, 1024, 256, 32, 64, 4096, 1048576, 4, 4, 12582912, 4352, 201326592, 32768, 65536,
    /*  74 */ 131072, 262144, 98304, 393216, 8, 32, 64, 1048576, 16, 512, 1024, 512, 1024, 256, 256, 16, 128, 512, 1024,
    /*  93 */ 4352, 1024, 1024, 1024, 1024, 256, 256, 256, 16, 512, 1024, 16, 512, 1024
  };

  private static final String[] TOKEN =
  {
    "%ERROR",
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
  };
}

// End
