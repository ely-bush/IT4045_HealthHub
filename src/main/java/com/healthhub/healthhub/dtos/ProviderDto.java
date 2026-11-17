
package com.healthhub.healthhub.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderDto {

    private UUID id;

    @NotBlank(message = "Provider name is required")
    private String name;

    private String specialty;

    private String contactInfo;

    @NotNull(message = "User ID is required")
    private UUID userId;
}
