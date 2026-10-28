package com.healthhub.healthhub.controllers;

import com.healthhub.healthhub.dtos.ProviderDto;
import com.healthhub.healthhub.entities.Provider;
import com.healthhub.healthhub.entities.User;
import com.healthhub.healthhub.mappers.ProviderMapper;
import com.healthhub.healthhub.repositories.ProviderRepository;
import com.healthhub.healthhub.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/providers")
public class ProviderController {
    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;
    private final ProviderMapper providerMapper;

    public ProviderController(ProviderRepository providerRepository,
                              UserRepository userRepository,
                              ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.userRepository = userRepository;
        this.providerMapper = providerMapper;
    }


    @GetMapping
    public List<ProviderDto> getAllProviders(
            @RequestParam(name = "userId", required = false) UUID userId) {
        List<Provider> providers = (userId == null)
                ? providerRepository.findAll()
                : providerRepository.findByUser_Id(userId);
        return providers.stream().map(providerMapper::toDto).toList();
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProviderDto> getProvider(@PathVariable UUID id) {
        return providerRepository.findById(id)
                .map(p -> ResponseEntity.ok(providerMapper.toDto(p)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ProviderDto> createProvider(@Valid @RequestBody ProviderDto providerDto) {
        User user = userRepository.findById(providerDto.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        Provider provider = providerMapper.toEntity(providerDto);
        provider.setUser(user);
        providerRepository.save(provider);
        return ResponseEntity.status(HttpStatus.CREATED).body(providerMapper.toDto(provider));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProviderDto> updateProvider(@PathVariable UUID id,
                                                      @Valid @RequestBody ProviderDto providerDto) {
        Provider provider = providerRepository.findById(id).orElse(null);
        if (provider == null) {
            return ResponseEntity.notFound().build();
        }
        User user = userRepository.findById(providerDto.getUserId()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        providerMapper.update(providerDto, provider);
        provider.setUser(user);
        providerRepository.save(provider);
        return ResponseEntity.ok(providerMapper.toDto(provider));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable UUID id) {
        if (!providerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        providerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}