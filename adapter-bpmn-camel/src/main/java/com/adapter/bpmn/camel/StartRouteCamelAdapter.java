package com.adapter.bpmn.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteDefinition;

public interface StartRouteCamelAdapter {
    RouteDefinition adapt(RouteBuilder routeBuilder);
}
