package com.adapter.bpmn.model;

import com.adapter.bpmn.model.flowobject.activity.Activity;
import com.adapter.bpmn.model.flowobject.activity.InfoLog;
import com.adapter.bpmn.model.flowobject.startevent.NamedStartEvent;
import com.adapter.bpmn.model.flowobject.startevent.StartEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BusinessProcessTest {

    @Test
    public void testConstructor() throws Exception {
        StartEvent startEvent = new NamedStartEvent("name");
        Activity activity = new InfoLog("name");

        BusinessProcess bp = new BusinessProcess(startEvent, activity);

        assertEquals(startEvent, bp.getStartEvent());
        assertEquals(activity, bp.getFlowObjects().get(0));
    }
}