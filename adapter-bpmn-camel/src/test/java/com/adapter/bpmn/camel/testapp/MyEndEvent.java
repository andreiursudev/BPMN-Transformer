package com.adapter.bpmn.camel.testapp;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.model.flowobject.EndEvent;

public class MyEndEvent implements EndEvent {
    @Override
    public CamelAdapter getAdapter() {
        return new MyEndEventAdapter();
    }
}
