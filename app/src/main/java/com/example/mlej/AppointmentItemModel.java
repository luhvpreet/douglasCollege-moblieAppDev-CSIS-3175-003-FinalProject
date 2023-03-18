package com.example.mlej;

public class AppointmentItemModel {

    private int appointmentId;
    private String customerName;
    private String appointDateTime;

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
}
