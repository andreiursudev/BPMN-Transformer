package com.bpmncamelapp.testapp;

import com.bpmncamelapp.CamelAdapter;
import com.bpmncamelapp.flowobject.StopEvent;

public class MyStopEvent implements StopEvent{
    @Override
    public CamelAdapter getCamelAdapter() {
        return new MyStopEventAdapter();
    }
}
