package com.ft.mappers;

import com.ft.dto.TenantDto;
import com.ft.entity.Tenant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantMapper {
    Tenant fromDto(TenantDto dto);
    TenantDto toDto(Tenant entity);
}
