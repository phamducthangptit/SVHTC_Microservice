package com.example.thongtinservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ThongTinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThongTinServiceApplication.class, args);
	}

}
