package com.bpmn.transformer.model;

import com.bpmn.transformer.model.flowobject.activity.Activity;
import com.bpmn.transformer.model.flowobject.activity.InfoLog;
import com.bpmn.transformer.model.flowobject.startevent.StartEvent;
import com.bpmn.transformer.model.flowobject.startevent.UriStartEvent;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BusinessProcessTest {

    @Test
    public void testConstructor() throws Exception {
        StartEvent startEvent = new UriStartEvent("uri");
        Activity activity = new InfoLog("name");

        BusinessProcess bp = new BusinessProcess(startEvent, activity);

        Assert.assertEquals(startEvent, bp.getStartEvent());
        Assert.assertEquals(activity, bp.getFlowObjects().get(0));
    }
}