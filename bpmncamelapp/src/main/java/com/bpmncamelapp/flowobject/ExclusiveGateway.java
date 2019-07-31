package com.bpmncamelapp.flowobject;

import com.bpmncamelapp.CamelAdapter;
import com.bpmncamelapp.connectingobject.ConditionalFlow;
import com.bpmncamelapp.flowobject.adapter.ExclusiveGatewayAdapter;

import java.util.Arrays;
import java.util.List;

public class ExclusiveGateway implements FlowObject{
    private List<ConditionalFlow> conditionalFlow;

    public ExclusiveGateway(ConditionalFlow ... conditionalFlows) {

        this.conditionalFlow = Arrays.asList(conditionalFlows);
    }

    public List<ConditionalFlow> getConditionalFlows() {
        return conditionalFlow;
    }

    @Override
    public CamelAdapter getCamelAdapter() {
        return new ExclusiveGatewayAdapter(getConditionalFlows());
    }
}
