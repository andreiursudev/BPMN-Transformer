package com.bpmn.transformer.orders.orders;

import com.bpmn.transformer.model.BusinessProcess;
import com.bpmn.transformer.model.flowobject.activity.InfoLog;
import com.bpmn.transformer.model.flowobject.activity.SendTo;
import com.bpmn.transformer.model.flowobject.startevent.UriStartEvent;

import java.util.ArrayList;
import java.util.List;

public class OrdersApp {

    public List<BusinessProcess> getBusinessProcesses() {
        List<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(
                new BusinessProcess(
                        new UriStartEvent("file:data/orders?noop=true"),
                        new InfoLog("Order is received"),
                        new SendTo("direct:out")));
        return businessProcesses;
    }
    public List<BusinessProcess> getBusinessProcessesWithDomain() {
        List<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(
                new BusinessProcess(
                        new OrderStartEvent("data/orders"),
                        new InfoLog("Order is received"),
                        new SendTo("mock:out")));
        return businessProcesses;
    }
}
