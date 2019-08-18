package com.bpmn.transformer.orders.orders;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class OrdersCamelApp {

    public static void main(String[] args) throws Exception {
        String AVAILABILITY = "availability";


        CamelContext camelContext = new DefaultCamelContext();


        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from("file:data/orders?noop=true")
                        .setProperty(AVAILABILITY, new CheckAvailability())
                        .choice()
                            .when(exchangeProperty(AVAILABILITY).isEqualTo(Boolean.TRUE))
                                .log("Article ${body} is in stock!")
                            .endChoice()
                            .when(exchangeProperty(AVAILABILITY).isEqualTo(Boolean.FALSE))
                                .process(new ProcureArticle())
                            .endChoice()
                        .end()
                        .process(new ShipArticle())
                        .process(new FinnancialStatement());
                        //.to("direct:paymentReceived");
            }
        });

        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();
    }


}

class CheckAvailability implements Expression {

    @Override
    public <T> T evaluate(Exchange exchange, Class<T> type) {
        return type.cast(Boolean.TRUE);
    }
}

class ProcureArticle implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

    }
}

class ShipArticle implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

    }
}

class FinnancialStatement implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

    }
}