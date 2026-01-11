package com.energiefacile.services;

import com.energiefacile.dto.MonthlyConsumptionDTO;
import com.energiefacile.models.MonthlyConsumption;
import com.energiefacile.repositories.MonthlyConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonthlyConsumptionService {
    
    @Autowired
    private MonthlyConsumptionRepository monthlyRepository;
    
    public MonthlyConsumptionDTO createMonthly(MonthlyConsumptionDTO monthlyDTO) {
        MonthlyConsumption monthly = new MonthlyConsumption();
        monthly.setYear(monthlyDTO.getYear());
        monthly.setMonth(monthlyDTO.getMonth());
        monthly.setElectricityValue(monthlyDTO.getElectricityValue());
        monthly.setWaterValue(monthlyDTO.getWaterValue());
        monthly.setGasValue(monthlyDTO.getGasValue());
        monthly.setTotalValue(monthlyDTO.getTotalValue());
        monthly.setTrend(monthlyDTO.getTrend());
        
        MonthlyConsumption saved = monthlyRepository.save(monthly);
        return mapToDTO(saved);
    }
    
    public MonthlyConsumptionDTO getMonthly(Integer year, Integer month) {
        return monthlyRepository.findByYearAndMonth(year, month)
                .map(this::mapToDTO)
                .orElse(null);
    }
    
    public List<MonthlyConsumptionDTO> getMonthlyByYear(Integer year) {
        return monthlyRepository.findByYear(year).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    public List<MonthlyConsumptionDTO> getAllMonthly() {
        return monthlyRepository.findAllOrderByMonthDesc().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    private MonthlyConsumptionDTO mapToDTO(MonthlyConsumption monthly) {
        return new MonthlyConsumptionDTO(
            monthly.getId(),
            monthly.getYear(),
            monthly.getMonth(),
            Month.of(monthly.getMonth()).toString(),
            monthly.getElectricityValue(),
            monthly.getWaterValue(),
            monthly.getGasValue(),
            monthly.getTotalValue(),
            monthly.getTrend()
        );
    }
}
