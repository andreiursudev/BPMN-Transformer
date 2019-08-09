package com.adapter.bpmn.camel;

import com.adapter.bpmn.camel.startevent.StartFromCamelAdapter;
import com.adapter.bpmn.model.flowobject.startevent.NamedStartEvent;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DictionaryTest {

    @Test
    public void name() throws Exception {
        BPMNToCamelDictionary dictionary = new BPMNToCamelDictionary();

        CamelAdapter adapter = dictionary.getAdapter(new NamedStartEvent(""));

        assertTrue(adapter instanceof StartFromCamelAdapter);
    }
}