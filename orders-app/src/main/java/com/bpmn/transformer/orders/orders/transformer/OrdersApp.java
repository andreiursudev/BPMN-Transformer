package com.bpmn.transformer.orders.orders.transformer;

import com.bpmn.transformer.model.BusinessProcess;
import com.bpmn.transformer.model.connectingobject.ConditionalFlow;
import com.bpmn.transformer.model.flowobject.exclusivegateway.ExclusiveGateway;
import com.bpmn.transformer.orders.orders.transformer.flowobject.*;

import java.util.ArrayList;
import java.util.List;

public class OrdersApp {

    public List<BusinessProcess> getBusinessProcesses() {
        List<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(
                new BusinessProcess(
                        new OrderStartEvent("data/orders"),
                        new CheckAvailability(),
                        new ExclusiveGateway(
                                new ConditionalFlow(new IsArticleAvailable()),
                                new ConditionalFlow(new IsArticleNotAvailable(),
                                        new Procurement())),
                        new ShipArticle(),
                        new SendInvoice()
                )
        );
        return businessProcesses;
    }
}
