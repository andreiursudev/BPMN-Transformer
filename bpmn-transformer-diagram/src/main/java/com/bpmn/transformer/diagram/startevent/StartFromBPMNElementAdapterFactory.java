package com.bpmn.transformer.diagram.startevent;

import com.bpmn.transformer.diagram.BPMNElementAdapter;
import com.bpmn.transformer.diagram.BPMNElementAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.model.flowobject.startevent.UriStartEvent;

public class StartFromBPMNElementAdapterFactory implements BPMNElementAdapterFactory {
    @Override
    public BPMNElementAdapter getAdapter(FlowObject flowObject) {
        return new StartFromBPMNElementAdapter(((UriStartEvent)flowObject).getUri());
    }
}
