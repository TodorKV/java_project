package com.ft.service.base;

import com.ft.dto.ItemDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {
    Page<ItemDto> getAllItemsPaginated(Integer pageNo, Integer pageSize, String sortBy);
    ItemDto save(ItemDto dto);
    ItemDto edit(Long targetId, ItemDto sourceDto);
    void deleteById(Long id);
}
