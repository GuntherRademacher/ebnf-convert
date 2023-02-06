// This file was generated on Sat Jan 7, 2023 21:28 (UTC+01) by REx v5.56 which is Copyright (c) 1979-2022 by Gunther Rademacher <grd@gmx.net>
// REx command line: -backtrack -tree -a none -java -interface de.bottlecaps.railroad.convert.Parser -basex -saxon -name de.bottlecaps.railroad.convert.antlr_4.Antlr_4 antlr_4.ebnf

package de.bottlecaps.railroad.convert.antlr_4;

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

public class Antlr_4 implements de.bottlecaps.railroad.convert.Parser
{
  public static class SaxonInitializer implements Initializer
  {
    @Override
    public void initialize(Configuration conf)
    {
      conf.registerExtensionFunction(new SaxonDefinition_grammarSpec());
    }
  }

  public static Sequence parseGrammarSpec(XPathContext context, String input) throws XPathException
  {
    Builder builder = context.getController().makeBuilder();
    builder.open();
    Antlr_4 parser = new Antlr_4(input, new SaxonTreeBuilder(builder));
    try
    {
      parser.parse_grammarSpec();
    }
    catch (ParseException pe)
    {
      buildError(parser, pe, builder);
    }
    return builder.getCurrentRoot();
  }

  public static class SaxonDefinition_grammarSpec extends SaxonDefinition
  {
    @Override
    public String functionName() {return "parse-grammarSpec";}
    @Override
    public Sequence execute(XPathContext context, String input) throws XPathException
    {
      return parseGrammarSpec(context, input);
    }
  }

  public static abstract class SaxonDefinition extends ExtensionFunctionDefinition
  {
    abstract String functionName();
    abstract Sequence execute(XPathContext context, String input) throws XPathException;

    @Override
    public StructuredQName getFunctionQName() {return new StructuredQName("p", "de/bottlecaps/railroad/convert/antlr_4/Antlr_4", functionName());}
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

  private static void buildError(Antlr_4 parser, ParseException pe, Builder builder) throws XPathException
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

  public static ANode parseGrammarSpec(Str str) throws IOException
  {
    BaseXFunction baseXFunction = new BaseXFunction()
    {
      @Override
      public void execute(Antlr_4 p) {p.parse_grammarSpec();}
    };
    return baseXFunction.call(str);
  }

  public static abstract class BaseXFunction
  {
    protected abstract void execute(Antlr_4 p);

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
      Antlr_4 parser = new Antlr_4();
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

  public Antlr_4()
  {
  }

  public Antlr_4(CharSequence string, EventHandler t)
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
    parse_grammarSpec();
  }

  public void parse_grammarSpec()
  {
    eventHandler.startNonterminal("grammarSpec", e0);
    lookahead1W(35);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | LEXER | PARSER | GRAMMAR |
                                    // TREE_GRAMMAR | WS
    whitespace();
    parse_grammarType();
    lookahead1W(25);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
    whitespace();
    parse_id();
    lookahead1W(7);                 // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | WS
    consume(26);                    // SEMI
    lookahead1W(51);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | TOKENS_SPEC | CHANNELS |
                                    // IMPORT | FRAGMENT | MODE | AT | WS | EOF | RULE_REF | TOKEN_REF
    whitespace();
    parse_sync();
    for (;;)
    {
      if (l1 != 8                   // OPTIONS
       && l1 != 9                   // TOKENS_SPEC
       && l1 != 10                  // CHANNELS
       && l1 != 11                  // IMPORT
       && l1 != 40)                 // AT
      {
        break;
      }
      whitespace();
      parse_prequelConstruct();
      lookahead1W(51);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | TOKENS_SPEC | CHANNELS |
                                    // IMPORT | FRAGMENT | MODE | AT | WS | EOF | RULE_REF | TOKEN_REF
      whitespace();
      parse_sync();
    }
    whitespace();
    parse_rules();
    for (;;)
    {
      if (l1 != 22)                 // MODE
      {
        break;
      }
      whitespace();
      parse_modeSpec();
    }
    consume(51);                    // EOF
    eventHandler.endNonterminal("grammarSpec", e0);
  }

  private void parse_grammarType()
  {
    eventHandler.startNonterminal("grammarType", e0);
    switch (l1)
    {
    case 16:                        // TREE_GRAMMAR
      consume(16);                  // TREE_GRAMMAR
      break;
    default:
      if (l1 != 15)                 // GRAMMAR
      {
        switch (l1)
        {
        case 13:                    // LEXER
          consume(13);              // LEXER
          break;
        default:
          consume(14);              // PARSER
        }
      }
      lookahead1W(4);               // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | GRAMMAR | WS
      consume(15);                  // GRAMMAR
    }
    eventHandler.endNonterminal("grammarType", e0);
  }

  private void parse_prequelConstruct()
  {
    eventHandler.startNonterminal("prequelConstruct", e0);
    switch (l1)
    {
    case 8:                         // OPTIONS
      parse_optionsSpec();
      break;
    case 11:                        // IMPORT
      parse_delegateGrammars();
      break;
    case 9:                         // TOKENS_SPEC
      parse_tokensSpec();
      break;
    case 10:                        // CHANNELS
      parse_channelsSpec();
      break;
    default:
      parse_action();
    }
    eventHandler.endNonterminal("prequelConstruct", e0);
  }

  private void parse_optionsSpec()
  {
    eventHandler.startNonterminal("optionsSpec", e0);
    consume(8);                     // OPTIONS
    for (;;)
    {
      lookahead1W(30);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | RBRACE | WS | RULE_REF | TOKEN_REF
      if (l1 == 43)                 // RBRACE
      {
        break;
      }
      whitespace();
      parse_option();
      lookahead1W(7);               // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | WS
      consume(26);                  // SEMI
    }
    consume(43);                    // RBRACE
    eventHandler.endNonterminal("optionsSpec", e0);
  }

  private void parse_option()
  {
    eventHandler.startNonterminal("option", e0);
    parse_id();
    lookahead1W(9);                 // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | ASSIGN | WS
    consume(32);                    // ASSIGN
    lookahead1W(44);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | INT | STRING_LITERAL | WS | RULE_REF |
                                    // TOKEN_REF | '{'
    whitespace();
    parse_optionValue();
    eventHandler.endNonterminal("option", e0);
  }

  private void parse_optionValue()
  {
    eventHandler.startNonterminal("optionValue", e0);
    switch (l1)
    {
    case 48:                        // STRING_LITERAL
      consume(48);                  // STRING_LITERAL
      break;
    case 58:                        // '{'
      parse_ACTION();
      break;
    case 47:                        // INT
      consume(47);                  // INT
      break;
    default:
      parse_qid();
    }
    eventHandler.endNonterminal("optionValue", e0);
  }

  private void parse_delegateGrammars()
  {
    eventHandler.startNonterminal("delegateGrammars", e0);
    consume(11);                    // IMPORT
    lookahead1W(25);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
    whitespace();
    parse_delegateGrammar();
    for (;;)
    {
      lookahead1W(18);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COMMA | SEMI | WS
      if (l1 != 25)                 // COMMA
      {
        break;
      }
      consume(25);                  // COMMA
      lookahead1W(25);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
      whitespace();
      parse_delegateGrammar();
    }
    consume(26);                    // SEMI
    eventHandler.endNonterminal("delegateGrammars", e0);
  }

  private void parse_delegateGrammar()
  {
    eventHandler.startNonterminal("delegateGrammar", e0);
    parse_id();
    lookahead1W(28);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COMMA | SEMI | ASSIGN | WS
    if (l1 == 32)                   // ASSIGN
    {
      consume(32);                  // ASSIGN
      lookahead1W(25);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
      whitespace();
      parse_id();
    }
    eventHandler.endNonterminal("delegateGrammar", e0);
  }

  private void parse_tokensSpec()
  {
    eventHandler.startNonterminal("tokensSpec", e0);
    consume(9);                     // TOKENS_SPEC
    lookahead1W(30);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | RBRACE | WS | RULE_REF | TOKEN_REF
    switch (l1)
    {
    case 52:                        // RULE_REF
    case 53:                        // TOKEN_REF
      lookahead2W(38);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COMMA | SEMI | ASSIGN | RBRACE | WS
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 1652:                      // RULE_REF COMMA
    case 1653:                      // TOKEN_REF COMMA
    case 2804:                      // RULE_REF RBRACE
    case 2805:                      // TOKEN_REF RBRACE
      whitespace();
      parse_id();
      for (;;)
      {
        lookahead1W(20);            // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COMMA | RBRACE | WS
        if (l1 != 25)               // COMMA
        {
          break;
        }
        consume(25);                // COMMA
        lookahead1W(25);            // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
        whitespace();
        parse_id();
      }
      break;
    default:
      for (;;)
      {
        lookahead1W(30);            // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | RBRACE | WS | RULE_REF | TOKEN_REF
        if (l1 == 43)               // RBRACE
        {
          break;
        }
        whitespace();
        parse_v3tokenSpec();
      }
    }
    consume(43);                    // RBRACE
    eventHandler.endNonterminal("tokensSpec", e0);
  }

  private void parse_v3tokenSpec()
  {
    eventHandler.startNonterminal("v3tokenSpec", e0);
    parse_id();
    lookahead1W(21);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | ASSIGN | WS
    if (l1 == 32)                   // ASSIGN
    {
      consume(32);                  // ASSIGN
      lookahead1W(11);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | STRING_LITERAL | WS
      consume(48);                  // STRING_LITERAL
    }
    lookahead1W(7);                 // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | WS
    consume(26);                    // SEMI
    eventHandler.endNonterminal("v3tokenSpec", e0);
  }

  private void parse_channelsSpec()
  {
    eventHandler.startNonterminal("channelsSpec", e0);
    consume(10);                    // CHANNELS
    lookahead1W(25);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
    whitespace();
    parse_id();
    for (;;)
    {
      lookahead1W(20);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COMMA | RBRACE | WS
      if (l1 != 25)                 // COMMA
      {
        break;
      }
      consume(25);                  // COMMA
      lookahead1W(25);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
      whitespace();
      parse_id();
    }
    consume(43);                    // RBRACE
    eventHandler.endNonterminal("channelsSpec", e0);
  }

  private void parse_action()
  {
    eventHandler.startNonterminal("action", e0);
    consume(40);                    // AT
    lookahead1W(36);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | LEXER | PARSER | WS | RULE_REF |
                                    // TOKEN_REF
    switch (l1)
    {
    case 52:                        // RULE_REF
    case 53:                        // TOKEN_REF
      lookahead2W(17);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COLONCOLON | WS | '{'
      break;
    default:
      lk = l1;
    }
    if (lk != 3764                  // RULE_REF '{'
     && lk != 3765)                 // TOKEN_REF '{'
    {
      whitespace();
      parse_actionScopeName();
      lookahead1W(6);               // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COLONCOLON | WS
      consume(24);                  // COLONCOLON
    }
    lookahead1W(25);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
    whitespace();
    parse_id();
    lookahead1W(14);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | '{'
    whitespace();
    parse_ACTION();
    eventHandler.endNonterminal("action", e0);
  }

  private void parse_actionScopeName()
  {
    eventHandler.startNonterminal("actionScopeName", e0);
    switch (l1)
    {
    case 13:                        // LEXER
      consume(13);                  // LEXER
      break;
    case 14:                        // PARSER
      consume(14);                  // PARSER
      break;
    default:
      parse_id();
    }
    eventHandler.endNonterminal("actionScopeName", e0);
  }

  private void parse_modeSpec()
  {
    eventHandler.startNonterminal("modeSpec", e0);
    consume(22);                    // MODE
    lookahead1W(25);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
    whitespace();
    parse_id();
    lookahead1W(7);                 // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | WS
    consume(26);                    // SEMI
    lookahead1W(34);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | FRAGMENT | MODE | WS | EOF | TOKEN_REF
    whitespace();
    parse_sync();
    for (;;)
    {
      if (l1 != 12                  // FRAGMENT
       && l1 != 53)                 // TOKEN_REF
      {
        break;
      }
      whitespace();
      parse_lexerRule();
      lookahead1W(34);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | FRAGMENT | MODE | WS | EOF | TOKEN_REF
      whitespace();
      parse_sync();
    }
    eventHandler.endNonterminal("modeSpec", e0);
  }

  private void parse_rules()
  {
    eventHandler.startNonterminal("rules", e0);
    parse_sync();
    for (;;)
    {
      if (l1 == 22                  // MODE
       || l1 == 51)                 // EOF
      {
        break;
      }
      whitespace();
      parse_rule();
      lookahead1W(42);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | FRAGMENT | MODE | WS | EOF |
                                    // RULE_REF | TOKEN_REF
      whitespace();
      parse_sync();
    }
    eventHandler.endNonterminal("rules", e0);
  }

  private void parse_sync()
  {
    eventHandler.startNonterminal("sync", e0);
    eventHandler.endNonterminal("sync", e0);
  }

  private void parse_rule()
  {
    eventHandler.startNonterminal("rule", e0);
    switch (l1)
    {
    case 52:                        // RULE_REF
      parse_parserRule();
      break;
    default:
      parse_lexerRule();
    }
    eventHandler.endNonterminal("rule", e0);
  }

