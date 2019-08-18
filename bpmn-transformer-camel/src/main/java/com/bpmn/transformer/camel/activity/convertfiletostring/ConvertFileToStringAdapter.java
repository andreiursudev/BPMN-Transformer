package com.bpmn.transformer.camel.activity.convertfiletostring;

import com.bpmn.transformer.camel.CamelAdapter;
import com.bpmn.transformer.camel.BPMNToCamelDictionary;
import org.apache.camel.model.ProcessorDefinition;

public class ConvertFileToStringAdapter implements CamelAdapter {

    @Override
    public void adapt(ProcessorDefinition processorDefinition, BPMNToCamelDictionary dictionary) {
        processorDefinition.convertBodyTo(String.class);
    }
}
