package com.adapter.bpmn.camel;

import com.adapter.bpmn.model.AdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;

public interface CamelAdapterFactory extends AdapterFactory {
    CamelAdapter getAdapter(FlowObject flowObject);
}
