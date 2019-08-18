package com.bearnecessities.orders;

import com.adapter.bpmn.bpmnjs.BPMNElementAdapter;
import com.adapter.bpmn.bpmnjs.BPMNJsDiagram;
import com.adapter.bpmn.bpmnjs.adapter.AdapterHelper;
import com.adapter.bpmn.bpmnjs.diagram.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.diagram.Element;
import com.adapter.bpmn.bpmnjs.diagram.Position;
import com.adapter.bpmn.bpmnjs.startevent.StartEventFlowPoints;
import com.adapter.bpmn.bpmnjs.startevent.StartEventShapeBound;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.MessageEventDefinition;
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
