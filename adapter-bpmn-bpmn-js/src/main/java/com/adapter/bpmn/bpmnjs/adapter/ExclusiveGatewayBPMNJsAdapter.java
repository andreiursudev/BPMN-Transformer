package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.*;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;

import java.util.List;

import static com.adapter.bpmn.bpmnjs.adapter.AdapterHelper.*;

public class ExclusiveGatewayBPMNJsAdapter implements BPMNJsAdapter {


    private String name;
    private List<ConditionalFlow> conditionalFlows;

    public ExclusiveGatewayBPMNJsAdapter(String name, List<ConditionalFlow> conditionalFlows) {

        this.name = name;
        this.conditionalFlows = conditionalFlows;
    }

    @Override
    public BPMNDiagramElement addElement(BPMNDiagram bpmnDiagram, String conditionalFlowName, ElementIdGenerator elementIdGenerator, Position currentPosition) {
        String nextId = elementIdGenerator.getNextId();

        int shapeBoundXPosition = currentPosition.getX() + 15;
        int shapeBoundYPosition = currentPosition.getY() + 20;
        int shapeBoundHeight = 50;
        int shapeBoundWidth = 50;

        int labelXPosition = currentPosition.getX() + 40;
        int labelYPosition = currentPosition.getY();

        Element exclusiveGateway = new Element(nextId, name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth, labelXPosition, labelYPosition, new Position(currentPosition.getX() + 15, 45),
                new Position(currentPosition.getX() + 60, 45));




        bpmnDiagram.setCurrentElement(createElementWithLabelAndSequenceFlow(ExclusiveGateway.class, bpmnDiagram, exclusiveGateway));
        for (ConditionalFlow conditionalFlow : conditionalFlows) {
            conditionalFlowName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(conditionalFlow.getExpression().getClass().getSimpleName()), ' ');
            currentPosition.increment();
            for (FlowObject flowObject : conditionalFlow.getFlowObjects()) {
                bpmnDiagram.setCurrentElement(((BPMNJsAdapter) flowObject.getAdapter()).addElement(bpmnDiagram, conditionalFlowName, elementIdGenerator, currentPosition));
                conditionalFlowName = "";
                currentPosition.increment();
            }

        }
        String balanceId = nextId + "_balance";

         shapeBoundXPosition = currentPosition.getX() + 15;
         shapeBoundYPosition = currentPosition.getY() + 20;

        Element balanceExclusiveGateway = new Element(balanceId, "", shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth, null,
                new Position(currentPosition.getX() + 15, 45),
                new Position(currentPosition.getX() + 60, 45));




        return createElementWithSequenceFlow(ExclusiveGateway.class, bpmnDiagram, balanceExclusiveGateway);
    }




}
