# ebnf-convert - Grammar Converter

This is a tool for converting various forms of BNF and EBNF grammars to [W3C-style EBNF][W3C-EBNF],
which is the notation that is used primarily in the XML and XQuery recommendations. It is
useful for creating syntax diagrams using the [RR][RR] tool, as well as for generating parsers
with [REx Parser Generator][REx].

Input grammars are accepted in these notations:

- [ABNF](https://datatracker.ietf.org/doc/html/rfc5234)
- [ANTLR 3](https://www.antlr3.org/)
- [ANTLR 4](https://www.antlr.org/)
- [Bison](https://www.gnu.org/software/bison/manual/html_node/Grammar-File.html)
- [GOLD](http://goldparser.org/doc/index.htm)
- [Instaparse](https://github.com/Engelberg/instaparse/blob/master/src/instaparse/cfg.cljc)
- [Invisible XML](https://invisiblexml.org/ixml-specification.html)
- [JavaCC](https://javacc.github.io/javacc/documentation/grammar.html)
- [Jison](https://github.com/zaach/jison)
- [PEG.js](https://github.com/pegjs/pegjs/tree/master/docs/grammar)
- [Xtext](https://eclipse.org/Xtext/documentation/301_grammarlanguage.html)

Besides converting these grammar notations, ebnf-convert can also perform some grammar transformation,
e.g. factorization and elimination of direct recursion. To some extent this transforms BNF to EBNF, 
yielding more compact diagrams.

ebnf-convert has a simple browser UI as well as a command line interface for batch execution.

# Distribution

ebnf-convert comes as a `.zip`, containing a `.war` file. The `.war` file can be deployed
in servlet containers like Tomcat or Jetty for serving the browser UI. This makes up the webapp
that is running on the original website, <https://www.bottlecaps.de/ebnf-convert>.

The `.war` file is a Java "executable war", i.e. it can also be started
standalone from command line:

```
Usage: java -jar ebnf-convert.war [-xml|-f FACTORING|-r KIND|-noinline|-noepsilon|-v]... GRAMMAR

  -xml             create output grammar in XML, rather than EBNF
  -f FACTORING     apply factoring (any of "full-left" (default), "left-only", "full-right", "right-only", or "none")
  -r KIND          remove direct recursion, where KIND is any of "full" (default), "left", "right", or "none"
  -noinline        do not inline nonterminals that derive to a single string literal
  -noepsilon       do not keep nonterminal referemces, that derive to epsilon only
  -v               verbose output

  GRAMMAR          path of foreign grammar
```

## Building ebnf-convert
For building ebnf-convert, JDK 11 (or higher) must be available. In the
project folder, run this command to build the distribution `.zip` file in the `build/distributions` folder:

```bash
gradlew
```

## Installation

Some platforms may have packages available, find out about that on [Repology](https://repology.org/project/ebnf-convert/versions).

## ebnf-convert on Maven Central

ebnf-convert is available on Maven Central with groupId `de.bottlecaps.ebnf-convert` and artifactIds

 -  [`ebnf-convert-webapp`][ebnf-convert-webapp] for `ebnf-convert.war`,
 -  [`ebnf-convert-lib`][ebnf-convert-lib] for `ebnf-convert.jar`, which can be added to other projects as a dependency.

## Thanks

This project makes use of
  * [Saxon-HE][SAXON],
  * [Gradle][GRADLE], and
  * [Gradle-License-Report][GRADLE-LICENSE-REPORT].

## License

Copyright (c) 2011-2025 Gunther Rademacher. ebnf-convert is provided under the [Apache 2 License][ASL].

## Links

The official website for ebnf-convert is <https://www.bottlecaps.de/ebnf-convert>.

[ASL]: http://www.apache.org/licenses/LICENSE-2.0
[W3C-EBNF]: http://www.w3.org/TR/2010/REC-xquery-20101214/#EBNFNotation
[SAXON]: http://www.saxonica.com/products/products.xml
[GRADLE]: https://gradle.org/
[GRADLE-LICENSE-REPORT]: https://github.com/jk1/Gradle-License-Report
[REx]: https://www.bottlecaps.de/rex
[RR]: https://github.com/GuntherRademacher/rr
[ebnf-convert-webapp]: https://central.sonatype.com/artifact/de.bottlecaps.ebnf-convert/ebnf-convert-webapp
[ebnf-convert-lib]: https://central.sonatype.com/artifact/de.bottlecaps.ebnf-convert/ebnf-convert-lib
