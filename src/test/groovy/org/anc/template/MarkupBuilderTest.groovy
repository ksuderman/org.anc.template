package org.anc.template

import org.junit.*
import static org.junit.Assert.*

/**
 * @author Keith Suderman
 */
class MarkupBuilderTest {
    XmlParser parser
    @Before
    void setup() {
        parser = new XmlParser()
    }

    @After
    void cleanup() {
        parser = null
    }

    @Test
    void simpleTest() {
        String expected = 'Test'
        String template = """
html {
    head {
        title '$expected'
    }
}
"""
        TemplateEngine engine = new MarkupBuilderTemplateEngine(template)
        String product = engine.generate([:])
        def html = parser.parse(new StringReader(product))
        assertTrue html.head.title.text() == expected
    }

    @Test
    void paramTest() {
        String expected = 'Test'
        String template = """
html {
    head {
        title "\$title"
    }
}
"""
        TemplateEngine engine = new MarkupBuilderTemplateEngine(template)
        String product = engine.generate([title:expected])
        println(product)
        def html = parser.parse(new StringReader(product))
        assertTrue html.head.title.text() == expected
    }
}
