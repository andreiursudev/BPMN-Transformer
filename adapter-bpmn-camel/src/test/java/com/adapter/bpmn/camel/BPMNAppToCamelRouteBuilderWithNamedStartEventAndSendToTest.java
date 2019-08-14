package com.adapter.bpmn.camel;

import com.adapter.bpmn.model.BusinessProcess;
import com.adapter.bpmn.model.flowobject.activity.SendTo;
import com.adapter.bpmn.model.flowobject.startevent.NamedStartEvent;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BPMNAppToCamelRouteBuilderWithNamedStartEventAndSendToTest extends CamelTestSupport {

    @Test
    public void test() throws Exception {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:out");

        template.sendBody("direct:myRoute", "Hello World");

        Exchange exchange = mockEndpoint.getExchanges().get(0);
        assertEquals("Hello World", exchange.getIn().getBody(String.class));
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        List<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcess(new NamedStartEvent("direct:myRoute"), new SendTo("mock:out")));

        BPMNApp bpmnApp = new BPMNApp(businessProcesses);

        return new BPMNAppToCamelRoutesBuilder().buildCamelRoutes(bpmnApp, new BPMNToCamelDictionary());
    }

}


