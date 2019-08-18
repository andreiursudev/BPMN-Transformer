package com.bpmn.transformer.diagram.exclusivegateway;

import com.bpmn.transformer.diagram.diagram.Label;
import com.bpmn.transformer.diagram.diagram.Position;

public class ExclusiveGatewayLabel extends Label {
    public ExclusiveGatewayLabel(Position position) {
        super(position.getX() + 40,position.getY());

    }
}
