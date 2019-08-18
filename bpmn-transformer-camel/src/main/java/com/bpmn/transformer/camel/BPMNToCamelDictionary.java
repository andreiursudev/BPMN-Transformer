package com.bpmn.transformer.camel;

import com.bpmn.transformer.camel.activity.convertfiletostring.ConvertFileToStringAdapterFactory;
import com.bpmn.transformer.camel.activity.sendto.SendToCamelAdapterFactory;
import com.bpmn.transformer.camel.exclusivegateway.ExclusiveGatewayCamelAdapterFactory;
import com.bpmn.transformer.camel.infolog.InfoLogCamelAdapterFactory;
import com.bpmn.transformer.camel.startevent.StartFromCamelAdapterFactory;
import com.bpmn.transformer.model.Dictionary;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.model.flowobject.activity.ConvertFileToString;
import com.bpmn.transformer.model.flowobject.activity.InfoLog;
import com.bpmn.transformer.model.flowobject.activity.SendTo;
import com.bpmn.transformer.model.flowobject.exclusivegateway.ExclusiveGateway;
import com.bpmn.transformer.model.flowobject.startevent.UriStartEvent;

import java.util.HashMap;
import java.util.Map;

public class BPMNToCamelDictionary implements Dictionary {

    private final Map<Class<? extends FlowObject>, CamelAdapterFactory> dictionary;

    public BPMNToCamelDictionary() {
        dictionary = new HashMap<>();
        dictionary.put(UriStartEvent.class, new StartFromCamelAdapterFactory());
        dictionary.put(SendTo.class, new SendToCamelAdapterFactory());
        dictionary.put(ExclusiveGateway.class, new ExclusiveGatewayCamelAdapterFactory());
        dictionary.put(InfoLog.class, new InfoLogCamelAdapterFactory());
        dictionary.put(ConvertFileToString.class, new ConvertFileToStringAdapterFactory());
    }

    public CamelAdapter getAdapter(FlowObject flowObject) {
        CamelAdapterFactory camelAdapterFactory = dictionary.get(flowObject.getClass());
        if(camelAdapterFactory == null){
            throw new AdapterFactoryNotFoundException(flowObject);
        }
        return camelAdapterFactory.getAdapter(flowObject);
    }

    protected Map<Class<? extends FlowObject>, CamelAdapterFactory> getDictionary() {
        return dictionary;
    }
}
