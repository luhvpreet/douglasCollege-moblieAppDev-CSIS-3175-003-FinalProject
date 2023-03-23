package com.example.mlej;

public class ProviderItemModel {
    private int providerId;
    private String providerName;
    private String providerAddress;
    private String providerPhone;
    private String providerEmail;
    private String providerPostalCode;
    // private String providerServices;

    public ProviderItemModel(int providerId, String providerName, String providerAddress, String providerPhone, String providerEmail, String providerPostalCode) {
        this.providerId = providerId;
        this.providerName = providerName;
        this.providerAddress = providerAddress;
        this.providerPhone = providerPhone;
        this.providerEmail = providerEmail;
        this.providerPostalCode = providerPostalCode;
        // this.providerServices = providerServices;
    }

    public int getProviderId() {
        return providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public String getProviderPhone() {
        return providerPhone;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public String getProviderPostalCode() {
        return providerPostalCode;
    }

//    public String getProviderServices() {
//        return providerServices;
//    }
}
