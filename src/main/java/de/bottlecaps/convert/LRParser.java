// This file was generated on Sat Nov 14, 2026 08:40 (UTC+01) by REx v7.0 which is Copyright (c) 1979-2026 by Gunther Rademacher <grd@gmx.net>
// REx command line: -q -interface de.bottlecaps.convert.LRParser -glalr 1 -java -saxon -tree -main

package de.bottlecaps.convert;

import java.io.IOException;
import java.io.Writer;

import java.util.Arrays;
import net.sf.saxon.event.Builder;
import net.sf.saxon.expr.parser.Loc;
import net.sf.saxon.om.AttributeMap;
import net.sf.saxon.om.EmptyAttributeMap;
import net.sf.saxon.om.NamespaceMap;
import net.sf.saxon.s9api.Location;
import net.sf.saxon.om.NoNamespaceName;
import net.sf.saxon.trans.XPathException;
import net.sf.saxon.type.AnyType;
import net.sf.saxon.str.StringView;

public interface LRParser
{
  public void initialize(CharSequence input, BottomUpEventHandler eh);
  public void parse();
  public void reset();
  public String getErrorMessage(ParseException e);
  public String getTerminalName(int id);
  public String getNonterminalName(int id);

  public static class ParseException extends RuntimeException
  {
    private static final long serialVersionUID = 1L;
    private int begin, end, offending, expected, state;
    private boolean ambiguousInput;
    private ParseTreeBuilder ambiguityDescriptor;

    public ParseException(int b, int e, int s, int o, int x)
    {
      begin = b;
      end = e;
      state = s;
      offending = o;
      expected = x;
      ambiguousInput = false;
    }

    public ParseException(int b, int e, ParseTreeBuilder ambiguityDescriptor)
    {
      this(b, e, 1, -1, -1);
      ambiguousInput = true;
      this.ambiguityDescriptor = ambiguityDescriptor;
    }

    @Override
    public String getMessage()
    {
      return ambiguousInput
           ? "ambiguous input"
           : offending < 0
           ? "lexical analysis failed"
           : "syntax error";
    }

    public void serialize(EventHandler eventHandler)
    {
      ambiguityDescriptor.serialize(eventHandler);
    }

    public int getBegin() {return begin;}
    public int getEnd() {return end;}
    public int getState() {return state;}
    public int getOffending() {return offending;}
    public int getExpected() {return expected;}
    public boolean isAmbiguousInput() {return ambiguousInput;}
  }

  public interface EventHandler
  {
    public void reset(CharSequence string);
    public void startNonterminal(int id, int begin);
    public void endNonterminal(int id, int end);
    public void terminal(int id, int begin, int end);
    public void whitespace(int begin, int end);
  }

  public static class TopDownTreeBuilder implements EventHandler
  {
    private CharSequence input = null;
    private Nonterminal[] stack = new Nonterminal[64];
    private int top = -1;

    @Override
    public void reset(CharSequence input)
    {
      this.input = input;
      top = -1;
    }

    @Override
    public void startNonterminal(int id, int begin)
    {
      Nonterminal nonterminal = new Nonterminal(id, begin, begin, new Symbol[0]);
      if (top >= 0) addChild(nonterminal);
      if (++top >= stack.length) stack = Arrays.copyOf(stack, stack.length << 1);
      stack[top] = nonterminal;
    }

    @Override
    public void endNonterminal(int id, int end)
    {
      stack[top].end = end;
      if (top > 0) --top;
    }

    @Override
    public void terminal(int id, int begin, int end)
    {
      addChild(new Terminal(id, begin, end));
    }

    @Override
    public void whitespace(int begin, int end)
    {
    }

    private void addChild(Symbol s)
    {
      Nonterminal current = stack[top];
      current.children = Arrays.copyOf(current.children, current.children.length + 1);
      current.children[current.children.length - 1] = s;
    }

    public void serialize(EventHandler e)
    {
      e.reset(input);
      stack[0].send(e);
    }
  }

  public static abstract class Symbol
  {
    public int id;
    public int begin;
    public int end;

    protected Symbol(int id, int begin, int end)
    {
      this.id = id;
      this.begin = begin;
      this.end = end;
    }

    public abstract String getName(LRParser names);

    public abstract void send(EventHandler e);
  }

  public static class Terminal extends Symbol
  {
    public Terminal(int id, int begin, int end)
    {
      super(id, begin, end);
    }

    @Override
    public String getName(LRParser names)
    {
      return names.getTerminalName(id);
    }

    @Override
    public void send(EventHandler e)
    {
      e.terminal(id, begin, end);
    }
  }

  public static class Nonterminal extends Symbol
  {
    public Symbol[] children;

    public Nonterminal(int id, int begin, int end, Symbol[] children)
    {
      super(id, begin, end);
      this.children = children;
    }

    @Override
    public String getName(LRParser names)
    {
      return names.getNonterminalName(id);
    }

    @Override
    public void send(EventHandler e)
    {
      e.startNonterminal(id, begin);
      int pos = begin;
      for (Symbol c : children)
      {
        if (pos < c.begin) e.whitespace(pos, c.begin);
        c.send(e);
        pos = c.end;
      }
      if (pos < end) e.whitespace(pos, end);
      e.endNonterminal(id, end);
    }
  }

  public interface BottomUpEventHandler
  {
    public void reset(CharSequence string);
    public void nonterminal(int id, int begin, int end, int count);
    public void terminal(int id, int begin, int end);
  }

