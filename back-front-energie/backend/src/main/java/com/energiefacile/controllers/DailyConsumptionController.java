package com.energiefacile.controllers;

import com.energiefacile.dto.DailyConsumptionDTO;
import com.energiefacile.services.DailyConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/daily-consumption")
@CrossOrigin(origins = "http://localhost:5173")
public class DailyConsumptionController {
    
    @Autowired
    private DailyConsumptionService dailyService;
    
    @PostMapping
    public ResponseEntity<DailyConsumptionDTO> createDaily(@RequestBody DailyConsumptionDTO dailyDTO) {
        DailyConsumptionDTO created = dailyService.createDaily(dailyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/{date}")
    public ResponseEntity<DailyConsumptionDTO> getDailyByDate(@PathVariable LocalDate date) {
        DailyConsumptionDTO daily = dailyService.getDailyByDate(date);
        if (daily != null) {
            return ResponseEntity.ok(daily);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/range")
    public ResponseEntity<List<DailyConsumptionDTO>> getDailyByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<DailyConsumptionDTO> dailies = dailyService.getDailyByDateRange(startDate, endDate);
        return ResponseEntity.ok(dailies);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<DailyConsumptionDTO>> getAll() {
        List<DailyConsumptionDTO> dailies = dailyService.getAll();
        return ResponseEntity.ok(dailies);
    }
}
