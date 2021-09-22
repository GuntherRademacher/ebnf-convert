package de.bottlecaps.railroad.convert;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import de.bottlecaps.railroad.core.Download;
import de.bottlecaps.webapp.servlet.ServletRequest;

/**
 * The converter servlet for pegjs grammars. Handle POST request to
 * receive grammars, and return W3C-style EBNF wrapped in an HTML page.
 */
public class ConvertServlet extends HttpServlet
{
  private static final String DOWNLOAD_FILENAME =
          "convert" +
          "-" + Convert.VERSION.substring(1) +
          "-java" + Download.javaVersion() +
          ".zip";
  private static final String DOWNLOAD_PATH =
		  "/download/" + 
		  DOWNLOAD_FILENAME;
  
  private static final long serialVersionUID = 1L;
  private static final String GRAMMAR = "grammar";
  private static final String TARGET = "target";
  private static final String TEXT_PLAIN = "text/plain";
  private static final String UTF_8 = "UTF-8";

  private static final Map<String, String> DEFAULT_PARAMETERS = new HashMap<String, String>(); {
    DEFAULT_PARAMETERS.put("factoring", "on");
    DEFAULT_PARAMETERS.put("recursion", "on");
    DEFAULT_PARAMETERS.put("inline", "on");
    DEFAULT_PARAMETERS.put("keep", "on");
  }

  private String getHtmlPage(String servletPath, HttpServletResponse response, Map<String, String> parameters)
  {
    String index = "/index.html.template";

    InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/de/bottlecaps/railroad/convert" + index);
    String content;
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
        byte[] buffer = new byte[65536];
        for (int size; (size = resourceAsStream.read(buffer)) != -1; )
            out.write(buffer, 0, size);

        content = new String(out.toByteArray(), StandardCharsets.UTF_8);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e.getMessage(), e);
    }
    content = content.replaceAll("VERSION", Convert.VERSION + "." + Convert.REVISION)
                     .replaceAll("DATE", Convert.DATE)
                     .replaceAll("YEAR", Convert.YEAR)
                     .replaceAll("TARGET-EBNF", "ebnf".equals(parameters.get("target")) ? "selected=\"selected\"" : "")
                     .replaceAll("TARGET-XML", "xml".equals(parameters.get("target")) ? "selected=\"selected\"" : "")
//                       .replaceAll("FACTORING-NONE", "none".equals(parameters.get("factoring")) ? "selected=\"selected\"" : "")
//                       .replaceAll("FACTORING-LEFT-ONLY", "left-only".equals(parameters.get("factoring")) ? "selected=\"selected\"" : "")
//                       .replaceAll("FACTORING-RIGHT-ONLY", "right-only".equals(parameters.get("factoring")) ? "selected=\"selected\"" : "")
//                       .replaceAll("FACTORING-FULL-LEFT", "full-left".equals(parameters.get("factoring")) ? "selected=\"selected\"" : "")
//                       .replaceAll("FACTORING-FULL-RIGHT", "full-right".equals(parameters.get("factoring")) ? "selected=\"selected\"" : "")
                     .replaceAll("FACTORING", parameters.get("factoring") == null ? "" : "checked=\"on\"")
