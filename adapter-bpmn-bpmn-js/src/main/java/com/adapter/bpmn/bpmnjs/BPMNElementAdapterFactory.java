package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.model.flowobject.FlowObject;

public interface BPMNElementAdapterFactory {
    BPMNElementAdapter getAdapter(FlowObject flowObject);
}
