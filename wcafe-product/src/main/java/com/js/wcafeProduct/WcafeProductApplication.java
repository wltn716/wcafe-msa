package com.js.wcafeProduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WcafeProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(WcafeProductApplication.class, args);
	}

}
