package com.ft.service.base;

import com.ft.dto.HallDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HallService {
    List<HallDto> findAll();
    HallDto save(HallDto dto);
    HallDto edit(Long targetId, HallDto sourceDto);
    void deleteById(Long id);
    Page<HallDto> getAllHallsPaginated(Integer pageNo, Integer pageSize, String sortBy);
    Page<HallDto> searchByNamePaginated(String name, Integer pageNo, Integer pageSize, String sortBy);
}
