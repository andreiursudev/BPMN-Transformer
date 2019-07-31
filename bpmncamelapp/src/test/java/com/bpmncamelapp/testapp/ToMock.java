package com.bpmncamelapp.testapp;

import com.bpmncamelapp.CamelAdapter;
import com.bpmncamelapp.flowobject.Activity;

public class ToMock implements Activity {

    private String uri;

    public ToMock(String uri) {
        this.uri = uri;
    }

    @Override
    public CamelAdapter getCamelAdapter() {
        return new ToMockAdapter(uri);
    }
}
