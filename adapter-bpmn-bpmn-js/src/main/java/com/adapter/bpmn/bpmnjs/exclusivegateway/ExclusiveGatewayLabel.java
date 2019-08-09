package com.adapter.bpmn.bpmnjs.exclusivegateway;

import com.adapter.bpmn.bpmnjs.Label;
import com.adapter.bpmn.bpmnjs.Position;

public class ExclusiveGatewayLabel extends Label {
    public ExclusiveGatewayLabel(Position position) {
        super(position.getX() + 40,position.getY());

    }
}
