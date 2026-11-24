package com.healthhub.healthhub.controllers;

import com.healthhub.healthhub.dtos.ProviderDto;
import com.healthhub.healthhub.services.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;

    @GetMapping
    public List<ProviderDto> getAllProviders(
            @RequestParam(name = "userId", required = false) UUID userId) {
        return providerService.getAllProviders(userId);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProviderDto> getProvider(@PathVariable UUID id) {
        return providerService.getProviderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ProviderDto> createProvider(@Valid @RequestBody ProviderDto providerDto) {
        return providerService.createProvider(providerDto)
                .map(provider -> ResponseEntity.status(HttpStatus.CREATED).body(provider))
                .orElse(ResponseEntity.badRequest().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProviderDto> updateProvider(@PathVariable UUID id,
                                                      @Valid @RequestBody ProviderDto providerDto) {
        ProviderService.UpdateResult result = providerService.updateProvider(id, providerDto);
        return switch (result.status()) {
            case UPDATED -> ResponseEntity.ok(result.provider());
            case PROVIDER_NOT_FOUND -> ResponseEntity.notFound().build();
            case USER_NOT_FOUND -> ResponseEntity.badRequest().build();
        };
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable UUID id) {
        return providerService.deleteProvider(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}