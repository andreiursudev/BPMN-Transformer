package com.adapter.bpmn.model;

import com.adapter.bpmn.model.flowobject.Activity;
import com.adapter.bpmn.model.flowobject.StartEvent;
import org.junit.Test;

import static org.junit.Assert.*;

public class BusinessProcessesTest {

    @Test
    public void testConstructor() throws Exception {
        StartEvent startEvent = () -> null;
        Activity activity = () -> null;

        BusinessProcesses bp = new BusinessProcesses(startEvent, activity);

        assertEquals(startEvent, bp.getStartEvent());
        assertEquals(activity, bp.getFlowObjects().get(0));
    }
}