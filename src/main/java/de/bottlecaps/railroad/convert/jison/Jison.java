// This file was generated on Sat Jul 17, 2021 11:14 (UTC+02) by REx v5.53 which is Copyright (c) 1979-2021 by Gunther Rademacher <grd@gmx.net>
// REx command line: -tree -a none -java -interface de.bottlecaps.railroad.convert.Parser -saxon10 -name de.bottlecaps.railroad.convert.jison.Jison jison.ebnf

package de.bottlecaps.railroad.convert.jison;

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

public class Jison implements de.bottlecaps.railroad.convert.Parser
{
  public static class SaxonInitializer implements Initializer
  {
    @Override
    public void initialize(Configuration conf)
    {
      conf.registerExtensionFunction(new SaxonDefinition_jison());
    }
  }

  public static Sequence parseJison(XPathContext context, String input) throws XPathException
  {
    Builder builder = context.getController().makeBuilder();
    builder.open();
    Jison parser = new Jison(input, new SaxonTreeBuilder(builder));
    try
    {
      parser.parse_jison();
    }
    catch (ParseException pe)
    {
      buildError(parser, pe, builder);
    }
    return builder.getCurrentRoot();
  }

  public static class SaxonDefinition_jison extends SaxonDefinition
  {
    @Override
    public String functionName() {return "parse-jison";}
    @Override
    public Sequence execute(XPathContext context, String input) throws XPathException
    {
      return parseJison(context, input);
    }
  }

  public static abstract class SaxonDefinition extends ExtensionFunctionDefinition
  {
    abstract String functionName();
    abstract Sequence execute(XPathContext context, String input) throws XPathException;

    @Override
    public StructuredQName getFunctionQName() {return new StructuredQName("p", "Jison", functionName());}
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

  private static void buildError(Jison parser, ParseException pe, Builder builder) throws XPathException
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

  public Jison(CharSequence string, EventHandler t)
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
    parse_jison();
  }

  public void parse_jison()
  {
    eventHandler.startNonterminal("jison", e0);
    lookahead1W(24);                // START | LEX_BLOCK | ACTION | LEFT | RIGHT | NONASSOC | TOKEN | whitespace |
                                    // comment | '%%' | '{'
    switch (l1)
    {
    case 31:                        // '{'
      parse_json();
      break;
    default:
      parse_spec();
    }
    eventHandler.endNonterminal("jison", e0);
  }

  private void parse_spec()
  {
    eventHandler.startNonterminal("spec", e0);
    parse_declaration_list();
    consume(22);                    // '%%'
    lookahead1W(0);                 // ID | whitespace | comment
    whitespace();
    parse_grammar();
    if (l1 == 22)                   // '%%'
    {
      consume(22);                  // '%%'
      lookahead1W(9);               // CODE | EOF | whitespace | comment
      if (l1 == 1)                  // CODE
      {
        consume(1);                 // CODE
      }
    }
    lookahead1W(1);                 // EOF | whitespace | comment
    consume(14);                    // EOF
    eventHandler.endNonterminal("spec", e0);
  }

  private void parse_declaration_list()
  {
    eventHandler.startNonterminal("declaration_list", e0);
    for (;;)
    {
      lookahead1W(21);              // START | LEX_BLOCK | ACTION | LEFT | RIGHT | NONASSOC | TOKEN | whitespace |
                                    // comment | '%%'
      if (l1 == 22)                 // '%%'
      {
        break;
      }
      whitespace();
      parse_declaration();
    }
    eventHandler.endNonterminal("declaration_list", e0);
  }

  private void parse_declaration()
  {
    eventHandler.startNonterminal("declaration", e0);
    switch (l1)
    {
    case 2:                         // START
      consume(2);                   // START
      lookahead1W(0);               // ID | whitespace | comment
      whitespace();
      parse_id();
      break;
    case 3:                         // LEX_BLOCK
      consume(3);                   // LEX_BLOCK
      break;
    case 4:                         // ACTION
      consume(4);                   // ACTION
      break;
    default:
      parse_operator();
    }
    eventHandler.endNonterminal("declaration", e0);
  }

  private void parse_operator()
  {
    eventHandler.startNonterminal("operator", e0);
    parse_associativity();
    lookahead1W(10);                // STRING | ID | whitespace | comment
    whitespace();
    parse_token_list();
    eventHandler.endNonterminal("operator", e0);
  }