  private void parse_parserRule()
  {
    eventHandler.startNonterminal("parserRule", e0);
    consume(52);                    // RULE_REF
    lookahead1W(47);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | RETURNS | LOCALS | THROWS |
                                    // COLON | AT | WS | '['
    if (l1 == 56)                   // '['
    {
      whitespace();
      parse_ARG_ACTION();
    }
    lookahead1W(45);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | RETURNS | LOCALS | THROWS |
                                    // COLON | AT | WS
    if (l1 == 17)                   // RETURNS
    {
      whitespace();
      parse_ruleReturns();
    }
    lookahead1W(41);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | LOCALS | THROWS | COLON |
                                    // AT | WS
    if (l1 == 19)                   // THROWS
    {
      whitespace();
      parse_throwsSpec();
    }
    if (l1 == 18)                   // LOCALS
    {
      whitespace();
      parse_localsSpec();
    }
    lookahead1W(26);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | COLON | AT | WS
    whitespace();
    parse_rulePrequels();
    consume(23);                    // COLON
    lookahead1W(55);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | LT | OR | DOT |
                                    // POUND | NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '{'
    whitespace();
    parse_ruleBlock();
    consume(26);                    // SEMI
    lookahead1W(48);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | FRAGMENT | CATCH | FINALLY | MODE |
                                    // WS | EOF | RULE_REF | TOKEN_REF
    whitespace();
    parse_exceptionGroup();
    eventHandler.endNonterminal("parserRule", e0);
  }

  private void parse_exceptionGroup()
  {
    eventHandler.startNonterminal("exceptionGroup", e0);
    for (;;)
    {
      lookahead1W(48);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | FRAGMENT | CATCH | FINALLY | MODE |
                                    // WS | EOF | RULE_REF | TOKEN_REF
      if (l1 != 20)                 // CATCH
      {
        break;
      }
      whitespace();
      parse_exceptionHandler();
    }
    if (l1 == 21)                   // FINALLY
    {
      whitespace();
      parse_finallyClause();
    }
    eventHandler.endNonterminal("exceptionGroup", e0);
  }

  private void parse_exceptionHandler()
  {
    eventHandler.startNonterminal("exceptionHandler", e0);
    consume(20);                    // CATCH
    lookahead1W(13);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | '['
    whitespace();
    parse_ARG_ACTION();
    lookahead1W(14);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | '{'
    whitespace();
    parse_ACTION();
    eventHandler.endNonterminal("exceptionHandler", e0);
  }

  private void parse_finallyClause()
  {
    eventHandler.startNonterminal("finallyClause", e0);
    consume(21);                    // FINALLY
    lookahead1W(14);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | '{'
    whitespace();
    parse_ACTION();
    eventHandler.endNonterminal("finallyClause", e0);
  }

  private void parse_rulePrequels()
  {
    eventHandler.startNonterminal("rulePrequels", e0);
    parse_sync();
    for (;;)
    {
      if (l1 == 23)                 // COLON
      {
        break;
      }
      whitespace();
      parse_rulePrequel();
      lookahead1W(26);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | COLON | AT | WS
      whitespace();
      parse_sync();
    }
    eventHandler.endNonterminal("rulePrequels", e0);
  }

  private void parse_rulePrequel()
  {
    eventHandler.startNonterminal("rulePrequel", e0);
    switch (l1)
    {
    case 8:                         // OPTIONS
      parse_optionsSpec();
      break;
    default:
      parse_ruleAction();
    }
    eventHandler.endNonterminal("rulePrequel", e0);
  }

  private void parse_ruleReturns()
  {
    eventHandler.startNonterminal("ruleReturns", e0);
    consume(17);                    // RETURNS
    lookahead1W(13);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | '['
    whitespace();
    parse_ARG_ACTION();
    eventHandler.endNonterminal("ruleReturns", e0);
  }

  private void parse_throwsSpec()
  {
    eventHandler.startNonterminal("throwsSpec", e0);
    consume(19);                    // THROWS
    lookahead1W(25);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
    whitespace();
    parse_qid();
    for (;;)
    {
      if (l1 != 25)                 // COMMA
      {
        break;
      }
      consume(25);                  // COMMA
      lookahead1W(25);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
      whitespace();
      parse_qid();
    }
    eventHandler.endNonterminal("throwsSpec", e0);
  }

  private void parse_localsSpec()
  {
    eventHandler.startNonterminal("localsSpec", e0);
    consume(18);                    // LOCALS
    lookahead1W(13);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | '['
    whitespace();
    parse_ARG_ACTION();
    eventHandler.endNonterminal("localsSpec", e0);
  }

  private void parse_ruleAction()
  {
    eventHandler.startNonterminal("ruleAction", e0);
    consume(40);                    // AT
    lookahead1W(25);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
    whitespace();
    parse_id();
    lookahead1W(14);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | '{'
    whitespace();
    parse_ACTION();
    eventHandler.endNonterminal("ruleAction", e0);
  }

  private void parse_ruleBlock()
  {
    eventHandler.startNonterminal("ruleBlock", e0);
    parse_ruleAltList();
    eventHandler.endNonterminal("ruleBlock", e0);
  }

  private void parse_ruleAltList()
  {
    eventHandler.startNonterminal("ruleAltList", e0);
    parse_labeledAlt();
    for (;;)
    {
      lookahead1W(22);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | OR | WS
      if (l1 != 37)                 // OR
      {
        break;
      }
      consume(37);                  // OR
      lookahead1W(55);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | LT | OR | DOT |
                                    // POUND | NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '{'
      whitespace();
      parse_labeledAlt();
    }
    eventHandler.endNonterminal("ruleAltList", e0);
  }

  private void parse_labeledAlt()
  {
    eventHandler.startNonterminal("labeledAlt", e0);
    parse_alternative();
    if (l1 == 41)                   // POUND
    {
      consume(41);                  // POUND
      lookahead1W(25);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
      whitespace();
      parse_id();
    }
    eventHandler.endNonterminal("labeledAlt", e0);
  }

  private void parse_lexerRule()
  {
    eventHandler.startNonterminal("lexerRule", e0);
    if (l1 == 12)                   // FRAGMENT
    {
      consume(12);                  // FRAGMENT
    }
    lookahead1W(12);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | TOKEN_REF
    consume(53);                    // TOKEN_REF
    lookahead1W(5);                 // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COLON | WS
    consume(23);                    // COLON
    lookahead1W(54);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RARROW | OR | DOT |
                                    // NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '[' | '{'
    whitespace();
    parse_lexerRuleBlock();
    consume(26);                    // SEMI
    eventHandler.endNonterminal("lexerRule", e0);
  }

  private void parse_lexerRuleBlock()
  {
    eventHandler.startNonterminal("lexerRuleBlock", e0);
    parse_lexerAltList();
    eventHandler.endNonterminal("lexerRuleBlock", e0);
  }

  private void parse_lexerAltList()
  {
    eventHandler.startNonterminal("lexerAltList", e0);
    parse_lexerAlt();
    for (;;)
    {
      if (l1 != 37)                 // OR
      {
        break;
      }
      consume(37);                  // OR
      lookahead1W(58);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW | OR |
                                    // DOT | NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '[' | '{'
      whitespace();
      parse_lexerAlt();
    }
    eventHandler.endNonterminal("lexerAltList", e0);
  }

  private void parse_lexerAlt()
  {
    eventHandler.startNonterminal("lexerAlt", e0);
    parse_lexerElements();
    if (l1 == 29)                   // RARROW
    {
      whitespace();
      parse_lexerCommands();
    }
    eventHandler.endNonterminal("lexerAlt", e0);
  }

  private void parse_lexerElements()
  {
    eventHandler.startNonterminal("lexerElements", e0);
    for (;;)
    {
      lookahead1W(58);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW | OR |
                                    // DOT | NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '[' | '{'
      if (l1 == 26                  // SEMI
       || l1 == 28                  // RPAREN
       || l1 == 29                  // RARROW
       || l1 == 37)                 // OR
      {
        break;
      }
      whitespace();
      parse_lexerElement();
    }
    eventHandler.endNonterminal("lexerElements", e0);
  }

  private void parse_lexerElement()
  {
    eventHandler.startNonterminal("lexerElement", e0);
    switch (l1)
    {
    case 58:                        // '{'
      parse_actionElement();
      break;
    default:
      switch (l1)
      {
      case 52:                      // RULE_REF
        lookahead2W(69);            // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW |
                                    // ASSIGN | QUESTION | STAR | PLUS | PLUS_ASSIGN | OR | DOT | NOT | STRING_LITERAL |
                                    // WS | RULE_REF | TOKEN_REF | '[' | '{'
        break;
      case 53:                      // TOKEN_REF
        lookahead2W(71);            // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW | LT |
                                    // ASSIGN | QUESTION | STAR | PLUS | PLUS_ASSIGN | OR | DOT | NOT | STRING_LITERAL |
                                    // WS | RULE_REF | TOKEN_REF | '[' | '{'
        break;
      default:
        lk = l1;
      }
      switch (lk)
      {
      case 2100:                    // RULE_REF ASSIGN
      case 2101:                    // TOKEN_REF ASSIGN
      case 2356:                    // RULE_REF PLUS_ASSIGN
      case 2357:                    // TOKEN_REF PLUS_ASSIGN
        parse_labeledLexerElement();
        break;
      case 27:                      // LPAREN
        parse_lexerBlock();
        break;
      default:
        parse_lexerAtom();
      }
      lookahead1W(63);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW |
                                    // QUESTION | STAR | PLUS | OR | DOT | NOT | STRING_LITERAL | WS | RULE_REF |
                                    // TOKEN_REF | '[' | '{'
      if (l1 == 33                  // QUESTION
       || l1 == 34                  // STAR
       || l1 == 35)                 // PLUS
      {
        whitespace();
        parse_ebnfSuffix();
      }
    }
    eventHandler.endNonterminal("lexerElement", e0);
  }

  private void parse_labeledLexerElement()
  {
    eventHandler.startNonterminal("labeledLexerElement", e0);
    parse_id();
    lookahead1W(24);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | ASSIGN | PLUS_ASSIGN | WS
    switch (l1)
    {
    case 32:                        // ASSIGN
      consume(32);                  // ASSIGN
      break;
    default:
      consume(36);                  // PLUS_ASSIGN
    }
    lookahead1W(49);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | LPAREN | DOT | NOT | STRING_LITERAL |
                                    // WS | RULE_REF | TOKEN_REF | '['
    switch (l1)
    {
    case 27:                        // LPAREN
      whitespace();
      parse_lexerBlock();
      break;
    default:
      whitespace();
      parse_lexerAtom();
    }
    eventHandler.endNonterminal("labeledLexerElement", e0);
  }

  private void parse_lexerBlock()
  {
    eventHandler.startNonterminal("lexerBlock", e0);
    consume(27);                    // LPAREN
    lookahead1W(57);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | LPAREN | RPAREN | RARROW |
                                    // OR | DOT | NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '[' | '{'
    if (l1 == 8)                    // OPTIONS
    {
      whitespace();
      parse_optionsSpec();
      lookahead1W(5);               // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COLON | WS
      consume(23);                  // COLON
    }
    lookahead1W(56);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | LPAREN | RPAREN | RARROW | OR | DOT |
                                    // NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '[' | '{'
    whitespace();
    parse_lexerAltList();
    consume(28);                    // RPAREN
    eventHandler.endNonterminal("lexerBlock", e0);
  }

  private void parse_lexerCommands()
  {
    eventHandler.startNonterminal("lexerCommands", e0);
    consume(29);                    // RARROW
    lookahead1W(27);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | MODE | WS | RULE_REF | TOKEN_REF
    whitespace();
    parse_lexerCommand();
    for (;;)
    {
      lookahead1W(37);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COMMA | SEMI | RPAREN | OR | WS
      if (l1 != 25)                 // COMMA
      {
        break;
      }
      consume(25);                  // COMMA
      lookahead1W(27);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | MODE | WS | RULE_REF | TOKEN_REF
      whitespace();
      parse_lexerCommand();
    }
    eventHandler.endNonterminal("lexerCommands", e0);
  }

  private void parse_lexerCommand()
  {
    eventHandler.startNonterminal("lexerCommand", e0);
    parse_lexerCommandName();
    lookahead1W(43);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COMMA | SEMI | LPAREN | RPAREN | OR |
                                    // WS
    if (l1 == 27)                   // LPAREN
    {
      consume(27);                  // LPAREN
      lookahead1W(31);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | INT | WS | RULE_REF | TOKEN_REF
      whitespace();
      parse_lexerCommandExpr();
      lookahead1W(8);               // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | RPAREN | WS
      consume(28);                  // RPAREN
    }
    eventHandler.endNonterminal("lexerCommand", e0);
  }

  private void parse_lexerCommandExpr()
  {
    eventHandler.startNonterminal("lexerCommandExpr", e0);
    switch (l1)
    {
    case 47:                        // INT
      consume(47);                  // INT
      break;
    default:
      parse_id();
    }
    eventHandler.endNonterminal("lexerCommandExpr", e0);
  }

  private void parse_lexerCommandName()
  {
    eventHandler.startNonterminal("lexerCommandName", e0);
    switch (l1)
    {
    case 22:                        // MODE
      consume(22);                  // MODE
      break;
    default:
      parse_id();
    }
    eventHandler.endNonterminal("lexerCommandName", e0);
  }

