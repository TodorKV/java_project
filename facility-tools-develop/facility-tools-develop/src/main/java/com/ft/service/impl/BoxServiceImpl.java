package com.ft.service.impl;

import com.ft.dto.BoxDto;
import com.ft.dto.HallDto;
import com.ft.entity.Box;
import com.ft.entity.Hall;
import com.ft.exception.BadRequestException;
import com.ft.exception.NotFoundException;
import com.ft.mappers.BoxMapper;
import com.ft.repository.BoxRepository;
import com.ft.repository.HallRepository;
import com.ft.service.base.BoxService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoxServiceImpl implements BoxService {

    private final BoxRepository repository;
    private final HallRepository hallRepository;
    private final BoxMapper mapper;

    public BoxServiceImpl(BoxRepository repository,
                          HallRepository hallRepository,
                          BoxMapper mapper) {
        this.repository = repository;
        this.hallRepository = hallRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BoxDto> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BoxDto save(BoxDto dto) {
        Box entity = mapper.fromDto(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public BoxDto edit(Long targetId, final BoxDto sourceDto) {
        Box source = mapper.fromDto(sourceDto);

        Box target = repository
                .findById(targetId)
                .orElseThrow(() -> new NotFoundException(Box.class, targetId));

        BeanUtils.copyProperties(source, target, "id");

        if(source.getHall() != null) {
            Long hallId = source.getHall().getId();
            if (hallId != null) {
                Hall hall = hallRepository.findById(hallId)
                        .orElseThrow(() -> new NotFoundException(Hall.class, hallId));

                target.setHall(hall);
            } else {
                throw new BadRequestException(Hall.class);
            }
        }

        return mapper.toDto(repository.save(target));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<BoxDto> getAllBoxesPaginated(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Box> pagedResult = this.repository.findAll(paging);
        Page<BoxDto> dtoPagedResult = new PageImpl<>(
                pagedResult.getContent().stream().map(mapper::toDto).collect(Collectors.toList()),
                paging,
                pagedResult.getTotalElements());

        return dtoPagedResult;
    }
}
