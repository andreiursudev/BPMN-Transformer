package com.bearnecessities.orders;

import com.adapter.bpmn.model.flowobject.startevent.StartEvent;

public class OrderStartEvent implements StartEvent {
    private String directoryName;

    public OrderStartEvent(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getDirectoryName() {
        return directoryName;
    }
}
