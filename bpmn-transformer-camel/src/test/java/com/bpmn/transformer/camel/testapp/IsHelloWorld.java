package com.bpmn.transformer.camel.testapp;

import com.bpmn.transformer.model.expression.Expression;


public class IsHelloWorld extends Expression {
    public IsHelloWorld() {
        super((s) ->s.equals("Hello World"));
    }
}
