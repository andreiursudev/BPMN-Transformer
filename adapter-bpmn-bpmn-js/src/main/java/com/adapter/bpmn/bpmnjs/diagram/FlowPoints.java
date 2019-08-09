package com.adapter.bpmn.bpmnjs.diagram;

public class FlowPoints {

    private Position leftFlowPoint;
    private Position rightFlowPoint;
    private Position downFlowPoint;

    public FlowPoints(Position rightFlowPoint) {
        this.rightFlowPoint = rightFlowPoint;
    }

    public FlowPoints(Position leftFlowPoint, Position rightFlowPoint) {
        this.leftFlowPoint = leftFlowPoint;
        this.rightFlowPoint = rightFlowPoint;
    }

    public FlowPoints(Position leftFlowPoint, Position rightFlowPoint, Position downFlowPoint) {
        this.leftFlowPoint = leftFlowPoint;
        this.rightFlowPoint = rightFlowPoint;
        this.downFlowPoint = downFlowPoint;
    }

    private FlowPoints() {

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
