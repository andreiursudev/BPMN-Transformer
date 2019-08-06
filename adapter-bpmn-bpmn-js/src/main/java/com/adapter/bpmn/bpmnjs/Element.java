package com.adapter.bpmn.bpmnjs;

public class Element {
    private String id;
    private String name;
    private int shapeBoundXPosition;
    private int shapeBoundYPosition;
    private int shapeBoundHeight;
    private int shapeBoundWidth;
    private String conditionalFlowName;
    private int labelXPosition;
    private int labelYPosition;
    private Position leftFlowPoint;
    private Position rightFlowPoint;

    public Element(String id, String name, int shapeBoundXPosition, int shapeBoundYPosition, int shapeBoundHeight, int shapeBoundWidth) {

        this.id = id;
        this.name = name;
        this.shapeBoundXPosition = shapeBoundXPosition;
        this.shapeBoundYPosition = shapeBoundYPosition;
        this.shapeBoundHeight = shapeBoundHeight;
        this.shapeBoundWidth = shapeBoundWidth;
    }

    public Element(String id, String name, int shapeBoundXPosition, int shapeBoundYPosition, int shapeBoundHeight, int shapeBoundWidth, String conditionalFlowName) {
        this(id,name,shapeBoundXPosition,shapeBoundYPosition,shapeBoundHeight,shapeBoundWidth);
        this.conditionalFlowName = conditionalFlowName;
    }

    public Element(String id, String name, int shapeBoundXPosition, int shapeBoundYPosition, int shapeBoundHeight, int shapeBoundWidth, String conditionalFlowName, Position leftFlowPoint, Position rightFlowPoint) {
        this(id,name,shapeBoundXPosition,shapeBoundYPosition,shapeBoundHeight,shapeBoundWidth,conditionalFlowName);
        this.leftFlowPoint = leftFlowPoint;
        this.rightFlowPoint = rightFlowPoint;
    }

    public Element(String id, String name, int shapeBoundXPosition, int shapeBoundYPosition, int shapeBoundHeight, int shapeBoundWidth, int labelXPosition, int labelYPosition, Position leftFlowPoint, Position rightFlowPoint) {
        this(id,name,shapeBoundXPosition,shapeBoundYPosition,shapeBoundHeight,shapeBoundWidth,null, leftFlowPoint, rightFlowPoint);
        this.labelXPosition = labelXPosition;
        this.labelYPosition = labelYPosition;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getShapeBoundXPosition() {
        return shapeBoundXPosition;
    }

    public int getShapeBoundYPosition() {
        return shapeBoundYPosition;
    }

    public int getShapeBoundHeight() {
        return shapeBoundHeight;
    }

    public int getShapeBoundWidth() {
        return shapeBoundWidth;
    }

    public String getConditionalFlowName() {
        return conditionalFlowName;
    }

    public int getLabelXPosition() {
        return labelXPosition;
    }

    public int getLabelYPosition() {
        return labelYPosition;
    }

    public Position getLeftFlowPoint() {
        return leftFlowPoint;
    }

    public Position getRightFlowPoint() {
        return rightFlowPoint;
    }
}
