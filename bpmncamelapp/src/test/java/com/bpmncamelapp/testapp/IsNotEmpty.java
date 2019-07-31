package com.bpmncamelapp.testapp;

import com.bpmncamelapp.expression.Expression;


public class IsNotEmpty extends Expression {
    public IsNotEmpty() {
        super((s) ->!((String)s).isEmpty());
    }
}
