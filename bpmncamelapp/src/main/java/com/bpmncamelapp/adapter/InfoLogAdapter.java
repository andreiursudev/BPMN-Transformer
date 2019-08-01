package com.bpmncamelapp.adapter;

import com.bpmncamelapp.CamelAdapter;
import org.apache.camel.model.ProcessorDefinition;

public class InfoLogAdapter implements CamelAdapter {


    @Override
    public void adapt(ProcessorDefinition processorDefinition) {
        processorDefinition.log("Message received: ${body}");
    }
}
