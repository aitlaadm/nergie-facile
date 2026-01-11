package com.energiefacile.controllers;

import com.energiefacile.dto.ConsumptionRecordDTO;
import com.energiefacile.models.EnergyType;
import com.energiefacile.services.ConsumptionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/consumption-records")
@CrossOrigin(origins = "http://localhost:5173")
public class ConsumptionRecordController {
    
    @Autowired
    private ConsumptionRecordService recordService;
    
    @PostMapping
    public ResponseEntity<ConsumptionRecordDTO> createRecord(@RequestBody ConsumptionRecordDTO recordDTO) {
        ConsumptionRecordDTO created = recordService.createRecord(recordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<ConsumptionRecordDTO>> getRecordsByType(@PathVariable EnergyType type) {
        List<ConsumptionRecordDTO> records = recordService.getRecordsByType(type);
        return ResponseEntity.ok(records);
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<ConsumptionRecordDTO>> getRecordsByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<ConsumptionRecordDTO> records = recordService.getRecordsByDateRange(startDate, endDate);
        return ResponseEntity.ok(records);
    }
    
    @GetMapping("/type-date-range")
    public ResponseEntity<List<ConsumptionRecordDTO>> getRecordsByTypeAndDateRange(
            @RequestParam EnergyType type,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<ConsumptionRecordDTO> records = recordService.getRecordsByTypeAndDateRange(type, startDate, endDate);
        return ResponseEntity.ok(records);
    }
    
    @GetMapping("/total")
    public ResponseEntity<Double> getTotalConsumption(
            @RequestParam EnergyType type,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        Double total = recordService.getTotalConsumption(type, startDate, endDate);
        return ResponseEntity.ok(total);
    }
    @GetMapping
    public ResponseEntity<List<ConsumptionRecordDTO>> getAllRecords() {
        return ResponseEntity.ok(recordService.getAllRecords());
    }
}
