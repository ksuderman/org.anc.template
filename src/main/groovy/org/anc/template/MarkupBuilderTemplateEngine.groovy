package org.anc.template

import groovy.xml.MarkupBuilder

/**
 * @author Keith Suderman
 */
class MarkupBuilderTemplateEngine implements TemplateEngine {
    String template

    public MarkupBuilderTemplateEngine(File file) {
        this.template = file.text
    }

    public MarkupBuilderTemplateEngine(URL url) {
        this.template = url.text
    }

    public MarkupBuilderTemplateEngine(String template) {
        this.template = template
    }

    String generate(Map params) {
        // Create a list of element's ancestors in reverse order.
        StringWriter writer = new StringWriter()
        def html = new MarkupBuilder(writer)
        Binding binding = new Binding(params)
        Closure closure = new GroovyShell(binding).evaluate( "{ it-> ${template} }" )
        closure.delegate = html
        closure()
        return writer.toString()
    }
}
