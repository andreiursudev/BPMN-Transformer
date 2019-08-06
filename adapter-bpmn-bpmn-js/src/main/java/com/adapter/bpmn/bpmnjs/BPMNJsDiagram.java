package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.bpmnjs.adapter.StartEventBPMNJsAdapter;
import com.adapter.bpmn.model.Adapter;
import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnDiagram;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnLabel;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnShape;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import java.util.List;

public class BPMNJsDiagram {
    private List<BusinessProcesses> businessProcesses;
    private int elementsId = 0;
    private int currentXPosition = 10;
    private int currentYPosition = 10;
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

            plane.addChildElement(createStartEventElement(adapter, parentElement));
            for (FlowObject flowObject : businessProcess.getFlowObjects()) {
                plane.addChildElement(createElement(flowObject.getAdapter(),parentElement));
            }

        }
    }

    private ModelElementInstance createElement(Adapter adapter, Process parentElement) {
        return null;
    }

    private BpmnShape createStartEventElement(StartEventBPMNJsAdapter adapter, BpmnModelElementInstance parentElement) {
        StartEvent startEvent = modelInstance.newInstance(StartEvent.class);
        String elementId = getNextElementId();
        startEvent.setAttributeValue("id", elementId, true);
        startEvent.setAttributeValue("name", adapter.getName(), false);
        parentElement.addChildElement(startEvent);

        BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
        bpmnShape.setBpmnElement(startEvent);
        bpmnShape.setId(elementId + "_0");


        bpmnShape.setBounds(getBounds(currentXPosition, currentYPosition, 50, 50));
        bpmnShape.addChildElement(getBpmnLabel(elementId + "_1"));

        return bpmnShape;
    }

    private BpmnLabel getBpmnLabel(String id) {
        BpmnLabel bpmnLabel = modelInstance.newInstance(BpmnLabel.class);
        bpmnLabel.setId(id);
        Bounds labelBounds = getBounds(currentXPosition + 30, currentYPosition + 55, 0, 0);
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
