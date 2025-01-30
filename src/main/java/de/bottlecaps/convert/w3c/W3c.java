// This file was generated on Thu Jan 30, 2025 13:23 (UTC+01) by REx v6.1 which is Copyright (c) 1979-2025 by Gunther Rademacher <grd@gmx.net>
// REx command line: -q -tree -a none -java -interface de.bottlecaps.convert.Parser -name de.bottlecaps.convert.w3c.W3c w3c.ebnf

package de.bottlecaps.convert.w3c;


public class W3c implements de.bottlecaps.convert.Parser
{
  public W3c(CharSequence string, EventHandler t)
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
    for (;;)
    {
      lookahead1W(15);              // Whitespace | NCName | DocComment | '<?'
      if (l1 != 31)                 // '<?'
      {
        break;
      }
      whitespace();
      parse_ProcessingInstruction();
    }
    for (;;)
    {
      whitespace();
      parse_Production();
      lookahead1W(18);              // Whitespace | NCName | DocComment | EOF | '<?ENCORE?>' | '<?TOKENS?>'
      if (l1 != 3                   // NCName
       && l1 != 15)                 // DocComment
      {
        break;
      }
    }
    if (l1 == 33)                   // '<?TOKENS?>'
    {
      consume(33);                  // '<?TOKENS?>'
      for (;;)
      {
        lookahead1W(19);            // Whitespace | NCName | StringLiteral | DocComment | EOF | EquivalenceLookAhead |
                                    // '<?ENCORE?>'
        if (l1 == 16                // EOF
         || l1 == 32)               // '<?ENCORE?>'
        {
          break;
        }
        switch (l1)
        {
        case 3:                     // NCName
          lookahead2W(20);          // Whitespace | Context | '::=' | '<<' | '>>' | '?' | '\\'
          break;
        default:
          lk = l1;
        }
        switch (lk)
        {
        case 15:                    // DocComment
        case 1859:                  // NCName '::='
        case 2307:                  // NCName '?'
          whitespace();
          parse_Production();
          break;
        case 2563:                  // NCName '\\'
          whitespace();
          parse_Delimiter();
          break;
        case 17:                    // EquivalenceLookAhead
          whitespace();
          parse_Equivalence();
          break;
        default:
          whitespace();
          parse_Preference();
        }
      }
    }
    if (l1 == 32)                   // '<?ENCORE?>'
    {
      consume(32);                  // '<?ENCORE?>'
      for (;;)
      {
        lookahead1W(12);            // Whitespace | EOF | '<?'
        if (l1 != 31)               // '<?'
        {
          break;
        }
        whitespace();
        parse_ProcessingInstruction();
      }
    }
    consume(16);                    // EOF
    eventHandler.endNonterminal("Grammar", e0);
  }

  private void parse_ProcessingInstruction()
  {
    eventHandler.startNonterminal("ProcessingInstruction", e0);
    consume(31);                    // '<?'
    lookahead1(0);                  // NCName
    consume(3);                     // NCName
    lookahead1(9);                  // S | '?>'
    if (l1 == 14)                   // S
    {
      for (;;)
      {
        consume(14);                // S
        lookahead1(8);              // ProcessingInstructionContents | S
        if (l1 != 14)               // S
        {
          break;
        }
      }
      consume(2);                   // ProcessingInstructionContents
    }
    lookahead1(2);                  // '?>'
    consume(37);                    // '?>'
    eventHandler.endNonterminal("ProcessingInstruction", e0);
  }

  private void parse_Production()
  {
    eventHandler.startNonterminal("Production", e0);
    if (l1 == 15)                   // DocComment
    {
      consume(15);                  // DocComment
    }
    lookahead1W(4);                 // Whitespace | NCName
    consume(3);                     // NCName
    lookahead1W(13);                // Whitespace | '::=' | '?'
    if (l1 == 36)                   // '?'
    {
      consume(36);                  // '?'
    }
    lookahead1W(5);                 // Whitespace | '::='
    consume(29);                    // '::='
    lookahead1W(31);                // Whitespace | NCName | StringLiteral | CharCode | UrlIntroducer | WsExplicit |
                                    // WsDefinition | DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | '.' |
                                    // '/' | '<?' | '<?ENCORE?>' | '<?TOKENS?>' | '[' | '[^' | '|'
    switch (l1)
    {
    case 10:                        // UrlIntroducer
      whitespace();
      parse_Link();
      break;
    default:
      whitespace();
      parse_Alternatives();
    }
    lookahead1W(24);                // Whitespace | NCName | StringLiteral | WsExplicit | WsDefinition | DocComment |
                                    // EOF | EquivalenceLookAhead | '<?ENCORE?>' | '<?TOKENS?>'
    if (l1 == 12                    // WsExplicit
     || l1 == 13)                   // WsDefinition
    {
      whitespace();
      parse_Option();
    }
    eventHandler.endNonterminal("Production", e0);
  }

  private void parse_Alternatives()
  {
    eventHandler.startNonterminal("Alternatives", e0);
    parse_Alternative();
    if (l1 == 28                    // '/'
     || l1 == 42)                   // '|'
    {
      switch (l1)
      {
      case 42:                      // '|'
        for (;;)
        {
          consume(42);              // '|'
          lookahead1W(30);          // Whitespace | NCName | StringLiteral | CharCode | WsExplicit | WsDefinition |
                                    // DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | '.' | '<?' |
                                    // '<?ENCORE?>' | '<?TOKENS?>' | '[' | '[^' | '|'
          whitespace();
          parse_Alternative();
          if (l1 != 42)             // '|'
          {
            break;
          }
        }
        break;
      default:
        for (;;)
        {
          consume(28);              // '/'
          lookahead1W(29);          // Whitespace | NCName | StringLiteral | CharCode | WsExplicit | WsDefinition |
                                    // DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | '.' | '/' | '<?' |
                                    // '<?ENCORE?>' | '<?TOKENS?>' | '[' | '[^'
          whitespace();
          parse_Alternative();
          if (l1 != 28)             // '/'
          {
            break;
          }
        }
      }
    }
    eventHandler.endNonterminal("Alternatives", e0);
  }

  private void parse_Alternative()
  {
    eventHandler.startNonterminal("Alternative", e0);
    parse_CompositeExpression();
    if (l1 == 19)                   // '&'
    {
      consume(19);                  // '&'
      lookahead1W(23);              // Whitespace | NCName | StringLiteral | CharCode | '$' | '(' | '.' | '<?' | '[' |
                                    // '[^'
      whitespace();
      parse_Item();
    }
    eventHandler.endNonterminal("Alternative", e0);
  }

  private void parse_CompositeExpression()
  {
    eventHandler.startNonterminal("CompositeExpression", e0);
    switch (l1)
    {
    case 3:                         // NCName
      lookahead2W(40);              // Whitespace | NCName | Context | StringLiteral | CharCode | WsExplicit |
                                    // WsDefinition | DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' |
                                    // '*' | '**' | '+' | '++' | '-' | '.' | '/' | '::=' | '<<' | '<?' | '<?ENCORE?>' |
                                    // '<?TOKENS?>' | '>>' | '?' | '[' | '[^' | '\\' | '|'
      switch (lk)
      {
      case 259:                     // NCName Context
        lookahead3W(38);            // Whitespace | NCName | StringLiteral | CharCode | WsExplicit | WsDefinition |
                                    // DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' | '*' | '**' |
                                    // '+' | '++' | '-' | '.' | '/' | '<<' | '<?' | '<?ENCORE?>' | '<?TOKENS?>' | '>>' |
                                    // '?' | '[' | '[^' | '|'
        break;
      case 2307:                    // NCName '?'
        lookahead3W(36);            // Whitespace | NCName | StringLiteral | CharCode | WsExplicit | WsDefinition |
                                    // DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' | '*' | '**' |
                                    // '+' | '++' | '-' | '.' | '/' | '::=' | '<?' | '<?ENCORE?>' | '<?TOKENS?>' | '?' |
                                    // '[' | '[^' | '|'
        break;
      }
      break;
    case 5:                         // StringLiteral
      lookahead2W(39);              // Whitespace | NCName | Context | StringLiteral | CharCode | WsExplicit |
                                    // WsDefinition | DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' |
                                    // '*' | '**' | '+' | '++' | '-' | '.' | '/' | '<<' | '<?' | '<?ENCORE?>' |
                                    // '<?TOKENS?>' | '>>' | '?' | '[' | '[^' | '|'
      switch (lk)
      {
      case 261:                     // StringLiteral Context
        lookahead3W(38);            // Whitespace | NCName | StringLiteral | CharCode | WsExplicit | WsDefinition |
                                    // DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' | '*' | '**' |
                                    // '+' | '++' | '-' | '.' | '/' | '<<' | '<?' | '<?ENCORE?>' | '<?TOKENS?>' | '>>' |
                                    // '?' | '[' | '[^' | '|'
        break;
      }
      break;
    default:
      lk = l1;
    }
    if (lk != 12                    // WsExplicit
     && lk != 13                    // WsDefinition
     && lk != 15                    // DocComment
     && lk != 16                    // EOF
     && lk != 17                    // EquivalenceLookAhead
     && lk != 19                    // '&'
     && lk != 21                    // ')'
     && lk != 28                    // '/'
     && lk != 32                    // '<?ENCORE?>'
     && lk != 33                    // '<?TOKENS?>'
     && lk != 42                    // '|'
     && lk != 1859                  // NCName '::='
     && lk != 1923                  // NCName '<<'
     && lk != 1925                  // StringLiteral '<<'
     && lk != 2243                  // NCName '>>'
     && lk != 2245                  // StringLiteral '>>'
     && lk != 2563                  // NCName '\\'
     && lk != 121091                // NCName '?' '::='
     && lk != 123139                // NCName Context '<<'
     && lk != 123141                // StringLiteral Context '<<'
     && lk != 143619                // NCName Context '>>'
     && lk != 143621)               // StringLiteral Context '>>'
    {
      whitespace();
      parse_Item();
      switch (l1)
      {
      case 26:                      // '-'
        consume(26);                // '-'
        lookahead1W(23);            // Whitespace | NCName | StringLiteral | CharCode | '$' | '(' | '.' | '<?' | '[' |
                                    // '[^'
        whitespace();
        parse_Item();
        break;
      case 23:                      // '**'
        consume(23);                // '**'
        lookahead1W(23);            // Whitespace | NCName | StringLiteral | CharCode | '$' | '(' | '.' | '<?' | '[' |
                                    // '[^'
        whitespace();
        parse_Item();
        break;
      case 25:                      // '++'
        consume(25);                // '++'
        lookahead1W(23);            // Whitespace | NCName | StringLiteral | CharCode | '$' | '(' | '.' | '<?' | '[' |
                                    // '[^'
        whitespace();
        parse_Item();
        break;
      default:
        for (;;)
        {
          switch (l1)
          {
          case 3:                   // NCName
            lookahead2W(37);        // Whitespace | NCName | Context | StringLiteral | CharCode | WsExplicit |
                                    // WsDefinition | DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' |
                                    // '*' | '+' | '.' | '/' | '::=' | '<<' | '<?' | '<?ENCORE?>' | '<?TOKENS?>' |
                                    // '>>' | '?' | '[' | '[^' | '\\' | '|'
            switch (lk)
            {
            case 259:               // NCName Context
              lookahead3W(33);      // Whitespace | NCName | StringLiteral | CharCode | WsExplicit | WsDefinition |
                                    // DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' | '*' | '+' |
                                    // '.' | '/' | '<<' | '<?' | '<?ENCORE?>' | '<?TOKENS?>' | '>>' | '?' | '[' | '[^' |
                                    // '|'
              break;
            case 2307:              // NCName '?'
              lookahead3W(32);      // Whitespace | NCName | StringLiteral | CharCode | WsExplicit | WsDefinition |
                                    // DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' | '*' | '+' |
                                    // '.' | '/' | '::=' | '<?' | '<?ENCORE?>' | '<?TOKENS?>' | '?' | '[' | '[^' | '|'
              break;
            }
            break;
          case 5:                   // StringLiteral
            lookahead2W(34);        // Whitespace | NCName | Context | StringLiteral | CharCode | WsExplicit |
                                    // WsDefinition | DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' |
                                    // '*' | '+' | '.' | '/' | '<<' | '<?' | '<?ENCORE?>' | '<?TOKENS?>' | '>>' | '?' |
                                    // '[' | '[^' | '|'
            switch (lk)
            {
            case 261:               // StringLiteral Context
              lookahead3W(33);      // Whitespace | NCName | StringLiteral | CharCode | WsExplicit | WsDefinition |
                                    // DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' | '*' | '+' |
                                    // '.' | '/' | '<<' | '<?' | '<?ENCORE?>' | '<?TOKENS?>' | '>>' | '?' | '[' | '[^' |
                                    // '|'
              break;
            }
            break;
          default:
            lk = l1;
          }
          if (lk == 12              // WsExplicit
           || lk == 13              // WsDefinition
           || lk == 15              // DocComment
           || lk == 16              // EOF
           || lk == 17              // EquivalenceLookAhead
           || lk == 19              // '&'
           || lk == 21              // ')'
           || lk == 28              // '/'
           || lk == 32              // '<?ENCORE?>'
           || lk == 33              // '<?TOKENS?>'
           || lk == 42              // '|'
           || lk == 1859            // NCName '::='
           || lk == 1923            // NCName '<<'
           || lk == 1925            // StringLiteral '<<'
           || lk == 2243            // NCName '>>'
           || lk == 2245            // StringLiteral '>>'
           || lk == 2563            // NCName '\\'
           || lk == 121091          // NCName '?' '::='
           || lk == 123139          // NCName Context '<<'
           || lk == 123141          // StringLiteral Context '<<'
           || lk == 143619          // NCName Context '>>'
           || lk == 143621)         // StringLiteral Context '>>'
          {
            break;
          }
          whitespace();
          parse_Item();
        }
      }
    }
    eventHandler.endNonterminal("CompositeExpression", e0);
  }

  private void parse_Item()
  {
    eventHandler.startNonterminal("Item", e0);
    parse_Primary();
    for (;;)
    {
      lookahead1W(35);              // Whitespace | NCName | StringLiteral | CharCode | WsExplicit | WsDefinition |
                                    // DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' | '*' | '**' |
                                    // '+' | '++' | '-' | '.' | '/' | '<?' | '<?ENCORE?>' | '<?TOKENS?>' | '?' | '[' |
                                    // '[^' | '|'
      if (l1 != 22                  // '*'
       && l1 != 24                  // '+'
       && l1 != 36)                 // '?'
      {
        break;
      }
      switch (l1)
      {
      case 36:                      // '?'
        consume(36);                // '?'
        break;
      case 22:                      // '*'
        consume(22);                // '*'
        break;
      default:
        consume(24);                // '+'
      }
    }
    eventHandler.endNonterminal("Item", e0);
  }

  private void parse_Primary()
  {
    eventHandler.startNonterminal("Primary", e0);
    switch (l1)
    {
    case 3:                         // NCName
    case 5:                         // StringLiteral
      parse_NameOrString();
      break;
    case 31:                        // '<?'
      parse_ProcessingInstruction();
      break;
    case 6:                         // CharCode
      consume(6);                   // CharCode
      break;
    case 18:                        // '$'
      consume(18);                  // '$'
      break;
    case 27:                        // '.'
      consume(27);                  // '.'
      break;
    case 20:                        // '('
      consume(20);                  // '('
      lookahead1W(28);              // Whitespace | NCName | StringLiteral | CharCode | '$' | '(' | ')' | '.' | '/' |
                                    // '<?' | '[' | '[^' | '|'
      whitespace();
      parse_Choice();
      consume(21);                  // ')'
      break;
    default:
      parse_CharClass();
    }
    eventHandler.endNonterminal("Primary", e0);
  }

  private void parse_Choice()
  {
    eventHandler.startNonterminal("Choice", e0);
    parse_CompositeExpression();
    if (l1 != 21)                   // ')'
    {
      switch (l1)
      {
      case 42:                      // '|'
        for (;;)
        {
          consume(42);              // '|'
          lookahead1W(26);          // Whitespace | NCName | StringLiteral | CharCode | '$' | '(' | ')' | '.' | '<?' |
                                    // '[' | '[^' | '|'
          whitespace();
          parse_CompositeExpression();
          if (l1 != 42)             // '|'
          {
            break;
          }
        }
        break;
      default:
        for (;;)
        {
          consume(28);              // '/'
          lookahead1W(25);          // Whitespace | NCName | StringLiteral | CharCode | '$' | '(' | ')' | '.' | '/' |
                                    // '<?' | '[' | '[^'
          whitespace();
          parse_CompositeExpression();
          if (l1 != 28)             // '/'
          {
            break;
          }
        }
      }
    }
    eventHandler.endNonterminal("Choice", e0);
  }

  private void parse_NameOrString()
  {
    eventHandler.startNonterminal("NameOrString", e0);
    switch (l1)
    {
    case 3:                         // NCName
      consume(3);                   // NCName
      lookahead1W(39);              // Whitespace | NCName | Context | StringLiteral | CharCode | WsExplicit |
                                    // WsDefinition | DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' |
                                    // '*' | '**' | '+' | '++' | '-' | '.' | '/' | '<<' | '<?' | '<?ENCORE?>' |
                                    // '<?TOKENS?>' | '>>' | '?' | '[' | '[^' | '|'
      if (l1 == 4)                  // Context
      {
        consume(4);                 // Context
      }
      break;
    default:
      consume(5);                   // StringLiteral
      lookahead1W(39);              // Whitespace | NCName | Context | StringLiteral | CharCode | WsExplicit |
                                    // WsDefinition | DocComment | EOF | EquivalenceLookAhead | '$' | '&' | '(' | ')' |
                                    // '*' | '**' | '+' | '++' | '-' | '.' | '/' | '<<' | '<?' | '<?ENCORE?>' |
                                    // '<?TOKENS?>' | '>>' | '?' | '[' | '[^' | '|'
      if (l1 == 4)                  // Context
      {
        consume(4);                 // Context
      }
    }
    eventHandler.endNonterminal("NameOrString", e0);
  }

  private void parse_CharClass()
  {
    eventHandler.startNonterminal("CharClass", e0);
    switch (l1)
    {
    case 38:                        // '['
      consume(38);                  // '['
      break;
    default:
      consume(39);                  // '[^'
    }
    for (;;)
    {
      lookahead1(16);               // CharCode | Char | CharRange | CharCodeRange
      switch (l1)
      {
      case 7:                       // Char
        consume(7);                 // Char
        break;
      case 6:                       // CharCode
        consume(6);                 // CharCode
        break;
      case 8:                       // CharRange
        consume(8);                 // CharRange
        break;
      default:
        consume(9);                 // CharCodeRange
      }
      lookahead1(17);               // CharCode | Char | CharRange | CharCodeRange | ']'
      if (l1 == 41)                 // ']'
      {
        break;
      }
    }
    consume(41);                    // ']'
    eventHandler.endNonterminal("CharClass", e0);
  }

  private void parse_Link()
  {
    eventHandler.startNonterminal("Link", e0);
    consume(10);                    // UrlIntroducer
    lookahead1(1);                  // URL
    consume(11);                    // URL
    lookahead1(3);                  // ']'
    consume(41);                    // ']'
    eventHandler.endNonterminal("Link", e0);
  }

  private void parse_Option()
  {
    eventHandler.startNonterminal("Option", e0);
    switch (l1)
    {
    case 12:                        // WsExplicit
      consume(12);                  // WsExplicit
      break;
    default:
      consume(13);                  // WsDefinition
    }
    eventHandler.endNonterminal("Option", e0);
  }

  private void parse_Preference()
  {
    eventHandler.startNonterminal("Preference", e0);
    parse_NameOrString();
    lookahead1W(14);                // Whitespace | '<<' | '>>'
    switch (l1)
    {
    case 35:                        // '>>'
      consume(35);                  // '>>'
      break;
    default:
      consume(30);                  // '<<'
    }
    for (;;)
    {
      lookahead1W(10);              // Whitespace | NCName | StringLiteral
      whitespace();
      parse_NameOrString();
      lookahead1W(19);              // Whitespace | NCName | StringLiteral | DocComment | EOF | EquivalenceLookAhead |
                                    // '<?ENCORE?>'
      switch (l1)
      {
      case 3:                       // NCName
        lookahead2W(27);            // Whitespace | NCName | Context | StringLiteral | DocComment | EOF |
                                    // EquivalenceLookAhead | '::=' | '<<' | '<?ENCORE?>' | '>>' | '?' | '\\'
        switch (lk)
        {
        case 259:                   // NCName Context
          lookahead3W(21);          // Whitespace | NCName | StringLiteral | DocComment | EOF | EquivalenceLookAhead |
                                    // '<<' | '<?ENCORE?>' | '>>'
          break;
        }
        break;
      case 5:                       // StringLiteral
        lookahead2W(22);            // Whitespace | NCName | Context | StringLiteral | DocComment | EOF |
                                    // EquivalenceLookAhead | '<<' | '<?ENCORE?>' | '>>'
        switch (lk)
        {
        case 261:                   // StringLiteral Context
          lookahead3W(21);          // Whitespace | NCName | StringLiteral | DocComment | EOF | EquivalenceLookAhead |
                                    // '<<' | '<?ENCORE?>' | '>>'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 15                  // DocComment
       || lk == 16                  // EOF
       || lk == 17                  // EquivalenceLookAhead
       || lk == 32                  // '<?ENCORE?>'
       || lk == 1859                // NCName '::='
       || lk == 1923                // NCName '<<'
       || lk == 1925                // StringLiteral '<<'
       || lk == 2243                // NCName '>>'
       || lk == 2245                // StringLiteral '>>'
       || lk == 2307                // NCName '?'
       || lk == 2563                // NCName '\\'
       || lk == 123139              // NCName Context '<<'
       || lk == 123141              // StringLiteral Context '<<'
       || lk == 143619              // NCName Context '>>'
       || lk == 143621)             // StringLiteral Context '>>'
      {
        break;
      }
    }
    eventHandler.endNonterminal("Preference", e0);
  }

  private void parse_Delimiter()
  {
    eventHandler.startNonterminal("Delimiter", e0);
    consume(3);                     // NCName
    lookahead1W(7);                 // Whitespace | '\\'
    consume(40);                    // '\\'
    for (;;)
    {
      lookahead1W(10);              // Whitespace | NCName | StringLiteral
      whitespace();
      parse_NameOrString();
      lookahead1W(19);              // Whitespace | NCName | StringLiteral | DocComment | EOF | EquivalenceLookAhead |
                                    // '<?ENCORE?>'
      switch (l1)
      {
      case 3:                       // NCName
        lookahead2W(27);            // Whitespace | NCName | Context | StringLiteral | DocComment | EOF |
                                    // EquivalenceLookAhead | '::=' | '<<' | '<?ENCORE?>' | '>>' | '?' | '\\'
        switch (lk)
        {
        case 259:                   // NCName Context
          lookahead3W(21);          // Whitespace | NCName | StringLiteral | DocComment | EOF | EquivalenceLookAhead |
                                    // '<<' | '<?ENCORE?>' | '>>'
          break;
        }
        break;
      case 5:                       // StringLiteral
        lookahead2W(22);            // Whitespace | NCName | Context | StringLiteral | DocComment | EOF |
                                    // EquivalenceLookAhead | '<<' | '<?ENCORE?>' | '>>'
        switch (lk)
        {
        case 261:                   // StringLiteral Context
          lookahead3W(21);          // Whitespace | NCName | StringLiteral | DocComment | EOF | EquivalenceLookAhead |
                                    // '<<' | '<?ENCORE?>' | '>>'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 15                  // DocComment
       || lk == 16                  // EOF
       || lk == 17                  // EquivalenceLookAhead
       || lk == 32                  // '<?ENCORE?>'
       || lk == 1859                // NCName '::='
       || lk == 1923                // NCName '<<'
       || lk == 1925                // StringLiteral '<<'
       || lk == 2243                // NCName '>>'
       || lk == 2245                // StringLiteral '>>'
       || lk == 2307                // NCName '?'
       || lk == 2563                // NCName '\\'
       || lk == 123139              // NCName Context '<<'
       || lk == 123141              // StringLiteral Context '<<'
       || lk == 143619              // NCName Context '>>'
       || lk == 143621)             // StringLiteral Context '>>'
      {
        break;
      }
    }
    eventHandler.endNonterminal("Delimiter", e0);
  }

  private void parse_Equivalence()
  {
    eventHandler.startNonterminal("Equivalence", e0);
    consume(17);                    // EquivalenceLookAhead
    lookahead1W(11);                // Whitespace | StringLiteral | '['
    whitespace();
    parse_EquivalenceCharRange();
    lookahead1W(6);                 // Whitespace | '=='
    consume(34);                    // '=='
    lookahead1W(11);                // Whitespace | StringLiteral | '['
    whitespace();
    parse_EquivalenceCharRange();
    eventHandler.endNonterminal("Equivalence", e0);
  }

  private void parse_EquivalenceCharRange()
  {
    eventHandler.startNonterminal("EquivalenceCharRange", e0);
    switch (l1)
    {
    case 5:                         // StringLiteral
      consume(5);                   // StringLiteral
      break;
    default:
      consume(38);                  // '['
      lookahead1(16);               // CharCode | Char | CharRange | CharCodeRange
      switch (l1)
      {
      case 7:                       // Char
        consume(7);                 // Char
        break;
      case 6:                       // CharCode
        consume(6);                 // CharCode
        break;
      case 8:                       // CharRange
        consume(8);                 // CharRange
        break;
      default:
        consume(9);                 // CharCodeRange
      }
      lookahead1(3);                // ']'
      consume(41);                  // ']'
    }
    eventHandler.endNonterminal("EquivalenceCharRange", e0);
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
      if (code != 1)                // Whitespace
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
      int i0 = (charclass << 8) + code - 1;
      code = TRANSITION[(i0 & 7) + TRANSITION[i0 >> 3]];

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
    else if ((result & 64) != 0)
    {
      end = begin;
      if (nonbmp)
      {
        for (int i = result >> 7; i > 0; --i)
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
        end += (result >> 7);
      }
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
    return (result & 63) - 1;
  }

  private static String[] getTokenSet(int tokenSetId)
  {
    java.util.ArrayList<String> expected = new java.util.ArrayList<>();
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 255;
    for (int i = 0; i < 43; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 166 + s - 1;
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
    /*   0 */ 52, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 5,
    /*  35 */ 6, 7, 4, 8, 9, 10, 11, 12, 13, 4, 14, 15, 16, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 4, 19, 20, 21,
    /*  63 */ 22, 4, 23, 23, 24, 23, 25, 23, 26, 26, 26, 26, 27, 26, 26, 28, 29, 26, 26, 30, 31, 32, 26, 26, 26, 26, 26,
    /*  90 */ 26, 33, 34, 35, 36, 26, 4, 23, 23, 37, 38, 39, 40, 26, 26, 41, 26, 26, 42, 26, 43, 44, 45, 26, 26, 46, 47,
    /* 117 */ 26, 26, 48, 49, 26, 26, 4, 50, 4, 4, 4
  };

  private static final int[] MAP1 =
  {
    /*    0 */ 216, 291, 323, 383, 415, 908, 351, 815, 815, 447, 479, 511, 543, 575, 621, 882, 589, 681, 815, 815, 815,
    /*   21 */ 815, 815, 815, 815, 815, 815, 815, 815, 815, 713, 745, 821, 649, 815, 815, 815, 815, 815, 815, 815, 815,
    /*   42 */ 815, 815, 815, 815, 815, 815, 777, 809, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815,
    /*   63 */ 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 815, 247, 247, 247, 247, 247, 247,
    /*   84 */ 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    /*  105 */ 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    /*  126 */ 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    /*  147 */ 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 259, 815, 815, 815, 815, 815, 815, 815, 815,
    /*  168 */ 815, 815, 815, 815, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    /*  189 */ 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247, 247,
    /*  210 */ 247, 247, 247, 247, 247, 853, 940, 949, 941, 941, 957, 965, 973, 979, 987, 1010, 1018, 1035, 1053, 1071,
    /*  230 */ 1079, 1087, 1262, 1262, 1262, 1262, 1262, 1262, 1433, 1262, 1254, 1254, 1255, 1254, 1254, 1254, 1255,
    /*  247 */ 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254,
    /*  264 */ 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1256, 1262,
    /*  281 */ 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1254, 1254, 1254, 1254, 1254, 1254, 1342,
    /*  298 */ 1255, 1253, 1252, 1254, 1254, 1254, 1254, 1254, 1255, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254,
    /*  315 */ 1258, 1418, 1254, 1254, 1254, 1254, 1062, 1421, 1254, 1254, 1254, 1262, 1262, 1262, 1262, 1262, 1262,
    /*  332 */ 1262, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1261, 1262, 1420, 1260, 1262,
    /*  349 */ 1388, 1262, 1262, 1262, 1262, 1262, 1253, 1254, 1254, 1259, 1131, 1308, 1387, 1262, 1382, 1388, 1131,
    /*  366 */ 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1344, 1254, 1255, 1142, 1382, 1297, 1196, 1382, 1388,
    /*  383 */ 1382, 1382, 1382, 1382, 1382, 1382, 1382, 1382, 1384, 1262, 1262, 1262, 1388, 1262, 1262, 1262, 1367,
    /*  400 */ 1231, 1254, 1254, 1251, 1254, 1254, 1254, 1254, 1255, 1255, 1407, 1252, 1254, 1258, 1262, 1253, 1100,
    /*  417 */ 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1253, 1100, 1254, 1254, 1254, 1254, 1109, 1262, 1254,
    /*  434 */ 1254, 1254, 1254, 1254, 1254, 1122, 1042, 1254, 1254, 1254, 1123, 1256, 1260, 1446, 1254, 1254, 1254,
    /*  451 */ 1254, 1254, 1254, 1160, 1382, 1384, 1197, 1254, 1178, 1382, 1262, 1262, 1446, 1122, 1343, 1254, 1254,
    /*  468 */ 1252, 1060, 1192, 1169, 1181, 1433, 1207, 1178, 1382, 1260, 1262, 1218, 1241, 1343, 1254, 1254, 1252,
    /*  485 */ 1397, 1192, 1184, 1181, 1262, 1229, 1434, 1382, 1239, 1262, 1446, 1230, 1251, 1254, 1254, 1252, 1249,
    /*  502 */ 1160, 1272, 1114, 1262, 1262, 994, 1382, 1262, 1262, 1446, 1122, 1343, 1254, 1254, 1252, 1340, 1160,
    /*  519 */ 1198, 1181, 1434, 1207, 1045, 1382, 1262, 1262, 1002, 1023, 1285, 1281, 1063, 1023, 1133, 1045, 1199,
    /*  536 */ 1196, 1433, 1262, 1433, 1382, 1262, 1262, 1446, 1100, 1252, 1254, 1254, 1252, 1101, 1045, 1273, 1196,
    /*  553 */ 1435, 1262, 1045, 1382, 1262, 1262, 1002, 1100, 1252, 1254, 1254, 1252, 1101, 1045, 1273, 1196, 1435,
    /*  570 */ 1264, 1045, 1382, 1262, 1262, 1002, 1100, 1252, 1254, 1254, 1252, 1254, 1045, 1170, 1196, 1433, 1262,
    /*  587 */ 1045, 1382, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262,
    /*  604 */ 1262, 1262, 1262, 1262, 1262, 1254, 1254, 1254, 1254, 1256, 1262, 1254, 1254, 1254, 1254, 1255, 1262,
    /*  621 */ 1253, 1254, 1254, 1254, 1254, 1255, 1293, 1387, 1305, 1383, 1382, 1388, 1262, 1262, 1262, 1262, 1210,
    /*  638 */ 1317, 1419, 1253, 1327, 1337, 1293, 1152, 1352, 1384, 1382, 1388, 1262, 1262, 1262, 1262, 1264, 1027,
    /*  655 */ 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1259, 1262, 1262, 1262, 1262, 1262, 1262,
    /*  672 */ 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1249, 1396, 1259, 1262, 1262, 1262, 1262, 1405,
    /*  689 */ 1261, 1405, 1062, 1416, 1329, 1061, 1209, 1262, 1262, 1262, 1262, 1264, 1262, 1319, 1263, 1283, 1259,
    /*  706 */ 1262, 1262, 1262, 1262, 1429, 1261, 1431, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254,
    /*  723 */ 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1258, 1254, 1254, 1254, 1254, 1254, 1254, 1254,
    /*  740 */ 1254, 1254, 1254, 1254, 1260, 1254, 1254, 1256, 1256, 1254, 1254, 1254, 1254, 1256, 1256, 1254, 1408,
    /*  757 */ 1254, 1254, 1254, 1256, 1254, 1254, 1254, 1254, 1254, 1254, 1100, 1134, 1221, 1257, 1123, 1258, 1254,
    /*  774 */ 1257, 1221, 1257, 1092, 1262, 1262, 1262, 1253, 1309, 1168, 1262, 1253, 1254, 1254, 1254, 1254, 1254,
    /*  791 */ 1254, 1254, 1254, 1254, 1257, 999, 1253, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254,
    /*  808 */ 1443, 1418, 1254, 1254, 1254, 1254, 1257, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262,
    /*  825 */ 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262,
    /*  842 */ 1262, 1262, 1262, 1262, 1262, 1382, 1385, 1365, 1262, 1262, 1262, 1254, 1254, 1254, 1254, 1254, 1254,
    /*  859 */ 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1254, 1258, 1262, 1262,
    /*  876 */ 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1262, 1388, 1382, 1388, 1375, 1357, 1254, 1253, 1254,
    /*  893 */ 1254, 1254, 1260, 1381, 1382, 1273, 1386, 1272, 1381, 1382, 1384, 1381, 1365, 1262, 1262, 1262, 1262,
    /*  910 */ 1262, 1262, 1262, 1262, 1253, 1254, 1254, 1254, 1255, 1431, 1253, 1254, 1254, 1254, 1255, 1262, 1381,
    /*  927 */ 1382, 1166, 1382, 1382, 1148, 1362, 1262, 1254, 1254, 1254, 1259, 1259, 1262, 52, 0, 0, 0, 0, 0, 0, 0, 0,
    /*  949 */ 0, 1, 2, 0, 0, 1, 0, 0, 3, 4, 5, 6, 7, 4, 8, 9, 10, 11, 12, 13, 4, 14, 15, 16, 17, 17, 17, 17, 17, 17,
    /*  979 */ 17, 17, 18, 4, 19, 20, 21, 22, 4, 23, 23, 24, 23, 25, 23, 26, 4, 4, 4, 4, 4, 51, 51, 4, 4, 51, 51, 4, 26,
    /* 1008 */ 26, 26, 26, 26, 26, 27, 26, 26, 28, 29, 26, 26, 30, 31, 32, 26, 26, 26, 4, 4, 4, 26, 26, 4, 4, 26, 4, 26,
    /* 1036 */ 26, 26, 33, 34, 35, 36, 26, 4, 4, 26, 26, 4, 4, 4, 4, 51, 51, 4, 23, 23, 37, 38, 39, 40, 26, 4, 26, 4, 4,
    /* 1065 */ 4, 26, 26, 4, 4, 4, 26, 41, 26, 26, 42, 26, 43, 44, 45, 26, 26, 46, 47, 26, 26, 48, 49, 26, 26, 4, 50, 4,
    /* 1093 */ 4, 4, 4, 4, 51, 4, 26, 26, 26, 26, 26, 26, 4, 26, 26, 26, 26, 26, 4, 51, 51, 51, 51, 4, 51, 51, 51, 4, 4,
    /* 1122 */ 26, 26, 26, 26, 26, 4, 4, 26, 26, 51, 26, 26, 26, 26, 26, 26, 26, 4, 26, 4, 26, 26, 26, 26, 4, 26, 51,
    /* 1149 */ 51, 4, 51, 51, 51, 4, 51, 51, 26, 4, 4, 26, 26, 4, 4, 51, 26, 51, 51, 4, 51, 51, 51, 51, 51, 4, 4, 51,
    /* 1177 */ 51, 26, 26, 51, 51, 4, 4, 51, 51, 51, 4, 4, 4, 4, 51, 26, 26, 4, 4, 51, 4, 51, 51, 51, 51, 4, 4, 4, 51,
    /* 1206 */ 51, 4, 4, 4, 4, 26, 26, 4, 26, 4, 4, 26, 4, 4, 51, 4, 4, 26, 26, 26, 4, 26, 26, 4, 26, 26, 26, 26, 4, 26,
    /* 1236 */ 4, 26, 26, 51, 51, 26, 26, 26, 4, 4, 4, 4, 26, 26, 4, 26, 26, 4, 26, 26, 26, 26, 26, 26, 26, 26, 4, 4, 4,
    /* 1265 */ 4, 4, 4, 4, 4, 26, 4, 51, 51, 51, 51, 51, 51, 4, 51, 51, 4, 26, 26, 4, 26, 4, 26, 26, 26, 26, 4, 4, 26,
    /* 1294 */ 51, 26, 26, 51, 51, 51, 51, 51, 26, 26, 51, 26, 26, 26, 26, 26, 26, 51, 51, 51, 51, 51, 51, 26, 4, 26, 4,
    /* 1321 */ 4, 26, 4, 4, 26, 26, 4, 26, 26, 26, 4, 26, 4, 26, 4, 26, 4, 4, 26, 26, 4, 26, 26, 4, 4, 26, 26, 26, 26,
    /* 1350 */ 26, 4, 26, 26, 26, 26, 26, 4, 51, 4, 4, 4, 4, 51, 51, 4, 51, 4, 4, 4, 4, 4, 4, 26, 51, 4, 4, 4, 4, 4, 51,
    /* 1381 */ 4, 51, 51, 51, 51, 51, 51, 51, 51, 4, 4, 4, 4, 4, 4, 4, 26, 4, 26, 26, 4, 26, 26, 4, 4, 4, 4, 4, 26, 4,
    /* 1411 */ 26, 4, 26, 4, 26, 4, 26, 4, 4, 4, 4, 4, 26, 26, 26, 26, 26, 26, 4, 4, 4, 26, 4, 4, 4, 4, 4, 4, 4, 51, 51,
    /* 1442 */ 4, 26, 26, 26, 4, 51, 51, 51, 4, 26, 26, 26
  };

  private static final int[] MAP2 =
  {
    /* 0 */ 57344, 65536, 65533, 1114111, 4, 4
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
    /* 29 */ 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41
  };

  private static final int[] TRANSITION =
  {
    /*    0 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /*   17 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2200, 1696,
    /*   34 */ 2202, 2204, 2204, 1703, 2400, 3056, 2053, 2142, 2700, 2869, 1716, 3124, 2745, 1731, 2108, 1736, 1738,
    /*   51 */ 3222, 2640, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2200, 1696, 2202, 2204,
    /*   68 */ 2204, 1703, 2400, 3056, 2053, 2122, 2700, 2869, 1716, 3124, 2745, 1747, 2628, 1736, 1738, 3222, 2640,
    /*   85 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2200, 1696, 1762, 2204, 2204, 1703,
    /*  102 */ 2475, 3075, 2216, 2160, 2700, 3001, 1770, 3124, 2745, 1731, 2108, 1736, 1738, 3222, 2640, 2403, 2403,
    /*  119 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2647, 2402, 1797, 2403, 2403, 2646, 2475, 3075,
    /*  136 */ 2216, 2160, 2874, 3001, 1813, 2872, 2745, 1807, 2108, 1736, 1738, 3222, 2789, 2403, 2403, 2403, 2403,
    /*  153 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2647, 1821, 1833, 1838, 1838, 1846, 3184, 3075, 2216, 2160,
    /*  170 */ 2874, 3001, 1813, 2872, 2745, 1807, 2108, 1736, 1738, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403,
    /*  187 */ 2403, 2403, 2403, 2403, 2403, 2403, 2402, 1855, 1861, 1865, 1873, 2475, 1783, 2807, 2160, 1799, 2074,
    /*  204 */ 1955, 2612, 3149, 1883, 2108, 1901, 1738, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /*  221 */ 2403, 2403, 2403, 2647, 2402, 1911, 1917, 1921, 1929, 2475, 3075, 2216, 2160, 2874, 3001, 1813, 2872,
    /*  238 */ 2745, 1807, 2108, 1736, 1738, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /*  255 */ 2403, 2647, 2402, 1797, 2178, 1942, 1949, 2475, 3075, 2216, 2160, 2874, 3001, 1813, 2872, 2745, 1807,
    /*  272 */ 2108, 1736, 1738, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2647,
    /*  289 */ 1963, 1975, 1980, 1980, 1988, 2528, 3075, 2216, 2160, 2874, 3001, 1813, 2872, 2745, 1807, 2108, 1736,
    /*  306 */ 1738, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2647, 2402, 1999,
    /*  323 */ 2005, 2009, 2017, 2475, 3075, 2216, 2160, 2874, 3001, 1813, 2872, 2745, 1807, 2108, 1736, 1738, 3222,
    /*  340 */ 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2647, 2402, 1797, 2028, 2043,
    /*  357 */ 2050, 2475, 3075, 2216, 2160, 2874, 3001, 1813, 2872, 2745, 1807, 2108, 1736, 1738, 3222, 2789, 2403,
    /*  374 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2647, 2402, 1797, 2403, 2061, 2068, 2475,
    /*  391 */ 2082, 2319, 2438, 2614, 2096, 2341, 2104, 2116, 2136, 2759, 1752, 1754, 2765, 2154, 2403, 2403, 2403,
    /*  408 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2647, 2402, 1797, 2403, 2168, 2175, 2475, 3075, 2216,
    /*  425 */ 2186, 2874, 3001, 1813, 2872, 2745, 1807, 2108, 1736, 1738, 3222, 2789, 2403, 2403, 2403, 2403, 2403,
    /*  442 */ 2403, 2403, 2403, 2403, 2403, 2403, 2647, 2402, 1797, 2403, 1893, 2212, 2475, 2224, 2216, 2160, 3288,
    /*  459 */ 2238, 1813, 2872, 2246, 1807, 2108, 2903, 2916, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /*  476 */ 2403, 2403, 2403, 2403, 2647, 2402, 2260, 2266, 2270, 2278, 2475, 3075, 2216, 2160, 2597, 3001, 1813,
    /*  493 */ 2872, 2745, 1807, 2108, 2903, 2916, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /*  510 */ 2403, 2403, 1825, 2290, 2298, 2306, 2327, 2313, 2475, 2335, 2432, 2349, 1799, 2364, 2372, 2393, 3282,
    /*  527 */ 1807, 2779, 1736, 1738, 3222, 2728, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /*  544 */ 2647, 2402, 1797, 2403, 2403, 2803, 2475, 1991, 2216, 2230, 3085, 2411, 2419, 2872, 2446, 2460, 2108,
    /*  561 */ 2468, 2854, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 3059, 2488,
    /*  578 */ 2502, 3061, 2506, 2514, 2475, 3075, 1967, 2160, 1875, 2541, 1789, 2872, 2549, 1807, 2108, 1736, 1738,
    /*  595 */ 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2647, 2563, 2571, 2579,
    /*  612 */ 2586, 2593, 2521, 3099, 2282, 2160, 2874, 3001, 1813, 2872, 2745, 1807, 2108, 1736, 1738, 3222, 2789,
    /*  629 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2193, 2402, 1797, 2403, 2403, 2128,
    /*  646 */ 2475, 3075, 2216, 2252, 2874, 3001, 2605, 1723, 2745, 2622, 2722, 1736, 1738, 3222, 2789, 2403, 2403,
    /*  663 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2647, 2655, 2666, 2658, 3237, 2672, 2356, 3075,
    /*  680 */ 2216, 2160, 2874, 3001, 1813, 2872, 2745, 1807, 2108, 1736, 1903, 3222, 2789, 2403, 2403, 2403, 2403,
    /*  697 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 3081, 2684, 2694, 2686, 2708, 2698, 2716, 3138, 2533, 2160,
    /*  714 */ 1799, 2074, 2906, 2872, 2146, 1807, 2108, 1736, 2753, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403,
    /*  731 */ 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 2020, 2216, 2230, 3085, 2411,
    /*  748 */ 2419, 2872, 2446, 2844, 2108, 2852, 2854, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /*  765 */ 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 2020, 2216, 2230, 3085, 2411, 2419, 2426,
    /*  782 */ 2446, 2844, 2108, 2852, 2854, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /*  799 */ 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 2020, 2216, 2230, 2862, 2411, 2419, 2872, 2446, 2882,
    /*  816 */ 2108, 2890, 2854, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773,
    /*  833 */ 2797, 2815, 2820, 2820, 2828, 2475, 3113, 2216, 2160, 2597, 3001, 1813, 2872, 2745, 2898, 2108, 2914,
    /*  850 */ 2916, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815,
    /*  867 */ 2820, 2820, 2828, 2475, 3113, 2216, 2160, 2597, 3001, 1813, 2379, 2745, 2898, 2108, 2914, 2916, 3222,
    /*  884 */ 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820,
    /*  901 */ 2828, 2475, 3113, 2216, 2160, 2597, 2924, 1813, 2872, 2745, 2898, 2932, 2914, 2916, 3222, 2789, 2403,
    /*  918 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475,
    /*  935 */ 3113, 2216, 2160, 2597, 2963, 1813, 2872, 3260, 2898, 2108, 2914, 2916, 3222, 2789, 2403, 2403, 2403,
    /*  952 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 3113, 2216,
    /*  969 */ 2160, 2597, 3001, 1813, 2872, 2745, 2898, 2971, 2914, 2916, 3222, 2789, 2403, 2403, 2403, 2403, 2403,
    /*  986 */ 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 3113, 2216, 2160, 2597,
    /* 1003 */ 3001, 1813, 2872, 2745, 2898, 2108, 2986, 2916, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /* 1020 */ 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 3113, 2216, 2160, 2994, 3001, 1813,
    /* 1037 */ 2872, 2745, 2898, 2108, 2914, 2916, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /* 1054 */ 2403, 2403, 2647, 3009, 3029, 3035, 3043, 3050, 2475, 3075, 2216, 2160, 2874, 3001, 1813, 2872, 2745,
    /* 1071 */ 1807, 2108, 1736, 1738, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /* 1088 */ 1847, 2402, 3069, 1934, 1932, 3093, 3107, 3075, 2216, 2160, 2874, 3001, 1813, 2872, 2745, 1807, 2108,
    /* 1105 */ 1736, 1738, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2033, 2402,
    /* 1122 */ 2035, 2403, 2403, 2403, 2475, 3056, 2403, 2142, 2740, 3121, 1739, 1777, 3132, 3146, 2108, 1952, 2734,
    /* 1139 */ 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2647, 2402, 3157, 1888,
    /* 1156 */ 2385, 3163, 2475, 3075, 3177, 2160, 2874, 3001, 1813, 2872, 2745, 1807, 2108, 1736, 1738, 3222, 2789,
    /* 1173 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828,
    /* 1190 */ 2475, 2020, 2216, 2230, 3085, 2411, 2419, 2872, 2446, 2844, 2108, 2852, 2854, 2785, 2789, 2403, 2403,
    /* 1207 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 2020,
    /* 1224 */ 2216, 2230, 3085, 2411, 2419, 2872, 2446, 3197, 2108, 2852, 2854, 3222, 2789, 2403, 2403, 2403, 2403,
    /* 1241 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 2020, 2216, 2230,
    /* 1258 */ 3085, 2411, 2419, 2872, 2446, 3205, 2494, 2852, 2854, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403,
    /* 1275 */ 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 2020, 2216, 2230, 3085, 2411,
    /* 1292 */ 2419, 2872, 2446, 2844, 2108, 3213, 2854, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /* 1309 */ 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 3113, 2216, 2160, 2597, 3001, 1813, 2872,
    /* 1326 */ 2745, 2898, 2108, 2914, 3189, 2978, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /* 1343 */ 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 3113, 2216, 2160, 2597, 3001, 1813, 2872, 2745, 2898,
    /* 1360 */ 2108, 2914, 1708, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773,
    /* 1377 */ 2797, 2815, 2820, 2820, 2828, 2475, 3113, 2216, 2160, 2597, 3001, 1813, 2872, 2745, 2898, 2108, 2914,
    /* 1394 */ 2916, 3221, 2938, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815,
    /* 1411 */ 2820, 2820, 2828, 2475, 3113, 2216, 2160, 2597, 3001, 1813, 2872, 2745, 2898, 2108, 2914, 2916, 3222,
    /* 1428 */ 3230, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820,
    /* 1445 */ 2828, 2475, 3113, 2216, 2160, 2597, 3001, 1813, 2872, 2745, 2898, 2108, 3245, 2916, 3222, 2789, 2403,
    /* 1462 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475,
    /* 1479 */ 3113, 2216, 2160, 2597, 3001, 2088, 2872, 2745, 2898, 2108, 2914, 2916, 3222, 2789, 2403, 2403, 2403,
    /* 1496 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 3113, 2216,
    /* 1513 */ 2160, 2597, 3001, 1813, 2872, 2745, 2898, 2108, 2914, 2916, 2634, 2789, 2403, 2403, 2403, 2403, 2403,
    /* 1530 */ 2403, 2403, 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 3113, 2216, 2160, 2676,
    /* 1547 */ 3001, 2452, 2872, 2745, 2898, 2108, 2914, 2916, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /* 1564 */ 2403, 2403, 2403, 2403, 2773, 2797, 2815, 2820, 2820, 2828, 2475, 3169, 3253, 2160, 2480, 3001, 1813,
    /* 1581 */ 2872, 3275, 2898, 2555, 2914, 2916, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /* 1598 */ 2403, 2403, 2647, 2402, 1797, 3015, 3021, 3268, 2475, 3075, 2216, 2160, 2874, 3001, 1813, 2872, 2745,
    /* 1615 */ 1807, 2108, 1736, 1738, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /* 1632 */ 2647, 2402, 1797, 2403, 2403, 2803, 2475, 3075, 2216, 2160, 2597, 3001, 1813, 2872, 2745, 1807, 2108,
    /* 1649 */ 2903, 2916, 3222, 2789, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2832,
    /* 1666 */ 2944, 2950, 2955, 2836, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403,
    /* 1683 */ 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 2403, 3890, 3840, 557, 557, 557,
    /* 1701 */ 557, 557, 557, 0, 43, 0, 557, 0, 0, 0, 75, 154, 52118, 0, 0, 110, 0, 99, 75, 0, 88, 3190, 104, 0, 0, 0,
    /* 1727 */ 0, 112, 0, 20992, 0, 122, 123, 0, 125, 52105, 0, 0, 0, 0, 75, 75, 0, 0, 0, 104, 0, 122, 132, 0, 125,
    /* 1752 */ 52105, 0, 0, 0, 0, 91, 91, 0, 0, 0, 2106, 2106, 557, 557, 557, 557, 557, 557, 110, 0, 99, 75, 0, 116,
    /* 1776 */ 3190, 104, 0, 0, 0, 97, 0, 0, 0, 34816, 0, 0, 84, 0, 0, 75, 75, 0, 117, 3190, 104, 2106, 2106, 0, 0, 0,
    /* 1802 */ 0, 0, 0, 75, 0, 0, 122, 123, 0, 75, 52105, 0, 0, 75, 75, 0, 116, 3190, 104, 50, 0, 52, 52, 0, 0, 0, 0,
    /* 1829 */ 46, 46, 46, 46, 2106, 2106, 0, 52, 0, 52, 52, 52, 52, 52, 52, 52, 52, 52, 0, 43, 0, 0, 0, 0, 0, 49, 59,
    /* 1856 */ 59, 0, 0, 0, 0, 0, 65, 65, 0, 65, 65, 65, 65, 65, 65, 65, 65, 65, 0, 0, 0, 0, 0, 0, 0, 75, 101, 0, 122,
    /* 1885 */ 123, 134, 75, 0, 0, 0, 1343, 0, 0, 0, 0, 6912, 6912, 0, 6912, 6912, 144, 0, 0, 0, 0, 75, 75, 0, 8448,
    /* 1910 */ 8704, 2106, 2106, 0, 0, 0, 0, 0, 4864, 4864, 0, 4864, 4864, 4864, 4864, 4864, 4864, 4864, 4864, 4864, 0,
    /* 1931 */ 43, 0, 0, 0, 0, 0, 49, 0, 0, 0, 0, 5120, 5120, 5120, 5120, 5120, 5120, 5120, 5120, 0, 43, 0, 0, 0, 0, 0,
    /* 1957 */ 75, 75, 0, 109, 0, 104, 50, 0, 53, 53, 0, 0, 0, 0, 83, 83, 0, 0, 2106, 2106, 0, 53, 0, 53, 53, 53, 53,
    /* 1984 */ 53, 53, 53, 53, 53, 0, 43, 0, 0, 0, 0, 0, 83, 0, 0, 2106, 2106, 0, 0, 0, 0, 0, 5376, 5376, 0, 5376, 5376,
    /* 2011 */ 5376, 5376, 5376, 5376, 5376, 5376, 5376, 0, 43, 0, 0, 0, 0, 0, 83, 1365, 0, 0, 5632, 5632, 0, 5632, 0,
    /* 2034 */ 0, 0, 10752, 0, 0, 0, 0, 0, 0, 5632, 5632, 5632, 5632, 5632, 5632, 5632, 5632, 0, 43, 0, 0, 0, 0, 0, 88,
    /* 2059 */ 0, 0, 5888, 5888, 5888, 5960, 5960, 5888, 5960, 5960, 0, 43, 0, 0, 75, 0, 0, 75, 104, 0, 0, 0, 109, 78,
    /* 2083 */ 0, 34816, 0, 0, 83, 0, 0, 75, 114, 0, 116, 3190, 104, 88, 0, 91, 105, 0, 0, 0, 109, 105, 0, 0, 0, 0, 0,
    /* 2110 */ 122, 0, 123, 0, 75, 75, 0, 91, 0, 88, 0, 3190, 0, 0, 75, 512, 0, 75, 0, 0, 43, 0, 0, 0, 0, 8960, 0, 131,
    /* 2138 */ 123, 0, 91, 52105, 0, 0, 75, 588, 0, 75, 0, 0, 0, 3190, 0, 0, 91, 164, 91, 91, 166, 91, 0, 0, 75, 588, 0,
    /* 2165 */ 75, 2304, 0, 6400, 6400, 6400, 6473, 6473, 6400, 6473, 6473, 0, 43, 0, 0, 0, 0, 0, 5120, 5120, 5120,
    /* 2186 */ 6656, 0, 75, 588, 0, 75, 2304, 0, 43, 0, 0, 0, 0, 48, 0, 43, 0, 0, 557, 557, 557, 557, 557, 557, 557,
    /* 2211 */ 557, 6912, 1066, 43, 0, 0, 0, 0, 0, 83, 89, 0, 0, 0, 79, 34895, 0, 0, 83, 0, 0, 75, 588, 0, 75, 2304,
    /* 2237 */ 1885, 102, 0, 75, 104, 106, 0, 0, 109, 124, 75, 0, 88, 0, 3190, 0, 0, 75, 588, 7680, 75, 2304, 0, 2106,
    /* 2261 */ 2106, 0, 0, 0, 0, 0, 7168, 7168, 0, 7168, 7168, 7168, 7168, 7168, 7168, 7168, 7168, 7168, 1066, 43, 0, 0,
    /* 2283 */ 0, 0, 0, 83, 89, 7936, 0, 50, 0, 46, 46, 46, 46, 46, 57, 2106, 2106, 57, 57, 46, 57, 57, 46, 67, 7470,
    /* 2308 */ 46, 57, 7470, 7491, 67, 7491, 0, 0, 0, 0, 588, 0, 0, 87, 0, 83, 89, 0, 6144, 7491, 7491, 7491, 7491,
    /* 2331 */ 7491, 7491, 7491, 7491, 588, 0, 34816, 0, 0, 83, 0, 0, 91, 91, 0, 116, 3190, 105, 0, 90, 75, 588, 0, 75,
    /* 2355 */ 2304, 0, 50, 0, 52, 53, 0, 0, 9216, 0, 103, 512, 616, 0, 0, 0, 109, 111, 0, 75, 75, 115, 109, 3190, 104,
    /* 2380 */ 0, 0, 121, 0, 0, 0, 0, 1343, 0, 0, 1343, 0, 1343, 4096, 0, 0, 0, 0, 0, 123, 0, 50, 50, 0, 0, 0, 0, 0, 0,
    /* 2409 */ 0, 0, 88, 0, 75, 104, 1885, 0, 0, 109, 0, 113, 75, 75, 0, 116, 3190, 104, 0, 120, 0, 0, 0, 0, 0, 588, 0,
    /* 2436 */ 83, 83, 0, 0, 91, 588, 0, 92, 2304, 0, 113, 75, 0, 88, 0, 3190, 0, 0, 100, 75, 0, 116, 3190, 104, 0, 122,
    /* 2462 */ 123, 0, 75, 52105, 0, 2688, 52105, 3210, 0, 0, 147, 75, 75, 0, 50, 50, 52, 53, 0, 0, 0, 98, 1365, 0, 75,
    /* 2487 */ 88, 50, 0, 0, 0, 0, 47, 0, 0, 122, 0, 123, 0, 142, 75, 2106, 2106, 0, 0, 47, 0, 0, 0, 47, 47, 0, 0, 47,
    /* 2515 */ 0, 74, 0, 0, 0, 77, 0, 50, 50, 52, 53, 0, 7936, 0, 50, 50, 52, 1536, 0, 0, 0, 8273, 83, 83, 8273, 0, 101,
    /* 2542 */ 0, 75, 104, 0, 0, 0, 109, 0, 125, 0, 101, 0, 3190, 0, 0, 122, 0, 123, 141, 75, 143, 50, 0, 0, 0, 54, 0,
    /* 2569 */ 55, 54, 2106, 2106, 60, 61, 55, 64, 64, 54, 60, 54, 54, 64, 54, 68, 68, 68, 71, 71, 68, 68, 71, 71, 71,
    /* 2594 */ 0, 43, 0, 0, 0, 0, 0, 1365, 0, 75, 88, 112, 0, 75, 75, 0, 116, 3190, 104, 119, 0, 0, 0, 0, 0, 0, 92, 88,
    /* 2622 */ 0, 122, 133, 0, 75, 52105, 0, 0, 122, 0, 132, 0, 75, 75, 75, 75, 159, 75, 75, 162, 75, 75, 165, 75, 0, 0,
    /* 2648 */ 43, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 56, 0, 0, 0, 0, 2106, 2106, 0, 0, 56, 56, 56, 0, 43, 9728, 0, 0,
    /* 2678 */ 0, 0, 1365, 0, 100, 88, 33587, 44, 0, 0, 0, 9472, 0, 0, 0, 0, 2106, 2106, 0, 0, 9472, 0, 0, 0, 0, 0, 0,
    /* 2705 */ 0, 99, 88, 9472, 9472, 9472, 9472, 9472, 9472, 9472, 9472, 0, 33587, 33587, 52, 53, 8192, 0, 0, 122, 112,
    /* 2726 */ 21125, 0, 75, 75, 75, 3328, 75, 3584, 0, 0, 97, 75, 75, 0, 0, 0, 97, 97, 0, 0, 75, 0, 88, 0, 3190, 0, 0,
    /* 2753 */ 151, 152, 0, 75, 75, 0, 0, 0, 131, 0, 123, 0, 91, 91, 91, 91, 91, 91, 91, 91, 1066, 43, 0, 0, 1066, 0, 0,
    /* 2780 */ 0, 132, 0, 123, 0, 75, 75, 75, 158, 75, 75, 75, 75, 75, 75, 0, 0, 50, 0, 1066, 0, 0, 0, 0, 1066, 43, 0,
    /* 2807 */ 0, 0, 0, 0, 84, 84, 0, 0, 2106, 2106, 1066, 1066, 0, 1066, 1066, 1066, 1066, 1066, 1066, 1066, 1066,
    /* 2828 */ 1066, 1066, 43, 0, 0, 0, 0, 0, 4352, 0, 0, 0, 0, 0, 0, 0, 0, 122, 123, 0, 75, 52105, 3210, 2688, 52105,
    /* 2853 */ 3210, 0, 0, 147, 75, 75, 52118, 0, 0, 94, 94, 0, 0, 1365, 1878, 75, 88, 0, 75, 104, 0, 0, 0, 0, 0, 0, 0,
    /* 2880 */ 75, 88, 130, 122, 123, 0, 75, 52105, 3210, 2688, 52105, 3210, 145, 0, 147, 75, 75, 52118, 0, 122, 123, 0,
    /* 2902 */ 75, 52105, 3210, 0, 0, 0, 75, 75, 0, 109, 3190, 104, 52105, 3210, 0, 0, 0, 75, 75, 52118, 0, 0, 88, 0,
    /* 2926 */ 75, 104, 0, 107, 0, 109, 0, 140, 122, 0, 123, 0, 75, 75, 165, 75, 75, 75, 0, 0, 4352, 4352, 0, 4352,
    /* 2950 */ 4352, 0, 0, 4352, 0, 4352, 4352, 4352, 4352, 4352, 4352, 4352, 4352, 88, 0, 75, 104, 0, 0, 108, 109, 139,
    /* 2972 */ 0, 122, 0, 123, 0, 75, 75, 156, 157, 75, 75, 160, 161, 75, 52105, 3210, 0, 146, 0, 75, 75, 52118, 95, 0,
    /* 2996 */ 0, 0, 1365, 0, 75, 88, 0, 75, 104, 0, 0, 0, 109, 50, 0, 0, 9984, 0, 0, 0, 0, 11008, 0, 11008, 0, 11008,
    /* 3022 */ 11008, 11008, 11008, 11008, 11008, 11008, 11008, 2106, 2106, 0, 62, 0, 62, 62, 10050, 10050, 62, 10050,
    /* 3040 */ 10053, 10053, 10054, 10053, 10053, 10053, 10053, 10053, 10053, 10053, 10053, 0, 43, 0, 0, 0, 0, 0, 34816,
    /* 3059 */ 0, 0, 0, 0, 0, 47, 0, 0, 0, 0, 2106, 2106, 0, 0, 49, 0, 0, 0, 34816, 0, 0, 83, 0, 0, 44, 0, 0, 0, 0, 0,
    /* 3089 */ 1365, 1878, 75, 88, 49, 0, 43, 0, 0, 0, 0, 0, 34816, 0, 0, 83, 0, 7936, 10496, 50, 50, 52, 53, 0, 0, 0,
    /* 3115 */ 34816, 0, 0, 83, 1365, 0, 97, 0, 75, 104, 0, 0, 0, 0, 110, 0, 0, 97, 75, 0, 97, 97, 0, 0, 0, 34816, 81,
    /* 3142 */ 82, 83, 0, 82, 0, 122, 123, 0, 75, 0, 0, 0, 127, 0, 0, 2106, 2106, 0, 0, 1343, 0, 1343, 0, 43, 0, 0, 0,
    /* 3169 */ 0, 0, 34896, 0, 0, 83, 1365, 0, 0, 10240, 0, 0, 10323, 10329, 0, 0, 50, 50, 1536, 53, 0, 0, 0, 153, 75,
    /* 3194 */ 52118, 0, 0, 0, 122, 123, 0, 135, 52105, 3210, 2688, 0, 122, 123, 0, 136, 52105, 3210, 2688, 52105, 3210,
    /* 3215 */ 0, 0, 147, 148, 75, 52118, 155, 75, 75, 75, 75, 75, 75, 75, 75, 163, 75, 75, 75, 75, 75, 0, 0, 56, 56, 0,
    /* 3241 */ 0, 56, 56, 56, 52105, 3210, 0, 0, 0, 75, 149, 52118, 86, 0, 0, 0, 83, 89, 0, 0, 75, 0, 88, 0, 3190, 0,
    /* 3267 */ 129, 11008, 0, 43, 0, 0, 0, 0, 0, 75, 0, 88, 0, 3190, 128, 0, 75, 126, 0, 115, 3190, 0, 0, 96, 96, 1365,
    /* 3293 */ 0, 75, 88
  };

  private static final int[] EXPECTED =
  {
    /*   0 */ 122, 83, 89, 93, 97, 101, 105, 109, 113, 117, 121, 85, 179, 182, 149, 254, 126, 130, 134, 138, 227, 222,
    /*  22 */ 146, 141, 156, 172, 163, 229, 170, 176, 228, 159, 227, 189, 225, 190, 142, 197, 194, 194, 194, 205, 213,
    /*  43 */ 201, 219, 209, 233, 236, 240, 244, 247, 251, 216, 200, 259, 208, 166, 185, 152, 258, 259, 165, 259, 259,
    /*  64 */ 260, 264, 259, 259, 263, 259, 259, 262, 259, 261, 259, 259, 263, 261, 259, 263, 259, 259, 259, 10,
    /*  84 */ 536870914, 2, 2, 536870912, 0, 16388, 16384, 42, 34, -2147418110, 536870914, 1073741826, -2147450870, 960,
    /*  98 */ 960, 98314, 229418, 1610612754, 1073971242, 1073971258, -2011955094, 241706, -1741422486, -2009857942,
    /* 108 */ 1610842170, -1741422486, -1742753686, -2011189142, -1742752662, -1182814102, -645943190, -645943174,
    /* 116 */ -1610633110, -1073762198, -109072262, -536891286, -536891270, -20358, 8, 2048, 0, 0, 64, 0, 45058,
    /* 129 */ -2147483648, 131072, 132096, -1073741824, 8388608, 33554432, 2048, 2, 2, 536870912, 32770, 256, 576, 0, 0,
    /* 144 */ 131072, 8194, 132096, 2048, 2, 32770, 256, 960, 0, 3, 128, 128, 131072, 131072, 12290, 12290, 1024, 2048,
    /* 162 */ 512, 32768, 512, 0, 0, 3, 1, 0, 131072, 12290, 1024, 132096, 2048, 32768, 132096, 2048, 512, 0, 4, 4, 32,
    /* 183 */ -2147483648, 1073741824, 0, 1, 0, 128, 131072, 131072, 8194, 4098, 1024, 8194, 4098, 8194, 4098, 1024, 0,
    /* 200 */ 0, 4, 256, 0, 32, 8194, 8194, 0, 0, 8, 0, 0, 512, 32, 512, 0, 0, 32, 0, 0, 64, 0, 16, 64, 45058, 1024,
    /* 226 */ 2048, 0, 0, 131072, 131072, 131072, 131072, 3, 1, 280, 9, 192, 3, 192, 1216, 281, 1216, 195, 1219, 1219,
    /* 246 */ 1235, 1243, 1235, 1235, 1499, 1243, 1243, 1499, 0, 131072, 16, 1073741824, 3, 0, 0, 0, 0, 1, 2, 0, 0, 0
  };

  private static final String[] TOKEN =
  {
    "%ERROR",
    "Whitespace",
    "ProcessingInstructionContents",
    "NCName",
    "Context",
    "StringLiteral",
    "CharCode",
    "Char",
    "CharRange",
    "CharCodeRange",
    "'['",
    "URL",
    "'/*ws:explicit*/'",
    "'/*ws:definition*/'",
    "S",
    "DocComment",
    "EOF",
    "EquivalenceLookAhead",
    "'$'",
    "'&'",
    "'('",
    "')'",
    "'*'",
    "'**'",
    "'+'",
    "'++'",
    "'-'",
    "'.'",
    "'/'",
    "'::='",
    "'<<'",
    "'<?'",
    "'<?ENCORE?>'",
    "'<?TOKENS?>'",
    "'=='",
    "'>>'",
    "'?'",
    "'?>'",
    "'['",
    "'[^'",
    "'\\\\'",
    "']'",
    "'|'"
  };
}

// End
