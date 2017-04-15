package org.anc.template

import groovy.xml.MarkupBuilder

/**
 * Uses the MarkupBuilder DSL as the template language.
 * <pre>
 *     String template = """
 *     html {
 *         head {
 *             title "This is a test"
 *         }
 *         body {
 *             h1 "Testing"
 *             p "Hello world."
 *         }
 *     }
 *     """
 *     def builder = new MarkupBuilderTemplateEngine(template)
 *     println builder.generate([:])
 *
 * @author Keith Suderman
 */
public class MarkupBuilderTemplateEngine implements TemplateEngine {
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

    public String generate(Map params) {
        // Create a list of element's ancestors in reverse order.
        StringWriter writer = new StringWriter()
        def html = new MarkupBuilder(writer)
        Binding binding = new Binding(params)
        binding.setVariable('builder', html)
        Closure closure = new GroovyShell(binding).evaluate( "{ it-> ${template} }" )
        closure.delegate = html
        closure()
        return writer.toString()
    }
}
