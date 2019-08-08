package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.model.flowobject.FlowObject;

import java.util.Map;

public interface BPMNElementAdapter {

    BPMNDiagramElement addElement(BPMNDiagram bpmnDiagram, String conditionalFlowName, ElementIdGenerator elementId, Position currentPosition, Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary);
}
