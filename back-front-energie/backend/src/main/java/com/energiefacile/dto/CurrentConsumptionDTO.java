package com.energiefacile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentConsumptionDTO {
    private EnergyValueDTO electricity;
    private EnergyValueDTO water;
    private EnergyValueDTO gas;
    private EnergyValueDTO total;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnergyValueDTO {
        private Double value;
        private String unit;
        private Double trend;
    }
}
