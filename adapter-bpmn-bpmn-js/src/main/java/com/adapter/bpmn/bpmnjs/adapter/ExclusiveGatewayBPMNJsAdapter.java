package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.ElementIdGenerator;
import com.adapter.bpmn.bpmnjs.Position;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnLabel;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;

import java.util.List;

public class ExclusiveGatewayBPMNJsAdapter implements BPMNJsAdapter {


    private String name;
    private List<ConditionalFlow> conditionalFlows;

    public ExclusiveGatewayBPMNJsAdapter(String name, List<ConditionalFlow> conditionalFlows) {

        this.name = name;
        this.conditionalFlows = conditionalFlows;
    }

    @Override
    public BPMNDiagramElement addElement(BpmnPlane plane, BpmnModelInstance modelInstance, Process parentElement, BPMNDiagramElement previousBpmnDiagramElement, String conditionalFlowName, ElementIdGenerator elementIdGenerator, Position currentPosition) {
        ExclusiveGateway exclusiveGateway = modelInstance.newInstance(ExclusiveGateway.class);
        String nextId = elementIdGenerator.getNextId();
        exclusiveGateway.setAttributeValue("id", nextId, true);
        exclusiveGateway.setAttributeValue("name", name, false);
        parentElement.addChildElement(exclusiveGateway);

        BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
        bpmnShape.setBpmnElement(exclusiveGateway);
        bpmnShape.setId(nextId + "_0");

        bpmnShape.setBounds(getBounds(modelInstance, currentPosition.getX() + 15, currentPosition.getY() + 20, 50, 50));
        bpmnShape.addChildElement(getExclusiveGatewayLabel(modelInstance, currentPosition, nextId + "_1"));


        plane.addChildElement(bpmnShape);


        BPMNDiagramElement bpmnDiagramElement = new BPMNDiagramElement(bpmnShape, currentPosition.getX() + 15, 45, currentPosition.getX() + 60, 45);

        BpmnEdge bpmnEdge = AdapterHelper.createSequenceFlow(modelInstance, parentElement, previousBpmnDiagramElement, bpmnDiagramElement, conditionalFlowName);

        plane.addChildElement(bpmnEdge);

        BPMNDiagramElement currentElement = bpmnDiagramElement;
        for (ConditionalFlow conditionalFlow : conditionalFlows) {
            conditionalFlowName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(conditionalFlow.getExpression().getClass().getSimpleName()), ' ');
            currentPosition.increment();
            for (FlowObject flowObject : conditionalFlow.getFlowObjects()) {
                currentElement = ((BPMNJsAdapter) flowObject.getAdapter()).addElement(plane, modelInstance, parentElement, currentElement, conditionalFlowName, elementIdGenerator, currentPosition);
                conditionalFlowName = "";
                currentPosition.increment();
            }

        }

        ExclusiveGateway balanceExclusiveGateway = modelInstance.newInstance(ExclusiveGateway.class);
        balanceExclusiveGateway.setAttributeValue("id", nextId+"_balance", true);
        parentElement.addChildElement(balanceExclusiveGateway);

        BpmnShape balanceBpmnShape = modelInstance.newInstance(BpmnShape.class);
        balanceBpmnShape.setBpmnElement(balanceExclusiveGateway);
        balanceBpmnShape.setId(nextId +"_balance" +  "_0");

        balanceBpmnShape.setBounds(getBounds(modelInstance, currentPosition.getX() + 15, currentPosition.getY() + 20, 50, 50));
        balanceBpmnShape.addChildElement(getExclusiveGatewayLabel(modelInstance, currentPosition, nextId + "_balance" +"_1"));

        plane.addChildElement(balanceBpmnShape);

        BPMNDiagramElement balanceBpmnDiagramElement = new BPMNDiagramElement(balanceBpmnShape, currentPosition.getX() + 15, 45, currentPosition.getX() + 60, 45);

        BpmnEdge balanceBpmnEdge = AdapterHelper.createSequenceFlow(modelInstance, parentElement, currentElement, balanceBpmnDiagramElement, null);

        plane.addChildElement(balanceBpmnEdge);


        return balanceBpmnDiagramElement;
    }

    private Bounds getBounds(BpmnModelInstance modelInstance, int currentXPosition, int currentYPosition, int height, int width) {
        Bounds bounds = modelInstance.newInstance(Bounds.class);
        bounds.setX(currentXPosition);
        bounds.setY(currentYPosition);
        bounds.setHeight(height);
        bounds.setWidth(width);
        return bounds;
    }

    private BpmnLabel getExclusiveGatewayLabel(BpmnModelInstance modelInstance, Position currentPosition, String id) {
        BpmnLabel bpmnLabel = modelInstance.newInstance(BpmnLabel.class);
        bpmnLabel.setId(id);
        Bounds labelBounds = getBounds(modelInstance, currentPosition.getX() + 40, currentPosition.getY(), 0, 0);
        bpmnLabel.addChildElement(labelBounds);
        return bpmnLabel;
    }

}
