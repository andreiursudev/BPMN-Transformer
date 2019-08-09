package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.bpmnjs.diagram.BPMNDiagramElement;

public interface BPMNElementAdapter {

    BPMNDiagramElement addElement(BPMNJsDiagram bpmnJsDiagram, String conditionalFlowName);
}
