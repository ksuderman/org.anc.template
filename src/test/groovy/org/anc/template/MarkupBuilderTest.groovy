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

    @Test
    void someTest() {
        String title = "This is a test"
        String heading = "Testing"
        String text = "Hello world."
        String template = """
            html {
                head {
                    title "$title"
                }
                body {
                    h1 "$heading"
                    p "$text"
                }
            }
        """
        def builder = new MarkupBuilderTemplateEngine(template)
        String html = builder.generate([:])
        def doc = parser.parseText(html)
        assertEquals(title, doc.head.title.text())
        assertEquals(heading, doc.body.h1.text())
        assertEquals(text, doc.body.p.text())
    }
}
