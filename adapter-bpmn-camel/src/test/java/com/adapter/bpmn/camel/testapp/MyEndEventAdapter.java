package com.adapter.bpmn.camel.testapp;

import com.adapter.bpmn.camel.CamelAdapter;
import org.apache.camel.model.ProcessorDefinition;

public class MyEndEventAdapter implements CamelAdapter {
    @Override
    public void adapt(ProcessorDefinition processorDefinition) {
        processorDefinition.stop();
    }
}
