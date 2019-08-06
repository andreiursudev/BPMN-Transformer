package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.model.Adapter;

public class ActivityBPMNJsAdapter implements Adapter {
    private String name;

    public ActivityBPMNJsAdapter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
