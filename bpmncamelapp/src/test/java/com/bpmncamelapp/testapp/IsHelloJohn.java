package com.bpmncamelapp.testapp;

import com.bpmncamelapp.expression.Expression;

public class IsHelloJohn extends Expression{
    public IsHelloJohn() {
        super((s) ->s.equals("Hello John"));
    }
}
