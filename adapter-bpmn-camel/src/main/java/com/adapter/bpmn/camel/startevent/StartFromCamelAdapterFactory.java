package com.adapter.bpmn.camel.startevent;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.camel.CamelAdapterFactory;
import com.adapter.bpmn.model.flowobject.startevent.StartFrom;
import com.adapter.bpmn.model.flowobject.FlowObject;

public class StartFromCamelAdapterFactory implements CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        return new StartFromCamelAdapter(((StartFrom)flowObject).getUri());
    }
}
