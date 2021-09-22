package de.bottlecaps.railroad.convert;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import de.bottlecaps.railroad.convert.LRParser.ParseTreeBuilder;
import de.bottlecaps.railroad.convert.abnf.Abnf;
import de.bottlecaps.railroad.convert.antlr_3.Antlr_3;
import de.bottlecaps.railroad.convert.antlr_4.Antlr_4;
import de.bottlecaps.railroad.convert.bison.Bison;
import de.bottlecaps.railroad.convert.gold.Gold;
import de.bottlecaps.railroad.convert.instaparse.Instaparse;
import de.bottlecaps.railroad.convert.javacc.Javacc;
import de.bottlecaps.railroad.convert.jison.Jison;
import de.bottlecaps.railroad.convert.pegjs.Pegjs;
import de.bottlecaps.railroad.convert.phythia.Phythia;
import de.bottlecaps.railroad.convert.pss.Pss;
import de.bottlecaps.railroad.convert.rex_5_9.REx_5_9;
import de.bottlecaps.railroad.convert.w3c.W3c;
import de.bottlecaps.railroad.convert.xtext.Xtext;
import de.bottlecaps.railroad.core.ResourceModuleUriResolver;
import net.sf.saxon.jaxp.SaxonTransformerFactory;
import net.sf.saxon.s9api.DOMDestination;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XQueryCompiler;
import net.sf.saxon.s9api.XQueryEvaluator;
import net.sf.saxon.s9api.XQueryExecutable;
import net.sf.saxon.s9api.XdmAtomicValue;

public class Convert
{
  enum Target
  {
    JAVA,
    XQUERY,
    XSLT
  };

  public static final String VERSION = "v0.54";
  public static final String REVISION = "2082";
  public static final String DATE = "Sep 22";
  public static final String YEAR = "2021";
  public static final String URL = "https://bottlecaps.de/convert/";

  public static void main(String args[]) throws SaxonApiException, Exception
  {
    int exitCode = 0;

    if (args.length == 0)
    {
      String resource = Convert.class.getResource("/" + Convert.class.getName().replace('.',  '/') + ".class").toString();
      final String file = resource.startsWith("jar:fatjar:")
        ? "convert.war"
        : Convert.class.getName();
      usage(System.err, file);
    }
    else try
    {
      Target target = Target.JAVA;
      String notation = GRAMMAR_AUTO;
      boolean toXML = false;
      boolean verbose = false;
      boolean inline = true;
      boolean keep = true;
      boolean noTimestamp = false;
      String factoring = "full-left";
      String recursionRemoval = "full";

      for (int i = 0; i < args.length; ++i)
      {
        String arg = args[i];

        if (arg.equals("-v"))
        {
          verbose = true;
        }
        else if (arg.equals("-xml"))
        {
          toXML = true;
        }
        else if (arg.equals("-xquery"))
        {
          target = Target.XQUERY;
        }
        else if (arg.equals("-xslt"))
        {
          target = Target.XSLT;
        }
        else if (arg.equals("-java"))
        {
          target = Target.JAVA;
        }
        else if (arg.equals("-notimestamp"))
        {
          noTimestamp = true;
        }
        else if (arg.equals("-f") && i + 1 < args.length)
        {
          factoring = args[++i];
        }
        else if (arg.equals("-r") && i + 1 < args.length)
        {
          recursionRemoval = args[++i];
        }
        else if (arg.equals("-noinline"))
        {
          inline = false;
        }
        else if (arg.equals("-noepsilon"))
        {
          keep = false;
        }
        else if (arg.startsWith("-"))
        {
          notation = null;
          for (String n : notations) {
            if (n.equals(arg.substring(1))) {
              notation = arg.substring(1);
              break;
            }
          }
          if (notation == null) {
            System.err.println("unsupported option: " + arg);
            System.exit(1);
          }
        }
        else
        {
          String ebnfGrammar = convert(notation, read(arg), localTzOffset(), toXML, recursionRemoval, factoring, inline, keep, target, noTimestamp, verbose);
          System.out.print(ebnfGrammar);
        }
      }
    }
    catch (Exception e)
    {
      exitCode = 1;
      System.err.print("Exception ");
      e.printStackTrace(System.err);
    }
    System.exit(exitCode);
  }

