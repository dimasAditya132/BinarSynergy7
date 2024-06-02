package com.example.BinFood.service;

import com.example.BinFood.model.accounts.User;

import java.util.List;

public interface UserService {
    boolean insertUserProcedure(String name, String username, String emailAddress, String password);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    User getUserById(String id);

    List<User> getUserByUsernameLike(String s);

    void hardDeleteUser(User user);

    void softDeleteUser(User user);

    User updateUser(User user);

    void createUserPostLogin(String name, String email);
}
