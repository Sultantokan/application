package com.ru.application.controller;

import com.ru.application.model.dto.UserDTO;
import com.ru.application.model.entity.User;
import com.ru.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/security")
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    User registerUser(@RequestBody UserDTO userDTO) {
        System.out.println("hello");
        return userService.registerUser(userDTO);
    }

    @GetMapping("/hello")
    String sayHello(){
        System.out.println("Whatttt");
        return "Hello World!!!";
    }
    @GetMapping("/bye")
    String sayBy(){
        return "Bye Bye!!!";
    }
}
