package com.bpmn.transformer.orders.orders.transformer;

import com.bpmn.transformer.model.connectingobject.ConnectingObject;
import com.bpmn.transformer.model.expression.Expression;
import com.bpmn.transformer.model.flowobject.FlowObject;

import java.util.Arrays;
import java.util.List;

public class ConditionalFlow implements ConnectingObject {

    private String property;
    private List<FlowObject> flowObjects;

    public ConditionalFlow(String property, FlowObject... flowObjects) {
        this.property = property;
        this.flowObjects = Arrays.asList(flowObjects);
    }

    public String getProperty() {
        return property;
    }

    public List<FlowObject> getFlowObjects() {
        return flowObjects;
    }
}
