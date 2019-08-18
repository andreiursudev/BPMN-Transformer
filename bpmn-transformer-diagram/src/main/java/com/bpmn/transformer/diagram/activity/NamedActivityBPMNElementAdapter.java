package com.bpmn.transformer.diagram.activity;

import com.bpmn.transformer.diagram.BPMNElementAdapter;
import com.bpmn.transformer.diagram.BPMNJsDiagram;
import com.bpmn.transformer.diagram.diagram.BPMNDiagramElement;
import com.bpmn.transformer.diagram.diagram.Element;
import com.bpmn.transformer.diagram.diagram.Position;
import org.camunda.bpm.model.bpmn.instance.Task;

import static com.bpmn.transformer.diagram.adapter.AdapterHelper.createElementWithSequenceFlow;

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
