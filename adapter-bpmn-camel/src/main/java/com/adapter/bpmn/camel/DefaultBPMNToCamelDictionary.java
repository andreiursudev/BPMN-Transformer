package com.adapter.bpmn.camel;

import com.adapter.bpmn.camel.activity.convertfiletostring.ConvertFileToString;
import com.adapter.bpmn.camel.activity.convertfiletostring.ConvertFileToStringAdapterFactory;
import com.adapter.bpmn.camel.exclusivegateway.ExclusiveGateway;
import com.adapter.bpmn.camel.exclusivegateway.ExclusiveGatewayCamelAdapterFactory;
import com.adapter.bpmn.camel.infolog.InfoLog;
import com.adapter.bpmn.camel.infolog.InfoLogCamelAdapterFactory;
import com.adapter.bpmn.camel.activity.sendto.SendTo;
import com.adapter.bpmn.camel.activity.sendto.SendToCamelAdapterFactory;
import com.adapter.bpmn.camel.startevent.StartFrom;
import com.adapter.bpmn.camel.startevent.StartFromCamelAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;

import java.util.HashMap;
import java.util.Map;

public enum DefaultBPMNToCamelDictionary {

    INSTANCE;

    private final Map<Class<? extends FlowObject>, CamelAdapterFactory> dictionary;

    DefaultBPMNToCamelDictionary() {
        dictionary = new HashMap<>();
        dictionary.put(StartFrom.class, new StartFromCamelAdapterFactory());
        dictionary.put(SendTo.class, new SendToCamelAdapterFactory());
        dictionary.put(ExclusiveGateway.class, new ExclusiveGatewayCamelAdapterFactory());
        dictionary.put(InfoLog.class, new InfoLogCamelAdapterFactory());
        dictionary.put(ConvertFileToString.class, new ConvertFileToStringAdapterFactory());
    }

    public Map<Class<? extends FlowObject>, CamelAdapterFactory> getDictionary() {
        return dictionary;
    }
}
