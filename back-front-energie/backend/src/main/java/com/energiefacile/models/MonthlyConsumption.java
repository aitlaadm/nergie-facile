package com.energiefacile.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.YearMonth;

@Entity
@Table(name = "monthly_consumption")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyConsumption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Integer year;
    
    @Column(nullable = false)
    private Integer month;
    
    @Column(nullable = false)
    private Double electricityValue;
    
    @Column(nullable = false)
    private Double waterValue;
    
    @Column(nullable = false)
    private Double gasValue;
    
    @Column(nullable = false)
    private Double totalValue;
    
    @Column
    private Double trend; // Percentage change from previous month
}