  private void parse_associativity()
  {
    eventHandler.startNonterminal("associativity", e0);
    switch (l1)
    {
    case 5:                         // LEFT
      consume(5);                   // LEFT
      break;
    case 6:                         // RIGHT
      consume(6);                   // RIGHT
      break;
    case 7:                         // NONASSOC
      consume(7);                   // NONASSOC
      break;
    default:
      consume(8);                   // TOKEN
    }
    eventHandler.endNonterminal("associativity", e0);
  }

  private void parse_token_list()
  {
    eventHandler.startNonterminal("token_list", e0);
    for (;;)
    {
      whitespace();
      parse_symbol();
      lookahead1W(25);              // START | LEX_BLOCK | ACTION | LEFT | RIGHT | NONASSOC | TOKEN | STRING | ID |
                                    // whitespace | comment | '%%'
      if (l1 != 10                  // STRING
       && l1 != 11)                 // ID
      {
        break;
      }
    }
    eventHandler.endNonterminal("token_list", e0);
  }

  private void parse_grammar()
  {
    eventHandler.startNonterminal("grammar", e0);
    parse_production_list();
    eventHandler.endNonterminal("grammar", e0);
  }

  private void parse_production_list()
  {
    eventHandler.startNonterminal("production_list", e0);
    for (;;)
    {
      whitespace();
      parse_production();
      lookahead1W(17);              // ID | EOF | whitespace | comment | '%%'
      if (l1 != 11)                 // ID
      {
        break;
      }
    }
    eventHandler.endNonterminal("production_list", e0);
  }

  private void parse_production()
  {
    eventHandler.startNonterminal("production", e0);
    parse_id();
    lookahead1W(6);                 // whitespace | comment | ':'
    consume(24);                    // ':'
    lookahead1W(22);                // ACTION | PREC | STRING | ID | ARROW_ACTION | whitespace | comment | ';' | '{' |
                                    // '|'
    whitespace();
    parse_handle_list();
    consume(25);                    // ';'
    eventHandler.endNonterminal("production", e0);
  }

  private void parse_handle_list()
  {
    eventHandler.startNonterminal("handle_list", e0);
    parse_handle_action();
    for (;;)
    {
      lookahead1W(16);              // whitespace | comment | ';' | '|'
      if (l1 != 32)                 // '|'
      {
        break;
      }
      consume(32);                  // '|'
      lookahead1W(22);              // ACTION | PREC | STRING | ID | ARROW_ACTION | whitespace | comment | ';' | '{' |
                                    // '|'
      whitespace();
      parse_handle_action();
    }
    eventHandler.endNonterminal("handle_list", e0);
  }

  private void parse_handle_action()
  {
    eventHandler.startNonterminal("handle_action", e0);
    parse_handle();
    whitespace();
    parse_prec();
    lookahead1W(19);                // ACTION | ARROW_ACTION | whitespace | comment | ';' | '{' | '|'
    whitespace();
    parse_action();
    eventHandler.endNonterminal("handle_action", e0);
  }

  private void parse_handle()
  {
    eventHandler.startNonterminal("handle", e0);
    for (;;)
    {
      lookahead1W(22);              // ACTION | PREC | STRING | ID | ARROW_ACTION | whitespace | comment | ';' | '{' |
                                    // '|'
      if (l1 != 10                  // STRING
       && l1 != 11)                 // ID
      {
        break;
      }
      whitespace();
      parse_symbol();
    }
    eventHandler.endNonterminal("handle", e0);
  }

  private void parse_prec()
  {
    eventHandler.startNonterminal("prec", e0);
    switch (l1)
    {
    case 9:                         // PREC
      consume(9);                   // PREC
      lookahead1W(10);              // STRING | ID | whitespace | comment
      whitespace();
      parse_symbol();
      break;
    default:
      break;
    }
    eventHandler.endNonterminal("prec", e0);
  }

  private void parse_symbol()
  {
    eventHandler.startNonterminal("symbol", e0);
    switch (l1)
    {
    case 11:                        // ID
      parse_id();
      break;
    default:
      consume(10);                  // STRING
    }
    eventHandler.endNonterminal("symbol", e0);
  }

  private void parse_id()
  {
    eventHandler.startNonterminal("id", e0);
    consume(11);                    // ID
    eventHandler.endNonterminal("id", e0);
  }

  private void parse_action()
  {
    eventHandler.startNonterminal("action", e0);
    switch (l1)
    {
    case 31:                        // '{'
      parse_action_body();
      break;
    case 4:                         // ACTION
      consume(4);                   // ACTION
      break;
    case 12:                        // ARROW_ACTION
      consume(12);                  // ARROW_ACTION
      break;
    default:
      break;
    }
    eventHandler.endNonterminal("action", e0);
  }

