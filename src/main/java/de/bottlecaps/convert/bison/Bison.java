// This file was generated on Sat Feb 11, 2023 18:00 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net>
// REx command line: -tree -a none -java -interface de.bottlecaps.convert.Parser -basex -saxon -name de.bottlecaps.convert.bison.Bison bison.ebnf

package de.bottlecaps.convert.bison;

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

public class Bison implements de.bottlecaps.convert.Parser
{
  public static class SaxonInitializer implements Initializer
  {
    @Override
    public void initialize(Configuration conf)
    {
      conf.registerExtensionFunction(new SaxonDefinition_input());
    }
  }

  public static Sequence parseInput(XPathContext context, String input) throws XPathException
  {
    Builder builder = context.getController().makeBuilder();
    builder.open();
    Bison parser = new Bison(input, new SaxonTreeBuilder(builder));
    try
    {
      parser.parse_input();
    }
    catch (ParseException pe)
    {
      buildError(parser, pe, builder);
    }
    return builder.getCurrentRoot();
  }

  public static class SaxonDefinition_input extends SaxonDefinition
  {
    @Override
    public String functionName() {return "parse-input";}
    @Override
    public Sequence execute(XPathContext context, String input) throws XPathException
    {
      return parseInput(context, input);
    }
  }

  public static abstract class SaxonDefinition extends ExtensionFunctionDefinition
  {
    abstract String functionName();
    abstract Sequence execute(XPathContext context, String input) throws XPathException;

    @Override
    public StructuredQName getFunctionQName() {return new StructuredQName("p", "de/bottlecaps/convert/bison/Bison", functionName());}
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

  private static void buildError(Bison parser, ParseException pe, Builder builder) throws XPathException
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

  public static ANode parseInput(Str str) throws IOException
  {
    BaseXFunction baseXFunction = new BaseXFunction()
    {
      @Override
      public void execute(Bison p) {p.parse_input();}
    };
    return baseXFunction.call(str);
  }

  public static abstract class BaseXFunction
  {
    protected abstract void execute(Bison p);

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
      Bison parser = new Bison();
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

  public Bison()
  {
  }

  public Bison(CharSequence string, EventHandler t)
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
    parse_input();
  }

  public void parse_input()
  {
    eventHandler.startNonterminal("input", e0);
    for (;;)
    {
      lookahead1W(26);              // WHITESPACE | PROLOGUE | PERCENT_FLAG | '%%' | '%code' | '%default-prec' |
                                    // '%define' | '%defines' | '%destructor' | '%error-verbose' | '%expect' |
                                    // '%expect-rr' | '%file-prefix' | '%glr-parser' | '%initial-action' | '%language' |
                                    // '%left' | '%lex-param' | '%name-prefix' | '%no-default-prec' | '%no-lines' |
                                    // '%nonassoc' | '%nondeterministic-parser' | '%nterm' | '%output' | '%param' |
                                    // '%parse-param' | '%precedence' | '%printer' | '%require' | '%right' |
                                    // '%skeleton' | '%start' | '%token' | '%token-table' | '%type' | '%union' |
                                    // '%verbose' | '%yacc' | ';'
      if (l1 == 15)                 // '%%'
      {
        break;
      }
      whitespace();
      parse_prologue_declaration();
    }
    consume(15);                    // '%%'
    for (;;)
    {
      lookahead1W(19);              // WHITESPACE | ID | CHAR_LITERAL | '%code' | '%default-prec' | '%destructor' |
                                    // '%left' | '%no-default-prec' | '%nonassoc' | '%nterm' | '%precedence' |
                                    // '%printer' | '%right' | '%start' | '%token' | '%type' | '%union'
      whitespace();
      parse_rules_or_grammar_declaration();
      lookahead1W(20);              // WHITESPACE | ID | CHAR_LITERAL | EOF | '%%' | '%code' | '%default-prec' |
                                    // '%destructor' | '%left' | '%no-default-prec' | '%nonassoc' | '%nterm' |
                                    // '%precedence' | '%printer' | '%right' | '%start' | '%token' | '%type' | '%union'
      if (l1 == 14                  // EOF
       || l1 == 15)                 // '%%'
      {
        break;
      }
    }
    whitespace();
    parse_epilogue_opt();
    lookahead1W(4);                 // WHITESPACE | EOF
    consume(14);                    // EOF
    eventHandler.endNonterminal("input", e0);
  }

  private void parse_prologue_declaration()
  {
    eventHandler.startNonterminal("prologue_declaration", e0);
    switch (l1)
    {
    case 10:                        // PROLOGUE
      consume(10);                  // PROLOGUE
      break;
    case 13:                        // PERCENT_FLAG
      consume(13);                  // PERCENT_FLAG
      break;
    case 18:                        // '%define'
      consume(18);                  // '%define'
      lookahead1W(2);               // WHITESPACE | ID
      whitespace();
      parse_variable();
      lookahead1W(29);              // WHITESPACE | STRING | ID | PROLOGUE | PERCENT_FLAG | '%%' | '%code' |
                                    // '%default-prec' | '%define' | '%defines' | '%destructor' | '%error-verbose' |
                                    // '%expect' | '%expect-rr' | '%file-prefix' | '%glr-parser' | '%initial-action' |
                                    // '%language' | '%left' | '%lex-param' | '%name-prefix' | '%no-default-prec' |
                                    // '%no-lines' | '%nonassoc' | '%nondeterministic-parser' | '%nterm' | '%output' |
                                    // '%param' | '%parse-param' | '%precedence' | '%printer' | '%require' | '%right' |
                                    // '%skeleton' | '%start' | '%token' | '%token-table' | '%type' | '%union' |
                                    // '%verbose' | '%yacc' | ';' | '{'
      whitespace();
      parse_value();
      break;
    case 19:                        // '%defines'
      consume(19);                  // '%defines'
      lookahead1W(27);              // WHITESPACE | STRING | PROLOGUE | PERCENT_FLAG | '%%' | '%code' |
                                    // '%default-prec' | '%define' | '%defines' | '%destructor' | '%error-verbose' |
                                    // '%expect' | '%expect-rr' | '%file-prefix' | '%glr-parser' | '%initial-action' |
                                    // '%language' | '%left' | '%lex-param' | '%name-prefix' | '%no-default-prec' |
                                    // '%no-lines' | '%nonassoc' | '%nondeterministic-parser' | '%nterm' | '%output' |
                                    // '%param' | '%parse-param' | '%precedence' | '%printer' | '%require' | '%right' |
                                    // '%skeleton' | '%start' | '%token' | '%token-table' | '%type' | '%union' |
                                    // '%verbose' | '%yacc' | ';'
      if (l1 == 2)                  // STRING
      {
        consume(2);                 // STRING
      }
      break;
    case 23:                        // '%error-verbose'
      consume(23);                  // '%error-verbose'
      break;
    case 24:                        // '%expect'
    case 25:                        // '%expect-rr'
      switch (l1)
      {
      case 24:                      // '%expect'
        consume(24);                // '%expect'
        break;
      default:
        consume(25);                // '%expect-rr'
      }
      lookahead1W(1);               // WHITESPACE | INT_LITERAL
      consume(4);                   // INT_LITERAL
      break;
    case 26:                        // '%file-prefix'
    case 29:                        // '%language'
    case 33:                        // '%name-prefix'
    case 39:                        // '%output'
    case 45:                        // '%require'
    case 47:                        // '%skeleton'
      switch (l1)
      {
      case 29:                      // '%language'
        consume(29);                // '%language'
        break;
      case 45:                      // '%require'
        consume(45);                // '%require'
        break;
      case 47:                      // '%skeleton'
        consume(47);                // '%skeleton'
        break;
      default:
        switch (l1)
        {
        case 26:                    // '%file-prefix'
          consume(26);              // '%file-prefix'
          break;
        case 33:                    // '%name-prefix'
          consume(33);              // '%name-prefix'
          break;
        default:
          consume(39);              // '%output'
        }
        lookahead1W(9);             // WHITESPACE | STRING | '='
        if (l1 == 60)               // '='
        {
          consume(60);              // '='
        }
      }
      lookahead1W(0);               // WHITESPACE | STRING
      consume(2);                   // STRING
      break;
    case 27:                        // '%glr-parser'
      consume(27);                  // '%glr-parser'
      break;
    case 28:                        // '%initial-action'
      consume(28);                  // '%initial-action'
      lookahead1W(8);               // WHITESPACE | '{'
      whitespace();
      parse_BRACED_CODE();
      break;
    case 35:                        // '%no-lines'
      consume(35);                  // '%no-lines'
      break;
    case 37:                        // '%nondeterministic-parser'
      consume(37);                  // '%nondeterministic-parser'
      break;
    case 31:                        // '%lex-param'
    case 40:                        // '%param'
    case 41:                        // '%parse-param'
      switch (l1)
      {
      case 40:                      // '%param'
        consume(40);                // '%param'
        break;
      case 41:                      // '%parse-param'
        consume(41);                // '%parse-param'
        break;
      default:
        consume(31);                // '%lex-param'
      }
      for (;;)
      {
        lookahead1W(8);             // WHITESPACE | '{'
        whitespace();
        parse_BRACED_CODE();
        lookahead1W(28);            // WHITESPACE | PROLOGUE | PERCENT_FLAG | '%%' | '%code' | '%default-prec' |
                                    // '%define' | '%defines' | '%destructor' | '%error-verbose' | '%expect' |
                                    // '%expect-rr' | '%file-prefix' | '%glr-parser' | '%initial-action' | '%language' |
                                    // '%left' | '%lex-param' | '%name-prefix' | '%no-default-prec' | '%no-lines' |
                                    // '%nonassoc' | '%nondeterministic-parser' | '%nterm' | '%output' | '%param' |
                                    // '%parse-param' | '%precedence' | '%printer' | '%require' | '%right' |
                                    // '%skeleton' | '%start' | '%token' | '%token-table' | '%type' | '%union' |
                                    // '%verbose' | '%yacc' | ';' | '{'
        if (l1 != 62)               // '{'
        {
          break;
        }
      }
      break;
    case 50:                        // '%token-table'
      consume(50);                  // '%token-table'
      break;
    case 53:                        // '%verbose'
      consume(53);                  // '%verbose'
      break;
    case 54:                        // '%yacc'
      consume(54);                  // '%yacc'
      break;
    case 56:                        // ';'
      consume(56);                  // ';'
      break;
    default:
      parse_grammar_declaration();
    }
    eventHandler.endNonterminal("prologue_declaration", e0);
  }

