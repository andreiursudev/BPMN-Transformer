package com.bpmn.transformer.orders.orders.transformer.flowobject;

import com.bpmn.transformer.model.flowobject.endevent.NamedEndEvent;

public class SendInvoice extends NamedEndEvent {
    public SendInvoice() {
        super("Send invoice");
    }

    public String getMessage() {
        return "Invoice for %s has been sent.";
    }
}
