package com.example.incidentmanagement.dto;

import com.example.incidentmanagement.entity.IncidentPriority;
import com.example.incidentmanagement.entity.IncidentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IncidentDTO {
    private Long id;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotNull(message = "Status is required")
    private IncidentStatus status;
    
    @NotNull(message = "Priority is required")
    private IncidentPriority priority;
} 