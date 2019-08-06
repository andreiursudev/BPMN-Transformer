package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.ElementIdGenerator;
import com.adapter.bpmn.bpmnjs.Position;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnLabel;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createSequenceFlow;

public class ActivityBPMNJsAdapter implements BPMNJsAdapter {
    private String name;

    public ActivityBPMNJsAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BpmnPlane plane, BpmnModelInstance modelInstance, Process parentElement, BPMNDiagramElement previousBpmnDiagramElement, String conditionalFlowName, ElementIdGenerator elementIdGenerator, Position currentPosition) {
        Task task = modelInstance.newInstance(Task.class);
        String nextId = elementIdGenerator.getNextId();
        task.setAttributeValue("id", nextId, true);
        task.setAttributeValue("name", name, false);
        parentElement.addChildElement(task);

        BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
        bpmnShape.setBpmnElement(task);
        bpmnShape.setId(nextId + "_0");

        bpmnShape.setBounds(getBounds(modelInstance, currentPosition.getX(), currentPosition.getY() + 10, 80, 150));


        bpmnShape.addChildElement(getTaskBpmnLabel(modelInstance, nextId + "_1"));

        plane.addChildElement(bpmnShape);

        BPMNDiagramElement bpmnDiagramElement = new BPMNDiagramElement(bpmnShape, currentPosition.getX(), 45, currentPosition.getX() + 150, 45);

        BpmnEdge bpmnEdge = createSequenceFlow(modelInstance, parentElement, previousBpmnDiagramElement, bpmnDiagramElement,conditionalFlowName);

        plane.addChildElement(bpmnEdge);

        return bpmnDiagramElement;
    }

    private ModelElementInstance getTaskBpmnLabel(BpmnModelInstance modelInstance, String id) {
        BpmnLabel bpmnLabel = modelInstance.newInstance(BpmnLabel.class);
        bpmnLabel.setId(id);
        Bounds labelBounds = getBounds(modelInstance, 0, 0, 0, 0);
        bpmnLabel.addChildElement(labelBounds);
        return bpmnLabel;
    }

    private Bounds getBounds(BpmnModelInstance modelInstance, int currentXPosition, int currentYPosition, int height, int width) {
        Bounds bounds = modelInstance.newInstance(Bounds.class);
        bounds.setX(currentXPosition);
        bounds.setY(currentYPosition);
        bounds.setHeight(height);
        bounds.setWidth(width);
        return bounds;
    }


}
