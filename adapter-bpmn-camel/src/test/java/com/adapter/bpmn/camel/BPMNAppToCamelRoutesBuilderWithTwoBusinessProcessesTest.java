package com.adapter.bpmn.camel;

import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.activity.SendTo;
import com.adapter.bpmn.model.flowobject.startevent.NamedStartEvent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.ArrayList;
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
        businessProcesses.add(new BusinessProcesses(new NamedStartEvent("direct:myRoute"), new SendTo("mock:out")));
        businessProcesses.add(new BusinessProcesses(new NamedStartEvent("direct:myRoute2"), new SendTo("mock:out2")));

        BPMNApp bpmnApp = new BPMNApp(businessProcesses);

        Map<Class<? extends FlowObject>, CamelAdapterFactory> dictionary = DefaultBPMNToCamelDictionary.INSTANCE.getDictionary();


        return new BPMNAppToCamelRoutesBuilder().buildCamelRoutes(bpmnApp, dictionary);
    }

}
