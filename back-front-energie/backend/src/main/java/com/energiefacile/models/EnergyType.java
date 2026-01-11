package com.energiefacile.models;

public enum EnergyType {
    ELECTRICITY("kWh"),
    WATER("L"),
    GAS("kWh");
    
    private final String defaultUnit;
    
    EnergyType(String defaultUnit) {
        this.defaultUnit = defaultUnit;
    }
    
    public String getDefaultUnit() {
        return defaultUnit;
    }
}
