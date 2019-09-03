package com.bpmn.transformer.orders.orders.transformer.diagram.element;

import com.bpmn.transformer.diagram.BPMNElementAdapter;
import com.bpmn.transformer.diagram.startevent.StartFromBPMNElementAdapter;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.orders.orders.transformer.flowobject.OrderStartEvent;

public class OrderStartEventBPMNElementAdapterFactory implements com.bpmn.transformer.diagram.BPMNElementAdapterFactory {
    @Override
    public BPMNElementAdapter getAdapter(FlowObject flowObject) {
        return new StartFromBPMNElementAdapter(((OrderStartEvent)flowObject).getDirectoryName());
    }
}
