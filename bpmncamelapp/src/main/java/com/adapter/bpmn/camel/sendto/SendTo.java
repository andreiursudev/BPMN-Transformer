package com.adapter.bpmn.camel.sendto;

import com.adapter.bpmn.camel.CamelAdapter;
import com.adapter.bpmn.model.flowobject.Activity;

public class SendTo implements Activity {

    private String uri;

    public SendTo(String uri) {
        this.uri = uri;
    }

    @Override
    public CamelAdapter getAdapter() {
        return new SendToCamelAdapter(uri);
    }
}
