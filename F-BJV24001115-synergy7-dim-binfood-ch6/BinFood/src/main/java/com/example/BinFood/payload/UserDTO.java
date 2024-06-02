package com.example.BinFood.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;
    private String name;
    private String username;
    private String emailAddress;
    private String password;
}
