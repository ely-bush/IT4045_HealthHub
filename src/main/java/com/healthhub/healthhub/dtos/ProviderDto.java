package com.healthhub.healthhub.dtos;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderDto {
    private UUID id;
    private String name;
    private String specialty;
    private String contactInfo;
    private UUID userId;
}