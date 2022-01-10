package com.ft.repository;

import com.ft.entity.Hall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    Page<Hall> findByNameIgnoreCaseContaining(String name, Pageable pageable);
}
