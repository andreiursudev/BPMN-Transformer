package com.bearnecessities;

import com.adapter.bpmn.bpmnjs.BPMNJsDiagram;
import com.adapter.bpmn.bpmnjs.BPMNToBPMNElementsDictionary;
import com.adapter.bpmn.camel.BPMNApp;
import com.adapter.bpmn.camel.BPMNAppToCamelRoutesBuilder;
import com.adapter.bpmn.model.BusinessProcess;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.model.expression.IsNotEmpty;
import com.adapter.bpmn.model.flowobject.activity.ConvertFileToString;
import com.adapter.bpmn.model.flowobject.activity.InfoLog;
import com.adapter.bpmn.model.flowobject.activity.SendTo;
import com.adapter.bpmn.model.flowobject.exclusivegateway.ExclusiveGateway;
import com.adapter.bpmn.model.flowobject.startevent.NamedStartEvent;
import org.apache.camel.*;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.ArrayList;

public class BearNecessitiesApp {
    public static void main(String[] args) throws Exception {
        ArrayList<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcess(new NamedStartEvent("file:data/inbox?noop=true"), new ConvertFileToString(""), new InfoLog(""), new SendTo("mock:out"), new ExclusiveGateway(new ConditionalFlow(new IsNotEmpty(), new InfoLog("")))));
        businessProcesses.add(new BusinessProcess(new BearNecessitiesStartPoint(), new LogBearNecessity()));
        BPMNApp app = new BPMNApp(businessProcesses);


        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addRoutes(new BPMNAppToCamelRoutesBuilder().buildCamelRoutes(app, new BearNecessitiesDictionaryBPMNTo()));

      /*  camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();*/


        BPMNJsDiagram diagram = new BPMNJsDiagram();
        String xmlDiagram = diagram.asXml(businessProcesses, new BearNecessitiesBPMNJsDictionary());
        System.out.println(xmlDiagram);
    }
}
