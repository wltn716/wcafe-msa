package com.js.wcafeUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WcafeUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(WcafeUserApplication.class, args);
	}
	
}
