package com.adapter.bpmn.camel;

import com.adapter.bpmn.model.flowobject.FlowObject;

public interface CamelAdapterFactory {
    CamelAdapter getAdapter(FlowObject flowObject);
}
