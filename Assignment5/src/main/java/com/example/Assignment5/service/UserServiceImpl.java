package com.example.Assignment5.service;

import com.example.Assignment5.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final DataSource dataSource;

    @Override
    public User authenticate(String userName, String password) {
        String query = String.format("select * from user where userName = '%s' and password = '%s' ",userName,password);
        try (Connection connection = dataSource.getConnection();
             ResultSet  resultSet = connection.createStatement()
                     .executeQuery(query)){
            while(resultSet.next())
            {
                return new User(resultSet.getInt("id"),resultSet.getString("userName"),resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}