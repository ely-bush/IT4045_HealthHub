package com.healthhub.healthhub.dtos;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationDto {
    private UUID id;
    private String name;
    private String dosage;
    private String refillSchedule;
    private String instructions;
    private UUID userId;
}