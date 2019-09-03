package com.bpmn.transformer.orders.orders.transformer.flowobject;

import com.bpmn.transformer.model.flowobject.activity.NamedActivity;

public class Procurement extends NamedActivity {
    public Procurement() {
        super("Procurement");
    }

    public String getMessage() {
        return "Article %s has been procured.";
    }
}
