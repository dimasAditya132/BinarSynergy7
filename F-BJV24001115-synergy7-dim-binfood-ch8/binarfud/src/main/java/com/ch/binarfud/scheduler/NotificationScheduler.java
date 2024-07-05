package com.ch.binarfud.scheduler;

import com.ch.binarfud.service.FirebaseService;
import com.ch.binarfud.stream.data.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationScheduler {
    @Autowired
    FirebaseService firebaseService;
    private final MessageProducer messageProducer;

    public NotificationScheduler(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @Scheduled(cron = "0 0 12 * * *")
    public void sendPromotionNotification() {
        firebaseService.sendNotification(
                "djD7Zm4bUU3qE79G93JT0P:APA91bG9aXZ2VICUf1X1NwwJSDLtAAD7Xi77e6Yn06xdi9KBMAnWMi4GAzi7DgJ81-pYhYcoYa3kov34ypCzeHVsqM3pMFUzLL2biRkPkxSx48Sv2KdmtPIKY2_CifCvcCYoRy07avLx",
                "Promotion",
                "Get 50% discount for all products");

        System.out.println("Test Scheduler");
    }
}
