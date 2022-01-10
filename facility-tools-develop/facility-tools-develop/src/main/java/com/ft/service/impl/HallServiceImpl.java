package com.ft.service.impl;

import com.ft.dto.HallDto;
import com.ft.dto.ItemDto;
import com.ft.entity.Hall;
import com.ft.entity.Item;
import com.ft.exception.NotFoundException;
import com.ft.mappers.HallMapper;
import com.ft.repository.HallRepository;
import com.ft.service.base.HallService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository repository;
    private final HallMapper mapper;

    public HallServiceImpl(HallRepository repository,
                           HallMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<HallDto> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HallDto save(HallDto dto) {
        Hall entity = mapper.fromDto(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public HallDto edit(Long targetId, HallDto sourceDto) {
        Hall source = mapper.fromDto(sourceDto);

        Hall target = repository
                .findById(targetId)
                .orElseThrow(() -> new NotFoundException(Hall.class, targetId));

        BeanUtils.copyProperties(source, target, "id");

        return mapper.toDto(repository.save(target));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<HallDto> getAllHallsPaginated(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Hall> pagedResult = this.repository.findAll(paging);
        Page<HallDto> dtoPagedResult = new PageImpl<>(
                pagedResult.getContent().stream().map(mapper::toDto).collect(Collectors.toList()),
                paging,
                pagedResult.getTotalElements());

        return dtoPagedResult;
    }

    @Override
    public Page<HallDto> searchByNamePaginated(String name, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Hall> pagedSearchResult = this.repository.findByNameIgnoreCaseContaining(name, paging);
        Page<HallDto> dtoPagedResult = new PageImpl<>(
                pagedSearchResult.getContent().stream().map(mapper::toDto).collect(Collectors.toList()),
                paging,
                pagedSearchResult.getTotalElements());
        return dtoPagedResult;
    }
}
