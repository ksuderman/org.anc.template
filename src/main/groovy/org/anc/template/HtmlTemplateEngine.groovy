package org.anc.template

import groovy.text.SimpleTemplateEngine
import groovy.text.Template

/**
 * @author Keith Suderman
 */
public class HtmlTemplateEngine implements TemplateEngine {
    Template template

    public HtmlTemplateEngine(String template) {
        this.template = new SimpleTemplateEngine().createTemplate(template)
    }

    public HtmlTemplateEngine(File file) {
        template = new SimpleTemplateEngine().createTemplate(file)
    }

    public HtmlTemplateEngine(Reader reader) {
        template = new SimpleTemplateEngine().createTemplate(reader)
    }

    public HtmlTemplateEngine(URL url) {
        template = new SimpleTemplateEngine().createTemplate(url)
    }

    public String generate(Map params) {
        return template.make(params).toString()
    }
}
