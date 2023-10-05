package com.backendspring.service;

import com.backendspring.entity.User;
import com.backendspring.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword() , BCrypt.gensalt()));
        return userRepository.save(user);
    }

}
