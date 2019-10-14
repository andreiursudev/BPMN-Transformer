# BPMN-Transformer

## Description
BPMN-Transformer Framework helps you write your business processes using a sintax similar to BPMN standard and then transform this bussiness process to either Apache Camel Routes or BPMN Diagrams.

## Getting Started

To use this project you need to clone the git repository and install it intro your local repository using Maven.
After that you can add the following dependencies to you project:
```
<dependency>
  <groupId>com.bpmn.transformer</groupId>
  <artifactId>bpmn-transformer-model</artifactId>
  <version>1.0</version>
</dependency>
	
<dependency>
  <groupId>com.bpmn.transformer</groupId>
  <artifactId>bpmn-transformer-camel</artifactId>
  <version>1.0</version>
</dependency>

<dependency>
  <groupId>com.bpmn.transformer</groupId>
  <artifactId>bpmn-transformer-diagram</artifactId>
  <version>1.0</version>
</dependency>
```

The module bpmn-transformer-model contains the basic BPMN elements like StartEvent, Activity , Gateway and EndEvent.
The following sample shows how a business pocess look when expressed with the help of bpmn-transformer-model module.
```
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
```

The same business process can be built using the Apache Camel API.

```
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
    }
});
```



After implementing your bussiness processes you can use bpmn-transformer-camel module to transfom them to Camel Routes or 
bpmn-transformer-diagram to transform render them as BPMN diagrams.

The diagram generated for the orders-app look as folows.
![alt text](https://github.com/ursuandrei42/BPMN-Transformer/blob/development/orders-app-diagram.png)
