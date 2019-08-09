package com.adapter.bpmn.model.flowobject.startevent;

public class NamedStartEvent implements StartEvent {

    private String name;

    public NamedStartEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
