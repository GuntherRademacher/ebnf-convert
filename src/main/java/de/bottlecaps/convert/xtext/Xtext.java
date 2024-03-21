// This file was generated on Thu Mar 21, 2024 21:14 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net>
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
      int i0 = (i >> 5) * 145 + s - 1;
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
    /*   0 */ 57, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5,
    /*  35 */ 6, 6, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 19, 20, 21, 22,
    /*  63 */ 23, 24, 25, 25, 25, 25, 26, 27, 28, 28, 28, 28, 28, 28, 28, 28, 29, 28, 28, 28, 28, 28, 28, 28, 28, 28,
    /*  89 */ 28, 28, 30, 31, 32, 33, 28, 6, 34, 35, 36, 37, 38, 39, 40, 41, 42, 28, 28, 43, 44, 45, 46, 47, 28, 48, 49,
    /* 116 */ 50, 51, 52, 53, 28, 28, 28, 54, 55, 56, 6, 6
  };

  private static final int[] MAP1 =
  {
    /*   0 */ 54, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  26 */ 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58,
    /*  52 */ 58, 58, 90, 122, 154, 186, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216,
    /*  74 */ 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 57, 0, 0, 0, 0, 0, 0, 0,
    /*  98 */ 0, 1, 2, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 5, 6, 6, 6, 7, 8, 9, 10, 11,
    /* 133 */ 12, 13, 14, 15, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 19, 20, 21, 22, 23, 24, 25, 25, 25, 25,
    /* 159 */ 26, 27, 28, 28, 28, 28, 28, 28, 28, 28, 29, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 30, 31, 32, 33,
    /* 185 */ 28, 6, 34, 35, 36, 37, 38, 39, 40, 41, 42, 28, 28, 43, 44, 45, 46, 47, 28, 48, 49, 50, 51, 52, 53, 28, 28,
    /* 212 */ 28, 54, 55, 56, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
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
    /*    0 */ 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 928, 928,
    /*   18 */ 928, 942, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 928, 928, 928, 942,
    /*   36 */ 1071, 1069, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 928, 928, 928, 942, 2780, 990,
    /*   54 */ 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1212, 1219, 965, 2915, 990, 2117, 2117,
    /*   72 */ 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 988, 1902, 1006, 1021, 3108, 990, 2117, 2117, 2117, 2117,
    /*   90 */ 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1560, 2915, 990, 2117, 2117, 2117, 2117, 2117,
    /*  107 */ 2117, 2117, 2117, 2117, 2117, 2117, 949, 1265, 1044, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
    /*  125 */ 2117, 2117, 2117, 1067, 1943, 1087, 1102, 3108, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
    /*  142 */ 2117, 2117, 1125, 1303, 1144, 1159, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
    /*  159 */ 2117, 1367, 1861, 2012, 1182, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
    /*  176 */ 2117, 1530, 972, 1205, 1590, 1691, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1189,
    /*  193 */ 2776, 1028, 1235, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1567, 1051,
    /*  210 */ 1568, 1258, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1281,
    /*  227 */ 1296, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1128, 2117, 1319, 1360,
    /*  244 */ 1242, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1383, 1383, 1383, 1398, 2915,
    /*  261 */ 2439, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1421, 1807, 1974,
    /*  278 */ 1450, 1466, 2960, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2247, 2236, 1508, 1523, 1546, 990, 2117,
    /*  295 */ 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1584, 1753, 1606, 2915, 990, 2117, 2117,
    /*  312 */ 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 3088, 1989, 2004, 2915, 990, 2117, 2117, 2117,
    /*  329 */ 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1638, 1629, 1654, 1670, 2915, 990, 2117, 2117, 2117, 2117,
    /*  346 */ 2117, 2117, 2117, 2117, 2117, 2117, 1613, 1109, 2117, 1560, 1707, 990, 2117, 2117, 2117, 2117, 2117,
    /*  363 */ 2117, 2117, 2117, 2117, 2117, 2117, 2911, 1166, 1745, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117,
    /*  380 */ 2117, 2117, 2117, 2117, 2117, 2117, 1769, 1560, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
    /*  397 */ 2117, 2117, 2117, 2107, 1797, 1434, 2294, 1807, 1974, 1450, 1466, 2960, 2116, 2117, 2117, 2117, 2117,
    /*  414 */ 2117, 2117, 2107, 1797, 2698, 1823, 1807, 1974, 1450, 1466, 2960, 2116, 2117, 2117, 2117, 2117, 2117,
    /*  431 */ 2117, 2107, 1797, 1434, 2294, 1807, 2073, 1450, 1466, 2960, 2116, 2117, 2117, 2117, 2117, 2117, 2117,
    /*  448 */ 2107, 1797, 1434, 2294, 1807, 1974, 3017, 2976, 2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107,
    /*  465 */ 1797, 1434, 2294, 2148, 1974, 3017, 2976, 2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2118,
    /*  482 */ 2117, 1560, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
    /*  499 */ 1721, 3108, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1685, 1689, 1689, 1852,
    /*  516 */ 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 1892, 1877, 1918, 1933, 2915, 990,
    /*  534 */ 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1836, 2530, 1807, 1959, 1450,
    /*  551 */ 2028, 2134, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 1492, 1974, 1450, 1466,
    /*  568 */ 2960, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2098, 1797, 1434, 2294, 1807, 1974, 1450, 1466, 2960,
    /*  585 */ 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 1807, 2191, 2263, 1466, 2960, 2116,
    /*  602 */ 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 2279, 2561, 2323, 1974, 2353, 2369, 2411, 2437, 2117,
    /*  619 */ 2117, 2117, 2117, 2117, 2117, 2107, 2455, 2499, 2470, 1492, 1974, 1450, 1466, 2960, 2116, 2117, 2117,
    /*  636 */ 2117, 2117, 2117, 2117, 2629, 1797, 2574, 2294, 1807, 1974, 1479, 2976, 2970, 2116, 2117, 2117, 2117,
    /*  653 */ 2117, 2117, 2117, 1344, 2515, 2546, 2294, 1807, 1974, 3070, 2976, 2970, 2116, 2117, 2117, 2117, 2117,
    /*  670 */ 2117, 2117, 2107, 1797, 2483, 2685, 2603, 1974, 3017, 1334, 2970, 2116, 2117, 2117, 2117, 2117, 2117,
    /*  687 */ 2117, 2107, 1797, 1434, 2294, 1807, 2221, 3017, 2976, 2421, 2116, 2117, 2117, 2117, 2117, 2117, 2117,
    /*  704 */ 2107, 1797, 1434, 2294, 2175, 1974, 2590, 2619, 2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107,
    /*  721 */ 1797, 1434, 2294, 2654, 2058, 3017, 2337, 2670, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797,
    /*  738 */ 1434, 2294, 1807, 1974, 1781, 2976, 2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434,
    /*  755 */ 2294, 1807, 2043, 3017, 2976, 2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2638, 2714, 1434, 2729,
    /*  772 */ 2745, 2761, 2796, 2088, 2854, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 2809,
    /*  789 */ 1974, 2382, 2976, 2864, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 2825, 2880, 2840, 1492, 2896,
    /*  806 */ 3017, 2976, 2931, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2946, 2395, 2206, 2162,
    /*  823 */ 2976, 2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 1434, 2294, 1492, 1974, 3017, 2976,
    /*  840 */ 2970, 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2107, 1797, 2307, 2294, 1807, 1974, 3017, 2976, 2970,
    /*  857 */ 2116, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2992, 3007, 2915, 990, 2117, 2117, 2117, 2117,
    /*  874 */ 2117, 2117, 2117, 2117, 2117, 2117, 1405, 3047, 3033, 3060, 2915, 990, 2117, 2117, 2117, 2117, 2117,
    /*  891 */ 2117, 2117, 2117, 2117, 2117, 1729, 2117, 3086, 1560, 2915, 990, 2117, 2117, 2117, 2117, 2117, 2117,
    /*  908 */ 2117, 2117, 2117, 2117, 2117, 2117, 3104, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117, 2117,
    /*  925 */ 2117, 2117, 2117, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 562, 0, 0,
    /*  946 */ 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1792, 1792, 0, 0, 1536, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0,
    /*  977 */ 0, 0, 0, 0, 0, 0, 0, 2560, 2560, 2560, 2560, 0, 54, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 0, 0,
    /* 1007 */ 0, 54, 0, 54, 54, 0, 0, 54, 54, 54, 0, 54, 54, 54, 54, 0, 0, 0, 0, 1024, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 1037 */ 0, 0, 0, 2816, 2816, 2816, 2874, 1792, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3328,
    /* 1064 */ 3328, 0, 0, 0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 512, 0, 0, 0, 0, 55, 0, 55, 55, 0, 0,
    /* 1095 */ 55, 55, 55, 0, 55, 55, 55, 55, 0, 0, 0, 0, 54, 1024, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5888, 5888, 0,
    /* 1124 */ 0, 0, 0, 2048, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3840, 0, 0, 0, 0, 2048, 0, 2048, 2048, 0, 0, 2048,
    /* 1153 */ 2048, 2048, 0, 2048, 2048, 2048, 2048, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6144,
    /* 1179 */ 6144, 6144, 6207, 2304, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 58, 0, 0, 0, 0, 2560, 0, 77,
    /* 1208 */ 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1536, 0, 0, 0, 0, 1536, 0, 0, 1536, 0, 1536, 0, 2816, 0,
    /* 1237 */ 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4096, 77, 590, 0, 0, 3328, 0, 0, 0, 0, 54, 55, 0, 0, 0,
    /* 1268 */ 0, 0, 0, 0, 0, 0, 0, 1792, 0, 0, 1792, 0, 1792, 0, 0, 68, 0, 68, 68, 0, 0, 68, 68, 68, 0, 68, 68, 68, 68,
    /* 1297 */ 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2048, 2048, 0, 0, 0, 2048, 0, 3840, 0, 0, 3840, 0, 0,
    /* 1326 */ 0, 0, 3840, 0, 3840, 3840, 0, 3916, 0, 0, 0, 0, 0, 820, 820, 899, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 1353 */ 820, 0, 0, 0, 0, 59, 0, 3840, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2304, 0, 0, 0, 0, 0, 51,
    /* 1384 */ 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 0, 590, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 1413 */ 0, 0, 11264, 0, 0, 0, 0, 0, 0, 0, 0, 820, 0, 54, 55, 0, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820,
    /* 1441 */ 820, 820, 820, 820, 820, 820, 820, 820, 820, 112, 113, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820,
    /* 1463 */ 820, 820, 124, 125, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 135, 136, 0, 0, 0, 0, 0, 0, 820, 820, 887,
    /* 1488 */ 820, 820, 820, 820, 820, 820, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 54, 55, 0, 4419, 0, 0,
    /* 1512 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2560, 0, 0, 0, 0,
    /* 1544 */ 2560, 0, 0, 0, 4608, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 590, 0, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 1575 */ 0, 0, 0, 0, 3328, 0, 0, 0, 0, 4864, 0, 0, 0, 0, 4864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 95, 590, 0, 0,
    /* 1606 */ 4864, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5888, 0, 0, 5888, 0, 0, 0, 0, 0, 0, 5376, 0, 5376,
    /* 1635 */ 0, 0, 0, 0, 0, 0, 0, 5376, 0, 0, 0, 0, 0, 0, 5376, 0, 0, 0, 0, 0, 0, 69, 0, 0, 69, 0, 0, 69, 0, 69, 0, 0,
    /* 1667 */ 69, 0, 5445, 69, 0, 0, 0, 0, 54, 55, 0, 0, 3072, 0, 0, 0, 0, 6400, 0, 0, 0, 0, 0, 7424, 0, 0, 0, 0, 0, 0,
    /* 1697 */ 0, 0, 0, 0, 0, 0, 0, 0, 95, 0, 0, 0, 0, 3584, 5632, 0, 0, 0, 0, 0, 0, 0, 77, 590, 0, 0, 0, 0, 0, 79, 80,
    /* 1728 */ 0, 0, 0, 0, 0, 0, 0, 0, 0, 11520, 0, 0, 0, 0, 11520, 0, 0, 6144, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0,
    /* 1759 */ 0, 0, 4864, 4864, 4864, 0, 4864, 4864, 4864, 4864, 6656, 0, 0, 6656, 0, 0, 6656, 6656, 0, 0, 0, 6656, 0,
    /* 1782 */ 0, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 890, 820, 820, 0, 0, 0, 820, 0, 0, 0, 820, 820, 820, 0, 820,
    /* 1808 */ 820, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 0, 0, 838, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0,
    /* 1835 */ 820, 820, 0, 820, 820, 820, 820, 820, 841, 820, 820, 820, 820, 820, 820, 820, 820, 7424, 0, 0, 0, 0, 54,
    /* 1858 */ 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2304, 0, 0, 0, 0, 2304, 0, 0, 0, 0, 0, 53, 0, 0, 0, 53, 53, 53, 0, 53, 53,
    /* 1889 */ 0, 0, 0, 53, 0, 0, 0, 0, 0, 0, 0, 0, 53, 0, 0, 0, 0, 0, 0, 54, 0, 0, 0, 54, 0, 0, 0, 0, 54, 53, 0, 53,
    /* 1921 */ 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 55, 0,
    /* 1951 */ 0, 0, 55, 0, 0, 0, 0, 55, 0, 100, 0, 0, 820, 820, 873, 820, 820, 820, 820, 820, 820, 820, 77, 0, 0, 0, 0,
    /* 1978 */ 820, 820, 820, 820, 820, 820, 820, 820, 820, 820, 77, 0, 0, 0, 0, 0, 5120, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 2004 */ 5120, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2304, 2304, 2304, 0, 2304, 2304, 2304, 2304, 125, 0,
    /* 2030 */ 0, 0, 0, 820, 820, 820, 820, 820, 820, 135, 136, 0, 138, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 820,
    /* 2054 */ 877, 820, 820, 77, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 876, 820, 820, 820, 77, 0, 0, 0, 0, 820,
    /* 2078 */ 820, 820, 820, 820, 6964, 820, 820, 820, 820, 77, 0, 0, 0, 0, 129, 820, 820, 820, 900, 901, 820, 0, 0, 0,
    /* 2102 */ 0, 0, 56, 0, 0, 820, 0, 0, 0, 0, 0, 0, 0, 0, 820, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7168,
    /* 2134 */ 0, 820, 820, 910, 820, 820, 54, 55, 0, 0, 0, 820, 912, 820, 820, 820, 0, 0, 0, 858, 820, 820, 820, 820,
    /* 2158 */ 820, 0, 77, 590, 0, 0, 0, 0, 0, 117, 820, 820, 820, 820, 820, 820, 820, 820, 820, 0, 0, 0, 820, 820, 860,
    /* 2183 */ 820, 820, 820, 0, 77, 590, 0, 0, 0, 0, 101, 0, 820, 820, 820, 820, 820, 820, 820, 820, 878, 820, 77, 0,
    /* 2207 */ 0, 0, 0, 820, 872, 820, 874, 820, 820, 820, 820, 820, 820, 77, 0, 0, 0, 0, 871, 820, 820, 820, 820, 820,
    /* 2231 */ 820, 820, 820, 820, 77, 0, 0, 0, 0, 4352, 0, 0, 0, 0, 4352, 0, 0, 0, 0, 4352, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 2260 */ 0, 4352, 4352, 112, 113, 0, 0, 116, 0, 820, 820, 820, 820, 820, 820, 820, 891, 820, 124, 833, 0, 820,
    /* 2282 */ 833, 820, 820, 833, 833, 820, 820, 820, 833, 820, 820, 820, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0,
    /* 2306 */ 820, 820, 0, 820, 820, 820, 820, 820, 820, 820, 820, 820, 843, 820, 820, 820, 820, 820, 857, 0, 0, 0,
    /* 2328 */ 820, 859, 820, 820, 820, 820, 0, 77, 590, 0, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 0, 137, 0,
    /* 2352 */ 9472, 112, 113, 0, 0, 0, 0, 820, 10548, 820, 820, 820, 889, 820, 820, 820, 124, 125, 126, 0, 128, 0,
    /* 2374 */ 8500, 820, 820, 820, 820, 902, 135, 136, 0, 0, 0, 0, 0, 0, 886, 820, 820, 820, 820, 820, 820, 820, 820,
    /* 2397 */ 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 97, 98, 0, 908, 820, 820, 820, 820, 54, 55, 0, 0, 0,
    /* 2422 */ 820, 820, 820, 820, 820, 0, 0, 0, 0, 0, 820, 820, 820, 820, 10292, 9012, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 2449 */ 0, 0, 0, 0, 512, 0, 0, 0, 829, 0, 0, 0, 829, 829, 832, 0, 829, 829, 0, 0, 0, 829, 0, 0, 820, 820, 54, 55,
    /* 2477 */ 0, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 840, 840, 820, 820, 820, 840, 820, 820, 820, 820, 832, 0,
    /* 2501 */ 829, 832, 829, 829, 832, 832, 829, 829, 829, 832, 829, 829, 829, 829, 0, 0, 820, 0, 59, 0, 820, 820, 820,
    /* 2524 */ 59, 820, 820, 0, 0, 59, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 853, 820, 0, 853, 820, 59, 820, 820,
    /* 2550 */ 820, 820, 820, 820, 820, 820, 820, 842, 820, 820, 820, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 84, 820,
    /* 2574 */ 820, 0, 820, 820, 820, 820, 839, 839, 820, 820, 820, 839, 820, 820, 820, 820, 0, 0, 0, 115, 0, 0, 820,
    /* 2597 */ 820, 820, 8244, 888, 820, 820, 820, 820, 0, 0, 0, 820, 820, 820, 820, 861, 862, 0, 77, 590, 0, 0, 0, 0,
    /* 2621 */ 127, 0, 0, 820, 898, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 57, 0, 820, 0, 0, 0, 0, 0, 0, 0, 0, 820, 0, 0,
    /* 2650 */ 0, 0, 0, 60, 856, 820, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 77, 590, 54, 55, 139, 820, 909, 820,
    /* 2674 */ 820, 9524, 0, 0, 0, 0, 0, 911, 820, 820, 820, 820, 0, 0, 820, 820, 54, 55, 0, 0, 0, 83, 0, 820, 820, 0,
    /* 2700 */ 820, 820, 838, 820, 820, 820, 820, 838, 820, 820, 838, 820, 838, 820, 0, 0, 820, 0, 60, 0, 820, 820, 820,
    /* 2723 */ 60, 820, 820, 0, 0, 60, 820, 0, 0, 820, 820, 54, 55, 0, 82, 0, 0, 0, 820, 854, 0, 855, 820, 854, 0, 0, 0,
    /* 2750 */ 820, 820, 820, 820, 820, 820, 0, 77, 590, 54, 55, 99, 0, 0, 0, 820, 820, 820, 820, 875, 820, 820, 820,
    /* 2773 */ 820, 820, 77, 0, 0, 0, 58, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 96, 0, 0, 0, 0, 114, 0, 0, 0, 820,
    /* 2803 */ 820, 820, 820, 820, 820, 820, 820, 820, 0, 0, 0, 820, 820, 820, 7732, 820, 820, 0, 77, 590, 0, 0, 0, 0,
    /* 2827 */ 830, 0, 0, 0, 830, 830, 830, 0, 830, 830, 0, 0, 0, 830, 0, 0, 820, 820, 54, 55, 0, 0, 0, 0, 0, 820, 820,
    /* 2854 */ 0, 820, 820, 820, 820, 820, 0, 0, 0, 9216, 0, 820, 820, 820, 820, 820, 0, 0, 0, 0, 9984, 820, 820, 820,
    /* 2878 */ 820, 820, 834, 0, 830, 834, 830, 830, 834, 834, 830, 830, 830, 834, 830, 830, 830, 830, 0, 0, 0, 102,
    /* 2900 */ 820, 820, 820, 820, 820, 820, 820, 820, 820, 879, 77, 0, 0, 0, 63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    /* 2927 */ 77, 590, 0, 0, 0, 820, 820, 820, 9780, 820, 0, 0, 7936, 0, 0, 820, 820, 913, 8756, 820, 0, 0, 820, 820,
    /* 2951 */ 54, 55, 81, 0, 0, 0, 0, 820, 820, 0, 820, 820, 820, 820, 820, 54, 55, 0, 0, 0, 820, 820, 820, 820, 820,
    /* 2976 */ 0, 0, 0, 0, 0, 820, 820, 820, 820, 820, 820, 0, 0, 0, 0, 0, 0, 0, 11008, 0, 0, 11008, 0, 0, 11008, 0,
    /* 3002 */ 11008, 0, 0, 11008, 0, 11008, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 820, 820, 820, 820, 820,
    /* 3028 */ 820, 820, 820, 820, 0, 0, 11264, 0, 0, 0, 0, 0, 0, 11264, 11264, 11264, 0, 11264, 11264, 11264, 11264, 0,
    /* 3050 */ 0, 0, 11264, 0, 0, 0, 0, 0, 0, 11264, 11264, 0, 0, 0, 0, 54, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 820, 820,
    /* 3078 */ 820, 820, 820, 820, 820, 820, 10804, 0, 0, 11520, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5120, 0, 0,
    /* 3105 */ 0, 0, 1280, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 590, 54, 55
  };

  private static final int[] EXPECTED =
  {
    /*   0 */ 73, 80, 83, 87, 91, 76, 95, 99, 103, 107, 111, 115, 119, 149, 172, 124, 128, 132, 140, 136, 152, 157, 144,
    /*  23 */ 134, 150, 155, 122, 147, 151, 156, 147, 151, 156, 148, 161, 146, 164, 153, 170, 196, 178, 219, 181, 222,
    /*  44 */ 184, 188, 191, 200, 194, 153, 207, 204, 216, 154, 226, 153, 166, 230, 234, 244, 153, 238, 232, 243, 165,
    /*  65 */ 239, 248, 165, 210, 249, 174, 212, 248, 6, 10, 130, 65538, 1310722, 14, 262, 1048578, 268435458,
    /*  82 */ 1073741826, 2, 4194310, 258, 1050626, 4198402, 16386, 65538, 65538, 262146, 268435458, 6, 17827842, 6,
    /*  96 */ 66050, 142, 166, 4198722, 5247042, 590338, 134217870, -2113929210, 268648450, 2105486, -2113929194,
    /* 107 */ 67133614, 2629774, -2113929210, -1577058298, 2367886, 67396014, 2367950, -2113908730, 75786158, 10758094,
    /* 117 */ 75818926, 29241294, 346978286, 2, 2, 4, -2147483644, 4, 4, 16777216, 4, -2147483644, 4, 131072, 8192,
    /* 132 */ 2097152, 67108868, 4, 4, 2, 2, 8, 8, 536870916, 4, 4, 32768, 4, 67108868, 4, 4, 4, 4, 8, 8, 1073741824, 0,
    /* 154 */ 0, 0, 0, 4, 4, 4, -2147483644, 1073741824, 0, 0, 4, 0, 0, 0, 8, 16, 8, 4096, 0, 2048, 0, 0, 8, 64, 64,
    /* 179 */ 2048, 2048, 257, 259, 80, 257, 387, 6160, 1281, 387, 257, 1281, 423, 3329, 2305, 3329, 3329, 0, 0, 4096,
    /* 199 */ 16, 951, 2305, 3329, 2305, 64, 1, 256, 0, 8, 0, 16, 64, 2, 128, 4, 2, 3, 0, 384, 0, 80, 2048, 257, 2048,
    /* 224 */ 2048, 80, 32, 0, 16, 512, 64, 1, 256, 2, 0, 128, 0, 4, 8, 16, 64, 1, 2, 4, 32, 16, 512, 0, 128, 4, 32, 16,
    /* 252 */ 0
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
