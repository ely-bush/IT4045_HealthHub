package com.healthhub.healthhub.mappers;

import com.healthhub.healthhub.dtos.AppointmentDto;
import com.healthhub.healthhub.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(source = "user.id", target = "userId")
    AppointmentDto toDto(Appointment appointment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Appointment toEntity(AppointmentDto appointmentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void update(AppointmentDto appointmentDto, @MappingTarget Appointment appointment);
}