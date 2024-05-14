package com.example.BinFood.service;

import com.example.BinFood.model.User;
import com.example.BinFood.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        User userToSave = Optional.ofNullable(user)
                .filter(val -> val.getUsername() != null && val.getPassword() != null && val.getEmailAddress() != null)
                .orElse(User.builder()
                        .username("DELETETHIS0")
                        .build());

        userRepository.save(userToSave);

        try {
            this.deleteUser("DELETETHIS0");
        } catch (Exception e) {
            log.info("User berhasil ditambahkan");
        }
    }

    @Override
    public void updateUsername(String newUsername, String oldUsername) {
        userRepository.queryUpdateUsername(newUsername, oldUsername);
    }

    @Override
    public void updatePassword(String newPass, String name) {
        userRepository.queryUpdatePassword(newPass, name);
    }

    @Override
    public void updateEmail(String newEmailAdress, String username) {
        userRepository.queryUpdateEmail(newEmailAdress, username);

    }

    @Override
    public void deleteUser(String username) {
        userRepository.queryDeleteByName(username);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.queryFindUserByName(username);
    }
}
