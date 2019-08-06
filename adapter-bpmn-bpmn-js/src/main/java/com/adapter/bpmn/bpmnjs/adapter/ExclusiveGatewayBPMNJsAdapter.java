package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.ElementIdGenerator;
import com.adapter.bpmn.bpmnjs.Position;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;

import java.util.List;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElement;
import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElementWithLabel;
import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.createElementWithLabelAndSequenceFlow;

public class ExclusiveGatewayBPMNJsAdapter implements BPMNJsAdapter {


    private String name;
    private List<ConditionalFlow> conditionalFlows;

    public ExclusiveGatewayBPMNJsAdapter(String name, List<ConditionalFlow> conditionalFlows) {

        this.name = name;
        this.conditionalFlows = conditionalFlows;
    }

    @Override
    public BPMNDiagramElement addElement(BpmnPlane plane, BpmnModelInstance modelInstance, Process parentElement, BPMNDiagramElement previousBpmnDiagramElement, String conditionalFlowName, ElementIdGenerator elementIdGenerator, Position currentPosition) {
        String nextId = elementIdGenerator.getNextId();

        int shapeBoundXPosition = currentPosition.getX() + 15;
        int shapeBoundYPosition = currentPosition.getY() + 20;
        int shapeBoundHeight = 50;
        int shapeBoundWidth = 50;

        int labelXPosition = currentPosition.getX() + 40;
        int labelYPosition = currentPosition.getY();
        FlowNode exclusiveGateway = createElementWithLabel(ExclusiveGateway.class, modelInstance, plane, parentElement, nextId, this.name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth, labelXPosition, labelYPosition);

        BPMNDiagramElement bpmnDiagramElement = new BPMNDiagramElement(exclusiveGateway, currentPosition.getX() + 15, 45, currentPosition.getX() + 60, 45);

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
        String balanceId = nextId + "_balance";

         shapeBoundXPosition = currentPosition.getX() + 15;
         shapeBoundYPosition = currentPosition.getY() + 20;


        FlowNode balanceExclusiveGateway = createElement(ExclusiveGateway.class, modelInstance, plane, parentElement, balanceId, "", shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth);

        BPMNDiagramElement balanceBpmnDiagramElement = new BPMNDiagramElement(balanceExclusiveGateway, currentPosition.getX() + 15, 45, currentPosition.getX() + 60, 45);

        BpmnEdge balanceBpmnEdge = AdapterHelper.createSequenceFlow(modelInstance, parentElement, currentElement, balanceBpmnDiagramElement, null);

        plane.addChildElement(balanceBpmnEdge);


        return balanceBpmnDiagramElement;
    }




}
