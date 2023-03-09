// This file was generated on Thu Mar 9, 2023 13:02 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net>
// REx command line: -q -tree -a none -java -interface de.bottlecaps.convert.Parser -name de.bottlecaps.convert.abnf.Abnf abnf.ebnf

package de.bottlecaps.convert.abnf;


public class Abnf implements de.bottlecaps.convert.Parser
{
  public Abnf(CharSequence string, EventHandler t)
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
    l2 = 0; b2 = 0; e2 = 0;
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
    parse_rulelist();
  }

  public void parse_rulelist()
  {
    eventHandler.startNonterminal("rulelist", e0);
    for (;;)
    {
      lookahead1W(4);               // whitespace | rulename
      whitespace();
      parse_rule();
      if (l1 != 2)                  // rulename
      {
        break;
      }
    }
    consume(8);                     // EOF
    eventHandler.endNonterminal("rulelist", e0);
  }

  private void parse_rule()
  {
    eventHandler.startNonterminal("rule", e0);
    consume(2);                     // rulename
    lookahead1W(6);                 // whitespace | '::=' | '=' | '=/'
    whitespace();
    parse_defined_as();
    lookahead1W(10);                // whitespace | rulename | integer | char-val | prose-val | '%' | '(' | '*' | '['
    whitespace();
    parse_elements();
    eventHandler.endNonterminal("rule", e0);
  }

  private void parse_defined_as()
  {
    eventHandler.startNonterminal("defined-as", e0);
    switch (l1)
    {
    case 17:                        // '='
      consume(17);                  // '='
      break;
    case 18:                        // '=/'
      consume(18);                  // '=/'
      break;
    default:
      consume(16);                  // '::='
    }
    eventHandler.endNonterminal("defined-as", e0);
  }

  private void parse_elements()
  {
    eventHandler.startNonterminal("elements", e0);
    parse_alternation();
    eventHandler.endNonterminal("elements", e0);
  }

  private void parse_alternation()
  {
    eventHandler.startNonterminal("alternation", e0);
    parse_concatenation();
    for (;;)
    {
      if (l1 != 15                  // '/'
       && l1 != 24)                 // '|'
      {
        break;
      }
      switch (l1)
      {
      case 15:                      // '/'
        consume(15);                // '/'
        break;
      default:
        consume(24);                // '|'
      }
      lookahead1W(10);              // whitespace | rulename | integer | char-val | prose-val | '%' | '(' | '*' | '['
      whitespace();
      parse_concatenation();
    }
    eventHandler.endNonterminal("alternation", e0);
  }

  private void parse_concatenation()
  {
    eventHandler.startNonterminal("concatenation", e0);
    for (;;)
    {
      whitespace();
      parse_repetition();
      lookahead1W(11);              // whitespace | rulename | integer | char-val | prose-val | EOF | '%' | '(' | ')' |
                                    // '*' | '/' | '[' | ']' | '|'
      switch (l1)
      {
      case 2:                       // rulename
        lookahead2W(14);            // whitespace | rulename | integer | char-val | prose-val | EOF | '%' | '(' | ')' |
                                    // '*' | '/' | '::=' | '=' | '=/' | '[' | ']' | '|'
        break;
      default:
        lk = l1;
      }
      if (lk == 8                   // EOF
       || lk == 11                  // ')'
       || lk == 15                  // '/'
       || lk == 20                  // ']'
       || lk == 24                  // '|'
       || lk == 514                 // rulename '::='
       || lk == 546                 // rulename '='
       || lk == 578)                // rulename '=/'
      {
        break;
      }
    }
    eventHandler.endNonterminal("concatenation", e0);
  }

  private void parse_repetition()
  {
    eventHandler.startNonterminal("repetition", e0);
    if (l1 == 3                     // integer
     || l1 == 12)                   // '*'
    {
      parse_repeat();
    }
    parse_element();
    eventHandler.endNonterminal("repetition", e0);
  }

  private void parse_repeat()
  {
    eventHandler.startNonterminal("repeat", e0);
    switch (l1)
    {
    case 3:                         // integer
      lookahead2(9);                // rulename | char-val | prose-val | '%' | '(' | '*' | '['
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 12:                        // '*'
    case 387:                       // integer '*'
      if (l1 == 3)                  // integer
      {
        consume(3);                 // integer
      }
      lookahead1(3);                // '*'
      consume(12);                  // '*'
      lookahead1(8);                // rulename | integer | char-val | prose-val | '%' | '(' | '['
      if (l1 == 3)                  // integer
      {
        consume(3);                 // integer
      }
      break;
    default:
      consume(3);                   // integer
    }
    eventHandler.endNonterminal("repeat", e0);
  }

  private void parse_element()
  {
    eventHandler.startNonterminal("element", e0);
    lookahead1(7);                  // rulename | char-val | prose-val | '%' | '(' | '['
    switch (l1)
    {
    case 2:                         // rulename
      consume(2);                   // rulename
      break;
    case 10:                        // '('
      parse_group();
      break;
    case 19:                        // '['
      parse_option();
      break;
    case 4:                         // char-val
      consume(4);                   // char-val
      break;
    case 9:                         // '%'
      parse_num_val();
      break;
    default:
      consume(7);                   // prose-val
    }
    eventHandler.endNonterminal("element", e0);
  }

  private void parse_group()
  {
    eventHandler.startNonterminal("group", e0);
    consume(10);                    // '('
    lookahead1W(10);                // whitespace | rulename | integer | char-val | prose-val | '%' | '(' | '*' | '['
    whitespace();
    parse_alternation();
    consume(11);                    // ')'
    eventHandler.endNonterminal("group", e0);
  }

  private void parse_option()
  {
    eventHandler.startNonterminal("option", e0);
    consume(19);                    // '['
    lookahead1W(10);                // whitespace | rulename | integer | char-val | prose-val | '%' | '(' | '*' | '['
    whitespace();
    parse_alternation();
    consume(20);                    // ']'
    eventHandler.endNonterminal("option", e0);
  }

  private void parse_num_val()
  {
    eventHandler.startNonterminal("num-val", e0);
    consume(9);                     // '%'
    lookahead1(5);                  // 'b' | 'd' | 'x'
    switch (l1)
    {
    case 21:                        // 'b'
      parse_bin_val();
      break;
    case 22:                        // 'd'
      parse_dec_val();
      break;
    default:
      parse_hex_val();
    }
    eventHandler.endNonterminal("num-val", e0);
  }

  private void parse_bin_val()
  {
    eventHandler.startNonterminal("bin-val", e0);
    consume(21);                    // 'b'
    lookahead1(1);                  // bits
    consume(5);                     // bits
    lookahead1W(13);                // whitespace | rulename | integer | char-val | prose-val | EOF | '%' | '(' | ')' |
                                    // '*' | '-' | '.' | '/' | '[' | ']' | '|'
    if (l1 == 13                    // '-'
     || l1 == 14)                   // '.'
    {
      switch (l1)
      {
      case 14:                      // '.'
        for (;;)
        {
          consume(14);              // '.'
          lookahead1(1);            // bits
          consume(5);               // bits
          lookahead1W(12);          // whitespace | rulename | integer | char-val | prose-val | EOF | '%' | '(' | ')' |
                                    // '*' | '.' | '/' | '[' | ']' | '|'
          if (l1 != 14)             // '.'
          {
            break;
          }
        }
        break;
      default:
        consume(13);                // '-'
        lookahead1(1);              // bits
        consume(5);                 // bits
      }
    }
    eventHandler.endNonterminal("bin-val", e0);
  }

  private void parse_dec_val()
  {
    eventHandler.startNonterminal("dec-val", e0);
    consume(22);                    // 'd'
    lookahead1(0);                  // integer
    consume(3);                     // integer
    lookahead1W(13);                // whitespace | rulename | integer | char-val | prose-val | EOF | '%' | '(' | ')' |
                                    // '*' | '-' | '.' | '/' | '[' | ']' | '|'
    if (l1 == 13                    // '-'
     || l1 == 14)                   // '.'
    {
      switch (l1)
      {
      case 14:                      // '.'
        for (;;)
        {
          consume(14);              // '.'
          lookahead1(0);            // integer
          consume(3);               // integer
          lookahead1W(12);          // whitespace | rulename | integer | char-val | prose-val | EOF | '%' | '(' | ')' |
                                    // '*' | '.' | '/' | '[' | ']' | '|'
          if (l1 != 14)             // '.'
          {
            break;
          }
        }
        break;
      default:
        consume(13);                // '-'
        lookahead1(0);              // integer
        consume(3);                 // integer
      }
    }
    eventHandler.endNonterminal("dec-val", e0);
  }

  private void parse_hex_val()
  {
    eventHandler.startNonterminal("hex-val", e0);
    consume(23);                    // 'x'
    lookahead1(2);                  // hexdigs
    consume(6);                     // hexdigs
    lookahead1W(13);                // whitespace | rulename | integer | char-val | prose-val | EOF | '%' | '(' | ')' |
                                    // '*' | '-' | '.' | '/' | '[' | ']' | '|'
    if (l1 == 13                    // '-'
     || l1 == 14)                   // '.'
    {
      switch (l1)
      {
      case 14:                      // '.'
        for (;;)
        {
          consume(14);              // '.'
          lookahead1(2);            // hexdigs
          consume(6);               // hexdigs
          lookahead1W(12);          // whitespace | rulename | integer | char-val | prose-val | EOF | '%' | '(' | ')' |
                                    // '*' | '.' | '/' | '[' | ']' | '|'
          if (l1 != 14)             // '.'
          {
            break;
          }
        }
        break;
      default:
        consume(13);                // '-'
        lookahead1(2);              // hexdigs
        consume(6);                 // hexdigs
      }
    }
    eventHandler.endNonterminal("hex-val", e0);
  }

  private void consume(int t)
  {
    if (l1 == t)
    {
      whitespace();
      eventHandler.terminal(TOKEN[l1], b1, e1);
      b0 = b1; e0 = e1; l1 = l2; if (l1 != 0) {
      b1 = b2; e1 = e2; l2 = 0; }
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
      if (code != 1)                // whitespace
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

  private void lookahead2W(int tokenSetId)
  {
    if (l2 == 0)
    {
      l2 = matchW(tokenSetId);
      b2 = begin;
      e2 = end;
    }
    lk = (l2 << 5) | l1;
  }

  private void lookahead1(int tokenSetId)
  {
    if (l1 == 0)
    {
      l1 = match(tokenSetId);
      b1 = begin;
      e1 = end;
    }
  }

  private void lookahead2(int tokenSetId)
  {
    if (l2 == 0)
    {
      l2 = match(tokenSetId);
      b2 = begin;
      e2 = end;
    }
    lk = (l2 << 5) | l1;
  }

  private int error(int b, int e, int s, int l, int t)
  {
    throw new ParseException(b, e, s, l, t);
  }

  private int lk, b0, e0;
  private int l1, b1, e1;
  private int l2, b2, e2;
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

    for (int code = result & 31; code != 0; )
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
        int c1 = c0 >> 5;
        charclass = MAP1[(c0 & 31) + MAP1[(c1 & 31) + MAP1[c1 >> 5]]];
      }
      else
      {
        charclass = 0;
      }

      state = code;
      int i0 = (charclass << 5) + code - 1;
      code = TRANSITION[(i0 & 3) + TRANSITION[i0 >> 2]];

      if (code > 31)
      {
        result = code;
        code &= 31;
        end = current;
      }
    }

    result >>= 5;
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
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 31;
    for (int i = 0; i < 25; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 27 + s - 1;
      int f = EXPECTED[i0];
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
    /*   0 */ 30, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 6,
    /*  35 */ 5, 5, 7, 5, 5, 8, 9, 10, 5, 5, 11, 12, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 16, 17, 18, 19, 20, 5,
    /*  64 */ 5, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
    /*  91 */ 23, 5, 24, 5, 25, 5, 21, 26, 21, 27, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
    /* 118 */ 22, 22, 28, 22, 22, 5, 29, 5, 5, 0
  };

  private static final int[] MAP1 =
  {
    /*   0 */ 54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  26 */ 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  52 */ 58, 58, 90, 136, 167, 199, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104,
    /*  74 */ 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 30, 0, 0, 0, 0, 0, 0, 0,
    /*  98 */ 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 133 */ 0, 0, 0, 4, 5, 6, 5, 5, 7, 5, 5, 8, 9, 10, 5, 5, 11, 12, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 16,
    /* 163 */ 17, 18, 19, 20, 5, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
    /* 190 */ 22, 22, 22, 22, 23, 5, 24, 5, 25, 5, 21, 26, 21, 27, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
    /* 217 */ 22, 22, 22, 22, 22, 22, 28, 22, 22, 5, 29, 5, 5, 0
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
  };

  private static final int[] TRANSITION =
  {
    /*   0 */ 258, 258, 258, 258, 258, 258, 258, 258, 258, 248, 423, 425, 250, 258, 256, 258, 258, 264, 442, 280, 443,
    /*  21 */ 258, 283, 258, 258, 289, 306, 308, 360, 258, 312, 258, 258, 248, 423, 425, 250, 260, 256, 258, 258, 258,
    /*  42 */ 258, 258, 375, 260, 256, 258, 258, 259, 318, 319, 375, 323, 256, 258, 258, 343, 331, 332, 375, 260, 256,
    /*  63 */ 258, 258, 349, 336, 337, 375, 260, 256, 258, 258, 258, 267, 270, 375, 260, 256, 258, 273, 258, 275, 276,
    /*  84 */ 375, 260, 256, 258, 258, 258, 258, 341, 402, 260, 256, 258, 258, 258, 258, 347, 375, 260, 256, 258, 258,
    /* 105 */ 258, 292, 295, 375, 353, 256, 258, 357, 258, 384, 386, 402, 260, 256, 258, 388, 258, 384, 386, 402, 260,
    /* 126 */ 256, 258, 258, 399, 258, 399, 375, 392, 256, 258, 258, 252, 378, 380, 375, 260, 256, 258, 258, 285, 326,
    /* 147 */ 327, 375, 260, 256, 258, 258, 396, 258, 396, 375, 260, 413, 258, 258, 258, 258, 258, 375, 416, 256, 258,
    /* 168 */ 420, 405, 408, 409, 402, 260, 256, 258, 258, 405, 408, 409, 402, 260, 256, 258, 258, 298, 301, 302, 375,
    /* 189 */ 260, 256, 258, 258, 258, 363, 366, 375, 260, 256, 258, 258, 258, 258, 258, 402, 260, 256, 258, 420, 429,
    /* 210 */ 408, 409, 402, 260, 256, 258, 420, 432, 408, 409, 402, 260, 256, 258, 258, 435, 408, 409, 402, 260, 256,
    /* 231 */ 258, 258, 258, 369, 372, 375, 260, 256, 258, 258, 258, 314, 439, 258, 258, 258, 258, 80, 0, 80, 0, 19, 0,
    /* 254 */ 19, 0, 0, 26, 0, 0, 0, 0, 23, 24, 81, 0, 81, 0, 0, 0, 384, 384, 384, 0, 0, 0, 416, 416, 416, 0, 81, 81,
    /* 282 */ 81, 17, 17, 0, 0, 0, 24, 82, 0, 82, 0, 0, 0, 512, 512, 512, 0, 0, 0, 640, 640, 640, 640, 0, 0, 0, 82, 82,
    /* 310 */ 82, 25, 0, 25, 0, 0, 0, 288, 23, 23, 23, 23, 0, 0, 0, 160, 24, 24, 24, 24, 0, 320, 320, 320, 320, 0, 352,
    /* 337 */ 352, 352, 352, 0, 0, 448, 0, 0, 0, 320, 480, 480, 0, 0, 0, 352, 0, 608, 23, 24, 129, 194, 227, 0, 0, 18,
    /* 363 */ 0, 0, 0, 672, 672, 672, 0, 0, 0, 800, 800, 800, 0, 0, 19, 0, 0, 19, 19, 19, 26, 129, 0, 129, 129, 129, 0,
    /* 390 */ 227, 0, 27, 0, 23, 24, 0, 0, 598, 0, 0, 21, 0, 0, 19, 116, 0, 0, 116, 116, 116, 116, 0, 0, 26, 544, 0, 0,
    /* 418 */ 23, 256, 0, 0, 227, 0, 0, 80, 80, 80, 80, 116, 704, 0, 116, 736, 0, 116, 768, 0, 116, 288, 288, 288, 0, 0,
    /* 444 */ 81, 81, 0
  };

  private static final int[] EXPECTED =
  {
    /*  0 */ 8, 32, 64, 4096, 6, 14680064, 458754, 525972, 525980, 530068, 530078, 18390942, 18407326, 18415518,
    /* 14 */ 18849694, 2, 2, 2, 2, 4, 65536, 262144, 16, 128, 2, 2, 65536
  };

  private static final String[] TOKEN =
  {
    "(0)",
    "whitespace",
    "rulename",
    "integer",
    "char-val",
    "bits",
    "hexdigs",
    "prose-val",
    "EOF",
    "'%'",
    "'('",
    "')'",
    "'*'",
    "'-'",
    "'.'",
    "'/'",
    "'::='",
    "'='",
    "'=/'",
    "'['",
    "']'",
    "'b'",
    "'d'",
    "'x'",
    "'|'"
  };
}

// End
