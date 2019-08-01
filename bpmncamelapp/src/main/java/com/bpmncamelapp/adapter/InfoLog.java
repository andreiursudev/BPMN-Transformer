package com.bpmncamelapp.adapter;

import com.bpmncamelapp.CamelAdapter;
import ro.rodin.adapter.bpmn.model.flowobject.Activity;

public class InfoLog implements Activity {
    @Override
    public CamelAdapter getAdapter() {
        return new InfoLogAdapter();
    }
}
