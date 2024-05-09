package com.example.loptinchiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LopTinChiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LopTinChiServiceApplication.class, args);
	}

}