//                       .replaceAll("RECURSION-REMOVAL-NONE", "none".equals(parameters.get("recursion-removal")) ? "selected=\"selected\"" : "")
//                       .replaceAll("RECURSION-REMOVAL-LEFT", "left".equals(parameters.get("recursion-removal")) ? "selected=\"selected\"" : "")
//                       .replaceAll("RECURSION-REMOVAL-RIGHT", "right".equals(parameters.get("recursion-removal")) ? "selected=\"selected\"" : "")
//                       .replaceAll("RECURSION-REMOVAL-FULL", "full".equals(parameters.get("recursion-removal")) ? "selected=\"selected\"" : "")
                     .replaceAll("RECURSION", parameters.get("recursion") == null ? "" : "checked=\"on\"")
                     .replaceAll("INLINE", parameters.get("inline") == null ? "" : "checked=\"on\"")
                     .replaceAll("KEEP", parameters.get("keep") == null ? "" : "checked=\"on\"")
                     ;
    return content;
  }

  /**
   * Servlet GET method.
   *
   * @param request the servlet request object.
   * @param response the servlet response object.
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    String pathInfo = request.getPathInfo();

    ServletRequest servletRequest = new ServletRequest(request);
	if (pathInfo.equals(DOWNLOAD_PATH) && Download.warFile(servletRequest) != null)
    {
      response.setStatus(200);
      response.setContentType("application/zip");
      response.setHeader("Content-Disposition", "attachment; filename=" + DOWNLOAD_FILENAME);
      OutputStream outputStream = response.getOutputStream();
      Download.distZip(
            (out, warName) -> Convert.usage(out, warName),
            license -> license.replaceAll("RR - Railroad Diagram Generator", "Convert - Grammar Converter"),
	        Download.warFile(servletRequest),
    		outputStream);
    }
    else if (pathInfo.equals("") || pathInfo.equals("/"))
    {
      String page = getHtmlPage(pathInfo, response, DEFAULT_PARAMETERS);
      response.getWriter().write(page);
    }
    else
    {
      response.setContentType(TEXT_PLAIN);
      response.setCharacterEncoding(UTF_8);
      response.setStatus(404);
      try (Writer writer = response.getWriter())
      {
        writer.write("HTTP 404 Not Found");
      }
    }
  }

  /**
   * Servlet POST method.
   *
   * @param request the servlet request object.
   * @param response the servlet response object.
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    String encoding = request.getCharacterEncoding();

    if (encoding == null)
    {
      encoding = "UTF-8";
      request.setCharacterEncoding(encoding);
    }

    List<FileItem> fileItems = null;

    if (! DiskFileUpload.isMultipartContent(request))
    {
      fileItems = null;
    }
    else
    {
      DiskFileUpload fu = new DiskFileUpload();
      fu.setSizeMax(1000000);
      fu.setHeaderEncoding(encoding);

      try
      {
        List<FileItem> parseRequest = (List<FileItem>) fu.parseRequest(request);
        fileItems = parseRequest;
      }
      catch (FileUploadException e)
      {
        throw new RuntimeException(e);
      }
    }

    Map<String, String> parameters = new HashMap<String, String>();

    if (fileItems == null)
    {
      for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();)
      {
        String name = e.nextElement();
        String value = request.getParameter(name);
        parameters.put(name, value);
      }
    }
    else
    {
      for (FileItem fi : fileItems)
      {
        String name = fi.getFieldName();
        String value = fi.getString(encoding);
        parameters.put(name, value);
      }
    }

    if (parameters.get(GRAMMAR) == null)
    {
      doGet(request, response);
      return;
    }

    int tzOffset;
    try
    {
      tzOffset = Integer.parseInt(parameters.get("tz"));
    }
    catch (Exception e)
    {
      tzOffset = Convert.localTzOffset();
    }

    String ebnfGrammar = null;
    String fragment = null;
    try
    {
      boolean xml = "xml".equals(parameters.get(TARGET));
      String factoring = parameters.get("factoring") == null ? "none" : "full-left";
      boolean inline = parameters.get("inline") != null;
      boolean keep = parameters.get("keep") != null;
      String recursionRemoval = parameters.get("recursion") == null ? "none" : "full";

      ebnfGrammar = Convert.convert(null, parameters.get(GRAMMAR), tzOffset, xml, recursionRemoval, factoring, inline, keep, Convert.Target.JAVA, false, false);

      if (xml)
      {
        fragment =
          "<h4>W3C-style&#160;grammar:</h4>\n" +
          "<textarea name=\"text\">" +
          escapeXmlContent("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n") +
          escapeXmlContent(ebnfGrammar) +
          "</textarea>\n";
      }
      else
      {
        int commentEnd = ebnfGrammar.indexOf("*/");
        String name = "";
        if (commentEnd >= 0)
        {
          String[] part = ebnfGrammar.substring(commentEnd + 2).split("\\s+");
          if (part.length >= 2) name = part[1];
        }
        fragment =
          "<form enctype=\"multipart/form-data\" method=\"POST\" action=\"/rr/ui\">\n" +
          "  <h4>W3C-style&#160;grammar:</h4>\n" +
          "  <p><nobr><b>Name:&#160;</b><input type=\"text\" name=\"name\" value=\"" + name + "\"/></nobr></p>\n" +
          "  <textarea name=\"text\">" + escapeXmlContent(ebnfGrammar) + "</textarea>\n" +
          "  <p title=\"Submit to Railroad Diagram Generator\">\n" +
          "  <input type=\"submit\" value=\"View Diagram\"/>\n" +
          "  </p>\n" +
          "  <input type=\"hidden\" name=\"task\" value=\"VIEW\"/>\n" +
          (parameters.get("factoring") == null ? "" : "  <input type=\"hidden\" name=\"options\" value=\"factoring\"/>\n") +
          (parameters.get("recursion") == null ? "" : "  <input type=\"hidden\" name=\"options\" value=\"eliminaterecursion\"/>\n") +
          (parameters.get("inline") == null ? "" : "  <input type=\"hidden\" name=\"options\" value=\"inline\"/>\n") +
          (parameters.get("keep") == null ? "" : "  <input type=\"hidden\" name=\"options\" value=\"keep\"/>\n") +
          "  <input type=\"hidden\" name=\"uri\" value=\"\"/>\n" +
          "</form>\n" +
          "<p/>";
      }
    }
    catch (Convert.ConvertException c)
    {
      ebnfGrammar = null;

      Convert.ErrorLog log = c.getErrorLog();
      if (log == null)
      {
        fragment =
          "<pre>" + escapeErrorMessage(c.getMessage()) + "</pre>\n" +
          "<p/>";
      }
      else
      {
        fragment = "";
        int i = 0;
        for (Convert.ErrorMessage m : log.values())
        {
          fragment += m.converter + "-to-w3c conversion failed:\n<br/>" +
            "<pre style=\"left-margin: 30px;\">" + escapeErrorMessage(m.message) + "</pre>\n" +
            "<p/>";
          if (i++ == 0)
          {
            fragment += "<div id=\"ask-for-more\">\n" +
              "  <small><a href=\"javascript:more();\">more...</a></small>\n" +
              "  <script type=\"text/javascript\">\n" +
              "    function more()\n" +
              "    {\n" +
              "      document.getElementById(\"ask-for-more\").style.display = \"none\";\n" +
              "      document.getElementById(\"more\").style.display = \"block\";\n" +
              "      return false();" +
              "    }\n" +
              "  </script>\n" +
              "</div>\n" +
              "<div id=\"more\" style=\"display:none\">";
          }
        }
        fragment += "</div>";
      }
    }
    catch (Exception e)
    {
      throw e instanceof RuntimeException
          ? (RuntimeException) e
          : new RuntimeException(e);
    }

    String resultPage = getHtmlPage("/", response, parameters);
    resultPage = resultPage.replaceFirst("<textarea name=\\\"grammar\\\"></textarea>", Matcher.quoteReplacement("<textarea name=\"grammar\">" + parameters.get(GRAMMAR) + "</textarea>"));
    resultPage = resultPage.replaceFirst("<p class=\"result\"/>", Matcher.quoteReplacement(fragment));
    response.getWriter().write(resultPage);
  }

  private static String escapeErrorMessage(String s)
  {
    s = escapeXmlContent(s);
    String threeDots = "...";
    String threeDotsRegex = "\\.\\.\\.";
    if (s.endsWith(threeDots) && s.replaceAll(threeDotsRegex, "").length() == s.length() - 6)
    {
      String beginSpan = "...<span style=\"background-color:ddd\">";
      String endSpan = "</span>...";
      return s.substring(0, s.length() - 3).replaceFirst(threeDotsRegex, beginSpan) + endSpan;
    }
    else
    {
      return s;
    }
  }

  private static String escapeXmlContent(String s)
  {
    if (   s.indexOf('<') < 0
        && s.indexOf('&') < 0
        && s.indexOf('"') < 0
        && s.indexOf('>') < 0)
    {
      return s;
    }
    else
    {
      int size = s.length();
      StringBuffer sb = new StringBuffer(2 * size);
      for (int i = 0; i < size; i++)
      {
        char c = s.charAt(i);
        switch (c)
        {
        case '&' : sb.append("&amp;" ); break;
        case '\'': sb.append("&apos;"); break;
        case '"' : sb.append("&quot;"); break;
        case '<' : sb.append("&lt;"  ); break;
        case '>' : sb.append("&gt;"  ); break;
        default  : sb.append(c       ); break;
        }
      }
      return sb.toString();
    }
  }

}
