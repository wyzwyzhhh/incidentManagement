package com.example.incidentmanagement.controller;

import com.example.incidentmanagement.dto.IncidentDTO;
import com.example.incidentmanagement.service.IncidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
@Tag(name = "Incident Management", description = "APIs for managing incidents")
@CrossOrigin(origins = "*")
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping
    @Operation(summary = "Create a new incident")
    public ResponseEntity<IncidentDTO> createIncident(@Valid @RequestBody IncidentDTO incidentDTO) {
        IncidentDTO created = incidentService.createIncident(incidentDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing incident")
    public ResponseEntity<IncidentDTO> updateIncident(
            @PathVariable Long id,
            @Valid @RequestBody IncidentDTO incidentDTO) {
        IncidentDTO updated = incidentService.updateIncident(id, incidentDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an incident")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an incident by ID")
    public ResponseEntity<IncidentDTO> getIncident(@PathVariable Long id) {
        IncidentDTO incident = incidentService.getIncident(id);
        return ResponseEntity.ok(incident);
    }

    @GetMapping
    @Operation(summary = "Get all incidents with pagination")
    public ResponseEntity<Page<IncidentDTO>> getAllIncidents(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        Page<IncidentDTO> incidents = incidentService.getAllIncidents(pageable);
        return ResponseEntity.ok(incidents);
    }
} 