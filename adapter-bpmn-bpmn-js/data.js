var diagram =`<?xml version="1.0" encoding="UTF-8" standalone="no"?>
              <definitions xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" id="definitions" targetNamespace="http://camunda.org/examples" xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL">
                <process id="ProjectName">
                  <startEvent id="element_0" name="My Start Event">
                    <outgoing>element_0-element_1</outgoing>
                  </startEvent>
                  <exclusiveGateway id="element_1" name="ok?">
                    <incoming>element_0-element_1</incoming>
                    <outgoing>element_1-myNextId</outgoing>
                  </exclusiveGateway>
                  <sequenceFlow id="element_0-element_1" name="" sourceRef="element_0" targetRef="element_1"/>
                  <task id="myNextId" name="This is an activity">
                    <incoming>element_1-myNextId</incoming>
                  </task>
                  <sequenceFlow id="element_1-myNextId" name="Is Hello World" sourceRef="element_1" targetRef="myNextId"/>
                </process>
                <bpmndi:BPMNDiagram id="BPMNDiagram">
                  <bpmndi:BPMNPlane bpmnElement="ProjectName" id="BPMNPlane">
                    <bpmndi:BPMNShape bpmnElement="element_0" id="element_0_0">
                      <dc:Bounds height="50.0" width="50.0" x="15.0" y="20.0"/>
                      <bpmndi:BPMNLabel id="element_0_1">
                        <dc:Bounds height="0.0" width="0.0" x="40.0" y="75.0"/>
                      </bpmndi:BPMNLabel>
                    </bpmndi:BPMNShape>
                    <bpmndi:BPMNShape bpmnElement="element_1" id="element_1_0">
                      <dc:Bounds height="50.0" width="50.0" x="215.0" y="20.0"/>
                      <bpmndi:BPMNLabel id="element_1_1">
                        <dc:Bounds height="0.0" width="0.0" x="240.0" y="0.0"/>
                      </bpmndi:BPMNLabel>
                    </bpmndi:BPMNShape>
                    <bpmndi:BPMNEdge bpmnElement="element_0-element_1" id="edge_element_0-element_1">
                      <di:waypoint x="65.0" y="45.0"/>
                      <di:waypoint x="215.0" y="45.0"/>
                    </bpmndi:BPMNEdge>
                    <bpmndi:BPMNShape bpmnElement="myNextId" id="myNextId_0">
                      <dc:Bounds height="80.0" width="150.0" x="400.0" y="10.0"/>
                      <bpmndi:BPMNLabel id="myNextId_1">
                        <dc:Bounds height="0.0" width="0.0" x="0.0" y="0.0"/>
                      </bpmndi:BPMNLabel>
                    </bpmndi:BPMNShape>
                    <bpmndi:BPMNEdge bpmnElement="element_1-myNextId" id="edge_element_1-myNextId">
                      <di:waypoint x="260.0" y="45.0"/>
                      <di:waypoint x="400.0" y="45.0"/>
                    </bpmndi:BPMNEdge>
                  </bpmndi:BPMNPlane>
                </bpmndi:BPMNDiagram>
              </definitions>

`