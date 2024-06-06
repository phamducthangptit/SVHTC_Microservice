package com.example.thanhtoanhocphiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ThanhToanHocPhiServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThanhToanHocPhiServiceApplication.class, args);
    }
}
