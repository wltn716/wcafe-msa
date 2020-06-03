package com.js.wcafeWeb.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;

import feign.Logger;

@Configuration
public class FeignClientConfig {

    @Autowired 
    private ClientTokenServices clientTokenService;
    
    @Value("${oauth2-access-token-uri}")
    private String accessTokenUri;

    @Value("${oauth2-client-id}")
    private String clientId;

    @Value("${oauth2-client-secret}")
    private String clientSecret;

	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
	public OAuth2ProtectedResourceDetails resource() {
		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		
		resourceDetails.setId("resource");
		resourceDetails.setTokenName("oauth_token");
		resourceDetails.setClientId(clientId);
		resourceDetails.setClientSecret(clientSecret);
		resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setScope(Arrays.asList("read","write"));
        resourceDetails.setUsername("admin");
        resourceDetails.setPassword("1234");
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		
		return resourceDetails;
    }
    
    @Bean
    public OAuth2FeignRequestInterceptor oauth2FeignRequestInterceptor() {
        OAuth2ProtectedResourceDetails resourceDetails = resource();
        OAuth2FeignRequestInterceptor interceptor = new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resourceDetails);
        AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new ResourceOwnerPasswordAccessTokenProvider()));
		provider.setClientTokenServices(clientTokenService);
        interceptor.setAccessTokenProvider(provider);
        
        return interceptor;
    }


}
