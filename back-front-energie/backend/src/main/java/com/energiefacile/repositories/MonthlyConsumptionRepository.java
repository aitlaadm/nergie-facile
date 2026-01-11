package com.energiefacile.repositories;

import com.energiefacile.models.MonthlyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonthlyConsumptionRepository extends JpaRepository<MonthlyConsumption, Long> {
    
    Optional<MonthlyConsumption> findByYearAndMonth(Integer year, Integer month);
    
    @Query("SELECT m FROM MonthlyConsumption m WHERE m.year = :year ORDER BY m.month")
    List<MonthlyConsumption> findByYear(Integer year);
    
    @Query("SELECT m FROM MonthlyConsumption m ORDER BY m.year DESC, m.month DESC")
    List<MonthlyConsumption> findAllOrderByMonthDesc();
}
