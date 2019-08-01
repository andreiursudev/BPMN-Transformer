package com.bpmncamelapp.testapp;

import ro.rodin.adapter.bpmn.model.expression.Expression;

public class IsHelloJohn extends Expression{
    public IsHelloJohn() {
        super((s) ->s.equals("Hello John"));
    }
}
