package de.bottlecaps.xml;

import java.io.OutputStream;
import java.util.Map;

public interface XsltProcessor {

  public static XsltProcessor defaultXsltProcessor() {
    return SaxonXsltProcessor.instance;
  }

  public void evaluateXslt(String xslt, Map<String, Object> parameters, OutputStream outputStream) throws Exception;

}