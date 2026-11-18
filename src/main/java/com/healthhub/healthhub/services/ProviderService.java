package com.healthhub.healthhub.services;

import com.healthhub.healthhub.dtos.ProviderDto;
import com.healthhub.healthhub.entities.Provider;
import com.healthhub.healthhub.entities.User;
import com.healthhub.healthhub.mappers.ProviderMapper;
import com.healthhub.healthhub.repositories.ProviderRepository;
import com.healthhub.healthhub.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ProviderService {
    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;
    private final ProviderMapper providerMapper;

    @Transactional
    public List<ProviderDto> findAll() {
        return providerRepository.findAll().stream()
                .map(providerMapper::toDto)
                .toList();
    }

    @Transactional
    public ProviderDto findById(UUID id) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Provider not found"));
        return providerMapper.toDto(provider);
    }


    @Transactional
    public ProviderDto create(ProviderDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "User not found"));

        Provider provider = providerMapper.toEntity(dto);
        provider.setUser(user);

        return providerMapper.toDto(providerRepository.save(provider));
    }

    @Transactional
    public ProviderDto update(UUID id, ProviderDto dto) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Provider not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "User not found"));

        providerMapper.update(dto, provider);
        provider.setUser(user);

        return providerMapper.toDto(providerRepository.save(provider));
    }

    @Transactional
    public void delete(UUID id) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Provider not found"));
        providerRepository.delete(provider);
    }
}
