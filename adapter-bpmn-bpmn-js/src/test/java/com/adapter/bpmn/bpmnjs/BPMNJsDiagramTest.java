package com.adapter.bpmn.bpmnjs;

import com.adapter.bpmn.bpmnjs.startevent.StartFromBPMNElementAdapterFactory;
import com.adapter.bpmn.model.BusinessProcesses;
import com.adapter.bpmn.model.flowobject.FlowObject;
import com.adapter.bpmn.model.flowobject.startevent.StartFrom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BPMNJsDiagramTest {

    @Test
    public void testEmptyBusinessProcessesAsXmlDiagram() throws Exception {
        Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary = DefaultBPMNToBPMNElementsDictionary.INSTANCE.getDictionary();

        BPMNJsDiagram diagram = new BPMNJsDiagram(new ArrayList<>(), dictionary);

        String xml = diagram.asXml();

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
        Map<Class<? extends FlowObject>, BPMNElementAdapterFactory> dictionary = DefaultBPMNToBPMNElementsDictionary.INSTANCE.getDictionary();

        ArrayList<BusinessProcesses> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcesses(new StartFrom("My Start Event")));




        BPMNJsDiagram diagram = new BPMNJsDiagram(businessProcesses,dictionary);

        String xml = diagram.asXml();

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

    private void assertEqualsIgnoreLineEndings(String xml, String expected) {
        assertEquals(expected.replaceAll("[\r\n]+", "\n"), xml.replaceAll("[\r\n]+", "\n"));
    }

}