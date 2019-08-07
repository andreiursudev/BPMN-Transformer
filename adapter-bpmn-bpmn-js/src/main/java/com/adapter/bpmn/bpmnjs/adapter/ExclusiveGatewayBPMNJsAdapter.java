package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.*;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.model.bpmn.instance.EndEvent;
import org.camunda.bpm.model.bpmn.instance.ExclusiveGateway;

import java.util.ArrayList;
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

        Element exclusiveGateway = new Element(nextId, name, shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth, labelXPosition, labelYPosition,
                new Position(currentPosition.getX() + 15, currentPosition.getY()+45),
                new Position(currentPosition.getX() + 60, currentPosition.getY()+ 45),
                new Position(currentPosition.getX() + 40,currentPosition.getY()+ 70));

        BPMNDiagramElement exclusiveGatewayDiagram = createElementWithCustomLabelAndSequenceFlow(ExclusiveGateway.class, bpmnDiagram, exclusiveGateway);
        bpmnDiagram.setCurrentElement(exclusiveGatewayDiagram);
        Position exclusiveGatewayPosition = new Position(currentPosition.getX(),currentPosition.getY());
        Position conditionalFlowPosition = new Position(currentPosition.getX(), currentPosition.getY());
        List<BPMNDiagramElement> conditionalFlowsLastElement = new ArrayList<>();
        for (ConditionalFlow conditionalFlow : conditionalFlows) {
            conditionalFlowName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(conditionalFlow.getExpression().getClass().getSimpleName()), ' ');
            conditionalFlowPosition.incrementX();
            for (FlowObject flowObject : conditionalFlow.getFlowObjects()) {
                BPMNDiagramElement currentElement = ((BPMNJsAdapter) flowObject.getAdapter()).addElement(bpmnDiagram, conditionalFlowName, elementIdGenerator, conditionalFlowPosition);
                bpmnDiagram.setCurrentElement(currentElement);
                conditionalFlowName = "";
                conditionalFlowPosition.incrementX();
            }
                conditionalFlowsLastElement.add(bpmnDiagram.getCurrentElement());
            conditionalFlowPosition = new Position(exclusiveGatewayPosition.getX(),exclusiveGatewayPosition.getY());
            conditionalFlowPosition.incrementY();
            bpmnDiagram.setCurrentElement(exclusiveGatewayDiagram);
        }

        conditionalFlows.get(0).getFlowObjects().forEach((e) -> currentPosition.incrementX());


        if (conditionalFlowsLastElement.stream().filter((element) ->!(element.getFlowNode() instanceof EndEvent)).count() > 0) {
            BPMNDiagramElement conditionalFlowLastElement = conditionalFlowsLastElement.get(0);
                bpmnDiagram.setCurrentElement(conditionalFlowLastElement);
                String balanceId = nextId + "_balance";
            currentPosition.incrementX();

                shapeBoundXPosition = currentPosition.getX() + 15;
                shapeBoundYPosition = currentPosition.getY() + 20;

                Element balanceExclusiveGateway = new Element(balanceId, "", shapeBoundXPosition, shapeBoundYPosition, shapeBoundHeight, shapeBoundWidth, 0,0,
                        new Position(currentPosition.getX() + 15, 45),
                        new Position(currentPosition.getX() + 60, 45),
                        new Position(currentPosition.getX() + 40,currentPosition.getY()+ 70));

            BPMNDiagramElement balanceExclusiveGatewayElement = createElementWithSequenceFlow(ExclusiveGateway.class, bpmnDiagram, balanceExclusiveGateway);
            bpmnDiagram.setCurrentElement(balanceExclusiveGatewayElement);

            conditionalFlowsLastElement.stream().skip(1).forEach((element) ->{
                createSequenceFlow(bpmnDiagram.getModelInstance(),bpmnDiagram.getParentElement(),element,balanceExclusiveGatewayElement,"", bpmnDiagram.getPlane());
            });
        }
        return bpmnDiagram.getCurrentElement();
    }



}
