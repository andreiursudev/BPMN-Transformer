package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.*;
import org.camunda.bpm.model.bpmn.instance.Task;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElementWithSequenceFlow;

public class ActivityBPMNJsAdapter implements BPMNJsAdapter {
    private String name;

    public ActivityBPMNJsAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BPMNDiagram bpmnDiagram, String conditionalFlowName, ElementIdGenerator elementIdGenerator, Position currentPosition) {
        String nextId = elementIdGenerator.getNextId();
        String name = this.name;
        int shapeBoundXPosition = currentPosition.getX();
        int shapeBoundYPosition = currentPosition.getY() + 10;
        int shapeBoundHeight = 80;
        int shapeBoundWidth = 150;

        Position leftFlowPoint = new Position(currentPosition.getX(), currentPosition.getY() + 45);
        Position rightFlowPoint = new Position(currentPosition.getX() + 150, currentPosition.getY() + 45);

        Element element = new Element(nextId, name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth, conditionalFlowName, leftFlowPoint, rightFlowPoint);

        return createElementWithSequenceFlow(Task.class, bpmnDiagram, element);
    }


}
