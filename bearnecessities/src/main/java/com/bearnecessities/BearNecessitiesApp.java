package com.bearnecessities;

import com.adapter.bpmn.camel.BPMNApp;
import com.adapter.bpmn.camel.BPMNAppToCamelRoutesBuilder;
import com.adapter.bpmn.camel.CamelAdapterFactory;
import com.adapter.bpmn.camel.Language;
import com.adapter.bpmn.camel.infolog.InfoLog;
import com.adapter.bpmn.camel.exclusivegateway.ExclusiveGateway;
import com.adapter.bpmn.camel.sendto.SendTo;
import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.ArrayList;
import java.util.Map;

public class BearNecessitiesApp {
    public static void main(String[] args) throws Exception {

        ArrayList<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new BearNecessitiesStartPoint(), new InfoLog(), new LogBearNecessity(), new SendTo("mock:out"), new ExclusiveGateway(new ConditionalFlow(new IsFood(), new InfoLog()))));
        BPMNApp app =  new BPMNApp(businessProcesses);

        CamelContext camelContext = new DefaultCamelContext();


        Map<Class<? extends FlowObject>, CamelAdapterFactory> dictionary = Language.INSTANCE.getDictionary();
        dictionary.put(BearNecessitiesStartPoint.class, new BearNecessitiesStartPointCamelAdapterFactory());
        dictionary.put(LogBearNecessity.class, new LogBearNecessityCamelAdapterFactory());

        camelContext.addRoutes(new BPMNAppToCamelRoutesBuilder().buildCamelRoutes(app, dictionary));
        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();

        /*BPMNJsDiagram diagram = new BPMNJsDiagram(app.getBusinessProcesses());
        String xmlDiagram = diagram.asXml();
        System.out.println(xmlDiagram);*/



    }
}