  public static class XmlSerializer implements EventHandler
  {
    private CharSequence input;
    private String delayedTag;
    private Writer out;
    private boolean indent;
    private boolean hasChildElement;
    private int depth;

    private LRParser names;

    public XmlSerializer(Writer w, boolean indent, LRParser names)
    {
      this.names = names;
      input = null;
      delayedTag = null;
      out = w;
      this.indent = indent;
    }

    @Override
    public void reset(CharSequence string)
    {
      writeOutput("<?xml version=\"1.0\" encoding=\"UTF-8\"?" + ">");
      input = string;
      delayedTag = null;
      hasChildElement = false;
      depth = 0;
    }

    @Override
    public void startNonterminal(int id, int begin)
    {
      startElement(names.getNonterminalName(id));
    }

    private void startElement(String name)
    {
      if (delayedTag != null)
      {
        writeOutput("<");
        writeOutput(delayedTag);
        writeOutput(">");
      }
      delayedTag = name;
      if (indent)
      {
        writeOutput("\n");
        for (int i = 0; i < depth; ++i)
        {
          writeOutput("  ");
        }
      }
      hasChildElement = false;
      ++depth;
    }

    @Override
    public void endNonterminal(int id, int end)
    {
      endElement(names.getNonterminalName(id));
    }

    private void endElement(String name)
    {
      --depth;
      if (delayedTag != null)
      {
        delayedTag = null;
        writeOutput("<");
        writeOutput(name);
        writeOutput("/>");
      }
      else
      {
        if (indent)
        {
          if (hasChildElement)
          {
            writeOutput("\n");
            for (int i = 0; i < depth; ++i)
            {
              writeOutput("  ");
            }
          }
        }
        writeOutput("</");
        writeOutput(name);
        writeOutput(">");
      }
      hasChildElement = true;
    }

    @Override
    public void terminal(int id, int begin, int end)
    {
      String name = names.getTerminalName(id);
      if (name.charAt(0) == '\'')
      {
        name = "TOKEN";
      }
      startElement(name);
      characters(begin, end);
      endElement(name);
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
        if (delayedTag != null)
        {
          writeOutput("<");
          writeOutput(delayedTag);
          writeOutput(">");
          delayedTag = null;
        }
        writeOutput(input.subSequence(begin, end)
                         .toString()
                         .replace("&", "&amp;")
                         .replace("<", "&lt;")
                         .replace(">", "&gt;"));
      }
    }

    public void writeOutput(String content)
    {
      try
      {
        out.write(content);
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }
  }

  public static class SaxonTreeBuilder implements EventHandler
  {
    private CharSequence input;
    private Builder builder;
    private AnyType anyType;

    private LRParser names;

    public SaxonTreeBuilder(Builder b, LRParser names)
    {
      this.names = names;
      input = null;
      builder = b;
      anyType = AnyType.getInstance();
    }

    @Override
    public void reset(CharSequence string)
    {
      input = string;
    }

    @Override
    public void startNonterminal(int id, int begin)
    {
      startElement(names.getNonterminalName(id));
    }

    private void startElement(String name)
    {
      try
      {
        builder.startElement(new NoNamespaceName(name), anyType, NO_ATTRIBUTES, NO_NAMESPACES, LOCATION, 0);
      }
      catch (XPathException e)
      {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void endNonterminal(int id, int end)
    {
      endElement();
    }

    private void endElement()
    {
      try
      {
        builder.endElement();
      }
      catch (XPathException e)
      {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void terminal(int id, int begin, int end)
    {
      String name = names.getTerminalName(id);
      if (name.charAt(0) == '\'')
      {
        name = "TOKEN";
      }
      startElement(name);
      characters(begin, end);
      endElement();
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
          builder.characters(StringView.of(input.subSequence(begin, end).toString()), LOCATION, 0);
        }
        catch (XPathException e)
        {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public static class ParseTreeBuilder implements BottomUpEventHandler
  {
    private CharSequence input;
    public Symbol[] stack = new Symbol[64];
    public int top = -1;

    @Override
    public void reset(CharSequence input)
    {
      this.input = input;
      top = -1;
    }

    @Override
    public void nonterminal(int id, int begin, int end, int count)
    {
      if (count > top + 1)
      {
        Symbol[] content = pop(top + 1);
        nonterminal(-4, begin, content.length == 0 ? end : content[0].begin, 0);
        for (Symbol symbol : content)
        {
          push(symbol);
        }
        count = top + 1;
      }
      push(new Nonterminal(id, begin, end, pop(count)));
    }

    @Override
    public void terminal(int id, int begin, int end)
    {
      push(new Terminal(id, begin, end));
    }

    public void serialize(EventHandler e)
    {
      e.reset(input);
      for (int i = 0; i <= top; ++i)
      {
        stack[i].send(e);
      }
    }

    public void push(Symbol s)
    {
      if (++top >= stack.length)
      {
        stack = Arrays.copyOf(stack, stack.length << 1);
      }
      stack[top] = s;
    }

    public Symbol[] pop(int count)
    {
      top -= count;
      return Arrays.copyOfRange(stack, top + 1, top + count + 1);
    }
  }

  public static final AttributeMap NO_ATTRIBUTES = EmptyAttributeMap.getInstance();
  public static final NamespaceMap NO_NAMESPACES = NamespaceMap.emptyMap();
  public static final Location LOCATION = Loc.NONE;
}

// End
