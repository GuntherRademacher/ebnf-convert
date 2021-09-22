// This file was generated on Sat Jul 17, 2021 11:14 (UTC+02) by REx v5.53 which is Copyright (c) 1979-2021 by Gunther Rademacher <grd@gmx.net>
// REx command line: -backtrack -tree -a none -java -interface de.bottlecaps.railroad.convert.Parser -saxon10 -name de.bottlecaps.railroad.convert.antlr_3.Antlr_3 antlr_3.ebnf

package de.bottlecaps.railroad.convert.antlr_3;

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

public class Antlr_3 implements de.bottlecaps.railroad.convert.Parser
{
  public static class SaxonInitializer implements Initializer
  {
    @Override
    public void initialize(Configuration conf)
    {
      conf.registerExtensionFunction(new SaxonDefinition_grammar_());
    }
  }

  public static Sequence parseGrammar_(XPathContext context, String input) throws XPathException
  {
    Builder builder = context.getController().makeBuilder();
    builder.open();
    Antlr_3 parser = new Antlr_3(input, new SaxonTreeBuilder(builder));
    try
    {
      parser.parse_grammar_();
    }
    catch (ParseException pe)
    {
      buildError(parser, pe, builder);
    }
    return builder.getCurrentRoot();
  }

  public static class SaxonDefinition_grammar_ extends SaxonDefinition
  {
    @Override
    public String functionName() {return "parse-grammar_";}
    @Override
    public Sequence execute(XPathContext context, String input) throws XPathException
    {
      return parseGrammar_(context, input);
    }
  }

  public static abstract class SaxonDefinition extends ExtensionFunctionDefinition
  {
    abstract String functionName();
    abstract Sequence execute(XPathContext context, String input) throws XPathException;

    @Override
    public StructuredQName getFunctionQName() {return new StructuredQName("p", "Antlr_3", functionName());}
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

  private static void buildError(Antlr_3 parser, ParseException pe, Builder builder) throws XPathException
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

  public Antlr_3(CharSequence string, EventHandler t)
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
    ex = -1;
    memo.clear();
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
    parse_grammar_();
  }

  public void parse_grammar_()
  {
    eventHandler.startNonterminal("grammar_", e0);
    lookahead1W(43);                // DOC_COMMENT | WHITESPACE | 'grammar' | 'lexer' | 'parser' | 'tree' | '{'
    if (l1 == 55)                   // '{'
    {
      whitespace();
      parse_ACTION();
    }
    lookahead1W(38);                // DOC_COMMENT | WHITESPACE | 'grammar' | 'lexer' | 'parser' | 'tree'
    if (l1 == 1)                    // DOC_COMMENT
    {
      consume(1);                   // DOC_COMMENT
    }
    lookahead1W(37);                // WHITESPACE | 'grammar' | 'lexer' | 'parser' | 'tree'
    whitespace();
    parse_grammarType();
    lookahead1W(14);                // TOKEN_REF | RULE_REF | WHITESPACE
    whitespace();
    parse_id();
    lookahead1W(9);                 // WHITESPACE | ';'
    consume(32);                    // ';'
    lookahead1W(64);                // DOC_COMMENT | TOKEN_REF | TOKENS | OPTIONS | RULE_REF | WHITESPACE | '@' |
                                    // 'fragment' | 'import' | 'private' | 'protected' | 'public' | 'scope'
    if (l1 == 15)                   // OPTIONS
    {
      whitespace();
      parse_optionsSpec();
    }
    lookahead1W(63);                // DOC_COMMENT | TOKEN_REF | TOKENS | RULE_REF | WHITESPACE | '@' | 'fragment' |
                                    // 'import' | 'private' | 'protected' | 'public' | 'scope'
    if (l1 == 45)                   // 'import'
    {
      whitespace();
      parse_delegateGrammars();
    }
    lookahead1W(58);                // DOC_COMMENT | TOKEN_REF | TOKENS | RULE_REF | WHITESPACE | '@' | 'fragment' |
                                    // 'private' | 'protected' | 'public' | 'scope'
    if (l1 == 14)                   // TOKENS
    {
      whitespace();
      parse_tokensSpec();
    }
    lookahead1W(55);                // DOC_COMMENT | TOKEN_REF | RULE_REF | WHITESPACE | '@' | 'fragment' | 'private' |
                                    // 'protected' | 'public' | 'scope'
    whitespace();
    parse_attrScopes();
    if (l1 == 38)                   // '@'
    {
      whitespace();
      parse_actions();
    }
    whitespace();
    parse_rules();
    consume(17);                    // EOF
    eventHandler.endNonterminal("grammar_", e0);
  }

  private void parse_grammarType()
  {
    eventHandler.startNonterminal("grammarType", e0);
    if (l1 != 44)                   // 'grammar'
    {
      switch (l1)
      {
      case 46:                      // 'lexer'
        consume(46);                // 'lexer'
        break;
      case 47:                      // 'parser'
        consume(47);                // 'parser'
        break;
      default:
        consume(54);                // 'tree'
      }
    }
    lookahead1W(11);                // WHITESPACE | 'grammar'
    consume(44);                    // 'grammar'
    eventHandler.endNonterminal("grammarType", e0);
  }

  private void parse_actions()
  {
    eventHandler.startNonterminal("actions", e0);
    for (;;)
    {
      whitespace();
      parse_action();
      lookahead1W(53);              // DOC_COMMENT | TOKEN_REF | RULE_REF | WHITESPACE | '@' | 'fragment' | 'private' |
                                    // 'protected' | 'public'
      if (l1 != 38)                 // '@'
      {
        break;
      }
    }
    eventHandler.endNonterminal("actions", e0);
  }

  private void parse_action()
  {
    eventHandler.startNonterminal("action", e0);
    consume(38);                    // '@'
    lookahead1W(36);                // TOKEN_REF | RULE_REF | WHITESPACE | 'lexer' | 'parser'
    switch (l1)
    {
    case 13:                        // TOKEN_REF
    case 16:                        // RULE_REF
      lookahead2W(21);              // WHITESPACE | ':' | '{'
      break;
    default:
      lk = l1;
    }
    if (lk != 3533                  // TOKEN_REF '{'
     && lk != 3536)                 // RULE_REF '{'
    {
      whitespace();
      parse_actionScopeName();
      lookahead1W(8);               // WHITESPACE | ':'
      consume(31);                  // ':'
      lookahead1W(8);               // WHITESPACE | ':'
      consume(31);                  // ':'
    }
    lookahead1W(14);                // TOKEN_REF | RULE_REF | WHITESPACE
    whitespace();
    parse_id();
    lookahead1W(12);                // WHITESPACE | '{'
    whitespace();
    parse_ACTION();
    eventHandler.endNonterminal("action", e0);
  }

  private void parse_actionScopeName()
  {
    eventHandler.startNonterminal("actionScopeName", e0);
    switch (l1)
    {
    case 46:                        // 'lexer'
      consume(46);                  // 'lexer'
      break;
    case 47:                        // 'parser'
      consume(47);                  // 'parser'
      break;
    default:
      parse_id();
    }
    eventHandler.endNonterminal("actionScopeName", e0);
  }

  private void parse_optionsSpec()
  {
    eventHandler.startNonterminal("optionsSpec", e0);
    consume(15);                    // OPTIONS
    for (;;)
    {
      lookahead1W(14);              // TOKEN_REF | RULE_REF | WHITESPACE
      whitespace();
      parse_option();
      lookahead1W(9);               // WHITESPACE | ';'
      consume(32);                  // ';'
      lookahead1W(27);              // TOKEN_REF | RULE_REF | WHITESPACE | '}'
      if (l1 == 57)                 // '}'
      {
        break;
      }
    }
    consume(57);                    // '}'
    eventHandler.endNonterminal("optionsSpec", e0);
  }

  private void try_optionsSpec()
  {
    consumeT(15);                   // OPTIONS
    for (;;)
    {
      lookahead1W(14);              // TOKEN_REF | RULE_REF | WHITESPACE
      try_option();
      lookahead1W(9);               // WHITESPACE | ';'
      consumeT(32);                 // ';'
      lookahead1W(27);              // TOKEN_REF | RULE_REF | WHITESPACE | '}'
      if (l1 == 57)                 // '}'
      {
        break;
      }
    }
    consumeT(57);                   // '}'
  }

  private void parse_option()
  {
    eventHandler.startNonterminal("option", e0);
    parse_id();
    lookahead1W(10);                // WHITESPACE | '='
    consume(34);                    // '='
    lookahead1W(45);                // CHAR_LITERAL | STRING_LITERAL | INT | TOKEN_REF | RULE_REF | WHITESPACE | '*'
    whitespace();
    parse_optionValue();
    eventHandler.endNonterminal("option", e0);
  }

  private void try_option()
  {
    try_id();
    lookahead1W(10);                // WHITESPACE | '='
    consumeT(34);                   // '='
    lookahead1W(45);                // CHAR_LITERAL | STRING_LITERAL | INT | TOKEN_REF | RULE_REF | WHITESPACE | '*'
    try_optionValue();
  }

  private void parse_optionValue()
  {
    eventHandler.startNonterminal("optionValue", e0);
    switch (l1)
    {
    case 4:                         // STRING_LITERAL
      consume(4);                   // STRING_LITERAL
      break;
    case 3:                         // CHAR_LITERAL
      consume(3);                   // CHAR_LITERAL
      break;
    case 7:                         // INT
      consume(7);                   // INT
      break;
    case 23:                        // '*'
      consume(23);                  // '*'
      break;
    default:
      parse_id();
    }
    eventHandler.endNonterminal("optionValue", e0);
  }

  private void try_optionValue()
  {
    switch (l1)
    {
    case 4:                         // STRING_LITERAL
      consumeT(4);                  // STRING_LITERAL
      break;
    case 3:                         // CHAR_LITERAL
      consumeT(3);                  // CHAR_LITERAL
      break;
    case 7:                         // INT
      consumeT(7);                  // INT
      break;
    case 23:                        // '*'
      consumeT(23);                 // '*'
      break;
    default:
      try_id();
    }
  }

  private void parse_delegateGrammars()
  {
    eventHandler.startNonterminal("delegateGrammars", e0);
    consume(45);                    // 'import'
    lookahead1W(14);                // TOKEN_REF | RULE_REF | WHITESPACE
    whitespace();
    parse_delegateGrammar();
    for (;;)
    {
      lookahead1W(19);              // WHITESPACE | ',' | ';'
      if (l1 != 26)                 // ','
      {
        break;
      }
      consume(26);                  // ','
      lookahead1W(14);              // TOKEN_REF | RULE_REF | WHITESPACE
      whitespace();
      parse_delegateGrammar();
    }
    consume(32);                    // ';'
    eventHandler.endNonterminal("delegateGrammars", e0);
  }

  private void parse_delegateGrammar()
  {
    eventHandler.startNonterminal("delegateGrammar", e0);
    parse_id();
    lookahead1W(29);                // WHITESPACE | ',' | ';' | '='
    if (l1 == 34)                   // '='
    {
      consume(34);                  // '='
      lookahead1W(14);              // TOKEN_REF | RULE_REF | WHITESPACE
      whitespace();
      parse_id();
    }
    eventHandler.endNonterminal("delegateGrammar", e0);
  }

  private void parse_tokensSpec()
  {
    eventHandler.startNonterminal("tokensSpec", e0);
    consume(14);                    // TOKENS
    for (;;)
    {
      lookahead1W(15);              // TOKEN_REF | WHITESPACE | '}'
      if (l1 != 13)                 // TOKEN_REF
      {
        break;
      }
      whitespace();
      parse_tokenSpec();
    }
    consume(57);                    // '}'
    eventHandler.endNonterminal("tokensSpec", e0);
  }

  private void parse_tokenSpec()
  {
    eventHandler.startNonterminal("tokenSpec", e0);
    consume(13);                    // TOKEN_REF
    lookahead1W(22);                // WHITESPACE | ';' | '='
    if (l1 == 34)                   // '='
    {
      consume(34);                  // '='
      lookahead1W(13);              // CHAR_LITERAL | STRING_LITERAL | WHITESPACE
      switch (l1)
      {
      case 4:                       // STRING_LITERAL
        consume(4);                 // STRING_LITERAL
        break;
      default:
        consume(3);                 // CHAR_LITERAL
      }
    }
    lookahead1W(9);                 // WHITESPACE | ';'
    consume(32);                    // ';'
    eventHandler.endNonterminal("tokenSpec", e0);
  }

  private void parse_attrScopes()
  {
    eventHandler.startNonterminal("attrScopes", e0);
    for (;;)
    {
      lookahead1W(55);              // DOC_COMMENT | TOKEN_REF | RULE_REF | WHITESPACE | '@' | 'fragment' | 'private' |
                                    // 'protected' | 'public' | 'scope'
      if (l1 != 52)                 // 'scope'
      {
        break;
      }
      whitespace();
      parse_attrScope();
    }
    eventHandler.endNonterminal("attrScopes", e0);
  }

  private void parse_attrScope()
  {
    eventHandler.startNonterminal("attrScope", e0);
    consume(52);                    // 'scope'
    lookahead1W(14);                // TOKEN_REF | RULE_REF | WHITESPACE
    whitespace();
    parse_id();
    lookahead1W(25);                // WHITESPACE | '@' | '{'
    if (l1 == 38)                   // '@'
    {
      whitespace();
      parse_ruleActions();
    }
    whitespace();
    parse_ACTION();
    eventHandler.endNonterminal("attrScope", e0);
  }

  private void parse_rules()
  {
    eventHandler.startNonterminal("rules", e0);
    for (;;)
    {
      whitespace();
      parse_rule();
      lookahead1W(52);              // DOC_COMMENT | TOKEN_REF | RULE_REF | EOF | WHITESPACE | 'fragment' | 'private' |
                                    // 'protected' | 'public'
      if (l1 == 17)                 // EOF
      {
        break;
      }
    }
    eventHandler.endNonterminal("rules", e0);
  }

  private void parse_rule()
  {
    eventHandler.startNonterminal("rule", e0);
    if (l1 == 1)                    // DOC_COMMENT
    {
      consume(1);                   // DOC_COMMENT
    }
    lookahead1W(48);                // TOKEN_REF | RULE_REF | WHITESPACE | 'fragment' | 'private' | 'protected' |
                                    // 'public'
    if (l1 != 13                    // TOKEN_REF
     && l1 != 16)                   // RULE_REF
    {
      switch (l1)
      {
      case 49:                      // 'protected'
        consume(49);                // 'protected'
        break;
      case 50:                      // 'public'
        consume(50);                // 'public'
        break;
      case 48:                      // 'private'
        consume(48);                // 'private'
        break;
      default:
        consume(43);                // 'fragment'
      }
    }
    lookahead1W(14);                // TOKEN_REF | RULE_REF | WHITESPACE
    whitespace();
    parse_id();
    lookahead1W(54);                // ARG_ACTION | OPTIONS | WHITESPACE | '!' | ':' | '@' | 'returns' | 'scope' |
                                    // 'throws'
    if (l1 == 19)                   // '!'
    {
      consume(19);                  // '!'
    }
    lookahead1W(51);                // ARG_ACTION | OPTIONS | WHITESPACE | ':' | '@' | 'returns' | 'scope' | 'throws'
    if (l1 == 8)                    // ARG_ACTION
    {
      consume(8);                   // ARG_ACTION
    }
    lookahead1W(49);                // OPTIONS | WHITESPACE | ':' | '@' | 'returns' | 'scope' | 'throws'
    if (l1 == 51)                   // 'returns'
    {
      consume(51);                  // 'returns'
      lookahead1W(2);               // ARG_ACTION | WHITESPACE
      consume(8);                   // ARG_ACTION
    }
    lookahead1W(42);                // OPTIONS | WHITESPACE | ':' | '@' | 'scope' | 'throws'
    if (l1 == 53)                   // 'throws'
    {
      whitespace();
      parse_throwsSpec();
    }
    if (l1 == 15)                   // OPTIONS
    {
      whitespace();
      parse_optionsSpec();
    }
    lookahead1W(32);                // WHITESPACE | ':' | '@' | 'scope'
    whitespace();
    parse_ruleScopeSpec();
    if (l1 == 38)                   // '@'
    {
      whitespace();
      parse_ruleActions();
    }
    consume(31);                    // ':'
    lookahead1W(68);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | '->' |
                                    // '.' | ';' | '^(' | '{' | '|' | '~'
    whitespace();
    parse_ruleAltList();
    consume(32);                    // ';'
    lookahead1W(59);                // DOC_COMMENT | TOKEN_REF | RULE_REF | EOF | WHITESPACE | 'catch' | 'finally' |
                                    // 'fragment' | 'private' | 'protected' | 'public'
    if (l1 == 41                    // 'catch'
     || l1 == 42)                   // 'finally'
    {
      whitespace();
      parse_exceptionGroup();
    }
    eventHandler.endNonterminal("rule", e0);
  }

  private void parse_ruleActions()
  {
    eventHandler.startNonterminal("ruleActions", e0);
    for (;;)
    {
      whitespace();
      parse_ruleAction();
      lookahead1W(33);              // WHITESPACE | ':' | '@' | '{'
      if (l1 != 38)                 // '@'
      {
        break;
      }
    }
    eventHandler.endNonterminal("ruleActions", e0);
  }

  private void try_ruleActions()
  {
    for (;;)
    {
      try_ruleAction();
      lookahead1W(33);              // WHITESPACE | ':' | '@' | '{'
      if (l1 != 38)                 // '@'
      {
        break;
      }
    }
  }

  private void parse_ruleAction()
  {
    eventHandler.startNonterminal("ruleAction", e0);
    consume(38);                    // '@'
    lookahead1W(14);                // TOKEN_REF | RULE_REF | WHITESPACE
    whitespace();
    parse_id();
    lookahead1W(12);                // WHITESPACE | '{'
    whitespace();
    parse_ACTION();
    eventHandler.endNonterminal("ruleAction", e0);
  }

  private void try_ruleAction()
  {
    consumeT(38);                   // '@'
    lookahead1W(14);                // TOKEN_REF | RULE_REF | WHITESPACE
    try_id();
    lookahead1W(12);                // WHITESPACE | '{'
    try_ACTION();
  }

  private void parse_throwsSpec()
  {
    eventHandler.startNonterminal("throwsSpec", e0);
    consume(53);                    // 'throws'
    lookahead1W(14);                // TOKEN_REF | RULE_REF | WHITESPACE
    whitespace();
    parse_id();
    for (;;)
    {
      lookahead1W(41);              // OPTIONS | WHITESPACE | ',' | ':' | '@' | 'scope'
      if (l1 != 26)                 // ','
      {
        break;
      }
      consume(26);                  // ','
      lookahead1W(14);              // TOKEN_REF | RULE_REF | WHITESPACE
      whitespace();
      parse_id();
    }
    eventHandler.endNonterminal("throwsSpec", e0);
  }

  private void parse_ruleScopeSpec()
  {
    eventHandler.startNonterminal("ruleScopeSpec", e0);
    switch (l1)
    {
    case 52:                        // 'scope'
      lookahead2W(35);              // TOKEN_REF | RULE_REF | WHITESPACE | '@' | '{'
      break;
    default:
      lk = l1;
    }
    if (lk == 2484                  // 'scope' '@'
     || lk == 3572)                 // 'scope' '{'
    {
      consume(52);                  // 'scope'
      lookahead1W(25);              // WHITESPACE | '@' | '{'
      if (l1 == 38)                 // '@'
      {
        whitespace();
        parse_ruleActions();
      }
      whitespace();
      parse_ACTION();
    }
    for (;;)
    {
      lookahead1W(32);              // WHITESPACE | ':' | '@' | 'scope'
      if (l1 != 52)                 // 'scope'
      {
        break;
      }
      consume(52);                  // 'scope'
      lookahead1W(14);              // TOKEN_REF | RULE_REF | WHITESPACE
      whitespace();
      parse_idList();
      consume(32);                  // ';'
    }
    eventHandler.endNonterminal("ruleScopeSpec", e0);
  }

  private void parse_ruleAltList()
  {
    eventHandler.startNonterminal("ruleAltList", e0);
    parse_alternative();
    whitespace();
    parse_rewrite();
    for (;;)
    {
      lookahead1W(24);              // WHITESPACE | ';' | '|'
      if (l1 != 56)                 // '|'
      {
        break;
      }
      consume(56);                  // '|'
      lookahead1W(68);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | '->' |
                                    // '.' | ';' | '^(' | '{' | '|' | '~'
      whitespace();
      parse_alternative();
      whitespace();
      parse_rewrite();
    }
    eventHandler.endNonterminal("ruleAltList", e0);
  }