  private void parse_action_body()
  {
    eventHandler.startNonterminal("action_body", e0);
    consume(31);                    // '{'
    for (;;)
    {
      lookahead1W(18);              // ACTION_BODY | whitespace | comment | '{' | '}'
      if (l1 == 33)                 // '}'
      {
        break;
      }
      switch (l1)
      {
      case 13:                      // ACTION_BODY
        consume(13);                // ACTION_BODY
        break;
      default:
        whitespace();
        parse_action_body();
      }
    }
    consume(33);                    // '}'
    eventHandler.endNonterminal("action_body", e0);
  }

  private void parse_json()
  {
    eventHandler.startNonterminal("json", e0);
    consume(31);                    // '{'
    lookahead1W(13);                // whitespace | comment | non-bnf-string | '"bnf"'
    whitespace();
    parse_leading_non_bnf_pairs();
    lookahead1W(5);                 // whitespace | comment | '"bnf"'
    whitespace();
    parse_bnf_pair();
    lookahead1W(15);                // whitespace | comment | ',' | '}'
    whitespace();
    parse_trailing_non_bnf_pairs();
    consume(33);                    // '}'
    lookahead1W(4);                 // whitespace | comment | eof
    consume(20);                    // eof
    eventHandler.endNonterminal("json", e0);
  }

  private void parse_leading_non_bnf_pairs()
  {
    eventHandler.startNonterminal("leading-non-bnf-pairs", e0);
    if (l1 == 18)                   // non-bnf-string
    {
      whitespace();
      parse_non_bnf_pairs();
      consume(23);                  // ','
    }
    eventHandler.endNonterminal("leading-non-bnf-pairs", e0);
  }

  private void parse_non_bnf_pairs()
  {
    eventHandler.startNonterminal("non-bnf-pairs", e0);
    parse_non_bnf_pair();
    for (;;)
    {
      lookahead1W(15);              // whitespace | comment | ',' | '}'
      switch (l1)
      {
      case 23:                      // ','
        lookahead2W(13);            // whitespace | comment | non-bnf-string | '"bnf"'
        break;
      default:
        lk = l1;
      }
      if (lk != 1175)               // ',' non-bnf-string
      {
        break;
      }
      consume(23);                  // ','
      lookahead1W(3);               // whitespace | comment | non-bnf-string
      whitespace();
      parse_non_bnf_pair();
    }
    eventHandler.endNonterminal("non-bnf-pairs", e0);
  }

  private void parse_non_bnf_pair()
  {
    eventHandler.startNonterminal("non-bnf-pair", e0);
    consume(18);                    // non-bnf-string
    lookahead1W(6);                 // whitespace | comment | ':'
    consume(24);                    // ':'
    lookahead1W(20);                // whitespace | comment | string | number | '[' | 'false' | 'null' | 'true' | '{'
    whitespace();
    parse_value();
    eventHandler.endNonterminal("non-bnf-pair", e0);
  }

  private void parse_value()
  {
    eventHandler.startNonterminal("value", e0);
    switch (l1)
    {
    case 17:                        // string
      consume(17);                  // string
      break;
    case 19:                        // number
      consume(19);                  // number
      break;
    case 31:                        // '{'
      parse_object();
      break;
    case 26:                        // '['
      parse_array();
      break;
    case 30:                        // 'true'
      consume(30);                  // 'true'
      break;
    case 28:                        // 'false'
      consume(28);                  // 'false'
      break;
    default:
      consume(29);                  // 'null'
    }
    eventHandler.endNonterminal("value", e0);
  }

  private void parse_object()
  {
    eventHandler.startNonterminal("object", e0);
    consume(31);                    // '{'
    lookahead1W(12);                // whitespace | comment | string | '}'
    if (l1 == 17)                   // string
    {
      whitespace();
      parse_pair();
      for (;;)
      {
        lookahead1W(15);            // whitespace | comment | ',' | '}'
        if (l1 != 23)               // ','
        {
          break;
        }
        consume(23);                // ','
        lookahead1W(2);             // whitespace | comment | string
        whitespace();
        parse_pair();
      }
    }
    consume(33);                    // '}'
    eventHandler.endNonterminal("object", e0);
  }

