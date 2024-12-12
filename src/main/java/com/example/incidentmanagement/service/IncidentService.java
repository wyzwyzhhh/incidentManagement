package com.example.incidentmanagement.service;

import com.example.incidentmanagement.dto.IncidentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncidentService {
    IncidentDTO createIncident(IncidentDTO incidentDTO);
    IncidentDTO updateIncident(Long id, IncidentDTO incidentDTO);
    void deleteIncident(Long id);
    IncidentDTO getIncident(Long id);
    Page<IncidentDTO> getAllIncidents(Pageable pageable);
} 