package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.bpmnjs.adapter.ExclusiveGatewayBPMNJsAdapter;
import com.adapter.bpmn.model.Adapter;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;

public class ExclusiveGateway extends com.adapter.bpmn.model.flowobject.ExclusiveGateway {

    public ExclusiveGateway(ConditionalFlow... conditionalFlows) {
        super(conditionalFlows);
    }
    @Override
    public Adapter getAdapter() {
        return new ExclusiveGatewayBPMNJsAdapter("ok?", getConditionalFlows());
    }
}
