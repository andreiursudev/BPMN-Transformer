package com.bpmn.transformer.diagram.activity;

import com.bpmn.transformer.diagram.BPMNElementAdapter;
import com.bpmn.transformer.diagram.BPMNElementAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.model.flowobject.activity.NamedActivity;

public class NamedActivityBPMNElementAdapterFactory implements BPMNElementAdapterFactory {
    @Override
    public BPMNElementAdapter getAdapter(FlowObject flowObject) {
        return new NamedActivityBPMNElementAdapter(((NamedActivity)flowObject).getName());
    }
}
