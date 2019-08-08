package com.adapter.bpmn.camel;

import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.StartEvent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

import java.util.Map;


public class BPMNAppToCamelRoutesBuilder {

    public RouteBuilder buildCamelRoutes(final BPMNApp app, Map<Class<? extends FlowObject>, CamelAdapterFactory> dictionary) {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                for (BusinessProcesses businessProcess : app.getBusinessProcesses()) {
                    StartEvent startEvent = businessProcess.getStartEvent();
                    RouteDefinition routeDefinition = dictionary.get(startEvent.getClass()).getAdapter(startEvent).adapt(this);
                    businessProcess.getFlowObjects().forEach(flowObject -> dictionary.get(flowObject.getClass()).getAdapter(flowObject).adapt(routeDefinition));
                }
            }
        };
    }

}
