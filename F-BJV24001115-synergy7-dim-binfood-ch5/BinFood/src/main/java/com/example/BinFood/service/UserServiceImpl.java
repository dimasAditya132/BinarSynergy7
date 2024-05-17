package com.example.BinFood.service;

import com.example.BinFood.model.User;
import com.example.BinFood.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean userIsExistAndCorrect(String username, String emailAddress) {
        try {
            log.info("Finding user with username {} and email address {}", username, emailAddress);
            User foundUserByName = userRepository.queryFindUserByName(username);
            User foundUserByEmail = userRepository.queryFindUserByEmail(emailAddress);
            return (foundUserByName != null && foundUserByEmail != null) && foundUserByName.equals(foundUserByName);
        } catch (Exception e) {
            log.error("User not found");
            log.error("Cause : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        try {
            log.info("Adding user to Database");
            userRepository.save(Objects.requireNonNull(Optional.ofNullable(user)
                    .filter(val -> val.getUsername() != null && val.getPassword() != null && val.getEmailAddress() != null)
                    .orElse(null)));
            log.info("User (username: {}) added successfully", user.getUsername());
            return true;
        } catch (Exception e) {
            log.error("Failed to add user to Database");
            log.error("Cause : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUsername(String newUsername, String oldUsername, String emailAddress) {
        boolean foundUser = this.userIsExistAndCorrect(oldUsername, emailAddress);
        if (foundUser) {
            log.info("User {} found", oldUsername);
            log.info("Updating new username");
            userRepository.queryUpdateUsername(newUsername, oldUsername);
            log.info("Successfully changed {} -> {}", oldUsername, newUsername);
            return true;
        } else {
            log.error("User with username {} and email {} is incorrect", oldUsername, emailAddress);
            return false;
        }
    }

    @Override
    public boolean updatePassword(String newPass, String username, String emailAddress) {
        boolean foundUser = this.userIsExistAndCorrect(username, emailAddress);
        if (foundUser) {
            log.info("User {} found", username);
            log.info("Updating new password");
            userRepository.queryUpdatePassword(newPass, username);
            log.info("Successfully changed {} password", username);
            return true;
        } else {
            log.error("User with username {} and email {} is incorrect", username, emailAddress);
            return false;
        }
    }

    @Override
    public boolean updateEmail(String newEmailAdress, String username, String oldEmailAddress) {
        boolean foundUser = this.userIsExistAndCorrect(username, oldEmailAddress);
        if (foundUser) {
            log.info("User {} found", username);
            log.info("Updating new email address");
            userRepository.queryUpdateEmail(newEmailAdress, username);
            log.info("Successfully changed {} email address", username);
            return true;
        } else {
            log.error("User with username {} and email {} is incorrect", username, oldEmailAddress);
            return false;
        }

    }

    @Override
    public boolean deleteUser(String username, String password, String emailAddress) {
        boolean foundUser = this.userIsExistAndCorrect(username, emailAddress);
        if (foundUser) {
            log.info("User {} found", username);
            log.info("Deleting user {}", username);
            userRepository.queryDeleteByName(username, password, emailAddress);
            log.info("Successfully deleted user {}", username);
            return true;
        } else {
            log.error("User with username {} and email {} is incorrect", username, emailAddress);
            return false;
        }
    }

    @Override
    public User getUserByName(String username) {
        if (Objects.nonNull(username)) return userRepository.queryFindUserByName(username);
        else return null;
    }
}
