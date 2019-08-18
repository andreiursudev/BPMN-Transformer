package com.bpmn.transformer.diagram;

import static org.junit.Assert.assertEquals;

public class TestHelper {

    static void assertEqualsIgnoreLineEndings(String xml, String expected) {
        assertEquals(expected.replaceAll("[\r\n]+", "\n"), xml.replaceAll("[\r\n]+", "\n"));
    }
}
