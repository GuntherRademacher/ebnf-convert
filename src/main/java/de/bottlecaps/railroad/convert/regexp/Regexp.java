// This file was generated on Sat Jan 7, 2023 21:33 (UTC+01) by REx v5.56 which is Copyright (c) 1979-2022 by Gunther Rademacher <grd@gmx.net>
// REx command line: -tree -a none -java -interface de.bottlecaps.railroad.convert.Parser -basex -saxon -name de.bottlecaps.railroad.convert.regexp.Regexp regexp.ebnf

package de.bottlecaps.railroad.convert.regexp;

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

public class Regexp implements de.bottlecaps.railroad.convert.Parser
{
  public static class SaxonInitializer implements Initializer
  {
    @Override
    public void initialize(Configuration conf)
    {
      conf.registerExtensionFunction(new SaxonDefinition_regexp());
    }
  }

  public static Sequence parseRegexp(XPathContext context, String input) throws XPathException
  {
    Builder builder = context.getController().makeBuilder();
    builder.open();
    Regexp parser = new Regexp(input, new SaxonTreeBuilder(builder));
    try
    {
      parser.parse_regexp();
    }
    catch (ParseException pe)
    {
      buildError(parser, pe, builder);
    }
    return builder.getCurrentRoot();
  }

  public static class SaxonDefinition_regexp extends SaxonDefinition
  {
    @Override
    public String functionName() {return "parse-regexp";}
    @Override
    public Sequence execute(XPathContext context, String input) throws XPathException
    {
      return parseRegexp(context, input);
    }
  }

  public static abstract class SaxonDefinition extends ExtensionFunctionDefinition
  {
    abstract String functionName();
    abstract Sequence execute(XPathContext context, String input) throws XPathException;

    @Override
    public StructuredQName getFunctionQName() {return new StructuredQName("p", "de/bottlecaps/railroad/convert/regexp/Regexp", functionName());}
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

  private static void buildError(Regexp parser, ParseException pe, Builder builder) throws XPathException
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

  public static ANode parseRegexp(Str str) throws IOException
  {
    BaseXFunction baseXFunction = new BaseXFunction()
    {
      @Override
      public void execute(Regexp p) {p.parse_regexp();}
    };
    return baseXFunction.call(str);
  }

  public static abstract class BaseXFunction
  {
    protected abstract void execute(Regexp p);

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
      Regexp parser = new Regexp();
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

  public Regexp()
  {
  }

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
    "(0)",
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
