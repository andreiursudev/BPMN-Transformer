package com.bpmncamelapp.testapp;

import ro.rodin.adapter.bpmn.model.expression.Expression;


public class IsNotEmpty extends Expression {
    public IsNotEmpty() {
        super((s) ->!((String)s).isEmpty());
    }
}
