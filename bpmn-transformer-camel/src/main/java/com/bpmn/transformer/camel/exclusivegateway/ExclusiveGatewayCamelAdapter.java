package com.bpmn.transformer.camel.exclusivegateway;

import com.bpmn.transformer.camel.CamelAdapter;
import com.bpmn.transformer.camel.BPMNToCamelDictionary;
import com.bpmn.transformer.model.connectingobject.ConditionalFlow;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.ProcessorDefinition;

import java.util.List;

public class ExclusiveGatewayCamelAdapter implements CamelAdapter {
    private List<ConditionalFlow> conditionalFlows;

    public ExclusiveGatewayCamelAdapter(List<ConditionalFlow> conditionalFlows) {
        this.conditionalFlows = conditionalFlows;
    }

    @Override
    public void adapt(ProcessorDefinition processorDefinition, BPMNToCamelDictionary dictionary) {
        ChoiceDefinition choiceDefinition = processorDefinition.choice();
        for (ConditionalFlow conditionalFlow : conditionalFlows) {
            choiceDefinition.when(e -> conditionalFlow.getExpression().getCondition().test(e.getIn().getBody(Object.class)));
            conditionalFlow.getFlowObjects().forEach(flowObject -> dictionary.getAdapter(flowObject).adapt(choiceDefinition, dictionary));
            choiceDefinition.endChoice();
        }
    }
}
