package com.adapter.bpmn.camel.activity.sendto;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.camel.CamelAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.activity.SendTo;

public class SendToCamelAdapterFactory implements CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        return new SendToCamelAdapter(((SendTo)flowObject).getUri());
    }
}
