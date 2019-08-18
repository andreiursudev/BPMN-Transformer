package com.bpmn.transformer.diagram.endevent;

import com.bpmn.transformer.diagram.diagram.Position;
import com.bpmn.transformer.diagram.diagram.ShapeBound;

public class EndEventShapeBound extends ShapeBound {
    public EndEventShapeBound(Position position) {
        super(position.getX(),position.getY() + 20,50,50);
    }
}
