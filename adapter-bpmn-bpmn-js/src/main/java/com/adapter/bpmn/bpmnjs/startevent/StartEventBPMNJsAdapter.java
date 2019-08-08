package com.adapter.bpmn.bpmnjs.startevent;

import com.adapter.bpmn.bpmnjs.*;
import com.adapter.bpmn.bpmnjs.adapter.AdapterHelper;
import com.adapter.bpmn.bpmnjs.BPMNJsAdapter;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.StartEvent;

public class StartEventBPMNJsAdapter implements BPMNJsAdapter {
    private String name;

    public StartEventBPMNJsAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BPMNDiagram bpmnDiagram, String conditionalFlowName, ElementIdGenerator elementIdGenerator, Position currentPosition) {
        String nextId = elementIdGenerator.getNextId();

        int shapeBoundXPosition = currentPosition.getX() + 15;
        int shapeBoundYPosition = currentPosition.getY() + 20;
        int shapeBoundHeight = 50;
        int shapeBoundWidth = 50;

        Element element = new Element(nextId, name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth);

        FlowNode flowNode = AdapterHelper.createElement(StartEvent.class, bpmnDiagram, element);

        BPMNDiagramElement bpmnDiagramElement = new BPMNDiagramElement(flowNode, currentPosition, new Position(currentPosition.getX() + 65, currentPosition.getY() + 45), null);

        currentPosition.incrementX();

        return bpmnDiagramElement;
    }


}
