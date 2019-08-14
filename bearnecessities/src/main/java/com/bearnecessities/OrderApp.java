package com.bearnecessities;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class OrderApp {

    public static void main(String[] args) throws Exception {

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

        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();


    }
}
