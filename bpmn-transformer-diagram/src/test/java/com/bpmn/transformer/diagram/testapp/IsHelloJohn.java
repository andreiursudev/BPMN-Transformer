package com.bpmn.transformer.diagram.testapp;

import com.bpmn.transformer.model.expression.Expression;

public class IsHelloJohn extends Expression{
    public IsHelloJohn() {
        super((s) ->s.equals("Hello John"));
    }
}
