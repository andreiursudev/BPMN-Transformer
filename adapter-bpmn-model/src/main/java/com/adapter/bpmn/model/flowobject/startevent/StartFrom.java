package com.adapter.bpmn.model.flowobject.startevent;

public class StartFrom implements StartEvent {

    private String uri;

    public StartFrom(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
