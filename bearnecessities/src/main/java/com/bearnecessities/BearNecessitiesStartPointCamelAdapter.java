package com.bearnecessities;

import com.adapter.bpmn.camel.StartEventCamelAdapter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

public class BearNecessitiesStartPointCamelAdapter extends StartEventCamelAdapter {

    @Override
    public RouteDefinition adapt(RouteBuilder routeBuilder) {
        return routeBuilder.from("file:data/inbox2?noop=true");
    }
}
