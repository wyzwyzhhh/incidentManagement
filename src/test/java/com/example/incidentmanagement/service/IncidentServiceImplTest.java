package com.example.incidentmanagement.service;

import com.example.incidentmanagement.dto.IncidentDTO;
import com.example.incidentmanagement.entity.Incident;
import com.example.incidentmanagement.entity.IncidentPriority;
import com.example.incidentmanagement.entity.IncidentStatus;
import com.example.incidentmanagement.exception.IncidentNotFoundException;
import com.example.incidentmanagement.repository.IncidentRepository;
import com.example.incidentmanagement.service.impl.IncidentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncidentServiceImplTest {

    @Mock
    private IncidentRepository incidentRepository;

    @InjectMocks
    private IncidentServiceImpl incidentService;

    private IncidentDTO incidentDTO;
    private Incident incident;

    @BeforeEach
    void setUp() {
        incidentDTO = new IncidentDTO();
        incidentDTO.setTitle("Test Incident");
        incidentDTO.setDescription("Test Description");
        incidentDTO.setStatus(IncidentStatus.OPEN);
        incidentDTO.setPriority(IncidentPriority.HIGH);

        incident = new Incident();
        incident.setId(1L);
        incident.setTitle("Test Incident");
        incident.setDescription("Test Description");
        incident.setStatus(IncidentStatus.OPEN);
        incident.setPriority(IncidentPriority.HIGH);
    }

    @Test
    void createIncident_Success() {
        when(incidentRepository.save(any(Incident.class))).thenReturn(incident);

        IncidentDTO result = incidentService.createIncident(incidentDTO);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(incidentDTO.getTitle());
        verify(incidentRepository).save(any(Incident.class));
    }

    @Test
    void updateIncident_Success() {
        when(incidentRepository.findById(1L)).thenReturn(Optional.of(incident));
        when(incidentRepository.save(any(Incident.class))).thenReturn(incident);

        IncidentDTO result = incidentService.updateIncident(1L, incidentDTO);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(incidentDTO.getTitle());
        verify(incidentRepository).save(any(Incident.class));
    }

    @Test
    void updateIncident_NotFound() {
        when(incidentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IncidentNotFoundException.class, () -> 
            incidentService.updateIncident(1L, incidentDTO)
        );
    }

    @Test
    void deleteIncident_Success() {
        when(incidentRepository.existsById(1L)).thenReturn(true);
        doNothing().when(incidentRepository).deleteById(1L);

        incidentService.deleteIncident(1L);

        verify(incidentRepository).deleteById(1L);
    }

    @Test
    void deleteIncident_NotFound() {
        when(incidentRepository.existsById(1L)).thenReturn(false);

        assertThrows(IncidentNotFoundException.class, () -> 
            incidentService.deleteIncident(1L)
        );
    }

    @Test
    void getAllIncidents_Success() {
        Page<Incident> page = new PageImpl<>(List.of(incident));
        when(incidentRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<IncidentDTO> result = incidentService.getAllIncidents(Pageable.unpaged());

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        verify(incidentRepository).findAll(any(Pageable.class));
    }
} 