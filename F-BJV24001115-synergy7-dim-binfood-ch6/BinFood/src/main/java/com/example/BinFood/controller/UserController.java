package com.example.BinFood.controller;

import com.example.BinFood.model.accounts.User;
import com.example.BinFood.payload.Response;
import com.example.BinFood.payload.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    final ModelMapper modelMapper;
    final UserUtil userUtil;

    public UserController(ModelMapper modelMapper, UserUtil userUtil) {
        this.modelMapper = modelMapper;
        this.userUtil = userUtil;
    }

    @GetMapping("{username}")
    public ResponseEntity<Response> getUser(@PathVariable String username) {
        User user;
        try {
            user = userUtil.getUserDetailByUsername(username);
            return ResponseEntity.ok(new Response.Success(modelMapper.map(user, UserDTO.class)));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Response.Error(e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<Response> addUser(@RequestBody UserDTO userDTO) {
        User user;
        try {
            user = modelMapper.map(userDTO, User.class);
            userUtil.createUser(user.getName(), user.getUsername(), user.getEmailAddress(), user.getPassword());
            return ResponseEntity.ok(new Response.SuccessNull("User Created"));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Response.Error(e.getMessage()), HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable("id") String userId) {
        try {
            userUtil.deleteUser(userId);
            return ResponseEntity.ok(new Response.SuccessNull("User Deleted"));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Response.Error(e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Response> updateUser(@PathVariable("id") String userId, @RequestBody UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setId(UUID.fromString(userId));
        try {
            user = userUtil.updateUser(user);
            return ResponseEntity.ok(new Response.Success(modelMapper.map(user, UserDTO.class)));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Response.Error(e.getLocalizedMessage()), HttpStatus.OK);
        }
    }
}
