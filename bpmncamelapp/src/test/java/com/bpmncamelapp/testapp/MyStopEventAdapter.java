package com.bpmncamelapp.testapp;

import com.bpmncamelapp.CamelAdapter;
import org.apache.camel.model.ProcessorDefinition;

public class MyStopEventAdapter implements CamelAdapter {
    @Override
    public void adapt(ProcessorDefinition processorDefinition) {
        processorDefinition.stop();
    }
}
