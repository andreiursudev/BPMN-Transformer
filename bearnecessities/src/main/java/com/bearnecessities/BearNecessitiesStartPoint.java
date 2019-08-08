package com.bearnecessities;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.model.flowobject.startevent.StartEvent;

public class BearNecessitiesStartPoint implements StartEvent {
    @Override
    public CamelAdapter getAdapter() {
        return new BearNecessitiesStartPointCamelAdapter();
    }
}
