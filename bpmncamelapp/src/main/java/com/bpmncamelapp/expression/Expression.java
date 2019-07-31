package com.bpmncamelapp.expression;

import java.util.function.Predicate;

public class Expression {

    Predicate condition;

    public Expression(Predicate condition) {
        this.condition = condition;
    }

    public Predicate getCondition() {
        return condition;
    }
}
