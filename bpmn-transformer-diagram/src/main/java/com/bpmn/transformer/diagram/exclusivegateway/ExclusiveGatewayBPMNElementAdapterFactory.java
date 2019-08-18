package com.bpmn.transformer.diagram.exclusivegateway;

import com.bpmn.transformer.diagram.BPMNElementAdapter;
import com.bpmn.transformer.diagram.BPMNElementAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.model.flowobject.exclusivegateway.ExclusiveGateway;

public class ExclusiveGatewayBPMNElementAdapterFactory implements BPMNElementAdapterFactory {
    @Override
    public BPMNElementAdapter getAdapter(FlowObject flowObject) {
        return new ExclusiveGatewayBPMNElementAdapter("ok?",((ExclusiveGateway)flowObject).getConditionalFlows());
    }
}
