package com.adapter.bpmn.bpmnjs.activity;

import com.adapter.bpmn.bpmnjs.*;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.camunda.bpm.model.bpmn.instance.Task;

import java.util.Map;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElementWithSequenceFlow;

public class NamedActivityBPMNElementAdapter implements BPMNElementAdapter {
    private String name;

    public NamedActivityBPMNElementAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BPMNDiagram bpmnDiagram, String conditionalFlowName, ElementIdGenerator elementIdGenerator, Position currentPosition, Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary) {
        String nextId = elementIdGenerator.getNextId();
        String name = this.name;
        int shapeBoundXPosition = currentPosition.getX();
        int shapeBoundYPosition = currentPosition.getY() + 10;
        int shapeBoundHeight = 80;
        int shapeBoundWidth = 150;

        Position leftFlowPoint = new Position(currentPosition.getX(), currentPosition.getY() + 45);
        Position rightFlowPoint = new Position(currentPosition.getX() + 150, currentPosition.getY() + 45);

        Element element = new Element(nextId, name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth, conditionalFlowName, leftFlowPoint, rightFlowPoint);

        BPMNDiagramElement elementWithSequenceFlow = createElementWithSequenceFlow(Task.class, bpmnDiagram, element);

        currentPosition.incrementX();

        return elementWithSequenceFlow;
    }


}
