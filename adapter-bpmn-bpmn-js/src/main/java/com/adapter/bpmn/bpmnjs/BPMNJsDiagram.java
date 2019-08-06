package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.bpmnjs.adapter.ActivityBPMNJsAdapter;
import com.adapter.bpmn.bpmnjs.adapter.StartEventBPMNJsAdapter;
import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.*;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.bpmn.instance.di.Waypoint;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import java.util.List;

public class BPMNJsDiagram {
    private List<BusinessProcesses> businessProcesses;
    private int elementsId = 0;
    private int currentXPosition = 0;
    private int currentYPosition = 0;
    private BpmnModelInstance modelInstance;

    public BPMNJsDiagram(List<BusinessProcesses> businessProcesses) {

        this.businessProcesses = businessProcesses;
    }

    public String asXml() {
        BpmnModelInstance modelInstance = getBpmnModelInstance();


        Bpmn.validateModel(modelInstance);
        return Bpmn.convertToString(modelInstance);
    }

    BpmnModelInstance getBpmnModelInstance() {
        modelInstance = Bpmn.createEmptyModel();
        Definitions definitions = modelInstance.newInstance(Definitions.class);
        definitions.setTargetNamespace("http://camunda.org/examples");
        modelInstance.setDefinitions(definitions);

        Process process = modelInstance.newInstance(Process.class);
        process.setAttributeValue("id", "ProjectName", true);
        definitions.addChildElement(process);
        definitions.setId("definitions");

        BpmnDiagram diagram = modelInstance.newInstance(BpmnDiagram.class);
        BpmnPlane plane = modelInstance.newInstance(BpmnPlane.class);
        plane.setBpmnElement(process);
        plane.setId("BPMNPlane");
        diagram.setId("BPMNDiagram");
        diagram.setBpmnPlane(plane);
        definitions.addChildElement(diagram);


        adaptFlowObject(process, plane, businessProcesses);
        return modelInstance;
    }

    private void adaptFlowObject(Process parentElement, BpmnPlane plane, List<BusinessProcesses> businessProcesses) {
        for (BusinessProcesses businessProcess : businessProcesses) {
            StartEventBPMNJsAdapter adapter =(StartEventBPMNJsAdapter) businessProcess.getStartEvent().getAdapter();

            BpmnShapeWithFlowPoints startEventElement = createStartEventElement(adapter, parentElement);
            plane.addChildElement(startEventElement.getBpmnShape());
            for (FlowObject flowObject : businessProcess.getFlowObjects()) {
                incrementPosition();
                BpmnShapeWithFlowPoints taskElement = createTaskElement((ActivityBPMNJsAdapter) flowObject.getAdapter(), parentElement);
                plane.addChildElement(taskElement.getBpmnShape());
                BpmnEdge bpmnEdge = createSequenceFlow(parentElement, startEventElement, taskElement);
                plane.addChildElement(bpmnEdge);
            }

        }
    }

    private void incrementPosition() {
        currentXPosition =+ 300;
    }


    public BpmnEdge createSequenceFlow(Process process, BpmnShapeWithFlowPoints from, BpmnShapeWithFlowPoints to) {
        FlowNode fromBpmnShape = (FlowNode)from.getBpmnShape().getBpmnElement();
        FlowNode toBpmnShape = (FlowNode)to.getBpmnShape().getBpmnElement();
        String identifier = fromBpmnShape.getId() + "-" + toBpmnShape.getId();
        SequenceFlow sequenceFlow = modelInstance.newInstance(SequenceFlow.class);
        sequenceFlow.setAttributeValue("id", identifier, true);
        process.addChildElement(sequenceFlow);
        sequenceFlow.setSource(fromBpmnShape);
        fromBpmnShape.getOutgoing().add(sequenceFlow);
        sequenceFlow.setTarget(toBpmnShape);
        toBpmnShape.getIncoming().add(sequenceFlow);

        BpmnEdge bpmnEdge = modelInstance.newInstance(BpmnEdge.class);
        bpmnEdge.setId("edge_" +identifier);
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

    private BpmnShapeWithFlowPoints createTaskElement(ActivityBPMNJsAdapter adapter, BpmnModelElementInstance parentElement) {
        Task task = modelInstance.newInstance(Task.class);
        String elementId = getNextElementId();
        task.setAttributeValue("id", elementId, true);
        task.setAttributeValue("name", adapter.getName(), false);
        parentElement.addChildElement(task);

        BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
        bpmnShape.setBpmnElement(task);
        bpmnShape.setId(elementId + "_0");

        bpmnShape.setBounds(getBounds(currentXPosition, currentYPosition+10, 80, 150));

        bpmnShape.addChildElement(getTaskBpmnLabel(elementId + "_1"));

        return new BpmnShapeWithFlowPoints(bpmnShape, currentXPosition, 45,0,0);
    }

    private ModelElementInstance getTaskBpmnLabel(String id) {
        BpmnLabel bpmnLabel = modelInstance.newInstance(BpmnLabel.class);
        bpmnLabel.setId(id);
        Bounds labelBounds = getBounds(0, 0, 0, 0);
        bpmnLabel.addChildElement(labelBounds);
        return bpmnLabel;
    }

    private BpmnShapeWithFlowPoints createStartEventElement(StartEventBPMNJsAdapter adapter, BpmnModelElementInstance parentElement) {
        StartEvent startEvent = modelInstance.newInstance(StartEvent.class);
        String elementId = getNextElementId();
        startEvent.setAttributeValue("id", elementId, true);
        startEvent.setAttributeValue("name", adapter.getName(), false);
        parentElement.addChildElement(startEvent);

        BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
        bpmnShape.setBpmnElement(startEvent);
        bpmnShape.setId(elementId + "_0");

        bpmnShape.setBounds(getBounds(currentXPosition +15, currentYPosition +20, 50, 50));
        bpmnShape.addChildElement(getStartEventBpmnLabel(elementId + "_1"));

        return new BpmnShapeWithFlowPoints(bpmnShape, 0,0, currentXPosition + 65,currentYPosition + 45);
    }

    private BpmnLabel getStartEventBpmnLabel(String id) {
        BpmnLabel bpmnLabel = modelInstance.newInstance(BpmnLabel.class);
        bpmnLabel.setId(id);
        Bounds labelBounds = getBounds(currentXPosition + 40, currentYPosition + 75, 0, 0);
        bpmnLabel.addChildElement(labelBounds);
        return bpmnLabel;
    }

    private Bounds getBounds(int currentXPosition, int currentYPosition, int height, int width) {
        Bounds bounds = modelInstance.newInstance(Bounds.class);
        bounds.setX(currentXPosition);
        bounds.setY(currentYPosition);
        bounds.setHeight(height);
        bounds.setWidth(width);
        return bounds;
    }

    private String getNextElementId() {
        return "element_" + elementsId++;
    }
}
