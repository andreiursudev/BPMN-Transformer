package com.bpmn.transformer.diagram.diagram;

import org.camunda.bpm.model.bpmn.instance.FlowNode;

public class BPMNDiagramElement {
    private FlowNode flowNode;
    private FlowPoints flowPoints;

    public BPMNDiagramElement(FlowNode flowNode, FlowPoints flowPoints) {
        this.flowNode = flowNode;
        this.flowPoints = flowPoints;
    }

    public FlowNode getFlowNode() {
        return flowNode;
    }

    public FlowPoints getFlowPoints() {
        return flowPoints;
    }
}
