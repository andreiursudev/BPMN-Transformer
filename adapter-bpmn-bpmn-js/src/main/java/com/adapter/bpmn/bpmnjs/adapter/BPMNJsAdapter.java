package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.BPMNDiagram;
import com.adapter.bpmn.bpmnjs.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.ElementIdGenerator;
import com.adapter.bpmn.bpmnjs.Position;
import com.adapter.bpmn.model.Adapter;

public interface BPMNJsAdapter extends Adapter {

    BPMNDiagramElement addElement(BPMNDiagram bpmnDiagram, String conditionalFlowName, ElementIdGenerator elementId, Position currentPosition);
}
