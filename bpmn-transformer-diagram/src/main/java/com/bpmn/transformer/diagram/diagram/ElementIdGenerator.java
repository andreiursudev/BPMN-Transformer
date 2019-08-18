package com.bpmn.transformer.diagram.diagram;

public class ElementIdGenerator {

    private int elementsId = 0;

    public String getNextId() {
        return "element_" + elementsId++;
    }
}
