package com.adapter.bpmn.camel.activity.convertfiletostring;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.model.Adapter;
import com.adapter.bpmn.model.flowobject.Activity;

public class ConvertFileToString implements Activity {
    @Override
    public CamelAdapter getAdapter() {
        return new ConvertFileToStringAdapter();
    }
}
