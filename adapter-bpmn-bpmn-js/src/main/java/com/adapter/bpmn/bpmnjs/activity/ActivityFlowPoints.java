package com.adapter.bpmn.bpmnjs.activity;

import com.adapter.bpmn.bpmnjs.diagram.FlowPoints;
import com.adapter.bpmn.bpmnjs.diagram.Position;

public class ActivityFlowPoints extends FlowPoints {

    public ActivityFlowPoints(Position position) {
        super(new Position(position.getX(), position.getY() + 45), new Position(position.getX() + 150, position.getY() + 45));
    }
}
