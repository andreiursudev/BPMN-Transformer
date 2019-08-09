package com.adapter.bpmn.bpmnjs.activity;

import com.adapter.bpmn.bpmnjs.diagram.Position;
import com.adapter.bpmn.bpmnjs.diagram.ShapeBound;

public class ActivityShapeBound extends ShapeBound {
    public ActivityShapeBound(Position position) {
        super(position.getX(),position.getY()+10,80,150);
    }
}
