package com.adapter.bpmn.bpmnjs.activity;

import com.adapter.bpmn.bpmnjs.*;
import com.adapter.bpmn.bpmnjs.diagram.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.diagram.Element;
import com.adapter.bpmn.bpmnjs.diagram.Position;
import org.camunda.bpm.model.bpmn.instance.Task;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElementWithSequenceFlow;

public class NamedActivityBPMNElementAdapter implements BPMNElementAdapter {
    private String name;

    public NamedActivityBPMNElementAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BPMNJsDiagram bpmnJsDiagram, String conditionalFlowName) {
        String nextId = bpmnJsDiagram.getNextId();
        Position position = bpmnJsDiagram.getCurrentPosition();

        Element element = new Element(nextId, name, new ActivityShapeBound(position));

        BPMNDiagramElement elementWithSequenceFlow = createElementWithSequenceFlow(Task.class, element, new ActivityFlowPoints(position), conditionalFlowName, bpmnJsDiagram);

        bpmnJsDiagram.incrementX();

        return elementWithSequenceFlow;
    }


}
