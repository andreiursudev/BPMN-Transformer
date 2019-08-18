package com.bpmn.transformer.diagram.activity;

import com.bpmn.transformer.diagram.diagram.Position;
import com.bpmn.transformer.diagram.diagram.ShapeBound;

public class ActivityShapeBound extends ShapeBound {
    public ActivityShapeBound(Position position) {
        super(position.getX(),position.getY()+10,80,150);
    }
}
