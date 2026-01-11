package com.energiefacile.services;

import com.energiefacile.dto.DailyConsumptionDTO;
import com.energiefacile.models.DailyConsumption;
import com.energiefacile.repositories.DailyConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DailyConsumptionService {
    
    @Autowired
    private DailyConsumptionRepository dailyRepository;
    
    public DailyConsumptionDTO createDaily(DailyConsumptionDTO dailyDTO) {
        DailyConsumption daily = new DailyConsumption();
        daily.setDate(dailyDTO.getDate());
        daily.setElectricityValue(dailyDTO.getElectricityValue());
        daily.setWaterValue(dailyDTO.getWaterValue());
        daily.setGasValue(dailyDTO.getGasValue());
        daily.setTotalValue(dailyDTO.getTotalValue());
        
        DailyConsumption saved = dailyRepository.save(daily);
        return toDTO(saved);
    }
    
    public List<DailyConsumptionDTO> getDailyByDateRange(LocalDate startDate, LocalDate endDate) {
        return dailyRepository.findByDateRange(startDate, endDate).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public DailyConsumptionDTO getDailyByDate(LocalDate date) {
        return dailyRepository.findByDate(date)
                .map(this::toDTO)
                .orElse(null);
    }
    
    public List<DailyConsumptionDTO> getAll() {
        return dailyRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    private DailyConsumptionDTO toDTO(DailyConsumption daily) {
        return new DailyConsumptionDTO(
            daily.getId(),
            daily.getDate(),
            daily.getElectricityValue(),
            daily.getWaterValue(),
            daily.getGasValue(),
            daily.getTotalValue()
        );
    }
}
