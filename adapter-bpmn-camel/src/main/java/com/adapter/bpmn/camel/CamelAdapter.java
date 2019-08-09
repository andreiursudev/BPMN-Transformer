package com.adapter.bpmn.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteDefinition;

public interface CamelAdapter {

    default void adapt(ProcessorDefinition processorDefinition, BPMNToCamelDictionary dictionary) {
    }

    default RouteDefinition adapt(RouteBuilder routeBuilder) {
        return null;
    }
}
