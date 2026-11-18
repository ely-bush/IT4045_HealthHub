package com.healthhub.healthhub.services;

import com.healthhub.healthhub.dtos.MedicationDto;
import com.healthhub.healthhub.entities.Medication;
import com.healthhub.healthhub.entities.User;
import com.healthhub.healthhub.mappers.MedicationMapper;
import com.healthhub.healthhub.repositories.MedicationRepository;
import com.healthhub.healthhub.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class MedicationService {
    private final MedicationRepository medicationRepository;
    private final UserRepository userRepository;
    private final MedicationMapper medicationMapper;

    @Transactional
    public List<MedicationDto> findAll() {
        return medicationRepository.findAll().stream()
                .map(medicationMapper::toDto)
                .toList();
    }

    @Transactional
    public MedicationDto findById(UUID id) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Medication not found"));
        return medicationMapper.toDto(medication);
    }


    @Transactional
    public MedicationDto create(MedicationDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "User not found"));

        Medication medication = medicationMapper.toEntity(dto);
        medication.setUser(user);

        return medicationMapper.toDto(medicationRepository.save(medication));
    }

    @Transactional
    public MedicationDto update(UUID id, MedicationDto dto) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Medication not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "User not found"));

        medicationMapper.update(dto, medication);
        medication.setUser(user);

        return medicationMapper.toDto(medicationRepository.save(medication));
    }

    @Transactional
    public void delete(UUID id) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Medication not found"));
        medicationRepository.delete(medication);
    }
}
