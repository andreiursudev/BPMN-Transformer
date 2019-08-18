package com.bpmn.transformer.orders.orders;

import com.bpmn.transformer.diagram.BPMNJsDiagram;
import com.bpmn.transformer.orders.orders.diagram.OrdersBPMNElementDictionary;

public class OrderDiagramApp {

    public static void main(String[] args) {
        BPMNJsDiagram diagram = new BPMNJsDiagram();
        String xmlDiagram = diagram.asXml(new OrdersApp().getBusinessProcesses(), new OrdersBPMNElementDictionary());
        System.out.println(xmlDiagram);
    }
}
