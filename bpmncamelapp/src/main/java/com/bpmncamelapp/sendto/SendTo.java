package com.bpmncamelapp.sendto;

import com.bpmncamelapp.CamelAdapter;
import ro.rodin.adapter.bpmn.model.flowobject.Activity;

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
