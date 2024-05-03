package com.example.BinarFood.service;

import com.example.BinarFood.model.User;

public interface UserService {
    boolean addUser();
    User addUser(User user);
    boolean confirmUser(String username,String password);

    User updateUser();

    User deleteUser();
}
