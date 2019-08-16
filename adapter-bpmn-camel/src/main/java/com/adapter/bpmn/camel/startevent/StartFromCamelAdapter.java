package com.adapter.bpmn.camel.startevent;

import com.adapter.bpmn.camel.StartEventCamelAdapter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

public class StartFromCamelAdapter extends StartEventCamelAdapter {

    private String uri;

    public StartFromCamelAdapter(String uri) {
        this.uri = uri;
    }

    public RouteDefinition adapt(RouteBuilder routeBuilder) {
        return routeBuilder.from(uri);
    }
}
