package com.healthhub.healthhub.mappers;

import com.healthhub.healthhub.dtos.MedicationDto;
import com.healthhub.healthhub.entities.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    @Mapping(source = "user.id", target = "userId")
    MedicationDto toDto(Medication medication);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Medication toEntity(MedicationDto medicationDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void update(MedicationDto medicationDto, @MappingTarget Medication medication);
}