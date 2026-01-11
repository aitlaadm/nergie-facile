package com.energiefacile.config;

import com.energiefacile.models.*;
import com.energiefacile.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private ConsumptionRecordRepository recordRepository;
    
    @Autowired
    private DailyConsumptionRepository dailyRepository;
    
    @Autowired
    private MonthlyConsumptionRepository monthlyRepository;
    
    @Autowired
    private AlertRepository alertRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (recordRepository.count() == 0) {
            initializeData();
        }
    }
    
    private void initializeData() {
        // Initialize Monthly Data
        initializeMonthlyData();
        
        // Initialize Daily Data
        initializeDailyData();
        
        // Initialize Consumption Records
        initializeConsumptionRecords();
        
        // Initialize Alerts
        initializeAlerts();
    }
    
    private void initializeMonthlyData() {
        int[][] monthlyValues = {
            {320, 4500, 180},
            {280, 4200, 165},
            {250, 3800, 140},
            {220, 3500, 110},
            {200, 4000, 85},
            {240, 4800, 60},
            {280, 5200, 45},
            {290, 5500, 40},
            {260, 4600, 70},
            {300, 4100, 120},
            {340, 4000, 155},
            {380, 4300, 190}
        };
        
        String[] months = {"Jan", "Fév", "Mar", "Avr", "Mai", "Juin", "Juil", "Août", "Sep", "Oct", "Nov", "Déc"};
        
        for (int i = 0; i < monthlyValues.length; i++) {
            MonthlyConsumption monthly = new MonthlyConsumption();
            monthly.setYear(2024);
            monthly.setMonth(i + 1);
            monthly.setElectricityValue((double) monthlyValues[i][0]);
            monthly.setWaterValue((double) monthlyValues[i][1]);
            monthly.setGasValue((double) monthlyValues[i][2]);
            monthly.setTotalValue((double) (monthlyValues[i][0] + monthlyValues[i][1] / 100 + monthlyValues[i][2]));
            
            if (i == 0) {
                monthly.setTrend(null);
            } else {
                double prevTotal = monthlyValues[i-1][0] + monthlyValues[i-1][1] / 100 + monthlyValues[i-1][2];
                double currentTotal = monthly.getTotalValue();
                double trend = ((currentTotal - prevTotal) / prevTotal) * 100;
                monthly.setTrend(trend);
            }
            
            monthlyRepository.save(monthly);
        }
    }
    
    private void initializeDailyData() {
        String[] days = {"Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"};
        int[][] dailyValues = {
            {45, 650, 28},
            {52, 720, 32},
            {48, 680, 30},
            {55, 750, 35},
            {60, 800, 38},
            {70, 900, 42},
            {65, 850, 40}
        };
        
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        for (int i = 0; i < dailyValues.length; i++) {
            DailyConsumption daily = new DailyConsumption();
            daily.setDate(startDate.plusDays(i));
            daily.setElectricityValue((double) dailyValues[i][0]);
            daily.setWaterValue((double) dailyValues[i][1]);
            daily.setGasValue((double) dailyValues[i][2]);
            daily.setTotalValue((double) (dailyValues[i][0] + dailyValues[i][1] / 100 + dailyValues[i][2]));
            
            dailyRepository.save(daily);
        }
    }
    
    private void initializeConsumptionRecords() {
        LocalDateTime now = LocalDateTime.now();
        
        // Create sample records for the last week
        for (int i = 0; i < 7; i++) {
            LocalDateTime recordTime = now.minusDays(i);
            
            // Electricity records
            ConsumptionRecord electricity = new ConsumptionRecord();
            electricity.setType(EnergyType.ELECTRICITY);
            electricity.setValue(45.0 + i * 2);
            electricity.setUnit("kWh");
            electricity.setRecordedAt(recordTime);
            electricity.setCreatedAt(LocalDateTime.now());
            recordRepository.save(electricity);
            
            // Water records
            ConsumptionRecord water = new ConsumptionRecord();
            water.setType(EnergyType.WATER);
            water.setValue(650.0 + i * 50);
            water.setUnit("L");
            water.setRecordedAt(recordTime);
            water.setCreatedAt(LocalDateTime.now());
            recordRepository.save(water);
            
            // Gas records
            ConsumptionRecord gas = new ConsumptionRecord();
            gas.setType(EnergyType.GAS);
            gas.setValue(28.0 + i * 1.5);
            gas.setUnit("kWh");
            gas.setRecordedAt(recordTime);
            gas.setCreatedAt(LocalDateTime.now());
            recordRepository.save(gas);
        }
    }
    
    private void initializeAlerts() {
        Alert alert1 = new Alert();
        alert1.setType(AlertType.DANGER);
        alert1.setTitle("Consommation de gaz élevée");
        alert1.setMessage("Votre consommation de gaz a augmenté de 12.5% ce mois-ci. Vérifiez votre chauffage.");
        alert1.setIsActive(true);
        alertRepository.save(alert1);
        
        Alert alert2 = new Alert();
        alert2.setType(AlertType.SUCCESS);
        alert2.setTitle("Économie d'eau réalisée");
        alert2.setMessage("Félicitations ! Vous avez réduit votre consommation d'eau de 3.8% ce mois.");
        alert2.setIsActive(true);
        alertRepository.save(alert2);
        
        Alert alert3 = new Alert();
        alert3.setType(AlertType.WARNING);
        alert3.setTitle("Pic de consommation détecté");
        alert3.setMessage("Un pic de consommation électrique a été détecté samedi entre 18h et 21h.");
        alert3.setIsActive(true);
        alertRepository.save(alert3);
    }
}
