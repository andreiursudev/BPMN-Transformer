package com.adapter.bpmn.camel.infolog;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.model.flowobject.Activity;

public class InfoLog implements Activity {
    @Override
    public CamelAdapter getAdapter() {
        return new InfoLogAdapter();
    }
}
