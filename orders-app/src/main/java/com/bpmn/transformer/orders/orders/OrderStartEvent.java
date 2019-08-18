package com.bpmn.transformer.orders.orders;

import com.bpmn.transformer.model.flowobject.startevent.StartEvent;

public class OrderStartEvent implements StartEvent {
    private String directoryName;

    public OrderStartEvent(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getDirectoryName() {
        return directoryName;
    }
}
