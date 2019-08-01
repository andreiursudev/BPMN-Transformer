package com.bpmncamelapp;

import com.bpmncamelapp.exclusivegateway.ExclusiveGateway;
import ro.rodin.adapter.bpmn.model.BusinessProcesses;
import ro.rodin.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.bpmncamelapp.testapp.IsHelloWorld;
import com.bpmncamelapp.startevent.StartFrom;
import com.bpmncamelapp.sendto.SendTo;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BPMNCamelRouteBuilderWithExclusiveGatewayTest extends CamelTestSupport {

    @Test
    public void test() throws Exception {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:out");
        mockEndpoint.expectedMessageCount(1);

        template.sendBody("direct:myRoute", "Hello World");

        mockEndpoint.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        List<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new StartFrom("direct:myRoute"), new ExclusiveGateway(new ConditionalFlow(new IsHelloWorld(), new SendTo("mock:out")))));

        BPMNCamelApp bpmnCamelApp = new BPMNCamelApp(businessProcesses);

        return new BPMNCamelRouteBuilder().buildCamelRoutes(bpmnCamelApp);
    }

}
