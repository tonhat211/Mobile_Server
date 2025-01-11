package com.example.nlu.dto;

import com.example.nlu.model.Notification;

import java.time.LocalDateTime;

public class ShortNotificationResponse {
    private long id;
    private String title;
    private LocalDateTime createAt;
    private String subContent;

    public ShortNotificationResponse(Notification notification) {
        this.id=notification.getId();
        this.title = notification.getTitle();
        this.createAt = notification.getCreateAt();
        this.subContent = notification.getSubContent();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }
}

