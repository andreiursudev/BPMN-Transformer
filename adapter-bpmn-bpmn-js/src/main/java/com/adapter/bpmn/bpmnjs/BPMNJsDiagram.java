package com.adapter.bpmn.bpmnjs;

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


    private Position currentPosition = new Position(0, 0);
    private ElementIdGenerator elementIdGenerator = new ElementIdGenerator();

    public BPMNJsDiagram() {
    }

    public String asXml(List<BusinessProcesses> businessProcesses, Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary) {
        buildDiagram(businessProcesses, dictionary);
        Bpmn.validateModel(modelInstance);
        return Bpmn.convertToString(modelInstance);
    }

    void buildDiagram(List<BusinessProcesses> businessProcesses, Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary) {
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

        adaptFlowObject(businessProcesses, dictionary);
    }

    private void adaptFlowObject( List<BusinessProcesses> businessProcesses, Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary) {
        BPMNDiagramElement currentElement = null;

        BPMNDiagram bpmnDiagram = new BPMNDiagram(modelInstance, plane, process, currentElement);

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