  private void parse_block()
  {
    eventHandler.startNonterminal("block", e0);
    consume(21);                    // '('
    lookahead1W(75);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | OPTIONS | RULE_REF | WHITESPACE |
                                    // '(' | ')' | '->' | '.' | ':' | '@' | '^(' | '{' | '|' | '~'
    switch (l1)
    {
    case 55:                        // '{'
      lookahead2(44);               // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
      switch (lk)
      {
      case 3703:                    // '{' '}'
        lookahead3W(71);            // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | ')' |
                                    // '->' | '.' | ':' | '?' | '^(' | '{' | '|' | '~'
        break;
      case 183:                     // '{' COMMENT
      case 631:                     // '{' NESTED_ACTION_char
      case 695:                     // '{' ACTION_CHAR_LITERAL
      case 759:                     // '{' ACTION_STRING_LITERAL
      case 823:                     // '{' ACTION_ESC
      case 3575:                    // '{' '{'
        lookahead3(44);             // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
        break;
      }
      break;
    default:
      lk = l1;
    }
    if (lk != 3                     // CHAR_LITERAL
     && lk != 4                     // STRING_LITERAL
     && lk != 13                    // TOKEN_REF
     && lk != 15                    // OPTIONS
     && lk != 16                    // RULE_REF
     && lk != 21                    // '('
     && lk != 22                    // ')'
     && lk != 27                    // '->'
     && lk != 28                    // '.'
     && lk != 31                    // ':'
     && lk != 38                    // '@'
     && lk != 40                    // '^('
     && lk != 56                    // '|'
     && lk != 58                    // '~'
     && lk != 15991                 // '{' '}' CHAR_LITERAL
     && lk != 20087                 // '{' '}' STRING_LITERAL
     && lk != 56951                 // '{' '}' TOKEN_REF
     && lk != 69239                 // '{' '}' RULE_REF
     && lk != 89719                 // '{' '}' '('
     && lk != 93815                 // '{' '}' ')'
     && lk != 114295                // '{' '}' '->'
     && lk != 118391                // '{' '}' '.'
     && lk != 130679                // '{' '}' ':'
     && lk != 155255                // '{' '}' '?'
     && lk != 167543                // '{' '}' '^('
     && lk != 228983                // '{' '}' '{'
     && lk != 233079                // '{' '}' '|'
     && lk != 241271)               // '{' '}' '~'
    {
      lk = memoized(0, e0);
      if (lk == 0)
      {
        int b0A = b0; int e0A = e0; int l1A = l1;
        int b1A = b1; int e1A = e1; int l2A = l2;
        int b2A = b2; int e2A = e2; int l3A = l3;
        int b3A = b3; int e3A = e3;
        try
        {
          switch (l1)
          {
          case 55:                  // '{'
            try_ACTION();
            break;
          default:
            if (l1 == 15)           // OPTIONS
            {
              try_optionsSpec();
            }
            lookahead1W(20);        // WHITESPACE | ':' | '@'
            if (l1 == 38)           // '@'
            {
              try_ruleActions();
            }
          }
          lookahead1W(8);           // WHITESPACE | ':'
          consumeT(31);             // ':'
          lk = -1;
        }
        catch (ParseException p1A)
        {
          lk = -2;
        }
        b0 = b0A; e0 = e0A; l1 = l1A; if (l1 == 0) {end = e0A;} else {
        b1 = b1A; e1 = e1A; l2 = l2A; if (l2 == 0) {end = e1A;} else {
        b2 = b2A; e2 = e2A; l3 = l3A; if (l3 == 0) {end = e2A;} else {
        b3 = b3A; e3 = e3A; end = e3A; }}}
        memoize(0, e0, lk);
      }
    }
    if (lk == -1
     || lk == 15                    // OPTIONS
     || lk == 31                    // ':'
     || lk == 38                    // '@'
     || lk == 130679)               // '{' '}' ':'
    {
      switch (l1)
      {
      case 55:                      // '{'
        whitespace();
        parse_ACTION();
        break;
      default:
        if (l1 == 15)               // OPTIONS
        {
          whitespace();
          parse_optionsSpec();
        }
        lookahead1W(20);            // WHITESPACE | ':' | '@'
        if (l1 == 38)               // '@'
        {
          whitespace();
          parse_ruleActions();
        }
      }
      lookahead1W(8);               // WHITESPACE | ':'
      consume(31);                  // ':'
    }
    lookahead1W(67);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | ')' |
                                    // '->' | '.' | '^(' | '{' | '|' | '~'
    whitespace();
    parse_alternative();
    whitespace();
    parse_rewrite();
    for (;;)
    {
      lookahead1W(17);              // WHITESPACE | ')' | '|'
      if (l1 != 56)                 // '|'
      {
        break;
      }
      consume(56);                  // '|'
      lookahead1W(67);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | ')' |
                                    // '->' | '.' | '^(' | '{' | '|' | '~'
      whitespace();
      parse_alternative();
      whitespace();
      parse_rewrite();
    }
    consume(22);                    // ')'
    eventHandler.endNonterminal("block", e0);
  }

  private void parse_alternative()
  {
    eventHandler.startNonterminal("alternative", e0);
    for (;;)
    {
      lookahead1W(69);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | ')' |
                                    // '->' | '.' | ';' | '^(' | '{' | '|' | '~'
      if (l1 == 22                  // ')'
       || l1 == 27                  // '->'
       || l1 == 32                  // ';'
       || l1 == 56)                 // '|'
      {
        break;
      }
      whitespace();
      parse_element();
    }
    eventHandler.endNonterminal("alternative", e0);
  }

  private void parse_exceptionGroup()
  {
    eventHandler.startNonterminal("exceptionGroup", e0);
    switch (l1)
    {
    case 41:                        // 'catch'
      for (;;)
      {
        whitespace();
        parse_exceptionHandler();
        lookahead1W(59);            // DOC_COMMENT | TOKEN_REF | RULE_REF | EOF | WHITESPACE | 'catch' | 'finally' |
                                    // 'fragment' | 'private' | 'protected' | 'public'
        if (l1 != 41)               // 'catch'
        {
          break;
        }
      }
      if (l1 == 42)                 // 'finally'
      {
        whitespace();
        parse_finallyClause();
      }
      break;
    default:
      parse_finallyClause();
    }
    eventHandler.endNonterminal("exceptionGroup", e0);
  }

  private void parse_exceptionHandler()
  {
    eventHandler.startNonterminal("exceptionHandler", e0);
    consume(41);                    // 'catch'
    lookahead1W(2);                 // ARG_ACTION | WHITESPACE
    consume(8);                     // ARG_ACTION
    lookahead1W(12);                // WHITESPACE | '{'
    whitespace();
    parse_ACTION();
    eventHandler.endNonterminal("exceptionHandler", e0);
  }

  private void parse_finallyClause()
  {
    eventHandler.startNonterminal("finallyClause", e0);
    consume(42);                    // 'finally'
    lookahead1W(12);                // WHITESPACE | '{'
    whitespace();
    parse_ACTION();
    eventHandler.endNonterminal("finallyClause", e0);
  }

  private void parse_element()
  {
    eventHandler.startNonterminal("element", e0);
    parse_elementNoOptionSpec();
    eventHandler.endNonterminal("element", e0);
  }

  private void parse_elementNoOptionSpec()
  {
    eventHandler.startNonterminal("elementNoOptionSpec", e0);
    switch (l1)
    {
    case 55:                        // '{'
      lookahead2(44);               // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
      switch (lk)
      {
      case 3703:                    // '{' '}'
        lookahead3W(73);            // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | ')' |
                                    // '->' | '.' | ';' | '?' | '^(' | '{' | '|' | '~'
        break;
      case 183:                     // '{' COMMENT
      case 631:                     // '{' NESTED_ACTION_char
      case 695:                     // '{' ACTION_CHAR_LITERAL
      case 759:                     // '{' ACTION_STRING_LITERAL
      case 823:                     // '{' ACTION_ESC
      case 3575:                    // '{' '{'
        lookahead3(44);             // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
        break;
      }
      break;
    default:
      lk = l1;
    }
    if (lk != 3                     // CHAR_LITERAL
     && lk != 4                     // STRING_LITERAL
     && lk != 13                    // TOKEN_REF
     && lk != 16                    // RULE_REF
     && lk != 21                    // '('
     && lk != 28                    // '.'
     && lk != 40                    // '^('
     && lk != 58                    // '~'
     && lk != 15991                 // '{' '}' CHAR_LITERAL
     && lk != 20087                 // '{' '}' STRING_LITERAL
     && lk != 56951                 // '{' '}' TOKEN_REF
     && lk != 69239                 // '{' '}' RULE_REF
     && lk != 89719                 // '{' '}' '('
     && lk != 93815                 // '{' '}' ')'
     && lk != 114295                // '{' '}' '->'
     && lk != 118391                // '{' '}' '.'
     && lk != 134775                // '{' '}' ';'
     && lk != 155255                // '{' '}' '?'
     && lk != 167543                // '{' '}' '^('
     && lk != 228983                // '{' '}' '{'
     && lk != 233079                // '{' '}' '|'
     && lk != 241271)               // '{' '}' '~'
    {
      lk = memoized(1, e0);
      if (lk == 0)
      {
        int b0A = b0; int e0A = e0; int l1A = l1;
        int b1A = b1; int e1A = e1; int l2A = l2;
        int b2A = b2; int e2A = e2; int l3A = l3;
        int b3A = b3; int e3A = e3;
        try
        {
          try_SEMPRED();
          lookahead1W(72);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | ')' |
                                    // '->' | '.' | ';' | '=>' | '^(' | '{' | '|' | '~'
          if (l1 == 35)             // '=>'
          {
            consumeT(35);           // '=>'
          }
          lk = -3;
        }
        catch (ParseException p3A)
        {
          lk = -4;
        }
        b0 = b0A; e0 = e0A; l1 = l1A; if (l1 == 0) {end = e0A;} else {
        b1 = b1A; e1 = e1A; l2 = l2A; if (l2 == 0) {end = e1A;} else {
        b2 = b2A; e2 = e2A; l3 = l3A; if (l3 == 0) {end = e2A;} else {
        b3 = b3A; e3 = e3A; end = e3A; }}}
        memoize(1, e0, lk);
      }
    }
    switch (lk)
    {
    case 3:                         // CHAR_LITERAL
    case 4:                         // STRING_LITERAL
    case 13:                        // TOKEN_REF
    case 16:                        // RULE_REF
    case 28:                        // '.'
    case 58:                        // '~'
      switch (l1)
      {
      case 13:                      // TOKEN_REF
        lookahead2W(88);            // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | ')' | '*' | '+' | '+=' | '->' | '.' | ';' | '<' | '=' | '?' | '^' |
                                    // '^(' | '{' | '|' | '~'
        break;
      case 16:                      // RULE_REF
        lookahead2W(87);            // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | ')' | '*' | '+' | '+=' | '->' | '.' | ';' | '=' | '?' | '^' | '^(' |
                                    // '{' | '|' | '~'
        break;
      default:
        lk = l1;
      }
      switch (lk)
      {
      case 1613:                    // TOKEN_REF '+='
      case 1616:                    // RULE_REF '+='
      case 2189:                    // TOKEN_REF '='
      case 2192:                    // RULE_REF '='
        parse_id();
        lookahead1W(18);            // WHITESPACE | '+=' | '='
        switch (l1)
        {
        case 34:                    // '='
          consume(34);              // '='
          break;
        default:
          consume(25);              // '+='
        }
        lookahead1W(50);            // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | '.' |
                                    // '~'
        switch (l1)
        {
        case 21:                    // '('
          whitespace();
          parse_block();
          break;
        default:
          whitespace();
          parse_atom();
        }
        break;
      default:
        parse_atom();
      }
      lookahead1W(79);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | ')' |
                                    // '*' | '+' | '->' | '.' | ';' | '?' | '^(' | '{' | '|' | '~'
      if (l1 == 23                  // '*'
       || l1 == 24                  // '+'
       || l1 == 37)                 // '?'
      {
        whitespace();
        parse_ebnfSuffix();
      }
      break;
    case 21:                        // '('
      parse_ebnf();
      break;
    case -3:
    case 155255:                    // '{' '}' '?'
      parse_SEMPRED();
      lookahead1W(72);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | ')' |
                                    // '->' | '.' | ';' | '=>' | '^(' | '{' | '|' | '~'
      if (l1 == 35)                 // '=>'
      {
        consume(35);                // '=>'
      }
      break;
    case 40:                        // '^('
      parse_tree_();
      break;
    default:
      parse_ACTION();
    }
    eventHandler.endNonterminal("elementNoOptionSpec", e0);
  }

