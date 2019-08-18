package com.bpmn.transformer.camel.startevent;

import com.bpmn.transformer.camel.CamelAdapter;
import com.bpmn.transformer.camel.CamelAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.model.flowobject.startevent.UriStartEvent;

public class StartFromCamelAdapterFactory implements CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        return new StartFromCamelAdapter(((UriStartEvent)flowObject).getUri());
    }
}
