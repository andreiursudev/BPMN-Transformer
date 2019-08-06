package com.adapter.bpmn.bpmnjs;

import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;

public class BpmnShapeWithFlowPoints {
    private BpmnShape bpmnShape;
    private int xLeftFlowPoint;
    private int yLeftYFlowPoint;
    private int xRightFlowPoint;
    private int yRightFlowPoint;

    public BpmnShapeWithFlowPoints(BpmnShape bpmnShape, int xLeftFlowPoint, int yLeftFlowPoint, int xRightFlowPoint, int yRightFlowPoint) {
        this.bpmnShape = bpmnShape;
        this.xLeftFlowPoint = xLeftFlowPoint;
        this.yLeftYFlowPoint = yLeftFlowPoint;
        this.xRightFlowPoint = xRightFlowPoint;
        this.yRightFlowPoint = yRightFlowPoint;
    }

    public BpmnShape getBpmnShape() {
        return bpmnShape;
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
