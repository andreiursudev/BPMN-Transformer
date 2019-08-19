package com.bpmn.transformer.orders.orders.transformer.camel.element;

import com.bpmn.transformer.camel.BPMNToCamelDictionary;
import com.bpmn.transformer.camel.CamelAdapter;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.model.ProcessorDefinition;

public class CheckAvailabilityCamelAdapter implements CamelAdapter {
    private String message;
    private String availableArticle;

    public CheckAvailabilityCamelAdapter(String message, String availableArticle) {
        this.message = message;
        this.availableArticle = availableArticle;
    }

    @Override
    public void adapt(ProcessorDefinition processorDefinition, BPMNToCamelDictionary dictionary) {
        processorDefinition.setBody(new Expression() {
            @Override
            public <T> T evaluate(Exchange exchange, Class<T> type) {
                return type.cast(exchange.getIn().getBody(String.class).equals(availableArticle));
            }
        });
        processorDefinition.log(String.format(message, "${exchangeProperty.order.body}", "${body}"));
    }
}
