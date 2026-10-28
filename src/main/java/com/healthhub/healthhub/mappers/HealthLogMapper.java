package com.healthhub.healthhub.mappers;

import com.healthhub.healthhub.dtos.HealthLogDto;
import com.healthhub.healthhub.entities.HealthLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HealthLogMapper {

    @Mapping(source = "user.id", target = "userId")
    HealthLogDto toDto(HealthLog healthLog);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    HealthLog toEntity(HealthLogDto healthLogDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void update(HealthLogDto healthLogDto, @MappingTarget HealthLog healthLog);
}