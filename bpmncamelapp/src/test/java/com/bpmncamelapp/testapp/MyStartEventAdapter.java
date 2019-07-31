package com.bpmncamelapp.testapp;

import com.bpmncamelapp.CamelAdapter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

public class MyStartEventAdapter implements CamelAdapter {
    public RouteDefinition adapt(RouteBuilder routeBuilder) {
        return routeBuilder.from("direct:myRoute");
    }
}
