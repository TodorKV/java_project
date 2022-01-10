package com.ft.mappers;

import com.ft.dto.ItemDto;
import com.ft.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item fromDto(ItemDto dto);
    ItemDto toDto(Item entity);
}