  private void parse_altList()
  {
    eventHandler.startNonterminal("altList", e0);
    parse_alternative();
    for (;;)
    {
      if (l1 != 37)                 // OR
      {
        break;
      }
      consume(37);                  // OR
      lookahead1W(52);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | LPAREN | RPAREN | LT | OR | DOT |
                                    // NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '{'
      whitespace();
      parse_alternative();
    }
    eventHandler.endNonterminal("altList", e0);
  }

  private void parse_alternative()
  {
    eventHandler.startNonterminal("alternative", e0);
    if (l1 == 30)                   // LT
    {
      whitespace();
      parse_elementOptions();
    }
    for (;;)
    {
      lookahead1W(53);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | OR | DOT |
                                    // POUND | NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '{'
      if (l1 == 26                  // SEMI
       || l1 == 28                  // RPAREN
       || l1 == 37                  // OR
       || l1 == 41)                 // POUND
      {
        break;
      }
      whitespace();
      parse_element();
    }
    eventHandler.endNonterminal("alternative", e0);
  }

  private void parse_element()
  {
    eventHandler.startNonterminal("element", e0);
    switch (l1)
    {
    case 27:                        // LPAREN
      parse_ebnf();
      break;
    case 58:                        // '{'
      parse_actionElement();
      break;
    default:
      switch (l1)
      {
      case 52:                      // RULE_REF
        lookahead2W(73);            // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | LT | ASSIGN |
                                    // QUESTION | STAR | PLUS | PLUS_ASSIGN | OR | DOT | POUND | NOT | STRING_LITERAL |
                                    // WS | RULE_REF | TOKEN_REF | '[' | '{'
        break;
      case 53:                      // TOKEN_REF
        lookahead2W(70);            // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | LT | ASSIGN |
                                    // QUESTION | STAR | PLUS | PLUS_ASSIGN | OR | DOT | POUND | NOT | STRING_LITERAL |
                                    // WS | RULE_REF | TOKEN_REF | '{'
        break;
      default:
        lk = l1;
      }
      switch (lk)
      {
      case 2100:                    // RULE_REF ASSIGN
      case 2101:                    // TOKEN_REF ASSIGN
      case 2356:                    // RULE_REF PLUS_ASSIGN
      case 2357:                    // TOKEN_REF PLUS_ASSIGN
        parse_labeledElement();
        break;
      default:
        parse_atom();
      }
      lookahead1W(62);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | QUESTION |
                                    // STAR | PLUS | OR | DOT | POUND | NOT | STRING_LITERAL | WS | RULE_REF |
                                    // TOKEN_REF | '{'
      if (l1 == 33                  // QUESTION
       || l1 == 34                  // STAR
       || l1 == 35)                 // PLUS
      {
        whitespace();
        parse_ebnfSuffix();
      }
    }
    eventHandler.endNonterminal("element", e0);
  }

  private void parse_actionElement()
  {
    eventHandler.startNonterminal("actionElement", e0);
    parse_ACTION();
    lookahead1W(60);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW | LT |
                                    // OR | DOT | POUND | NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '[' | '{'
    if (l1 == 30)                   // LT
    {
      whitespace();
      parse_elementOptions();
    }
    eventHandler.endNonterminal("actionElement", e0);
  }

  private void parse_labeledElement()
  {
    eventHandler.startNonterminal("labeledElement", e0);
    parse_id();
    lookahead1W(24);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | ASSIGN | PLUS_ASSIGN | WS
    switch (l1)
    {
    case 32:                        // ASSIGN
      consume(32);                  // ASSIGN
      break;
    default:
      consume(36);                  // PLUS_ASSIGN
    }
    lookahead1W(46);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | LPAREN | DOT | NOT | STRING_LITERAL |
                                    // WS | RULE_REF | TOKEN_REF
    switch (l1)
    {
    case 27:                        // LPAREN
      whitespace();
      parse_block();
      break;
    default:
      whitespace();
      parse_atom();
    }
    eventHandler.endNonterminal("labeledElement", e0);
  }

  private void parse_ebnf()
  {
    eventHandler.startNonterminal("ebnf", e0);
    parse_block();
    lookahead1W(62);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | QUESTION |
                                    // STAR | PLUS | OR | DOT | POUND | NOT | STRING_LITERAL | WS | RULE_REF |
                                    // TOKEN_REF | '{'
    if (l1 == 33                    // QUESTION
     || l1 == 34                    // STAR
     || l1 == 35)                   // PLUS
    {
      whitespace();
      parse_blockSuffix();
    }
    eventHandler.endNonterminal("ebnf", e0);
  }

  private void parse_blockSuffix()
  {
    eventHandler.startNonterminal("blockSuffix", e0);
    parse_ebnfSuffix();
    eventHandler.endNonterminal("blockSuffix", e0);
  }

  private void parse_ebnfSuffix()
  {
    eventHandler.startNonterminal("ebnfSuffix", e0);
    switch (l1)
    {
    case 33:                        // QUESTION
      consume(33);                  // QUESTION
      break;
    case 34:                        // STAR
      consume(34);                  // STAR
      break;
    default:
      consume(35);                  // PLUS
    }
    lookahead1W(61);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW |
                                    // QUESTION | OR | DOT | POUND | NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF |
                                    // '[' | '{'
    if (l1 == 33)                   // QUESTION
    {
      consume(33);                  // QUESTION
    }
    eventHandler.endNonterminal("ebnfSuffix", e0);
  }

  private void parse_lexerAtom()
  {
    eventHandler.startNonterminal("lexerAtom", e0);
    switch (l1)
    {
    case 48:                        // STRING_LITERAL
      lookahead2W(67);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW | LT |
                                    // QUESTION | STAR | PLUS | OR | DOT | RANGE | NOT | STRING_LITERAL | WS |
                                    // RULE_REF | TOKEN_REF | '[' | '{'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 2544:                      // STRING_LITERAL RANGE
      parse_range();
      break;
    case 52:                        // RULE_REF
      consume(52);                  // RULE_REF
      break;
    case 42:                        // NOT
      parse_notSet();
      break;
    case 38:                        // DOT
      parse_wildcard();
      break;
    case 56:                        // '['
      parse_LEXER_CHAR_SET();
      break;
    default:
      parse_terminal();
    }
    eventHandler.endNonterminal("lexerAtom", e0);
  }

  private void parse_atom()
  {
    eventHandler.startNonterminal("atom", e0);
    switch (l1)
    {
    case 48:                        // STRING_LITERAL
      lookahead2W(65);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | LT |
                                    // QUESTION | STAR | PLUS | OR | DOT | RANGE | POUND | NOT | STRING_LITERAL | WS |
                                    // RULE_REF | TOKEN_REF | '{'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 2544:                      // STRING_LITERAL RANGE
      parse_range();
      break;
    case 52:                        // RULE_REF
      parse_ruleref();
      break;
    case 42:                        // NOT
      parse_notSet();
      break;
    case 38:                        // DOT
      parse_wildcard();
      break;
    default:
      parse_terminal();
    }
    eventHandler.endNonterminal("atom", e0);
  }

  private void parse_wildcard()
  {
    eventHandler.startNonterminal("wildcard", e0);
    consume(38);                    // DOT
    lookahead1W(68);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW | LT |
                                    // QUESTION | STAR | PLUS | OR | DOT | POUND | NOT | STRING_LITERAL | WS |
                                    // RULE_REF | TOKEN_REF | '[' | '{'
    if (l1 == 30)                   // LT
    {
      whitespace();
      parse_elementOptions();
    }
    eventHandler.endNonterminal("wildcard", e0);
  }

  private void parse_notSet()
  {
    eventHandler.startNonterminal("notSet", e0);
    consume(42);                    // NOT
    lookahead1W(40);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | LPAREN | STRING_LITERAL | WS |
                                    // TOKEN_REF | '['
    switch (l1)
    {
    case 27:                        // LPAREN
      whitespace();
      parse_blockSet();
      break;
    default:
      whitespace();
      parse_setElement();
    }
    eventHandler.endNonterminal("notSet", e0);
  }

  private void parse_blockSet()
  {
    eventHandler.startNonterminal("blockSet", e0);
    consume(27);                    // LPAREN
    lookahead1W(32);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | STRING_LITERAL | WS | TOKEN_REF | '['
    whitespace();
    parse_setElement();
    for (;;)
    {
      lookahead1W(23);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | RPAREN | OR | WS
      if (l1 != 37)                 // OR
      {
        break;
      }
      consume(37);                  // OR
      lookahead1W(32);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | STRING_LITERAL | WS | TOKEN_REF | '['
      whitespace();
      parse_setElement();
    }
    consume(28);                    // RPAREN
    eventHandler.endNonterminal("blockSet", e0);
  }

  private void parse_setElement()
  {
    eventHandler.startNonterminal("setElement", e0);
    switch (l1)
    {
    case 48:                        // STRING_LITERAL
      lookahead2W(72);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW | LT |
                                    // QUESTION | STAR | PLUS | OR | DOT | RANGE | POUND | NOT | STRING_LITERAL | WS |
                                    // RULE_REF | TOKEN_REF | '[' | '{'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 2544:                      // STRING_LITERAL RANGE
      parse_range();
      break;
    case 56:                        // '['
      parse_LEXER_CHAR_SET();
      break;
    default:
      switch (l1)
      {
      case 53:                      // TOKEN_REF
        consume(53);                // TOKEN_REF
        break;
      default:
        consume(48);                // STRING_LITERAL
      }
      lookahead1W(68);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW | LT |
                                    // QUESTION | STAR | PLUS | OR | DOT | POUND | NOT | STRING_LITERAL | WS |
                                    // RULE_REF | TOKEN_REF | '[' | '{'
      if (l1 == 30)                 // LT
      {
        whitespace();
        parse_elementOptions();
      }
    }
    eventHandler.endNonterminal("setElement", e0);
  }

  private void parse_block()
  {
    eventHandler.startNonterminal("block", e0);
    consume(27);                    // LPAREN
    lookahead1W(59);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | COLON | LPAREN | RPAREN |
                                    // LT | OR | DOT | AT | NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '{'
    if (l1 == 8                     // OPTIONS
     || l1 == 23                    // COLON
     || l1 == 40)                   // AT
    {
      if (l1 == 8)                  // OPTIONS
      {
        whitespace();
        parse_optionsSpec();
      }
      for (;;)
      {
        lookahead1W(16);            // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COLON | AT | WS
        if (l1 != 40)               // AT
        {
          break;
        }
        whitespace();
        parse_ruleAction();
      }
      consume(23);                  // COLON
    }
    lookahead1W(52);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | LPAREN | RPAREN | LT | OR | DOT |
                                    // NOT | STRING_LITERAL | WS | RULE_REF | TOKEN_REF | '{'
    whitespace();
    parse_altList();
    consume(28);                    // RPAREN
    eventHandler.endNonterminal("block", e0);
  }

  private void parse_ruleref()
  {
    eventHandler.startNonterminal("ruleref", e0);
    consume(52);                    // RULE_REF
    lookahead1W(66);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | LT |
                                    // QUESTION | STAR | PLUS | OR | DOT | POUND | NOT | STRING_LITERAL | WS |
                                    // RULE_REF | TOKEN_REF | '[' | '{'
    if (l1 == 56)                   // '['
    {
      whitespace();
      parse_ARG_ACTION();
    }
    lookahead1W(64);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | LT |
                                    // QUESTION | STAR | PLUS | OR | DOT | POUND | NOT | STRING_LITERAL | WS |
                                    // RULE_REF | TOKEN_REF | '{'
    if (l1 == 30)                   // LT
    {
      whitespace();
      parse_elementOptions();
    }
    eventHandler.endNonterminal("ruleref", e0);
  }

  private void parse_range()
  {
    eventHandler.startNonterminal("range", e0);
    consume(48);                    // STRING_LITERAL
    lookahead1W(10);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | RANGE | WS
    consume(39);                    // RANGE
    lookahead1W(11);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | STRING_LITERAL | WS
    consume(48);                    // STRING_LITERAL
    eventHandler.endNonterminal("range", e0);
  }

  private void parse_terminal()
  {
    eventHandler.startNonterminal("terminal", e0);
    switch (l1)
    {
    case 53:                        // TOKEN_REF
      consume(53);                  // TOKEN_REF
      break;
    default:
      consume(48);                  // STRING_LITERAL
    }
    lookahead1W(68);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | SEMI | LPAREN | RPAREN | RARROW | LT |
                                    // QUESTION | STAR | PLUS | OR | DOT | POUND | NOT | STRING_LITERAL | WS |
                                    // RULE_REF | TOKEN_REF | '[' | '{'
    if (l1 == 30)                   // LT
    {
      whitespace();
      parse_elementOptions();
    }
    eventHandler.endNonterminal("terminal", e0);
  }

