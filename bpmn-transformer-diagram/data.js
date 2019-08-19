var diagram =`<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<definitions xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" id="definitions" targetNamespace="http://camunda.org/examples" xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL">
  <process id="ProjectName">
    <startEvent id="element_0" name="data/orders">
      <outgoing>element_0-element_1</outgoing>
    </startEvent>
    <task id="element_1" name="Check availability">
      <incoming>element_0-element_1</incoming>
      <outgoing>element_1-element_2</outgoing>
    </task>
    <sequenceFlow id="element_0-element_1" name="" sourceRef="element_0" targetRef="element_1"/>
    <exclusiveGateway id="element_2" name="">
      <incoming>element_1-element_2</incoming>
      <outgoing>element_2-element_3</outgoing>
      <outgoing>element_2-element_2_balance</outgoing>
    </exclusiveGateway>
    <sequenceFlow id="element_1-element_2" name="" sourceRef="element_1" targetRef="element_2"/>
    <task id="element_3" name="Procurement">
      <incoming>element_2-element_3</incoming>
      <outgoing>element_3-element_2_balance</outgoing>
    </task>
    <sequenceFlow id="element_2-element_3" name="Is Article Not Available" sourceRef="element_2" targetRef="element_3"/>
    <exclusiveGateway id="element_2_balance" name="">
      <incoming>element_2-element_2_balance</incoming>
      <incoming>element_3-element_2_balance</incoming>
      <outgoing>element_2_balance-element_4</outgoing>
    </exclusiveGateway>
    <sequenceFlow id="element_2-element_2_balance" name="Is Article Available" sourceRef="element_2" targetRef="element_2_balance"/>
    <sequenceFlow id="element_3-element_2_balance" name="" sourceRef="element_3" targetRef="element_2_balance"/>
    <task id="element_4" name="Ship article">
      <incoming>element_2_balance-element_4</incoming>
      <outgoing>element_4-element_5</outgoing>
    </task>
    <sequenceFlow id="element_2_balance-element_4" name="" sourceRef="element_2_balance" targetRef="element_4"/>
    <endEvent id="element_5" name="Send invoice">
      <incoming>element_4-element_5</incoming>
    </endEvent>
    <sequenceFlow id="element_4-element_5" name="" sourceRef="element_4" targetRef="element_5"/>
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
        <dc:Bounds height="50.0" width="50.0" x="415.0" y="20.0"/>
        <bpmndi:BPMNLabel id="element_2_label">
          <dc:Bounds height="0.0" width="0.0" x="440.0" y="0.0"/>
        </bpmndi:BPMNLabel>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="element_1-element_2" id="edge_element_1-element_2">
        <di:waypoint x="350.0" y="45.0"/>
        <di:waypoint x="415.0" y="45.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNShape bpmnElement="element_3" id="element_3_shape">
        <dc:Bounds height="80.0" width="150.0" x="600.0" y="110.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="element_2-element_3" id="edge_element_2-element_3">
        <di:waypoint x="440.0" y="70.0"/>
        <di:waypoint x="440.0" y="145.0"/>
        <di:waypoint x="600.0" y="145.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNShape bpmnElement="element_2_balance" id="element_2_balance_shape">
        <dc:Bounds height="50.0" width="50.0" x="815.0" y="20.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="element_2-element_2_balance" id="edge_element_2-element_2_balance">
        <di:waypoint x="460.0" y="45.0"/>
        <di:waypoint x="815.0" y="45.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="element_3-element_2_balance" id="edge_element_3-element_2_balance">
        <di:waypoint x="750.0" y="145.0"/>
        <di:waypoint x="840.0" y="145.0"/>
        <di:waypoint x="840.0" y="70.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNShape bpmnElement="element_4" id="element_4_shape">
        <dc:Bounds height="80.0" width="150.0" x="1000.0" y="10.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="element_2_balance-element_4" id="edge_element_2_balance-element_4">
        <di:waypoint x="860.0" y="45.0"/>
        <di:waypoint x="1000.0" y="45.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNShape bpmnElement="element_5" id="element_5_shape">
        <dc:Bounds height="50.0" width="50.0" x="1200.0" y="20.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="element_4-element_5" id="edge_element_4-element_5">
        <di:waypoint x="1150.0" y="45.0"/>
        <di:waypoint x="1200.0" y="45.0"/>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</definitions>
`