package com.adapter.bpmn.camel;

import com.adapter.bpmn.model.BusinessProcess;

import java.util.List;

public class BPMNApp {
    private List<BusinessProcess> businessProcesses;


    public BPMNApp(List<BusinessProcess> businessProcesses) {
        if (businessProcesses == null || businessProcesses.isEmpty()) {
            throw new IllegalArgumentException("Your app does not have any Business Processes defined!");
        }

        this.businessProcesses = businessProcesses;
    }

    public List<BusinessProcess> getBusinessProcesses() {
        return businessProcesses;
    }


}
