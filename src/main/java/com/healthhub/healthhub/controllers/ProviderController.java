package com.healthhub.healthhub.controllers;

import com.healthhub.healthhub.dtos.ProviderDto;
import com.healthhub.healthhub.services.ProviderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/providers")
public class ProviderController {
    private final ProviderService providerService;
    
    @GetMapping
    public List<ProviderDto> getAllProviders() {
        return providerService.findAll();
    }
    
    @GetMapping("/{id}")
    public ProviderDto getProvider(@PathVariable UUID id) {
        return providerService.findById(id);
    }
    
    @PostMapping
    public ResponseEntity<ProviderDto> createProvider(@Valid @RequestBody ProviderDto ProviderDto) {
        ProviderDto created = providerService.create(ProviderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDto> updateProvider(@PathVariable UUID id,
                                                          @Valid @RequestBody ProviderDto ProviderDto) {
        ProviderDto updated = providerService.update(id, ProviderDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProvider(@PathVariable UUID id) {
        providerService.delete(id);
    }
}