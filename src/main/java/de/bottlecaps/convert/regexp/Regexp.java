// This file was generated on Thu Jan 30, 2025 13:23 (UTC+01) by REx v6.1 which is Copyright (c) 1979-2025 by Gunther Rademacher <grd@gmx.net>
// REx command line: -q -tree -a none -java -interface de.bottlecaps.convert.Parser -name de.bottlecaps.convert.regexp.Regexp regexp.ebnf

package de.bottlecaps.convert.regexp;


public class Regexp implements de.bottlecaps.convert.Parser
{
  public Regexp(CharSequence string, EventHandler t)
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
    parse_regexp();
  }

  public void parse_regexp()
  {
    eventHandler.startNonterminal("regexp", e0);
    parse_alternatives();
    consume(4);                     // EOF
    eventHandler.endNonterminal("regexp", e0);
  }

  private void parse_alternatives()
  {
    eventHandler.startNonterminal("alternatives", e0);
    parse_sequence();
    for (;;)
    {
      if (l1 != 19)                 // '|'
      {
        break;
      }
      consume(19);                  // '|'
      parse_sequence();
    }
    eventHandler.endNonterminal("alternatives", e0);
  }

  private void parse_sequence()
  {
    eventHandler.startNonterminal("sequence", e0);
    for (;;)
    {
      parse_item();
      lookahead1(5);                // escaped-character | plain-character | EOF | '(' | '(?:' | '(?=' | ')' | '.' |
                                    // '[' | '|'
      if (l1 == 4                   // EOF
       || l1 == 8                   // ')'
       || l1 == 19)                 // '|'
      {
        break;
      }
    }
    eventHandler.endNonterminal("sequence", e0);
  }

  private void parse_item()
  {
    eventHandler.startNonterminal("item", e0);
    parse_primary();
    lookahead1(6);                  // escaped-character | plain-character | EOF | '(' | '(?:' | '(?=' | ')' | '*' |
                                    // '*?' | '+' | '+?' | '.' | '?' | '[' | '|'
    if (l1 == 9                     // '*'
     || l1 == 10                    // '*?'
     || l1 == 11                    // '+'
     || l1 == 12                    // '+?'
     || l1 == 15)                   // '?'
    {
      parse_occurrence();
    }
    eventHandler.endNonterminal("item", e0);
  }

  private void parse_primary()
  {
    eventHandler.startNonterminal("primary", e0);
    lookahead1(4);                  // escaped-character | plain-character | '(' | '(?:' | '(?=' | '.' | '['
    switch (l1)
    {
    case 14:                        // '.'
      consume(14);                  // '.'
      break;
    case 16:                        // '['
      parse_charset();
      break;
    case 5:                         // '('
      consume(5);                   // '('
      parse_alternatives();
      consume(8);                   // ')'
      break;
    case 6:                         // '(?:'
      consume(6);                   // '(?:'
      parse_alternatives();
      consume(8);                   // ')'
      break;
    case 7:                         // '(?='
      consume(7);                   // '(?='
      parse_alternatives();
      consume(8);                   // ')'
      break;
    default:
      parse_character();
    }
    eventHandler.endNonterminal("primary", e0);
  }

  private void parse_character()
  {
    eventHandler.startNonterminal("character", e0);
    switch (l1)
    {
    case 1:                         // escaped-character
      consume(1);                   // escaped-character
      break;
    default:
      consume(2);                   // plain-character
    }
    eventHandler.endNonterminal("character", e0);
  }

  private void parse_charset()
  {
    eventHandler.startNonterminal("charset", e0);
    consume(16);                    // '['
    lookahead1(2);                  // range-character | '^'
    if (l1 == 18)                   // '^'
    {
      consume(18);                  // '^'
    }
    for (;;)
    {
      parse_range();
      lookahead1(1);                // range-character | ']'
      if (l1 != 3)                  // range-character
      {
        break;
      }
    }
    consume(17);                    // ']'
    eventHandler.endNonterminal("charset", e0);
  }

  private void parse_range()
  {
    eventHandler.startNonterminal("range", e0);
    lookahead1(0);                  // range-character
    consume(3);                     // range-character
    lookahead1(3);                  // range-character | '-' | ']'
    if (l1 == 13)                   // '-'
    {
      consume(13);                  // '-'
      lookahead1(0);                // range-character
      consume(3);                   // range-character
    }
    eventHandler.endNonterminal("range", e0);
  }

  private void parse_occurrence()
  {
    eventHandler.startNonterminal("occurrence", e0);
    switch (l1)
    {
    case 15:                        // '?'
      consume(15);                  // '?'
      break;
    case 9:                         // '*'
      consume(9);                   // '*'
      break;
    case 11:                        // '+'
      consume(11);                  // '+'
      break;
    case 10:                        // '*?'
      consume(10);                  // '*?'
      break;
    default:
      consume(12);                  // '+?'
    }
    eventHandler.endNonterminal("occurrence", e0);
  }

  private void consume(int t)
  {
    if (l1 == t)
    {
      eventHandler.terminal(TOKEN[l1], b1, e1);
      b0 = b1; e0 = e1; l1 = 0;
    }
    else
    {
      error(b1, e1, 0, l1, t);
    }
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
    boolean nonbmp = false;
    begin = end;
    int current = end;
    int result = INITIAL[tokenSetId];
    int state = 0;

    for (int code = result & 15; code != 0; )
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
        if (c0 < 0xdc00)
        {
          int c1 = current < size ? input.charAt(current) : 0;
          if (c1 >= 0xdc00 && c1 < 0xe000)
          {
            nonbmp = true;
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
      int i0 = (charclass << 4) + code - 1;
      code = TRANSITION[(i0 & 3) + TRANSITION[i0 >> 2]];

      if (code > 15)
      {
        result = code;
        code &= 15;
        end = current;
      }
    }

    result >>= 4;
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
    else if ((result & 32) != 0)
    {
      end = begin;
      if (nonbmp)
      {
        for (int i = result >> 6; i > 0; --i)
        {
          int c1 = end < size ? input.charAt(end) : 0;
          ++end;
          if (c1 >= 0xd800 && c1 < 0xdc000)
          {
            ++end;
          }
        }
      }
      else
      {
        end += (result >> 6);
      }
    }
    else if (nonbmp)
    {
      for (int i = result >> 6; i > 0; --i)
      {
        --end;
        int c1 = end < size ? input.charAt(end) : 0;
        if (c1 >= 0xdc00 && c1 < 0xe000)
        {
          --end;
        }
      }
    }
    else
    {
      end -= result >> 6;
    }

    if (end > size) end = size;
    return (result & 31) - 1;
  }

  private static String[] getTokenSet(int tokenSetId)
  {
    java.util.ArrayList<String> expected = new java.util.ArrayList<>();
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 15;
    for (int i = 0; i < 20; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 15 + s - 1;
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
    /*   0 */ 17, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1,
    /*  35 */ 1, 1, 1, 1, 1, 2, 3, 4, 5, 1, 6, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 1, 1, 9, 1, 10, 1, 11, 11, 11, 11,
    /*  69 */ 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 13, 14, 15, 1,
    /*  96 */ 1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 1, 11, 11, 11, 1, 1, 1, 11, 11, 11, 11, 11, 11, 1,
    /* 124 */ 16, 1, 1, 1
  };

  private static final int[] MAP1 =
  {
    /*   0 */ 54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  26 */ 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  52 */ 58, 58, 90, 206, 122, 153, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182,
    /*  74 */ 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 182, 17, 0, 0, 0, 0, 0, 0, 0,
    /*  98 */ 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 11, 11, 11, 11, 11, 11, 11, 11,
    /* 131 */ 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 13, 14, 15, 1, 11, 11, 11, 11,
    /* 158 */ 11, 11, 11, 11, 11, 11, 11, 11, 11, 1, 11, 11, 11, 1, 1, 1, 11, 11, 11, 11, 11, 11, 1, 16, 1, 1, 1, 1, 1,
    /* 187 */ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 4, 5, 1, 6, 7, 1,
    /* 222 */ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 1, 1, 9, 1, 10
  };

  private static final int[] MAP2 =
  {
    /* 0 */ 57344, 65536, 65533, 1114111, 1, 1
  };

  private static final int[] INITIAL =
  {
    /* 0 */ 1, 2, 3, 4, 5, 6, 7
  };

  private static final int[] TRANSITION =
  {
    /*   0 */ 138, 138, 138, 138, 75, 72, 80, 136, 75, 90, 80, 136, 75, 97, 80, 136, 75, 101, 80, 136, 75, 105, 80, 136,
    /*  24 */ 76, 72, 80, 136, 75, 109, 80, 136, 75, 72, 80, 133, 75, 72, 80, 130, 75, 113, 117, 121, 75, 127, 137, 138,
    /*  48 */ 75, 142, 80, 136, 146, 150, 154, 136, 86, 72, 123, 136, 93, 72, 80, 136, 75, 158, 80, 136, 138, 83, 138,
    /*  71 */ 138, 48, 48, 48, 64, 64, 64, 64, 73, 1760, 0, 32, 0, 80, 80, 0, 288, 0, 288, 106, 106, 106, 64, 64, 304,
    /*  96 */ 64, 48, 144, 144, 64, 48, 48, 172, 64, 48, 48, 205, 64, 240, 240, 240, 64, 48, 48, 256, 64, 1760, 15, 32,
    /* 120 */ 176, 208, 1760, 0, 0, 32, 0, 48, 48, 48, 0, 1760, 128, 0, 1760, 112, 0, 1760, 0, 0, 0, 0, 272, 272, 272,
    /* 145 */ 64, 8, 8, 8, 8, 59, 59, 59, 64, 14, 0, 32, 0, 48, 320, 320, 64
  };

  private static final int[] EXPECTED =
  {
    /*  0 */ 8, 131080, 262152, 139272, 82150, 606710, 647158, 8, 8192, 192, 2, 1024, 4096, 8192, 192
  };

  private static final String[] TOKEN =
  {
    "%ERROR",
    "escaped-character",
    "plain-character",
    "range-character",
    "EOF",
    "'('",
    "'(?:'",
    "'(?='",
    "')'",
    "'*'",
    "'*?'",
    "'+'",
    "'+?'",
    "'-'",
    "'.'",
    "'?'",
    "'['",
    "']'",
    "'^'",
    "'|'"
  };
}

// End
