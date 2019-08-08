package com.adapter.bpmn.bpmnjs.activity;

import com.adapter.bpmn.bpmnjs.BPMNElementAdapter;
import com.adapter.bpmn.bpmnjs.BPMNElementAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.activity.NamedActivity;

public class NamedActivityBPMNElementAdapterFactory implements BPMNElementAdapterFactory {
    @Override
    public BPMNElementAdapter getAdapter(FlowObject flowObject) {
        return new NamedActivityBPMNElementAdapter(((NamedActivity)flowObject).getName());
    }
}
