package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.bpmnjs.testapp.IsHelloWorld;
import com.adapter.bpmn.bpmnjs.testapp.MyActivity;
import com.adapter.bpmn.bpmnjs.testapp.MyStartEvent;
import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.connectingobject.ConditionalFlow;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BPMNJsDiagramExclusiveGatewayTest {

    @Test
    public void testBusinessProcessesWithStartEventAndActivity() throws Exception {
        ArrayList<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new MyStartEvent(),  new ExclusiveGateway(new ConditionalFlow(new IsHelloWorld(), new MyActivity()))));
        BPMNJsDiagram diagram = new BPMNJsDiagram(businessProcesses);

        String xml = diagram.asXml();

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<definitions xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" id=\"definitions\" targetNamespace=\"http://camunda.org/examples\" xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\">\n" +
                "  <process id=\"ProjectName\">\n" +
                "    <startEvent id=\"element_0\" name=\"My Start Event\">\n" +
                "      <outgoing>element_0-element_1</outgoing>\n" +
                "    </startEvent>\n" +
                "    <exclusiveGateway id=\"element_1\" name=\"ok?\">\n" +
                "      <incoming>element_0-element_1</incoming>\n" +
                "      <outgoing>element_1-element_2</outgoing>\n" +
                "    </exclusiveGateway>\n" +
                "    <sequenceFlow id=\"element_0-element_1\" name=\"\" sourceRef=\"element_0\" targetRef=\"element_1\"/>\n" +
                "    <task id=\"element_2\" name=\"This is an activity\">\n" +
                "      <incoming>element_1-element_2</incoming>\n" +
                "    </task>\n" +
                "    <sequenceFlow id=\"element_1-element_2\" name=\"Is Hello World\" sourceRef=\"element_1\" targetRef=\"element_2\"/>\n" +
                "  </process>\n" +
                "  <bpmndi:BPMNDiagram id=\"BPMNDiagram\">\n" +
                "    <bpmndi:BPMNPlane bpmnElement=\"ProjectName\" id=\"BPMNPlane\">\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_0\" id=\"element_0_0\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"15.0\" y=\"20.0\"/>\n" +
                "        <bpmndi:BPMNLabel id=\"element_0_1\">\n" +
                "          <dc:Bounds height=\"0.0\" width=\"0.0\" x=\"40.0\" y=\"75.0\"/>\n" +
                "        </bpmndi:BPMNLabel>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_1\" id=\"element_1_0\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"215.0\" y=\"20.0\"/>\n" +
                "        <bpmndi:BPMNLabel id=\"element_1_1\">\n" +
                "          <dc:Bounds height=\"0.0\" width=\"0.0\" x=\"240.0\" y=\"0.0\"/>\n" +
                "        </bpmndi:BPMNLabel>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_0-element_1\" id=\"edge_element_0-element_1\">\n" +
                "        <di:waypoint x=\"65.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"215.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_2\" id=\"element_2_0\">\n" +
                "        <dc:Bounds height=\"80.0\" width=\"150.0\" x=\"400.0\" y=\"10.0\"/>\n" +
                "        <bpmndi:BPMNLabel id=\"element_2_1\">\n" +
                "          <dc:Bounds height=\"0.0\" width=\"0.0\" x=\"0.0\" y=\"0.0\"/>\n" +
                "        </bpmndi:BPMNLabel>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_1-element_2\" id=\"edge_element_1-element_2\">\n" +
                "        <di:waypoint x=\"260.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"400.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "    </bpmndi:BPMNPlane>\n" +
                "  </bpmndi:BPMNDiagram>\n" +
                "</definitions>" +
                "\n";

        assertEqualsIgnoreLineEndings(xml, expected);
    }

    private void assertEqualsIgnoreLineEndings(String xml, String expected) {
        assertEquals(expected.replaceAll("[\r\n]+", "\n"), xml.replaceAll("[\r\n]+", "\n"));
    }
}
