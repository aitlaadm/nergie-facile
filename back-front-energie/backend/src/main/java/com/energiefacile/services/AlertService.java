package com.energiefacile.services;

import com.energiefacile.dto.AlertDTO;
import com.energiefacile.models.Alert;
import com.energiefacile.models.AlertType;
import com.energiefacile.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertService {
    
    @Autowired
    private AlertRepository alertRepository;
    
    public AlertDTO createAlert(AlertDTO alertDTO) {
        Alert alert = new Alert();
        alert.setType(alertDTO.getType());
        alert.setTitle(alertDTO.getTitle());
        alert.setMessage(alertDTO.getMessage());
        alert.setIsActive(alertDTO.getIsActive() != null ? alertDTO.getIsActive() : true);
        
        Alert saved = alertRepository.save(alert);
        return toDTO(saved);
    }
    
    public List<AlertDTO> getActiveAlerts() {
        return alertRepository.findByIsActive(true).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<AlertDTO> getAlertsByType(AlertType type) {
        return alertRepository.findByType(type).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public AlertDTO updateAlert(Long id, AlertDTO alertDTO) {
        Alert alert = alertRepository.findById(id).orElse(null);
        if (alert != null) {
            alert.setTitle(alertDTO.getTitle());
            alert.setMessage(alertDTO.getMessage());
            alert.setIsActive(alertDTO.getIsActive());
            Alert saved = alertRepository.save(alert);
            return toDTO(saved);
        }
        return null;
    }
    
    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }
    
    private AlertDTO toDTO(Alert alert) {
        return new AlertDTO(
            alert.getId(),
            alert.getType(),
            alert.getTitle(),
            alert.getMessage(),
            alert.getIsActive()
        );
    }
    public List<AlertDTO> getAllAlerts() {
    return alertRepository.findAll()
        .stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
    }

}
