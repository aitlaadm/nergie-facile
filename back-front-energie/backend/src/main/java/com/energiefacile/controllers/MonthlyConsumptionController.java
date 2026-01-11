package com.energiefacile.controllers;

import com.energiefacile.dto.MonthlyConsumptionDTO;
import com.energiefacile.services.MonthlyConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monthly-consumption")
@CrossOrigin(origins = "http://localhost:5173")
public class MonthlyConsumptionController {
    
    @Autowired
    private MonthlyConsumptionService monthlyService;
    
    @PostMapping
    public ResponseEntity<MonthlyConsumptionDTO> createMonthly(@RequestBody MonthlyConsumptionDTO monthlyDTO) {
        MonthlyConsumptionDTO created = monthlyService.createMonthly(monthlyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @GetMapping("/{year}/{month}")
    public ResponseEntity<MonthlyConsumptionDTO> getMonthly(
            @PathVariable Integer year,
            @PathVariable Integer month) {
        MonthlyConsumptionDTO monthly = monthlyService.getMonthly(year, month);
        if (monthly != null) {
            return ResponseEntity.ok(monthly);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/year/{year}")
    public ResponseEntity<List<MonthlyConsumptionDTO>> getMonthlyByYear(@PathVariable Integer year) {
        List<MonthlyConsumptionDTO> monthlies = monthlyService.getMonthlyByYear(year);
        return ResponseEntity.ok(monthlies);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<MonthlyConsumptionDTO>> getAll() {
        List<MonthlyConsumptionDTO> monthlies = monthlyService.getAllMonthly();
        return ResponseEntity.ok(monthlies);
    }
}
