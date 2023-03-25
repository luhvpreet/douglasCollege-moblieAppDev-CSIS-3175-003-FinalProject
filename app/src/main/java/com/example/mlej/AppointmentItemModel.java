package com.example.mlej;

public class AppointmentItemModel {

    private int appointmentId;



    private int customerId;
    private String customerName;
    private String appointDateTime;
    private int dropOffOrPickup;

    public AppointmentItemModel() {}

    public AppointmentItemModel(int appointmentId, String customerName, String appointDateTime) {
        this.appointmentId = appointmentId;
        this.customerName = customerName;
        this.appointDateTime = appointDateTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAppointDateTime() {
        return appointDateTime;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAppointDateTime(String appointDateTime) {
        this.appointDateTime = appointDateTime;
    }

    public int getDropOffOrPickup() {
        return dropOffOrPickup;
    }

    public void setDropOffOrPickup(int dropOffOrPickup) {
        this.dropOffOrPickup = dropOffOrPickup;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
