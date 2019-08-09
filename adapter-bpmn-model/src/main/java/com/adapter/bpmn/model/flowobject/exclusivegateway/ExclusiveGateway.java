package com.adapter.bpmn.model.flowobject.exclusivegateway;

import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.model.flowobject.FlowObject;

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
