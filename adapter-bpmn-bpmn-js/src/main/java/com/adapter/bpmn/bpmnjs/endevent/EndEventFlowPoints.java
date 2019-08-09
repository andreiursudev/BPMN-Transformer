package com.adapter.bpmn.bpmnjs.endevent;

import com.adapter.bpmn.bpmnjs.FlowPoints;
import com.adapter.bpmn.bpmnjs.Position;

public class EndEventFlowPoints extends FlowPoints {
    public EndEventFlowPoints(Position position) {
        super(new Position(position.getX(),position.getY()+45), null, null);
    }
}
