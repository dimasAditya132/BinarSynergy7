package com.example.BinFood.service;

import com.example.BinFood.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean userIsExistAndCorrect(String username, String emailAddress);

    boolean addUser(User user);

    boolean updateUsername(String newUsername, String oldUsername, String emailAddress);

    boolean updatePassword(String newPasswword, String username, String emailAddress);

    boolean updateEmail(String newEmailAddress, String username, String oldEmailAddress);

    boolean deleteUser(String username, String password, String emailAddress);

    User getUserByName(String username);
}
