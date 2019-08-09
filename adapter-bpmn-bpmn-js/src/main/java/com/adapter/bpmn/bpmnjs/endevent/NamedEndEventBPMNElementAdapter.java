package com.adapter.bpmn.bpmnjs.endevent;

import com.adapter.bpmn.bpmnjs.*;
import org.camunda.bpm.model.bpmn.instance.EndEvent;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElementWithSequenceFlow;

public class NamedEndEventBPMNElementAdapter implements BPMNElementAdapter {
    private String name;

    public NamedEndEventBPMNElementAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BPMNJsDiagram bpmnJsDiagram, String conditionalFlowName) {
        String nextId = bpmnJsDiagram.getNextId();
        Position currentPosition = bpmnJsDiagram.getCurrentPosition();

        Element element = new Element(nextId, name, new EndEventShapeBound(currentPosition));

        BPMNDiagramElement elementWithSequenceFlow = createElementWithSequenceFlow(EndEvent.class, element, new EndEventFlowPoints(currentPosition), conditionalFlowName, bpmnJsDiagram);

        currentPosition.incrementX();
        return elementWithSequenceFlow;
    }
}
