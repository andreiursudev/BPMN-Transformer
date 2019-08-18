package com.bpmn.transformer.model;

import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.model.flowobject.startevent.StartEvent;

import java.util.Arrays;
import java.util.List;

public class BusinessProcess {
    private StartEvent startEvent;
    private List<FlowObject> flowObject;

    public BusinessProcess(StartEvent startEvent, FlowObject... flowObjects) {
        this.startEvent = startEvent;
        this.flowObject = Arrays.asList(flowObjects);
    }

    public StartEvent getStartEvent() {
        return startEvent;
    }

    public List<FlowObject> getFlowObjects() {
        return flowObject;
    }
}
