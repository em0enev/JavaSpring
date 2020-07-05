package com.botronsoft.internship.models;

public class Ticket {
    private int customerId;
    private String serviceId;
    private int queueCount;

    public Ticket(String serviceId, int customerId) {
        this.customerId = customerId;
        this.serviceId = serviceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getServiceId(){
        return serviceId;
    }

    public int getQueueCount() {
        return queueCount;
    }

    public void setQueueCount(int value){
        this.queueCount = value;
    }

    public void setCustomerId(int value){ this.customerId = value; }
}
