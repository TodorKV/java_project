package com.ft.controllers;

import com.ft.dto.UserDto;
import com.ft.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Post Mapping
     *
     * @param dto UserDto
     * @return UserDto
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto dto) {
        return new ResponseEntity<>(userService.saveAndReturnAsDto(dto), HttpStatus.CREATED);
    }
    /**
     * Get Mapping
     *
     * @return UserDto
     */
    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }
}
