package com.bearnecessities.orders;

import com.adapter.bpmn.camel.BPMNToCamelDictionary;

public class OrdersDictionary extends BPMNToCamelDictionary {
    public OrdersDictionary() {
        getDictionary().put(OrderStartEvent.class, new OrderStartEventCamelAdapterFactory());
    }
}
