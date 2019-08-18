package com.bearnecessities.orders;

import com.adapter.bpmn.bpmnjs.BPMNElementAdapter;
import com.adapter.bpmn.bpmnjs.BPMNElementAdapterFactory;
import com.adapter.bpmn.model.flowobject.FlowObject;

public class TimerEventBPMNElementAdapterFactory implements BPMNElementAdapterFactory {
    @Override
    public BPMNElementAdapter getAdapter(FlowObject flowObject) {
        return new TimerEventBPMNElementAdapter(((OrderStartEvent)flowObject).getDirectoryName());
    }
}
