package com.healthhub.healthhub.services;

import com.healthhub.healthhub.dtos.UserDto;
import com.healthhub.healthhub.entities.User;
import com.healthhub.healthhub.mappers.UserMapper;
import com.healthhub.healthhub.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public Optional<UserDto> getUserById(UUID id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    public Optional<UserDto> updateUser(UUID id, UserDto userDto) {
        return userRepository.findById(id).map(user -> {
            userMapper.update(userDto, user);
            return userMapper.toDto(userRepository.save(user));
        });
    }

    public boolean deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}
