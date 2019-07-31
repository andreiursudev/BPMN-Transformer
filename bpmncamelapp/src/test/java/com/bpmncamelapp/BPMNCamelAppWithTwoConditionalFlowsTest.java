package com.bpmncamelapp;

import com.bpmncamelapp.connectingobject.ConditionalFlow;
import com.bpmncamelapp.flowobject.ExclusiveGateway;
import com.bpmncamelapp.testapp.IsHelloJohn;
import com.bpmncamelapp.testapp.IsHelloWorld;
import com.bpmncamelapp.testapp.MyStartEvent;
import com.bpmncamelapp.testapp.ToMock;
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
        List<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new MyStartEvent(), new ExclusiveGateway(new ConditionalFlow(new IsHelloWorld(), new ToMock("mock:out")), new ConditionalFlow(new IsHelloJohn(), new ToMock("mock:outJohn")))));

        BPMNCamelApp bpmnCamelApp = new BPMNCamelApp(businessProcesses);

        return new CamelApp().buildCamelApp(bpmnCamelApp);
    }

}
