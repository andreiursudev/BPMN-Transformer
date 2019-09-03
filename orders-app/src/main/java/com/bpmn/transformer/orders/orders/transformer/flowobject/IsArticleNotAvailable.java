package com.bpmn.transformer.orders.orders.transformer.flowobject;

import com.bpmn.transformer.model.expression.Expression;

public class IsArticleNotAvailable extends Expression {
    public IsArticleNotAvailable() {
        super((s) -> s.equals(Boolean.FALSE));
    }
}
