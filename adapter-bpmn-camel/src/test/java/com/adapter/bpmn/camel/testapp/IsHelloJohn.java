package com.adapter.bpmn.camel.testapp;

import com.adapter.bpmn.model.expression.Expression;

public class IsHelloJohn extends Expression{
    public IsHelloJohn() {
        super((s) ->s.equals("Hello John"));
    }
}