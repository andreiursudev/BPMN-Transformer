package com.adapter.bpmn.camel.activity.sendto;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.camel.BPMNToCamelDictionary;
import org.apache.camel.model.ProcessorDefinition;

public class SendToCamelAdapter implements CamelAdapter {

    private String uri;

    public SendToCamelAdapter(String uri) {
        this.uri = uri;
    }

    @Override
    public void adapt(ProcessorDefinition processorDefinition, BPMNToCamelDictionary dictionary) {
        processorDefinition.to(uri);
    }

}
