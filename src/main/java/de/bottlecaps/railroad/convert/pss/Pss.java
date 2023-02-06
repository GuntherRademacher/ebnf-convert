// This file was generated on Wed Jan 11, 2023 23:03 (UTC+01) by REx v5.56 which is Copyright (c) 1979-2022 by Gunther Rademacher <grd@gmx.net>
// REx command line: -tree -a none -java -interface de.bottlecaps.railroad.convert.Parser -basex -saxon -name de.bottlecaps.railroad.convert.pss.Pss pss.ebnf

package de.bottlecaps.railroad.convert.pss;

import java.io.IOException;
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

public class Pss implements de.bottlecaps.railroad.convert.Parser
{
  public static class SaxonInitializer implements Initializer
  {
    @Override
    public void initialize(Configuration conf)
    {
      conf.registerExtensionFunction(new SaxonDefinition_PSS_Grammar());
    }
  }

  public static Sequence parsePSS_Grammar(XPathContext context, String input) throws XPathException
  {
    Builder builder = context.getController().makeBuilder();
    builder.open();
    Pss parser = new Pss(input, new SaxonTreeBuilder(builder));
    try
    {
      parser.parse_PSS_Grammar();
    }
    catch (ParseException pe)
    {
      buildError(parser, pe, builder);
    }
    return builder.getCurrentRoot();
  }

  public static class SaxonDefinition_PSS_Grammar extends SaxonDefinition
  {
    @Override
    public String functionName() {return "parse-PSS-Grammar";}
    @Override
    public Sequence execute(XPathContext context, String input) throws XPathException
    {
      return parsePSS_Grammar(context, input);
    }
  }

  public static abstract class SaxonDefinition extends ExtensionFunctionDefinition
  {
    abstract String functionName();
    abstract Sequence execute(XPathContext context, String input) throws XPathException;

    @Override
    public StructuredQName getFunctionQName() {return new StructuredQName("p", "de/bottlecaps/railroad/convert/pss/Pss", functionName());}
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

  private static void buildError(Pss parser, ParseException pe, Builder builder) throws XPathException
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

  public static ANode parsePSSGrammar(Str str) throws IOException
  {
    BaseXFunction baseXFunction = new BaseXFunction()
    {
      @Override
      public void execute(Pss p) {p.parse_PSS_Grammar();}
    };
    return baseXFunction.call(str);
  }

  public static abstract class BaseXFunction
  {
    protected abstract void execute(Pss p);

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
      Pss parser = new Pss();
      parser.initialize(input, treeBuilder);
      try
      {
        execute(parser);
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

  public Pss()
  {
  }

  public Pss(CharSequence string, EventHandler t)
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
    parse_PSS_Grammar();
  }

  public void parse_PSS_Grammar()
  {
    eventHandler.startNonterminal("PSS-Grammar", e0);
    for (;;)
    {
      lookahead1W(0);               // Whitespace | Nonterminal
      whitespace();
      parse_Rule();
      lookahead1W(1);               // Whitespace | Nonterminal | EOF
      if (l1 != 2)                  // Nonterminal
      {
        break;
      }
    }
    consume(4);                     // EOF
    eventHandler.endNonterminal("PSS-Grammar", e0);
  }

  private void parse_Rule()
  {
    eventHandler.startNonterminal("Rule", e0);
    consume(2);                     // Nonterminal
    lookahead1W(2);                 // Whitespace | ':' | '::=' | '='
    switch (l1)
    {
    case 12:                        // '='
      consume(12);                  // '='
      break;
    case 9:                         // ':'
      consume(9);                   // ':'
      break;
    default:
      consume(10);                  // '::='
    }
    lookahead1W(7);                 // Whitespace | Nonterminal | Terminal | EOF | '(' | ';' | '[' | '{' | '|' | 'ε'
    whitespace();
    parse_Alternatives();
    if (l1 == 11)                   // ';'
    {
      consume(11);                  // ';'
    }
    eventHandler.endNonterminal("Rule", e0);
  }

  private void parse_Alternatives()
  {
    eventHandler.startNonterminal("Alternatives", e0);
    parse_Alternative();
    for (;;)
    {
      lookahead1W(3);               // Whitespace | Nonterminal | EOF | ')' | ';' | ']' | '|' | '}'
      if (l1 != 17)                 // '|'
      {
        break;
      }
      consume(17);                  // '|'
      lookahead1W(9);               // Whitespace | Nonterminal | Terminal | EOF | '(' | ')' | ';' | '[' | ']' | '{' |
                                    // '|' | '}' | 'ε'
      whitespace();
      parse_Alternative();
    }
    eventHandler.endNonterminal("Alternatives", e0);
  }

