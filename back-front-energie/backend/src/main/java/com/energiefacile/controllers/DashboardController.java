package com.energiefacile.controllers;

import com.energiefacile.dto.CurrentConsumptionDTO;
import com.energiefacile.dto.MonthlyConsumptionDTO;
import com.energiefacile.dto.DailyConsumptionDTO;
import com.energiefacile.dto.AlertDTO;
import com.energiefacile.services.ConsumptionRecordService;
import com.energiefacile.services.DailyConsumptionService;
import com.energiefacile.services.MonthlyConsumptionService;
import com.energiefacile.services.AlertService;
import com.energiefacile.models.EnergyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {
    
    @Autowired
    private ConsumptionRecordService recordService;
    
    @Autowired
    private DailyConsumptionService dailyService;
    
    @Autowired
    private MonthlyConsumptionService monthlyService;
    
    @Autowired
    private AlertService alertService;
    
    @GetMapping("/current-consumption")
    public ResponseEntity<CurrentConsumptionDTO> getCurrentConsumption() {
        // Get current month
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfMonth = now;
        
        // Calculate totals and trends
        Double electricityTotal = recordService.getTotalConsumption(EnergyType.ELECTRICITY, startOfMonth, endOfMonth);
        Double waterTotal = recordService.getTotalConsumption(EnergyType.WATER, startOfMonth, endOfMonth);
        Double gasTotal = recordService.getTotalConsumption(EnergyType.GAS, startOfMonth, endOfMonth);
        Double totalValue = electricityTotal + waterTotal + gasTotal;
        
        CurrentConsumptionDTO consumption = new CurrentConsumptionDTO();
        consumption.setElectricity(new CurrentConsumptionDTO.EnergyValueDTO(electricityTotal, "kWh", 5.2));
        consumption.setWater(new CurrentConsumptionDTO.EnergyValueDTO(waterTotal, "L", -3.8));
        consumption.setGas(new CurrentConsumptionDTO.EnergyValueDTO(gasTotal, "kWh", 12.5));
        consumption.setTotal(new CurrentConsumptionDTO.EnergyValueDTO(totalValue, "kWh eq.", 2.1));
        
        return ResponseEntity.ok(consumption);
    }
    
    @GetMapping("/monthly-data")
    public ResponseEntity<List<MonthlyConsumptionDTO>> getMonthlyData() {
        List<MonthlyConsumptionDTO> data = monthlyService.getAllMonthly();
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/weekly-data")
    public ResponseEntity<List<DailyConsumptionDTO>> getWeeklyData() {
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.minusDays(now.getDayOfWeek().getValue() - 1);
        List<DailyConsumptionDTO> data = dailyService.getDailyByDateRange(startOfWeek, now);
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/daily-data")
    public ResponseEntity<List<DailyConsumptionDTO>> getDailyData() {
        List<DailyConsumptionDTO> data = dailyService.getAll();
        return ResponseEntity.ok(data);
    }
    
    @GetMapping("/alerts")
    public ResponseEntity<List<AlertDTO>> getAlerts() {
        List<AlertDTO> alerts = alertService.getActiveAlerts();
        return ResponseEntity.ok(alerts);
    }
}
