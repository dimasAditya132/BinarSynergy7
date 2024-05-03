package com.example.BinFood.service;

import com.example.BinFood.model.User;

public interface UserService {
    boolean addUser();
    User addUser(User user);
    boolean confirmUser(String username,String password);

    User updateUser();

    User deleteUser();
}
