package com.adapter.bpmn.bpmnjs.exclusivegateway;

import com.adapter.bpmn.bpmnjs.BPMNElementAdapter;
import com.adapter.bpmn.bpmnjs.BPMNElementAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.exclusivegateway.ExclusiveGateway;

public class ExclusiveGatewayBPMNElementAdapterFactory implements BPMNElementAdapterFactory {
    @Override
    public BPMNElementAdapter getAdapter(FlowObject flowObject) {
        return new ExclusiveGatewayBPMNElementAdapter("ok?",((ExclusiveGateway)flowObject).getConditionalFlows());
    }
}
