package com.bpmn.transformer.camel;

import com.bpmn.transformer.model.flowobject.FlowObject;

public interface CamelAdapterFactory {
    CamelAdapter getAdapter(FlowObject flowObject);
}
