package com.healthhub.healthhub.dtos;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthLogDto {
    private UUID id;
    private String condition;
    private String symptoms;
    private String familyHistory;
    private UUID userId;
}