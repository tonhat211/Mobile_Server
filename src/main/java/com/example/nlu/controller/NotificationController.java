package com.example.nlu.controller;

import com.example.nlu.dto.ShortNotificationResponse;
import com.example.nlu.model.Notification;
import com.example.nlu.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/notifications")
    public List<ShortNotificationResponse> getAllNotifications() {
        System.out.println("getAllNotifications");
        List<Notification> notifications = notificationRepository.findAll();
        List<ShortNotificationResponse> notificationResponses = new ArrayList<>();
        for (Notification notification : notifications) {
            notificationResponses.add(new ShortNotificationResponse(notification));
        }
        return notificationResponses;
    }

    @PostMapping("/notification")
    public Notification getNotificationDetail(@RequestBody long id) {
        System.out.println("getNotificationDetail");
        Optional<Notification> optional= notificationRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }
}
