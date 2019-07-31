package com.bpmncamelapp;

import org.junit.Test;

import java.util.ArrayList;


public class BPMNCamelAppTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBusinessProcessShouldNotBeNull() throws Exception {
        new BPMNCamelApp(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBusinessProcessShouldNotEmpty() throws Exception {
        new BPMNCamelApp(new ArrayList<>());
    }
}