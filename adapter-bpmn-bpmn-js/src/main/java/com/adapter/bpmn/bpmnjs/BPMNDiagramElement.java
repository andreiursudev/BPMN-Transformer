package com.adapter.bpmn.bpmnjs;

import org.camunda.bpm.model.bpmn.instance.FlowNode;

public class BPMNDiagramElement {
    private FlowNode flowNode;
    private Position leftFlowPoint;
    private Position rightFlowPoint;
    private Position downFlowPoint;

    public BPMNDiagramElement(FlowNode flowNode, Position leftFlowPoint, Position rightFlowPoint, Position downFlowPoint) {
        this.flowNode = flowNode;
        this.leftFlowPoint = leftFlowPoint;
        this.rightFlowPoint = rightFlowPoint;
        this.downFlowPoint = downFlowPoint;
    }

    public FlowNode getFlowNode() {
        return flowNode;
    }

    public Position getLeftFlowPoint() {
        return leftFlowPoint;
    }

    public Position getRightFlowPoint() {
        return rightFlowPoint;
    }

    public Position getDownFlowPoint() {
        return downFlowPoint;
    }
}
