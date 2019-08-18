package com.bpmn.transformer.camel;

import com.bpmn.transformer.camel.startevent.StartFromCamelAdapter;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.model.flowobject.startevent.UriStartEvent;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BPMNToCamelDictionaryTest {

    @Test(expected = AdapterFactoryNotFoundException.class)
    public void testGetAdapterForFlowObjectWithoutAdapterFactory() throws Exception {
        BPMNToCamelDictionary dictionary = new BPMNToCamelDictionary();

        dictionary.getAdapter(new CustomFlowObject());
    }

    @Test
    public void testGetAdapter() throws Exception {
        BPMNToCamelDictionary dictionary = new BPMNToCamelDictionary();

        CamelAdapter adapter = dictionary.getAdapter(new UriStartEvent(""));

        assertTrue(adapter instanceof StartFromCamelAdapter);
    }
}
class CustomFlowObject implements FlowObject{

}