package com.adapter.bpmn.camel.exclusivegateway;


import com.adapter.bpmn.model.Adapter;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;

public class ExclusiveGateway extends com.adapter.bpmn.model.flowobject.ExclusiveGateway {

    public ExclusiveGateway(ConditionalFlow... conditionalFlows) {
        super(conditionalFlows);
    }

    @Override
    public Adapter getAdapter() {
        return new ExclusiveGatewayCamelAdapter(getConditionalFlows());
    }
}
