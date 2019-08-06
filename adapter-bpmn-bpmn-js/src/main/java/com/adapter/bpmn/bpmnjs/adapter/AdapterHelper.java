package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.Position;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnLabel;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.bpmn.instance.di.Waypoint;

public class AdapterHelper {

    static BpmnEdge createSequenceFlow(BpmnModelInstance modelInstance, Process process, BPMNDiagramElement from, BPMNDiagramElement to, String conditionalFlowName) {
        FlowNode fromFlowNode = from.getFlowNode();
        FlowNode toFlowNode = to.getFlowNode();
        String identifier = fromFlowNode.getId() + "-" + toFlowNode.getId();
        SequenceFlow sequenceFlow = modelInstance.newInstance(SequenceFlow.class);
        sequenceFlow.setAttributeValue("id", identifier, true);
        sequenceFlow.setAttributeValue("name", conditionalFlowName, false);
        process.addChildElement(sequenceFlow);
        sequenceFlow.setSource(fromFlowNode);
        fromFlowNode.getOutgoing().add(sequenceFlow);
        sequenceFlow.setTarget(toFlowNode);
        toFlowNode.getIncoming().add(sequenceFlow);

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

    static <T extends BpmnModelElementInstance> BPMNDiagramElement createElementWithLabelAndSequenceFlow(Class<T> type, BpmnPlane plane, BpmnModelInstance modelInstance, Process parentElement, BPMNDiagramElement previousBpmnDiagramElement, String conditionalFlowName, Position currentPosition, String nextId, String name, int shapeBoundXPosition, int shapeBoundYPosition, int shapeBoundHeight, int shapeBoundWidth,  int labelXPosition, int labelYPosition) {
        BpmnLabel label = getLabel(modelInstance, nextId + "_label", labelXPosition, labelYPosition);
        return createElementWithSequenceFlow(type, plane, modelInstance, parentElement, previousBpmnDiagramElement, conditionalFlowName, currentPosition, nextId, name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth, label);
    }


    static <T extends BpmnModelElementInstance> BPMNDiagramElement createElementWithSequenceFlow(Class<T> type, BpmnPlane plane, BpmnModelInstance modelInstance, Process parentElement, BPMNDiagramElement previousBpmnDiagramElement, String conditionalFlowName, Position currentPosition, String nextId, String name, int shapeBoundXPosition, int shapeBoundYPosition, int shapeBoundHeight, int shapeBoundWidth) {
        return createElementWithSequenceFlow(type, plane, modelInstance, parentElement, previousBpmnDiagramElement, conditionalFlowName, currentPosition, nextId, name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth, null);
    }

    private static <T extends BpmnModelElementInstance> BPMNDiagramElement createElementWithSequenceFlow(Class<T> type, BpmnPlane plane, BpmnModelInstance modelInstance, Process parentElement, BPMNDiagramElement previousBpmnDiagramElement, String conditionalFlowName, Position currentPosition, String nextId, String name, int shapeBoundXPosition, int shapeBoundYPosition, int shapeBoundHeight, int shapeBoundWidth, BpmnLabel label) {
        FlowNode element = (FlowNode)createElement(type, modelInstance, plane, parentElement, nextId, name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth, label);

        BPMNDiagramElement bpmnDiagramElement = new BPMNDiagramElement(element, currentPosition.getX(), 45, currentPosition.getX() + 150, 45);

        BpmnEdge bpmnEdge = createSequenceFlow(modelInstance, parentElement, previousBpmnDiagramElement, bpmnDiagramElement, conditionalFlowName);

        plane.addChildElement(bpmnEdge);

        return bpmnDiagramElement;
    }
    static <T extends BpmnModelElementInstance> T createElementWithLabel(Class<T> type, BpmnModelInstance modelInstance, BpmnPlane plane, Process parentElement, String nextId, String name, int shapeBoundXPosition, int shapeBoundYPosition, int shapeBoundHeight, int shapeBoundWidth, int labelXPosition, int labelYPosition) {
        BpmnLabel label = getLabel(modelInstance, nextId + "_label", labelXPosition, labelYPosition);
        return createElement(type,modelInstance,plane,parentElement,nextId,name,shapeBoundXPosition,shapeBoundYPosition,shapeBoundHeight,shapeBoundWidth,label);
    }

    static <T extends BpmnModelElementInstance> T createElement(Class<T> type, BpmnModelInstance modelInstance, BpmnPlane plane, Process parentElement, String nextId, String name, int shapeBoundXPosition, int shapeBoundYPosition, int shapeBoundHeight, int shapeBoundWidth) {
        return createElement(type,modelInstance,plane,parentElement,nextId,name,shapeBoundXPosition,shapeBoundYPosition,shapeBoundHeight,shapeBoundWidth,null);
    }


    static <T extends BpmnModelElementInstance> T createElement(Class<T> type, BpmnModelInstance modelInstance, BpmnPlane plane, Process parentElement, String nextId, String name, int shapeBoundXPosition, int shapeBoundYPosition, int shapeBoundHeight, int shapeBoundWidth,BpmnLabel label) {
        T element = modelInstance.newInstance(type);
        element.setAttributeValue("id", nextId, true);
        element.setAttributeValue("name", name, false);
        parentElement.addChildElement(element);

        BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
        bpmnShape.setBpmnElement((BaseElement)element);
        bpmnShape.setId(nextId + "_shape");
        bpmnShape.setBounds(getBounds(modelInstance, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth));
        if(label!=null) {
            bpmnShape.addChildElement(label);
        }

        plane.addChildElement(bpmnShape);
        return element;
    }

    private static  Bounds getBounds(BpmnModelInstance modelInstance, int currentXPosition, int currentYPosition, int height, int width) {
        Bounds bounds = modelInstance.newInstance(Bounds.class);
        bounds.setX(currentXPosition);
        bounds.setY(currentYPosition);
        bounds.setHeight(height);
        bounds.setWidth(width);
        return bounds;
    }

    private static BpmnLabel getLabel(BpmnModelInstance modelInstance, String id, int x, int y) {
        BpmnLabel bpmnLabel = modelInstance.newInstance(BpmnLabel.class);
        bpmnLabel.setId(id);
        Bounds labelBounds = getBounds(modelInstance, x, y, 0, 0);
        bpmnLabel.addChildElement(labelBounds);
        return bpmnLabel;
    }
}