  private void parse_pair()
  {
    eventHandler.startNonterminal("pair", e0);
    consume(17);                    // string
    lookahead1W(6);                 // whitespace | comment | ':'
    consume(24);                    // ':'
    lookahead1W(20);                // whitespace | comment | string | number | '[' | 'false' | 'null' | 'true' | '{'
    whitespace();
    parse_value();
    eventHandler.endNonterminal("pair", e0);
  }

  private void parse_array()
  {
    eventHandler.startNonterminal("array", e0);
    consume(26);                    // '['
    lookahead1W(23);                // whitespace | comment | string | number | '[' | ']' | 'false' | 'null' | 'true' |
                                    // '{'
    if (l1 != 27)                   // ']'
    {
      whitespace();
      parse_value();
      for (;;)
      {
        lookahead1W(14);            // whitespace | comment | ',' | ']'
        if (l1 != 23)               // ','
        {
          break;
        }
        consume(23);                // ','
        lookahead1W(20);            // whitespace | comment | string | number | '[' | 'false' | 'null' | 'true' | '{'
        whitespace();
        parse_value();
      }
    }
    consume(27);                    // ']'
    eventHandler.endNonterminal("array", e0);
  }

  private void parse_bnf_pair()
  {
    eventHandler.startNonterminal("bnf-pair", e0);
    consume(21);                    // '"bnf"'
    lookahead1W(6);                 // whitespace | comment | ':'
    consume(24);                    // ':'
    lookahead1W(8);                 // whitespace | comment | '{'
    consume(31);                    // '{'
    lookahead1W(12);                // whitespace | comment | string | '}'
    if (l1 == 17)                   // string
    {
      whitespace();
      parse_rule_pair();
      for (;;)
      {
        lookahead1W(15);            // whitespace | comment | ',' | '}'
        if (l1 != 23)               // ','
        {
          break;
        }
        consume(23);                // ','
        lookahead1W(2);             // whitespace | comment | string
        whitespace();
        parse_rule_pair();
      }
    }
    consume(33);                    // '}'
    eventHandler.endNonterminal("bnf-pair", e0);
  }

  private void parse_rule_pair()
  {
    eventHandler.startNonterminal("rule-pair", e0);
    consume(17);                    // string
    lookahead1W(6);                 // whitespace | comment | ':'
    consume(24);                    // ':'
    lookahead1W(7);                 // whitespace | comment | '['
    consume(26);                    // '['
    lookahead1W(11);                // whitespace | comment | string | '['
    whitespace();
    parse_alternative();
    for (;;)
    {
      lookahead1W(14);              // whitespace | comment | ',' | ']'
      if (l1 != 23)                 // ','
      {
        break;
      }
      consume(23);                  // ','
      lookahead1W(11);              // whitespace | comment | string | '['
      whitespace();
      parse_alternative();
    }
    consume(27);                    // ']'
    eventHandler.endNonterminal("rule-pair", e0);
  }

  private void parse_alternative()
  {
    eventHandler.startNonterminal("alternative", e0);
    switch (l1)
    {
    case 26:                        // '['
      consume(26);                  // '['
      lookahead1W(2);               // whitespace | comment | string
      consume(17);                  // string
      for (;;)
      {
        lookahead1W(14);            // whitespace | comment | ',' | ']'
        if (l1 != 23)               // ','
        {
          break;
        }
        consume(23);                // ','
        lookahead1W(20);            // whitespace | comment | string | number | '[' | 'false' | 'null' | 'true' | '{'
        whitespace();
        parse_value();
      }
      consume(27);                  // ']'
      break;
    default:
      consume(17);                  // string
    }
    eventHandler.endNonterminal("alternative", e0);
  }