  private static String read(String input) throws Exception
  {
    if (input.startsWith("{") && input.endsWith("}"))
    {
      return input.substring(1, input.length() - 1);
    }
    else
    {
      byte buffer[] = new byte[(int) new java.io.File(input).length()];
      FileInputStream fis = new java.io.FileInputStream(input);
      fis.read(buffer);
      fis.close();
      String content = new String(buffer, System.getProperty("file.encoding"));
      if (content.length() > 0 && content.charAt(0) == '\uFEFF')
      {
        content = content.substring(1);
      }
      return content.replaceAll("\r+\n", "\n");
    }
  }

  public static final String GRAMMAR_AUTO       = null;
  public static final String GRAMMAR_ABNF       = "abnf";
  public static final String GRAMMAR_ANTLR_3    = "antlr_3";
  public static final String GRAMMAR_ANTLR_4    = "antlr_4";
  public static final String GRAMMAR_BISON      = "bison";
  public static final String GRAMMAR_GOLD       = "gold";
  public static final String GRAMMAR_JAVACC     = "javacc";
  public static final String GRAMMAR_JISON      = "jison";
  public static final String GRAMMAR_PEGJS      = "pegjs";
  public static final String GRAMMAR_PSS        = "pss";
  public static final String GRAMMAR_PHYTHIA    = "phythia";
  public static final String GRAMMAR_REX_5_9    = "rex_5_9";
  public static final String GRAMMAR_W3C        = "w3c";
  public static final String GRAMMAR_XTEXT      = "xtext";
  public static final String GRAMMAR_INSTAPARSE = "instaparse";

  private static String notations[] = new String[]{
    GRAMMAR_W3C,
    GRAMMAR_ABNF,
    GRAMMAR_ANTLR_3,
    GRAMMAR_ANTLR_4,
    GRAMMAR_BISON,
    GRAMMAR_GOLD,
    GRAMMAR_JAVACC,
    GRAMMAR_JISON,
    GRAMMAR_INSTAPARSE,
    GRAMMAR_PEGJS,
    GRAMMAR_PSS,
    GRAMMAR_PHYTHIA,
    GRAMMAR_REX_5_9,
    GRAMMAR_XTEXT,
  };

  private static String startSymbols[] = new String[]{
    "Grammar",
    "rulelist",
    "grammar_",
    "grammarSpec",
    "input",
    "Grammar",
    "javacc_input",
    "jison",
    "rules",
    "Grammar",
    "Grammar",
    "Grammar",
    "Grammar",
    "Grammar",
  };

  private static final IgnoringEventHandler ignoringEventHandler = new IgnoringEventHandler();

  private static Parser llParsers[] = new Parser[]{
    new W3c("", ignoringEventHandler),
    new Abnf("", ignoringEventHandler),
    new Antlr_3("", ignoringEventHandler),
    new Antlr_4("", ignoringEventHandler),
    new Bison("", ignoringEventHandler),
    new Gold("", ignoringEventHandler),
    new Javacc("", ignoringEventHandler),
    new Jison("", ignoringEventHandler),
    null,
    new Pegjs("", ignoringEventHandler),
    new Pss("", ignoringEventHandler),
    new Phythia("", ignoringEventHandler),
    new REx_5_9("", ignoringEventHandler),
    new Xtext("", ignoringEventHandler),
  };

  private static LRParser lrParsers[] = new LRParser[]{
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    new Instaparse("", ignoringEventHandler),
    null,
    null,
    null,
    null,
    null,
  };

