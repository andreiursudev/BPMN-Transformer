package com.bpmn.transformer.orders.orders.transformer.flowobject;

import com.bpmn.transformer.model.flowobject.activity.NamedActivity;

public class CheckAvailability extends NamedActivity {
    public CheckAvailability() {
        super("Check availability");
    }

    public String getMessage() {
        return "%s availability is : %s";
    }

    public String getAvailableArticle() {
        return "article1";
    }
}
