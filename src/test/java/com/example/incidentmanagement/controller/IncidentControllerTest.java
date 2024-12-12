package com.example.incidentmanagement.controller;

import com.example.incidentmanagement.dto.IncidentDTO;
import com.example.incidentmanagement.entity.IncidentPriority;
import com.example.incidentmanagement.entity.IncidentStatus;
import com.example.incidentmanagement.service.IncidentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IncidentController.class)
class IncidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncidentService incidentService;

    @Autowired
    private ObjectMapper objectMapper;

    private IncidentDTO incidentDTO;

    @BeforeEach
    void setUp() {
        incidentDTO = new IncidentDTO();
        incidentDTO.setId(1L);
        incidentDTO.setTitle("Test Incident");
        incidentDTO.setDescription("Test Description");
        incidentDTO.setStatus(IncidentStatus.OPEN);
        incidentDTO.setPriority(IncidentPriority.HIGH);
    }

    @Test
    void createIncident_Success() throws Exception {
        when(incidentService.createIncident(any(IncidentDTO.class))).thenReturn(incidentDTO);

        mockMvc.perform(post("/api/incidents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incidentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(incidentDTO.getTitle()));
    }

    @Test
    void updateIncident_Success() throws Exception {
        when(incidentService.updateIncident(eq(1L), any(IncidentDTO.class))).thenReturn(incidentDTO);

        mockMvc.perform(put("/api/incidents/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incidentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(incidentDTO.getTitle()));
    }

    @Test
    void deleteIncident_Success() throws Exception {
        doNothing().when(incidentService).deleteIncident(1L);

        mockMvc.perform(delete("/api/incidents/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllIncidents_Success() throws Exception {
        Page<IncidentDTO> page = new PageImpl<>(List.of(incidentDTO));
        when(incidentService.getAllIncidents(any())).thenReturn(page);

        mockMvc.perform(get("/api/incidents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value(incidentDTO.getTitle()));
    }
} 