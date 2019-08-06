package com.bearnecessities;

import com.adapter.bpmn.model.expression.Expression;

public class IsFood extends Expression {
    public IsFood() {
        super((s)->true);
    }
}
