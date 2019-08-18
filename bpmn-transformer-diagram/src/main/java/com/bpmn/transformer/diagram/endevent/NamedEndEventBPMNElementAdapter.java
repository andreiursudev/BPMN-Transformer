package com.bpmn.transformer.diagram.endevent;

import com.bpmn.transformer.diagram.BPMNElementAdapter;
import com.bpmn.transformer.diagram.BPMNJsDiagram;
import com.bpmn.transformer.diagram.diagram.BPMNDiagramElement;
import com.bpmn.transformer.diagram.diagram.Element;
import com.bpmn.transformer.diagram.diagram.Position;
import org.camunda.bpm.model.bpmn.instance.EndEvent;

import static com.bpmn.transformer.diagram.adapter.AdapterHelper.createElementWithSequenceFlow;

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
