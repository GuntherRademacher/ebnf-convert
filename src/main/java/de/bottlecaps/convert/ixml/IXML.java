// This file was generated on Thu Mar 9, 2023 13:03 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net>
// REx command line: -q -tree -a none -java -interface de.bottlecaps.convert.LRParser -name de.bottlecaps.convert.ixml.IXML -glr 1 ixml.ebnf

package de.bottlecaps.convert.ixml;

import java.util.PriorityQueue;

public class IXML implements de.bottlecaps.convert.LRParser
{
  public IXML(CharSequence string, BottomUpEventHandler t)
  {
    initialize(string, t);
  }

  public void initialize(CharSequence source, BottomUpEventHandler parsingEventHandler)
  {
    eventHandler = parsingEventHandler;
    input = source;
    size = source.length();
    reset();
  }

  public void reset()
  {
    maxId = 0;
    thread = new ParsingThread();
    thread.reset(0, 0, 0);
  }

  public static String getOffendingToken(ParseException e)
  {
    return e.getOffending() < 0 ? null : TOKEN[e.getOffending()];
  }

  public static String[] getExpectedTokenSet(ParseException e)
  {
    String[] expected = {};
    if (e.getExpected() >= 0)
    {
      expected = new String[]{TOKEN[e.getExpected()]};
    }
    else if (! e.isAmbiguousInput())
    {
      expected = getTokenSet(- e.getState());
    }
    return expected;
  }

  public String getErrorMessage(ParseException e)
  {
    String message = e.getMessage();
    if (e.isAmbiguousInput())
    {
      message += "\n";
    }
    else
    {
      String[] tokenSet = getExpectedTokenSet(e);
      String found = getOffendingToken(e);
      int size = e.getEnd() - e.getBegin();
      message += (found == null ? "" : ", found " + found)
              + "\nwhile expecting "
              + (tokenSet.length == 1 ? tokenSet[0] : java.util.Arrays.toString(tokenSet))
              + "\n"
              + (size == 0 || found != null ? "" : "after successfully scanning " + size + " characters beginning ");
    }
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
    parse_ixml();
  }

  public void parse_ixml()
  {
    thread = parse(0, 0, eventHandler, thread);
  }

  private static class StackNode
  {
    public int state;
    public int code;
    public int pos;
    public StackNode link;

    public StackNode(int state, int code, int pos, StackNode link)
    {
      this.state = state;
      this.code = code;
      this.pos = pos;
      this.link = link;
    }

    @Override
    public boolean equals(Object obj)
    {
      StackNode lhs = this;
      StackNode rhs = (StackNode) obj;
      while (lhs != null && rhs != null)
      {
        if (lhs == rhs) return true;
        if (lhs.state != rhs.state) return false;
        if (lhs.code != rhs.code) return false;
        if (lhs.pos != rhs.pos) return false;
        lhs = lhs.link;
        rhs = rhs.link;
      }
      return lhs == rhs;
    }

    public int lookback(int x, int y)
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

    public int count(int code)
    {
      int count = 0;
      for (StackNode node = this; node.state >= 0; node = node.link)
      {
        code = lookback(node.code, code);
        if (code == 0)
        {
          break;
        }
        count += 1;
      }
      return count;
    }

  }

  private abstract static class DeferredEvent
  {
    public DeferredEvent link;
    public String name;
    public int begin;
    public int end;

    public DeferredEvent(DeferredEvent link, String name, int begin, int end)
    {
      this.link = link;
      this.name = name;
      this.begin = begin;
      this.end = end;
    }

    public abstract void execute(BottomUpEventHandler eventHandler);

    public void release(BottomUpEventHandler eventHandler)
    {
      DeferredEvent current = this;
      DeferredEvent predecessor = current.link;
      current.link = null;
      while (predecessor != null)
      {
        DeferredEvent next = predecessor.link;
        predecessor.link = current;
        current = predecessor;
        predecessor = next;
      }
      do
      {
        current.execute(eventHandler);
        current = current.link;
      }
      while (current != null);
    }

    public void show(BottomUpEventHandler eventHandler)
    {
      java.util.Stack<DeferredEvent> stack = new java.util.Stack<>();
      for (DeferredEvent current = this; current != null; current = current.link)
      {
        stack.push(current);
      }
      while (! stack.isEmpty())
      {
        stack.pop().execute(eventHandler);
      }
    }
  }

  public static class TerminalEvent extends DeferredEvent
  {
    public TerminalEvent(DeferredEvent link, String name, int begin, int end)
    {
      super(link, name, begin, end);
    }

    @Override
    public void execute(BottomUpEventHandler eventHandler)
    {
      eventHandler.terminal(name, begin, end);
    }

    @Override
    public String toString()
    {
      return "terminal(" + name + ", " + begin + ", " + end + ")";
    }
  }

  public static class NonterminalEvent extends DeferredEvent
  {
    public int count;

    public NonterminalEvent(DeferredEvent link, String name, int begin, int end, int count)
    {
      super(link, name, begin, end);
      this.count = count;
    }

    @Override
    public void execute(BottomUpEventHandler eventHandler)
    {
      eventHandler.nonterminal(name, begin, end, count);
    }

    @Override
    public String toString()
    {
      return "nonterminal(" + name + ", " + begin + ", " + end + ", " + count + ")";
    }
  }

  private static final int PARSING = 0;
  private static final int ACCEPTED = 1;
  private static final int ERROR = 2;

  private ParsingThread parse(int target, int initialState, BottomUpEventHandler eventHandler, ParsingThread thread)
  {
    PriorityQueue<ParsingThread> threads = thread.open(initialState, eventHandler, target);
    for (;;)
    {
      thread = threads.poll();
      if (thread.accepted)
      {
        ParsingThread other = null;
        while (! threads.isEmpty())
        {
          other = threads.poll();
          if (thread.e0 < other.e0)
          {
            thread = other;
            other = null;
          }
        }
        if (other != null)
        {
          rejectAmbiguity(thread.stack.pos, thread.e0, thread.deferredEvent, other.deferredEvent);
        }
        if (thread.deferredEvent != null)
        {
          thread.deferredEvent.release(eventHandler);
          thread.deferredEvent = null;
        }
        return thread;
      }

      if (! threads.isEmpty())
      {
        if (threads.peek().equals(thread))
        {
          rejectAmbiguity(thread.stack.pos, thread.e0, thread.deferredEvent, threads.peek().deferredEvent);
        }
      }
      else
      {
        if (thread.deferredEvent != null)
        {
          thread.deferredEvent.release(eventHandler);
          thread.deferredEvent = null;
        }
      }

      int status;
      for (;;)
      {
        if ((status = thread.parse()) != PARSING) break;
        if (! threads.isEmpty()) break;
      }

      if (status != ERROR)
      {
        threads.offer(thread);
      }
      else if (threads.isEmpty())
      {
        throw new ParseException(thread.b1,
                                 thread.e1,
                                 TOKENSET[thread.state] + 1,
                                 thread.l1,
                                 -1
                                );
      }
    }
  }

  private void rejectAmbiguity(int begin, int end, DeferredEvent first, DeferredEvent second)
  {
    ParseTreeBuilder treeBuilder = new ParseTreeBuilder();
    treeBuilder.reset(input);
    second.show(treeBuilder);
    treeBuilder.nonterminal("ALTERNATIVE", treeBuilder.stack[0].begin, treeBuilder.stack[treeBuilder.top].end, treeBuilder.top + 1);
    Symbol secondTree = treeBuilder.pop(1)[0];
    first.show(treeBuilder);
    treeBuilder.nonterminal("ALTERNATIVE", treeBuilder.stack[0].begin, treeBuilder.stack[treeBuilder.top].end, treeBuilder.top + 1);
    treeBuilder.push(secondTree);
    treeBuilder.nonterminal("AMBIGUOUS", treeBuilder.stack[0].begin, treeBuilder.stack[treeBuilder.top].end, 2);
    throw new ParseException(begin, end, treeBuilder);
  }

  private ParsingThread thread = new ParsingThread();
  private BottomUpEventHandler eventHandler;
  private CharSequence input = null;
  private int size = 0;
  private int maxId = 0;

  private class ParsingThread implements Comparable<ParsingThread>
  {
    public PriorityQueue<ParsingThread> threads;
    public boolean accepted;
    public StackNode stack;
    public int state;
    public int action;
    public int target;
    public DeferredEvent deferredEvent;
    public int id;

    public PriorityQueue<ParsingThread> open(int initialState, BottomUpEventHandler eh, int t)
    {
      accepted = false;
      target = t;
      eventHandler = eh;
      if (eventHandler != null)
      {
        eventHandler.reset(input);
      }
      deferredEvent = null;
      stack = new StackNode(-1, 0, e0, null);
      state = initialState;
      action = predict(initialState);
      bw = e0;
      bs = e0;
      es = e0;
      threads = new PriorityQueue<>();
      threads.offer(this);
      return threads;
    }

    public ParsingThread copy(ParsingThread other, int action)
    {
      this.action = action;
      accepted = other.accepted;
      target = other.target;
      bs = other.bs;
      es = other.es;
      bw = other.bw;
      eventHandler = other.eventHandler;
      deferredEvent = other.deferredEvent;
      id = ++maxId;
      threads = other.threads;
      state = other.state;
      stack = other.stack;
      b0 = other.b0;
      e0 = other.e0;
      l1 = other.l1;
      b1 = other.b1;
      e1 = other.e1;
      end = other.end;
      return this;
    }

    @Override
    public int compareTo(ParsingThread other)
    {
      if (accepted != other.accepted)
        return accepted ? 1 : -1;
      int comp = e0 - other.e0;
      return comp == 0 ? id - other.id : comp;
    }

    @Override
    public boolean equals(Object obj)
    {
      ParsingThread other = (ParsingThread) obj;
      if (accepted != other.accepted) return false;
      if (b1 != other.b1) return false;
      if (e1 != other.e1) return false;
      if (l1 != other.l1) return false;
      if (state != other.state) return false;
      if (action != other.action) return false;
      if (! stack.equals(other.stack)) return false;
      return true;
    }

    public int parse()
    {
      int nonterminalId = -1;
      for (;;)
      {
        int argument = action >> 12;
        int lookback = (action >> 3) & 511;
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
          symbols = stack.count(lookback);
          break;

        case 4: // SHIFT+REDUCE
          shift = state;
          reduce = argument;
          symbols = lookback + 1;
          break;

        case 5: // SHIFT+REDUCE+LOOKBACK
          shift = state;
          reduce = argument;
          symbols = stack.count(lookback) + 1;
          break;

        case 6: // ACCEPT
          accepted = true;
          action = 0;
          return ACCEPTED;

        case 7: // FORK
          threads.offer(new ParsingThread().copy(this, APPENDIX[argument]));
          action = APPENDIX[argument + 1];
          return PARSING;

        default: // ERROR
          return ERROR;
        }

        if (shift >= 0)
        {
          if (nonterminalId < 0)
          {
            if (eventHandler != null)
            {
              if (isUnambiguous())
              {
                eventHandler.terminal(TOKEN[l1], b1, e1);
              }
              else
              {
                deferredEvent = new TerminalEvent(deferredEvent, TOKEN[l1], b1, e1);
              }
            }
            es = e1;
            stack = new StackNode(state, lookback, b1, stack);
            consume(l1);
          }
          else
          {
            stack = new StackNode(state, lookback, bs, stack);
          }
          state = shift;
        }

        if (reduce < 0)
        {
          action = predict(state);
          return PARSING;
        }
        else
        {
          nonterminalId = reduce;
          if (symbols > 0)
          {
            for (int i = 1; i < symbols; i++)
            {
              stack = stack.link;
            }
            state = stack.state;
            bs = stack.pos;
            stack = stack.link;
          }
          else
          {
            bs = b1;
            es = b1;
          }
          if (nonterminalId == target && stack.link == null)
          {
            bs = bw;
            es = b1;
            bw = b1;
          }
          if (eventHandler != null)
          {
            if (isUnambiguous())
            {
              eventHandler.nonterminal(NONTERMINAL[nonterminalId], bs, es, symbols);
            }
            else
            {
              deferredEvent = new NonterminalEvent(deferredEvent, NONTERMINAL[nonterminalId], bs, es, symbols);
            }
          }
          action = goTo(nonterminalId, state);
        }
      }
    }

    public boolean isUnambiguous()
    {
      return threads.isEmpty();
    }

    public final void reset(int l, int b, int e)
    {
              b0 = b; e0 = b;
      l1 = l; b1 = b; e1 = e;
      end = e;
      maxId = 0;
      id = maxId;
    }

    private void consume(int t)
    {
      if (l1 == t)
      {
        b0 = b1; e0 = e1; l1 = 0;
      }
      else
      {
        error(b1, e1, 0, l1, t);
      }
    }

    private int error(int b, int e, int s, int l, int t)
    {
      throw new ParseException(b, e, s, l, t);
    }

    private int     b0, e0;
    private int l1, b1, e1;
    private int bw, bs, es;
    private BottomUpEventHandler eventHandler = null;

    private int begin = 0;
    private int end = 0;

