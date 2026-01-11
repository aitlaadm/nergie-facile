package com.energiefacile.repositories;

import com.energiefacile.models.DailyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyConsumptionRepository extends JpaRepository<DailyConsumption, Long> {
    
    Optional<DailyConsumption> findByDate(LocalDate date);
    
    @Query("SELECT d FROM DailyConsumption d WHERE d.date BETWEEN :startDate AND :endDate ORDER BY d.date")
    List<DailyConsumption> findByDateRange(LocalDate startDate, LocalDate endDate);
    
    List<DailyConsumption> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
