package com.bearnecessities;

import com.adapter.bpmn.bpmnjs.BPMNElementAdapterFactory;
import com.adapter.bpmn.bpmnjs.BPMNJsDiagram;
import com.adapter.bpmn.camel.BPMNApp;
import com.adapter.bpmn.camel.BPMNAppToCamelRoutesBuilder;
import com.adapter.bpmn.camel.CamelAdapterFactory;
import com.adapter.bpmn.camel.DefaultBPMNToCamelDictionary;
import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.model.expression.IsNotEmpty;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.activity.ConvertFileToString;
import com.adapter.bpmn.model.flowobject.activity.InfoLog;
import com.adapter.bpmn.model.flowobject.activity.SendTo;
import com.adapter.bpmn.model.flowobject.exclusivegateway.ExclusiveGateway;
import com.adapter.bpmn.model.flowobject.startevent.NamedStartEvent;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BearNecessitiesApp {
    public static void main(String[] args) throws Exception {
        Map<Class<? extends FlowObject>, CamelAdapterFactory> defaultDictionary = DefaultBPMNToCamelDictionary.INSTANCE.getDictionary();
        Map<Class<? extends FlowObject>, CamelAdapterFactory> bearNecessitiesDictionary = BearNecessitiesToCamelDictionary.INSTANCE.getDictionary();


        ArrayList<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new NamedStartEvent("file:data/inbox?noop=true"), new ConvertFileToString(""), new InfoLog(""), new SendTo("mock:out"), new ExclusiveGateway(new ConditionalFlow(new IsNotEmpty(), new InfoLog("")))));
        businessProcesses.add(new BusinessProcesses(new BearNecessitiesStartPoint(), new LogBearNecessity()));
        BPMNApp app = new BPMNApp(businessProcesses);


        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addRoutes(new BPMNAppToCamelRoutesBuilder().buildCamelRoutes(app, defaultDictionary, bearNecessitiesDictionary));

        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();


        Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary = new HashMap<>();
        BPMNJsDiagram diagram = new BPMNJsDiagram();
        String xmlDiagram = diagram.asXml(businessProcesses, dictionary);
        System.out.println(xmlDiagram);


    }
}
