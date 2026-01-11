package com.energiefacile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyConsumptionDTO {
    private Long id;
    private Integer year;
    private Integer month;
    private String monthName;
    private Double electricityValue;
    private Double waterValue;
    private Double gasValue;
    private Double totalValue;
    private Double trend;
}
