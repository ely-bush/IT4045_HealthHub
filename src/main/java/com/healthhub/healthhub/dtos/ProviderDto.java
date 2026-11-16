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
public class ProviderDto {
    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be 100 characters or less")
    private String name;

    @Size(max = 100, message = "Specialty must be 100 characters or less")
    private String specialty;

    @Size(max = 255, message = "Contact info must be 255 characters or less")
    private String contactInfo;

    @NotNull(message = "userId is required")
    private UUID userId;
}