package com.healthhub.healthhub.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationDto {
    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be 100 characters or less")
    private String name;

    @NotBlank(message = "Dosage is required")
    @Size(max = 50, message = "Dosage must be 50 characters or less")
    private String dosage;

    @Size(max = 100, message = "Refill schedule must be 100 characters or less")
    private String refillSchedule;

    private String instructions;

    @NotNull(message = "userId is required")
    private UUID userId;
}