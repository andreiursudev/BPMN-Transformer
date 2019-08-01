package com.bpmncamelapp;

import com.bpmncamelapp.startevent.StartFrom;
import com.bpmncamelapp.sendto.SendTo;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import ro.rodin.adapter.bpmn.model.BusinessProcesses;

import java.util.ArrayList;
import java.util.List;

public class BPMNCamelRouteBuilderWithStartFromAndSendToTest extends CamelTestSupport {

    @Test
    public void test() throws Exception {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:out");

        template.sendBody("direct:myRoute", "Hello World");

        Exchange exchange = mockEndpoint.getExchanges().get(0);
        assertEquals("Hello World", exchange.getIn().getBody(String.class));
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        List<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new StartFrom("direct:myRoute"), new SendTo("mock:out")));

        BPMNCamelApp bpmnCamelApp = new BPMNCamelApp(businessProcesses);

        return new BPMNCamelRouteBuilder().buildCamelRoutes(bpmnCamelApp);
    }

}

