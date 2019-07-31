package com.bpmncamelapp.testapp;

import com.bpmncamelapp.expression.Expression;


public class IsHelloWorld extends Expression {
    public IsHelloWorld() {
        super((s) ->s.equals("Hello World"));
    }
}
