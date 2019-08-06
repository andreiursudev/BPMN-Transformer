package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.model.Adapter;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.*;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.bpmndi.*;
import org.camunda.bpm.model.bpmn.instance.dc.Bounds;
import org.camunda.bpm.model.bpmn.instance.di.Waypoint;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ApplicationToBPMNJsDiagram {

    int x = 15;

    public BpmnModelInstance modelInstance;
    private int elementCounter = 0;
    private int y = 15;

    public static void main(String[] args) throws IOException {
        ApplicationToBPMNJsDiagram createProcess = new ApplicationToBPMNJsDiagram();
        createProcess.generateProcess();
    }

    public void generateProcess() throws IOException {



        modelInstance = Bpmn.createEmptyModel();
        Definitions definitions = modelInstance.newInstance(Definitions.class);
        definitions.setTargetNamespace("http://camunda.org/examples");
        modelInstance.setDefinitions(definitions);

        // create the process
        Process process = modelInstance.newInstance(Process.class);
        process.setAttributeValue("id", "ProjectName", true);
        definitions.addChildElement(process);

        BpmnDiagram diagram = modelInstance.newInstance(BpmnDiagram.class);
        BpmnPlane plane = modelInstance.newInstance(BpmnPlane.class);
        plane.setBpmnElement(process);
        diagram.setBpmnPlane(plane);
        definitions.addChildElement(diagram);


        List<FlowObject> flowObjects = new ArrayList<>();
        flowObjects.add(new com.adapter.bpmn.model.flowobject.StartEvent() {
            @Override
            public Adapter getAdapter() {
                return null;
            }
        });
        /*flowObjects.add(new LogMessage("message"));
        flowObjects.add(new LogMessage("message"));
        flowObjects.add(new LogMessage("message"));
        flowObjects.add(new com.bpmn.cameladapter.flowobject.ExclusiveGateway(new ConditionalFlow(new IsEmpty(), new LogMessage("Test"), new LogMessage("test")),
                new ConditionalFlow(new IsNotEmpty(), new LogMessage("testset"))));
        flowObjects.add(new MyStopEvent());*/

        FlowNode previousElement = null ;


        adaptFlowObject(process, plane, flowObjects, previousElement);




        // validate and write model to file
        Bpmn.validateModel(modelInstance);
        OutputStream stream = new FileOutputStream("data1.js");
        stream.write("var diagram =`".getBytes(Charset.forName("UTF-8")));
        Bpmn.writeModelToStream(stream, modelInstance);
        stream.write("`".getBytes(Charset.forName("UTF-8")));


    }

    private FlowNode adaptFlowObject(Process process, BpmnPlane plane, List<FlowObject> flowObjects, FlowNode previousElement) {
        for (FlowObject flowObject : flowObjects) {
            int elementDistance = 200;
            if(flowObject instanceof com.adapter.bpmn.model.flowobject.StartEvent){
                StartEvent startEvent = createElement(process, "start" + this.elementCounter++, "startEvent",
                        StartEvent.class, plane, x, y, 50, 50, true);
                previousElement = startEvent;
                x += elementDistance;
            }
            /*if(flowObject instanceof StopEvent){
                EndEvent endEvent = createElement(process, "end"+ this.elementCounter++, MyStopEvent.class.getSimpleName(),
                        EndEvent.class, plane, x, y, 50, 50, true);
                createSequenceFlow(process, previousElement, endEvent, plane, x -100, y+25, x, y+25);
                previousElement = endEvent;
                x += elementDistance;
            }
            if(flowObject instanceof Activity){
                Task taskElement = createElement(process, "myTask"+ this.elementCounter++, "myTask"+ this.elementCounter,
                        Task.class, plane, x, 0, 80, 100, false);
                createSequenceFlow(process, previousElement, taskElement, plane, x-100, y+25, x, y+25);
                previousElement = taskElement;
                x += elementDistance;
            }
            if(flowObject instanceof com.bpmn.cameladapter.flowobject.ExclusiveGateway){
                ExclusiveGateway exclusiveGateway = createElement(process, "gateway" + this.elementCounter++, "gateway name",
                        ExclusiveGateway.class, plane, x, y, 50, 50, false);
                createSequenceFlow(process, previousElement, exclusiveGateway, plane, x-100, y+25, x, y+25);



                previousElement = exclusiveGateway;
                x += elementDistance;


                for (ConditionalFlow conditionalFlow : ((com.bpmn.cameladapter.flowobject.ExclusiveGateway) flowObject).getConditionalFlows()) {
                    previousElement = adaptFlowObject(process,plane,conditionalFlow.getFlowObject(), exclusiveGateway);
                }


                ExclusiveGateway balanceGateway = createElement(process, "gateway" + this.elementCounter++, "gateway name",
                        ExclusiveGateway.class, plane, x, y, 50, 50, false);
                createSequenceFlow(process, previousElement, balanceGateway, plane, x-100, y+25, x, y+25);
                previousElement = balanceGateway;
                x += elementDistance;


            }*/

        }
        return previousElement;
    }

    protected <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement,
                                                                   String id, String name, Class<T> elementClass, BpmnPlane plane,
                                                                   double x, double y, double heigth, double width, boolean withLabel) {
        T element = modelInstance.newInstance(elementClass);
        element.setAttributeValue("id", id, true);
        element.setAttributeValue("name", name, false);
        parentElement.addChildElement(element);

        BpmnShape bpmnShape = modelInstance.newInstance(BpmnShape.class);
        bpmnShape.setBpmnElement((BaseElement) element);

        Bounds bounds = modelInstance.newInstance(Bounds.class);
        bounds.setX(x);
        bounds.setY(y);
        bounds.setHeight(heigth);
        bounds.setWidth(width);
        bpmnShape.setBounds(bounds);

        if (withLabel) {
            BpmnLabel bpmnLabel = modelInstance.newInstance(BpmnLabel.class);
            Bounds labelBounds = modelInstance.newInstance(Bounds.class);
            labelBounds.setX(x);
            labelBounds.setY(y + heigth);
            labelBounds.setHeight(heigth);
            labelBounds.setWidth(width);
            bpmnLabel.addChildElement(labelBounds);
            bpmnShape.addChildElement(bpmnLabel);
        }
        plane.addChildElement(bpmnShape);

        return element;
    }

    public SequenceFlow createSequenceFlow(org.camunda.bpm.model.bpmn.instance.Process process, FlowNode from, FlowNode to, BpmnPlane plane,
                                           int... waypoints) {
        String identifier = from.getId() + "-" + to.getId();
        SequenceFlow sequenceFlow = modelInstance.newInstance(SequenceFlow.class);
        sequenceFlow.setAttributeValue("id", identifier, true);
        process.addChildElement(sequenceFlow);
        sequenceFlow.setSource(from);
        from.getOutgoing().add(sequenceFlow);
        sequenceFlow.setTarget(to);
        to.getIncoming().add(sequenceFlow);

        BpmnEdge bpmnEdge = modelInstance.newInstance(BpmnEdge.class);
        bpmnEdge.setBpmnElement(sequenceFlow);
        for (int i = 0; i < waypoints.length / 2; i++) {
            double waypointX = waypoints[i*2];
            double waypointY = waypoints[i*2+1];
            Waypoint wp = modelInstance.newInstance(Waypoint.class);
            wp.setX(waypointX);
            wp.setY(waypointY);
            bpmnEdge.addChildElement(wp);
        }
        plane.addChildElement(bpmnEdge);

        return sequenceFlow;
    }

}
