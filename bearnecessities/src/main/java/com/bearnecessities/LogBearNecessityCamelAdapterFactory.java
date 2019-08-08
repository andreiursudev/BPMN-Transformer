package com.bearnecessities;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.model.flowobject.FlowObject;

public class LogBearNecessityCamelAdapterFactory implements com.adapter.bpmn.camel.CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        return new LogBearNecessityCamelAdapter();
    }
}
