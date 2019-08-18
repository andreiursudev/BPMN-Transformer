package com.bpmn.transformer.camel;

import com.bpmn.transformer.camel.startevent.StartFromCamelAdapter;
import com.bpmn.transformer.model.flowobject.startevent.UriStartEvent;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DictionaryTest {

    @Test
    public void name() throws Exception {
        BPMNToCamelDictionary dictionary = new BPMNToCamelDictionary();

        CamelAdapter adapter = dictionary.getAdapter(new UriStartEvent(""));

        assertTrue(adapter instanceof StartFromCamelAdapter);
    }
}