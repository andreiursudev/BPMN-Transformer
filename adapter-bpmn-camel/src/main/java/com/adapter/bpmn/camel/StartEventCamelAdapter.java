package com.adapter.bpmn.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteDefinition;

public abstract class StartEventCamelAdapter implements CamelAdapter {
    @Override
    public void adapt(ProcessorDefinition processorDefinition, BPMNToCamelDictionary dictionary) {

    }

    public abstract RouteDefinition adapt(RouteBuilder routeBuilder);
}
