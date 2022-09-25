package com.yildiz.serhat.sharingplatformservice.controller;


import com.yildiz.serhat.sharingplatformservice.domain.model.UserResponseModel;
import com.yildiz.serhat.sharingplatformservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public UserResponseModel login(@RequestParam("email") String email) {
        return userService.registerUser(email);
    }
}
