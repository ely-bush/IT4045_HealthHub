package com.healthhub.healthhub.controllers;

import com.healthhub.healthhub.dtos.MedicationDto;
import com.healthhub.healthhub.services.MedicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/medications")
public class MedicationController {
    private final MedicationService medicationService;

    @GetMapping
    public List<MedicationDto> getAllMedications() {
        return medicationService.findAll();
    }

    @GetMapping("/{id}")
    public MedicationDto getMedication(@PathVariable UUID id) {
        return medicationService.findById(id);
    }

    @PostMapping
    public ResponseEntity<MedicationDto> createMedication(@Valid @RequestBody MedicationDto medicationDto) {
        MedicationDto created = medicationService.create(medicationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationDto> updateMedication(@PathVariable UUID id,
                                                          @Valid @RequestBody MedicationDto medicationDto) {
        MedicationDto updated = medicationService.update(id, medicationDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedication(@PathVariable UUID id) {
        medicationService.delete(id);
    }
}