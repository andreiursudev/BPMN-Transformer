package com.adapter.bpmn.camel.activity.convertfiletostring;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.camel.BPMNToCamelDictionary;
import org.apache.camel.model.ProcessorDefinition;

public class ConvertFileToStringAdapter implements CamelAdapter {

    @Override
    public void adapt(ProcessorDefinition processorDefinition, BPMNToCamelDictionary dictionary) {
        processorDefinition.convertBodyTo(String.class);
    }
}
