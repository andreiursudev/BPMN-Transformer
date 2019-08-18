package com.bpmn.transformer.orders.orders.diagram;

import com.bpmn.transformer.diagram.BPMNModelToBPMNElementsDictionary;
import com.bpmn.transformer.diagram.activity.NamedActivityBPMNElementAdapterFactory;
import com.bpmn.transformer.model.flowobject.activity.InfoLog;
import com.bpmn.transformer.model.flowobject.activity.SendTo;
import com.bpmn.transformer.orders.orders.OrderStartEvent;

public class OrdersBPMNElementDictionary extends BPMNModelToBPMNElementsDictionary {

    public OrdersBPMNElementDictionary() {
        getDictionary().put(OrderStartEvent.class, new TimerEventBPMNElementAdapterFactory());
        getDictionary().put(InfoLog.class, new NamedActivityBPMNElementAdapterFactory());
        getDictionary().put(SendTo.class, new NamedActivityBPMNElementAdapterFactory());
    }
}