  private void parse_elementOptions()
  {
    eventHandler.startNonterminal("elementOptions", e0);
    consume(30);                    // LT
    lookahead1W(29);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | GT | WS | RULE_REF | TOKEN_REF
    if (l1 != 31)                   // GT
    {
      whitespace();
      parse_elementOption();
      for (;;)
      {
        lookahead1W(19);            // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COMMA | GT | WS
        if (l1 != 25)               // COMMA
        {
          break;
        }
        consume(25);                // COMMA
        lookahead1W(25);            // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
        whitespace();
        parse_elementOption();
      }
    }
    consume(31);                    // GT
    eventHandler.endNonterminal("elementOptions", e0);
  }

  private void parse_elementOption()
  {
    eventHandler.startNonterminal("elementOption", e0);
    switch (l1)
    {
    case 52:                        // RULE_REF
    case 53:                        // TOKEN_REF
      lookahead2W(39);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | COMMA | GT | ASSIGN | DOT | WS
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 2100:                      // RULE_REF ASSIGN
    case 2101:                      // TOKEN_REF ASSIGN
      parse_id();
      lookahead1W(9);               // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | ASSIGN | WS
      consume(32);                  // ASSIGN
      lookahead1W(44);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | INT | STRING_LITERAL | WS | RULE_REF |
                                    // TOKEN_REF | '{'
      whitespace();
      parse_optionValue();
      break;
    default:
      parse_qid();
    }
    eventHandler.endNonterminal("elementOption", e0);
  }

  private void parse_id()
  {
    eventHandler.startNonterminal("id", e0);
    switch (l1)
    {
    case 52:                        // RULE_REF
      consume(52);                  // RULE_REF
      break;
    default:
      consume(53);                  // TOKEN_REF
    }
    eventHandler.endNonterminal("id", e0);
  }

  private void parse_qid()
  {
    eventHandler.startNonterminal("qid", e0);
    parse_id();
    for (;;)
    {
      lookahead1W(50);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | LOCALS | COLON | COMMA |
                                    // SEMI | GT | DOT | AT | WS
      if (l1 != 38)                 // DOT
      {
        break;
      }
      consume(38);                  // DOT
      lookahead1W(25);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | WS | RULE_REF | TOKEN_REF
      whitespace();
      parse_id();
    }
    eventHandler.endNonterminal("qid", e0);
  }

  private void parse_ARG_ACTION()
  {
    eventHandler.startNonterminal("ARG_ACTION", e0);
    consume(56);                    // '['
    for (;;)
    {
      lookahead1(15);               // NON_RIGHT_BRACKET | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL | '[' | ']'
      if (l1 == 57)                 // ']'
      {
        break;
      }
      switch (l1)
      {
      case 56:                      // '['
        parse_ARG_ACTION();
        break;
      case 45:                      // ACTION_STRING_LITERAL
        consume(45);                // ACTION_STRING_LITERAL
        break;
      case 44:                      // ACTION_CHAR_LITERAL
        consume(44);                // ACTION_CHAR_LITERAL
        break;
      default:
        consume(3);                 // NON_RIGHT_BRACKET
      }
    }
    consume(57);                    // ']'
    eventHandler.endNonterminal("ARG_ACTION", e0);
  }

  private void parse_ACTION()
  {
    eventHandler.startNonterminal("ACTION", e0);
    parse_NESTED_ACTION();
    lookahead1W(74);                // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | TOKENS_SPEC | CHANNELS |
                                    // IMPORT | FRAGMENT | CATCH | FINALLY | MODE | COLON | COMMA | SEMI | LPAREN |
                                    // RPAREN | RARROW | LT | GT | OR | DOT | AT | POUND | NOT | STRING_LITERAL | WS |
                                    // EOF | RULE_REF | TOKEN_REF | '?' | '[' | '{'
    if (l1 == 55)                   // '?'
    {
      consume(55);                  // '?'
      lookahead1W(75);              // MULTI_LINE_COMMENT | SINGLE_LINE_COMMENT | OPTIONS | TOKENS_SPEC | CHANNELS |
                                    // IMPORT | FRAGMENT | CATCH | FINALLY | MODE | COLON | COMMA | SEMI | LPAREN |
                                    // RPAREN | RARROW | LT | GT | OR | DOT | AT | POUND | NOT | STRING_LITERAL | WS |
                                    // WSNLCHARS | EOF | RULE_REF | TOKEN_REF | '=>' | '[' | '{'
      if (l1 == 50                  // WSNLCHARS
       || l1 == 54)                 // '=>'
      {
        for (;;)
        {
          lookahead1(2);            // WSNLCHARS | '=>'
          if (l1 != 50)             // WSNLCHARS
          {
            break;
          }
          consume(50);              // WSNLCHARS
        }
        consume(54);                // '=>'
      }
    }
    eventHandler.endNonterminal("ACTION", e0);
  }

  private void parse_NESTED_ACTION()
  {
    eventHandler.startNonterminal("NESTED_ACTION", e0);
    consume(58);                    // '{'
    for (;;)
    {
      lookahead1(33);               // NON_SLASH_QUOTE_BRACE | ACTION_COMMENT | ACTION_CHAR_LITERAL |
                                    // ACTION_STRING_LITERAL | ACTION_ESC | '{' | '}'
      if (l1 == 59)                 // '}'
      {
        break;
      }
      switch (l1)
      {
      case 58:                      // '{'
        parse_NESTED_ACTION();
        break;
      case 44:                      // ACTION_CHAR_LITERAL
        consume(44);                // ACTION_CHAR_LITERAL
        break;
      case 5:                       // ACTION_COMMENT
        consume(5);                 // ACTION_COMMENT
        break;
      case 45:                      // ACTION_STRING_LITERAL
        consume(45);                // ACTION_STRING_LITERAL
        break;
      case 46:                      // ACTION_ESC
        consume(46);                // ACTION_ESC
        break;
      default:
        consume(4);                 // NON_SLASH_QUOTE_BRACE
      }
    }
    consume(59);                    // '}'
    eventHandler.endNonterminal("NESTED_ACTION", e0);
  }

  private void parse_LEXER_CHAR_SET()
  {
    eventHandler.startNonterminal("LEXER_CHAR_SET", e0);
    consume(56);                    // '['
    for (;;)
    {
      lookahead1(1);                // LEXER_CHAR | ']'
      if (l1 != 1)                  // LEXER_CHAR
      {
        break;
      }
      parse_LEXER_CHAR_RANGE();
    }
    consume(57);                    // ']'
    eventHandler.endNonterminal("LEXER_CHAR_SET", e0);
  }

  private void parse_LEXER_CHAR_RANGE()
  {
    eventHandler.startNonterminal("LEXER_CHAR_RANGE", e0);
    consume(1);                     // LEXER_CHAR
    lookahead1(3);                  // LEXER_CHAR | LEXER_RANGE_OPERATOR | ']'
    if (l1 == 2)                    // LEXER_RANGE_OPERATOR
    {
      consume(2);                   // LEXER_RANGE_OPERATOR
      lookahead1(0);                // LEXER_CHAR
      consume(1);                   // LEXER_CHAR
    }
    eventHandler.endNonterminal("LEXER_CHAR_RANGE", e0);
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
      if (code != 6                 // MULTI_LINE_COMMENT
       && code != 7                 // SINGLE_LINE_COMMENT
       && code != 49)               // WS
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
    boolean nonbmp = false;
    begin = end;
    int current = end;
    int result = INITIAL[tokenSetId];
    int state = 0;

    for (int code = result & 511; code != 0; )
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

        int lo = 0, hi = 7;
        for (int m = 4; ; m = (hi + lo) >> 1)
        {
          if (MAP2[m] > c0) {hi = m - 1;}
          else if (MAP2[8 + m] < c0) {lo = m + 1;}
          else {charclass = MAP2[16 + m]; break;}
          if (lo > hi) {charclass = 0; break;}
        }
      }

      state = code;
      int i0 = (charclass << 9) + code - 1;
      code = TRANSITION[(i0 & 15) + TRANSITION[i0 >> 4]];

      if (code > 511)
      {
        result = code;
        code &= 511;
        end = current;
      }
    }

    result >>= 9;
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

