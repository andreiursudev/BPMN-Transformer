package com.bpmn.transformer.diagram.startevent;

import com.bpmn.transformer.diagram.diagram.FlowPoints;
import com.bpmn.transformer.diagram.diagram.Position;

public class StartEventFlowPoints extends FlowPoints {
    public StartEventFlowPoints(Position position) {
        super(new Position(position.getX() + 65, position.getY() + 45));
    }
}
