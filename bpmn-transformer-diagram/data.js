var diagram =`<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<definitions xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" id="definitions" targetNamespace="http://camunda.org/examples" xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL">
  <process id="ProjectName">
    <startEvent id="element_0" name="data/orders">
      <outgoing>element_0-element_1</outgoing>
      <timerEventDefinition id="element_0_timer"/>
      <eventDefinitionRef>element_0_timer</eventDefinitionRef>
    </startEvent>
    <task id="element_1" name="Order is received">
      <incoming>element_0-element_1</incoming>
      <outgoing>element_1-element_2</outgoing>
    </task>
    <sequenceFlow id="element_0-element_1" name="" sourceRef="element_0" targetRef="element_1"/>
    <task id="element_2" name="mock:out">
      <incoming>element_1-element_2</incoming>
    </task>
    <sequenceFlow id="element_1-element_2" name="" sourceRef="element_1" targetRef="element_2"/>
  </process>
  <bpmndi:BPMNDiagram id="BPMNDiagram">
    <bpmndi:BPMNPlane bpmnElement="ProjectName" id="BPMNPlane">
      <bpmndi:BPMNShape bpmnElement="element_0" id="element_0_shape">
        <dc:Bounds height="50.0" width="50.0" x="15.0" y="20.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="element_1" id="element_1_shape">
        <dc:Bounds height="80.0" width="150.0" x="200.0" y="10.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="element_0-element_1" id="edge_element_0-element_1">
        <di:waypoint x="65.0" y="45.0"/>
        <di:waypoint x="200.0" y="45.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNShape bpmnElement="element_2" id="element_2_shape">
        <dc:Bounds height="80.0" width="150.0" x="400.0" y="10.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="element_1-element_2" id="edge_element_1-element_2">
        <di:waypoint x="350.0" y="45.0"/>
        <di:waypoint x="400.0" y="45.0"/>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</definitions>
`