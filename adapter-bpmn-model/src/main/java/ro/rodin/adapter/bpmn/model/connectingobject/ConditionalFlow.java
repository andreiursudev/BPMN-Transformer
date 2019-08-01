package ro.rodin.adapter.bpmn.model.connectingobject;

import ro.rodin.adapter.bpmn.model.flowobject.FlowObject;
import ro.rodin.adapter.bpmn.model.expression.Expression;

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
