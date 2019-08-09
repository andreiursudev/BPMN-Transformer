package com.adapter.bpmn.camel;

import com.adapter.bpmn.model.BusinessProcesses;

import java.util.List;

public class BPMNApp {
    private List<BusinessProcesses> businessProcesses;


    public BPMNApp(List<BusinessProcesses> businessProcesses) {
        if (businessProcesses == null || businessProcesses.isEmpty()) {
            throw new IllegalArgumentException("Your app does not have any Business Processes defined!");
        }

        this.businessProcesses = businessProcesses;
    }

    public List<BusinessProcesses> getBusinessProcesses() {
        return businessProcesses;
    }


}
