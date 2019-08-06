package com.adapter.bpmn.bpmnjs.testapp;

import com.adapter.bpmn.bpmnjs.adapter.StartEventBPMNJsAdapter;
import com.adapter.bpmn.model.Adapter;
import com.adapter.bpmn.model.flowobject.StartEvent;

public class MyStartEvent implements StartEvent {
    @Override
    public Adapter getAdapter() {
        return new StartEventBPMNJsAdapter("My Start Event");
    }
}
