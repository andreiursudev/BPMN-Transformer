package com.bpmn.transformer.orders.orders.transformer.flowobject;

import com.bpmn.transformer.model.expression.Expression;

public class IsArticleAvailable extends Expression {
    public IsArticleAvailable() {
        super((s) -> s.equals(Boolean.TRUE));
    }
}
