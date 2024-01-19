package de.bottlecaps.convert;

import static de.bottlecaps.xml.XQueryProcessor.defaultXQueryProcessor;
import static de.bottlecaps.xml.XsltProcessor.defaultXsltProcessor;
import static java.util.function.Function.identity;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.bottlecaps.convert.LRParser.ParseTreeBuilder;
import de.bottlecaps.convert.abnf.Abnf;
import de.bottlecaps.convert.antlr_3.Antlr_3;
import de.bottlecaps.convert.antlr_4.Antlr_4;
import de.bottlecaps.convert.bison.Bison;
import de.bottlecaps.convert.gold.Gold;
import de.bottlecaps.convert.instaparse.Instaparse;
import de.bottlecaps.convert.ixml.IXML;
import de.bottlecaps.convert.javacc.Javacc;
import de.bottlecaps.convert.jison.Jison;
import de.bottlecaps.convert.pegjs.Pegjs;
import de.bottlecaps.convert.phythia.Phythia;
import de.bottlecaps.convert.pss.Pss;
import de.bottlecaps.convert.rex_5_9.REx_5_9;
import de.bottlecaps.convert.w3c.W3c;
import de.bottlecaps.convert.xtext.Xtext;
import de.bottlecaps.railroad.core.Download;
import de.bottlecaps.xml.XQueryProcessor.Result;

public class Convert
{
  enum ParserImplementation
  {
    JAVA,
    XQUERY,
    XSLT
  };

  public static final String CONVERT_URL = "https://www.bottlecaps.de/" + ConvertVersion.PROJECT_NAME;

