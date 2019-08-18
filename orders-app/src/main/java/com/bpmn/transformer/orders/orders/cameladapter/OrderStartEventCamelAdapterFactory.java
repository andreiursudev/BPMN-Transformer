package com.bpmn.transformer.orders.orders.cameladapter;

import com.bpmn.transformer.camel.CamelAdapter;
import com.bpmn.transformer.camel.CamelAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.orders.orders.OrderStartEvent;

public class OrderStartEventCamelAdapterFactory implements CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        return new OrderStartEventCamelAdapter(((OrderStartEvent)flowObject).getDirectoryName());
    }
}
