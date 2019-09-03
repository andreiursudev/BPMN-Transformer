package com.bpmn.transformer.orders.orders.transformer.diagram;

import com.bpmn.transformer.diagram.BPMNModelToBPMNElementsDictionary;
import com.bpmn.transformer.diagram.activity.NamedActivityBPMNElementAdapterFactory;
import com.bpmn.transformer.diagram.endevent.NamedEndEventBPMNElementAdapterFactory;
import com.bpmn.transformer.orders.orders.transformer.diagram.element.OrderStartEventBPMNElementAdapterFactory;
import com.bpmn.transformer.orders.orders.transformer.flowobject.*;

public class OrdersBPMNElementDictionary extends BPMNModelToBPMNElementsDictionary {

    public OrdersBPMNElementDictionary() {
        getDictionary().put(OrderStartEvent.class, new OrderStartEventBPMNElementAdapterFactory());
        getDictionary().put(CheckAvailability.class, new NamedActivityBPMNElementAdapterFactory());
        getDictionary().put(Procurement.class, new NamedActivityBPMNElementAdapterFactory());
        getDictionary().put(ShipArticle.class, new NamedActivityBPMNElementAdapterFactory());
        getDictionary().put(SendInvoice.class, new NamedEndEventBPMNElementAdapterFactory());
    }
}
