package com.adapter.bpmn.bpmnjs;

public class Element {
    private String id;
    private String name;
    private ShapeBound shapeBound;

    public Element(String id, String name, ShapeBound shapeBound) {
        this.id = id;
        this.name = name;
        this.shapeBound = shapeBound;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ShapeBound getShapeBound() {
        return shapeBound;
    }

}
