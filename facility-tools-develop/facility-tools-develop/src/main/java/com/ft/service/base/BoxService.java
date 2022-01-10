package com.ft.service.base;

import com.ft.dto.BoxDto;
import com.ft.dto.HallDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoxService {
    List<BoxDto> findAll();
    BoxDto save(BoxDto dto);
    BoxDto edit(Long targetId, BoxDto sourceDto);
    void deleteById(Long id);
    Page<BoxDto> getAllBoxesPaginated(Integer pageNo, Integer pageSize, String sortBy);
}