  public static String convert(String notation,
                               String grammar,
                               int tzOffset,
                               boolean toXML,
                               String recursionRemoval,
                               String factoring,
                               boolean inline,
                               boolean keep,
                               Target target,
                               boolean noTimestamp,
                               boolean verbose) throws Exception
  {
    XQueryExecutable executable;
    XQueryEvaluator evaluator;
    Processor processor = new Processor(false);
    XQueryCompiler compiler = processor.newXQueryCompiler();
    compiler.setModuleURIResolver(ResourceModuleUriResolver.instance);

    String sensedNotation = null;
    Element parseTree = null;
    int errorLocation = -1;
    ErrorLog errorMessages = new ErrorLog();

    DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    for (int i = 0; i < notations.length; ++i)
    {
      if (notation == null || notation.equals(notations[i]))
      {
        String module = notations[i];
        if (verbose)
        {
          System.err.println("trying " + module + " using " + target.toString() + "-coded parser");
        }

        if (target == Target.XQUERY)
        {
          String startSymbol = startSymbols[i];

          executable = compiler.compile(
            "import module namespace p=\"de/bottlecaps/railroad/convert/xq/" + module + "/" + module + ".xquery\";\n" +
            "declare variable $grammar external;\n" +
            "p:parse-" + startSymbol + "($grammar)");

          evaluator = executable.load();
          evaluator.setExternalVariable(new QName("grammar"), new XdmAtomicValue(grammar));
          Document document = documentBuilder.newDocument();
          processor.writeXdmValue(evaluator.evaluate(), new DOMDestination(document));
          parseTree = document.getDocumentElement();

          if (parseTree.getNodeName().equals(startSymbol))
          {
            sensedNotation = module;
            errorLocation = -1;
            errorMessages = null;
            break;
          }

          int e = Integer.parseInt(parseTree.getAttribute("e"));
          errorMessages.log(e, module, parseTree.getTextContent());

          if (e > errorLocation)
          {
            sensedNotation = module;
            errorLocation = e;
          }
        }
        else if (target == Target.XSLT)
        {
          String startSymbol = startSymbols[i];

          String xslt =
            "<?xml version=\"1.0\"?>"+
            "<xsl:stylesheet version=\"2.0\"\n" +
            "                xmlns:p=\"de/bottlecaps/railroad/convert/xq/" + module + "/" + module + "\"\n" +
            "                xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n" +
            "  <xsl:include href=\"de/bottlecaps/railroad/convert/xq/" + module + "/" + module + ".xslt\"/>" +
            "  <xsl:param name=\"grammar\"/>" +
            "  <xsl:template match=\"/\">\n" +
            "    <xsl:sequence select=\"p:parse-" + startSymbol + "($grammar)\"/>\n" +
            "  </xsl:template>\n" +
            "</xsl:stylesheet>";

          StreamSource stylesheet = new StreamSource(new StringReader(xslt));
          TransformerFactory transformerFactory = TransformerFactory.newInstance(SaxonTransformerFactory.class.getName(), Convert.class.getClassLoader());
          transformerFactory.setURIResolver(ResourceModuleUriResolver.instance);
          Transformer transformer = transformerFactory.newTransformer(stylesheet);
          transformer.setURIResolver(ResourceModuleUriResolver.instance);

          Document document = documentBuilder.newDocument();
          transformer.setParameter("grammar", grammar);
          transformer.transform(new StreamSource(new StringReader("<input/>")), new DOMResult(document));

          parseTree = document.getDocumentElement();

          if (parseTree.getNodeName().equals(startSymbol))
          {
            sensedNotation = module;
            errorLocation = -1;
            errorMessages = null;
            break;
          }

          int e = Integer.parseInt(parseTree.getAttribute("e"));
          errorMessages.log(e, module, parseTree.getTextContent());

          if (e > errorLocation)
          {
            sensedNotation = module;
            errorLocation = e;
          }
        }
        else
        {
          Writer writer = new StringWriter();
          if (llParsers[i] != null)
          {
            Parser parser = llParsers[i];
            parser.initialize(grammar, new Parser.XmlSerializer(writer, false));
            try
            {
              parser.parse();
            }
            catch (Parser.ParseException pe)
            {
              int e = pe.getEnd();
              errorMessages.log(e, module, parser.getErrorMessage(pe));

              if (e > errorLocation)
              {
                sensedNotation = module;
                errorLocation = e;
              }
              continue;
            }
          }
          else
          {
            LRParser parser = lrParsers[i];
            ParseTreeBuilder treeBuilder = new ParseTreeBuilder();
            parser.initialize(grammar, treeBuilder);
            try
            {
              parser.parse();
            }
            catch (LRParser.ParseException pe)
            {
              int e = pe.getEnd();
              errorMessages.log(e, module, parser.getErrorMessage(pe));

              if (e > errorLocation)
              {
                sensedNotation = module;
                errorLocation = e;
              }
              continue;
            }
            treeBuilder.serialize(new LRParser.XmlSerializer(writer, false));
          }
          writer.flush();
          Reader reader = new StringReader(writer.toString());
          InputSource inputSource = new InputSource(reader);
          Document document = documentBuilder.parse(inputSource);
          parseTree = document.getDocumentElement();
          sensedNotation = module;
          errorLocation = -1;
          errorMessages = null;
          break;
        }
      }
    }

    if (errorLocation >= 0)
    {
      if (! grammar.startsWith("<"))
      {
        throw new ConvertException(errorMessages);
      }
      else
      {
        Document document = documentBuilder.parse(new InputSource(new StringReader(grammar)));
        parseTree = document.getDocumentElement();
        sensedNotation = null;
        errorLocation = -1;
        errorMessages = null;
      }
    }

    String query;
    String copyright = VERSION + " which is Copyright (c) 2011-" + YEAR + " by Gunther Rademacher <grd@gmx.net>";

    String toW3c = sensedNotation == null
        ? "$parse-tree"
        : "c:" + sensedNotation + "-to-w3c($parse-tree)";
    String transform = GRAMMAR_REX_5_9.equals(sensedNotation)
        ? toW3c
        : "t:transform(" + toW3c + ", \"" + xqueryStringEscape(recursionRemoval) + "\", \"" + xqueryStringEscape(factoring) + "\", " + inline + "(), " + keep + "())\n";
    String render = GRAMMAR_REX_5_9.equals(sensedNotation)
        ? "string"
        : "b:render";
    if (sensedNotation == null)
      sensedNotation = "w3c";
    if (toXML)
    {
      query =
        "import module namespace c=\"de/bottlecaps/railroad/convert/xq/" + sensedNotation + "/" + sensedNotation + "-to-w3c.xq\";\n" +
        "import module namespace e=\"de/bottlecaps/railroad/xq/html-to-ebnf.xq\";\n" +
        "import module namespace x=\"de/bottlecaps/railroad/convert/xq/to-w3c.xq\";\n" +
        "import module namespace t=\"de/bottlecaps/railroad/xq/transform-ast.xq\";\n" +
        "declare namespace g=\"http://www.w3.org/2001/03/XPath/grammar\";\n" +
        "declare variable $parse-tree external;\n" +
        "document\n" +
        "{\n" +
        (
          noTimestamp
            ? ""
            : ("  comment {concat(\" converted on \", e:timestamp(" + tzOffset + "), \" by " + sensedNotation + "-to-w3c " + copyright + " \")},\n")
        ) +
        "  \"&#xA;\"," +
        "  " + transform +
        "}";
    }
    else
    {
      query =
        "import module namespace c=\"de/bottlecaps/railroad/convert/xq/" + sensedNotation + "/" + sensedNotation + "-to-w3c.xq\";\n" +
        "import module namespace e=\"de/bottlecaps/railroad/xq/html-to-ebnf.xq\";\n" +
        "import module namespace b=\"de/bottlecaps/railroad/xq/ast-to-ebnf.xq\";\n" +
        "import module namespace x=\"de/bottlecaps/railroad/convert/xq/to-w3c.xq\";\n" +
        "import module namespace t=\"de/bottlecaps/railroad/xq/transform-ast.xq\";\n" +
        "declare namespace g=\"http://www.w3.org/2001/03/XPath/grammar\";\n" +
        "declare variable $parse-tree external;\n" +
        "  concat\n" +
        "  (\n" +
        (
          noTimestamp
            ? "    \"\","
            : ("    \"/* converted on \", e:timestamp(" + tzOffset + "), \" by " + sensedNotation + "-to-w3c " + copyright + " */&#xA;&#xA;\",")
        ) +
        "    let $ast := " + transform +
        "    return " + render + "($ast)\n" +
        "  )";
    }

    try
    {
      executable = compiler.compile(query);
      evaluator = executable.load();
      evaluator.setExternalVariable(new QName("parse-tree"), processor.newDocumentBuilder().wrap(parseTree));
      return evaluator.evaluate().toString();
    }
    catch (Exception e)
    {
      throw new ConvertException("grammar was parsed OK, but conversion failed\n" + e.getMessage(), e);
    }
  }

