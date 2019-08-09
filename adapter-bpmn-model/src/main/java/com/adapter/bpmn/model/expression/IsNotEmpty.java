package com.adapter.bpmn.model.expression;

public class IsNotEmpty extends Expression {
    public IsNotEmpty() {
        super((s) -> !((String) s).isEmpty());
    }
}
