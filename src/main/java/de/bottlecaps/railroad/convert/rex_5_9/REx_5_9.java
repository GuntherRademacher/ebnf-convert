// This file was generated on Sat Jul 17, 2021 11:14 (UTC+02) by REx v5.53 which is Copyright (c) 1979-2021 by Gunther Rademacher <grd@gmx.net>
// REx command line: -tree -a none -java -interface de.bottlecaps.railroad.convert.Parser -saxon10 -name de.bottlecaps.railroad.convert.rex_5_9.REx_5_9 rex_5_9.ebnf

package de.bottlecaps.railroad.convert.rex_5_9;

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

public class REx_5_9 implements de.bottlecaps.railroad.convert.Parser
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
    REx_5_9 parser = new REx_5_9(input, new SaxonTreeBuilder(builder));
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
    public StructuredQName getFunctionQName() {return new StructuredQName("p", "REx_5_9", functionName());}
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

  private static void buildError(REx_5_9 parser, ParseException pe, Builder builder) throws XPathException
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

  public REx_5_9(CharSequence string, EventHandler t)
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
    lookahead1W(39);                // Name^Token | Number | StringLiteral | S | BeginOfGrammar | '<?' | 'rule'
    switch (l1)
    {
    case 63:                        // 'rule'
      lookahead2W(29);              // Name^Token | S | '::=' | 'rule'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 19:                        // BeginOfGrammar
    case 191:                       // 'rule' Name^Token
    case 8127:                      // 'rule' 'rule'
      parse_PhythiaGrammar();
      lookahead1W(14);              // S | EOF
      consume(23);                  // EOF
      break;
    default:
      parse_W3CGrammar();
      consume(23);                  // EOF
    }
    eventHandler.endNonterminal("Grammar", e0);
  }

  private void parse_PhythiaGrammar()
  {
    eventHandler.startNonterminal("PhythiaGrammar", e0);
    if (l1 == 19)                   // BeginOfGrammar
    {
      consume(19);                  // BeginOfGrammar
    }
    for (;;)
    {
      lookahead1W(17);              // S | 'rule'
      whitespace();
      parse_PhythiaProduction();
      lookahead1W(32);              // S | EndOfGrammar | EOF | 'rule'
      if (l1 != 63)                 // 'rule'
      {
        break;
      }
    }
    if (l1 == 22)                   // EndOfGrammar
    {
      consume(22);                  // EndOfGrammar
    }
    eventHandler.endNonterminal("PhythiaGrammar", e0);
  }

  private void parse_PhythiaProduction()
  {
    eventHandler.startNonterminal("PhythiaProduction", e0);
    consume(63);                    // 'rule'
    lookahead1W(19);                // Name^Token | S | 'rule'
    whitespace();
    parse_Name();
    lookahead1W(18);                // S | 'syntax'
    consume(64);                    // 'syntax'
    lookahead1W(19);                // Name^Token | S | 'rule'
    whitespace();
    parse_Name();
    lookahead1W(15);                // S | '::='
    consume(41);                    // '::='
    lookahead1W(56);                // Name^Token | StringLiteral | S | Sem | '(' | '.' | '<*' | '<+' | '[' | 'rule' |
                                    // '{*' | '{+' | '|'
    whitespace();
    parse_PhythiaChoice();
    consume(37);                    // '.'
    lookahead1W(23);                // S | Semantic | 'end_of_rule'
    if (l1 == 21)                   // Semantic
    {
      consume(21);                  // Semantic
    }
    lookahead1W(16);                // S | 'end_of_rule'
    consume(60);                    // 'end_of_rule'
    lookahead1W(19);                // Name^Token | S | 'rule'
    whitespace();
    parse_Name();
    lookahead1W(36);                // S | EndOfGrammar | EOF | '.' | 'rule'
    if (l1 == 37)                   // '.'
    {
      consume(37);                  // '.'
    }
    eventHandler.endNonterminal("PhythiaProduction", e0);
  }

  private void parse_PhythiaChoice()
  {
    eventHandler.startNonterminal("PhythiaChoice", e0);
    parse_PhythiaSequence();
    for (;;)
    {
      if (l1 != 70)                 // '|'
      {
        break;
      }
      consume(70);                  // '|'
      lookahead1W(72);              // Name^Token | StringLiteral | S | Sem | '(' | ')' | '*>' | '*}' | '+>' | '+}' |
                                    // '.' | ';' | '<*' | '<+' | '[' | ']' | 'rule' | '{*' | '{+' | '|'
      whitespace();
      parse_PhythiaSequence();
    }
    eventHandler.endNonterminal("PhythiaChoice", e0);
  }

  private void parse_PhythiaSequence()
  {
    eventHandler.startNonterminal("PhythiaSequence", e0);
    for (;;)
    {
      lookahead1W(72);              // Name^Token | StringLiteral | S | Sem | '(' | ')' | '*>' | '*}' | '+>' | '+}' |
                                    // '.' | ';' | '<*' | '<+' | '[' | ']' | 'rule' | '{*' | '{+' | '|'
      if (l1 == 28                  // ')'
       || l1 == 31                  // '*>'
       || l1 == 32                  // '*}'
       || l1 == 34                  // '+>'
       || l1 == 35                  // '+}'
       || l1 == 37                  // '.'
       || l1 == 42                  // ';'
       || l1 == 56                  // ']'
       || l1 == 70)                 // '|'
      {
        break;
      }
      whitespace();
      parse_PhythiaItem();
    }
    eventHandler.endNonterminal("PhythiaSequence", e0);
  }

  private void parse_PhythiaItem()
  {
    eventHandler.startNonterminal("PhythiaItem", e0);
    switch (l1)
    {
    case 5:                         // StringLiteral
      consume(5);                   // StringLiteral
      break;
    case 43:                        // '<*'
      consume(43);                  // '<*'
      lookahead1W(57);              // Name^Token | StringLiteral | S | Sem | '(' | ';' | '<*' | '<+' | '[' | 'rule' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_PhythiaChoice();
      consume(42);                  // ';'
      lookahead1W(52);              // Name^Token | StringLiteral | S | Sem | '(' | '*>' | '<*' | '<+' | '[' | 'rule' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_PhythiaChoice();
      consume(31);                  // '*>'
      break;
    case 44:                        // '<+'
      consume(44);                  // '<+'
      lookahead1W(57);              // Name^Token | StringLiteral | S | Sem | '(' | ';' | '<*' | '<+' | '[' | 'rule' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_PhythiaChoice();
      consume(42);                  // ';'
      lookahead1W(54);              // Name^Token | StringLiteral | S | Sem | '(' | '+>' | '<*' | '<+' | '[' | 'rule' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_PhythiaChoice();
      consume(34);                  // '+>'
      break;
    case 68:                        // '{*'
      consume(68);                  // '{*'
      lookahead1W(53);              // Name^Token | StringLiteral | S | Sem | '(' | '*}' | '<*' | '<+' | '[' | 'rule' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_PhythiaChoice();
      consume(32);                  // '*}'
      break;
    case 69:                        // '{+'
      consume(69);                  // '{+'
      lookahead1W(55);              // Name^Token | StringLiteral | S | Sem | '(' | '+}' | '<*' | '<+' | '[' | 'rule' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_PhythiaChoice();
      consume(35);                  // '+}'
      break;
    case 55:                        // '['
      consume(55);                  // '['
      lookahead1W(58);              // Name^Token | StringLiteral | S | Sem | '(' | '<*' | '<+' | '[' | ']' | 'rule' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_PhythiaChoice();
      consume(56);                  // ']'
      break;
    case 27:                        // '('
      consume(27);                  // '('
      lookahead1W(51);              // Name^Token | StringLiteral | S | Sem | '(' | ')' | '<*' | '<+' | '[' | 'rule' |
                                    // '{*' | '{+' | '|'
      whitespace();
      parse_PhythiaChoice();
      consume(28);                  // ')'
      break;
    case 20:                        // Sem
      consume(20);                  // Sem
      break;
    default:
      parse_Name();
    }
    eventHandler.endNonterminal("PhythiaItem", e0);
  }

  private void parse_W3CGrammar()
  {
    eventHandler.startNonterminal("W3CGrammar", e0);
    for (;;)
    {
      lookahead1W(37);              // Name^Token | Number | StringLiteral | S | '<?' | 'rule'
      if (l1 != 46)                 // '<?'
      {
        break;
      }
      whitespace();
      parse_ProcessingInstruction();
    }
    for (;;)
    {
      whitespace();
      parse_SyntaxProduction();
      if (l1 != 1                   // Name^Token
       && l1 != 4                   // Number
       && l1 != 5                   // StringLiteral
       && l1 != 63)                 // 'rule'
      {
        break;
      }
    }
    if (l1 == 48                    // '<?TERMINALS?>'
     || l1 == 49)                   // '<?TOKENS?>'
    {
      switch (l1)
      {
      case 48:                      // '<?TERMINALS?>'
        consume(48);                // '<?TERMINALS?>'
        break;
      default:
        consume(49);                // '<?TOKENS?>'
      }
      for (;;)
      {
        lookahead1W(41);            // Name^Token | Number | StringLiteral | S | EOF | '.' | '<?ENCORE?>' | 'rule'
        if (l1 == 23                // EOF
         || l1 == 47)               // '<?ENCORE?>'
        {
          break;
        }
        switch (l1)
        {
        case 5:                     // StringLiteral
          lookahead2W(38);          // CaretName | S | '::=' | '<<' | '>>' | '?'
          break;
        case 1:                     // Name^Token
        case 63:                    // 'rule'
          lookahead2W(40);          // CaretName | S | DoubleBackslash | '::=' | '<<' | '>>' | '?'
          break;
        default:
          lk = l1;
        }
        switch (lk)
        {
        case 4:                     // Number
        case 37:                    // '.'
        case 5249:                  // Name^Token '::='
        case 5253:                  // StringLiteral '::='
        case 5311:                  // 'rule' '::='
        case 6785:                  // Name^Token '?'
        case 6789:                  // StringLiteral '?'
        case 6847:                  // 'rule' '?'
          whitespace();
          parse_LexicalProduction();
          break;
        case 1921:                  // Name^Token DoubleBackslash
        case 1983:                  // 'rule' DoubleBackslash
          whitespace();
          parse_Delimiter();
          break;
        default:
          whitespace();
          parse_Preference();
        }
      }
    }
    if (l1 == 47)                   // '<?ENCORE?>'
    {
      consume(47);                  // '<?ENCORE?>'
      for (;;)
      {
        lookahead1W(24);            // S | EOF | '<?'
        if (l1 != 46)               // '<?'
        {
          break;
        }
        whitespace();
        parse_ProcessingInstruction();
      }
    }
    eventHandler.endNonterminal("W3CGrammar", e0);
  }

  private void parse_ProcessingInstruction()
  {
    eventHandler.startNonterminal("ProcessingInstruction", e0);
    consume(46);                    // '<?'
    parse_Name();
    for (;;)
    {
      lookahead1(0);                // Space
      consume(2);                   // Space
      lookahead1(20);               // Space | DirPIContents | '?>'
      if (l1 != 2)                  // Space
      {
        break;
      }
    }
    if (l1 == 3)                    // DirPIContents
    {
      consume(3);                   // DirPIContents
    }
    lookahead1(4);                  // '?>'
    consume(54);                    // '?>'
    eventHandler.endNonterminal("ProcessingInstruction", e0);
  }

  private void parse_SyntaxProduction()
  {
    eventHandler.startNonterminal("SyntaxProduction", e0);
    if (l1 == 4)                    // Number
    {
      consume(4);                   // Number
    }
    lookahead1W(28);                // Name^Token | StringLiteral | S | 'rule'
    switch (l1)
    {
    case 5:                         // StringLiteral
      consume(5);                   // StringLiteral
      break;
    default:
      whitespace();
      parse_Name();
    }
    lookahead1W(15);                // S | '::='
    consume(41);                    // '::='
    lookahead1W(65);                // Name^Token | Number | StringLiteral | S | VC | WFC | EOF | '(' | '/' | '/*' |
                                    // '<?' | '<?ENCORE?>' | '<?TERMINALS?>' | '<?TOKENS?>' | 'rule' | '{' | '|'
    whitespace();
    parse_SyntaxChoice();
    for (;;)
    {
      lookahead1W(45);              // Name^Token | Number | StringLiteral | S | EOF | '/*' | '<?ENCORE?>' |
                                    // '<?TERMINALS?>' | '<?TOKENS?>' | 'rule'
      if (l1 != 39)                 // '/*'
      {
        break;
      }
      whitespace();
      parse_Option();
    }
    eventHandler.endNonterminal("SyntaxProduction", e0);
  }

  private void parse_SyntaxChoice()
  {
    eventHandler.startNonterminal("SyntaxChoice", e0);
    parse_SyntaxSequence();
    if (l1 == 38                    // '/'
     || l1 == 70)                   // '|'
    {
      switch (l1)
      {
      case 70:                      // '|'
        for (;;)
        {
          consume(70);              // '|'
          lookahead1W(64);          // Name^Token | Number | StringLiteral | S | VC | WFC | EOF | '(' | ')' | '/*' |
                                    // '<?' | '<?ENCORE?>' | '<?TERMINALS?>' | '<?TOKENS?>' | 'rule' | '{' | '|'
          whitespace();
          parse_SyntaxSequence();
          if (l1 != 70)             // '|'
          {
            break;
          }
        }
        break;
      default:
        for (;;)
        {
          consume(38);              // '/'
          lookahead1W(63);          // Name^Token | Number | StringLiteral | S | VC | WFC | EOF | '(' | ')' | '/' |
                                    // '/*' | '<?' | '<?ENCORE?>' | '<?TERMINALS?>' | '<?TOKENS?>' | 'rule' | '{'
          whitespace();
          parse_SyntaxSequence();
          if (l1 != 38)             // '/'
          {
            break;
          }
        }
      }
    }
    eventHandler.endNonterminal("SyntaxChoice", e0);
  }

  private void parse_SyntaxSequence()
  {
    eventHandler.startNonterminal("SyntaxSequence", e0);
    switch (l1)
    {
    case 1:                         // Name^Token
    case 5:                         // StringLiteral
    case 63:                        // 'rule'
      lookahead2W(78);              // Name^Token | Number | StringLiteral | CaretName | S | VC | WFC | EOF | '(' |
                                    // ')' | '*' | '+' | '/' | '/*' | '::=' | '<?' | '<?ENCORE?>' | '<?TERMINALS?>' |
                                    // '<?TOKENS?>' | '=>' | '?' | 'rule' | '{' | '|'
      break;
    default:
      lk = l1;
    }
    if (lk != 4                     // Number
     && lk != 16                    // VC
     && lk != 17                    // WFC
     && lk != 23                    // EOF
     && lk != 28                    // ')'
     && lk != 38                    // '/'
     && lk != 39                    // '/*'
     && lk != 47                    // '<?ENCORE?>'
     && lk != 48                    // '<?TERMINALS?>'
     && lk != 49                    // '<?TOKENS?>'
     && lk != 70                    // '|'
     && lk != 5249                  // Name^Token '::='
     && lk != 5253                  // StringLiteral '::='
     && lk != 5311)                 // 'rule' '::='
    {
      whitespace();
      parse_SyntaxItem();
      lookahead1W(70);              // Name^Token | Number | StringLiteral | S | VC | WFC | EOF | '(' | ')' | '/' |
                                    // '/*' | '<?' | '<?ENCORE?>' | '<?TERMINALS?>' | '<?TOKENS?>' | '=>' | 'rule' |
                                    // '{' | '|'
      if (l1 == 51)                 // '=>'
      {
        consume(51);                // '=>'
      }
      for (;;)
      {
        lookahead1W(67);            // Name^Token | Number | StringLiteral | S | VC | WFC | EOF | '(' | ')' | '/' |
                                    // '/*' | '<?' | '<?ENCORE?>' | '<?TERMINALS?>' | '<?TOKENS?>' | 'rule' | '{' | '|'
        switch (l1)
        {
        case 1:                     // Name^Token
        case 5:                     // StringLiteral
        case 63:                    // 'rule'
          lookahead2W(76);          // Name^Token | Number | StringLiteral | CaretName | S | VC | WFC | EOF | '(' |
                                    // ')' | '*' | '+' | '/' | '/*' | '::=' | '<?' | '<?ENCORE?>' | '<?TERMINALS?>' |
                                    // '<?TOKENS?>' | '?' | 'rule' | '{' | '|'
          break;
        default:
          lk = l1;
        }
        if (lk == 4                 // Number
         || lk == 16                // VC
         || lk == 17                // WFC
         || lk == 23                // EOF
         || lk == 28                // ')'
         || lk == 38                // '/'
         || lk == 39                // '/*'
         || lk == 47                // '<?ENCORE?>'
         || lk == 48                // '<?TERMINALS?>'
         || lk == 49                // '<?TOKENS?>'
         || lk == 70                // '|'
         || lk == 5249              // Name^Token '::='
         || lk == 5253              // StringLiteral '::='
         || lk == 5311)             // 'rule' '::='
        {
          break;
        }
        whitespace();
        parse_SyntaxItem();
      }
    }
    for (;;)
    {
      lookahead1W(60);              // Name^Token | Number | StringLiteral | S | VC | WFC | EOF | ')' | '/' | '/*' |
                                    // '<?ENCORE?>' | '<?TERMINALS?>' | '<?TOKENS?>' | 'rule' | '|'
      if (l1 != 16                  // VC
       && l1 != 17)                 // WFC
      {
        break;
      }
      whitespace();
      parse_Constraint();
    }
    eventHandler.endNonterminal("SyntaxSequence", e0);
  }

  private void parse_SyntaxItem()
  {
    eventHandler.startNonterminal("SyntaxItem", e0);
    parse_SyntaxPrimary();
    lookahead1W(75);                // Name^Token | Number | StringLiteral | S | VC | WFC | EOF | '(' | ')' | '*' |
                                    // '+' | '/' | '/*' | '<?' | '<?ENCORE?>' | '<?TERMINALS?>' | '<?TOKENS?>' | '=>' |
                                    // '?' | 'rule' | '{' | '|'
    if (l1 == 29                    // '*'
     || l1 == 33                    // '+'
     || l1 == 53)                   // '?'
    {
      switch (l1)
      {
      case 53:                      // '?'
        consume(53);                // '?'
        break;
      case 29:                      // '*'
        consume(29);                // '*'
        break;
      default:
        consume(33);                // '+'
      }
    }
    eventHandler.endNonterminal("SyntaxItem", e0);
  }

  private void parse_SyntaxPrimary()
  {
    eventHandler.startNonterminal("SyntaxPrimary", e0);
    switch (l1)
    {
    case 27:                        // '('
      consume(27);                  // '('
      lookahead1W(48);              // Name^Token | StringLiteral | S | VC | WFC | '(' | ')' | '/' | '<?' | 'rule' |
                                    // '{' | '|'
      whitespace();
      parse_SyntaxChoice();
      consume(28);                  // ')'
      break;
    case 46:                        // '<?'
      parse_ProcessingInstruction();
      break;
    case 67:                        // '{'
      parse_DisAllow();
      break;
    default:
      parse_NameOrString();
    }
    eventHandler.endNonterminal("SyntaxPrimary", e0);
  }

  private void parse_LexicalProduction()
  {
    eventHandler.startNonterminal("LexicalProduction", e0);
    if (l1 == 4)                    // Number
    {
      consume(4);                   // Number
    }
    lookahead1W(33);                // Name^Token | StringLiteral | S | '.' | 'rule'
    switch (l1)
    {
    case 37:                        // '.'
      consume(37);                  // '.'
      break;
    case 5:                         // StringLiteral
      consume(5);                   // StringLiteral
      break;
    default:
      whitespace();
      parse_Name();
    }
    lookahead1W(26);                // S | '::=' | '?'
    if (l1 == 53)                   // '?'
    {
      consume(53);                  // '?'
    }
    lookahead1W(15);                // S | '::='
    consume(41);                    // '::='
    lookahead1W(62);                // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | '.' | '/*' | '<?ENCORE?>' | '[' | 'rule' | '|'
    whitespace();
    parse_ContextChoice();
    for (;;)
    {
      lookahead1W(42);              // Name^Token | Number | StringLiteral | S | EOF | '.' | '/*' | '<?ENCORE?>' |
                                    // 'rule'
      if (l1 != 39)                 // '/*'
      {
        break;
      }
      whitespace();
      parse_Option();
    }
    eventHandler.endNonterminal("LexicalProduction", e0);
  }

  private void parse_ContextChoice()
  {
    eventHandler.startNonterminal("ContextChoice", e0);
    parse_ContextExpression();
    for (;;)
    {
      if (l1 != 70)                 // '|'
      {
        break;
      }
      consume(70);                  // '|'
      lookahead1W(62);              // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | '.' | '/*' | '<?ENCORE?>' | '[' | 'rule' | '|'
      whitespace();
      parse_ContextExpression();
    }
    eventHandler.endNonterminal("ContextChoice", e0);
  }

  private void parse_LexicalChoice()
  {
    eventHandler.startNonterminal("LexicalChoice", e0);
    parse_LexicalSequence();
    for (;;)
    {
      lookahead1W(25);              // S | ')' | '|'
      if (l1 != 70)                 // '|'
      {
        break;
      }
      consume(70);                  // '|'
      lookahead1W(46);              // Name^Token | StringLiteral | CharCode | S | '$' | '(' | ')' | '.' | '[' |
                                    // 'rule' | '|'
      whitespace();
      parse_LexicalSequence();
    }
    eventHandler.endNonterminal("LexicalChoice", e0);
  }

  private void parse_ContextExpression()
  {
    eventHandler.startNonterminal("ContextExpression", e0);
    parse_LexicalSequence();
    lookahead1W(50);                // Name^Token | Number | StringLiteral | S | VC | WFC | EOF | '&' | '.' | '/*' |
                                    // '<?ENCORE?>' | 'rule' | '|'
    if (l1 == 26)                   // '&'
    {
      consume(26);                  // '&'
      lookahead1W(61);              // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '(' |
                                    // '.' | '/*' | '<?ENCORE?>' | '[' | 'rule' | '|'
      whitespace();
      parse_LexicalSequence();
    }
    for (;;)
    {
      lookahead1W(47);              // Name^Token | Number | StringLiteral | S | VC | WFC | EOF | '.' | '/*' |
                                    // '<?ENCORE?>' | 'rule' | '|'
      if (l1 != 16                  // VC
       && l1 != 17)                 // WFC
      {
        break;
      }
      whitespace();
      parse_Constraint();
    }
    eventHandler.endNonterminal("ContextExpression", e0);
  }

  private void parse_LexicalSequence()
  {
    eventHandler.startNonterminal("LexicalSequence", e0);
    switch (l1)
    {
    case 5:                         // StringLiteral
      lookahead2W(81);              // Name^Token | Number | StringLiteral | CaretName | CharCode | S | VC | WFC | EOF |
                                    // '$' | '&' | '(' | ')' | '*' | '+' | '-' | '.' | '/*' | '::=' | '<<' |
                                    // '<?ENCORE?>' | '>>' | '?' | '[' | 'rule' | '|'
      switch (lk)
      {
      case 6789:                    // StringLiteral '?'
        lookahead3W(71);            // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | ')' | '-' | '.' | '/*' | '::=' | '<?ENCORE?>' | '[' | 'rule' | '|'
        break;
      }
      break;
    case 37:                        // '.'
      lookahead2W(77);              // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | ')' | '*' | '+' | '-' | '.' | '/*' | '::=' | '<?ENCORE?>' | '?' | '[' |
                                    // 'rule' | '|'
      switch (lk)
      {
      case 6821:                    // '.' '?'
        lookahead3W(71);            // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | ')' | '-' | '.' | '/*' | '::=' | '<?ENCORE?>' | '[' | 'rule' | '|'
        break;
      }
      break;
    case 1:                         // Name^Token
    case 63:                        // 'rule'
      lookahead2W(83);              // Name^Token | Number | StringLiteral | CaretName | CharCode | S |
                                    // DoubleBackslash | VC | WFC | EOF | '$' | '&' | '(' | ')' | '*' | '+' | '-' |
                                    // '.' | '/*' | '::=' | '<<' | '<?ENCORE?>' | '>>' | '?' | '[' | 'rule' | '|'
      switch (lk)
      {
      case 6785:                    // Name^Token '?'
      case 6847:                    // 'rule' '?'
        lookahead3W(71);            // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | ')' | '-' | '.' | '/*' | '::=' | '<?ENCORE?>' | '[' | 'rule' | '|'
        break;
      }
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 4:                         // Number
    case 16:                        // VC
    case 17:                        // WFC
    case 23:                        // EOF
    case 26:                        // '&'
    case 28:                        // ')'
    case 39:                        // '/*'
    case 47:                        // '<?ENCORE?>'
    case 70:                        // '|'
    case 769:                       // Name^Token CaretName
    case 773:                       // StringLiteral CaretName
    case 831:                       // 'rule' CaretName
    case 1921:                      // Name^Token DoubleBackslash
    case 1983:                      // 'rule' DoubleBackslash
    case 5249:                      // Name^Token '::='
    case 5253:                      // StringLiteral '::='
    case 5285:                      // '.' '::='
    case 5311:                      // 'rule' '::='
    case 5761:                      // Name^Token '<<'
    case 5765:                      // StringLiteral '<<'
    case 5823:                      // 'rule' '<<'
    case 6657:                      // Name^Token '>>'
    case 6661:                      // StringLiteral '>>'
    case 6719:                      // 'rule' '>>'
    case 678529:                    // Name^Token '?' '::='
    case 678533:                    // StringLiteral '?' '::='
    case 678565:                    // '.' '?' '::='
    case 678591:                    // 'rule' '?' '::='
      break;
    default:
      parse_LexicalItem();
      lookahead1W(68);              // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | ')' | '-' | '.' | '/*' | '<?ENCORE?>' | '[' | 'rule' | '|'
      switch (l1)
      {
      case 36:                      // '-'
        consume(36);                // '-'
        lookahead1W(43);            // Name^Token | StringLiteral | CharCode | S | '$' | '(' | '.' | '[' | 'rule'
        whitespace();
        parse_LexicalItem();
        break;
      default:
        for (;;)
        {
          lookahead1W(66);          // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | ')' | '.' | '/*' | '<?ENCORE?>' | '[' | 'rule' | '|'
          switch (l1)
          {
          case 5:                   // StringLiteral
            lookahead2W(79);        // Name^Token | Number | StringLiteral | CaretName | CharCode | S | VC | WFC | EOF |
                                    // '$' | '&' | '(' | ')' | '*' | '+' | '.' | '/*' | '::=' | '<<' | '<?ENCORE?>' |
                                    // '>>' | '?' | '[' | 'rule' | '|'
            switch (lk)
            {
            case 6789:              // StringLiteral '?'
              lookahead3W(69);      // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | ')' | '.' | '/*' | '::=' | '<?ENCORE?>' | '[' | 'rule' | '|'
              break;
            }
            break;
          case 37:                  // '.'
            lookahead2W(74);        // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | ')' | '*' | '+' | '.' | '/*' | '::=' | '<?ENCORE?>' | '?' | '[' | 'rule' |
                                    // '|'
            switch (lk)
            {
            case 6821:              // '.' '?'
              lookahead3W(69);      // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | ')' | '.' | '/*' | '::=' | '<?ENCORE?>' | '[' | 'rule' | '|'
              break;
            }
            break;
          case 1:                   // Name^Token
          case 63:                  // 'rule'
            lookahead2W(80);        // Name^Token | Number | StringLiteral | CaretName | CharCode | S |
                                    // DoubleBackslash | VC | WFC | EOF | '$' | '&' | '(' | ')' | '*' | '+' | '.' |
                                    // '/*' | '::=' | '<<' | '<?ENCORE?>' | '>>' | '?' | '[' | 'rule' | '|'
            switch (lk)
            {
            case 6785:              // Name^Token '?'
            case 6847:              // 'rule' '?'
              lookahead3W(69);      // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | ')' | '.' | '/*' | '::=' | '<?ENCORE?>' | '[' | 'rule' | '|'
              break;
            }
            break;
          default:
            lk = l1;
          }
          if (lk == 4               // Number
           || lk == 16              // VC
           || lk == 17              // WFC
           || lk == 23              // EOF
           || lk == 26              // '&'
           || lk == 28              // ')'
           || lk == 39              // '/*'
           || lk == 47              // '<?ENCORE?>'
           || lk == 70              // '|'
           || lk == 769             // Name^Token CaretName
           || lk == 773             // StringLiteral CaretName
           || lk == 831             // 'rule' CaretName
           || lk == 1921            // Name^Token DoubleBackslash
           || lk == 1983            // 'rule' DoubleBackslash
           || lk == 5249            // Name^Token '::='
           || lk == 5253            // StringLiteral '::='
           || lk == 5285            // '.' '::='
           || lk == 5311            // 'rule' '::='
           || lk == 5761            // Name^Token '<<'
           || lk == 5765            // StringLiteral '<<'
           || lk == 5823            // 'rule' '<<'
           || lk == 6657            // Name^Token '>>'
           || lk == 6661            // StringLiteral '>>'
           || lk == 6719            // 'rule' '>>'
           || lk == 678529          // Name^Token '?' '::='
           || lk == 678533          // StringLiteral '?' '::='
           || lk == 678565          // '.' '?' '::='
           || lk == 678591)         // 'rule' '?' '::='
          {
            break;
          }
          whitespace();
          parse_LexicalItem();
        }
      }
    }
    eventHandler.endNonterminal("LexicalSequence", e0);
  }

  private void parse_LexicalItem()
  {
    eventHandler.startNonterminal("LexicalItem", e0);
    parse_LexicalPrimary();
    lookahead1W(73);                // Name^Token | Number | StringLiteral | CharCode | S | VC | WFC | EOF | '$' | '&' |
                                    // '(' | ')' | '*' | '+' | '-' | '.' | '/*' | '<?ENCORE?>' | '?' | '[' | 'rule' |
                                    // '|'
    if (l1 == 29                    // '*'
     || l1 == 33                    // '+'
     || l1 == 53)                   // '?'
    {
      switch (l1)
      {
      case 53:                      // '?'
        consume(53);                // '?'
        break;
      case 29:                      // '*'
        consume(29);                // '*'
        break;
      default:
        consume(33);                // '+'
      }
    }
    eventHandler.endNonterminal("LexicalItem", e0);
  }

  private void parse_LexicalPrimary()
  {
    eventHandler.startNonterminal("LexicalPrimary", e0);
    switch (l1)
    {
    case 5:                         // StringLiteral
      consume(5);                   // StringLiteral
      break;
    case 27:                        // '('
      consume(27);                  // '('
      lookahead1W(46);              // Name^Token | StringLiteral | CharCode | S | '$' | '(' | ')' | '.' | '[' |
                                    // 'rule' | '|'
      whitespace();
      parse_LexicalChoice();
      consume(28);                  // ')'
      break;
    case 25:                        // '$'
      consume(25);                  // '$'
      break;
    case 7:                         // CharCode
      consume(7);                   // CharCode
      break;
    case 55:                        // '['
      parse_CharClass();
      break;
    default:
      switch (l1)
      {
      case 37:                      // '.'
        consume(37);                // '.'
        break;
      default:
        parse_Name();
      }
    }
    eventHandler.endNonterminal("LexicalPrimary", e0);
  }

  private void parse_NameOrString()
  {
    eventHandler.startNonterminal("NameOrString", e0);
    switch (l1)
    {
    case 5:                         // StringLiteral
      consume(5);                   // StringLiteral
      lookahead1W(82);              // Name^Token | Number | StringLiteral | CaretName | S | VC | WFC | EOF | '(' |
                                    // ')' | '*' | '+' | '.' | '/' | '/*' | '<<' | '<?' | '<?ENCORE?>' |
                                    // '<?TERMINALS?>' | '<?TOKENS?>' | '=>' | '>>' | '?' | 'rule' | '{' | '|'
      if (l1 == 6)                  // CaretName
      {
        whitespace();
        parse_Context();
      }
      break;
    default:
      parse_Name();
      lookahead1W(82);              // Name^Token | Number | StringLiteral | CaretName | S | VC | WFC | EOF | '(' |
                                    // ')' | '*' | '+' | '.' | '/' | '/*' | '<<' | '<?' | '<?ENCORE?>' |
                                    // '<?TERMINALS?>' | '<?TOKENS?>' | '=>' | '>>' | '?' | 'rule' | '{' | '|'
      if (l1 == 6)                  // CaretName
      {
        whitespace();
        parse_Context();
      }
    }
    eventHandler.endNonterminal("NameOrString", e0);
  }

  private void parse_Context()
  {
    eventHandler.startNonterminal("Context", e0);
    consume(6);                     // CaretName
    eventHandler.endNonterminal("Context", e0);
  }

  private void parse_DisAllow()
  {
    eventHandler.startNonterminal("DisAllow", e0);
    consume(67);                    // '{'
    for (;;)
    {
      lookahead1(10);               // Space | 'disallow'
      if (l1 != 2)                  // Space
      {
        break;
      }
      consume(2);                   // Space
    }
    consume(59);                    // 'disallow'
    for (;;)
    {
      lookahead1(9);                // Space | '='
      if (l1 != 2)                  // Space
      {
        break;
      }
      consume(2);                   // Space
    }
    consume(50);                    // '='
    for (;;)
    {
      lookahead1(7);                // Space | '"'
      if (l1 != 2)                  // Space
      {
        break;
      }
      consume(2);                   // Space
    }
    consume(24);                    // '"'
    lookahead1(1);                  // TokenIdentifier
    consume(8);                     // TokenIdentifier
    for (;;)
    {
      lookahead1(12);               // Blank | '"'
      if (l1 != 9)                  // Blank
      {
        break;
      }
      consume(9);                   // Blank
      lookahead1(1);                // TokenIdentifier
      consume(8);                   // TokenIdentifier
    }
    consume(24);                    // '"'
    for (;;)
    {
      lookahead1(11);               // Space | '}'
      if (l1 != 2)                  // Space
      {
        break;
      }
      consume(2);                   // Space
    }
    consume(71);                    // '}'
    eventHandler.endNonterminal("DisAllow", e0);
  }

  private void parse_CharClass()
  {
    eventHandler.startNonterminal("CharClass", e0);
    consume(55);                    // '['
    lookahead1(35);                 // CharCode | Char | CharRange | CharCodeRange | '^'
    if (l1 == 57)                   // '^'
    {
      consume(57);                  // '^'
    }
    for (;;)
    {
      lookahead1(31);               // CharCode | Char | CharRange | CharCodeRange
      switch (l1)
      {
      case 10:                      // Char
        consume(10);                // Char
        break;
      case 7:                       // CharCode
        consume(7);                 // CharCode
        break;
      case 11:                      // CharRange
        consume(11);                // CharRange
        break;
      default:
        consume(12);                // CharCodeRange
      }
      lookahead1(34);               // CharCode | Char | CharRange | CharCodeRange | ']'
      if (l1 == 56)                 // ']'
      {
        break;
      }
    }
    consume(56);                    // ']'
    eventHandler.endNonterminal("CharClass", e0);
  }

  private void parse_Option()
  {
    eventHandler.startNonterminal("Option", e0);
    consume(39);                    // '/*'
    for (;;)
    {
      lookahead1(30);               // Space | 'gn' | 'ws' | 'xgs'
      if (l1 != 2)                  // Space
      {
        break;
      }
      consume(2);                   // Space
    }
    switch (l1)
    {
    case 65:                        // 'ws'
      consume(65);                  // 'ws'
      lookahead1(3);                // ':'
      consume(40);                  // ':'
      for (;;)
      {
        lookahead1(22);             // Space | 'definition' | 'explicit'
        if (l1 != 2)                // Space
        {
          break;
        }
        consume(2);                 // Space
      }
      switch (l1)
      {
      case 61:                      // 'explicit'
        consume(61);                // 'explicit'
        break;
      default:
        consume(58);                // 'definition'
      }
      for (;;)
      {
        lookahead1(8);              // Space | '*/'
        if (l1 != 2)                // Space
        {
          break;
        }
        consume(2);                 // Space
      }
      break;
    case 62:                        // 'gn'
      consume(62);                  // 'gn'
      lookahead1(3);                // ':'
      consume(40);                  // ':'
      for (;;)
      {
        lookahead1(21);             // Space | PragmaContents | '*/'
        if (l1 != 2)                // Space
        {
          break;
        }
        consume(2);                 // Space
      }
      if (l1 == 13)                 // PragmaContents
      {
        consume(13);                // PragmaContents
      }
      break;
    default:
      consume(66);                  // 'xgs'
      lookahead1(3);                // ':'
      consume(40);                  // ':'
      for (;;)
      {
        lookahead1(21);             // Space | PragmaContents | '*/'
        if (l1 != 2)                // Space
        {
          break;
        }
        consume(2);                 // Space
      }
      if (l1 == 13)                 // PragmaContents
      {
        consume(13);                // PragmaContents
      }
    }
    lookahead1(2);                  // '*/'
    consume(30);                    // '*/'
    eventHandler.endNonterminal("Option", e0);
  }

  private void parse_Constraint()
  {
    eventHandler.startNonterminal("Constraint", e0);
    switch (l1)
    {
    case 16:                        // VC
      consume(16);                  // VC
      lookahead1(3);                // ':'
      consume(40);                  // ':'
      for (;;)
      {
        lookahead1(6);              // Space | NonRBrackedContents
        if (l1 != 2)                // Space
        {
          break;
        }
        consume(2);                 // Space
      }
      consume(18);                  // NonRBrackedContents
      break;
    default:
      consume(17);                  // WFC
      lookahead1(3);                // ':'
      consume(40);                  // ':'
      for (;;)
      {
        lookahead1(6);              // Space | NonRBrackedContents
        if (l1 != 2)                // Space
        {
          break;
        }
        consume(2);                 // Space
      }
      consume(18);                  // NonRBrackedContents
    }
    eventHandler.endNonterminal("Constraint", e0);
  }

  private void parse_Preference()
  {
    eventHandler.startNonterminal("Preference", e0);
    parse_NameOrString();
    lookahead1W(27);                // S | '<<' | '>>'
    switch (l1)
    {
    case 52:                        // '>>'
      consume(52);                  // '>>'
      for (;;)
      {
        lookahead1W(28);            // Name^Token | StringLiteral | S | 'rule'
        whitespace();
        parse_NameOrString();
        lookahead1W(41);            // Name^Token | Number | StringLiteral | S | EOF | '.' | '<?ENCORE?>' | 'rule'
        switch (l1)
        {
        case 5:                     // StringLiteral
          lookahead2W(49);          // Name^Token | Number | StringLiteral | CaretName | S | EOF | '.' | '::=' | '<<' |
                                    // '<?ENCORE?>' | '>>' | '?' | 'rule'
          switch (lk)
          {
          case 773:                 // StringLiteral CaretName
            lookahead3W(44);        // Name^Token | Number | StringLiteral | S | EOF | '.' | '<<' | '<?ENCORE?>' |
                                    // '>>' | 'rule'
            break;
          }
          break;
        case 1:                     // Name^Token
        case 63:                    // 'rule'
          lookahead2W(59);          // Name^Token | Number | StringLiteral | CaretName | S | DoubleBackslash | EOF |
                                    // '.' | '::=' | '<<' | '<?ENCORE?>' | '>>' | '?' | 'rule'
          switch (lk)
          {
          case 769:                 // Name^Token CaretName
          case 831:                 // 'rule' CaretName
            lookahead3W(44);        // Name^Token | Number | StringLiteral | S | EOF | '.' | '<<' | '<?ENCORE?>' |
                                    // '>>' | 'rule'
            break;
          }
          break;
        default:
          lk = l1;
        }
        if (lk == 4                 // Number
         || lk == 23                // EOF
         || lk == 37                // '.'
         || lk == 47                // '<?ENCORE?>'
         || lk == 1921              // Name^Token DoubleBackslash
         || lk == 1983              // 'rule' DoubleBackslash
         || lk == 5249              // Name^Token '::='
         || lk == 5253              // StringLiteral '::='
         || lk == 5311              // 'rule' '::='
         || lk == 5761              // Name^Token '<<'
         || lk == 5765              // StringLiteral '<<'
         || lk == 5823              // 'rule' '<<'
         || lk == 6657              // Name^Token '>>'
         || lk == 6661              // StringLiteral '>>'
         || lk == 6719              // 'rule' '>>'
         || lk == 6785              // Name^Token '?'
         || lk == 6789              // StringLiteral '?'
         || lk == 6847              // 'rule' '?'
         || lk == 738049            // Name^Token CaretName '<<'
         || lk == 738053            // StringLiteral CaretName '<<'
         || lk == 738111            // 'rule' CaretName '<<'
         || lk == 852737            // Name^Token CaretName '>>'
         || lk == 852741            // StringLiteral CaretName '>>'
         || lk == 852799)           // 'rule' CaretName '>>'
        {
          break;
        }
      }
      break;
    default:
      consume(45);                  // '<<'
      for (;;)
      {
        lookahead1W(28);            // Name^Token | StringLiteral | S | 'rule'
        whitespace();
        parse_NameOrString();
        lookahead1W(41);            // Name^Token | Number | StringLiteral | S | EOF | '.' | '<?ENCORE?>' | 'rule'
        switch (l1)
        {
        case 5:                     // StringLiteral
          lookahead2W(49);          // Name^Token | Number | StringLiteral | CaretName | S | EOF | '.' | '::=' | '<<' |
                                    // '<?ENCORE?>' | '>>' | '?' | 'rule'
          switch (lk)
          {
          case 773:                 // StringLiteral CaretName
            lookahead3W(44);        // Name^Token | Number | StringLiteral | S | EOF | '.' | '<<' | '<?ENCORE?>' |
                                    // '>>' | 'rule'
            break;
          }
          break;
        case 1:                     // Name^Token
        case 63:                    // 'rule'
          lookahead2W(59);          // Name^Token | Number | StringLiteral | CaretName | S | DoubleBackslash | EOF |
                                    // '.' | '::=' | '<<' | '<?ENCORE?>' | '>>' | '?' | 'rule'
          switch (lk)
          {
          case 769:                 // Name^Token CaretName
          case 831:                 // 'rule' CaretName
            lookahead3W(44);        // Name^Token | Number | StringLiteral | S | EOF | '.' | '<<' | '<?ENCORE?>' |
                                    // '>>' | 'rule'
            break;
          }
          break;
        default:
          lk = l1;
        }
        if (lk == 4                 // Number
         || lk == 23                // EOF
         || lk == 37                // '.'
         || lk == 47                // '<?ENCORE?>'
         || lk == 1921              // Name^Token DoubleBackslash
         || lk == 1983              // 'rule' DoubleBackslash
         || lk == 5249              // Name^Token '::='
         || lk == 5253              // StringLiteral '::='
         || lk == 5311              // 'rule' '::='
         || lk == 5761              // Name^Token '<<'
         || lk == 5765              // StringLiteral '<<'
         || lk == 5823              // 'rule' '<<'
         || lk == 6657              // Name^Token '>>'
         || lk == 6661              // StringLiteral '>>'
         || lk == 6719              // 'rule' '>>'
         || lk == 6785              // Name^Token '?'
         || lk == 6789              // StringLiteral '?'
         || lk == 6847              // 'rule' '?'
         || lk == 738049            // Name^Token CaretName '<<'
         || lk == 738053            // StringLiteral CaretName '<<'
         || lk == 738111            // 'rule' CaretName '<<'
         || lk == 852737            // Name^Token CaretName '>>'
         || lk == 852741            // StringLiteral CaretName '>>'
         || lk == 852799)           // 'rule' CaretName '>>'
        {
          break;
        }
      }
    }
    eventHandler.endNonterminal("Preference", e0);
  }

  private void parse_Delimiter()
  {
    eventHandler.startNonterminal("Delimiter", e0);
    parse_Name();
    lookahead1W(13);                // S | DoubleBackslash
    consume(15);                    // DoubleBackslash
    for (;;)
    {
      lookahead1W(28);              // Name^Token | StringLiteral | S | 'rule'
      whitespace();
      parse_NameOrString();
      lookahead1W(41);              // Name^Token | Number | StringLiteral | S | EOF | '.' | '<?ENCORE?>' | 'rule'
      switch (l1)
      {
      case 5:                       // StringLiteral
        lookahead2W(49);            // Name^Token | Number | StringLiteral | CaretName | S | EOF | '.' | '::=' | '<<' |
                                    // '<?ENCORE?>' | '>>' | '?' | 'rule'
        switch (lk)
        {
        case 773:                   // StringLiteral CaretName
          lookahead3W(44);          // Name^Token | Number | StringLiteral | S | EOF | '.' | '<<' | '<?ENCORE?>' |
                                    // '>>' | 'rule'
          break;
        }
        break;
      case 1:                       // Name^Token
      case 63:                      // 'rule'
        lookahead2W(59);            // Name^Token | Number | StringLiteral | CaretName | S | DoubleBackslash | EOF |
                                    // '.' | '::=' | '<<' | '<?ENCORE?>' | '>>' | '?' | 'rule'
        switch (lk)
        {
        case 769:                   // Name^Token CaretName
        case 831:                   // 'rule' CaretName
          lookahead3W(44);          // Name^Token | Number | StringLiteral | S | EOF | '.' | '<<' | '<?ENCORE?>' |
                                    // '>>' | 'rule'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 4                   // Number
       || lk == 23                  // EOF
       || lk == 37                  // '.'
       || lk == 47                  // '<?ENCORE?>'
       || lk == 1921                // Name^Token DoubleBackslash
       || lk == 1983                // 'rule' DoubleBackslash
       || lk == 5249                // Name^Token '::='
       || lk == 5253                // StringLiteral '::='
       || lk == 5311                // 'rule' '::='
       || lk == 5761                // Name^Token '<<'
       || lk == 5765                // StringLiteral '<<'
       || lk == 5823                // 'rule' '<<'
       || lk == 6657                // Name^Token '>>'
       || lk == 6661                // StringLiteral '>>'
       || lk == 6719                // 'rule' '>>'
       || lk == 6785                // Name^Token '?'
       || lk == 6789                // StringLiteral '?'
       || lk == 6847                // 'rule' '?'
       || lk == 738049              // Name^Token CaretName '<<'
       || lk == 738053              // StringLiteral CaretName '<<'
       || lk == 738111              // 'rule' CaretName '<<'
       || lk == 852737              // Name^Token CaretName '>>'
       || lk == 852741              // StringLiteral CaretName '>>'
       || lk == 852799)             // 'rule' CaretName '>>'
      {
        break;
      }
    }
    eventHandler.endNonterminal("Delimiter", e0);
  }

  private void parse_Name()
  {
    eventHandler.startNonterminal("Name", e0);
    lookahead1(5);                  // Name^Token | 'rule'
    switch (l1)
    {
    case 63:                        // 'rule'
      consume(63);                  // 'rule'
      break;
    default:
      consume(1);                   // Name^Token
    }
    eventHandler.endNonterminal("Name", e0);
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
      if (code != 14)               // S
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
    for (int i = 0; i < 72; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 323 + s - 1;
      int i1 = i0 >> 2;
      int f = EXPECTED[(i0 & 3) + EXPECTED[(i1 & 7) + EXPECTED[i1 >> 3]]];
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
      /*   0 */ "72, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 4",
      /*  34 */ "5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 8, 15, 16, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19, 20, 21",
      /*  61 */ "22, 23, 24, 8, 25, 26, 27, 26, 28, 29, 30, 30, 31, 30, 32, 33, 34, 35, 36, 30, 30, 37, 38, 39, 30, 40",
      /*  87 */ "41, 30, 30, 30, 42, 43, 44, 45, 46, 8, 47, 48, 49, 50, 51, 52, 53, 54, 55, 54, 54, 56, 57, 58, 59, 60",
      /* 113 */ "54, 61, 62, 63, 64, 54, 65, 66, 67, 54, 68, 69, 70, 8, 8"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 128; ++i) {MAP0[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] MAP1 = new int[1458];
  static
  {
    final String s1[] =
    {
      /*    0 */ "216, 291, 323, 383, 415, 908, 351, 815, 815, 447, 479, 511, 543, 575, 621, 882, 589, 681, 815, 815",
      /*   20 */ "815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 713, 745, 821, 649, 815, 815, 815, 815, 815, 815",
      /*   40 */ "815, 815, 815, 815, 815, 815, 815, 815, 777, 809, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815",
      /*   60 */ "815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 247, 247",
      /*   80 */ "247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247",
      /*  100 */ "247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247",
      /*  120 */ "247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247",
      /*  140 */ "247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 259",
      /*  160 */ "815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 247, 247, 247, 247, 247, 247, 247, 247",
      /*  180 */ "247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247",
      /*  200 */ "247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 853, 940, 949, 941, 941",
      /*  220 */ "957, 965, 973, 979, 987, 1010, 1018, 1026, 1034, 1042, 1050, 1058, 1267, 1267, 1267, 1267, 1267",
      /*  237 */ "1267, 1437, 1267, 1259, 1259, 1260, 1259, 1259, 1259, 1260, 1259, 1259, 1259, 1259, 1259, 1259, 1259",
      /*  254 */ "1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259",
      /*  271 */ "1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1261, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267",
      /*  288 */ "1267, 1267, 1267, 1259, 1259, 1259, 1259, 1259, 1259, 1359, 1260, 1258, 1257, 1259, 1259, 1259, 1259",
      /*  305 */ "1259, 1260, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1263, 1072, 1259, 1259, 1259, 1259, 1188",
      /*  322 */ "1075, 1259, 1259, 1259, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1259, 1259, 1259, 1259, 1259, 1259",
      /*  339 */ "1259, 1259, 1259, 1259, 1259, 1266, 1267, 1074, 1265, 1267, 1405, 1267, 1267, 1267, 1267, 1267, 1258",
      /*  356 */ "1259, 1259, 1264, 1125, 1325, 1404, 1267, 1399, 1405, 1125, 1259, 1259, 1259, 1259, 1259, 1259, 1259",
      /*  373 */ "1259, 1361, 1259, 1260, 1136, 1399, 1314, 1201, 1399, 1405, 1399, 1399, 1399, 1399, 1399, 1399, 1399",
      /*  390 */ "1399, 1401, 1267, 1267, 1267, 1405, 1267, 1267, 1267, 1384, 1236, 1259, 1259, 1256, 1259, 1259, 1259",
      /*  407 */ "1259, 1260, 1260, 1424, 1257, 1259, 1263, 1267, 1258, 1083, 1259, 1259, 1259, 1259, 1259, 1259, 1259",
      /*  424 */ "1259, 1258, 1083, 1259, 1259, 1259, 1259, 1092, 1267, 1259, 1259, 1259, 1259, 1259, 1259, 1105, 1114",
      /*  441 */ "1259, 1259, 1259, 1106, 1261, 1265, 1450, 1259, 1259, 1259, 1259, 1259, 1259, 1154, 1399, 1401, 1202",
      /*  458 */ "1259, 1172, 1399, 1267, 1267, 1450, 1105, 1360, 1259, 1259, 1257, 1186, 1197, 1163, 1175, 1437, 1212",
      /*  475 */ "1172, 1399, 1265, 1267, 1223, 1246, 1360, 1259, 1259, 1257, 1414, 1197, 1178, 1175, 1267, 1234, 1438",
      /*  492 */ "1399, 1244, 1267, 1450, 1235, 1256, 1259, 1259, 1257, 1254, 1154, 1277, 1097, 1267, 1267, 994, 1399",
      /*  509 */ "1267, 1267, 1450, 1105, 1360, 1259, 1259, 1257, 1357, 1154, 1203, 1175, 1438, 1212, 1117, 1399, 1267",
      /*  526 */ "1267, 1002, 1286, 1302, 1298, 1189, 1286, 1127, 1117, 1204, 1201, 1437, 1267, 1437, 1399, 1267, 1267",
      /*  543 */ "1450, 1083, 1257, 1259, 1259, 1257, 1084, 1117, 1278, 1201, 1439, 1267, 1117, 1399, 1267, 1267, 1002",
      /*  560 */ "1083, 1257, 1259, 1259, 1257, 1084, 1117, 1278, 1201, 1439, 1269, 1117, 1399, 1267, 1267, 1002, 1083",
      /*  577 */ "1257, 1259, 1259, 1257, 1259, 1117, 1164, 1201, 1437, 1267, 1117, 1399, 1267, 1267, 1267, 1267, 1267",
      /*  594 */ "1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1259, 1259",
      /*  611 */ "1259, 1259, 1261, 1267, 1259, 1259, 1259, 1259, 1260, 1267, 1258, 1259, 1259, 1259, 1259, 1260, 1310",
      /*  628 */ "1404, 1322, 1400, 1399, 1405, 1267, 1267, 1267, 1267, 1215, 1334, 1073, 1258, 1344, 1354, 1310, 1146",
      /*  645 */ "1369, 1401, 1399, 1405, 1267, 1267, 1267, 1267, 1269, 1290, 1267, 1267, 1267, 1267, 1267, 1267, 1267",
      /*  662 */ "1267, 1267, 1267, 1264, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267",
      /*  679 */ "1267, 1267, 1254, 1413, 1264, 1267, 1267, 1267, 1267, 1422, 1266, 1422, 1188, 1070, 1346, 1187, 1214",
      /*  696 */ "1267, 1267, 1267, 1267, 1269, 1267, 1336, 1268, 1300, 1264, 1267, 1267, 1267, 1267, 1433, 1266, 1435",
      /*  713 */ "1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259",
      /*  730 */ "1259, 1259, 1263, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1265, 1259, 1259",
      /*  747 */ "1261, 1261, 1259, 1259, 1259, 1259, 1261, 1261, 1259, 1425, 1259, 1259, 1259, 1261, 1259, 1259, 1259",
      /*  764 */ "1259, 1259, 1259, 1083, 1128, 1226, 1262, 1106, 1263, 1259, 1262, 1226, 1262, 1064, 1267, 1267, 1267",
      /*  781 */ "1258, 1326, 1162, 1267, 1258, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1262, 999, 1258",
      /*  798 */ "1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1447, 1072, 1259, 1259, 1259, 1259, 1262",
      /*  815 */ "1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267",
      /*  832 */ "1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1399, 1402",
      /*  849 */ "1382, 1267, 1267, 1267, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259, 1259",
      /*  866 */ "1259, 1259, 1259, 1259, 1259, 1259, 1259, 1263, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267",
      /*  883 */ "1267, 1267, 1405, 1399, 1405, 1392, 1374, 1259, 1258, 1259, 1259, 1259, 1265, 1398, 1399, 1278, 1403",
      /*  900 */ "1277, 1398, 1399, 1401, 1398, 1382, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1267, 1258, 1259, 1259",
      /*  917 */ "1259, 1260, 1435, 1258, 1259, 1259, 1259, 1260, 1267, 1398, 1399, 1160, 1399, 1399, 1142, 1379, 1267",
      /*  934 */ "1259, 1259, 1259, 1264, 1264, 1267, 72, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 3, 4, 5, 6",
      /*  961 */ "7, 8, 9, 10, 11, 12, 13, 14, 8, 15, 16, 17, 18, 18, 18, 18, 18, 18, 18, 18, 19, 20, 21, 22, 23, 24",
      /*  987 */ "8, 25, 26, 27, 26, 28, 29, 30, 8, 8, 8, 8, 8, 71, 71, 8, 8, 71, 71, 8, 30, 30, 30, 30, 31, 30, 32",
      /* 1014 */ "33, 34, 35, 36, 30, 30, 37, 38, 39, 30, 40, 41, 30, 30, 30, 42, 43, 44, 45, 46, 8, 47, 48, 49, 50",
      /* 1039 */ "51, 52, 53, 54, 55, 54, 54, 56, 57, 58, 59, 60, 54, 61, 62, 63, 64, 54, 65, 66, 67, 54, 68, 69, 70",
      /* 1064 */ "8, 8, 8, 8, 8, 71, 8, 30, 8, 8, 8, 8, 8, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 8, 30, 30, 30",
      /* 1092 */ "30, 30, 8, 71, 71, 71, 71, 8, 71, 71, 71, 8, 8, 30, 30, 30, 30, 30, 8, 8, 30, 30, 30, 8, 8, 30, 30",
      /* 1119 */ "8, 8, 8, 8, 71, 71, 71, 30, 30, 30, 30, 30, 30, 30, 8, 30, 8, 30, 30, 30, 30, 8, 30, 71, 71, 8, 71",
      /* 1146 */ "71, 71, 8, 71, 71, 30, 8, 8, 30, 30, 8, 8, 71, 30, 71, 71, 8, 71, 71, 71, 71, 71, 8, 8, 71, 71, 30",
      /* 1173 */ "30, 71, 71, 8, 8, 71, 71, 71, 8, 8, 8, 8, 71, 30, 8, 30, 8, 8, 8, 30, 30, 8, 8, 8, 30, 30, 8, 8, 71",
      /* 1202 */ "8, 71, 71, 71, 71, 8, 8, 8, 71, 71, 8, 8, 8, 8, 30, 30, 8, 30, 8, 8, 30, 8, 8, 71, 8, 8, 30, 30, 30",
      /* 1231 */ "8, 30, 30, 8, 30, 30, 30, 30, 8, 30, 8, 30, 30, 71, 71, 30, 30, 30, 8, 8, 8, 8, 30, 30, 8, 30, 30, 8",
      /* 1259 */ "30, 30, 30, 30, 30, 30, 30, 30, 8, 8, 8, 8, 8, 8, 8, 8, 30, 8, 71, 71, 71, 71, 71, 71, 8, 71, 71, 30",
      /* 1287 */ "30, 30, 8, 8, 8, 30, 30, 8, 8, 30, 8, 8, 30, 30, 8, 30, 8, 30, 30, 30, 30, 8, 8, 30, 71, 30, 30, 71",
      /* 1315 */ "71, 71, 71, 71, 30, 30, 71, 30, 30, 30, 30, 30, 30, 71, 71, 71, 71, 71, 71, 30, 8, 30, 8, 8, 30, 8",
      /* 1341 */ "8, 30, 30, 8, 30, 30, 30, 8, 30, 8, 30, 8, 30, 8, 8, 30, 30, 8, 30, 30, 8, 8, 30, 30, 30, 30, 30, 8",
      /* 1369 */ "30, 30, 30, 30, 30, 8, 71, 8, 8, 8, 8, 71, 71, 8, 71, 8, 8, 8, 8, 8, 8, 30, 71, 8, 8, 8, 8, 8, 71, 8",
      /* 1399 */ "71, 71, 71, 71, 71, 71, 71, 71, 8, 8, 8, 8, 8, 8, 8, 30, 8, 30, 30, 8, 30, 30, 8, 8, 8, 8, 8, 30, 8",
      /* 1428 */ "30, 8, 30, 8, 30, 8, 8, 8, 30, 8, 8, 8, 8, 8, 8, 8, 71, 71, 8, 30, 30, 30, 8, 71, 71, 71, 8, 30, 30",
      /* 1457 */ "30"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 1458; ++i) {MAP1[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] MAP2 = new int[6];
  static
  {
    final String s1[] =
    {
      /* 0 */ "57344, 65536, 65533, 1114111, 8, 8"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 6; ++i) {MAP2[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] INITIAL = new int[84];
  static
  {
    final String s1[] =
    {
      /*  0 */ "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28",
      /* 28 */ "29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54",
      /* 54 */ "55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80",
      /* 80 */ "81, 82, 83, 84"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 84; ++i) {INITIAL[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] TRANSITION = new int[7507];
  static
  {
    final String s1[] =
    {
      /*    0 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*   17 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*   34 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*   51 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4672, 4679, 4684, 4908",
      /*   68 */ "4692, 4906, 4906, 4906, 4906, 4906, 4696, 4708, 5698, 5271, 5723, 6855, 6855, 4962, 5700, 4737, 6855",
      /*   85 */ "7450, 4748, 5035, 5210, 4756, 6854, 7487, 5650, 6851, 4764, 5574, 5571, 4766, 5577, 4774, 4782, 4787",
      /*  102 */ "4792, 4797, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  119 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4813, 4820, 4825, 4842, 4833, 4850, 4839, 4876",
      /*  136 */ "4879, 4878, 4857, 4887, 5698, 5271, 5723, 6855, 6855, 4962, 5700, 4737, 6855, 7450, 4748, 5035, 5210",
      /*  153 */ "4756, 6854, 7487, 5650, 6851, 4764, 5574, 5571, 4766, 5577, 4774, 4782, 4787, 4792, 4797, 4802, 6855",
      /*  170 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  187 */ "6855, 6855, 6855, 6855, 6855, 4700, 4901, 4684, 4908, 4692, 4906, 4906, 4906, 4906, 4906, 4696, 4708",
      /*  204 */ "5698, 5271, 5723, 6855, 6855, 4962, 5700, 4737, 6855, 7450, 4748, 5035, 5210, 4756, 6854, 7487, 5650",
      /*  221 */ "6851, 4764, 5574, 5571, 4766, 5577, 4774, 4782, 4787, 4792, 4797, 4802, 6855, 6855, 6855, 6855, 6855",
      /*  238 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  255 */ "6855, 4916, 6881, 4937, 4946, 4954, 4944, 4944, 4944, 4944, 4944, 4958, 4970, 5698, 5271, 6855, 6855",
      /*  272 */ "6855, 6351, 5700, 4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571",
      /*  289 */ "4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  306 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4996, 4719, 5158",
      /*  323 */ "5287, 5004, 5010, 5011, 5011, 5011, 5011, 5019, 4970, 5698, 6783, 6855, 6855, 6855, 6351, 5700, 4737",
      /*  340 */ "6855, 7450, 4994, 6361, 7455, 6699, 6854, 7280, 4740, 5250, 5592, 5574, 5371, 5594, 6745, 6741, 6026",
      /*  357 */ "7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  374 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 6278, 5031, 6231, 6855",
      /*  391 */ "4861, 4868, 4865, 5043, 4970, 5698, 5271, 6855, 6855, 6855, 6351, 5700, 4737, 6855, 7450, 5055, 6174",
      /*  408 */ "7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802",
      /*  425 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  442 */ "6855, 6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 7178, 5646, 5311, 6855, 5076, 5083, 5080, 5072",
      /*  459 */ "4970, 5698, 5271, 6855, 6855, 6855, 6351, 5700, 4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854, 6084",
      /*  476 */ "4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855",
      /*  493 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  510 */ "6855, 6855, 4916, 6855, 5158, 7178, 5646, 6855, 6855, 6855, 6855, 6855, 6855, 4970, 5698, 5271, 6855",
      /*  527 */ "6855, 6855, 6351, 5700, 4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574",
      /*  544 */ "5571, 4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  561 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6855",
      /*  578 */ "5158, 7178, 5646, 6855, 5091, 6953, 5105, 5102, 5113, 4970, 5698, 5271, 6855, 6855, 6855, 6351, 5700",
      /*  595 */ "4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741",
      /*  612 */ "6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  629 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 5505, 5125, 5131",
      /*  646 */ "5132, 5132, 5132, 5132, 6685, 4970, 5698, 5297, 6855, 6855, 6855, 6351, 5700, 4737, 6855, 7450, 4994",
      /*  663 */ "5047, 7455, 7108, 6173, 6971, 4740, 6170, 5547, 6309, 6306, 5549, 6312, 6741, 6026, 7239, 7238, 7156",
      /*  680 */ "4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  697 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 7178, 5646, 5337, 5142, 5140, 5150, 5150",
      /*  714 */ "5154, 4970, 5698, 5271, 6855, 6855, 6855, 6351, 5700, 4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854",
      /*  731 */ "6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855",
      /*  748 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  765 */ "6855, 6855, 6855, 4916, 6855, 5158, 5168, 5646, 6448, 5166, 6450, 5176, 5178, 5186, 4970, 5698, 5271",
      /*  782 */ "6855, 6855, 6855, 6351, 5700, 4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854, 6084, 4740, 6851, 5547",
      /*  799 */ "5574, 5571, 4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  816 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5198",
      /*  833 */ "5218, 5190, 7178, 5646, 6855, 5606, 6855, 6855, 5227, 5231, 5243, 6253, 5271, 6855, 5261, 5269, 6351",
      /*  850 */ "6255, 4737, 6855, 5205, 4994, 5219, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577",
      /*  867 */ "6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  884 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 7178, 5646",
      /*  901 */ "6855, 7408, 6855, 6855, 5279, 5283, 4970, 5698, 5271, 6855, 4986, 5295, 6351, 5700, 4737, 6855, 7450",
      /*  918 */ "4994, 6174, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741, 6026, 7239, 7238",
      /*  935 */ "7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /*  952 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 7178, 5646, 6855, 6855, 6855, 4729",
      /*  969 */ "5305, 5319, 5331, 5698, 5271, 4714, 6082, 6082, 5919, 5700, 5345, 5353, 6003, 5364, 5917, 7455, 6042",
      /*  986 */ "6854, 6187, 4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855",
      /* 1003 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1020 */ "6855, 6855, 6855, 6855, 4916, 6855, 5158, 7178, 5382, 5400, 5405, 5418, 5419, 5416, 5427, 5331, 5698",
      /* 1037 */ "5271, 6855, 6082, 6082, 5919, 5700, 5439, 5353, 6003, 4994, 5917, 7455, 6042, 6854, 6187, 4805, 6851",
      /* 1054 */ "5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 6780, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1071 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1088 */ "4916, 6982, 5451, 5485, 5447, 5459, 5482, 5493, 5470, 5465, 5474, 4970, 5836, 5271, 6855, 6855, 6855",
      /* 1105 */ "6351, 7192, 4737, 6855, 7450, 4994, 6323, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766",
      /* 1122 */ "5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1139 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 7178",
      /* 1156 */ "5646, 6855, 6855, 6855, 6855, 6855, 7201, 5331, 5698, 5271, 4724, 5501, 6082, 5919, 5700, 5513, 5521",
      /* 1173 */ "6003, 5533, 5557, 5713, 6042, 5253, 5567, 4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239",
      /* 1190 */ "7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1207 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5585, 6856, 5158, 5784, 5780, 7200, 7199, 7197",
      /* 1224 */ "7374, 7377, 5602, 4970, 5614, 5271, 6855, 6855, 6855, 6351, 5700, 4737, 6855, 7450, 4994, 6174, 6855",
      /* 1241 */ "6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855",
      /* 1258 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1275 */ "6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 7178, 5646, 6855, 6855, 5624, 6855, 5625, 6855, 4970",
      /* 1292 */ "5698, 5271, 6855, 6855, 6855, 6351, 5700, 4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854, 6084, 4740",
      /* 1309 */ "6851, 5547, 5574, 5571, 4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855",
      /* 1326 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1343 */ "6855, 4916, 6855, 5158, 5641, 5634, 5658, 5666, 5671, 5679, 5687, 5694, 4970, 5698, 5392, 6855, 5387",
      /* 1360 */ "6855, 5708, 5700, 4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571",
      /* 1377 */ "4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1394 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 5721, 5158",
      /* 1411 */ "7178, 5646, 6855, 6855, 6855, 5794, 6508, 6512, 4970, 5698, 5271, 6855, 6855, 6855, 5023, 5700, 4737",
      /* 1428 */ "6855, 7450, 4994, 6174, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741, 6026",
      /* 1445 */ "7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1462 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 5775, 7401, 5731, 7406",
      /* 1479 */ "5732, 6855, 7456, 5740, 4970, 6392, 5117, 6855, 6855, 5757, 5765, 7053, 4737, 6855, 7450, 5792, 6174",
      /* 1496 */ "7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 6497, 5577, 6741, 5802, 7239, 7238, 7156, 4802",
      /* 1513 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1530 */ "6855, 6855, 6855, 6855, 6855, 6855, 5813, 6855, 5235, 5851, 7171, 7177, 7176, 5850, 6855, 5828, 5832",
      /* 1547 */ "4970, 6935, 7065, 6855, 5844, 6794, 5859, 4923, 4737, 6855, 7450, 5872, 6174, 7455, 6083, 6854, 6084",
      /* 1564 */ "4740, 6851, 5547, 5374, 5882, 4766, 5577, 6736, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855",
      /* 1581 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1598 */ "6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626",
      /* 1615 */ "6082, 6082, 5919, 5700, 5948, 5956, 6003, 5533, 5917, 7455, 5968, 6854, 5567, 4805, 6851, 5747, 5885",
      /* 1632 */ "5981, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1649 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855",
      /* 1666 */ "5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700",
      /* 1683 */ "5948, 5956, 6003, 5533, 5917, 7455, 5968, 6854, 5567, 4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321",
      /* 1700 */ "6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1717 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927",
      /* 1734 */ "5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700, 5948, 5992, 6003, 5533",
      /* 1751 */ "5998, 7455, 6016, 6854, 5567, 4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156",
      /* 1768 */ "4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1785 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928",
      /* 1802 */ "5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700, 5948, 6037, 6003, 5533, 6665, 7455, 5968, 6854",
      /* 1819 */ "6055, 4805, 6851, 6221, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855",
      /* 1836 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1853 */ "6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271",
      /* 1870 */ "5626, 6082, 6082, 5919, 5700, 5948, 6075, 6003, 5533, 5917, 7455, 5968, 6854, 5567, 4805, 6851, 5747",
      /* 1887 */ "5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1904 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896",
      /* 1921 */ "6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919",
      /* 1938 */ "5700, 5439, 5353, 6003, 4994, 5917, 7455, 5968, 6854, 6187, 4805, 6851, 5747, 5885, 5571, 5749, 6593",
      /* 1955 */ "7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 1972 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911",
      /* 1989 */ "5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700, 5439, 5353, 6003",
      /* 2006 */ "4994, 5917, 7455, 5968, 6854, 6187, 4805, 6092, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238",
      /* 2023 */ "7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2040 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928",
      /* 2057 */ "5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700, 5439, 5353, 6003, 4994, 5917, 7455, 6103",
      /* 2074 */ "6854, 6187, 4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855",
      /* 2091 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2108 */ "6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698",
      /* 2125 */ "5271, 5626, 6082, 6082, 5919, 5700, 5439, 5353, 6003, 4994, 5917, 7455, 5968, 6854, 6187, 4805, 6851",
      /* 2142 */ "5747, 5885, 5571, 6111, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2159 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2176 */ "5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082",
      /* 2193 */ "5919, 5700, 5439, 5353, 6003, 4994, 5917, 7455, 5968, 6854, 7229, 4805, 6851, 5747, 5885, 5571, 5749",
      /* 2210 */ "6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2227 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356",
      /* 2244 */ "5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700, 5439, 5353",
      /* 2261 */ "6003, 4994, 6128, 7455, 5968, 6854, 6187, 4805, 6141, 6206, 5885, 5571, 5749, 6593, 7321, 6026, 7239",
      /* 2278 */ "7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2295 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928",
      /* 2312 */ "5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700, 5439, 5353, 6003, 4994, 6152, 7455",
      /* 2329 */ "5968, 6854, 7123, 4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855",
      /* 2346 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2363 */ "6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331",
      /* 2380 */ "5698, 5271, 5626, 6082, 6082, 5919, 5700, 5439, 5353, 6003, 4994, 5917, 7455, 6165, 6854, 6187, 5094",
      /* 2397 */ "6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855",
      /* 2414 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2431 */ "6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082",
      /* 2448 */ "6082, 5919, 5700, 5439, 5353, 6003, 4994, 5917, 7455, 5968, 6854, 6187, 4805, 6851, 5903, 5885, 5571",
      /* 2465 */ "5749, 6608, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2482 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940",
      /* 2499 */ "5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700, 5439",
      /* 2516 */ "6182, 6003, 4994, 5917, 7455, 5968, 6854, 6187, 4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026",
      /* 2533 */ "7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2550 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928",
      /* 2567 */ "5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6199, 6082, 5919, 5700, 5439, 5353, 6003, 4994, 5917",
      /* 2584 */ "7455, 5968, 6854, 6187, 4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802",
      /* 2601 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2618 */ "6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936",
      /* 2635 */ "5331, 5698, 5271, 5626, 6214, 6082, 5919, 5700, 5439, 5353, 6003, 4994, 5917, 7455, 5968, 6854, 6187",
      /* 2652 */ "4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855",
      /* 2669 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2686 */ "6855, 6855, 4916, 6855, 5158, 7178, 5646, 4929, 6239, 6244, 6266, 6263, 6249, 4970, 5698, 5271, 5874",
      /* 2703 */ "6855, 6855, 6351, 5700, 4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574",
      /* 2720 */ "5571, 4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2737 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6541",
      /* 2754 */ "5158, 7178, 5646, 6277, 6855, 6543, 6855, 6855, 6274, 6286, 5698, 5271, 6855, 6855, 6855, 6351, 5700",
      /* 2771 */ "4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741",
      /* 2788 */ "6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2805 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6294, 6855, 5158, 6855, 6320, 6855",
      /* 2822 */ "6855, 6320, 6855, 6322, 6855, 6331, 5698, 5271, 6855, 6855, 6855, 6351, 5700, 6855, 6855, 7450, 4994",
      /* 2839 */ "6349, 7455, 6359, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741, 6026, 7239, 7238, 7156",
      /* 2856 */ "4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2873 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 7178, 6369, 4985, 4984, 4982, 6855, 7499",
      /* 2890 */ "6388, 4970, 5698, 5271, 6855, 6855, 6855, 6351, 5700, 4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854",
      /* 2907 */ "6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855",
      /* 2924 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2941 */ "6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271",
      /* 2958 */ "5626, 6082, 6082, 5919, 5700, 5439, 5353, 6670, 4994, 5917, 7455, 5968, 6854, 6187, 6400, 6851, 5747",
      /* 2975 */ "5885, 5571, 5749, 7138, 7321, 6417, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 2992 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896",
      /* 3009 */ "6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919",
      /* 3026 */ "5700, 5948, 5956, 6439, 6458, 6478, 6950, 5968, 6854, 5567, 4805, 6851, 5747, 5885, 7127, 5749, 6593",
      /* 3043 */ "6491, 6026, 6524, 6523, 6532, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3060 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911",
      /* 3077 */ "5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700, 5948, 5956, 6003",
      /* 3094 */ "5533, 6478, 7455, 5968, 6854, 5567, 5408, 6851, 6407, 6117, 5571, 6409, 6120, 6551, 6559, 6643, 6642",
      /* 3111 */ "6562, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3128 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928",
      /* 3145 */ "5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700, 5948, 5956, 6003, 5533, 6478, 7455, 5968",
      /* 3162 */ "6144, 5567, 4805, 6851, 5888, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 6774, 4802, 6855, 6855",
      /* 3179 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3196 */ "6855, 6855, 6855, 6855, 5896, 6445, 5960, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698",
      /* 3213 */ "5271, 5626, 6082, 6082, 5559, 5700, 6570, 6578, 6003, 5533, 6478, 7455, 5968, 6854, 5567, 4805, 6851",
      /* 3230 */ "5820, 6590, 5571, 5749, 6601, 7321, 6026, 7094, 7238, 5805, 4802, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3247 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3264 */ "5896, 6855, 6651, 5356, 6659, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 6678, 5626, 6082, 6697",
      /* 3281 */ "5919, 5700, 5948, 5956, 6707, 5533, 6478, 7455, 5968, 6854, 6715, 4805, 6851, 6301, 6820, 6817, 6067",
      /* 3298 */ "6723, 6731, 6753, 6767, 6757, 6759, 6791, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3315 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356",
      /* 3332 */ "5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 7031, 5948, 5956",
      /* 3349 */ "6003, 5533, 6478, 7455, 5968, 6802, 6813, 4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321, 6828, 7239",
      /* 3366 */ "6832, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3383 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 7341, 5911, 5927, 5928, 5928",
      /* 3400 */ "5928, 5928, 5936, 5331, 5698, 5271, 7294, 6082, 6082, 5323, 5700, 5439, 5353, 6133, 4994, 6478, 7455",
      /* 3417 */ "5968, 6854, 6187, 4805, 6851, 6840, 5984, 5571, 6848, 6593, 7321, 6026, 6907, 7238, 7156, 4802, 6855",
      /* 3434 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3451 */ "6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331",
      /* 3468 */ "5698, 5271, 5626, 6082, 6082, 5919, 5700, 5439, 5353, 6003, 4994, 6478, 7455, 5968, 6854, 6187, 4805",
      /* 3485 */ "6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855",
      /* 3502 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3519 */ "6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 6864, 5698, 5271, 5626, 6082",
      /* 3536 */ "6082, 5919, 5700, 5439, 5353, 6003, 6878, 6478, 5770, 5968, 6503, 6187, 4893, 6191, 5747, 5885, 5571",
      /* 3553 */ "6889, 6622, 7321, 6026, 7239, 7238, 6900, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3570 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940",
      /* 3587 */ "5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 4976, 6917, 5439",
      /* 3604 */ "5353, 6003, 6931, 6943, 7455, 6961, 6969, 6187, 4805, 6851, 5747, 5885, 6021, 5749, 6593, 7321, 6026",
      /* 3621 */ "7239, 7238, 7156, 6979, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3638 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 6990, 5927, 5928",
      /* 3655 */ "5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700, 6998, 7006, 6003, 4994, 6478",
      /* 3672 */ "7455, 5968, 6854, 6187, 4805, 6851, 5747, 5885, 5571, 5749, 6629, 7014, 6026, 6431, 6636, 7080, 4802",
      /* 3689 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3706 */ "6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936",
      /* 3723 */ "5331, 7029, 5271, 7039, 6082, 6082, 5919, 7047, 5439, 5353, 6483, 4994, 6478, 5864, 5968, 6854, 6187",
      /* 3740 */ "4805, 6376, 5747, 5885, 5973, 5749, 6615, 7073, 6026, 7088, 6427, 7156, 6538, 6855, 6855, 6855, 6855",
      /* 3757 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3774 */ "6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626",
      /* 3791 */ "7106, 6082, 5919, 5700, 5439, 5353, 6003, 4994, 6478, 7309, 5968, 6854, 6187, 7116, 6851, 5747, 7135",
      /* 3808 */ "5571, 5749, 6593, 7146, 6026, 7239, 7154, 7098, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3825 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855",
      /* 3842 */ "5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5616",
      /* 3859 */ "5439, 5353, 6003, 4994, 6478, 7455, 5968, 6854, 6187, 4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321",
      /* 3876 */ "6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3893 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 7164, 6855, 7186, 6341, 7209, 7215",
      /* 3910 */ "7216, 7216, 7216, 7216, 7224, 5331, 5698, 5271, 5626, 6082, 6082, 5919, 5700, 5439, 5353, 6003, 4994",
      /* 3927 */ "6478, 7455, 5968, 6854, 6187, 4805, 6047, 5747, 6062, 5571, 5749, 6593, 7321, 7237, 6422, 7238, 7021",
      /* 3944 */ "4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 3961 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 6870, 5356, 5911, 5927, 7247, 7252, 5928, 7254",
      /* 3978 */ "5936, 5331, 5698, 5271, 7262, 6082, 6082, 6582, 5700, 7270, 5353, 6157, 4994, 6478, 7455, 5968, 6854",
      /* 3995 */ "6187, 4805, 6851, 5747, 5885, 6465, 5749, 6593, 7321, 6470, 7239, 7238, 7156, 4802, 6855, 6855, 6855",
      /* 4012 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4029 */ "6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271",
      /* 4046 */ "5626, 6082, 6082, 5919, 5700, 5439, 5353, 6003, 7278, 6478, 7455, 5968, 6095, 6187, 5540, 6892, 5747",
      /* 4063 */ "5885, 5571, 5749, 6593, 7321, 6026, 7239, 6909, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4080 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896",
      /* 4097 */ "6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 7288, 6923, 5271, 5626, 6082, 6082, 5919",
      /* 4114 */ "5700, 5439, 5353, 6003, 4994, 7302, 7455, 5968, 6854, 6187, 4805, 6851, 5747, 7317, 5571, 5749, 6593",
      /* 4131 */ "7321, 6026, 7239, 7238, 6029, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4148 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 7353, 5911",
      /* 4165 */ "5927, 5928, 5928, 5928, 5928, 5936, 5331, 5698, 5271, 5626, 6082, 6082, 5431, 5700, 5439, 5353, 6003",
      /* 4182 */ "4994, 6478, 7455, 5968, 6854, 6187, 4805, 6226, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238",
      /* 4199 */ "7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4216 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 5896, 6855, 5940, 7436, 5911, 5927, 5928, 5928, 5928",
      /* 4233 */ "5928, 5936, 5331, 5698, 7329, 6380, 7337, 6082, 5525, 5700, 5439, 5353, 6003, 4994, 6478, 6008, 5968",
      /* 4250 */ "7349, 6187, 4805, 6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855",
      /* 4267 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4284 */ "6855, 6855, 6855, 6855, 5896, 6855, 5940, 5356, 5911, 5927, 5928, 5928, 5928, 5928, 5936, 5331, 7469",
      /* 4301 */ "5271, 5626, 6082, 6082, 5919, 5700, 5439, 5353, 6003, 4994, 6478, 7455, 5968, 6854, 6187, 4805, 6851",
      /* 4318 */ "5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4335 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4352 */ "4916, 6855, 5158, 7178, 5646, 6855, 7361, 7366, 7388, 7385, 7371, 4970, 5698, 5271, 6855, 6855, 6855",
      /* 4369 */ "6351, 5700, 4737, 6855, 7450, 4994, 6174, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766",
      /* 4386 */ "5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4403 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 7396",
      /* 4420 */ "5646, 6805, 7422, 7416, 7424, 7424, 7432, 4970, 5698, 5271, 6855, 6855, 6855, 6351, 5700, 4737, 6855",
      /* 4437 */ "7450, 4994, 6174, 7455, 6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741, 6026, 7239",
      /* 4454 */ "7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4471 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 4916, 6337, 5158, 7178, 5646, 6855, 6855, 6855",
      /* 4488 */ "6855, 6855, 6855, 4970, 5698, 5271, 6855, 6855, 7059, 7444, 5700, 4737, 6855, 7450, 4994, 6174, 7455",
      /* 4505 */ "6083, 6854, 6084, 4740, 6851, 5547, 5574, 5571, 4766, 5577, 6741, 6026, 7239, 7238, 7156, 4802, 6855",
      /* 4522 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4539 */ "6855, 6855, 6855, 6855, 6855, 4916, 6855, 5158, 7178, 5646, 6855, 6855, 6855, 6855, 6855, 7201, 5331",
      /* 4556 */ "5698, 5271, 6855, 6082, 6082, 5919, 5700, 5439, 5353, 6003, 4994, 5917, 7455, 6042, 6854, 6187, 4805",
      /* 4573 */ "6851, 5747, 5885, 5571, 5749, 6593, 7321, 6026, 7239, 7238, 7156, 4802, 6855, 6855, 6855, 6855, 6855",
      /* 4590 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4607 */ "6855, 6855, 6515, 6855, 7485, 7464, 7477, 7483, 5061, 5064, 5063, 7495, 6689, 6855, 6855, 6855, 6855",
      /* 4624 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4641 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855",
      /* 4658 */ "6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 6855, 1621, 4610, 0, 0",
      /* 4676 */ "0, 0, 1626, 1621, 1621, 1621, 1621, 0, 7773, 7773, 7773, 7773, 1621, 1621, 1621, 7773, 7773, 7773",
      /* 4694 */ "5748, 5748, 7773, 7773, 7773, 7773, 1621, 0, 0, 0, 0, 0, 1626, 1621, 0, 1626, 91, 0, 7773, 94, 0, 0",
      /* 4716 */ "0, 155, 155, 0, 0, 0, 0, 12800, 0, 0, 0, 0, 4252, 0, 0, 0, 0, 18944, 0, 0, 18944, 0, 0, 6144, 0, 0",
      /* 4742 */ "0, 0, 0, 0, 243, 0, 0, 147, 0, 0, 0, 0, 0, 200, 201, 0, 0, 0, 0, 0, 207, 208, 0, 242, 243, 243, 0, 0",
      /* 4770 */ "0, 208, 209, 0, 0, 243, 288, 0, 252, 0, 288, 288, 0, 252, 0, 243, 252, 294, 298, 252, 294, 243, 252",
      /* 4793 */ "306, 243, 252, 294, 243, 252, 315, 243, 252, 294, 294, 294, 0, 0, 0, 0, 0, 0, 1255, 0, 1536, 4610, 0",
      /* 4816 */ "0, 0, 0, 1627, 1536, 1536, 1536, 1536, 0, 7680, 7680, 7680, 7680, 1536, 1536, 1536, 7680, 7680, 7680",
      /* 4835 */ "5748, 5748, 7680, 7799, 7680, 7799, 7799, 7680, 7680, 7680, 7680, 7680, 7680, 1536, 5748, 7680, 7799",
      /* 4852 */ "7799, 7680, 7799, 7799, 7680, 7799, 7799, 7799, 7799, 0, 0, 0, 0, 0, 124, 124, 0, 0, 124, 0, 124",
      /* 4873 */ "124, 0, 124, 7680, 7680, 7680, 7799, 7799, 7799, 7799, 7799, 7799, 7799, 7799, 0, 91, 91, 0, 0",
      /* 4892 */ "73216, 0, 0, 0, 240, 241, 0, 1255, 0, 1621, 1621, 1621, 1621, 5120, 7773, 7773, 7773, 7773, 7773",
      /* 4911 */ "7773, 7773, 7773, 1621, 5748, 0, 4610, 0, 0, 0, 0, 91, 0, 0, 178, 148, 103, 103, 0, 0, 0, 28672, 0",
      /* 4934 */ "0, 28672, 127, 94, 94, 94, 94, 101, 103, 0, 94, 94, 94, 94, 94, 94, 94, 94, 0, 5748, 94, 94, 5748",
      /* 4957 */ "5748, 94, 94, 94, 94, 0, 0, 0, 0, 0, 142, 0, 0, 0, 91, 91, 0, 0, 94, 0, 0, 0, 1193, 0, 171, 0, 0, 0",
      /* 4985 */ "3704, 0, 0, 0, 0, 0, 0, 0, 23040, 0, 147, 0, 0, 0, 0, 0, 0, 91, 12800, 0, 111, 5748, 5748, 0, 111, 0",
      /* 5011 */ "111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 0, 0, 0, 0, 0, 171, 21504, 0, 0, 0, 5749",
      /* 5034 */ "5749, 0, 0, 0, 0, 0, 207, 0, 171, 124, 124, 0, 124, 0, 0, 0, 0, 0, 209, 0, 171, 0, 147, 0, 0, 0, 199",
      /* 5061 */ "0, 0, 0, 12288, 12288, 12288, 12288, 12288, 12288, 12288, 12288, 13312, 13312, 0, 13312, 0, 0, 0, 0",
      /* 5080 */ "0, 13312, 13312, 0, 0, 13312, 0, 13312, 13312, 0, 13312, 0, 0, 13824, 0, 0, 0, 0, 0, 0, 1255, 245, 0",
      /* 5103 */ "13824, 13824, 0, 0, 13824, 0, 13824, 13824, 0, 13824, 13824, 13824, 0, 13824, 0, 0, 0, 0, 0, 27136",
      /* 5123 */ "111, 112, 0, 112, 5748, 5748, 0, 112, 0, 112, 112, 112, 112, 112, 112, 112, 112, 14336, 14336, 14336",
      /* 5143 */ "0, 0, 14336, 14336, 14336, 14336, 14336, 14336, 14336, 14336, 14336, 14336, 14336, 14336, 14336, 0",
      /* 5159 */ "0, 0, 0, 101, 103, 0, 0, 14848, 0, 0, 14848, 0, 0, 0, 0, 0, 5748, 14848, 0, 14848, 14848, 14848",
      /* 5181 */ "14848, 14848, 14848, 14848, 14848, 14848, 14848, 14848, 14848, 0, 0, 0, 0, 101, 104, 0, 0, 0, 4610",
      /* 5200 */ "86, 0, 0, 0, 91, 0, 0, 192, 171, 171, 171, 0, 0, 0, 0, 0, 0, 200, 86, 0, 0, 0, 0, 0, 0, 0, 192, 137",
      /* 5228 */ "15360, 15360, 15360, 15360, 15360, 15360, 15360, 0, 0, 0, 0, 102, 103, 0, 0, 0, 91, 91, 0, 0, 94",
      /* 5249 */ "142, 0, 0, 223, 209, 0, 0, 0, 0, 0, 0, 6872, 0, 0, 20622, 0, 0, 0, 0, 22528, 0, 35328, 0, 0, 0, 0, 0",
      /* 5276 */ "0, 111, 112, 138, 17408, 17408, 17408, 17408, 17408, 17408, 17408, 0, 0, 0, 0, 111, 0, 0, 5748, 0",
      /* 5296 */ "35840, 0, 0, 0, 0, 0, 0, 111, 3072, 0, 18944, 0, 0, 0, 18944, 0, 0, 0, 13312, 0, 0, 13312, 0, 0",
      /* 5320 */ "18944, 0, 18944, 0, 0, 0, 1112, 0, 172, 0, 0, 1112, 91, 91, 0, 0, 94, 0, 0, 0, 14336, 0, 0, 14336, 0",
      /* 5345 */ "0, 0, 6144, 182, 0, 0, 0, 3744, 1112, 0, 0, 0, 0, 0, 0, 1112, 1112, 0, 5748, 0, 147, 0, 0, 0, 0, 182",
      /* 5371 */ "0, 0, 223, 209, 0, 0, 252, 0, 243, 243, 267, 0, 19456, 5748, 5748, 19456, 0, 0, 0, 0, 23552, 0, 0, 0",
      /* 5395 */ "0, 23552, 0, 111, 112, 0, 19456, 19456, 19456, 19456, 0, 19456, 19456, 0, 0, 0, 0, 0, 0, 1268, 0",
      /* 5416 */ "19456, 19456, 19456, 0, 0, 19456, 0, 19456, 19456, 0, 19456, 19456, 19456, 19456, 19456, 0, 0, 0",
      /* 5434 */ "1112, 0, 173, 0, 0, 0, 0, 6144, 0, 0, 0, 0, 3744, 95, 95, 5748, 5748, 95, 95, 95, 95, 101, 103, 0",
      /* 5458 */ "95, 95, 95, 123, 95, 95, 123, 95, 123, 123, 20091, 20091, 123, 20091, 123, 20091, 123, 123, 20091",
      /* 5477 */ "123, 0, 15872, 0, 0, 20063, 95, 123, 95, 95, 95, 95, 95, 95, 0, 5748, 95, 95, 95, 95, 20091, 123",
      /* 5499 */ "123, 20091, 1112, 0, 0, 4259, 0, 0, 0, 0, 112, 0, 0, 5748, 0, 0, 6144, 4252, 4279, 0, 185, 3744",
      /* 5521 */ "1112, 0, 4259, 4260, 0, 0, 0, 1112, 0, 174, 0, 0, 0, 147, 0, 0, 0, 0, 4279, 0, 0, 239, 0, 0, 0, 1255",
      /* 5547 */ "0, 0, 243, 243, 0, 0, 0, 208, 224, 0, 185, 1112, 0, 0, 0, 1112, 0, 171, 0, 175, 6873, 0, 1112, 0, 0",
      /* 5572 */ "0, 208, 209, 0, 0, 252, 0, 243, 243, 0, 208, 209, 252, 0, 4610, 0, 20992, 0, 0, 91, 0, 0, 243, 243",
      /* 5596 */ "0, 0, 0, 223, 209, 0, 97, 97, 0, 97, 0, 0, 0, 0, 131, 132, 0, 0, 143, 0, 0, 0, 147, 147, 103, 103, 0",
      /* 5623 */ "180, 0, 22016, 0, 0, 0, 0, 0, 0, 0, 3744, 0, 0, 5748, 5748, 0, 108, 109, 108, 0, 0, 109, 0, 0, 0",
      /* 5648 */ "5748, 5748, 0, 0, 0, 0, 0, 242, 243, 0, 109, 122, 122, 0, 125, 126, 0, 122, 108, 125, 122, 128, 128",
      /* 5671 */ "128, 128, 128, 125, 126, 122, 122, 135, 135, 135, 122, 135, 122, 122, 135, 122, 128, 122, 122, 135",
      /* 5691 */ "135, 122, 135, 125, 125, 139, 125, 0, 0, 0, 0, 147, 147, 103, 103, 0, 0, 0, 0, 23552, 0, 0, 171, 0",
      /* 5715 */ "0, 0, 0, 0, 6872, 0, 0, 26112, 0, 0, 0, 0, 0, 0, 119, 0, 110, 0, 0, 0, 110, 0, 0, 0, 0, 110, 110",
      /* 5742 */ "110, 110, 0, 0, 28160, 0, 0, 243, 1255, 0, 0, 0, 208, 209, 0, 0, 0, 16384, 0, 17920, 0, 0, 26624",
      /* 5765 */ "16384, 17920, 0, 0, 0, 171, 0, 0, 0, 214, 0, 0, 0, 110, 0, 0, 0, 5748, 5748, 0, 0, 97, 0, 0, 97, 0",
      /* 5791 */ "5748, 0, 133120, 0, 0, 0, 0, 0, 0, 136, 0, 25088, 252, 0, 243, 252, 294, 243, 252, 294, 320, 294, 0",
      /* 5814 */ "4610, 0, 0, 87, 0, 91, 0, 0, 243, 1255, 0, 0, 0, 259, 0, 27648, 27648, 27648, 27648, 27648, 27648",
      /* 5835 */ "27648, 0, 0, 0, 0, 147, 147, 103, 15872, 0, 162, 0, 0, 162, 165, 0, 0, 0, 27648, 0, 0, 0, 0, 5748, 0",
      /* 5860 */ "0, 24229, 0, 0, 171, 0, 0, 213, 0, 215, 0, 0, 0, 178, 0, 0, 0, 0, 0, 0, 159, 0, 0, 269, 208, 209, 0",
      /* 5887 */ "0, 252, 0, 243, 1255, 0, 0, 0, 208, 0, 4610, 0, 0, 0, 1112, 91, 0, 0, 243, 1255, 0, 0, 258, 208, 0",
      /* 5912 */ "1112, 5748, 5748, 0, 1112, 0, 1112, 0, 0, 0, 1112, 0, 171, 0, 0, 0, 1112, 1112, 1112, 1112, 1112",
      /* 5933 */ "1112, 1112, 1112, 1112, 1112, 1112, 1112, 0, 0, 0, 1112, 101, 103, 0, 0, 0, 0, 6144, 0, 4279, 0, 0",
      /* 5955 */ "3744, 1112, 0, 0, 4260, 0, 0, 0, 1112, 101, 103, 105, 0, 68096, 0, 1112, 0, 0, 0, 0, 208, 209, 0",
      /* 5978 */ "30208, 273, 0, 268, 0, 208, 209, 0, 0, 252, 0, 243, 1290, 0, 1112, 0, 0, 4260, 0, 8704, 0, 1112, 0",
      /* 6001 */ "0, 9216, 1112, 0, 171, 171, 171, 171, 0, 0, 0, 0, 0, 217, 0, 68096, 0, 1112, 220, 0, 0, 0, 208, 209",
      /* 6025 */ "272, 0, 252, 0, 243, 252, 294, 243, 252, 294, 294, 321, 1112, 187, 0, 4260, 187, 0, 0, 1112, 0, 0, 0",
      /* 6048 */ "0, 208, 209, 0, 250, 0, 0, 6873, 0, 1112, 0, 0, 234, 208, 209, 0, 0, 252, 264, 243, 1255, 0, 0, 0",
      /* 6072 */ "278, 279, 31232, 1112, 0, 0, 4260, 0, 0, 189, 1112, 0, 0, 0, 0, 0, 0, 0, 208, 209, 246, 0, 208, 209",
      /* 6096 */ "0, 0, 0, 0, 0, 229, 0, 68096, 0, 1112, 0, 0, 222, 0, 208, 243, 1255, 0, 277, 0, 208, 209, 0, 0, 252",
      /* 6121 */ "0, 254, 1268, 0, 208, 209, 252, 0, 1112, 204, 0, 0, 1112, 0, 171, 171, 171, 173, 0, 0, 0, 247, 208",
      /* 6144 */ "209, 0, 0, 0, 0, 228, 0, 0, 0, 1112, 0, 206, 0, 1112, 0, 171, 171, 193, 171, 0, 0, 68096, 0, 1112, 0",
      /* 6169 */ "221, 0, 0, 208, 224, 0, 0, 0, 0, 0, 0, 0, 171, 1112, 0, 0, 0, 188, 0, 0, 1112, 0, 0, 0, 208, 209, 0",
      /* 6196 */ "0, 251, 0, 1112, 0, 0, 0, 0, 0, 166, 0, 0, 243, 1255, 0, 257, 0, 208, 1112, 0, 0, 0, 0, 0, 167, 0, 0",
      /* 6223 */ "243, 1255, 256, 0, 0, 208, 209, 30720, 0, 0, 0, 124, 0, 0, 124, 0, 127, 0, 127, 28672, 28672, 28672",
      /* 6245 */ "28672, 28672, 0, 127, 28799, 28799, 127, 28799, 0, 0, 0, 0, 147, 147, 149, 150, 0, 0, 28672, 28799",
      /* 6265 */ "28799, 127, 127, 28799, 127, 28799, 28799, 127, 28799, 96, 0, 0, 96, 0, 0, 0, 0, 0, 0, 0, 5749, 0",
      /* 6287 */ "91, 91, 0, 0, 94, 0, 8192, 0, 4610, 0, 0, 0, 0, 9728, 0, 0, 243, 1279, 0, 0, 0, 208, 224, 0, 0, 252",
      /* 6313 */ "0, 243, 243, 0, 208, 224, 252, 0, 0, 29184, 0, 0, 0, 0, 0, 0, 0, 7680, 0, 9728, 9728, 0, 0, 94, 0, 0",
      /* 6339 */ "0, 36864, 0, 0, 0, 0, 1113, 1113, 0, 5748, 201, 0, 0, 0, 0, 0, 0, 171, 0, 0, 0, 201, 0, 0, 0, 0, 0",
      /* 6366 */ "208, 0, 171, 0, 0, 5748, 29812, 0, 0, 3704, 0, 0, 248, 249, 0, 0, 0, 0, 157, 0, 0, 3744, 3704, 3704",
      /* 6390 */ "3704, 3704, 0, 0, 0, 0, 147, 28307, 103, 103, 0, 238, 0, 0, 0, 0, 1255, 0, 0, 254, 1268, 0, 0, 0",
      /* 6414 */ "208, 209, 0, 0, 252, 0, 243, 296, 294, 243, 252, 294, 304, 252, 294, 243, 311, 294, 243, 252, 294",
      /* 6435 */ "243, 305, 294, 243, 1112, 191, 171, 171, 171, 171, 0, 0, 92, 0, 0, 0, 0, 0, 0, 14848, 0, 0, 14848, 0",
      /* 6459 */ "147, 0, 0, 198, 0, 4279, 0, 0, 270, 271, 0, 0, 252, 0, 243, 252, 294, 243, 299, 202, 1112, 0, 0, 0",
      /* 6483 */ "1112, 0, 171, 193, 171, 171, 0, 0, 0, 243, 1255, 0, 252, 291, 243, 243, 24576, 0, 25600, 208, 209, 0",
      /* 6505 */ "0, 0, 227, 0, 0, 0, 136, 0, 0, 136, 0, 0, 0, 0, 0, 0, 12288, 0, 308, 294, 243, 252, 294, 243, 252",
      /* 6530 */ "294, 307, 316, 252, 294, 243, 252, 294, 294, 294, 300, 0, 0, 0, 0, 0, 96, 0, 0, 0, 0, 0, 254, 1268",
      /* 6554 */ "0, 252, 0, 254, 254, 0, 252, 0, 254, 252, 294, 254, 252, 294, 294, 294, 0, 0, 6144, 0, 4279, 184, 0",
      /* 6577 */ "3744, 1210, 0, 0, 4260, 0, 0, 0, 1112, 170, 171, 0, 0, 260, 0, 0, 252, 0, 243, 1255, 0, 208, 209",
      /* 6600 */ "252, 280, 0, 243, 1255, 0, 208, 209, 252, 0, 243, 1255, 284, 208, 209, 252, 0, 243, 1307, 0, 208",
      /* 6621 */ "209, 252, 0, 282, 1255, 0, 208, 209, 252, 281, 243, 1255, 0, 10752, 10752, 252, 294, 310, 252, 294",
      /* 6641 */ "313, 252, 294, 254, 252, 294, 254, 252, 294, 254, 98, 0, 0, 1112, 101, 103, 106, 98, 118, 1112, 5748",
      /* 6662 */ "5748, 118, 1112, 0, 1112, 0, 205, 0, 1112, 0, 171, 171, 171, 171, 194, 0, 151, 0, 153, 0, 0, 0, 111",
      /* 6685 */ "112, 112, 112, 112, 0, 0, 0, 0, 0, 73216, 0, 0, 1192, 0, 0, 0, 0, 0, 0, 0, 208, 223, 32856, 0, 171",
      /* 6710 */ "171, 171, 171, 0, 32768, 6873, 0, 1255, 0, 0, 0, 235, 236, 263, 0, 243, 1255, 0, 208, 209, 263, 0",
      /* 6732 */ "243, 1255, 0, 263, 0, 243, 243, 289, 252, 0, 243, 243, 0, 252, 0, 243, 243, 0, 223, 209, 252, 0, 263",
      /* 6755 */ "0, 243, 263, 297, 243, 263, 297, 243, 263, 297, 297, 297, 297, 243, 302, 297, 243, 263, 297, 243",
      /* 6775 */ "252, 294, 243, 11264, 294, 294, 294, 12070, 0, 0, 0, 0, 0, 0, 3072, 112, 297, 323, 297, 0, 0, 0, 0",
      /* 6798 */ "0, 0, 24229, 0, 209, 0, 226, 0, 0, 0, 0, 0, 0, 36352, 36352, 6873, 230, 1112, 0, 0, 0, 208, 209, 0",
      /* 6822 */ "0, 263, 0, 265, 1255, 0, 0, 293, 0, 295, 252, 294, 243, 252, 312, 243, 252, 294, 0, 253, 243, 1255",
      /* 6844 */ "0, 0, 0, 208, 275, 1255, 0, 0, 0, 208, 209, 0, 0, 0, 0, 0, 0, 0, 0, 97, 1112, 91, 91, 141, 0, 94, 0",
      /* 6871 */ "0, 100, 1112, 101, 103, 0, 107, 0, 147, 196, 0, 0, 0, 0, 0, 94, 94, 94, 243, 1300, 0, 0, 0, 208, 209",
      /* 6896 */ "0, 0, 0, 31744, 243, 317, 294, 243, 252, 294, 294, 294, 301, 252, 294, 243, 252, 294, 243, 314, 294",
      /* 6917 */ "176, 0, 147, 147, 103, 103, 0, 0, 145, 0, 147, 147, 103, 103, 0, 147, 0, 197, 0, 0, 0, 0, 148, 148",
      /* 6941 */ "103, 103, 202, 1112, 0, 0, 0, 1112, 210, 171, 0, 212, 0, 0, 0, 0, 0, 0, 13824, 0, 68096, 0, 1243, 0",
      /* 6965 */ "0, 0, 0, 208, 209, 225, 0, 0, 0, 0, 0, 0, 208, 224, 322, 294, 294, 0, 0, 0, 0, 0, 95, 95, 95, 0",
      /* 6991 */ "1112, 5748, 5748, 0, 1112, 0, 1145, 181, 0, 6144, 0, 0, 0, 0, 3744, 1112, 0, 0, 0, 0, 0, 0, 1214",
      /* 7014 */ "286, 243, 1255, 0, 252, 0, 243, 243, 252, 294, 10240, 252, 294, 294, 294, 0, 144, 0, 0, 147, 147",
      /* 7035 */ "103, 103, 179, 0, 32256, 0, 0, 0, 0, 158, 0, 3744, 0, 177, 147, 147, 103, 103, 0, 0, 147, 0, 103",
      /* 7058 */ "103, 0, 0, 0, 16896, 0, 18432, 0, 0, 0, 24064, 0, 0, 111, 112, 0, 287, 1255, 0, 252, 0, 243, 243",
      /* 7081 */ "252, 318, 243, 252, 294, 294, 294, 300, 243, 252, 294, 243, 273, 294, 243, 252, 303, 243, 252, 294",
      /* 7101 */ "243, 252, 319, 294, 294, 1185, 0, 0, 0, 0, 0, 0, 0, 209, 208, 237, 0, 0, 0, 0, 0, 1255, 0, 0, 1112",
      /* 7126 */ "232, 0, 0, 208, 209, 0, 0, 252, 274, 209, 0, 262, 252, 0, 243, 1255, 0, 208, 209, 285, 0, 243, 1255",
      /* 7149 */ "0, 290, 0, 243, 292, 252, 309, 243, 252, 294, 243, 252, 294, 294, 294, 0, 4610, 0, 0, 0, 1113, 91, 0",
      /* 7172 */ "0, 5748, 5748, 0, 0, 27648, 0, 0, 0, 0, 0, 0, 0, 5748, 0, 99, 0, 1113, 101, 103, 0, 0, 147, 147",
      /* 7196 */ "138240, 0, 0, 0, 97, 0, 0, 0, 0, 0, 0, 0, 1112, 99, 1113, 5748, 5748, 99, 1113, 0, 1113, 1113, 1113",
      /* 7219 */ "1113, 1113, 1113, 1113, 1113, 1113, 1113, 1113, 1113, 0, 0, 0, 1112, 0, 233, 0, 208, 209, 0, 252",
      /* 7239 */ "294, 243, 252, 294, 243, 252, 294, 243, 1112, 1112, 1112, 1153, 1153, 1153, 1153, 1153, 1112, 1112",
      /* 7257 */ "1112, 1112, 1112, 1112, 1112, 0, 33792, 0, 0, 0, 0, 0, 3744, 0, 34304, 6144, 0, 0, 0, 0, 3744, 195",
      /* 7279 */ "147, 0, 0, 0, 0, 0, 0, 223, 209, 1164, 91, 91, 0, 0, 94, 0, 0, 154, 0, 0, 0, 0, 3744, 202, 1227, 0",
      /* 7305 */ "0, 0, 1112, 0, 171, 211, 0, 0, 0, 0, 0, 218, 209, 261, 0, 252, 0, 243, 1255, 0, 252, 0, 243, 243, 0",
      /* 7330 */ "152, 0, 0, 0, 0, 111, 112, 1112, 0, 0, 164, 0, 0, 0, 0, 1112, 1112, 113, 5748, 209, 0, 0, 33280, 0",
      /* 7354 */ "0, 0, 0, 1112, 1112, 114, 5748, 34816, 0, 0, 130, 130, 130, 130, 130, 0, 0, 0, 0, 34816, 0, 0, 0, 0",
      /* 7378 */ "0, 97, 0, 97, 97, 97, 97, 130, 0, 0, 34816, 34816, 0, 34816, 0, 0, 34816, 0, 0, 36352, 0, 0, 0, 0, 0",
      /* 7403 */ "5748, 5748, 0, 0, 110, 0, 0, 0, 0, 0, 0, 133, 134, 36352, 36352, 36352, 0, 36352, 36352, 36352, 0",
      /* 7424 */ "36352, 36352, 36352, 36352, 36352, 36352, 36352, 36352, 36352, 36352, 36352, 36352, 0, 0, 0, 0, 1112",
      /* 7441 */ "1112, 115, 5748, 16896, 18432, 0, 0, 0, 171, 0, 0, 171, 171, 171, 171, 0, 0, 0, 0, 0, 0, 0, 110",
      /* 7464 */ "12288, 0, 0, 0, 12288, 0, 0, 0, 146, 147, 147, 103, 103, 0, 12288, 12288, 0, 12288, 12288, 0, 12288",
      /* 7485 */ "12288, 0, 0, 0, 0, 0, 0, 0, 223, 224, 12288, 12288, 12288, 12288, 0, 0, 0, 0, 3704, 0, 3704, 3704"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 7507; ++i) {TRANSITION[i] = Integer.parseInt(s2[i]);}
  }

  private static final int[] EXPECTED = new int[640];
  static
  {
    final String s1[] =
    {
      /*   0 */ "31, 39, 47, 204, 55, 63, 71, 86, 78, 94, 102, 110, 118, 126, 134, 141, 149, 164, 156, 172, 181, 189",
      /*  22 */ "197, 230, 212, 220, 226, 173, 173, 173, 238, 241, 347, 252, 261, 458, 279, 283, 293, 297, 301, 305",
      /*  42 */ "309, 313, 317, 318, 322, 325, 329, 333, 337, 341, 345, 351, 264, 376, 577, 453, 575, 356, 534, 422",
      /*  62 */ "384, 404, 571, 456, 285, 354, 382, 389, 573, 576, 248, 388, 288, 393, 247, 402, 288, 273, 431, 289",
      /*  82 */ "429, 419, 430, 275, 577, 408, 288, 577, 429, 287, 414, 430, 274, 426, 427, 428, 426, 427, 428, 435",
      /* 102 */ "436, 636, 563, 440, 592, 378, 446, 450, 462, 466, 470, 474, 478, 482, 486, 490, 494, 498, 502, 506",
      /* 122 */ "510, 514, 523, 583, 591, 562, 527, 531, 540, 577, 577, 544, 270, 536, 548, 552, 593, 577, 557, 577",
      /* 142 */ "267, 578, 441, 561, 557, 577, 568, 584, 556, 577, 616, 598, 555, 590, 616, 609, 604, 617, 610, 256",
      /* 162 */ "577, 257, 582, 588, 616, 582, 588, 615, 597, 603, 256, 577, 577, 577, 577, 577, 577, 577, 577, 577",
      /* 182 */ "577, 577, 577, 517, 577, 397, 599, 577, 614, 577, 577, 577, 628, 621, 622, 626, 365, 371, 368, 632",
      /* 202 */ "372, 634, 577, 358, 415, 519, 605, 362, 410, 244, 577, 442, 577, 577, 577, 394, 577, 564, 577, 577",
      /* 222 */ "577, 577, 577, 395, 577, 577, 397, 577, 577, 577, 396, 577, 577, 398, 255, 577, 577, 577, 578, 4, 256",
      /* 243 */ "1073741824, 0, 0, 196608, 0, 0, 2097152, 4096, 4194304, 1073741828, 4, 4, 4, 0, 0, 0, 65536, 0",
      /* 261 */ "16777728, 49152, 8404992, 16384, 16384, 16384, 32768, 0, 0, 229376, 0, 6144, 0, 0, 2097152, 4194304",
      /* 277 */ "524288, 524288, 12, 1073750020, 4, 2113536, 8404992, 268451840, 16384, 16384, 0, 0, 0, 1048576",
      /* 291 */ "1048576, 0, 16418, 16386, 4, 7296, 12599296, 16418, 7296, 7296, 12599296, 16434, 16448, 540722, 49216",
      /* 306 */ "8405042, 8405042, 167788706, 8405042, 8405042, 436224162, 8601650, 402866210, 8405106, 75710514",
      /* 316 */ "403718178, -2012200926, 135282722, 135282722, 135282722, 8437874, 277037106, 176373938, 243482802",
      /* 325 */ "411254834, 142819378, 511918258, 411254834, 511918258, 511918258, 411254834, 511918258, -1743765470",
      /* 334 */ "1048789170, 1048789170, 948125746, 948125810, 1048789170, 948125810, 1048789234, 1048822002",
      /* 342 */ "1048789234, 948125810, 1048822002, 4, 1073741824, 0, 2, 262148, 16777220, 2, 262148, 262144, 0, 8, 0",
      /* 357 */ "0, 8, 8, 8192, 1073750016, 6272, 4194304, 16, 64, 8, 72, 72, 64, 112, 64, 72, 64, 64, 64, 1048578, 0",
      /* 378 */ "-2147483648, 0, -2147483648, 4194304, 2097152, 4096, 4224, 4194304, 16, 64, 16, 16, 524290, 0, 0",
      /* 393 */ "1048576, 0, 0, 0, 1, 0, 0, 0, 2, 4096, 4194304, 524290, 0, 128, 128, 2097152, 4194304, 524290, 0",
      /* 412 */ "16384, 128, 1048576, 0, 0, 2097152, 0, 0, 1048576, 1048576, 2097152, 0, 2048, 4224, 2097152, 4194304",
      /* 428 */ "524288, 2097152, 4194304, 524288, 524290, 0, 0, 2097152, 4194304, 4194304, 4194304, 0, 262144",
      /* 441 */ "134217728, 0, 0, 0, 48, 0, 603979776, 268435456, 16384, 0, 2097664, 1056768, -2147483648, 0, 0, 2, 0",
      /* 458 */ "16384, 16384, 16384, 16386, -2147483136, 1073741824, 0, -2147483648, -2147483616, 16777216, 33554432",
      /* 469 */ "-2147483616, -2147467264, 3154432, -2147467264, 3154432, -2147450848, -2147450720, -2139095008",
      /* 477 */ "-2146394080, -2147254144, -2139095008, -2147450720, -2147467200, -2144296416, -2147450720",
      /* 484 */ "-2139088896, -2139088896, -2139088895, -2139088892, -2139088888, -2139088864, -2139087872",
      /* 491 */ "-2122311680, -2144296416, -2147254080, -2139062112, -2139062112, -2147237696, -2147237760",
      /* 498 */ "-2147237696, -2139062112, -2147237696, -2139062096, -2139061600, -2146713408, -2139061584",
      /* 505 */ "-2122310611, -2136964942, -2136964446, -2144616254, -2145140030, -2136964430, -2144615742",
      /* 512 */ "-2135907678, -2135907678, -2135907662, -2143559454, -2135907662, 0, 128, 0, 0, 32, 32, 0, 4194304, 0",
      /* 526 */ "-2147483648, 4194304, 0, 0, 67108864, 536870912, 0, 16384, 8192, 8192, 0, 0, 1, 4, 1048576, 0, 0",
      /* 543 */ "1073741824, 32768, 128, 0, 40960, 8, 245760, 524288, 1, 12, 253952, -2147483648, 134217728, 268435456",
      /* 557 */ "0, 67108864, 536870912, 0, 0, 268435456, -2147483648, 0, 0, 0, 4, 0, 32768, 196608, 0, 65536, 131072",
      /* 574 */ "1048578, 0, 16384, 0, 0, 0, 0, -2147483648, 131072, 0, 0, 134217728, 0, 0, 268435456, 67108864",
      /* 590 */ "536870912, 0, 0, 0, 512, 268435456, -2147483648, 65536, 131072, 0, 0, 0, 64, 268435456, 67108864, 0",
      /* 606 */ "0, 0, 2048, 131072, 0, 0, 268435456, 0, 6, 0, 0, 0, 32768, 65536, 131072, 64, 112, 112, 112, 112, 112",
      /* 627 */ "0, 64, 64, 72, 0, 64, 72, 72, 64, 0, 0, 256, 4194304"
    };
    String[] s2 = java.util.Arrays.toString(s1).replaceAll("[ \\[\\]]", "").split(",");
    for (int i = 0; i < 640; ++i) {EXPECTED[i] = Integer.parseInt(s2[i]);}
  }

  private static final String[] TOKEN =
  {
    "(0)",
    "Name",
    "Space",
    "DirPIContents",
    "Number",
    "StringLiteral",
    "CaretName",
    "CharCode",
    "TokenIdentifier",
    "Blank",
    "Char",
    "CharRange",
    "CharCodeRange",
    "PragmaContents",
    "S",
    "'\\\\'",
    "'[VC'",
    "'[WFC'",
    "NonRBrackedContents",
    "BeginOfGrammar",
    "Sem",
    "Semantic",
    "EndOfGrammar",
    "EOF",
    "'\"'",
    "'$'",
    "'&'",
    "'('",
    "')'",
    "'*'",
    "'*/'",
    "'*>'",
    "'*}'",
    "'+'",
    "'+>'",
    "'+}'",
    "'-'",
    "'.'",
    "'/'",
    "'/*'",
    "':'",
    "'::='",
    "';'",
    "'<*'",
    "'<+'",
    "'<<'",
    "'<?'",
    "'<?ENCORE?>'",
    "'<?TERMINALS?>'",
    "'<?TOKENS?>'",
    "'='",
    "'=>'",
    "'>>'",
    "'?'",
    "'?>'",
    "'['",
    "']'",
    "'^'",
    "'definition'",
    "'disallow'",
    "'end_of_rule'",
    "'explicit'",
    "'gn'",
    "'rule'",
    "'syntax'",
    "'ws'",
    "'xgs'",
    "'{'",
    "'{*'",
    "'{+'",
    "'|'",
    "'}'"
  };
}

// End
