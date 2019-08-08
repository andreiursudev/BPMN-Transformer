package com.adapter.bpmn.camel;

import com.adapter.bpmn.camel.sendto.SendTo;
import com.adapter.bpmn.camel.sendto.SendToCamelAdapterFactory;
import com.adapter.bpmn.camel.startevent.StartFrom;
import com.adapter.bpmn.camel.startevent.StartFromCamelAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import com.adapter.bpmn.model.BusinessProcesses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BPMNAppToCamelRouteBuilderWithStartFromAndSendToTest extends CamelTestSupport {

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

        BPMNApp bpmnApp = new BPMNApp(businessProcesses);
        Map<Class<? extends FlowObject>, CamelAdapterFactory> language = new HashMap<>();
        language.put(StartFrom.class, new StartFromCamelAdapterFactory());
        language.put(SendTo.class, new SendToCamelAdapterFactory());
        return new BPMNAppToCamelRoutesBuilder().buildCamelRoutes(bpmnApp, language);
    }

}


