package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.BPMNJsAdapter;

public class StartEventBPMNJsAdapter implements BPMNJsAdapter {
    private String name;

    public StartEventBPMNJsAdapter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
