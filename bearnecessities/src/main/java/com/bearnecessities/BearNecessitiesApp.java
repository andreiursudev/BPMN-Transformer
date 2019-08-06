package com.bearnecessities;

import com.adapter.bpmn.bpmnjs.BPMNJsDiagram;
import com.adapter.bpmn.bpmnjs.BPMNJsHtmlDiagram;
import com.adapter.bpmn.camel.BPMNCamelApp;
import com.adapter.bpmn.camel.BPMNCamelRouteBuilder;
import com.adapter.bpmn.camel.adapter.InfoLog;
import com.adapter.bpmn.camel.exclusivegateway.ExclusiveGateway;
import com.adapter.bpmn.camel.sendto.SendTo;
import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import java.io.File;
import java.util.ArrayList;

public class BearNecessitiesApp {
    public static void main(String[] args) throws Exception {
        BPMNCamelApp app = getBpmnCamelApp();

        BPMNJsDiagram diagram = new BPMNJsDiagram(app.getBusinessProcesses());
        String xmlDiagram = diagram.asXml();
        BPMNJsHtmlDiagram htmlDiagram = new BPMNJsHtmlDiagram(xmlDiagram);
        htmlDiagram.writeToFile(new File(""));


        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new BPMNCamelRouteBuilder().buildCamelRoutes(app));
        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();
    }

    private static BPMNCamelApp getBpmnCamelApp() {
        ArrayList<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new BearNecessitiesStartPoint(), new InfoLog(), new SendTo("mock:out"), new ExclusiveGateway(new ConditionalFlow(new IsFood(), new InfoLog()))));
        return new BPMNCamelApp(businessProcesses);
    }
}
