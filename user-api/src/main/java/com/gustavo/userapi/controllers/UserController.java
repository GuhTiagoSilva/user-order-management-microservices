package com.gustavo.userapi.controllers;

import com.gustavo.userapi.dto.UserDTO;
import com.gustavo.userapi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public UserDTO findById(@PathVariable String userId) {
        return userService.findById(userId);
    }

}
