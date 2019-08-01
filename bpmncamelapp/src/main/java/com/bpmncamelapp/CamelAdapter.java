package com.bpmncamelapp;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteDefinition;
import ro.rodin.adapter.bpmn.model.Adapter;

public interface CamelAdapter extends Adapter{

    default void adapt(ProcessorDefinition processorDefinition) {
    }

    default RouteDefinition adapt(RouteBuilder routeBuilder) {
        return null;
    }
}
