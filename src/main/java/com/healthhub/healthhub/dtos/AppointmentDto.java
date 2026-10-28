package com.healthhub.healthhub.dtos;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDto {
    private UUID id;
    private LocalDateTime dateTime;
    private String location;
    private String notes;
    private UUID userId;
}