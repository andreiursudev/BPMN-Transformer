package com.adapter.bpmn.bpmnjs.exclusivegateway;

import com.adapter.bpmn.bpmnjs.diagram.FlowPoints;
import com.adapter.bpmn.bpmnjs.diagram.Position;

public class ExclusiveGatewayFlowPoints extends FlowPoints {
    public ExclusiveGatewayFlowPoints(Position position) {
        super(new Position(position.getX() + 15, position.getY() + 45),
                new Position(position.getX() + 60, position.getY() + 45),
                new Position(position.getX() + 40, position.getY() + 70));
    }
}
