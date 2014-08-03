package org.anc.template

import org.junit.*
import static org.junit.Assert.*

/**
 * @author Keith Suderman
 */
class HtmlTest {
    XmlParser parser

    @Before
    void setup() {
        parser = new XmlParser();
    }

    @After
    void cleanup() {
        parser = null
    }

    @Test
    void simpleTest() {
        String expected = "hello world"
        TemplateEngine engine = new HtmlTemplateEngine(expected)
        String result = engine.generate([:])
        assertTrue result == expected
    }

    @Test
    void paramTest() {
        String expected = "hello world"
        TemplateEngine engine = new HtmlTemplateEngine('$x')
        Map params = [x:expected]
        String result = engine.generate(params)
        assertTrue result == expected
    }

    @Test
    void titleTest() {
        String template = """
<html>
    <head>
        <title>\$title</title>
    </head>
</html>
"""
        TemplateEngine engine = new HtmlTemplateEngine(template)
        String html = engine.generate([title:'Test'])
        def root = parser.parse(new StringReader(html))
        assertTrue root.head.title.text() == 'Test'
    }
}


