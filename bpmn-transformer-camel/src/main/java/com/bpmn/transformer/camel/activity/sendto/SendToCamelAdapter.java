package com.bpmn.transformer.camel.activity.sendto;

import com.bpmn.transformer.camel.CamelAdapter;
import com.bpmn.transformer.camel.BPMNToCamelDictionary;
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
