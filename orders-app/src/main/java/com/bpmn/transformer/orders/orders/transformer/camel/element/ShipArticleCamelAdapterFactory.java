package com.bpmn.transformer.orders.orders.transformer.camel.element;

import com.bpmn.transformer.camel.CamelAdapter;
import com.bpmn.transformer.camel.CamelAdapterFactory;
import com.bpmn.transformer.model.flowobject.FlowObject;
import com.bpmn.transformer.orders.orders.transformer.flowobject.ShipArticle;

public class ShipArticleCamelAdapterFactory implements CamelAdapterFactory {
    @Override
    public CamelAdapter getAdapter(FlowObject flowObject) {
        return new ShipArticleCamelAdapter(((ShipArticle)flowObject).getMessage());
    }
}
