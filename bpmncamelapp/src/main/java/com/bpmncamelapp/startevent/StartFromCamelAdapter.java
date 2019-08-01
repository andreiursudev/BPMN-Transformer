package com.bpmncamelapp.startevent;

import com.bpmncamelapp.CamelAdapter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

public class StartFromCamelAdapter implements CamelAdapter {

    private String uri;

    public StartFromCamelAdapter(String uri) {
        this.uri = uri;
    }

    public RouteDefinition adapt(RouteBuilder routeBuilder) {
        return routeBuilder.from(uri);
    }
}
