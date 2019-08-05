package com.adapter.bpmn.model;

import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.StartEvent;

import java.util.Arrays;
import java.util.List;

public class BusinessProcesses {
    private StartEvent startEvent;
    private List<FlowObject> flowObject;

    public BusinessProcesses(StartEvent startEvent, FlowObject... flowObjects) {
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
