package com.example.Assignment5.controller;

import com.example.Assignment5.dto.RequestDto;
import com.example.Assignment5.entity.User;
import com.example.Assignment5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    ResponseEntity<User> authenticate(@RequestBody RequestDto requestDto){

        return new ResponseEntity<>(userService.authenticate(requestDto.getUserName(),requestDto.getPassword()), HttpStatus.OK);
    }
}
