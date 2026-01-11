package com.energiefacile.repositories;

import com.energiefacile.models.Alert;
import com.energiefacile.models.AlertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    
    List<Alert> findByType(AlertType type);
    
    List<Alert> findByIsActive(Boolean isActive);
    
    List<Alert> findByTypeAndIsActive(AlertType type, Boolean isActive);
}
