package com.example.apigateway.DTO;

public class LoginResponse {
    private boolean valid;

    public LoginResponse() {
    }

    public LoginResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}