package com.adapter.bpmn.camel.sendto;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.camel.CamelAdapterFactory;
import com.adapter.bpmn.camel.sendto.SendTo;
import com.adapter.bpmn.camel.sendto.SendToCamelAdapter;
import com.adapter.bpmn.model.flowobject.FlowObject;

public class SendToCamelAdapterFactory implements CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        return new SendToCamelAdapter(((SendTo)flowObject).getUri());
    }
}
