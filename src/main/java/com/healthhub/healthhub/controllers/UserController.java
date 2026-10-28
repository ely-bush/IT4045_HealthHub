package com.healthhub.healthhub.controllers;

import com.healthhub.healthhub.dtos.UserDto;
import com.healthhub.healthhub.entities.User;
import com.healthhub.healthhub.mappers.UserMapper;
import com.healthhub.healthhub.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    // Fetch all users
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    // Fetch one user
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id) {
        return userRepository.findById(id)
                .map(u -> ResponseEntity.ok(userMapper.toDto(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a user
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(user));
    }

    // Update a user
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID id,
                                              @Valid @RequestBody UserDto userDto) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userMapper.update(userDto, user);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    // Delete a user (also deletes their appointments, medications, providers and health logs)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}