package com.example.nlu.model;

public class UserMin {
    private long id;
    private String name;
    private boolean isBlocked;

    public UserMin(long id, String name, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.isBlocked = isBlocked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
