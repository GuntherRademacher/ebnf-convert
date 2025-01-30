// This file was generated on Thu Jan 30, 2025 13:23 (UTC+01) by REx v6.1 which is Copyright (c) 1979-2025 by Gunther Rademacher <grd@gmx.net>
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
    lookahead1W(7);                 // whitespace | '::=' | '=' | '=/'
    whitespace();
    parse_defined_as();
    lookahead1W(11);                // whitespace | rulename | integer | quoted-string | prose-val | '%' | '%i' | '%s' |
                                    // '(' | '*' | '['
    whitespace();
    parse_elements();
    eventHandler.endNonterminal("rule", e0);
  }

  private void parse_defined_as()
  {
    eventHandler.startNonterminal("defined-as", e0);
    switch (l1)
    {
    case 19:                        // '='
      consume(19);                  // '='
      break;
    case 20:                        // '=/'
      consume(20);                  // '=/'
      break;
    default:
      consume(18);                  // '::='
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
      if (l1 != 17                  // '/'
       && l1 != 26)                 // '|'
      {
        break;
      }
      switch (l1)
      {
      case 17:                      // '/'
        consume(17);                // '/'
        break;
      default:
        consume(26);                // '|'
      }
      lookahead1W(11);              // whitespace | rulename | integer | quoted-string | prose-val | '%' | '%i' | '%s' |
                                    // '(' | '*' | '['
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
      lookahead1W(12);              // whitespace | rulename | integer | quoted-string | prose-val | EOF | '%' | '%i' |
                                    // '%s' | '(' | ')' | '*' | '/' | '[' | ']' | '|'
      switch (l1)
      {
      case 2:                       // rulename
        lookahead2W(15);            // whitespace | rulename | integer | quoted-string | prose-val | EOF | '%' | '%i' |
                                    // '%s' | '(' | ')' | '*' | '/' | '::=' | '=' | '=/' | '[' | ']' | '|'
        break;
      default:
        lk = l1;
      }
      if (lk == 8                   // EOF
       || lk == 13                  // ')'
       || lk == 17                  // '/'
       || lk == 22                  // ']'
       || lk == 26                  // '|'
       || lk == 578                 // rulename '::='
       || lk == 610                 // rulename '='
       || lk == 642)                // rulename '=/'
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
     || l1 == 14)                   // '*'
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
      lookahead2W(10);              // whitespace | rulename | quoted-string | prose-val | '%' | '%i' | '%s' | '(' |
                                    // '*' | '['
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 14:                        // '*'
    case 451:                       // integer '*'
      if (l1 == 3)                  // integer
      {
        consume(3);                 // integer
      }
      lookahead1(3);                // '*'
      consume(14);                  // '*'
      lookahead1W(9);               // whitespace | rulename | integer | quoted-string | prose-val | '%' | '%i' | '%s' |
                                    // '(' | '['
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
    lookahead1W(8);                 // whitespace | rulename | quoted-string | prose-val | '%' | '%i' | '%s' | '(' | '['
    switch (l1)
    {
    case 2:                         // rulename
      consume(2);                   // rulename
      break;
    case 12:                        // '('
      parse_group();
      break;
    case 21:                        // '['
      parse_option();
      break;
    case 9:                         // '%'
      parse_num_val();
      break;
    case 7:                         // prose-val
      consume(7);                   // prose-val
      break;
    default:
      parse_char_val();
    }
    eventHandler.endNonterminal("element", e0);
  }

  private void parse_group()
  {
    eventHandler.startNonterminal("group", e0);
    consume(12);                    // '('
    lookahead1W(11);                // whitespace | rulename | integer | quoted-string | prose-val | '%' | '%i' | '%s' |
                                    // '(' | '*' | '['
    whitespace();
    parse_alternation();
    consume(13);                    // ')'
    eventHandler.endNonterminal("group", e0);
  }

  private void parse_option()
  {
    eventHandler.startNonterminal("option", e0);
    consume(21);                    // '['
    lookahead1W(11);                // whitespace | rulename | integer | quoted-string | prose-val | '%' | '%i' | '%s' |
                                    // '(' | '*' | '['
    whitespace();
    parse_alternation();
    consume(22);                    // ']'
    eventHandler.endNonterminal("option", e0);
  }

  private void parse_char_val()
  {
    eventHandler.startNonterminal("char-val", e0);
    switch (l1)
    {
    case 11:                        // '%s'
      parse_case_sensitive_string();
      break;
    default:
      parse_case_insensitive_string();
    }
    eventHandler.endNonterminal("char-val", e0);
  }

  private void parse_case_insensitive_string()
  {
    eventHandler.startNonterminal("case-insensitive-string", e0);
    if (l1 == 10)                   // '%i'
    {
      consume(10);                  // '%i'
    }
    lookahead1W(5);                 // whitespace | quoted-string
    consume(4);                     // quoted-string
    eventHandler.endNonterminal("case-insensitive-string", e0);
  }

  private void parse_case_sensitive_string()
  {
    eventHandler.startNonterminal("case-sensitive-string", e0);
    consume(11);                    // '%s'
    lookahead1W(5);                 // whitespace | quoted-string
    consume(4);                     // quoted-string
    eventHandler.endNonterminal("case-sensitive-string", e0);
  }

  private void parse_num_val()
  {
    eventHandler.startNonterminal("num-val", e0);
    consume(9);                     // '%'
    lookahead1(6);                  // 'b' | 'd' | 'x'
    switch (l1)
    {
    case 23:                        // 'b'
      parse_bin_val();
      break;
    case 24:                        // 'd'
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
    consume(23);                    // 'b'
    lookahead1(1);                  // bits
    consume(5);                     // bits
    lookahead1W(14);                // whitespace | rulename | integer | quoted-string | prose-val | EOF | '%' | '%i' |
                                    // '%s' | '(' | ')' | '*' | '-' | '.' | '/' | '[' | ']' | '|'
    if (l1 == 15                    // '-'
     || l1 == 16)                   // '.'
    {
      switch (l1)
      {
      case 16:                      // '.'
        for (;;)
        {
          consume(16);              // '.'
          lookahead1(1);            // bits
          consume(5);               // bits
          lookahead1W(13);          // whitespace | rulename | integer | quoted-string | prose-val | EOF | '%' | '%i' |
                                    // '%s' | '(' | ')' | '*' | '.' | '/' | '[' | ']' | '|'
          if (l1 != 16)             // '.'
          {
            break;
          }
        }
        break;
      default:
        consume(15);                // '-'
        lookahead1(1);              // bits
        consume(5);                 // bits
      }
    }
    eventHandler.endNonterminal("bin-val", e0);
  }

  private void parse_dec_val()
  {
    eventHandler.startNonterminal("dec-val", e0);
    consume(24);                    // 'd'
    lookahead1(0);                  // integer
    consume(3);                     // integer
    lookahead1W(14);                // whitespace | rulename | integer | quoted-string | prose-val | EOF | '%' | '%i' |
                                    // '%s' | '(' | ')' | '*' | '-' | '.' | '/' | '[' | ']' | '|'
    if (l1 == 15                    // '-'
     || l1 == 16)                   // '.'
    {
      switch (l1)
      {
      case 16:                      // '.'
        for (;;)
        {
          consume(16);              // '.'
          lookahead1(0);            // integer
          consume(3);               // integer
          lookahead1W(13);          // whitespace | rulename | integer | quoted-string | prose-val | EOF | '%' | '%i' |
                                    // '%s' | '(' | ')' | '*' | '.' | '/' | '[' | ']' | '|'
          if (l1 != 16)             // '.'
          {
            break;
          }
        }
        break;
      default:
        consume(15);                // '-'
        lookahead1(0);              // integer
        consume(3);                 // integer
      }
    }
    eventHandler.endNonterminal("dec-val", e0);
  }

  private void parse_hex_val()
  {
    eventHandler.startNonterminal("hex-val", e0);
    consume(25);                    // 'x'
    lookahead1(2);                  // hexdigs
    consume(6);                     // hexdigs
    lookahead1W(14);                // whitespace | rulename | integer | quoted-string | prose-val | EOF | '%' | '%i' |
                                    // '%s' | '(' | ')' | '*' | '-' | '.' | '/' | '[' | ']' | '|'
    if (l1 == 15                    // '-'
     || l1 == 16)                   // '.'
    {
      switch (l1)
      {
      case 16:                      // '.'
        for (;;)
        {
          consume(16);              // '.'
          lookahead1(2);            // hexdigs
          consume(6);               // hexdigs
          lookahead1W(13);          // whitespace | rulename | integer | quoted-string | prose-val | EOF | '%' | '%i' |
                                    // '%s' | '(' | ')' | '*' | '.' | '/' | '[' | ']' | '|'
          if (l1 != 16)             // '.'
          {
            break;
          }
        }
        break;
      default:
        consume(15);                // '-'
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
    for (int i = 0; i < 27; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 29 + s - 1;
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
    /*   0 */ 32, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 6,
    /*  35 */ 5, 5, 7, 5, 5, 8, 9, 10, 5, 5, 11, 12, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 16, 17, 18, 19, 20, 5,
    /*  64 */ 5, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
    /*  91 */ 23, 5, 24, 5, 25, 5, 21, 26, 21, 27, 21, 21, 22, 22, 28, 22, 22, 22, 22, 22, 22, 22, 22, 22, 29, 22, 22,
    /* 118 */ 22, 22, 30, 22, 22, 5, 31, 5, 5, 0
  };

  private static final int[] MAP1 =
  {
    /*   0 */ 54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  26 */ 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  52 */ 58, 58, 90, 136, 167, 199, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104,
    /*  74 */ 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 104, 32, 0, 0, 0, 0, 0, 0, 0,
    /*  98 */ 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 133 */ 0, 0, 0, 4, 5, 6, 5, 5, 7, 5, 5, 8, 9, 10, 5, 5, 11, 12, 13, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 16,
    /* 163 */ 17, 18, 19, 20, 5, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
    /* 190 */ 22, 22, 22, 22, 23, 5, 24, 5, 25, 5, 21, 26, 21, 27, 21, 21, 22, 22, 28, 22, 22, 22, 22, 22, 22, 22, 22,
    /* 217 */ 22, 29, 22, 22, 22, 22, 30, 22, 22, 5, 31, 5, 5, 0
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
  };

  private static final int[] TRANSITION =
  {
    /*   0 */ 355, 355, 355, 355, 355, 355, 355, 355, 355, 264, 267, 267, 269, 355, 289, 355, 355, 326, 324, 324, 322,
    /*  21 */ 355, 319, 355, 355, 275, 273, 273, 279, 355, 424, 355, 355, 264, 267, 267, 269, 422, 393, 355, 355, 355,
    /*  42 */ 355, 355, 356, 422, 393, 355, 355, 422, 283, 283, 356, 287, 393, 355, 355, 355, 293, 293, 356, 422, 393,
    /*  63 */ 355, 355, 355, 297, 297, 356, 422, 393, 355, 355, 355, 355, 301, 356, 422, 393, 355, 311, 355, 312, 305,
    /*  84 */ 356, 422, 393, 355, 355, 355, 355, 343, 356, 309, 393, 355, 355, 355, 355, 316, 356, 422, 393, 355, 355,
    /* 105 */ 355, 355, 330, 356, 386, 393, 355, 337, 355, 416, 334, 356, 309, 393, 355, 419, 355, 416, 334, 356, 309,
    /* 126 */ 393, 355, 355, 368, 355, 368, 356, 383, 393, 355, 355, 401, 399, 399, 396, 422, 393, 355, 355, 355, 350,
    /* 147 */ 350, 356, 422, 393, 355, 355, 346, 355, 346, 356, 422, 393, 354, 355, 355, 355, 355, 356, 422, 360, 355,
    /* 168 */ 340, 367, 364, 364, 356, 309, 393, 355, 355, 367, 364, 364, 356, 309, 393, 355, 355, 355, 372, 372, 356,
    /* 189 */ 422, 393, 355, 355, 355, 355, 376, 356, 422, 393, 355, 355, 355, 355, 355, 356, 309, 393, 355, 340, 380,
    /* 210 */ 364, 364, 356, 309, 393, 355, 340, 390, 364, 364, 356, 309, 393, 355, 355, 367, 364, 364, 356, 309, 405,
    /* 231 */ 355, 355, 367, 364, 364, 356, 309, 409, 355, 355, 413, 364, 364, 356, 309, 393, 355, 355, 355, 355, 428,
    /* 252 */ 356, 422, 393, 355, 355, 355, 355, 432, 355, 355, 355, 355, 81, 81, 0, 81, 81, 81, 81, 0, 20, 83, 83, 83,
    /* 276 */ 83, 0, 83, 27, 0, 0, 19, 22, 22, 22, 22, 0, 160, 0, 0, 0, 28, 345, 345, 345, 345, 416, 416, 416, 416, 448,
    /* 302 */ 448, 448, 448, 480, 480, 480, 480, 117, 22, 0, 0, 0, 480, 480, 0, 544, 544, 0, 0, 18, 18, 0, 82, 82, 82,
    /* 327 */ 82, 0, 82, 576, 576, 576, 576, 129, 129, 129, 129, 194, 227, 0, 0, 227, 0, 0, 512, 0, 0, 0, 664, 26, 26,
    /* 352 */ 26, 26, 608, 0, 0, 0, 0, 20, 0, 256, 0, 28, 117, 117, 117, 117, 0, 0, 0, 23, 704, 704, 704, 704, 736, 736,
    /* 378 */ 736, 736, 117, 0, 768, 0, 22, 29, 0, 22, 0, 672, 117, 0, 800, 0, 26, 0, 28, 0, 0, 20, 20, 20, 20, 0, 20,
    /* 405 */ 352, 26, 0, 28, 384, 26, 0, 28, 117, 0, 832, 0, 129, 0, 129, 0, 227, 0, 22, 0, 0, 0, 27, 864, 864, 864,
    /* 431 */ 864, 288, 288, 288, 288
  };

  private static final int[] EXPECTED =
  {
    /*  0 */ 8, 32, 64, 16384, 6, 18, 58720256, 1835010, 2104982, 2104990, 2121366, 2121374, 73564062, 73629598,
    /* 14 */ 73662366, 75399070, 2, 2, 2, 2, 4, 16, 262144, 1048576, 3072, 128, 2, 2, 262144
  };

  private static final String[] TOKEN =
  {
    "%ERROR",
    "whitespace",
    "rulename",
    "integer",
    "quoted-string",
    "bits",
    "hexdigs",
    "prose-val",
    "EOF",
    "'%'",
    "'%i'",
    "'%s'",
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
