package com.healthhub.healthhub.mappers;

import com.healthhub.healthhub.dtos.ProviderDto;
import com.healthhub.healthhub.entities.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    @Mapping(source = "user.id", target = "userId")
    ProviderDto toDto(Provider provider);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Provider toEntity(ProviderDto providerDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void update(ProviderDto providerDto, @MappingTarget Provider provider);
}