package com.adapter.bpmn.camel.expresion;

import com.adapter.bpmn.model.expression.Expression;


public class IsNotEmpty extends Expression {
    public IsNotEmpty() {
        super((s) ->!((String)s).isEmpty());
    }
}
