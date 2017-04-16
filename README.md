# A Simple Template Engine

[![Master Status](http://grid.anc.org:9080/travis/svg/oanc/org.anc.template?branch=master)](https://travis-ci.org/oanc/org.anc.template)
[![Develop Status](http://grid.anc.org:9080/travis/svg/oanc/org.anc.template?branch=develop)](https://travis-ci.org/oanc/org.anc.template)

The `org.anc.template` module provides simple wrappers around Groovy's [template engines](http://docs.groovy-lang.org/next/html/documentation/template-engines.html) to generate XML or HTML output.

The `HtmlTemplateEngine` uses a JSP/GSP like syntax as the template language:
```
<html>
    <body>
        <ol><% for (int i=0; i < n; ++i) { %>
                <li><%= text %></li>
        <% } %></ol>
    </body>
</html>    
```

While the `MarkupBuilderTemplateEngine` uses a Groovy DSL like syntax
```
html {
    body {
        ol {
            n.times {
                li text
            }
        }
    }
}
```

Both engines implement the `org.anc.template.TemplateEngine` interface that contains a single method:
```
String generate(Map params)
```

## Example

```
String template = "...as above..."

TemplateEngine engine = new HtmlTemplateEngine(template)
Map params = [ n:10, text: 'This is a list item' ]
println engine.generate(params)
```

# Notes

To use this module in a Java project you will need to ensure that Groovy 2.x is available on the classpath.