  public static void main(String args[]) throws Exception
  {
    int exitCode = 0;

    try
    {
      ParserImplementation parserImplementation = ParserImplementation.JAVA;
      String notation = GRAMMAR_AUTO;
      boolean distZip = false;
      boolean toXML = false;
      boolean verbose = false;
      boolean inline = true;
      boolean keep = true;
      boolean noTimestamp = false;
      String factoring = "full-left";
      String recursionRemoval = "full";
      String inputFile = null;

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
          parserImplementation = ParserImplementation.XQUERY;
        }
        else if (arg.equals("-xslt"))
        {
          parserImplementation = ParserImplementation.XSLT;
        }
        else if (arg.equals("-java"))
        {
          parserImplementation = ParserImplementation.JAVA;
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
        else if (arg.equals("-distZip"))
        {
          distZip = true;
        }
        else if (arg.startsWith("-"))
        {
          notation = null;
          for (String n : notations)
          {
            if (n.equals(arg.substring(1))) {
              notation = arg.substring(1);
              break;
            }
          }
          if (notation == null)
          {
            System.err.println("unsupported option: " + arg);
            System.exit(1);
          }
        }
        else
        {
          inputFile = arg;
          if (i + 1 != args.length)
          {
            System.err.println("excessive input file specification: " + args[i + 1]);
            System.exit(1);
          }
        }
      }

      if (distZip)
      {
        String downloadFilename = ConvertVersion.PROJECT_NAME +
            "-" + ConvertVersion.VERSION +
            "-java" + Download.javaVersion() +
            ".zip";
        try (OutputStream outputStream = new FileOutputStream(downloadFilename))
        {
          Download.distZip(
                  (out, warName) -> usage(out, warName),
                  identity(),
                  Download.warFile(),
                  outputStream);
        }
      }
      else if (inputFile == null)
      {
        String resource = Convert.class.getResource("/" + Convert.class.getName().replace('.',  '/') + ".class").toString();
        final String file = resource.startsWith("jar:fatjar:")
          ? ConvertVersion.PROJECT_NAME + ".war"
          : Convert.class.getName();
        usage(System.err, file);
      }
      else
      {
        String ebnfGrammar = convert(notation, read(inputFile), localTzOffset(), toXML, recursionRemoval, factoring, inline, keep, parserImplementation, noTimestamp, verbose);
        System.out.print(ebnfGrammar);
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
  public static final String GRAMMAR_IXML       = "ixml";

  private static String notations[] = new String[]{
    GRAMMAR_W3C,
    GRAMMAR_ABNF,
    GRAMMAR_ANTLR_3,
    GRAMMAR_ANTLR_4,
    GRAMMAR_BISON,
    GRAMMAR_GOLD,
    GRAMMAR_JAVACC,
    GRAMMAR_JISON,
    GRAMMAR_IXML,
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
    "ixml",
    "rules",
    "Grammar",
    "PSS-Grammar",
    "Grammar",
    "Grammar",
    "Grammar",
  };

  private static final IgnoringEventHandler ignoringEventHandler = new IgnoringEventHandler();

  private static Object parsers[] = new Object[]{
    new W3c("", ignoringEventHandler),
    new Abnf("", ignoringEventHandler),
    new Antlr_3("", ignoringEventHandler),
    new Antlr_4("", ignoringEventHandler),
    new Bison("", ignoringEventHandler),
    new Gold("", ignoringEventHandler),
    new Javacc("", ignoringEventHandler),
    new Jison("", ignoringEventHandler),
    new IXML("", ignoringEventHandler),
    new Instaparse("", ignoringEventHandler),
    new Pegjs("", ignoringEventHandler),
    new Pss("", ignoringEventHandler),
    new Phythia("", ignoringEventHandler),
    new REx_5_9("", ignoringEventHandler),
    new Xtext("", ignoringEventHandler),
  };

  public static String convert(String notation,
                               String grammar,
                               int tzOffset,
                               boolean toXML,
                               String recursionRemoval,
                               String factoring,
                               boolean inline,
                               boolean keep,
                               ParserImplementation target,
                               boolean noTimestamp,
                               boolean verbose) throws Exception
  {
    String successfullyParsedNotation = null;
    Result parseTree = null;
    TreeSet<Error> errorMessages = new TreeSet<>();

    for (int i = 0; i < notations.length; ++i)
    {
      if (notation == null || notation.equals(notations[i]))
      {
        String module = notations[i];
        if (verbose)
        {
          System.err.println("trying " + module + " using " + target.toString() + "-coded parser");
        }

        if (target == ParserImplementation.XQUERY)
        {
          String startSymbol = startSymbols[i];

          String moduleNamespace = "de/bottlecaps/convert/xq/" + module + "/" + module + ".xquery";
          String query =
            "import module namespace p='" + moduleNamespace + "' at '" + moduleURL(moduleNamespace) + "';\n" +
            "declare variable $grammar external;\n" +
            "let $parse-tree := p:parse-" + startSymbol + "($grammar)\n" +
            "return\n" +
            "  if ($parse-tree/self::ERROR) then\n" +
            "    error(xs:QName(\"" + module + "\"), string($parse-tree))\n" +
            "  else\n" +
            "    document{$parse-tree}";
          try {
            parseTree = defaultXQueryProcessor()
                .compile(query)
                .evaluate(Collections.singletonMap("grammar", grammar));
            successfullyParsedNotation = module;
            break;
          }
          catch (Exception e) {
            if (! logError(errorMessages, module, e.getMessage()))
              throw e;
          }
        }
        else if (target == ParserImplementation.XSLT)
        {
          String startSymbol = startSymbols[i];
          String moduleNamespace = "de/bottlecaps/convert/xq/" + module + "/" + module + ".xslt";
          String xslt =
            "<?xml version=\"1.0\"?>"+
            "<xsl:stylesheet version=\"2.0\"\n" +
            "                xmlns:p=\"de/bottlecaps/convert/xq/" + module + "/" + module + ".xslt\"\n" +
            "                xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n" +
            "                xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n" +
            "  <xsl:include href=\"" + moduleURL(moduleNamespace) + "\"/>" +
            "  <xsl:param name=\"grammar\"/>" +
            "  <xsl:template match=\"/\">\n" +
            "    <xsl:variable name=\"parse-tree\" select=\"p:parse-" + startSymbol + "($grammar)\"/>\n" +
            "    <xsl:choose>\n" +
            "      <xsl:when test=\"$parse-tree/self::ERROR\">\n" +
            "        <xsl:sequence select=\"error(xs:QName('" + module + "'), string($parse-tree))\"/>\n" +
            "      </xsl:when>\n" +
            "      <xsl:otherwise>\n" +
            "        <xsl:sequence select=\"$parse-tree\"/>\n" +
            "      </xsl:otherwise>\n" +
            "    </xsl:choose>\n"+
            "  </xsl:template>\n" +
            "</xsl:stylesheet>";

          Map<String, Object> parameters = Collections.singletonMap("grammar", grammar);
          try
          {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            defaultXsltProcessor().evaluateXslt(xslt, parameters, baos);
            parseTree = defaultXQueryProcessor().parseXml(baos.toString(StandardCharsets.UTF_8));
            successfullyParsedNotation = module;
            break;
          }
          catch (Exception e) {
            if (! logError(errorMessages, module, e.getMessage()))
              throw e;
          }
        }
        else
        {
          Writer writer = new StringWriter();
          if (parsers[i] instanceof Parser)
          {
            Parser parser = (Parser) parsers[i];
            parser.initialize(grammar, new Parser.XmlSerializer(writer, false));
            try
            {
              parser.parse();
            }
            catch (Parser.ParseException pe)
            {
              if (! logError(errorMessages, module, parser.getErrorMessage(pe)))
                  throw pe;
              continue;
            }
          }
          else
          {
            LRParser parser = (LRParser) parsers[i];
            ParseTreeBuilder treeBuilder = new ParseTreeBuilder();
            parser.initialize(grammar, treeBuilder);
            try
            {
              parser.parse();
            }
            catch (LRParser.ParseException pe)
            {
              if (! logError(errorMessages, module, parser.getErrorMessage(pe)))
                throw pe;
              continue;
            }
            treeBuilder.serialize(new LRParser.XmlSerializer(writer, false));
          }
          writer.flush();
          parseTree = defaultXQueryProcessor().parseXml(writer.toString());
          successfullyParsedNotation = module;
          break;
        }
      }
    }

    if (successfullyParsedNotation == null)
    {
      if (! grammar.startsWith("<"))
      {
        throw new ConvertException(errorMessages);
      }
      else
      {
        parseTree = defaultXQueryProcessor().parseXml(grammar);
        successfullyParsedNotation = null;
      }
    }

    StringBuilder query = new StringBuilder();
    String copyright = "v" + ConvertVersion.VERSION + " which is Copyright (c) 2011-" + ConvertVersion.DATE.replaceFirst("[^,]+, ", "") + " by Gunther Rademacher <grd@gmx.net>";

    String toW3c = successfullyParsedNotation == null
        ? "$parse-tree/*"
        : "c:" + successfullyParsedNotation + "-to-w3c($parse-tree/*)";
    String transform = GRAMMAR_REX_5_9.equals(successfullyParsedNotation)
        ? toW3c
        : "t:transform(" + toW3c + ", \"" + xqueryStringEscape(recursionRemoval) + "\", \"" + xqueryStringEscape(factoring) + "\", " + inline + "(), " + keep + "())\n";
    String render = GRAMMAR_REX_5_9.equals(successfullyParsedNotation)
        ? "string"
        : "b:render";
    if (successfullyParsedNotation == null)
      successfullyParsedNotation = "w3c";
    String[] prefixes = {"c", "e", "x", "t"};
    String[] namespaces = {
        "de/bottlecaps/convert/xq/" + successfullyParsedNotation + "/" + successfullyParsedNotation + "-to-w3c.xq",
        "de/bottlecaps/railroad/xq/html-to-ebnf.xq",
        "de/bottlecaps/convert/xq/to-w3c.xq",
        "de/bottlecaps/railroad/xq/transform-ast.xq"
    };
    for (int i = 0; i < namespaces.length; ++i) {
      query.append("import module namespace ")
      .append(prefixes[i])
      .append("='")
      .append(namespaces[i])
      .append("' at '")
      .append(moduleURL(namespaces[i]))
      .append("';\n");
    }
    Map<String, String> outputOptions;
    if (toXML)
    {
      outputOptions = Map.of(
          "method", "xml",
          "indent", "yes");
      query.append(
        "declare namespace g=\"http://www.w3.org/2001/03/XPath/grammar\";\n" +
        "declare variable $parse-tree external;\n" +
        "document\n" +
        "{\n" +
        (
          noTimestamp
            ? ""
            : ("  comment {concat(\" converted on \", e:timestamp(" + tzOffset + "), \" by " + successfullyParsedNotation + "-to-w3c " + copyright + " \")},\n")
        ) +
        "  \"&#xA;\"," +
        "  " + transform +
        "}");
    }
    else
    {
      outputOptions = Collections.singletonMap("method", "text");
      String namespace = "de/bottlecaps/railroad/xq/ast-to-ebnf.xq";
      query.append("import module namespace b='")
        .append(namespace)
        .append("' at '")
        .append(moduleURL(namespace))
        .append("';\n")
        .append(
          "declare namespace g=\"http://www.w3.org/2001/03/XPath/grammar\";\n" +
          "declare variable $parse-tree external;\n" +
          "  concat\n" +
          "  (\n" +
          (
            noTimestamp
              ? "    \"\","
              : ("    \"/* converted on \", e:timestamp(" + tzOffset + "), \" by " + successfullyParsedNotation + "-to-w3c " + copyright + " */&#xA;&#xA;\",")
          ) +
          "    let $ast := " + transform +
          "    return " + render + "($ast)\n" +
          "  )"
        );
    }

    try
    {
      return defaultXQueryProcessor()
        .compile(query.toString())
        .evaluate(Collections.singletonMap("parse-tree", parseTree))
        .serializeToString(outputOptions);
    }
    catch (Exception e)
    {
      throw new ConvertException("grammar was parsed OK, but conversion failed\n" + e.getMessage(), e);
    }
  }

  private static String moduleURL(String moduleNamespace) {
    URL moduleURL = Thread.currentThread().getContextClassLoader().getResource(moduleNamespace);
    return "file".equals(moduleURL.getProtocol())
        ? "classpath:" + moduleNamespace
        : moduleURL.toString();
  }

  private static boolean logError(TreeSet<Error> errorMessages, String converter, String msg) throws Exception {
    Pattern errorMessagePattern = Pattern.compile("(?s)^.*(?<message>(?:syntax error, found |lexical analysis failed).*at line (?<line>\\d+), column (?<column>\\d+):(?:.*\\.\\.\\.){2}).*$");
    Matcher matcher = errorMessagePattern.matcher(msg);
    if (! matcher.matches())
      return false;
    String message = matcher.group("message");
    int line = Integer.parseInt(matcher.group("line"));
    int column = Integer.parseInt(matcher.group("column"));
    errorMessages.add(new Error(line, column, converter, message));
    return true;
  }

  public static class ConvertException extends RuntimeException
  {
    private static final long serialVersionUID = 1L;
    private TreeSet<Error> errorLog = null;

    public ConvertException(String message) {super(message);}

    public ConvertException(String message, Exception e) {super(message, e);}

    public ConvertException(TreeSet<Error> errorLog)
    {
      super(errorLog.iterator().next().message);
      this.errorLog = errorLog;
    }

    public TreeSet<Error> getErrorLog() {return errorLog;}
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

  public static class Error implements Comparable<Error>
  {
    int line;
    int column;
    String converter;
    String message;

    public Error(int line, int column, String converter, String message)
    {
      this.line = line;
      this.column = column;
      this.converter = converter;
      this.message = message;
    }

    @Override
    public int compareTo(Error other)
    {
      if (line != other.line) return other.line - line;
      if (column != other.column) return other.column - column;
      return other.converter.compareTo(converter);
    }
  }

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
    out.println("ebnf-convert - Grammar Converter");
    out.println();
    out.println("Converts foreign grammars to W3C-style EBNF as suitable for REx and RR.");
    out.println();
    out.println("  version v" + ConvertVersion.VERSION);
    out.println("  released " + ConvertVersion.DATE);
    out.println("  from " + CONVERT_URL);
    out.println();
    out.println("Usage: java " + jar + file + " [-xml|-f FACTORING|-r KIND|-noinline|-noepsilon|-v]... GRAMMAR");
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
