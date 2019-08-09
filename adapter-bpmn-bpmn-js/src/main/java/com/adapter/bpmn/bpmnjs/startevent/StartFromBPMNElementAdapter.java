package com.adapter.bpmn.bpmnjs.startevent;

import com.adapter.bpmn.bpmnjs.*;
import com.adapter.bpmn.bpmnjs.adapter.AdapterHelper;
import com.adapter.bpmn.bpmnjs.BPMNElementAdapter;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.StartEvent;

import java.util.Map;

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
