package com.js.wcafeWeb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class ClientConfig {
	
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }

    // @Bean
    // public Contract feignContract() {
    //     return new feign.Contract.Default();
    // }

    // @Bean
    // public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
    //     return new BasicAuthRequestInterceptor("admin", "{bcrypt}$2a$10$VeXTQYEOJLsXiF378nYnduyflNOjzuFBrEd55wVTQlwEqj8aaeMSm");
    // }
	
}
