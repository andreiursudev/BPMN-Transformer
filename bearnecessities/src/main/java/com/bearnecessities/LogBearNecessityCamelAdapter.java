package com.bearnecessities;

import com.adapter.bpmn.camel.CamelAdapter;
import org.apache.camel.model.ProcessorDefinition;

public class LogBearNecessityCamelAdapter implements CamelAdapter {

    @Override
    public void adapt(ProcessorDefinition processorDefinition) {
        processorDefinition.log("This is a bear necessity!");
    }
}
