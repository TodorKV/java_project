package com.ft.service.base;

import com.ft.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto findByUsername(String username);
}
