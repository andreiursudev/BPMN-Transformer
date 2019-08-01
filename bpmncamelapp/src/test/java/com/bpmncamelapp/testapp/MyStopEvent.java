package com.bpmncamelapp.testapp;

import com.bpmncamelapp.CamelAdapter;
import ro.rodin.adapter.bpmn.model.flowobject.StopEvent;

public class MyStopEvent implements StopEvent{
    @Override
    public CamelAdapter getAdapter() {
        return new MyStopEventAdapter();
    }
}
