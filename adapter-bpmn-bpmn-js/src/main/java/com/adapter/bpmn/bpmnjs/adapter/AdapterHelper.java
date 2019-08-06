package com.adapter.bpmn.bpmnjs.adapter;

import com.adapter.bpmn.bpmnjs.BPMNDiagramElement;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnEdge;
import org.camunda.bpm.model.bpmn.instance.di.Waypoint;

public class AdapterHelper {

    static BpmnEdge createSequenceFlow(BpmnModelInstance modelInstance, Process process, BPMNDiagramElement from, BPMNDiagramElement to, String conditionalFlowName) {
        FlowNode fromBpmnShape = (FlowNode) from.getBpmnShape().getBpmnElement();
        FlowNode toBpmnShape = (FlowNode) to.getBpmnShape().getBpmnElement();
        String identifier = fromBpmnShape.getId() + "-" + toBpmnShape.getId();
        SequenceFlow sequenceFlow = modelInstance.newInstance(SequenceFlow.class);
        sequenceFlow.setAttributeValue("id", identifier, true);
        sequenceFlow.setAttributeValue("name", conditionalFlowName, false);
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
