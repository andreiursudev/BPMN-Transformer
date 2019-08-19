package com.bpmn.transformer.orders.orders.transformer.camel.element;

import com.bpmn.transformer.camel.StartEventCamelAdapter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

import static org.apache.camel.builder.Builder.body;

public class OrderStartEventCamelAdapter extends StartEventCamelAdapter {
    private String directoryName;

    public OrderStartEventCamelAdapter(String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public RouteDefinition adapt(RouteBuilder routeBuilder) {
        RouteDefinition from = routeBuilder.from("file:" + directoryName + "?noop=true");
        from.convertBodyTo(String.class);
        from.setProperty("order.body",body());
        return from;
    }
}