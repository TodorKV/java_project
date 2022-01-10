package com.ft.repository;

import com.ft.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Page<User> findByUsernameIgnoreCaseContaining(@RequestParam("username") String username, Pageable pageable);
}
