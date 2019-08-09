package com.adapter.bpmn.bpmnjs.exclusivegateway;

import com.adapter.bpmn.bpmnjs.Position;
import com.adapter.bpmn.bpmnjs.ShapeBound;

public class ExclusiveGatewayShapeBound extends ShapeBound {
    public ExclusiveGatewayShapeBound(Position position) {
        super(position.getX() + 15, position.getY() + 20, 50, 50);
    }
}
