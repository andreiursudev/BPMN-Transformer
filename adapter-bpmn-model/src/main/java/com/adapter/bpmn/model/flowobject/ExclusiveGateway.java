package com.adapter.bpmn.model.flowobject;

import com.adapter.bpmn.model.connectingobject.ConditionalFlow;

import java.util.Arrays;
import java.util.List;

public abstract class ExclusiveGateway implements FlowObject{
    private List<ConditionalFlow> conditionalFlow;

    public ExclusiveGateway(ConditionalFlow ... conditionalFlows) {

        this.conditionalFlow = Arrays.asList(conditionalFlows);
    }

    public List<ConditionalFlow> getConditionalFlows() {
        return conditionalFlow;
    }

}
