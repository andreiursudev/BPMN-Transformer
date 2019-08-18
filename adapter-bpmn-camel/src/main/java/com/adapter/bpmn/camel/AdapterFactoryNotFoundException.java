package com.adapter.bpmn.camel;

import com.adapter.bpmn.model.flowobject.FlowObject;

public class AdapterFactoryNotFoundException extends RuntimeException {

    public AdapterFactoryNotFoundException(FlowObject flowObject) {
        super("No Adapter Factory found for " +flowObject.getClass().getSimpleName());
    }
}
