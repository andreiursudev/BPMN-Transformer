package com.adapter.bpmn.bpmnjs.endevent;

import com.adapter.bpmn.bpmnjs.*;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.camunda.bpm.model.bpmn.instance.EndEvent;

import java.util.Map;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElementWithSequenceFlow;

public class NamedEndEventBPMNElementAdapter implements BPMNElementAdapter {
    private String name;

    public NamedEndEventBPMNElementAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BPMNDiagram bpmnDiagram, String conditionalFlowName, ElementIdGenerator elementId, Position currentPosition, Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary) {
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
