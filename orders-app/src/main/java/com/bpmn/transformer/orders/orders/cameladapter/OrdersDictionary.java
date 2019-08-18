package com.bpmn.transformer.orders.orders.cameladapter;

import com.bpmn.transformer.camel.BPMNToCamelDictionary;
import com.bpmn.transformer.orders.orders.OrderStartEvent;

public class OrdersDictionary extends BPMNToCamelDictionary {
    public OrdersDictionary() {
        getDictionary().put(OrderStartEvent.class, new OrderStartEventCamelAdapterFactory());
    }
}
