package com.adapter.bpmn.bpmnjs.startevent;

import com.adapter.bpmn.bpmnjs.diagram.FlowPoints;
import com.adapter.bpmn.bpmnjs.diagram.Position;

public class StartEventFlowPoints extends FlowPoints {
    public StartEventFlowPoints(Position position) {
        super(new Position(position.getX() + 65, position.getY() + 45));
    }
}
