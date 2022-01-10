package com.ft.service.impl;

import com.ft.dto.ItemDto;
import com.ft.entity.Box;
import com.ft.entity.Item;
import com.ft.exception.BadRequestException;
import com.ft.exception.NotFoundException;
import com.ft.mappers.ItemMapper;
import com.ft.repository.BoxRepository;
import com.ft.repository.ContentRepository;
import com.ft.service.base.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ContentRepository repository;
    private final BoxRepository boxRepository;
    private final ItemMapper mapper;

    public ItemServiceImpl(ContentRepository repository,
                           BoxRepository boxRepository,
                           ItemMapper mapper) {
        this.repository = repository;
        this.boxRepository = boxRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<ItemDto> getAllItemsPaginated(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Item> pagedResult = this.repository.findAll(paging);
        Page<ItemDto> dtoPagedResult = new PageImpl<>(
                pagedResult.getContent().stream().map(mapper::toDto).collect(Collectors.toList()),
                paging,
                pagedResult.getTotalElements());

        return dtoPagedResult;
    }

    @Override
    public ItemDto save(ItemDto dto) {
        Item entity = mapper.fromDto(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public ItemDto edit(Long targetId, ItemDto sourceDto) {
        Item source = mapper.fromDto(sourceDto);

        Item target = repository.findById(targetId)
                .orElseThrow(() -> new NotFoundException(Item.class, targetId));

        BeanUtils.copyProperties(source, target, "id", "tenant");

        if (source.getBox() != null) {
            Long boxId = source.getBox().getId();
            if (boxId != null) {
                Box box = boxRepository.findById(boxId)
                        .orElseThrow(() -> new NotFoundException(Box.class, boxId));

                target.setBox(box);
            } else {
                throw new BadRequestException(Box.class);
            }
        }

        return mapper.toDto(repository.save(target));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
