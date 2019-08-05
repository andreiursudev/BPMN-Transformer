package com.adapter.bpmn.model.connectingobject;

import com.adapter.bpmn.model.expression.Expression;
import com.adapter.bpmn.model.flowobject.FlowObject;

import java.util.Arrays;
import java.util.List;

public class ConditionalFlow implements ConnectingObject{

    private Expression expression;
    private List<FlowObject> flowObject;

    public ConditionalFlow(Expression expression, FlowObject ...flowObject) {
        this.expression = expression;
        this.flowObject = Arrays.asList(flowObject);
    }

    public Expression getExpression(){
        return expression;
    }

    public List<FlowObject> getFlowObject() {
        return flowObject;
    }
}
