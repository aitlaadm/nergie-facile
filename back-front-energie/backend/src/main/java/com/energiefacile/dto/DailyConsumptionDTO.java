package com.energiefacile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyConsumptionDTO {
    private Long id;
    private LocalDate date;
    private Double electricityValue;
    private Double waterValue;
    private Double gasValue;
    private Double totalValue;
}
