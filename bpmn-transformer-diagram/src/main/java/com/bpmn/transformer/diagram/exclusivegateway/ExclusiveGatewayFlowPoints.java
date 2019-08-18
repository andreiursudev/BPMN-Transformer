package com.bpmn.transformer.diagram.exclusivegateway;

import com.bpmn.transformer.diagram.diagram.FlowPoints;
import com.bpmn.transformer.diagram.diagram.Position;

public class ExclusiveGatewayFlowPoints extends FlowPoints {
    public ExclusiveGatewayFlowPoints(Position position) {
        super(new Position(position.getX() + 15, position.getY() + 45),
                new Position(position.getX() + 60, position.getY() + 45),
                new Position(position.getX() + 40, position.getY() + 70));
    }
}
