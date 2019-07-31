package com.bpmncamelapp.flowobject.adapter;

import com.bpmncamelapp.CamelAdapter;
import com.bpmncamelapp.connectingobject.ConditionalFlow;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.ProcessorDefinition;

import java.util.List;

public class ExclusiveGatewayAdapter implements CamelAdapter {
    private List<ConditionalFlow> conditionalFlows;

    public ExclusiveGatewayAdapter(List<ConditionalFlow> conditionalFlows) {
        this.conditionalFlows = conditionalFlows;
    }

    @Override
    public void adapt(ProcessorDefinition processorDefinition) {
        ChoiceDefinition choiceDefinition = processorDefinition.choice();
        for (ConditionalFlow conditionalFlow : conditionalFlows) {

            choiceDefinition.when(e -> conditionalFlow.getExpression().getCondition().test(e.getIn().getBody(Object.class)));

            conditionalFlow.getFlowObject().forEach(flowObject -> flowObject.getCamelAdapter().adapt(choiceDefinition));
            choiceDefinition.endChoice();
        }
    }
}
