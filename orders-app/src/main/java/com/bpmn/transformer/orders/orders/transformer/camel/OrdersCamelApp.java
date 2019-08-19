package com.bpmn.transformer.orders.orders.transformer.camel;

import com.bpmn.transformer.camel.BPMNAppToCamelRoutesBuilder;
import com.bpmn.transformer.camel.BPMNCamelApp;
import com.bpmn.transformer.orders.orders.transformer.OrdersApp;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class OrdersCamelApp {

    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();

        OrdersApp ordersApp = new OrdersApp();
        BPMNCamelApp bpmnCamelApp = new BPMNCamelApp(ordersApp.getBusinessProcesses());
        camelContext.addRoutes(new BPMNAppToCamelRoutesBuilder().buildCamelRoutes(bpmnCamelApp, new OrdersCamelDictionary()));

        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();
    }
}
