package com.adapter.bpmn.bpmnjs;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;

public class BPMNDiagram {
    private BpmnModelInstance modelInstance;
    private BpmnPlane plane;
    private Process parentElement;
    private BPMNDiagramElement currentElement;

    public BPMNDiagram(BpmnModelInstance modelInstance, BpmnPlane plane, Process parentElement, BPMNDiagramElement currentElement) {
        this.modelInstance = modelInstance;
        this.plane = plane;
        this.parentElement = parentElement;
        this.currentElement = currentElement;
    }

    public BpmnModelInstance getModelInstance() {
        return modelInstance;
    }

    public BpmnPlane getPlane() {
        return plane;
    }

    public Process getParentElement() {
        return parentElement;
    }

    public BPMNDiagramElement getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(BPMNDiagramElement currentElement) {
        this.currentElement = currentElement;
    }
}
