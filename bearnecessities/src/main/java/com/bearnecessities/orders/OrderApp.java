package com.bearnecessities.orders;

import com.adapter.bpmn.bpmnjs.BPMNJsDiagram;
import com.adapter.bpmn.camel.BPMNCamelApp;
import com.adapter.bpmn.model.BusinessProcess;
import com.adapter.bpmn.model.flowobject.activity.InfoLog;
import com.adapter.bpmn.model.flowobject.activity.SendTo;
import com.adapter.bpmn.model.flowobject.startevent.UriStartEvent;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.ArrayList;

public class OrderApp {

    public static void main(String[] args) throws Exception {
        ArrayList<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcess(new UriStartEvent("file:data/orders?noop=true"), new InfoLog("Order is received"), new SendTo("direct:out")));
        BPMNCamelApp app = new BPMNCamelApp(businessProcesses);

        businessProcesses.add(new BusinessProcess(new OrderStartEvent("data/orders"), new InfoLog("Order is received"), new SendTo("mock:out")));
        BPMNJsDiagram diagram = new BPMNJsDiagram();
        String xmlDiagram = diagram.asXml(app.getBusinessProcesses(), new OrdersBPMNElementDictionary());
        System.out.println(xmlDiagram);


        CamelContext camelContext = new DefaultCamelContext();


        Expression checkAvailability = new Expression() {
            @Override
            public <T> T evaluate(Exchange exchange, Class<T> type) {
                return type.cast(!exchange.getIn().getBody(String.class).isEmpty());
            }
        };
        Processor procureArticle = new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {

            }
        };

        Processor shipArticle = new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {

            }
        };

                Processor finnancialStatement = new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                    }
                };

                String AVAILABILITY = "availability";
        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:data/orders?noop=true")
                    .setProperty(AVAILABILITY, checkAvailability)
                    .choice()
                        .when(exchangeProperty(AVAILABILITY).isEqualTo(Boolean.TRUE))
                        .log("Article ${body} is in stock!")
                    .endChoice()
                        .when(exchangeProperty(AVAILABILITY).isEqualTo(Boolean.FALSE))
                        .process(procureArticle)
                    .endChoice()
                    .end()
                    .process(shipArticle)
                    .process(finnancialStatement)
                    .to("direct:paymentReceived");
            }
        });

       /* camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();*/


    }
}
