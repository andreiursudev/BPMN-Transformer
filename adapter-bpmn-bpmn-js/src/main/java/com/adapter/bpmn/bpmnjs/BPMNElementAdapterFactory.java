package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.model.AdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;

public interface BPMNElementAdapterFactory extends AdapterFactory {
    BPMNElementAdapter getAdapter(FlowObject flowObject);
}
