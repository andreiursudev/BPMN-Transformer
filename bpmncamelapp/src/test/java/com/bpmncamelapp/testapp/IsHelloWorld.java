package com.bpmncamelapp.testapp;

import ro.rodin.adapter.bpmn.model.expression.Expression;


public class IsHelloWorld extends Expression {
    public IsHelloWorld() {
        super((s) ->s.equals("Hello World"));
    }
}
