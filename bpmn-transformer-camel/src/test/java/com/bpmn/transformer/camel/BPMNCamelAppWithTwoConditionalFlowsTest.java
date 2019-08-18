package com.bpmn.transformer.camel;

import com.bpmn.transformer.camel.testapp.IsHelloJohn;
import com.bpmn.transformer.camel.testapp.IsHelloWorld;
import com.bpmn.transformer.model.BusinessProcess;
import com.bpmn.transformer.model.connectingobject.ConditionalFlow;
import com.bpmn.transformer.model.flowobject.activity.SendTo;
import com.bpmn.transformer.model.flowobject.exclusivegateway.ExclusiveGateway;
import com.bpmn.transformer.model.flowobject.startevent.UriStartEvent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BPMNCamelAppWithTwoConditionalFlowsTest extends CamelTestSupport {

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
        List<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcess(new UriStartEvent("direct:myRoute"), new ExclusiveGateway(new ConditionalFlow(new IsHelloWorld(), new SendTo("mock:out")), new ConditionalFlow(new IsHelloJohn(), new SendTo("mock:outJohn")))));

        BPMNCamelApp bpmnCamelApp = new BPMNCamelApp(businessProcesses);

        return new BPMNAppToCamelRoutesBuilder().buildCamelRoutes(bpmnCamelApp, new BPMNToCamelDictionary());
    }

}
