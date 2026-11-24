package com.healthhub.healthhub.controllers;

import com.healthhub.healthhub.dtos.MedicationDto;
import com.healthhub.healthhub.services.MedicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medications")
@RequiredArgsConstructor
public class MedicationController {
    private final MedicationService medicationService;

    @GetMapping
    public List<MedicationDto> getAllMedications(
            @RequestParam(name = "userId", required = false) UUID userId) {
        return medicationService.getAllMedications(userId);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MedicationDto> getMedication(@PathVariable UUID id) {
        return medicationService.getMedicationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<MedicationDto> createMedication(@Valid @RequestBody MedicationDto medicationDto) {
        return medicationService.createMedication(medicationDto)
                .map(medication -> ResponseEntity.status(HttpStatus.CREATED).body(medication))
                .orElse(ResponseEntity.badRequest().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<MedicationDto> updateMedication(@PathVariable UUID id,
                                                          @Valid @RequestBody MedicationDto medicationDto) {
        MedicationService.UpdateResult result = medicationService.updateMedication(id, medicationDto);
        return switch (result.status()) {
            case UPDATED -> ResponseEntity.ok(result.medication());
            case MEDICATION_NOT_FOUND -> ResponseEntity.notFound().build();
            case USER_NOT_FOUND -> ResponseEntity.badRequest().build();
        };
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable UUID id) {
        return medicationService.deleteMedication(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}