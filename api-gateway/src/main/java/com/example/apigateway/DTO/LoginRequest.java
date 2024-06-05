package com.example.apigateway.DTO;

public class LoginRequest {
    private String token;

    public LoginRequest() {
    }

    public LoginRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}