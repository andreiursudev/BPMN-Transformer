package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.model.flowobject.FlowObject;

import java.util.HashMap;
import java.util.Map;

public enum BPMNToBPMNJsDictionary {

    INSTANCE;

    /*private final Map<Class<? extends FlowObject>, CamelAdapterFactory> dictionary;

    BPMNToBPMNJsDictionary() {
        dictionary = new HashMap<>();
        dictionary.put(StartFrom.class, new StartFromCamelAdapterFactory());
        dictionary.put(SendTo.class, new SendToCamelAdapterFactory());
        dictionary.put(ExclusiveGateway.class, new ExclusiveGatewayCamelAdapterFactory());
        dictionary.put(InfoLog.class, new InfoLogCamelAdapterFactory());
    }

    public Map<Class<? extends FlowObject>, CamelAdapterFactory> getDictionary() {
        return dictionary;
    }*/
}
