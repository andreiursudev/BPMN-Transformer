package com.bearnecessities;

import com.adapter.bpmn.bpmnjs.BPMNToBPMNElementsDictionary;
import com.adapter.bpmn.bpmnjs.activity.NamedActivityBPMNElementAdapterFactory;
import com.adapter.bpmn.bpmnjs.startevent.StartFromBPMNElementAdapterFactory;
import com.adapter.bpmn.model.flowobject.activity.ConvertFileToString;
import com.adapter.bpmn.model.flowobject.activity.InfoLog;
import com.adapter.bpmn.model.flowobject.activity.SendTo;

public class BearNecessitiesBPMNJsDictionary extends BPMNToBPMNElementsDictionary {

    public BearNecessitiesBPMNJsDictionary() {
        getDictionary().put(ConvertFileToString.class, new NamedActivityBPMNElementAdapterFactory());
        getDictionary().put(InfoLog.class, new NamedActivityBPMNElementAdapterFactory());
        getDictionary().put(SendTo.class, new NamedActivityBPMNElementAdapterFactory());
        getDictionary().put(BearNecessitiesStartPoint.class, new StartFromBPMNElementAdapterFactory());
        getDictionary().put(LogBearNecessity.class, new NamedActivityBPMNElementAdapterFactory());
    }
}
