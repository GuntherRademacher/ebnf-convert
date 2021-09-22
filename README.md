# convert - Grammar Converter

This is an experimental version of RR - Railroad Diagram Generator, that includes
convert, the Grammar Converter. It builds both an experimental rr.war and convert.war.

# Distribution

convert comes in a .war file, convert.war. This can be deployed
in servlet containers like Tomcat or Jetty for serving the GUI, and it makes up the webapp
that is running on the original website, <https://bottlecaps.de/convert>.

convert.war file is a Java "executable war", i.e. it can also be started
standalone from command line.

For listing the set of available command line options, run

```bash
   java -jar convert.war
```

## Building convert
For building convert, JDK 8 (or higher) must be available. In the
project folder, run this command to build the distribution .zip file:

```bash
gradlew
```
