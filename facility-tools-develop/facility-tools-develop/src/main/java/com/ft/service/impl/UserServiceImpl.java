package com.ft.service.impl;

import com.ft.dto.UserDto;
import com.ft.entity.User;
import com.ft.repository.UserRepository;
import com.ft.mappers.UserMapper;
import com.ft.service.base.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService implementation.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toDto(userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " was not found!")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " was not found!"));
    }

    public UserDto saveAndReturnAsDto(UserDto dto) {
        User user = userMapper.fromDto(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }
}
