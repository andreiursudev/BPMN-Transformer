package com.bpmn.transformer.diagram.endevent;

import com.bpmn.transformer.diagram.BPMNElementAdapter;
import com.bpmn.transformer.diagram.BPMNElementAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.model.flowobject.endevent.NamedEndEvent;

public class NamedEndEventBPMNElementAdapterFactory implements BPMNElementAdapterFactory {
    @Override
    public BPMNElementAdapter getAdapter(FlowObject flowObject) {
        return new NamedEndEventBPMNElementAdapter(((NamedEndEvent)flowObject).getName());
    }
}
