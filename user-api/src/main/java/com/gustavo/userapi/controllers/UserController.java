package com.gustavo.userapi.controllers;

import com.gustavo.userapi.dto.UserDTO;
import com.gustavo.userapi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public UserDTO findById(@PathVariable String userId) {
        return userService.findById(userId);
    }

}
