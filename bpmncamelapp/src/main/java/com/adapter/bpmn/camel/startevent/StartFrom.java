package com.adapter.bpmn.camel.startevent;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.model.flowobject.StartEvent;

public class StartFrom implements StartEvent {

    private String uri;

    public StartFrom(String uri) {
        this.uri = uri;
    }

    @Override
    public CamelAdapter getAdapter() {
        return new StartFromCamelAdapter(uri);
    }
}
