package com.bpmn.transformer.diagram;

import com.bpmn.transformer.diagram.activity.NamedActivityBPMNElementAdapterFactory;
import com.bpmn.transformer.diagram.endevent.NamedEndEventBPMNElementAdapterFactory;
import com.bpmn.transformer.diagram.exclusivegateway.ExclusiveGatewayBPMNElementAdapterFactory;
import com.bpmn.transformer.diagram.startevent.StartFromBPMNElementAdapterFactory;
import com.bpmn.transformer.model.Dictionary;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.model.flowobject.activity.NamedActivity;
import com.bpmn.transformer.model.flowobject.endevent.NamedEndEvent;
import com.bpmn.transformer.model.flowobject.exclusivegateway.ExclusiveGateway;
import com.bpmn.transformer.model.flowobject.startevent.UriStartEvent;

import java.util.HashMap;
import java.util.Map;

public class BPMNModelToBPMNElementsDictionary implements Dictionary {

   private final Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary;

    public BPMNModelToBPMNElementsDictionary() {
        dictionary = new HashMap<>();
        dictionary.put(UriStartEvent.class, new StartFromBPMNElementAdapterFactory());
        dictionary.put(NamedActivity.class, new NamedActivityBPMNElementAdapterFactory());
        dictionary.put(ExclusiveGateway.class, new ExclusiveGatewayBPMNElementAdapterFactory());
        dictionary.put(NamedEndEvent.class, new NamedEndEventBPMNElementAdapterFactory());
    }

    public BPMNElementAdapter getAdapter(FlowObject flowObject) {
        return dictionary.get(flowObject.getClass()).getAdapter(flowObject);
    }

    public Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> getDictionary() {
        return dictionary;
    }
}
