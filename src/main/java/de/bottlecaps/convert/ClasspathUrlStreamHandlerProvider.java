package de.bottlecaps.convert;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.spi.URLStreamHandlerProvider;

public class ClasspathUrlStreamHandlerProvider extends URLStreamHandlerProvider
{
  @Override
  public URLStreamHandler createURLStreamHandler(String protocol)
  {
    if ("classpath".equals(protocol))
    {
      return new URLStreamHandler()
      {
        @Override
        protected URLConnection openConnection(URL url) throws IOException
        {
          return ClassLoader.getSystemClassLoader().getResource(url.getPath()).openConnection();
        }
      };
    }
    return null;
  }
}