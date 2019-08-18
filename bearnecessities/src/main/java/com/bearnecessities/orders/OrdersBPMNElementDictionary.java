package com.bearnecessities.orders;

import com.adapter.bpmn.bpmnjs.BPMNModelToBPMNElementsDictionary;
import com.adapter.bpmn.bpmnjs.activity.NamedActivityBPMNElementAdapterFactory;
import com.adapter.bpmn.model.flowobject.activity.InfoLog;
import com.adapter.bpmn.model.flowobject.activity.SendTo;

public class OrdersBPMNElementDictionary extends BPMNModelToBPMNElementsDictionary {

    public OrdersBPMNElementDictionary() {
        getDictionary().put(OrderStartEvent.class, new TimerEventBPMNElementAdapterFactory());
        getDictionary().put(InfoLog.class, new NamedActivityBPMNElementAdapterFactory());
        getDictionary().put(SendTo.class, new NamedActivityBPMNElementAdapterFactory());
    }
}
