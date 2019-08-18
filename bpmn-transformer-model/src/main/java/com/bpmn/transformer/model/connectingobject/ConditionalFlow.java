package com.bpmn.transformer.model.connectingobject;

import com.bpmn.transformer.model.expression.Expression;
import com.bpmn.transformer.model.flowobject.FlowObject;

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
