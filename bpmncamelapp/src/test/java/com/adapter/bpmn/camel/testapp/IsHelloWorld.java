package com.adapter.bpmn.camel.testapp;

import com.adapter.bpmn.model.expression.Expression;


public class IsHelloWorld extends Expression {
    public IsHelloWorld() {
        super((s) ->s.equals("Hello World"));
    }
}
