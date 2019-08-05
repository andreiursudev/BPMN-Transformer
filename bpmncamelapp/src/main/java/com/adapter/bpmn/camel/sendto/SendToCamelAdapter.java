package com.adapter.bpmn.camel.sendto;

import com.adapter.bpmn.camel.CamelAdapter;
import org.apache.camel.model.ProcessorDefinition;

public class SendToCamelAdapter implements CamelAdapter {

    private String uri;

    public SendToCamelAdapter(String uri) {
        this.uri = uri;
    }

    @Override
    public void adapt(ProcessorDefinition processorDefinition) {
        processorDefinition.to(uri);
    }

}
