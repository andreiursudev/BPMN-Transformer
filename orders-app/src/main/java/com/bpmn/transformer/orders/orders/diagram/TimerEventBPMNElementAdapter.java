package com.bpmn.transformer.orders.orders.diagram;

import com.bpmn.transformer.diagram.BPMNElementAdapter;
import com.bpmn.transformer.diagram.BPMNJsDiagram;
import com.bpmn.transformer.diagram.adapter.AdapterHelper;
import com.bpmn.transformer.diagram.diagram.BPMNDiagramElement;
import com.bpmn.transformer.diagram.diagram.Element;
import com.bpmn.transformer.diagram.diagram.Position;
import com.bpmn.transformer.diagram.startevent.StartEventFlowPoints;
import com.bpmn.transformer.diagram.startevent.StartEventShapeBound;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.TimerEventDefinition;

public class TimerEventBPMNElementAdapter implements BPMNElementAdapter {
    private String directoryName;

    public TimerEventBPMNElementAdapter(String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public BPMNDiagramElement addElement(BPMNJsDiagram bpmnJsDiagram, String conditionalFlowName) {
        String nextId = bpmnJsDiagram.getNextId();
        Position currentPosition = bpmnJsDiagram.getCurrentPosition();

        Element element = new Element(nextId, directoryName, new StartEventShapeBound(currentPosition));

        StartEvent startEvent = AdapterHelper.createElement(StartEvent.class, element, bpmnJsDiagram);

        TimerEventDefinition timerEventDefinition = bpmnJsDiagram.getModelInstance().newInstance(TimerEventDefinition.class);
        timerEventDefinition.setId(nextId + "_timer");

        startEvent.getEventDefinitions().add(timerEventDefinition);
        startEvent.getEventDefinitionRefs().add(timerEventDefinition);

        BPMNDiagramElement bpmnDiagramElement = new BPMNDiagramElement((FlowNode)startEvent, new StartEventFlowPoints(currentPosition));

        bpmnJsDiagram.incrementX();

        return bpmnDiagramElement;
    }
}
