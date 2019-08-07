package com.adapter.bpmn.bpmnjs.testapp;

import com.adapter.bpmn.bpmnjs.adapter.EndEventBPMNAdapter;
import com.adapter.bpmn.model.Adapter;
import com.adapter.bpmn.model.flowobject.EndEvent;

public class MyEndEvent implements EndEvent {
    private String name;

    public MyEndEvent(String name) {
        this.name = name;
    }

    @Override
    public Adapter getAdapter() {
        return new EndEventBPMNAdapter(name);
    }
}
