package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.model.BusinessProcess;
import com.adapter.bpmn.model.flowobject.startevent.NamedStartEvent;
import org.junit.Test;

import java.util.ArrayList;

import static com.adapter.bpmn.bpmnjs.TestHelper.assertEqualsIgnoreLineEndings;
import static org.junit.Assert.assertEquals;

public class BPMNJsDiagramTest {

    private final BPMNModelToBPMNElementsDictionary dictionary = new BPMNModelToBPMNElementsDictionary();

    @Test
    public void testEmptyBusinessProcessesAsXmlDiagram() throws Exception {
        BPMNJsDiagram diagram = new BPMNJsDiagram();

        String xml = diagram.asXml(new ArrayList<>(), dictionary);

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<definitions xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" id=\"definitions\" targetNamespace=\"http://camunda.org/examples\" xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\">\n" +
                "  <process id=\"ProjectName\"/>\n" +
                "  <bpmndi:BPMNDiagram id=\"BPMNDiagram\">\n" +
                "    <bpmndi:BPMNPlane bpmnElement=\"ProjectName\" id=\"BPMNPlane\"/>\n" +
                "  </bpmndi:BPMNDiagram>\n" +
                "</definitions>\n";

        assertEqualsIgnoreLineEndings(xml, expected);
    }

    @Test
    public void testBusinessProcessesWithStartEvent() throws Exception {
        ArrayList<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcess(new NamedStartEvent("My Start Event")));

        BPMNJsDiagram diagram = new BPMNJsDiagram();

        String xml = diagram.asXml(businessProcesses, dictionary);

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<definitions xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" id=\"definitions\" targetNamespace=\"http://camunda.org/examples\" xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\">\n" +
                "  <process id=\"ProjectName\">\n" +
                "    <startEvent id=\"element_0\" name=\"My Start Event\"/>\n" +
                "  </process>\n" +
                "  <bpmndi:BPMNDiagram id=\"BPMNDiagram\">\n" +
                "    <bpmndi:BPMNPlane bpmnElement=\"ProjectName\" id=\"BPMNPlane\">\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_0\" id=\"element_0_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"15.0\" y=\"20.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "    </bpmndi:BPMNPlane>\n" +
                "  </bpmndi:BPMNDiagram>\n" +
                "</definitions>\n";

        assertEqualsIgnoreLineEndings(xml, expected);
    }



}