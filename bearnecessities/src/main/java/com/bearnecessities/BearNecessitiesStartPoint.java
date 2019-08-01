package com.bearnecessities;

import com.bpmncamelapp.CamelAdapter;
import ro.rodin.adapter.bpmn.model.flowobject.StartEvent;

public class BearNecessitiesStartPoint implements StartEvent {
    @Override
    public CamelAdapter getAdapter() {
        return new BearNecessitiesStartPointCamelAdapter();
    }
}
