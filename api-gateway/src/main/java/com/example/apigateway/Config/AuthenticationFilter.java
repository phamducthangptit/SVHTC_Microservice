package com.example.apigateway.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import com.example.apigateway.DTO.LoginRequest;
import com.example.apigateway.DTO.LoginResponse;
import com.example.apigateway.Service.LoginService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private LoginService loginService;

    @NonFinal
    private String[] publicEndpoints = {
            "user/login",
            "user/valid",
            "user/quen-mat-khau",
            "thong-tin/sinh-vien/get-img"
            , "thong-tin/giang-vien/get-img"
    };

    private String apiPrefix = "/api/";

    @Override
    public int getOrder() {
        return -1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("check filter api gateway");
        // Bỏ qua những api public
        if (isPublicEndpoint(exchange.getRequest()))
            return chain.filter(exchange);

        // lấy token từ header
        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (CollectionUtils.isEmpty(authHeader)) {
            return unauthenticated(exchange.getResponse());
        }
        String token = authHeader.get(0).replace("Bearer ", "");

        // call service thông qua webclient để xác thực token và trả kết quả
        return loginService.validToken(new LoginRequest(token)).flatMap(result -> {
            if (result.isValid()) {
                return chain.filter(exchange);
            } else {
                return unauthenticated(exchange.getResponse());
            }
        }).onErrorResume(throwable -> unauthenticated(exchange.getResponse()));
    }

    private boolean isPublicEndpoint(ServerHttpRequest request) {
        return Arrays.stream(publicEndpoints)
                .anyMatch(s -> request.getURI().getPath().matches(apiPrefix + s));
    }

    Mono<Void> unauthenticated(ServerHttpResponse response) {
        String body = "Unauthenticated";
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }

}
