package com.adapter.bpmn.camel.exclusivegateway;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.camel.CamelAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.exclusivegateway.ExclusiveGateway;

public class ExclusiveGatewayCamelAdapterFactory implements CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        return new ExclusiveGatewayCamelAdapter(((ExclusiveGateway) flowObject).getConditionalFlows());
    }
}
