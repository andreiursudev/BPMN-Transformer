package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.Position;
import com.adapter.bpmn.model.Adapter;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnLabel;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.bpmn.instance.di.Waypoint;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

public class ActivityBPMNJsAdapter implements Adapter {
    private String name;

    public ActivityBPMNJsAdapter(String name) {
        this.name = name;
    }

    public BPMNDiagramElement addElement(BpmnPlane plane, BpmnModelInstance modelInstance, Process parentElement, BPMNDiagramElement previousBpmnDiagramElement, String elementId, Position currentPosition) {
        Task task = modelInstance.newInstance(Task.class);
        task.setAttributeValue("id", elementId, true);
        task.setAttributeValue("name", name, false);
        parentElement.addChildElement(task);

        BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
        bpmnShape.setBpmnElement(task);
        bpmnShape.setId(elementId + "_0");

        bpmnShape.setBounds(getBounds(modelInstance, currentPosition.getX(), currentPosition.getY() + 10, 80, 150));


        bpmnShape.addChildElement(getTaskBpmnLabel(modelInstance,elementId + "_1"));

        plane.addChildElement(bpmnShape);

        BPMNDiagramElement bpmnDiagramElement = new BPMNDiagramElement(bpmnShape, currentPosition.getX(), 45, 0, 0);

        BpmnEdge bpmnEdge = createSequenceFlow(modelInstance,parentElement, previousBpmnDiagramElement, bpmnDiagramElement);

        plane.addChildElement(bpmnEdge);

        return bpmnDiagramElement;
    }

    private ModelElementInstance getTaskBpmnLabel(BpmnModelInstance modelInstance, String id) {
        BpmnLabel bpmnLabel = modelInstance.newInstance(BpmnLabel.class);
        bpmnLabel.setId(id);
        Bounds labelBounds = getBounds(modelInstance,0, 0, 0, 0);
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

    private BpmnEdge createSequenceFlow(BpmnModelInstance modelInstance, Process process, BPMNDiagramElement from, BPMNDiagramElement to) {
        FlowNode fromBpmnShape = (FlowNode) from.getBpmnShape().getBpmnElement();
        FlowNode toBpmnShape = (FlowNode) to.getBpmnShape().getBpmnElement();
        String identifier = fromBpmnShape.getId() + "-" + toBpmnShape.getId();
        SequenceFlow sequenceFlow = modelInstance.newInstance(SequenceFlow.class);
        sequenceFlow.setAttributeValue("id", identifier, true);
        process.addChildElement(sequenceFlow);
        sequenceFlow.setSource(fromBpmnShape);
        fromBpmnShape.getOutgoing().add(sequenceFlow);
        sequenceFlow.setTarget(toBpmnShape);
        toBpmnShape.getIncoming().add(sequenceFlow);

        BpmnEdge bpmnEdge = modelInstance.newInstance(BpmnEdge.class);
        bpmnEdge.setId("edge_" + identifier);
        bpmnEdge.setBpmnElement(sequenceFlow);

        Waypoint wp1 = modelInstance.newInstance(Waypoint.class);
        wp1.setX(from.getxRightFlowPoint());
        wp1.setY(from.getyRightFlowPoint());
        bpmnEdge.addChildElement(wp1);

        Waypoint wp2 = modelInstance.newInstance(Waypoint.class);
        wp2.setX(to.getxLeftFlowPoint());
        wp2.setY(to.getyLeftFlowPoint());
        bpmnEdge.addChildElement(wp2);


        return bpmnEdge;
    }
}
