package com.adapter.bpmn.model.flowobject.endevent;

public class NamedEndEvent implements EndEvent {
    private String name;

    public NamedEndEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
