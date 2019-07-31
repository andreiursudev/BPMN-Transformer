package com.bpmncamelapp.testapp;

import com.bpmncamelapp.CamelAdapter;
import com.bpmncamelapp.flowobject.StartEvent;

public class MyStartEvent implements StartEvent {
    @Override
    public CamelAdapter getCamelAdapter() {
        return new MyStartEventAdapter();
    }
}
