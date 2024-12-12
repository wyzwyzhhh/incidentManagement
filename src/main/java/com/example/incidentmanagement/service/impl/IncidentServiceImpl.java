package com.example.incidentmanagement.service.impl;

import com.example.incidentmanagement.dto.IncidentDTO;
import com.example.incidentmanagement.entity.Incident;
import com.example.incidentmanagement.exception.IncidentNotFoundException;
import com.example.incidentmanagement.repository.IncidentRepository;
import com.example.incidentmanagement.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;

    @Override
    public IncidentDTO createIncident(IncidentDTO incidentDTO) {
        Incident incident = mapToEntity(incidentDTO);
        Incident savedIncident = incidentRepository.save(incident);
        return mapToDTO(savedIncident);
    }

    @Override
    public IncidentDTO updateIncident(Long id, IncidentDTO incidentDTO) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));
        
        updateIncidentFromDTO(incident, incidentDTO);
        Incident updatedIncident = incidentRepository.save(incident);
        return mapToDTO(updatedIncident);
    }

    @Override
    public void deleteIncident(Long id) {
        if (!incidentRepository.existsById(id)) {
            throw new IncidentNotFoundException(id);
        }
        incidentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public IncidentDTO getIncident(Long id) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));
        return mapToDTO(incident);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IncidentDTO> getAllIncidents(Pageable pageable) {
        return incidentRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    private Incident mapToEntity(IncidentDTO dto) {
        Incident incident = new Incident();
        updateIncidentFromDTO(incident, dto);
        return incident;
    }

    private void updateIncidentFromDTO(Incident incident, IncidentDTO dto) {
        incident.setTitle(dto.getTitle());
        incident.setDescription(dto.getDescription());
        incident.setStatus(dto.getStatus());
        incident.setPriority(dto.getPriority());
    }

    private IncidentDTO mapToDTO(Incident incident) {
        IncidentDTO dto = new IncidentDTO();
        dto.setId(incident.getId());
        dto.setTitle(incident.getTitle());
        dto.setDescription(incident.getDescription());
        dto.setStatus(incident.getStatus());
        dto.setPriority(incident.getPriority());
        return dto;
    }
} 