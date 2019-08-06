package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.*;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.StartEvent;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElement;

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

        FlowNode flowNode = createElement(StartEvent.class, bpmnDiagram, element);

        return new BPMNDiagramElement(flowNode, 0, 0, currentPosition.getX() + 65, currentPosition.getY() + 45);
    }


}
