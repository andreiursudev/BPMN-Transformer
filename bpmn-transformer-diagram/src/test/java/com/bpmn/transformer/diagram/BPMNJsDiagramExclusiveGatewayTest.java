package com.bpmn.transformer.diagram;

import com.bpmn.transformer.diagram.testapp.IsHelloJohn;
import com.bpmn.transformer.diagram.testapp.IsHelloWorld;
import com.bpmn.transformer.model.BusinessProcess;
import com.bpmn.transformer.model.connectingobject.ConditionalFlow;
import com.bpmn.transformer.model.flowobject.activity.NamedActivity;
import com.bpmn.transformer.model.flowobject.endevent.NamedEndEvent;
import com.bpmn.transformer.model.flowobject.exclusivegateway.ExclusiveGateway;
import com.bpmn.transformer.model.flowobject.startevent.UriStartEvent;
import org.junit.Test;

import java.util.ArrayList;

import static com.bpmn.transformer.diagram.TestHelper.assertEqualsIgnoreLineEndings;
import static org.junit.Assert.assertEquals;

public class BPMNJsDiagramExclusiveGatewayTest {

    private final BPMNModelToBPMNElementsDictionary dictionary = new BPMNModelToBPMNElementsDictionary();

    @Test
    public void testBusinessProcessesWithExclusiveGatewayAndActivity() throws Exception {
        ArrayList<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcess(new UriStartEvent("My Start Event"), new ExclusiveGateway(new ConditionalFlow(new IsHelloWorld(), new NamedActivity("This is an activity")))));
        BPMNJsDiagram diagram = new BPMNJsDiagram();

        String xml = diagram.asXml(businessProcesses, dictionary);

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<definitions xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" id=\"definitions\" targetNamespace=\"http://camunda.org/examples\" xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\">\n" +
                "  <process id=\"ProjectName\">\n" +
                "    <startEvent id=\"element_0\" name=\"My Start Event\">\n" +
                "      <outgoing>element_0-element_1</outgoing>\n" +
                "    </startEvent>\n" +
                "    <exclusiveGateway id=\"element_1\" name=\"\">\n" +
                "      <incoming>element_0-element_1</incoming>\n" +
                "      <outgoing>element_1-element_2</outgoing>\n" +
                "    </exclusiveGateway>\n" +
                "    <sequenceFlow id=\"element_0-element_1\" name=\"\" sourceRef=\"element_0\" targetRef=\"element_1\"/>\n" +
                "    <task id=\"element_2\" name=\"This is an activity\">\n" +
                "      <incoming>element_1-element_2</incoming>\n" +
                "      <outgoing>element_2-element_1_balance</outgoing>\n" +
                "    </task>\n" +
                "    <sequenceFlow id=\"element_1-element_2\" name=\"Is Hello World\" sourceRef=\"element_1\" targetRef=\"element_2\"/>\n" +
                "    <exclusiveGateway id=\"element_1_balance\" name=\"\">\n" +
                "      <incoming>element_2-element_1_balance</incoming>\n" +
                "    </exclusiveGateway>\n" +
                "    <sequenceFlow id=\"element_2-element_1_balance\" name=\"\" sourceRef=\"element_2\" targetRef=\"element_1_balance\"/>\n" +
                "  </process>\n" +
                "  <bpmndi:BPMNDiagram id=\"BPMNDiagram\">\n" +
                "    <bpmndi:BPMNPlane bpmnElement=\"ProjectName\" id=\"BPMNPlane\">\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_0\" id=\"element_0_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"15.0\" y=\"20.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_1\" id=\"element_1_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"215.0\" y=\"20.0\"/>\n" +
                "        <bpmndi:BPMNLabel id=\"element_1_label\">\n" +
                "          <dc:Bounds height=\"0.0\" width=\"0.0\" x=\"240.0\" y=\"0.0\"/>\n" +
                "        </bpmndi:BPMNLabel>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_0-element_1\" id=\"edge_element_0-element_1\">\n" +
                "        <di:waypoint x=\"65.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"215.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_2\" id=\"element_2_shape\">\n" +
                "        <dc:Bounds height=\"80.0\" width=\"150.0\" x=\"400.0\" y=\"10.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_1-element_2\" id=\"edge_element_1-element_2\">\n" +
                "        <di:waypoint x=\"260.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"400.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_1_balance\" id=\"element_1_balance_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"615.0\" y=\"20.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_2-element_1_balance\" id=\"edge_element_2-element_1_balance\">\n" +
                "        <di:waypoint x=\"550.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"615.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "    </bpmndi:BPMNPlane>\n" +
                "  </bpmndi:BPMNDiagram>\n" +
                "</definitions>\n" +
                "\n";

