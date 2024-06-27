package com.example.service;

import com.example.stream.data.EmailSendOtpDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    @Override
    public void sendVerificationEmail(EmailSendOtpDto sendOtpDto) {
        Context context = new Context();
        context.setVariable("username", sendOtpDto.getUsername());
        context.setVariable("verificationOtp", sendOtpDto.getOtp());

        String subject = "Email Verification";
        String body = sendOtpDto.getOtp();

        sendEmail(sendOtpDto.getTo(), subject, body);
    }

    @Override
    public void sendResetPasswordEmail(EmailSendOtpDto sendOtpDto) {
        Context context = new Context();
        context.setVariable("username", sendOtpDto.getUsername());
        context.setVariable("resetPasswordOtp", sendOtpDto.getOtp());

        String subject = "Reset Password";
        String body = sendOtpDto.getOtp();

        sendEmail(sendOtpDto.getTo(), subject, body);
    }
}