    public int predict(int dpi)
    {
      int d = dpi;
      if (l1 == 0)
      {
        l1 = match(TOKENSET[d]);
        b1 = begin;
        e1 = end;
      }
      if (l1 < 0)
        return 0;
      int j10 = 48 * d + l1;
      int j11 = j10 >> 2;
      int action = CASEID[(j10 & 3) + CASEID[(j11 & 3) + CASEID[j11 >> 2]]];
      return action >> 1;
    }

    private int match(int tokenSetId)
    {
      begin = end;
      int current = end;
      int result = INITIAL[tokenSetId];

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
          int c1 = c0 >> 3;
          charclass = MAP1[(c0 & 7) + MAP1[(c1 & 15) + MAP1[c1 >> 4]]];
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

          int lo = 0, hi = 349;
          for (int m = 175; ; m = (hi + lo) >> 1)
          {
            if (MAP2[m] > c0) {hi = m - 1;}
            else if (MAP2[350 + m] < c0) {lo = m + 1;}
            else {charclass = MAP2[700 + m]; break;}
            if (lo > hi) {charclass = 0; break;}
          }
        }

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
        end = begin;
        return -1;
      }

      if (end > size) end = size;
      return (result & 63) - 1;
    }

  }

  private static int goTo(int nonterminal, int state)
  {
    int i0 = 40 * state + nonterminal;
    int i1 = i0 >> 2;
    return GOTO[(i0 & 3) + GOTO[(i1 & 3) + GOTO[i1 >> 2]]];
  }

  private static String[] getTokenSet(int tokenSetId)
  {
    java.util.ArrayList<String> expected = new java.util.ArrayList<>();
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 127;
    for (int i = 0; i < 45; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 76 + s - 1;
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

  private static final int[] MAP0 = new int[128];
  static
  {
    final String s1[] =
    {
      /*   0 */ "43, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3",
      /*  34 */ "4, 5, 3, 3, 3, 6, 7, 8, 9, 10, 11, 12, 13, 3, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 16, 3, 17",
      /*  62 */ "3, 18, 19, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21",
      /*  88 */ "21, 21, 21, 22, 3, 23, 24, 25, 3, 26, 26, 26, 26, 27, 26, 28, 28, 29, 28, 28, 30, 31, 32, 33, 28, 28",
      /* 114 */ "34, 35, 28, 28, 36, 28, 37, 28, 28, 38, 39, 40, 41, 3"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 128; ++i) {MAP0[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] MAP1 = new int[2338];
  static
  {
    final String s1[] =
    {
      /*    0 */ "432, 580, 1309, 1309, 1309, 463, 1245, 448, 1309, 493, 537, 512, 642, 1270, 688, 1201, 1339, 1351",
      /*   18 */ "553, 596, 612, 628, 658, 674, 714, 749, 765, 796, 568, 823, 839, 855, 871, 902, 1309, 1309, 698, 918",
      /*   38 */ "945, 961, 1308, 1309, 1309, 1309, 496, 982, 998, 1014, 527, 1030, 1488, 1460, 1054, 1070, 1092, 1108",
      /*   56 */ "1563, 1076, 1309, 886, 1309, 1309, 1380, 1124, 1140, 477, 1156, 1172, 1173, 1173, 1173, 1173, 1173",
      /*   73 */ "1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1189, 733",
      /*   90 */ "1217, 1233, 807, 1173, 1173, 1173, 1261, 1286, 1302, 1325, 1173, 1173, 1173, 1173, 1309, 1309, 1309",
      /*  107 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  124 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  141 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1038, 1309, 1309",
      /*  158 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  175 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  192 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  209 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  226 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  243 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  260 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  277 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  294 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  311 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 966, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  328 */ "1309, 1367, 1309, 1309, 1396, 1412, 728, 1428, 1451, 780, 1476, 1504, 929, 1520, 1536, 1435, 1309",
      /*  345 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  362 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  379 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  396 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  413 */ "1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309",
      /*  430 */ "1309, 1552, 1579, 1588, 1580, 1580, 1596, 1604, 1628, 1634, 1642, 1649, 1649, 1654, 1662, 1676, 1669",
      /*  447 */ "1684, 2246, 1733, 1843, 1843, 2004, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 2007, 1843",
      /*  464 */ "1843, 1843, 1843, 1843, 1843, 1843, 1843, 2092, 1843, 1849, 2174, 1846, 2186, 2174, 2174, 1843, 1846",
      /*  481 */ "2174, 2174, 2174, 2174, 2174, 2174, 2107, 2110, 2062, 2107, 2114, 2174, 1710, 1841, 1843, 1843, 1843",
      /*  498 */ "1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 2198, 1843, 1843, 1843, 2174, 2106",
      /*  515 */ "2107, 2107, 2107, 2107, 1748, 1720, 2174, 1843, 1843, 1843, 1848, 1848, 2174, 2064, 2107, 2113, 1843",
      /*  532 */ "1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 2174, 1842, 1843, 1843, 1843, 1844, 2243",
      /*  549 */ "1842, 1843, 1843, 1843, 2073, 1843, 1843, 1843, 1843, 1843, 1843, 1762, 2106, 2270, 1746, 1843, 1802",
      /*  566 */ "2107, 1842, 1842, 1843, 1843, 1843, 1843, 1843, 1928, 2112, 2144, 2108, 2107, 2113, 2174, 2174, 2174",
      /*  583 */ "2174, 2173, 2242, 1611, 2242, 1843, 1843, 1844, 1843, 1843, 1843, 1844, 1843, 1988, 2198, 1840, 1843",
      /*  600 */ "1843, 2005, 1820, 1835, 2129, 1857, 2174, 2218, 1802, 2107, 1849, 2174, 2230, 1866, 1840, 1843, 1843",
      /*  617 */ "2005, 1913, 2330, 2321, 1698, 2258, 1920, 2047, 2107, 1875, 2174, 2230, 2007, 2004, 1843, 1843, 2005",
      /*  634 */ "1916, 1835, 1712, 2270, 2244, 2174, 1802, 2107, 2174, 2174, 2107, 2112, 1843, 1843, 1843, 1843, 1843",
      /*  651 */ "2306, 2107, 2107, 2107, 1702, 2143, 1843, 1988, 2198, 1840, 1843, 1843, 2005, 1916, 1886, 2129, 2117",
      /*  668 */ "2116, 2218, 1802, 2107, 2243, 2174, 1860, 1904, 1863, 1909, 1768, 1904, 1843, 1849, 2114, 2117, 2244",
      /*  685 */ "2174, 2047, 2107, 2174, 2174, 2142, 1843, 1843, 1843, 2107, 2107, 2107, 1619, 1843, 1843, 1843, 1843",
      /*  702 */ "1843, 1843, 1843, 1843, 1843, 1863, 1844, 1863, 1843, 1843, 1843, 1843, 1951, 2001, 2005, 1843, 1843",
      /*  719 */ "2005, 2002, 2158, 2030, 2128, 2048, 1849, 1802, 2107, 2174, 2174, 2245, 1843, 1841, 1843, 1843, 1843",
      /*  736 */ "1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1846, 1812, 2090, 2174, 1951, 2001, 2005, 1843",
      /*  753 */ "1843, 2005, 2002, 1886, 2116, 2049, 2174, 2246, 1802, 2107, 1770, 2174, 1951, 2001, 2005, 1843, 1843",
      /*  770 */ "1843, 1843, 1936, 2129, 1857, 2174, 2174, 1802, 2107, 2174, 1841, 1843, 1843, 1843, 1843, 1843, 1847",
      /*  787 */ "2174, 2118, 2174, 2107, 2113, 2107, 2107, 1778, 2241, 1951, 1843, 1844, 1841, 1843, 1843, 2004, 2009",
      /*  804 */ "1844, 2257, 1964, 2174, 2174, 2174, 2174, 2174, 2245, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174",
      /*  821 */ "2174, 2174, 1825, 1827, 1952, 1842, 1732, 1728, 1928, 1944, 2008, 2109, 2107, 1620, 2174, 2174, 2174",
      /*  838 */ "2174, 2244, 2174, 2174, 2113, 2107, 2113, 1691, 2258, 1843, 1842, 1843, 1843, 1843, 1846, 2106, 2108",
      /*  855 */ "1749, 1781, 2107, 2106, 2107, 2107, 2107, 2110, 2116, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 1843",
      /*  872 */ "1843, 1843, 1843, 1843, 1961, 2105, 1896, 2107, 2113, 1845, 1974, 1766, 1950, 1931, 1843, 1843, 1843",
      /*  889 */ "1843, 1843, 1843, 1843, 1843, 2107, 2107, 2107, 2107, 2108, 2174, 2174, 2032, 1878, 1857, 2107, 1984",
      /*  906 */ "1843, 1843, 1843, 1843, 2007, 2247, 1843, 1843, 1843, 1843, 1843, 2003, 1843, 1863, 1843, 1843, 1843",
      /*  923 */ "1843, 1863, 1844, 1863, 1843, 1844, 1843, 1843, 1843, 1843, 1843, 1793, 1892, 2174, 2140, 2266, 2107",
      /*  940 */ "2113, 1843, 1843, 1844, 2242, 1843, 1843, 1863, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1961",
      /*  957 */ "2174, 2174, 2174, 2174, 1843, 1843, 2174, 2174, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843",
      /*  974 */ "1843, 1846, 2174, 2174, 2174, 2174, 2174, 2174, 1998, 1843, 1843, 1848, 1843, 1843, 1843, 1843, 1843",
      /*  991 */ "1843, 1843, 1843, 1843, 1848, 2174, 2174, 1843, 2001, 2017, 2174, 1843, 1843, 2017, 2174, 1843, 1843",
      /* 1008 */ "2027, 2174, 1843, 2001, 2320, 2174, 1843, 1843, 1843, 1843, 1843, 1843, 1976, 2109, 2116, 2106, 2130",
      /* 1025 */ "2041, 2107, 2113, 2174, 2174, 1843, 1843, 1843, 1843, 1843, 2294, 1843, 1843, 1843, 1843, 1843, 1843",
      /* 1042 */ "1843, 1843, 1845, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 1843, 1843, 2144, 2114, 1843",
      /* 1059 */ "1843, 1843, 1843, 1843, 1843, 2057, 2108, 1696, 2110, 2033, 2309, 2107, 2113, 2107, 2113, 2245, 2174",
      /* 1076 */ "2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2126, 2107, 2105, 2138, 2153, 2174, 2072",
      /* 1093 */ "1843, 1843, 1843, 1843, 1843, 2082, 1966, 2273, 1847, 2107, 2113, 2174, 2033, 2111, 2174, 2074, 1843",
      /* 1110 */ "1843, 1843, 1616, 1724, 2107, 1778, 1843, 1843, 1843, 1843, 2145, 2100, 2113, 2174, 1843, 1843, 1843",
      /* 1127 */ "1843, 1843, 1843, 2001, 2008, 2276, 1846, 2199, 1847, 1843, 1846, 2276, 1846, 2166, 2171, 2174, 2174",
      /* 1144 */ "2174, 2175, 2174, 2115, 2114, 2174, 2174, 2175, 2174, 2174, 2243, 2245, 2183, 1841, 2009, 2197, 2186",
      /* 1161 */ "1919, 1843, 2207, 1951, 1867, 2174, 2174, 2174, 2174, 2174, 2174, 1768, 2174, 2174, 2174, 2174, 2174",
      /* 1178 */ "2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 1843, 1843, 1843, 1843, 1843, 1844",
      /* 1195 */ "1843, 1843, 1843, 1843, 1843, 1844, 1843, 1843, 1843, 1843, 1780, 2107, 2295, 2174, 2107, 1778, 1843",
      /* 1212 */ "1843, 1843, 2306, 2088, 2242, 1843, 1843, 1843, 1843, 2007, 2247, 1843, 1843, 1843, 1843, 1843, 1843",
      /* 1229 */ "1843, 2245, 2174, 2115, 1843, 1843, 1844, 2174, 1844, 1844, 1844, 1844, 1844, 1844, 1844, 1844, 2107",
      /* 1246 */ "2107, 2107, 2107, 2107, 2107, 2107, 2107, 2107, 2107, 2107, 2107, 2107, 2107, 2001, 2209, 2217, 2174",
      /* 1263 */ "2174, 2174, 2174, 1806, 2197, 1768, 1842, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843",
      /* 1280 */ "1741, 2309, 1757, 2086, 2107, 1934, 1843, 1843, 1844, 2230, 1842, 1843, 1843, 1843, 1843, 1843, 1843",
      /* 1297 */ "1843, 1843, 1843, 1843, 2003, 1951, 1843, 1843, 1843, 1843, 1845, 1842, 1843, 1843, 1843, 1843, 1843",
      /* 1314 */ "1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1844, 2174, 2174, 1843, 1843",
      /* 1331 */ "1843, 1848, 2174, 2174, 2174, 2174, 2174, 2174, 1843, 1843, 1780, 1791, 1789, 2308, 2174, 2174, 1843",
      /* 1348 */ "1843, 1843, 2018, 2174, 2174, 2174, 2174, 2005, 1846, 2174, 2174, 2174, 2174, 2174, 2174, 2032, 2107",
      /* 1365 */ "2107, 2108, 1843, 1846, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 1843, 1843, 1843, 1843, 1843",
      /* 1382 */ "1845, 1845, 1843, 1843, 1843, 1843, 1845, 1845, 1843, 2189, 1843, 1843, 1843, 1845, 1843, 1846, 1843",
      /* 1399 */ "1843, 2107, 2090, 2174, 2174, 1843, 1843, 1843, 1843, 1843, 2144, 2032, 1794, 1843, 1843, 1843, 2115",
      /* 1416 */ "1843, 1843, 1843, 1843, 1843, 1843, 1843, 1843, 1845, 2174, 2113, 2174, 1843, 2208, 1847, 2174, 1843",
      /* 1433 */ "1848, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 1843, 1843, 1843, 1843, 2329, 2270, 2107",
      /* 1450 */ "2113, 2289, 2140, 1843, 1843, 2226, 2174, 2174, 2174, 1843, 1843, 1843, 1843, 1843, 1843, 1847, 2174",
      /* 1467 */ "2174, 1842, 2174, 2107, 2113, 2174, 2174, 2174, 2174, 2107, 1778, 1843, 1843, 1780, 2109, 1843, 1843",
      /* 1484 */ "2144, 2107, 2113, 2174, 1843, 1843, 1843, 1846, 2019, 2114, 2257, 1700, 2047, 2107, 1843, 1843, 1843",
      /* 1501 */ "1845, 1846, 2174, 2073, 1843, 1843, 1843, 1843, 1843, 1877, 2255, 2174, 2245, 2107, 2113, 2174, 2174",
      /* 1518 */ "2174, 2174, 1843, 1843, 1843, 1843, 1843, 1843, 2284, 2303, 2294, 2174, 2174, 1990, 1843, 2318, 2233",
      /* 1535 */ "2174, 2006, 2006, 2006, 2174, 1844, 1844, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174, 2174",
      /* 1552 */ "1843, 1843, 1843, 1843, 1847, 2174, 1843, 1843, 1844, 1953, 1843, 1843, 1843, 1843, 1843, 1847, 2032",
      /* 1569 */ "2310, 2174, 2107, 1810, 2107, 1778, 1843, 1843, 1843, 1845, 43, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0",
      /* 1592 */ "0, 2, 0, 0, 1, 3, 4, 5, 3, 3, 3, 6, 7, 8, 9, 10, 11, 12, 13, 3, 3, 3, 3, 3, 25, 3, 42, 42, 42, 42, 3",
      /* 1623 */ "3, 25, 25, 25, 25, 14, 14, 14, 14, 14, 14, 14, 14, 15, 16, 3, 17, 3, 18, 19, 20, 20, 20, 20, 20, 20",
      /* 1649 */ "21, 21, 21, 21, 21, 21, 21, 21, 22, 3, 23, 24, 25, 3, 26, 26, 26, 26, 27, 26, 28, 28, 34, 35, 28, 28",
      /* 1675 */ "36, 28, 29, 28, 28, 30, 31, 32, 33, 37, 28, 28, 38, 39, 40, 41, 3, 3, 3, 3, 3, 42, 3, 42, 3, 3, 42",
      /* 1702 */ "42, 42, 3, 3, 3, 3, 25, 25, 25, 25, 3, 42, 42, 42, 42, 42, 3, 42, 3, 42, 42, 3, 42, 42, 3, 42, 3, 3",
      /* 1730 */ "25, 25, 3, 25, 25, 25, 3, 25, 3, 25, 25, 25, 25, 25, 25, 3, 25, 42, 42, 42, 42, 42, 42, 42, 3, 42",
      /* 1756 */ "42, 42, 42, 42, 42, 42, 25, 25, 42, 3, 42, 25, 3, 3, 3, 25, 25, 3, 3, 3, 3, 3, 42, 42, 25, 25, 25",
      /* 1783 */ "25, 25, 25, 42, 42, 42, 42, 42, 42, 42, 25, 42, 42, 42, 42, 42, 42, 3, 25, 25, 25, 42, 42, 3, 3, 42",
      /* 1809 */ "42, 42, 42, 3, 3, 3, 25, 25, 25, 25, 42, 25, 3, 25, 3, 3, 3, 25, 25, 3, 25, 3, 3, 25, 3, 3, 25, 25",
      /* 1837 */ "3, 3, 42, 25, 3, 3, 25, 25, 25, 25, 25, 25, 25, 25, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 42, 25, 3, 25",
      /* 1866 */ "25, 25, 25, 3, 3, 3, 3, 25, 3, 42, 42, 25, 25, 25, 42, 3, 3, 42, 42, 3, 25, 25, 3, 3, 42, 25, 3, 42",
      /* 1894 */ "42, 3, 3, 42, 42, 3, 3, 42, 42, 25, 25, 25, 25, 3, 3, 3, 25, 25, 3, 25, 3, 25, 25, 3, 25, 25, 3, 25",
      /* 1922 */ "25, 25, 25, 3, 25, 3, 25, 42, 25, 25, 42, 42, 42, 42, 25, 25, 25, 3, 3, 25, 3, 3, 42, 42, 3, 42, 42",
      /* 1949 */ "25, 3, 3, 3, 3, 3, 3, 25, 25, 25, 25, 25, 25, 25, 25, 3, 3, 42, 42, 42, 3, 42, 3, 3, 3, 42, 42, 25",
      /* 1977 */ "25, 25, 25, 42, 42, 3, 42, 42, 42, 3, 3, 3, 42, 3, 3, 3, 25, 25, 25, 3, 3, 1, 25, 25, 25, 25, 25, 25",
      /* 2005 */ "25, 3, 25, 25, 25, 25, 25, 25, 3, 25, 3, 3, 25, 25, 42, 42, 42, 3, 3, 3, 3, 42, 25, 25, 42, 42, 3, 3",
      /* 2033 */ "3, 3, 3, 42, 42, 42, 42, 42, 3, 3, 3, 3, 25, 42, 3, 3, 3, 3, 3, 3, 42, 42, 3, 3, 25, 25, 25, 25, 25",
      /* 2062 */ "3, 42, 3, 3, 3, 42, 42, 42, 1, 3, 42, 42, 42, 42, 3, 25, 25, 25, 25, 25, 25, 25, 25, 25, 42, 3, 42",
      /* 2089 */ "42, 42, 42, 25, 25, 3, 3, 3, 3, 25, 25, 42, 42, 3, 3, 3, 42, 3, 42, 42, 42, 42, 42, 42, 42, 42, 3, 3",
      /* 2117 */ "3, 3, 3, 3, 3, 42, 3, 3, 3, 42, 42, 42, 3, 42, 42, 42, 42, 3, 3, 3, 25, 42, 25, 25, 25, 25, 42, 25",
      /* 2145 */ "25, 25, 25, 25, 25, 25, 42, 3, 25, 25, 3, 3, 42, 25, 25, 3, 3, 3, 25, 42, 42, 1, 1, 1, 1, 1, 1, 1, 1",
      /* 2174 */ "3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 3, 25, 3, 3, 3, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 25, 25, 25, 25, 3",
      /* 2204 */ "3, 25, 25, 25, 25, 3, 3, 25, 25, 25, 25, 3, 3, 1, 3, 3, 3, 3, 25, 25, 3, 25, 25, 25, 25, 3, 3, 42",
      /* 2232 */ "42, 3, 3, 25, 25, 25, 3, 42, 3, 3, 3, 3, 25, 3, 3, 3, 3, 3, 3, 3, 25, 3, 3, 42, 42, 3, 3, 42, 3, 3",
      /* 2262 */ "3, 3, 3, 3, 25, 25, 25, 25, 42, 3, 3, 3, 3, 42, 3, 3, 25, 25, 25, 3, 25, 25, 42, 25, 42, 42, 42, 25",
      /* 2290 */ "25, 42, 25, 25, 25, 42, 25, 3, 3, 3, 3, 3, 3, 42, 25, 25, 25, 25, 25, 42, 42, 42, 42, 42, 3, 3, 42",
      /* 2317 */ "42, 25, 25, 25, 3, 42, 42, 3, 3, 3, 3, 42, 25, 25, 25, 3, 3, 42, 3, 3, 3"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 2338; ++i) {MAP1[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] MAP2 = new int[1050];
  static
  {
    final String s1[] =
    {
      /*    0 */ "57344, 63744, 64110, 64112, 64218, 64256, 64263, 64275, 64280, 64285, 64286, 64287, 64297, 64298",
      /*   14 */ "64311, 64312, 64317, 64318, 64319, 64320, 64322, 64323, 64325, 64326, 64434, 64467, 64830, 64848",
      /*   28 */ "64912, 64914, 64968, 65008, 65020, 65024, 65040, 65056, 65063, 65136, 65141, 65142, 65277, 65296",
      /*   42 */ "65306, 65313, 65339, 65345, 65371, 65382, 65471, 65474, 65480, 65482, 65488, 65490, 65496, 65498",
      /*   56 */ "65501, 65536, 65548, 65549, 65575, 65576, 65595, 65596, 65598, 65599, 65614, 65616, 65630, 65664",
      /*   70 */ "65787, 66045, 66046, 66176, 66205, 66208, 66257, 66304, 66335, 66352, 66369, 66370, 66378, 66432",
      /*   84 */ "66462, 66464, 66500, 66504, 66512, 66560, 66718, 66720, 66730, 67584, 67590, 67592, 67593, 67594",
      /*   98 */ "67638, 67639, 67641, 67644, 67645, 67647, 67670, 67840, 67862, 67872, 67898, 67968, 68024, 68030",
      /*  112 */ "68032, 68096, 68097, 68100, 68101, 68103, 68108, 68112, 68116, 68117, 68120, 68121, 68148, 68152",
      /*  126 */ "68155, 68159, 68160, 68192, 68221, 68352, 68406, 68416, 68438, 68448, 68467, 68608, 68681, 69633",
      /*  140 */ "69634, 69635, 69688, 69703, 69734, 69744, 69760, 69762, 69763, 69808, 69811, 69815, 69817, 69819",
      /*  154 */ "69840, 69865, 69872, 69882, 69888, 69891, 69927, 69932, 69933, 69941, 69942, 69952, 70016, 70018",
      /*  168 */ "70019, 70067, 70070, 70079, 70081, 70085, 70096, 70106, 71296, 71339, 71340, 71341, 71342, 71344",
      /*  182 */ "71350, 71351, 71352, 71360, 71370, 73728, 74607, 77824, 78895, 92160, 92729, 93952, 94021, 94032",
      /*  196 */ "94033, 94095, 94099, 94112, 110592, 110594, 119143, 119146, 119163, 119171, 119173, 119180, 119210",
      /*  209 */ "119214, 119362, 119365, 119808, 119893, 119894, 119965, 119966, 119968, 119970, 119971, 119973",
      /*  221 */ "119975, 119977, 119981, 119982, 119994, 119995, 119996, 119997, 120004, 120005, 120070, 120071",
      /*  233 */ "120075, 120077, 120085, 120086, 120093, 120094, 120122, 120123, 120127, 120128, 120133, 120134",
      /*  245 */ "120135, 120138, 120145, 120146, 120486, 120488, 120513, 120514, 120539, 120540, 120571, 120572",
      /*  257 */ "120597, 120598, 120629, 120630, 120655, 120656, 120687, 120688, 120713, 120714, 120745, 120746",
      /*  269 */ "120771, 120772, 120780, 120782, 120832, 126464, 126468, 126469, 126496, 126497, 126499, 126500",
      /*  281 */ "126501, 126503, 126504, 126505, 126515, 126516, 126520, 126521, 126522, 126523, 126524, 126530",
      /*  293 */ "126531, 126535, 126536, 126537, 126538, 126539, 126540, 126541, 126544, 126545, 126547, 126548",
      /*  305 */ "126549, 126551, 126552, 126553, 126554, 126555, 126556, 126557, 126558, 126559, 126560, 126561",
      /*  317 */ "126563, 126564, 126565, 126567, 126571, 126572, 126579, 126580, 126584, 126585, 126589, 126590",
      /*  329 */ "126591, 126592, 126602, 126603, 126620, 126625, 126628, 126629, 126634, 126635, 126652, 131072",
      /*  341 */ "173783, 173824, 177973, 177984, 178206, 194560, 195102, 917760, 918000, 63743, 64109, 64111, 64217",
      /*  354 */ "64255, 64262, 64274, 64279, 64284, 64285, 64286, 64296, 64297, 64310, 64311, 64316, 64317, 64318",
      /*  368 */ "64319, 64321, 64322, 64324, 64325, 64433, 64466, 64829, 64847, 64911, 64913, 64967, 65007, 65019",
      /*  382 */ "65023, 65039, 65055, 65062, 65135, 65140, 65141, 65276, 65295, 65305, 65312, 65338, 65344, 65370",
      /*  396 */ "65381, 65470, 65473, 65479, 65481, 65487, 65489, 65495, 65497, 65500, 65533, 65547, 65548, 65574",
      /*  410 */ "65575, 65594, 65595, 65597, 65598, 65613, 65615, 65629, 65663, 65786, 66044, 66045, 66175, 66204",
      /*  424 */ "66207, 66256, 66303, 66334, 66351, 66368, 66369, 66377, 66431, 66461, 66463, 66499, 66503, 66511",
      /*  438 */ "66559, 66717, 66719, 66729, 67583, 67589, 67591, 67592, 67593, 67637, 67638, 67640, 67643, 67644",
      /*  452 */ "67646, 67669, 67839, 67861, 67871, 67897, 67967, 68023, 68029, 68031, 68095, 68096, 68099, 68100",
      /*  466 */ "68102, 68107, 68111, 68115, 68116, 68119, 68120, 68147, 68151, 68154, 68158, 68159, 68191, 68220",
      /*  480 */ "68351, 68405, 68415, 68437, 68447, 68466, 68607, 68680, 69632, 69633, 69634, 69687, 69702, 69733",
      /*  494 */ "69743, 69759, 69761, 69762, 69807, 69810, 69814, 69816, 69818, 69839, 69864, 69871, 69881, 69887",
      /*  508 */ "69890, 69926, 69931, 69932, 69940, 69941, 69951, 70015, 70017, 70018, 70066, 70069, 70078, 70080",
      /*  522 */ "70084, 70095, 70105, 71295, 71338, 71339, 71340, 71341, 71343, 71349, 71350, 71351, 71359, 71369",
      /*  536 */ "73727, 74606, 77823, 78894, 92159, 92728, 93951, 94020, 94031, 94032, 94094, 94098, 94111, 110591",
      /*  550 */ "110593, 119142, 119145, 119162, 119170, 119172, 119179, 119209, 119213, 119361, 119364, 119807",
      /*  562 */ "119892, 119893, 119964, 119965, 119967, 119969, 119970, 119972, 119974, 119976, 119980, 119981",
      /*  574 */ "119993, 119994, 119995, 119996, 120003, 120004, 120069, 120070, 120074, 120076, 120084, 120085",
      /*  586 */ "120092, 120093, 120121, 120122, 120126, 120127, 120132, 120133, 120134, 120137, 120144, 120145",
      /*  598 */ "120485, 120487, 120512, 120513, 120538, 120539, 120570, 120571, 120596, 120597, 120628, 120629",
      /*  610 */ "120654, 120655, 120686, 120687, 120712, 120713, 120744, 120745, 120770, 120771, 120779, 120781",
      /*  622 */ "120831, 126463, 126467, 126468, 126495, 126496, 126498, 126499, 126500, 126502, 126503, 126504",
      /*  634 */ "126514, 126515, 126519, 126520, 126521, 126522, 126523, 126529, 126530, 126534, 126535, 126536",
      /*  646 */ "126537, 126538, 126539, 126540, 126543, 126544, 126546, 126547, 126548, 126550, 126551, 126552",
      /*  658 */ "126553, 126554, 126555, 126556, 126557, 126558, 126559, 126560, 126562, 126563, 126564, 126566",
      /*  670 */ "126570, 126571, 126578, 126579, 126583, 126584, 126588, 126589, 126590, 126591, 126601, 126602",
      /*  682 */ "126619, 126624, 126627, 126628, 126633, 126634, 126651, 131071, 173782, 173823, 177972, 177983",
      /*  694 */ "178205, 194559, 195101, 917759, 917999, 1114111, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 42, 25, 3, 25, 3",
      /*  715 */ "25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 42, 3, 42, 3, 25, 3, 25, 3, 42, 3, 25",
      /*  744 */ "3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 42, 3",
      /*  773 */ "25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 42, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25",
      /*  802 */ "3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 42, 3, 42, 3, 42, 25, 3, 25, 3, 25, 3, 42, 3, 42, 3, 25, 3",
      /*  831 */ "25, 3, 25, 3, 25, 3, 25, 3, 42, 3, 25, 42, 3, 42, 3, 42, 3, 25, 3, 42, 3, 42, 3, 25, 3, 42, 3, 42",
      /*  859 */ "25, 42, 3, 42, 3, 42, 3, 42, 3, 25, 3, 42, 3, 25, 3, 42, 3, 25, 42, 3, 42, 3, 42, 3, 42, 3, 42, 3",
      /*  887 */ "25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 42, 25, 3, 25, 3, 42, 3, 42, 3, 42, 3, 42, 3, 42, 3, 25, 3, 25, 3",
      /*  916 */ "25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25",
      /*  945 */ "3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 42, 3",
      /*  974 */ "25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25",
      /* 1003 */ "3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3",
      /* 1032 */ "25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 25, 3, 42, 3"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 1050; ++i) {MAP2[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] INITIAL = new int[71];
  static
  {
    final String s1[] =
    {
      /*  0 */ "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28",
      /* 28 */ "29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54",
      /* 54 */ "55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 71; ++i) {INITIAL[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] TRANSITION = new int[1468];
  static
  {
    final String s1[] =
    {
      /*    0 */ "1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102",
      /*   17 */ "1104, 1097, 704, 732, 729, 743, 735, 735, 1080, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1078",
      /*   35 */ "704, 732, 729, 743, 735, 735, 1080, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 1314, 1104",
      /*   53 */ "1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 751, 1104, 1282, 901, 716, 721",
      /*   70 */ "756, 754, 764, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 831, 999, 785, 807, 805",
      /*   88 */ "815, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 771, 1104, 1446, 927, 792, 797, 777, 775, 839, 1103",
      /*  106 */ "1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 1314, 1104, 1104, 1104, 1105, 860, 1104, 1104",
      /*  123 */ "1104, 1104, 1104, 1104, 1104, 1295, 1104, 1227, 1314, 879, 1104, 889, 884, 884, 1104, 1104, 1104",
      /*  140 */ "1104, 1104, 1104, 1104, 1102, 1104, 1227, 1314, 1104, 1104, 1104, 822, 828, 898, 1104, 1104, 1104",
      /*  157 */ "1104, 1104, 1104, 1102, 1104, 1227, 1314, 1104, 1104, 1104, 846, 852, 867, 1104, 1104, 1104, 1104",
      /*  174 */ "1104, 1104, 1102, 1104, 1227, 1314, 1155, 915, 914, 909, 924, 1104, 1104, 1104, 1104, 1104, 1104",
      /*  191 */ "1104, 1274, 1104, 1227, 945, 1104, 1278, 940, 935, 953, 1104, 1104, 1104, 1104, 1104, 1104, 1104",
      /*  208 */ "1102, 987, 1227, 1345, 986, 987, 983, 978, 978, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 996, 1104",
      /*  226 */ "1227, 1314, 1104, 1104, 711, 1007, 1007, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227",
      /*  243 */ "1018, 1360, 1104, 1361, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 1314",
      /*  260 */ "1042, 1049, 1060, 1056, 1056, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 1071, 1033",
      /*  277 */ "1104, 1034, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 1314, 1104, 1104",
      /*  294 */ "1104, 960, 966, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 1314, 1104, 1356, 1352",
      /*  311 */ "890, 1090, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 996, 1104, 1116, 1314, 1138, 1131, 1216, 1113",
      /*  328 */ "1124, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1116, 1314, 1138, 1131, 1216, 1113, 1124",
      /*  345 */ "1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1153, 1227, 1166, 1104, 1201, 1200, 1163, 1174, 1104",
      /*  362 */ "1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 1314, 1197, 1189, 1194, 1104, 1104, 1104, 1104",
      /*  379 */ "1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 1314, 1104, 1029, 1025, 916, 1209, 1104, 1104, 1104",
      /*  396 */ "1104, 1104, 1104, 1104, 1102, 1104, 1116, 1314, 1326, 1326, 1181, 1113, 1124, 1104, 1104, 1104, 1104",
      /*  413 */ "1104, 1104, 1104, 996, 1104, 1116, 1314, 1326, 1145, 1181, 1113, 1124, 1104, 1104, 1104, 1104, 1104",
      /*  430 */ "1104, 1104, 996, 1224, 1116, 1314, 1326, 1145, 1181, 1113, 1124, 1104, 1104, 1104, 1104, 1104, 1104",
      /*  447 */ "1104, 1102, 1104, 1116, 1314, 1326, 1145, 1181, 1113, 1124, 1104, 1104, 1104, 1104, 1104, 1104, 1104",
      /*  464 */ "1102, 1376, 1246, 1314, 1376, 1395, 1235, 1243, 1254, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102",
      /*  481 */ "1380, 1116, 1314, 1326, 1145, 1181, 1113, 1124, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 871",
      /*  498 */ "1116, 1314, 1326, 1145, 1181, 1113, 1124, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1010, 1116",
      /*  515 */ "1314, 1326, 1145, 1181, 1113, 1124, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 988, 1116, 1314",
      /*  532 */ "1326, 1145, 1181, 1113, 1124, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1269, 1314, 1326",
      /*  549 */ "1145, 1181, 1113, 1124, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1290, 1314, 1326, 1145",
      /*  566 */ "1181, 1113, 1124, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1303, 1330, 1326, 1145, 1181",
      /*  583 */ "1113, 1124, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1116, 1314, 1326, 1145, 1261, 1322",
      /*  600 */ "1338, 1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1369, 1388, 1406, 1403, 1417, 1409, 1409",
      /*  617 */ "1104, 1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 1314, 1425, 1432, 1443, 1439, 1439, 1104",
      /*  634 */ "1104, 1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 1063, 1104, 1104, 1104, 1104, 1104, 1104, 1104",
      /*  651 */ "1104, 1104, 1104, 1104, 1104, 1102, 1104, 1227, 1314, 1104, 970, 969, 1454, 1460, 1104, 1104, 1104",
      /*  668 */ "1104, 1104, 1104, 1104, 1102, 1104, 1227, 1314, 1104, 1104, 711, 1007, 1007, 1104, 1104, 1104, 1104",
      /*  685 */ "1104, 1104, 1104, 1310, 1104, 1104, 1312, 1104, 1104, 1082, 1104, 1104, 1104, 1104, 1104, 1104, 1104",
      /*  702 */ "1104, 1104, 0, 3402, 3402, 3402, 3402, 3402, 256, 0, 0, 0, 0, 3584, 0, 0, 0, 0, 3968, 0, 3968, 0, 0",
      /*  725 */ "3968, 3968, 3968, 0, 0, 3402, 3402, 0, 0, 0, 3402, 3402, 3402, 0, 0, 3402, 3402, 0, 3402, 3402, 3402",
      /*  746 */ "3402, 3402, 3402, 3402, 3402, 72, 512, 0, 0, 3968, 0, 0, 0, 0, 0, 3968, 3968, 0, 0, 3968, 0, 3968",
      /*  768 */ "3968, 3968, 3968, 384, 73, 0, 0, 0, 4096, 0, 0, 0, 0, 0, 4096, 4096, 0, 0, 896, 0, 0, 896, 896, 896",
      /*  792 */ "0, 0, 0, 0, 4096, 0, 4096, 0, 0, 4096, 4096, 4096, 0, 0, 896, 0, 0, 0, 0, 0, 896, 896, 0, 0, 896, 0",
      /*  818 */ "896, 896, 896, 896, 0, 0, 0, 1227, 1227, 1227, 1227, 0, 1227, 0, 0, 0, 0, 0, 0, 256, 896, 0, 4096, 0",
      /*  842 */ "4096, 4096, 4096, 4096, 0, 0, 0, 1484, 1484, 1484, 1484, 1408, 1484, 1408, 1408, 1408, 1408, 0, 0",
      /*  861 */ "1024, 0, 1024, 1024, 1024, 1024, 0, 0, 0, 1536, 0, 0, 0, 0, 0, 2432, 0, 0, 4224, 0, 0, 0, 0, 0, 0",
      /*  886 */ "4224, 4224, 0, 4224, 0, 0, 0, 0, 0, 0, 0, 1792, 0, 0, 1280, 0, 0, 0, 0, 0, 0, 256, 3968, 4352, 0",
      /*  911 */ "4352, 4352, 4352, 4352, 4352, 0, 0, 0, 0, 0, 0, 0, 2048, 4352, 0, 4352, 0, 0, 0, 0, 0, 0, 256, 4096",
      /*  935 */ "3584, 0, 3584, 0, 0, 0, 0, 4480, 4480, 3584, 0, 0, 4480, 0, 0, 0, 256, 0, 3584, 4480, 3584, 4480",
      /*  957 */ "4480, 4480, 4480, 0, 0, 0, 1664, 1664, 1664, 1664, 0, 1664, 0, 0, 0, 0, 0, 0, 3200, 0, 0, 4608, 0",
      /*  980 */ "4608, 0, 4608, 0, 4608, 0, 0, 4608, 0, 0, 0, 0, 0, 0, 0, 2688, 384, 512, 643, 0, 0, 0, 0, 0, 0, 896",
      /* 1006 */ "0, 3584, 0, 3584, 0, 0, 0, 0, 0, 0, 2560, 0, 4736, 0, 0, 0, 0, 0, 256, 0, 0, 0, 2048, 0, 0, 0, 2048",
      /* 1033 */ "0, 0, 0, 0, 0, 4992, 0, 0, 0, 4864, 4864, 4864, 0, 0, 0, 0, 4864, 0, 4864, 0, 0, 0, 0, 4864, 0, 4864",
      /* 1059 */ "4864, 4864, 4864, 4864, 0, 0, 0, 0, 0, 0, 3072, 0, 4992, 0, 0, 0, 0, 0, 256, 0, 0, 0, 3402, 0, 0, 0",
      /* 1085 */ "0, 0, 0, 0, 3840, 0, 1792, 0, 1792, 1792, 1792, 1792, 0, 0, 0, 3402, 0, 384, 512, 0, 0, 0, 0, 0, 0",
      /* 1110 */ "0, 0, 1024, 3584, 3456, 3584, 0, 0, 0, 0, 3456, 384, 512, 0, 3584, 3456, 3584, 3456, 3456, 3456",
      /* 1130 */ "3456, 0, 0, 0, 3456, 768, 0, 768, 0, 0, 0, 3456, 0, 0, 768, 0, 0, 0, 3456, 0, 0, 0, 3712, 0, 5120, 0",
      /* 1156 */ "0, 0, 0, 0, 0, 0, 4352, 0, 5120, 0, 0, 0, 0, 0, 5120, 0, 256, 0, 0, 5120, 0, 5120, 5120, 5120, 5120",
      /* 1181 */ "0, 0, 0, 3456, 3584, 0, 0, 3456, 0, 0, 1920, 0, 1920, 0, 0, 1920, 0, 0, 1920, 0, 0, 0, 0, 0, 0, 5120",
      /* 1207 */ "0, 0, 0, 2048, 0, 2048, 2048, 2048, 2048, 0, 0, 0, 3456, 3584, 768, 0, 3456, 0, 0, 2176, 0, 0, 0, 0",
      /* 1231 */ "0, 384, 512, 0, 0, 0, 0, 5248, 3584, 0, 0, 5248, 3584, 5248, 3584, 0, 0, 0, 0, 5248, 384, 512, 0",
      /* 1254 */ "3584, 5248, 3584, 5248, 5248, 5248, 5248, 0, 0, 0, 3456, 5504, 0, 0, 3456, 2816, 0, 0, 0, 3456, 384",
      /* 1275 */ "512, 0, 0, 0, 0, 0, 4480, 0, 0, 0, 0, 0, 4040, 512, 3968, 0, 2944, 0, 0, 3456, 384, 512, 0, 0, 0, 0",
      /* 1301 */ "4224, 0, 0, 0, 5376, 0, 3456, 384, 512, 0, 0, 0, 3840, 0, 0, 0, 0, 0, 0, 256, 0, 5504, 3456, 5504, 0",
      /* 1326 */ "0, 0, 0, 3456, 0, 0, 0, 0, 0, 5376, 256, 0, 5504, 3456, 5504, 3456, 3456, 3456, 3456, 0, 0, 0, 4608",
      /* 1349 */ "0, 0, 256, 0, 0, 0, 1792, 0, 0, 0, 1792, 0, 0, 0, 0, 0, 4736, 0, 0, 0, 0, 0, 0, 5632, 0, 384, 512, 0",
      /* 1377 */ "0, 0, 5248, 0, 0, 0, 0, 2304, 0, 0, 0, 0, 5632, 5632, 5632, 5632, 5632, 5632, 0, 0, 0, 5248, 0, 0, 0",
      /* 1402 */ "3712, 0, 5632, 5632, 0, 0, 0, 5632, 5632, 5632, 0, 0, 5632, 5632, 0, 5632, 5632, 5632, 5632, 5632",
      /* 1422 */ "5632, 5632, 5632, 5760, 5760, 5760, 0, 0, 0, 0, 5760, 0, 5760, 0, 0, 0, 0, 5760, 0, 5760, 5760, 5760",
      /* 1444 */ "5760, 5760, 0, 0, 0, 0, 0, 384, 4169, 4096, 0, 3200, 0, 0, 0, 0, 0, 3200, 0, 3200, 3200, 3200, 3200",
      /* 1467 */ "0"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 1468; ++i) {TRANSITION[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] EXPECTED = new int[152];
  static
  {
    final String s1[] =
    {
      /*   0 */ "4, 8, 16, 536870912, 1073741824, -2147483648, 0, 0, 0, 0, 65536, 0, 131072, 262144, 524288, 1048576",
      /*  16 */ "2097152, 4194304, 0, 33554432, 67108864, 1073741828, -2147483640, -1073741824, 0, 570425344, 33554432",
      /*  27 */ "33554432, 33554432, 33554432, 8388610, -1073741760, 0, 0, 16384, 100663296, -1040187392, 33554432",
      /*  38 */ "-1073741728, 0, 0, -1040187328, 33570816, 67149824, -1073725344, -1056964544, -1040187296, 302006272",
      /*  48 */ "33554432, 33554432, 33570816, 100704256, 167772160, -1040170912, -1023410112, 637575168, 167772160",
      /*  57 */ "-956301248, 167772160, 7936, 7936, 33562368, 33562368, -989813568, 167780096, -956259136, 167780096",
      /*  67 */ "-989813568, -989813568, -956259136, -956259136, 4, 8, 33554432, 512, 2048, 0, 0, 0, 0, 0, 0, 1, 4, 8",
      /*  85 */ "128, 0, 256, 0, 0, 0, 0, 0, 0, 512, 2048, 256, 0, 0, 0, 80, 2048, 2052, 2056, 2176, 2560, 2048, 0",
      /* 108 */ "4129, 4136, 4128, 2304, 2048, 2128, 0, 4131, 4138, 2048, 6176, 260, 0, 128, 2048, 6176, 6179, 6186",
      /* 126 */ "6180, 2308, 3160, 2048, 2176, 2308, 7210, 2432, 7211, 4131, 4138, 6179, 6186, 388, 7210, 2436, 7211",
      /* 143 */ "4517, 4524, 6565, 6572, 0, 0, 0, 0, 0"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 152; ++i) {EXPECTED[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] CASEID = new int[2040];
  static
  {
    final String s1[] =
    {
      /*    0 */ "889, 1141, 747, 816, 977, 760, 889, 1141, 747, 769, 763, 1217, 816, 977, 775, 889, 1141, 747, 770",
      /*   19 */ "765, 1251, 770, 792, 1147, 770, 1117, 934, 770, 871, 785, 796, 763, 1217, 816, 977, 775, 797, 895",
      /*   38 */ "801, 810, 763, 1217, 770, 977, 993, 770, 792, 1147, 815, 763, 1217, 770, 1117, 934, 820, 763, 1217",
      /*   57 */ "778, 1005, 825, 1374, 1383, 829, 778, 1005, 825, 833, 763, 1217, 770, 971, 1307, 770, 792, 1147, 999",
      /*   76 */ "939, 781, 956, 871, 838, 790, 806, 750, 999, 939, 781, 778, 1039, 842, 790, 1045, 1333, 770, 856",
      /*   95 */ "753, 788, 1075, 1339, 770, 971, 1165, 846, 763, 1217, 770, 977, 993, 778, 1005, 825, 778, 1108, 851",
      /*  114 */ "770, 1123, 860, 778, 1108, 851, 770, 1123, 860, 778, 1108, 851, 770, 1123, 860, 999, 939, 781, 864",
      /*  133 */ "763, 1217, 1115, 1129, 869, 790, 806, 750, 875, 763, 1217, 880, 1135, 884, 778, 1039, 842, 888, 763",
      /*  152 */ "1217, 893, 922, 770, 790, 1045, 1333, 899, 763, 1217, 999, 939, 781, 770, 770, 869, 770, 856, 753",
      /*  171 */ "904, 763, 1217, 1012, 982, 770, 788, 1075, 1339, 909, 763, 1217, 1374, 1383, 829, 1374, 1383, 914",
      /*  189 */ "778, 1108, 851, 921, 763, 1217, 770, 1123, 860, 926, 763, 1217, 917, 1171, 931, 917, 1171, 931, 770",
      /*  208 */ "792, 1147, 999, 1177, 943, 956, 871, 947, 790, 806, 750, 999, 1177, 943, 778, 1039, 842, 790, 1045",
      /*  227 */ "1333, 770, 856, 753, 788, 1075, 1339, 999, 939, 781, 771, 1183, 756, 770, 1193, 951, 999, 939, 781",
      /*  246 */ "770, 792, 1147, 770, 1123, 860, 770, 871, 838, 790, 806, 750, 770, 1123, 860, 778, 1039, 842, 790",
      /*  265 */ "1045, 1333, 770, 856, 753, 788, 1075, 1339, 770, 977, 993, 999, 939, 781, 778, 1039, 842, 778, 1108",
      /*  284 */ "851, 770, 1199, 960, 778, 1108, 851, 770, 1199, 960, 778, 1108, 851, 770, 1199, 960, 999, 1177, 943",
      /*  303 */ "964, 763, 1217, 1381, 1205, 969, 880, 1135, 884, 975, 927, 770, 999, 1177, 943, 770, 770, 969, 1028",
      /*  322 */ "982, 770, 771, 1183, 756, 981, 763, 1217, 770, 1193, 951, 986, 763, 1217, 770, 977, 993, 991, 1211",
      /*  341 */ "997, 880, 1135, 884, 1003, 987, 770, 770, 1123, 860, 770, 770, 997, 1057, 982, 770, 880, 1135, 884",
      /*  360 */ "880, 1135, 1009, 770, 1199, 960, 1016, 763, 1217, 1021, 1223, 1025, 1021, 1223, 1025, 999, 1177, 943",
      /*  378 */ "771, 1183, 756, 999, 1177, 943, 937, 1239, 1357, 790, 1045, 1333, 770, 1123, 860, 771, 1183, 756",
      /*  396 */ "770, 1123, 860, 770, 792, 1147, 770, 1199, 960, 770, 871, 947, 790, 806, 750, 770, 1199, 960, 778",
      /*  415 */ "1039, 842, 790, 1045, 1333, 770, 856, 753, 788, 1075, 1339, 999, 1177, 943, 954, 982, 770, 937, 1239",
      /*  434 */ "1357, 1032, 763, 1217, 804, 1090, 770, 770, 1123, 860, 770, 977, 993, 1037, 1245, 1043, 880, 1135",
      /*  452 */ "884, 854, 965, 770, 770, 1199, 960, 770, 770, 1043, 1069, 982, 770, 937, 1239, 1357, 771, 1183, 756",
      /*  471 */ "937, 1239, 1357, 770, 1199, 960, 771, 1183, 756, 770, 1199, 960, 954, 982, 770, 770, 1261, 1367, 954",
      /*  490 */ "982, 770, 770, 1199, 960, 771, 1183, 756, 770, 811, 770, 770, 1267, 1367, 1049, 763, 1217, 771, 1183",
      /*  509 */ "756, 937, 1239, 1357, 770, 1273, 1054, 954, 982, 770, 770, 1273, 1054, 1061, 763, 1217, 771, 1183",
      /*  527 */ "756, 770, 1283, 1066, 770, 865, 770, 770, 770, 1073, 770, 1079, 770, 770, 1084, 770, 770, 770, 1089",
      /*  546 */ "770, 770, 1094, 770, 770, 1098, 1289, 770, 1102, 1106, 1295, 1112, 1121, 770, 770, 1127, 770, 770",
      /*  564 */ "1133, 770, 770, 770, 770, 1301, 1121, 821, 770, 1127, 1017, 770, 770, 1139, 770, 770, 770, 1145, 770",
      /*  583 */ "770, 1151, 770, 770, 1155, 1327, 770, 1159, 1163, 770, 770, 1169, 770, 770, 1175, 770, 770, 1181",
      /*  601 */ "770, 770, 1062, 1317, 1187, 1191, 770, 770, 1197, 770, 770, 1203, 770, 770, 770, 1209, 770, 1215",
      /*  619 */ "770, 770, 1221, 770, 770, 1227, 770, 770, 1163, 821, 770, 1169, 1017, 770, 876, 770, 1233, 770, 770",
      /*  638 */ "1237, 1243, 1080, 770, 1249, 1085, 770, 847, 1229, 1255, 770, 1259, 770, 1215, 821, 770, 1221, 1017",
      /*  656 */ "770, 770, 770, 1265, 1271, 770, 770, 1243, 821, 770, 834, 1345, 1277, 1249, 1017, 770, 770, 770",
      /*  674 */ "1351, 770, 770, 1281, 1287, 770, 770, 1293, 770, 770, 1299, 770, 770, 1305, 770, 770, 900, 770, 1311",
      /*  693 */ "770, 1315, 770, 905, 770, 1321, 1293, 821, 770, 1299, 1017, 770, 1325, 770, 770, 1331, 770, 770",
      /*  711 */ "1337, 770, 770, 770, 1343, 770, 770, 770, 1349, 1355, 770, 770, 770, 1050, 770, 770, 1033, 770, 910",
      /*  730 */ "770, 1361, 1365, 770, 770, 1371, 770, 770, 770, 770, 1378, 1365, 821, 770, 1371, 1017, 770, 1390",
      /*  748 */ "1388, 1443, 1388, 1389, 1899, 1388, 1389, 1915, 1388, 1391, 1952, 1392, 1487, 1388, 1387, 1388, 1393",
      /*  765 */ "1388, 1388, 1397, 1391, 1670, 1388, 1388, 1388, 1388, 1390, 1487, 1388, 1496, 1388, 1440, 1390, 1606",
      /*  782 */ "1391, 1884, 1392, 1826, 1419, 1827, 1388, 1441, 1388, 1390, 1388, 1388, 1403, 1388, 1426, 1388, 1388",
      /*  799 */ "1388, 1450, 1449, 1388, 1434, 1388, 1463, 1388, 1388, 1526, 1440, 1438, 1388, 1388, 1388, 1472, 1447",
      /*  816 */ "1388, 1388, 1388, 1488, 1454, 1388, 1388, 1388, 1528, 1440, 1606, 1405, 1392, 1690, 1476, 1496, 1480",
      /*  833 */ "1485, 1388, 1388, 1388, 1540, 1522, 1420, 1827, 1421, 1607, 1606, 1506, 1392, 1562, 1388, 1388, 1388",
      /*  850 */ "1549, 1390, 1389, 1534, 1388, 1466, 1388, 1388, 1547, 1388, 1606, 1391, 1925, 1392, 1586, 1388, 1388",
      /*  867 */ "1388, 1574, 1388, 1858, 1388, 1388, 1415, 1388, 1597, 1388, 1388, 1388, 1599, 1388, 1751, 1588, 1792",
      /*  884 */ "1611, 1620, 1496, 1480, 1624, 1388, 1388, 1388, 1606, 1388, 1762, 1388, 1388, 1432, 1391, 1630, 1388",
      /*  901 */ "1388, 1388, 1632, 1636, 1388, 1388, 1388, 1651, 1649, 1388, 1388, 1388, 1663, 1684, 1858, 1496, 1388",
      /*  918 */ "1576, 1626, 1792, 1655, 1388, 1388, 1388, 1696, 1661, 1388, 1388, 1388, 1730, 1684, 1947, 1496, 1388",
      /*  935 */ "1607, 1817, 1388, 1441, 1388, 1388, 1510, 1388, 1442, 1391, 1552, 1392, 1518, 1420, 1827, 1421, 1390",
      /*  952 */ "1388, 1957, 1388, 1642, 1388, 1388, 1516, 1421, 1442, 1391, 1543, 1392, 1700, 1388, 1388, 1388, 1739",
      /*  969 */ "1388, 1936, 1388, 1388, 1492, 1388, 1388, 1875, 1388, 1388, 1494, 1388, 1711, 1388, 1388, 1388, 1768",
      /*  986 */ "1717, 1388, 1388, 1388, 1774, 1388, 1780, 1388, 1388, 1496, 1388, 1388, 1947, 1388, 1388, 1501, 1392",
      /* 1003 */ "1388, 1942, 1388, 1388, 1504, 1440, 1684, 1936, 1496, 1388, 1642, 1388, 1599, 1728, 1388, 1388, 1388",
      /* 1020 */ "1847, 1388, 1989, 1657, 1792, 1684, 1968, 1496, 1388, 1642, 1388, 1632, 1749, 1388, 1388, 1388, 1863",
      /* 1037 */ "1388, 1963, 1388, 1388, 1532, 1440, 1388, 1968, 1388, 1388, 1538, 1440, 1766, 1388, 1388, 1388, 1864",
      /* 1054 */ "1389, 1388, 1994, 1388, 1642, 1388, 1651, 1778, 1388, 1388, 1388, 1881, 1809, 1785, 1428, 1388, 1642",
      /* 1071 */ "1388, 1663, 1388, 1789, 1388, 1388, 1556, 1440, 1678, 1388, 1388, 1388, 1905, 1796, 1388, 1388, 1388",
      /* 1088 */ "2025, 1481, 1388, 1388, 1388, 2031, 1707, 1802, 1388, 1808, 1813, 1815, 1388, 1816, 1831, 1833, 1388",
      /* 1105 */ "1834, 1388, 1836, 1388, 1388, 1568, 1440, 1388, 1835, 1842, 1388, 1702, 1388, 1388, 1409, 1388, 1719",
      /* 1122 */ "1388, 1388, 1388, 1580, 1388, 1999, 1388, 1388, 1388, 1592, 1696, 1388, 1846, 1388, 1388, 1603, 1730",
      /* 1139 */ "1857, 1388, 1388, 1388, 1614, 1388, 1862, 1388, 1388, 1388, 1616, 1388, 1868, 1873, 1388, 1879, 1888",
      /* 1156 */ "1815, 1388, 1816, 1903, 1833, 1388, 1834, 1798, 1388, 1388, 1388, 1645, 1388, 2010, 1388, 1388, 1388",
      /* 1173 */ "1667, 1774, 1388, 1909, 1388, 1388, 1676, 1388, 1388, 1914, 1388, 1388, 1682, 1388, 1388, 1882, 1880",
      /* 1190 */ "1883, 1804, 1388, 1388, 1388, 1688, 1388, 2015, 1388, 1388, 1388, 1694, 1388, 1388, 1924, 1388, 1388",
      /* 1207 */ "1706, 1730, 1388, 1929, 1388, 1388, 1723, 1774, 1853, 1388, 1388, 1388, 1724, 1388, 2020, 1388, 1388",
      /* 1224 */ "1388, 1734, 1739, 1388, 1935, 1388, 1388, 1737, 1388, 1388, 1940, 1388, 1946, 1638, 1388, 1388, 1388",
      /* 1241 */ "1743, 1440, 1890, 1388, 1388, 1388, 1755, 1739, 2036, 1388, 1388, 1388, 1756, 1388, 1564, 1550, 1422",
      /* 1258 */ "1551, 1388, 1931, 1388, 1388, 1760, 1388, 1951, 1388, 1388, 1388, 1760, 1456, 1388, 1956, 1388, 1388",
      /* 1275 */ "1772, 1388, 1582, 1541, 1593, 1542, 1972, 1388, 1388, 1388, 1784, 1388, 1388, 1977, 1388, 1388, 1821",
      /* 1292 */ "1825, 2005, 1388, 1388, 1388, 1840, 1512, 1672, 1388, 1388, 1388, 1851, 1388, 1388, 1982, 1388, 1388",
      /* 1309 */ "1869, 1388, 1388, 1987, 1388, 1993, 1388, 1998, 1388, 1388, 1882, 1919, 1388, 2003, 1388, 2009, 1469",
      /* 1326 */ "1388, 1388, 1388, 1894, 1898, 1399, 1388, 1388, 1388, 1910, 1388, 1388, 2014, 1388, 1388, 1920, 1388",
      /* 1343 */ "1978, 1388, 1388, 1388, 1961, 1388, 2019, 1388, 1388, 1388, 1967, 1388, 1388, 2024, 1388, 1388, 1973",
      /* 1360 */ "1388, 1388, 2029, 1388, 2035, 1571, 1388, 1388, 1388, 1983, 1388, 1411, 1388, 1388, 1388, 1713, 1558",
      /* 1377 */ "1792, 1497, 1388, 1388, 1388, 1745, 1388, 1388, 1460, 1696, 1441810, 0, 0, 0, 0, 8454, 0, 0, 0",
      /* 1396 */ "25418, 0, 98610, 0, 0, 0, 1982482, 0, 123170, 8454, 0, 0, 180882, 0, 139554, 0, 0, 0, 2033522, 0",
      /* 1416 */ "140790, 0, 155656, 140790, 0, 140790, 0, 0, 0, 49166, 0, 82642, 0, 0, 14, 147476, 0, 98610, 16886, 0",
      /* 1436 */ "0, 107154, 0, 107218, 0, 0, 8454, 8454, 0, 0, 25234, 0, 131794, 0, 0, 16886, 0, 16886, 0, 148178, 0",
      /* 1457 */ "0, 16886, 16886, 256146, 0, 147464, 0, 0, 1941522, 0, 0, 1968738, 0, 0, 1974290, 0, 0, 2000546",
      /* 1475 */ "2008898, 0, 73732, 0, 264402, 73732, 0, 0, 0, 50186, 0, 180946, 0, 0, 57352, 0, 57352, 0, 270866, 0",
      /* 1495 */ "0, 147464, 0, 0, 0, 41208, 8454, 8454, 8454, 8454, 172322, 8454, 0, 0, 410258, 0, 352546, 0, 0",
      /* 1514 */ "188436, 188436, 140790, 140790, 140790, 140790, 0, 155656, 0, 140790, 0, 16398, 8454, 377122, 0, 0",
      /* 1530 */ "198426, 0, 8454, 401698, 8454, 0, 0, 524946, 0, 426274, 0, 0, 198454, 0, 0, 0, 1000082, 0, 459042, 0",
      /* 1550 */ "0, 245796, 0, 0, 0, 828050, 0, 483618, 0, 0, 248338, 0, 0, 279250, 0, 0, 278564, 0, 8454, 516386",
      /* 1570 */ "8454, 0, 0, 2025170, 0, 234, 0, 0, 1705970, 712722, 0, 532770, 0, 0, 278580, 0, 0, 361170, 0, 0",
      /* 1590 */ "616978, 0, 256146, 0, 0, 0, 65550, 0, 385746, 0, 0, 666082, 0, 624786, 0, 147464, 0, 8454, 0, 8454",
      /* 1610 */ "0, 73732, 0, 1515538, 0, 16674, 8454, 0, 0, 131730, 0, 73732, 0, 633042, 0, 410322, 0, 0, 723474, 0",
      /* 1630 */ "0, 434898, 0, 0, 1042914, 0, 0, 467666, 0, 0, 1056786, 0, 0, 1646610, 1671186, 0, 16886, 0, 279186",
      /* 1649 */ "0, 492242, 0, 0, 1083874, 0, 0, 525010, 0, 0, 1141266, 0, 0, 541394, 0, 0, 1313250, 0, 731282, 0",
      /* 1669 */ "147464, 0, 25298, 0, 0, 0, 1918834, 0, 819490, 0, 0, 1474578, 0, 0, 885026, 0, 0, 1515538, 0, 0",
      /* 1689 */ "901410, 0, 0, 1515538, 73732, 0, 991522, 0, 0, 1525410, 1533762, 0, 828114, 0, 0, 1542130, 0, 624786",
      /* 1707 */ "0, 0, 0, 66662, 0, 893650, 0, 0, 1542130, 237586, 0, 910034, 0, 0, 1558226, 0, 731282, 0, 0, 0",
      /* 1727 */ "82578, 0, 1000146, 0, 0, 1615522, 1623874, 1149074, 0, 147464, 0, 49166, 0, 0, 1861282, 1869634, 0",
      /* 1744 */ "1179938, 0, 0, 1632242, 0, 0, 1188562, 0, 0, 1632242, 606226, 1149074, 0, 0, 0, 107154, 0, 1368594",
      /* 1762 */ "0, 0, 1641058, 0, 0, 1376978, 0, 0, 1656498, 1664850, 0, 1417506, 0, 0, 1689250, 1697602, 0, 1426130",
      /* 1780 */ "0, 0, 1705970, 0, 0, 147476, 0, 147476, 0, 156626, 0, 156610, 0, 57352, 0, 1515538, 0, 188434, 0, 0",
      /* 1800 */ "1713874, 0, 0, 296050, 0, 0, 1746658, 0, 296066, 0, 0, 0, 147476, 0, 304418, 0, 75030, 0, 0, 0",
      /* 1820 */ "148114, 311314, 319506, 327698, 335890, 344082, 0, 0, 0, 155656, 140790, 0, 81940, 0, 81940, 0, 0, 0",
      /* 1838 */ "188436, 0, 188436, 32782, 57364, 0, 0, 32782, 444418, 0, 0, 0, 198586, 0, 1572882, 0, 0, 1779410, 0",
      /* 1857 */ "1679378, 0, 0, 0, 264402, 753682, 0, 0, 0, 278568, 0, 66662, 0, 0, 0, 279186, 0, 762994, 0, 0",
      /* 1877 */ "1804898, 0, 763010, 0, 0, 0, 294932, 0, 0, 0, 361106, 75030, 771362, 0, 0, 1812178, 0, 778258",
      /* 1895 */ "786450, 794642, 802834, 811026, 0, 0, 0, 385682, 81940, 81940, 0, 0, 1820450, 0, 862210, 0, 0, 0",
      /* 1913 */ "434834, 641650, 0, 0, 0, 467602, 294936, 0, 0, 0, 492178, 1761298, 0, 0, 0, 541330, 0, 1769490, 0, 0",
      /* 1933 */ "1835026, 0, 952322, 0, 0, 0, 633042, 0, 1050978, 0, 0, 1854050, 0, 1050994, 0, 0, 0, 739538, 1163282",
      /* 1952 */ "0, 0, 0, 893586, 1026674, 0, 0, 0, 909970, 0, 65550, 0, 0, 1878002, 0, 1892370, 0, 0, 0, 1157330",
      /* 1972 */ "1204242, 0, 0, 0, 1188498, 1067634, 0, 0, 0, 1327122, 1247234, 0, 0, 0, 1376914, 0, 1272162, 0, 0",
      /* 1991 */ "1878002, 1130514, 1272178, 0, 0, 0, 1426066, 1949714, 0, 0, 0, 1566578, 0, 1288546, 0, 0, 1910482, 0",
      /* 2009 */ "1288562, 0, 0, 0, 1722226, 278552, 0, 0, 0, 1755010, 1343506, 0, 0, 0, 1787762, 1297010, 0, 0, 0",
      /* 2028 */ "1820610, 0, 1395042, 0, 0, 1925138, 1933330, 1395058, 0, 0, 0, 1828722"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 2040; ++i) {CASEID[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] TOKENSET = new int[249];
  static
  {
    final String s1[] =
    {
      /*   0 */ "51, 43, 51, 30, 43, 51, 25, 35, 37, 52, 30, 43, 55, 30, 20, 35, 30, 37, 30, 70, 68, 70, 30, 19, 35",
      /*  25 */ "62, 64, 54, 62, 69, 41, 28, 53, 29, 30, 20, 70, 65, 49, 65, 49, 65, 49, 62, 30, 45, 54, 30, 67, 69",
      /*  50 */ "30, 31, 41, 30, 62, 9, 28, 30, 44, 53, 30, 68, 63, 65, 30, 49, 30, 63, 63, 35, 61, 66, 54, 61, 69, 41",
      /*  76 */ "28, 53, 62, 42, 26, 62, 35, 49, 56, 54, 49, 69, 41, 28, 53, 20, 62, 69, 65, 48, 65, 48, 65, 48, 61",
      /* 101 */ "30, 45, 67, 31, 61, 9, 44, 42, 30, 26, 30, 20, 45, 67, 31, 49, 9, 44, 67, 63, 48, 30, 63, 63, 61, 42",
      /* 127 */ "61, 46, 41, 49, 42, 49, 35, 48, 58, 54, 48, 69, 41, 28, 53, 61, 38, 46, 30, 31, 49, 20, 45, 67, 31",
      /* 152 */ "48, 9, 44, 46, 42, 46, 48, 42, 48, 38, 19, 38, 48, 42, 23, 36, 30, 42, 46, 27, 38, 27, 30, 42, 52, 3",
      /* 178 */ "24, 13, 12, 8, 33, 40, 60, 57, 0, 1, 2, 18, 21, 22, 10, 6, 32, 39, 59, 0, 1, 2, 2, 47, 0, 1, 2, 16, 0",
      /* 207 */ "1, 2, 21, 22, 34, 7, 21, 22, 50, 17, 21, 22, 6, 2, 21, 50, 22, 11, 6, 2, 0, 1, 2, 34, 15, 34, 21, 22",
      /* 235 */ "0, 1, 2, 14, 6, 2, 4, 5, 34, 0, 1, 8, 21, 22"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 249; ++i) {TOKENSET[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] APPENDIX = new int[10];
  static
  {
    final String s1[] =
    {
      /*  0 */ "73738, 733193, 70395, 77828, 28682, 94218, 122898, 139282, 99227, 139290"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 10; ++i) {APPENDIX[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] LOOKBACK = new int[573];
  static
  {
    final String s1[] =
    {
      /*   0 */ "172, 172, 172, 172, 172, 170, 173, 173, 173, 178, 188, 188, 188, 183, 191, 194, 197, 206, 206, 215",
      /*  20 */ "206, 215, 206, 215, 228, 228, 237, 228, 237, 228, 237, 250, 259, 259, 259, 259, 268, 268, 268, 268",
      /*  40 */ "172, 172, 172, 172, 277, 277, 277, 277, 284, 284, 284, 284, 291, 298, 172, 301, 304, 307, 307, 307",
      /*  60 */ "310, 313, 316, 321, 324, 327, 172, 172, 172, 172, 330, 335, 340, 345, 350, 350, 350, 350, 172, 172",
      /*  80 */ "172, 353, 358, 363, 366, 366, 366, 172, 369, 372, 375, 378, 172, 172, 172, 381, 386, 386, 386, 172",
      /* 100 */ "172, 391, 394, 397, 400, 403, 172, 172, 406, 406, 415, 406, 415, 424, 429, 434, 172, 172, 443, 443",
      /* 120 */ "452, 443, 452, 461, 466, 172, 471, 474, 477, 480, 483, 172, 486, 489, 492, 172, 495, 498, 501, 504",
      /* 140 */ "507, 172, 510, 510, 510, 513, 523, 523, 523, 523, 526, 531, 536, 541, 544, 544, 544, 544, 516, 547",
      /* 160 */ "550, 172, 553, 553, 553, 556, 559, 567, 562, 570, 3, 2, 0, 5, 5, 3, 4, 0, 11, 10, 7, 6, 0, 11, 12, 7",
      /* 186 */ "8, 0, 9, 9, 0, 13, 13, 0, 14, 14, 0, 26, 24, 25, 24, 19, 17, 18, 17, 0, 26, 27, 25, 27, 19, 20, 18",
      /* 213 */ "20, 0, 37, 38, 33, 34, 26, 28, 25, 27, 19, 21, 18, 20, 0, 26, 29, 25, 29, 19, 22, 18, 22, 0, 37, 39",
      /* 239 */ "33, 35, 26, 30, 25, 29, 19, 23, 18, 22, 0, 37, 36, 33, 32, 26, 36, 19, 32, 0, 37, 38, 33, 34, 26, 38",
      /* 265 */ "19, 34, 0, 37, 39, 33, 35, 26, 39, 19, 35, 0, 49, 48, 45, 44, 41, 40, 0, 49, 50, 45, 46, 41, 42, 0",
      /* 291 */ "49, 51, 45, 47, 41, 43, 0, 52, 52, 0, 54, 54, 0, 55, 55, 0, 56, 56, 0, 58, 57, 0, 58, 59, 0, 61, 61",
      /* 318 */ "60, 60, 0, 62, 62, 0, 63, 63, 0, 64, 64, 0, 75, 74, 67, 66, 0, 75, 76, 67, 68, 0, 75, 77, 67, 69, 0",
      /* 345 */ "72, 72, 71, 71, 0, 73, 73, 0, 85, 84, 79, 78, 0, 85, 86, 79, 80, 0, 82, 82, 0, 83, 83, 0, 87, 87, 0",
      /* 372 */ "88, 88, 0, 89, 89, 0, 90, 90, 0, 97, 96, 93, 92, 0, 97, 98, 93, 94, 0, 100, 99, 0, 100, 100, 0, 102",
      /* 398 */ "101, 0, 103, 103, 0, 104, 104, 0, 110, 108, 109, 108, 107, 106, 106, 106, 0, 110, 108, 109, 108, 107",
      /* 420 */ "107, 106, 106, 0, 110, 111, 109, 111, 0, 110, 112, 109, 111, 0, 124, 123, 123, 123, 114, 113, 113",
      /* 441 */ "113, 0, 120, 118, 119, 118, 117, 116, 116, 116, 0, 120, 118, 119, 118, 117, 117, 116, 116, 0, 120",
      /* 462 */ "121, 119, 121, 0, 120, 122, 119, 121, 0, 100, 125, 0, 102, 126, 0, 127, 127, 0, 128, 128, 0, 129, 129",
      /* 485 */ "0, 100, 131, 0, 102, 132, 0, 133, 133, 0, 100, 135, 0, 102, 136, 0, 137, 137, 0, 138, 138, 0, 139",
      /* 508 */ "139, 0, 141, 141, 0, 143, 142, 0, 155, 157, 147, 149, 143, 144, 0, 145, 145, 0, 155, 154, 147, 146, 0",
      /* 531 */ "155, 156, 147, 148, 0, 151, 151, 150, 150, 0, 152, 152, 0, 153, 153, 0, 158, 158, 0, 159, 159, 0, 161",
      /* 554 */ "161, 0, 163, 162, 0, 163, 164, 0, 167, 167, 165, 165, 0, 166, 166, 0, 168, 168, 0"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 573; ++i) {LOOKBACK[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] GOTO = new int[1721];
  static
  {
    final String s1[] =
    {
      /*    0 */ "440, 953, 441, 950, 953, 445, 953, 556, 953, 953, 948, 952, 472, 953, 953, 450, 953, 478, 953, 953",
      /*   20 */ "455, 953, 953, 592, 953, 460, 953, 446, 950, 953, 465, 953, 780, 953, 953, 953, 952, 858, 953, 953",
      /*   40 */ "470, 953, 490, 953, 953, 476, 953, 610, 953, 953, 932, 604, 742, 953, 953, 482, 953, 496, 953, 953",
      /*   60 */ "488, 953, 502, 953, 953, 953, 494, 508, 953, 953, 500, 953, 514, 953, 953, 506, 953, 520, 953, 953",
      /*   80 */ "512, 953, 484, 953, 953, 518, 953, 953, 526, 953, 524, 953, 532, 953, 953, 530, 953, 538, 953, 953",
      /*  100 */ "536, 953, 544, 953, 953, 542, 953, 550, 953, 953, 548, 953, 953, 953, 554, 560, 953, 774, 953, 953",
      /*  120 */ "750, 738, 929, 953, 953, 566, 953, 953, 953, 572, 578, 953, 562, 953, 953, 584, 953, 953, 953, 988",
      /*  140 */ "590, 953, 568, 953, 953, 953, 574, 870, 953, 953, 596, 953, 954, 602, 606, 886, 604, 632, 953, 953",
      /*  160 */ "614, 953, 598, 953, 953, 620, 953, 461, 626, 630, 636, 628, 806, 953, 953, 640, 953, 953, 616, 953",
      /*  180 */ "646, 953, 622, 953, 953, 652, 953, 642, 953, 953, 658, 953, 648, 953, 953, 664, 953, 654, 953, 953",
      /*  200 */ "670, 953, 660, 953, 953, 676, 953, 666, 953, 953, 953, 682, 672, 953, 953, 688, 953, 678, 953, 953",
      /*  220 */ "694, 953, 684, 953, 953, 700, 953, 953, 690, 953, 706, 953, 696, 953, 953, 712, 953, 702, 953, 953",
      /*  240 */ "718, 953, 708, 953, 953, 724, 953, 714, 953, 953, 730, 953, 720, 953, 953, 953, 726, 451, 736, 740",
      /*  260 */ "953, 732, 756, 953, 953, 953, 456, 953, 953, 746, 754, 953, 762, 953, 953, 760, 953, 768, 953, 953",
      /*  280 */ "953, 766, 953, 953, 772, 967, 738, 608, 953, 778, 784, 953, 953, 953, 988, 953, 580, 748, 790, 740",
      /*  300 */ "974, 738, 883, 953, 953, 794, 953, 466, 800, 804, 810, 802, 945, 953, 953, 814, 953, 786, 953, 953",
      /*  320 */ "820, 953, 796, 953, 953, 826, 953, 816, 953, 953, 832, 953, 822, 953, 953, 838, 953, 953, 828, 953",
      /*  340 */ "844, 953, 834, 953, 953, 850, 953, 840, 953, 953, 856, 953, 846, 953, 953, 862, 953, 953, 953, 868",
      /*  360 */ "874, 953, 852, 953, 953, 953, 953, 880, 953, 953, 953, 890, 953, 953, 896, 981, 738, 608, 953, 902",
      /*  380 */ "908, 953, 953, 953, 988, 953, 586, 938, 953, 953, 914, 953, 864, 953, 953, 920, 953, 876, 953, 953",
      /*  400 */ "926, 953, 953, 953, 936, 942, 953, 953, 953, 958, 964, 953, 892, 953, 953, 953, 898, 904, 953, 953",
      /*  420 */ "971, 953, 910, 953, 953, 978, 953, 916, 953, 953, 953, 990, 960, 953, 953, 985, 953, 922, 953, 953",
      /*  440 */ "994, 1146, 1146, 1146, 998, 1147, 1146, 1146, 1146, 1029, 1017, 1146, 1146, 1146, 1157, 1025, 1146",
      /*  457 */ "1146, 1146, 1193, 1220, 1146, 1146, 1146, 1256, 1420, 1146, 1146, 1146, 1493, 1681, 1146, 1146, 1146",
      /*  474 */ "1013, 1146, 1070, 1146, 1146, 1146, 1021, 1146, 1195, 1146, 1146, 1146, 1051, 1146, 1078, 1146, 1146",
      /*  491 */ "1146, 1064, 1146, 1044, 1146, 1146, 1146, 1074, 1146, 1090, 1146, 1146, 1146, 1082, 1146, 1098, 1146",
      /*  508 */ "1146, 1146, 1086, 1146, 1106, 1146, 1146, 1146, 1094, 1146, 1113, 1146, 1146, 1146, 1102, 1146, 1117",
      /*  525 */ "1146, 1146, 1146, 1110, 1146, 1125, 1146, 1146, 1146, 1121, 1146, 1133, 1146, 1146, 1146, 1129, 1146",
      /*  542 */ "1141, 1146, 1146, 1146, 1137, 1146, 1263, 1146, 1146, 1146, 1168, 1146, 1145, 1151, 1146, 1146, 1181",
      /*  559 */ "1146, 1366, 1146, 1146, 1146, 1185, 1146, 1599, 1146, 1146, 1146, 1203, 1146, 1180, 1146, 1146, 1146",
      /*  576 */ "1210, 1207, 1610, 1146, 1146, 1146, 1210, 1473, 1189, 1146, 1146, 1146, 1210, 1632, 1199, 1146, 1146",
      /*  593 */ "1146, 1211, 1146, 1228, 1146, 1146, 1146, 1248, 1146, 1232, 1041, 1048, 1055, 1059, 1062, 1146, 1068",
      /*  610 */ "1146, 1146, 1033, 1146, 1244, 1146, 1146, 1146, 1283, 1146, 1252, 1146, 1146, 1146, 1291, 1146, 1615",
      /*  627 */ "1411, 1260, 1267, 1271, 1062, 1146, 1068, 1240, 1146, 1146, 1256, 1615, 1456, 1279, 1146, 1146, 1146",
      /*  644 */ "1299, 1146, 1287, 1146, 1146, 1146, 1307, 1146, 1295, 1146, 1146, 1146, 1315, 1146, 1303, 1146, 1146",
      /*  661 */ "1146, 1323, 1146, 1311, 1146, 1146, 1146, 1331, 1146, 1319, 1146, 1146, 1146, 1339, 1146, 1327, 1146",
      /*  678 */ "1146, 1146, 1347, 1146, 1335, 1146, 1146, 1146, 1355, 1146, 1343, 1146, 1146, 1146, 1363, 1146, 1351",
      /*  695 */ "1146, 1146, 1146, 1374, 1146, 1359, 1146, 1146, 1146, 1382, 1146, 1370, 1146, 1146, 1146, 1390, 1146",
      /*  712 */ "1378, 1146, 1146, 1146, 1398, 1146, 1386, 1146, 1146, 1146, 1406, 1146, 1394, 1146, 1146, 1146, 1410",
      /*  729 */ "1151, 1402, 1146, 1146, 1146, 1419, 1146, 1415, 1041, 1165, 1172, 1176, 1062, 1146, 1068, 1153, 1146",
      /*  746 */ "1210, 1428, 1215, 1219, 1146, 1157, 1161, 1041, 1432, 1146, 1146, 1146, 1424, 1146, 1440, 1146, 1146",
      /*  763 */ "1146, 1436, 1146, 1448, 1146, 1146, 1146, 1444, 1146, 1455, 1151, 1146, 1146, 1451, 1146, 1464, 1146",
      /*  780 */ "1146, 1146, 1465, 1146, 1469, 1146, 1146, 1146, 1520, 1146, 1477, 1041, 1165, 1172, 1489, 1146, 1146",
      /*  797 */ "1146, 1528, 1146, 1615, 1411, 1497, 1504, 1508, 1062, 1146, 1068, 1275, 1146, 1146, 1493, 1615, 1456",
      /*  814 */ "1516, 1146, 1146, 1146, 1536, 1146, 1524, 1146, 1146, 1146, 1544, 1146, 1532, 1146, 1146, 1146, 1552",
      /*  831 */ "1146, 1540, 1146, 1146, 1146, 1560, 1146, 1548, 1146, 1146, 1146, 1568, 1146, 1556, 1146, 1146, 1146",
      /*  848 */ "1576, 1146, 1564, 1146, 1146, 1146, 1592, 1146, 1572, 1146, 1146, 1146, 1624, 1146, 1580, 1146, 1146",
      /*  865 */ "1146, 1644, 1146, 1210, 1584, 1215, 1219, 1224, 1146, 1588, 1146, 1146, 1146, 1652, 1146, 1596, 1146",
      /*  882 */ "1603, 1146, 1068, 1485, 1146, 1005, 1236, 1041, 1607, 1146, 1146, 1146, 1676, 1146, 1614, 1151, 1146",
      /*  899 */ "1146, 1680, 1146, 1623, 1146, 1146, 1146, 1685, 1146, 1628, 1146, 1146, 1146, 1693, 1146, 1640, 1146",
      /*  916 */ "1146, 1146, 1701, 1146, 1648, 1146, 1146, 1146, 1717, 1146, 1656, 1146, 1146, 1146, 1068, 1500, 1146",
      /*  933 */ "1005, 1037, 1041, 1210, 1660, 1215, 1219, 1636, 1146, 1664, 1146, 1146, 1146, 1068, 1512, 1146, 1009",
      /*  950 */ "1146, 1146, 1002, 1146, 1146, 1146, 1146, 1005, 1210, 1668, 1215, 1219, 1709, 1146, 1672, 1146, 1146",
      /*  967 */ "1146, 1157, 1460, 1041, 1689, 1146, 1146, 1146, 1157, 1481, 1041, 1697, 1146, 1146, 1146, 1157, 1619",
      /*  984 */ "1041, 1713, 1146, 1146, 1146, 1193, 1146, 1146, 1210, 1705, 6, 4121, 0, 8393, 16425, 20489, 24633",
      /* 1001 */ "29105, 0, 33217, 37609, 0, 0, 0, 99001, 0, 0, 24633, 29105, 0, 16396, 0, 8393, 0, 725097, 45129",
      /* 1020 */ "49361, 0, 57785, 0, 61641, 0, 729553, 0, 69833, 0, 0, 24665, 29105, 0, 82417, 0, 86217, 741881",
      /* 1038 */ "746009, 750201, 753673, 40964, 40964, 40964, 0, 0, 0, 107273, 45060, 103113, 107241, 0, 0, 0, 135465",
      /* 1055 */ "45060, 81924, 86020, 111393, 115513, 86020, 81924, 106500, 111661, 0, 0, 0, 69833, 0, 45060, 0, 0, 0",
      /* 1073 */ "74121, 0, 0, 774153, 135465, 0, 144065, 0, 61641, 0, 66261, 0, 176329, 0, 185137, 0, 188617, 0",
      /* 1091 */ "90949, 0, 176329, 0, 196617, 0, 200905, 0, 210201, 0, 213193, 0, 226385, 0, 229577, 0, 238713, 0",
      /* 1109 */ "241865, 0, 103113, 107241, 0, 0, 0, 139657, 0, 250441, 0, 86217, 0, 254617, 0, 258249, 0, 49172, 0",
      /* 1128 */ "266441, 0, 274441, 0, 258249, 0, 53268, 0, 266441, 0, 278537, 0, 258249, 0, 57364, 0, 266441, 115513",
      /* 1146 */ "0, 0, 0, 0, 8393, 0, 111661, 0, 0, 0, 86217, 0, 0, 0, 283321, 790537, 795161, 799353, 802825, 45060",
      /* 1166 */ "287433, 291561, 0, 0, 0, 176329, 45060, 81924, 86020, 295713, 299833, 86020, 81924, 106500, 320809",
      /* 1181 */ "0, 0, 0, 12681, 0, 0, 0, 217481, 0, 103437, 0, 176329, 0, 115805, 0, 0, 0, 90505, 0, 0, 0, 229577, 0",
      /* 1204 */ "0, 0, 233865, 0, 0, 324745, 122884, 0, 0, 0, 37641, 327689, 0, 131076, 122884, 143364, 0, 0, 0",
      /* 1223 */ "41353, 0, 0, 0, 241865, 0, 0, 0, 246153, 0, 746073, 750201, 753673, 0, 0, 750249, 753673, 0, 0, 0",
      /* 1243 */ "258249, 0, 0, 0, 262537, 0, 0, 0, 266441, 0, 0, 0, 270729, 0, 0, 0, 336569, 45060, 340681, 344809, 0",
      /* 1264 */ "0, 0, 180617, 45060, 81924, 86020, 348961, 353081, 86020, 81924, 106500, 0, 373441, 0, 61641, 0",
      /* 1280 */ "66261, 0, 409801, 0, 0, 0, 291593, 0, 418609, 0, 188617, 0, 90949, 0, 409801, 0, 421897, 0, 200905",
      /* 1299 */ "0, 427289, 0, 213193, 0, 435281, 0, 229577, 0, 439417, 0, 241865, 0, 152901, 0, 176329, 0, 865433, 0",
      /* 1318 */ "442569, 0, 868361, 0, 450761, 0, 120061, 0, 176329, 0, 459457, 0, 61641, 0, 66261, 0, 266441, 0, 0",
      /* 1337 */ "0, 344841, 0, 463665, 0, 188617, 0, 90949, 0, 266441, 0, 466953, 0, 200905, 0, 472345, 0, 213193, 0",
      /* 1356 */ "480337, 0, 229577, 0, 484473, 0, 241865, 0, 287433, 291561, 0, 0, 0, 188617, 0, 45092, 0, 176329, 0",
      /* 1375 */ "488009, 0, 200905, 0, 492185, 0, 258249, 0, 49172, 0, 495817, 0, 503817, 0, 258249, 0, 53268, 0",
      /* 1393 */ "495817, 0, 507913, 0, 258249, 0, 57364, 0, 495817, 0, 0, 0, 409801, 0, 0, 0, 414089, 299833, 0, 0, 0",
      /* 1414 */ "49180, 897033, 795161, 799353, 802825, 513321, 0, 0, 0, 49361, 0, 103437, 0, 409801, 0, 0, 517257",
      /* 1431 */ "122884, 0, 0, 0, 442569, 0, 0, 0, 446857, 0, 0, 0, 450761, 0, 0, 0, 455049, 0, 340681, 344809, 0, 0",
      /* 1453 */ "0, 192905, 353081, 0, 0, 0, 53276, 921609, 795161, 799353, 802825, 533801, 0, 0, 0, 53641, 0, 103437",
      /* 1471 */ "0, 266441, 0, 0, 537737, 122884, 0, 795225, 799353, 802825, 0, 0, 799401, 802825, 0, 0, 0, 495817, 0",
      /* 1490 */ "0, 0, 500105, 0, 0, 0, 545465, 45060, 549577, 553705, 0, 0, 0, 200905, 45060, 81924, 86020, 557857",
      /* 1508 */ "561977, 86020, 81924, 106500, 0, 152901, 0, 409801, 0, 943257, 0, 442569, 0, 120061, 0, 409801, 0",
      /* 1525 */ "586945, 0, 590025, 0, 598025, 0, 213193, 0, 152901, 0, 266441, 0, 951449, 0, 442569, 0, 120061, 0",
      /* 1543 */ "266441, 0, 606913, 0, 61641, 0, 66261, 0, 495817, 0, 0, 0, 553737, 0, 611121, 0, 188617, 0, 90949, 0",
      /* 1563 */ "495817, 0, 614409, 0, 200905, 0, 619801, 0, 213193, 0, 627793, 0, 229577, 0, 631929, 0, 241865, 0",
      /* 1581 */ "45092, 0, 409801, 0, 0, 640201, 122884, 0, 0, 0, 590025, 0, 0, 0, 594313, 0, 127012, 135172, 0, 0, 0",
      /* 1602 */ "205193, 0, 45092, 0, 266441, 0, 549577, 553705, 0, 0, 0, 213193, 561977, 0, 0, 0, 61444, 978953",
      /* 1620 */ "795161, 799353, 802825, 648489, 0, 0, 0, 61641, 0, 103437, 0, 495817, 0, 0, 652425, 122884, 0",
      /* 1637 */ "660673, 0, 590025, 0, 865497, 0, 442569, 0, 668865, 0, 590025, 0, 152901, 0, 495817, 0, 996505, 0",
      /* 1655 */ "442569, 0, 120061, 0, 495817, 0, 0, 677065, 122884, 0, 0, 679945, 684329, 0, 0, 693449, 122884, 0",
      /* 1673 */ "45092, 0, 495817, 0, 943321, 0, 442569, 700425, 0, 0, 0, 65929, 0, 0, 0, 684329, 0, 0, 0, 688521, 0",
      /* 1694 */ "951513, 0, 442569, 0, 705729, 0, 590025, 0, 1007625, 0, 708809, 0, 0, 718025, 122884, 0, 0, 0",
      /* 1712 */ "708809, 0, 0, 0, 713097, 0, 996569, 0, 442569"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 1721; ++i) {GOTO[i] = Integer.parseInt(s2[i]);}
  }

  private static final String[] TOKEN =
  {
    "(0)",
    "cchar",
    "dchar",
    "schar",
    "hex",
    "capital",
    "'#'",
    "'('",
    "'*'",
    "'**'",
    "'+'",
    "'++'",
    "'?'",
    "'@'",
    "']'",
    "'^'",
    "'e'",
    "'l'",
    "'m'",
    "'n'",
    "'o'",
    "'r'",
    "'s'",
    "'}'",
    "'~'",
    "whitespace",
    "namestart",
    "namefollower",
    "letter",
    "eof",
    "'\"'",
    "''''",
    "')'",
    "','",
    "'-'",
    "'.'",
    "':'",
    "';'",
    "'='",
    "'['",
    "'i'",
    "'v'",
    "'x'",
    "'{'",
    "'|'"
  };

  private static final String[] NONTERMINAL =
  {
    "ixml",
    "s",
    "RS",
    "comment",
    "prolog",
    "version",
    "rule",
    "mark",
    "alts",
    "alt",
    "term",
    "factor",
    "repeat0",
    "repeat1",
    "option",
    "sep",
    "nonterminal",
    "name",
    "namestart",
    "namefollower",
    "terminal",
    "literal",
    "quoted",
    "tmark",
    "string",
    "encoded",
    "charset",
    "inclusion",
    "exclusion",
    "set",
    "member",
    "range",
    "from",
    "to",
    "character",
    "class",
    "code",
    "insertion"
  };
}

// End
