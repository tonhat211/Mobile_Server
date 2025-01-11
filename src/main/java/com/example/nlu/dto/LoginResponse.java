package com.example.nlu.dto;

public class LoginResponse {
    private String name;
    private long id;
    private String token;

    public LoginResponse(long id, String name, String token) {
        this.name = name;
        this.id = id;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

