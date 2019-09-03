package com.bpmn.transformer.orders.orders.transformer.camel.element;

import com.bpmn.transformer.camel.CamelAdapter;
import com.bpmn.transformer.camel.CamelAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.orders.orders.transformer.flowobject.CheckAvailability;

public class CheckAvailabilityCamelAdapterFactory implements CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        CheckAvailability checkAvailability = (CheckAvailability) flowObject;
        return new CheckAvailabilityCamelAdapter(checkAvailability.getMessage(), checkAvailability.getAvailableArticle());
    }
}
