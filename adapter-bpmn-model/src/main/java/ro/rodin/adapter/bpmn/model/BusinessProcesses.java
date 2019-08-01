package ro.rodin.adapter.bpmn.model;

import ro.rodin.adapter.bpmn.model.flowobject.FlowObject;
import ro.rodin.adapter.bpmn.model.flowobject.StartEvent;

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

    public List<FlowObject> getFlowObject() {
        return flowObject;
    }
}
