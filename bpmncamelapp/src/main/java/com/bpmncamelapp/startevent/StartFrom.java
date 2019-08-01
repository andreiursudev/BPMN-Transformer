package com.bpmncamelapp.startevent;

import com.bpmncamelapp.CamelAdapter;
import ro.rodin.adapter.bpmn.model.flowobject.StartEvent;

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
