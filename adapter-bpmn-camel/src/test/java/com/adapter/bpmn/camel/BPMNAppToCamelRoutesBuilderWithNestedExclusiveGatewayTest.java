package com.adapter.bpmn.camel;

import com.adapter.bpmn.camel.exclusivegateway.ExclusiveGatewayCamelAdapterFactory;
import com.adapter.bpmn.camel.activity.sendto.SendToCamelAdapterFactory;
import com.adapter.bpmn.camel.startevent.StartFromCamelAdapterFactory;
import com.adapter.bpmn.model.expression.IsNotEmpty;
import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.camel.testapp.IsHelloWorld;
import com.adapter.bpmn.model.flowobject.exclusivegateway.ExclusiveGateway;
import com.adapter.bpmn.model.flowobject.startevent.StartFrom;
import com.adapter.bpmn.model.flowobject.activity.SendTo;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BPMNAppToCamelRoutesBuilderWithNestedExclusiveGatewayTest extends CamelTestSupport {

    @Test
    public void test() throws Exception {
        MockEndpoint outMockEndpoint = getMockEndpoint("mock:out");
        outMockEndpoint.expectedMessageCount(1);

        template.sendBody("direct:myRoute", "Hello World");

        outMockEndpoint.assertIsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        List<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new StartFrom("direct:myRoute"),new ExclusiveGateway(new ConditionalFlow(new IsNotEmpty(),new ExclusiveGateway(new ConditionalFlow(new IsHelloWorld(), new SendTo("mock:out")))))));

        BPMNApp bpmnApp = new BPMNApp(businessProcesses);

        Map<Class<? extends FlowObject>, CamelAdapterFactory> dictionary = DefaultBPMNToCamelDictionary.INSTANCE.getDictionary();

        return new BPMNAppToCamelRoutesBuilder().buildCamelRoutes(bpmnApp, dictionary);
    }

}
