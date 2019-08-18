package com.bpmn.transformer.diagram.activity;

import com.bpmn.transformer.diagram.diagram.FlowPoints;
import com.bpmn.transformer.diagram.diagram.Position;

public class ActivityFlowPoints extends FlowPoints {

    public ActivityFlowPoints(Position position) {
        super(new Position(position.getX(), position.getY() + 45), new Position(position.getX() + 150, position.getY() + 45));
    }
}
