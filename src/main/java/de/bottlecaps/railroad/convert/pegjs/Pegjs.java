// This file was generated on Sat Jul 17, 2021 11:14 (UTC+02) by REx v5.53 which is Copyright (c) 1979-2021 by Gunther Rademacher <grd@gmx.net>
// REx command line: -tree -a none -java -interface de.bottlecaps.railroad.convert.Parser -saxon10 -name de.bottlecaps.railroad.convert.pegjs.Pegjs pegjs.ebnf

package de.bottlecaps.railroad.convert.pegjs;

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

public class Pegjs implements de.bottlecaps.railroad.convert.Parser
{
  public static class SaxonInitializer implements Initializer
  {
    @Override
    public void initialize(Configuration conf)
    {
      conf.registerExtensionFunction(new SaxonDefinition_Grammar());
    }
  }

  public static Sequence parseGrammar(XPathContext context, String input) throws XPathException
  {
    Builder builder = context.getController().makeBuilder();
    builder.open();
    Pegjs parser = new Pegjs(input, new SaxonTreeBuilder(builder));
    try
    {
      parser.parse_Grammar();
    }
    catch (ParseException pe)
    {
      buildError(parser, pe, builder);
    }
    return builder.getCurrentRoot();
  }

  public static class SaxonDefinition_Grammar extends SaxonDefinition
  {
    @Override
    public String functionName() {return "parse-Grammar";}
    @Override
    public Sequence execute(XPathContext context, String input) throws XPathException
    {
      return parseGrammar(context, input);
    }
  }

  public static abstract class SaxonDefinition extends ExtensionFunctionDefinition
  {
    abstract String functionName();
    abstract Sequence execute(XPathContext context, String input) throws XPathException;

    @Override
    public StructuredQName getFunctionQName() {return new StructuredQName("p", "Pegjs", functionName());}
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

  private static void buildError(Pegjs parser, ParseException pe, Builder builder) throws XPathException
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
    builder.characters(parser.getErrorMessage(pe), LOCATION, 0);
    builder.endElement();
  }

