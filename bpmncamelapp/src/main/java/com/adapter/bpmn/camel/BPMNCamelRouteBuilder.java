package com.adapter.bpmn.camel;

import com.adapter.bpmn.model.flowobject.StartEvent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import com.adapter.bpmn.model.BusinessProcesses;

import java.util.List;


public class BPMNCamelRouteBuilder {

    public RouteBuilder buildCamelRoutes(final BPMNCamelApp app) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                List<BusinessProcesses> businessProcesses = app.getBusinessProcesses();
                for (BusinessProcesses businessProcess : businessProcesses) {
                    RouteDefinition routeDefinition = getRouteDefinition(businessProcess.getStartEvent());
                    businessProcess.getFlowObjects().forEach(flowObject -> ((CamelAdapter)flowObject.getAdapter()).adapt(routeDefinition));
                }

            }

            private RouteDefinition getRouteDefinition(StartEvent startEvent) {
                return ((CamelAdapter)startEvent.getAdapter()).adapt(this);
            }
        };
    }

}
