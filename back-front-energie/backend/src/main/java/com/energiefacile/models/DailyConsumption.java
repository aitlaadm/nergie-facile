package com.energiefacile.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "daily_consumption")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyConsumption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private Double electricityValue;
    
    @Column(nullable = false)
    private Double waterValue;
    
    @Column(nullable = false)
    private Double gasValue;
    
    @Column(nullable = false)
    private Double totalValue; // Equivalent in kWh
}
