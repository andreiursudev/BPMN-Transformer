package com.adapter.bpmn.camel.infolog;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.camel.BPMNToCamelDictionary;
import org.apache.camel.model.ProcessorDefinition;

public class InfoLogCamelAdapter implements CamelAdapter {

    @Override
    public void adapt(ProcessorDefinition processorDefinition, BPMNToCamelDictionary dictionary) {
        processorDefinition.log("Message received: ${body}");
    }
}
