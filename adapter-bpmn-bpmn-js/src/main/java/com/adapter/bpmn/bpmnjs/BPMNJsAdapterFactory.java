package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.model.flowobject.FlowObject;

public interface BPMNJsAdapterFactory {
    BPMNJsAdapter getAdapter(FlowObject flowObject);
}