  public static class ConvertException extends RuntimeException
  {
    private static final long serialVersionUID = 1L;
    private ErrorLog errorLog = null;

    public ConvertException(String message) {super(message);}

    public ConvertException(String message, Exception e) {super(message, e);}

    public ConvertException(ErrorLog e)
    {
      super(e.firstEntry().getValue().message);
      errorLog = e;
    }

    public ErrorLog getErrorLog() {return errorLog;}
  }

  public static String xqueryStringEscape(String s)
  {
    return s.replaceAll("&", "&amp;")
            .replaceAll("\"", "&quot;");
  }

  public static int localTzOffset()
  {
    Calendar c = Calendar.getInstance();
    return - (c.get(Calendar.ZONE_OFFSET) + c.get(Calendar.DST_OFFSET)) / (60 * 1000);
  }

  public static class ErrorLog extends TreeMap<Integer, ErrorMessage>
  {
    private static final long serialVersionUID = 1L;

    public void log(int position, String converter, String message)
    {
      int key = - position * notations.length + Arrays.binarySearch(notations, converter);
      put(key, new ErrorMessage(converter, message));
    }
  }

  public static class ErrorMessage
  {
    String converter;
    String message;

    ErrorMessage(String c, String m)
    {
      converter = c;
      message = m;
    }
  };

