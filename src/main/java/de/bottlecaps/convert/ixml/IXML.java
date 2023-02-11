// This file was generated on Sat Feb 11, 2023 18:01 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net>
// REx command line: -tree -a none -java -interface de.bottlecaps.convert.LRParser -basex -saxon -name de.bottlecaps.convert.ixml.IXML -glalr 1 ixml.ebnf

package de.bottlecaps.convert.ixml;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import net.sf.saxon.Configuration;
import net.sf.saxon.event.Builder;
import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.lib.ExtensionFunctionCall;
import net.sf.saxon.lib.ExtensionFunctionDefinition;
import net.sf.saxon.lib.Initializer;
import net.sf.saxon.om.AttributeInfo;
import net.sf.saxon.om.NoNamespaceName;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.om.SmallAttributeMap;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.type.AnySimpleType;
import net.sf.saxon.type.AnyType;
import net.sf.saxon.value.SequenceType;
import net.sf.saxon.str.StringView;
import org.basex.build.MemBuilder;
import org.basex.build.SingleParser;
import org.basex.core.MainOptions;
import org.basex.io.IOContent;
import org.basex.query.value.item.Str;
import org.basex.query.value.node.ANode;
import org.basex.query.value.node.DBNode;
import org.basex.util.Atts;
import org.basex.util.Token;

public class IXML implements de.bottlecaps.convert.LRParser
{
  public static class SaxonInitializer implements Initializer
  {
    @Override
    public void initialize(Configuration conf)
    {
      conf.registerExtensionFunction(new SaxonDefinition_ixml());
    }
  }

  public static Sequence parseIxml(XPathContext context, String input) throws XPathException
  {
    Builder builder = context.getController().makeBuilder();
    ParseTreeBuilder bottomUpTreeBuilder = new ParseTreeBuilder();
    builder.open();
    IXML parser = new IXML(input, bottomUpTreeBuilder);
    try
    {
      parser.parse_ixml();
      bottomUpTreeBuilder.serialize(new SaxonTreeBuilder(builder));
    }
    catch (ParseException pe)
    {
      buildError(parser, pe, builder);
    }
    return builder.getCurrentRoot();
  }

  public static class SaxonDefinition_ixml extends SaxonDefinition
  {
    @Override
    public String functionName() {return "parse-ixml";}
    @Override
    public Sequence execute(XPathContext context, String input) throws XPathException
    {
      return parseIxml(context, input);
    }
  }

  public static abstract class SaxonDefinition extends ExtensionFunctionDefinition
  {
    abstract String functionName();
    abstract Sequence execute(XPathContext context, String input) throws XPathException;

    @Override
    public StructuredQName getFunctionQName() {return new StructuredQName("p", "de/bottlecaps/convert/ixml/IXML", functionName());}
    @Override
    public SequenceType[] getArgumentTypes() {return new SequenceType[] {SequenceType.SINGLE_STRING};}
    @Override
    public SequenceType getResultType(SequenceType[] suppliedArgumentTypes) {return SequenceType.SINGLE_NODE;}

    @Override
    public ExtensionFunctionCall makeCallExpression()
    {
      return new ExtensionFunctionCall()
      {
        @Override
        public Sequence call(XPathContext context, Sequence[] arguments) throws XPathException
        {
          return execute(context, arguments[0].iterate().next().getStringValue());
        }
      };
    }
  }

  private static void buildError(IXML parser, ParseException pe, Builder builder) throws XPathException
  {
    builder.close();
    builder.reset();
    builder.open();
    List<AttributeInfo> attributes = new ArrayList<>();
    AnySimpleType anySimpleType = AnySimpleType.getInstance();
    attributes.add(new AttributeInfo(new NoNamespaceName("b"), anySimpleType, Integer.toString(pe.getBegin() + 1), LOCATION, 0));
    attributes.add(new AttributeInfo(new NoNamespaceName("e"), anySimpleType, Integer.toString(pe.getEnd() + 1), LOCATION, 0));
    if (pe.getOffending() < 0)
    {
      attributes.add(new AttributeInfo(new NoNamespaceName("s"), anySimpleType, Integer.toString(pe.getState()), LOCATION, 0));
    }
    else
    {
      attributes.add(new AttributeInfo(new NoNamespaceName("o"), anySimpleType, Integer.toString(pe.getOffending()), LOCATION, 0));
      attributes.add(new AttributeInfo(new NoNamespaceName("x"), anySimpleType, Integer.toString(pe.getExpected()), LOCATION, 0));
    }
    builder.startElement(new NoNamespaceName("ERROR"), AnyType.getInstance(), new SmallAttributeMap(attributes), NO_NAMESPACES, LOCATION, 0);
    builder.characters(StringView.of(parser.getErrorMessage(pe)), LOCATION, 0);
    builder.endElement();
  }

  public static ANode parseIxml(Str str) throws IOException
  {
    BaseXFunction baseXFunction = new BaseXFunction()
    {
      @Override
      public void execute(IXML p) {p.parse_ixml();}
    };
    return baseXFunction.call(str);
  }

  public static abstract class BaseXFunction
  {
    protected abstract void execute(IXML p);

    public ANode call(Str str) throws IOException
    {
      String input = str.toJava();
      SingleParser singleParser = new SingleParser(new IOContent(""), new MainOptions())
      {
        @Override
        protected void parse() throws IOException {}
      };
      MemBuilder memBuilder = new MemBuilder(input, singleParser);
      memBuilder.init();
      BaseXTreeBuilder treeBuilder = new BaseXTreeBuilder(memBuilder);
      ParseTreeBuilder bottomUpTreeBuilder = new ParseTreeBuilder();
      IXML parser = new IXML();
      parser.initialize(input, bottomUpTreeBuilder);
      try
      {
        execute(parser);
        bottomUpTreeBuilder.serialize(treeBuilder);
      }
      catch (ParseException pe)
      {
        memBuilder = new MemBuilder(input, singleParser);
        memBuilder.init();
        Atts atts = new Atts();
        atts.add(Token.token("b"), Token.token(pe.getBegin() + 1));
        atts.add(Token.token("e"), Token.token(pe.getEnd() + 1));
        if (pe.getOffending() < 0)
        {
          atts.add(Token.token("s"), Token.token(pe.getState()));
        }
        else
        {
          atts.add(Token.token("o"), Token.token(pe.getOffending()));
          atts.add(Token.token("x"), Token.token(pe.getExpected()));
        }
        memBuilder.openElem(Token.token("ERROR"), atts, new Atts());
        memBuilder.text(Token.token(parser.getErrorMessage(pe)));
        memBuilder.closeElem();
      }
      return new DBNode(memBuilder.data());
    }
  }

  public static class BaseXTreeBuilder implements EventHandler
  {
    private CharSequence input;
    private MemBuilder builder;
    private Atts nsp = new Atts();
    private Atts atts = new Atts();

    public BaseXTreeBuilder(MemBuilder b)
    {
      input = null;
      builder = b;
    }

    @Override
    public void reset(CharSequence string)
    {
      input = string;
    }

