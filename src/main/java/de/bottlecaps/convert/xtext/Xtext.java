// This file was generated on Thu Mar 9, 2023 13:03 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net>
// REx command line: -q -tree -a none -java -interface de.bottlecaps.convert.Parser -name de.bottlecaps.convert.xtext.Xtext xtext.ebnf

package de.bottlecaps.convert.xtext;


public class Xtext implements de.bottlecaps.convert.Parser
{
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
    lookahead1W(7);                 // _ | 'grammar'
    consume(35);                    // 'grammar'
    lookahead1W(18);                // _ | ID | 'false' | 'true'
    whitespace();
    parse_GrammarID();
    if (l1 == 41)                   // 'with'
    {
      consume(41);                  // 'with'
      lookahead1W(18);              // _ | ID | 'false' | 'true'
      whitespace();
      parse_GrammarID();
      for (;;)
      {
        if (l1 != 12)               // ','
        {
          break;
        }
        consume(12);                // ','
        lookahead1W(18);            // _ | ID | 'false' | 'true'
        whitespace();
        parse_GrammarID();
      }
    }
    if (l1 == 36)                   // 'hidden'
    {
      consume(36);                  // 'hidden'
      lookahead1W(2);               // _ | '('
      consume(7);                   // '('
      lookahead1W(23);              // _ | ID | ')' | 'false' | 'true'
      if (l1 != 8)                  // ')'
      {
        whitespace();
        parse_RuleID();
        for (;;)
        {
          if (l1 != 12)             // ','
          {
            break;
          }
          consume(12);              // ','
          lookahead1W(18);          // _ | ID | 'false' | 'true'
          whitespace();
          parse_RuleID();
        }
      }
      consume(8);                   // ')'
    }
    for (;;)
    {
      lookahead1W(38);              // _ | ID | '@' | 'enum' | 'false' | 'fragment' | 'generate' | 'import' |
                                    // 'terminal' | 'true'
      if (l1 != 34                  // 'generate'
       && l1 != 37)                 // 'import'
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
      lookahead1W(35);              // _ | ID | EOF | '@' | 'enum' | 'false' | 'fragment' | 'terminal' | 'true'
      if (l1 == 4)                  // EOF
      {
        break;
      }
    }
    consume(4);                     // EOF
    eventHandler.endNonterminal("Grammar", e0);
  }

  private void parse_GrammarID()
  {
    eventHandler.startNonterminal("GrammarID", e0);
    parse_ValidID();
    for (;;)
    {
      lookahead1W(43);              // _ | ID | ',' | '.' | '@' | 'enum' | 'false' | 'fragment' | 'generate' |
                                    // 'hidden' | 'import' | 'terminal' | 'true' | 'with'
      if (l1 != 14)                 // '.'
      {
        break;
      }
      consume(14);                  // '.'
      lookahead1W(18);              // _ | ID | 'false' | 'true'
      whitespace();
      parse_ValidID();
    }
    eventHandler.endNonterminal("GrammarID", e0);
  }

  private void parse_AbstractRule()
  {
    eventHandler.startNonterminal("AbstractRule", e0);
    for (;;)
    {
      lookahead1W(32);              // _ | ID | '@' | 'enum' | 'false' | 'fragment' | 'terminal' | 'true'
      if (l1 != 25)                 // '@'
      {
        break;
      }
      whitespace();
      parse_Annotation();
    }
    switch (l1)
    {
    case 39:                        // 'terminal'
      whitespace();
      parse_TerminalRule();
      break;
    case 31:                        // 'enum'
      whitespace();
      parse_EnumRule();
      break;
    default:
      whitespace();
      parse_ParserRule();
    }
    eventHandler.endNonterminal("AbstractRule", e0);
  }

  private void parse_AbstractMetamodelDeclaration()
  {
    eventHandler.startNonterminal("AbstractMetamodelDeclaration", e0);
    switch (l1)
    {
    case 34:                        // 'generate'
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
    consume(34);                    // 'generate'
    lookahead1W(18);                // _ | ID | 'false' | 'true'
    whitespace();
    parse_ValidID();
    lookahead1W(1);                 // _ | STRING
    consume(3);                     // STRING
    lookahead1W(39);                // _ | ID | '@' | 'as' | 'enum' | 'false' | 'fragment' | 'generate' | 'import' |
                                    // 'terminal' | 'true'
    if (l1 == 29)                   // 'as'
    {
      consume(29);                  // 'as'
      lookahead1W(18);              // _ | ID | 'false' | 'true'
      whitespace();
      parse_ValidID();
    }
    eventHandler.endNonterminal("GeneratedMetamodel", e0);
  }

  private void parse_ReferencedMetamodel()
  {
    eventHandler.startNonterminal("ReferencedMetamodel", e0);
    consume(37);                    // 'import'
    lookahead1W(1);                 // _ | STRING
    consume(3);                     // STRING
    lookahead1W(39);                // _ | ID | '@' | 'as' | 'enum' | 'false' | 'fragment' | 'generate' | 'import' |
                                    // 'terminal' | 'true'
    if (l1 == 29)                   // 'as'
    {
      consume(29);                  // 'as'
      lookahead1W(18);              // _ | ID | 'false' | 'true'
      whitespace();
      parse_ValidID();
    }
    eventHandler.endNonterminal("ReferencedMetamodel", e0);
  }

  private void parse_Annotation()
  {
    eventHandler.startNonterminal("Annotation", e0);
    consume(25);                    // '@'
    lookahead1W(0);                 // _ | ID
    consume(2);                     // ID
    eventHandler.endNonterminal("Annotation", e0);
  }

  private void parse_ParserRule()
  {
    eventHandler.startNonterminal("ParserRule", e0);
    switch (l1)
    {
    case 33:                        // 'fragment'
      consume(33);                  // 'fragment'
      lookahead1W(18);              // _ | ID | 'false' | 'true'
      whitespace();
      parse_RuleNameAndParams();
      lookahead1W(25);              // _ | '*' | ':' | 'hidden' | 'returns'
      if (l1 == 9                   // '*'
       || l1 == 38)                 // 'returns'
      {
        switch (l1)
        {
        case 9:                     // '*'
          consume(9);               // '*'
          break;
        default:
          consume(38);              // 'returns'
          lookahead1W(0);           // _ | ID
          whitespace();
          parse_TypeRef();
        }
      }
      break;
    default:
      parse_RuleNameAndParams();
      lookahead1W(20);              // _ | ':' | 'hidden' | 'returns'
      if (l1 == 38)                 // 'returns'
      {
        consume(38);                // 'returns'
        lookahead1W(0);             // _ | ID
        whitespace();
        parse_TypeRef();
      }
    }
    lookahead1W(14);                // _ | ':' | 'hidden'
    if (l1 == 36)                   // 'hidden'
    {
      consume(36);                  // 'hidden'
      lookahead1W(2);               // _ | '('
      consume(7);                   // '('
      lookahead1W(23);              // _ | ID | ')' | 'false' | 'true'
      if (l1 != 8)                  // ')'
      {
        whitespace();
        parse_RuleID();
        for (;;)
        {
          if (l1 != 12)             // ','
          {
            break;
          }
          consume(12);              // ','
          lookahead1W(18);          // _ | ID | 'false' | 'true'
          whitespace();
          parse_RuleID();
        }
      }
      consume(8);                   // ')'
    }
    lookahead1W(3);                 // _ | ':'
    consume(16);                    // ':'
    lookahead1W(37);                // _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' | 'true' | '{'
    whitespace();
    parse_Alternatives();
    consume(18);                    // ';'
    eventHandler.endNonterminal("ParserRule", e0);
  }

  private void parse_RuleNameAndParams()
  {
    eventHandler.startNonterminal("RuleNameAndParams", e0);
    parse_ValidID();
    lookahead1W(30);                // _ | '*' | ':' | '<' | 'hidden' | 'returns'
    if (l1 == 19)                   // '<'
    {
      consume(19);                  // '<'
      lookahead1W(9);               // _ | ID | '>'
      if (l1 == 2)                  // ID
      {
        whitespace();
        parse_Parameter();
        for (;;)
        {
          lookahead1W(12);          // _ | ',' | '>'
          if (l1 != 12)             // ','
          {
            break;
          }
          consume(12);              // ','
          lookahead1W(0);           // _ | ID
          whitespace();
          parse_Parameter();
        }
      }
      consume(22);                  // '>'
    }
    eventHandler.endNonterminal("RuleNameAndParams", e0);
  }

  private void parse_Parameter()
  {
    eventHandler.startNonterminal("Parameter", e0);
    consume(2);                     // ID
    eventHandler.endNonterminal("Parameter", e0);
  }

  private void parse_TypeRef()
  {
    eventHandler.startNonterminal("TypeRef", e0);
    consume(2);                     // ID
    lookahead1W(33);                // _ | '.' | ':' | '::' | ']' | 'hidden' | '|' | '}'
    if (l1 == 17)                   // '::'
    {
      consume(17);                  // '::'
      lookahead1W(0);               // _ | ID
      consume(2);                   // ID
    }
    eventHandler.endNonterminal("TypeRef", e0);
  }

  private void parse_Alternatives()
  {
    eventHandler.startNonterminal("Alternatives", e0);
    parse_ConditionalBranch();
    for (;;)
    {
      if (l1 != 43)                 // '|'
      {
        break;
      }
      consume(43);                  // '|'
      lookahead1W(37);              // _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' | 'true' | '{'
      whitespace();
      parse_ConditionalBranch();
    }
    eventHandler.endNonterminal("Alternatives", e0);
  }

  private void parse_ConditionalBranch()
  {
    eventHandler.startNonterminal("ConditionalBranch", e0);
    switch (l1)
    {
    case 19:                        // '<'
      consume(19);                  // '<'
      lookahead1W(27);              // _ | ID | '!' | '(' | 'false' | 'true'
      whitespace();
      parse_Disjunction();
      consume(22);                  // '>'
      for (;;)
      {
        lookahead1W(34);            // _ | ID | STRING | '(' | '->' | '=>' | 'false' | 'true' | '{'
        whitespace();
        parse_AbstractToken();
        lookahead1W(40);            // _ | ID | STRING | '(' | ')' | '->' | ';' | '=>' | 'false' | 'true' | '{' | '|'
        if (l1 == 8                 // ')'
         || l1 == 18                // ';'
         || l1 == 43)               // '|'
        {
          break;
        }
      }
      break;
    default:
      parse_UnorderedGroup();
    }
    eventHandler.endNonterminal("ConditionalBranch", e0);
  }

  private void parse_UnorderedGroup()
  {
    eventHandler.startNonterminal("UnorderedGroup", e0);
    parse_Group();
    for (;;)
    {
      if (l1 != 6)                  // '&'
      {
        break;
      }
      consume(6);                   // '&'
      lookahead1W(34);              // _ | ID | STRING | '(' | '->' | '=>' | 'false' | 'true' | '{'
      whitespace();
      parse_Group();
    }
    eventHandler.endNonterminal("UnorderedGroup", e0);
  }

  private void parse_Group()
  {
    eventHandler.startNonterminal("Group", e0);
    for (;;)
    {
      whitespace();
      parse_AbstractToken();
      lookahead1W(42);              // _ | ID | STRING | '&' | '(' | ')' | '->' | ';' | '=>' | 'false' | 'true' | '{' |
                                    // '|'
      if (l1 == 6                   // '&'
       || l1 == 8                   // ')'
       || l1 == 18                  // ';'
       || l1 == 43)                 // '|'
      {
        break;
      }
    }
    eventHandler.endNonterminal("Group", e0);
  }

  private void parse_AbstractToken()
  {
    eventHandler.startNonterminal("AbstractToken", e0);
    switch (l1)
    {
    case 42:                        // '{'
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
    switch (l1)
    {
    case 13:                        // '->'
    case 21:                        // '=>'
      lookahead2W(26);              // _ | ID | STRING | '(' | 'false' | 'true'
      switch (lk)
      {
      case 141:                     // '->' ID
      case 2061:                    // '->' 'false'
      case 2573:                    // '->' 'true'
      case 149:                     // '=>' ID
      case 2069:                    // '=>' 'false'
      case 2581:                    // '=>' 'true'
        lookahead3W(47);            // _ | ID | STRING | '&' | '(' | ')' | '*' | '+' | '+=' | '->' | '::' | ';' | '<' |
                                    // '=' | '=>' | '?' | '?=' | 'false' | 'true' | '{' | '|'
        break;
      }
      break;
    case 2:                         // ID
    case 32:                        // 'false'
    case 40:                        // 'true'
      lookahead2W(47);              // _ | ID | STRING | '&' | '(' | ')' | '*' | '+' | '+=' | '->' | '::' | ';' | '<' |
                                    // '=' | '=>' | '?' | '?=' | 'false' | 'true' | '{' | '|'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 706:                       // ID '+='
    case 736:                       // 'false' '+='
    case 744:                       // 'true' '+='
    case 1282:                      // ID '='
    case 1312:                      // 'false' '='
    case 1320:                      // 'true' '='
    case 1538:                      // ID '?='
    case 1568:                      // 'false' '?='
    case 1576:                      // 'true' '?='
    case 45197:                     // '->' ID '+='
    case 45205:                     // '=>' ID '+='
    case 47117:                     // '->' 'false' '+='
    case 47125:                     // '=>' 'false' '+='
    case 47629:                     // '->' 'true' '+='
    case 47637:                     // '=>' 'true' '+='
    case 82061:                     // '->' ID '='
    case 82069:                     // '=>' ID '='
    case 83981:                     // '->' 'false' '='
    case 83989:                     // '=>' 'false' '='
    case 84493:                     // '->' 'true' '='
    case 84501:                     // '=>' 'true' '='
    case 98445:                     // '->' ID '?='
    case 98453:                     // '=>' ID '?='
    case 100365:                    // '->' 'false' '?='
    case 100373:                    // '=>' 'false' '?='
    case 100877:                    // '->' 'true' '?='
    case 100885:                    // '=>' 'true' '?='
      parse_Assignment();
      break;
    default:
      parse_AbstractTerminal();
    }
    lookahead1W(45);                // _ | ID | STRING | '&' | '(' | ')' | '*' | '+' | '->' | ';' | '=>' | '?' |
                                    // 'false' | 'true' | '{' | '|'
    if (l1 == 9                     // '*'
     || l1 == 10                    // '+'
     || l1 == 23)                   // '?'
    {
      switch (l1)
      {
      case 23:                      // '?'
        consume(23);                // '?'
        break;
      case 9:                       // '*'
        consume(9);                 // '*'
        break;
      default:
        consume(10);                // '+'
      }
    }
    eventHandler.endNonterminal("AbstractTokenWithCardinality", e0);
  }

  private void parse_Action()
  {
    eventHandler.startNonterminal("Action", e0);
    consume(42);                    // '{'
    lookahead1W(0);                 // _ | ID
    whitespace();
    parse_TypeRef();
    lookahead1W(13);                // _ | '.' | '}'
    if (l1 == 14)                   // '.'
    {
      consume(14);                  // '.'
      lookahead1W(18);              // _ | ID | 'false' | 'true'
      whitespace();
      parse_ValidID();
      lookahead1W(11);              // _ | '+=' | '='
      switch (l1)
      {
      case 20:                      // '='
        consume(20);                // '='
        break;
      default:
        consume(11);                // '+='
      }
      lookahead1W(6);               // _ | 'current'
      consume(30);                  // 'current'
    }
    lookahead1W(8);                 // _ | '}'
    consume(44);                    // '}'
    eventHandler.endNonterminal("Action", e0);
  }

  private void parse_AbstractTerminal()
  {
    eventHandler.startNonterminal("AbstractTerminal", e0);
    switch (l1)
    {
    case 13:                        // '->'
    case 21:                        // '=>'
      lookahead2W(26);              // _ | ID | STRING | '(' | 'false' | 'true'
      break;
    default:
      lk = l1;
    }
    switch (lk)
    {
    case 3:                         // STRING
      parse_Keyword();
      break;
    case 2:                         // ID
    case 32:                        // 'false'
    case 40:                        // 'true'
      parse_RuleCall();
      break;
    case 7:                         // '('
      parse_ParenthesizedElement();
      break;
    case 205:                       // '->' STRING
    case 213:                       // '=>' STRING
      parse_PredicatedKeyword();
      break;
    case 461:                       // '->' '('
    case 469:                       // '=>' '('
      parse_PredicatedGroup();
      break;
    default:
      parse_PredicatedRuleCall();
    }
    eventHandler.endNonterminal("AbstractTerminal", e0);
  }

  private void parse_Keyword()
  {
    eventHandler.startNonterminal("Keyword", e0);
    consume(3);                     // STRING
    eventHandler.endNonterminal("Keyword", e0);
  }

  private void parse_RuleCall()
  {
    eventHandler.startNonterminal("RuleCall", e0);
    parse_RuleID();
    if (l1 == 19)                   // '<'
    {
      consume(19);                  // '<'
      lookahead1W(27);              // _ | ID | '!' | '(' | 'false' | 'true'
      whitespace();
      parse_NamedArgument();
      for (;;)
      {
        if (l1 != 12)               // ','
        {
          break;
        }
        consume(12);                // ','
        lookahead1W(27);            // _ | ID | '!' | '(' | 'false' | 'true'
        whitespace();
        parse_NamedArgument();
      }
      consume(22);                  // '>'
    }
    eventHandler.endNonterminal("RuleCall", e0);
  }

  private void parse_NamedArgument()
  {
    eventHandler.startNonterminal("NamedArgument", e0);
    switch (l1)
    {
    case 2:                         // ID
      lookahead2W(29);              // _ | '&' | ',' | '=' | '>' | '|'
      break;
    default:
      lk = l1;
    }
    if (lk == 1282)                 // ID '='
    {
      consume(2);                   // ID
      lookahead1W(4);               // _ | '='
      consume(20);                  // '='
    }
    lookahead1W(27);                // _ | ID | '!' | '(' | 'false' | 'true'
    whitespace();
    parse_Disjunction();
    eventHandler.endNonterminal("NamedArgument", e0);
  }

  private void parse_LiteralCondition()
  {
    eventHandler.startNonterminal("LiteralCondition", e0);
    switch (l1)
    {
    case 40:                        // 'true'
      consume(40);                  // 'true'
      break;
    default:
      consume(32);                  // 'false'
    }
    eventHandler.endNonterminal("LiteralCondition", e0);
  }

  private void parse_Disjunction()
  {
    eventHandler.startNonterminal("Disjunction", e0);
    parse_Conjunction();
    for (;;)
    {
      if (l1 != 43)                 // '|'
      {
        break;
      }
      consume(43);                  // '|'
      lookahead1W(27);              // _ | ID | '!' | '(' | 'false' | 'true'
      whitespace();
      parse_Conjunction();
    }
    eventHandler.endNonterminal("Disjunction", e0);
  }

  private void parse_Conjunction()
  {
    eventHandler.startNonterminal("Conjunction", e0);
    parse_Negation();
    for (;;)
    {
      lookahead1W(28);              // _ | '&' | ')' | ',' | '>' | '|'
      if (l1 != 6)                  // '&'
      {
        break;
      }
      consume(6);                   // '&'
      lookahead1W(27);              // _ | ID | '!' | '(' | 'false' | 'true'
      whitespace();
      parse_Negation();
    }
    eventHandler.endNonterminal("Conjunction", e0);
  }

  private void parse_Negation()
  {
    eventHandler.startNonterminal("Negation", e0);
    for (;;)
    {
      lookahead1W(27);              // _ | ID | '!' | '(' | 'false' | 'true'
      if (l1 != 5)                  // '!'
      {
        break;
      }
      consume(5);                   // '!'
    }
    whitespace();
    parse_Atom();
    eventHandler.endNonterminal("Negation", e0);
  }

  private void parse_Atom()
  {
    eventHandler.startNonterminal("Atom", e0);
    switch (l1)
    {
    case 2:                         // ID
      parse_ParameterReference();
      break;
    case 7:                         // '('
      parse_ParenthesizedCondition();
      break;
    default:
      parse_LiteralCondition();
    }
    eventHandler.endNonterminal("Atom", e0);
  }

  private void parse_ParenthesizedCondition()
  {
    eventHandler.startNonterminal("ParenthesizedCondition", e0);
    consume(7);                     // '('
    lookahead1W(27);                // _ | ID | '!' | '(' | 'false' | 'true'
    whitespace();
    parse_Disjunction();
    consume(8);                     // ')'
    eventHandler.endNonterminal("ParenthesizedCondition", e0);
  }

  private void parse_ParameterReference()
  {
    eventHandler.startNonterminal("ParameterReference", e0);
    consume(2);                     // ID
    eventHandler.endNonterminal("ParameterReference", e0);
  }

  private void parse_TerminalRuleCall()
  {
    eventHandler.startNonterminal("TerminalRuleCall", e0);
    parse_RuleID();
    eventHandler.endNonterminal("TerminalRuleCall", e0);
  }

  private void parse_RuleID()
  {
    eventHandler.startNonterminal("RuleID", e0);
    parse_ValidID();
    for (;;)
    {
      lookahead1W(48);              // _ | ID | STRING | '!' | '&' | '(' | ')' | '*' | '+' | ',' | '->' | '.' | '::' |
                                    // ';' | '<' | '=>' | '?' | 'EOF' | ']' | 'false' | 'true' | '{' | '|'
      if (l1 != 17)                 // '::'
      {
        break;
      }
      consume(17);                  // '::'
      lookahead1W(18);              // _ | ID | 'false' | 'true'
      whitespace();
      parse_ValidID();
    }
    eventHandler.endNonterminal("RuleID", e0);
  }

  private void parse_ValidID()
  {
    eventHandler.startNonterminal("ValidID", e0);
    switch (l1)
    {
    case 2:                         // ID
      consume(2);                   // ID
      break;
    case 40:                        // 'true'
      consume(40);                  // 'true'
      break;
    default:
      consume(32);                  // 'false'
    }
    eventHandler.endNonterminal("ValidID", e0);
  }

  private void parse_PredicatedKeyword()
  {
    eventHandler.startNonterminal("PredicatedKeyword", e0);
    switch (l1)
    {
    case 21:                        // '=>'
      consume(21);                  // '=>'
      break;
    default:
      consume(13);                  // '->'
    }
    lookahead1W(1);                 // _ | STRING
    consume(3);                     // STRING
    eventHandler.endNonterminal("PredicatedKeyword", e0);
  }

  private void parse_PredicatedRuleCall()
  {
    eventHandler.startNonterminal("PredicatedRuleCall", e0);
    switch (l1)
    {
    case 21:                        // '=>'
      consume(21);                  // '=>'
      break;
    default:
      consume(13);                  // '->'
    }
    lookahead1W(18);                // _ | ID | 'false' | 'true'
    whitespace();
    parse_RuleID();
    if (l1 == 19)                   // '<'
    {
      consume(19);                  // '<'
      lookahead1W(27);              // _ | ID | '!' | '(' | 'false' | 'true'
      whitespace();
      parse_NamedArgument();
      for (;;)
      {
        if (l1 != 12)               // ','
        {
          break;
        }
        consume(12);                // ','
        lookahead1W(27);            // _ | ID | '!' | '(' | 'false' | 'true'
        whitespace();
        parse_NamedArgument();
      }
      consume(22);                  // '>'
    }
    eventHandler.endNonterminal("PredicatedRuleCall", e0);
  }

  private void parse_Assignment()
  {
    eventHandler.startNonterminal("Assignment", e0);
    if (l1 == 13                    // '->'
     || l1 == 21)                   // '=>'
    {
      switch (l1)
      {
      case 21:                      // '=>'
        consume(21);                // '=>'
        break;
      default:
        consume(13);                // '->'
      }
    }
    lookahead1W(18);                // _ | ID | 'false' | 'true'
    whitespace();
    parse_ValidID();
    lookahead1W(19);                // _ | '+=' | '=' | '?='
    switch (l1)
    {
    case 11:                        // '+='
      consume(11);                  // '+='
      break;
    case 20:                        // '='
      consume(20);                  // '='
      break;
    default:
      consume(24);                  // '?='
    }
    lookahead1W(31);                // _ | ID | STRING | '(' | '[' | 'false' | 'true'
    whitespace();
    parse_AssignableTerminal();
    eventHandler.endNonterminal("Assignment", e0);
  }

  private void parse_AssignableTerminal()
  {
    eventHandler.startNonterminal("AssignableTerminal", e0);
    switch (l1)
    {
    case 3:                         // STRING
      parse_Keyword();
      break;
    case 7:                         // '('
      parse_ParenthesizedAssignableElement();
      break;
    case 27:                        // '['
      parse_CrossReference();
      break;
    default:
      parse_RuleCall();
    }
    eventHandler.endNonterminal("AssignableTerminal", e0);
  }

  private void parse_ParenthesizedAssignableElement()
  {
    eventHandler.startNonterminal("ParenthesizedAssignableElement", e0);
    consume(7);                     // '('
    lookahead1W(31);                // _ | ID | STRING | '(' | '[' | 'false' | 'true'
    whitespace();
    parse_AssignableAlternatives();
    consume(8);                     // ')'
    eventHandler.endNonterminal("ParenthesizedAssignableElement", e0);
  }

  private void parse_AssignableAlternatives()
  {
    eventHandler.startNonterminal("AssignableAlternatives", e0);
    parse_AssignableTerminal();
    for (;;)
    {
      lookahead1W(10);              // _ | ')' | '|'
      if (l1 != 43)                 // '|'
      {
        break;
      }
      consume(43);                  // '|'
      lookahead1W(31);              // _ | ID | STRING | '(' | '[' | 'false' | 'true'
      whitespace();
      parse_AssignableTerminal();
    }
    eventHandler.endNonterminal("AssignableAlternatives", e0);
  }

  private void parse_CrossReference()
  {
    eventHandler.startNonterminal("CrossReference", e0);
    consume(27);                    // '['
    lookahead1W(0);                 // _ | ID
    whitespace();
    parse_TypeRef();
    lookahead1W(17);                // _ | ']' | '|'
    if (l1 == 43)                   // '|'
    {
      consume(43);                  // '|'
      lookahead1W(22);              // _ | ID | STRING | 'false' | 'true'
      whitespace();
      parse_CrossReferenceableTerminal();
    }
    lookahead1W(5);                 // _ | ']'
    consume(28);                    // ']'
    eventHandler.endNonterminal("CrossReference", e0);
  }

  private void parse_CrossReferenceableTerminal()
  {
    eventHandler.startNonterminal("CrossReferenceableTerminal", e0);
    switch (l1)
    {
    case 3:                         // STRING
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
    consume(7);                     // '('
    lookahead1W(37);                // _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' | 'true' | '{'
    whitespace();
    parse_Alternatives();
    consume(8);                     // ')'
    eventHandler.endNonterminal("ParenthesizedElement", e0);
  }

  private void parse_PredicatedGroup()
  {
    eventHandler.startNonterminal("PredicatedGroup", e0);
    switch (l1)
    {
    case 21:                        // '=>'
      consume(21);                  // '=>'
      break;
    default:
      consume(13);                  // '->'
    }
    lookahead1W(2);                 // _ | '('
    consume(7);                     // '('
    lookahead1W(37);                // _ | ID | STRING | '(' | '->' | '<' | '=>' | 'false' | 'true' | '{'
    whitespace();
    parse_Alternatives();
    consume(8);                     // ')'
    eventHandler.endNonterminal("PredicatedGroup", e0);
  }

  private void parse_TerminalRule()
  {
    eventHandler.startNonterminal("TerminalRule", e0);
    consume(39);                    // 'terminal'
    lookahead1W(24);                // _ | ID | 'false' | 'fragment' | 'true'
    switch (l1)
    {
    case 33:                        // 'fragment'
      consume(33);                  // 'fragment'
      lookahead1W(18);              // _ | ID | 'false' | 'true'
      whitespace();
      parse_ValidID();
      break;
    default:
      whitespace();
      parse_ValidID();
      lookahead1W(15);              // _ | ':' | 'returns'
      if (l1 == 38)                 // 'returns'
      {
        consume(38);                // 'returns'
        lookahead1W(0);             // _ | ID
        whitespace();
        parse_TypeRef();
      }
    }
    lookahead1W(3);                 // _ | ':'
    consume(16);                    // ':'
    lookahead1W(36);                // _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' | 'false' | 'true'
    whitespace();
    parse_TerminalAlternatives();
    consume(18);                    // ';'
    eventHandler.endNonterminal("TerminalRule", e0);
  }

  private void parse_TerminalAlternatives()
  {
    eventHandler.startNonterminal("TerminalAlternatives", e0);
    parse_TerminalGroup();
    for (;;)
    {
      if (l1 != 43)                 // '|'
      {
        break;
      }
      consume(43);                  // '|'
      lookahead1W(36);              // _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' | 'false' | 'true'
      whitespace();
      parse_TerminalGroup();
    }
    eventHandler.endNonterminal("TerminalAlternatives", e0);
  }

  private void parse_TerminalGroup()
  {
    eventHandler.startNonterminal("TerminalGroup", e0);
    for (;;)
    {
      whitespace();
      parse_TerminalToken();
      lookahead1W(41);              // _ | ID | STRING | '!' | '(' | ')' | '->' | '.' | ';' | 'EOF' | 'false' | 'true' |
                                    // '|'
      if (l1 == 8                   // ')'
       || l1 == 18                  // ';'
       || l1 == 43)                 // '|'
      {
        break;
      }
    }
    eventHandler.endNonterminal("TerminalGroup", e0);
  }

  private void parse_TerminalToken()
  {
    eventHandler.startNonterminal("TerminalToken", e0);
    parse_TerminalTokenElement();
    lookahead1W(44);                // _ | ID | STRING | '!' | '(' | ')' | '*' | '+' | '->' | '.' | ';' | '?' | 'EOF' |
                                    // 'false' | 'true' | '|'
    if (l1 == 9                     // '*'
     || l1 == 10                    // '+'
     || l1 == 23)                   // '?'
    {
      switch (l1)
      {
      case 23:                      // '?'
        consume(23);                // '?'
        break;
      case 9:                       // '*'
        consume(9);                 // '*'
        break;
      default:
        consume(10);                // '+'
      }
    }
    eventHandler.endNonterminal("TerminalToken", e0);
  }

  private void parse_TerminalTokenElement()
  {
    eventHandler.startNonterminal("TerminalTokenElement", e0);
    switch (l1)
    {
    case 3:                         // STRING
      parse_CharacterRange();
      break;
    case 7:                         // '('
      parse_ParenthesizedTerminalElement();
      break;
    case 5:                         // '!'
    case 13:                        // '->'
      parse_AbstractNegatedToken();
      break;
    case 14:                        // '.'
      consume(14);                  // '.'
      break;
    case 26:                        // 'EOF'
      consume(26);                  // 'EOF'
      break;
    default:
      parse_TerminalRuleCall();
    }
    eventHandler.endNonterminal("TerminalTokenElement", e0);
  }

  private void parse_ParenthesizedTerminalElement()
  {
    eventHandler.startNonterminal("ParenthesizedTerminalElement", e0);
    consume(7);                     // '('
    lookahead1W(36);                // _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' | 'false' | 'true'
    whitespace();
    parse_TerminalAlternatives();
    consume(8);                     // ')'
    eventHandler.endNonterminal("ParenthesizedTerminalElement", e0);
  }

  private void parse_AbstractNegatedToken()
  {
    eventHandler.startNonterminal("AbstractNegatedToken", e0);
    switch (l1)
    {
    case 5:                         // '!'
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
    consume(5);                     // '!'
    lookahead1W(36);                // _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' | 'false' | 'true'
    whitespace();
    parse_TerminalTokenElement();
    eventHandler.endNonterminal("NegatedToken", e0);
  }

  private void parse_UntilToken()
  {
    eventHandler.startNonterminal("UntilToken", e0);
    consume(13);                    // '->'
    lookahead1W(36);                // _ | ID | STRING | '!' | '(' | '->' | '.' | 'EOF' | 'false' | 'true'
    whitespace();
    parse_TerminalTokenElement();
    eventHandler.endNonterminal("UntilToken", e0);
  }

  private void parse_CharacterRange()
  {
    eventHandler.startNonterminal("CharacterRange", e0);
    parse_Keyword();
    lookahead1W(46);                // _ | ID | STRING | '!' | '(' | ')' | '*' | '+' | '->' | '.' | '..' | ';' | '?' |
                                    // 'EOF' | 'false' | 'true' | '|'
    if (l1 == 15)                   // '..'
    {
      consume(15);                  // '..'
      lookahead1W(1);               // _ | STRING
      whitespace();
      parse_Keyword();
    }
    eventHandler.endNonterminal("CharacterRange", e0);
  }

  private void parse_EnumRule()
  {
    eventHandler.startNonterminal("EnumRule", e0);
    consume(31);                    // 'enum'
    lookahead1W(18);                // _ | ID | 'false' | 'true'
    whitespace();
    parse_ValidID();
    lookahead1W(15);                // _ | ':' | 'returns'
    if (l1 == 38)                   // 'returns'
    {
      consume(38);                  // 'returns'
      lookahead1W(0);               // _ | ID
      whitespace();
      parse_TypeRef();
    }
    lookahead1W(3);                 // _ | ':'
    consume(16);                    // ':'
    lookahead1W(18);                // _ | ID | 'false' | 'true'
    whitespace();
    parse_EnumLiterals();
    consume(18);                    // ';'
    eventHandler.endNonterminal("EnumRule", e0);
  }

  private void parse_EnumLiterals()
  {
    eventHandler.startNonterminal("EnumLiterals", e0);
    parse_EnumLiteralDeclaration();
    for (;;)
    {
      lookahead1W(16);              // _ | ';' | '|'
      if (l1 != 43)                 // '|'
      {
        break;
      }
      consume(43);                  // '|'
      lookahead1W(18);              // _ | ID | 'false' | 'true'
      whitespace();
      parse_EnumLiteralDeclaration();
    }
    eventHandler.endNonterminal("EnumLiterals", e0);
  }

  private void parse_EnumLiteralDeclaration()
  {
    eventHandler.startNonterminal("EnumLiteralDeclaration", e0);
    parse_ValidID();
    lookahead1W(21);                // _ | ';' | '=' | '|'
    if (l1 == 20)                   // '='
    {
      consume(20);                  // '='
      lookahead1W(1);               // _ | STRING
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
      if (code != 1)                // _
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
    begin = end;
    int current = end;
    int result = INITIAL[tokenSetId];
    int state = 0;

    for (int code = result & 255; code != 0; )
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
      int i0 = (charclass << 8) + code - 1;
      code = TRANSITION[(i0 & 15) + TRANSITION[i0 >> 4]];

      if (code > 255)
      {
        result = code;
        code &= 255;
        end = current;
      }
    }

    result >>= 8;
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
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 255;
    for (int i = 0; i < 45; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 137 + s - 1;
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
    /*   0 */ 55, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5,
    /*  35 */ 6, 6, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 19, 20, 21, 22,
    /*  63 */ 23, 24, 25, 25, 25, 25, 26, 27, 25, 25, 25, 25, 25, 25, 25, 25, 28, 25, 25, 25, 25, 25, 25, 25, 25, 25,
    /*  89 */ 25, 25, 29, 30, 31, 32, 25, 6, 33, 34, 35, 36, 37, 38, 39, 40, 41, 25, 25, 42, 43, 44, 45, 46, 25, 47, 48,
    /* 116 */ 49, 50, 25, 51, 25, 25, 25, 52, 53, 54, 6, 6
  };

  private static final int[] MAP1 =
  {
    /*   0 */ 54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  26 */ 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  52 */ 58, 58, 90, 122, 154, 186, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216,
    /*  74 */ 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 55, 0, 0, 0, 0, 0, 0, 0,
    /*  98 */ 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 6, 6, 6, 7, 8, 9, 10, 11,
    /* 133 */ 12, 13, 14, 15, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 19, 20, 21, 22, 23, 24, 25, 25, 25, 25,
    /* 159 */ 26, 27, 25, 25, 25, 25, 25, 25, 25, 25, 28, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 29, 30, 31, 32,
    /* 185 */ 25, 6, 33, 34, 35, 36, 37, 38, 39, 40, 41, 25, 25, 42, 43, 44, 45, 46, 25, 47, 48, 49, 50, 25, 51, 25, 25,
    /* 212 */ 25, 52, 53, 54, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
    /* 246 */ 6, 6
  };

  private static final int[] MAP2 =
  {
    /* 0 */ 57344, 65536, 65533, 1114111, 6, 6
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
    /* 29 */ 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49
  };

  private static final int[] TRANSITION =
  {
    /*    0 */ 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 896, 896, 896, 910, 1506,
    /*   21 */ 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 896, 896, 896, 910, 2893, 2891, 958, 958, 958,
    /*   41 */ 958, 958, 958, 958, 958, 958, 958, 896, 896, 896, 910, 2997, 1038, 958, 958, 958, 958, 958, 958, 958,
    /*   61 */ 958, 958, 958, 958, 1226, 1233, 933, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 956,
    /*   81 */ 1862, 975, 990, 917, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 2706, 1506,
    /*  101 */ 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 997, 1325, 1013, 1506, 1038, 958, 958, 958,
    /*  121 */ 958, 958, 958, 958, 958, 958, 958, 1036, 2511, 1054, 1069, 917, 1038, 958, 958, 958, 958, 958, 958, 958,
    /*  141 */ 958, 958, 958, 1093, 1363, 1112, 1127, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958,
    /*  160 */ 1454, 2213, 2748, 1150, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1552, 2713,
    /*  179 */ 1173, 940, 2977, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1203, 2042, 1020, 1196, 1506, 1038,
    /*  198 */ 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1076, 1134, 1077, 1219, 1506, 1038, 958, 958, 958, 958,
    /*  218 */ 958, 958, 958, 958, 958, 958, 958, 958, 1249, 1264, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958,
    /*  238 */ 958, 958, 2430, 958, 1287, 1318, 1271, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1341,
    /*  257 */ 1341, 1341, 1356, 1506, 1096, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1379,
    /*  276 */ 1768, 2371, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1943, 1932, 1432, 1447, 1470, 1038, 958,
    /*  295 */ 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1500, 1821, 1522, 1506, 1038, 958, 958, 958, 958, 958,
    /*  315 */ 958, 958, 958, 958, 958, 958, 2046, 2843, 1545, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958,
    /*  335 */ 958, 1577, 1568, 1593, 1609, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1714, 1157,
    /*  354 */ 958, 2706, 1666, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 2426, 1180, 1707, 1506,
    /*  373 */ 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 1730, 2706, 1506, 1038, 958, 958, 958,
    /*  393 */ 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 1768, 2371, 2800, 2793, 1408, 958, 958, 958,
    /*  412 */ 958, 958, 958, 958, 1416, 1758, 2317, 1784, 1768, 2371, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958,
    /*  431 */ 958, 1416, 1758, 1392, 1974, 1768, 1484, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758,
    /*  450 */ 1392, 1974, 2629, 2371, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 958, 959, 958, 2706, 1506,
    /*  469 */ 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 2740, 917, 1038, 958, 958, 958,
    /*  489 */ 958, 958, 958, 958, 958, 958, 958, 2885, 2889, 2889, 1813, 1506, 1038, 958, 958, 958, 958, 958, 958, 958,
    /*  509 */ 958, 958, 958, 1852, 1837, 1878, 1893, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958,
    /*  528 */ 1416, 1758, 1797, 2114, 1768, 1917, 2800, 1959, 2003, 958, 958, 958, 958, 958, 958, 958, 1416, 1758,
    /*  546 */ 1392, 1974, 1691, 2371, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 2074, 1758, 1392, 1974,
    /*  564 */ 1768, 2371, 2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 1768, 2027,
    /*  582 */ 2062, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 2099, 2175, 2130, 2371, 1302, 2160,
    /*  600 */ 2204, 958, 958, 958, 958, 958, 958, 958, 1416, 2229, 2273, 2244, 1691, 2371, 2800, 2793, 1408, 958, 958,
    /*  619 */ 958, 958, 958, 958, 958, 2083, 1758, 2188, 1974, 1768, 2371, 1680, 2793, 1408, 958, 958, 958, 958, 958,
    /*  638 */ 958, 958, 2395, 2289, 2333, 1974, 1768, 2371, 2453, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416,
    /*  657 */ 1758, 2257, 2304, 2959, 2371, 2800, 2364, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392,
    /*  675 */ 1974, 1768, 1624, 2800, 2793, 2387, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 1650,
    /*  693 */ 2371, 2411, 2446, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 2469, 1742, 2800,
    /*  711 */ 2485, 2501, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 1768, 2371, 2948, 2793, 1408, 958,
    /*  730 */ 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 1974, 1768, 2679, 2800, 2793, 1408, 958, 958, 958, 958,
    /*  749 */ 958, 958, 958, 2011, 2527, 1392, 2348, 2572, 2588, 2645, 2672, 2695, 958, 958, 958, 958, 958, 958, 958,
    /*  768 */ 1416, 1758, 1392, 1974, 2656, 2371, 1639, 2793, 2729, 958, 958, 958, 958, 958, 958, 958, 1416, 2764,
    /*  786 */ 2816, 2779, 1691, 2603, 2800, 2556, 2832, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1392, 2542,
    /*  804 */ 1768, 2144, 2618, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 1416, 1758, 1987, 1974, 1768, 2371,
    /*  822 */ 2800, 2793, 1408, 958, 958, 958, 958, 958, 958, 958, 958, 958, 2859, 2874, 1506, 1038, 958, 958, 958,
    /*  841 */ 958, 958, 958, 958, 958, 958, 958, 1529, 2923, 2909, 2936, 1506, 1038, 958, 958, 958, 958, 958, 958, 958,
    /*  861 */ 958, 958, 958, 1901, 958, 2975, 2706, 1506, 1038, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958,
    /*  881 */ 958, 2993, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 958, 562, 562, 562, 562, 562, 562,
    /*  902 */ 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /*  929 */ 77, 590, 54, 55, 1536, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 95, 590, 0, 0, 0, 54, 0,
    /*  959 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7168, 0, 0, 54, 0, 54, 54, 0, 0, 54, 54, 54, 0, 54, 54, 54,
    /*  990 */ 54, 0, 0, 0, 0, 1024, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1792, 1792, 0, 0, 1792, 0, 0, 0, 0, 54, 55,
    /* 1020 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2816, 2816, 2816, 2874, 0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 1051 */ 0, 77, 0, 0, 0, 55, 0, 55, 55, 0, 0, 55, 55, 55, 0, 55, 55, 55, 55, 0, 0, 0, 0, 54, 1024, 0, 0, 0, 0, 0,
    /* 1081 */ 0, 0, 0, 0, 0, 0, 0, 3328, 0, 0, 0, 0, 0, 0, 2048, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 512, 0, 0,
    /* 1113 */ 0, 2048, 0, 2048, 2048, 0, 0, 2048, 2048, 2048, 0, 2048, 2048, 2048, 2048, 0, 0, 0, 0, 54, 55, 0, 0, 0,
    /* 1137 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 3328, 3328, 0, 0, 2304, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 1168 */ 0, 5888, 5888, 0, 0, 2560, 0, 77, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6144, 6144, 6144,
    /* 1195 */ 6207, 2816, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 58, 0, 0, 0, 0, 3328, 0, 0, 0, 0, 54,
    /* 1225 */ 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1536, 0, 0, 0, 0, 1536, 0, 0, 1536, 0, 1536, 0, 0, 0, 68, 0, 68, 68,
    /* 1255 */ 0, 0, 68, 68, 68, 0, 68, 68, 68, 68, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4096, 77, 590,
    /* 1285 */ 0, 0, 0, 3840, 0, 0, 3840, 0, 0, 0, 0, 3840, 0, 3840, 3840, 0, 3916, 0, 0, 0, 0, 820, 10548, 820, 820,
    /* 1310 */ 820, 885, 820, 820, 820, 120, 0, 122, 3840, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1792, 0, 0,
    /* 1338 */ 1792, 0, 1792, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 0, 590, 0, 0, 54, 55, 0,
    /* 1364 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 2048, 0, 0, 0, 2048, 0, 0, 0, 820, 0, 54, 55, 0, 0, 0, 0, 0, 820, 820,
    /* 1393 */ 0, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 0, 0, 0, 820, 820, 820, 820,
    /* 1415 */ 820, 820, 0, 0, 0, 0, 0, 0, 0, 0, 820, 0, 0, 0, 0, 0, 0, 0, 4419, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 1447 */ 67, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2304, 0, 0, 0, 0, 0, 0, 0, 4608, 0, 0, 0, 0, 0, 0,
    /* 1479 */ 0, 0, 0, 77, 590, 0, 0, 0, 0, 820, 820, 820, 820, 820, 6964, 820, 820, 820, 820, 77, 0, 4864, 0, 0, 0, 0,
    /* 1505 */ 4864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 590, 0, 0, 4864, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0,
    /* 1536 */ 0, 0, 0, 11264, 0, 0, 0, 0, 0, 5120, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2560, 0, 0, 0, 0,
    /* 1566 */ 2560, 0, 0, 0, 0, 5376, 0, 5376, 0, 0, 0, 0, 0, 0, 0, 5376, 0, 0, 0, 0, 0, 0, 5376, 0, 0, 0, 0, 0, 0, 69,
    /* 1596 */ 0, 0, 69, 0, 0, 69, 0, 69, 0, 0, 69, 0, 5445, 69, 0, 0, 0, 0, 54, 55, 0, 0, 3072, 0, 0, 0, 0, 6400, 0, 0,
    /* 1626 */ 0, 0, 869, 820, 820, 820, 820, 820, 820, 820, 820, 820, 77, 0, 0, 0, 0, 882, 820, 820, 820, 820, 820,
    /* 1649 */ 820, 820, 820, 0, 0, 0, 820, 820, 860, 820, 820, 820, 0, 77, 590, 0, 0, 0, 0, 0, 3584, 5632, 0, 0, 0, 0,
    /* 1675 */ 0, 0, 0, 77, 590, 0, 0, 0, 0, 820, 820, 883, 820, 820, 820, 820, 820, 820, 0, 0, 0, 820, 820, 820, 820,
    /* 1700 */ 820, 820, 0, 77, 590, 54, 55, 6144, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5888, 0, 0, 5888, 0,
    /* 1728 */ 0, 0, 6656, 0, 0, 6656, 0, 0, 6656, 6656, 0, 0, 0, 6656, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 874,
    /* 1753 */ 820, 820, 820, 77, 0, 0, 0, 820, 0, 0, 0, 820, 820, 820, 0, 820, 820, 0, 0, 0, 820, 820, 820, 820, 820,
    /* 1778 */ 820, 0, 77, 590, 0, 0, 838, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820,
    /* 1804 */ 841, 820, 820, 820, 820, 820, 820, 820, 820, 7424, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4864,
    /* 1830 */ 4864, 4864, 0, 4864, 4864, 4864, 4864, 0, 0, 53, 0, 0, 0, 53, 53, 53, 0, 53, 53, 0, 0, 0, 53, 0, 0, 0, 0,
    /* 1857 */ 0, 0, 0, 0, 53, 0, 0, 0, 0, 0, 0, 54, 0, 0, 0, 54, 0, 0, 0, 0, 54, 53, 0, 53, 53, 53, 53, 53, 53, 53, 53,
    /* 1888 */ 53, 53, 53, 53, 53, 53, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11520, 0, 0, 0, 0, 11520, 0, 0, 0,
    /* 1918 */ 98, 0, 0, 820, 820, 871, 820, 820, 820, 820, 820, 820, 820, 77, 0, 0, 0, 0, 4352, 0, 0, 0, 0, 4352, 0, 0,
    /* 1944 */ 0, 0, 4352, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4352, 4352, 0, 820, 820, 820, 820, 820, 820, 0, 130, 0, 0, 820,
    /* 1971 */ 820, 902, 820, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820, 820,
    /* 1995 */ 820, 820, 820, 843, 820, 820, 820, 820, 0, 0, 0, 820, 904, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 2020 */ 820, 0, 0, 0, 0, 0, 60, 0, 0, 99, 0, 820, 820, 820, 820, 820, 820, 820, 820, 876, 820, 77, 0, 0, 0, 58,
    /* 2046 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5120, 0, 0, 0, 112, 0, 820, 820, 820, 820, 820, 820, 820, 887,
    /* 2074 */ 820, 0, 0, 0, 0, 0, 56, 0, 0, 820, 0, 0, 0, 0, 0, 0, 57, 0, 820, 0, 0, 0, 0, 0, 0, 833, 0, 820, 833, 820,
    /* 2104 */ 820, 833, 833, 820, 820, 820, 833, 820, 820, 820, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 853, 820,
    /* 2128 */ 0, 853, 820, 857, 0, 0, 0, 820, 859, 820, 820, 820, 820, 0, 77, 590, 0, 0, 0, 0, 820, 870, 820, 872, 820,
    /* 2153 */ 820, 820, 820, 820, 820, 77, 0, 0, 8500, 820, 820, 820, 820, 896, 0, 0, 0, 0, 900, 820, 820, 820, 820, 0,
    /* 2177 */ 0, 820, 820, 54, 55, 0, 0, 0, 0, 84, 820, 820, 0, 820, 820, 820, 820, 839, 839, 820, 820, 820, 839, 820,
    /* 2201 */ 820, 820, 820, 0, 0, 0, 820, 820, 820, 820, 820, 9012, 0, 0, 0, 0, 0, 0, 0, 2304, 0, 0, 0, 0, 2304, 0, 0,
    /* 2228 */ 0, 0, 0, 829, 0, 0, 0, 829, 829, 832, 0, 829, 829, 0, 0, 0, 829, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0,
    /* 2256 */ 820, 820, 0, 820, 820, 820, 820, 840, 840, 820, 820, 820, 840, 820, 820, 820, 820, 832, 0, 829, 832, 829,
    /* 2278 */ 829, 832, 832, 829, 829, 829, 832, 829, 829, 829, 829, 0, 0, 820, 0, 59, 0, 820, 820, 820, 59, 820, 820,
    /* 2301 */ 0, 0, 59, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 83, 0, 820, 820, 0, 820, 820, 838, 820, 820, 820, 820,
    /* 2326 */ 838, 820, 820, 838, 820, 838, 820, 820, 59, 820, 820, 820, 820, 820, 820, 820, 820, 820, 842, 820, 820,
    /* 2347 */ 820, 820, 0, 0, 820, 820, 54, 55, 0, 82, 0, 0, 0, 820, 854, 0, 855, 0, 820, 820, 893, 820, 820, 820, 0,
    /* 2372 */ 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 77, 0, 0, 0, 0, 820, 820, 820, 820, 10292,
    /* 2395 */ 820, 0, 0, 0, 0, 0, 0, 0, 0, 820, 0, 0, 0, 0, 59, 0, 0, 111, 0, 0, 820, 820, 820, 8244, 884, 820, 820,
    /* 2422 */ 820, 820, 0, 121, 0, 0, 0, 63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3840, 0, 0, 0, 820, 892, 820, 820,
    /* 2451 */ 820, 820, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 820, 10804, 0, 0, 0, 856, 820, 0, 0, 0, 820,
    /* 2475 */ 820, 820, 820, 820, 820, 0, 77, 590, 54, 55, 0, 820, 820, 820, 820, 820, 820, 129, 0, 9472, 131, 820,
    /* 2497 */ 901, 820, 820, 9524, 0, 0, 0, 903, 820, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 55, 0, 0, 0, 55, 0, 0,
    /* 2524 */ 0, 0, 55, 0, 0, 820, 0, 60, 0, 820, 820, 820, 60, 820, 820, 0, 0, 60, 820, 0, 0, 820, 820, 54, 55, 81, 0,
    /* 2551 */ 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820, 820, 0, 0, 0, 0, 820, 820, 820, 9780, 820, 820, 854, 0, 0,
    /* 2576 */ 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 54, 55, 97, 0, 0, 0, 820, 820, 820, 820, 873, 820, 820, 820,
    /* 2600 */ 820, 820, 77, 0, 0, 0, 100, 820, 820, 820, 820, 820, 820, 820, 820, 820, 877, 77, 0, 0, 0, 113, 820, 820,
    /* 2624 */ 820, 820, 820, 820, 820, 820, 820, 0, 0, 0, 858, 820, 820, 820, 820, 820, 0, 77, 590, 0, 0, 110, 0, 0, 0,
    /* 2649 */ 820, 820, 820, 820, 820, 820, 820, 820, 820, 0, 0, 0, 820, 820, 820, 7732, 820, 820, 0, 77, 590, 0, 0,
    /* 2672 */ 123, 820, 820, 820, 894, 895, 820, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 875, 820, 820, 77, 0,
    /* 2695 */ 0, 9216, 0, 820, 820, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 2724 */ 0, 2560, 2560, 2560, 2560, 0, 0, 9984, 820, 820, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 79, 80, 0, 0,
    /* 2749 */ 0, 0, 0, 0, 0, 0, 0, 2304, 2304, 2304, 0, 2304, 2304, 2304, 2304, 0, 0, 830, 0, 0, 0, 830, 830, 830, 0,
    /* 2774 */ 830, 830, 0, 0, 0, 830, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820, 820,
    /* 2800 */ 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820, 820, 820, 0, 0, 0, 834, 0, 830, 834, 830, 830, 834, 834,
    /* 2824 */ 830, 830, 830, 834, 830, 830, 830, 830, 7936, 0, 0, 820, 820, 905, 8756, 820, 820, 0, 0, 0, 0, 0, 0, 0,
    /* 2848 */ 5120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5120, 0, 0, 11008, 0, 0, 11008, 0, 0, 11008, 0, 11008, 0, 0, 11008, 0,
    /* 2874 */ 11008, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7424, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77,
    /* 2906 */ 512, 0, 0, 0, 11264, 0, 0, 0, 0, 0, 0, 11264, 11264, 11264, 0, 11264, 11264, 11264, 11264, 0, 0, 0,
    /* 2928 */ 11264, 0, 0, 0, 0, 0, 0, 11264, 11264, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 820, 820, 820, 820,
    /* 2956 */ 820, 820, 886, 820, 820, 0, 0, 0, 820, 820, 820, 820, 861, 862, 0, 77, 590, 0, 0, 0, 11520, 0, 0, 0, 0,
    /* 2981 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 95, 0, 0, 0, 0, 1280, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 96, 0, 0
  };

  private static final int[] EXPECTED =
  {
    /*   0 */ 69, 76, 228, 89, 93, 72, 97, 101, 105, 109, 113, 117, 121, 214, 159, 211, 130, 134, 142, 138, 151, 221,
    /*  22 */ 146, 136, 151, 221, 220, 150, 219, 148, 153, 220, 217, 220, 124, 152, 157, 126, 166, 194, 169, 197, 172,
    /*  43 */ 176, 179, 201, 182, 152, 185, 191, 205, 153, 85, 152, 161, 225, 208, 232, 161, 225, 237, 233, 242, 238,
    /*  64 */ 162, 82, 188, 79, 238, 6, 10, 130, 65538, 1310722, 14, 262, 1048578, 268435458, 1073741826, 2, 128, 4, 2,
    /*  83 */ 128, 4, 32, 0, 16, 512, 4198402, 16386, 65538, 65538, 262146, 268435458, 6, 17827842, 6, 66050, 142, 166,
    /* 101 */ 4198722, 5247042, 590338, 134217870, -2113929210, 268648450, 2105486, -2113929194, 67133614, 2629774,
    /* 111 */ -2113929210, -1577058298, 2367886, 67396014, 2367950, -2113908730, 75786158, 10758094, 75818926, 29241294,
    /* 121 */ 346978286, 2, 2, 4, 0, 0, 0, 4096, 16, -2147483644, 4, 131072, 8192, 2097152, 67108868, 4, 4, 2, 2, 8, 8,
    /* 142 */ 536870916, 4, 4, 32768, 4, 67108868, 4, 4, 4, 1073741824, 0, 0, 0, 0, 4, 8, 4096, 0, 2048, 0, 0, 8, 16,
    /* 165 */ 64, 64, 2048, 2048, 257, 259, 80, 257, 387, 6160, 1281, 387, 257, 1281, 423, 3329, 2305, 3329, 3329, 0, 0,
    /* 186 */ 8, 0, 16, 0, 8, 64, 1, 256, 0, 80, 2048, 257, 2048, 2048, 80, 951, 2305, 3329, 2305, 3, 0, 384, 0, 128, 0,
    /* 211 */ 4, 4, 16777216, 4, 8, 8, 1073741824, 0, 0, 4, 4, 4, 4, -2147483644, 64, 1, 256, 2, 4194310, 258, 1050626,
    /* 232 */ 32, 16, 512, 0, 8, 0, 128, 4, 32, 16, 16, 64, 1, 2
  };

  private static final String[] TOKEN =
  {
    "(0)",
    "_",
    "ID",
    "STRING",
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
    "'<'",
    "'='",
    "'=>'",
    "'>'",
    "'?'",
    "'?='",
    "'@'",
    "'EOF'",
    "'['",
    "']'",
    "'as'",
    "'current'",
    "'enum'",
    "'false'",
    "'fragment'",
    "'generate'",
    "'grammar'",
    "'hidden'",
    "'import'",
    "'returns'",
    "'terminal'",
    "'true'",
    "'with'",
    "'{'",
    "'|'",
    "'}'"
  };
}

// End
