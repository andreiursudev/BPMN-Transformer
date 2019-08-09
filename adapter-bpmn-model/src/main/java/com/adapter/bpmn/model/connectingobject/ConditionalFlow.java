package com.adapter.bpmn.model.connectingobject;

import com.adapter.bpmn.model.expression.Expression;
import com.adapter.bpmn.model.flowobject.FlowObject;

import java.util.Arrays;
import java.util.List;

public class ConditionalFlow implements ConnectingObject {

    private Expression expression;
    private List<FlowObject> flowObjects;

    public ConditionalFlow(Expression expression, FlowObject... flowObjects) {
        this.expression = expression;
        this.flowObjects = Arrays.asList(flowObjects);
    }

    public Expression getExpression() {
        return expression;
    }

    public List<FlowObject> getFlowObjects() {
        return flowObjects;
    }
}
