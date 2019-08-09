package com.adapter.bpmn.bpmnjs.activity;

import com.adapter.bpmn.bpmnjs.FlowPoints;
import com.adapter.bpmn.bpmnjs.Position;

public class ActivityFlowPoints extends FlowPoints {

    public ActivityFlowPoints(Position position) {
        super(new Position(position.getX(), position.getY() + 45), new Position(position.getX() + 150, position.getY() + 45));
    }
}
