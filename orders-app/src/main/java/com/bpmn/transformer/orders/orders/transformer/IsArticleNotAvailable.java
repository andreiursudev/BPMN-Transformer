package com.bpmn.transformer.orders.orders.transformer;

import com.bpmn.transformer.model.expression.Expression;

public class IsArticleNotAvailable extends Expression {
    public IsArticleNotAvailable() {
        super((s) -> s.equals(Boolean.FALSE));
    }
}
