package com.adapter.bpmn.bpmnjs;

import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;

public class BPMNDiagramElement {
    private FlowNode flowNode;
    private int xLeftFlowPoint;
    private int yLeftYFlowPoint;
    private int xRightFlowPoint;
    private int yRightFlowPoint;

    public BPMNDiagramElement(FlowNode flowNode, int xLeftFlowPoint, int yLeftFlowPoint, int xRightFlowPoint, int yRightFlowPoint) {
        this.flowNode = flowNode;
        this.xLeftFlowPoint = xLeftFlowPoint;
        this.yLeftYFlowPoint = yLeftFlowPoint;
        this.xRightFlowPoint = xRightFlowPoint;
        this.yRightFlowPoint = yRightFlowPoint;
    }

    public FlowNode getFlowNode() {
        return flowNode;
    }

    public int getxLeftFlowPoint() {
        return xLeftFlowPoint;
    }

    public int getyLeftFlowPoint() {
        return yLeftYFlowPoint;
    }

    public int getxRightFlowPoint() {
        return xRightFlowPoint;
    }

    public int getyRightFlowPoint() {
        return yRightFlowPoint;
    }

}
