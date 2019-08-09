package com.adapter.bpmn.camel;

import com.adapter.bpmn.camel.activity.convertfiletostring.ConvertFileToStringAdapterFactory;
import com.adapter.bpmn.camel.activity.sendto.SendToCamelAdapterFactory;
import com.adapter.bpmn.camel.exclusivegateway.ExclusiveGatewayCamelAdapterFactory;
import com.adapter.bpmn.camel.infolog.InfoLogCamelAdapterFactory;
import com.adapter.bpmn.camel.startevent.StartFromCamelAdapterFactory;
import com.adapter.bpmn.model.Dictionary;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.activity.ConvertFileToString;
import com.adapter.bpmn.model.flowobject.activity.InfoLog;
import com.adapter.bpmn.model.flowobject.activity.SendTo;
import com.adapter.bpmn.model.flowobject.exclusivegateway.ExclusiveGateway;
import com.adapter.bpmn.model.flowobject.startevent.NamedStartEvent;

import java.util.HashMap;
import java.util.Map;

public class BPMNToCamelDictionary implements Dictionary {

    private final Map<Class<? extends FlowObject>, CamelAdapterFactory> dictionary;

    public BPMNToCamelDictionary() {
        dictionary = new HashMap<>();
        dictionary.put(NamedStartEvent.class, new StartFromCamelAdapterFactory());
        dictionary.put(SendTo.class, new SendToCamelAdapterFactory());
        dictionary.put(ExclusiveGateway.class, new ExclusiveGatewayCamelAdapterFactory());
        dictionary.put(InfoLog.class, new InfoLogCamelAdapterFactory());
        dictionary.put(ConvertFileToString.class, new ConvertFileToStringAdapterFactory());
    }

    public CamelAdapter getAdapter(FlowObject flowObject) {
        return dictionary.get(flowObject.getClass()).getAdapter(flowObject);
    }

    protected Map<Class<? extends FlowObject>, CamelAdapterFactory> getDictionary() {
        return dictionary;
    }
}
