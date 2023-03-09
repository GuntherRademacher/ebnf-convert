// This file was generated on Thu Mar 9, 2023 13:03 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net>
// REx command line: -q -lalr 4 -tree -a none -java -interface de.bottlecaps.convert.LRParser -name de.bottlecaps.convert.instaparse.Instaparse instaparse.ebnf

package de.bottlecaps.convert.instaparse;

import java.util.Arrays;

public class Instaparse implements de.bottlecaps.convert.LRParser
{
  public Instaparse(CharSequence string, BottomUpEventHandler t)
  {
    initialize(string, t);
  }

  public void initialize(CharSequence source, BottomUpEventHandler parsingEventHandler)
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
    l3 = 0; b3 = 0; e3 = 0;
    l4 = 0; b4 = 0; e4 = 0;
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
    parse_rules();
  }

  public void parse_rules()
  {
    top = -1;
    parse(0, 4, eventHandler);
  }

  private void push(int state, int lookback, int begin)
  {
    top += 3;
    if (top >= iStack.length)
    {
      iStack = Arrays.copyOf(iStack, iStack.length << 1);
    }
    iStack[top - 2] = begin;
    iStack[top - 1] = state;
    iStack[top] = lookback;
  }

  private int lookback(int x, int y)
  {
    int i = LOOKBACK[y];
    int l = LOOKBACK[i];
    while (l > x)
    {
      i += 2;
      l = LOOKBACK[i];
    }
    if (l < x)
    {
      return 0;
    }
    else
    {
      return LOOKBACK[i + 1];
    }
  }

  private int count(int code)
  {
    int count = 0;
    for (int t = top; t >= 0; t -= 3)
    {
      code = lookback(iStack[t], code);
      if (code == 0)
      {
        break;
      }
      count += 1;
    }
    return count;
  }

  private void parse(int target, int initialState, BottomUpEventHandler eventHandler)
  {
    int state = initialState;
    int bw = e0;
    int bs = e0;
    int es = e0;
    int t = top;
    int action = predict(state);
    int nonterminalId = -1;
    for (;;)
    {
      int argument = action >> 10;
      int lookback = (action >> 3) & 127;
      int shift = -1;
      int reduce = -1;
      int symbols = -1;
      switch (action & 7)
      {
      case 1: // SHIFT
        shift = argument;
        break;

      case 2: // REDUCE
        reduce = argument;
        symbols = lookback;
        break;

      case 3: // REDUCE+LOOKBACK
        reduce = argument;
        symbols = count(lookback);
        break;

      case 4: // SHIFT+REDUCE
        shift = state;
        reduce = argument;
        symbols = lookback + 1;
        break;

      case 5: // SHIFT+REDUCE+LOOKBACK
        shift = state;
        reduce = argument;
        symbols = count(lookback) + 1;
        break;

      case 6: // ACCEPT
        return;

      default: // ERROR
        throw new ParseException(b1, e1, TOKENSET[state] + 1, l1, -1);
      }

      if (shift >= 0)
      {
        if (nonterminalId < 0)
        {
          if (eventHandler != null)
          {
            eventHandler.terminal(TOKEN[l1], b1, e1);
          }
          es = e1;
          push(state, lookback, b1);
          consume(l1);
        }
        else
        {
          push(state, lookback, bs);
        }
        state = shift;
      }

      if (reduce < 0)
      {
        action = predict(state);
        nonterminalId = -1;
      }
      else
      {
        nonterminalId = reduce;
        if (symbols > 0)
        {
          top -= symbols * 3;
          state = iStack[top + 2];
          bs = iStack[top + 1];
        }
        else
        {
          bs = b1;
          es = b1;
        }
        if (nonterminalId == target && t == top)
        {
          bs = bw;
          es = b1;
          bw = b1;
        }
        if (eventHandler != null)
        {
          eventHandler.nonterminal(NONTERMINAL[nonterminalId], bs, es, symbols);
        }
        action = goTo(nonterminalId, state);
      }
    }
  }

  private void consume(int t)
  {
    if (l1 == t)
    {
      b0 = b1; e0 = e1; l1 = l2; if (l1 != 0) {
      b1 = b2; e1 = e2; l2 = l3; if (l2 != 0) {
      b2 = b3; e2 = e3; l3 = l4; if (l3 != 0) {
      b3 = b4; e3 = e4; l4 = 0; }}}
    }
    else
    {
      error(b1, e1, 0, l1, t);
    }
  }

  private void skip(int code)
  {
    int b0W = b0; int e0W = e0; int l1W = l1;
    int b1W = b1; int e1W = e1; int l2W = l2;
    int b2W = b2; int e2W = e2; int l3W = l3;
    int b3W = b3; int e3W = e3;

    l1 = code; b1 = begin; e1 = end;
    l2 = 0;
    l3 = 0;
    l4 = 0;
    parse(-1, 5, null);

    b0 = b0W; e0 = e0W; l1 = l1W; if (l1 != 0) {
    b1 = b1W; e1 = e1W; l2 = l2W; if (l2 != 0) {
    b2 = b2W; e2 = e2W; l3 = l3W; if (l3 != 0) {
    b3 = b3W; e3 = e3W; }}}
  }

  private int matchW(int tokenSetId)
  {
    int code;
    for (;;)
    {
      code = match(tokenSetId);
      if (code != 7)                // ws
      {
        if (code != 11)             // '(*'
        {
          break;
        }
        skip(code);
      }
    }
    return code;
  }

  private int error(int b, int e, int s, int l, int t)
  {
    throw new ParseException(b, e, s, l, t);
  }

  private int     b0, e0;
  private int l1, b1, e1;
  private int l2, b2, e2;
  private int l3, b3, e3;
  private int l4, b4, e4;
  private int iStack[] = new int[192];
  private int top = -1;
  private BottomUpEventHandler eventHandler = null;
  private CharSequence input = null;
  private int size = 0;
  private int begin = 0;
  private int end = 0;

  private int predict(int dpi)
  {
    int d = dpi;
    if (l1 == 0)
    {
      l1 = matchW(TOKENSET[d]);
      b1 = begin;
      e1 = end;
    }
    for (;; ++d)
    {
      int j10 = 48 * d + l1;
      int j11 = j10 >> 2;
      int action = CASEID[(j10 & 3) + CASEID[(j11 & 3) + CASEID[j11 >> 2]]];
      if ((action & 1) == 0)
        return action >> 1;
      if (l2 == 0)
      {
        l2 = matchW(action >> 1);
        b2 = begin;
        e2 = end;
      }
      int i20 = 40 * l2 + l1;
      int i21 = i20 >> 2;
      int matchCode = LOOKAHEAD[(i20 & 3) + LOOKAHEAD[(i21 & 7) + LOOKAHEAD[i21 >> 3]]];
      if (matchCode != 0)
      {
        int j20 = 48 * d + matchCode;
        int j21 = j20 >> 2;
        action = CASEID[(j20 & 3) + CASEID[(j21 & 3) + CASEID[j21 >> 2]]];
        if (action != 0)
        {
          if ((action & 1) == 0)
            return action >> 1;
          if (l3 == 0)
          {
            l3 = matchW(action >> 1);
            b3 = begin;
            e3 = end;
          }
          int i30 = 40 * l3 + matchCode;
          int i31 = i30 >> 2;
          matchCode = LOOKAHEAD[(i30 & 3) + LOOKAHEAD[(i31 & 7) + LOOKAHEAD[i31 >> 3]]];
          if (matchCode != 0)
          {
            int j30 = 48 * d + matchCode;
            int j31 = j30 >> 2;
            action = CASEID[(j30 & 3) + CASEID[(j31 & 3) + CASEID[j31 >> 2]]];
            if (action != 0)
            {
              if ((action & 1) == 0)
                return action >> 1;
              if (l4 == 0)
              {
                l4 = matchW(action >> 1);
                b4 = begin;
                e4 = end;
              }
              int i40 = 40 * l4 + matchCode;
              int i41 = i40 >> 2;
              matchCode = LOOKAHEAD[(i40 & 3) + LOOKAHEAD[(i41 & 7) + LOOKAHEAD[i41 >> 3]]];
              if (matchCode != 0)
              {
                int j40 = 48 * d + matchCode;
                int j41 = j40 >> 2;
                action = CASEID[(j40 & 3) + CASEID[(j41 & 3) + CASEID[j41 >> 2]]];
                if (action != 0)
                {
                  return action >> 1;
                }
              }
            }
          }
        }
      }
    }
  }

  private int match(int tokenSetId)
  {
    boolean nonbmp = false;
    begin = end;
    int current = end;
    int result = INITIAL[tokenSetId];
    int state = 0;

    for (int code = result & 127; code != 0; )
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
        int c1 = c0 >> 6;
        charclass = MAP1[(c0 & 63) + MAP1[(c1 & 31) + MAP1[c1 >> 5]]];
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
      int i0 = (charclass << 7) + code - 1;
      code = TRANSITION[(i0 & 7) + TRANSITION[i0 >> 3]];

      if (code > 127)
      {
        result = code;
        code &= 127;
        end = current;
      }
    }

    result >>= 7;
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
    return (result & 63) - 1;
  }

  private static int goTo(int nonterminal, int state)
  {
    int i0 = 24 * state + nonterminal;
    int i1 = i0 >> 2;
    return GOTO[(i0 & 3) + GOTO[(i1 & 3) + GOTO[i1 >> 2]]];
  }

  private static String[] getTokenSet(int tokenSetId)
  {
    java.util.ArrayList<String> expected = new java.util.ArrayList<>();
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 127;
    for (int i = 0; i < 33; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 70 + s - 1;
      int i1 = i0 >> 2;
      int f = EXPECTED[(i0 & 3) + EXPECTED[(i1 & 15) + EXPECTED[i1 >> 4]]];
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
    /*   0 */ 41, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3,
    /*  35 */ 4, 5, 5, 6, 7, 8, 9, 10, 11, 1, 5, 12, 13, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 14, 15, 16, 17, 18, 19, 5, 5, 5,
    /*  67 */ 5, 5, 20, 5, 5, 5, 21, 5, 5, 22, 5, 23, 24, 25, 5, 5, 26, 5, 5, 5, 5, 5, 5, 5, 27, 28, 29, 5, 5, 5, 5, 5,
    /*  99 */ 5, 5, 30, 5, 5, 5, 31, 5, 5, 32, 5, 33, 34, 35, 5, 5, 36, 5, 5, 5, 5, 5, 5, 5, 37, 38, 39, 5, 5
  };

  private static final int[] MAP1 =
  {
    /*   0 */ 27, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42,
    /*  26 */ 42, 74, 138, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 211, 200, 200, 200, 200, 200,
    /*  47 */ 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200,
    /*  68 */ 200, 200, 200, 200, 200, 200, 41, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /*  99 */ 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 11, 1, 5, 12, 13, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 14,
    /* 133 */ 15, 16, 17, 18, 19, 5, 5, 5, 5, 5, 20, 5, 5, 5, 21, 5, 5, 22, 5, 23, 24, 25, 5, 5, 26, 5, 5, 5, 5, 5, 5,
    /* 164 */ 5, 27, 28, 29, 5, 5, 5, 5, 5, 5, 5, 30, 5, 5, 5, 31, 5, 5, 32, 5, 33, 34, 35, 5, 5, 36, 5, 5, 5, 5, 5, 5,
    /* 196 */ 5, 37, 38, 39, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
    /* 230 */ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 40,
    /* 265 */ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5
  };

  private static final int[] MAP2 =
  {
    /* 0 */ 57344, 65536, 65533, 1114111, 5, 5
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
  };

  private static final int[] TRANSITION =
  {
    /*    0 */ 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 1060, 1064, 1066, 672,
    /*   20 */ 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 690, 682, 691, 978, 879, 1239, 1355,
    /*   40 */ 690, 690, 690, 690, 690, 690, 690, 690, 689, 674, 734, 701, 699, 709, 722, 1355, 690, 690, 690, 690, 690,
    /*   61 */ 690, 690, 690, 689, 714, 742, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 969,
    /*   81 */ 758, 1120, 728, 770, 861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 689, 690, 783, 691, 978,
    /*  101 */ 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 974, 804, 691, 1192, 824, 1188, 1355, 690,
    /*  121 */ 690, 690, 690, 690, 690, 690, 690, 844, 847, 837, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690,
    /*  141 */ 690, 690, 690, 811, 954, 816, 855, 892, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 912,
    /*  161 */ 690, 905, 924, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 690, 938, 691, 978,
    /*  181 */ 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 1225, 962, 691, 978, 879, 1239, 1355, 690,
    /*  201 */ 690, 690, 690, 690, 690, 690, 690, 689, 1249, 988, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690,
    /*  221 */ 690, 690, 690, 689, 950, 829, 1359, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689,
    /*  241 */ 1020, 1008, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 1028, 1032, 691, 978,
    /*  261 */ 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 1245, 897, 916, 978, 1040, 1239, 1355, 690,
    /*  281 */ 690, 690, 690, 690, 690, 690, 690, 790, 884, 796, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690,
    /*  301 */ 690, 690, 690, 689, 690, 1053, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690, 995,
    /*  321 */ 1074, 1081, 728, 770, 861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770,
    /*  341 */ 861, 1089, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1104, 762,
    /*  361 */ 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1116, 867, 690, 690, 690, 690,
    /*  381 */ 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1160, 1128, 690, 690, 690, 690, 690, 690, 690, 969,
    /*  401 */ 758, 1120, 873, 1154, 861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 1168,
    /*  421 */ 1140, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 689, 1045, 1202, 691, 978, 879, 1239, 1355,
    /*  440 */ 690, 690, 690, 690, 690, 690, 690, 690, 689, 690, 1212, 980, 1210, 1220, 1233, 1355, 690, 690, 690, 690,
    /*  460 */ 690, 690, 690, 690, 749, 750, 1257, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690, 690,
    /*  480 */ 1015, 1265, 1272, 728, 770, 861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728,
    /*  500 */ 770, 861, 1096, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1175,
    /*  520 */ 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1116, 775, 690, 690, 690,
    /*  540 */ 690, 690, 690, 690, 969, 758, 1120, 728, 770, 861, 1110, 1146, 1182, 690, 690, 690, 690, 690, 690, 690,
    /*  560 */ 969, 758, 1120, 1134, 1280, 861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 969, 758, 1120, 728,
    /*  580 */ 1288, 930, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 689, 1000, 1296, 691, 978, 879, 1239,
    /*  599 */ 1355, 690, 690, 690, 690, 690, 690, 690, 690, 689, 1194, 1304, 691, 978, 879, 1239, 1355, 690, 690, 690,
    /*  619 */ 690, 690, 690, 690, 690, 689, 1312, 1319, 691, 978, 879, 1239, 1355, 690, 690, 690, 690, 690, 690, 690,
    /*  639 */ 690, 945, 1327, 1334, 728, 770, 861, 1110, 1116, 762, 690, 690, 690, 690, 690, 690, 690, 690, 1342, 1349,
    /*  659 */ 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 690, 0, 1050, 0, 0, 0, 0, 0, 32, 0, 0, 0,
    /*  683 */ 1152, 1152, 1152, 1152, 1152, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 32, 43, 34, 0, 0, 0, 0, 0, 0, 0, 512, 0, 563,
    /*  711 */ 768, 44, 34, 0, 0, 0, 0, 0, 33, 0, 0, 0, 0, 512, 43, 44, 54, 0, 0, 0, 4124, 4124, 4124, 0, 32, 32, 32,
    /*  738 */ 32, 32, 1, 1, 0, 33, 33, 33, 33, 33, 1, 1, 0, 0, 0, 0, 0, 0, 3712, 3712, 0, 4124, 4124, 0, 4124, 4124,
    /*  764 */ 4124, 4124, 4124, 4124, 0, 0, 0, 34, 4124, 4124, 0, 4124, 4124, 4124, 28, 4124, 284, 0, 0, 0, 1280, 1280,
    /*  786 */ 1280, 1280, 1280, 1, 1, 0, 0, 0, 0, 0, 3456, 0, 3456, 3456, 3456, 3456, 1, 1, 0, 34, 34, 34, 34, 34, 1,
    /*  811 */ 1, 0, 0, 0, 0, 2560, 0, 0, 2560, 2560, 2560, 1, 17280, 0, 32, 43, 640, 438, 0, 0, 0, 0, 0, 1695, 1, 1,
    /*  837 */ 27, 1435, 1435, 1435, 1435, 1435, 23, 23, 0, 27, 27, 27, 27, 27, 27, 1435, 27, 27, 2816, 0, 0, 0, 0, 0,
    /*  861 */ 0, 32, 43, 44, 34, 4124, 4124, 4124, 28, 4124, 284, 4124, 0, 0, 0, 4124, 4134, 4124, 0, 32, 43, 44, 34,
    /*  884 */ 0, 0, 0, 0, 0, 0, 3456, 3456, 0, 34, 0, 0, 17280, 0, 0, 0, 0, 0, 2048, 1, 1, 0, 0, 2688, 0, 2688, 2688,
    /*  911 */ 17317, 24, 25, 0, 25, 0, 0, 0, 0, 0, 0, 1920, 32, 0, 0, 1536, 0, 0, 0, 0, 32, 43, 44, 34, 4124, 4152,
    /*  937 */ 312, 0, 0, 2944, 0, 2944, 2944, 1, 1, 0, 0, 0, 28, 0, 0, 0, 1695, 0, 0, 0, 0, 0, 0, 2560, 2560, 3072, 0,
    /*  964 */ 0, 3072, 3072, 3072, 1, 1, 0, 0, 0, 4124, 0, 0, 0, 0, 0, 34, 0, 0, 0, 0, 0, 0, 0, 42, 3200, 0, 3200,
    /*  991 */ 3200, 3200, 3200, 1, 1, 0, 0, 0, 4125, 0, 0, 0, 0, 0, 2304, 0, 0, 3328, 0, 0, 3328, 3328, 3328, 1, 1, 0,
    /* 1017 */ 0, 0, 4126, 0, 0, 0, 0, 3328, 0, 3328, 3328, 0, 4224, 4224, 0, 4224, 4224, 4224, 4224, 4224, 4224, 1, 1,
    /* 1040 */ 1792, 32, 43, 44, 34, 0, 0, 0, 0, 0, 2176, 0, 0, 0, 0, 3584, 0, 3584, 3584, 1, 1, 0, 1050, 0, 1050, 1050,
    /* 1066 */ 1050, 1050, 1050, 1050, 1050, 1050, 1, 1, 0, 4125, 4125, 0, 4125, 4131, 4125, 4125, 4131, 4131, 4131,
    /* 1085 */ 4131, 4131, 1, 1, 4153, 4124, 51, 43, 44, 54, 4157, 4124, 4154, 51, 43, 44, 54, 4124, 4158, 4159, 4124,
    /* 1106 */ 51, 54, 4161, 4124, 4124, 4124, 51, 43, 44, 54, 4124, 4124, 51, 54, 4124, 4124, 4124, 4124, 4124, 4124,
    /* 1126 */ 1, 1, 4165, 4124, 4124, 4124, 4124, 4124, 0, 0, 0, 4124, 4135, 4136, 0, 32, 43, 44, 34, 4151, 4124, 4124,
    /* 1148 */ 51, 54, 4124, 4124, 4124, 4164, 0, 34, 4142, 4124, 0, 4124, 4124, 4124, 51, 54, 4124, 4124, 4163, 4124,
    /* 1168 */ 0, 34, 4124, 4124, 0, 4145, 4124, 4124, 4160, 51, 54, 4124, 4162, 4124, 4124, 4166, 4124, 4124, 4124,
    /* 1187 */ 4124, 0, 0, 51, 43, 44, 384, 0, 0, 0, 0, 0, 0, 0, 3840, 0, 2176, 2176, 2176, 2176, 2176, 1, 1, 0, 45, 0,
    /* 1213 */ 0, 0, 0, 0, 0, 1, 1, 0, 42, 52, 53, 45, 0, 0, 0, 0, 3072, 0, 3072, 3072, 0, 0, 59, 43, 44, 60, 0, 0, 51,
    /* 1242 */ 43, 44, 54, 0, 0, 0, 2048, 0, 0, 0, 0, 0, 0, 3200, 0, 3712, 0, 0, 3712, 3712, 3712, 1, 1, 0, 4126, 4126,
    /* 1268 */ 0, 4126, 4132, 4126, 4126, 4132, 4132, 4132, 4132, 4132, 1, 1, 0, 34, 4143, 4144, 0, 4124, 4124, 4124, 0,
    /* 1289 */ 34, 4124, 4124, 0, 4124, 4146, 50, 0, 2304, 2304, 2304, 2304, 2304, 1, 1, 3840, 0, 3840, 3840, 3840,
    /* 1309 */ 3840, 1, 1, 3968, 0, 0, 0, 0, 0, 3968, 3968, 0, 0, 3968, 3968, 3968, 1, 1, 0, 28, 28, 0, 28, 284, 28, 28,
    /* 1335 */ 284, 284, 284, 284, 284, 1, 1, 0, 0, 2432, 0, 2432, 0, 2432, 2432, 0, 0, 2432, 2432, 2432, 0, 0, 51, 54,
    /* 1359 */ 0, 0, 0, 0, 0, 0, 41, 32
  };

  private static final int[] EXPECTED =
  {
    /*  0 */ 3, 18, 31, 34, 38, 42, 46, 50, 54, 63, 66, 70, 57, 75, 71, 82, 78, 59, 80, 86, 88, 89, 93, 95, 95, 88, 88,
    /* 27 */ 88, 88, 88, 88, 88, 88, 88, 64, 2097152, 2176, 2099200, -2147481472, 526464, 67111040, 268437632,
    /* 42 */ 1073744000, -2147481472, -2147219328, 63616, -2105276288, -2147283778, -678688640, -158594944, -141817728,
    /* 51 */ -2147283010, -1387065410, -141619266, -2158658, -2097218, 64, 64, -2147483648, -2147483648, -2147483648, 8,
    /* 62 */ 4, 2097152, 128, 2048, -2147483648, -2147483648, 24576, 8, 48, 4, -2147483646, -2147483646, -2147483646,
    /* 75 */ 8192, 8, 32, 16, 4, -2147483646, -2147483646, -2147483648, -2147483648, 8, 32, -2147483646, -2147483646, 0,
    /* 89 */ 0, 0, 0, 1, 1, 0, 1, 1, 1, 1
  };

  private static final int[] LOOKAHEAD =
  {
    /*   0 */ 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 44, 42, 52, 63, 55, 43, 43, 43, 43, 43, 43,
    /*  26 */ 43, 43, 43, 43, 43, 43, 43, 68, 43, 43, 43, 43, 43, 73, 43, 63, 84, 108, 108, 108, 108, 108, 108, 108,
    /*  50 */ 108, 109, 108, 103, 94, 108, 108, 108, 108, 108, 97, 91, 108, 108, 108, 108, 87, 81, 108, 108, 108, 108,
    /*  72 */ 101, 108, 108, 108, 108, 108, 108, 107, 108, 0, 0, 41, 0, 0, 39, 0, 0, 0, 37, 0, 0, 42, 0, 0, 40, 0, 0, 0,
    /* 100 */ 38, 0, 34, 0, 0, 0, 36, 33, 0, 0, 0, 0, 35
  };

  private static final int[] CASEID =
  {
    /*   0 */ 99, 108, 112, 122, 161, 121, 99, 127, 112, 122, 161, 121, 122, 161, 131, 147, 122, 122, 122, 136, 131,
    /*  21 */ 132, 122, 122, 122, 161, 122, 115, 122, 122, 99, 140, 121, 104, 150, 122, 99, 140, 121, 99, 140, 121, 99,
    /*  43 */ 140, 121, 99, 140, 121, 144, 140, 121, 144, 140, 121, 99, 140, 121, 99, 140, 121, 122, 152, 122, 122, 102,
    /*  65 */ 122, 122, 156, 160, 122, 165, 169, 177, 122, 122, 122, 117, 122, 122, 174, 122, 122, 170, 122, 122, 123,
    /*  86 */ 122, 181, 185, 189, 193, 197, 201, 122, 205, 169, 122, 209, 213, 217, 221, 227, 260, 239, 260, 260, 317,
    /* 107 */ 260, 245, 223, 255, 247, 251, 254, 255, 260, 241, 260, 260, 230, 260, 259, 260, 260, 260, 260, 236, 245,
    /* 128 */ 265, 268, 247, 272, 260, 260, 260, 278, 284, 260, 260, 308, 282, 260, 260, 308, 217, 221, 233, 260, 261,
    /* 149 */ 317, 260, 288, 260, 260, 290, 260, 274, 301, 294, 273, 300, 260, 260, 260, 308, 326, 325, 305, 312, 307,
    /* 170 */ 260, 260, 260, 316, 359, 260, 260, 260, 296, 260, 260, 321, 323, 322, 260, 334, 331, 335, 334, 324, 260,
    /* 191 */ 260, 260, 339, 341, 340, 260, 351, 348, 352, 351, 342, 260, 260, 260, 326, 325, 327, 312, 344, 343, 356,
    /* 212 */ 363, 358, 260, 260, 260, 0, 43016, 28680, 28680, 30728, 30728, 0, 0, 0, 27094, 34834, 32786, 26642, 0, 0,
    /* 232 */ 24616, 0, 0, 26642, 0, 0, 34856, 0, 2088, 0, 0, 22658, 0, 28690, 30738, 27094, 27094, 27094, 43, 35, 37,
    /* 253 */ 43, 27094, 27094, 27094, 27094, 0, 24594, 0, 0, 0, 0, 6152, 34840, 0, 36888, 27094, 27094, 27094, 32792,
    /* 272 */ 16402, 0, 0, 0, 10292, 0, 8200, 8200, 8200, 8200, 28690, 30738, 0, 0, 90, 0, 0, 4262, 0, 0, 14376, 0, 0,
    /* 295 */ 10296, 0, 0, 22738, 0, 10292, 0, 0, 0, 10296, 39314, 18726, 18726, 0, 0, 0, 12296, 18726, 37170, 18726,
    /* 315 */ 18726, 32808, 0, 0, 0, 18450, 0, 38948, 38948, 38948, 0, 0, 0, 18726, 18726, 0, 34840, 0, 36888, 38948,
    /* 335 */ 38948, 38948, 38948, 32792, 0, 40996, 40996, 40996, 0, 0, 0, 20870, 20870, 34840, 0, 36888, 40996, 40996,
    /* 353 */ 40996, 40996, 32792, 39314, 20870, 20870, 0, 0, 0, 22568, 20870, 0, 20870, 20870
  };

  private static final int[] TOKENSET =
  {
    /*  0 */ 19, 0, 20, 0, 9, 2, 10, 11, 4, 0, 17, 3, 17, 17, 17, 17, 13, 13, 17, 17, 6, 1, 12, 16, 0, 6, 5, 7, 8, 20,
    /* 30 */ 20, 15, 14
  };

  private static final int[] LOOKBACK =
  {
    /*   0 */ 47, 47, 45, 45, 45, 48, 51, 47, 47, 47, 54, 59, 64, 64, 64, 47, 47, 47, 67, 72, 77, 77, 77, 47, 88, 80,
    /*  26 */ 85, 85, 85, 91, 98, 98, 98, 98, 98, 105, 105, 105, 105, 105, 112, 112, 112, 112, 112, 3, 2, 0, 3, 4, 0, 5,
    /*  52 */ 5, 0, 13, 12, 8, 7, 0, 13, 14, 8, 9, 0, 11, 11, 0, 21, 20, 16, 15, 0, 21, 22, 16, 17, 0, 19, 19, 0, 27,
    /*  81 */ 28, 16, 23, 0, 25, 25, 0, 27, 26, 0, 41, 40, 36, 35, 31, 30, 0, 41, 42, 36, 37, 31, 32, 0, 41, 43, 36, 38,
    /* 109 */ 31, 33, 0, 41, 44, 36, 39, 31, 34, 0
  };

  private static final int[] GOTO =
  {
    /*   0 */ 70, 55, 57, 70, 55, 57, 30, 38, 57, 36, 58, 57, 42, 32, 57, 45, 84, 57, 49, 68, 53, 62, 68, 66, 70, 76,
    /*  26 */ 72, 78, 68, 82, 94, 146, 95, 95, 103, 95, 95, 146, 95, 95, 156, 95, 95, 90, 95, 95, 97, 107, 119, 95, 97,
    /*  51 */ 116, 119, 125, 119, 121, 88, 95, 95, 95, 95, 101, 95, 97, 135, 119, 139, 119, 121, 88, 95, 97, 96, 110,
    /*  74 */ 112, 150, 112, 143, 95, 97, 96, 128, 96, 131, 121, 88, 153, 95, 329, 2297, 0, 0, 20489, 0, 6, 0, 0, 0, 0,
    /*  99 */ 21508, 0, 10249, 0, 0, 0, 21513, 0, 22537, 8196, 8196, 21508, 0, 21508, 21508, 21508, 0, 25609, 8196,
    /* 118 */ 8196, 21508, 23681, 21508, 21508, 21508, 289, 26633, 8196, 8196, 21508, 31913, 21508, 21508, 32985, 21508,
    /* 134 */ 21508, 27657, 8196, 8196, 21508, 28681, 8196, 8196, 21508, 0, 29705, 0, 0, 6169, 7177, 7177, 0, 30729, 0,
    /* 153 */ 0, 24665, 0, 0, 3076, 0, 3078
  };

  private static final String[] TOKEN =
  {
    "(0)",
    "epsilon",
    "single-quoted-string",
    "double-quoted-string",
    "single-quoted-regexp",
    "double-quoted-regexp",
    "comment-content",
    "ws",
    "'!'",
    "'&'",
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
  };

  private static final String[] NONTERMINAL =
  {
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
  };
}

// End
