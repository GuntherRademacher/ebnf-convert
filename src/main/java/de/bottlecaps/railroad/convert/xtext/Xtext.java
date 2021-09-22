// This file was generated on Fri May 1, 2020 11:22 (UTC+02) by REx v5.50 which is Copyright (c) 1979-2020 by Gunther Rademacher <grd@gmx.net>
// REx command line: -tree -a none -java -interface de.bottlecaps.railroad.convert.Parser -saxon10 -name de.bottlecaps.railroad.convert.xtext.Xtext xtext.ebnf

package de.bottlecaps.railroad.convert.xtext;

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

public class Xtext implements de.bottlecaps.railroad.convert.Parser
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
    Xtext parser = new Xtext(input, new SaxonTreeBuilder(builder));
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
    public StructuredQName getFunctionQName() {return new StructuredQName("p", "Xtext", functionName());}
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

  private static void buildError(Xtext parser, ParseException pe, Builder builder) throws XPathException
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

  public Xtext(CharSequence string, EventHandler t)
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
    parse_Grammar();
  }

  public void parse_Grammar()
  {
    eventHandler.startNonterminal("Grammar", e0);
    lookahead1W(29);                // ID | ML_COMMENT | SL_COMMENT | WS | 'enum' | 'fragment' | 'generate' |
                                    // 'grammar' | 'hidden' | 'import' | 'terminal'
    if (l1 == 32)                   // 'grammar'
    {
      consume(32);                  // 'grammar'
      lookahead1W(0);               // ID | ML_COMMENT | SL_COMMENT | WS
      whitespace();
      parse_GrammarID();
      if (l1 == 37)                 // 'with'
      {
        consume(37);                // 'with'
        lookahead1W(0);             // ID | ML_COMMENT | SL_COMMENT | WS
        whitespace();
        parse_GrammarID();
        for (;;)
        {
          if (l1 != 14)             // ','
          {
            break;
          }
          consume(14);              // ','
          lookahead1W(0);           // ID | ML_COMMENT | SL_COMMENT | WS
          whitespace();
          parse_GrammarID();
        }
      }
    }
    if (l1 == 33)                   // 'hidden'
    {
      consume(33);                  // 'hidden'
      lookahead1W(2);               // ML_COMMENT | SL_COMMENT | WS | '('
      consume(9);                   // '('
      lookahead1W(9);               // ID | ML_COMMENT | SL_COMMENT | WS | ')'
      if (l1 == 1)                  // ID
      {
        consume(1);                 // ID
        for (;;)
        {
          lookahead1W(10);          // ML_COMMENT | SL_COMMENT | WS | ')' | ','
          if (l1 != 14)             // ','
          {
            break;
          }
          consume(14);              // ','
          lookahead1W(0);           // ID | ML_COMMENT | SL_COMMENT | WS
          consume(1);               // ID
        }
      }
      consume(10);                  // ')'
    }
    for (;;)
    {
      lookahead1W(26);              // ID | ML_COMMENT | SL_COMMENT | WS | 'enum' | 'fragment' | 'generate' | 'import' |
                                    // 'terminal'
      if (l1 != 31                  // 'generate'
       && l1 != 34)                 // 'import'
      {
        break;
      }
      whitespace();
      parse_AbstractMetamodelDeclaration();
    }
    for (;;)
    {
      whitespace();
      parse_AbstractRule();
      lookahead1W(23);              // ID | ML_COMMENT | SL_COMMENT | WS | EOF | 'enum' | 'fragment' | 'terminal'
      if (l1 == 6)                  // EOF
      {
        break;
      }
    }
    consume(6);                     // EOF
    eventHandler.endNonterminal("Grammar", e0);
  }

  private void parse_GrammarID()
  {
    eventHandler.startNonterminal("GrammarID", e0);
    consume(1);                     // ID
    for (;;)
    {
      lookahead1W(32);              // ID | ML_COMMENT | SL_COMMENT | WS | ',' | '.' | 'enum' | 'fragment' |
                                    // 'generate' | 'hidden' | 'import' | 'terminal' | 'with'
      if (l1 != 16)                 // '.'
      {
        break;
      }
      consume(16);                  // '.'
      lookahead1W(0);               // ID | ML_COMMENT | SL_COMMENT | WS
      consume(1);                   // ID
    }
    eventHandler.endNonterminal("GrammarID", e0);
  }

  private void parse_AbstractRule()
  {
    eventHandler.startNonterminal("AbstractRule", e0);
    switch (l1)
    {
    case 36:                        // 'terminal'
      parse_TerminalRule();
      break;
    case 29:                        // 'enum'
      parse_EnumRule();
      break;
    default:
      parse_ParserRule();
    }
    eventHandler.endNonterminal("AbstractRule", e0);
  }

  private void parse_AbstractMetamodelDeclaration()
  {
    eventHandler.startNonterminal("AbstractMetamodelDeclaration", e0);
    switch (l1)
    {
    case 31:                        // 'generate'
      parse_GeneratedMetamodel();
      break;
    default:
      parse_ReferencedMetamodel();
    }
    eventHandler.endNonterminal("AbstractMetamodelDeclaration", e0);
  }

  private void parse_GeneratedMetamodel()
  {
    eventHandler.startNonterminal("GeneratedMetamodel", e0);
    consume(31);                    // 'generate'
    lookahead1W(0);                 // ID | ML_COMMENT | SL_COMMENT | WS
    consume(1);                     // ID
    lookahead1W(1);                 // STRING | ML_COMMENT | SL_COMMENT | WS
    consume(2);                     // STRING
    lookahead1W(27);                // ID | ML_COMMENT | SL_COMMENT | WS | 'as' | 'enum' | 'fragment' | 'generate' |
                                    // 'import' | 'terminal'
    if (l1 == 27)                   // 'as'
    {
      consume(27);                  // 'as'
      lookahead1W(0);               // ID | ML_COMMENT | SL_COMMENT | WS
      consume(1);                   // ID
    }
    eventHandler.endNonterminal("GeneratedMetamodel", e0);
  }

  private void parse_ReferencedMetamodel()
  {
    eventHandler.startNonterminal("ReferencedMetamodel", e0);
    consume(34);                    // 'import'
    lookahead1W(1);                 // STRING | ML_COMMENT | SL_COMMENT | WS
    consume(2);                     // STRING
    lookahead1W(27);                // ID | ML_COMMENT | SL_COMMENT | WS | 'as' | 'enum' | 'fragment' | 'generate' |
                                    // 'import' | 'terminal'
    if (l1 == 27)                   // 'as'
    {
      consume(27);                  // 'as'
      lookahead1W(0);               // ID | ML_COMMENT | SL_COMMENT | WS
      consume(1);                   // ID
    }
    eventHandler.endNonterminal("ReferencedMetamodel", e0);
  }

  private void parse_ParserRule()
  {
    eventHandler.startNonterminal("ParserRule", e0);
    if (l1 == 30)                   // 'fragment'
    {
      consume(30);                  // 'fragment'
    }
    lookahead1W(0);                 // ID | ML_COMMENT | SL_COMMENT | WS
    consume(1);                     // ID
    lookahead1W(20);                // ML_COMMENT | SL_COMMENT | WS | ':' | 'hidden' | 'returns'
    if (l1 == 35)                   // 'returns'
    {
      consume(35);                  // 'returns'
      lookahead1W(0);               // ID | ML_COMMENT | SL_COMMENT | WS
      whitespace();
      parse_TypeRef();
    }
    lookahead1W(14);                // ML_COMMENT | SL_COMMENT | WS | ':' | 'hidden'
    if (l1 == 33)                   // 'hidden'
    {
      consume(33);                  // 'hidden'
      lookahead1W(2);               // ML_COMMENT | SL_COMMENT | WS | '('
      consume(9);                   // '('
      lookahead1W(9);               // ID | ML_COMMENT | SL_COMMENT | WS | ')'
      if (l1 == 1)                  // ID
      {
        consume(1);                 // ID
        for (;;)
        {
          lookahead1W(10);          // ML_COMMENT | SL_COMMENT | WS | ')' | ','
          if (l1 != 14)             // ','
          {
            break;
          }
          consume(14);              // ','
          lookahead1W(0);           // ID | ML_COMMENT | SL_COMMENT | WS
          consume(1);               // ID
        }
      }
      consume(10);                  // ')'
    }
    lookahead1W(3);                 // ML_COMMENT | SL_COMMENT | WS | ':'
    consume(18);                    // ':'
    lookahead1W(25);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '(' | '->' | '=>' | '{'
    whitespace();
    parse_Alternatives();
    consume(20);                    // ';'
    eventHandler.endNonterminal("ParserRule", e0);
  }

  private void parse_TypeRef()
  {
    eventHandler.startNonterminal("TypeRef", e0);
    switch (l1)
    {
    case 1:                         // ID
      lookahead2W(28);              // ML_COMMENT | SL_COMMENT | WS | '.' | ':' | '::' | ']' | 'hidden' | '|' | '}'
      break;
    default:
      lk = l1;
    }
    if (lk == 1217)                 // ID '::'
    {
      consume(1);                   // ID
      lookahead1W(4);               // ML_COMMENT | SL_COMMENT | WS | '::'
      consume(19);                  // '::'
    }
    lookahead1W(0);                 // ID | ML_COMMENT | SL_COMMENT | WS
    consume(1);                     // ID
    eventHandler.endNonterminal("TypeRef", e0);
  }

  private void parse_Alternatives()
  {
    eventHandler.startNonterminal("Alternatives", e0);
    parse_UnorderedGroup();
    if (l1 == 39)                   // '|'
    {
      for (;;)
      {
        consume(39);                // '|'
        lookahead1W(25);            // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '(' | '->' | '=>' | '{'
        whitespace();
        parse_UnorderedGroup();
        if (l1 != 39)               // '|'
        {
          break;
        }
      }
    }
    eventHandler.endNonterminal("Alternatives", e0);
  }

  private void parse_UnorderedGroup()
  {
    eventHandler.startNonterminal("UnorderedGroup", e0);
    parse_Group();
    if (l1 == 8)                    // '&'
    {
      for (;;)
      {
        consume(8);                 // '&'
        lookahead1W(25);            // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '(' | '->' | '=>' | '{'
        whitespace();
        parse_Group();
        if (l1 != 8)                // '&'
        {
          break;
        }
      }
    }
    eventHandler.endNonterminal("UnorderedGroup", e0);
  }

  private void parse_Group()
  {
    eventHandler.startNonterminal("Group", e0);
    parse_AbstractToken();
    lookahead1W(31);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '&' | '(' | ')' | '->' | ';' |
                                    // '=>' | '{' | '|'
    if (l1 != 8                     // '&'
     && l1 != 10                    // ')'
     && l1 != 20                    // ';'
     && l1 != 39)                   // '|'
    {
      for (;;)
      {
        whitespace();
        parse_AbstractToken();
        lookahead1W(31);            // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '&' | '(' | ')' | '->' | ';' |
                                    // '=>' | '{' | '|'
        if (l1 == 8                 // '&'
         || l1 == 10                // ')'
         || l1 == 20                // ';'
         || l1 == 39)               // '|'
        {
          break;
        }
      }
    }
    eventHandler.endNonterminal("Group", e0);
  }

  private void parse_AbstractToken()
  {
    eventHandler.startNonterminal("AbstractToken", e0);
    switch (l1)
    {
    case 38:                        // '{'
      parse_Action();
      break;
    default:
      parse_AbstractTokenWithCardinality();
    }
    eventHandler.endNonterminal("AbstractToken", e0);
  }

  private void parse_AbstractTokenWithCardinality()
  {
    eventHandler.startNonterminal("AbstractTokenWithCardinality", e0);
    if (l1 == 15                    // '->'
     || l1 == 22)                   // '=>'
    {
      switch (l1)
      {
      case 22:                      // '=>'
        consume(22);                // '=>'
        break;
      default:
        consume(15);                // '->'
      }
    }
    lookahead1W(18);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '('
    switch (l1)
    {
    case 1:                         // ID
      lookahead2W(36);              // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '&' | '(' | ')' | '*' | '+' | '+=' |
                                    // '->' | ';' | '=' | '=>' | '?' | '?=' | '{' | '|'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 833:                       // ID '+='
    case 1345:                      // ID '='
    case 1537:                      // ID '?='
      whitespace();
      parse_Assignment();
      break;
    default:
      whitespace();
      parse_AbstractTerminal();
    }
    lookahead1W(35);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '&' | '(' | ')' | '*' | '+' | '->' |
                                    // ';' | '=>' | '?' | '{' | '|'
    if (l1 == 11                    // '*'
     || l1 == 12                    // '+'
     || l1 == 23)                   // '?'
    {
      switch (l1)
      {
      case 23:                      // '?'
        consume(23);                // '?'
        break;
      case 11:                      // '*'
        consume(11);                // '*'
        break;
      default:
        consume(12);                // '+'
      }
    }
    eventHandler.endNonterminal("AbstractTokenWithCardinality", e0);
  }

  private void parse_Action()
  {
    eventHandler.startNonterminal("Action", e0);
    consume(38);                    // '{'
    lookahead1W(0);                 // ID | ML_COMMENT | SL_COMMENT | WS
    whitespace();
    parse_TypeRef();
    lookahead1W(13);                // ML_COMMENT | SL_COMMENT | WS | '.' | '}'
    if (l1 == 16)                   // '.'
    {
      consume(16);                  // '.'
      lookahead1W(0);               // ID | ML_COMMENT | SL_COMMENT | WS
      consume(1);                   // ID
      lookahead1W(12);              // ML_COMMENT | SL_COMMENT | WS | '+=' | '='
      switch (l1)
      {
      case 21:                      // '='
        consume(21);                // '='
        break;
      default:
        consume(13);                // '+='
      }
      lookahead1W(6);               // ML_COMMENT | SL_COMMENT | WS | 'current'
      consume(28);                  // 'current'
    }
    lookahead1W(7);                 // ML_COMMENT | SL_COMMENT | WS | '}'
    consume(40);                    // '}'
    eventHandler.endNonterminal("Action", e0);
  }

  private void parse_AbstractTerminal()
  {
    eventHandler.startNonterminal("AbstractTerminal", e0);
    switch (l1)
    {
    case 2:                         // STRING
      parse_Keyword();
      break;
    case 1:                         // ID
      parse_RuleCall();
      break;
    default:
      parse_ParenthesizedElement();
    }
    eventHandler.endNonterminal("AbstractTerminal", e0);
  }

  private void parse_Keyword()
  {
    eventHandler.startNonterminal("Keyword", e0);
    consume(2);                     // STRING
    eventHandler.endNonterminal("Keyword", e0);
  }

  private void parse_RuleCall()
  {
    eventHandler.startNonterminal("RuleCall", e0);
    consume(1);                     // ID
    eventHandler.endNonterminal("RuleCall", e0);
  }

  private void parse_Assignment()
  {
    eventHandler.startNonterminal("Assignment", e0);
    consume(1);                     // ID
    lookahead1W(19);                // ML_COMMENT | SL_COMMENT | WS | '+=' | '=' | '?='
    switch (l1)
    {
    case 13:                        // '+='
      consume(13);                  // '+='
      break;
    case 21:                        // '='
      consume(21);                  // '='
      break;
    default:
      consume(24);                  // '?='
    }
    lookahead1W(22);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '(' | '['
    whitespace();
    parse_AssignableTerminal();
    eventHandler.endNonterminal("Assignment", e0);
  }

  private void parse_AssignableTerminal()
  {
    eventHandler.startNonterminal("AssignableTerminal", e0);
    switch (l1)
    {
    case 2:                         // STRING
      parse_Keyword();
      break;
    case 1:                         // ID
      parse_RuleCall();
      break;
    case 9:                         // '('
      parse_ParenthesizedAssignableElement();
      break;
    default:
      parse_CrossReference();
    }
    eventHandler.endNonterminal("AssignableTerminal", e0);
  }

  private void parse_ParenthesizedAssignableElement()
  {
    eventHandler.startNonterminal("ParenthesizedAssignableElement", e0);
    consume(9);                     // '('
    lookahead1W(22);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '(' | '['
    whitespace();
    parse_AssignableAlternatives();
    consume(10);                    // ')'
    eventHandler.endNonterminal("ParenthesizedAssignableElement", e0);
  }

  private void parse_AssignableAlternatives()
  {
    eventHandler.startNonterminal("AssignableAlternatives", e0);
    parse_AssignableTerminal();
    lookahead1W(11);                // ML_COMMENT | SL_COMMENT | WS | ')' | '|'
    if (l1 == 39)                   // '|'
    {
      for (;;)
      {
        consume(39);                // '|'
        lookahead1W(22);            // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '(' | '['
        whitespace();
        parse_AssignableTerminal();
        lookahead1W(11);            // ML_COMMENT | SL_COMMENT | WS | ')' | '|'
        if (l1 != 39)               // '|'
        {
          break;
        }
      }
    }
    eventHandler.endNonterminal("AssignableAlternatives", e0);
  }

  private void parse_CrossReference()
  {
    eventHandler.startNonterminal("CrossReference", e0);
    consume(25);                    // '['
    lookahead1W(0);                 // ID | ML_COMMENT | SL_COMMENT | WS
    whitespace();
    parse_TypeRef();
    lookahead1W(17);                // ML_COMMENT | SL_COMMENT | WS | ']' | '|'
    if (l1 == 39)                   // '|'
    {
      consume(39);                  // '|'
      lookahead1W(8);               // ID | STRING | ML_COMMENT | SL_COMMENT | WS
      whitespace();
      parse_CrossReferenceableTerminal();
    }
    lookahead1W(5);                 // ML_COMMENT | SL_COMMENT | WS | ']'
    consume(26);                    // ']'
    eventHandler.endNonterminal("CrossReference", e0);
  }

  private void parse_CrossReferenceableTerminal()
  {
    eventHandler.startNonterminal("CrossReferenceableTerminal", e0);
    switch (l1)
    {
    case 2:                         // STRING
      parse_Keyword();
      break;
    default:
      parse_RuleCall();
    }
    eventHandler.endNonterminal("CrossReferenceableTerminal", e0);
  }

  private void parse_ParenthesizedElement()
  {
    eventHandler.startNonterminal("ParenthesizedElement", e0);
    consume(9);                     // '('
    lookahead1W(25);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '(' | '->' | '=>' | '{'
    whitespace();
    parse_Alternatives();
    consume(10);                    // ')'
    eventHandler.endNonterminal("ParenthesizedElement", e0);
  }

  private void parse_TerminalRule()
  {
    eventHandler.startNonterminal("TerminalRule", e0);
    consume(36);                    // 'terminal'
    lookahead1W(0);                 // ID | ML_COMMENT | SL_COMMENT | WS
    consume(1);                     // ID
    lookahead1W(15);                // ML_COMMENT | SL_COMMENT | WS | ':' | 'returns'
    if (l1 == 35)                   // 'returns'
    {
      consume(35);                  // 'returns'
      lookahead1W(0);               // ID | ML_COMMENT | SL_COMMENT | WS
      whitespace();
      parse_TypeRef();
    }
    lookahead1W(3);                 // ML_COMMENT | SL_COMMENT | WS | ':'
    consume(18);                    // ':'
    lookahead1W(24);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '!' | '(' | '->' | '.'
    whitespace();
    parse_TerminalAlternatives();
    consume(20);                    // ';'
    eventHandler.endNonterminal("TerminalRule", e0);
  }

  private void parse_TerminalAlternatives()
  {
    eventHandler.startNonterminal("TerminalAlternatives", e0);
    parse_TerminalGroup();
    if (l1 == 39)                   // '|'
    {
      for (;;)
      {
        consume(39);                // '|'
        lookahead1W(24);            // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '!' | '(' | '->' | '.'
        whitespace();
        parse_TerminalGroup();
        if (l1 != 39)               // '|'
        {
          break;
        }
      }
    }
    eventHandler.endNonterminal("TerminalAlternatives", e0);
  }

  private void parse_TerminalGroup()
  {
    eventHandler.startNonterminal("TerminalGroup", e0);
    parse_TerminalToken();
    lookahead1W(30);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '!' | '(' | ')' | '->' | '.' | ';' |
                                    // '|'
    if (l1 != 10                    // ')'
     && l1 != 20                    // ';'
     && l1 != 39)                   // '|'
    {
      for (;;)
      {
        whitespace();
        parse_TerminalToken();
        lookahead1W(30);            // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '!' | '(' | ')' | '->' | '.' | ';' |
                                    // '|'
        if (l1 == 10                // ')'
         || l1 == 20                // ';'
         || l1 == 39)               // '|'
        {
          break;
        }
      }
    }
    eventHandler.endNonterminal("TerminalGroup", e0);
  }

  private void parse_TerminalToken()
  {
    eventHandler.startNonterminal("TerminalToken", e0);
    parse_TerminalTokenElement();
    lookahead1W(33);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '!' | '(' | ')' | '*' | '+' | '->' |
                                    // '.' | ';' | '?' | '|'
    if (l1 == 11                    // '*'
     || l1 == 12                    // '+'
     || l1 == 23)                   // '?'
    {
      switch (l1)
      {
      case 23:                      // '?'
        consume(23);                // '?'
        break;
      case 11:                      // '*'
        consume(11);                // '*'
        break;
      default:
        consume(12);                // '+'
      }
    }
    eventHandler.endNonterminal("TerminalToken", e0);
  }

  private void parse_TerminalTokenElement()
  {
    eventHandler.startNonterminal("TerminalTokenElement", e0);
    switch (l1)
    {
    case 2:                         // STRING
      parse_CharacterRange();
      break;
    case 1:                         // ID
      parse_RuleCall();
      break;
    case 9:                         // '('
      parse_ParenthesizedTerminalElement();
      break;
    case 16:                        // '.'
      parse_Wildcard();
      break;
    default:
      parse_AbstractNegatedToken();
    }
    eventHandler.endNonterminal("TerminalTokenElement", e0);
  }

  private void parse_ParenthesizedTerminalElement()
  {
    eventHandler.startNonterminal("ParenthesizedTerminalElement", e0);
    consume(9);                     // '('
    lookahead1W(24);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '!' | '(' | '->' | '.'
    whitespace();
    parse_TerminalAlternatives();
    consume(10);                    // ')'
    eventHandler.endNonterminal("ParenthesizedTerminalElement", e0);
  }

  private void parse_AbstractNegatedToken()
  {
    eventHandler.startNonterminal("AbstractNegatedToken", e0);
    switch (l1)
    {
    case 7:                         // '!'
      parse_NegatedToken();
      break;
    default:
      parse_UntilToken();
    }
    eventHandler.endNonterminal("AbstractNegatedToken", e0);
  }

  private void parse_NegatedToken()
  {
    eventHandler.startNonterminal("NegatedToken", e0);
    consume(7);                     // '!'
    lookahead1W(24);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '!' | '(' | '->' | '.'
    whitespace();
    parse_TerminalTokenElement();
    eventHandler.endNonterminal("NegatedToken", e0);
  }

  private void parse_UntilToken()
  {
    eventHandler.startNonterminal("UntilToken", e0);
    consume(15);                    // '->'
    lookahead1W(24);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '!' | '(' | '->' | '.'
    whitespace();
    parse_TerminalTokenElement();
    eventHandler.endNonterminal("UntilToken", e0);
  }

  private void parse_Wildcard()
  {
    eventHandler.startNonterminal("Wildcard", e0);
    consume(16);                    // '.'
    eventHandler.endNonterminal("Wildcard", e0);
  }

  private void parse_CharacterRange()
  {
    eventHandler.startNonterminal("CharacterRange", e0);
    parse_Keyword();
    lookahead1W(34);                // ID | STRING | ML_COMMENT | SL_COMMENT | WS | '!' | '(' | ')' | '*' | '+' | '->' |
                                    // '.' | '..' | ';' | '?' | '|'
    if (l1 == 17)                   // '..'
    {
      consume(17);                  // '..'
      lookahead1W(1);               // STRING | ML_COMMENT | SL_COMMENT | WS
      whitespace();
      parse_Keyword();
    }
    eventHandler.endNonterminal("CharacterRange", e0);
  }

  private void parse_EnumRule()
  {
    eventHandler.startNonterminal("EnumRule", e0);
    consume(29);                    // 'enum'
    lookahead1W(0);                 // ID | ML_COMMENT | SL_COMMENT | WS
    consume(1);                     // ID
    lookahead1W(15);                // ML_COMMENT | SL_COMMENT | WS | ':' | 'returns'
    if (l1 == 35)                   // 'returns'
    {
      consume(35);                  // 'returns'
      lookahead1W(0);               // ID | ML_COMMENT | SL_COMMENT | WS
      whitespace();
      parse_TypeRef();
    }
    lookahead1W(3);                 // ML_COMMENT | SL_COMMENT | WS | ':'
    consume(18);                    // ':'
    lookahead1W(0);                 // ID | ML_COMMENT | SL_COMMENT | WS
    whitespace();
    parse_EnumLiterals();
    consume(20);                    // ';'
    eventHandler.endNonterminal("EnumRule", e0);
  }

  private void parse_EnumLiterals()
  {
    eventHandler.startNonterminal("EnumLiterals", e0);
    parse_EnumLiteralDeclaration();
    lookahead1W(16);                // ML_COMMENT | SL_COMMENT | WS | ';' | '|'
    if (l1 == 39)                   // '|'
    {
      for (;;)
      {
        consume(39);                // '|'
        lookahead1W(0);             // ID | ML_COMMENT | SL_COMMENT | WS
        whitespace();
        parse_EnumLiteralDeclaration();
        lookahead1W(16);            // ML_COMMENT | SL_COMMENT | WS | ';' | '|'
        if (l1 != 39)               // '|'
        {
          break;
        }
      }
    }
    eventHandler.endNonterminal("EnumLiterals", e0);
  }

  private void parse_EnumLiteralDeclaration()
  {
    eventHandler.startNonterminal("EnumLiteralDeclaration", e0);
    consume(1);                     // ID
    lookahead1W(21);                // ML_COMMENT | SL_COMMENT | WS | ';' | '=' | '|'
    if (l1 == 21)                   // '='
    {
      consume(21);                  // '='
      lookahead1W(1);               // STRING | ML_COMMENT | SL_COMMENT | WS
      whitespace();
      parse_Keyword();
    }
    eventHandler.endNonterminal("EnumLiteralDeclaration", e0);
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
      if (code != 3                 // ML_COMMENT
       && code != 4                 // SL_COMMENT
       && code != 5)                // WS
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

    if (end > size) end = size;
    return (result & 63) - 1;
  }

  private static String[] getTokenSet(int tokenSetId)
  {
    java.util.ArrayList<String> expected = new java.util.ArrayList<>();
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 127;
    for (int i = 0; i < 41; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 116 + s - 1;
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
    /*   0 */ 50, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5,
    /*  35 */ 6, 6, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 19, 6, 20, 21,
    /*  63 */ 22, 6, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23,
    /*  90 */ 23, 24, 25, 26, 27, 23, 6, 28, 29, 30, 31, 32, 33, 34, 35, 36, 23, 23, 37, 38, 39, 40, 41, 23, 42, 43, 44,
    /* 117 */ 45, 23, 46, 23, 23, 23, 47, 48, 49, 6, 6
  };

  private static final int[] MAP1 =
  {
    /*   0 */ 54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  26 */ 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  52 */ 58, 58, 90, 122, 154, 186, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216,
    /*  74 */ 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 50, 0, 0, 0, 0, 0, 0, 0,
    /*  98 */ 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 6, 6, 6, 7, 8, 9, 10, 11,
    /* 133 */ 12, 13, 14, 15, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 19, 6, 20, 21, 22, 6, 23, 23, 23, 23, 23,
    /* 160 */ 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 24, 25, 26, 27, 23, 6,
    /* 187 */ 28, 29, 30, 31, 32, 33, 34, 35, 36, 23, 23, 37, 38, 39, 40, 41, 23, 42, 43, 44, 45, 23, 46, 23, 23, 23,
    /* 213 */ 47, 48, 49, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
    /* 247 */ 6
  };

  private static final int[] MAP2 =
  {
    /* 0 */ 57344, 65536, 65533, 1114111, 6, 6
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
    /* 29 */ 30, 31, 32, 33, 34, 35, 36, 37
  };

  private static final int[] TRANSITION =
  {
    /*    0 */ 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 816, 816, 816, 816, 818,
    /*   21 */ 826, 941, 1130, 941, 1526, 941, 941, 941, 941, 941, 941, 816, 816, 816, 816, 818, 826, 941, 999, 941,
    /*   41 */ 1000, 941, 941, 941, 941, 941, 941, 816, 816, 816, 816, 818, 826, 941, 1415, 941, 1526, 941, 941, 941,
    /*   61 */ 941, 941, 941, 941, 941, 941, 839, 837, 826, 941, 1130, 941, 1526, 941, 941, 941, 941, 941, 941, 939,
    /*   81 */ 940, 934, 1601, 1598, 847, 941, 1141, 987, 1526, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941,
    /*  101 */ 826, 941, 1130, 941, 1526, 941, 941, 941, 941, 941, 941, 941, 941, 941, 1433, 1521, 826, 941, 1130, 941,
    /*  121 */ 1526, 941, 941, 941, 941, 941, 941, 986, 987, 981, 861, 858, 869, 941, 1141, 987, 1526, 941, 941, 941,
    /*  141 */ 941, 941, 941, 1412, 941, 1408, 883, 880, 826, 941, 1130, 941, 1526, 941, 941, 941, 941, 941, 941, 941,
    /*  161 */ 891, 941, 1151, 903, 826, 941, 1130, 941, 1526, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 927,
    /*  181 */ 826, 941, 895, 941, 908, 941, 941, 941, 941, 941, 941, 941, 1531, 1532, 941, 950, 826, 941, 1130, 941,
    /*  201 */ 1526, 941, 941, 941, 941, 941, 941, 941, 1430, 941, 941, 1432, 826, 941, 1130, 941, 1526, 941, 941, 941,
    /*  221 */ 941, 941, 941, 941, 941, 941, 966, 963, 826, 941, 1130, 941, 1526, 941, 941, 941, 941, 941, 941, 941,
    /*  241 */ 1023, 941, 974, 996, 826, 941, 1119, 941, 1526, 941, 941, 941, 941, 941, 941, 1008, 1008, 1008, 1008,
    /*  260 */ 1011, 826, 941, 1130, 941, 1672, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 942, 826, 1635, 1350,
    /*  280 */ 913, 1687, 916, 1293, 1292, 1636, 1275, 941, 1667, 829, 1114, 1109, 941, 1019, 941, 1130, 941, 1526, 941,
    /*  299 */ 941, 941, 941, 941, 941, 941, 941, 1035, 850, 1031, 826, 941, 1130, 941, 1526, 941, 941, 941, 941, 941,
    /*  319 */ 941, 941, 1104, 1061, 1043, 1047, 1055, 1090, 1130, 941, 1526, 941, 941, 941, 941, 941, 941, 941, 941,
    /*  338 */ 941, 941, 941, 826, 1692, 1130, 941, 1526, 941, 941, 941, 941, 941, 941, 941, 941, 1545, 941, 1099, 826,
    /*  358 */ 941, 1130, 941, 1526, 941, 941, 941, 941, 941, 941, 1278, 1277, 1232, 1631, 919, 1127, 1635, 1350, 913,
    /*  377 */ 1687, 916, 1293, 1292, 1636, 1275, 941, 941, 941, 872, 941, 941, 826, 941, 1130, 941, 1526, 941, 941,
    /*  396 */ 941, 941, 941, 941, 941, 941, 941, 941, 941, 1138, 941, 1141, 987, 1526, 941, 941, 941, 941, 941, 941,
    /*  416 */ 1549, 941, 1149, 1550, 941, 826, 941, 1130, 941, 1526, 941, 941, 941, 941, 941, 941, 1082, 1081, 1067,
    /*  435 */ 1073, 1078, 826, 941, 1130, 941, 1526, 941, 941, 941, 941, 941, 941, 1278, 1277, 1232, 1490, 919, 1127,
    /*  454 */ 1635, 1350, 955, 1159, 916, 1293, 1325, 1182, 1275, 941, 1278, 1277, 1232, 1631, 919, 1127, 1635, 1375,
    /*  472 */ 1203, 1687, 916, 1293, 1292, 1636, 1275, 941, 1444, 1277, 1232, 1631, 919, 1127, 1635, 1350, 913, 1687,
    /*  490 */ 916, 1293, 1292, 1636, 1275, 941, 1278, 1277, 1232, 1631, 919, 1127, 1635, 1350, 1260, 1214, 1206, 1293,
    /*  508 */ 1292, 1636, 1275, 941, 1278, 1277, 1311, 1222, 1227, 1245, 1237, 1253, 913, 1687, 1476, 1271, 1286, 1636,
    /*  526 */ 1724, 941, 1278, 1277, 1338, 1301, 1306, 1127, 1635, 1375, 1203, 1687, 916, 1293, 1292, 1636, 1275, 941,
    /*  544 */ 1278, 1277, 1232, 1319, 1333, 1127, 1635, 1350, 913, 1687, 1346, 1293, 1292, 1636, 1275, 941, 1278, 1654,
    /*  562 */ 1393, 1704, 1358, 1127, 1635, 1350, 913, 1687, 916, 1371, 1292, 1636, 1275, 941, 1278, 1277, 1232, 1383,
    /*  580 */ 1388, 1401, 1635, 1423, 913, 1687, 916, 1189, 1292, 1636, 1275, 941, 1278, 1277, 1232, 1631, 919, 1127,
    /*  598 */ 1635, 1350, 913, 1687, 916, 1293, 1292, 1636, 1441, 941, 1278, 1277, 1232, 1631, 919, 1127, 1494, 1350,
    /*  616 */ 913, 1687, 1452, 1459, 1472, 1636, 1275, 941, 1278, 1277, 1232, 1631, 919, 1127, 1484, 1375, 1502, 1687,
    /*  634 */ 916, 1293, 1195, 1510, 1275, 941, 1278, 1277, 1232, 1631, 919, 1127, 1635, 1350, 913, 1687, 1263, 1293,
    /*  652 */ 1292, 1636, 1275, 941, 1278, 1277, 1232, 1631, 919, 1127, 1635, 1350, 913, 1540, 916, 1293, 1292, 1636,
    /*  670 */ 1275, 941, 1278, 1583, 1619, 1631, 919, 1127, 1718, 1558, 1566, 1464, 916, 1573, 1292, 1636, 1581, 941,
    /*  688 */ 1278, 1277, 1232, 1631, 919, 1127, 1635, 1591, 913, 1687, 916, 1293, 1292, 1682, 1275, 941, 1278, 1277,
    /*  706 */ 1363, 1609, 1614, 1127, 1635, 1375, 1627, 1516, 916, 1293, 1292, 1644, 1652, 941, 1278, 1277, 1232, 1631,
    /*  724 */ 919, 1662, 1635, 1350, 1677, 1687, 1700, 1293, 1292, 1636, 1275, 941, 1278, 1277, 1232, 1631, 1712, 1127,
    /*  742 */ 1635, 1350, 913, 1687, 916, 1293, 1292, 1636, 1275, 941, 941, 941, 941, 1737, 1741, 826, 941, 1130, 941,
    /*  761 */ 1526, 941, 941, 941, 941, 941, 941, 941, 1174, 1172, 1164, 1169, 826, 941, 1130, 941, 1526, 941, 941,
    /*  780 */ 941, 941, 941, 941, 1091, 1728, 941, 1729, 941, 826, 941, 1130, 941, 1526, 941, 941, 941, 941, 941, 941,
    /*  800 */ 941, 941, 988, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 941, 806, 806, 806, 806, 806,
    /*  821 */ 806, 806, 806, 0, 0, 0, 42, 43, 0, 0, 0, 0, 0, 0, 2432, 2432, 0, 1024, 1024, 0, 0, 0, 0, 0, 1024, 0, 0,
    /*  848 */ 384, 43, 0, 0, 0, 0, 0, 0, 2688, 2688, 0, 43, 43, 43, 43, 0, 0, 0, 0, 43, 43, 0, 42, 384, 0, 0, 0, 0, 0,
    /*  877 */ 0, 3328, 0, 0, 1280, 1280, 1280, 1280, 0, 0, 0, 0, 1280, 1280, 0, 1408, 1408, 1408, 0, 0, 0, 0, 0, 77,
    /*  901 */ 703, 0, 0, 1408, 1408, 1408, 1408, 0, 0, 0, 0, 77, 0, 0, 0, 0, 296, 296, 296, 296, 296, 296, 296, 0, 0,
    /*  926 */ 296, 0, 1536, 1536, 1536, 1536, 0, 62, 0, 0, 42, 0, 0, 0, 42, 0, 0, 0, 0, 0, 0, 0, 0, 296, 0, 1664, 1664,
    /*  953 */ 1664, 1710, 0, 0, 0, 0, 296, 339, 296, 296, 0, 53, 53, 53, 53, 0, 0, 0, 0, 53, 53, 2176, 0, 0, 0, 2176,
    /*  979 */ 0, 2176, 0, 0, 43, 0, 0, 0, 43, 0, 0, 0, 0, 0, 0, 0, 896, 2176, 2176, 2237, 0, 0, 0, 0, 0, 62, 640, 0, 0,
    /* 1008 */ 39, 39, 39, 39, 39, 39, 39, 39, 0, 703, 0, 0, 42, 43, 2560, 0, 0, 0, 0, 0, 2176, 0, 0, 0, 2688, 2688,
    /* 1034 */ 2688, 2688, 0, 0, 0, 0, 2688, 0, 0, 0, 54, 0, 0, 0, 0, 0, 54, 2870, 0, 0, 0, 0, 42, 43, 0, 0, 1792, 0, 0,
    /* 1063 */ 0, 2816, 0, 2816, 0, 0, 41, 0, 0, 0, 41, 41, 41, 41, 0, 41, 41, 41, 41, 41, 0, 0, 0, 0, 0, 0, 0, 3200, 0,
    /* 1092 */ 0, 0, 0, 0, 0, 0, 5248, 0, 3072, 3072, 3072, 3121, 0, 0, 0, 0, 2816, 0, 0, 0, 0, 2476, 0, 0, 0, 0, 2432,
    /* 1119 */ 0, 0, 0, 0, 2304, 62, 703, 0, 296, 42, 43, 0, 0, 0, 0, 0, 62, 703, 0, 0, 64, 65, 0, 0, 0, 0, 0, 62, 703,
    /* 1148 */ 42, 0, 3456, 0, 0, 0, 0, 0, 0, 1408, 1408, 296, 343, 296, 296, 62, 0, 0, 0, 0, 5120, 0, 5120, 5120, 5120,
    /* 1173 */ 5120, 0, 0, 0, 5120, 0, 0, 0, 0, 296, 369, 296, 0, 0, 296, 371, 296, 0, 0, 0, 296, 359, 296, 296, 108,
    /* 1198 */ 4352, 109, 296, 367, 296, 43, 0, 0, 0, 296, 296, 296, 296, 296, 296, 354, 296, 296, 344, 296, 62, 0, 0,
    /* 1221 */ 91, 296, 296, 306, 306, 0, 306, 296, 296, 296, 296, 0, 0, 296, 0, 0, 0, 296, 296, 327, 0, 0, 328, 296,
    /* 1245 */ 296, 42, 43, 0, 0, 0, 0, 68, 296, 328, 296, 296, 0, 62, 703, 0, 0, 80, 0, 296, 296, 296, 296, 352, 296,
    /* 1270 */ 296, 296, 99, 100, 0, 296, 296, 296, 296, 0, 0, 0, 0, 0, 0, 0, 296, 363, 0, 0, 0, 366, 296, 296, 0, 0, 0,
    /* 1297 */ 296, 296, 296, 296, 296, 296, 307, 307, 0, 307, 296, 296, 296, 296, 0, 0, 296, 0, 0, 0, 296, 306, 296,
    /* 1320 */ 296, 311, 311, 0, 314, 296, 296, 0, 0, 0, 296, 296, 368, 311, 296, 296, 296, 296, 0, 0, 296, 0, 0, 0,
    /* 1344 */ 296, 307, 0, 296, 349, 296, 296, 296, 296, 296, 0, 62, 703, 0, 315, 296, 296, 296, 296, 0, 0, 296, 0, 0,
    /* 1368 */ 0, 296, 308, 4904, 0, 0, 0, 296, 296, 296, 296, 0, 62, 703, 42, 296, 296, 312, 312, 0, 312, 296, 296,
    /* 1391 */ 296, 296, 0, 0, 296, 0, 47, 0, 296, 296, 296, 42, 43, 0, 0, 0, 67, 0, 0, 1280, 0, 0, 0, 1280, 0, 0, 0, 0,
    /* 1419 */ 0, 62, 78, 0, 296, 296, 331, 332, 0, 62, 703, 0, 0, 1920, 0, 0, 0, 0, 0, 0, 0, 1152, 296, 296, 4776, 296,
    /* 1445 */ 0, 0, 0, 0, 0, 45, 0, 0, 3880, 296, 350, 296, 296, 353, 296, 0, 0, 0, 358, 296, 296, 296, 296, 62, 0, 90,
    /* 1471 */ 0, 362, 296, 0, 0, 0, 296, 296, 296, 351, 296, 296, 296, 0, 325, 296, 296, 0, 0, 296, 296, 296, 313, 0,
    /* 1495 */ 296, 296, 296, 0, 0, 296, 329, 43, 0, 0, 0, 296, 296, 296, 341, 296, 296, 4392, 0, 0, 370, 296, 296, 296,
    /* 1519 */ 345, 62, 0, 0, 0, 1152, 1152, 0, 0, 0, 0, 62, 0, 0, 0, 0, 46, 0, 0, 0, 0, 342, 296, 296, 296, 62, 0, 0,
    /* 1547 */ 0, 49, 0, 0, 0, 0, 0, 3456, 0, 0, 0, 296, 330, 296, 296, 0, 62, 703, 42, 43, 79, 0, 0, 296, 296, 340,
    /* 1573 */ 296, 0, 0, 101, 296, 296, 360, 361, 4264, 296, 296, 296, 0, 0, 0, 0, 0, 48, 3624, 296, 296, 296, 0, 62,
    /* 1597 */ 703, 0, 42, 42, 42, 42, 0, 0, 0, 0, 42, 42, 296, 296, 308, 308, 0, 308, 296, 296, 296, 296, 0, 0, 296, 0,
    /* 1623 */ 48, 0, 296, 296, 43, 0, 0, 81, 296, 296, 296, 296, 0, 296, 296, 296, 0, 0, 296, 296, 296, 4520, 296, 296,
    /* 1647 */ 3712, 0, 296, 296, 372, 296, 4008, 296, 296, 0, 0, 0, 0, 47, 0, 296, 42, 43, 0, 66, 0, 0, 0, 2432, 44, 0,
    /* 1673 */ 0, 0, 0, 512, 0, 0, 0, 0, 338, 296, 296, 296, 0, 4608, 296, 296, 296, 296, 62, 0, 0, 0, 0, 2048, 2944, 0,
    /* 1699 */ 0, 92, 296, 296, 296, 296, 296, 296, 296, 47, 315, 296, 296, 316, 296, 296, 296, 296, 0, 0, 296, 326,
    /* 1721 */ 296, 0, 0, 296, 296, 296, 4136, 0, 0, 0, 0, 0, 5248, 0, 0, 0, 0, 4992, 0, 0, 0, 0, 0, 4992, 4992, 0, 0,
    /* 1748 */ 0
  };

  private static final int[] EXPECTED =
  {
    /*   0 */ 58, 65, 69, 73, 77, 61, 81, 85, 89, 93, 96, 100, 110, 116, 123, 132, 145, 111, 125, 153, 162, 124, 128,
    /*  23 */ 112, 127, 166, 126, 166, 119, 184, 156, 172, 158, 170, 176, 204, 180, 190, 183, 184, 186, 147, 102, 197,
    /*  44 */ 184, 186, 148, 135, 185, 141, 104, 138, 149, 106, 194, 105, 194, 201, 58, 60, 568, 262200, 3145784,
    /*  63 */ 33555006, 1610612858, 524344, 67108920, 268435512, 56, 62, 1082, 17464, 1080, 2105400, 65592, 262200,
    /*  76 */ 262200, 1048632, 67108920, 574, 18882616, 99006, 4227646, -536870854, -402653126, 67960888, -536870854,
    /*  87 */ 1148606, 5277502, -536788934, 9543358, 9674430, 13672254, 32554814, 32, 24, 2, 4, 4, 524288, 268435456,
    /* 101 */ 8192, 0, 0, 0, 4, 1, 2, 0, 2, 16777216, 536870914, 1073741826, 2, -2147483646, 2, 32768, 4194304,
    /* 118 */ -2147483646, 2, 1073741826, 2, -2147483646, 134217730, -2147483646, 2, 2, 2, 2, 268435456, 0, 0, 131072,
    /* 133 */ 8, 16, 4, 1, 2, 32, 0, 2, 8, 0, 0, 16, 4, 268435456, 0, 0, 0, 16, 0, 4, 8, 16, 268435456, 0, 0, 0, 256, 2,
    /* 161 */ 8, 0, 536870914, 1073741826, 2, 0, 1073741826, 2, -2147483646, 128, 128, 0, 0, 0, 128, 10, 128, 0, 16,
    /* 180 */ 386, 23, 128, 192, 0, 0, 0, 0, 2, 8, 54, 128, 128, 192, 8, 0, 16, 0, 1, 2, 32, 1, 0, 16, 0, 64, 20, 20
  };

  private static final String[] TOKEN =
  {
    "(0)",
    "ID",
    "STRING",
    "ML_COMMENT",
    "SL_COMMENT",
    "WS",
    "EOF",
    "'!'",
    "'&'",
    "'('",
    "')'",
    "'*'",
    "'+'",
    "'+='",
    "','",
    "'->'",
    "'.'",
    "'..'",
    "':'",
    "'::'",
    "';'",
    "'='",
    "'=>'",
    "'?'",
    "'?='",
    "'['",
    "']'",
    "'as'",
    "'current'",
    "'enum'",
    "'fragment'",
    "'generate'",
    "'grammar'",
    "'hidden'",
    "'import'",
    "'returns'",
    "'terminal'",
    "'with'",
    "'{'",
    "'|'",
    "'}'"
  };
}

// End