  private void parse_grammar_declaration()
  {
    eventHandler.startNonterminal("grammar_declaration", e0);
    switch (l1)
    {
    case 48:                        // '%start'
      consume(48);                  // '%start'
      lookahead1W(15);              // WHITESPACE | STRING | ID | CHAR_LITERAL
      whitespace();
      parse_symbol();
      break;
    case 20:                        // '%destructor'
    case 44:                        // '%printer'
      parse_code_props_type();
      lookahead1W(8);               // WHITESPACE | '{'
      whitespace();
      parse_BRACED_CODE();
      for (;;)
      {
        lookahead1W(18);            // WHITESPACE | STRING | ID | CHAR_LITERAL | '<' | '<*>' | '<>'
        whitespace();
        parse_generic_symlist_item();
        lookahead1W(35);            // WHITESPACE | STRING | ID | CHAR_LITERAL | PROLOGUE | PERCENT_FLAG | '%%' |
                                    // '%code' | '%default-prec' | '%define' | '%defines' | '%destructor' |
                                    // '%error-verbose' | '%expect' | '%expect-rr' | '%file-prefix' | '%glr-parser' |
                                    // '%initial-action' | '%language' | '%left' | '%lex-param' | '%name-prefix' |
                                    // '%no-default-prec' | '%no-lines' | '%nonassoc' | '%nondeterministic-parser' |
                                    // '%nterm' | '%output' | '%param' | '%parse-param' | '%precedence' | '%printer' |
                                    // '%require' | '%right' | '%skeleton' | '%start' | '%token' | '%token-table' |
                                    // '%type' | '%union' | '%verbose' | '%yacc' | ';' | '<' | '<*>' | '<>'
        if (l1 != 2                 // STRING
         && l1 != 5                 // ID
         && l1 != 6                 // CHAR_LITERAL
         && l1 != 57                // '<'
         && l1 != 58                // '<*>'
         && l1 != 59)               // '<>'
        {
          break;
        }
      }
      break;
    case 17:                        // '%default-prec'
      consume(17);                  // '%default-prec'
      break;
    case 34:                        // '%no-default-prec'
      consume(34);                  // '%no-default-prec'
      break;
    case 16:                        // '%code'
    case 52:                        // '%union'
      switch (l1)
      {
      case 16:                      // '%code'
        consume(16);                // '%code'
        lookahead1W(11);            // WHITESPACE | ID | '{'
        if (l1 == 5)                // ID
        {
          consume(5);               // ID
        }
        break;
      default:
        consume(52);                // '%union'
        lookahead1W(11);            // WHITESPACE | ID | '{'
        whitespace();
        parse_union_name();
      }
      lookahead1W(8);               // WHITESPACE | '{'
      whitespace();
      parse_BRACED_CODE();
      break;
    default:
      parse_symbol_declaration();
    }
    eventHandler.endNonterminal("grammar_declaration", e0);
  }

  private void parse_code_props_type()
  {
    eventHandler.startNonterminal("code_props_type", e0);
    switch (l1)
    {
    case 20:                        // '%destructor'
      consume(20);                  // '%destructor'
      break;
    default:
      consume(44);                  // '%printer'
    }
    eventHandler.endNonterminal("code_props_type", e0);
  }

  private void parse_union_name()
  {
    eventHandler.startNonterminal("union_name", e0);
    if (l1 == 5)                    // ID
    {
      consume(5);                   // ID
    }
    eventHandler.endNonterminal("union_name", e0);
  }

  private void parse_symbol_declaration()
  {
    eventHandler.startNonterminal("symbol_declaration", e0);
    switch (l1)
    {
    case 38:                        // '%nterm'
      consume(38);                  // '%nterm'
      lookahead1W(16);              // WHITESPACE | ID | CHAR_LITERAL | '<'
      whitespace();
      parse_nterm_decls();
      break;
    case 49:                        // '%token'
      consume(49);                  // '%token'
      lookahead1W(16);              // WHITESPACE | ID | CHAR_LITERAL | '<'
      whitespace();
      parse_token_decls();
      break;
    case 51:                        // '%type'
      consume(51);                  // '%type'
      lookahead1W(17);              // WHITESPACE | STRING | ID | CHAR_LITERAL | '<'
      if (l1 == 57)                 // '<'
      {
        whitespace();
        parse_TAG();
      }
      lookahead1W(15);              // WHITESPACE | STRING | ID | CHAR_LITERAL
      whitespace();
      parse_symbol_decl_1();
      for (;;)
      {
        if (l1 != 57)               // '<'
        {
          break;
        }
        whitespace();
        parse_TAG();
        lookahead1W(15);            // WHITESPACE | STRING | ID | CHAR_LITERAL
        whitespace();
        parse_symbol_decl_1();
      }
      break;
    default:
      parse_precedence_declarator();
      lookahead1W(17);              // WHITESPACE | STRING | ID | CHAR_LITERAL | '<'
      if (l1 == 57)                 // '<'
      {
        whitespace();
        parse_TAG();
      }
      lookahead1W(15);              // WHITESPACE | STRING | ID | CHAR_LITERAL
      whitespace();
      parse_token_decl_for_prec_1();
      for (;;)
      {
        if (l1 != 57)               // '<'
        {
          break;
        }
        whitespace();
        parse_TAG();
        lookahead1W(15);            // WHITESPACE | STRING | ID | CHAR_LITERAL
        whitespace();
        parse_token_decl_for_prec_1();
      }
    }
    eventHandler.endNonterminal("symbol_declaration", e0);
  }

  private void parse_precedence_declarator()
  {
    eventHandler.startNonterminal("precedence_declarator", e0);
    switch (l1)
    {
    case 30:                        // '%left'
      consume(30);                  // '%left'
      break;
    case 46:                        // '%right'
      consume(46);                  // '%right'
      break;
    case 36:                        // '%nonassoc'
      consume(36);                  // '%nonassoc'
      break;
    default:
      consume(43);                  // '%precedence'
    }
    eventHandler.endNonterminal("precedence_declarator", e0);
  }

  private void parse_tag_opt()
  {
    eventHandler.startNonterminal("tag.opt", e0);
    if (l1 == 57)                   // '<'
    {
      whitespace();
      parse_TAG();
    }
    eventHandler.endNonterminal("tag.opt", e0);
  }

  private void parse_generic_symlist_item()
  {
    eventHandler.startNonterminal("generic_symlist_item", e0);
    switch (l1)
    {
    case 2:                         // STRING
    case 5:                         // ID
    case 6:                         // CHAR_LITERAL
      parse_symbol();
      break;
    default:
      parse_tag();
    }
    eventHandler.endNonterminal("generic_symlist_item", e0);
  }

  private void parse_tag()
  {
    eventHandler.startNonterminal("tag", e0);
    switch (l1)
    {
    case 57:                        // '<'
      parse_TAG();
      break;
    case 58:                        // '<*>'
      consume(58);                  // '<*>'
      break;
    default:
      consume(59);                  // '<>'
    }
    eventHandler.endNonterminal("tag", e0);
  }

  private void parse_nterm_decls()
  {
    eventHandler.startNonterminal("nterm_decls", e0);
    parse_token_decls();
    eventHandler.endNonterminal("nterm_decls", e0);
  }

  private void parse_token_decls()
  {
    eventHandler.startNonterminal("token_decls", e0);
    if (l1 == 57)                   // '<'
    {
      whitespace();
      parse_TAG();
    }
    lookahead1W(10);                // WHITESPACE | ID | CHAR_LITERAL
    whitespace();
    parse_token_decl_1();
    for (;;)
    {
      if (l1 != 57)                 // '<'
      {
        break;
      }
      whitespace();
      parse_TAG();
      lookahead1W(10);              // WHITESPACE | ID | CHAR_LITERAL
      whitespace();
      parse_token_decl_1();
    }
    eventHandler.endNonterminal("token_decls", e0);
  }

  private void parse_token_decl_1()
  {
    eventHandler.startNonterminal("token_decl.1", e0);
    for (;;)
    {
      whitespace();
      parse_token_decl();
      lookahead1W(30);              // WHITESPACE | ID | CHAR_LITERAL | PROLOGUE | PERCENT_FLAG | '%%' | '%code' |
                                    // '%default-prec' | '%define' | '%defines' | '%destructor' | '%error-verbose' |
                                    // '%expect' | '%expect-rr' | '%file-prefix' | '%glr-parser' | '%initial-action' |
                                    // '%language' | '%left' | '%lex-param' | '%name-prefix' | '%no-default-prec' |
                                    // '%no-lines' | '%nonassoc' | '%nondeterministic-parser' | '%nterm' | '%output' |
                                    // '%param' | '%parse-param' | '%precedence' | '%printer' | '%require' | '%right' |
                                    // '%skeleton' | '%start' | '%token' | '%token-table' | '%type' | '%union' |
                                    // '%verbose' | '%yacc' | ';' | '<'
      if (l1 != 5                   // ID
       && l1 != 6)                  // CHAR_LITERAL
      {
        break;
      }
    }
    eventHandler.endNonterminal("token_decl.1", e0);
  }

  private void parse_token_decl()
  {
    eventHandler.startNonterminal("token_decl", e0);
    parse_id();
    lookahead1W(34);                // WHITESPACE | STRING | TSTRING | INT_LITERAL | ID | CHAR_LITERAL | PROLOGUE |
                                    // PERCENT_FLAG | '%%' | '%code' | '%default-prec' | '%define' | '%defines' |
                                    // '%destructor' | '%error-verbose' | '%expect' | '%expect-rr' | '%file-prefix' |
                                    // '%glr-parser' | '%initial-action' | '%language' | '%left' | '%lex-param' |
                                    // '%name-prefix' | '%no-default-prec' | '%no-lines' | '%nonassoc' |
                                    // '%nondeterministic-parser' | '%nterm' | '%output' | '%param' | '%parse-param' |
                                    // '%precedence' | '%printer' | '%require' | '%right' | '%skeleton' | '%start' |
                                    // '%token' | '%token-table' | '%type' | '%union' | '%verbose' | '%yacc' | ';' | '<'
    whitespace();
    parse_int_opt();
    lookahead1W(32);                // WHITESPACE | STRING | TSTRING | ID | CHAR_LITERAL | PROLOGUE | PERCENT_FLAG |
                                    // '%%' | '%code' | '%default-prec' | '%define' | '%defines' | '%destructor' |
                                    // '%error-verbose' | '%expect' | '%expect-rr' | '%file-prefix' | '%glr-parser' |
                                    // '%initial-action' | '%language' | '%left' | '%lex-param' | '%name-prefix' |
                                    // '%no-default-prec' | '%no-lines' | '%nonassoc' | '%nondeterministic-parser' |
                                    // '%nterm' | '%output' | '%param' | '%parse-param' | '%precedence' | '%printer' |
                                    // '%require' | '%right' | '%skeleton' | '%start' | '%token' | '%token-table' |
                                    // '%type' | '%union' | '%verbose' | '%yacc' | ';' | '<'
    whitespace();
    parse_alias();
    eventHandler.endNonterminal("token_decl", e0);
  }

