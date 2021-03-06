package com.js.wcafeConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class WcafeConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WcafeConfigServerApplication.class, args);
	}

}
