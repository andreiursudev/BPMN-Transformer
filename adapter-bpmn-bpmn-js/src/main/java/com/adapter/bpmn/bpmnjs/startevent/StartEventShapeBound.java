package com.adapter.bpmn.bpmnjs.startevent;

import com.adapter.bpmn.bpmnjs.Position;
import com.adapter.bpmn.bpmnjs.ShapeBound;

public class StartEventShapeBound extends ShapeBound {
    public StartEventShapeBound(Position position) {
        super(position.getX() + 15, position.getY() + 20, 50, 50);
    }
}