  private static final class IgnoringEventHandler implements Parser.EventHandler, LRParser.BottomUpEventHandler
  {
    @Override public void reset(CharSequence string) {}
    @Override public void startNonterminal(String name, int begin) {}
    @Override public void endNonterminal(String name, int end) {}
    @Override public void terminal(String name, int begin, int end) {}
    @Override public void whitespace(int begin, int end) {}
    @Override public void nonterminal(String name, int begin, int end, int count) {}
  }

  public static void usage(PrintStream out, final String file) {
    final String jar = file.endsWith(".war") ? "-jar " : "";
    out.println("Convert - Grammar Converter");
    out.println();
    out.println("converts foreign grammars to W3C-style EBNF as suitable for REx and Railroad");
    out.println();
    out.println("  version " + VERSION);
    out.println("  released " + DATE + ", " + YEAR);
    out.println("  from " + URL);
    out.println();
    out.println("Usage: java " + jar + file + " [-xml|-f FACTORING|-r KIND|-noinline|-noepsilon|-v]... GRAMMAR...");
    out.println();
    out.println("  -xml             create output grammar in XML, rather than EBNF");
    out.println("  -f FACTORING     apply factoring (any of \"full-left\" (default), \"left-only\", \"full-right\", \"right-only\", or \"none\")");
    out.println("  -r KIND          remove direct recursion, where KIND is any of \"full\" (default), \"left\", \"right\", or \"none\"");
    out.println("  -noinline        do not inline nonterminals that derive to a single string literal");
    out.println("  -noepsilon       do not keep nonterminal referemces, that derive to epsilon only");
    out.println("  -v               verbose output");
    out.println();
    out.println("  GRAMMAR          path of foreign grammar");

    if (! jar.isEmpty())
    {
      out.println();
      out.println(file + " is an executable war file. It can be run with \"java -jar\" as shown");
      out.println("above, but it can also be deployed in servlet containers like Tomcat or Jetty.");
    }
  }

}
