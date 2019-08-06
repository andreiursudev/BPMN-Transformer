package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.ElementIdGenerator;
import com.adapter.bpmn.bpmnjs.Position;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnLabel;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;

public class StartEventBPMNJsAdapter implements BPMNJsAdapter {
    private String name;

    public StartEventBPMNJsAdapter(String name) {
        this.name = name;
    }

    @Override
    public BPMNDiagramElement addElement(BpmnPlane plane, BpmnModelInstance modelInstance, Process parentElement, BPMNDiagramElement previousBpmnDiagramElement, String conditionalFlowName, ElementIdGenerator elementIdGenerator, Position currentPosition) {
        StartEvent startEvent = modelInstance.newInstance(StartEvent.class);
        String nextId = elementIdGenerator.getNextId();
        startEvent.setAttributeValue("id", nextId, true);
        startEvent.setAttributeValue("name", name, false);
        parentElement.addChildElement(startEvent);

        BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
        bpmnShape.setBpmnElement(startEvent);
        bpmnShape.setId(nextId + "_0");

        bpmnShape.setBounds(getBounds(modelInstance, currentPosition.getX() + 15, currentPosition.getY() + 20, 50, 50));
        bpmnShape.addChildElement(getStartEventBpmnLabel(modelInstance, currentPosition, nextId + "_1"));

        plane.addChildElement(bpmnShape);
        return new BPMNDiagramElement(bpmnShape, 0, 0, currentPosition.getX() + 65, currentPosition.getY() + 45);
    }

    private Bounds getBounds(BpmnModelInstance modelInstance, int currentXPosition, int currentYPosition, int height, int width) {
        Bounds bounds = modelInstance.newInstance(Bounds.class);
        bounds.setX(currentXPosition);
        bounds.setY(currentYPosition);
        bounds.setHeight(height);
        bounds.setWidth(width);
        return bounds;
    }

    private BpmnLabel getStartEventBpmnLabel(BpmnModelInstance modelInstance, Position currentPosition, String id) {
        BpmnLabel bpmnLabel = modelInstance.newInstance(BpmnLabel.class);
        bpmnLabel.setId(id);
        Bounds labelBounds = getBounds(modelInstance, currentPosition.getX() + 40, currentPosition.getY() + 75, 0, 0);
        bpmnLabel.addChildElement(labelBounds);
        return bpmnLabel;
    }


}
