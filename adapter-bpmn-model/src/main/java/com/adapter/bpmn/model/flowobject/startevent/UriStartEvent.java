package com.adapter.bpmn.model.flowobject.startevent;

public class UriStartEvent implements StartEvent {

    private String uri;

    public UriStartEvent(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
