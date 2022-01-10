package com.ft.mappers;

import com.ft.dto.BoxDto;
import com.ft.entity.Box;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoxMapper {
    Box fromDto(BoxDto dto);
    BoxDto toDto(Box entity);
}
