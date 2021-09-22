package de.bottlecaps.railroad.core;
import java.io.InputStream;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.lib.ModuleURIResolver;
import net.sf.saxon.trans.XPathException;

public enum ResourceModuleUriResolver implements ModuleURIResolver, URIResolver
{
  instance;

  @Override
  public StreamSource[] resolve(String uri, String baseURI, String[] locations) throws XPathException
  {
    try
    {
      InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(uri);
      if (resource != null)
      {
        CrLfNormalizer inputStream = new CrLfNormalizer(resource);
        return new StreamSource[]{new StreamSource(inputStream, uri)};
      }
    }
    catch (Exception e)
    {
    }
    return null;
  }

    @Override
    public Source resolve(String href, String base) throws TransformerException {
        StreamSource[] sources = resolve(href, base, null);
        return sources == null ? null : sources[0];
    }
}
