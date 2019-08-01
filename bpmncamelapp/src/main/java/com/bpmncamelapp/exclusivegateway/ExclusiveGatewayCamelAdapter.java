package com.bpmncamelapp.exclusivegateway;

import com.bpmncamelapp.CamelAdapter;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.ProcessorDefinition;
import ro.rodin.adapter.bpmn.model.connectingobject.ConditionalFlow;

import java.util.List;

public class ExclusiveGatewayCamelAdapter implements CamelAdapter {
    private List<ConditionalFlow> conditionalFlows;

    public ExclusiveGatewayCamelAdapter(List<ConditionalFlow> conditionalFlows) {
        this.conditionalFlows = conditionalFlows;
    }

    @Override
    public void adapt(ProcessorDefinition processorDefinition) {
        ChoiceDefinition choiceDefinition = processorDefinition.choice();
        for (ConditionalFlow conditionalFlow : conditionalFlows) {

            choiceDefinition.when(e -> conditionalFlow.getExpression().getCondition().test(e.getIn().getBody(Object.class)));

            conditionalFlow.getFlowObject().forEach(flowObject -> ((CamelAdapter)flowObject.getAdapter()).adapt(choiceDefinition));
            choiceDefinition.endChoice();
        }
    }
}
