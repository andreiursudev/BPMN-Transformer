package com.adapter.bpmn.model.flowobject.activity;

public class NamedActivity implements Activity {

    private String name;

    public NamedActivity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
