package com.adapter.bpmn.camel.exclusivegateway;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.camel.CamelAdapterFactory;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import com.adapter.bpmn.model.flowobject.FlowObject;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.ProcessorDefinition;

import java.util.List;
import java.util.Map;

public class ExclusiveGatewayCamelAdapter implements CamelAdapter {
    private List<ConditionalFlow> conditionalFlows;

    public ExclusiveGatewayCamelAdapter(List<ConditionalFlow> conditionalFlows) {
        this.conditionalFlows = conditionalFlows;
    }

    @Override
    public void adapt(ProcessorDefinition processorDefinition, Map<? extends Class<? extends FlowObject>, CamelAdapterFactory> dictionary) {
        ChoiceDefinition choiceDefinition = processorDefinition.choice();
        for (ConditionalFlow conditionalFlow : conditionalFlows) {
            choiceDefinition.when(e -> conditionalFlow.getExpression().getCondition().test(e.getIn().getBody(Object.class)));
            conditionalFlow.getFlowObjects().forEach(flowObject -> dictionary.get(flowObject.getClass()).getAdapter(flowObject).adapt(choiceDefinition, dictionary));
            choiceDefinition.endChoice();
        }
    }
}
