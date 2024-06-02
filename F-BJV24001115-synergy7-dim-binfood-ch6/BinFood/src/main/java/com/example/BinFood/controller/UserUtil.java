package com.example.BinFood.controller;

import com.example.BinFood.model.accounts.User;
import com.example.BinFood.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserUtil {
    private final UserService userService;

    public UserUtil(UserService userService) {
        this.userService = userService;
    }

    public boolean createUser(String name, String username, String email, String password) {
        return userService.insertUserProcedure(name, username, email, password);
    }

    public void getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No User Found");
        } else {
            users.forEach(user -> log.info("User Name: {}", user.getUsername()));
        }
    }

    public User getUserDetailByUsername(String username) {
        User user;
        try {
            user = userService.getUserByUsername(username);
            log.info(user.getId() + " | " + user.getUsername() + " | " + user.getEmailAddress());
            return user;
        } catch (RuntimeException e) {
            log.error(e.getLocalizedMessage());
            throw e;
        }
    }

    public User getUserDetailById(String userId) {
        User user;
        try {
            user = userService.getUserById(userId);
            return user;
        } catch (RuntimeException e) {
            log.error(e.getLocalizedMessage());
            throw e;
        }
    }

    public void getAllUserByUsernameLike(String s) {
        List<User> users = userService.getUserByUsernameLike(s);
        users.forEach(user -> System.out.println(user.getId() + " | " + user.getUsername() + " | " + user.getEmailAddress()));
    }

    public void hardDeleteUser(String username) {
        User user = userService.getUserByUsername(username);
        userService.hardDeleteUser(user);
    }

    public void deleteUser(String id) {
        User user = userService.getUserById(id);
        user.setDeleted(true);
        userService.updateUser(user);
    }

    public User updateUser(User user) {
        try {
            user = userService.updateUser(user);
            log.debug("User updated: {}" + user.getUsername());
            return user;
        } catch (RuntimeException e) {
            log.error(e.getLocalizedMessage());
            throw e;
        }
    }
}
