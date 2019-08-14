package com.bearnecessities;

import com.adapter.bpmn.model.flowobject.startevent.NamedStartEvent;
import com.adapter.bpmn.model.flowobject.startevent.StartEvent;

public class BearNecessitiesStartPoint extends NamedStartEvent {
    public BearNecessitiesStartPoint() {
        super("BearNecessitiesStartPoint");
    }
}
