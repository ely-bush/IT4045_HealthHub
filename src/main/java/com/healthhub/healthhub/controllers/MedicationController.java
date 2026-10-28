package com.healthhub.healthhub.controllers;

import com.healthhub.healthhub.dtos.MedicationDto;
import com.healthhub.healthhub.entities.Medication;
import com.healthhub.healthhub.entities.User;
import com.healthhub.healthhub.mappers.MedicationMapper;
import com.healthhub.healthhub.repositories.MedicationRepository;
import com.healthhub.healthhub.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medications")
public class MedicationController {
    private final MedicationRepository medicationRepository;
    private final UserRepository userRepository;
    private final MedicationMapper medicationMapper;

    public MedicationController(MedicationRepository medicationRepository,
                                UserRepository userRepository,
                                MedicationMapper medicationMapper) {
        this.medicationRepository = medicationRepository;
        this.userRepository = userRepository;
        this.medicationMapper = medicationMapper;
    }


    @GetMapping
    public List<MedicationDto> getAllMedications(
            @RequestParam(name = "userId", required = false) UUID userId) {
        List<Medication> medications = (userId == null)
                ? medicationRepository.findAll()
                : medicationRepository.findByUser_Id(userId);
        return medications.stream().map(medicationMapper::toDto).toList();
    }


    @GetMapping("/{id}")
    public ResponseEntity<MedicationDto> getMedication(@PathVariable UUID id) {
        return medicationRepository.findById(id)
                .map(m -> ResponseEntity.ok(medicationMapper.toDto(m)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<MedicationDto> createMedication(@Valid @RequestBody MedicationDto medicationDto) {
        User user = userRepository.findById(medicationDto.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        Medication medication = medicationMapper.toEntity(medicationDto);
        medication.setUser(user);
        medicationRepository.save(medication);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicationMapper.toDto(medication));
    }


    @PutMapping("/{id}")
    public ResponseEntity<MedicationDto> updateMedication(@PathVariable UUID id,
                                                          @Valid @RequestBody MedicationDto medicationDto) {
        Medication medication = medicationRepository.findById(id).orElse(null);
        if (medication == null) {
            return ResponseEntity.notFound().build();
        }
        User user = userRepository.findById(medicationDto.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        medicationMapper.update(medicationDto, medication);
        medication.setUser(user);
        medicationRepository.save(medication);
        return ResponseEntity.ok(medicationMapper.toDto(medication));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable UUID id) {
        if (!medicationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        medicationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}