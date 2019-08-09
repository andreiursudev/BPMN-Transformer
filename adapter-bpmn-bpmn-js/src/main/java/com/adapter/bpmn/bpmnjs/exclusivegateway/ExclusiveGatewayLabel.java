package com.adapter.bpmn.bpmnjs.exclusivegateway;

import com.adapter.bpmn.bpmnjs.diagram.Label;
import com.adapter.bpmn.bpmnjs.diagram.Position;

public class ExclusiveGatewayLabel extends Label {
    public ExclusiveGatewayLabel(Position position) {
        super(position.getX() + 40,position.getY());

    }
}
