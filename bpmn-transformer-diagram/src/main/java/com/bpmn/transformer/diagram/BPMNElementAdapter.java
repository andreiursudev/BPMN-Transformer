package com.bpmn.transformer.diagram;

import com.bpmn.transformer.diagram.diagram.BPMNDiagramElement;

public interface BPMNElementAdapter {

    BPMNDiagramElement addElement(BPMNJsDiagram bpmnJsDiagram, String conditionalFlowName);
}
