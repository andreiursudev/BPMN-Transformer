package com.adapter.bpmn.bpmnjs.endevent;

import com.adapter.bpmn.bpmnjs.Position;
import com.adapter.bpmn.bpmnjs.ShapeBound;

public class EndEventShapeBound extends ShapeBound {
    public EndEventShapeBound(Position position) {
        super(position.getX(),position.getY() + 20,50,50);
    }
}
