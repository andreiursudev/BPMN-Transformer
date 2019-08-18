package com.bpmn.transformer.camel.infolog;

import com.bpmn.transformer.camel.CamelAdapter;
import com.bpmn.transformer.camel.BPMNToCamelDictionary;
import org.apache.camel.model.ProcessorDefinition;

public class InfoLogCamelAdapter implements CamelAdapter {

    @Override
    public void adapt(ProcessorDefinition processorDefinition, BPMNToCamelDictionary dictionary) {
        processorDefinition.log("Message received: ${body}");
    }
}
