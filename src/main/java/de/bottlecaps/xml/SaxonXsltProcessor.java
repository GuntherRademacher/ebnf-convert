package de.bottlecaps.xml;

import java.io.OutputStream;
import java.io.StringReader;
import java.util.Map;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.jaxp.SaxonTransformerFactory;

public class SaxonXsltProcessor implements XsltProcessor {
  public static final SaxonXsltProcessor instance = new SaxonXsltProcessor();

  private TransformerFactory transformerFactory;

  private SaxonXsltProcessor()  {
    transformerFactory = TransformerFactory.newInstance(SaxonTransformerFactory.class.getName(), SaxonXsltProcessor.class.getClassLoader());
  }

  @Override
  public void evaluateXslt(String xslt, Map<String, Object> parameters,
      OutputStream outputStream) throws Exception {

    StreamSource stylesheet = new StreamSource(new StringReader(xslt));
    Transformer transformer = transformerFactory.newTransformer(stylesheet);
    transformer.setErrorListener(new ErrorListener()  {

      @Override
      public void warning(TransformerException exception) throws TransformerException {
      }

      @Override
      public void error(TransformerException exception) throws TransformerException {
      }

      @Override
      public void fatalError(TransformerException exception) throws TransformerException {
      }
    });
    parameters.forEach((key, value) -> transformer.setParameter(key, value));
    transformer.transform(new StreamSource(new StringReader("<input/>")), new StreamResult(outputStream));
  }

}