        assertEqualsIgnoreLineEndings(xml, expected);
    }

    @Test
    public void testBusinessProcessesWithExclusiveGatewayAndTwoActivity() throws Exception {
        ArrayList<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcess(new UriStartEvent("My Start Event"), new ExclusiveGateway(new ConditionalFlow(new IsHelloWorld(), new NamedActivity("This is an activity"), new NamedActivity("This is an activity")))));
        BPMNJsDiagram diagram = new BPMNJsDiagram();

        String xml = diagram.asXml(businessProcesses, dictionary);

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<definitions xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" id=\"definitions\" targetNamespace=\"http://camunda.org/examples\" xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\">\n" +
                "  <process id=\"ProjectName\">\n" +
                "    <startEvent id=\"element_0\" name=\"My Start Event\">\n" +
                "      <outgoing>element_0-element_1</outgoing>\n" +
                "    </startEvent>\n" +
                "    <exclusiveGateway id=\"element_1\" name=\"\">\n" +
                "      <incoming>element_0-element_1</incoming>\n" +
                "      <outgoing>element_1-element_2</outgoing>\n" +
                "    </exclusiveGateway>\n" +
                "    <sequenceFlow id=\"element_0-element_1\" name=\"\" sourceRef=\"element_0\" targetRef=\"element_1\"/>\n" +
                "    <task id=\"element_2\" name=\"This is an activity\">\n" +
                "      <incoming>element_1-element_2</incoming>\n" +
                "      <outgoing>element_2-element_3</outgoing>\n" +
                "    </task>\n" +
                "    <sequenceFlow id=\"element_1-element_2\" name=\"Is Hello World\" sourceRef=\"element_1\" targetRef=\"element_2\"/>\n" +
                "    <task id=\"element_3\" name=\"This is an activity\">\n" +
                "      <incoming>element_2-element_3</incoming>\n" +
                "      <outgoing>element_3-element_1_balance</outgoing>\n" +
                "    </task>\n" +
                "    <sequenceFlow id=\"element_2-element_3\" name=\"\" sourceRef=\"element_2\" targetRef=\"element_3\"/>\n" +
                "    <exclusiveGateway id=\"element_1_balance\" name=\"\">\n" +
                "      <incoming>element_3-element_1_balance</incoming>\n" +
                "    </exclusiveGateway>\n" +
                "    <sequenceFlow id=\"element_3-element_1_balance\" name=\"\" sourceRef=\"element_3\" targetRef=\"element_1_balance\"/>\n" +
                "  </process>\n" +
                "  <bpmndi:BPMNDiagram id=\"BPMNDiagram\">\n" +
                "    <bpmndi:BPMNPlane bpmnElement=\"ProjectName\" id=\"BPMNPlane\">\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_0\" id=\"element_0_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"15.0\" y=\"20.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_1\" id=\"element_1_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"215.0\" y=\"20.0\"/>\n" +
                "        <bpmndi:BPMNLabel id=\"element_1_label\">\n" +
                "          <dc:Bounds height=\"0.0\" width=\"0.0\" x=\"240.0\" y=\"0.0\"/>\n" +
                "        </bpmndi:BPMNLabel>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_0-element_1\" id=\"edge_element_0-element_1\">\n" +
                "        <di:waypoint x=\"65.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"215.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_2\" id=\"element_2_shape\">\n" +
                "        <dc:Bounds height=\"80.0\" width=\"150.0\" x=\"400.0\" y=\"10.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_1-element_2\" id=\"edge_element_1-element_2\">\n" +
                "        <di:waypoint x=\"260.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"400.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_3\" id=\"element_3_shape\">\n" +
                "        <dc:Bounds height=\"80.0\" width=\"150.0\" x=\"600.0\" y=\"10.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_2-element_3\" id=\"edge_element_2-element_3\">\n" +
                "        <di:waypoint x=\"550.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"600.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_1_balance\" id=\"element_1_balance_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"815.0\" y=\"20.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_3-element_1_balance\" id=\"edge_element_3-element_1_balance\">\n" +
                "        <di:waypoint x=\"750.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"815.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "    </bpmndi:BPMNPlane>\n" +
                "  </bpmndi:BPMNDiagram>\n" +
                "</definitions>\n" +
                "\n";

        assertEqualsIgnoreLineEndings(xml, expected);
    }

    @Test
    public void testBusinessProcessesWithExclusiveGatewayAndMore() throws Exception {
        ArrayList<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcess(new UriStartEvent("My Start Event"), new ExclusiveGateway(new ConditionalFlow(new IsHelloWorld(), new NamedActivity("This is an activity"))), new NamedActivity("This is an activity")));
        BPMNJsDiagram diagram = new BPMNJsDiagram();

        String xml = diagram.asXml(businessProcesses, dictionary);

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<definitions xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" id=\"definitions\" targetNamespace=\"http://camunda.org/examples\" xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\">\n" +
                "  <process id=\"ProjectName\">\n" +
                "    <startEvent id=\"element_0\" name=\"My Start Event\">\n" +
                "      <outgoing>element_0-element_1</outgoing>\n" +
                "    </startEvent>\n" +
                "    <exclusiveGateway id=\"element_1\" name=\"\">\n" +
                "      <incoming>element_0-element_1</incoming>\n" +
                "      <outgoing>element_1-element_2</outgoing>\n" +
                "    </exclusiveGateway>\n" +
                "    <sequenceFlow id=\"element_0-element_1\" name=\"\" sourceRef=\"element_0\" targetRef=\"element_1\"/>\n" +
                "    <task id=\"element_2\" name=\"This is an activity\">\n" +
                "      <incoming>element_1-element_2</incoming>\n" +
                "      <outgoing>element_2-element_1_balance</outgoing>\n" +
                "    </task>\n" +
                "    <sequenceFlow id=\"element_1-element_2\" name=\"Is Hello World\" sourceRef=\"element_1\" targetRef=\"element_2\"/>\n" +
                "    <exclusiveGateway id=\"element_1_balance\" name=\"\">\n" +
                "      <incoming>element_2-element_1_balance</incoming>\n" +
                "      <outgoing>element_1_balance-element_3</outgoing>\n" +
                "    </exclusiveGateway>\n" +
                "    <sequenceFlow id=\"element_2-element_1_balance\" name=\"\" sourceRef=\"element_2\" targetRef=\"element_1_balance\"/>\n" +
                "    <task id=\"element_3\" name=\"This is an activity\">\n" +
                "      <incoming>element_1_balance-element_3</incoming>\n" +
                "    </task>\n" +
                "    <sequenceFlow id=\"element_1_balance-element_3\" name=\"\" sourceRef=\"element_1_balance\" targetRef=\"element_3\"/>\n" +
                "  </process>\n" +
                "  <bpmndi:BPMNDiagram id=\"BPMNDiagram\">\n" +
                "    <bpmndi:BPMNPlane bpmnElement=\"ProjectName\" id=\"BPMNPlane\">\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_0\" id=\"element_0_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"15.0\" y=\"20.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_1\" id=\"element_1_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"215.0\" y=\"20.0\"/>\n" +
                "        <bpmndi:BPMNLabel id=\"element_1_label\">\n" +
                "          <dc:Bounds height=\"0.0\" width=\"0.0\" x=\"240.0\" y=\"0.0\"/>\n" +
                "        </bpmndi:BPMNLabel>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_0-element_1\" id=\"edge_element_0-element_1\">\n" +
                "        <di:waypoint x=\"65.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"215.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_2\" id=\"element_2_shape\">\n" +
                "        <dc:Bounds height=\"80.0\" width=\"150.0\" x=\"400.0\" y=\"10.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_1-element_2\" id=\"edge_element_1-element_2\">\n" +
                "        <di:waypoint x=\"260.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"400.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_1_balance\" id=\"element_1_balance_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"615.0\" y=\"20.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_2-element_1_balance\" id=\"edge_element_2-element_1_balance\">\n" +
                "        <di:waypoint x=\"550.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"615.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_3\" id=\"element_3_shape\">\n" +
                "        <dc:Bounds height=\"80.0\" width=\"150.0\" x=\"800.0\" y=\"10.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_1_balance-element_3\" id=\"edge_element_1_balance-element_3\">\n" +
                "        <di:waypoint x=\"660.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"800.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "    </bpmndi:BPMNPlane>\n" +
                "  </bpmndi:BPMNDiagram>\n" +
                "</definitions>\n" +
                "\n";

        assertEqualsIgnoreLineEndings(xml, expected);
    }

    @Test
    public void testBusinessProcessesWithExclusiveGatewayAndStop() throws Exception {
        ArrayList<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcess(new UriStartEvent("My Start Event"), new ExclusiveGateway(new ConditionalFlow(new IsHelloWorld(), new NamedActivity("This is an activity"), new NamedEndEvent("My stop")))));
        BPMNJsDiagram diagram = new BPMNJsDiagram();

        String xml = diagram.asXml(businessProcesses, dictionary);

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<definitions xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" id=\"definitions\" targetNamespace=\"http://camunda.org/examples\" xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\">\n" +
                "  <process id=\"ProjectName\">\n" +
                "    <startEvent id=\"element_0\" name=\"My Start Event\">\n" +
                "      <outgoing>element_0-element_1</outgoing>\n" +
                "    </startEvent>\n" +
                "    <exclusiveGateway id=\"element_1\" name=\"\">\n" +
                "      <incoming>element_0-element_1</incoming>\n" +
                "      <outgoing>element_1-element_2</outgoing>\n" +
                "    </exclusiveGateway>\n" +
                "    <sequenceFlow id=\"element_0-element_1\" name=\"\" sourceRef=\"element_0\" targetRef=\"element_1\"/>\n" +
                "    <task id=\"element_2\" name=\"This is an activity\">\n" +
                "      <incoming>element_1-element_2</incoming>\n" +
                "      <outgoing>element_2-element_3</outgoing>\n" +
                "    </task>\n" +
                "    <sequenceFlow id=\"element_1-element_2\" name=\"Is Hello World\" sourceRef=\"element_1\" targetRef=\"element_2\"/>\n" +
                "    <endEvent id=\"element_3\" name=\"My stop\">\n" +
                "      <incoming>element_2-element_3</incoming>\n" +
                "    </endEvent>\n" +
                "    <sequenceFlow id=\"element_2-element_3\" name=\"\" sourceRef=\"element_2\" targetRef=\"element_3\"/>\n" +
                "  </process>\n" +
                "  <bpmndi:BPMNDiagram id=\"BPMNDiagram\">\n" +
                "    <bpmndi:BPMNPlane bpmnElement=\"ProjectName\" id=\"BPMNPlane\">\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_0\" id=\"element_0_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"15.0\" y=\"20.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_1\" id=\"element_1_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"215.0\" y=\"20.0\"/>\n" +
                "        <bpmndi:BPMNLabel id=\"element_1_label\">\n" +
                "          <dc:Bounds height=\"0.0\" width=\"0.0\" x=\"240.0\" y=\"0.0\"/>\n" +
                "        </bpmndi:BPMNLabel>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_0-element_1\" id=\"edge_element_0-element_1\">\n" +
                "        <di:waypoint x=\"65.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"215.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_2\" id=\"element_2_shape\">\n" +
                "        <dc:Bounds height=\"80.0\" width=\"150.0\" x=\"400.0\" y=\"10.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_1-element_2\" id=\"edge_element_1-element_2\">\n" +
                "        <di:waypoint x=\"260.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"400.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_3\" id=\"element_3_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"600.0\" y=\"20.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_2-element_3\" id=\"edge_element_2-element_3\">\n" +
                "        <di:waypoint x=\"550.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"600.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "    </bpmndi:BPMNPlane>\n" +
                "  </bpmndi:BPMNDiagram>\n" +
                "</definitions>" +
                "\n";

        assertEqualsIgnoreLineEndings(xml, expected);
    }

    @Test
    public void testBusinessProcessesWithExclusiveGatewayAndTwoConditionalFlows() throws Exception {
        ArrayList<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcess(new UriStartEvent("My Start Event"), new ExclusiveGateway(new ConditionalFlow(new IsHelloWorld(), new NamedActivity("This is an activity")), new ConditionalFlow(new IsHelloJohn(), new NamedActivity("This is an activity")))));
        BPMNJsDiagram diagram = new BPMNJsDiagram();

        String xml = diagram.asXml(businessProcesses, dictionary);

        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<definitions xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\" id=\"definitions\" targetNamespace=\"http://camunda.org/examples\" xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\">\n" +
                "  <process id=\"ProjectName\">\n" +
                "    <startEvent id=\"element_0\" name=\"My Start Event\">\n" +
                "      <outgoing>element_0-element_1</outgoing>\n" +
                "    </startEvent>\n" +
                "    <exclusiveGateway id=\"element_1\" name=\"\">\n" +
                "      <incoming>element_0-element_1</incoming>\n" +
                "      <outgoing>element_1-element_2</outgoing>\n" +
                "      <outgoing>element_1-element_3</outgoing>\n" +
                "    </exclusiveGateway>\n" +
                "    <sequenceFlow id=\"element_0-element_1\" name=\"\" sourceRef=\"element_0\" targetRef=\"element_1\"/>\n" +
                "    <task id=\"element_2\" name=\"This is an activity\">\n" +
                "      <incoming>element_1-element_2</incoming>\n" +
                "      <outgoing>element_2-element_1_balance</outgoing>\n" +
                "    </task>\n" +
                "    <sequenceFlow id=\"element_1-element_2\" name=\"Is Hello World\" sourceRef=\"element_1\" targetRef=\"element_2\"/>\n" +
                "    <task id=\"element_3\" name=\"This is an activity\">\n" +
                "      <incoming>element_1-element_3</incoming>\n" +
                "      <outgoing>element_3-element_1_balance</outgoing>\n" +
                "    </task>\n" +
                "    <sequenceFlow id=\"element_1-element_3\" name=\"Is Hello John\" sourceRef=\"element_1\" targetRef=\"element_3\"/>\n" +
                "    <exclusiveGateway id=\"element_1_balance\" name=\"\">\n" +
                "      <incoming>element_2-element_1_balance</incoming>\n" +
                "      <incoming>element_3-element_1_balance</incoming>\n" +
                "    </exclusiveGateway>\n" +
                "    <sequenceFlow id=\"element_2-element_1_balance\" name=\"\" sourceRef=\"element_2\" targetRef=\"element_1_balance\"/>\n" +
                "    <sequenceFlow id=\"element_3-element_1_balance\" name=\"\" sourceRef=\"element_3\" targetRef=\"element_1_balance\"/>\n" +
                "  </process>\n" +
                "  <bpmndi:BPMNDiagram id=\"BPMNDiagram\">\n" +
                "    <bpmndi:BPMNPlane bpmnElement=\"ProjectName\" id=\"BPMNPlane\">\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_0\" id=\"element_0_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"15.0\" y=\"20.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_1\" id=\"element_1_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"215.0\" y=\"20.0\"/>\n" +
                "        <bpmndi:BPMNLabel id=\"element_1_label\">\n" +
                "          <dc:Bounds height=\"0.0\" width=\"0.0\" x=\"240.0\" y=\"0.0\"/>\n" +
                "        </bpmndi:BPMNLabel>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_0-element_1\" id=\"edge_element_0-element_1\">\n" +
                "        <di:waypoint x=\"65.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"215.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_2\" id=\"element_2_shape\">\n" +
                "        <dc:Bounds height=\"80.0\" width=\"150.0\" x=\"400.0\" y=\"10.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_1-element_2\" id=\"edge_element_1-element_2\">\n" +
                "        <di:waypoint x=\"260.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"400.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_3\" id=\"element_3_shape\">\n" +
                "        <dc:Bounds height=\"80.0\" width=\"150.0\" x=\"400.0\" y=\"110.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_1-element_3\" id=\"edge_element_1-element_3\">\n" +
                "        <di:waypoint x=\"240.0\" y=\"70.0\"/>\n" +
                "        <di:waypoint x=\"240.0\" y=\"145.0\"/>\n" +
                "        <di:waypoint x=\"400.0\" y=\"145.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNShape bpmnElement=\"element_1_balance\" id=\"element_1_balance_shape\">\n" +
                "        <dc:Bounds height=\"50.0\" width=\"50.0\" x=\"615.0\" y=\"20.0\"/>\n" +
                "      </bpmndi:BPMNShape>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_2-element_1_balance\" id=\"edge_element_2-element_1_balance\">\n" +
                "        <di:waypoint x=\"550.0\" y=\"45.0\"/>\n" +
                "        <di:waypoint x=\"615.0\" y=\"45.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "      <bpmndi:BPMNEdge bpmnElement=\"element_3-element_1_balance\" id=\"edge_element_3-element_1_balance\">\n" +
                "        <di:waypoint x=\"550.0\" y=\"145.0\"/>\n" +
                "        <di:waypoint x=\"640.0\" y=\"145.0\"/>\n" +
                "        <di:waypoint x=\"640.0\" y=\"70.0\"/>\n" +
                "      </bpmndi:BPMNEdge>\n" +
                "    </bpmndi:BPMNPlane>\n" +
                "  </bpmndi:BPMNDiagram>\n" +
                "</definitions>\n" +
                "\n";

        assertEqualsIgnoreLineEndings(xml, expected);
    }

}
