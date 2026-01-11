package com.energiefacile.repositories;

import com.energiefacile.models.ConsumptionRecord;
import com.energiefacile.models.EnergyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsumptionRecordRepository extends JpaRepository<ConsumptionRecord, Long> {
    
    List<ConsumptionRecord> findByType(EnergyType type);
    
    List<ConsumptionRecord> findByRecordedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT c FROM ConsumptionRecord c WHERE c.type = :type AND c.recordedAt BETWEEN :startDate AND :endDate")
    List<ConsumptionRecord> findByTypeAndDateRange(
        @Param("type") EnergyType type,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    @Query("SELECT SUM(c.value) FROM ConsumptionRecord c WHERE c.type = :type AND c.recordedAt BETWEEN :startDate AND :endDate")
    Double sumByTypeAndDateRange(
        @Param("type") EnergyType type,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
}
