package com.js.wcafeOrder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WcafeOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(WcafeOrderApplication.class, args);
	}

}
