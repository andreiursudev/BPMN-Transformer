package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.ElementIdGenerator;
import com.adapter.bpmn.bpmnjs.Position;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElementWithSequenceFlow;

public class ActivityBPMNJsAdapter implements BPMNJsAdapter {
    private String name;

    public ActivityBPMNJsAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BpmnPlane plane, BpmnModelInstance modelInstance, Process parentElement, BPMNDiagramElement previousBpmnDiagramElement, String conditionalFlowName, ElementIdGenerator elementIdGenerator, Position currentPosition) {
        String nextId = elementIdGenerator.getNextId();
        String name = this.name;
        int shapeBoundXPosition = currentPosition.getX();
        int shapeBoundYPosition = currentPosition.getY() + 10;
        int shapeBoundHeight = 80;
        int shapeBoundWidth = 150;

        return createElementWithSequenceFlow(Task.class, plane, modelInstance, parentElement, previousBpmnDiagramElement, conditionalFlowName, currentPosition, nextId, name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth);
    }


}
