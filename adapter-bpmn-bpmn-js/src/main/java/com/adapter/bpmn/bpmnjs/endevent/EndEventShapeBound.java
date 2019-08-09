package com.adapter.bpmn.bpmnjs.endevent;

import com.adapter.bpmn.bpmnjs.diagram.Position;
import com.adapter.bpmn.bpmnjs.diagram.ShapeBound;

public class EndEventShapeBound extends ShapeBound {
    public EndEventShapeBound(Position position) {
        super(position.getX(),position.getY() + 20,50,50);
    }
}
