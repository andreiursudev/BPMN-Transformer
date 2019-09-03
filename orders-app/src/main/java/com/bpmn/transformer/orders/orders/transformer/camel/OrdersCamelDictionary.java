package com.bpmn.transformer.orders.orders.transformer.camel;

import com.bpmn.transformer.camel.BPMNToCamelDictionary;
import com.bpmn.transformer.orders.orders.transformer.camel.element.*;
import com.bpmn.transformer.orders.orders.transformer.flowobject.*;

class OrdersCamelDictionary extends BPMNToCamelDictionary {

    OrdersCamelDictionary() {
        getDictionary().put(OrderStartEvent.class, new OrderStartEventCamelAdapterFactory());
        getDictionary().put(CheckAvailability.class, new CheckAvailabilityCamelAdapterFactory());
        getDictionary().put(Procurement.class, new ProcurementCamelAdapterFactory());
        getDictionary().put(ShipArticle.class, new ShipArticleCamelAdapterFactory());
        getDictionary().put(SendInvoice.class, new SendInvoiceCamelAdapterFactory());
    }
}
