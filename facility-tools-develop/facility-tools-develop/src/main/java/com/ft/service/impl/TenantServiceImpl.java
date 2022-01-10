package com.ft.service.impl;

import com.ft.repository.TenantRepository;
import com.ft.service.base.TenantService;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepository repository;

    public TenantServiceImpl(TenantRepository repository) {
        this.repository = repository;
    }
}
