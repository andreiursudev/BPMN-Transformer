package com.bearnecessities;

import com.adapter.bpmn.camel.CamelAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;

import java.util.HashMap;
import java.util.Map;

public enum BearNecessitiesToCamelDictionary {

    INSTANCE;

    private final Map<Class<? extends FlowObject>, CamelAdapterFactory> dictionary;

    BearNecessitiesToCamelDictionary() {
        dictionary = new HashMap<>();
        dictionary.put(BearNecessitiesStartPoint.class, new BearNecessitiesStartPointCamelAdapterFactory());
        dictionary.put(LogBearNecessity.class, new LogBearNecessityCamelAdapterFactory());
    }

    public Map<Class<? extends FlowObject>, CamelAdapterFactory> getDictionary() {
        return dictionary;
    }
}
