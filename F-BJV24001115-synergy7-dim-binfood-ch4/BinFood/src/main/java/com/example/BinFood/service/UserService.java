package com.example.BinFood.service;

import com.example.BinFood.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void addUser(User user);

    void updateUsername(String newUsername, String oldUsername);
    void updatePassword(String newPasswword, String name);
    void updateEmail(String newEmailAddress, String name);

    void deleteUser(String username);
    User getUserByName(String name);
}
