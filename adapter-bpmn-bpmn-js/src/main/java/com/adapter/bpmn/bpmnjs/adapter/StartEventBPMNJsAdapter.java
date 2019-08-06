package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.ElementIdGenerator;
import com.adapter.bpmn.bpmnjs.Position;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElement;

public class StartEventBPMNJsAdapter implements BPMNJsAdapter {
    private String name;

    public StartEventBPMNJsAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BpmnPlane plane, BpmnModelInstance modelInstance, Process parentElement, BPMNDiagramElement previousBpmnDiagramElement, String conditionalFlowName, ElementIdGenerator elementIdGenerator, Position currentPosition) {
        String nextId = elementIdGenerator.getNextId();

        int shapeBoundXPosition = currentPosition.getX() + 15;
        int shapeBoundYPosition = currentPosition.getY() + 20;
        int shapeBoundHeight = 50;
        int shapeBoundWidth = 50;

        FlowNode element = createElement(StartEvent.class, modelInstance, plane, parentElement, nextId, name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth);

        return new BPMNDiagramElement(element, 0, 0, currentPosition.getX() + 65, currentPosition.getY() + 45);
    }


}
