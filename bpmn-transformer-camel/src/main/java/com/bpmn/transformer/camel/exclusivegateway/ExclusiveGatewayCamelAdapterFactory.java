package com.bpmn.transformer.camel.exclusivegateway;

import com.bpmn.transformer.camel.CamelAdapter;
import com.bpmn.transformer.camel.CamelAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.model.flowobject.exclusivegateway.ExclusiveGateway;

public class ExclusiveGatewayCamelAdapterFactory implements CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        return new ExclusiveGatewayCamelAdapter(((ExclusiveGateway) flowObject).getConditionalFlows());
    }
}
