package com.example.service;

import com.example.stream.data.EmailSendOtpDto;

public interface MailService {
    void sendEmail(String to, String subject, String message);

    void sendVerificationEmail(EmailSendOtpDto sendOtpDto);

    void sendResetPasswordEmail(EmailSendOtpDto sendOtpDto);
}