  private void parse_atom()
  {
    eventHandler.startNonterminal("atom", e0);
    switch (l1)
    {
    case 3:                         // CHAR_LITERAL
      lookahead2W(86);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '!' | '(' |
                                    // ')' | '*' | '+' | '->' | '.' | '..' | ';' | '<' | '?' | '^' | '^(' | '{' | '|' |
                                    // '~'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 58:                        // '~'
    case 1859:                      // CHAR_LITERAL '..'
      switch (l1)
      {
      case 3:                       // CHAR_LITERAL
        parse_range();
        break;
      default:
        parse_notSet();
      }
      lookahead1W(81);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '!' | '(' |
                                    // ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' | '~'
      if (l1 == 19                  // '!'
       || l1 == 39)                 // '^'
      {
        switch (l1)
        {
        case 39:                    // '^'
          consume(39);              // '^'
          break;
        default:
          consume(19);              // '!'
        }
      }
      break;
    default:
      switch (l1)
      {
      case 13:                      // TOKEN_REF
        lookahead2W(85);            // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | ')' | '*' | '+' | '->' | '.' | ';' | '<' | '?' | '^' | '^(' | '{' |
                                    // '|' | '~'
        switch (lk)
        {
        case 1805:                  // TOKEN_REF '.'
          lookahead3W(81);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '!' | '(' |
                                    // ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' | '~'
          break;
        }
        break;
      case 16:                      // RULE_REF
        lookahead2W(82);            // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' |
                                    // '~'
        switch (lk)
        {
        case 1808:                  // RULE_REF '.'
          lookahead3W(81);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '!' | '(' |
                                    // ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' | '~'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 14093               // TOKEN_REF '.' CHAR_LITERAL
       || lk == 14096               // RULE_REF '.' CHAR_LITERAL
       || lk == 18189               // TOKEN_REF '.' STRING_LITERAL
       || lk == 18192               // RULE_REF '.' STRING_LITERAL
       || lk == 55053               // TOKEN_REF '.' TOKEN_REF
       || lk == 55056               // RULE_REF '.' TOKEN_REF
       || lk == 67341               // TOKEN_REF '.' RULE_REF
       || lk == 67344               // RULE_REF '.' RULE_REF
       || lk == 116493              // TOKEN_REF '.' '.'
       || lk == 116496)             // RULE_REF '.' '.'
      {
        lk = memoized(2, e0);
        if (lk == 0)
        {
          int b0A = b0; int e0A = e0; int l1A = l1;
          int b1A = b1; int e1A = e1; int l2A = l2;
          int b2A = b2; int e2A = e2; int l3A = l3;
          int b3A = b3; int e3A = e3;
          try
          {
            try_id();
            lookahead1W(6);         // WHITESPACE | '.'
            consumeT(28);           // '.'
            lookahead1W(39);        // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '.'
            switch (l1)
            {
            case 16:                // RULE_REF
              try_ruleref();
              break;
            default:
              try_terminal();
            }
            lk = -1;
          }
          catch (ParseException p1A)
          {
            try
            {
              b0 = b0A; e0 = e0A; l1 = l1A; if (l1 == 0) {end = e0A;} else {
              b1 = b1A; e1 = e1A; l2 = l2A; if (l2 == 0) {end = e1A;} else {
              b2 = b2A; e2 = e2A; l3 = l3A; if (l3 == 0) {end = e2A;} else {
              b3 = b3A; e3 = e3A; end = e3A; }}}
              try_terminal();
              lk = -2;
            }
            catch (ParseException p2A)
            {
              lk = -3;
            }
          }
          b0 = b0A; e0 = e0A; l1 = l1A; if (l1 == 0) {end = e0A;} else {
          b1 = b1A; e1 = e1A; l2 = l2A; if (l2 == 0) {end = e1A;} else {
          b2 = b2A; e2 = e2A; l3 = l3A; if (l3 == 0) {end = e2A;} else {
          b3 = b3A; e3 = e3A; end = e3A; }}}
          memoize(2, e0, lk);
        }
      }
      switch (lk)
      {
      case -1:
        parse_id();
        lookahead1W(6);             // WHITESPACE | '.'
        consume(28);                // '.'
        lookahead1W(39);            // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '.'
        switch (l1)
        {
        case 16:                    // RULE_REF
          whitespace();
          parse_ruleref();
          break;
        default:
          whitespace();
          parse_terminal();
        }
        break;
      case -3:
      case 208:                     // RULE_REF CHAR_LITERAL
      case 272:                     // RULE_REF STRING_LITERAL
      case 528:                     // RULE_REF ARG_ACTION
      case 848:                     // RULE_REF TOKEN_REF
      case 1040:                    // RULE_REF RULE_REF
      case 1232:                    // RULE_REF '!'
      case 1360:                    // RULE_REF '('
      case 1424:                    // RULE_REF ')'
      case 1488:                    // RULE_REF '*'
      case 1552:                    // RULE_REF '+'
      case 1744:                    // RULE_REF '->'
      case 2064:                    // RULE_REF ';'
      case 2384:                    // RULE_REF '?'
      case 2512:                    // RULE_REF '^'
      case 2576:                    // RULE_REF '^('
      case 3536:                    // RULE_REF '{'
      case 3600:                    // RULE_REF '|'
      case 3728:                    // RULE_REF '~'
      case 79632:                   // RULE_REF '.' '!'
      case 87824:                   // RULE_REF '.' '('
      case 91920:                   // RULE_REF '.' ')'
      case 96016:                   // RULE_REF '.' '*'
      case 100112:                  // RULE_REF '.' '+'
      case 112400:                  // RULE_REF '.' '->'
      case 132880:                  // RULE_REF '.' ';'
      case 153360:                  // RULE_REF '.' '?'
      case 161552:                  // RULE_REF '.' '^'
      case 165648:                  // RULE_REF '.' '^('
      case 227088:                  // RULE_REF '.' '{'
      case 231184:                  // RULE_REF '.' '|'
      case 239376:                  // RULE_REF '.' '~'
        parse_ruleref();
        break;
      default:
        parse_terminal();
      }
    }
    eventHandler.endNonterminal("atom", e0);
  }

  private void parse_ruleref()
  {
    eventHandler.startNonterminal("ruleref", e0);
    consume(16);                    // RULE_REF
    lookahead1W(82);                // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' |
                                    // '~'
    if (l1 == 8)                    // ARG_ACTION
    {
      consume(8);                   // ARG_ACTION
    }
    lookahead1W(81);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '!' | '(' |
                                    // ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' | '~'
    if (l1 == 19                    // '!'
     || l1 == 39)                   // '^'
    {
      switch (l1)
      {
      case 39:                      // '^'
        consume(39);                // '^'
        break;
      default:
        consume(19);                // '!'
      }
    }
    eventHandler.endNonterminal("ruleref", e0);
  }

  private void try_ruleref()
  {
    consumeT(16);                   // RULE_REF
    lookahead1W(82);                // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' |
                                    // '~'
    if (l1 == 8)                    // ARG_ACTION
    {
      consumeT(8);                  // ARG_ACTION
    }
    lookahead1W(81);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '!' | '(' |
                                    // ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' | '~'
    if (l1 == 19                    // '!'
     || l1 == 39)                   // '^'
    {
      switch (l1)
      {
      case 39:                      // '^'
        consumeT(39);               // '^'
        break;
      default:
        consumeT(19);               // '!'
      }
    }
  }

  private void parse_notSet()
  {
    eventHandler.startNonterminal("notSet", e0);
    consume(58);                    // '~'
    lookahead1W(34);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | WHITESPACE | '('
    switch (l1)
    {
    case 21:                        // '('
      whitespace();
      parse_block();
      break;
    default:
      whitespace();
      parse_notTerminal();
    }
    eventHandler.endNonterminal("notSet", e0);
  }

  private void parse_treeRoot()
  {
    eventHandler.startNonterminal("treeRoot", e0);
    switch (l1)
    {
    case 13:                        // TOKEN_REF
      lookahead2W(74);              // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | '+=' | '.' | '<' | '=' | '^' | '^(' | '{' | '~'
      break;
    case 16:                        // RULE_REF
      lookahead2W(70);              // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | '+=' | '.' | '=' | '^' | '^(' | '{' | '~'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 1613:                      // TOKEN_REF '+='
    case 1616:                      // RULE_REF '+='
    case 2189:                      // TOKEN_REF '='
    case 2192:                      // RULE_REF '='
      parse_id();
      lookahead1W(18);              // WHITESPACE | '+=' | '='
      switch (l1)
      {
      case 34:                      // '='
        consume(34);                // '='
        break;
      default:
        consume(25);                // '+='
      }
      lookahead1W(50);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | '.' |
                                    // '~'
      switch (l1)
      {
      case 21:                      // '('
        whitespace();
        parse_block();
        break;
      default:
        whitespace();
        parse_atom();
      }
      break;
    case 21:                        // '('
      parse_block();
      break;
    default:
      parse_atom();
    }
    eventHandler.endNonterminal("treeRoot", e0);
  }

  private void parse_tree_()
  {
    eventHandler.startNonterminal("tree_", e0);
    consume(40);                    // '^('
    lookahead1W(50);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | '.' |
                                    // '~'
    whitespace();
    parse_treeRoot();
    for (;;)
    {
      lookahead1W(57);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | '.' |
                                    // '^(' | '{' | '~'
      whitespace();
      parse_element();
      lookahead1W(62);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '(' | ')' |
                                    // '.' | '^(' | '{' | '~'
      if (l1 == 22)                 // ')'
      {
        break;
      }
    }
    consume(22);                    // ')'
    eventHandler.endNonterminal("tree_", e0);
  }

  private void parse_ebnf()
  {
    eventHandler.startNonterminal("ebnf", e0);
    parse_block();
    lookahead1W(84);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '!' | '(' |
                                    // ')' | '*' | '+' | '->' | '.' | ';' | '=>' | '?' | '^' | '^(' | '{' | '|' | '~'
    if (l1 == 19                    // '!'
     || l1 == 23                    // '*'
     || l1 == 24                    // '+'
     || l1 == 35                    // '=>'
     || l1 == 37                    // '?'
     || l1 == 39)                   // '^'
    {
      switch (l1)
      {
      case 37:                      // '?'
        consume(37);                // '?'
        break;
      case 23:                      // '*'
        consume(23);                // '*'
        break;
      case 24:                      // '+'
        consume(24);                // '+'
        break;
      case 35:                      // '=>'
        consume(35);                // '=>'
        break;
      case 39:                      // '^'
        consume(39);                // '^'
        break;
      default:
        consume(19);                // '!'
      }
    }
    eventHandler.endNonterminal("ebnf", e0);
  }

  private void parse_range()
  {
    eventHandler.startNonterminal("range", e0);
    consume(3);                     // CHAR_LITERAL
    lookahead1W(7);                 // WHITESPACE | '..'
    consume(29);                    // '..'
    lookahead1W(1);                 // CHAR_LITERAL | WHITESPACE
    consume(3);                     // CHAR_LITERAL
    eventHandler.endNonterminal("range", e0);
  }

  private void parse_terminal()
  {
    eventHandler.startNonterminal("terminal", e0);
    switch (l1)
    {
    case 13:                        // TOKEN_REF
      consume(13);                  // TOKEN_REF
      lookahead1W(85);              // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | ')' | '*' | '+' | '->' | '.' | ';' | '<' | '?' | '^' | '^(' | '{' |
                                    // '|' | '~'
      if (l1 == 33)                 // '<'
      {
        whitespace();
        parse_elementOptions();
      }
      lookahead1W(82);              // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' |
                                    // '~'
      if (l1 == 8)                  // ARG_ACTION
      {
        consume(8);                 // ARG_ACTION
      }
      break;
    case 28:                        // '.'
      consume(28);                  // '.'
      break;
    default:
      switch (l1)
      {
      case 3:                       // CHAR_LITERAL
        consume(3);                 // CHAR_LITERAL
        break;
      default:
        consume(4);                 // STRING_LITERAL
      }
      lookahead1W(83);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '!' | '(' |
                                    // ')' | '*' | '+' | '->' | '.' | ';' | '<' | '?' | '^' | '^(' | '{' | '|' | '~'
      if (l1 == 33)                 // '<'
      {
        whitespace();
        parse_elementOptions();
      }
    }
    lookahead1W(81);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '!' | '(' |
                                    // ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' | '~'
    if (l1 == 19                    // '!'
     || l1 == 39)                   // '^'
    {
      switch (l1)
      {
      case 39:                      // '^'
        consume(39);                // '^'
        break;
      default:
        consume(19);                // '!'
      }
    }
    eventHandler.endNonterminal("terminal", e0);
  }

  private void try_terminal()
  {
    switch (l1)
    {
    case 13:                        // TOKEN_REF
      consumeT(13);                 // TOKEN_REF
      lookahead1W(85);              // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | ')' | '*' | '+' | '->' | '.' | ';' | '<' | '?' | '^' | '^(' | '{' |
                                    // '|' | '~'
      if (l1 == 33)                 // '<'
      {
        try_elementOptions();
      }
      lookahead1W(82);              // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '!' | '(' | ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' |
                                    // '~'
      if (l1 == 8)                  // ARG_ACTION
      {
        consumeT(8);                // ARG_ACTION
      }
      break;
    case 28:                        // '.'
      consumeT(28);                 // '.'
      break;
    default:
      switch (l1)
      {
      case 3:                       // CHAR_LITERAL
        consumeT(3);                // CHAR_LITERAL
        break;
      default:
        consumeT(4);                // STRING_LITERAL
      }
      lookahead1W(83);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '!' | '(' |
                                    // ')' | '*' | '+' | '->' | '.' | ';' | '<' | '?' | '^' | '^(' | '{' | '|' | '~'
      if (l1 == 33)                 // '<'
      {
        try_elementOptions();
      }
    }
    lookahead1W(81);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '!' | '(' |
                                    // ')' | '*' | '+' | '->' | '.' | ';' | '?' | '^' | '^(' | '{' | '|' | '~'
    if (l1 == 19                    // '!'
     || l1 == 39)                   // '^'
    {
      switch (l1)
      {
      case 39:                      // '^'
        consumeT(39);               // '^'
        break;
      default:
        consumeT(19);               // '!'
      }
    }
  }

  private void parse_elementOptions()
  {
    eventHandler.startNonterminal("elementOptions", e0);
    consume(33);                    // '<'
    lookahead1W(14);                // TOKEN_REF | RULE_REF | WHITESPACE
    switch (l1)
    {
    case 13:                        // TOKEN_REF
    case 16:                        // RULE_REF
      lookahead2W(31);              // WHITESPACE | '.' | '=' | '>'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 2189:                      // TOKEN_REF '='
    case 2192:                      // RULE_REF '='
      whitespace();
      parse_elementOption();
      for (;;)
      {
        lookahead1W(23);            // WHITESPACE | ';' | '>'
        if (l1 != 32)               // ';'
        {
          break;
        }
        consume(32);                // ';'
        lookahead1W(14);            // TOKEN_REF | RULE_REF | WHITESPACE
        whitespace();
        parse_elementOption();
      }
      break;
    default:
      whitespace();
      parse_defaultNodeOption();
    }
    consume(36);                    // '>'
    eventHandler.endNonterminal("elementOptions", e0);
  }

  private void try_elementOptions()
  {
    consumeT(33);                   // '<'
    lookahead1W(14);                // TOKEN_REF | RULE_REF | WHITESPACE
    switch (l1)
    {
    case 13:                        // TOKEN_REF
    case 16:                        // RULE_REF
      lookahead2W(31);              // WHITESPACE | '.' | '=' | '>'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 2189:                      // TOKEN_REF '='
    case 2192:                      // RULE_REF '='
      try_elementOption();
      for (;;)
      {
        lookahead1W(23);            // WHITESPACE | ';' | '>'
        if (l1 != 32)               // ';'
        {
          break;
        }
        consumeT(32);               // ';'
        lookahead1W(14);            // TOKEN_REF | RULE_REF | WHITESPACE
        try_elementOption();
      }
      break;
    default:
      try_defaultNodeOption();
    }
    consumeT(36);                   // '>'
  }

  private void parse_defaultNodeOption()
  {
    eventHandler.startNonterminal("defaultNodeOption", e0);
    parse_elementOptionId();
    eventHandler.endNonterminal("defaultNodeOption", e0);
  }

  private void try_defaultNodeOption()
  {
    try_elementOptionId();
  }

  private void parse_elementOption()
  {
    eventHandler.startNonterminal("elementOption", e0);
    parse_id();
    lookahead1W(10);                // WHITESPACE | '='
    consume(34);                    // '='
    lookahead1W(40);                // STRING_LITERAL | DOUBLE_QUOTE_STRING_LITERAL | DOUBLE_ANGLE_STRING_LITERAL |
                                    // TOKEN_REF | RULE_REF | WHITESPACE
    switch (l1)
    {
    case 13:                        // TOKEN_REF
    case 16:                        // RULE_REF
      whitespace();
      parse_elementOptionId();
      break;
    default:
      switch (l1)
      {
      case 4:                       // STRING_LITERAL
        consume(4);                 // STRING_LITERAL
        break;
      case 5:                       // DOUBLE_QUOTE_STRING_LITERAL
        consume(5);                 // DOUBLE_QUOTE_STRING_LITERAL
        break;
      default:
        consume(6);                 // DOUBLE_ANGLE_STRING_LITERAL
      }
    }
    eventHandler.endNonterminal("elementOption", e0);
  }

  private void try_elementOption()
  {
    try_id();
    lookahead1W(10);                // WHITESPACE | '='
    consumeT(34);                   // '='
    lookahead1W(40);                // STRING_LITERAL | DOUBLE_QUOTE_STRING_LITERAL | DOUBLE_ANGLE_STRING_LITERAL |
                                    // TOKEN_REF | RULE_REF | WHITESPACE
    switch (l1)
    {
    case 13:                        // TOKEN_REF
    case 16:                        // RULE_REF
      try_elementOptionId();
      break;
    default:
      switch (l1)
      {
      case 4:                       // STRING_LITERAL
        consumeT(4);                // STRING_LITERAL
        break;
      case 5:                       // DOUBLE_QUOTE_STRING_LITERAL
        consumeT(5);                // DOUBLE_QUOTE_STRING_LITERAL
        break;
      default:
        consumeT(6);                // DOUBLE_ANGLE_STRING_LITERAL
      }
    }
  }

  private void parse_elementOptionId()
  {
    eventHandler.startNonterminal("elementOptionId", e0);
    parse_id();
    for (;;)
    {
      lookahead1W(30);              // WHITESPACE | '.' | ';' | '>'
      if (l1 != 28)                 // '.'
      {
        break;
      }
      consume(28);                  // '.'
      lookahead1W(14);              // TOKEN_REF | RULE_REF | WHITESPACE
      whitespace();
      parse_id();
    }
    eventHandler.endNonterminal("elementOptionId", e0);
  }

  private void try_elementOptionId()
  {
    try_id();
    for (;;)
    {
      lookahead1W(30);              // WHITESPACE | '.' | ';' | '>'
      if (l1 != 28)                 // '.'
      {
        break;
      }
      consumeT(28);                 // '.'
      lookahead1W(14);              // TOKEN_REF | RULE_REF | WHITESPACE
      try_id();
    }
  }

  private void parse_ebnfSuffix()
  {
    eventHandler.startNonterminal("ebnfSuffix", e0);
    switch (l1)
    {
    case 37:                        // '?'
      consume(37);                  // '?'
      break;
    case 23:                        // '*'
      consume(23);                  // '*'
      break;
    default:
      consume(24);                  // '+'
    }
    eventHandler.endNonterminal("ebnfSuffix", e0);
  }

  private void try_ebnfSuffix()
  {
    switch (l1)
    {
    case 37:                        // '?'
      consumeT(37);                 // '?'
      break;
    case 23:                        // '*'
      consumeT(23);                 // '*'
      break;
    default:
      consumeT(24);                 // '+'
    }
  }

  private void parse_notTerminal()
  {
    eventHandler.startNonterminal("notTerminal", e0);
    switch (l1)
    {
    case 3:                         // CHAR_LITERAL
      consume(3);                   // CHAR_LITERAL
      break;
    case 13:                        // TOKEN_REF
      consume(13);                  // TOKEN_REF
      break;
    default:
      consume(4);                   // STRING_LITERAL
    }
    eventHandler.endNonterminal("notTerminal", e0);
  }

  private void parse_idList()
  {
    eventHandler.startNonterminal("idList", e0);
    parse_id();
    for (;;)
    {
      lookahead1W(19);              // WHITESPACE | ',' | ';'
      if (l1 != 26)                 // ','
      {
        break;
      }
      consume(26);                  // ','
      lookahead1W(14);              // TOKEN_REF | RULE_REF | WHITESPACE
      whitespace();
      parse_id();
    }
    eventHandler.endNonterminal("idList", e0);
  }

  private void parse_id()
  {
    eventHandler.startNonterminal("id", e0);
    switch (l1)
    {
    case 13:                        // TOKEN_REF
      consume(13);                  // TOKEN_REF
      break;
    default:
      consume(16);                  // RULE_REF
    }
    eventHandler.endNonterminal("id", e0);
  }

  private void try_id()
  {
    switch (l1)
    {
    case 13:                        // TOKEN_REF
      consumeT(13);                 // TOKEN_REF
      break;
    default:
      consumeT(16);                 // RULE_REF
    }
  }

  private void parse_rewrite()
  {
    eventHandler.startNonterminal("rewrite", e0);
    if (l1 == 27)                   // '->'
    {
      for (;;)
      {
        lookahead1W(5);             // WHITESPACE | '->'
        switch (l1)
        {
        case 27:                    // '->'
          lookahead2W(66);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '...' | ';' | '^(' | '{' | '|'
          switch (lk)
          {
          case 3547:                // '->' '{'
            lookahead3(44);         // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
            break;
          }
          break;
        default:
          lk = l1;
        }
        if (lk == 11739             // '->' '{' COMMENT
         || lk == 40411             // '->' '{' NESTED_ACTION_char
         || lk == 44507             // '->' '{' ACTION_CHAR_LITERAL
         || lk == 48603             // '->' '{' ACTION_STRING_LITERAL
         || lk == 52699             // '->' '{' ACTION_ESC
         || lk == 228827            // '->' '{' '{'
         || lk == 237019)           // '->' '{' '}'
        {
          lk = memoized(3, e0);
          if (lk == 0)
          {
            int b0A = b0; int e0A = e0; int l1A = l1;
            int b1A = b1; int e1A = e1; int l2A = l2;
            int b2A = b2; int e2A = e2; int l3A = l3;
            int b3A = b3; int e3A = e3;
            try
            {
              try_rewrite_with_sempred();
              lk = -1;
            }
            catch (ParseException p1A)
            {
              lk = -2;
            }
            b0 = b0A; e0 = e0A; l1 = l1A; if (l1 == 0) {end = e0A;} else {
            b1 = b1A; e1 = e1A; l2 = l2A; if (l2 == 0) {end = e1A;} else {
            b2 = b2A; e2 = e2A; l3 = l3A; if (l3 == 0) {end = e2A;} else {
            b3 = b3A; e3 = e3A; end = e3A; }}}
            memoize(3, e0, lk);
          }
        }
        if (lk != -1)
        {
          break;
        }
        whitespace();
        parse_rewrite_with_sempred();
      }
      consume(27);                  // '->'
      lookahead1W(66);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '...' | ';' | '^(' | '{' | '|'
      whitespace();
      parse_rewrite_alternative();
    }
    eventHandler.endNonterminal("rewrite", e0);
  }

  private void parse_rewrite_with_sempred()
  {
    eventHandler.startNonterminal("rewrite_with_sempred", e0);
    consume(27);                    // '->'
    lookahead1W(12);                // WHITESPACE | '{'
    whitespace();
    parse_SEMPRED();
    lookahead1W(61);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // '->' | '...' | '^(' | '{'
    whitespace();
    parse_rewrite_alternative();
    eventHandler.endNonterminal("rewrite_with_sempred", e0);
  }

  private void try_rewrite_with_sempred()
  {
    consumeT(27);                   // '->'
    lookahead1W(12);                // WHITESPACE | '{'
    try_SEMPRED();
    lookahead1W(61);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // '->' | '...' | '^(' | '{'
    try_rewrite_alternative();
  }

  private void parse_rewrite_block()
  {
    eventHandler.startNonterminal("rewrite_block", e0);
    consume(21);                    // '('
    lookahead1W(60);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '...' | '^(' | '{'
    whitespace();
    parse_rewrite_alternative();
    lookahead1W(4);                 // WHITESPACE | ')'
    consume(22);                    // ')'
    eventHandler.endNonterminal("rewrite_block", e0);
  }

  private void try_rewrite_block()
  {
    consumeT(21);                   // '('
    lookahead1W(60);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '...' | '^(' | '{'
    try_rewrite_alternative();
    lookahead1W(4);                 // WHITESPACE | ')'
    consumeT(22);                   // ')'
  }

  private void parse_rewrite_alternative()
  {
    eventHandler.startNonterminal("rewrite_alternative", e0);
    if (l1 != 22                    // ')'
     && l1 != 27                    // '->'
     && l1 != 32                    // ';'
     && l1 != 56)                   // '|'
    {
      switch (l1)
      {
      case 13:                      // TOKEN_REF
        lookahead2W(80);            // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '$' | '(' | ')' | '*' | '+' | '->' | ';' | '<' | '?' | '^(' | '{' | '|'
        switch (lk)
        {
        case 1357:                  // TOKEN_REF '('
          lookahead3W(60);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '...' | '^(' | '{'
          break;
        }
        break;
      case 16:                      // RULE_REF
        lookahead2W(76);            // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '*' | '+' | '->' | ';' | '?' | '^(' | '{' | '|'
        switch (lk)
        {
        case 1360:                  // RULE_REF '('
          lookahead3W(60);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '...' | '^(' | '{'
          break;
        }
        break;
      case 21:                      // '('
        lookahead2W(60);            // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '...' | '^(' | '{'
        switch (lk)
        {
        case 3541:                  // '(' '{'
          lookahead3(44);           // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
          break;
        }
        break;
      case 55:                      // '{'
        lookahead2(44);             // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
        switch (lk)
        {
        case 3703:                  // '{' '}'
          lookahead3W(76);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '*' | '+' | '->' | ';' | '?' | '^(' | '{' | '|'
          break;
        case 183:                   // '{' COMMENT
        case 631:                   // '{' NESTED_ACTION_char
        case 695:                   // '{' ACTION_CHAR_LITERAL
        case 759:                   // '{' ACTION_STRING_LITERAL
        case 823:                   // '{' ACTION_ESC
        case 3575:                  // '{' '{'
          lookahead3(44);           // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 8375                // '{' COMMENT COMMENT
       || lk == 8823                // '{' NESTED_ACTION_char COMMENT
       || lk == 8887                // '{' ACTION_CHAR_LITERAL COMMENT
       || lk == 8951                // '{' ACTION_STRING_LITERAL COMMENT
       || lk == 9015                // '{' ACTION_ESC COMMENT
       || lk == 11733               // '(' '{' COMMENT
       || lk == 11767               // '{' '{' COMMENT
       || lk == 37047               // '{' COMMENT NESTED_ACTION_char
       || lk == 37495               // '{' NESTED_ACTION_char NESTED_ACTION_char
       || lk == 37559               // '{' ACTION_CHAR_LITERAL NESTED_ACTION_char
       || lk == 37623               // '{' ACTION_STRING_LITERAL NESTED_ACTION_char
       || lk == 37687               // '{' ACTION_ESC NESTED_ACTION_char
       || lk == 40405               // '(' '{' NESTED_ACTION_char
       || lk == 40439               // '{' '{' NESTED_ACTION_char
       || lk == 41143               // '{' COMMENT ACTION_CHAR_LITERAL
       || lk == 41591               // '{' NESTED_ACTION_char ACTION_CHAR_LITERAL
       || lk == 41655               // '{' ACTION_CHAR_LITERAL ACTION_CHAR_LITERAL
       || lk == 41719               // '{' ACTION_STRING_LITERAL ACTION_CHAR_LITERAL
       || lk == 41783               // '{' ACTION_ESC ACTION_CHAR_LITERAL
       || lk == 44501               // '(' '{' ACTION_CHAR_LITERAL
       || lk == 44535               // '{' '{' ACTION_CHAR_LITERAL
       || lk == 45239               // '{' COMMENT ACTION_STRING_LITERAL
       || lk == 45687               // '{' NESTED_ACTION_char ACTION_STRING_LITERAL
       || lk == 45751               // '{' ACTION_CHAR_LITERAL ACTION_STRING_LITERAL
       || lk == 45815               // '{' ACTION_STRING_LITERAL ACTION_STRING_LITERAL
       || lk == 45879               // '{' ACTION_ESC ACTION_STRING_LITERAL
       || lk == 48597               // '(' '{' ACTION_STRING_LITERAL
       || lk == 48631               // '{' '{' ACTION_STRING_LITERAL
       || lk == 49335               // '{' COMMENT ACTION_ESC
       || lk == 49783               // '{' NESTED_ACTION_char ACTION_ESC
       || lk == 49847               // '{' ACTION_CHAR_LITERAL ACTION_ESC
       || lk == 49911               // '{' ACTION_STRING_LITERAL ACTION_ESC
       || lk == 49975               // '{' ACTION_ESC ACTION_ESC
       || lk == 52693               // '(' '{' ACTION_ESC
       || lk == 52727               // '{' '{' ACTION_ESC
       || lk == 54605               // TOKEN_REF '(' TOKEN_REF
       || lk == 54608               // RULE_REF '(' TOKEN_REF
       || lk == 66893               // TOKEN_REF '(' RULE_REF
       || lk == 66896               // RULE_REF '(' RULE_REF
       || lk == 91469               // TOKEN_REF '(' ')'
       || lk == 91472               // RULE_REF '(' ')'
       || lk == 93815               // '{' '}' ')'
       || lk == 114295              // '{' '}' '->'
       || lk == 134775              // '{' '}' ';'
       || lk == 225463              // '{' COMMENT '{'
       || lk == 225911              // '{' NESTED_ACTION_char '{'
       || lk == 225975              // '{' ACTION_CHAR_LITERAL '{'
       || lk == 226039              // '{' ACTION_STRING_LITERAL '{'
       || lk == 226103              // '{' ACTION_ESC '{'
       || lk == 228821              // '(' '{' '{'
       || lk == 228855              // '{' '{' '{'
       || lk == 233079              // '{' '}' '|'
       || lk == 233655              // '{' COMMENT '}'
       || lk == 234103              // '{' NESTED_ACTION_char '}'
       || lk == 234167              // '{' ACTION_CHAR_LITERAL '}'
       || lk == 234231              // '{' ACTION_STRING_LITERAL '}'
       || lk == 234295              // '{' ACTION_ESC '}'
       || lk == 237013              // '(' '{' '}'
       || lk == 237047)             // '{' '{' '}'
      {
        lk = memoized(4, e0);
        if (lk == 0)
        {
          int b0A = b0; int e0A = e0; int l1A = l1;
          int b1A = b1; int e1A = e1; int l2A = l2;
          int b2A = b2; int e2A = e2; int l3A = l3;
          int b3A = b3; int e3A = e3;
          try
          {
            for (;;)
            {
              try_rewrite_element();
              lookahead1W(65);      // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '->' | ';' | '^(' | '{' | '|'
              if (l1 == 22          // ')'
               || l1 == 27          // '->'
               || l1 == 32          // ';'
               || l1 == 56)         // '|'
              {
                break;
              }
            }
            lk = -1;
          }
          catch (ParseException p1A)
          {
            lk = -2;
          }
          b0 = b0A; e0 = e0A; l1 = l1A; if (l1 == 0) {end = e0A;} else {
          b1 = b1A; e1 = e1A; l2 = l2A; if (l2 == 0) {end = e1A;} else {
          b2 = b2A; e2 = e2A; l3 = l3A; if (l3 == 0) {end = e2A;} else {
          b3 = b3A; e3 = e3A; end = e3A; }}}
          memoize(4, e0, lk);
        }
      }
      switch (lk)
      {
      case -2:
        whitespace();
        parse_rewrite_template();
        break;
      case 30:                      // '...'
        consume(30);                // '...'
        break;
      default:
        for (;;)
        {
          whitespace();
          parse_rewrite_element();
          lookahead1W(65);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '->' | ';' | '^(' | '{' | '|'
          if (l1 == 22              // ')'
           || l1 == 27              // '->'
           || l1 == 32              // ';'
           || l1 == 56)             // '|'
          {
            break;
          }
        }
      }
    }
    eventHandler.endNonterminal("rewrite_alternative", e0);
  }

  private void try_rewrite_alternative()
  {
    if (l1 != 22                    // ')'
     && l1 != 27                    // '->'
     && l1 != 32                    // ';'
     && l1 != 56)                   // '|'
    {
      switch (l1)
      {
      case 13:                      // TOKEN_REF
        lookahead2W(80);            // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '$' | '(' | ')' | '*' | '+' | '->' | ';' | '<' | '?' | '^(' | '{' | '|'
        switch (lk)
        {
        case 1357:                  // TOKEN_REF '('
          lookahead3W(60);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '...' | '^(' | '{'
          break;
        }
        break;
      case 16:                      // RULE_REF
        lookahead2W(76);            // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '*' | '+' | '->' | ';' | '?' | '^(' | '{' | '|'
        switch (lk)
        {
        case 1360:                  // RULE_REF '('
          lookahead3W(60);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '...' | '^(' | '{'
          break;
        }
        break;
      case 21:                      // '('
        lookahead2W(60);            // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '...' | '^(' | '{'
        switch (lk)
        {
        case 3541:                  // '(' '{'
          lookahead3(44);           // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
          break;
        }
        break;
      case 55:                      // '{'
        lookahead2(44);             // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
        switch (lk)
        {
        case 3703:                  // '{' '}'
          lookahead3W(76);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '*' | '+' | '->' | ';' | '?' | '^(' | '{' | '|'
          break;
        case 183:                   // '{' COMMENT
        case 631:                   // '{' NESTED_ACTION_char
        case 695:                   // '{' ACTION_CHAR_LITERAL
        case 759:                   // '{' ACTION_STRING_LITERAL
        case 823:                   // '{' ACTION_ESC
        case 3575:                  // '{' '{'
          lookahead3(44);           // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 8375                // '{' COMMENT COMMENT
       || lk == 8823                // '{' NESTED_ACTION_char COMMENT
       || lk == 8887                // '{' ACTION_CHAR_LITERAL COMMENT
       || lk == 8951                // '{' ACTION_STRING_LITERAL COMMENT
       || lk == 9015                // '{' ACTION_ESC COMMENT
       || lk == 11733               // '(' '{' COMMENT
       || lk == 11767               // '{' '{' COMMENT
       || lk == 37047               // '{' COMMENT NESTED_ACTION_char
       || lk == 37495               // '{' NESTED_ACTION_char NESTED_ACTION_char
       || lk == 37559               // '{' ACTION_CHAR_LITERAL NESTED_ACTION_char
       || lk == 37623               // '{' ACTION_STRING_LITERAL NESTED_ACTION_char
       || lk == 37687               // '{' ACTION_ESC NESTED_ACTION_char
       || lk == 40405               // '(' '{' NESTED_ACTION_char
       || lk == 40439               // '{' '{' NESTED_ACTION_char
       || lk == 41143               // '{' COMMENT ACTION_CHAR_LITERAL
       || lk == 41591               // '{' NESTED_ACTION_char ACTION_CHAR_LITERAL
       || lk == 41655               // '{' ACTION_CHAR_LITERAL ACTION_CHAR_LITERAL
       || lk == 41719               // '{' ACTION_STRING_LITERAL ACTION_CHAR_LITERAL
       || lk == 41783               // '{' ACTION_ESC ACTION_CHAR_LITERAL
       || lk == 44501               // '(' '{' ACTION_CHAR_LITERAL
       || lk == 44535               // '{' '{' ACTION_CHAR_LITERAL
       || lk == 45239               // '{' COMMENT ACTION_STRING_LITERAL
       || lk == 45687               // '{' NESTED_ACTION_char ACTION_STRING_LITERAL
       || lk == 45751               // '{' ACTION_CHAR_LITERAL ACTION_STRING_LITERAL
       || lk == 45815               // '{' ACTION_STRING_LITERAL ACTION_STRING_LITERAL
       || lk == 45879               // '{' ACTION_ESC ACTION_STRING_LITERAL
       || lk == 48597               // '(' '{' ACTION_STRING_LITERAL
       || lk == 48631               // '{' '{' ACTION_STRING_LITERAL
       || lk == 49335               // '{' COMMENT ACTION_ESC
       || lk == 49783               // '{' NESTED_ACTION_char ACTION_ESC
       || lk == 49847               // '{' ACTION_CHAR_LITERAL ACTION_ESC
       || lk == 49911               // '{' ACTION_STRING_LITERAL ACTION_ESC
       || lk == 49975               // '{' ACTION_ESC ACTION_ESC
       || lk == 52693               // '(' '{' ACTION_ESC
       || lk == 52727               // '{' '{' ACTION_ESC
       || lk == 54605               // TOKEN_REF '(' TOKEN_REF
       || lk == 54608               // RULE_REF '(' TOKEN_REF
       || lk == 66893               // TOKEN_REF '(' RULE_REF
       || lk == 66896               // RULE_REF '(' RULE_REF
       || lk == 91469               // TOKEN_REF '(' ')'
       || lk == 91472               // RULE_REF '(' ')'
       || lk == 93815               // '{' '}' ')'
       || lk == 114295              // '{' '}' '->'
       || lk == 134775              // '{' '}' ';'
       || lk == 225463              // '{' COMMENT '{'
       || lk == 225911              // '{' NESTED_ACTION_char '{'
       || lk == 225975              // '{' ACTION_CHAR_LITERAL '{'
       || lk == 226039              // '{' ACTION_STRING_LITERAL '{'
       || lk == 226103              // '{' ACTION_ESC '{'
       || lk == 228821              // '(' '{' '{'
       || lk == 228855              // '{' '{' '{'
       || lk == 233079              // '{' '}' '|'
       || lk == 233655              // '{' COMMENT '}'
       || lk == 234103              // '{' NESTED_ACTION_char '}'
       || lk == 234167              // '{' ACTION_CHAR_LITERAL '}'
       || lk == 234231              // '{' ACTION_STRING_LITERAL '}'
       || lk == 234295              // '{' ACTION_ESC '}'
       || lk == 237013              // '(' '{' '}'
       || lk == 237047)             // '{' '{' '}'
      {
        lk = memoized(4, e0);
        if (lk == 0)
        {
          int b0A = b0; int e0A = e0; int l1A = l1;
          int b1A = b1; int e1A = e1; int l2A = l2;
          int b2A = b2; int e2A = e2; int l3A = l3;
          int b3A = b3; int e3A = e3;
          try
          {
            for (;;)
            {
              try_rewrite_element();
              lookahead1W(65);      // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '->' | ';' | '^(' | '{' | '|'
              if (l1 == 22          // ')'
               || l1 == 27          // '->'
               || l1 == 32          // ';'
               || l1 == 56)         // '|'
              {
                break;
              }
            }
            memoize(4, e0A, -1);
            lk = -4;
          }
          catch (ParseException p1A)
          {
            lk = -2;
            b0 = b0A; e0 = e0A; l1 = l1A; if (l1 == 0) {end = e0A;} else {
            b1 = b1A; e1 = e1A; l2 = l2A; if (l2 == 0) {end = e1A;} else {
            b2 = b2A; e2 = e2A; l3 = l3A; if (l3 == 0) {end = e2A;} else {
            b3 = b3A; e3 = e3A; end = e3A; }}}
            memoize(4, e0A, -2);
          }
        }
      }
      switch (lk)
      {
      case -2:
        try_rewrite_template();
        break;
      case 30:                      // '...'
        consumeT(30);               // '...'
        break;
      case -4:
        break;
      default:
        for (;;)
        {
          try_rewrite_element();
          lookahead1W(65);          // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '->' | ';' | '^(' | '{' | '|'
          if (l1 == 22              // ')'
           || l1 == 27              // '->'
           || l1 == 32              // ';'
           || l1 == 56)             // '|'
          {
            break;
          }
        }
      }
    }
  }

  private void parse_rewrite_element()
  {
    eventHandler.startNonterminal("rewrite_element", e0);
    switch (l1)
    {
    case 21:                        // '('
      parse_rewrite_ebnf();
      break;
    default:
      switch (l1)
      {
      case 40:                      // '^('
        parse_rewrite_tree();
        break;
      default:
        parse_rewrite_atom();
      }
      lookahead1W(76);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '*' | '+' | '->' | ';' | '?' | '^(' | '{' | '|'
      if (l1 == 23                  // '*'
       || l1 == 24                  // '+'
       || l1 == 37)                 // '?'
      {
        whitespace();
        parse_ebnfSuffix();
      }
    }
    eventHandler.endNonterminal("rewrite_element", e0);
  }

  private void try_rewrite_element()
  {
    switch (l1)
    {
    case 21:                        // '('
      try_rewrite_ebnf();
      break;
    default:
      switch (l1)
      {
      case 40:                      // '^('
        try_rewrite_tree();
        break;
      default:
        try_rewrite_atom();
      }
      lookahead1W(76);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '*' | '+' | '->' | ';' | '?' | '^(' | '{' | '|'
      if (l1 == 23                  // '*'
       || l1 == 24                  // '+'
       || l1 == 37)                 // '?'
      {
        try_ebnfSuffix();
      }
    }
  }

  private void parse_rewrite_atom()
  {
    eventHandler.startNonterminal("rewrite_atom", e0);
    switch (l1)
    {
    case 13:                        // TOKEN_REF
      consume(13);                  // TOKEN_REF
      lookahead1W(80);              // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '$' | '(' | ')' | '*' | '+' | '->' | ';' | '<' | '?' | '^(' | '{' | '|'
      if (l1 == 33)                 // '<'
      {
        whitespace();
        parse_elementOptions();
      }
      lookahead1W(77);              // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '$' | '(' | ')' | '*' | '+' | '->' | ';' | '?' | '^(' | '{' | '|'
      if (l1 == 8)                  // ARG_ACTION
      {
        consume(8);                 // ARG_ACTION
      }
      break;
    case 16:                        // RULE_REF
      consume(16);                  // RULE_REF
      break;
    case 20:                        // '$'
      consume(20);                  // '$'
      lookahead1W(14);              // TOKEN_REF | RULE_REF | WHITESPACE
      whitespace();
      parse_label();
      break;
    case 55:                        // '{'
      parse_ACTION();
      break;
    default:
      switch (l1)
      {
      case 3:                       // CHAR_LITERAL
        consume(3);                 // CHAR_LITERAL
        break;
      default:
        consume(4);                 // STRING_LITERAL
      }
      lookahead1W(78);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '*' | '+' | '->' | ';' | '<' | '?' | '^(' | '{' | '|'
      if (l1 == 33)                 // '<'
      {
        whitespace();
        parse_elementOptions();
      }
    }
    eventHandler.endNonterminal("rewrite_atom", e0);
  }

  private void try_rewrite_atom()
  {
    switch (l1)
    {
    case 13:                        // TOKEN_REF
      consumeT(13);                 // TOKEN_REF
      lookahead1W(80);              // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '$' | '(' | ')' | '*' | '+' | '->' | ';' | '<' | '?' | '^(' | '{' | '|'
      if (l1 == 33)                 // '<'
      {
        try_elementOptions();
      }
      lookahead1W(77);              // CHAR_LITERAL | STRING_LITERAL | ARG_ACTION | TOKEN_REF | RULE_REF | WHITESPACE |
                                    // '$' | '(' | ')' | '*' | '+' | '->' | ';' | '?' | '^(' | '{' | '|'
      if (l1 == 8)                  // ARG_ACTION
      {
        consumeT(8);                // ARG_ACTION
      }
      break;
    case 16:                        // RULE_REF
      consumeT(16);                 // RULE_REF
      break;
    case 20:                        // '$'
      consumeT(20);                 // '$'
      lookahead1W(14);              // TOKEN_REF | RULE_REF | WHITESPACE
      try_label();
      break;
    case 55:                        // '{'
      try_ACTION();
      break;
    default:
      switch (l1)
      {
      case 3:                       // CHAR_LITERAL
        consumeT(3);                // CHAR_LITERAL
        break;
      default:
        consumeT(4);                // STRING_LITERAL
      }
      lookahead1W(78);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '*' | '+' | '->' | ';' | '<' | '?' | '^(' | '{' | '|'
      if (l1 == 33)                 // '<'
      {
        try_elementOptions();
      }
    }
  }

  private void parse_label()
  {
    eventHandler.startNonterminal("label", e0);
    switch (l1)
    {
    case 13:                        // TOKEN_REF
      consume(13);                  // TOKEN_REF
      break;
    default:
      consume(16);                  // RULE_REF
    }
    eventHandler.endNonterminal("label", e0);
  }

  private void try_label()
  {
    switch (l1)
    {
    case 13:                        // TOKEN_REF
      consumeT(13);                 // TOKEN_REF
      break;
    default:
      consumeT(16);                 // RULE_REF
    }
  }

  private void parse_rewrite_ebnf()
  {
    eventHandler.startNonterminal("rewrite_ebnf", e0);
    parse_rewrite_block();
    lookahead1W(28);                // WHITESPACE | '*' | '+' | '?'
    switch (l1)
    {
    case 37:                        // '?'
      consume(37);                  // '?'
      break;
    case 23:                        // '*'
      consume(23);                  // '*'
      break;
    default:
      consume(24);                  // '+'
    }
    eventHandler.endNonterminal("rewrite_ebnf", e0);
  }

  private void try_rewrite_ebnf()
  {
    try_rewrite_block();
    lookahead1W(28);                // WHITESPACE | '*' | '+' | '?'
    switch (l1)
    {
    case 37:                        // '?'
      consumeT(37);                 // '?'
      break;
    case 23:                        // '*'
      consumeT(23);                 // '*'
      break;
    default:
      consumeT(24);                 // '+'
    }
  }

  private void parse_rewrite_tree()
  {
    eventHandler.startNonterminal("rewrite_tree", e0);
    consume(40);                    // '^('
    lookahead1W(46);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '{'
    whitespace();
    parse_rewrite_atom();
    for (;;)
    {
      lookahead1W(56);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '^(' | '{'
      if (l1 == 22)                 // ')'
      {
        break;
      }
      whitespace();
      parse_rewrite_element();
    }
    consume(22);                    // ')'
    eventHandler.endNonterminal("rewrite_tree", e0);
  }

  private void try_rewrite_tree()
  {
    consumeT(40);                   // '^('
    lookahead1W(46);                // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '{'
    try_rewrite_atom();
    for (;;)
    {
      lookahead1W(56);              // CHAR_LITERAL | STRING_LITERAL | TOKEN_REF | RULE_REF | WHITESPACE | '$' | '(' |
                                    // ')' | '^(' | '{'
      if (l1 == 22)                 // ')'
      {
        break;
      }
      try_rewrite_element();
    }
    consumeT(22);                   // ')'
  }

  private void parse_rewrite_template()
  {
    eventHandler.startNonterminal("rewrite_template", e0);
    switch (l1)
    {
    case 21:                        // '('
      parse_rewrite_indirect_template_head();
      break;
    case 55:                        // '{'
      parse_ACTION();
      break;
    default:
      parse_rewrite_template_head();
      lookahead1W(47);              // DOUBLE_QUOTE_STRING_LITERAL | DOUBLE_ANGLE_STRING_LITERAL | WHITESPACE | ')' |
                                    // '->' | ';' | '|'
      if (l1 == 5                   // DOUBLE_QUOTE_STRING_LITERAL
       || l1 == 6)                  // DOUBLE_ANGLE_STRING_LITERAL
      {
        switch (l1)
        {
        case 5:                     // DOUBLE_QUOTE_STRING_LITERAL
          consume(5);               // DOUBLE_QUOTE_STRING_LITERAL
          break;
        default:
          consume(6);               // DOUBLE_ANGLE_STRING_LITERAL
        }
      }
    }
    eventHandler.endNonterminal("rewrite_template", e0);
  }

  private void try_rewrite_template()
  {
    switch (l1)
    {
    case 21:                        // '('
      try_rewrite_indirect_template_head();
      break;
    case 55:                        // '{'
      try_ACTION();
      break;
    default:
      try_rewrite_template_head();
      lookahead1W(47);              // DOUBLE_QUOTE_STRING_LITERAL | DOUBLE_ANGLE_STRING_LITERAL | WHITESPACE | ')' |
                                    // '->' | ';' | '|'
      if (l1 == 5                   // DOUBLE_QUOTE_STRING_LITERAL
       || l1 == 6)                  // DOUBLE_ANGLE_STRING_LITERAL
      {
        switch (l1)
        {
        case 5:                     // DOUBLE_QUOTE_STRING_LITERAL
          consumeT(5);              // DOUBLE_QUOTE_STRING_LITERAL
          break;
        default:
          consumeT(6);              // DOUBLE_ANGLE_STRING_LITERAL
        }
      }
    }
  }

  private void parse_rewrite_template_head()
  {
    eventHandler.startNonterminal("rewrite_template_head", e0);
    parse_id();
    lookahead1W(3);                 // WHITESPACE | '('
    consume(21);                    // '('
    lookahead1W(26);                // TOKEN_REF | RULE_REF | WHITESPACE | ')'
    whitespace();
    parse_rewrite_template_args();
    consume(22);                    // ')'
    eventHandler.endNonterminal("rewrite_template_head", e0);
  }

  private void try_rewrite_template_head()
  {
    try_id();
    lookahead1W(3);                 // WHITESPACE | '('
    consumeT(21);                   // '('
    lookahead1W(26);                // TOKEN_REF | RULE_REF | WHITESPACE | ')'
    try_rewrite_template_args();
    consumeT(22);                   // ')'
  }

  private void parse_rewrite_indirect_template_head()
  {
    eventHandler.startNonterminal("rewrite_indirect_template_head", e0);
    consume(21);                    // '('
    lookahead1W(12);                // WHITESPACE | '{'
    whitespace();
    parse_ACTION();
    lookahead1W(4);                 // WHITESPACE | ')'
    consume(22);                    // ')'
    lookahead1W(3);                 // WHITESPACE | '('
    consume(21);                    // '('
    lookahead1W(26);                // TOKEN_REF | RULE_REF | WHITESPACE | ')'
    whitespace();
    parse_rewrite_template_args();
    consume(22);                    // ')'
    eventHandler.endNonterminal("rewrite_indirect_template_head", e0);
  }

  private void try_rewrite_indirect_template_head()
  {
    consumeT(21);                   // '('
    lookahead1W(12);                // WHITESPACE | '{'
    try_ACTION();
    lookahead1W(4);                 // WHITESPACE | ')'
    consumeT(22);                   // ')'
    lookahead1W(3);                 // WHITESPACE | '('
    consumeT(21);                   // '('
    lookahead1W(26);                // TOKEN_REF | RULE_REF | WHITESPACE | ')'
    try_rewrite_template_args();
    consumeT(22);                   // ')'
  }

  private void parse_rewrite_template_args()
  {
    eventHandler.startNonterminal("rewrite_template_args", e0);
    if (l1 != 22)                   // ')'
    {
      whitespace();
      parse_rewrite_template_arg();
      for (;;)
      {
        lookahead1W(16);            // WHITESPACE | ')' | ','
        if (l1 != 26)               // ','
        {
          break;
        }
        consume(26);                // ','
        lookahead1W(14);            // TOKEN_REF | RULE_REF | WHITESPACE
        whitespace();
        parse_rewrite_template_arg();
      }
    }
    eventHandler.endNonterminal("rewrite_template_args", e0);
  }

  private void try_rewrite_template_args()
  {
    if (l1 != 22)                   // ')'
    {
      try_rewrite_template_arg();
      for (;;)
      {
        lookahead1W(16);            // WHITESPACE | ')' | ','
        if (l1 != 26)               // ','
        {
          break;
        }
        consumeT(26);               // ','
        lookahead1W(14);            // TOKEN_REF | RULE_REF | WHITESPACE
        try_rewrite_template_arg();
      }
    }
  }

  private void parse_rewrite_template_arg()
  {
    eventHandler.startNonterminal("rewrite_template_arg", e0);
    parse_id();
    lookahead1W(10);                // WHITESPACE | '='
    consume(34);                    // '='
    lookahead1W(12);                // WHITESPACE | '{'
    whitespace();
    parse_ACTION();
    eventHandler.endNonterminal("rewrite_template_arg", e0);
  }

  private void try_rewrite_template_arg()
  {
    try_id();
    lookahead1W(10);                // WHITESPACE | '='
    consumeT(34);                   // '='
    lookahead1W(12);                // WHITESPACE | '{'
    try_ACTION();
  }

  private void parse_SEMPRED()
  {
    eventHandler.startNonterminal("SEMPRED", e0);
    parse_NESTED_ACTION();
    lookahead1(0);                  // '?'
    consume(37);                    // '?'
    eventHandler.endNonterminal("SEMPRED", e0);
  }

  private void try_SEMPRED()
  {
    try_NESTED_ACTION();
    lookahead1(0);                  // '?'
    consumeT(37);                   // '?'
  }

  private void parse_ACTION()
  {
    eventHandler.startNonterminal("ACTION", e0);
    parse_NESTED_ACTION();
    eventHandler.endNonterminal("ACTION", e0);
  }

  private void try_ACTION()
  {
    try_NESTED_ACTION();
  }

  private void parse_NESTED_ACTION()
  {
    eventHandler.startNonterminal("NESTED_ACTION", e0);
    consume(55);                    // '{'
    for (;;)
    {
      lookahead1(44);               // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
      if (l1 == 57)                 // '}'
      {
        break;
      }
      switch (l1)
      {
      case 55:                      // '{'
        parse_NESTED_ACTION();
        break;
      case 10:                      // ACTION_CHAR_LITERAL
        consume(10);                // ACTION_CHAR_LITERAL
        break;
      case 2:                       // COMMENT
        consume(2);                 // COMMENT
        break;
      case 11:                      // ACTION_STRING_LITERAL
        consume(11);                // ACTION_STRING_LITERAL
        break;
      case 12:                      // ACTION_ESC
        consume(12);                // ACTION_ESC
        break;
      default:
        consume(9);                 // NESTED_ACTION_char
      }
    }
    consume(57);                    // '}'
    eventHandler.endNonterminal("NESTED_ACTION", e0);
  }

  private void try_NESTED_ACTION()
  {
    consumeT(55);                   // '{'
    for (;;)
    {
      lookahead1(44);               // COMMENT | NESTED_ACTION_char | ACTION_CHAR_LITERAL | ACTION_STRING_LITERAL |
                                    // ACTION_ESC | '{' | '}'
      if (l1 == 57)                 // '}'
      {
        break;
      }
      switch (l1)
      {
      case 55:                      // '{'
        try_NESTED_ACTION();
        break;
      case 10:                      // ACTION_CHAR_LITERAL
        consumeT(10);               // ACTION_CHAR_LITERAL
        break;
      case 2:                       // COMMENT
        consumeT(2);                // COMMENT
        break;
      case 11:                      // ACTION_STRING_LITERAL
        consumeT(11);               // ACTION_STRING_LITERAL
        break;
      case 12:                      // ACTION_ESC
        consumeT(12);               // ACTION_ESC
        break;
      default:
        consumeT(9);                // NESTED_ACTION_char
      }
    }
    consumeT(57);                   // '}'
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

  private void consumeT(int t)
  {
    if (l1 == t)
    {
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
      if (code != 18)               // WHITESPACE
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

  private void lookahead2(int tokenSetId)
  {
    if (l2 == 0)
    {
      l2 = match(tokenSetId);
      b2 = begin;
      e2 = end;
    }
    lk = (l2 << 6) | l1;
  }

  private void lookahead3(int tokenSetId)
  {
    if (l3 == 0)
    {
      l3 = match(tokenSetId);
      b3 = begin;
      e3 = end;
    }
    lk |= l3 << 12;
  }

  private int error(int b, int e, int s, int l, int t)
  {
    if (e >= ex)
    {
      bx = b;
      ex = e;
      sx = s;
      lx = l;
      tx = t;
    }
    throw new ParseException(bx, ex, sx, lx, tx);
  }

  private void memoize(int i, int e, int v)
  {
    memo.put((e << 3) + i, v);
  }

  private int memoized(int i, int e)
  {
    Integer v = memo.get((e << 3) + i);
    return v == null ? 0 : v;
  }

  private int lk, b0, e0;
  private int l1, b1, e1;
  private int l2, b2, e2;
  private int l3, b3, e3;
  private int bx, ex, sx, lx, tx;
  private EventHandler eventHandler = null;
  private java.util.Map<Integer, Integer> memo = new java.util.HashMap<Integer, Integer>();
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

    if (end > size) end = size;
    return (result & 63) - 1;
  }

  private static String[] getTokenSet(int tokenSetId)
  {
    java.util.ArrayList<String> expected = new java.util.ArrayList<>();
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 511;
    for (int i = 0; i < 59; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 484 + s - 1;
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

  private static final int[] MAP0 = new int[128];
  static
  {
    final String s1[] =
    {
      /*   0 */ "66, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5",
      /*  34 */ "6, 7, 8, 7, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19, 20, 21",
      /*  61 */ "22, 23, 24, 25, 26, 27, 27, 27, 27, 27, 28, 28, 28, 28, 28, 29, 28, 30, 28, 28, 28, 31, 28, 32, 28",
      /*  86 */ "28, 28, 28, 28, 28, 33, 34, 35, 36, 37, 7, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52",
      /* 112 */ "53, 47, 54, 55, 56, 57, 58, 59, 60, 61, 47, 62, 63, 64, 65, 7"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 128; ++i) {MAP0[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] MAP1 = new int[249];
  static
  {
    final String s1[] =
    {
      /*   0 */ "54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58",
      /*  25 */ "58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58",
      /*  50 */ "58, 58, 58, 58, 90, 122, 154, 186, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217",
      /*  71 */ "217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 217, 66, 0",
      /*  92 */ "0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 5, 6, 7",
      /* 126 */ "8, 7, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19, 20, 21, 22",
      /* 152 */ "23, 24, 25, 26, 27, 27, 27, 27, 27, 28, 28, 28, 28, 28, 29, 28, 30, 28, 28, 28, 31, 28, 32, 28, 28",
      /* 177 */ "28, 28, 28, 28, 33, 34, 35, 36, 37, 7, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53",
      /* 203 */ "47, 54, 55, 56, 57, 58, 59, 60, 61, 47, 62, 63, 64, 65, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7",
      /* 232 */ "7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 249; ++i) {MAP1[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] MAP2 = new int[6];
  static
  {
    final String s1[] =
    {
      /* 0 */ "57344, 65536, 65533, 1114111, 7, 7"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 6; ++i) {MAP2[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] INITIAL = new int[89];
  static
  {
    final String s1[] =
    {
      /*  0 */ "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28",
      /* 28 */ "29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54",
      /* 54 */ "55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80",
      /* 80 */ "81, 82, 83, 84, 85, 86, 87, 88, 89"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 89; ++i) {INITIAL[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] TRANSITION = new int[8787];
  static
  {
    final String s1[] =
    {
      /*    0 */ "4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337",
      /*   17 */ "4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337",
      /*   34 */ "4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337",
      /*   51 */ "4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4288, 4289, 4289, 4289",
      /*   68 */ "4289, 4297, 4289, 4289, 4289, 4289, 4289, 4399, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337",
      /*   85 */ "4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 6162, 6709, 5759, 4986, 4377, 4442, 4385",
      /*  102 */ "4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5293, 4464, 5194, 4478, 4486, 4505, 5208, 4540, 4560",
      /*  119 */ "5238, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4288, 4289, 4289, 4289, 4289, 4297, 4289, 4289",
      /*  136 */ "4289, 4289, 4289, 4303, 4336, 4806, 4322, 4337, 7463, 4334, 8364, 4568, 4337, 4580, 4365, 7305, 4337",
      /*  153 */ "7437, 5561, 4337, 7436, 7115, 7434, 5549, 6222, 6169, 4599, 6716, 4628, 4642, 4392, 4607, 4674, 4615",
      /*  170 */ "4622, 4636, 4668, 4678, 4648, 4654, 5919, 4686, 4694, 4702, 4660, 4710, 4724, 4716, 4732, 4732, 4732",
      /*  187 */ "4732, 4740, 4337, 4337, 4337, 7911, 7912, 7912, 7912, 7912, 4794, 7912, 7912, 7912, 7912, 7912, 4801",
      /*  204 */ "4336, 4806, 4322, 4337, 7347, 4334, 8364, 4752, 4337, 4764, 4365, 8369, 4337, 7932, 4369, 4337, 7931",
      /*  221 */ "6043, 7929, 5409, 7175, 5752, 4779, 5416, 4841, 4855, 4787, 4819, 4875, 4827, 4835, 4849, 4869, 4879",
      /*  238 */ "4861, 4887, 6319, 4901, 4909, 4917, 4893, 4925, 4939, 4931, 4947, 4947, 4947, 4947, 4955, 4337, 4337",
      /*  255 */ "4337, 4288, 4289, 4289, 4289, 4289, 4297, 4289, 4289, 4289, 4289, 4289, 4399, 4336, 4806, 4322, 4337",
      /*  272 */ "8690, 4334, 8364, 4967, 4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 6162, 6709",
      /*  289 */ "5759, 4980, 4377, 5379, 4385, 4994, 5002, 7039, 5021, 5029, 5037, 8123, 5045, 5293, 4464, 5053, 5061",
      /*  306 */ "5069, 5077, 5307, 5085, 5093, 5238, 5105, 5101, 5117, 5129, 5141, 4337, 4337, 4337, 4337, 4337, 4337",
      /*  323 */ "4337, 4337, 5503, 7639, 4337, 7639, 7431, 5154, 5161, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346",
      /*  340 */ "4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442",
      /*  357 */ "5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232",
      /*  374 */ "4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 5252, 4337",
      /*  391 */ "4337, 4337, 4337, 4337, 7295, 4336, 6033, 5260, 4337, 7376, 4334, 8364, 4346, 4337, 4358, 4365, 8306",
      /*  408 */ "4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5273",
      /*  425 */ "7504, 5281, 4450, 8123, 5289, 7220, 5301, 5315, 5323, 5331, 5350, 5358, 5824, 5366, 5830, 4493, 4493",
      /*  442 */ "4493, 4497, 5009, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 5503, 4337, 4337, 4337, 4337, 4337",
      /*  459 */ "7110, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337",
      /*  476 */ "5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123",
      /*  493 */ "5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337",
      /*  510 */ "4337, 4337, 4337, 4337, 4337, 4337, 4337, 4756, 4337, 5387, 5390, 5013, 5392, 7110, 4336, 4806, 4322",
      /*  527 */ "4337, 8037, 4334, 8364, 4346, 4337, 5402, 4365, 7541, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731",
      /*  544 */ "6587, 7671, 4986, 5424, 4442, 5174, 4413, 6519, 6955, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194",
      /*  561 */ "4478, 5432, 5224, 5440, 5232, 5459, 5467, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 7927, 7633",
      /*  578 */ "4337, 4337, 6815, 5481, 7636, 5486, 6821, 6822, 6822, 5494, 4337, 4308, 5511, 4337, 5523, 5531, 8301",
      /*  595 */ "4346, 4338, 5542, 5557, 8306, 4337, 5265, 6479, 4337, 5264, 5499, 5734, 5569, 6587, 7671, 4986, 4377",
      /*  612 */ "4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470",
      /*  629 */ "5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 6667, 4337, 4337, 4337, 6668, 5503",
      /*  646 */ "6668, 5581, 5604, 5605, 5605, 5587, 4336, 4806, 4322, 5613, 8037, 4334, 8364, 4346, 4337, 4358, 4365",
      /*  663 */ "8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489",
      /*  680 */ "5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105",
      /*  697 */ "5105, 5105, 5105, 5338, 4337, 4337, 4337, 4312, 4337, 5623, 4314, 4337, 4572, 4337, 5640, 5633, 5659",
      /*  714 */ "5662, 5646, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737, 6479",
      /*  731 */ "4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450",
      /*  748 */ "8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338",
      /*  765 */ "4337, 4337, 4337, 4337, 4337, 4337, 5515, 4337, 4959, 4337, 4337, 4337, 5687, 5691, 5670, 4336, 6176",
      /*  782 */ "5683, 4337, 8729, 4334, 5699, 5712, 4337, 5724, 4587, 5745, 4337, 5737, 4591, 4337, 5736, 5499, 5734",
      /*  799 */ "5731, 6587, 5797, 5817, 7045, 5888, 5838, 5851, 5866, 5843, 5874, 5882, 5896, 8117, 5904, 5912, 4526",
      /*  816 */ "8278, 5927, 5935, 5943, 4532, 5951, 5959, 5973, 5967, 5967, 5967, 5967, 5981, 4337, 4337, 4337, 4337",
      /*  833 */ "4337, 5786, 5716, 4337, 5503, 4337, 4337, 5625, 8775, 8779, 5993, 4336, 4806, 4322, 4337, 8037, 4334",
      /*  850 */ "8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986",
      /*  867 */ "4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224",
      /*  884 */ "4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 4337, 6010, 6654, 4337",
      /*  901 */ "6028, 4337, 4337, 4337, 4337, 4337, 7110, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358",
      /*  918 */ "4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413",
      /*  935 */ "7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546",
      /*  952 */ "5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 5789, 4337, 4337, 4337, 4337, 5342, 4337, 5789, 8224",
      /*  969 */ "8228, 8231, 6055, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737",
      /*  986 */ "6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436",
      /* 1003 */ "4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105",
      /* 1020 */ "5338, 4337, 4337, 4337, 5394, 4337, 4337, 5534, 5393, 5503, 7630, 6073, 7092, 7096, 6087, 6079, 4336",
      /* 1037 */ "4806, 4322, 7883, 8037, 4334, 8364, 4346, 5985, 4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499",
      /* 1054 */ "5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187",
      /* 1071 */ "5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337",
      /* 1088 */ "8052, 8053, 8053, 8053, 6104, 6116, 6095, 6112, 6102, 8053, 8053, 6124, 4336, 6237, 6137, 4337, 8037",
      /* 1105 */ "4334, 8364, 4346, 4337, 6155, 4771, 6194, 4337, 5737, 6442, 4337, 5736, 5499, 5734, 7147, 7320, 6230",
      /* 1122 */ "6250, 6257, 8532, 6265, 6948, 6273, 8526, 6281, 6289, 6297, 6305, 6327, 6312, 6335, 8608, 6349, 6357",
      /* 1139 */ "6365, 6341, 6373, 6381, 6395, 6389, 6389, 6389, 6389, 6403, 4337, 4337, 4337, 4337, 4337, 4337, 4337",
      /* 1156 */ "4337, 6047, 4337, 4337, 4337, 4337, 4337, 7110, 6417, 4806, 6428, 7414, 8037, 6450, 8364, 6463, 6002",
      /* 1173 */ "4358, 6475, 5704, 8349, 7571, 6504, 8348, 7736, 5592, 7951, 6487, 6498, 6065, 4986, 4377, 4442, 5174",
      /* 1190 */ "4413, 7489, 5179, 4428, 4436, 4450, 6512, 6527, 5187, 5202, 5194, 6535, 5216, 6543, 4519, 6551, 6559",
      /* 1207 */ "5473, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6019, 6014, 4337, 6018, 6567, 6580, 4337",
      /* 1224 */ "6020, 6681, 4337, 7110, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337",
      /* 1241 */ "5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428",
      /* 1258 */ "4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105",
      /* 1275 */ "5105, 5338, 4337, 4337, 4337, 4337, 6600, 6741, 6610, 4337, 7119, 4337, 4337, 6614, 6618, 6622, 6630",
      /* 1292 */ "4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736",
      /* 1309 */ "5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373",
      /* 1326 */ "5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337",
      /* 1343 */ "4337, 4337, 4337, 4337, 4337, 4337, 6643, 4337, 4337, 4337, 7654, 7660, 7666, 4336, 7300, 4322, 4337",
      /* 1360 */ "8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587",
      /* 1377 */ "7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478",
      /* 1394 */ "5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 7880, 7876",
      /* 1411 */ "5573, 4337, 5503, 4337, 4337, 5572, 6651, 4350, 6662, 6676, 4806, 4322, 4337, 8037, 4334, 8364, 4346",
      /* 1428 */ "4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442",
      /* 1445 */ "5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232",
      /* 1462 */ "4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 4337, 6146, 6147, 4337, 5503, 4337",
      /* 1479 */ "4337, 4337, 4337, 4337, 7169, 4336, 4806, 4322, 4337, 6694, 4334, 8449, 4346, 4337, 4358, 4365, 6702",
      /* 1496 */ "4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179",
      /* 1513 */ "4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105",
      /* 1530 */ "5105, 5105, 5338, 4337, 4337, 4337, 6145, 4337, 4337, 6141, 4337, 5503, 4337, 4337, 6781, 6724, 6728",
      /* 1547 */ "6736, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337",
      /* 1564 */ "5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123",
      /* 1581 */ "5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337",
      /* 1598 */ "4337, 4337, 4337, 4337, 6407, 6779, 6777, 6749, 6771, 6409, 6780, 6408, 4337, 7110, 4336, 4806, 4322",
      /* 1615 */ "4337, 8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731",
      /* 1632 */ "6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194",
      /* 1649 */ "4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6602",
      /* 1666 */ "4337, 5809, 5804, 6803, 6796, 6789, 6789, 6789, 6789, 6809, 6417, 4806, 6830, 7414, 8037, 6450, 8364",
      /* 1683 */ "6463, 6002, 4358, 6475, 5704, 8349, 7712, 6836, 8348, 7736, 5592, 7951, 6487, 6498, 6065, 4986, 4377",
      /* 1700 */ "6844, 5174, 4413, 7489, 4420, 6852, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470",
      /* 1717 */ "6860, 4560, 5244, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6602, 4337, 5809, 5804, 6803",
      /* 1734 */ "6796, 6789, 6789, 6789, 6789, 6809, 6417, 4806, 6830, 7414, 8037, 6450, 8364, 6463, 6002, 4358, 6475",
      /* 1751 */ "5704, 8349, 7571, 6504, 8348, 7736, 5592, 7951, 6487, 6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489",
      /* 1768 */ "5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105",
      /* 1785 */ "5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6602, 4337, 5809, 5804, 6803, 6796, 6789, 6789, 6789",
      /* 1802 */ "6789, 6809, 6417, 4806, 6830, 7414, 8037, 6450, 8364, 6463, 5596, 4358, 6438, 5704, 8349, 7580, 6504",
      /* 1819 */ "8348, 7780, 5592, 7778, 6868, 6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450",
      /* 1836 */ "8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338",
      /* 1853 */ "4337, 4337, 4337, 4337, 6602, 4337, 5809, 5804, 6803, 6796, 6789, 6789, 6789, 6789, 6809, 6417, 4806",
      /* 1870 */ "6830, 7414, 8037, 6450, 8364, 6463, 5596, 4358, 6438, 5704, 8349, 7580, 6504, 8348, 7780, 5592, 7778",
      /* 1887 */ "6868, 6891, 6065, 4986, 4377, 4442, 5174, 4413, 6905, 6913, 4428, 4436, 6921, 8111, 5373, 5187, 5202",
      /* 1904 */ "5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5447, 6929, 5105, 5105, 5338, 4337, 4337, 4337, 4337",
      /* 1921 */ "6602, 4337, 5809, 5804, 6803, 6796, 6789, 6789, 6789, 6789, 6809, 6417, 4806, 6830, 7414, 8037, 6450",
      /* 1938 */ "8364, 6463, 5596, 4358, 6438, 5704, 8349, 7580, 6504, 8348, 6186, 5998, 7778, 6868, 6498, 6065, 4986",
      /* 1955 */ "4377, 4442, 6941, 4413, 7489, 5179, 6963, 6971, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224",
      /* 1972 */ "4470, 5232, 6979, 4512, 6987, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6602, 4337, 5809, 5804",
      /* 1989 */ "6803, 6796, 6789, 6789, 6789, 6789, 6809, 6417, 4806, 6830, 7414, 8037, 6450, 8364, 6463, 5596, 4358",
      /* 2006 */ "6438, 5704, 8349, 7580, 6504, 8348, 7780, 5592, 7778, 6868, 6498, 6208, 4986, 4377, 4442, 5174, 4413",
      /* 2023 */ "7489, 5858, 6999, 4436, 4450, 8129, 7007, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546",
      /* 2040 */ "5109, 6933, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6602, 4337, 5809, 5804, 6803, 6796, 6789, 6789",
      /* 2057 */ "6789, 6789, 6809, 6417, 4806, 6830, 7414, 8037, 6450, 8364, 6463, 5596, 4358, 6438, 5704, 8349, 7580",
      /* 2074 */ "6504, 8348, 7780, 5592, 8408, 7015, 6498, 6065, 4986, 4377, 4442, 5174, 7032, 7053, 5179, 4428, 7061",
      /* 2091 */ "7069, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4552, 5451, 5105, 5105, 5105",
      /* 2108 */ "5338, 4337, 4337, 4337, 8455, 4337, 4337, 4337, 4337, 5503, 4404, 4337, 5615, 4405, 5773, 5780, 4336",
      /* 2125 */ "4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499",
      /* 2142 */ "5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187",
      /* 2159 */ "5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337",
      /* 2176 */ "4337, 4337, 4337, 4337, 4337, 4326, 4337, 4337, 4337, 4337, 4337, 8769, 7077, 6038, 7086, 4337, 8479",
      /* 2193 */ "7104, 7127, 4346, 4337, 7140, 7155, 8306, 4337, 5146, 6479, 4337, 5145, 5499, 5734, 7163, 6587, 7671",
      /* 2210 */ "4986, 4377, 4442, 5174, 4413, 7489, 5179, 7183, 7191, 4450, 8123, 5373, 7199, 7214, 7206, 7228, 7236",
      /* 2227 */ "7244, 7252, 7267, 7281, 7273, 7259, 7259, 7259, 7259, 7289, 4337, 4337, 4337, 4337, 4337, 4337, 4337",
      /* 2244 */ "4337, 5503, 4337, 4337, 4337, 4337, 4337, 7341, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337",
      /* 2261 */ "4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174",
      /* 2278 */ "4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560",
      /* 2295 */ "4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 5503, 4337, 7313",
      /* 2312 */ "8430, 8434, 7328, 7335, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337",
      /* 2329 */ "5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428",
      /* 2346 */ "4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105",
      /* 2363 */ "5105, 5338, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 5503, 4337, 4337, 4337, 4337, 4337, 7110",
      /* 2380 */ "6417, 4806, 6830, 7414, 8037, 6450, 8364, 6463, 5596, 4358, 6438, 5704, 8349, 7580, 6504, 8348, 7780",
      /* 2397 */ "5592, 7778, 6868, 6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373",
      /* 2414 */ "5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337",
      /* 2431 */ "4337, 4337, 6763, 4337, 6420, 6060, 6455, 6873, 7981, 7981, 7981, 7981, 7457, 7355, 7370, 6830, 7020",
      /* 2448 */ "8037, 7384, 8364, 7397, 6002, 4358, 6475, 5704, 7822, 7571, 6504, 7411, 7854, 5592, 7951, 6487, 6498",
      /* 2465 */ "6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478",
      /* 2482 */ "5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337",
      /* 2499 */ "6420, 6060, 6455, 6873, 7981, 7981, 7981, 7981, 7457, 6417, 4806, 6830, 7414, 8037, 6450, 8364, 7424",
      /* 2516 */ "6002, 4358, 6475, 5704, 8349, 7571, 6504, 8348, 7736, 5592, 7951, 6487, 6498, 6065, 4986, 4377, 4442",
      /* 2533 */ "5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232",
      /* 2550 */ "4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420, 6060, 6455, 6873",
      /* 2567 */ "8344, 7981, 7981, 7981, 7457, 7445, 4806, 6830, 7471, 8037, 6450, 8364, 6463, 6002, 4358, 6475, 5704",
      /* 2584 */ "7416, 7571, 6504, 8348, 7736, 5651, 7951, 6487, 6498, 6065, 4986, 4377, 4442, 7483, 7497, 7489, 5179",
      /* 2601 */ "4428, 4436, 4450, 8135, 5373, 5187, 7512, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105",
      /* 2618 */ "5133, 8162, 5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420, 6060, 6455, 6873, 7981, 7981, 7981, 7981",
      /* 2635 */ "7457, 6417, 4806, 6830, 7414, 8037, 6450, 8364, 6463, 6002, 4358, 6475, 5704, 8349, 7571, 6504, 8348",
      /* 2652 */ "7736, 5592, 7951, 6487, 6498, 8183, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123",
      /* 2669 */ "5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337",
      /* 2686 */ "4337, 4337, 4337, 6763, 4337, 6420, 6060, 6455, 6873, 7981, 7981, 7981, 7981, 7457, 7520, 4806, 7528",
      /* 2703 */ "7414, 8037, 6450, 7536, 6463, 6002, 4358, 7549, 5704, 6490, 7616, 7557, 7565, 7736, 6181, 7951, 7588",
      /* 2720 */ "7647, 6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194",
      /* 2737 */ "4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763",
      /* 2754 */ "4337, 6420, 6060, 6455, 7973, 7966, 7980, 7981, 7981, 7457, 6417, 4806, 6830, 7414, 8037, 6450, 8364",
      /* 2771 */ "6463, 6002, 4358, 6475, 5704, 8349, 7571, 6504, 8348, 7736, 5592, 7951, 6487, 6498, 6065, 4986, 4377",
      /* 2788 */ "4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470",
      /* 2805 */ "5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 4811, 4337, 6420, 6129, 7389",
      /* 2822 */ "6873, 7981, 7981, 7981, 7981, 7457, 6417, 4806, 6830, 7414, 8037, 6450, 8364, 6463, 5596, 4358, 6438",
      /* 2839 */ "7132, 8349, 7580, 6504, 8348, 7780, 5592, 7778, 6868, 6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489",
      /* 2856 */ "5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105",
      /* 2873 */ "5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420, 6060, 6455, 6873, 7981, 7981, 7981",
      /* 2890 */ "7981, 7457, 6417, 5166, 6830, 7414, 8037, 6450, 8364, 6463, 5596, 4358, 6438, 5704, 8349, 7580, 6504",
      /* 2907 */ "7683, 7780, 5592, 7778, 6868, 6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450",
      /* 2924 */ "8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338",
      /* 2941 */ "4337, 4337, 4337, 4337, 6763, 4337, 6420, 6060, 6455, 6873, 7982, 7679, 7981, 7981, 7457, 6417, 4806",
      /* 2958 */ "6830, 6878, 8037, 6450, 8364, 7691, 5596, 4358, 6438, 7705, 8349, 7744, 6504, 7947, 7780, 5592, 7778",
      /* 2975 */ "6868, 6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202",
      /* 2992 */ "5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337",
      /* 3009 */ "6763, 4337, 6420, 6060, 6455, 6873, 7981, 7981, 7981, 7981, 7457, 6417, 4806, 6830, 7414, 8037, 6450",
      /* 3026 */ "8364, 6463, 5596, 4358, 6438, 5704, 8349, 7580, 6504, 8348, 7780, 5592, 7778, 6868, 6498, 6065, 4986",
      /* 3043 */ "4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224",
      /* 3060 */ "4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420, 6060",
      /* 3077 */ "6455, 6873, 7981, 7981, 7981, 7981, 7457, 6417, 4806, 6830, 7414, 8037, 6450, 8364, 6463, 7757, 4358",
      /* 3094 */ "6438, 5704, 8349, 7580, 6504, 8348, 7780, 5592, 7778, 6868, 6498, 6065, 4986, 4377, 4442, 5174, 4413",
      /* 3111 */ "7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546",
      /* 3128 */ "5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420, 6572, 7896, 6873, 7981, 7981",
      /* 3145 */ "7981, 7981, 7457, 6417, 4806, 6830, 7414, 8037, 6450, 8364, 6463, 5596, 4358, 6438, 5704, 7775, 7580",
      /* 3162 */ "6504, 8348, 7788, 5592, 7577, 6868, 6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436",
      /* 3179 */ "4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105",
      /* 3196 */ "5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420, 6060, 6455, 6873, 7981, 7981, 7981, 7981, 7457, 6417",
      /* 3213 */ "4806, 6830, 7475, 8037, 6450, 8364, 6463, 5596, 7806, 6438, 5704, 8349, 6433, 6504, 7821, 7780, 5592",
      /* 3230 */ "7778, 6868, 6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187",
      /* 3247 */ "5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337",
      /* 3264 */ "4337, 6763, 4337, 6420, 6060, 6455, 6873, 7981, 7981, 7981, 7981, 7457, 6417, 4806, 6830, 7414, 8037",
      /* 3281 */ "6450, 8364, 6463, 7869, 4358, 6438, 5704, 8349, 7580, 6504, 7834, 7780, 7830, 7842, 7862, 6498, 6065",
      /* 3298 */ "4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216",
      /* 3315 */ "5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420",
      /* 3332 */ "6060, 7623, 7595, 7981, 7798, 7795, 7981, 7457, 6417, 4806, 6830, 7763, 8037, 7891, 8364, 7904, 7920",
      /* 3349 */ "4358, 6438, 7940, 8349, 7959, 7403, 8348, 7990, 5592, 7778, 6868, 6498, 6065, 4986, 4377, 4442, 5174",
      /* 3366 */ "4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560",
      /* 3383 */ "4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420, 6635, 8397, 8004, 7997",
      /* 3400 */ "8011, 7981, 7981, 7457, 6417, 6592, 6830, 7024, 8037, 6450, 8364, 6463, 8015, 4358, 8023, 5704, 6467",
      /* 3417 */ "7580, 6504, 8348, 7780, 5592, 7778, 6868, 6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428",
      /* 3434 */ "4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105",
      /* 3451 */ "5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420, 6060, 6455, 7609, 7981, 7981, 7981, 7981, 7697",
      /* 3468 */ "6417, 8031, 8045, 7767, 8037, 8061, 8384, 6463, 5596, 4358, 6438, 5704, 8349, 8194, 8069, 8089, 8077",
      /* 3485 */ "8085, 8317, 6868, 6498, 6065, 4986, 4377, 4456, 5174, 4413, 7489, 5179, 4428, 4436, 8097, 8105, 5373",
      /* 3502 */ "8143, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 8158, 8170, 5338, 4337, 4337",
      /* 3519 */ "4337, 4337, 6763, 4337, 6420, 8178, 6215, 7719, 8209, 7726, 7981, 7981, 7457, 6417, 4806, 6830, 7414",
      /* 3536 */ "8037, 6450, 8364, 6463, 5596, 4358, 7749, 5704, 8349, 7580, 6504, 8348, 7780, 7849, 8191, 8202, 8217",
      /* 3553 */ "6065, 4986, 8239, 4442, 5174, 4413, 7489, 5179, 4428, 8247, 8255, 8123, 8263, 8271, 5202, 5194, 4478",
      /* 3570 */ "5216, 5224, 4470, 5232, 4560, 4546, 5105, 5121, 6991, 5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337",
      /* 3587 */ "6420, 6242, 7451, 7602, 8332, 8339, 7981, 7981, 7457, 6417, 4806, 6830, 7414, 8037, 6450, 8423, 8286",
      /* 3604 */ "8294, 4358, 6438, 5704, 8314, 7580, 6504, 8348, 7780, 5675, 6883, 8325, 8357, 6065, 4986, 4377, 4442",
      /* 3621 */ "5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232",
      /* 3638 */ "4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420, 6060, 6455, 6873",
      /* 3655 */ "7981, 7981, 7981, 7981, 7457, 6417, 4806, 8377, 7414, 5766, 8392, 8364, 6463, 5596, 4358, 6438, 5704",
      /* 3672 */ "7731, 7580, 6504, 8348, 7780, 5592, 7778, 6868, 6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179",
      /* 3689 */ "4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105",
      /* 3706 */ "5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420, 6060, 6455, 6873, 7981, 7981, 7981, 7981",
      /* 3723 */ "7457, 6417, 4806, 6830, 7414, 8037, 6450, 8364, 6463, 5596, 4358, 6438, 5704, 8405, 7580, 6504, 8348",
      /* 3740 */ "7780, 5592, 7778, 6868, 6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123",
      /* 3757 */ "5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337",
      /* 3774 */ "4337, 4337, 4337, 6763, 4337, 6420, 6060, 6455, 6873, 7981, 7981, 7981, 7981, 7457, 6417, 4806, 6830",
      /* 3791 */ "7414, 8037, 6450, 8364, 6463, 5596, 4358, 6438, 5704, 8349, 7580, 6897, 8348, 7780, 5592, 7778, 6868",
      /* 3808 */ "6498, 6065, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194",
      /* 3825 */ "4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763",
      /* 3842 */ "4337, 6420, 6060, 6455, 6873, 7981, 7981, 7981, 7981, 7457, 6417, 4806, 6830, 7414, 8037, 8416, 8364",
      /* 3859 */ "6463, 5596, 4358, 6438, 5704, 8349, 7580, 6504, 8348, 7780, 5592, 7778, 6868, 6498, 6065, 4986, 4377",
      /* 3876 */ "4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470",
      /* 3893 */ "5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 6763, 4337, 6420, 6060, 6455",
      /* 3910 */ "6873, 7981, 7981, 7981, 7981, 7457, 6417, 4806, 6830, 7414, 8037, 6450, 8364, 6463, 5596, 4358, 6438",
      /* 3927 */ "5704, 8349, 7580, 6504, 8348, 7780, 5592, 7778, 6868, 8442, 6065, 4986, 4377, 4442, 5174, 4413, 7489",
      /* 3944 */ "5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105",
      /* 3961 */ "5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 8459, 8458, 6761, 6759, 6754, 4337, 8467, 8487, 8488",
      /* 3978 */ "8488, 8473, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737, 6479",
      /* 3995 */ "4337, 5736, 5499, 5734, 7813, 7362, 6201, 8496, 8511, 8546, 8503, 8519, 8585, 8540, 8554, 8562, 8570",
      /* 4012 */ "8578, 8593, 8601, 8616, 8150, 8630, 8638, 8646, 8622, 8654, 8668, 8660, 8676, 8676, 8676, 8676, 8684",
      /* 4029 */ "4337, 4337, 4337, 4337, 4337, 8698, 8699, 4337, 4744, 4337, 4337, 8715, 8713, 8707, 8723, 4336, 4806",
      /* 4046 */ "4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734",
      /* 4063 */ "5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202",
      /* 4080 */ "5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337",
      /* 4097 */ "7078, 4337, 6686, 4337, 6685, 4337, 4337, 4337, 4337, 4337, 7110, 4336, 4806, 4322, 4337, 8037, 4334",
      /* 4114 */ "8364, 4346, 4337, 4358, 4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986",
      /* 4131 */ "4377, 4442, 5174, 4413, 7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224",
      /* 4148 */ "4470, 5232, 4560, 4546, 5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337",
      /* 4165 */ "5503, 8741, 8737, 8753, 8749, 8755, 8763, 4336, 4806, 4322, 4337, 8037, 4334, 8364, 4346, 4337, 4358",
      /* 4182 */ "4365, 8306, 4337, 5737, 6479, 4337, 5736, 5499, 5734, 5731, 6587, 7671, 4986, 4377, 4442, 5174, 4413",
      /* 4199 */ "7489, 5179, 4428, 4436, 4450, 8123, 5373, 5187, 5202, 5194, 4478, 5216, 5224, 4470, 5232, 4560, 4546",
      /* 4216 */ "5105, 5105, 5105, 5105, 5338, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4971, 4972, 4337",
      /* 4233 */ "4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337",
      /* 4250 */ "4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337",
      /* 4267 */ "4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337, 4337",
      /* 4284 */ "4337, 4337, 4337, 4337, 0, 9728, 9728, 9728, 9728, 9728, 9728, 9728, 9728, 9728, 9728, 9728, 9728",
      /* 4301 */ "5120, 9728, 9728, 9728, 130, 0, 93, 0, 0, 0, 108, 0, 0, 0, 0, 11776, 0, 0, 0, 0, 0, 113, 114, 0",
      /* 4325 */ "6656, 0, 0, 0, 0, 116, 0, 0, 0, 0, 175, 138, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 113, 114, 155, 1725, 0, 0",
      /* 4352 */ "0, 0, 129, 0, 0, 17920, 132, 9898, 9898, 134, 135, 0, 175, 175, 0, 0, 0, 0, 0, 0, 212, 212, 0, 0",
      /* 4376 */ "190, 280, 280, 9898, 276, 285, 285, 285, 1725, 280, 298, 273, 290, 290, 9898, 285, 304, 304, 304",
      /* 4395 */ "1536, 264, 273, 298, 9728, 0, 130, 0, 93, 0, 0, 0, 93, 0, 0, 93, 0, 0, 276, 294, 294, 1725, 280, 273",
      /* 4419 */ "290, 9898, 285, 276, 294, 294, 1725, 280, 334, 10057, 285, 294, 1868, 280, 290, 335, 335, 10057, 285",
      /* 4438 */ "294, 340, 340, 1868, 280, 290, 280, 9898, 285, 294, 285, 1725, 0, 335, 285, 294, 0, 340, 280, 290",
      /* 4458 */ "280, 10030, 285, 294, 285, 1844, 371, 371, 290, 380, 392, 373, 373, 294, 0, 273, 400, 400, 400, 389",
      /* 4478 */ "0, 392, 392, 396, 373, 294, 0, 400, 371, 402, 400, 389, 389, 389, 371, 290, 280, 294, 285, 290, 280",
      /* 4499 */ "294, 285, 290, 371, 294, 373, 0, 410, 373, 412, 410, 396, 396, 396, 448, 273, 389, 371, 276, 396",
      /* 4519 */ "373, 294, 419, 273, 400, 400, 400, 389, 371, 300, 392, 392, 396, 373, 306, 0, 279, 421, 421, 434",
      /* 4539 */ "389, 371, 260, 276, 410, 410, 410, 396, 373, 273, 389, 371, 276, 396, 373, 273, 389, 450, 276, 396",
      /* 4559 */ "452, 273, 400, 389, 389, 371, 276, 410, 396, 113, 114, 155, 1536, 0, 0, 0, 0, 5120, 0, 0, 11776, 132",
      /* 4581 */ "9728, 9728, 134, 135, 0, 175, 175, 0, 0, 0, 0, 0, 0, 213, 213, 0, 0, 1725, 273, 264, 9728, 276, 260",
      /* 4604 */ "1536, 273, 264, 260, 276, 304, 1536, 264, 273, 298, 298, 10063, 260, 304, 1876, 264, 298, 335, 10063",
      /* 4623 */ "260, 304, 340, 1876, 1876, 264, 298, 264, 9728, 260, 304, 260, 1536, 0, 335, 260, 304, 0, 340, 264",
      /* 4643 */ "298, 298, 298, 298, 9728, 260, 304, 375, 298, 380, 304, 375, 375, 298, 380, 380, 380, 380, 304, 264",
      /* 4663 */ "273, 400, 400, 400, 402, 0, 260, 304, 0, 264, 298, 9728, 260, 276, 304, 304, 1536, 264, 298, 260",
      /* 4683 */ "304, 264, 298, 0, 392, 392, 412, 380, 304, 0, 400, 375, 402, 402, 402, 402, 402, 375, 298, 0, 410",
      /* 4704 */ "380, 412, 412, 412, 412, 412, 375, 260, 276, 410, 410, 410, 412, 380, 298, 402, 375, 304, 412, 380",
      /* 4724 */ "273, 400, 402, 402, 375, 276, 410, 412, 402, 375, 412, 380, 402, 375, 412, 380, 402, 412, 402, 412",
      /* 4744 */ "0, 0, 0, 0, 5120, 0, 0, 29184, 113, 114, 155, 190, 0, 0, 0, 0, 5120, 0, 10752, 0, 132, 90, 90, 134",
      /* 4768 */ "135, 0, 175, 175, 0, 0, 0, 0, 0, 0, 9940, 273, 265, 90, 276, 269, 190, 273, 265, 276, 305, 305, 190",
      /* 4791 */ "265, 273, 299, 90, 90, 90, 90, 5120, 90, 90, 90, 0, 130, 0, 93, 0, 0, 0, 108, 148, 0, 0, 0, 96, 0, 0",
      /* 4817 */ "8803, 0, 269, 276, 305, 190, 265, 273, 299, 299, 336, 269, 305, 341, 265, 299, 335, 335, 336, 269",
      /* 4837 */ "305, 340, 340, 341, 265, 299, 265, 90, 269, 305, 269, 190, 0, 335, 269, 305, 0, 340, 265, 299, 273",
      /* 4858 */ "299, 299, 90, 269, 305, 376, 299, 381, 305, 376, 385, 0, 269, 305, 0, 265, 299, 90, 269, 276, 305",
      /* 4879 */ "305, 190, 265, 299, 269, 305, 265, 299, 376, 376, 299, 381, 392, 381, 381, 305, 265, 273, 400, 400",
      /* 4899 */ "400, 403, 0, 392, 392, 413, 381, 305, 0, 400, 376, 403, 400, 403, 403, 403, 376, 299, 0, 410, 381",
      /* 4920 */ "413, 410, 413, 413, 413, 376, 269, 276, 410, 410, 410, 413, 381, 299, 403, 376, 305, 413, 381, 273",
      /* 4940 */ "400, 403, 403, 376, 276, 410, 413, 403, 376, 413, 381, 403, 376, 413, 381, 403, 413, 403, 413, 0, 0",
      /* 4961 */ "0, 0, 5120, 12288, 0, 0, 113, 114, 155, 1727, 0, 0, 0, 0, 9216, 0, 0, 0, 0, 273, 281, 10011, 276",
      /* 4984 */ "286, 1824, 273, 280, 9898, 276, 285, 1725, 273, 280, 276, 294, 294, 1725, 280, 273, 320, 10049, 285",
      /* 5003 */ "276, 325, 1862, 280, 273, 320, 290, 294, 389, 396, 0, 0, 0, 0, 10752, 10752, 10752, 0, 10057, 285",
      /* 5023 */ "294, 1868, 343, 290, 335, 335, 10057, 347, 294, 340, 340, 1868, 280, 290, 353, 335, 285, 294, 356",
      /* 5042 */ "340, 280, 290, 294, 0, 367, 368, 369, 370, 280, 290, 385, 399, 385, 385, 389, 407, 408, 392, 409",
      /* 5062 */ "392, 392, 396, 417, 418, 0, 400, 407, 402, 400, 389, 389, 389, 371, 290, 0, 410, 417, 412, 410, 396",
      /* 5083 */ "396, 396, 371, 260, 438, 410, 410, 410, 441, 373, 273, 400, 436, 389, 371, 276, 410, 441, 389, 462",
      /* 5103 */ "396, 464, 389, 371, 396, 373, 389, 371, 396, 373, 389, 458, 396, 460, 469, 371, 471, 373, 389, 371",
      /* 5123 */ "396, 373, 389, 466, 396, 468, 389, 478, 396, 480, 389, 371, 396, 373, 389, 474, 396, 476, 483, 484",
      /* 5143 */ "389, 396, 0, 0, 0, 0, 9898, 0, 176, 0, 0, 0, 10240, 10240, 10240, 10240, 10240, 10240, 10240, 0, 130",
      /* 5164 */ "0, 93, 0, 0, 0, 108, 148, 0, 0, 152, 280, 273, 273, 290, 290, 9898, 285, 276, 294, 294, 1725, 280",
      /* 5186 */ "290, 285, 294, 371, 290, 373, 294, 385, 385, 0, 385, 385, 389, 371, 290, 392, 371, 371, 290, 392",
      /* 5206 */ "392, 373, 373, 294, 264, 273, 400, 400, 400, 389, 371, 400, 400, 389, 389, 389, 371, 290, 0, 410",
      /* 5226 */ "373, 410, 410, 396, 396, 396, 371, 0, 276, 410, 410, 410, 396, 373, 298, 389, 371, 304, 396, 373",
      /* 5246 */ "273, 449, 371, 276, 451, 373, 108, 0, 0, 0, 113, 0, 0, 108, 6144, 114, 0, 6656, 0, 0, 0, 0, 9898, 0",
      /* 5270 */ "2560, 0, 0, 10057, 285, 276, 294, 294, 1868, 280, 290, 10057, 285, 294, 349, 349, 1868, 280, 290",
      /* 5289 */ "294, 0, 280, 290, 285, 294, 371, 290, 373, 294, 375, 385, 280, 371, 290, 393, 393, 285, 373, 294",
      /* 5309 */ "264, 433, 400, 400, 400, 436, 386, 0, 386, 385, 290, 280, 290, 393, 0, 393, 392, 294, 285, 294, 0",
      /* 5330 */ "420, 280, 420, 420, 290, 290, 389, 280, 389, 396, 389, 396, 0, 0, 0, 0, 5120, 0, 0, 94, 0, 427, 285",
      /* 5353 */ "427, 427, 294, 294, 396, 285, 396, 0, 273, 420, 400, 420, 290, 273, 420, 290, 290, 280, 276, 427",
      /* 5373 */ "294, 0, 280, 290, 285, 294, 280, 290, 281, 9898, 285, 294, 286, 1725, 10752, 0, 0, 0, 10752, 10752",
      /* 5393 */ "0, 0, 0, 0, 0, 0, 0, 14848, 95, 132, 9898, 9932, 134, 135, 0, 175, 175, 0, 190, 0, 0, 0, 0, 265, 265",
      /* 5418 */ "90, 276, 269, 269, 269, 190, 289, 280, 9898, 276, 285, 293, 285, 1725, 371, 400, 400, 389, 389, 389",
      /* 5438 */ "425, 290, 432, 294, 0, 273, 400, 400, 400, 389, 454, 396, 456, 389, 371, 396, 373, 457, 371, 459",
      /* 5458 */ "373, 273, 400, 389, 444, 371, 276, 410, 396, 447, 373, 273, 389, 371, 276, 396, 373, 443, 389, 371",
      /* 5478 */ "446, 396, 373, 109, 0, 0, 0, 114, 97, 97, 0, 0, 97, 97, 97, 0, 97, 0, 0, 0, 135, 0, 0, 0, 1725, 0, 0",
      /* 5505 */ "0, 0, 5120, 0, 0, 0, 113, 5632, 0, 6656, 0, 0, 0, 0, 12288, 0, 0, 0, 0, 2048, 130, 132, 9898, 134",
      /* 5529 */ "93, 93, 0, 2048, 138, 0, 0, 0, 0, 0, 0, 14848, 14848, 132, 9898, 9898, 134, 135, 0, 2560, 175, 0",
      /* 5551 */ "1536, 0, 0, 0, 0, 264, 2048, 0, 0, 0, 0, 0, 0, 212, 212, 0, 0, 1536, 2560, 0, 1725, 0, 0, 0, 0, 0, 0",
      /* 5578 */ "17920, 0, 17920, 11264, 11264, 0, 0, 11264, 11264, 11264, 0, 130, 0, 93, 0, 0, 0, 1725, 8803, 8803",
      /* 5598 */ "8803, 8803, 0, 8803, 8803, 0, 0, 11264, 11264, 11264, 11264, 11264, 11264, 11264, 11264, 0, 20992, 0",
      /* 5616 */ "0, 0, 0, 0, 0, 93, 0, 11776, 11776, 0, 0, 0, 0, 0, 0, 100, 0, 0, 11776, 11776, 11776, 0, 11776, 0",
      /* 5640 */ "11776, 0, 0, 0, 11776, 0, 11776, 0, 130, 0, 93, 0, 0, 0, 1725, 8803, 8803, 8958, 26211, 11776, 11776",
      /* 5661 */ "0, 11776, 11776, 11776, 11776, 11776, 11776, 11776, 11776, 12288, 0, 130, 132, 93, 0, 0, 0, 1725",
      /* 5679 */ "8803, 8957, 8803, 8803, 113, 114, 155, 6656, 0, 0, 0, 0, 12288, 12288, 12288, 12288, 12288, 12288",
      /* 5697 */ "12288, 12288, 0, 184, 108, 175, 148, 150, 0, 0, 155, 1725, 0, 1725, 8803, 113, 114, 188, 1725, 0, 0",
      /* 5718 */ "0, 0, 12800, 0, 0, 0, 169, 9898, 9898, 134, 135, 0, 175, 175, 0, 1725, 0, 0, 0, 0, 0, 0, 9898, 0",
      /* 5742 */ "175, 0, 0, 150, 0, 0, 188, 1725, 0, 1725, 0, 0, 269, 90, 0, 0, 190, 0, 0, 260, 9898, 0, 0, 1725, 0",
      /* 5767 */ "0, 168, 132, 9898, 134, 135, 93, 0, 93, 0, 0, 93, 0, 93, 0, 130, 0, 93, 0, 0, 0, 100, 0, 0, 0, 0, 0",
      /* 5794 */ "94, 0, 0, 0, 273, 0, 9898, 0, 276, 1725, 0, 0, 7266, 7266, 7266, 0, 0, 7266, 7266, 0, 0, 0, 0, 279",
      /* 5818 */ "280, 9898, 284, 285, 1725, 279, 280, 0, 276, 427, 410, 427, 294, 285, 273, 290, 280, 276, 294, 285",
      /* 5838 */ "280, 279, 279, 300, 290, 9898, 285, 284, 306, 306, 1725, 280, 300, 284, 306, 294, 1725, 280, 318",
      /* 5857 */ "290, 9898, 285, 276, 294, 294, 1725, 333, 290, 285, 323, 294, 1725, 280, 279, 300, 300, 10057, 285",
      /* 5876 */ "306, 1868, 280, 300, 335, 335, 10057, 285, 306, 340, 340, 1868, 280, 300, 290, 9898, 285, 306, 294",
      /* 5895 */ "1725, 0, 335, 285, 306, 0, 340, 280, 300, 306, 0, 280, 300, 285, 306, 280, 300, 285, 306, 371, 300",
      /* 5916 */ "373, 306, 385, 385, 0, 385, 385, 402, 375, 298, 392, 0, 410, 392, 414, 396, 306, 0, 421, 371, 421",
      /* 5937 */ "421, 404, 389, 404, 371, 300, 0, 428, 373, 428, 428, 414, 396, 414, 371, 0, 284, 428, 428, 439, 396",
      /* 5958 */ "373, 279, 421, 404, 404, 371, 284, 428, 414, 404, 371, 414, 373, 404, 371, 414, 373, 279, 404, 371",
      /* 5978 */ "284, 414, 373, 404, 414, 404, 414, 0, 0, 0, 0, 15872, 0, 0, 0, 12900, 0, 130, 0, 93, 0, 0, 0, 1787",
      /* 6002 */ "8803, 8803, 8803, 8803, 0, 8803, 8803, 203, 13824, 0, 0, 13824, 0, 0, 0, 0, 16384, 16384, 0, 0, 0, 0",
      /* 6024 */ "0, 0, 0, 16384, 0, 13824, 0, 0, 5120, 0, 0, 0, 3072, 148, 0, 0, 0, 147, 149, 0, 0, 0, 190, 0, 0, 0",
      /* 6050 */ "0, 5120, 4213, 0, 0, 94, 0, 130, 0, 93, 0, 0, 0, 8803, 8803, 0, 0, 8803, 9898, 0, 0, 1725, 8803, 0",
      /* 6074 */ "14848, 0, 0, 126, 126, 14848, 0, 130, 0, 93, 0, 15360, 0, 0, 14848, 14848, 14848, 14848, 14848",
      /* 6093 */ "14943, 14848, 92, 92, 92, 92, 107, 107, 92, 107, 92, 92, 92, 92, 92, 92, 92, 107, 92, 92, 92, 107",
      /* 6115 */ "107, 92, 92, 92, 107, 5235, 92, 92, 92, 92, 0, 130, 9861, 93, 0, 0, 0, 8803, 8803, 96, 96, 8803, 113",
      /* 6138 */ "114, 1692, 6656, 0, 0, 0, 0, 19456, 0, 0, 0, 0, 0, 0, 0, 18944, 18944, 9728, 9898, 9898, 134, 135, 0",
      /* 6161 */ "175, 175, 0, 1725, 0, 0, 0, 0, 264, 0, 260, 9728, 260, 0, 1536, 0, 0, 146, 108, 148, 0, 0, 0, 1725",
      /* 6185 */ "8956, 8803, 8803, 8803, 0, 9975, 0, 175, 8803, 150, 0, 0, 1536, 1725, 0, 1725, 0, 0, 8192, 9898, 0",
      /* 6206 */ "0, 1725, 0, 0, 8803, 10003, 0, 0, 1814, 8803, 101, 101, 0, 5120, 8803, 8803, 0, 0, 9728, 260, 1536",
      /* 6227 */ "0, 0, 264, 0, 274, 270, 9898, 0, 277, 1725, 0, 0, 9861, 108, 148, 0, 0, 0, 8803, 8803, 106, 106",
      /* 6249 */ "8803, 273, 282, 9898, 276, 287, 1725, 264, 282, 291, 9898, 260, 287, 287, 295, 1725, 282, 310, 273",
      /* 6268 */ "282, 311, 9898, 287, 314, 287, 324, 315, 1725, 282, 298, 301, 301, 10057, 287, 307, 1868, 282, 301",
      /* 6287 */ "335, 335, 10057, 287, 307, 340, 340, 1868, 282, 301, 0, 335, 287, 307, 0, 340, 282, 301, 0, 287, 307",
      /* 6308 */ "0, 282, 301, 0, 287, 307, 377, 301, 382, 307, 387, 385, 0, 385, 385, 403, 376, 299, 392, 307, 0, 282",
      /* 6330 */ "301, 287, 307, 282, 301, 390, 377, 301, 394, 392, 397, 382, 307, 266, 273, 375, 400, 435, 424, 0",
      /* 6350 */ "411, 392, 415, 397, 307, 0, 400, 377, 423, 400, 377, 424, 405, 377, 301, 0, 410, 382, 430, 410, 382",
      /* 6371 */ "431, 415, 377, 270, 276, 380, 410, 440, 431, 382, 273, 402, 405, 405, 377, 276, 412, 415, 405, 377",
      /* 6391 */ "415, 382, 405, 377, 415, 382, 310, 405, 377, 314, 415, 382, 405, 415, 405, 415, 0, 0, 0, 0, 19968, 0",
      /* 6413 */ "0, 0, 0, 19968, 138, 7266, 8803, 0, 0, 8803, 8803, 0, 0, 0, 0, 113, 114, 0, 6656, 4213, 8803, 8803",
      /* 6435 */ "0, 9898, 230, 175, 0, 8803, 8803, 0, 0, 0, 212, 1024, 0, 0, 1725, 0, 175, 138, 0, 8803, 8803, 0, 0",
      /* 6458 */ "0, 5120, 8803, 8803, 0, 113, 114, 155, 1725, 8803, 8803, 8803, 0, 8926, 8803, 8803, 8803, 206, 0",
      /* 6477 */ "8803, 8803, 0, 0, 0, 212, 212, 0, 0, 1725, 138, 0, 1725, 8803, 8803, 8803, 0, 8803, 8927, 8803, 8803",
      /* 6498 */ "8803, 8803, 9898, 0, 1725, 8803, 8803, 0, 0, 212, 212, 0, 0, 1725, 10087, 285, 294, 1898, 280, 290",
      /* 6518 */ "10087, 285, 276, 294, 1725, 280, 273, 290, 328, 294, 1898, 280, 290, 285, 294, 280, 290, 0, 392, 392",
      /* 6538 */ "396, 373, 294, 419, 400, 426, 410, 373, 410, 410, 396, 396, 396, 371, 426, 276, 410, 410, 410, 396",
      /* 6558 */ "373, 443, 400, 389, 389, 371, 446, 410, 396, 0, 16384, 16384, 0, 5120, 0, 0, 0, 8803, 8806, 104, 104",
      /* 6579 */ "8803, 0, 16384, 0, 16384, 0, 0, 16384, 0, 0, 9898, 0, 1725, 0, 0, 0, 108, 148, 0, 151, 0, 0, 16896",
      /* 6602 */ "0, 0, 0, 0, 0, 0, 7266, 7266, 16896, 0, 0, 0, 0, 16896, 16896, 0, 16896, 16896, 0, 0, 16896, 16896",
      /* 6624 */ "16896, 16896, 16896, 16896, 16896, 16896, 16896, 0, 130, 0, 93, 0, 0, 0, 8803, 8807, 105, 105, 8803",
      /* 6643 */ "110, 0, 0, 0, 5120, 0, 0, 110, 129, 0, 17920, 0, 0, 0, 0, 0, 13824, 0, 0, 17920, 0, 130, 0, 93, 0, 0",
      /* 6669 */ "0, 11264, 0, 0, 0, 0, 0, 138, 0, 0, 13312, 0, 0, 0, 0, 16384, 0, 0, 0, 0, 29696, 0, 0, 0, 0, 18432",
      /* 6695 */ "0, 130, 132, 9898, 134, 135, 93, 3584, 0, 0, 155, 1725, 0, 1725, 0, 0, 9898, 260, 1725, 0, 0, 264",
      /* 6717 */ "264, 9728, 276, 260, 260, 260, 1536, 0, 19456, 0, 0, 19456, 19456, 19456, 19456, 19456, 19456, 19456",
      /* 6735 */ "19456, 19456, 0, 130, 0, 93, 0, 0, 0, 16896, 0, 0, 16896, 16896, 0, 19968, 19968, 0, 5120, 0, 0, 0",
      /* 6757 */ "28672, 28672, 0, 28672, 0, 28672, 0, 0, 0, 0, 0, 0, 8803, 0, 0, 19968, 0, 19968, 0, 19968, 19968",
      /* 6778 */ "19968, 0, 19968, 0, 0, 0, 0, 0, 0, 0, 19456, 7266, 7266, 7266, 7266, 7266, 7266, 7266, 7266, 0, 7266",
      /* 6799 */ "0, 7266, 7266, 0, 7266, 0, 0, 0, 5120, 7266, 7266, 0, 130, 0, 93, 0, 0, 0, 97, 0, 0, 0, 0, 97, 97",
      /* 6824 */ "97, 97, 97, 97, 97, 97, 113, 114, 0, 6656, 0, 8803, 8803, 0, 0, 212, 212, 0, 0, 1772, 297, 290, 280",
      /* 6847 */ "9898, 303, 294, 285, 1725, 10057, 285, 339, 1868, 280, 290, 335, 335, 437, 0, 276, 410, 410, 410",
      /* 6866 */ "396, 442, 175, 0, 1725, 8803, 8803, 8803, 0, 8803, 0, 8803, 8803, 0, 8803, 8803, 8868, 0, 8803, 8803",
      /* 6886 */ "23651, 8803, 0, 9898, 0, 8803, 8803, 9996, 0, 1807, 8803, 8803, 0, 0, 212, 212, 0, 235, 1725, 285",
      /* 6906 */ "276, 294, 1725, 327, 273, 290, 290, 9898, 330, 276, 294, 294, 1725, 280, 290, 0, 335, 285, 294, 0",
      /* 6926 */ "340, 280, 358, 461, 371, 463, 373, 389, 371, 396, 373, 465, 371, 467, 373, 309, 273, 273, 290, 290",
      /* 6946 */ "9898, 313, 276, 287, 315, 1725, 282, 319, 311, 9898, 285, 276, 294, 331, 1725, 280, 290, 10057, 285",
      /* 6965 */ "294, 1868, 280, 344, 335, 335, 10057, 285, 348, 340, 340, 1868, 280, 290, 273, 400, 389, 389, 445",
      /* 6984 */ "276, 410, 396, 453, 371, 455, 373, 389, 371, 396, 373, 473, 371, 475, 373, 10057, 338, 294, 1868",
      /* 7003 */ "280, 290, 335, 335, 366, 0, 280, 290, 285, 294, 280, 290, 175, 0, 1797, 8803, 8803, 8803, 0, 8803",
      /* 7023 */ "8867, 8803, 0, 8803, 8803, 8803, 0, 8803, 8871, 276, 294, 294, 1725, 317, 273, 290, 9898, 285, 276",
      /* 7042 */ "325, 294, 1725, 280, 290, 9898, 284, 285, 285, 294, 1725, 322, 276, 294, 1725, 280, 273, 290, 290",
      /* 7061 */ "10057, 285, 294, 340, 340, 1868, 280, 352, 0, 335, 285, 355, 0, 340, 280, 290, 139, 0, 0, 0, 0, 0, 0",
      /* 7084 */ "0, 29696, 153, 154, 0, 6656, 0, 0, 0, 0, 126, 14848, 14848, 14848, 14848, 14848, 0, 0, 0, 14848, 0",
      /* 7105 */ "176, 138, 0, 0, 0, 0, 0, 130, 0, 93, 0, 0, 0, 1536, 0, 0, 0, 0, 5120, 0, 0, 16896, 0, 132, 108, 176",
      /* 7131 */ "148, 150, 0, 0, 155, 1725, 0, 1725, 8921, 132, 9898, 9898, 134, 135, 0, 176, 175, 0, 1725, 0, 0, 0",
      /* 7153 */ "0, 266, 176, 0, 0, 0, 0, 0, 0, 212, 176, 0, 1725, 0, 0, 0, 0, 0, 130, 0, 93, 14336, 0, 0, 90, 269",
      /* 7179 */ "190, 0, 0, 265, 10065, 285, 294, 1878, 280, 290, 346, 346, 10057, 285, 294, 350, 350, 1868, 280, 290",
      /* 7199 */ "285, 294, 378, 290, 383, 294, 388, 388, 0, 388, 385, 406, 378, 290, 395, 378, 371, 290, 395, 395",
      /* 7219 */ "383, 373, 294, 280, 290, 285, 294, 386, 386, 0, 395, 392, 416, 383, 294, 0, 422, 378, 422, 422, 406",
      /* 7240 */ "406, 389, 378, 290, 0, 429, 383, 429, 429, 416, 416, 396, 383, 294, 0, 273, 422, 400, 422, 406, 378",
      /* 7261 */ "416, 383, 406, 378, 416, 383, 378, 0, 276, 429, 410, 429, 416, 383, 273, 406, 378, 276, 416, 383",
      /* 7281 */ "273, 422, 406, 406, 378, 276, 429, 416, 406, 416, 406, 416, 0, 0, 0, 0, 130, 0, 134, 0, 0, 0, 108",
      /* 7304 */ "148, 150, 0, 0, 155, 1536, 1536, 1536, 0, 122, 122, 0, 0, 122, 122, 122, 0, 0, 9898, 270, 1725, 0, 0",
      /* 7327 */ "266, 122, 20602, 20602, 20602, 20602, 20602, 20602, 20602, 0, 130, 0, 93, 0, 0, 0, 130, 0, 4608, 0",
      /* 7347 */ "0, 0, 130, 132, 90, 134, 135, 93, 138, 7266, 8803, 0, 0, 8803, 8846, 0, 0, 9898, 8192, 1725, 0, 0",
      /* 7369 */ "7680, 144, 0, 0, 108, 148, 0, 0, 0, 130, 132, 9898, 93, 135, 93, 174, 175, 138, 0, 8803, 8803, 0, 0",
      /* 7392 */ "96, 5120, 8803, 8803, 0, 113, 114, 155, 1725, 8896, 8803, 8803, 0, 0, 212, 212, 234, 0, 1725, 8803",
      /* 7412 */ "8942, 8803, 8803, 0, 8803, 8803, 8803, 0, 8803, 8803, 8928, 8803, 113, 114, 155, 1725, 8803, 8803",
      /* 7430 */ "8899, 0, 0, 10240, 0, 0, 0, 0, 0, 0, 9728, 0, 175, 0, 0, 138, 7266, 8803, 0, 140, 8803, 8803, 0, 112",
      /* 7454 */ "106, 5120, 8803, 8803, 0, 130, 0, 93, 0, 0, 0, 130, 132, 9728, 134, 135, 93, 8865, 0, 8803, 8803",
      /* 7475 */ "8803, 0, 8803, 8803, 8803, 0, 8870, 8803, 280, 273, 273, 290, 290, 10040, 285, 276, 294, 1725, 280",
      /* 7494 */ "273, 290, 290, 276, 294, 294, 1852, 280, 273, 290, 9898, 285, 294, 1725, 280, 290, 345, 345, 371",
      /* 7513 */ "371, 391, 392, 392, 373, 373, 398, 138, 7266, 8803, 0, 0, 8845, 8803, 143, 113, 114, 0, 6656, 0",
      /* 7533 */ "8803, 8803, 160, 183, 132, 108, 175, 148, 150, 0, 0, 155, 1725, 0, 1752, 0, 206, 0, 8912, 8803, 210",
      /* 7554 */ "0, 28160, 212, 8936, 0, 233, 212, 212, 0, 0, 1725, 8803, 8803, 8943, 8803, 0, 27235, 8803, 8803, 228",
      /* 7574 */ "9898, 0, 231, 0, 8803, 8961, 8803, 8803, 0, 9898, 0, 175, 0, 8803, 138, 0, 1725, 8803, 25187, 8803",
      /* 7594 */ "0, 8803, 111, 8803, 111, 8803, 8803, 111, 8803, 112, 8803, 112, 8803, 8803, 112, 8803, 120, 8803",
      /* 7612 */ "120, 8803, 8803, 120, 8803, 8803, 228, 9898, 0, 231, 27136, 8803, 111, 111, 0, 5120, 8803, 8803, 0",
      /* 7631 */ "0, 14848, 0, 0, 0, 0, 0, 97, 0, 0, 0, 0, 0, 0, 10240, 0, 8803, 8803, 9898, 0, 1725, 8803, 8976, 0, 0",
      /* 7656 */ "17408, 0, 0, 0, 17408, 0, 0, 17408, 0, 17408, 17408, 0, 130, 0, 93, 0, 0, 0, 9898, 0, 0, 1725, 0",
      /* 7679 */ "8831, 8803, 8803, 8803, 8803, 8803, 8803, 8803, 0, 8803, 8803, 21603, 113, 114, 155, 1725, 8803",
      /* 7696 */ "8897, 8803, 0, 130, 0, 93, 0, 0, 137, 150, 214, 0, 155, 1725, 0, 1725, 8803, 8803, 228, 9957, 0, 231",
      /* 7718 */ "0, 8803, 101, 8803, 101, 8803, 8803, 101, 8825, 8803, 8803, 8803, 8803, 8803, 8803, 8803, 221, 8803",
      /* 7736 */ "8803, 8803, 8803, 246, 9898, 0, 249, 8803, 8803, 8931, 0, 9898, 0, 175, 0, 8803, 8913, 0, 211, 0",
      /* 7756 */ "212, 8803, 8902, 8803, 8803, 0, 8803, 8803, 0, 8866, 8803, 8803, 0, 8803, 8803, 8861, 0, 8803, 8803",
      /* 7775 */ "8803, 8803, 8924, 0, 8803, 8803, 8803, 8803, 0, 9898, 0, 175, 8803, 8947, 8803, 8803, 0, 9898, 0",
      /* 7794 */ "175, 8803, 8803, 8803, 8832, 8803, 8803, 8803, 8803, 8803, 8803, 8803, 132, 9898, 9898, 134, 135",
      /* 7811 */ "205, 175, 175, 0, 1725, 0, 0, 0, 0, 7680, 8941, 8803, 8803, 8803, 0, 8803, 8803, 8803, 8929, 0, 250",
      /* 7832 */ "0, 1725, 8803, 8803, 8803, 8803, 0, 8803, 8946, 8803, 255, 8803, 8803, 8803, 8962, 0, 9898, 0, 0",
      /* 7851 */ "27648, 1725, 8803, 8803, 8803, 8803, 246, 9898, 248, 249, 8803, 175, 0, 1725, 8966, 8803, 8803, 0",
      /* 7869 */ "8803, 8803, 8803, 8904, 0, 8803, 8803, 0, 0, 17920, 0, 0, 0, 17920, 0, 0, 0, 0, 0, 165, 0, 0, 0, 175",
      /* 7893 */ "138, 178, 8803, 8803, 0, 0, 104, 5120, 8803, 8803, 0, 113, 114, 155, 1725, 8803, 8898, 8803, 0, 90",
      /* 7913 */ "90, 90, 90, 90, 90, 90, 90, 8901, 8803, 8803, 8803, 0, 8803, 8803, 0, 91, 0, 0, 0, 0, 0, 0, 90, 0",
      /* 7937 */ "175, 0, 0, 150, 0, 215, 155, 1725, 0, 1725, 8803, 8803, 8803, 8944, 0, 8803, 8803, 8803, 8803, 130",
      /* 7957 */ "9898, 0, 8930, 8803, 0, 9898, 0, 175, 0, 8803, 8803, 8822, 8829, 8803, 8803, 8803, 8822, 0, 8803, 0",
      /* 7977 */ "8822, 8822, 0, 8822, 8803, 8803, 8803, 8803, 8803, 8803, 8803, 8803, 8831, 8803, 8803, 8949, 0, 9898",
      /* 7995 */ "0, 175, 8803, 8803, 8823, 8823, 8803, 8803, 8803, 8823, 0, 8803, 0, 8823, 8823, 0, 8823, 8803, 8803",
      /* 8014 */ "8803, 8803, 8803, 8803, 8803, 0, 8905, 8803, 0, 175, 207, 8803, 8803, 0, 0, 0, 212, 0, 145, 0, 108",
      /* 8035 */ "148, 0, 0, 0, 130, 132, 9898, 134, 135, 93, 113, 114, 0, 6656, 0, 8861, 8862, 0, 92, 92, 92, 92, 92",
      /* 8058 */ "92, 92, 92, 0, 175, 138, 0, 8803, 8884, 0, 182, 8803, 24064, 0, 212, 212, 0, 0, 1725, 8803, 8948",
      /* 8079 */ "8803, 0, 9898, 0, 175, 24675, 24576, 0, 0, 1725, 8803, 8803, 8803, 8803, 241, 8803, 8803, 8803, 0",
      /* 8098 */ "335, 285, 294, 0, 340, 357, 290, 0, 360, 294, 0, 280, 290, 0, 285, 361, 0, 280, 290, 0, 285, 306, 0",
      /* 8121 */ "280, 300, 0, 285, 294, 0, 280, 290, 0, 285, 294, 0, 280, 364, 0, 285, 294, 0, 363, 290, 0, 365, 285",
      /* 8144 */ "294, 371, 379, 373, 384, 385, 385, 0, 385, 385, 8069, 8051, 7970, 392, 389, 470, 396, 472, 389, 371",
      /* 8164 */ "396, 373, 481, 371, 482, 373, 477, 371, 479, 373, 389, 371, 396, 373, 101, 0, 0, 8803, 8803, 0, 0",
      /* 8185 */ "8803, 9898, 0, 0, 1725, 25699, 0, 8960, 8803, 8803, 8803, 0, 9898, 0, 175, 0, 24163, 175, 260, 1725",
      /* 8205 */ "8803, 8803, 8803, 26624, 8803, 8803, 8825, 8803, 8803, 8803, 8803, 8825, 8803, 8971, 9898, 0, 1725",
      /* 8222 */ "8803, 8803, 0, 94, 0, 94, 94, 94, 0, 94, 94, 94, 94, 94, 94, 94, 94, 280, 280, 10020, 276, 285, 285",
      /* 8245 */ "285, 1832, 10057, 285, 294, 340, 340, 1868, 351, 290, 0, 335, 354, 294, 0, 340, 280, 290, 294, 0",
      /* 8265 */ "280, 290, 285, 294, 280, 372, 285, 374, 371, 290, 373, 294, 385, 385, 0, 400, 385, 404, 389, 300",
      /* 8285 */ "392, 113, 114, 155, 1725, 8803, 8803, 8803, 196, 8803, 8803, 8903, 8803, 0, 8803, 8906, 0, 132, 108",
      /* 8304 */ "0, 148, 150, 0, 0, 155, 1725, 0, 1725, 0, 8803, 8923, 8803, 0, 8803, 8803, 8803, 8803, 0, 9898",
      /* 8324 */ "23040, 175, 0, 1725, 8803, 8803, 8967, 0, 8803, 8803, 8827, 8803, 8803, 8803, 8803, 8827, 8803, 8803",
      /* 8342 */ "8803, 8803, 8803, 8803, 8803, 8828, 8803, 8803, 8803, 8803, 0, 8803, 8803, 8803, 8803, 8803, 8803",
      /* 8359 */ "9898, 0, 1725, 22627, 8803, 0, 132, 108, 175, 148, 150, 0, 0, 155, 190, 0, 190, 0, 113, 114, 0, 6656",
      /* 8381 */ "0, 8803, 8863, 0, 132, 108, 175, 148, 150, 0, 187, 0, 175, 177, 0, 8803, 8803, 0, 0, 105, 5120, 8803",
      /* 8403 */ "8803, 0, 8922, 8803, 8803, 0, 8803, 8803, 8803, 8803, 0, 9987, 0, 0, 175, 138, 0, 8883, 8803, 181, 0",
      /* 8424 */ "132, 108, 175, 148, 150, 186, 0, 122, 122, 122, 122, 122, 20602, 122, 122, 122, 122, 122, 22115",
      /* 8443 */ "8803, 9898, 0, 1725, 8803, 8803, 0, 132, 108, 175, 148, 185, 0, 0, 93, 0, 0, 0, 0, 0, 28672, 0, 0, 0",
      /* 8467 */ "28672, 28672, 0, 0, 28672, 28672, 28672, 0, 130, 0, 93, 0, 0, 0, 130, 132, 9898, 172, 173, 93, 0",
      /* 8488 */ "28672, 28672, 28672, 28672, 28672, 28672, 28672, 28672, 273, 7960, 9898, 276, 8477, 1725, 273, 7960",
      /* 8504 */ "7953, 273, 7970, 7970, 9898, 8477, 8468, 7960, 7960, 9898, 276, 8477, 8477, 8477, 1725, 276, 8486",
      /* 8521 */ "8486, 1725, 7960, 273, 7970, 9898, 287, 304, 307, 307, 1725, 282, 301, 291, 9898, 287, 307, 295",
      /* 8539 */ "1725, 9898, 8477, 276, 8486, 8486, 1725, 7960, 7970, 7960, 9898, 8477, 8486, 8477, 1725, 10057, 8477",
      /* 8556 */ "8486, 1868, 7960, 7970, 335, 335, 10057, 8477, 8486, 340, 340, 1868, 7960, 7970, 0, 335, 8477, 8486",
      /* 8574 */ "0, 340, 7960, 7970, 0, 8477, 8486, 0, 7960, 7970, 0, 8477, 276, 8486, 1725, 7960, 273, 7970, 7970",
      /* 8593 */ "8486, 0, 7960, 7970, 8477, 8486, 7960, 7970, 8477, 8486, 8051, 7970, 8565, 8486, 8065, 385, 0, 401",
      /* 8611 */ "385, 405, 390, 301, 392, 8051, 8051, 7970, 8584, 392, 8565, 8565, 8486, 7680, 273, 400, 400, 400",
      /* 8629 */ "8069, 0, 392, 392, 8588, 8565, 8486, 0, 400, 8051, 8080, 400, 8069, 8069, 8069, 8051, 7970, 0, 410",
      /* 8648 */ "8565, 8602, 410, 8588, 8588, 8588, 8051, 8192, 276, 410, 410, 410, 8588, 8565, 7953, 8069, 8051",
      /* 8665 */ "8468, 8588, 8565, 273, 400, 8069, 8069, 8051, 276, 410, 8588, 8069, 8051, 8588, 8565, 8069, 8051",
      /* 8682 */ "8588, 8565, 8069, 8588, 8069, 8588, 0, 0, 0, 0, 130, 132, 9899, 134, 135, 93, 0, 29184, 0, 0, 0, 0",
      /* 8704 */ "0, 0, 0, 29184, 29184, 29184, 29184, 29184, 29184, 29184, 29184, 0, 29184, 29184, 29184, 29184",
      /* 8720 */ "29184, 0, 29184, 29184, 0, 130, 0, 93, 0, 0, 0, 130, 169, 9898, 134, 135, 93, 0, 30208, 0, 0, 0, 0",
      /* 8743 */ "30208, 0, 0, 0, 0, 0, 30208, 30208, 30208, 30208, 0, 0, 0, 30208, 30208, 30208, 30208, 30208, 30208",
      /* 8762 */ "30208, 30208, 0, 130, 0, 93, 0, 0, 0, 131, 0, 136, 0, 0, 0, 100, 0, 12800, 12800, 12800, 12800",
      /* 8783 */ "12800, 12800, 12800, 12900"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 8787; ++i) {TRANSITION[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] EXPECTED = new int[586];
  static
  {
    final String s1[] =
    {
      /*   0 */ "16, 32, 48, 64, 80, 111, 95, 127, 143, 159, 175, 191, 204, 204, 204, 204, 220, 224, 237, 228, 232",
      /*  21 */ "236, 242, 249, 253, 256, 260, 264, 245, 271, 268, 275, 279, 286, 282, 290, 294, 297, 300, 311, 325",
      /*  41 */ "393, 532, 464, 329, 333, 335, 340, 455, 458, 452, 394, 533, 464, 347, 307, 369, 341, 238, 461, 392",
      /*  61 */ "531, 465, 349, 307, 369, 342, 391, 395, 355, 306, 368, 376, 303, 560, 306, 336, 343, 373, 334, 385",
      /*  81 */ "351, 419, 364, 361, 399, 361, 405, 360, 405, 401, 362, 405, 360, 405, 401, 402, 413, 417, 401, 401",
      /* 101 */ "413, 413, 417, 401, 404, 413, 401, 404, 417, 404, 412, 399, 363, 409, 363, 358, 411, 358, 363, 361",
      /* 121 */ "399, 417, 417, 417, 402, 413, 403, 403, 403, 403, 403, 403, 403, 403, 320, 423, 424, 314, 429, 433",
      /* 141 */ "437, 476, 441, 445, 449, 469, 473, 480, 484, 488, 492, 495, 499, 503, 507, 511, 515, 519, 520, 424",
      /* 161 */ "319, 547, 380, 424, 578, 524, 528, 379, 424, 542, 320, 548, 380, 424, 537, 582, 529, 424, 541, 318",
      /* 181 */ "546, 381, 425, 554, 582, 530, 317, 321, 381, 553, 581, 568, 558, 388, 553, 566, 520, 425, 564, 572",
      /* 201 */ "575, 424, 549, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 424, 0",
      /* 221 */ "262152, 262400, 2359296, 4456448, 134479872, 268697600, 537133056, 262144, 262168, 335872, 270336",
      /* 232 */ "71565312, 4456448, 33816576, 67371008, -2147221504, -2147221504, 262144, 262144, 262144, 256, 262144",
      /* 243 */ "262144, 4530176, 335872, -2147188736, 270868504, -2147188480, 25427968, 67371008, 268697600",
      /* 252 */ "268697600, -2147221504, -2147221504, 2367512, 335872, 262144, 262146, 268771352, 335984, -2080079872",
      /* 262 */ "-2147188736, 262146, 7684, 8724632, 1384472, 138674272, 7675928, 270868504, 352258, 466946, 335874",
      /* 273 */ "-2146664192, 335874, 1081417752, 1211441176, 275062808, 352258, 385026, 141893656, 1081417752",
      /* 282 */ "409280536, 409280536, 304947480, -1738170344, 405086232, 409280536, 304947480, -1738203112, 167059480",
      /* 291 */ "167059736, 167059480, 434446360, 167059736, 434970648, 434970904, 434970648, 434970904, 971841560",
      /* 300 */ "468525336, 262144, 8, 262144, 0, 24, 65536, 65536, 65536, 65536, 0, 256, 134217728, 536870912, 0, 1",
      /* 316 */ "4, 4096, 0, 1048576, 16384, 32768, 16384, 32768, 0, 24, 8192, 65536, 33554432, 2048, 1024, 4, 4096",
      /* 333 */ "128, 65536, 65536, 0, 81920, 65536, 65536, 65536, 1073741824, 65536, 98304, 8, 262144, 0, 2048, 1024",
      /* 349 */ "4, 4, 4, 65536, 65536, 16384, 2, 32768, 0, 4, 4, 16384, 16384, 16384, 262144, 32768, 32768, 4, 65536",
      /* 368 */ "0, 65536, 81920, 65536, 65536, 24, 32768, 4, 65536, 65536, 98304, 8, 0, 0, 0, 2097152, 0, 65536",
      /* 386 */ "98304, 262144, 32768, 0, 2097152, 0, 24, 0, 65536, 65536, 0, 0, 2, 32768, 4, 16384, 16384, 16384",
      /* 404 */ "16384, 32768, 32768, 32768, 4, 16384, 16384, 262144, 262144, 32768, 32768, 32768, 32768, 32768, 32768",
      /* 419 */ "16384, 16384, 98304, 262144, 32, 0, 0, 0, 0, 2048, 8388608, 0, 0, 33554432, 0, 16777216, 4, 1, 64",
      /* 438 */ "8388608, 5, 17, 32, 5, 17, 20, 1048640, 8388672, 0, 8388672, 49152, 4247552, 4247552, 0, 24, 24, 0, 8",
      /* 457 */ "8, 262144, 256, 256, 256, 0, 16, 16, 64, 32768, 0, 4, 0, 1048640, 3145792, 12636160, 41943040, 0",
      /* 475 */ "8388608, 16777217, 8388672, 0, 33554432, 460800, 3670080, 67108864, 3670080, 460800, 460864, 3670080",
      /* 487 */ "1509440, 8388864, 75497728, 1509440, 462336, 8388864, 8388864, 75497728, 1517632, 25166081, 25166081",
      /* 498 */ "92274944, 92274945, 92274945, 75497860, 92274976, 92274953, 92274977, 75497862, 92275008, 25166113",
      /* 508 */ "25166113, 25166115, 92274977, 25166115, 92275105, 92275105, 92275107, 92275113, 92275107, 92275107",
      /* 518 */ "92275109, 92275111, 0, 0, 0, 4096, 1048576, 256, 0, 512, 3072, 0, 8192, 0, 0, 0, 262146, 32, 16, 2048",
      /* 538 */ "196608, 262144, 524288, 0, 4096, 0, 0, 1048576, 16384, 32768, 4194304, 0, 0, 0, 131072, 2048, 65536",
      /* 555 */ "131072, 262144, 524288, 0, 4096, 0, 32768, 0, 4, 65536, 131072, 524288, 0, 1024, 8192, 0, 0, 1024, 0",
      /* 574 */ "0, 0, 2048, 131072, 0, 2048, 458752, 524288, 1048576, 0, 512, 1024"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 586; ++i) {EXPECTED[i] = Integer.parseInt(s2[i]);}
  }

  private static final String[] TOKEN =
  {
    "(0)",
    "DOC_COMMENT",
    "COMMENT",
    "CHAR_LITERAL",
    "STRING_LITERAL",
    "DOUBLE_QUOTE_STRING_LITERAL",
    "DOUBLE_ANGLE_STRING_LITERAL",
    "INT",
    "ARG_ACTION",
    "NESTED_ACTION_char",
    "ACTION_CHAR_LITERAL",
    "ACTION_STRING_LITERAL",
    "ACTION_ESC",
    "TOKEN_REF",
    "TOKENS",
    "OPTIONS",
    "RULE_REF",
    "EOF",
    "WHITESPACE",
    "'!'",
    "'$'",
    "'('",
    "')'",
    "'*'",
    "'+'",
    "'+='",
    "','",
    "'->'",
    "'.'",
    "'..'",
    "'...'",
    "':'",
    "';'",
    "'<'",
    "'='",
    "'=>'",
    "'>'",
    "'?'",
    "'@'",
    "'^'",
    "'^('",
    "'catch'",
    "'finally'",
    "'fragment'",
    "'grammar'",
    "'import'",
    "'lexer'",
    "'parser'",
    "'private'",
    "'protected'",
    "'public'",
    "'returns'",
    "'scope'",
    "'throws'",
    "'tree'",
    "'{'",
    "'|'",
    "'}'",
    "'~'"
  };
}

// End
