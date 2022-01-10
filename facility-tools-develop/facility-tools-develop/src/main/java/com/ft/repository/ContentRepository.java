package com.ft.repository;

import com.ft.entity.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface ContentRepository extends PagingAndSortingRepository<Item, Long> {
}