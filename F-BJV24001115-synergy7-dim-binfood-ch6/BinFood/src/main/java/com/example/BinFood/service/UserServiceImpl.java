package com.example.BinFood.service;

import com.example.BinFood.model.accounts.ERole;
import com.example.BinFood.model.accounts.Role;
import com.example.BinFood.model.accounts.User;
import com.example.BinFood.repository.RoleRepository;
import com.example.BinFood.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean insertUserProcedure(String name, String username, String emailAddress, String password) {
        userRepository.insertUserData(name, username, emailAddress, password);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            return Collections.emptyList();
        }
        return userList;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsernameAndDeleted(username, Boolean.FALSE);
        if (user.isEmpty()) {
            throw new RuntimeException("Data with username \"" + username + "\" not found");
        }
        return user.get();
    }

    @Override
    public User getUserById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<User> user = userRepository.findById(uuid);
        if (user.isEmpty()) {
            throw new RuntimeException("Data with id \"" + id + "\" not found");
        }
        return user.get();
    }

    @Override
    public List<User> getUserByUsernameLike(String s) {
        List<User> users = userRepository.findByUsernameLike("%" + s + "%");
        if (users.isEmpty()) {
            throw new RuntimeException();
        }
        return users;
    }

    @Override
    public void hardDeleteUser(User user) {
        userRepository.deleteUserData(user.getId());
        log.info("Data with id \"" + user.getId() + "\" successfully deleted");
    }

    @Override
    public void softDeleteUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User oldUser = getUserNoException(user.getId());
        if (oldUser != null) {
            assignNewUserData(oldUser, user);
        } else {
            throw new RuntimeException("Data does not found");
        }
        userRepository.save(oldUser);
        log.info("User successfully updated");
        return oldUser;
    }

    @Override
    public void createUserPostLogin(String name, String email) {
        Role role = roleRepository.findByName(ERole.ROLE_CUSTOMER);
        Set<Role> roles = new HashSet<>(Collections.singletonList(role));

        User user = getUserByUsername(email);
        if (user == null) {
            user = User.builder()
                    .username(name)
                    .emailAddress(email)
                    .roles(roles)
                    .build();
            userRepository.save(user);
        }
    }

    private User getUserNoException(UUID id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            return userOptional.orElse(null);
        } catch (RuntimeException e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    private void assignNewUserData(User oldUser, User newUser) {
        if (newUser.getUsername() != null) oldUser.setUsername(newUser.getUsername());
        if (newUser.getEmailAddress() != null) oldUser.setEmailAddress(newUser.getEmailAddress());
        if (newUser.getName() != null) oldUser.setName(newUser.getName());
        if (newUser.getPassword() != null) oldUser.setPassword(newUser.getPassword());
        if ((newUser.getUsername() == null) && (newUser.getName() == null) && (newUser.getEmailAddress() == null) && (newUser.getPassword() == null)) {
            throw new RuntimeException("No data to update");
        }
    }
}
