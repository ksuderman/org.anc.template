package org.anc.template

import org.junit.Test

/**
 * @author Keith Suderman
 */
class Examples {

    @Test
    void htmlTemplateEngine() {
        String template = '''
<html>
    <body>
        <ol><% for (int i=0; i < n; ++i) { %>
                <li><%= text %></li>
        <% } %></ol>
    </body>
</html>    
'''
        test(new HtmlTemplateEngine(template))
    }


    @Test
    void testMarkupBuilderTemplateEngine() {
        String template = '''
html {
    body {
        ol {
            n.times {
                li text
            }
        }
    }
}

'''
        test(new MarkupBuilderTemplateEngine(template))
    }

    void test(TemplateEngine engine) {
        String xml = engine.generate(n:10, text:'This is your test.')
        println xml
        Node root = new XmlParser().parseText(xml)
        assert root.name() == 'html'
        assert root.children().size() == 1
        Node body = root.children()[0]
        assert body != null
        assert body.children().size() == 1

        Node list = body.children()[0]
        assert list.children().size() == 10
    }
}
