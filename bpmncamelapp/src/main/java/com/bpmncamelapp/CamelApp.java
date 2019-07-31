package com.bpmncamelapp;

import com.bpmncamelapp.flowobject.FlowObject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

import java.util.List;


public class CamelApp {

    public RouteBuilder buildCamelApp(final BPMNCamelApp app) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                List<BusinessProcesses> businessProcesses = app.getBusinessProcesses();
                for (BusinessProcesses businessProcess : businessProcesses) {
                    RouteDefinition routeDefinition = getRouteDefinition(businessProcess.getStartEvent());
                    businessProcess.getFlowObject().forEach(flowObject -> flowObject.getCamelAdapter().adapt(routeDefinition));
                }

            }

            private RouteDefinition getRouteDefinition(FlowObject startEvent) {
                return startEvent.getCamelAdapter().adapt(this);
            }
        };
    }

}
