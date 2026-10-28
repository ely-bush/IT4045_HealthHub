package com.healthhub.healthhub.mappers;

import com.healthhub.healthhub.dtos.UserDto;
import com.healthhub.healthhub.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    void update(UserDto userDto, @MappingTarget User user);
}