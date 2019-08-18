package com.bpmn.transformer.diagram.exclusivegateway;

import com.bpmn.transformer.diagram.diagram.Position;
import com.bpmn.transformer.diagram.diagram.ShapeBound;

public class ExclusiveGatewayShapeBound extends ShapeBound {
    public ExclusiveGatewayShapeBound(Position position) {
        super(position.getX() + 15, position.getY() + 20, 50, 50);
    }
}
