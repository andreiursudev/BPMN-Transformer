package com.bpmncamelapp;

import com.bpmncamelapp.startevent.StartFrom;
import com.bpmncamelapp.sendto.SendTo;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import ro.rodin.adapter.bpmn.model.BusinessProcesses;

import java.util.ArrayList;
import java.util.List;

public class BPMNCamelRouteBuilderWithTwoBusinessProcessesTest extends CamelTestSupport {

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

        BPMNCamelApp bpmnCamelApp = new BPMNCamelApp(businessProcesses);

        return new BPMNCamelRouteBuilder().buildCamelRoutes(bpmnCamelApp);
    }

}
