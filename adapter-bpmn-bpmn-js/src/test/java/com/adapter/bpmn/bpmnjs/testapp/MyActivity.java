package com.adapter.bpmn.bpmnjs.testapp;

import com.adapter.bpmn.bpmnjs.adapter.ActivityBPMNJsAdapter;
import com.adapter.bpmn.model.Adapter;
import com.adapter.bpmn.model.flowobject.Activity;

public class MyActivity implements Activity {
    @Override
    public Adapter getAdapter() {
        return new ActivityBPMNJsAdapter("This is an activity");
    }
}
