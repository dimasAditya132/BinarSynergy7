package com.example.stream.data;

import lombok.Data;

@Data
public class EmailSendOtpDto {
    private String to;
    private String username;
    private String otp;
}
