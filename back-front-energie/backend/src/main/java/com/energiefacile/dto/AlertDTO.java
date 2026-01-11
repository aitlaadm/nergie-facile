package com.energiefacile.dto;

import com.energiefacile.models.AlertType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertDTO {
    private Long id;
    private AlertType type;
    private String title;
    private String message;
    private Boolean isActive;
}
