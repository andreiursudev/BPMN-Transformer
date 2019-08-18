package com.adapter.bpmn.camel;

import org.junit.Test;

import java.util.ArrayList;


public class BPMNAppTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBusinessProcessShouldNotBeNull() throws Exception {
        new BPMNApp(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBusinessProcessShouldNotEmpty() throws Exception {
        new BPMNApp(new ArrayList<>());
    }
}