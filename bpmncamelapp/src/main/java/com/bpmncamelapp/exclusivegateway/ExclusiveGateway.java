package com.bpmncamelapp.exclusivegateway;

import ro.rodin.adapter.bpmn.model.Adapter;
import ro.rodin.adapter.bpmn.model.connectingobject.ConditionalFlow;

public class ExclusiveGateway extends ro.rodin.adapter.bpmn.model.flowobject.ExclusiveGateway {

    public ExclusiveGateway(ConditionalFlow... conditionalFlows) {
        super(conditionalFlows);
    }

    @Override
    public Adapter getAdapter() {
        return new ExclusiveGatewayCamelAdapter(getConditionalFlows());
    }
}
