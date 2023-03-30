package com.example.mlej;

public interface SelectServicesListener {
    void onSelectedPriceChange(double priceEstimates);
    void addServices(int servicesId);
    void removeServices(int servicesId);

}
