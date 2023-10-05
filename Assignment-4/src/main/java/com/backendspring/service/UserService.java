package com.backendspring.service;

import com.backendspring.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();
    User save(User user);
}
