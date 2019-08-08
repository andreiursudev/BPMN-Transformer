package com.adapter.bpmn.camel.activity.convertfiletostring;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.camel.CamelAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.camel.model.ProcessorDefinition;

import java.util.Map;

public class ConvertFileToStringAdapter implements CamelAdapter {

    @Override
    public void adapt(ProcessorDefinition processorDefinition, Map<? extends Class<? extends FlowObject>, CamelAdapterFactory> dictionary) {
        processorDefinition.convertBodyTo(String.class);
    }
}
