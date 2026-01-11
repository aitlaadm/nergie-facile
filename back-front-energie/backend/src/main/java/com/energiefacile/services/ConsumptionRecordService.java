package com.energiefacile.services;

import com.energiefacile.dto.ConsumptionRecordDTO;
import com.energiefacile.models.ConsumptionRecord;
import com.energiefacile.models.EnergyType;
import com.energiefacile.repositories.ConsumptionRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumptionRecordService {
    
    @Autowired
    private ConsumptionRecordRepository recordRepository;
    
    public ConsumptionRecordDTO createRecord(ConsumptionRecordDTO recordDTO) {
        ConsumptionRecord record = new ConsumptionRecord();
        record.setType(recordDTO.getType());
        record.setValue(recordDTO.getValue());
        record.setUnit(recordDTO.getUnit() != null ? recordDTO.getUnit() : recordDTO.getType().getDefaultUnit());
        record.setRecordedAt(recordDTO.getRecordedAt() != null ? recordDTO.getRecordedAt() : LocalDateTime.now());
        record.setNotes(recordDTO.getNotes());
        record.setCreatedAt(LocalDateTime.now());
        
        ConsumptionRecord saved = recordRepository.save(record);
        return toDTO(saved);
    }
    
    public List<ConsumptionRecordDTO> getRecordsByType(EnergyType type) {
        return recordRepository.findByType(type).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<ConsumptionRecordDTO> getRecordsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return recordRepository.findByRecordedAtBetween(startDate, endDate).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<ConsumptionRecordDTO> getRecordsByTypeAndDateRange(EnergyType type, LocalDateTime startDate, LocalDateTime endDate) {
        return recordRepository.findByTypeAndDateRange(type, startDate, endDate).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public Double getTotalConsumption(EnergyType type, LocalDateTime startDate, LocalDateTime endDate) {
        Double total = recordRepository.sumByTypeAndDateRange(type, startDate, endDate);
        return total != null ? total : 0.0;
    }
    
    private ConsumptionRecordDTO toDTO(ConsumptionRecord record) {
        return new ConsumptionRecordDTO(
            record.getId(),
            record.getType(),
            record.getValue(),
            record.getUnit(),
            record.getRecordedAt(),
            record.getNotes(),      // ← AJOUTER
            record.getCreatedAt()   // ← AJOUTER
        );
    }
    public List<ConsumptionRecordDTO> getAllRecords() {
        return recordRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}