  private void parse_int_opt()
  {
    eventHandler.startNonterminal("int.opt", e0);
    if (l1 == 4)                    // INT_LITERAL
    {
      consume(4);                   // INT_LITERAL
    }
    eventHandler.endNonterminal("int.opt", e0);
  }

  private void parse_alias()
  {
    eventHandler.startNonterminal("alias", e0);
    if (l1 == 2                     // STRING
     || l1 == 3)                    // TSTRING
    {
      switch (l1)
      {
      case 2:                       // STRING
        whitespace();
        parse_string_as_id();
        break;
      default:
        consume(3);                 // TSTRING
      }
    }
    eventHandler.endNonterminal("alias", e0);
  }

  private void parse_token_decl_for_prec_1()
  {
    eventHandler.startNonterminal("token_decl_for_prec.1", e0);
    for (;;)
    {
      whitespace();
      parse_token_decl_for_prec();
      lookahead1W(31);              // WHITESPACE | STRING | ID | CHAR_LITERAL | PROLOGUE | PERCENT_FLAG | '%%' |
                                    // '%code' | '%default-prec' | '%define' | '%defines' | '%destructor' |
                                    // '%error-verbose' | '%expect' | '%expect-rr' | '%file-prefix' | '%glr-parser' |
                                    // '%initial-action' | '%language' | '%left' | '%lex-param' | '%name-prefix' |
                                    // '%no-default-prec' | '%no-lines' | '%nonassoc' | '%nondeterministic-parser' |
                                    // '%nterm' | '%output' | '%param' | '%parse-param' | '%precedence' | '%printer' |
                                    // '%require' | '%right' | '%skeleton' | '%start' | '%token' | '%token-table' |
                                    // '%type' | '%union' | '%verbose' | '%yacc' | ';' | '<'
      if (l1 != 2                   // STRING
       && l1 != 5                   // ID
       && l1 != 6)                  // CHAR_LITERAL
      {
        break;
      }
    }
    eventHandler.endNonterminal("token_decl_for_prec.1", e0);
  }

  private void parse_token_decl_for_prec()
  {
    eventHandler.startNonterminal("token_decl_for_prec", e0);
    switch (l1)
    {
    case 2:                         // STRING
      parse_string_as_id();
      break;
    default:
      parse_id();
      lookahead1W(33);              // WHITESPACE | STRING | INT_LITERAL | ID | CHAR_LITERAL | PROLOGUE | PERCENT_FLAG |
                                    // '%%' | '%code' | '%default-prec' | '%define' | '%defines' | '%destructor' |
                                    // '%error-verbose' | '%expect' | '%expect-rr' | '%file-prefix' | '%glr-parser' |
                                    // '%initial-action' | '%language' | '%left' | '%lex-param' | '%name-prefix' |
                                    // '%no-default-prec' | '%no-lines' | '%nonassoc' | '%nondeterministic-parser' |
                                    // '%nterm' | '%output' | '%param' | '%parse-param' | '%precedence' | '%printer' |
                                    // '%require' | '%right' | '%skeleton' | '%start' | '%token' | '%token-table' |
                                    // '%type' | '%union' | '%verbose' | '%yacc' | ';' | '<'
      whitespace();
      parse_int_opt();
    }
    eventHandler.endNonterminal("token_decl_for_prec", e0);
  }

  private void parse_symbol_decl_1()
  {
    eventHandler.startNonterminal("symbol_decl.1", e0);
    for (;;)
    {
      whitespace();
      parse_symbol();
      lookahead1W(31);              // WHITESPACE | STRING | ID | CHAR_LITERAL | PROLOGUE | PERCENT_FLAG | '%%' |
                                    // '%code' | '%default-prec' | '%define' | '%defines' | '%destructor' |
                                    // '%error-verbose' | '%expect' | '%expect-rr' | '%file-prefix' | '%glr-parser' |
                                    // '%initial-action' | '%language' | '%left' | '%lex-param' | '%name-prefix' |
                                    // '%no-default-prec' | '%no-lines' | '%nonassoc' | '%nondeterministic-parser' |
                                    // '%nterm' | '%output' | '%param' | '%parse-param' | '%precedence' | '%printer' |
                                    // '%require' | '%right' | '%skeleton' | '%start' | '%token' | '%token-table' |
                                    // '%type' | '%union' | '%verbose' | '%yacc' | ';' | '<'
      if (l1 != 2                   // STRING
       && l1 != 5                   // ID
       && l1 != 6)                  // CHAR_LITERAL
      {
        break;
      }
    }
    eventHandler.endNonterminal("symbol_decl.1", e0);
  }

  private void parse_rules_or_grammar_declaration()
  {
    eventHandler.startNonterminal("rules_or_grammar_declaration", e0);
    switch (l1)
    {
    case 5:                         // ID
    case 6:                         // CHAR_LITERAL
      parse_rules();
      break;
    default:
      parse_grammar_declaration();
      lookahead1W(6);               // WHITESPACE | ';'
      consume(56);                  // ';'
    }
    eventHandler.endNonterminal("rules_or_grammar_declaration", e0);
  }

  private void parse_rules()
  {
    eventHandler.startNonterminal("rules", e0);
    parse_id();
    lookahead1W(12);                // WHITESPACE | BRACKETED_ID | ':'
    whitespace();
    parse_named_ref_opt();
    lookahead1W(5);                 // WHITESPACE | ':'
    consume(55);                    // ':'
    lookahead1W(22);                // WHITESPACE | STRING | ID | CHAR_LITERAL | BRACED_PREDICATE | EOF | '%%' |
                                    // '%code' | '%default-prec' | '%destructor' | '%dprec' | '%empty' | '%expect' |
                                    // '%expect-rr' | '%left' | '%merge' | '%no-default-prec' | '%nonassoc' | '%nterm' |
                                    // '%prec' | '%precedence' | '%printer' | '%right' | '%start' | '%token' | '%type' |
                                    // '%union' | ';' | '<' | '{' | '|'
    whitespace();
    parse_rhs();
    for (;;)
    {
      lookahead1W(21);              // WHITESPACE | ID | CHAR_LITERAL | EOF | '%%' | '%code' | '%default-prec' |
                                    // '%destructor' | '%left' | '%no-default-prec' | '%nonassoc' | '%nterm' |
                                    // '%precedence' | '%printer' | '%right' | '%start' | '%token' | '%type' |
                                    // '%union' | ';' | '|'
      if (l1 != 56                  // ';'
       && l1 != 63)                 // '|'
      {
        break;
      }
      switch (l1)
      {
      case 63:                      // '|'
        consume(63);                // '|'
        lookahead1W(22);            // WHITESPACE | STRING | ID | CHAR_LITERAL | BRACED_PREDICATE | EOF | '%%' |
                                    // '%code' | '%default-prec' | '%destructor' | '%dprec' | '%empty' | '%expect' |
                                    // '%expect-rr' | '%left' | '%merge' | '%no-default-prec' | '%nonassoc' | '%nterm' |
                                    // '%prec' | '%precedence' | '%printer' | '%right' | '%start' | '%token' | '%type' |
                                    // '%union' | ';' | '<' | '{' | '|'
        whitespace();
        parse_rhs();
        break;
      default:
        consume(56);                // ';'
      }
    }
    eventHandler.endNonterminal("rules", e0);
  }

