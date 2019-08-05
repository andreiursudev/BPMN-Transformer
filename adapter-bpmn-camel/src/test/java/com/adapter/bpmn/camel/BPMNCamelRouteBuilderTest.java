package com.adapter.bpmn.camel;

import org.junit.Test;

import java.util.ArrayList;


public class BPMNCamelRouteBuilderTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBusinessProcessShouldNotBeNull() throws Exception {
        new BPMNCamelApp(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBusinessProcessShouldNotEmpty() throws Exception {
        new BPMNCamelApp(new ArrayList<>());
    }
}