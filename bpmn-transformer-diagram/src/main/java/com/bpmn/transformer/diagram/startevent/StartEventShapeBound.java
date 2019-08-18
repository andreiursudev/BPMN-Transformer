package com.bpmn.transformer.diagram.startevent;

import com.bpmn.transformer.diagram.diagram.Position;
import com.bpmn.transformer.diagram.diagram.ShapeBound;

public class StartEventShapeBound extends ShapeBound {
    public StartEventShapeBound(Position position) {
        super(position.getX() + 15, position.getY() + 20, 50, 50);
    }
}
