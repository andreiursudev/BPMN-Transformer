package com.bpmn.transformer.diagram;

import com.bpmn.transformer.model.flowobject.FlowObject;

public interface BPMNElementAdapterFactory {
    BPMNElementAdapter getAdapter(FlowObject flowObject);
}