  private static String[] getTokenSet(int tokenSetId)
  {
    java.util.ArrayList<String> expected = new java.util.ArrayList<>();
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 511;
    for (int i = 0; i < 60; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 277 + s - 1;
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
    /*   0 */ 65, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 6,
    /*  35 */ 7, 8, 5, 5, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19, 20, 21, 22, 23,
    /*  63 */ 24, 25, 26, 27, 27, 27, 27, 27, 28, 28, 28, 28, 28, 29, 28, 30, 28, 31, 28, 32, 28, 33, 28, 28, 28, 28,
    /*  89 */ 28, 28, 34, 35, 36, 5, 37, 5, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 47, 54, 55,
    /* 116 */ 56, 57, 47, 58, 59, 60, 47, 61, 62, 63, 64, 5
  };

  private static final int[] MAP1 =
  {
    /*    0 */ 216, 448, 337, 279, 311, 379, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 391, 650, 650, 650, 650,
    /*   21 */ 650, 650, 650, 650, 650, 650, 650, 650, 650, 423, 247, 480, 536, 554, 554, 554, 554, 554, 554, 554, 554,
    /*   42 */ 554, 554, 586, 650, 650, 347, 649, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650,
    /*   63 */ 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650,
    /*   84 */ 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650,
    /*  105 */ 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650,
    /*  126 */ 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650,
    /*  147 */ 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 504, 617,
    /*  168 */ 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650,
    /*  189 */ 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650, 650,
    /*  210 */ 650, 650, 650, 650, 650, 650, 682, 691, 683, 683, 699, 707, 715, 721, 729, 746, 770, 753, 814, 784, 792,
    /*  231 */ 800, 761, 761, 761, 761, 761, 761, 762, 761, 899, 899, 900, 775, 980, 980, 981, 980, 899, 980, 736, 980,
    /*  252 */ 899, 980, 899, 980, 736, 980, 929, 980, 899, 980, 980, 980, 980, 980, 980, 980, 980, 980, 738, 980, 738,
    /*  273 */ 980, 738, 980, 737, 980, 738, 876, 876, 876, 876, 876, 876, 876, 876, 876, 876, 876, 876, 876, 876, 884,
    /*  294 */ 982, 969, 856, 898, 899, 896, 738, 980, 980, 980, 867, 940, 928, 928, 928, 971, 908, 899, 899, 899, 899,
    /*  315 */ 899, 899, 980, 980, 980, 980, 980, 980, 928, 928, 928, 928, 866, 822, 928, 928, 928, 928, 928, 928, 927,
    /*  336 */ 823, 928, 928, 928, 928, 928, 928, 864, 845, 997, 928, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980,
    /*  357 */ 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 761,
    /*  378 */ 761, 928, 928, 928, 928, 928, 980, 898, 899, 899, 899, 775, 980, 980, 980, 980, 980, 980, 980, 980, 980,
    /*  399 */ 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 899, 899, 899, 899, 776, 970, 980, 980, 980,
    /*  420 */ 980, 980, 980, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928,
    /*  441 */ 824, 969, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928, 928, 929, 929, 822, 928, 928, 928, 928,
    /*  462 */ 928, 832, 994, 958, 847, 843, 930, 919, 855, 886, 971, 918, 929, 834, 928, 928, 919, 928, 761, 807, 761,
    /*  483 */ 761, 761, 761, 761, 762, 760, 761, 761, 761, 761, 761, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980,
    /*  504 */ 980, 980, 980, 980, 980, 980, 980, 980, 928, 928, 928, 928, 928, 824, 980, 980, 928, 928, 928, 980, 980,
    /*  525 */ 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 888, 939, 915, 912, 948, 998, 738, 868, 970, 980,
    /*  546 */ 980, 980, 899, 899, 980, 980, 887, 980, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761,
    /*  567 */ 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 761, 899, 899,
    /*  588 */ 899, 899, 899, 775, 980, 980, 980, 980, 980, 980, 957, 931, 835, 868, 928, 928, 928, 928, 928, 928, 928,
    /*  609 */ 928, 928, 928, 928, 928, 864, 949, 966, 980, 980, 980, 980, 822, 928, 822, 928, 928, 928, 928, 928, 928,
    /*  630 */ 928, 980, 990, 928, 949, 864, 980, 928, 864, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 979, 980,
    /*  651 */ 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 980,
    /*  672 */ 980, 980, 980, 980, 980, 980, 980, 980, 980, 980, 65, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 3, 2, 0, 0, 4,
    /*  700 */ 5, 6, 7, 8, 5, 5, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18, 18, 18, 18, 18, 18, 18, 19, 20, 21, 22, 23,
    /*  728 */ 24, 25, 26, 27, 27, 27, 27, 27, 28, 28, 28, 28, 28, 28, 47, 47, 47, 47, 28, 28, 28, 28, 29, 28, 30, 28,
    /*  754 */ 28, 28, 34, 35, 36, 5, 37, 5, 5, 5, 5, 5, 5, 5, 5, 37, 31, 28, 32, 28, 33, 28, 28, 28, 28, 28, 28, 28,
    /*  782 */ 47, 28, 45, 46, 47, 48, 49, 50, 51, 52, 53, 47, 54, 55, 56, 57, 47, 58, 59, 60, 47, 61, 62, 63, 64, 5, 5,
    /*  809 */ 5, 5, 47, 47, 5, 5, 38, 39, 40, 41, 42, 43, 44, 47, 47, 28, 47, 28, 47, 28, 47, 47, 47, 28, 28, 47, 28,
    /*  836 */ 47, 28, 47, 47, 28, 47, 47, 28, 47, 47, 47, 28, 28, 47, 28, 28, 47, 28, 28, 47, 28, 28, 28, 47, 28, 47,
    /*  862 */ 28, 28, 28, 47, 28, 47, 47, 47, 47, 47, 47, 47, 28, 28, 37, 37, 37, 37, 37, 37, 37, 37, 28, 47, 28, 47,
    /*  888 */ 47, 47, 28, 47, 47, 47, 47, 28, 28, 28, 47, 28, 28, 28, 28, 28, 28, 28, 28, 5, 47, 28, 28, 47, 47, 28,
    /*  914 */ 28, 28, 28, 28, 47, 47, 28, 47, 47, 28, 47, 28, 28, 28, 28, 47, 28, 47, 28, 47, 28, 47, 28, 28, 28, 47,
    /*  940 */ 47, 47, 28, 28, 28, 47, 47, 47, 47, 47, 47, 47, 28, 47, 28, 47, 47, 28, 47, 28, 28, 28, 47, 47, 28, 28,
    /*  966 */ 47, 47, 28, 47, 47, 47, 47, 47, 47, 28, 47, 47, 28, 5, 47, 47, 47, 47, 47, 47, 47, 47, 5, 47, 47, 28, 47,
    /*  993 */ 28, 47, 28, 28, 47, 28, 47, 28, 28, 28, 28, 47, 47
  };

  private static final int[] MAP2 =
  {
    /*  0 */ 57344, 63744, 64976, 65008, 65279, 65280, 65313, 65536, 63743, 64975, 65007, 65278, 65279, 65312, 65533,
    /* 15 */ 1114111, 5, 47, 5, 47, 5, 47, 37, 5
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
    /* 29 */ 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56,
    /* 56 */ 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76
  };

  private static final int[] TRANSITION =
  {
    /*    0 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*   17 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2112, 2366,
    /*   34 */ 2364, 2366, 2349, 4738, 2589, 3617, 2590, 3516, 3061, 2128, 3392, 3391, 2151, 2163, 2179, 2297, 2589,
    /*   51 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2318, 2366, 2364, 2366,
    /*   68 */ 2334, 3527, 2589, 2895, 2590, 3266, 5236, 4649, 2589, 2589, 2382, 2394, 2620, 2410, 2589, 2589, 2589,
    /*   85 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2432, 2366, 2446, 2366, 2450, 2589,
    /*  102 */ 2589, 2589, 2589, 2589, 2589, 4649, 2589, 2589, 2382, 2466, 2495, 2552, 2589, 2589, 2589, 2589, 2589,
    /*  119 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2112, 2366, 2364, 2366, 2349, 4738, 2589, 5791,
    /*  136 */ 2590, 3516, 3061, 2128, 3392, 3391, 2151, 2163, 2179, 2297, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  153 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 2589, 2587, 2589, 2302, 4738, 2589, 3617, 2590, 3516,
    /*  170 */ 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  187 */ 2589, 2589, 2589, 2589, 2589, 2636, 2589, 2652, 2589, 2302, 2194, 2589, 5712, 2590, 3516, 3061, 5232,
    /*  204 */ 3392, 3391, 5170, 4789, 2670, 2705, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  221 */ 2589, 2589, 2589, 2571, 2589, 2587, 3967, 2727, 4738, 2589, 3617, 2590, 3516, 3061, 5232, 3392, 3391,
    /*  238 */ 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  255 */ 2589, 2571, 2589, 2587, 2589, 2302, 4738, 2589, 3617, 2590, 4189, 3061, 5232, 3392, 3391, 5170, 5182,
    /*  272 */ 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2785,
    /*  289 */ 2589, 2801, 2814, 2818, 3797, 2589, 3855, 2590, 3516, 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410,
    /*  306 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 2589, 2834,
    /*  323 */ 2847, 2851, 4738, 2589, 3617, 2590, 3516, 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589,
    /*  340 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2867, 3284, 2883, 3356, 3364,
    /*  357 */ 4738, 2589, 3617, 2590, 3516, 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589,
    /*  374 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 2589, 2587, 4158, 2911, 2957, 2589,
    /*  391 */ 3032, 2590, 4638, 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  408 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 3275, 2587, 5656, 2988, 4738, 2589, 3617, 2590,
    /*  425 */ 3516, 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  442 */ 2589, 2589, 2589, 2589, 2589, 2589, 2571, 3019, 3048, 3057, 2711, 4738, 2589, 3617, 2590, 3516, 3061,
    /*  459 */ 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  476 */ 2589, 2589, 2589, 2589, 3077, 2589, 2587, 4200, 2742, 4738, 2589, 3617, 2590, 3516, 3061, 5232, 3392,
    /*  493 */ 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  510 */ 2589, 2589, 3093, 2589, 3109, 3140, 3155, 2926, 2589, 3617, 2590, 3516, 3061, 5232, 3392, 3391, 5170,
    /*  527 */ 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  544 */ 3186, 3204, 3202, 3204, 3220, 3251, 2589, 3617, 2590, 3308, 3061, 5232, 3392, 3391, 5170, 5182, 2606,
    /*  561 */ 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 2654,
    /*  578 */ 3343, 2589, 2302, 2224, 5418, 4526, 5419, 3380, 4708, 5415, 2523, 2281, 4858, 5182, 3408, 3424, 2589,
    /*  595 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 3446, 3477, 3462, 3485,
    /*  612 */ 2416, 3501, 2589, 3617, 2590, 3516, 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589,
    /*  629 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 3543, 3559, 3605, 3633, 3645, 4738,
    /*  646 */ 2589, 3617, 2590, 3516, 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589,
    /*  663 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 2589, 2587, 3572, 3661, 4738, 2589, 3617,
    /*  680 */ 2590, 3516, 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  697 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 3703, 3909, 3719, 2589, 3687, 4571, 2589, 3617, 2590, 3516,
    /*  714 */ 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  731 */ 2589, 2589, 2589, 2589, 2589, 2571, 3747, 3743, 3763, 3430, 4738, 2589, 3783, 2590, 3516, 3061, 5232,
    /*  748 */ 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  765 */ 2589, 2589, 2589, 2571, 2589, 2587, 2555, 3813, 4738, 2589, 3617, 2590, 3516, 3061, 5232, 3392, 3391,
    /*  782 */ 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  799 */ 2589, 2571, 3898, 3871, 3887, 4222, 4738, 2589, 3617, 2590, 3516, 3061, 5232, 3392, 3391, 5170, 5182,
    /*  816 */ 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 3925,
    /*  833 */ 3327, 3941, 3983, 4003, 2209, 5418, 4526, 5419, 3380, 5281, 4046, 2523, 2281, 4858, 5182, 2606, 2410,
    /*  850 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 3925, 3327, 3941,
    /*  867 */ 3983, 4003, 2209, 5418, 4526, 5419, 3380, 4708, 5415, 2523, 2281, 4858, 5182, 2606, 2410, 2589, 2589,
    /*  884 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 3925, 3327, 3941, 3983, 4003,
    /*  901 */ 2209, 5418, 2536, 5419, 3676, 4923, 5415, 5371, 2281, 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589,
    /*  918 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 3925, 3327, 3941, 3983, 4003, 2209, 5418,
    /*  935 */ 2536, 5419, 3676, 4923, 5415, 5371, 2281, 4065, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  952 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 3925, 3327, 3941, 3983, 4003, 2209, 5418, 2536, 5419,
    /*  969 */ 3676, 4923, 5415, 4513, 2281, 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /*  986 */ 2589, 2589, 2589, 2589, 2589, 2589, 3925, 3327, 3941, 3983, 3987, 2209, 5418, 2536, 5419, 3676, 4923,
    /* 1003 */ 5415, 5371, 2281, 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1020 */ 2589, 2589, 2589, 2589, 3925, 3327, 3941, 3983, 4003, 2209, 5418, 2536, 5419, 3676, 4923, 5415, 5371,
    /* 1037 */ 2281, 4889, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1054 */ 2589, 2589, 3925, 3327, 3941, 3983, 4003, 2209, 5418, 2536, 5419, 3676, 4923, 5415, 5371, 5358, 4858,
    /* 1071 */ 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1088 */ 4081, 2589, 4111, 4095, 4124, 4738, 2589, 3617, 2590, 3516, 3061, 5232, 3392, 3391, 5170, 5182, 2606,
    /* 1105 */ 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 4140, 2589,
    /* 1122 */ 4156, 2589, 2302, 2479, 2589, 4030, 2590, 3516, 3061, 5232, 3392, 3391, 5170, 4685, 4174, 4216, 2589,
    /* 1139 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 4238, 2589, 2587, 2589,
    /* 1156 */ 3767, 4738, 2589, 3617, 2590, 3516, 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589,
    /* 1173 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 2589, 2587, 2589, 2302, 2209,
    /* 1190 */ 5418, 2536, 5419, 3676, 4923, 5415, 5371, 2281, 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589,
    /* 1207 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 4656, 4254, 4283, 4591, 2209, 4318, 4303,
    /* 1224 */ 3170, 3380, 4345, 5415, 5080, 5124, 4858, 4361, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1241 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 4656, 4254, 4283, 4591, 2209, 5418, 4526, 5419, 3380,
    /* 1258 */ 4708, 5415, 2523, 2281, 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1275 */ 2589, 2589, 2589, 2589, 2589, 2571, 4656, 4254, 4377, 5534, 2209, 5418, 4526, 3235, 3380, 4397, 5415,
    /* 1292 */ 2523, 2281, 4858, 5019, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1309 */ 2589, 2589, 2589, 2571, 4656, 4254, 4283, 4591, 2209, 5418, 4267, 5419, 3380, 4708, 5415, 2523, 2281,
    /* 1326 */ 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1343 */ 2589, 2571, 4656, 4254, 4283, 4591, 2209, 4413, 4526, 4920, 4439, 4455, 4471, 4974, 4500, 4858, 5182,
    /* 1360 */ 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571,
    /* 1377 */ 4656, 4542, 4587, 4827, 2209, 5418, 4526, 5419, 3380, 4708, 5415, 2523, 2281, 4858, 5182, 2606, 2410,
    /* 1394 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 4607, 4656, 4623,
    /* 1411 */ 4283, 4591, 2209, 5418, 2536, 5419, 3843, 4672, 4701, 5371, 2281, 4858, 5182, 2606, 2410, 2589, 2589,
    /* 1428 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 4656, 4254, 4283, 4591,
    /* 1445 */ 2209, 2510, 4724, 5419, 3676, 4923, 5267, 5371, 2281, 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589,
    /* 1462 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 4656, 4254, 4754, 4945, 2209, 2269,
    /* 1479 */ 2536, 5419, 3956, 4776, 5415, 5371, 2281, 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1496 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 4656, 4254, 4283, 4591, 2209, 5418, 2536, 5419,
    /* 1513 */ 3676, 4923, 5415, 5371, 2281, 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1530 */ 2589, 2589, 2589, 2589, 2589, 2589, 2571, 4656, 4254, 4283, 4591, 2209, 5418, 2536, 2941, 3676, 4923,
    /* 1547 */ 5415, 5371, 2281, 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1564 */ 2589, 2589, 2589, 2589, 2571, 4656, 4805, 4821, 4591, 2209, 5418, 2536, 5419, 3676, 4923, 4843, 5371,
    /* 1581 */ 4874, 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1598 */ 2589, 2589, 2571, 3292, 4905, 4939, 4760, 2209, 5201, 2536, 5419, 4018, 4923, 4961, 5371, 2281, 4990,
    /* 1615 */ 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1632 */ 2571, 4656, 4254, 4283, 4591, 2209, 5418, 2536, 3003, 3676, 5006, 5035, 5051, 5067, 4858, 5182, 2606,
    /* 1649 */ 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 3727,
    /* 1666 */ 5096, 5112, 5505, 2239, 5140, 2536, 5419, 3676, 5156, 5198, 5217, 2281, 4858, 5182, 2606, 2410, 2589,
    /* 1683 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 4656, 5252, 4283,
    /* 1700 */ 4287, 2254, 4049, 2536, 2972, 3676, 4923, 5415, 5371, 2281, 4858, 5182, 2606, 2410, 2589, 2589, 2589,
    /* 1717 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 4656, 5297, 4283, 4591, 5313,
    /* 1734 */ 2757, 2536, 5329, 3676, 4923, 5345, 5400, 2769, 4858, 5435, 2606, 2410, 2589, 2589, 2589, 2589, 2589,
    /* 1751 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 4656, 4254, 4283, 4591, 2209, 5418, 2536,
    /* 1768 */ 5419, 3828, 5451, 5415, 4484, 5595, 5467, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1785 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 4656, 5483, 5499, 4423, 2209, 5418, 5384, 3124, 3676,
    /* 1802 */ 4923, 5415, 5371, 5521, 5550, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1819 */ 2589, 2589, 2589, 2589, 2589, 2571, 4656, 4254, 4283, 4381, 2209, 5418, 2536, 5419, 3676, 5566, 5415,
    /* 1836 */ 5371, 2281, 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1853 */ 2589, 2589, 2589, 2571, 4656, 4254, 4283, 4591, 2209, 5418, 2536, 5419, 3676, 4923, 5582, 5371, 2281,
    /* 1870 */ 4858, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 1887 */ 2589, 2571, 4656, 4254, 4283, 4591, 2209, 5418, 2536, 5611, 3676, 4923, 5415, 5371, 2281, 4858, 5182,
    /* 1904 */ 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571,
    /* 1921 */ 4656, 4254, 4283, 4591, 2209, 5418, 2536, 5419, 3676, 4923, 5415, 5371, 4329, 4858, 5182, 2606, 2410,
    /* 1938 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 5627, 5654, 5643,
    /* 1955 */ 2685, 2689, 4738, 2589, 4557, 2590, 3516, 3061, 5232, 3392, 3391, 5672, 5684, 2606, 2410, 2589, 2589,
    /* 1972 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 3319, 5700, 3585, 3589,
    /* 1989 */ 4738, 2589, 3617, 2590, 3516, 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589,
    /* 2006 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 5730, 5728, 2589, 2302, 4738, 2589,
    /* 2023 */ 3617, 2589, 3516, 5811, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 2040 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2571, 2589, 5746, 5759, 5763, 4738, 2589, 3617, 2590,
    /* 2057 */ 3516, 3061, 5232, 3392, 3391, 5170, 5182, 2606, 2410, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 2074 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 5779, 5807, 2135, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 2091 */ 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589, 2589,
    /* 2108 */ 2589, 2589, 2589, 2589, 1024, 1024, 26112, 1024, 25680, 25680, 25680, 25680, 25680, 25680, 25680, 25680,
    /* 2124 */ 25680, 25680, 25680, 2048, 0, 0, 3226, 0, 0, 0, 183, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 26624, 26624, 0, 0, 0,
    /* 2150 */ 0, 234, 4244, 227, 3226, 0, 0, 0, 0, 227, 234, 4244, 3226, 0, 242, 4244, 3226, 0, 4244, 3226, 4346, 3324,
    /* 2172 */ 4346, 251, 3324, 253, 4350, 255, 3328, 257, 4354, 4350, 266, 255, 3334, 3328, 269, 257, 266, 4363, 273,
    /* 2191 */ 269, 3342, 275, 0, 0, 0, 0, 25173, 0, 23552, 88, 0, 0, 0, 0, 0, 0, 24064, 0, 0, 0, 0, 25173, 27734, 87,
    /* 2216 */ 88, 0, 27226, 0, 27226, 0, 0, 24064, 0, 0, 0, 0, 25173, 27734, 87, 88, 0, 27226, 0, 27226, 24669, 0,
    /* 2238 */ 24064, 0, 0, 0, 0, 25173, 27734, 87, 88, 0, 27226, 0, 27260, 0, 0, 24064, 0, 0, 0, 0, 25173, 27734, 87,
    /* 2261 */ 88, 0, 27226, 123, 27226, 0, 0, 24064, 0, 0, 0, 0, 27226, 27226, 0, 0, 27226, 0, 27226, 27275, 27226,
    /* 2282 */ 27226, 27226, 27226, 27226, 4244, 0, 0, 3226, 0, 0, 27226, 0, 27226, 27226, 27226, 273, 0, 275, 255, 257,
    /* 2302 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1024, 0, 34304, 0, 0, 0, 26112, 0, 25680, 25680, 25680, 25680, 25680,
    /* 2327 */ 25680, 25680, 25680, 25680, 25680, 25680, 2048, 25680, 25680, 25680, 25680, 25680, 25680, 25680, 25680,
    /* 2342 */ 25680, 25680, 25680, 25680, 1024, 0, 0, 25680, 25680, 25680, 25680, 25680, 25680, 25680, 25680, 25680,
    /* 2358 */ 25680, 25680, 25680, 1024, 0, 34304, 25680, 2560, 25680, 25680, 25680, 25680, 25680, 25680, 25680, 25680,
    /* 2374 */ 25680, 25680, 25680, 25680, 25680, 25680, 25680, 25680, 234, 0, 227, 0, 0, 0, 0, 0, 227, 234, 0, 0, 0,
    /* 2395 */ 242, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 255, 255, 257, 255, 0, 257, 255, 257, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 2425 */ 0, 12288, 12288, 1024, 0, 34304, 0, 0, 0, 26112, 0, 25680, 25680, 25680, 25680, 25680, 25680, 25680,
    /* 2443 */ 25680, 25680, 25680, 25680, 0, 25680, 25680, 25680, 25680, 25680, 25680, 25680, 25680, 25680, 25680,
    /* 2458 */ 25680, 25680, 25680, 25680, 0, 0, 0, 25680, 0, 242, 0, 0, 0, 0, 0, 251, 253, 251, 251, 253, 253, 0, 0, 0,
    /* 2482 */ 0, 120, 0, 121, 122, 0, 0, 0, 0, 0, 0, 24064, 0, 0, 266, 0, 266, 0, 269, 0, 269, 0, 266, 266, 266, 269,
    /* 2508 */ 269, 269, 0, 0, 0, 0, 27226, 27226, 0, 135, 27226, 0, 27226, 27226, 27276, 27226, 27226, 27226, 1024,
    /* 2527 */ 4244, 0, 0, 3226, 0, 0, 0, 27226, 0, 0, 27226, 0, 0, 117, 4244, 0, 25173, 87, 88, 0, 27226, 125, 3226, 0,
    /* 2551 */ 0, 266, 0, 269, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17408, 17408, 17408, 1024, 1024, 0, 1024, 0, 0, 0,
    /* 2578 */ 0, 0, 0, 0, 0, 0, 0, 0, 2048, 0, 2560, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 173, 257, 4244,
    /* 2608 */ 4350, 0, 255, 3226, 3328, 0, 257, 0, 4350, 255, 0, 3328, 257, 0, 255, 0, 255, 0, 257, 0, 257, 0, 255,
    /* 2631 */ 255, 0, 257, 257, 0, 1024, 1024, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2135, 0, 87, 0, 0, 0, 0, 0, 0,
    /* 2660 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 24669, 264, 4244, 4363, 0, 268, 3226, 3342, 0, 271, 0, 4354, 260, 0, 3334,
    /* 2684 */ 264, 0, 0, 0, 0, 30208, 30208, 30208, 30208, 30208, 30208, 30208, 30208, 30208, 30208, 30208, 30208,
    /* 2701 */ 1024, 0, 34304, 0, 260, 0, 264, 260, 264, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13312, 13312, 1024, 0, 34304,
    /* 2726 */ 0, 21504, 21504, 21504, 0, 21504, 0, 21504, 0, 21504, 21504, 21504, 21504, 1024, 0, 34304, 0, 0, 0, 113,
    /* 2746 */ 113, 113, 0, 113, 113, 0, 113, 113, 1024, 0, 34304, 0, 0, 0, 131, 27226, 27226, 0, 0, 27272, 0, 27226,
    /* 2768 */ 27272, 27226, 27226, 27226, 27226, 27226, 4244, 8192, 0, 3226, 0, 0, 27226, 0, 27226, 27226, 27226, 1024,
    /* 2786 */ 1024, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 25173, 0, 0, 0, 2136, 25173, 88, 0, 0, 0, 0, 0, 0, 25173, 0, 0, 0,
    /* 2813 */ 25173, 0, 25173, 0, 0, 25173, 25173, 25173, 25173, 25173, 25173, 25173, 25173, 25173, 25173, 25173,
    /* 2829 */ 25173, 1024, 0, 34304, 0, 0, 2560, 0, 0, 0, 0, 0, 0, 14336, 0, 0, 14336, 0, 0, 14336, 0, 0, 14336, 14336,
    /* 2853 */ 14336, 14336, 14336, 14336, 14336, 14336, 14336, 14336, 14336, 14336, 1024, 0, 34304, 0, 1024, 1024, 0,
    /* 2870 */ 1024, 0, 0, 0, 0, 14848, 0, 0, 0, 0, 0, 0, 2048, 0, 2560, 0, 0, 0, 14848, 0, 0, 0, 0, 0, 14848, 0, 0, 0,
    /* 2898 */ 0, 117, 0, 0, 25173, 87, 88, 0, 0, 125, 0, 0, 0, 17920, 17920, 17920, 17920, 17920, 17920, 17920, 17920,
    /* 2919 */ 17920, 17920, 0, 0, 1024, 0, 34304, 0, 0, 0, 20480, 25173, 0, 87, 88, 0, 0, 0, 0, 0, 0, 24064, 0, 0, 0,
    /* 2944 */ 27226, 27226, 0, 0, 27226, 0, 27226, 27226, 27226, 27226, 27226, 27308, 173, 117, 0, 0, 0, 25173, 0, 87,
    /* 2964 */ 88, 0, 0, 0, 0, 0, 125, 24064, 0, 0, 0, 27226, 27226, 0, 0, 27226, 0, 27226, 27226, 27226, 27306, 27226,
    /* 2986 */ 27226, 173, 18432, 18432, 18432, 18432, 18432, 18521, 18521, 18521, 18432, 18521, 0, 0, 1024, 0, 34304,
    /* 3003 */ 0, 0, 0, 27226, 27226, 0, 0, 27226, 0, 27226, 27304, 27226, 27226, 27226, 27226, 173, 0, 0, 13312, 13312,
    /* 3023 */ 13312, 0, 0, 0, 0, 0, 0, 0, 13312, 0, 0, 0, 0, 147, 4244, 0, 25173, 87, 88, 0, 0, 153, 3226, 0, 0, 0,
    /* 3049 */ 2560, 0, 0, 0, 13312, 13312, 13312, 0, 0, 0, 13312, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 173, 0, 0,
    /* 3076 */ 4244, 1024, 1024, 0, 1103, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 1024, 1024, 0, 1024, 0, 0, 0, 0, 0, 0,
    /* 3103 */ 84, 0, 0, 0, 0, 2048, 0, 2560, 0, 0, 0, 0, 0, 19968, 0, 0, 0, 0, 0, 0, 19968, 0, 0, 0, 27226, 27226, 0,
    /* 3130 */ 0, 27226, 166, 27303, 27226, 27226, 27226, 27307, 27226, 173, 0, 19968, 19968, 0, 19968, 19968, 19968,
    /* 3147 */ 19968, 19968, 19968, 19968, 19968, 19968, 19968, 19968, 19968, 20052, 19968, 20052, 19968, 19968, 19968,
    /* 3162 */ 19968, 20052, 19968, 19968, 19968, 1024, 0, 34304, 0, 0, 0, 27226, 27226, 0, 0, 27301, 0, 27226, 27226,
    /* 3181 */ 27305, 27226, 27226, 27226, 173, 1024, 1024, 0, 1024, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 2048,
    /* 3202 */ 81, 94, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81,
    /* 3228 */ 81, 81, 81, 81, 1024, 0, 34304, 0, 0, 0, 27226, 27226, 163, 0, 27226, 0, 27226, 27226, 27226, 27226,
    /* 3248 */ 27226, 27226, 173, 4214, 0, 0, 0, 25173, 0, 87, 88, 0, 0, 0, 0, 0, 3198, 24064, 0, 0, 117, 0, 0, 0, 0, 0,
    /* 3274 */ 125, 0, 0, 0, 0, 0, 0, 0, 0, 89, 0, 0, 0, 0, 0, 0, 0, 14848, 0, 0, 0, 0, 0, 0, 0, 0, 0, 27226, 0, 27228,
    /* 3304 */ 0, 27226, 27226, 27226, 0, 0, 3584, 4244, 4244, 0, 0, 0, 3072, 3226, 3226, 0, 0, 0, 0, 0, 0, 19456,
    /* 3326 */ 19456, 0, 0, 0, 0, 0, 0, 0, 0, 0, 27734, 0, 27734, 0, 27734, 27734, 27734, 0, 2560, 0, 0, 0, 0, 0, 0, 0,
    /* 3352 */ 0, 0, 0, 24669, 0, 0, 0, 0, 14848, 14848, 0, 0, 14848, 14848, 14848, 14848, 14848, 14848, 14848, 14848,
    /* 3372 */ 14848, 14848, 14848, 14848, 1024, 0, 34304, 0, 174, 175, 117, 4244, 4244, 0, 0, 27226, 125, 3226, 3226,
    /* 3391 */ 0, 0, 0, 0, 0, 4244, 0, 0, 3226, 0, 0, 0, 0, 0, 0, 0, 0, 257, 4244, 4350, 0, 255, 3226, 3328, 0, 257,
    /* 3417 */ 4368, 4350, 255, 3346, 3328, 257, 4368, 4372, 3346, 3349, 4372, 3349, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 3440 */ 16384, 16384, 1024, 28160, 34304, 0, 1024, 1024, 0, 1024, 0, 12288, 83, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 0,
    /* 3463 */ 2560, 0, 0, 0, 0, 0, 0, 0, 12288, 0, 0, 0, 12288, 0, 12288, 83, 0, 0, 0, 0, 0, 0, 0, 0, 12288, 0, 0, 0,
    /* 3491 */ 0, 0, 0, 0, 0, 12288, 0, 0, 0, 0, 0, 0, 12800, 0, 25173, 0, 87, 88, 0, 0, 0, 0, 0, 0, 24064, 0, 0, 117,
    /* 3519 */ 4244, 4244, 0, 0, 0, 125, 3226, 3226, 0, 0, 0, 0, 0, 0, 87, 88, 0, 0, 0, 0, 0, 0, 24064, 0, 1024, 1024,
    /* 3545 */ 0, 1024, 0, 0, 0, 13824, 0, 0, 0, 0, 0, 0, 0, 2048, 0, 0, 13824, 0, 0, 13824, 13824, 0, 0, 0, 0, 0,
    /* 3571 */ 13824, 0, 0, 0, 0, 15872, 0, 0, 15872, 0, 0, 0, 15872, 15872, 0, 0, 0, 0, 19456, 19456, 19456, 19456,
    /* 3593 */ 19456, 19456, 19456, 19456, 19456, 19456, 19456, 19456, 1024, 0, 34304, 0, 0, 2560, 0, 0, 0, 13824,
    /* 3611 */ 13824, 0, 0, 0, 0, 13824, 0, 0, 0, 0, 117, 4244, 0, 25173, 87, 88, 0, 0, 125, 3226, 0, 0, 0, 0, 13824, 0,
    /* 3637 */ 0, 13824, 13824, 13824, 0, 0, 13824, 0, 13824, 13824, 13824, 13824, 13824, 13824, 13824, 13824, 13824,
    /* 3654 */ 13824, 13824, 13824, 1024, 0, 34304, 0, 15872, 15872, 15872, 15872, 15872, 0, 15872, 15872, 15872, 15872,
    /* 3671 */ 15872, 15872, 1024, 0, 34304, 0, 0, 117, 4244, 4244, 0, 0, 27226, 125, 3226, 3226, 0, 0, 0, 0, 0, 16896,
    /* 3693 */ 16896, 16896, 0, 16896, 0, 78, 1024, 0, 34304, 0, 1024, 1024, 78, 1024, 0, 0, 0, 0, 0, 16896, 0, 0, 0, 0,
    /* 3717 */ 0, 2048, 0, 2560, 0, 0, 0, 0, 16896, 16896, 0, 0, 0, 0, 0, 0, 0, 0, 0, 27226, 91, 27226, 0, 27226, 27226,
    /* 3742 */ 27226, 0, 2560, 0, 0, 0, 0, 0, 16384, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16384, 0, 0, 0, 0, 16384, 0, 0, 0, 0, 0,
    /* 3771 */ 0, 0, 0, 0, 0, 0, 0, 0, 1024, 0, 0, 0, 15360, 0, 0, 0, 117, 4244, 0, 25173, 87, 88, 0, 0, 125, 3226, 0,
    /* 3798 */ 0, 0, 0, 25088, 0, 87, 23040, 0, 0, 0, 0, 0, 0, 24064, 0, 17408, 17408, 17408, 17408, 17408, 17408,
    /* 3819 */ 17408, 17408, 17408, 17408, 28672, 0, 1024, 0, 34304, 0, 0, 117, 4244, 4244, 0, 0, 27226, 125, 3226,
    /* 3838 */ 3226, 0, 0, 0, 182, 0, 0, 117, 4244, 4244, 0, 0, 27226, 125, 3226, 3226, 180, 0, 0, 0, 0, 117, 4244, 0,
    /* 3862 */ 25173, 87, 23128, 0, 0, 125, 3226, 0, 0, 0, 2560, 0, 0, 0, 0, 0, 0, 0, 20992, 0, 0, 0, 20992, 0, 20992,
    /* 3887 */ 0, 0, 20992, 20992, 0, 0, 0, 0, 0, 0, 0, 20992, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20992, 0, 0, 0, 0, 0, 16896,
    /* 3915 */ 0, 0, 16896, 0, 0, 0, 16896, 0, 0, 0, 1024, 1024, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 27734, 0, 0, 2048,
    /* 3941 */ 27734, 2560, 27734, 0, 27734, 0, 0, 0, 27734, 0, 27734, 0, 27734, 0, 27734, 0, 0, 117, 4244, 4244, 0,
    /* 3962 */ 178, 27226, 125, 3226, 3226, 0, 0, 0, 0, 0, 21504, 0, 21504, 0, 0, 0, 0, 21504, 21504, 21504, 0, 27734,
    /* 3984 */ 27734, 0, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 115,
    /* 4000 */ 0, 34304, 0, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 27734, 1024, 0,
    /* 4017 */ 34304, 0, 0, 117, 4244, 4244, 177, 0, 27226, 125, 3226, 3226, 0, 0, 0, 0, 0, 117, 4244, 0, 25173, 121,
    /* 4039 */ 122, 0, 0, 125, 3226, 0, 0, 0, 0, 3272, 0, 0, 0, 0, 27226, 27226, 0, 0, 27226, 0, 27226, 27226, 27226,
    /* 4062 */ 27226, 27278, 27226, 27226, 4331, 0, 3308, 0, 0, 27226, 27226, 27226, 0, 4244, 3226, 0, 27226, 4244,
    /* 4080 */ 3226, 1024, 1024, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 0, 29184, 0, 29184, 0, 0, 0, 0, 29184, 0, 29184,
    /* 4104 */ 29184, 29184, 0, 29184, 29184, 0, 29184, 29184, 2560, 0, 0, 0, 0, 0, 0, 29184, 0, 0, 0, 0, 0, 0, 29184,
    /* 4127 */ 29184, 29184, 29184, 0, 29184, 29184, 29184, 29184, 29184, 1024, 0, 34304, 0, 77, 77, 0, 77, 0, 0, 0, 0,
    /* 4148 */ 0, 0, 0, 0, 0, 0, 0, 2048, 0, 95, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17920, 17920, 265, 4244,
    /* 4176 */ 4355, 0, 261, 3226, 3335, 0, 265, 0, 4355, 261, 0, 3335, 265, 0, 0, 117, 4244, 4272, 0, 0, 0, 125, 3226,
    /* 4199 */ 3251, 0, 0, 0, 0, 0, 0, 113, 0, 113, 113, 113, 0, 113, 113, 0, 113, 261, 0, 265, 261, 265, 0, 0, 0, 0, 0,
    /* 4226 */ 0, 0, 0, 0, 0, 0, 20992, 20992, 1024, 0, 34304, 0, 0, 29696, 0, 29696, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 4253 */ 29696, 0, 2560, 0, 0, 27226, 0, 0, 0, 0, 0, 27226, 0, 27226, 0, 27226, 0, 145, 117, 4244, 0, 25173, 87,
    /* 4276 */ 88, 0, 27288, 125, 3226, 0, 157, 27226, 27226, 0, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226,
    /* 4294 */ 27226, 27226, 27226, 27226, 27226, 115, 0, 34304, 0, 0, 27274, 0, 145, 117, 4244, 150, 25173, 87, 88, 0,
    /* 4314 */ 27226, 125, 3226, 156, 0, 0, 130, 0, 27226, 27269, 0, 0, 27226, 0, 27274, 27226, 27226, 27226, 27226,
    /* 4333 */ 27226, 4244, 0, 0, 3226, 0, 0, 27226, 0, 11354, 27226, 27226, 27226, 27226, 186, 0, 27226, 0, 27226,
    /* 4352 */ 27327, 27226, 27226, 27226, 27226, 173, 196, 175, 4244, 245, 0, 4244, 3226, 0, 4244, 3226, 4244, 3226,
    /* 4370 */ 4244, 0, 3226, 0, 4350, 255, 3328, 27243, 27226, 0, 27245, 27226, 27226, 27226, 27226, 27226, 27226,
    /* 4387 */ 27226, 27226, 27226, 27226, 27226, 27226, 116, 0, 34304, 0, 27226, 27226, 0, 0, 27226, 0, 27326, 27226,
    /* 4405 */ 27226, 27226, 27226, 27226, 173, 196, 175, 4244, 0, 129, 0, 0, 27268, 27226, 0, 0, 27226, 137, 27226,
    /* 4424 */ 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27248, 27248, 1024, 0, 34304, 0, 174, 175,
    /* 4441 */ 117, 4244, 4244, 0, 0, 11866, 125, 3226, 3226, 0, 11776, 181, 0, 183, 27320, 27226, 0, 0, 27226, 0,
    /* 4461 */ 27226, 27226, 27226, 27226, 27226, 27331, 173, 196, 175, 4244, 0, 0, 3226, 0, 0, 202, 0, 27226, 27340, 0,
    /* 4481 */ 0, 27226, 0, 27226, 27226, 27226, 0, 4244, 0, 0, 3226, 0, 0, 0, 27226, 9728, 10240, 27226, 0, 27226,
    /* 4501 */ 27359, 27226, 27226, 27226, 4244, 0, 0, 3226, 0, 0, 27226, 0, 27226, 27226, 27226, 0, 4310, 0, 0, 3289,
    /* 4521 */ 0, 0, 0, 27226, 0, 0, 27226, 0, 145, 117, 4244, 0, 25173, 87, 88, 0, 27226, 125, 3226, 0, 0, 0, 2560, 96,
    /* 4545 */ 0, 27226, 0, 0, 0, 0, 0, 27241, 0, 27226, 0, 27226, 0, 0, 144, 146, 117, 4244, 0, 25173, 87, 88, 0, 0,
    /* 4569 */ 125, 3226, 0, 0, 0, 0, 25173, 0, 87, 88, 18944, 0, 0, 0, 0, 0, 24064, 0, 27244, 27226, 0, 27241, 27226,
    /* 4592 */ 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 1024, 0, 34304, 0, 1024,
    /* 4608 */ 1024, 0, 1024, 82, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 0, 2560, 0, 82, 27226, 0, 0, 0, 0, 0, 27226, 0,
    /* 4635 */ 27226, 0, 27226, 0, 0, 147, 4244, 4244, 0, 0, 0, 153, 3226, 3226, 0, 0, 0, 0, 0, 0, 183, 0, 0, 0, 0, 0,
    /* 4661 */ 0, 0, 0, 0, 27226, 0, 27226, 0, 27226, 27226, 27226, 27226, 27226, 0, 0, 27324, 0, 27226, 27226, 27226,
    /* 4681 */ 27226, 27226, 27226, 173, 0, 0, 4244, 3226, 0, 4244, 3226, 4244, 3226, 4244, 0, 3226, 0, 4355, 261, 3335,
    /* 4701 */ 0, 0, 3226, 0, 0, 0, 203, 27226, 27226, 0, 0, 27226, 0, 27226, 27226, 27226, 27226, 27226, 27226, 173,
    /* 4721 */ 196, 175, 4244, 0, 27276, 0, 0, 117, 4244, 0, 25173, 87, 88, 0, 27226, 125, 3226, 0, 0, 0, 0, 25173, 0,
    /* 4744 */ 87, 88, 0, 0, 0, 0, 0, 0, 24064, 0, 27226, 27226, 0, 27246, 27226, 27226, 27226, 27226, 27226, 27226,
    /* 4764 */ 27226, 27226, 27226, 27226, 27226, 27226, 27228, 27228, 1024, 0, 34304, 0, 27226, 27226, 0, 0, 27226, 0,
    /* 4782 */ 27226, 27226, 27226, 27226, 27330, 27226, 173, 0, 0, 4244, 3226, 0, 4244, 3226, 4244, 3226, 4350, 255,
    /* 4800 */ 3328, 257, 4354, 260, 3334, 0, 2560, 0, 98, 27237, 0, 0, 0, 0, 103, 27226, 0, 27226, 103, 27226, 103,
    /* 4821 */ 27226, 27226, 103, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226,
    /* 4836 */ 27226, 27244, 27244, 1024, 0, 34304, 0, 0, 0, 3226, 0, 0, 0, 0, 27226, 27226, 205, 0, 27226, 0, 27226,
    /* 4857 */ 27345, 27226, 4244, 0, 3226, 0, 0, 27226, 27226, 27226, 0, 4244, 3226, 0, 27226, 4244, 3226, 27358,
    /* 4875 */ 27226, 27226, 27226, 27226, 4244, 0, 0, 3226, 0, 0, 27226, 0, 27226, 27368, 27226, 4244, 0, 3226, 0, 0,
    /* 4895 */ 27226, 27226, 27226, 0, 4335, 3312, 0, 27226, 4244, 3226, 0, 2560, 97, 0, 27226, 0, 0, 0, 0, 0, 27228, 0,
    /* 4917 */ 27226, 0, 27226, 0, 0, 160, 27226, 27226, 0, 0, 27226, 0, 27226, 27226, 27226, 27226, 27226, 27226, 173,
    /* 4936 */ 0, 0, 4244, 27228, 27226, 0, 27228, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226,
    /* 4953 */ 27226, 27226, 27246, 27246, 1024, 0, 34304, 0, 198, 0, 3226, 201, 0, 0, 0, 27226, 27226, 0, 0, 27343, 0,
    /* 4974 */ 27226, 27226, 27226, 1024, 4244, 0, 0, 3226, 218, 0, 0, 27226, 0, 0, 27356, 0, 27226, 4244, 0, 3226, 0,
    /* 4995 */ 237, 27226, 27226, 27226, 0, 4244, 3226, 241, 27226, 4244, 3226, 27226, 27226, 0, 0, 27226, 0, 27226,
    /* 5013 */ 27226, 27328, 27226, 27226, 27226, 173, 0, 0, 4244, 3226, 0, 4344, 3321, 4244, 3226, 4244, 0, 3226, 0,
    /* 5032 */ 4350, 255, 3328, 0, 0, 3226, 0, 0, 0, 0, 27226, 27226, 0, 0, 27226, 0, 27226, 27226, 27346, 27226, 27226,
    /* 5053 */ 27349, 0, 4244, 0, 216, 3226, 0, 0, 0, 27226, 0, 0, 27226, 221, 27226, 27226, 27226, 27360, 27226, 4244,
    /* 5073 */ 0, 0, 3226, 229, 0, 27367, 0, 27226, 27226, 27226, 1024, 4244, 215, 0, 3226, 0, 0, 0, 27226, 0, 0, 27226,
    /* 5095 */ 0, 0, 2560, 0, 0, 27226, 0, 0, 0, 0, 91, 27226, 0, 27226, 91, 27226, 91, 27226, 27226, 91, 27247, 27226,
    /* 5117 */ 27226, 27226, 27226, 27226, 27247, 27226, 27247, 27226, 27226, 27226, 27226, 27226, 4244, 0, 0, 3226, 0,
    /* 5134 */ 230, 27226, 0, 27226, 27226, 27226, 128, 0, 0, 0, 27226, 27226, 134, 0, 27226, 0, 27226, 27226, 27226,
    /* 5153 */ 27226, 27226, 27279, 27226, 27226, 0, 187, 27226, 0, 27226, 27226, 27226, 27329, 27226, 27226, 173, 0, 0,
    /* 5171 */ 4244, 0, 3226, 0, 0, 0, 0, 0, 0, 4244, 3226, 0, 0, 4244, 3226, 0, 4244, 3226, 4244, 3226, 4244, 0, 3226,
    /* 5194 */ 0, 4350, 255, 3328, 0, 199, 3226, 0, 0, 0, 0, 27226, 27226, 0, 0, 27226, 0, 27226, 27226, 27226, 27277,
    /* 5215 */ 27226, 27226, 27226, 27348, 27226, 0, 4244, 0, 0, 3226, 0, 0, 0, 27226, 0, 0, 27226, 0, 0, 3226, 0, 0, 0,
    /* 5238 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 173, 0, 0, 0, 0, 2560, 0, 99, 27238, 0, 0, 0, 0, 0, 27226, 0, 27226, 0,
    /* 5266 */ 27226, 0, 0, 3226, 0, 0, 0, 0, 27226, 27226, 0, 0, 27226, 0, 10842, 27226, 27226, 0, 0, 27226, 0, 27226,
    /* 5288 */ 27226, 27226, 27226, 27226, 27226, 173, 196, 175, 4293, 0, 2560, 0, 0, 27226, 0, 0, 0, 0, 0, 27226, 0,
    /* 5309 */ 27226, 106, 27226, 106, 0, 119, 0, 0, 25173, 27734, 87, 88, 0, 27226, 0, 27226, 0, 0, 24064, 127, 0, 159,
    /* 5331 */ 0, 27226, 27298, 0, 164, 27226, 0, 27226, 27226, 27226, 27226, 27226, 27226, 173, 0, 0, 3226, 0, 7168, 0,
    /* 5351 */ 0, 7258, 27226, 0, 0, 27226, 208, 27226, 27226, 27226, 27226, 27226, 4322, 0, 0, 3300, 0, 0, 27226, 0,
    /* 5371 */ 27226, 27226, 27226, 0, 4244, 0, 0, 3226, 0, 0, 0, 27226, 0, 0, 27226, 0, 0, 117, 4244, 0, 25173, 87, 88,
    /* 5394 */ 151, 27226, 125, 3226, 0, 0, 27347, 27226, 27226, 0, 4244, 0, 0, 3226, 0, 7680, 219, 7770, 0, 0, 27226,
    /* 5415 */ 0, 0, 3226, 0, 0, 0, 0, 27226, 27226, 0, 0, 27226, 0, 27226, 27226, 27226, 27226, 27226, 27226, 173, 0,
    /* 5436 */ 0, 4342, 3319, 8704, 4244, 3226, 4244, 3226, 4244, 0, 3226, 0, 4350, 255, 3328, 27226, 27321, 0, 0,
    /* 5455 */ 27226, 0, 27226, 27226, 27226, 27226, 27226, 27226, 173, 0, 0, 4244, 27226, 4244, 0, 3226, 0, 0, 27226,
    /* 5474 */ 27374, 27226, 0, 4244, 3226, 0, 27226, 4339, 3316, 0, 2560, 0, 100, 27226, 0, 0, 0, 0, 104, 27226, 0,
    /* 5495 */ 27226, 104, 27226, 104, 27226, 27226, 0, 27248, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226,
    /* 5511 */ 27226, 27226, 27226, 27226, 27247, 27247, 1024, 0, 34304, 0, 27226, 27226, 6234, 27226, 27226, 4244, 0,
    /* 5528 */ 0, 3226, 0, 0, 27226, 0, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27226, 27250,
    /* 5545 */ 27250, 1024, 0, 34304, 0, 27226, 4244, 0, 3226, 6656, 0, 6746, 27226, 27226, 0, 4244, 3226, 0, 27226,
    /* 5564 */ 4244, 3226, 27226, 27226, 0, 0, 27226, 189, 27226, 27226, 27226, 27226, 27226, 27226, 173, 0, 0, 4244, 0,
    /* 5583 */ 0, 3226, 0, 0, 0, 0, 27226, 27226, 0, 206, 27226, 0, 27226, 27226, 27226, 27226, 27361, 4244, 0, 227,
    /* 5603 */ 3226, 0, 0, 27226, 9216, 27226, 27226, 27369, 158, 0, 0, 27297, 27226, 0, 0, 27226, 0, 27226, 27226,
    /* 5622 */ 27226, 27226, 27226, 27226, 173, 1024, 1024, 0, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30208, 2048, 0,
    /* 5644 */ 30208, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30208, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 18432, 18432, 5120,
    /* 5673 */ 4244, 4608, 3226, 0, 0, 0, 0, 4608, 5120, 4244, 3226, 0, 5632, 4244, 3226, 0, 4244, 3226, 4244, 3226,
    /* 5693 */ 4244, 0, 3226, 0, 4350, 255, 3328, 0, 2560, 0, 0, 0, 19456, 0, 0, 0, 0, 0, 19456, 0, 0, 0, 0, 117, 4244,
    /* 5718 */ 0, 25173, 23639, 88, 0, 0, 125, 3226, 0, 0, 0, 30720, 0, 0, 0, 0, 22528, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 5744 */ 22528, 0, 0, 2560, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 22016, 0, 0, 22016, 22016, 22016, 22016, 22016,
    /* 5768 */ 22016, 22016, 22016, 22016, 22016, 22016, 22016, 1024, 0, 34304, 0, 0, 0, 26624, 0, 0, 0, 0, 0, 0, 0,
    /* 5789 */ 26624, 0, 0, 0, 0, 0, 117, 4245, 0, 25173, 87, 88, 0, 0, 125, 3227, 0, 0, 26624, 0, 0, 26624, 0, 0, 0, 0,
    /* 5815 */ 0, 0, 0, 0, 0, 0, 0, 0, 1024, 0, 1024, 4244
  };

  private static final int[] EXPECTED =
  {
    /*   0 */ 235, 139, 150, 151, 143, 147, 157, 155, 161, 165, 169, 173, 177, 181, 185, 192, 189, 196, 199, 203, 214,
    /*  21 */ 328, 206, 217, 209, 396, 229, 267, 233, 241, 206, 246, 210, 397, 251, 268, 239, 258, 245, 209, 396, 250,
    /*  42 */ 267, 255, 274, 210, 397, 251, 268, 273, 262, 398, 266, 272, 278, 283, 293, 279, 286, 300, 300, 308, 304,
    /*  63 */ 304, 310, 288, 310, 289, 302, 296, 314, 316, 320, 324, 336, 339, 343, 347, 351, 355, 359, 363, 367, 369,
    /*  84 */ 373, 377, 381, 385, 389, 327, 393, 220, 402, 328, 411, 331, 225, 332, 328, 406, 413, 328, 412, 410, 225,
    /* 105 */ 328, 328, 413, 328, 411, 331, 225, 413, 328, 328, 412, 410, 225, 328, 328, 222, 224, 412, 328, 410, 412,
    /* 126 */ 329, 412, 330, 328, 328, 328, 328, 328, 328, 328, 328, 328, 328, 32960, 8388800, 16777408, 67109056,
    /* 143 */ 8388800, 16777408, 100663488, -2113929024, 33554624, 67109056, 67109056, 268435648, 192, 192, 192, 8,
    /* 155 */ 100663488, -2147483456, 192, 192, 8389056, 4194496, 192, 48, 4198592, 123072, 24768, 369098944, 100663488,
    /* 168 */ -2113929024, 134217920, 9175488, 4198592, 503316672, 192, 9306560, 134217920, 9306560, 7344320, 134217920,
    /* 179 */ -2038169152, 4202432, 1476395200, 469762240, 738197696, 1275068608, 939524288, 939524544, 1006633152,
    /* 188 */ 1484784064, 1543504064, 1543504064, 1543504064, 2080374976, 1006633152, 469762240, 1006633152, 2080374976,
    /* 197 */ 1006633152, 1543504064, 2080374976, 1543504064, -17817664, -17817664, 2, 0, 4, 0, 0, 256, 4194304, 8192,
    /* 211 */ 16384, 65536, 8192, 192, 32768, 16777216, 0, 32, 0, 4096, 16, 1048576, 0, 0, 1048576, 1048576, 1048576,
    /* 228 */ 1048576, 4096, 131072, 1048576, 2101248, 536870912, 1049600, 2, 2, 0, 6, 2, 2, 64, 128, 32768, 0, 32, 32,
    /* 247 */ 32, 4096, 4194304, 4096, 131072, 1048576, 2097152, 1024, 2, 2, 2, 128, 32768, 256, 4194304, 4096, 16384,
    /* 264 */ 65536, 16384, 2097152, 1024, 2048, 256, 512, 2, 512, 128, 32768, 256, 32, 4096, 32, 4096, 65536, 4096,
    /* 282 */ 1024, 131072, 2097152, 1024, 256, 512, 128, 32, 32, 32, 128, 512, 128, 256, 32, 0, 33554432, 4456448,
    /* 300 */ 65536, 1024, 128, 32, 32, 128, 128, 32, 65536, 128, 32, 128, 128, 128, 33554432, 131072, 131072, 131072,
    /* 318 */ 131073, 131200, 196608, 2228224, 16908288, 67239936, 50343936, 131328, 67239936, 131072, 0, 0, 0, 0,
    /* 332 */ 1048576, 0, 1048576, 0, 131072, 133120, 131073, 131104, 131089, 3276800, 131328, 3276800, 131073, 3276800,
    /* 346 */ 3278848, 3309568, 19070976, 201355264, 2752512, 131072, 3276800, 131104, 133121, 131137, 19070976, 131328,
    /* 358 */ 3801088, 131104, 70483968, 131328, 3343424, 16908544, 3801088, 20120640, 131392, 3801344, 70452320,
    /* 369 */ 70452832, 87229536, 87229536, 87229536, 70452576, 87230048, 87230050, 70452846, 87229550, 70452846,
    /* 379 */ 70452974, 87230062, 87229678, 87230062, 87229567, 70452863, 87229567, 87230190, 87230079, 96143200,
    /* 389 */ 91948896, 0, 4194304, 0, 128, 65536, 2097152, 8192, 16384, 262144, 524288, 4096, 131072, 1048576, 32768,
    /* 404 */ 0, 16384, 65536, 8192, 4096, 0, 1048576, 0, 1048576, 1048576, 0, 0, 0
  };

  private static final String[] TOKEN =
  {
    "(0)",
    "LEXER_CHAR",
    "'-'",
    "NON_RIGHT_BRACKET",
    "NON_SLASH_QUOTE_BRACE",
    "ACTION_COMMENT",
    "MULTI_LINE_COMMENT",
    "SINGLE_LINE_COMMENT",
    "OPTIONS",
    "TOKENS_SPEC",
    "CHANNELS",
    "'import'",
    "'fragment'",
    "'lexer'",
    "'parser'",
    "'grammar'",
    "TREE_GRAMMAR",
    "'returns'",
    "'locals'",
    "'throws'",
    "'catch'",
    "'finally'",
    "'mode'",
    "':'",
    "'::'",
    "','",
    "';'",
    "'('",
    "')'",
    "'->'",
    "'<'",
    "'>'",
    "'='",
    "'?'",
    "'*'",
    "'+'",
    "'+='",
    "'|'",
    "'.'",
    "'..'",
    "'@'",
    "'#'",
    "'~'",
    "'}'",
    "ACTION_CHAR_LITERAL",
    "ACTION_STRING_LITERAL",
    "ACTION_ESC",
    "INT",
    "STRING_LITERAL",
    "WS",
    "WSNLCHARS",
    "EOF",
    "RULE_REF",
    "TOKEN_REF",
    "'=>'",
    "'?'",
    "'['",
    "']'",
    "'{'",
    "'}'"
  };
}

// End
