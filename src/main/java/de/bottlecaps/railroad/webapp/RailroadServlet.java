package de.bottlecaps.railroad.webapp;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.bottlecaps.railroad.convert.ConvertServlet;
import de.bottlecaps.railroad.core.Download;
import de.bottlecaps.webapp.servlet.ServletRequest;
import de.bottlecaps.webapp.servlet.ServletResponse;

@MultipartConfig
public class RailroadServlet extends HttpServlet
{
  private static final long serialVersionUID = -8907590040450734599L;

  private RailroadWebApp railroadWebApp = new RailroadWebApp();

  private RailroadServlet railroadServlet = this;
  private ConvertServlet convertServlet = new ConvertServlet()
  {
    private static final long serialVersionUID = 1L;

    @Override
    public ServletConfig getServletConfig()
    {
      return railroadServlet.getServletConfig();
    }
  };

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException
  {
    ServletRequest servletRequest = new ServletRequest(request);
    if (Download.warFile(servletRequest).getName().equals("convert.war"))
    {
      convertServlet.doGet(request, response);
      return;
    }

    railroadWebApp.doRequest(servletRequest, new ServletResponse(response));
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException
  {
    ServletRequest servletRequest = new ServletRequest(request);
    if (Download.warFile(servletRequest).getName().equals("convert.war"))
    {
      convertServlet.doPost(request, response);
      return;
    }

    railroadWebApp.doRequest(servletRequest, new ServletResponse(response));
  }

}
