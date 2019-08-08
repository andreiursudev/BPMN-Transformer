package com.adapter.bpmn.model.expression;

import java.util.function.Predicate;

public class Expression {

    private Predicate condition;

    public Expression(Predicate condition) {
        this.condition = condition;
    }

    public Predicate getCondition() {
        return condition;
    }
}
