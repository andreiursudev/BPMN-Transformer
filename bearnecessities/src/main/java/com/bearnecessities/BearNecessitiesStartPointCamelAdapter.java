package com.bearnecessities;

import com.bpmncamelapp.CamelAdapter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

public class BearNecessitiesStartPointCamelAdapter implements CamelAdapter {

    @Override
    public RouteDefinition adapt(RouteBuilder routeBuilder) {
        return routeBuilder.from("file:data/inbox?noop=true");
    }
}
