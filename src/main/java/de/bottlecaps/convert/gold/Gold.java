// This file was generated on Sat Feb 11, 2023 18:00 (UTC+01) by REx v5.57 which is Copyright (c) 1979-2023 by Gunther Rademacher <grd@gmx.net>
// REx command line: -backtrack -tree -a none -java -interface de.bottlecaps.convert.Parser -basex -saxon -name de.bottlecaps.convert.gold.Gold gold.ebnf

package de.bottlecaps.convert.gold;

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

public class Gold implements de.bottlecaps.convert.Parser
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
    Gold parser = new Gold(input, new SaxonTreeBuilder(builder));
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
    public StructuredQName getFunctionQName() {return new StructuredQName("p", "de/bottlecaps/convert/gold/Gold", functionName());}
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

  private static void buildError(Gold parser, ParseException pe, Builder builder) throws XPathException
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

  public static ANode parseGrammar(Str str) throws IOException
  {
    BaseXFunction baseXFunction = new BaseXFunction()
    {
      @Override
      public void execute(Gold p) {p.parse_Grammar();}
    };
    return baseXFunction.call(str);
  }

  public static abstract class BaseXFunction
  {
    protected abstract void execute(Gold p);

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
      Gold parser = new Gold();
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

  public Gold()
  {
  }

  public Gold(CharSequence string, EventHandler t)
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
    parse_Grammar();
  }

  public void parse_Grammar()
  {
    eventHandler.startNonterminal("Grammar", e0);
    lookahead1W(8);                 // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment
    whitespace();
    parse_nl_opt();
    whitespace();
    parse_Content();
    consume(9);                     // EOF
    eventHandler.endNonterminal("Grammar", e0);
  }

  private void parse_Content()
  {
    eventHandler.startNonterminal("Content", e0);
    for (;;)
    {
      whitespace();
      parse_Definition();
      if (l1 == 9)                  // EOF
      {
        break;
      }
    }
    eventHandler.endNonterminal("Content", e0);
  }

  private void parse_Definition()
  {
    eventHandler.startNonterminal("Definition", e0);
    switch (l1)
    {
    case 1:                         // ParameterName
      parse_Parameter();
      break;
    case 5:                         // SetName
      parse_Set_Decl();
      break;
    case 3:                         // Terminal
      parse_Terminal_Decl();
      break;
    default:
      parse_Rule_Decl();
    }
    eventHandler.endNonterminal("Definition", e0);
  }

  private void parse_nl_opt()
  {
    eventHandler.startNonterminal("nl_opt", e0);
    for (;;)
    {
      lookahead1W(14);              // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment | '+' | '-' | '::=' | '=' | '|'
      if (l1 != 7)                  // NewLine
      {
        break;
      }
      consume(7);                   // NewLine
    }
    eventHandler.endNonterminal("nl_opt", e0);
  }

  private void try_nl_opt()
  {
    for (;;)
    {
      lookahead1W(14);              // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment | '+' | '-' | '::=' | '=' | '|'
      if (l1 != 7)                  // NewLine
      {
        break;
      }
      consumeT(7);                  // NewLine
    }
  }

  private void parse_nl()
  {
    eventHandler.startNonterminal("nl", e0);
    for (;;)
    {
      consume(7);                   // NewLine
      lookahead1W(9);               // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment | EOF
      if (l1 != 7)                  // NewLine
      {
        break;
      }
    }
    eventHandler.endNonterminal("nl", e0);
  }

  private void parse_Parameter()
  {
    eventHandler.startNonterminal("Parameter", e0);
    consume(1);                     // ParameterName
    lookahead1W(2);                 // Whitespace_Ch | NewLine | Comment | '='
    whitespace();
    parse_nl_opt();
    consume(16);                    // '='
    lookahead1W(7);                 // ParameterName | Nonterminal | Terminal | SetLiteral | SetName | Whitespace_Ch |
                                    // Comment
    whitespace();
    parse_Parameter_Body();
    whitespace();
    parse_nl();
    eventHandler.endNonterminal("Parameter", e0);
  }

  private void parse_Parameter_Body()
  {
    eventHandler.startNonterminal("Parameter_Body", e0);
    parse_Parameter_Items();
    for (;;)
    {
      switch (l1)
      {
      case 7:                       // NewLine
        lookahead2W(11);            // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment | EOF | '|'
        switch (lk)
        {
        case 231:                   // NewLine NewLine
          lookahead3W(11);          // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment | EOF | '|'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 7399)               // NewLine NewLine NewLine
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
            try_nl_opt();
            consumeT(18);           // '|'
            lookahead1W(7);         // ParameterName | Nonterminal | Terminal | SetLiteral | SetName | Whitespace_Ch |
                                    // Comment
            try_Parameter_Items();
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
      if (lk != -1
       && lk != 18                  // '|'
       && lk != 583                 // NewLine '|'
       && lk != 18663)              // NewLine NewLine '|'
      {
        break;
      }
      whitespace();
      parse_nl_opt();
      consume(18);                  // '|'
      lookahead1W(7);               // ParameterName | Nonterminal | Terminal | SetLiteral | SetName | Whitespace_Ch |
                                    // Comment
      whitespace();
      parse_Parameter_Items();
    }
    eventHandler.endNonterminal("Parameter_Body", e0);
  }

  private void parse_Parameter_Items()
  {
    eventHandler.startNonterminal("Parameter_Items", e0);
    for (;;)
    {
      whitespace();
      parse_Parameter_Item();
      lookahead1W(10);              // ParameterName | Nonterminal | Terminal | SetLiteral | SetName | Whitespace_Ch |
                                    // NewLine | Comment | '|'
      if (l1 == 7                   // NewLine
       || l1 == 18)                 // '|'
      {
        break;
      }
    }
    eventHandler.endNonterminal("Parameter_Items", e0);
  }

  private void try_Parameter_Items()
  {
    for (;;)
    {
      try_Parameter_Item();
      lookahead1W(10);              // ParameterName | Nonterminal | Terminal | SetLiteral | SetName | Whitespace_Ch |
                                    // NewLine | Comment | '|'
      if (l1 == 7                   // NewLine
       || l1 == 18)                 // '|'
      {
        break;
      }
    }
  }

  private void parse_Parameter_Item()
  {
    eventHandler.startNonterminal("Parameter_Item", e0);
    switch (l1)
    {
    case 1:                         // ParameterName
      consume(1);                   // ParameterName
      break;
    case 3:                         // Terminal
      consume(3);                   // Terminal
      break;
    case 4:                         // SetLiteral
      consume(4);                   // SetLiteral
      break;
    case 5:                         // SetName
      consume(5);                   // SetName
      break;
    default:
      consume(2);                   // Nonterminal
    }
    eventHandler.endNonterminal("Parameter_Item", e0);
  }

  private void try_Parameter_Item()
  {
    switch (l1)
    {
    case 1:                         // ParameterName
      consumeT(1);                  // ParameterName
      break;
    case 3:                         // Terminal
      consumeT(3);                  // Terminal
      break;
    case 4:                         // SetLiteral
      consumeT(4);                  // SetLiteral
      break;
    case 5:                         // SetName
      consumeT(5);                  // SetName
      break;
    default:
      consumeT(2);                  // Nonterminal
    }
  }

  private void parse_Set_Decl()
  {
    eventHandler.startNonterminal("Set_Decl", e0);
    consume(5);                     // SetName
    lookahead1W(2);                 // Whitespace_Ch | NewLine | Comment | '='
    whitespace();
    parse_nl_opt();
    consume(16);                    // '='
    lookahead1W(0);                 // SetLiteral | SetName | Whitespace_Ch | Comment
    whitespace();
    parse_Set_Exp();
    whitespace();
    parse_nl();
    eventHandler.endNonterminal("Set_Decl", e0);
  }

  private void parse_Set_Exp()
  {
    eventHandler.startNonterminal("Set_Exp", e0);
    parse_Set_Item();
    for (;;)
    {
      lookahead1W(4);               // Whitespace_Ch | NewLine | Comment | '+' | '-'
      switch (l1)
      {
      case 7:                       // NewLine
        lookahead2W(13);            // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment | EOF | '+' | '-'
        switch (lk)
        {
        case 231:                   // NewLine NewLine
          lookahead3W(13);          // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment | EOF | '+' | '-'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 7399)               // NewLine NewLine NewLine
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
            try_nl_opt();
            switch (l1)
            {
            case 13:                // '+'
              consumeT(13);         // '+'
              break;
            default:
              consumeT(14);         // '-'
            }
            lookahead1W(0);         // SetLiteral | SetName | Whitespace_Ch | Comment
            try_Set_Item();
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
          memoize(1, e0, lk);
        }
      }
      if (lk != -1
       && lk != 13                  // '+'
       && lk != 14                  // '-'
       && lk != 423                 // NewLine '+'
       && lk != 455                 // NewLine '-'
       && lk != 13543               // NewLine NewLine '+'
       && lk != 14567)              // NewLine NewLine '-'
      {
        break;
      }
      whitespace();
      parse_nl_opt();
      switch (l1)
      {
      case 13:                      // '+'
        consume(13);                // '+'
        break;
      default:
        consume(14);                // '-'
      }
      lookahead1W(0);               // SetLiteral | SetName | Whitespace_Ch | Comment
      whitespace();
      parse_Set_Item();
    }
    eventHandler.endNonterminal("Set_Exp", e0);
  }

  private void parse_Set_Item()
  {
    eventHandler.startNonterminal("Set_Item", e0);
    switch (l1)
    {
    case 4:                         // SetLiteral
      consume(4);                   // SetLiteral
      break;
    default:
      consume(5);                   // SetName
    }
    eventHandler.endNonterminal("Set_Item", e0);
  }

  private void try_Set_Item()
  {
    switch (l1)
    {
    case 4:                         // SetLiteral
      consumeT(4);                  // SetLiteral
      break;
    default:
      consumeT(5);                  // SetName
    }
  }

  private void parse_Terminal_Decl()
  {
    eventHandler.startNonterminal("Terminal_Decl", e0);
    parse_Terminal_Name();
    whitespace();
    parse_nl_opt();
    consume(16);                    // '='
    lookahead1W(6);                 // Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '('
    whitespace();
    parse_Reg_Exp();
    whitespace();
    parse_nl();
    eventHandler.endNonterminal("Terminal_Decl", e0);
  }

  private void parse_Terminal_Name()
  {
    eventHandler.startNonterminal("Terminal_Name", e0);
    for (;;)
    {
      consume(3);                   // Terminal
      lookahead1W(3);               // Terminal | Whitespace_Ch | NewLine | Comment | '='
      if (l1 != 3)                  // Terminal
      {
        break;
      }
    }
    eventHandler.endNonterminal("Terminal_Name", e0);
  }

  private void parse_Reg_Exp()
  {
    eventHandler.startNonterminal("Reg_Exp", e0);
    parse_Reg_Exp_Seq();
    for (;;)
    {
      switch (l1)
      {
      case 7:                       // NewLine
        lookahead2W(11);            // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment | EOF | '|'
        switch (lk)
        {
        case 231:                   // NewLine NewLine
          lookahead3W(11);          // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment | EOF | '|'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 7399)               // NewLine NewLine NewLine
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
            try_nl_opt();
            consumeT(18);           // '|'
            lookahead1W(6);         // Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '('
            try_Reg_Exp_Seq();
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
          memoize(2, e0, lk);
        }
      }
      if (lk != -1
       && lk != 18                  // '|'
       && lk != 583                 // NewLine '|'
       && lk != 18663)              // NewLine NewLine '|'
      {
        break;
      }
      whitespace();
      parse_nl_opt();
      consume(18);                  // '|'
      lookahead1W(6);               // Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '('
      whitespace();
      parse_Reg_Exp_Seq();
    }
    eventHandler.endNonterminal("Reg_Exp", e0);
  }

  private void parse_Reg_Exp_Seq()
  {
    eventHandler.startNonterminal("Reg_Exp_Seq", e0);
    for (;;)
    {
      whitespace();
      parse_Reg_Exp_Item();
      lookahead1W(12);              // Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment | '(' | ')' |
                                    // '|'
      if (l1 == 7                   // NewLine
       || l1 == 11                  // ')'
       || l1 == 18)                 // '|'
      {
        break;
      }
    }
    eventHandler.endNonterminal("Reg_Exp_Seq", e0);
  }

  private void try_Reg_Exp_Seq()
  {
    for (;;)
    {
      try_Reg_Exp_Item();
      lookahead1W(12);              // Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment | '(' | ')' |
                                    // '|'
      if (l1 == 7                   // NewLine
       || l1 == 11                  // ')'
       || l1 == 18)                 // '|'
      {
        break;
      }
    }
  }

  private void parse_Reg_Exp_Item()
  {
    eventHandler.startNonterminal("Reg_Exp_Item", e0);
    switch (l1)
    {
    case 4:                         // SetLiteral
      consume(4);                   // SetLiteral
      lookahead1W(15);              // Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment | '(' | ')' |
                                    // '*' | '+' | '?' | '|'
      whitespace();
      parse_Kleene_Opt();
      break;
    case 5:                         // SetName
      consume(5);                   // SetName
      lookahead1W(15);              // Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment | '(' | ')' |
                                    // '*' | '+' | '?' | '|'
      whitespace();
      parse_Kleene_Opt();
      break;
    case 3:                         // Terminal
      consume(3);                   // Terminal
      lookahead1W(15);              // Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment | '(' | ')' |
                                    // '*' | '+' | '?' | '|'
      whitespace();
      parse_Kleene_Opt();
      break;
    default:
      consume(10);                  // '('
      lookahead1W(6);               // Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '('
      whitespace();
      parse_Reg_Exp_2();
      consume(11);                  // ')'
      lookahead1W(15);              // Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment | '(' | ')' |
                                    // '*' | '+' | '?' | '|'
      whitespace();
      parse_Kleene_Opt();
    }
    eventHandler.endNonterminal("Reg_Exp_Item", e0);
  }

  private void try_Reg_Exp_Item()
  {
    switch (l1)
    {
    case 4:                         // SetLiteral
      consumeT(4);                  // SetLiteral
      lookahead1W(15);              // Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment | '(' | ')' |
                                    // '*' | '+' | '?' | '|'
      try_Kleene_Opt();
      break;
    case 5:                         // SetName
      consumeT(5);                  // SetName
      lookahead1W(15);              // Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment | '(' | ')' |
                                    // '*' | '+' | '?' | '|'
      try_Kleene_Opt();
      break;
    case 3:                         // Terminal
      consumeT(3);                  // Terminal
      lookahead1W(15);              // Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment | '(' | ')' |
                                    // '*' | '+' | '?' | '|'
      try_Kleene_Opt();
      break;
    default:
      consumeT(10);                 // '('
      lookahead1W(6);               // Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '('
      try_Reg_Exp_2();
      consumeT(11);                 // ')'
      lookahead1W(15);              // Terminal | SetLiteral | SetName | Whitespace_Ch | NewLine | Comment | '(' | ')' |
                                    // '*' | '+' | '?' | '|'
      try_Kleene_Opt();
    }
  }

  private void parse_Reg_Exp_2()
  {
    eventHandler.startNonterminal("Reg_Exp_2", e0);
    parse_Reg_Exp_Seq();
    for (;;)
    {
      if (l1 != 18)                 // '|'
      {
        break;
      }
      consume(18);                  // '|'
      lookahead1W(6);               // Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '('
      whitespace();
      parse_Reg_Exp_Seq();
    }
    eventHandler.endNonterminal("Reg_Exp_2", e0);
  }

  private void try_Reg_Exp_2()
  {
    try_Reg_Exp_Seq();
    for (;;)
    {
      if (l1 != 18)                 // '|'
      {
        break;
      }
      consumeT(18);                 // '|'
      lookahead1W(6);               // Terminal | SetLiteral | SetName | Whitespace_Ch | Comment | '('
      try_Reg_Exp_Seq();
    }
  }

  private void parse_Kleene_Opt()
  {
    eventHandler.startNonterminal("Kleene_Opt", e0);
    switch (l1)
    {
    case 13:                        // '+'
      consume(13);                  // '+'
      break;
    case 17:                        // '?'
      consume(17);                  // '?'
      break;
    case 12:                        // '*'
      consume(12);                  // '*'
      break;
    default:
      break;
    }
    eventHandler.endNonterminal("Kleene_Opt", e0);
  }

  private void try_Kleene_Opt()
  {
    switch (l1)
    {
    case 13:                        // '+'
      consumeT(13);                 // '+'
      break;
    case 17:                        // '?'
      consumeT(17);                 // '?'
      break;
    case 12:                        // '*'
      consumeT(12);                 // '*'
      break;
    default:
      break;
    }
  }

  private void parse_Rule_Decl()
  {
    eventHandler.startNonterminal("Rule_Decl", e0);
    consume(2);                     // Nonterminal
    lookahead1W(1);                 // Whitespace_Ch | NewLine | Comment | '::='
    whitespace();
    parse_nl_opt();
    consume(15);                    // '::='
    lookahead1W(5);                 // Nonterminal | Terminal | Whitespace_Ch | NewLine | Comment | '|'
    whitespace();
    parse_Handles();
    whitespace();
    parse_nl();
    eventHandler.endNonterminal("Rule_Decl", e0);
  }

  private void parse_Handles()
  {
    eventHandler.startNonterminal("Handles", e0);
    parse_Handle();
    for (;;)
    {
      switch (l1)
      {
      case 7:                       // NewLine
        lookahead2W(11);            // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment | EOF | '|'
        switch (lk)
        {
        case 231:                   // NewLine NewLine
          lookahead3W(11);          // ParameterName | Nonterminal | Terminal | SetName | Whitespace_Ch | NewLine |
                                    // Comment | EOF | '|'
          break;
        }
        break;
      default:
        lk = l1;
      }
      if (lk == 7399)               // NewLine NewLine NewLine
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
            try_nl_opt();
            consumeT(18);           // '|'
            lookahead1W(5);         // Nonterminal | Terminal | Whitespace_Ch | NewLine | Comment | '|'
            try_Handle();
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
      if (lk != -1
       && lk != 18                  // '|'
       && lk != 583                 // NewLine '|'
       && lk != 18663)              // NewLine NewLine '|'
      {
        break;
      }
      whitespace();
      parse_nl_opt();
      consume(18);                  // '|'
      lookahead1W(5);               // Nonterminal | Terminal | Whitespace_Ch | NewLine | Comment | '|'
      whitespace();
      parse_Handle();
    }
    eventHandler.endNonterminal("Handles", e0);
  }

  private void parse_Handle()
  {
    eventHandler.startNonterminal("Handle", e0);
    for (;;)
    {
      lookahead1W(5);               // Nonterminal | Terminal | Whitespace_Ch | NewLine | Comment | '|'
      if (l1 != 2                   // Nonterminal
       && l1 != 3)                  // Terminal
      {
        break;
      }
      whitespace();
      parse_Symbol();
    }
    eventHandler.endNonterminal("Handle", e0);
  }

  private void try_Handle()
  {
    for (;;)
    {
      lookahead1W(5);               // Nonterminal | Terminal | Whitespace_Ch | NewLine | Comment | '|'
      if (l1 != 2                   // Nonterminal
       && l1 != 3)                  // Terminal
      {
        break;
      }
      try_Symbol();
    }
  }

  private void parse_Symbol()
  {
    eventHandler.startNonterminal("Symbol", e0);
    switch (l1)
    {
    case 3:                         // Terminal
      consume(3);                   // Terminal
      break;
    default:
      consume(2);                   // Nonterminal
    }
    eventHandler.endNonterminal("Symbol", e0);
  }

  private void try_Symbol()
  {
    switch (l1)
    {
    case 3:                         // Terminal
      consumeT(3);                  // Terminal
      break;
    default:
      consumeT(2);                  // Nonterminal
    }
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
      if (code != 6                 // Whitespace_Ch
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
    lk = (l2 << 5) | l1;
  }

  private void lookahead3W(int tokenSetId)
  {
    if (l3 == 0)
    {
      l3 = matchW(tokenSetId);
      b3 = begin;
      e3 = end;
    }
    lk |= l3 << 10;
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
    memo.put((e << 2) + i, v);
  }

  private int memoized(int i, int e)
  {
    Integer v = memo.get((e << 2) + i);
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
        int c1 = c0 >> 4;
        charclass = MAP1[(c0 & 15) + MAP1[(c1 & 63) + MAP1[c1 >> 6]]];
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

    if (end > size) end = size;
    return (result & 31) - 1;
  }

  private static String[] getTokenSet(int tokenSetId)
  {
    java.util.ArrayList<String> expected = new java.util.ArrayList<>();
    int s = tokenSetId < 0 ? - tokenSetId : INITIAL[tokenSetId] & 63;
    for (int i = 0; i < 19; i += 32)
    {
      int j = i;
      int i0 = (i >> 5) * 36 + s - 1;
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
    /*   0 */ 28, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 7,
    /*  35 */ 8, 8, 8, 8, 9, 10, 11, 12, 13, 8, 14, 15, 8, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 16, 8, 17, 18, 19,
    /*  63 */ 20, 8, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
    /*  90 */ 15, 21, 8, 22, 8, 15, 8, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
    /* 117 */ 15, 15, 15, 15, 15, 15, 23, 24, 25, 8, 8
  };

  private static final int[] MAP1 =
  {
    /*   0 */ 54, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65,
    /*  26 */ 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65,
    /*  52 */ 65, 65, 129, 143, 159, 195, 174, 179, 174, 211, 228, 228, 227, 228, 228, 228, 228, 228, 228, 228, 228,
    /*  73 */ 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228,
    /*  94 */ 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228,
    /* 115 */ 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 228, 28, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3,
    /* 141 */ 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 7, 8, 8, 8, 8, 9, 10, 11, 12, 13, 8, 14, 15,
    /* 174 */ 8, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 21, 8, 22, 8, 15, 15, 15, 15, 15, 15, 15,
    /* 201 */ 15, 15, 15, 15, 16, 8, 17, 18, 19, 20, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 23, 24, 25, 8, 8, 27,
    /* 228 */ 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26
  };

  private static final int[] MAP2 =
  {
    /* 0 */ 57344, 65536, 65533, 1114111, 26, 26
  };

  private static final int[] INITIAL =
  {
    /*  0 */ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
  };

  private static final int[] TRANSITION =
  {
    /*   0 */ 240, 240, 240, 240, 240, 240, 240, 240, 232, 232, 249, 620, 464, 240, 240, 240, 259, 278, 571, 334, 464,
    /*  21 */ 240, 240, 240, 232, 232, 239, 240, 240, 240, 240, 240, 286, 300, 240, 334, 464, 240, 240, 240, 232, 232,
    /*  42 */ 308, 346, 361, 240, 240, 240, 373, 373, 381, 491, 405, 240, 240, 240, 241, 398, 381, 315, 567, 240, 240,
    /*  63 */ 240, 240, 240, 381, 491, 498, 240, 240, 240, 596, 417, 425, 322, 464, 240, 240, 240, 624, 626, 381, 491,
    /*  84 */ 498, 240, 240, 240, 240, 502, 381, 491, 498, 240, 240, 240, 240, 251, 444, 491, 432, 240, 240, 240, 436,
    /* 105 */ 365, 381, 491, 498, 240, 240, 240, 265, 270, 476, 346, 361, 240, 240, 240, 530, 270, 476, 346, 361, 240,
    /* 126 */ 240, 240, 388, 606, 484, 491, 498, 240, 240, 240, 338, 510, 381, 491, 498, 240, 240, 240, 329, 468, 381,
    /* 147 */ 517, 498, 240, 240, 240, 240, 240, 381, 491, 525, 240, 240, 240, 240, 390, 381, 491, 498, 240, 240, 240,
    /* 168 */ 538, 292, 546, 451, 498, 240, 240, 240, 240, 240, 546, 560, 498, 240, 240, 240, 579, 585, 593, 458, 498,
    /* 189 */ 240, 240, 240, 409, 353, 381, 491, 498, 240, 240, 240, 240, 240, 593, 553, 498, 240, 240, 240, 240, 240,
    /* 210 */ 604, 620, 464, 240, 240, 240, 232, 232, 308, 491, 498, 240, 240, 240, 240, 614, 240, 240, 240, 240, 240,
    /* 231 */ 240, 465, 465, 465, 465, 465, 465, 465, 465, 0, 0, 0, 0, 0, 0, 0, 0, 26, 465, 603, 0, 0, 0, 0, 0, 0, 0,
    /* 258 */ 832, 0, 512, 512, 512, 512, 512, 0, 0, 0, 280, 960, 280, 280, 280, 280, 280, 280, 280, 280, 512, 512, 512,
    /* 281 */ 512, 512, 512, 512, 512, 0, 533, 533, 533, 533, 533, 0, 0, 19, 0, 19, 0, 0, 19, 533, 533, 533, 533, 533,
    /* 305 */ 533, 533, 533, 465, 603, 29, 31, 0, 0, 23, 0, 0, 603, 604, 29, 30, 31, 0, 0, 603, 604, 30, 29, 31, 0, 0,
    /* 331 */ 1088, 1088, 0, 0, 0, 0, 35, 0, 0, 0, 0, 0, 25, 0, 25, 33, 34, 603, 604, 29, 30, 31, 0, 0, 1216, 1216,
    /* 357 */ 1216, 0, 1216, 1216, 33, 34, 35, 35, 0, 0, 0, 0, 0, 896, 896, 896, 594, 594, 594, 594, 594, 594, 594, 594,
    /* 381 */ 0, 603, 29, 31, 0, 0, 23, 0, 22, 0, 0, 0, 0, 0, 0, 0, 1152, 26, 26, 26, 26, 0, 26, 26, 0, 34, 35, 611, 0,
    /* 410 */ 0, 0, 0, 0, 1216, 0, 0, 23, 23, 23, 23, 23, 23, 23, 23, 0, 603, 30, 31, 0, 0, 256, 0, 34, 36, 36, 0, 0, 0,
    /* 439 */ 0, 896, 0, 0, 0, 0, 604, 29, 31, 0, 0, 23, 0, 34, 603, 604, 0, 30, 31, 0, 34, 603, 604, 29, 30, 0, 0, 35,
    /* 467 */ 35, 0, 0, 0, 0, 0, 0, 1088, 0, 0, 603, 29, 31, 0, 0, 23, 280, 0, 603, 29, 31, 0, 32, 23, 0, 34, 603, 604,
    /* 495 */ 29, 30, 31, 0, 34, 35, 35, 0, 0, 0, 0, 768, 0, 0, 768, 25, 25, 25, 25, 0, 25, 25, 0, 34, 603, 604, 29, 30,
    /* 523 */ 31, 1024, 192, 34, 35, 35, 0, 0, 0, 0, 280, 0, 280, 280, 280, 19, 0, 0, 0, 0, 0, 19, 19, 0, 603, 0, 31, 0,
    /* 551 */ 0, 23, 0, 34, 603, 604, 29, 30, 384, 0, 34, 603, 604, 320, 30, 31, 0, 128, 35, 35, 0, 0, 0, 0, 512, 0, 0,
    /* 578 */ 0, 20, 0, 0, 0, 0, 0, 20, 20, 20, 20, 20, 20, 20, 20, 0, 603, 29, 0, 0, 0, 23, 0, 23, 23, 23, 0, 603, 0,
    /* 607 */ 0, 0, 0, 0, 0, 22, 0, 0, 640, 0, 640, 0, 640, 0, 0, 603, 604, 0, 0, 0, 0, 0, 0, 704, 0, 0, 704
  };

  private static final int[] EXPECTED =
  {
    /*  0 */ 368, 33216, 65984, 65992, 25024, 262604, 1400, 382, 494, 1006, 262654, 263150, 265720, 9198, 369134,
    /* 15 */ 409080, 64, 256, 16, 32, 128, 32768, 8, 8, 4, 2, 256, 256, 16, 16, 32, 32768, 4, 2, 256, 256
  };

  private static final String[] TOKEN =
  {
    "(0)",
    "ParameterName",
    "Nonterminal",
    "Terminal",
    "SetLiteral",
    "SetName",
    "Whitespace_Ch",
    "NewLine",
    "Comment",
    "EOF",
    "'('",
    "')'",
    "'*'",
    "'+'",
    "'-'",
    "'::='",
    "'='",
    "'?'",
    "'|'"
  };
}

// End
