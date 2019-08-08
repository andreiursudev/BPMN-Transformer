package com.bearnecessities;

import com.adapter.bpmn.camel.CamelAdapter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

public class BearNecessitiesStartPointCamelAdapter implements CamelAdapter {

    @Override
    public RouteDefinition adapt(RouteBuilder routeBuilder) {
        return routeBuilder.from("file:data/inbox2?noop=true");
    }
}
