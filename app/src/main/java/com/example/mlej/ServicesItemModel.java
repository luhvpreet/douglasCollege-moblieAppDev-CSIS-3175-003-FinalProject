package com.example.mlej;

public class ServicesItemModel {

    private int servicesId;
    private String servicesName;
    private double price;

    public ServicesItemModel(){
    }

    public ServicesItemModel(int servicesId, String servicesName, double price) {
        this.servicesId = servicesId;
        this.servicesName = servicesName;
        this.price = price;
    }

    public int getServicesId() {
        return servicesId;
    }

    public void setServicesId(int servicesId) {
        this.servicesId = servicesId;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
