package com.adapter.bpmn.camel.testapp;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.model.flowobject.StopEvent;

public class MyStopEvent implements StopEvent{
    @Override
    public CamelAdapter getAdapter() {
        return new MyStopEventAdapter();
    }
}
