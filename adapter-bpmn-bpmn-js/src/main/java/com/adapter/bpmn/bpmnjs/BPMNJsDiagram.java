package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.bpmnjs.diagram.BPMNDiagramElement;
import com.adapter.bpmn.bpmnjs.diagram.ElementIdGenerator;
import com.adapter.bpmn.bpmnjs.diagram.Position;
import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.startevent.StartEvent;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnDiagram;
import org.camunda.bpm.model.bpmn.instance.bpmndi.BpmnPlane;

import java.util.List;
import java.util.Map;

public class BPMNJsDiagram {
    private BpmnModelInstance modelInstance;
    private Process process;
    private BpmnPlane plane;
    private ElementIdGenerator elementIdGenerator = new ElementIdGenerator();
    private Position currentPosition = new Position(0, 0);
    private BPMNDiagramElement lastNode;
    private BPMNToBPMNElementsDictionary dictionary;

    public BPMNJsDiagram() {
    }

    public String asXml(List<BusinessProcesses> businessProcesses, BPMNToBPMNElementsDictionary dictionary) {
        this.dictionary = dictionary;
        buildDiagram(businessProcesses);
        Bpmn.validateModel(modelInstance);
        return Bpmn.convertToString(modelInstance);
    }

    void buildDiagram(List<BusinessProcesses> businessProcesses) {
        modelInstance = Bpmn.createEmptyModel();
        Definitions definitions = modelInstance.newInstance(Definitions.class);
        definitions.setTargetNamespace("http://camunda.org/examples");
        definitions.setId("definitions");
        modelInstance.setDefinitions(definitions);

        process = modelInstance.newInstance(Process.class);
        process.setAttributeValue("id", "ProjectName", true);
        definitions.addChildElement(process);

        BpmnDiagram diagram = modelInstance.newInstance(BpmnDiagram.class);
        diagram.setId("BPMNDiagram");

        plane = modelInstance.newInstance(BpmnPlane.class);
        plane.setBpmnElement(process);
        plane.setId("BPMNPlane");

        diagram.setBpmnPlane(plane);
        definitions.addChildElement(diagram);

        adaptFlowObject(businessProcesses);
    }

    private void adaptFlowObject( List<BusinessProcesses> businessProcesses) {
        for (BusinessProcesses businessProcess : businessProcesses) {
            StartEvent startEvent = businessProcess.getStartEvent();
            BPMNElementAdapter adapter = dictionary.getAdapter(startEvent);
            this.setLastNode(adapter.addElement(this, null));
            for (FlowObject flowObject : businessProcess.getFlowObjects()) {
                BPMNElementAdapter flowObjectAdapter = dictionary.getAdapter(flowObject);
                this.setLastNode(flowObjectAdapter.addElement(this, null));
            }
        }
    }

    public BpmnModelInstance getModelInstance() {
        return modelInstance;
    }

    public BpmnPlane getPlane() {
        return plane;
    }

    public Process getProcess() {
        return process;
    }

    public BPMNDiagramElement getLastNode() {
        return lastNode;
    }

    public void setLastNode(BPMNDiagramElement lastNode) {
        this.lastNode = lastNode;
    }

    public ElementIdGenerator getElementIdGenerator() {
        return elementIdGenerator;
    }

    public BPMNToBPMNElementsDictionary getDictionary() {
        return dictionary;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public String getNextId() {
        return getElementIdGenerator().getNextId();
    }

    public void incrementX() {
        currentPosition.incrementX();
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = new Position(currentPosition.getX(),currentPosition.getY());
    }

    public void incrementY() {
        currentPosition.incrementY();
    }

    public void decrementY() {
        currentPosition.decrementY();
    }

    public void decrementX() {
        currentPosition.decrementX();

    }
}