  private void parse_Alternative()
  {
    eventHandler.startNonterminal("Alternative", e0);
    switch (l1)
    {
    case 2:                         // Nonterminal
      lookahead2W(11);              // Whitespace | Nonterminal | Terminal | EOF | '(' | ')' | '*' | '+' | ':' | '::=' |
                                    // ';' | '=' | '?' | '[' | ']' | '{' | '|' | '}'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 4:                         // EOF
    case 6:                         // ')'
    case 11:                        // ';'
    case 15:                        // ']'
    case 17:                        // '|'
    case 18:                        // '}'
    case 19:                        // 'ε'
    case 290:                       // Nonterminal ':'
    case 322:                       // Nonterminal '::='
    case 386:                       // Nonterminal '='
      if (l1 == 19)                 // 'ε'
      {
        consume(19);                // 'ε'
      }
      break;
    default:
      for (;;)
      {
        whitespace();
        parse_Item();
        lookahead1W(8);             // Whitespace | Nonterminal | Terminal | EOF | '(' | ')' | ';' | '[' | ']' | '{' |
                                    // '|' | '}'
        switch (l1)
        {
        case 2:                     // Nonterminal
          lookahead2W(11);          // Whitespace | Nonterminal | Terminal | EOF | '(' | ')' | '*' | '+' | ':' | '::=' |
                                    // ';' | '=' | '?' | '[' | ']' | '{' | '|' | '}'
          break;
        default:
          lk = l1;
        }
        if (lk == 4                 // EOF
         || lk == 6                 // ')'
         || lk == 11                // ';'
         || lk == 15                // ']'
         || lk == 17                // '|'
         || lk == 18                // '}'
         || lk == 290               // Nonterminal ':'
         || lk == 322               // Nonterminal '::='
         || lk == 386)              // Nonterminal '='
        {
          break;
        }
      }
    }
    eventHandler.endNonterminal("Alternative", e0);
  }

