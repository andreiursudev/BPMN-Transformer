package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.bpmnjs.adapter.BPMNJsAdapter;
import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.*;

import java.util.List;

public class BPMNJsDiagram {
    private List<BusinessProcesses> businessProcesses;

    private Position currentPosition = new Position(0, 0);
    private BpmnModelInstance modelInstance;
    private ElementIdGenerator elementIdGenerator =new ElementIdGenerator();

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
        BPMNDiagramElement currentElement;
        for (BusinessProcesses businessProcess : businessProcesses) {
            BPMNJsAdapter adapter = (BPMNJsAdapter) businessProcess.getStartEvent().getAdapter();
            currentElement = adapter.addElement(plane, modelInstance, parentElement, null, null, elementIdGenerator, currentPosition);
            currentPosition.increment();
            for (FlowObject flowObject : businessProcess.getFlowObjects()) {
                currentElement = ((BPMNJsAdapter) flowObject.getAdapter()).addElement(plane, modelInstance, parentElement, currentElement, null,elementIdGenerator, currentPosition);
                currentPosition.increment();
            }
        }
    }





}
