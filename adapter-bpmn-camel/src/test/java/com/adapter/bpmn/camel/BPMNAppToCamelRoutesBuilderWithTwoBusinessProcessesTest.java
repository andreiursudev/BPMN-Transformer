package com.adapter.bpmn.camel;

import com.adapter.bpmn.camel.activity.sendto.SendToCamelAdapterFactory;
import com.adapter.bpmn.camel.startevent.StartFrom;
import com.adapter.bpmn.camel.activity.sendto.SendTo;
import com.adapter.bpmn.camel.startevent.StartFromCamelAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import com.adapter.bpmn.model.BusinessProcesses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BPMNAppToCamelRoutesBuilderWithTwoBusinessProcessesTest extends CamelTestSupport {

    @Test
    public void test() throws Exception {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:out");
        mockEndpoint.expectedMessageCount(1);

        MockEndpoint mockEndpoint2 = getMockEndpoint("mock:out2");
        mockEndpoint2.expectedMessageCount(1);

        template.sendBody("direct:myRoute", "Hello World");
        template.sendBody("direct:myRoute2", "Hello World");

        mockEndpoint.assertIsSatisfied();
        mockEndpoint2.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        List<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new StartFrom("direct:myRoute"), new SendTo("mock:out")));
        businessProcesses.add(new BusinessProcesses(new StartFrom("direct:myRoute2"), new SendTo("mock:out2")));

        BPMNApp bpmnApp = new BPMNApp(businessProcesses);

        Map<Class<? extends FlowObject>, CamelAdapterFactory> language = new HashMap<>();
        language.put(StartFrom.class, new StartFromCamelAdapterFactory());
        language.put(SendTo.class, new SendToCamelAdapterFactory());

        return new BPMNAppToCamelRoutesBuilder().buildCamelRoutes(bpmnApp, language);
    }

}
