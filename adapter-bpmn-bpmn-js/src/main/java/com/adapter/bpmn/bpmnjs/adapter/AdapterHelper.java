package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.*;
import com.adapter.bpmn.bpmnjs.diagram.*;
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

    public static <T extends BpmnModelElementInstance> BPMNDiagramElement createElementWithSequenceFlow(Class<T> type, Element element, FlowPoints flowPoints, String conditionalFlowName, BPMNJsDiagram bpmnJsDiagram) {
        FlowNode flowNode = (FlowNode) createElement(type, element, bpmnJsDiagram);
        BPMNDiagramElement bpmnDiagramElement = new BPMNDiagramElement(flowNode, flowPoints);
        createSequenceFlow(bpmnJsDiagram.getLastNode(), bpmnDiagramElement, conditionalFlowName, bpmnJsDiagram.getProcess(), bpmnJsDiagram.getPlane(), bpmnJsDiagram.getModelInstance());
        return bpmnDiagramElement;
    }

    public static <T extends BpmnModelElementInstance> BPMNDiagramElement createElementWithCustomLabelAndSequenceFlow(Class<T> type, Element element, Label label, FlowPoints flowPoints, BPMNJsDiagram bpmnJsDiagram) {
        FlowNode flowNode = (FlowNode) createElementWitCustomLabel(type, bpmnJsDiagram, element, label);
        BPMNDiagramElement bpmnDiagramElement = new BPMNDiagramElement(flowNode, flowPoints);
        createSequenceFlow(bpmnJsDiagram.getLastNode(), bpmnDiagramElement, null, bpmnJsDiagram.getProcess(), bpmnJsDiagram.getPlane(), bpmnJsDiagram.getModelInstance());
        return bpmnDiagramElement;
    }

    static <T extends BpmnModelElementInstance> T createElementWitCustomLabel(Class<T> type, BPMNJsDiagram bpmnJsDiagram, Element element, Label label) {
        BpmnModelInstance modelInstance = bpmnJsDiagram.getModelInstance();
        String id = element.getId();
        BpmnLabel bpmnLabel = getLabel(modelInstance, id + "_label", label);
        return createElement(type,
                modelInstance,
                bpmnJsDiagram.getPlane(),
                bpmnJsDiagram.getProcess(),
                id,
                element.getName(),
                element.getShapeBound(),
                bpmnLabel);
    }


    public static <T extends BpmnModelElementInstance> T createElement(Class<T> type, Element element, BPMNJsDiagram bpmnJsDiagram) {
        return createElement(type,
                bpmnJsDiagram.getModelInstance(),
                bpmnJsDiagram.getPlane(),
                bpmnJsDiagram.getProcess(),
                element.getId(),
                element.getName(),
                element.getShapeBound(),
                null);
    }


    public static BpmnEdge createSequenceFlow(BPMNDiagramElement from, BPMNDiagramElement to, String conditionalFlowName, Process process, BpmnPlane plane, BpmnModelInstance modelInstance) {
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

        FlowPoints fromFlowPoints = from.getFlowPoints();
        FlowPoints toFlowPoints = to.getFlowPoints();
        Position fromRightFlowPoint = fromFlowPoints.getRightFlowPoint();
        Position toLeftFlowPoint = toFlowPoints.getLeftFlowPoint();
        if (fromRightFlowPoint != null && toLeftFlowPoint != null && fromRightFlowPoint.getY() < toLeftFlowPoint.getY()) {
            bpmnEdge.addChildElement(getWaypoint(modelInstance, fromFlowPoints.getDownFlowPoint().getX(), fromFlowPoints.getDownFlowPoint().getY()));
            bpmnEdge.addChildElement(getWaypoint(modelInstance, fromFlowPoints.getDownFlowPoint().getX(), toLeftFlowPoint.getY()));
            bpmnEdge.addChildElement(getWaypoint(modelInstance, toLeftFlowPoint.getX(), toLeftFlowPoint.getY()));
        } else if (fromRightFlowPoint != null && toLeftFlowPoint != null && fromRightFlowPoint.getY() > toLeftFlowPoint.getY()) {
            bpmnEdge.addChildElement(getWaypoint(modelInstance, fromRightFlowPoint.getX(), fromRightFlowPoint.getY()));
            bpmnEdge.addChildElement(getWaypoint(modelInstance, toFlowPoints.getDownFlowPoint().getX(), fromRightFlowPoint.getY()));
            bpmnEdge.addChildElement(getWaypoint(modelInstance, toFlowPoints.getDownFlowPoint().getX(), toFlowPoints.getDownFlowPoint().getY()));
        } else {
            bpmnEdge.addChildElement(getWaypoint(modelInstance, fromRightFlowPoint.getX(), fromRightFlowPoint.getY()));
            bpmnEdge.addChildElement(getWaypoint(modelInstance, toLeftFlowPoint.getX(), toLeftFlowPoint.getY()));
        }
        plane.addChildElement(bpmnEdge);

        return bpmnEdge;
    }

    private static Waypoint getWaypoint(BpmnModelInstance modelInstance, int x, int y) {
        Waypoint wp1 = modelInstance.newInstance(Waypoint.class);
        wp1.setX(x);
        wp1.setY(y);
        return wp1;
    }


    private static <T extends BpmnModelElementInstance> T createElement(Class<T> type,
                                                                        BpmnModelInstance modelInstance,
                                                                        BpmnPlane plane,
                                                                        Process process,
                                                                        String nextId,
                                                                        String name,
                                                                        ShapeBound shapeBound,
                                                                        BpmnLabel label) {
        T element = modelInstance.newInstance(type);
        element.setAttributeValue("id", nextId, true);
        element.setAttributeValue("name", name, false);
        process.addChildElement(element);

        BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
        bpmnShape.setBpmnElement((BaseElement) element);
        bpmnShape.setId(nextId + "_shape");
        bpmnShape.setBounds(getBounds(modelInstance, shapeBound));
        if (label != null) {
            bpmnShape.addChildElement(label);
        }

        plane.addChildElement(bpmnShape);
        return element;
    }

    private static Bounds getBounds(BpmnModelInstance modelInstance, ShapeBound shapeBound) {
        Bounds bounds = modelInstance.newInstance(Bounds.class);
        bounds.setX(shapeBound.getX());
        bounds.setY(shapeBound.getY());
        bounds.setHeight(shapeBound.getHeight());
        bounds.setWidth(shapeBound.getWidth());
        return bounds;
    }

    private static BpmnLabel getLabel(BpmnModelInstance modelInstance, String id, Label label) {
        BpmnLabel bpmnLabel = modelInstance.newInstance(BpmnLabel.class);
        bpmnLabel.setId(id);
        Bounds labelBounds = getBounds(modelInstance, new ShapeBound(label.getX(), label.getY(), 0, 0));
        bpmnLabel.addChildElement(labelBounds);
        return bpmnLabel;
    }
}
