package com.adapter.bpmn.bpmnjs.startevent;

import com.adapter.bpmn.bpmnjs.startevent.StartEventBPMNJsAdapter;
import com.adapter.bpmn.model.Adapter;
import com.adapter.bpmn.model.flowobject.StartEvent;

public class MyStartEvent implements StartEvent {
    @Override
    public Adapter getAdapter() {
        return new StartEventBPMNJsAdapter("My Start Event");
    }
}
