package com.adapter.bpmn.camel;

import com.adapter.bpmn.camel.testapp.IsHelloJohn;
import com.adapter.bpmn.camel.testapp.IsHelloWorld;
import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.activity.SendTo;
import com.adapter.bpmn.model.flowobject.exclusivegateway.ExclusiveGateway;
import com.adapter.bpmn.model.flowobject.startevent.NamedStartEvent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BPMNAppToCamelRoutesBuilderWithTwoConditionalFlowsTest extends CamelTestSupport {

    @Test
    public void test() throws Exception {
        MockEndpoint outMockEndpoint = getMockEndpoint("mock:out");
        outMockEndpoint.expectedMessageCount(1);

        MockEndpoint outJohnMockEndpoint = getMockEndpoint("mock:outJohn");
        outJohnMockEndpoint.expectedMessageCount(1);

        template.sendBody("direct:myRoute", "Hello World");
        template.sendBody("direct:myRoute", "Hello John");

        outMockEndpoint.assertIsSatisfied();
        outJohnMockEndpoint.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        List<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new NamedStartEvent("direct:myRoute"), new ExclusiveGateway(new ConditionalFlow(new IsHelloWorld(), new SendTo("mock:out")), new ConditionalFlow(new IsHelloJohn(), new SendTo("mock:outJohn")))));

        BPMNApp bpmnApp = new BPMNApp(businessProcesses);

        Map<Class<? extends FlowObject>, CamelAdapterFactory> dictionary = DefaultBPMNToCamelDictionary.INSTANCE.getDictionary();

        return new BPMNAppToCamelRoutesBuilder().buildCamelRoutes(bpmnApp, dictionary);
    }

}
