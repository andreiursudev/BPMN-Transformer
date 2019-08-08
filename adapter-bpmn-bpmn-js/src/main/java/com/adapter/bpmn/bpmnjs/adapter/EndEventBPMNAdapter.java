package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.*;
import org.camunda.bpm.model.bpmn.instance.EndEvent;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElementWithSequenceFlow;

public class EndEventBPMNAdapter implements BPMNJsAdapter{
    private String name;

    public EndEventBPMNAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BPMNDiagram bpmnDiagram, String conditionalFlowName, ElementIdGenerator elementId, Position currentPosition) {
        String nextId = elementId.getNextId();
        String name = this.name;
        int shapeBoundXPosition = currentPosition.getX();
        int shapeBoundYPosition = currentPosition.getY() + 20;
        int shapeBoundHeight = 50;
        int shapeBoundWidth = 50;

        Position leftFlowPoint = new Position(currentPosition.getX(), currentPosition.getY() + 45);

        Element element = new Element(nextId, name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth, conditionalFlowName, leftFlowPoint, leftFlowPoint);

        BPMNDiagramElement elementWithSequenceFlow = createElementWithSequenceFlow(EndEvent.class, bpmnDiagram, element);

        currentPosition.incrementX();
        return elementWithSequenceFlow;
    }
}
