package com.energiefacile.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "consumption_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnergyType type; // ELECTRICITY, WATER, GAS
    
    @Column(nullable = false)
    private Double value;
    
    @Column(nullable = false)
    private String unit; // kWh, L, etc.
    
    @Column(nullable = false)
    private LocalDateTime recordedAt;
    
    @Column
    private String notes;
    
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
