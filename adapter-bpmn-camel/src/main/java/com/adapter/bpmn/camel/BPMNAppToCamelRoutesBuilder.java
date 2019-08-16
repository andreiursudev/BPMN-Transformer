package com.adapter.bpmn.camel;

import com.adapter.bpmn.model.BusinessProcess;
import com.adapter.bpmn.model.flowobject.startevent.StartEvent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;


public class BPMNAppToCamelRoutesBuilder {

    public RouteBuilder buildCamelRoutes(final BPMNApp app, BPMNToCamelDictionary dictionary) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                for (BusinessProcess businessProcess : app.getBusinessProcesses()) {
                    StartEvent startEvent = businessProcess.getStartEvent();
                    RouteDefinition routeDefinition = ((StartEventCamelAdapter)dictionary.getAdapter(startEvent)).adapt(this);
                    businessProcess.getFlowObjects().forEach(flowObject -> dictionary.getAdapter(flowObject).adapt(routeDefinition, dictionary));
                }
            }
        };
    }
}
