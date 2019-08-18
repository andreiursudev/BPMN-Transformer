package com.bpmn.transformer.camel.infolog;

import com.bpmn.transformer.camel.CamelAdapter;
import com.bpmn.transformer.camel.CamelAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;

public class InfoLogCamelAdapterFactory implements CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        return new InfoLogCamelAdapter();
    }
}
