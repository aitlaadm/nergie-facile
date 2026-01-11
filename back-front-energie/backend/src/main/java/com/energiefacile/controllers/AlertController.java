package com.energiefacile.controllers;

import com.energiefacile.dto.AlertDTO;
import com.energiefacile.models.AlertType;
import com.energiefacile.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
@CrossOrigin(origins = "http://localhost:5173")
public class AlertController {
    
    @Autowired
    private AlertService alertService;
    
    @PostMapping
    public ResponseEntity<AlertDTO> createAlert(@RequestBody AlertDTO alertDTO) {
        AlertDTO created = alertService.createAlert(alertDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    // @GetMapping("/active")
    // public ResponseEntity<List<AlertDTO>> getActiveAlerts() {
    //     List<AlertDTO> alerts = alertService.getActiveAlerts();
    //     return ResponseEntity.ok(alerts);
    // }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<AlertDTO>> getAlertsByType(@PathVariable AlertType type) {
        List<AlertDTO> alerts = alertService.getAlertsByType(type);
        return ResponseEntity.ok(alerts);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AlertDTO> updateAlert(
            @PathVariable Long id,
            @RequestBody AlertDTO alertDTO) {
        AlertDTO updated = alertService.updateAlert(id, alertDTO);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<AlertDTO>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    @GetMapping("/active")
    public ResponseEntity<List<AlertDTO>> getActiveAlerts() {
        return ResponseEntity.ok(alertService.getActiveAlerts());
    }
}
