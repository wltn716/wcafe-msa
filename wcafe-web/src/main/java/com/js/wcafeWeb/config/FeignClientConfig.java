package com.js.wcafeWeb.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;

import feign.Logger;
import feign.RequestInterceptor;

@Configuration
public class FeignClientConfig {

    @Autowired 
    private ClientTokenServices clientTokenService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        OAuth2ProtectedResourceDetails resourceDetails = resource();
        OAuth2FeignRequestInterceptor interceptor = new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resourceDetails);
        AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new ResourceOwnerPasswordAccessTokenProvider()));
		provider.setClientTokenServices(clientTokenService);
        interceptor.setAccessTokenProvider(provider);
        
        return interceptor;
    }
    
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }

    @Bean
	public OAuth2ProtectedResourceDetails resource() {
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		
		resourceDetails.setId("resource");
		resourceDetails.setTokenName("oauth_token");
		resourceDetails.setClientId("2e9f6889-1d2d-4829-aa18-f5ad962a1e69");
		resourceDetails.setClientSecret("b74f2ff9-edf2-4b0d-b397-5e20be0cd781");
		resourceDetails.setAccessTokenUri("http://localhost:8095/oauth/token");
        resourceDetails.setScope(Arrays.asList("read","write"));
        resourceDetails.setUsername("admin");
        resourceDetails.setPassword("1234");
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		
		return resourceDetails;
	}
}
