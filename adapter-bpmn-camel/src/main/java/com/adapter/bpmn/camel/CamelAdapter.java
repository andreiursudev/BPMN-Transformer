package com.adapter.bpmn.camel;

import org.apache.camel.model.ProcessorDefinition;

public interface CamelAdapter {

    void adapt(ProcessorDefinition processorDefinition, BPMNToCamelDictionary dictionary);

}