  private void parse_rhs()
  {
    eventHandler.startNonterminal("rhs", e0);
    for (;;)
    {
      lookahead1W(22);              // WHITESPACE | STRING | ID | CHAR_LITERAL | BRACED_PREDICATE | EOF | '%%' |
                                    // '%code' | '%default-prec' | '%destructor' | '%dprec' | '%empty' | '%expect' |
                                    // '%expect-rr' | '%left' | '%merge' | '%no-default-prec' | '%nonassoc' | '%nterm' |
                                    // '%prec' | '%precedence' | '%printer' | '%right' | '%start' | '%token' | '%type' |
                                    // '%union' | ';' | '<' | '{' | '|'
      switch (l1)
      {
      case 5:                       // ID
      case 6:                       // CHAR_LITERAL
        lookahead2W(25);            // WHITESPACE | STRING | ID | CHAR_LITERAL | BRACKETED_ID | BRACED_PREDICATE | EOF |
                                    // '%%' | '%code' | '%default-prec' | '%destructor' | '%dprec' | '%empty' |
                                    // '%expect' | '%expect-rr' | '%left' | '%merge' | '%no-default-prec' |
                                    // '%nonassoc' | '%nterm' | '%prec' | '%precedence' | '%printer' | '%right' |
                                    // '%start' | '%token' | '%type' | '%union' | ':' | ';' | '<' | '{' | '|'
        switch (lk)
        {
        case 1157:                  // ID BRACKETED_ID
        case 1158:                  // CHAR_LITERAL BRACKETED_ID
          lookahead3W(24);          // WHITESPACE | STRING | ID | CHAR_LITERAL | BRACED_PREDICATE | EOF | '%%' |
                                    // '%code' | '%default-prec' | '%destructor' | '%dprec' | '%empty' | '%expect' |
                                    // '%expect-rr' | '%left' | '%merge' | '%no-default-prec' | '%nonassoc' | '%nterm' |
                                    // '%prec' | '%precedence' | '%printer' | '%right' | '%start' | '%token' | '%type' |
                                    // '%union' | ':' | ';' | '<' | '{' | '|'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 14                  // EOF
       || lk == 15                  // '%%'
       || lk == 16                  // '%code'
       || lk == 17                  // '%default-prec'
       || lk == 20                  // '%destructor'
       || lk == 30                  // '%left'
       || lk == 34                  // '%no-default-prec'
       || lk == 36                  // '%nonassoc'
       || lk == 38                  // '%nterm'
       || lk == 43                  // '%precedence'
       || lk == 44                  // '%printer'
       || lk == 46                  // '%right'
       || lk == 48                  // '%start'
       || lk == 49                  // '%token'
       || lk == 51                  // '%type'
       || lk == 52                  // '%union'
       || lk == 56                  // ';'
       || lk == 63                  // '|'
       || lk == 7045                // ID ':'
       || lk == 7046                // CHAR_LITERAL ':'
       || lk == 902277              // ID BRACKETED_ID ':'
       || lk == 902278)             // CHAR_LITERAL BRACKETED_ID ':'
      {
        break;
      }
      switch (l1)
      {
      case 11:                      // BRACED_PREDICATE
        consume(11);                // BRACED_PREDICATE
        break;
      case 22:                      // '%empty'
        consume(22);                // '%empty'
        break;
      case 42:                      // '%prec'
        consume(42);                // '%prec'
        lookahead1W(15);            // WHITESPACE | STRING | ID | CHAR_LITERAL
        whitespace();
        parse_symbol();
        break;
      case 21:                      // '%dprec'
      case 24:                      // '%expect'
      case 25:                      // '%expect-rr'
        switch (l1)
        {
        case 21:                    // '%dprec'
          consume(21);              // '%dprec'
          break;
        case 24:                    // '%expect'
          consume(24);              // '%expect'
          break;
        default:
          consume(25);              // '%expect-rr'
        }
        lookahead1W(1);             // WHITESPACE | INT_LITERAL
        consume(4);                 // INT_LITERAL
        break;
      case 32:                      // '%merge'
        consume(32);                // '%merge'
        lookahead1W(7);             // WHITESPACE | '<'
        whitespace();
        parse_TAG();
        break;
      default:
        switch (l1)
        {
        case 57:                    // '<'
        case 62:                    // '{'
          whitespace();
          parse_tag_opt();
          lookahead1W(8);           // WHITESPACE | '{'
          whitespace();
          parse_BRACED_CODE();
          break;
        default:
          whitespace();
          parse_symbol();
        }
        lookahead1W(23);            // WHITESPACE | STRING | ID | CHAR_LITERAL | BRACKETED_ID | BRACED_PREDICATE | EOF |
                                    // '%%' | '%code' | '%default-prec' | '%destructor' | '%dprec' | '%empty' |
                                    // '%expect' | '%expect-rr' | '%left' | '%merge' | '%no-default-prec' |
                                    // '%nonassoc' | '%nterm' | '%prec' | '%precedence' | '%printer' | '%right' |
                                    // '%start' | '%token' | '%type' | '%union' | ';' | '<' | '{' | '|'
        whitespace();
        parse_named_ref_opt();
      }
    }
    eventHandler.endNonterminal("rhs", e0);
  }

  private void parse_named_ref_opt()
  {
    eventHandler.startNonterminal("named_ref.opt", e0);
    if (l1 == 9)                    // BRACKETED_ID
    {
      consume(9);                   // BRACKETED_ID
    }
    eventHandler.endNonterminal("named_ref.opt", e0);
  }

  private void parse_variable()
  {
    eventHandler.startNonterminal("variable", e0);
    consume(5);                     // ID
    eventHandler.endNonterminal("variable", e0);
  }

  private void parse_value()
  {
    eventHandler.startNonterminal("value", e0);
    if (l1 == 2                     // STRING
     || l1 == 5                     // ID
     || l1 == 62)                   // '{'
    {
      switch (l1)
      {
      case 5:                       // ID
        consume(5);                 // ID
        break;
      case 2:                       // STRING
        consume(2);                 // STRING
        break;
      default:
        whitespace();
        parse_BRACED_CODE();
      }
    }
    eventHandler.endNonterminal("value", e0);
  }

  private void parse_id()
  {
    eventHandler.startNonterminal("id", e0);
    switch (l1)
    {
    case 5:                         // ID
      consume(5);                   // ID
      break;
    default:
      consume(6);                   // CHAR_LITERAL
    }
    eventHandler.endNonterminal("id", e0);
  }

  private void parse_symbol()
  {
    eventHandler.startNonterminal("symbol", e0);
    switch (l1)
    {
    case 2:                         // STRING
      parse_string_as_id();
      break;
    default:
      parse_id();
    }
    eventHandler.endNonterminal("symbol", e0);
  }

  private void parse_string_as_id()
  {
    eventHandler.startNonterminal("string_as_id", e0);
    consume(2);                     // STRING
    eventHandler.endNonterminal("string_as_id", e0);
  }

  private void parse_epilogue_opt()
  {
    eventHandler.startNonterminal("epilogue.opt", e0);
    if (l1 == 15)                   // '%%'
    {
      consume(15);                  // '%%'
      lookahead1W(3);               // WHITESPACE | EPILOGUE
      consume(7);                   // EPILOGUE
    }
    eventHandler.endNonterminal("epilogue.opt", e0);
  }

  private void parse_TAG()
  {
    eventHandler.startNonterminal("TAG", e0);
    consume(57);                    // '<'
    for (;;)
    {
      lookahead1(13);               // ANGLELESS_CODE | '<' | '>'
      if (l1 == 61)                 // '>'
      {
        break;
      }
      switch (l1)
      {
      case 8:                       // ANGLELESS_CODE
        consume(8);                 // ANGLELESS_CODE
        break;
      default:
        parse_TAG();
      }
    }
    consume(61);                    // '>'
    eventHandler.endNonterminal("TAG", e0);
  }

  private void parse_BRACED_CODE()
  {
    eventHandler.startNonterminal("BRACED_CODE", e0);
    consume(62);                    // '{'
    for (;;)
    {
      lookahead1(14);               // BRACELESS_CODE | '{' | '}'
      if (l1 == 64)                 // '}'
      {
        break;
      }
      switch (l1)
      {
      case 12:                      // BRACELESS_CODE
        consume(12);                // BRACELESS_CODE
        break;
      default:
        parse_BRACED_CODE();
      }
    }
    consume(64);                    // '}'
    eventHandler.endNonterminal("BRACED_CODE", e0);
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
      if (code != 1)                // WHITESPACE
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
    lk = (l2 << 7) | l1;
  }

  private void lookahead3W(int tokenSetId)
  {
    if (l3 == 0)
    {
      l3 = matchW(tokenSetId);
      b3 = begin;
      e3 = end;
    }
    lk |= l3 << 14;
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
      int i0 = (charclass << 9) + code - 1;
      code = TRANSITION[(i0 & 7) + TRANSITION[i0 >> 3]];

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
      for (int i = result >> 7; i > 0; --i)
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
      end -= result >> 7;
    }

    if (end > size) end = size;
    return (result & 127) - 1;
  }

