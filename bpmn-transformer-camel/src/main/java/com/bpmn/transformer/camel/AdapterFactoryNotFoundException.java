package com.bpmn.transformer.camel;

import com.bpmn.transformer.model.flowobject.FlowObject;

public class AdapterFactoryNotFoundException extends RuntimeException {

    public AdapterFactoryNotFoundException(FlowObject flowObject) {
        super("No Adapter Factory found for " +flowObject.getClass().getSimpleName());
    }
}