    @Override
    public void startNonterminal(String name, int begin)
    {
      try
      {
        builder.openElem(Token.token(name), atts, nsp);
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void endNonterminal(String name, int end)
    {
      try
      {
        builder.closeElem();
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void terminal(String name, int begin, int end)
    {
      if (name.charAt(0) == '\'')
      {
        name = "TOKEN";
      }
      startNonterminal(name, begin);
      characters(begin, end);
      endNonterminal(name, end);
    }

    @Override
    public void whitespace(int begin, int end)
    {
      characters(begin, end);
    }

    private void characters(int begin, int end)
    {
      if (begin < end)
      {
        try
        {
          builder.text(Token.token(input.subSequence(begin, end).toString()));
        }
        catch (IOException e)
        {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public IXML()
  {
  }

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

      for (int code = result & 63; code != 0; )
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

        int i0 = (charclass << 6) + code - 1;
        code = TRANSITION[(i0 & 3) + TRANSITION[i0 >> 2]];

        if (code > 63)
        {
          result = code;
          code &= 63;
          end = current;
        }
      }

      result >>= 6;
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
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 63;
    for (int i = 0; i < 34; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 53 + s - 1;
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
    /*   0 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 2, 3,
    /*  35 */ 4, 2, 2, 2, 5, 6, 7, 8, 9, 10, 11, 12, 2, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 15, 2, 16, 2, 17,
    /*  64 */ 18, 19, 19, 19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
    /*  90 */ 20, 21, 2, 22, 23, 24, 2, 25, 25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26,
    /* 117 */ 26, 26, 26, 26, 26, 26, 27, 28, 29, 30, 2
  };

  private static final int[] MAP1 =
  {
    /*    0 */ 432, 580, 1309, 1309, 1309, 463, 1245, 448, 1309, 493, 537, 512, 642, 1270, 688, 1201, 1339, 1351, 553,
    /*   19 */ 596, 612, 628, 658, 674, 714, 749, 765, 796, 568, 823, 839, 855, 871, 902, 1309, 1309, 698, 918, 945,
    /*   39 */ 961, 1308, 1309, 1309, 1309, 496, 982, 998, 1014, 527, 1030, 1488, 1460, 1054, 1070, 1092, 1108, 1563,
    /*   57 */ 1076, 1309, 886, 1309, 1309, 1380, 1124, 1140, 477, 1156, 1172, 1173, 1173, 1173, 1173, 1173, 1173, 1173,
    /*   75 */ 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1173, 1189, 733, 1217, 1233, 807,
    /*   93 */ 1173, 1173, 1173, 1261, 1286, 1302, 1325, 1173, 1173, 1173, 1173, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  110 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  127 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  144 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1038, 1309, 1309, 1309, 1309, 1309,
    /*  161 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  178 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  195 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  212 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  229 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  246 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  263 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  280 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  297 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  314 */ 1309, 1309, 1309, 1309, 1309, 966, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1367, 1309,
    /*  331 */ 1309, 1396, 1412, 728, 1428, 1451, 780, 1476, 1504, 929, 1520, 1536, 1435, 1309, 1309, 1309, 1309, 1309,
    /*  349 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  366 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  383 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  400 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309,
    /*  417 */ 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1309, 1552, 1585, 1579,
    /*  434 */ 1585, 1585, 1593, 1601, 1625, 1631, 1639, 1646, 1646, 1651, 1659, 1666, 1666, 1671, 2161, 1702, 1812,
    /*  451 */ 1812, 1992, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1995, 1812, 1812, 1812, 1812, 1812,
    /*  468 */ 1812, 1812, 1812, 2080, 1812, 1818, 2159, 1815, 2173, 2159, 2159, 1812, 1815, 2159, 2159, 2159, 2159,
    /*  485 */ 2159, 2159, 2095, 2098, 2050, 2095, 2102, 2159, 1679, 1810, 1812, 1812, 1812, 1812, 1812, 1812, 1812,
    /*  502 */ 1812, 1812, 1812, 1812, 1812, 1812, 1812, 2185, 1812, 1812, 1812, 2159, 2094, 2095, 2095, 2095, 2095,
    /*  519 */ 1717, 1689, 2159, 1812, 1812, 1812, 1817, 1817, 2159, 2052, 2095, 2101, 1812, 1812, 1812, 1812, 1812,
    /*  536 */ 1812, 1812, 1812, 1812, 1812, 1812, 2159, 1811, 1812, 1812, 1812, 1813, 2230, 1811, 1812, 1812, 1812,
    /*  553 */ 2061, 1812, 1812, 1812, 1812, 1812, 1812, 1731, 2094, 2258, 1715, 1812, 1771, 2095, 1811, 1811, 1812,
    /*  570 */ 1812, 1812, 1812, 1812, 1897, 2100, 2129, 2096, 2095, 2101, 2159, 2159, 2159, 2159, 2158, 2229, 1608,
    /*  587 */ 2229, 1812, 1812, 1813, 1812, 1812, 1812, 1813, 1812, 1976, 2185, 1809, 1812, 1812, 1993, 1789, 1804,
    /*  604 */ 2114, 1826, 2159, 2205, 1771, 2095, 1818, 2159, 2217, 1835, 1809, 1812, 1812, 1993, 1882, 2318, 2309,
    /*  621 */ 1937, 2246, 1889, 2035, 2095, 1844, 2159, 2217, 1995, 1992, 1812, 1812, 1993, 1885, 1804, 1681, 2258,
    /*  638 */ 2231, 2159, 1771, 2095, 2159, 2159, 2095, 2100, 1812, 1812, 1812, 1812, 1812, 2294, 2095, 2095, 2095,
    /*  655 */ 1941, 2128, 1812, 1976, 2185, 1809, 1812, 1812, 1993, 1885, 1855, 2114, 2234, 2233, 2205, 1771, 2095,
    /*  672 */ 2230, 2159, 1829, 1873, 1832, 1878, 1737, 1873, 1812, 1818, 2102, 2234, 2231, 2159, 2035, 2095, 2159,
    /*  689 */ 2159, 2127, 1812, 1812, 1812, 2095, 2095, 2095, 1616, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812,
    /*  706 */ 1812, 1832, 1813, 1832, 1812, 1812, 1812, 1812, 1920, 1989, 1993, 1812, 1812, 1993, 1990, 2143, 2018,
    /*  723 */ 2113, 2036, 1818, 1771, 2095, 2159, 2159, 2160, 1812, 1810, 1812, 1812, 1812, 1812, 1812, 1812, 1812,
    /*  740 */ 1812, 1812, 1812, 1812, 1812, 1815, 1781, 2078, 2159, 1920, 1989, 1993, 1812, 1812, 1993, 1990, 1855,
    /*  757 */ 2233, 2037, 2159, 2161, 1771, 2095, 1739, 2159, 1920, 1989, 1993, 1812, 1812, 1812, 1812, 1905, 2114,
    /*  774 */ 1826, 2159, 2159, 1771, 2095, 2159, 1810, 1812, 1812, 1812, 1812, 1812, 1816, 2159, 2235, 2159, 2095,
    /*  791 */ 2101, 2095, 2095, 1747, 2228, 1920, 1812, 1813, 1810, 1812, 1812, 1992, 1997, 1813, 2245, 1952, 2159,
    /*  808 */ 2159, 2159, 2159, 2159, 2160, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 1794, 1796,
    /*  825 */ 1921, 1811, 1701, 1697, 1897, 1913, 1996, 2097, 2095, 1617, 2159, 2159, 2159, 2159, 2231, 2159, 2159,
    /*  842 */ 2101, 2095, 2101, 1930, 2246, 1812, 1811, 1812, 1812, 1812, 1815, 2094, 2096, 1718, 1750, 2095, 2094,
    /*  859 */ 2095, 2095, 2095, 2098, 2233, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 1812, 1812, 1812, 1812, 1812,
    /*  876 */ 1949, 2093, 1865, 2095, 2101, 1814, 1962, 1735, 1919, 1900, 1812, 1812, 1812, 1812, 1812, 1812, 1812,
    /*  893 */ 1812, 2095, 2095, 2095, 2095, 2096, 2159, 2159, 2020, 1847, 1826, 2095, 1972, 1812, 1812, 1812, 1812,
    /*  910 */ 1995, 2162, 1812, 1812, 1812, 1812, 1812, 1991, 1812, 1832, 1812, 1812, 1812, 1812, 1832, 1813, 1832,
    /*  927 */ 1812, 1813, 1812, 1812, 1812, 1812, 1812, 1762, 1861, 2159, 2125, 2254, 2095, 2101, 1812, 1812, 1813,
    /*  944 */ 2229, 1812, 1812, 1832, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1949, 2159, 2159, 2159, 2159,
    /*  961 */ 1812, 1812, 2159, 2159, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1815, 2159, 2159,
    /*  978 */ 2159, 2159, 2159, 2159, 1986, 1812, 1812, 1817, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812,
    /*  995 */ 1817, 2159, 2159, 1812, 1989, 2005, 2159, 1812, 1812, 2005, 2159, 1812, 1812, 2015, 2159, 1812, 1989,
    /* 1012 */ 2308, 2159, 1812, 1812, 1812, 1812, 1812, 1812, 1964, 2097, 2233, 2094, 2115, 2029, 2095, 2101, 2159,
    /* 1029 */ 2159, 1812, 1812, 1812, 1812, 1812, 2282, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1814, 2159,
    /* 1046 */ 2159, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 1812, 1812, 2129, 2102, 1812, 1812, 1812, 1812, 1812,
    /* 1063 */ 1812, 2045, 2096, 1935, 2098, 2021, 2297, 2095, 2101, 2095, 2101, 2160, 2159, 2159, 2159, 2159, 2159,
    /* 1080 */ 2159, 2159, 2159, 2159, 2159, 2159, 2111, 2095, 2093, 2123, 2138, 2159, 2060, 1812, 1812, 1812, 1812,
    /* 1097 */ 1812, 2070, 1954, 2261, 1816, 2095, 2101, 2159, 2021, 2099, 2159, 2062, 1812, 1812, 1812, 1613, 1693,
    /* 1114 */ 2095, 1747, 1812, 1812, 1812, 1812, 2130, 2088, 2101, 2159, 1812, 1812, 1812, 1812, 1812, 1812, 1989,
    /* 1131 */ 1996, 2264, 1815, 2186, 1816, 1812, 1815, 2264, 1815, 2151, 2156, 2159, 2159, 2159, 2103, 2159, 2232,
    /* 1148 */ 2102, 2159, 2159, 2103, 2159, 2159, 2230, 2160, 2170, 1810, 1997, 2184, 2173, 1888, 1812, 2194, 1920,
    /* 1165 */ 1836, 2159, 2159, 2159, 2159, 2159, 2159, 1737, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 2159,
    /* 1182 */ 2159, 2159, 2159, 2159, 2159, 2159, 2159, 1812, 1812, 1812, 1812, 1812, 1813, 1812, 1812, 1812, 1812,
    /* 1199 */ 1812, 1813, 1812, 1812, 1812, 1812, 1749, 2095, 2283, 2159, 2095, 1747, 1812, 1812, 1812, 2294, 2076,
    /* 1216 */ 2229, 1812, 1812, 1812, 1812, 1995, 2162, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 2160, 2159, 2232,
    /* 1233 */ 1812, 1812, 1813, 2159, 1813, 1813, 1813, 1813, 1813, 1813, 1813, 1813, 2095, 2095, 2095, 2095, 2095,
    /* 1250 */ 2095, 2095, 2095, 2095, 2095, 2095, 2095, 2095, 2095, 1989, 2196, 2204, 2159, 2159, 2159, 2159, 1775,
    /* 1267 */ 2184, 1737, 1811, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1710, 2297, 1726, 2074,
    /* 1284 */ 2095, 1903, 1812, 1812, 1813, 2217, 1811, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812,
    /* 1301 */ 1991, 1920, 1812, 1812, 1812, 1812, 1814, 1811, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812,
    /* 1318 */ 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1812, 1813, 2159, 2159, 1812, 1812, 1812, 1817, 2159, 2159,
    /* 1335 */ 2159, 2159, 2159, 2159, 1812, 1812, 1749, 1760, 1758, 2296, 2159, 2159, 1812, 1812, 1812, 2006, 2159,
    /* 1352 */ 2159, 2159, 2159, 1993, 1815, 2159, 2159, 2159, 2159, 2159, 2159, 2020, 2095, 2095, 2096, 1812, 1815,
    /* 1369 */ 2159, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 1812, 1812, 1812, 1812, 1812, 1814, 1814, 1812, 1812,
    /* 1386 */ 1812, 1812, 1814, 1814, 1812, 2176, 1812, 1812, 1812, 1814, 1812, 1815, 1812, 1812, 2095, 2078, 2159,
    /* 1403 */ 2159, 1812, 1812, 1812, 1812, 1812, 2129, 2020, 1763, 1812, 1812, 1812, 2232, 1812, 1812, 1812, 1812,
    /* 1420 */ 1812, 1812, 1812, 1812, 1814, 2159, 2101, 2159, 1812, 2195, 1816, 2159, 1812, 1817, 2159, 2159, 2159,
    /* 1437 */ 2159, 2159, 2159, 2159, 2159, 2159, 1812, 1812, 1812, 1812, 2317, 2258, 2095, 2101, 2277, 2125, 1812,
    /* 1454 */ 1812, 2213, 2159, 2159, 2159, 1812, 1812, 1812, 1812, 1812, 1812, 1816, 2159, 2159, 1811, 2159, 2095,
    /* 1471 */ 2101, 2159, 2159, 2159, 2159, 2095, 1747, 1812, 1812, 1749, 2097, 1812, 1812, 2129, 2095, 2101, 2159,
    /* 1488 */ 1812, 1812, 1812, 1815, 2007, 2102, 2245, 1939, 2035, 2095, 1812, 1812, 1812, 1814, 1815, 2159, 2061,
    /* 1505 */ 1812, 1812, 1812, 1812, 1812, 1846, 2243, 2159, 2160, 2095, 2101, 2159, 2159, 2159, 2159, 1812, 1812,
    /* 1522 */ 1812, 1812, 1812, 1812, 2272, 2291, 2282, 2159, 2159, 1978, 1812, 2306, 2220, 2159, 1994, 1994, 1994,
    /* 1539 */ 2159, 1813, 1813, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 2159, 1812, 1812, 1812, 1812,
    /* 1556 */ 1816, 2159, 1812, 1812, 1813, 1922, 1812, 1812, 1812, 1812, 1812, 1816, 2020, 2298, 2159, 2095, 1779,
    /* 1573 */ 2095, 1747, 1812, 1812, 1812, 1814, 0, 33, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 33, 2, 3, 4, 2, 2, 2, 5,
    /* 1601 */ 6, 7, 8, 9, 10, 11, 12, 2, 2, 2, 2, 2, 31, 2, 32, 32, 32, 32, 2, 2, 31, 31, 31, 31, 13, 13, 13, 13, 13,
    /* 1630 */ 13, 13, 13, 14, 15, 2, 16, 2, 17, 18, 19, 19, 19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 20, 21, 2, 22,
    /* 1657 */ 23, 24, 2, 25, 25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 26, 26, 27, 28, 29, 30, 2, 31, 31, 2, 32, 32,
    /* 1684 */ 32, 32, 32, 2, 32, 2, 32, 32, 2, 32, 32, 2, 32, 2, 2, 31, 31, 2, 31, 31, 31, 2, 31, 2, 31, 31, 31, 31,
    /* 1712 */ 31, 31, 2, 31, 32, 32, 32, 32, 32, 32, 32, 2, 32, 32, 32, 32, 32, 32, 32, 31, 31, 32, 2, 32, 31, 2, 2, 2,
    /* 1740 */ 31, 31, 2, 2, 2, 2, 2, 32, 32, 31, 31, 31, 31, 31, 31, 32, 32, 32, 32, 32, 32, 32, 31, 32, 32, 32, 32,
    /* 1767 */ 32, 32, 2, 31, 31, 31, 32, 32, 2, 2, 32, 32, 32, 32, 2, 2, 2, 31, 31, 31, 31, 32, 31, 2, 31, 2, 2, 2, 31,
    /* 1796 */ 31, 2, 31, 2, 2, 31, 2, 2, 31, 31, 2, 2, 32, 31, 2, 2, 31, 31, 31, 31, 31, 31, 31, 31, 2, 2, 2, 2, 2, 2,
    /* 1826 */ 2, 2, 2, 2, 2, 32, 31, 2, 31, 31, 31, 31, 2, 2, 2, 2, 31, 2, 32, 32, 31, 31, 31, 32, 2, 2, 32, 32, 2, 31,
    /* 1856 */ 31, 2, 2, 32, 31, 2, 32, 32, 2, 2, 32, 32, 2, 2, 32, 32, 31, 31, 31, 31, 2, 2, 2, 31, 31, 2, 31, 2, 31,
    /* 1885 */ 31, 2, 31, 31, 2, 31, 31, 31, 31, 2, 31, 2, 31, 32, 31, 31, 32, 32, 32, 32, 31, 31, 31, 2, 2, 31, 2, 2,
    /* 1913 */ 32, 32, 2, 32, 32, 31, 2, 2, 2, 2, 2, 2, 31, 31, 31, 31, 31, 2, 2, 2, 2, 2, 32, 2, 32, 2, 2, 32, 32, 32,
    /* 1943 */ 2, 2, 2, 2, 31, 31, 31, 31, 31, 2, 2, 32, 32, 32, 2, 32, 2, 2, 2, 32, 32, 31, 31, 31, 31, 32, 32, 2, 32,
    /* 1972 */ 32, 32, 2, 2, 2, 32, 2, 2, 2, 31, 31, 31, 2, 2, 33, 31, 31, 31, 31, 31, 31, 31, 2, 31, 31, 31, 31, 31,
    /* 2000 */ 31, 2, 31, 2, 2, 31, 31, 32, 32, 32, 2, 2, 2, 2, 32, 31, 31, 32, 32, 2, 2, 2, 2, 2, 32, 32, 32, 32, 32,
    /* 2029 */ 2, 2, 2, 2, 31, 32, 2, 2, 2, 2, 2, 2, 32, 32, 2, 2, 31, 31, 31, 31, 31, 2, 32, 2, 2, 2, 32, 32, 32, 33,
    /* 2059 */ 2, 32, 32, 32, 32, 2, 31, 31, 31, 31, 31, 31, 31, 31, 31, 32, 2, 32, 32, 32, 32, 31, 31, 2, 2, 2, 2, 31,
    /* 2087 */ 31, 32, 32, 2, 2, 2, 32, 2, 32, 32, 32, 32, 32, 32, 32, 32, 2, 2, 2, 2, 2, 2, 2, 33, 32, 32, 32, 2, 32,
    /* 2116 */ 32, 32, 32, 2, 2, 2, 31, 32, 31, 31, 31, 31, 32, 31, 31, 31, 31, 31, 31, 31, 32, 2, 31, 31, 2, 2, 32, 31,
    /* 2144 */ 31, 2, 2, 2, 31, 32, 32, 33, 33, 33, 33, 33, 33, 33, 33, 2, 2, 2, 2, 2, 2, 2, 2, 31, 2, 2, 2, 2, 31, 2,
    /* 2174 */ 2, 2, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 31, 31, 31, 31, 2, 2, 31, 31, 31, 31, 2, 2, 31, 31, 31, 31, 2,
    /* 2203 */ 2, 33, 2, 2, 2, 2, 31, 31, 2, 31, 31, 31, 31, 2, 2, 32, 32, 2, 2, 31, 31, 31, 2, 32, 2, 2, 2, 2, 31, 2,
    /* 2233 */ 2, 2, 2, 2, 2, 2, 32, 2, 2, 2, 32, 32, 2, 2, 32, 2, 2, 2, 2, 2, 2, 31, 31, 31, 31, 32, 2, 2, 2, 2, 32, 2,
    /* 2265 */ 2, 31, 31, 31, 2, 31, 31, 32, 31, 32, 32, 32, 31, 31, 32, 31, 31, 31, 32, 31, 2, 2, 2, 2, 2, 2, 32, 31,
    /* 2293 */ 31, 31, 31, 31, 32, 32, 32, 32, 32, 2, 2, 32, 32, 31, 31, 31, 2, 32, 32, 2, 2, 2, 2, 32, 31, 31, 31, 2,
    /* 2321 */ 2, 32, 2, 2, 2
  };

  private static final int[] MAP2 =
  {
    /*    0 */ 57344, 63744, 64110, 64112, 64218, 64256, 64263, 64275, 64280, 64285, 64286, 64287, 64297, 64298, 64311,
    /*   15 */ 64312, 64317, 64318, 64319, 64320, 64322, 64323, 64325, 64326, 64434, 64467, 64830, 64848, 64912, 64914,
    /*   30 */ 64968, 65008, 65020, 65024, 65040, 65056, 65063, 65136, 65141, 65142, 65277, 65296, 65306, 65313, 65339,
    /*   45 */ 65345, 65371, 65382, 65471, 65474, 65480, 65482, 65488, 65490, 65496, 65498, 65501, 65536, 65548, 65549,
    /*   60 */ 65575, 65576, 65595, 65596, 65598, 65599, 65614, 65616, 65630, 65664, 65787, 66045, 66046, 66176, 66205,
    /*   75 */ 66208, 66257, 66304, 66335, 66352, 66369, 66370, 66378, 66432, 66462, 66464, 66500, 66504, 66512, 66560,
    /*   90 */ 66718, 66720, 66730, 67584, 67590, 67592, 67593, 67594, 67638, 67639, 67641, 67644, 67645, 67647, 67670,
    /*  105 */ 67840, 67862, 67872, 67898, 67968, 68024, 68030, 68032, 68096, 68097, 68100, 68101, 68103, 68108, 68112,
    /*  120 */ 68116, 68117, 68120, 68121, 68148, 68152, 68155, 68159, 68160, 68192, 68221, 68352, 68406, 68416, 68438,
    /*  135 */ 68448, 68467, 68608, 68681, 69633, 69634, 69635, 69688, 69703, 69734, 69744, 69760, 69762, 69763, 69808,
    /*  150 */ 69811, 69815, 69817, 69819, 69840, 69865, 69872, 69882, 69888, 69891, 69927, 69932, 69933, 69941, 69942,
    /*  165 */ 69952, 70016, 70018, 70019, 70067, 70070, 70079, 70081, 70085, 70096, 70106, 71296, 71339, 71340, 71341,
    /*  180 */ 71342, 71344, 71350, 71351, 71352, 71360, 71370, 73728, 74607, 77824, 78895, 92160, 92729, 93952, 94021,
    /*  195 */ 94032, 94033, 94095, 94099, 94112, 110592, 110594, 119143, 119146, 119163, 119171, 119173, 119180,
    /*  208 */ 119210, 119214, 119362, 119365, 119808, 119893, 119894, 119965, 119966, 119968, 119970, 119971, 119973,
    /*  221 */ 119975, 119977, 119981, 119982, 119994, 119995, 119996, 119997, 120004, 120005, 120070, 120071, 120075,
    /*  234 */ 120077, 120085, 120086, 120093, 120094, 120122, 120123, 120127, 120128, 120133, 120134, 120135, 120138,
    /*  247 */ 120145, 120146, 120486, 120488, 120513, 120514, 120539, 120540, 120571, 120572, 120597, 120598, 120629,
    /*  260 */ 120630, 120655, 120656, 120687, 120688, 120713, 120714, 120745, 120746, 120771, 120772, 120780, 120782,
    /*  273 */ 120832, 126464, 126468, 126469, 126496, 126497, 126499, 126500, 126501, 126503, 126504, 126505, 126515,
    /*  286 */ 126516, 126520, 126521, 126522, 126523, 126524, 126530, 126531, 126535, 126536, 126537, 126538, 126539,
    /*  299 */ 126540, 126541, 126544, 126545, 126547, 126548, 126549, 126551, 126552, 126553, 126554, 126555, 126556,
    /*  312 */ 126557, 126558, 126559, 126560, 126561, 126563, 126564, 126565, 126567, 126571, 126572, 126579, 126580,
    /*  325 */ 126584, 126585, 126589, 126590, 126591, 126592, 126602, 126603, 126620, 126625, 126628, 126629, 126634,
    /*  338 */ 126635, 126652, 131072, 173783, 173824, 177973, 177984, 178206, 194560, 195102, 917760, 918000, 63743,
    /*  351 */ 64109, 64111, 64217, 64255, 64262, 64274, 64279, 64284, 64285, 64286, 64296, 64297, 64310, 64311, 64316,
    /*  366 */ 64317, 64318, 64319, 64321, 64322, 64324, 64325, 64433, 64466, 64829, 64847, 64911, 64913, 64967, 65007,
    /*  381 */ 65019, 65023, 65039, 65055, 65062, 65135, 65140, 65141, 65276, 65295, 65305, 65312, 65338, 65344, 65370,
    /*  396 */ 65381, 65470, 65473, 65479, 65481, 65487, 65489, 65495, 65497, 65500, 65533, 65547, 65548, 65574, 65575,
    /*  411 */ 65594, 65595, 65597, 65598, 65613, 65615, 65629, 65663, 65786, 66044, 66045, 66175, 66204, 66207, 66256,
    /*  426 */ 66303, 66334, 66351, 66368, 66369, 66377, 66431, 66461, 66463, 66499, 66503, 66511, 66559, 66717, 66719,
    /*  441 */ 66729, 67583, 67589, 67591, 67592, 67593, 67637, 67638, 67640, 67643, 67644, 67646, 67669, 67839, 67861,
    /*  456 */ 67871, 67897, 67967, 68023, 68029, 68031, 68095, 68096, 68099, 68100, 68102, 68107, 68111, 68115, 68116,
    /*  471 */ 68119, 68120, 68147, 68151, 68154, 68158, 68159, 68191, 68220, 68351, 68405, 68415, 68437, 68447, 68466,
    /*  486 */ 68607, 68680, 69632, 69633, 69634, 69687, 69702, 69733, 69743, 69759, 69761, 69762, 69807, 69810, 69814,
    /*  501 */ 69816, 69818, 69839, 69864, 69871, 69881, 69887, 69890, 69926, 69931, 69932, 69940, 69941, 69951, 70015,
    /*  516 */ 70017, 70018, 70066, 70069, 70078, 70080, 70084, 70095, 70105, 71295, 71338, 71339, 71340, 71341, 71343,
    /*  531 */ 71349, 71350, 71351, 71359, 71369, 73727, 74606, 77823, 78894, 92159, 92728, 93951, 94020, 94031, 94032,
    /*  546 */ 94094, 94098, 94111, 110591, 110593, 119142, 119145, 119162, 119170, 119172, 119179, 119209, 119213,
    /*  559 */ 119361, 119364, 119807, 119892, 119893, 119964, 119965, 119967, 119969, 119970, 119972, 119974, 119976,
    /*  572 */ 119980, 119981, 119993, 119994, 119995, 119996, 120003, 120004, 120069, 120070, 120074, 120076, 120084,
    /*  585 */ 120085, 120092, 120093, 120121, 120122, 120126, 120127, 120132, 120133, 120134, 120137, 120144, 120145,
    /*  598 */ 120485, 120487, 120512, 120513, 120538, 120539, 120570, 120571, 120596, 120597, 120628, 120629, 120654,
    /*  611 */ 120655, 120686, 120687, 120712, 120713, 120744, 120745, 120770, 120771, 120779, 120781, 120831, 126463,
    /*  624 */ 126467, 126468, 126495, 126496, 126498, 126499, 126500, 126502, 126503, 126504, 126514, 126515, 126519,
    /*  637 */ 126520, 126521, 126522, 126523, 126529, 126530, 126534, 126535, 126536, 126537, 126538, 126539, 126540,
    /*  650 */ 126543, 126544, 126546, 126547, 126548, 126550, 126551, 126552, 126553, 126554, 126555, 126556, 126557,
    /*  663 */ 126558, 126559, 126560, 126562, 126563, 126564, 126566, 126570, 126571, 126578, 126579, 126583, 126584,
    /*  676 */ 126588, 126589, 126590, 126591, 126601, 126602, 126619, 126624, 126627, 126628, 126633, 126634, 126651,
    /*  689 */ 131071, 173782, 173823, 177972, 177983, 178205, 194559, 195101, 917759, 917999, 1114111, 2, 31, 2, 31, 2,
    /*  705 */ 31, 2, 31, 2, 31, 32, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 32, 2,
    /*  735 */ 32, 2, 31, 2, 31, 2, 32, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2,
    /*  765 */ 31, 2, 31, 2, 31, 2, 32, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 32, 2, 31, 2,
    /*  795 */ 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 32, 2, 32, 2, 32, 31, 2, 31, 2, 31, 2,
    /*  825 */ 32, 2, 32, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 32, 2, 31, 32, 2, 32, 2, 32, 2, 31, 2, 32, 2, 32, 2, 31,
    /*  855 */ 2, 32, 2, 32, 31, 32, 2, 32, 2, 32, 2, 32, 2, 31, 2, 32, 2, 31, 2, 32, 2, 31, 32, 2, 32, 2, 32, 2, 32, 2,
    /*  885 */ 32, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 32, 31, 2, 31, 2, 32, 2, 32, 2, 32, 2, 32, 2, 32, 2, 31, 2, 31,
    /*  915 */ 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31,
    /*  945 */ 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 32, 2, 31,
    /*  975 */ 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31,
    /* 1005 */ 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31,
    /* 1035 */ 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 31, 2, 32, 2
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
    /* 29 */ 30, 31, 32, 861, 33, 34, 35, 36, 867, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 883
  };

  private static final int[] TRANSITION =
  {
    /*   0 */ 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 544, 551,
    /*  21 */ 561, 558, 626, 558, 560, 545, 560, 560, 546, 546, 546, 771, 546, 770, 565, 546, 546, 546, 546, 546, 546,
    /*  42 */ 546, 546, 546, 546, 546, 546, 572, 546, 581, 589, 546, 576, 590, 574, 591, 577, 596, 595, 597, 546, 546,
    /*  63 */ 546, 771, 546, 770, 601, 546, 609, 602, 607, 603, 610, 615, 614, 616, 546, 546, 546, 620, 640, 630, 638,
    /*  84 */ 546, 645, 639, 650, 652, 646, 655, 654, 656, 772, 546, 546, 771, 546, 770, 565, 546, 546, 546, 546, 546,
    /* 105 */ 547, 661, 660, 662, 546, 546, 546, 771, 787, 770, 565, 786, 785, 546, 785, 787, 668, 669, 667, 666, 546,
    /* 126 */ 546, 546, 771, 546, 770, 565, 546, 546, 546, 546, 783, 782, 546, 784, 780, 546, 546, 546, 771, 546, 770,
    /* 147 */ 565, 546, 546, 546, 546, 807, 806, 546, 808, 804, 546, 546, 546, 771, 546, 770, 565, 546, 809, 546, 809,
    /* 168 */ 676, 675, 546, 677, 673, 546, 546, 546, 771, 711, 770, 565, 710, 546, 712, 683, 713, 710, 681, 681, 682,
    /* 189 */ 546, 546, 546, 771, 849, 770, 565, 850, 849, 546, 849, 851, 687, 825, 689, 691, 546, 546, 546, 695, 546,
    /* 210 */ 770, 565, 546, 546, 546, 546, 546, 546, 546, 709, 546, 546, 546, 546, 771, 546, 717, 565, 847, 546, 546,
    /* 231 */ 546, 546, 546, 546, 848, 846, 546, 546, 546, 771, 546, 770, 721, 722, 728, 722, 872, 730, 729, 723, 734,
    /* 252 */ 724, 546, 546, 546, 771, 546, 738, 565, 870, 546, 546, 546, 546, 546, 546, 871, 869, 546, 546, 546, 771,
    /* 273 */ 546, 770, 565, 546, 546, 546, 546, 887, 886, 546, 888, 884, 546, 546, 546, 771, 546, 770, 565, 546, 546,
    /* 294 */ 546, 742, 774, 773, 750, 749, 751, 546, 546, 546, 695, 546, 770, 565, 763, 546, 762, 762, 756, 755, 760,
    /* 315 */ 760, 761, 546, 546, 546, 771, 546, 770, 565, 763, 546, 762, 762, 756, 755, 760, 760, 761, 546, 546, 546,
    /* 336 */ 771, 546, 767, 778, 546, 584, 546, 584, 546, 585, 792, 791, 793, 546, 546, 546, 771, 546, 770, 797, 566,
    /* 357 */ 568, 798, 568, 566, 567, 546, 546, 567, 546, 546, 546, 771, 546, 770, 565, 546, 546, 802, 819, 816, 641,
    /* 378 */ 813, 817, 818, 546, 546, 546, 771, 546, 770, 565, 823, 546, 823, 823, 633, 634, 829, 829, 830, 546, 546,
    /* 399 */ 546, 695, 546, 770, 565, 624, 546, 834, 624, 622, 835, 839, 839, 840, 546, 546, 546, 771, 546, 770, 565,
    /* 420 */ 624, 546, 834, 624, 622, 835, 839, 839, 840, 546, 546, 546, 771, 546, 770, 704, 844, 704, 701, 698, 701,
    /* 441 */ 703, 705, 703, 703, 546, 546, 546, 771, 546, 770, 855, 890, 857, 890, 889, 859, 858, 891, 863, 892, 546,
    /* 462 */ 546, 546, 771, 546, 770, 867, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 546, 771, 546, 770,
    /* 483 */ 565, 546, 553, 546, 553, 546, 554, 877, 876, 878, 546, 546, 546, 771, 546, 770, 565, 882, 546, 882, 882,
    /* 504 */ 744, 745, 896, 896, 897, 546, 546, 546, 771, 546, 770, 565, 546, 546, 546, 546, 546, 546, 546, 709, 546,
    /* 525 */ 546, 546, 546, 771, 546, 770, 544, 551, 561, 558, 626, 558, 560, 545, 560, 560, 546, 546, 546, 128, 1024,
    /* 546 */ 0, 0, 0, 0, 448, 0, 1024, 0, 0, 768, 0, 768, 1024, 0, 1024, 1024, 1024, 0, 0, 128, 0, 0, 0, 576, 0, 0, 52,
    /* 573 */ 256, 0, 1216, 1216, 0, 1216, 0, 1216, 0, 1268, 256, 0, 0, 1984, 0, 1984, 128, 0, 1216, 0, 0, 1216, 0,
    /* 596 */ 1216, 1216, 1216, 1216, 192, 128, 0, 384, 0, 0, 384, 0, 384, 384, 0, 384, 0, 384, 0, 384, 384, 384, 384,
    /* 619 */ 0, 192, 53, 0, 0, 960, 0, 0, 0, 1024, 1024, 0, 192, 1333, 0, 0, 2048, 0, 2048, 128, 0, 1280, 0, 0, 0, 640,
    /* 645 */ 1280, 0, 1280, 0, 1280, 0, 1280, 1280, 0, 0, 1280, 1280, 1280, 1280, 0, 0, 448, 448, 448, 448, 0, 1344,
    /* 667 */ 1344, 1344, 0, 1344, 0, 1344, 0, 1536, 1536, 0, 1536, 0, 0, 1536, 1600, 1600, 1600, 1600, 0, 0, 1664, 0,
    /* 689 */ 1664, 0, 1664, 1664, 1664, 0, 192, 256, 323, 0, 0, 2112, 2112, 0, 2112, 2112, 2112, 0, 0, 0, 1152, 0, 0,
    /* 712 */ 0, 1600, 0, 1600, 1600, 0, 192, 256, 1728, 128, 0, 0, 1792, 1792, 1792, 0, 0, 1792, 0, 1792, 0, 0, 1792,
    /* 735 */ 1792, 1792, 1792, 0, 192, 256, 1856, 512, 0, 0, 0, 1088, 0, 1088, 0, 512, 512, 512, 512, 0, 0, 896, 0,
    /* 758 */ 896, 896, 896, 896, 896, 896, 0, 0, 0, 1984, 192, 256, 0, 192, 256, 0, 0, 0, 512, 0, 128, 1984, 0, 0,
    /* 782 */ 1408, 0, 1408, 0, 0, 0, 1344, 0, 0, 0, 1984, 1984, 1984, 1984, 0, 128, 0, 0, 576, 576, 0, 640, 0, 0, 1472,
    /* 807 */ 0, 1472, 0, 0, 0, 1536, 640, 640, 640, 640, 0, 640, 640, 640, 0, 0, 2048, 0, 0, 0, 1664, 1664, 2048, 2048,
    /* 831 */ 2048, 2048, 0, 960, 0, 960, 0, 960, 960, 960, 960, 960, 0, 0, 2112, 0, 0, 1728, 0, 0, 0, 1664, 0, 0, 128,
    /* 856 */ 0, 0, 2176, 0, 2176, 0, 0, 2176, 2176, 2176, 2176, 704, 0, 0, 0, 1856, 0, 0, 0, 1792, 0, 768, 768, 768,
    /* 880 */ 768, 0, 1088, 0, 0, 0, 1920, 0, 1920, 0, 0, 0, 2176, 2176, 2176, 0, 1088, 1088, 1088, 1088, 0
  };

  private static final int[] EXPECTED =
  {
    /*   0 */ 4, 8, 16, 262144, 524288, 1048576, 16777216, 33554432, 1073741824, 262148, 524296, 335544320, 1026,
    /*  13 */ 1073774592, 786464, 134217984, -2147393536, 335577088, 168820736, 150995200, 819232, 134250752,
    /*  22 */ 1074530336, 177209344, -2147360768, 17572384, 134267136, 151027968, -2130615680, 17572640, 1074563104,
    /*  31 */ 177242112, 17605152, 720371712, -2130582912, 17605408, 720404480, -1072830432, 720404736, -1056085280,
    /*  40 */ -1056052512, -920818976, -888313120, -887264544, -1074536448, -920786208, -888280352, -878875936,
    /*  48 */ -887231776, -878843168, -136224, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 2, 0, 1, 2, 2, 1, 3,
    /*  75 */ 0, 2, 1, 0, 3, 3, 0, 0, 1, 3, 1, 2, 1, 1, 3, 1, 3, 0, 1, 2, 2, 2, 3, 3, 3, 2, 3, 3, 3, 0, 0
  };

  private static final int[] CASEID =
  {
    /*   0 */ 442, 228, 232, 407, 239, 233, 519, 243, 247, 252, 233, 232, 255, 228, 232, 264, 312, 232, 248, 235, 232,
    /*  21 */ 286, 259, 263, 418, 239, 233, 298, 268, 233, 425, 272, 247, 449, 281, 285, 264, 312, 232, 248, 432, 247,
    /*  42 */ 336, 293, 232, 425, 290, 247, 297, 233, 233, 302, 233, 233, 248, 234, 232, 495, 310, 232, 298, 268, 233,
    /*  63 */ 425, 307, 247, 425, 317, 232, 425, 321, 247, 425, 321, 247, 248, 439, 247, 456, 325, 233, 449, 329, 285,
    /*  84 */ 354, 432, 247, 233, 303, 233, 466, 333, 233, 449, 340, 285, 449, 344, 233, 449, 348, 352, 449, 358, 362,
    /* 105 */ 354, 432, 247, 354, 432, 247, 459, 275, 232, 354, 313, 247, 368, 233, 233, 374, 233, 233, 248, 432, 247,
    /* 126 */ 248, 432, 247, 529, 380, 233, 485, 310, 232, 459, 275, 232, 354, 364, 247, 541, 333, 233, 473, 386, 233,
    /* 147 */ 354, 364, 247, 354, 364, 247, 354, 313, 247, 233, 277, 233, 233, 370, 233, 233, 482, 392, 233, 492, 398,
    /* 168 */ 233, 502, 404, 509, 411, 415, 516, 233, 233, 233, 422, 233, 297, 429, 233, 233, 436, 233, 302, 446, 233,
    /* 189 */ 233, 526, 233, 382, 531, 453, 233, 475, 233, 388, 376, 463, 538, 233, 233, 297, 470, 233, 302, 479, 233,
    /* 210 */ 400, 394, 489, 499, 233, 233, 506, 233, 233, 513, 233, 233, 233, 523, 233, 233, 535, 233, 550, 546, 550,
    /* 231 */ 547, 545, 546, 546, 546, 546, 548, 550, 599, 546, 556, 622, 562, 564, 564, 564, 569, 546, 546, 546, 551,
    /* 252 */ 812, 546, 814, 546, 547, 549, 565, 587, 579, 593, 595, 581, 546, 546, 546, 572, 599, 546, 546, 622, 562,
    /* 273 */ 546, 561, 571, 546, 546, 546, 583, 692, 606, 546, 618, 644, 620, 546, 546, 546, 576, 562, 550, 570, 571,
    /* 294 */ 546, 546, 548, 733, 546, 546, 546, 597, 772, 546, 546, 546, 634, 562, 550, 561, 571, 546, 550, 546, 546,
    /* 315 */ 547, 546, 562, 546, 550, 571, 562, 570, 561, 571, 628, 546, 546, 634, 606, 621, 638, 644, 699, 546, 649,
    /* 336 */ 546, 549, 547, 551, 606, 621, 618, 644, 606, 546, 660, 644, 606, 669, 666, 644, 668, 546, 546, 546, 550,
    /* 357 */ 551, 606, 676, 673, 644, 675, 546, 546, 546, 570, 546, 739, 546, 546, 546, 697, 546, 783, 546, 546, 546,
    /* 378 */ 704, 546, 628, 546, 546, 546, 771, 546, 745, 546, 546, 546, 788, 688, 712, 546, 546, 546, 802, 546, 720,
    /* 399 */ 546, 546, 546, 805, 546, 731, 546, 546, 546, 557, 555, 597, 757, 546, 546, 662, 752, 546, 546, 546, 557,
    /* 420 */ 555, 604, 682, 546, 546, 546, 563, 796, 572, 656, 546, 546, 546, 564, 796, 549, 753, 546, 546, 546, 570,
    /* 441 */ 796, 546, 547, 549, 572, 693, 546, 546, 546, 610, 614, 597, 776, 546, 546, 546, 632, 650, 546, 549, 546,
    /* 462 */ 551, 787, 546, 546, 546, 642, 648, 654, 589, 546, 546, 546, 686, 546, 546, 782, 546, 600, 546, 546, 546,
    /* 483 */ 703, 708, 546, 549, 549, 798, 804, 546, 546, 546, 721, 718, 546, 549, 626, 798, 778, 546, 546, 546, 725,
    /* 504 */ 729, 737, 789, 546, 546, 546, 743, 661, 749, 546, 809, 546, 546, 761, 546, 546, 563, 561, 565, 714, 546,
    /* 525 */ 546, 546, 766, 546, 546, 632, 546, 546, 762, 546, 767, 546, 546, 546, 793, 546, 546, 642, 680, 654, 24946,
    /* 546 */ 0, 0, 0, 0, 8406, 0, 0, 0, 16626, 0, 32776, 0, 0, 0, 32776, 8406, 8406, 0, 8406, 8406, 8406, 8406, 16626,
    /* 569 */ 24946, 8406, 0, 0, 8406, 8406, 16626, 0, 147464, 147464, 140630, 140630, 140630, 140630, 0, 0, 82610, 0,
    /* 587 */ 147464, 155656, 0, 0, 32782, 0, 155656, 14, 140630, 140630, 0, 147464, 147464, 0, 0, 0, 49166, 198,
    /* 605 */ 147464, 147464, 0, 131090, 139282, 0, 476754, 122898, 32776, 0, 466962, 0, 149282, 466962, 49156, 0,
    /* 621 */ 49156, 0, 0, 0, 147464, 8406, 8406, 0, 0, 131090, 139282, 0, 476754, 0, 0, 157538, 0, 466962, 0, 0, 49156,
    /* 642 */ 0, 550498, 0, 0, 157538, 147464, 338002, 163848, 0, 0, 0, 149282, 0, 540690, 0, 0, 181654, 0, 466962, 0,
    /* 662 */ 0, 0, 163860, 32788, 466962, 73780, 0, 73780, 0, 0, 73780, 466962, 81972, 0, 81972, 0, 0, 81972, 0,
    /* 681 */ 163848, 0, 0, 286738, 0, 0, 598034, 0, 0, 286744, 286740, 82594, 0, 0, 0, 189926, 0, 25322, 0, 0, 319506,
    /* 702 */ 327698, 41798, 0, 0, 0, 286740, 0, 41798, 0, 172882, 0, 172898, 0, 0, 401426, 0, 0, 50166, 0, 50166, 0, 0,
    /* 724 */ 181250, 57364, 188434, 196626, 57364, 0, 57364, 0, 57364, 0, 0, 492978, 0, 0, 204818, 0, 0, 558530, 0, 0,
    /* 744 */ 163860, 0, 0, 581650, 589842, 0, 32788, 32788, 16398, 0, 0, 0, 294930, 32788, 0, 163860, 163860, 231026,
    /* 762 */ 0, 0, 0, 362466, 344082, 0, 0, 0, 409618, 338002, 0, 0, 0, 509442, 0, 362482, 0, 0, 606226, 0, 368658, 0,
    /* 784 */ 0, 0, 566802, 286740, 286740, 0, 0, 0, 614418, 378498, 0, 0, 0, 8406, 0, 8406, 0, 16626, 270388, 0, 0,
    /* 805 */ 198326, 0, 0, 0, 417810, 0, 0, 0, 25010, 0, 0, 16938, 0
  };

  private static final int[] TOKENSET =
  {
    /*  0 */ 35, 28, 52, 12, 37, 24, 17, 46, 32, 16, 48, 44, 24, 38, 30, 47, 0, 1, 13, 36, 16, 50, 42, 51, 51, 31, 22,
    /* 27 */ 43, 40, 8, 29, 45, 41, 49, 49, 40, 40, 20, 21, 0, 1, 38, 38, 14, 33, 20, 27, 25, 14, 27, 27, 21, 11, 7, 18,
    /* 55 */ 23, 34, 39, 2, 3, 9, 4, 10, 5, 15, 6, 26, 2, 9, 10, 19, 0, 1, 2, 3, 4
  };

  private static final int[] APPENDIX =
  {
    /* 0 */ 70315, 77828, 16394, 81930, 90827, 200713, 94963, 204809
  };

  private static final int[] LOOKBACK =
  {
    /*   0 */ 138, 138, 138, 136, 136, 136, 139, 144, 154, 154, 154, 149, 157, 160, 165, 165, 165, 165, 170, 170, 170,
    /*  21 */ 170, 138, 138, 138, 138, 175, 175, 175, 175, 182, 182, 182, 182, 189, 196, 138, 199, 202, 205, 205, 205,
    /*  42 */ 208, 211, 214, 219, 222, 225, 138, 138, 138, 138, 228, 233, 238, 243, 248, 248, 248, 248, 138, 138, 138,
    /*  63 */ 251, 256, 261, 264, 264, 264, 138, 267, 270, 273, 276, 138, 138, 138, 279, 284, 289, 294, 299, 138, 138,
    /*  84 */ 138, 302, 307, 307, 307, 312, 317, 317, 317, 317, 322, 327, 327, 327, 327, 138, 332, 337, 337, 342, 347,
    /* 105 */ 352, 357, 362, 138, 367, 370, 373, 138, 376, 379, 382, 385, 388, 138, 391, 391, 391, 394, 394, 394, 394,
    /* 126 */ 404, 409, 414, 419, 419, 419, 419, 397, 422, 425, 2, 2, 0, 9, 8, 4, 3, 0, 9, 9, 4, 4, 0, 9, 10, 4, 5, 0,
    /* 154 */ 7, 6, 0, 7, 11, 0, 19, 18, 15, 14, 0, 19, 20, 15, 16, 0, 19, 21, 15, 17, 0, 31, 30, 27, 26, 23, 22, 0, 31,
    /* 183 */ 32, 27, 28, 23, 24, 0, 31, 33, 27, 29, 23, 25, 0, 34, 34, 0, 36, 36, 0, 37, 37, 0, 38, 38, 0, 40, 39, 0,
    /* 211 */ 40, 41, 0, 43, 43, 42, 42, 0, 44, 44, 0, 45, 45, 0, 46, 46, 0, 57, 56, 49, 48, 0, 57, 58, 49, 50, 0, 57,
    /* 239 */ 59, 49, 51, 0, 54, 54, 53, 53, 0, 55, 55, 0, 67, 66, 61, 60, 0, 67, 68, 61, 62, 0, 64, 64, 0, 65, 65, 0,
    /* 267 */ 69, 69, 0, 70, 70, 0, 71, 71, 0, 72, 72, 0, 76, 74, 75, 74, 0, 76, 75, 75, 75, 0, 76, 76, 75, 75, 0, 79,
    /* 295 */ 77, 78, 77, 0, 80, 80, 0, 87, 86, 83, 82, 0, 87, 88, 83, 84, 0, 92, 90, 91, 90, 0, 92, 93, 91, 93, 0, 97,
    /* 323 */ 95, 96, 95, 0, 97, 98, 96, 98, 0, 76, 99, 75, 99, 0, 79, 100, 78, 100, 0, 102, 101, 101, 101, 0, 102, 102,
    /* 349 */ 101, 101, 0, 104, 103, 103, 103, 0, 104, 104, 103, 103, 0, 106, 105, 105, 105, 0, 76, 108, 0, 79, 109, 0,
    /* 373 */ 110, 110, 0, 76, 112, 0, 79, 113, 0, 114, 114, 0, 115, 115, 0, 116, 116, 0, 118, 118, 0, 120, 119, 0, 130,
    /* 398 */ 132, 123, 125, 120, 121, 0, 130, 129, 123, 122, 0, 130, 131, 123, 124, 0, 127, 127, 126, 126, 0, 128, 128,
    /* 421 */ 0, 133, 133, 0, 134, 134, 0
  };

  private static final int[] GOTO =
  {
    /*   0 */ 130, 131, 234, 153, 131, 136, 131, 272, 131, 131, 141, 131, 254, 131, 131, 146, 131, 131, 194, 131, 151,
    /*  21 */ 155, 131, 153, 131, 160, 131, 132, 228, 232, 165, 131, 266, 131, 131, 170, 131, 176, 131, 131, 131, 180,
    /*  42 */ 131, 147, 131, 186, 131, 188, 131, 131, 131, 192, 200, 131, 131, 198, 131, 206, 131, 131, 204, 131, 260,
    /*  63 */ 131, 131, 131, 210, 137, 228, 232, 214, 131, 131, 131, 220, 142, 278, 284, 228, 232, 226, 230, 156, 238,
    /*  84 */ 232, 242, 230, 172, 131, 131, 174, 131, 222, 131, 131, 246, 131, 131, 161, 131, 131, 252, 182, 131, 131,
    /* 105 */ 258, 131, 131, 166, 131, 264, 131, 248, 131, 131, 270, 131, 131, 276, 282, 131, 131, 288, 131, 131, 290,
    /* 126 */ 131, 216, 131, 131, 294, 305, 305, 305, 305, 310, 351, 305, 305, 305, 364, 434, 305, 305, 305, 369, 431,
    /* 147 */ 305, 305, 305, 374, 360, 304, 305, 305, 297, 305, 305, 305, 392, 446, 305, 305, 305, 394, 321, 305, 305,
    /* 168 */ 305, 455, 330, 305, 305, 305, 324, 305, 305, 305, 333, 305, 305, 394, 305, 305, 336, 305, 339, 305, 305,
    /* 189 */ 305, 342, 305, 449, 305, 305, 305, 380, 305, 404, 305, 305, 305, 401, 305, 416, 305, 305, 305, 407, 305,
    /* 210 */ 305, 455, 305, 359, 327, 305, 305, 305, 410, 305, 305, 368, 305, 305, 422, 305, 305, 388, 314, 300, 318,
    /* 231 */ 349, 355, 359, 305, 305, 306, 304, 398, 461, 318, 349, 305, 392, 398, 465, 419, 305, 305, 305, 428, 305,
    /* 252 */ 305, 374, 305, 305, 440, 305, 437, 305, 305, 305, 443, 305, 425, 305, 305, 305, 452, 305, 469, 305, 305,
    /* 273 */ 305, 457, 305, 305, 369, 373, 378, 373, 472, 373, 479, 345, 305, 305, 384, 476, 305, 413, 305, 305, 305,
    /* 294 */ 6, 4113, 8345, 0, 24881, 29337, 0, 32772, 32772, 57348, 20769, 0, 0, 0, 0, 16417, 49705, 217449, 221577,
    /* 313 */ 225769, 229385, 28676, 28676, 28676, 61444, 53817, 29337, 0, 82481, 8345, 0, 86044, 8345, 0, 99149, 8345,
    /* 330 */ 0, 107129, 8345, 0, 110601, 8345, 0, 115765, 8345, 0, 119705, 8345, 0, 123841, 8345, 0, 126980, 118788,
    /* 348 */ 155657, 57953, 66181, 0, 0, 8345, 0, 61444, 57348, 102404, 102404, 107381, 0, 0, 0, 16457, 49705, 258057,
    /* 366 */ 221577, 225769, 111525, 0, 0, 0, 118788, 61444, 0, 0, 0, 249865, 152153, 66181, 0, 0, 77828, 29369, 49705,
    /* 385 */ 0, 221641, 225769, 49705, 0, 0, 225817, 49705, 0, 0, 0, 241673, 0, 49156, 0, 0, 0, 127417, 8345, 0,
    /* 405 */ 131593, 8345, 0, 135177, 8345, 0, 135188, 8345, 0, 135196, 8345, 0, 139273, 8345, 0, 139276, 8345, 0,
    /* 423 */ 176753, 8345, 0, 193537, 8345, 0, 196617, 8345, 0, 213313, 8345, 0, 32825, 8345, 0, 32804, 8345, 0, 37161,
    /* 442 */ 8345, 0, 45076, 8345, 0, 45409, 8345, 0, 53817, 29337, 0, 53829, 8345, 0, 66181, 0, 0, 12537, 0, 36892,
    /* 462 */ 32772, 32772, 57348, 40988, 32772, 32772, 57348, 0, 287569, 8345, 0, 263129, 118788, 266249, 122908,
    /* 477 */ 131076, 0, 0, 263185, 118788, 266249
  };

  private static final String[] TOKEN =
  {
    "(0)",
    "cchar",
    "dchar",
    "schar",
    "hex",
    "'#'",
    "'('",
    "'@'",
    "']'",
    "'^'",
    "'}'",
    "'~'",
    "END",
    "capital",
    "letter",
    "whitespace",
    "namestart1",
    "namefollower1",
    "'\"'",
    "''''",
    "')'",
    "'*'",
    "'+'",
    "','",
    "'-'",
    "'.'",
    "':'",
    "';'",
    "'='",
    "'?'",
    "'['",
    "'_'",
    "'{'",
    "'|'"
  };

  private static final String[] NONTERMINAL =
  {
    "ixml",
    "s",
    "comment",
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
    "terminal",
    "literal",
    "quoted",
    "name",
    "namestart",
    "namefollower",
    "tmark",
    "string",
    "dstring",
    "sstring",
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
    "code"
  };
}

// End
