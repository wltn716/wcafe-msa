package com.js.wcafeWeb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class ClientConfig {

    
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }

    // @Bean
    // public RequestInterceptor requestInterceptor() {
    //     return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
    // }

    // private OAuth2ProtectedResourceDetails resource() {
    //     final ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
    //     details.setAccessTokenUri(accessTokenUri);
    //     details.setClientId(clientID);
    //     details.setClientSecret(clientSecret);
    //     details.setScope(Arrays.asList("read"));
    //     details.setUsername("admin");
    //     details.setPassword("1234");

    //     return details;
    // }

    // @Bean
    // public Contract feignContract() {
    //     return new feign.Contract.Default();
    // }

    // @Bean
    // public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
    //     return new BasicAuthRequestInterceptor("admin", "{bcrypt}$2a$10$VeXTQYEOJLsXiF378nYnduyflNOjzuFBrEd55wVTQlwEqj8aaeMSm");
    // }
	
}
