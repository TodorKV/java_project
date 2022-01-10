package com.ft.mappers;

import com.ft.dto.HallDto;
import com.ft.entity.Hall;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HallMapper {
    Hall fromDto(HallDto dto);
    HallDto toDto(Hall entity);
}
