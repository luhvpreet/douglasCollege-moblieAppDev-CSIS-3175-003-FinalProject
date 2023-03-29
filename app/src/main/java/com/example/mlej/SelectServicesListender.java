package com.example.mlej;

public interface SelectServicesListender {
    void onSelectedPriceChange(double priceEstimates);
    void addServices(int servicesId);
    void removeServices(int servicesId);

}
