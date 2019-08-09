package com.bearnecessities;

import com.adapter.bpmn.camel.BPMNToCamelDictionary;

public class BearNecessitiesDictionaryBPMNTo extends BPMNToCamelDictionary {

    BearNecessitiesDictionaryBPMNTo() {
        getDictionary().put(BearNecessitiesStartPoint.class, new BearNecessitiesStartPointCamelAdapterFactory());
        getDictionary().put(LogBearNecessity.class, new LogBearNecessityCamelAdapterFactory());
    }
}
