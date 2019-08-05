package com.bearnecessities;

import com.adapter.bpmn.camel.BPMNCamelApp;
import com.adapter.bpmn.camel.BPMNCamelRouteBuilder;
import com.adapter.bpmn.camel.adapter.InfoLog;
import com.adapter.bpmn.camel.sendto.SendTo;
import com.adapter.bpmn.model.BusinessProcesses;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.ArrayList;

public class BearNecessitiesApp {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();

        ArrayList<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new BearNecessitiesStartPoint(), new InfoLog(), new SendTo("mock:out")));
        BPMNCamelApp app = new BPMNCamelApp(businessProcesses);

        camelContext.addRoutes(new BPMNCamelRouteBuilder().buildCamelRoutes(app));
        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();
    }
}
