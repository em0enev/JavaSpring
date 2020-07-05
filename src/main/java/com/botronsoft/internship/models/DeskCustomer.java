package com.botronsoft.internship.models;

public class DeskCustomer {
    private int deskNumber;
    private int customerId;

    public DeskCustomer(int deskNumber, int customerId) {
        this.deskNumber = deskNumber;
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getDeskNumber() {
        return deskNumber;
    }
}
