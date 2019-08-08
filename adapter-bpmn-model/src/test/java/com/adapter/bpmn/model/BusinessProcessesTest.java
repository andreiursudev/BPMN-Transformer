package com.adapter.bpmn.model;

import com.adapter.bpmn.model.flowobject.activity.Activity;
import com.adapter.bpmn.model.flowobject.activity.InfoLog;
import com.adapter.bpmn.model.flowobject.startevent.StartEvent;
import com.adapter.bpmn.model.flowobject.startevent.StartFrom;
import org.junit.Test;

import static org.junit.Assert.*;

public class BusinessProcessesTest {

    @Test
    public void testConstructor() throws Exception {
        StartEvent startEvent = new StartFrom("uri");
        Activity activity = new InfoLog("");

        BusinessProcesses bp = new BusinessProcesses(startEvent, activity);

        assertEquals(startEvent, bp.getStartEvent());
        assertEquals(activity, bp.getFlowObjects().get(0));
    }
}