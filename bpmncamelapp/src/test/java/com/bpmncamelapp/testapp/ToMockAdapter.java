package com.bpmncamelapp.testapp;

import com.bpmncamelapp.CamelAdapter;
import org.apache.camel.model.ProcessorDefinition;

public class ToMockAdapter implements CamelAdapter {

    private String uri;

    public ToMockAdapter(String uri) {
        this.uri = uri;
    }

    @Override
    public void adapt(ProcessorDefinition processorDefinition) {
        processorDefinition.to(uri);
    }

}
