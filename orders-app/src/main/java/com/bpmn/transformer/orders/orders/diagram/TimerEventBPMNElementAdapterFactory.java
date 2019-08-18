package com.bpmn.transformer.orders.orders.diagram;

import com.bpmn.transformer.diagram.BPMNElementAdapter;
import com.bpmn.transformer.diagram.BPMNElementAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.orders.orders.OrderStartEvent;

public class TimerEventBPMNElementAdapterFactory implements BPMNElementAdapterFactory {
    @Override
    public BPMNElementAdapter getAdapter(FlowObject flowObject) {
        return new TimerEventBPMNElementAdapter(((OrderStartEvent)flowObject).getDirectoryName());
    }
}
