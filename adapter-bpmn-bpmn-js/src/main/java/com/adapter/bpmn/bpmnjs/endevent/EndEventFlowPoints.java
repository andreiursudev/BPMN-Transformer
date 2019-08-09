package com.adapter.bpmn.bpmnjs.endevent;

import com.adapter.bpmn.bpmnjs.diagram.FlowPoints;
import com.adapter.bpmn.bpmnjs.diagram.Position;

public class EndEventFlowPoints extends FlowPoints {
    public EndEventFlowPoints(Position position) {
        super(new Position(position.getX(),position.getY()+45), null, null);
    }
}
