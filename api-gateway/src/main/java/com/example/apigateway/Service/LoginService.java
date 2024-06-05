package com.example.apigateway.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.apigateway.DTO.LoginRequest;
import com.example.apigateway.DTO.LoginResponse;

import reactor.core.publisher.Mono;

@Service
public class LoginService {
    private WebClient webClient;

    @Autowired
    public LoginService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<LoginResponse> validToken(LoginRequest loginRequest) {
        Mono<LoginResponse> loginResponse = webClient.post()
                .uri("/api/user/valid")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(loginRequest), LoginRequest.class)
                .retrieve()
                .bodyToMono(LoginResponse.class);
        return loginResponse;
    }
}
