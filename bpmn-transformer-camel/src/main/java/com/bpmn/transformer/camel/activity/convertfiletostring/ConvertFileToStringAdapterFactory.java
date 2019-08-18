package com.bpmn.transformer.camel.activity.convertfiletostring;

import com.bpmn.transformer.camel.CamelAdapter;
import com.bpmn.transformer.camel.CamelAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;

public class ConvertFileToStringAdapterFactory implements CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        return new ConvertFileToStringAdapter();
    }
}