  private void parse_trailing_non_bnf_pairs()
  {
    eventHandler.startNonterminal("trailing-non-bnf-pairs", e0);
    if (l1 == 23)                   // ','
    {
      consume(23);                  // ','
      lookahead1W(3);               // whitespace | comment | non-bnf-string
      whitespace();
      parse_non_bnf_pairs();
    }
    eventHandler.endNonterminal("trailing-non-bnf-pairs", e0);
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
      if (code != 15                // whitespace
       && code != 16)               // comment
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
    for (int i = 0; i < 34; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 120 + s - 1;
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
    /*   0 */ 46, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 4,
    /*  35 */ 3, 3, 5, 3, 6, 3, 3, 7, 8, 9, 10, 11, 12, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 16, 3, 3, 17, 3, 3,
    /*  65 */ 18, 18, 18, 18, 19, 18, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
    /*  91 */ 21, 22, 23, 3, 24, 3, 25, 26, 27, 18, 28, 29, 30, 31, 32, 20, 33, 34, 20, 35, 36, 37, 20, 38, 39, 40, 41,
    /* 118 */ 20, 20, 42, 20, 20, 43, 44, 45, 3, 3
  };

  private static final int[] MAP1 =
  {
    /*   0 */ 54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  26 */ 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  52 */ 58, 58, 90, 122, 153, 185, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215,
    /*  74 */ 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 46, 0, 0, 0, 0, 0, 0, 0,
    /*  98 */ 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 4, 3, 3, 5, 3, 6, 3, 3, 7,
    /* 133 */ 8, 9, 10, 11, 12, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 16, 3, 3, 17, 3, 18, 18, 18, 18, 19, 18, 20,
    /* 161 */ 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 22, 23, 3, 24, 3, 25, 26,
    /* 188 */ 27, 18, 28, 29, 30, 31, 32, 20, 33, 34, 20, 35, 36, 37, 20, 38, 39, 40, 41, 20, 20, 42, 20, 20, 43, 44,
    /* 214 */ 45, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3
  };

  private static final int[] MAP2 =
  {
    /* 0 */ 57344, 65536, 65533, 1114111, 3, 3
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26
  };

  private static final int[] TRANSITION =
  {
    /*    0 */ 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 754, 752, 762, 767, 775,
    /*   21 */ 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 754, 752, 762, 767, 775, 820, 1339, 815, 820,
    /*   41 */ 835, 819, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1490, 775, 820, 1339, 790, 820, 835, 805, 821,
    /*   60 */ 1344, 1555, 1580, 820, 847, 829, 989, 1423, 872, 820, 1457, 887, 820, 1268, 805, 820, 907, 1555, 1580,
    /*   79 */ 820, 820, 1679, 1480, 934, 949, 820, 969, 982, 820, 835, 997, 821, 1344, 1555, 1580, 820, 820, 1007, 956,
    /*   99 */ 961, 1018, 820, 1339, 1043, 820, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1349, 1068, 820,
    /*  118 */ 1430, 790, 820, 797, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1490, 775, 820, 1339, 790, 1083,
    /*  137 */ 835, 805, 821, 1344, 1555, 1580, 820, 820, 1092, 1487, 1490, 775, 820, 1339, 790, 820, 835, 805, 821,
    /*  156 */ 1344, 1555, 1580, 820, 820, 1679, 1151, 1719, 775, 820, 1339, 790, 1083, 835, 805, 821, 1344, 1555, 1580,
    /*  175 */ 820, 820, 1679, 1487, 1490, 775, 912, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 1102, 1100,
    /*  194 */ 1110, 1116, 775, 820, 1325, 790, 820, 1222, 805, 821, 1575, 1664, 1304, 820, 820, 1679, 1025, 1719, 775,
    /*  213 */ 1030, 1339, 1124, 1135, 1707, 1132, 1247, 1344, 894, 1262, 820, 820, 1679, 1050, 1719, 775, 1055, 1339,
    /*  231 */ 1124, 1135, 1707, 1132, 1247, 1344, 894, 1262, 820, 999, 1679, 1487, 1490, 775, 820, 1339, 790, 820, 835,
    /*  250 */ 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1144, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344,
    /*  269 */ 1555, 1580, 820, 820, 1679, 1487, 1490, 775, 1159, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820,
    /*  288 */ 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 1707, 805, 1247, 1344, 894, 1262, 820, 1170, 1168,
    /*  306 */ 1179, 1184, 775, 917, 1339, 790, 820, 1707, 1192, 1247, 1344, 894, 1262, 820, 1170, 1168, 1179, 1184,
    /*  324 */ 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 1171, 1203, 1075, 1490, 775, 820, 1339,
    /*  343 */ 790, 820, 835, 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1487, 1010, 1215, 820, 1392, 1230, 820, 853,
    /*  362 */ 805, 1084, 859, 1555, 1580, 820, 820, 1255, 941, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344,
    /*  381 */ 1555, 1580, 820, 820, 1679, 1487, 1719, 775, 820, 1339, 790, 820, 835, 805, 821, 1344, 1555, 1580, 820,
    /*  400 */ 1170, 1168, 1179, 1184, 775, 1195, 1339, 790, 820, 1707, 805, 879, 1378, 894, 1262, 820, 1170, 1168,
    /*  418 */ 1179, 1276, 1284, 820, 1325, 790, 820, 1707, 805, 1247, 1344, 894, 1262, 820, 1170, 1168, 1179, 1184,
    /*  436 */ 775, 820, 1339, 790, 820, 1707, 805, 1247, 1344, 1299, 1291, 820, 1170, 1168, 1179, 1184, 775, 917, 1339,
    /*  455 */ 790, 1207, 1707, 1312, 1652, 841, 1333, 1319, 820, 1170, 1168, 1357, 1184, 775, 820, 1325, 790, 820, 782,
    /*  474 */ 1365, 1247, 1344, 894, 1262, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 835, 805, 1635, 1344,
    /*  493 */ 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 820, 835, 805, 821, 1435, 1555, 1580, 820,
    /*  512 */ 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 1681, 835, 805, 821, 1344, 1555, 1580, 820, 1170, 1168,
    /*  530 */ 1179, 1184, 775, 820, 1339, 790, 820, 835, 805, 864, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775,
    /*  549 */ 820, 1373, 790, 1602, 835, 1386, 821, 1344, 1693, 1580, 820, 1170, 1168, 1400, 1184, 775, 820, 1408,
    /*  567 */ 1416, 820, 835, 805, 1443, 1344, 1555, 1451, 820, 1170, 1168, 1179, 1184, 775, 820, 1339, 790, 1605,
    /*  585 */ 1465, 805, 821, 1344, 1555, 1035, 820, 1170, 1168, 1179, 1184, 775, 820, 1570, 790, 820, 835, 805, 821,
    /*  604 */ 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 1136, 1473, 790, 820, 1620, 805, 821, 974, 1555,
    /*  622 */ 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1498, 790, 820, 835, 1511, 821, 1344, 1544, 1060, 820, 1170,
    /*  641 */ 1168, 1525, 1184, 775, 820, 1533, 790, 1160, 835, 805, 821, 1503, 926, 1580, 820, 1170, 1168, 1179, 1184,
    /*  660 */ 775, 807, 1517, 790, 922, 835, 805, 821, 1344, 1555, 1580, 820, 1170, 1168, 1179, 1184, 775, 820, 1339,
    /*  679 */ 790, 820, 835, 1552, 821, 1344, 1555, 899, 820, 820, 1244, 1237, 1563, 1588, 1540, 1595, 1613, 820, 835,
    /*  698 */ 805, 821, 1344, 1555, 1580, 820, 820, 1679, 1628, 1490, 775, 820, 1339, 790, 820, 835, 805, 821, 1344,
    /*  717 */ 1555, 1580, 820, 820, 1643, 1648, 1490, 1660, 820, 1339, 1672, 820, 835, 1689, 821, 1344, 1555, 1580,
    /*  735 */ 820, 1701, 1715, 1715, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 2075, 289, 2075,
    /*  755 */ 2075, 2075, 2075, 2075, 2075, 2075, 2075, 2075, 2075, 1830, 2075, 2075, 2075, 2075, 2075, 0, 0, 30, 53,
    /*  774 */ 0, 289, 57, 58, 53, 0, 1830, 1830, 0, 0, 51, 94, 95, 96, 97, 98, 57, 58, 53, 1852, 61, 1726, 63, 0, 0,
    /*  799 */ 75, 0, 0, 53, 0, 53, 61, 63, 0, 0, 0, 0, 0, 0, 67, 0, 57, 58, 53, 1852, 61, 0, 0, 0, 0, 0, 0, 0, 0, 53,
    /*  829 */ 0, 289, 34, 30, 30, 36, 0, 0, 51, 0, 0, 53, 0, 53, 3712, 0, 101, 0, 0, 0, 30, 31, 0, 32, 0, 0, 51, 0, 0,
    /*  858 */ 54, 0, 54, 0, 0, 101, 0, 0, 0, 105, 0, 0, 0, 53, 289, 0, 58, 2432, 0, 1830, 1830, 0, 0, 104, 0, 0, 107,
    /*  885 */ 108, 53, 1408, 58, 2432, 1852, 61, 1726, 63, 0, 0, 114, 115, 101, 0, 0, 0, 101, 0, 512, 0, 0, 2816, 2816,
    /*  909 */ 0, 0, 101, 0, 0, 0, 64, 64, 0, 0, 0, 65, 65, 0, 0, 0, 87, 0, 0, 0, 0, 101, 0, 896, 384, 49, 49, 0, 0, 0,
    /*  939 */ 30, 53, 0, 0, 1830, 0, 0, 0, 0, 3584, 289, 57, 58, 53, 2944, 1830, 1830, 0, 0, 1830, 0, 0, 0, 35, 0, 0,
    /*  965 */ 0, 30, 53, 0, 2944, 0, 51, 0, 53, 0, 53, 0, 0, 101, 0, 0, 112, 57, 58, 53, 1852, 81, 1726, 63, 0, 0,
    /*  991 */ 1830, 0, 30, 0, 34, 30, 81, 63, 0, 0, 0, 0, 0, 0, 3200, 0, 0, 289, 35, 0, 0, 0, 0, 0, 52, 54, 0, 289, 57,
    /* 1020 */ 0, 53, 0, 1830, 1830, 0, 0, 1830, 0, 2604, 0, 0, 2604, 0, 2605, 0, 0, 0, 101, 0, 101, 120, 0, 57, 1408,
    /* 1045 */ 53, 1852, 61, 1726, 63, 0, 0, 1830, 0, 2605, 0, 0, 2605, 0, 2605, 0, 0, 0, 101, 119, 101, 0, 0, 289, 57,
    /* 1070 */ 58, 53, 0, 1830, 1852, 0, 0, 1830, 0, 3456, 0, 0, 3456, 84, 0, 0, 0, 0, 0, 0, 0, 54, 0, 289, 0, 0, 0, 0,
    /* 1098 */ 3072, 3072, 28, 289, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 1831, 28, 28, 28, 28, 28, 0, 0, 0, 30, 53,
    /* 1123 */ 0, 57, 58, 53, 1852, 61, 1726, 63, 2643, 61, 63, 2643, 2644, 0, 0, 0, 0, 0, 0, 0, 68, 3328, 0, 1830,
    /* 1147 */ 3328, 0, 0, 3328, 0, 0, 1830, 41, 43, 0, 41, 43, 1726, 0, 0, 0, 0, 0, 0, 0, 91, 0, 289, 1565, 0, 0, 0, 0,
    /* 1175 */ 0, 0, 0, 3456, 0, 1565, 1830, 0, 0, 0, 1565, 0, 0, 1565, 30, 53, 0, 61, 63, 65, 0, 0, 0, 0, 0, 66, 0, 0,
    /* 1203 */ 0, 289, 0, 3456, 0, 0, 0, 0, 88, 0, 0, 0, 289, 57, 58, 54, 0, 1830, 1830, 0, 0, 2176, 0, 0, 53, 0, 53,
    /* 1230 */ 57, 58, 54, 1852, 61, 1726, 63, 0, 0, 4096, 4138, 4096, 42, 4138, 4096, 289, 0, 0, 0, 0, 0, 0, 107, 108,
    /* 1254 */ 53, 0, 289, 0, 0, 0, 0, 3584, 0, 30, 53, 101, 0, 101, 0, 0, 51, 0, 0, 2432, 0, 2432, 0, 1565, 0, 0, 1565,
    /* 1281 */ 30, 55, 56, 289, 57, 58, 59, 0, 1830, 1830, 0, 30, 53, 101, 0, 101, 0, 1024, 0, 1280, 114, 115, 101, 0,
    /* 1305 */ 0, 0, 109, 0, 109, 0, 0, 61, 63, 65, 0, 0, 0, 3968, 0, 30, 53, 118, 0, 101, 0, 0, 51, 30, 53, 53, 53, 0,
    /* 1333 */ 113, 0, 114, 115, 101, 0, 0, 0, 51, 0, 53, 0, 53, 0, 0, 101, 0, 0, 0, 51, 0, 30, 53, 0, 0, 1565, 1830, 0,
    /* 1361 */ 46, 0, 1565, 46, 61, 63, 0, 0, 0, 0, 0, 100, 69, 0, 51, 0, 53, 0, 53, 0, 0, 101, 110, 0, 0, 61, 63, 0, 0,
    /* 1390 */ 0, 3840, 0, 0, 51, 30, 54, 53, 54, 0, 0, 1565, 1830, 0, 47, 0, 1565, 47, 70, 0, 51, 30, 53, 53, 78, 79,
    /* 1416 */ 57, 58, 80, 1852, 61, 1726, 63, 0, 34, 0, 0, 0, 2304, 2432, 0, 0, 75, 0, 53, 0, 53, 0, 0, 101, 0, 111, 0,
    /* 1443 */ 102, 0, 0, 0, 0, 0, 0, 53, 1152, 0, 0, 101, 0, 101, 0, 0, 51, 30, 2432, 53, 2432, 0, 92, 0, 51, 0, 0, 53,
    /* 1471 */ 0, 53, 71, 0, 51, 30, 53, 53, 53, 0, 37, 1830, 40, 0, 49, 50, 0, 0, 1830, 0, 0, 0, 0, 0, 30, 53, 0, 72,
    /* 1499 */ 0, 51, 0, 53, 0, 53, 0, 768, 101, 0, 0, 0, 61, 63, 0, 0, 99, 0, 0, 0, 51, 76, 53, 77, 53, 0, 0, 1565,
    /* 1527 */ 1830, 0, 48, 0, 1565, 48, 73, 0, 51, 30, 53, 53, 53, 0, 63, 0, 0, 0, 0, 0, 0, 101, 117, 0, 0, 61, 63, 0,
    /* 1555 */ 0, 0, 0, 0, 101, 0, 0, 0, 4138, 42, 0, 0, 0, 30, 53, 0, 74, 51, 0, 53, 0, 53, 0, 0, 109, 0, 0, 0, 101, 0,
    /* 1585 */ 101, 0, 0, 289, 57, 58, 53, 0, 0, 0, 61, 61, 51, 0, 53, 0, 53, 0, 85, 86, 0, 0, 0, 0, 0, 89, 0, 0, 57,
    /* 1614 */ 58, 53, 51, 61, 1726, 63, 0, 93, 51, 0, 0, 53, 0, 53, 4224, 0, 1830, 4224, 0, 0, 4224, 0, 103, 0, 0, 0,
    /* 1640 */ 0, 0, 53, 0, 289, 0, 0, 4352, 0, 0, 4352, 0, 0, 0, 0, 0, 106, 107, 108, 53, 289, 57, 58, 53, 0, 0, 0, 0,
    /* 1668 */ 109, 0, 0, 0, 57, 58, 53, 51, 61, 1726, 82, 0, 289, 0, 0, 0, 0, 0, 0, 90, 0, 640, 640, 0, 0, 0, 0, 0, 0,
    /* 1697 */ 116, 0, 0, 0, 0, 1920, 0, 0, 2688, 0, 0, 0, 51, 94, 95, 53, 0, 53, 0, 1920, 0, 0, 0, 0, 0, 0, 1565, 30,
    /* 1725 */ 53, 0
  };

  private static final int[] EXPECTED =
  {
    /*   0 */ 4, 20, 35, 39, 51, 55, 59, 63, 67, 71, 75, 79, 147, 86, 107, 92, 97, 101, 148, 106, 92, 111, 116, 102, 89,
    /*  25 */ 93, 112, 121, 82, 128, 120, 128, 125, 132, 136, 136, 144, 140, 138, 136, 136, 136, 136, 136, 136, 136,
    /*  46 */ 136, 136, 136, 136, 136, 100352, 114688, 229376, 360448, 1146880, 2195456, 16875520, 67207168,
    /*  59 */ -2147385344, 16386, 101376, 67338240, 229376, 2457600, 142704640, 8486912, 33652736, 4311040, -2147409920,
    /*  70 */ -2113826800, -200572928, 4293116, -2113823216, -66355200, -2143190532, 4296188, 32768, 65536, 2048,
    /*  80 */ 131072, 262144, 2097152, 2359296, 268435456, 32, 4194304, 8192, 73728, 16, 16, 524288, 524288, 268435456,
    /*  94 */ 536870912, 1073741824, 40, 4194812, 528, 65536, 131072, 262144, 262144, 262144, 2097152, 2359296, 16,
    /* 107 */ 4096, 16, 524288, 524288, 40, 128, 64, 4, 256, 256, 512, 65536, 131072, 256, 512, 131072, 262144, 262144,
    /* 125 */ 256, 131072, 262144, 8, 128, 64, 4, 128, 8, 128, 128, 0, 0, 0, 0, 1, 0, 2, 1, 2, 0, 0, 2, 1024, 1024,
    /* 150 */ 2359296, 73728
  };

  private static final String[] TOKEN =
  {
    "(0)",
    "CODE",
    "'%start'",
    "LEX_BLOCK",
    "ACTION",
    "'%left'",
    "'%right'",
    "'%nonassoc'",
    "'%token'",
    "'%prec'",
    "STRING",
    "ID",
    "ARROW_ACTION",
    "ACTION_BODY",
    "EOF",
    "whitespace",
    "comment",
    "string",
    "non-bnf-string",
    "number",
    "eof",
    "'\"bnf\"'",
    "'%%'",
    "','",
    "':'",
    "';'",
    "'['",
    "']'",
    "'false'",
    "'null'",
    "'true'",
    "'{'",
    "'|'",
    "'}'"
  };
}

// End
