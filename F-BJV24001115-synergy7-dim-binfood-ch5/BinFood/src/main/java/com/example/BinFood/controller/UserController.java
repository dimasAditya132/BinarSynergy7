package com.example.BinFood.controller;

import com.example.BinFood.model.User;
import com.example.BinFood.model.response.ErrorResponse;
import com.example.BinFood.model.response.UserResponse;
import com.example.BinFood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "add", consumes = "application/json")
    public ResponseEntity<String> requestAddUser(@RequestBody User user) {
        if (userService.addUser(user)) return new ResponseEntity<>("Succesfully add user", HttpStatus.OK);
        else return new ResponseEntity<>("Could not add user", HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping(value = "edit/username/{username}")
    public ResponseEntity<ErrorResponse<Object>> requestEditUsername(@PathVariable("username") String oldUsername,
                                                                     @RequestBody UserResponse userResponse) {
        if (userService.updateUsername(userResponse.getUsernameR(),
                oldUsername,
                userResponse.getEmailAddressR())) {
            ErrorResponse<Object> ER = ErrorResponse.builder()
                    .entity(userResponse)
                    .errorMessage("Successfully edited " + oldUsername + " username")
                    .errorCode(HttpStatus.OK.value()).build();
            return new ResponseEntity<>(ER, HttpStatus.OK);
        } else return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage("Could not edit user " + oldUsername + " username")
                .errorCode(HttpStatus.NOT_FOUND.value())
                .build(), HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "edit/password/{username}")
    public ResponseEntity<ErrorResponse<Object>> requestEditPassword(@PathVariable("username") String username,
                                                                     @RequestBody UserResponse userResponse) {
        if (userService.updatePassword(userResponse.getPasswordR(),
                username,
                userResponse.getEmailAddressR ())) {
            ErrorResponse<Object> ER = ErrorResponse.builder()
                    .entity(userResponse)
                    .errorMessage("Successfully edited " + username + " password")
                    .errorCode(HttpStatus.OK.value()).build();
            return new ResponseEntity<>(ER, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Could not edit user " + username + " password")
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "edit/email/{username}")
    public ResponseEntity<String> requestEditEmail(@PathVariable("username") String username,
                                                   @RequestParam("newE") String newEmail,
                                                   @RequestParam("uE") String oldEmail) {
        if (userService.updateEmail(newEmail, username, oldEmail)) {
            return new ResponseEntity<>("Successfully edited " + username + " email", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Could not edit user " + username + " email", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "delete/{username}")
    public ResponseEntity<String> requestDeleteUser(@PathVariable("username") String username,
                                                    @RequestParam("uP") String password,
                                                    @RequestParam("uE") String email) {
        if (userService.deleteUser(username, password, email))
            return new ResponseEntity<>("Successfully remove user " + username, HttpStatus.OK);
        else return new ResponseEntity<>("Could not remove user " + username, HttpStatus.NOT_ACCEPTABLE);
    }


}
