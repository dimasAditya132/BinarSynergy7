package com.example.stream;

import com.example.service.MailService;
import com.example.stream.data.EmailSendOtpDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @Autowired
    private MailService mailService;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "email-verification")
    public void handleMessage(String message) {
        try {
            EmailSendOtpDto emailSendOtpDto = objectMapper.readValue(message, EmailSendOtpDto.class);
            mailService.sendVerificationEmail(emailSendOtpDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @KafkaListener(topics = "email-forgot-password")
    public void consumeResetPasswordEmail(String message) {
        try {
            EmailSendOtpDto sendOtpDto = objectMapper.readValue(message, EmailSendOtpDto.class);
            mailService.sendResetPasswordEmail(sendOtpDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
