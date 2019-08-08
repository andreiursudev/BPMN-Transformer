package com.adapter.bpmn.camel.activity.convertfiletostring;

import com.adapter.bpmn.camel.CamelAdapter;
import org.apache.camel.model.ProcessorDefinition;

public class ConvertFileToStringAdapter implements CamelAdapter {

    @Override
    public void adapt(ProcessorDefinition processorDefinition) {
        processorDefinition.convertBodyTo(String.class);
    }
}
