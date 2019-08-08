package com.adapter.bpmn.camel.infolog;

import com.adapter.bpmn.camel.CamelAdapter;
import org.apache.camel.model.ProcessorDefinition;

public class InfoLogAdapter implements CamelAdapter {


    @Override
    public void adapt(ProcessorDefinition processorDefinition) {
        processorDefinition.log("Message received: ${body}");
    }
}