  private void parse_Item()
  {
    eventHandler.startNonterminal("Item", e0);
    switch (l1)
    {
    case 2:                         // Nonterminal
      consume(2);                   // Nonterminal
      break;
    case 3:                         // Terminal
      consume(3);                   // Terminal
      break;
    case 14:                        // '['
      consume(14);                  // '['
      lookahead1W(5);               // Whitespace | Nonterminal | Terminal | '(' | '[' | ']' | '{' | '|' | 'ε'
      whitespace();
      parse_Alternatives();
      consume(15);                  // ']'
      break;
    case 16:                        // '{'
      consume(16);                  // '{'
      lookahead1W(6);               // Whitespace | Nonterminal | Terminal | '(' | '[' | '{' | '|' | '}' | 'ε'
      whitespace();
      parse_Alternatives();
      consume(18);                  // '}'
      break;
    default:
      consume(5);                   // '('
      lookahead1W(4);               // Whitespace | Nonterminal | Terminal | '(' | ')' | '[' | '{' | '|' | 'ε'
      whitespace();
      parse_Alternatives();
      consume(6);                   // ')'
    }
    lookahead1W(10);                // Whitespace | Nonterminal | Terminal | EOF | '(' | ')' | '*' | '+' | ';' | '?' |
                                    // '[' | ']' | '{' | '|' | '}'
    if (l1 == 7                     // '*'
     || l1 == 8                     // '+'
     || l1 == 13)                   // '?'
    {
      switch (l1)
      {
      case 7:                       // '*'
        consume(7);                 // '*'
        break;
      case 8:                       // '+'
        consume(8);                 // '+'
        break;
      default:
        consume(13);                // '?'
      }
    }
    eventHandler.endNonterminal("Item", e0);
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
      if (code != 1)                // Whitespace
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
        int c1 = c0 >> 4;
        charclass = MAP1[(c0 & 15) + MAP1[(c1 & 31) + MAP1[c1 >> 5]]];
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

        int lo = 0, hi = 5;
        for (int m = 3; ; m = (hi + lo) >> 1)
        {
          if (MAP2[m] > c0) {hi = m - 1;}
          else if (MAP2[6 + m] < c0) {lo = m + 1;}
          else {charclass = MAP2[12 + m]; break;}
          if (lo > hi) {charclass = 0; break;}
        }
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
    for (int i = 0; i < 20; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 19 + s - 1;
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
    /*   0 */ 24, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5,
    /*  35 */ 6, 4, 4, 4, 7, 8, 9, 10, 11, 4, 12, 12, 4, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 14, 4, 15, 4, 16,
    /*  64 */ 4, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
    /*  91 */ 18, 4, 19, 4, 17, 4, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
    /* 118 */ 17, 17, 17, 17, 17, 20, 21, 22, 4, 4
  };

  private static final int[] MAP1 =
  {
    /*   0 */ 108, 124, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 156, 181, 181, 181, 181,
    /*  21 */ 181, 214, 215, 213, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214,
    /*  42 */ 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214,
    /*  63 */ 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214,
    /*  84 */ 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214, 214,
    /* 105 */ 214, 214, 214, 247, 261, 277, 391, 315, 293, 315, 332, 368, 368, 368, 360, 316, 308, 316, 308, 316, 316,
    /* 126 */ 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 385, 385, 385, 385, 385, 385, 385,
    /* 147 */ 413, 316, 316, 316, 407, 316, 316, 316, 316, 346, 368, 368, 369, 367, 368, 368, 316, 316, 316, 316, 316,
    /* 168 */ 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 368, 368, 368, 368, 368, 368, 368, 368,
    /* 189 */ 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368, 368,
    /* 210 */ 368, 368, 368, 315, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316,
    /* 231 */ 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 316, 368, 24, 0, 0, 0, 0, 0, 0, 0,
    /* 255 */ 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 6, 4, 4, 4, 7, 8, 9, 10, 11, 4,
    /* 290 */ 12, 12, 4, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 4, 19, 4, 17, 17, 17, 17, 17, 17, 17, 4, 17,
    /* 317 */ 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
    /* 343 */ 20, 21, 22, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 17, 17, 4, 4, 4, 4, 4, 4, 4, 12, 4, 4, 4, 4, 4, 4, 4, 4,
    /* 376 */ 4, 4, 4, 4, 4, 4, 4, 4, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 14, 4, 15,
    /* 405 */ 4, 16, 17, 17, 17, 17, 17, 23, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 4, 17
  };

  private static final int[] MAP2 =
  {
    /*  0 */ 57344, 63744, 64976, 65008, 65536, 983040, 63743, 64975, 65007, 65533, 983039, 1114111, 4, 17, 4, 17, 17,
    /* 17 */ 4
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12
  };

  private static final int[] TRANSITION =
  {
    /*   0 */ 204, 204, 204, 204, 204, 204, 204, 204, 200, 200, 200, 209, 226, 204, 204, 204, 200, 200, 200, 203, 204,
    /*  21 */ 204, 204, 204, 200, 200, 200, 209, 204, 204, 204, 204, 204, 204, 204, 216, 226, 204, 204, 204, 204, 223,
    /*  42 */ 223, 216, 232, 204, 204, 204, 242, 242, 242, 216, 226, 204, 204, 204, 204, 246, 246, 216, 250, 204, 204,
    /*  63 */ 204, 204, 257, 257, 216, 226, 204, 204, 204, 265, 264, 261, 216, 226, 204, 204, 204, 204, 204, 272, 216,
    /*  84 */ 226, 204, 204, 204, 204, 204, 287, 216, 226, 204, 204, 204, 204, 204, 204, 269, 226, 204, 204, 204, 212,
    /* 105 */ 204, 211, 276, 226, 204, 204, 204, 228, 228, 280, 216, 226, 204, 204, 204, 219, 204, 218, 216, 284, 204,
    /* 126 */ 204, 204, 204, 204, 291, 216, 226, 204, 204, 204, 297, 295, 295, 269, 226, 204, 204, 204, 204, 301, 301,
    /* 147 */ 216, 226, 204, 204, 204, 234, 236, 305, 216, 226, 204, 204, 204, 204, 309, 309, 216, 226, 204, 204, 204,
    /* 168 */ 238, 313, 313, 216, 226, 204, 204, 204, 252, 253, 317, 216, 226, 204, 204, 204, 327, 321, 325, 269, 226,
    /* 189 */ 204, 204, 204, 331, 205, 334, 204, 204, 204, 204, 204, 77, 77, 77, 77, 0, 0, 0, 0, 160, 77, 78, 0, 0, 0,
    /* 214 */ 336, 0, 0, 78, 0, 0, 0, 416, 0, 17, 17, 17, 17, 18, 0, 0, 0, 384, 128, 18, 0, 0, 0, 512, 0, 0, 0, 576, 78,
    /* 243 */ 78, 78, 78, 18, 18, 18, 18, 17, 128, 0, 0, 0, 608, 0, 192, 192, 192, 192, 224, 224, 224, 224, 0, 0, 0,
    /* 268 */ 224, 0, 78, 111, 0, 0, 256, 256, 0, 78, 0, 19, 384, 384, 384, 384, 17, 18, 352, 0, 0, 288, 288, 0, 0, 448,
    /* 294 */ 448, 111, 111, 111, 111, 0, 111, 480, 480, 480, 480, 512, 512, 512, 512, 544, 544, 544, 544, 576, 576,
    /* 315 */ 576, 576, 608, 608, 608, 608, 655, 655, 655, 655, 15, 655, 15, 15, 0, 15, 0, 160, 0, 160, 160, 160, 160
  };

  private static final int[] EXPECTED =
  {
    /*  0 */ 6, 22, 5634, 428118, 737390, 770094, 999470, 739390, 510078, 1034366, 518654, 524286, 2, 2, 4, 1024, 8, 8,
    /* 18 */ 1024
  };

  private static final String[] TOKEN =
  {
    "(0)",
    "Whitespace",
    "Nonterminal",
    "Terminal",
    "EOF",
    "'('",
    "')'",
    "'*'",
    "'+'",
    "':'",
    "'::='",
    "';'",
    "'='",
    "'?'",
    "'['",
    "']'",
    "'{'",
    "'|'",
    "'}'",
    "'ε'"
  };
}

// End
