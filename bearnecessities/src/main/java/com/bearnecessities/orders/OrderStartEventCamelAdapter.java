package com.bearnecessities.orders;

import com.adapter.bpmn.camel.StartEventCamelAdapter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

public class OrderStartEventCamelAdapter extends StartEventCamelAdapter {
    private String directoryName;

    public OrderStartEventCamelAdapter(String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public RouteDefinition adapt(RouteBuilder routeBuilder) {
        return routeBuilder.from("file:"+directoryName + "?noop=true");
    }
}
