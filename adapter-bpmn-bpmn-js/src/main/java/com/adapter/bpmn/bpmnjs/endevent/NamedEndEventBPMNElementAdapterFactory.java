package com.adapter.bpmn.bpmnjs.endevent;

import com.adapter.bpmn.bpmnjs.BPMNElementAdapter;
import com.adapter.bpmn.bpmnjs.BPMNElementAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.endevent.NamedEndEvent;

public class NamedEndEventBPMNElementAdapterFactory implements BPMNElementAdapterFactory {
    @Override
    public BPMNElementAdapter getAdapter(FlowObject flowObject) {
        return new NamedEndEventBPMNElementAdapter(((NamedEndEvent)flowObject).getName());
    }
}
