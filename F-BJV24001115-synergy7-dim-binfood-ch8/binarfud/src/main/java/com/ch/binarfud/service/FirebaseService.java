package com.ch.binarfud.service;


public interface FirebaseService {
    void sendNotification(String token, String header, String body);
}
