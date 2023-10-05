package com.example.Assignment5.service;

import com.example.Assignment5.entity.User;

public interface UserService {
    public User authenticate(String userName, String password);
}