  private static String[] getTokenSet(int tokenSetId)
  {
    java.util.ArrayList<String> expected = new java.util.ArrayList<>();
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 511;
    for (int i = 0; i < 65; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 371 + s - 1;
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
    /*   0 */ 52, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5, 6,
    /*  35 */ 5, 5, 7, 5, 8, 9, 10, 11, 5, 5, 12, 13, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 17, 18, 19, 20,
    /*  63 */ 21, 5, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,
    /*  90 */ 13, 22, 23, 24, 5, 25, 5, 26, 27, 28, 29, 30, 31, 32, 33, 34, 13, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44,
    /* 117 */ 45, 46, 13, 47, 48, 13, 49, 50, 51, 5, 5
  };

  private static final int[] MAP1 =
  {
    /*   0 */ 54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  26 */ 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  52 */ 58, 58, 90, 122, 154, 186, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216,
    /*  74 */ 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 52, 0, 0, 0, 0, 0, 0, 0,
    /*  98 */ 0, 1, 2, 3, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 5, 6, 5, 5, 7, 5, 8, 9, 10, 11,
    /* 133 */ 5, 5, 12, 13, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 17, 18, 19, 20, 21, 5, 13, 13, 13, 13, 13,
    /* 160 */ 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 22, 23, 24, 5, 25, 5,
    /* 187 */ 26, 27, 28, 29, 30, 31, 32, 33, 34, 13, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 13, 47, 48, 13,
    /* 213 */ 49, 50, 51, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
    /* 247 */ 5
  };

  private static final int[] MAP2 =
  {
    /* 0 */ 57344, 65536, 65533, 1114111, 5, 5
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 6671, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
    /* 28 */ 29, 30, 31, 32, 33, 34, 35, 36
  };

  private static final int[] TRANSITION =
  {
    /*    0 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*   17 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*   34 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*   51 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3392, 3404, 3428, 3428,
    /*   68 */ 3411, 3439, 3458, 3469, 3477, 3492, 3491, 3492, 3483, 3501, 3962, 3513, 3492, 3492, 3523, 3492, 4046,
    /*   85 */ 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  102 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  119 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3392, 3404, 3428, 3428, 3411, 3439, 3458, 5288,
    /*  136 */ 3547, 3492, 3491, 3492, 3483, 3561, 3962, 3513, 3492, 3492, 3573, 3492, 5071, 3537, 3492, 3492, 4498,
    /*  153 */ 3492, 4303, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  170 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  187 */ 3492, 3492, 3492, 3492, 3492, 3428, 3396, 3428, 3428, 3431, 3587, 3492, 3515, 3601, 3492, 3491, 3492,
    /*  204 */ 3602, 3492, 3962, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  221 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  238 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  255 */ 3492, 3392, 3404, 3428, 3428, 3411, 3439, 3458, 4924, 3610, 3492, 3491, 3492, 3483, 3501, 3962, 3513,
    /*  272 */ 3492, 3492, 3523, 3492, 4046, 3537, 3492, 3492, 4498, 3492, 4330, 3492, 3492, 3492, 3972, 3492, 3492,
    /*  289 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  306 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4716, 5654, 3492,
    /*  323 */ 3492, 3461, 3624, 3458, 3644, 4748, 3492, 3492, 3492, 5747, 3501, 3492, 3513, 3492, 3492, 3523, 3492,
    /*  340 */ 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  357 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  374 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3416, 3724, 3632, 3638, 3652, 3670, 3446,
    /*  391 */ 3644, 4748, 3492, 3492, 3492, 3593, 3501, 3492, 3688, 3492, 3492, 3698, 3492, 5051, 3795, 3492, 3492,
    /*  408 */ 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  425 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  442 */ 3492, 3492, 3492, 3492, 3492, 3492, 4716, 5654, 4655, 3716, 3720, 3624, 3732, 3644, 4748, 3492, 3492,
    /*  459 */ 3492, 4754, 3501, 3492, 3513, 3492, 3492, 3740, 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492,
    /*  476 */ 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  493 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  510 */ 3492, 3492, 4716, 4734, 3764, 3758, 3768, 3776, 3784, 3644, 4748, 3492, 3492, 3492, 5747, 3501, 3492,
    /*  527 */ 3794, 3492, 3492, 3523, 3492, 4046, 3514, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492,
    /*  544 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  561 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4716, 5654,
    /*  578 */ 3492, 3492, 3461, 3624, 3458, 3804, 4748, 3492, 3492, 3492, 5747, 3501, 3492, 3513, 3492, 3492, 3523,
    /*  595 */ 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492,
    /*  612 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  629 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4716, 5654, 3492, 3492, 3461, 3624,
    /*  646 */ 3458, 3644, 4748, 3492, 3492, 3492, 5747, 3501, 3492, 3513, 3492, 3492, 3523, 3492, 4046, 3537, 3492,
    /*  663 */ 3492, 3817, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  680 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  697 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4716, 5654, 3492, 3492, 4002, 3831, 3839, 3851, 3877, 3492,
    /*  714 */ 3492, 3492, 3553, 3895, 3492, 3907, 3492, 3492, 3917, 3492, 5185, 3935, 3492, 3492, 4014, 3492, 5693,
    /*  731 */ 3492, 3492, 3492, 4242, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  748 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  765 */ 3492, 3492, 3492, 4716, 5654, 3492, 3492, 3461, 3945, 3458, 4029, 4093, 3492, 3492, 3492, 5747, 3953,
    /*  782 */ 3492, 3513, 3492, 3961, 3523, 3492, 4046, 3537, 3823, 3492, 3899, 3492, 4535, 4962, 5321, 3923, 3972,
    /*  799 */ 3970, 4553, 3980, 3990, 3492, 3999, 4375, 3492, 3492, 3492, 3492, 5373, 3492, 3492, 5571, 3492, 3492,
    /*  816 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010,
    /*  833 */ 4022, 4059, 4054, 4063, 4071, 3458, 4079, 4093, 3492, 3492, 3492, 5747, 3501, 3492, 3513, 3492, 3492,
    /*  850 */ 4087, 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492,
    /*  867 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  884 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4107, 4122, 4119, 4119, 4111,
    /*  901 */ 4130, 4138, 3644, 4149, 3492, 4163, 3492, 3579, 4172, 3492, 4184, 3492, 3492, 4194, 3492, 4046, 4212,
    /*  918 */ 3492, 3492, 4176, 3492, 4977, 3492, 3492, 3492, 3982, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  935 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /*  952 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4228, 5654, 3492, 3492, 4222, 3945, 3458, 4029, 4093,
    /*  969 */ 3492, 3492, 3492, 5747, 3501, 3492, 3513, 3492, 3492, 3523, 3492, 4046, 3537, 3492, 3492, 3899, 3492,
    /*  986 */ 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1003 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1020 */ 3492, 3492, 3492, 3492, 5489, 4811, 3492, 4240, 3461, 3624, 3458, 3644, 4748, 3492, 3492, 3492, 5747,
    /* 1037 */ 3501, 3492, 3513, 3492, 3492, 3523, 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492,
    /* 1054 */ 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1071 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1088 */ 5262, 5654, 5769, 4250, 4254, 3624, 3458, 3644, 4748, 3492, 3492, 3492, 5747, 3501, 3492, 3513, 3492,
    /* 1105 */ 3492, 3523, 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492,
    /* 1122 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1139 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4767, 5794, 4262, 4268,
    /* 1156 */ 4274, 4288, 3458, 3644, 4748, 3492, 3492, 3492, 5747, 3501, 3492, 3513, 3492, 3492, 3523, 3492, 4046,
    /* 1173 */ 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1190 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1207 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4716, 4296, 3492, 3492, 3461, 3624, 3458, 3644,
    /* 1224 */ 4748, 3492, 3492, 3492, 5747, 3501, 3492, 3513, 3492, 3492, 3523, 3492, 4046, 3537, 3492, 3492, 3899,
    /* 1241 */ 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1258 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1275 */ 3492, 3492, 3492, 3492, 3492, 4716, 3505, 3492, 3492, 3461, 4288, 4311, 3644, 5635, 3492, 3492, 3492,
    /* 1292 */ 5747, 3501, 3492, 3513, 3492, 3492, 3523, 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492,
    /* 1309 */ 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1326 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1343 */ 3492, 4716, 5654, 3492, 3492, 3461, 3624, 4323, 3644, 4748, 3492, 3492, 3492, 5747, 3501, 3492, 3513,
    /* 1360 */ 3492, 3492, 3523, 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492,
    /* 1377 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1394 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4716, 4720, 3493,
    /* 1411 */ 4338, 3461, 3624, 3458, 3644, 4748, 3492, 3492, 3492, 5747, 3501, 3492, 3513, 3492, 3492, 3523, 3492,
    /* 1428 */ 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1445 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1462 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4716, 5654, 3492, 3492, 4141, 4348, 4356,
    /* 1479 */ 3644, 4748, 3492, 3492, 3492, 5747, 3501, 3492, 3513, 3492, 3492, 4369, 3492, 4998, 4387, 3492, 3492,
    /* 1496 */ 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1513 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1530 */ 3492, 3492, 3492, 3492, 3492, 3492, 4716, 5654, 3492, 3492, 3461, 3624, 3458, 3644, 4397, 3492, 3492,
    /* 1547 */ 3492, 3529, 3501, 3492, 3513, 3492, 3492, 3523, 3492, 4046, 3537, 3492, 3492, 4379, 3492, 4535, 3492,
    /* 1564 */ 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1581 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1598 */ 3492, 3492, 4010, 4022, 4059, 4054, 4411, 4071, 3458, 4079, 4093, 3492, 3492, 3492, 5747, 3501, 3492,
    /* 1615 */ 3513, 3492, 3492, 4087, 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3923, 3972, 3492,
    /* 1632 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1649 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022,
    /* 1666 */ 4059, 4054, 4063, 4071, 3458, 4079, 4093, 3492, 3492, 3811, 4036, 3501, 3658, 3513, 3492, 3492, 4087,
    /* 1683 */ 4425, 4046, 3537, 4438, 4791, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 4155, 3492, 3869, 3492, 5089,
    /* 1700 */ 3492, 5445, 4446, 4861, 3492, 3492, 4460, 3492, 3492, 4987, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1717 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071,
    /* 1734 */ 3458, 4079, 4093, 3492, 3492, 3492, 5747, 3501, 3492, 4469, 3492, 3492, 4087, 3492, 4046, 3537, 3492,
    /* 1751 */ 3492, 4477, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1768 */ 5467, 4495, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1785 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071, 4506, 4079, 4093, 3492,
    /* 1802 */ 3492, 3492, 5747, 3501, 3492, 3513, 3786, 3492, 4514, 3937, 4046, 4528, 3492, 3492, 4543, 3492, 4535,
    /* 1819 */ 4561, 3492, 3492, 3972, 3492, 3492, 4200, 3492, 3492, 3680, 3492, 3492, 4572, 3492, 5517, 3492, 5758,
    /* 1836 */ 3492, 4582, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1853 */ 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071, 4594, 4079, 4093, 3492, 3492, 3492, 5747, 4602,
    /* 1870 */ 3492, 3513, 3492, 3492, 4087, 3746, 4046, 3537, 3492, 4615, 3899, 3492, 4535, 3492, 3492, 3492, 3708,
    /* 1887 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 1904 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010,
    /* 1921 */ 4022, 4059, 4054, 4063, 4071, 4626, 4079, 4093, 4634, 4644, 3858, 4663, 3501, 4678, 4688, 3492, 5110,
    /* 1938 */ 4703, 3492, 4884, 4711, 5048, 4728, 4742, 4762, 4535, 4483, 5015, 5629, 5349, 4775, 3492, 4574, 3492,
    /* 1955 */ 4784, 4819, 4829, 3492, 5456, 3492, 4837, 4847, 4806, 4452, 3492, 4859, 3492, 3492, 3492, 3492, 3492,
    /* 1972 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063,
    /* 1989 */ 4071, 4869, 4079, 4093, 3492, 3492, 3492, 5747, 4877, 3492, 4892, 4564, 3492, 4087, 3492, 4046, 3537,
    /* 2006 */ 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 5686, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2023 */ 5361, 4900, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2040 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071, 4910, 4079, 4093,
    /* 2057 */ 3492, 3492, 3492, 5747, 3501, 4043, 3513, 3492, 3492, 4087, 3492, 4046, 4918, 5222, 3492, 3899, 3492,
    /* 2074 */ 4535, 5806, 3492, 3492, 3972, 3492, 3492, 3492, 4636, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2091 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2108 */ 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071, 3458, 4079, 4093, 3492, 3492, 3492, 5747,
    /* 2125 */ 3501, 3492, 3513, 3492, 3492, 4087, 3492, 4932, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492,
    /* 2142 */ 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2159 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2176 */ 4010, 4022, 4059, 4054, 4063, 4071, 4940, 4079, 4093, 3450, 3991, 3448, 5747, 3501, 4953, 4948, 3704,
    /* 2193 */ 3492, 4087, 3492, 4046, 3537, 4961, 3492, 3899, 3492, 4535, 3492, 4970, 4851, 3972, 5156, 3492, 3492,
    /* 2210 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4417, 3492, 3492, 4985, 4995, 3492, 3492, 3492, 3492, 3492,
    /* 2227 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054,
    /* 2244 */ 4063, 4071, 3458, 4079, 4093, 3492, 3492, 4164, 5747, 3501, 4232, 3513, 3492, 3909, 4087, 3492, 4046,
    /* 2261 */ 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2278 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2295 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071, 5006, 4079,
    /* 2312 */ 4093, 3492, 3492, 5014, 5747, 3501, 3492, 3513, 5023, 3492, 4087, 3492, 4046, 3537, 3492, 5033, 3899,
    /* 2329 */ 3492, 4535, 3492, 3492, 3492, 5041, 3492, 3492, 3492, 5395, 3492, 3492, 3492, 3492, 5059, 3492, 3492,
    /* 2346 */ 5068, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2363 */ 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071, 5079, 4079, 4093, 3492, 5423, 3492,
    /* 2380 */ 5747, 3501, 3492, 3513, 4461, 3492, 4087, 3492, 4046, 3537, 3492, 3492, 3899, 4549, 4535, 3492, 3492,
    /* 2397 */ 5097, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 5663, 5107, 3492, 3492, 5118, 3492, 3492,
    /* 2414 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2431 */ 3492, 4010, 4022, 4059, 4054, 4063, 4071, 5129, 4079, 4093, 3796, 3492, 5137, 5747, 5147, 3492, 3513,
    /* 2448 */ 4315, 5155, 4087, 4776, 4046, 3537, 3492, 3492, 3899, 3492, 5164, 5735, 3492, 5099, 3972, 3492, 5172,
    /* 2465 */ 3492, 3492, 3492, 4680, 4821, 4801, 3492, 3492, 3492, 3492, 5182, 5614, 3492, 3492, 3492, 3492, 3492,
    /* 2482 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059,
    /* 2499 */ 4054, 4063, 4071, 5193, 4079, 4520, 5310, 3492, 3865, 5201, 3501, 3492, 3513, 3492, 3492, 4087, 3492,
    /* 2516 */ 4204, 3537, 5219, 3492, 3899, 3492, 4535, 3492, 3492, 5060, 3972, 3492, 3492, 5384, 3492, 5230, 3492,
    /* 2533 */ 3492, 5476, 3492, 3492, 3492, 5528, 3492, 5540, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2550 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071, 5238,
    /* 2567 */ 4079, 4093, 3492, 5246, 3492, 5747, 3501, 4618, 5257, 3492, 3492, 4087, 3492, 4046, 3537, 3492, 3883,
    /* 2584 */ 3899, 3492, 4535, 3492, 5270, 3492, 3972, 5732, 5282, 3492, 3492, 3887, 3492, 3492, 4839, 3492, 3492,
    /* 2601 */ 3492, 3492, 4902, 3492, 5211, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2618 */ 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071, 3458, 4079, 4093, 3492, 3492,
    /* 2635 */ 3492, 5747, 3501, 3492, 3513, 3492, 3662, 4087, 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492,
    /* 2652 */ 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2669 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2686 */ 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071, 5296, 4079, 4093, 4361, 3420, 4359, 5747, 3501, 3492,
    /* 2703 */ 5304, 5318, 4695, 4087, 5121, 5329, 3537, 3492, 3492, 3899, 5337, 4535, 4340, 3492, 3492, 3972, 3492,
    /* 2720 */ 4389, 3539, 4280, 5346, 5338, 3492, 5357, 5369, 4403, 4796, 3492, 5807, 5381, 5589, 5392, 3492, 3492,
    /* 2737 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022,
    /* 2754 */ 4059, 4054, 4063, 4071, 5403, 4079, 4093, 3492, 3492, 3492, 5747, 5411, 3492, 5434, 3492, 3492, 4087,
    /* 2771 */ 3492, 4046, 3537, 3492, 3750, 3899, 5086, 4535, 3492, 3492, 3492, 4099, 3492, 3492, 5442, 5453, 3492,
    /* 2788 */ 3492, 5464, 5475, 3492, 5484, 3492, 3492, 5580, 5559, 3492, 5497, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2805 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071,
    /* 2822 */ 5506, 4079, 4093, 5417, 3492, 3616, 5747, 3501, 3492, 3513, 3492, 5514, 4087, 5525, 4046, 5536, 4586,
    /* 2839 */ 3492, 3899, 5249, 4535, 3492, 4487, 3492, 4186, 3492, 5548, 5556, 3492, 3492, 5567, 3492, 3492, 3492,
    /* 2856 */ 3492, 5579, 5498, 3492, 3492, 5588, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2873 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071, 5597, 4079, 4093, 3492,
    /* 2890 */ 3492, 3843, 5747, 3501, 3492, 3513, 3492, 3492, 4087, 3492, 4046, 5605, 3492, 4214, 3927, 3492, 4535,
    /* 2907 */ 3492, 5208, 5613, 5622, 3492, 3492, 3492, 3492, 3492, 5274, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2924 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2941 */ 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063, 4071, 5643, 4079, 4093, 3492, 3492, 3492, 5747, 3501,
    /* 2958 */ 3492, 3513, 3492, 3492, 4087, 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972,
    /* 2975 */ 3492, 3492, 3492, 5651, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 2992 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010,
    /* 3009 */ 4022, 4059, 4054, 4063, 4071, 3458, 4079, 4093, 3492, 5783, 3492, 5747, 3501, 3492, 3513, 5426, 3492,
    /* 3026 */ 4087, 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492,
    /* 3043 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 5025, 5662, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 3060 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4010, 4022, 4059, 4054, 4063,
    /* 3077 */ 4071, 5671, 4079, 4093, 3690, 3492, 3492, 5679, 3501, 3492, 3513, 3492, 3492, 4087, 3492, 4046, 3537,
    /* 3094 */ 3492, 3492, 3899, 3492, 4535, 5701, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 3111 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 3128 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4716, 5717, 5139, 5712, 3461, 5725, 5743, 3644, 4748,
    /* 3145 */ 3492, 3514, 3492, 5747, 3501, 3981, 3492, 3492, 3492, 3523, 3492, 4046, 3795, 3492, 3492, 3899, 3492,
    /* 3162 */ 4670, 3492, 3492, 3492, 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 3179 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 3196 */ 3492, 3492, 3492, 3492, 4716, 5654, 3565, 5755, 3461, 3624, 3458, 3644, 4748, 3492, 3492, 3492, 5747,
    /* 3213 */ 3501, 3492, 3513, 3492, 3492, 3523, 3492, 4046, 3537, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492,
    /* 3230 */ 3972, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 3247 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 3264 */ 4716, 5704, 3492, 3492, 3461, 5725, 3677, 3644, 4748, 3492, 3492, 3492, 5747, 3501, 3492, 5766, 3492,
    /* 3281 */ 3492, 5777, 3492, 4046, 3795, 3492, 3492, 3899, 3492, 4535, 3492, 3492, 3492, 3972, 3492, 3492, 3492,
    /* 3298 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 3315 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 4650, 3492, 4430, 5791,
    /* 3332 */ 3492, 5802, 3492, 4607, 3492, 3492, 3492, 3492, 5174, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 3349 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 3366 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492,
    /* 3383 */ 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 3492, 1061, 1061, 1061, 1066, 1061, 1061, 1061, 1061,
    /* 3400 */ 1061, 0, 0, 1061, 1061, 1061, 1061, 1061, 1061, 4655, 6704, 1061, 1061, 1061, 1061, 1061, 38, 0, 0, 43,
    /* 3420 */ 0, 0, 0, 0, 127, 0, 129, 0, 1061, 1061, 1061, 1061, 1061, 1061, 1061, 1061, 0, 0, 0, 0, 1066, 43, 43, 45,
    /* 3444 */ 64, 4655, 6704, 50, 0, 0, 0, 0, 0, 0, 115, 0, 0, 0, 49, 50, 6704, 0, 0, 0, 0, 0, 38, 0, 0, 0, 38, 59, 60,
    /* 3473 */ 61, 62, 45, 64, 104, 0, 49, 50, 69, 70, 0, 0, 0, 100, 0, 59, 61, 104, 120, 0, 0, 0, 0, 0, 0, 0, 0, 46, 0,
    /* 3502 */ 106, 107, 69, 0, 0, 0, 0, 0, 31744, 6704, 0, 0, 122, 0, 0, 0, 0, 0, 0, 0, 64, 0, 100, 147, 148, 149, 106,
    /* 3529 */ 0, 0, 0, 100, 0, 59, 61, 5120, 169, 122, 0, 0, 0, 0, 0, 0, 0, 23040, 104, 0, 0, 0, 69, 6704, 0, 0, 0,
    /* 3556 */ 100, 0, 102, 103, 0, 0, 106, 151, 69, 0, 0, 0, 0, 0, 32768, 32768, 32768, 0, 100, 147, 148, 199, 106, 0,
    /* 3580 */ 0, 0, 100, 0, 1024, 1067, 0, 0, 1061, 0, 0, 0, 64, 0, 0, 0, 100, 147, 59, 61, 0, 104, 0, 0, 0, 0, 0, 0,
    /* 3608 */ 0, 104, 104, 0, 0, 0, 69, 70, 0, 0, 0, 113, 0, 0, 0, 116, 0, 43, 43, 43, 45, 0, 4655, 6704, 0, 38, 38, 0,
    /* 3636 */ 0, 0, 38, 38, 0, 38, 0, 38, 0, 38, 59, 60, 61, 62, 45, 0, 38, 38, 38, 38, 0, 1536, 0, 0, 0, 162, 0, 0, 0,
    /* 3665 */ 0, 191, 0, 0, 0, 0, 43, 43, 43, 45, 0, 4655, 49, 50, 0, 0, 0, 0, 0, 0, 18944, 0, 0, 0, 168, 0, 0, 0, 0,
    /* 3694 */ 0, 0, 118, 0, 0, 100, 196, 148, 149, 106, 0, 0, 0, 181, 0, 0, 0, 0, 271, 0, 215, 0, 55, 55, 56, 56, 56,
    /* 3721 */ 56, 56, 56, 0, 38, 0, 0, 0, 4655, 49, 38, 49, 50, 6704, 0, 0, 8192, 8192, 8192, 0, 146, 147, 148, 149,
    /* 3745 */ 106, 0, 0, 0, 202, 0, 0, 0, 0, 235, 0, 0, 0, 45, 45, 0, 0, 0, 0, 45, 45, 45, 45, 45, 45, 45, 45, 0, 38,
    /* 3774 */ 0, 0, 0, 43, 43, 43, 3584, 0, 4655, 50, 49, 6704, 0, 0, 0, 0, 0, 0, 184, 0, 0, 169, 0, 0, 0, 0, 0, 0, 0,
    /* 3803 */ 119, 101, 38, 59, 60, 61, 62, 45, 0, 0, 133, 136, 0, 139, 0, 0, 0, 2048, 147, 148, 0, 0, 0, 225, 0, 0,
    /* 3829 */ 228, 0, 0, 43, 43, 61, 45, 0, 4655, 6704, 49, 50, 69, 71, 0, 0, 0, 0, 138, 140, 0, 0, 0, 38, 102, 60,
    /* 3855 */ 103, 62, 45, 0, 0, 134, 0, 0, 0, 141, 0, 0, 135, 137, 0, 0, 0, 0, 293, 0, 0, 0, 0, 106, 49, 50, 108, 70,
    /* 3883 */ 0, 0, 0, 233, 0, 0, 0, 0, 307, 0, 0, 0, 148, 150, 107, 108, 0, 0, 0, 0, 147, 148, 0, 0, 166, 122, 0, 0,
    /* 3911 */ 0, 0, 0, 0, 193, 0, 0, 100, 147, 198, 149, 150, 0, 0, 0, 262, 0, 0, 0, 0, 147, 148, 0, 241, 169, 215, 0,
    /* 3938 */ 0, 0, 0, 0, 0, 205, 0, 3113, 43, 43, 43, 45, 0, 4655, 6704, 0, 106, 107, 69, 0, 0, 0, 156, 186, 0, 0, 0,
    /* 3965 */ 0, 0, 0, 0, 120, 0, 275, 0, 0, 0, 0, 0, 0, 215, 0, 289, 0, 0, 0, 0, 0, 0, 0, 122, 0, 296, 0, 0, 0, 0, 0,
    /* 3996 */ 0, 0, 130, 0, 0, 311, 0, 0, 0, 0, 0, 38, 59, 0, 0, 0, 3113, 43, 0, 0, 0, 0, 147, 198, 0, 0, 0, 0, 3113,
    /* 4025 */ 3113, 0, 4655, 6704, 3113, 38, 59, 60, 61, 62, 45, 0, 0, 145, 100, 0, 59, 61, 0, 0, 161, 0, 0, 0, 0, 0,
    /* 4051 */ 166, 167, 168, 3113, 3113, 0, 0, 0, 3113, 3113, 3113, 3113, 3113, 3113, 3113, 3113, 0, 38, 0, 0, 3113,
    /* 4072 */ 43, 43, 43, 45, 65, 4655, 6704, 3113, 38, 59, 60, 61, 62, 45, 65, 0, 100, 147, 148, 149, 106, 65, 0, 49,
    /* 4096 */ 50, 69, 70, 0, 0, 0, 270, 0, 0, 215, 0, 39, 39, 39, 44, 39, 39, 39, 39, 0, 38, 60, 0, 39, 39, 39, 39, 39,
    /* 4124 */ 39, 39, 39, 4655, 51, 39, 0, 43, 43, 62, 45, 66, 4655, 51, 49, 50, 70, 0, 0, 0, 0, 0, 58, 0, 0, 105, 107,
    /* 4151 */ 49, 50, 69, 70, 0, 0, 0, 277, 278, 279, 280, 0, 121, 0, 0, 0, 0, 0, 0, 0, 142, 149, 106, 107, 6704, 0, 0,
    /* 4178 */ 0, 0, 147, 199, 0, 0, 167, 170, 0, 0, 0, 0, 0, 0, 215, 13073, 0, 100, 147, 148, 149, 151, 0, 0, 0, 292,
    /* 4204 */ 0, 0, 0, 0, 210, 166, 167, 168, 169, 216, 0, 0, 0, 0, 0, 0, 237, 0, 0, 2600, 2600, 0, 0, 38, 0, 2600, 0,
    /* 4231 */ 43, 0, 0, 0, 0, 163, 0, 0, 0, 28672, 28672, 0, 0, 0, 0, 0, 0, 247, 0, 29184, 29184, 29184, 29184, 29184,
    /* 4255 */ 29184, 29184, 29184, 0, 38, 0, 0, 29696, 29696, 29748, 0, 0, 0, 29696, 29696, 0, 0, 0, 0, 29696, 29696,
    /* 4276 */ 29696, 29748, 0, 38, 0, 0, 0, 298, 299, 0, 0, 302, 0, 43, 43, 43, 45, 0, 0, 6704, 0, 31232, 0, 0, 0,
    /* 4301 */ 4655, 6704, 0, 0, 166, 0, 0, 0, 215, 122, 49, 50, 6704, 30720, 0, 0, 0, 0, 182, 0, 0, 0, 49, 50, 6704, 0,
    /* 4327 */ 0, 0, 81, 0, 0, 166, 0, 0, 0, 215, 216, 0, 46, 0, 0, 0, 0, 0, 0, 250, 0, 0, 43, 43, 43, 63, 0, 4655,
    /* 4355 */ 6704, 67, 68, 6704, 0, 0, 0, 0, 0, 114, 0, 0, 0, 0, 0, 100, 197, 148, 149, 106, 0, 0, 0, 319, 0, 0, 0, 0,
    /* 4383 */ 147, 148, 5120, 0, 214, 122, 0, 0, 0, 0, 0, 0, 287, 0, 5120, 0, 49, 50, 69, 70, 0, 0, 0, 337, 0, 0, 340,
    /* 4410 */ 10752, 3129, 3113, 3129, 3113, 0, 38, 0, 0, 0, 343, 0, 0, 345, 346, 200, 0, 0, 0, 203, 0, 0, 0, 0, 7680,
    /* 4435 */ 7680, 7680, 7680, 200, 0, 0, 0, 0, 0, 0, 229, 0, 0, 324, 0, 0, 326, 0, 0, 0, 12288, 0, 0, 362, 0, 347, 0,
    /* 4462 */ 0, 0, 0, 0, 0, 0, 185, 0, 122, 0, 0, 0, 0, 0, 176, 0, 240, 0, 0, 147, 148, 0, 0, 0, 16896, 0, 0, 0, 0,
    /* 4491 */ 256, 0, 0, 0, 0, 0, 342, 0, 0, 0, 0, 0, 148, 0, 0, 49, 50, 6704, 0, 72, 72, 72, 72, 195, 100, 147, 148,
    /* 4518 */ 149, 106, 65, 0, 49, 50, 69, 70, 0, 109, 169, 122, 0, 0, 0, 0, 22221, 0, 0, 166, 0, 168, 169, 215, 216,
    /* 4543 */ 0, 0, 28160, 0, 147, 148, 0, 0, 0, 19968, 0, 0, 0, 0, 285, 0, 0, 0, 11264, 0, 248, 0, 0, 0, 0, 0, 155, 0,
    /* 4571 */ 0, 0, 330, 0, 0, 0, 0, 0, 0, 295, 0, 0, 17920, 0, 365, 0, 0, 0, 0, 226, 0, 0, 0, 49, 50, 6704, 0, 73, 73,
    /* 4600 */ 82, 86, 0, 106, 107, 69, 152, 0, 0, 0, 0, 69632, 69632, 0, 0, 0, 202, 232, 0, 0, 0, 0, 0, 164, 0, 0, 49,
    /* 4627 */ 50, 6704, 0, 0, 0, 83, 87, 110, 111, 0, 0, 0, 0, 0, 0, 301, 0, 0, 110, 0, 126, 0, 128, 0, 0, 0, 69632,
    /* 4654 */ 7680, 0, 0, 0, 53, 54, 54, 55, 55, 0, 144, 0, 100, 0, 59, 61, 0, 0, 166, 122, 168, 169, 215, 216, 158,
    /* 4679 */ 159, 0, 0, 0, 0, 0, 0, 314, 0, 0, 122, 0, 0, 0, 0, 175, 0, 0, 189, 190, 0, 0, 0, 194, 0, 100, 147, 148,
    /* 4707 */ 149, 106, 65, 8704, 169, 122, 217, 0, 219, 0, 0, 0, 43, 0, 0, 0, 0, 46, 4655, 6704, 0, 230, 0, 0, 0, 0,
    /* 4733 */ 236, 0, 0, 45, 0, 0, 4655, 50, 45, 239, 0, 0, 0, 147, 148, 0, 0, 49, 50, 69, 70, 0, 0, 0, 146, 0, 59, 61,
    /* 4761 */ 0, 0, 243, 0, 0, 245, 0, 0, 0, 43, 0, 0, 0, 29696, 10002, 0, 0, 0, 0, 0, 0, 0, 206, 0, 0, 305, 306, 0, 0,
    /* 4790 */ 23552, 0, 0, 203, 0, 234, 0, 0, 0, 0, 14336, 0, 0, 0, 0, 24576, 0, 0, 0, 0, 26112, 0, 0, 0, 0, 28672,
    /* 4816 */ 4655, 6704, 0, 0, 27648, 0, 0, 0, 0, 0, 0, 321, 0, 316, 317, 0, 0, 15360, 0, 0, 322, 0, 22528, 0, 0, 0,
    /* 4842 */ 0, 0, 0, 327, 0, 0, 348, 0, 350, 0, 0, 0, 0, 263, 0, 0, 0, 0, 371, 0, 0, 0, 0, 0, 0, 334, 0, 49, 50,
    /* 4871 */ 6704, 0, 0, 0, 0, 88, 0, 106, 107, 69, 0, 153, 155, 0, 0, 209, 26624, 0, 166, 167, 168, 0, 122, 0, 0, 0,
    /* 4897 */ 0, 0, 177, 0, 335, 0, 0, 0, 0, 0, 0, 355, 0, 49, 50, 6704, 0, 0, 0, 0, 89, 169, 122, 0, 0, 0, 220, 0, 0,
    /* 4926 */ 59, 60, 61, 62, 0, 64, 207, 0, 0, 0, 0, 166, 167, 168, 49, 50, 6704, 0, 0, 0, 0, 90, 0, 122, 0, 0, 0, 0,
    /* 4954 */ 160, 0, 0, 0, 0, 165, 0, 222, 0, 0, 0, 0, 0, 0, 0, 251, 0, 253, 0, 0, 0, 0, 258, 0, 0, 212, 0, 168, 169,
    /* 4983 */ 215, 216, 357, 358, 0, 0, 0, 0, 0, 0, 368, 0, 0, 0, 364, 0, 0, 0, 0, 0, 166, 167, 213, 49, 50, 6704, 0,
    /* 5010 */ 74, 74, 74, 91, 131, 0, 0, 0, 0, 0, 0, 0, 259, 0, 179, 0, 0, 0, 0, 0, 0, 13824, 0, 0, 231, 0, 0, 0, 0, 0,
    /* 5040 */ 238, 267, 0, 0, 0, 0, 0, 215, 0, 0, 224, 0, 0, 0, 0, 0, 166, 167, 122, 329, 0, 0, 0, 0, 0, 0, 0, 266, 0,
    /* 5069 */ 0, 349, 0, 0, 0, 0, 0, 166, 212, 168, 49, 50, 6704, 0, 0, 0, 84, 0, 0, 244, 0, 0, 0, 0, 0, 308, 0, 0, 0,
    /* 5098 */ 20992, 0, 0, 0, 0, 0, 0, 25865, 0, 0, 0, 336, 0, 0, 0, 0, 0, 192, 0, 0, 0, 0, 21504, 0, 0, 0, 0, 0, 204,
    /* 5127 */ 0, 0, 49, 50, 6704, 0, 75, 75, 75, 92, 0, 132, 0, 0, 0, 0, 0, 0, 32256, 32256, 0, 106, 107, 69, 0, 0, 0,
    /* 5154 */ 157, 187, 0, 0, 0, 0, 0, 0, 0, 281, 25600, 27136, 166, 0, 168, 169, 215, 216, 0, 283, 0, 0, 0, 0, 0, 0,
    /* 5180 */ 69632, 0, 0, 354, 0, 0, 0, 0, 0, 0, 211, 167, 168, 49, 50, 6704, 0, 0, 0, 0, 93, 143, 0, 0, 100, 0, 59,
    /* 5207 */ 61, 0, 0, 254, 0, 0, 0, 0, 0, 367, 0, 0, 0, 223, 0, 0, 0, 0, 0, 0, 227, 0, 0, 303, 0, 0, 0, 0, 0, 0, 309,
    /* 5238 */ 49, 50, 6704, 0, 76, 76, 85, 94, 0, 123, 0, 0, 0, 0, 0, 0, 246, 24064, 25088, 0, 122, 0, 172, 173, 0, 0,
    /* 5264 */ 0, 43, 0, 0, 29184, 0, 252, 0, 0, 255, 0, 0, 0, 0, 313, 0, 0, 0, 282, 0, 0, 0, 0, 286, 0, 0, 59, 1024,
    /* 5292 */ 61, 1067, 0, 64, 49, 50, 6704, 0, 77, 77, 77, 95, 0, 122, 171, 0, 0, 174, 0, 0, 112, 0, 0, 0, 117, 0,
    /* 5318 */ 178, 0, 180, 0, 0, 0, 0, 0, 257, 0, 0, 0, 208, 0, 0, 0, 166, 167, 168, 242, 0, 0, 0, 0, 0, 0, 0, 315, 0,
    /* 5347 */ 304, 0, 0, 0, 0, 0, 0, 272, 215, 0, 0, 323, 0, 325, 0, 0, 0, 0, 332, 0, 0, 0, 0, 0, 13312, 331, 0, 0, 0,
    /* 5376 */ 0, 351, 0, 0, 0, 0, 0, 359, 0, 0, 0, 0, 0, 294, 0, 0, 0, 0, 19456, 0, 0, 0, 0, 0, 300, 0, 0, 49, 50,
    /* 5405 */ 6704, 0, 78, 78, 78, 96, 0, 106, 107, 69, 0, 154, 0, 0, 113, 0, 0, 116, 0, 0, 124, 0, 0, 0, 0, 0, 183, 0,
    /* 5433 */ 0, 0, 122, 0, 0, 0, 0, 0, 154, 0, 290, 0, 0, 0, 0, 0, 0, 320, 0, 0, 0, 10240, 0, 0, 0, 0, 0, 0, 333, 0,
    /* 5463 */ 0, 0, 0, 318, 0, 0, 0, 0, 0, 339, 0, 0, 18432, 0, 0, 0, 0, 0, 0, 0, 328, 7168, 0, 0, 0, 338, 0, 0, 0, 43,
    /* 5493 */ 0, 28672, 0, 0, 370, 0, 0, 0, 0, 0, 0, 0, 353, 49, 50, 6704, 0, 79, 79, 79, 97, 0, 188, 0, 0, 0, 0, 0, 0,
    /* 5522 */ 344, 0, 0, 0, 201, 15872, 0, 0, 0, 0, 0, 352, 0, 0, 169, 122, 0, 218, 0, 0, 0, 0, 360, 0, 0, 0, 0, 0,
    /* 5550 */ 284, 20480, 0, 0, 0, 288, 0, 0, 291, 0, 0, 0, 0, 0, 361, 0, 0, 310, 0, 0, 312, 0, 0, 0, 0, 366, 0, 0, 0,
    /* 5579 */ 341, 0, 0, 0, 0, 0, 0, 0, 356, 363, 0, 0, 0, 0, 0, 0, 0, 369, 49, 50, 6704, 0, 80, 80, 80, 80, 169, 122,
    /* 5607 */ 0, 0, 0, 0, 0, 221, 260, 0, 0, 0, 0, 0, 0, 0, 14848, 0, 268, 0, 0, 0, 0, 215, 0, 0, 261, 0, 0, 264, 0, 0,
    /* 5637 */ 49, 50, 69, 70, 30208, 0, 49, 50, 6704, 0, 0, 0, 0, 98, 0, 0, 297, 0, 0, 0, 0, 0, 4655, 6704, 0, 17408,
    /* 5663 */ 0, 0, 0, 0, 0, 0, 0, 16384, 49, 50, 6704, 0, 0, 0, 0, 99, 118, 0, 0, 100, 0, 59, 61, 0, 0, 269, 0, 0, 0,
    /* 5692 */ 215, 0, 0, 211, 0, 168, 169, 247, 216, 0, 11776, 0, 0, 0, 0, 0, 0, 4655, 33280, 0, 32256, 32256, 0, 0,
    /* 5716 */ 32256, 32256, 0, 0, 32256, 0, 4655, 32256, 0, 0, 43, 43, 43, 45, 0, 4655, 0, 0, 276, 0, 0, 0, 0, 0, 249,
    /* 5741 */ 0, 0, 49, 50, 0, 0, 0, 0, 0, 100, 0, 59, 61, 0, 32768, 32768, 0, 0, 0, 0, 0, 0, 9216, 0, 0, 0, 6144, 0,
    /* 5769 */ 0, 0, 0, 0, 0, 29184, 29184, 29184, 0, 5632, 147, 148, 149, 106, 0, 0, 125, 0, 0, 0, 125, 0, 7680, 7680,
    /* 5793 */ 0, 0, 0, 0, 0, 0, 29696, 6704, 0, 0, 69632, 69632, 69632, 0, 0, 0, 0, 7168, 0, 0, 0, 0
  };

  private static final int[] EXPECTED =
  {
    /*   0 */ 18, 34, 50, 80, 64, 96, 112, 128, 144, 160, 176, 192, 206, 206, 206, 206, 206, 206, 222, 231, 284, 246,
    /*  22 */ 250, 254, 256, 260, 264, 234, 268, 227, 272, 277, 281, 225, 326, 273, 331, 459, 288, 346, 303, 457, 494,
    /*  43 */ 322, 325, 330, 459, 239, 335, 344, 446, 350, 438, 459, 353, 359, 375, 459, 459, 241, 335, 455, 445, 391,
    /*  64 */ 459, 457, 537, 459, 413, 390, 458, 457, 537, 339, 446, 392, 438, 537, 340, 447, 541, 355, 540, 459, 240,
    /*  85 */ 242, 453, 385, 390, 458, 438, 459, 539, 526, 386, 391, 458, 476, 398, 396, 475, 399, 438, 378, 397, 403,
    /* 106 */ 459, 459, 459, 459, 381, 406, 410, 417, 421, 424, 427, 429, 459, 459, 459, 291, 294, 459, 459, 459, 433,
    /* 127 */ 306, 362, 314, 459, 506, 442, 337, 459, 459, 467, 308, 312, 459, 316, 459, 318, 473, 451, 459, 459, 370,
    /* 148 */ 515, 310, 459, 459, 465, 459, 460, 471, 365, 337, 459, 513, 517, 540, 459, 435, 459, 459, 480, 485, 367,
    /* 169 */ 371, 493, 459, 460, 481, 487, 369, 491, 459, 459, 498, 487, 369, 237, 459, 461, 299, 510, 238, 459, 298,
    /* 190 */ 510, 238, 297, 502, 460, 500, 504, 524, 521, 530, 531, 535, 459, 459, 459, 437, 459, 459, 459, 459, 459,
    /* 211 */ 459, 459, 459, 459, 459, 459, 459, 459, 459, 459, 459, 6, 18, 34, 130, 130, 64, 512, 256, 4096, 16386, 2,
    /* 233 */ 2, 2, 4, 2, 16, 2048, 0, 0, 0, 2048, 2048, 2048, 2048, 514, 256, 4096, 102, 98, 102, 102, 1074987106,
    /* 254 */ 1075036258, 1075036258, 1131661414, 1131661926, -6315006, -6315002, -6315006, -6314970, -6314910,
    /* 263 */ -6314906, -6314898, -6314890, -6314882, -6314906, 32, 130, 128, 130, 4096, 4096, 4096, 0, 65536,
    /* 277 */ 1074987008, 1075019776, 1131644928, -6315008, 40, 4, 2, 2, 6, 98, 34, 2048, 3276800, 54525952, 0, 0,
    /* 293 */ 201326592, 1792084, 1793109, 8387582, 0, 2, 32, 512, 0, 32768, 134217728, 268435456, -536862720, 0, 84,
    /* 308 */ 6144, 16384, 65536, 131072, 524288, 1048576, 0, 0, 1, 7168, 0, 0, 2, 60, 8, 2, 130, 512, 512, 512, 4096,
    /* 329 */ 4096, 65536, 1179648, 1073741824, 0, 0, 2048, 2048, 2097152, 4194304, 0, 0, 0, 33554432, 8388608,
    /* 344 */ 50331648, 0, 0, 1974272, 58720256, 67108864, 536870912, -1073741824, 8192, 0, 1024, 8, 512, 512, 131072,
    /* 359 */ 512, 512, 512, 65536, 655360, 1048576, 0, 8192, 32768, 393216, 2097152, 0, 0, 4, 16, 2048, 131072,
    /* 376 */ 1048576, 1073741824, 0, 131072, 0, 8388608, 16777216, 33554432, 1073741824, 8192, 786432, 8388608,
    /* 388 */ 67108864, 134217728, 134217728, 268435456, 536870912, -2147483648, 8192, 0, 134217728, 268435456, 0, 0,
    /* 400 */ 8388608, 67108864, 268435456, 268435456, 0, 0, 268435456, 0, 1073741824, 8388608, 570425344, 1073741824,
    /* 412 */ 0, 33554432, 524288, 8388608, 67108864, 33554432, 234881024, 1792084, 1792084, -2128914348, -1021617067,
    /* 423 */ -1021617067, -1013228459, 25164798, 25164798, 1098906622, 58719230, 58719230, 58719230, 260045822, 0, 0,
    /* 434 */ 67108864, 0, 0, 1, 0, 0, 0, 8192, 6912, 24576, 98304, 917504, 8388608, 67108864, 134217728, 268435456,
    /* 450 */ -2147483648, 32768, 393216, 2097152, 4194304, 50331648, 0, 0, 8192, 0, 0, 0, 0, 2, 8, 1, 3072, 0, 0, 20,
    /* 470 */ 64, 12, 48, 128, 768, 0, 8192, 0, 131072, 1048576, 2, 8, 32, 128, 512, 256, 512, 0, 8192, 32768, 262144,
    /* 491 */ 16, 2048, 4096, 0, 0, 0, 1024, 2, 8, 32, 512, 0, 262144, 0, 4, 0, 0, 126, 128, 262144, 0, 0, 4, 16, 64,
    /* 516 */ 2048, 4096, 16384, 65536, 131072, 32, 4, 0, 32, 4, 0, 0, 2048, 50331648, 4, 32, 32, 32, 32, 32, 32, 0, 0,
    /* 539 */ 131072, 1048576, 0, 0, 0, 8
  };

  private static final String[] TOKEN =
  {
    "(0)",
    "WHITESPACE",
    "STRING",
    "TSTRING",
    "INT_LITERAL",
    "ID",
    "CHAR_LITERAL",
    "EPILOGUE",
    "ANGLELESS_CODE",
    "BRACKETED_ID",
    "PROLOGUE",
    "BRACED_PREDICATE",
    "BRACELESS_CODE",
    "PERCENT_FLAG",
    "EOF",
    "'%%'",
    "'%code'",
    "'%default-prec'",
    "'%define'",
    "'%defines'",
    "'%destructor'",
    "'%dprec'",
    "'%empty'",
    "'%error-verbose'",
    "'%expect'",
    "'%expect-rr'",
    "'%file-prefix'",
    "'%glr-parser'",
    "'%initial-action'",
    "'%language'",
    "'%left'",
    "'%lex-param'",
    "'%merge'",
    "'%name-prefix'",
    "'%no-default-prec'",
    "'%no-lines'",
    "'%nonassoc'",
    "'%nondeterministic-parser'",
    "'%nterm'",
    "'%output'",
    "'%param'",
    "'%parse-param'",
    "'%prec'",
    "'%precedence'",
    "'%printer'",
    "'%require'",
    "'%right'",
    "'%skeleton'",
    "'%start'",
    "'%token'",
    "'%token-table'",
    "'%type'",
    "'%union'",
    "'%verbose'",
    "'%yacc'",
    "':'",
    "';'",
    "'<'",
    "'<*>'",
    "'<>'",
    "'='",
    "'>'",
    "'{'",
    "'|'",
    "'}'"
  };
}

// End
