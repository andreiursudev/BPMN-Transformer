package com.bpmn.transformer.model.flowobject.exclusivegateway;

import com.bpmn.transformer.model.connectingobject.ConditionalFlow;
import com.bpmn.transformer.model.flowobject.FlowObject;

import java.util.Arrays;
import java.util.List;

public class ExclusiveGateway implements FlowObject {
    private List<ConditionalFlow> conditionalFlow;

    public ExclusiveGateway(ConditionalFlow... conditionalFlows) {
        this.conditionalFlow = Arrays.asList(conditionalFlows);
    }

    public List<ConditionalFlow> getConditionalFlows() {
        return conditionalFlow;
    }

}
