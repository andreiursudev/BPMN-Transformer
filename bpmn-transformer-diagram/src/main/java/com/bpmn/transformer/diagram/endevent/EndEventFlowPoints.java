package com.bpmn.transformer.diagram.endevent;

import com.bpmn.transformer.diagram.diagram.FlowPoints;
import com.bpmn.transformer.diagram.diagram.Position;

public class EndEventFlowPoints extends FlowPoints {
    public EndEventFlowPoints(Position position) {
        super(new Position(position.getX(),position.getY()+45), null, null);
    }
}
