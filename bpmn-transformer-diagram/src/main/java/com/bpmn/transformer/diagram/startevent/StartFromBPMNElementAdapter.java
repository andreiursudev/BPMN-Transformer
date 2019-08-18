package com.bpmn.transformer.diagram.startevent;

import com.bpmn.transformer.diagram.BPMNJsDiagram;
import com.bpmn.transformer.diagram.adapter.AdapterHelper;
import com.bpmn.transformer.diagram.BPMNElementAdapter;
import com.bpmn.transformer.diagram.diagram.BPMNDiagramElement;
import com.bpmn.transformer.diagram.diagram.Element;
import com.bpmn.transformer.diagram.diagram.Position;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.StartEvent;

public class StartFromBPMNElementAdapter implements BPMNElementAdapter {
    private String name;

    public StartFromBPMNElementAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BPMNJsDiagram bpmnJsDiagram, String conditionalFlowName) {
        String nextId = bpmnJsDiagram.getNextId();
        Position currentPosition = bpmnJsDiagram.getCurrentPosition();

        Element element = new Element(nextId, name, new StartEventShapeBound(currentPosition));

        FlowNode flowNode = AdapterHelper.createElement(StartEvent.class, element, bpmnJsDiagram);
        BPMNDiagramElement bpmnDiagramElement = new BPMNDiagramElement(flowNode, new StartEventFlowPoints(currentPosition));

        bpmnJsDiagram.incrementX();

        return bpmnDiagramElement;
    }

}
