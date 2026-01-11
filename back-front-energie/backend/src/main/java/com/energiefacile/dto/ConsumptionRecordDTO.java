package com.energiefacile.dto;

import com.energiefacile.models.EnergyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionRecordDTO {
    private Long id;
    private EnergyType type;
    private Double value;
    private String unit;
    private LocalDateTime recordedAt;
    private String notes;
    private LocalDateTime createdAt;
}
