package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.startevent.StartEvent;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.*;

import java.util.List;
import java.util.Map;

public class BPMNJsDiagram {
    private List<BusinessProcesses> businessProcesses;
    private Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary;

    private Position currentPosition = new Position(0, 0);
    private BpmnModelInstance modelInstance;
    private ElementIdGenerator elementIdGenerator = new ElementIdGenerator();

    public BPMNJsDiagram(List<BusinessProcesses> businessProcesses, Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary) {

        this.businessProcesses = businessProcesses;
        this.dictionary = dictionary;
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
        BPMNDiagramElement currentElement = null;

        BPMNDiagram bpmnDiagram = new BPMNDiagram(modelInstance, plane, parentElement, currentElement);

        for (BusinessProcesses businessProcess : businessProcesses) {
            StartEvent startEvent = businessProcess.getStartEvent();
            BPMNElementAdapter adapter = dictionary.get(startEvent.getClass()).getAdapter(startEvent);
            bpmnDiagram.setCurrentElement(adapter.addElement(bpmnDiagram, null, elementIdGenerator, currentPosition, dictionary));
            for (FlowObject flowObject : businessProcess.getFlowObjects()) {
                BPMNElementAdapter flowObjectAdapter = dictionary.get(flowObject.getClass()).getAdapter(flowObject);
                bpmnDiagram.setCurrentElement(flowObjectAdapter.addElement(bpmnDiagram, null, elementIdGenerator, currentPosition, dictionary));
            }
        }
    }


}
