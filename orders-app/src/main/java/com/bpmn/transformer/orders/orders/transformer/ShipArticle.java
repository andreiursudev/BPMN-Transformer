package com.bpmn.transformer.orders.orders.transformer;

import com.bpmn.transformer.model.flowobject.activity.NamedActivity;

public class ShipArticle extends NamedActivity {
    public ShipArticle() {
        super("Ship article");
    }

    public String getMessage() {
        return "Article %s is shipped.";
    }
}
