package com.bearnecessities;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.model.Adapter;
import com.adapter.bpmn.model.flowobject.FlowObject;

public class LogBearNecessity implements FlowObject {
    @Override
    public CamelAdapter getAdapter() {
        return new LogBearNecessityCamelAdapter();
    }
}