  public Pegjs(CharSequence string, EventHandler t)
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
    l3 = 0; b3 = 0; e3 = 0;
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
    lookahead1W(7);                 // IdentifierName | WhiteSpace | Comment | '{'
    if (l1 == 28)                   // '{'
    {
      whitespace();
      parse_Initializer();
    }
    for (;;)
    {
      lookahead1W(3);               // IdentifierName | WhiteSpace | Comment
      whitespace();
      parse_Rule();
      lookahead1W(6);               // EOF | IdentifierName | WhiteSpace | Comment
      if (l1 != 3)                  // IdentifierName
      {
        break;
      }
    }
    consume(1);                     // EOF
    eventHandler.endNonterminal("Grammar", e0);
  }

  private void parse_Initializer()
  {
    eventHandler.startNonterminal("Initializer", e0);
    parse_JavaScriptCode();
    eventHandler.endNonterminal("Initializer", e0);
  }

  private void parse_JavaScriptCode()
  {
    eventHandler.startNonterminal("JavaScriptCode", e0);
    consume(28);                    // '{'
    for (;;)
    {
      lookahead1(2);                // NonBraceChars | '{' | '}'
      if (l1 == 29)                 // '}'
      {
        break;
      }
      switch (l1)
      {
      case 2:                       // NonBraceChars
        consume(2);                 // NonBraceChars
        break;
      default:
        parse_JavaScriptCode();
      }
    }
    consume(29);                    // '}'
    eventHandler.endNonterminal("JavaScriptCode", e0);
  }

  private void parse_Rule()
  {
    eventHandler.startNonterminal("Rule", e0);
    parse_Name();
    lookahead1W(10);                // StringLiteral | WhiteSpace | Comment | '<-' | '='
    if (l1 == 4)                    // StringLiteral
    {
      whitespace();
      parse_HumanReadableName();
    }
    lookahead1W(9);                 // WhiteSpace | Comment | '<-' | '='
    switch (l1)
    {
    case 21:                        // '='
      consume(21);                  // '='
      break;
    default:
      consume(20);                  // '<-'
    }
    lookahead1W(15);                // EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' | '&' | '(' |
                                    // '.' | '/' | ';' | '<' | '>' | '[' | '{'
    whitespace();
    parse_ParsingExpression();
    if (l1 == 18)                   // ';'
    {
      consume(18);                  // ';'
    }
    eventHandler.endNonterminal("Rule", e0);
  }

  private void parse_Name()
  {
    eventHandler.startNonterminal("Name", e0);
    consume(3);                     // IdentifierName
    eventHandler.endNonterminal("Name", e0);
  }

  private void parse_HumanReadableName()
  {
    eventHandler.startNonterminal("HumanReadableName", e0);
    consume(4);                     // StringLiteral
    eventHandler.endNonterminal("HumanReadableName", e0);
  }

  private void parse_ParsingExpression()
  {
    eventHandler.startNonterminal("ParsingExpression", e0);
    parse_Alternative();
    for (;;)
    {
      lookahead1W(11);              // EOF | IdentifierName | WhiteSpace | Comment | ')' | '/' | ';'
      if (l1 != 16)                 // '/'
      {
        break;
      }
      consume(16);                  // '/'
      lookahead1W(16);              // EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' | '&' | '(' |
                                    // ')' | '.' | '/' | ';' | '<' | '>' | '[' | '{'
      whitespace();
      parse_Alternative();
    }
    eventHandler.endNonterminal("ParsingExpression", e0);
  }

  private void parse_Alternative()
  {
    eventHandler.startNonterminal("Alternative", e0);
    for (;;)
    {
      lookahead1W(16);              // EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' | '&' | '(' |
                                    // ')' | '.' | '/' | ';' | '<' | '>' | '[' | '{'
      switch (l1)
      {
      case 3:                       // IdentifierName
        lookahead2W(20);            // EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' | '&' | '(' |
                                    // ')' | '*' | '+' | '.' | '/' | ':' | ';' | '<' | '<-' | '=' | '>' | '?' | '[' |
                                    // '{'
        switch (lk)
        {
        case 259:                   // IdentifierName StringLiteral
          lookahead3W(19);          // EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' | '&' | '(' |
                                    // ')' | '*' | '+' | '.' | '/' | ';' | '<' | '<-' | '=' | '>' | '?' | '[' | '{'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 1                   // EOF
       || lk == 12                  // ')'
       || lk == 16                  // '/'
       || lk == 18                  // ';'
       || lk == 28                  // '{'
       || lk == 1283                // IdentifierName '<-'
       || lk == 1347                // IdentifierName '='
       || lk == 82179               // IdentifierName StringLiteral '<-'
       || lk == 86275)              // IdentifierName StringLiteral '='
      {
        break;
      }
      switch (l1)
      {
      case 3:                       // IdentifierName
        lookahead2W(18);            // EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' | '&' | '(' |
                                    // ')' | '*' | '+' | '.' | '/' | ':' | ';' | '<' | '>' | '?' | '[' | '{'
        break;
      default:
        lk = l1;
      }
      if (lk == 1091)               // IdentifierName ':'
      {
        whitespace();
        parse_Label();
        lookahead1W(5);             // WhiteSpace | Comment | ':'
        consume(17);                // ':'
      }
      lookahead1W(13);              // IdentifierName | StringLiteral | WhiteSpace | Comment | '!' | '&' | '(' | '.' |
                                    // '<' | '>' | '['
      switch (l1)
      {
      case 9:                       // '!'
      case 10:                      // '&'
        whitespace();
        parse_Predicate();
        break;
      default:
        whitespace();
        parse_Expression();
      }
    }
    if (l1 == 28)                   // '{'
    {
      whitespace();
      parse_JavaScriptCode();
    }
    eventHandler.endNonterminal("Alternative", e0);
  }

  private void parse_Label()
  {
    eventHandler.startNonterminal("Label", e0);
    consume(3);                     // IdentifierName
    eventHandler.endNonterminal("Label", e0);
  }

  private void parse_Predicate()
  {
    eventHandler.startNonterminal("Predicate", e0);
    switch (l1)
    {
    case 10:                        // '&'
      consume(10);                  // '&'
      break;
    default:
      consume(9);                   // '!'
    }
    lookahead1W(12);                // IdentifierName | StringLiteral | WhiteSpace | Comment | '(' | '.' | '<' | '>' |
                                    // '[' | '{'
    switch (l1)
    {
    case 28:                        // '{'
      whitespace();
      parse_JavaScriptCode();
      break;
    default:
      whitespace();
      parse_Expression();
    }
    eventHandler.endNonterminal("Predicate", e0);
  }

  private void parse_Expression()
  {
    eventHandler.startNonterminal("Expression", e0);
    parse_Primary();
    lookahead1W(17);                // EOF | IdentifierName | StringLiteral | WhiteSpace | Comment | '!' | '&' | '(' |
                                    // ')' | '*' | '+' | '.' | '/' | ';' | '<' | '>' | '?' | '[' | '{'
    if (l1 == 13                    // '*'
     || l1 == 14                    // '+'
     || l1 == 23)                   // '?'
    {
      switch (l1)
      {
      case 13:                      // '*'
        consume(13);                // '*'
        break;
      case 14:                      // '+'
        consume(14);                // '+'
        break;
      default:
        consume(23);                // '?'
      }
    }
    eventHandler.endNonterminal("Expression", e0);
  }

  private void parse_Primary()
  {
    eventHandler.startNonterminal("Primary", e0);
    switch (l1)
    {
    case 4:                         // StringLiteral
      consume(4);                   // StringLiteral
      break;
    case 15:                        // '.'
      consume(15);                  // '.'
      break;
    case 24:                        // '['
      parse_CharacterSet();
      break;
    case 3:                         // IdentifierName
      parse_Name();
      break;
    case 11:                        // '('
      consume(11);                  // '('
      lookahead1W(14);              // IdentifierName | StringLiteral | WhiteSpace | Comment | '!' | '&' | '(' | ')' |
                                    // '.' | '/' | '<' | '>' | '[' | '{'
      whitespace();
      parse_ParsingExpression();
      consume(12);                  // ')'
      break;
    case 19:                        // '<'
      consume(19);                  // '<'
      break;
    default:
      consume(22);                  // '>'
    }
    eventHandler.endNonterminal("Primary", e0);
  }

  private void parse_CharacterSet()
  {
    eventHandler.startNonterminal("CharacterSet", e0);
    consume(24);                    // '['
    lookahead1(1);                  // Character | '^'
    if (l1 == 27)                   // '^'
    {
      consume(27);                  // '^'
    }
    for (;;)
    {
      parse_CharacterRange();
      lookahead1(4);                // Character | ']' | ']i'
      if (l1 != 5)                  // Character
      {
        break;
      }
    }
    switch (l1)
    {
    case 25:                        // ']'
      consume(25);                  // ']'
      break;
    default:
      consume(26);                  // ']i'
    }
    eventHandler.endNonterminal("CharacterSet", e0);
  }

  private void parse_CharacterRange()
  {
    eventHandler.startNonterminal("CharacterRange", e0);
    lookahead1(0);                  // Character
    consume(5);                     // Character
    lookahead1(8);                  // Character | Minus | ']' | ']i'
    if (l1 == 6)                    // Minus
    {
      consume(6);                   // Minus
      lookahead1(0);                // Character
      consume(5);                   // Character
    }
    eventHandler.endNonterminal("CharacterRange", e0);
  }

  private void consume(int t)
  {
    if (l1 == t)
    {
      whitespace();
      eventHandler.terminal(TOKEN[l1], b1, e1);
      b0 = b1; e0 = e1; l1 = l2; if (l1 != 0) {
      b1 = b2; e1 = e2; l2 = l3; if (l2 != 0) {
      b2 = b3; e2 = e3; l3 = 0; }}
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
      if (code != 7                 // WhiteSpace
       && code != 8)                // Comment
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
    lk = (l2 << 6) | l1;
  }

  private void lookahead3W(int tokenSetId)
  {
    if (l3 == 0)
    {
      l3 = matchW(tokenSetId);
      b3 = begin;
      e3 = end;
    }
    lk |= l3 << 12;
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
  private int l3, b3, e3;
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
        charclass = MAP1[(c0 & 7) + MAP1[(c1 & 31) + MAP1[c1 >> 5]]];
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

        int lo = 0, hi = 3;
        for (int m = 2; ; m = (hi + lo) >> 1)
        {
          if (MAP2[m] > c0) {hi = m - 1;}
          else if (MAP2[4 + m] < c0) {lo = m + 1;}
          else {charclass = MAP2[8 + m]; break;}
          if (lo > hi) {charclass = 0; break;}
        }
      }

      state = code;
      int i0 = (charclass << 6) + code - 1;
      code = TRANSITION[(i0 & 7) + TRANSITION[i0 >> 3]];

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
      return error(begin, end, state, -1, -1);
    }
    else if (nonbmp)
    {
      for (int i = result >> 5; i > 0; --i)
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
      end -= result >> 5;
    }

    if (end > size) end = size;
    return (result & 31) - 1;
  }

  private static String[] getTokenSet(int tokenSetId)
  {
    java.util.ArrayList<String> expected = new java.util.ArrayList<>();
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 63;
    for (int i = 0; i < 30; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 50 + s - 1;
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
    /*   0 */ 38, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5,
    /*  35 */ 6, 7, 8, 9, 10, 11, 12, 13, 14, 8, 15, 16, 17, 18, 18, 18, 18, 19, 19, 19, 19, 20, 20, 21, 22, 23, 24, 25,
    /*  63 */ 26, 8, 27, 27, 27, 27, 27, 27, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 28, 29, 30, 31,
    /*  95 */ 7, 8, 27, 27, 27, 27, 27, 27, 7, 7, 32, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 33, 7, 7, 34, 7, 7, 35, 8, 36, 8,
    /* 127 */ 8
  };

  private static final int[] MAP1 =
  {
    /*    0 */ 216, 279, 311, 399, 464, 727, 367, 432, 432, 496, 528, 560, 592, 624, 672, 701, 899, 759, 432, 432, 432,
    /*   21 */ 432, 640, 432, 430, 432, 432, 432, 432, 432, 791, 247, 823, 339, 432, 432, 432, 432, 432, 432, 432, 432,
    /*   42 */ 432, 432, 432, 432, 432, 432, 855, 887, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432,
    /*   63 */ 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 931, 931, 931, 931, 931, 931,
    /*   84 */ 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931,
    /*  105 */ 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931,
    /*  126 */ 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931,
    /*  147 */ 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 975, 432, 432, 432, 432, 432, 432, 432, 432,
    /*  168 */ 432, 432, 432, 432, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931,
    /*  189 */ 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931, 931,
    /*  210 */ 931, 931, 931, 931, 931, 943, 1007, 1016, 1008, 1008, 1024, 1032, 1040, 1048, 1056, 1339, 1339, 1073,
    /*  228 */ 1056, 1105, 1117, 1137, 1214, 1211, 1211, 1211, 1152, 1211, 1370, 1211, 1339, 1339, 1340, 1339, 1339,
    /*  245 */ 1339, 1340, 1339, 1339, 1107, 1107, 1339, 1339, 1339, 1339, 1107, 1107, 1339, 1434, 1339, 1339, 1339,
    /*  262 */ 1107, 1339, 1339, 1339, 1339, 1339, 1339, 1342, 1123, 1318, 1063, 1109, 1064, 1339, 1063, 1318, 1063,
    /*  279 */ 1339, 1339, 1339, 1339, 1339, 1339, 1335, 1340, 1338, 1322, 1339, 1339, 1339, 1339, 1339, 1340, 1339,
    /*  296 */ 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1064, 1446, 1339, 1339, 1339, 1339, 1281, 1337, 1339, 1339,
    /*  313 */ 1339, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339,
    /*  330 */ 1339, 1339, 1210, 1211, 1448, 1209, 1211, 1097, 1211, 1211, 1211, 1211, 1211, 1154, 1483, 1211, 1211,
    /*  347 */ 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1208, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211,
    /*  364 */ 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1338, 1339, 1339, 1208, 1421, 1222, 1096, 1211, 1091, 1097,
    /*  381 */ 1421, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1449, 1339, 1340, 1231, 1091, 1396, 1294, 1091,
    /*  398 */ 1097, 1091, 1091, 1091, 1091, 1091, 1091, 1091, 1091, 1093, 1211, 1211, 1211, 1097, 1211, 1211, 1211,
    /*  415 */ 1188, 1505, 1339, 1339, 1321, 1339, 1339, 1339, 1339, 1340, 1340, 1433, 1322, 1339, 1064, 1211, 1213,
    /*  432 */ 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211,
    /*  449 */ 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1338, 1342,
    /*  466 */ 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1338, 1342, 1339, 1339, 1339, 1339, 1162, 1211, 1339,
    /*  483 */ 1339, 1339, 1339, 1339, 1339, 1108, 1175, 1339, 1339, 1339, 1109, 1107, 1209, 1516, 1339, 1339, 1339,
    /*  500 */ 1339, 1339, 1339, 1247, 1091, 1093, 1295, 1339, 1265, 1091, 1211, 1211, 1516, 1108, 1336, 1339, 1339,
    /*  517 */ 1322, 1279, 1290, 1199, 1268, 1370, 1305, 1265, 1091, 1209, 1211, 1315, 1065, 1336, 1339, 1339, 1322,
    /*  534 */ 1330, 1290, 1271, 1268, 1211, 1503, 1371, 1091, 1237, 1211, 1516, 1504, 1321, 1339, 1339, 1322, 1489,
    /*  551 */ 1247, 1351, 1167, 1211, 1211, 1360, 1091, 1211, 1211, 1516, 1108, 1336, 1339, 1339, 1322, 1333, 1247,
    /*  568 */ 1296, 1268, 1371, 1305, 1178, 1091, 1211, 1211, 1475, 1239, 1384, 1380, 1282, 1239, 1341, 1178, 1297,
    /*  585 */ 1294, 1370, 1211, 1370, 1091, 1211, 1211, 1516, 1342, 1322, 1339, 1339, 1322, 1343, 1178, 1352, 1294,
    /*  602 */ 1372, 1211, 1178, 1091, 1211, 1211, 1475, 1342, 1322, 1339, 1339, 1322, 1343, 1178, 1352, 1294, 1372,
    /*  619 */ 1154, 1178, 1091, 1211, 1211, 1475, 1342, 1322, 1339, 1339, 1322, 1339, 1178, 1200, 1294, 1370, 1211,
    /*  636 */ 1178, 1091, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211,
    /*  653 */ 1211, 1211, 1211, 1152, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211,
    /*  670 */ 1211, 1211, 1338, 1339, 1339, 1339, 1339, 1340, 1392, 1096, 1423, 1092, 1091, 1097, 1211, 1211, 1211,
    /*  687 */ 1211, 1144, 1127, 1447, 1338, 1404, 1307, 1392, 1257, 1080, 1093, 1091, 1097, 1211, 1211, 1211, 1211,
    /*  704 */ 1097, 1091, 1097, 1414, 1085, 1339, 1338, 1339, 1339, 1339, 1209, 1090, 1091, 1352, 1095, 1351, 1090,
    /*  721 */ 1091, 1093, 1090, 1368, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1338, 1339, 1339, 1339, 1340,
    /*  738 */ 1186, 1338, 1339, 1339, 1339, 1340, 1211, 1090, 1091, 1196, 1091, 1091, 1253, 1365, 1211, 1339, 1339,
    /*  755 */ 1339, 1208, 1208, 1211, 1489, 1488, 1208, 1211, 1211, 1211, 1211, 1431, 1210, 1431, 1281, 1444, 1406,
    /*  772 */ 1280, 1143, 1211, 1211, 1211, 1211, 1154, 1211, 1129, 1153, 1382, 1208, 1211, 1211, 1211, 1211, 1442,
    /*  789 */ 1210, 1186, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339,
    /*  806 */ 1339, 1339, 1339, 1339, 1064, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1209,
    /*  823 */ 1464, 1469, 1211, 1211, 1211, 1457, 1211, 1211, 1211, 1211, 1211, 1212, 1211, 1211, 1211, 1211, 1211,
    /*  840 */ 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1091, 1094, 1368, 1211, 1211, 1211, 1497, 1211,
    /*  857 */ 1211, 1211, 1338, 1223, 1198, 1211, 1338, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1063,
    /*  874 */ 1472, 1338, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1513, 1446, 1339, 1339, 1339,
    /*  891 */ 1339, 1063, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211,
    /*  908 */ 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1339, 1339, 1339, 1339, 1107, 1211,
    /*  925 */ 1339, 1339, 1339, 1339, 1340, 1211, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339,
    /*  942 */ 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339,
    /*  959 */ 1339, 1339, 1339, 1339, 1064, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1339,
    /*  976 */ 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339, 1339,
    /*  993 */ 1339, 1339, 1107, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 1211, 38, 0, 0, 0, 0, 0, 0,
    /* 1014 */ 0, 0, 0, 1, 2, 3, 3, 2, 0, 0, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 8, 15, 16, 17, 18, 18, 18, 18, 19,
    /* 1045 */ 19, 19, 19, 20, 20, 21, 22, 23, 24, 25, 26, 8, 27, 27, 27, 27, 27, 27, 7, 7, 7, 7, 7, 8, 8, 8, 8, 7, 7,
    /* 1074 */ 7, 7, 28, 29, 30, 31, 7, 7, 7, 7, 7, 8, 37, 8, 8, 8, 8, 37, 37, 37, 37, 37, 37, 37, 37, 8, 8, 8, 8, 8, 8,
    /* 1105 */ 7, 32, 7, 7, 7, 7, 7, 7, 8, 8, 7, 7, 7, 7, 7, 7, 7, 33, 7, 7, 7, 7, 7, 8, 7, 8, 8, 7, 8, 8, 7, 7, 34, 7,
    /* 1139 */ 7, 35, 8, 36, 8, 8, 7, 7, 8, 7, 8, 8, 7, 1, 8, 8, 8, 8, 8, 8, 8, 7, 8, 7, 7, 8, 37, 37, 37, 37, 8, 37,
    /* 1171 */ 37, 37, 8, 8, 7, 8, 8, 7, 7, 8, 8, 8, 8, 37, 37, 8, 7, 8, 8, 8, 8, 8, 8, 7, 37, 37, 37, 8, 37, 37, 37,
    /* 1202 */ 37, 37, 8, 8, 37, 37, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 1, 8, 8, 7, 7, 7, 37, 37, 37, 37, 37, 37, 7, 7, 7,
    /* 1234 */ 7, 8, 7, 37, 37, 7, 7, 7, 8, 8, 8, 7, 7, 7, 7, 8, 8, 37, 7, 37, 37, 8, 37, 37, 37, 8, 37, 37, 7, 8, 8, 7,
    /* 1266 */ 7, 37, 37, 8, 8, 37, 37, 37, 8, 8, 8, 8, 37, 7, 8, 7, 8, 8, 8, 7, 7, 8, 8, 8, 7, 7, 8, 8, 37, 8, 37, 37,
    /* 1298 */ 37, 37, 8, 8, 8, 37, 37, 8, 8, 8, 8, 7, 7, 8, 7, 7, 8, 8, 8, 37, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 7, 7, 7,
    /* 1331 */ 8, 7, 7, 8, 7, 7, 8, 8, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 37, 37, 37, 37, 37, 37, 8, 37, 37, 7, 8, 8,
    /* 1363 */ 8, 8, 8, 37, 37, 8, 37, 8, 8, 8, 8, 8, 8, 8, 37, 37, 8, 8, 7, 7, 8, 7, 8, 7, 7, 7, 7, 8, 8, 7, 37, 7, 7,
    /* 1396 */ 37, 37, 37, 37, 37, 7, 7, 37, 8, 7, 7, 7, 8, 7, 8, 7, 8, 7, 8, 8, 8, 8, 8, 37, 8, 37, 7, 7, 7, 7, 7, 7,
    /* 1428 */ 7, 37, 37, 8, 8, 8, 8, 7, 8, 7, 8, 7, 8, 7, 8, 8, 8, 7, 8, 8, 8, 8, 8, 7, 7, 7, 7, 7, 8, 2, 2, 8, 8, 8,
    /* 1462 */ 8, 8, 1, 1, 1, 1, 1, 1, 1, 1, 8, 37, 37, 8, 8, 37, 37, 8, 7, 7, 7, 8, 8, 7, 7, 8, 8, 7, 8, 7, 7, 8, 7, 7,
    /* 1496 */ 7, 1, 8, 8, 8, 8, 37, 8, 7, 7, 7, 7, 8, 7, 8, 7, 7, 7, 7, 7, 8, 37, 37, 37, 8, 7, 7, 7
  };

  private static final int[] MAP2 =
  {
    /*  0 */ 57344, 65279, 65280, 65536, 65278, 65279, 65533, 1114111, 8, 1, 8, 8
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
  };

  private static final int[] TRANSITION =
  {
    /*   0 */ 347, 347, 347, 347, 347, 347, 347, 347, 312, 316, 862, 477, 517, 562, 347, 347, 327, 331, 334, 555, 560,
    /*  21 */ 562, 347, 347, 857, 331, 319, 347, 347, 347, 347, 347, 342, 821, 355, 477, 517, 562, 347, 347, 342, 363,
    /*  42 */ 367, 850, 517, 562, 347, 347, 375, 379, 382, 477, 517, 562, 347, 347, 390, 404, 415, 423, 517, 562, 347,
    /*  63 */ 347, 342, 346, 673, 477, 517, 562, 347, 347, 342, 902, 438, 477, 517, 562, 347, 347, 342, 446, 450, 548,
    /*  84 */ 517, 562, 347, 347, 342, 458, 462, 477, 517, 562, 347, 347, 342, 470, 525, 477, 517, 562, 347, 347, 342,
    /* 105 */ 346, 533, 541, 498, 572, 347, 347, 342, 346, 582, 477, 517, 562, 347, 347, 342, 590, 673, 836, 517, 562,
    /* 126 */ 347, 347, 342, 601, 605, 477, 517, 562, 347, 347, 613, 621, 627, 890, 517, 635, 347, 347, 342, 346, 742,
    /* 147 */ 646, 654, 662, 670, 347, 342, 346, 797, 646, 654, 662, 670, 347, 342, 346, 574, 423, 681, 662, 670, 347,
    /* 168 */ 689, 346, 703, 477, 517, 562, 347, 347, 342, 711, 718, 477, 517, 562, 347, 347, 342, 726, 731, 477, 517,
    /* 189 */ 562, 347, 347, 342, 739, 430, 477, 517, 562, 347, 347, 342, 750, 754, 477, 517, 562, 347, 347, 342, 346,
    /* 210 */ 762, 477, 517, 562, 347, 347, 390, 404, 415, 423, 770, 662, 670, 347, 342, 778, 782, 477, 517, 562, 347,
    /* 231 */ 347, 790, 794, 673, 843, 517, 562, 347, 347, 805, 809, 673, 593, 517, 562, 347, 347, 817, 346, 673, 477,
    /* 252 */ 517, 562, 347, 347, 390, 404, 415, 829, 491, 562, 347, 347, 390, 404, 407, 423, 695, 562, 347, 347, 390,
    /* 273 */ 404, 396, 423, 484, 562, 347, 347, 870, 878, 884, 477, 517, 562, 347, 347, 898, 346, 638, 477, 517, 562,
    /* 294 */ 347, 347, 342, 346, 673, 423, 517, 562, 347, 347, 564, 505, 512, 347, 347, 347, 347, 347, 384, 384, 215,
    /* 315 */ 512, 384, 512, 512, 512, 512, 512, 512, 512, 0, 0, 0, 0, 0, 215, 512, 0, 512, 512, 512, 512, 512, 512,
    /* 338 */ 512, 0, 215, 0, 384, 384, 215, 0, 384, 0, 0, 0, 0, 0, 0, 0, 0, 640, 640, 640, 640, 640, 384, 215, 600,
    /* 363 */ 384, 0, 30, 0, 30, 30, 30, 30, 30, 384, 215, 600, 384, 384, 215, 600, 384, 600, 600, 600, 600, 600, 600,
    /* 386 */ 600, 384, 215, 600, 384, 384, 215, 281, 384, 0, 281, 281, 281, 281, 281, 35, 215, 600, 384, 0, 0, 281,
    /* 408 */ 281, 281, 281, 281, 34, 215, 600, 281, 281, 281, 281, 281, 384, 215, 600, 281, 0, 0, 2496, 0, 30, 31, 0,
    /* 431 */ 0, 0, 1408, 1408, 384, 215, 600, 704, 704, 704, 704, 704, 384, 215, 600, 384, 0, 31, 0, 31, 31, 31, 31,
    /* 454 */ 31, 384, 215, 600, 384, 0, 0, 0, 768, 768, 768, 768, 768, 384, 215, 600, 384, 0, 0, 832, 0, 0, 832, 0, 0,
    /* 479 */ 0, 2496, 0, 30, 31, 0, 0, 0, 36, 0, 44, 46, 0, 0, 0, 36, 320, 30, 31, 0, 0, 0, 42, 0, 30, 31, 0, 0, 0,
    /* 508 */ 128, 0, 0, 0, 128, 128, 128, 128, 128, 0, 0, 0, 36, 0, 30, 31, 0, 832, 832, 832, 832, 832, 384, 215, 600,
    /* 533 */ 0, 896, 896, 896, 896, 384, 215, 600, 0, 36, 0, 2496, 0, 30, 31, 0, 0, 0, 2496, 0, 30, 357, 0, 0, 0, 2496,
    /* 559 */ 0, 0, 0, 0, 36, 0, 0, 0, 0, 0, 0, 128, 0, 0, 42, 0, 0, 0, 0, 0, 0, 215, 600, 0, 960, 960, 960, 960, 384,
    /* 588 */ 215, 600, 412, 0, 0, 0, 0, 0, 0, 0, 30, 31, 0, 384, 0, 0, 0, 1024, 1024, 1024, 1024, 1024, 384, 215, 600,
    /* 613 */ 384, 384, 215, 26, 384, 26, 26, 26, 384, 26, 26, 1114, 26, 26, 1114, 1114, 1114, 1114, 1114, 384, 215,
    /* 634 */ 600, 0, 576, 0, 0, 0, 0, 0, 0, 384, 0, 600, 281, 0, 0, 2496, 0, 30, 31, 417, 384, 40, 41, 36, 0, 30, 31,
    /* 661 */ 35, 384, 36, 47, 48, 49, 50, 44, 30, 46, 31, 0, 0, 0, 0, 0, 0, 384, 215, 600, 0, 40, 41, 36, 0, 0, 0, 35,
    /* 689 */ 384, 384, 215, 0, 384, 1152, 0, 0, 0, 36, 0, 43, 45, 0, 0, 0, 1152, 0, 1152, 384, 215, 600, 384, 0, 0,
    /* 714 */ 1216, 0, 0, 0, 1216, 1216, 1216, 1216, 1216, 384, 215, 600, 384, 29, 29, 0, 1280, 1280, 1280, 1280, 1309,
    /* 735 */ 1309, 384, 215, 600, 384, 1408, 1408, 0, 0, 0, 0, 0, 416, 215, 600, 384, 0, 0, 0, 1472, 1472, 1472, 1472,
    /* 758 */ 1472, 384, 215, 600, 0, 1536, 1536, 1536, 1536, 384, 215, 600, 0, 40, 41, 36, 0, 30, 31, 35, 384, 0, 0, 0,
    /* 782 */ 1600, 1600, 1600, 1600, 1600, 384, 215, 600, 22, 22, 215, 0, 22, 0, 0, 0, 0, 0, 0, 0, 417, 215, 600, 0, 0,
    /* 807 */ 215, 0, 1691, 0, 0, 0, 0, 0, 0, 0, 384, 1792, 215, 0, 384, 0, 0, 0, 0, 640, 640, 640, 281, 0, 1728, 2496,
    /* 833 */ 0, 30, 31, 0, 0, 0, 2496, 1344, 30, 31, 0, 0, 0, 2496, 0, 38, 39, 0, 0, 0, 2496, 0, 357, 31, 0, 0, 0, 512,
    /* 861 */ 0, 512, 512, 512, 512, 512, 384, 215, 600, 384, 384, 1856, 0, 384, 0, 0, 1856, 384, 0, 0, 0, 1856, 0,
    /* 884 */ 1856, 1856, 1856, 1856, 1856, 384, 0, 600, 0, 2496, 0, 30, 31, 0, 384, 384, 1920, 0, 384, 0, 0, 0, 0, 704,
    /* 908 */ 704, 704
  };

  private static final int[] EXPECTED =
  {
    /*  0 */ 32, 134217760, 805306372, 392, 100663328, 131456, 394, 268435848, 100663392, 3146112, 3146128, 332170,
    /* 12 */ 289966488, 21532568, 290037656, 290295706, 290299802, 298712986, 298844058, 301858714, 301989786, 32, 4,
    /* 23 */ 256, 8, 256, 67108864, 64, 1048576, 16, 16, 32, 32, 32, 32, 256, 16, 16, 16, 32, 32, 256, 16, 16, 16, 16,
    /* 46 */ 16, 16, 16, 16
  };

  private static final String[] TOKEN =
  {
    "(0)",
    "EOF",
    "NonBraceChars",
    "IdentifierName",
    "StringLiteral",
    "Character",
    "'-'",
    "WhiteSpace",
    "Comment",
    "'!'",
    "'&'",
    "'('",
    "')'",
    "'*'",
    "'+'",
    "'.'",
    "'/'",
    "':'",
    "';'",
    "'<'",
    "'<-'",
    "'='",
    "'>'",
    "'?'",
    "'['",
    "']'",
    "']i'",
    "'^'",
    "'{'",
    "'}'"
  };
}

// End
