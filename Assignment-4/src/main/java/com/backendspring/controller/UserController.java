package com.backendspring.controller;

import com.backendspring.entity.User;
import com.backendspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user)
    {
        return userService.save(user);
    }

    @GetMapping("/findall")
    public List<User> findAllUsers() {
        return userService.getAllUser();
    }
}
