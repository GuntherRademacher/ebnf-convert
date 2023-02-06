package de.bottlecaps.railroad.convert;

import de.bottlecaps.railroad.convert.abnf.Abnf;
import de.bottlecaps.railroad.convert.antlr_3.Antlr_3;
import de.bottlecaps.railroad.convert.antlr_4.Antlr_4;
import de.bottlecaps.railroad.convert.bison.Bison;
import de.bottlecaps.railroad.convert.gold.Gold;
import de.bottlecaps.railroad.convert.instaparse.Instaparse;
import de.bottlecaps.railroad.convert.ixml.IXML;
import de.bottlecaps.railroad.convert.javacc.Javacc;
import de.bottlecaps.railroad.convert.jison.Jison;
import de.bottlecaps.railroad.convert.pegjs.Pegjs;
import de.bottlecaps.railroad.convert.phythia.Phythia;
import de.bottlecaps.railroad.convert.pss.Pss;
import de.bottlecaps.railroad.convert.regexp.Regexp;
import de.bottlecaps.railroad.convert.rex_5_9.REx_5_9;
import de.bottlecaps.railroad.convert.w3c.W3c;
import de.bottlecaps.railroad.convert.xtext.Xtext;
import net.sf.saxon.Configuration;
import net.sf.saxon.lib.Initializer;

public class SaxonInitializer implements Initializer
{
  @Override
  public void initialize(Configuration config)
  {
    new Abnf.SaxonInitializer().initialize(config);
    new Antlr_3.SaxonInitializer().initialize(config);
    new Antlr_4.SaxonInitializer().initialize(config);
    new Bison.SaxonInitializer().initialize(config);
    new Gold.SaxonInitializer().initialize(config);
    new Instaparse.SaxonInitializer().initialize(config);
    new IXML.SaxonInitializer().initialize(config);
    new Javacc.SaxonInitializer().initialize(config);
    new Jison.SaxonInitializer().initialize(config);
    new Pegjs.SaxonInitializer().initialize(config);
    new Phythia.SaxonInitializer().initialize(config);
    new Pss.SaxonInitializer().initialize(config);
    new Regexp.SaxonInitializer().initialize(config);
    new REx_5_9.SaxonInitializer().initialize(config);
    new W3c.SaxonInitializer().initialize(config);
    new Xtext.SaxonInitializer().initialize(config);
  }
}
