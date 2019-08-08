package com.adapter.bpmn.camel.activity.sendto;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.camel.CamelAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.camel.model.ProcessorDefinition;

import java.util.Map;

public class SendToCamelAdapter implements CamelAdapter {

    private String uri;

    public SendToCamelAdapter(String uri) {
        this.uri = uri;
    }

    @Override
    public void adapt(ProcessorDefinition processorDefinition, Map<? extends Class<? extends FlowObject>, CamelAdapterFactory> dictionary) {
        processorDefinition.to(uri);
    }

}
