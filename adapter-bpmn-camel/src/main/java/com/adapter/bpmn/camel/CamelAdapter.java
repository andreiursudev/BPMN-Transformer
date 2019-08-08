package com.adapter.bpmn.camel;

import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteDefinition;

import java.util.Map;

public interface CamelAdapter {

    default void adapt(ProcessorDefinition processorDefinition, Map<? extends Class<? extends FlowObject>, CamelAdapterFactory> dictionary) {
    }

    default RouteDefinition adapt(RouteBuilder routeBuilder) {
        return null;
    }
}
