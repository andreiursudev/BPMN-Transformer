package com.bpmncamelapp;

import ro.rodin.adapter.bpmn.model.BusinessProcesses;

import java.util.List;

public class BPMNCamelApp {
    private List<BusinessProcesses> businessProcesses;


    public BPMNCamelApp(List<BusinessProcesses>businessProcesses) {
        if (businessProcesses == null || businessProcesses.isEmpty()) {
            throw new IllegalArgumentException("Your app does not have any Business Processes defined!");
        }

        this.businessProcesses = businessProcesses;
    }

    public List<BusinessProcesses> getBusinessProcesses(){
        return businessProcesses;
    }


}
