package com.js.wcafeWeb.config;

import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Component;

@Component
public class ResourceOwnerConfig extends ResourceOwnerPasswordResourceDetails {



    private String tokenName;
    private String id;
    private String clientId;

    public ResourceOwnerConfig() {
        this.id = "resource";
        this.tokenName="oauth_token";
        this.clientId="2e9f6889-1d2d-4829-aa18-f5ad962a1e69";
    }
    
}



// @Bean
// 	public ResourceOwnerPasswordResourceDetails resource() {

//         ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        
// 		resourceDetails.setId("resource");
// 		resourceDetails.setTokenName("oauth_token");
// 		resourceDetails.setClientId("2e9f6889-1d2d-4829-aa18-f5ad962a1e69");
// 		resourceDetails.setClientSecret("b74f2ff9-edf2-4b0d-b397-5e20be0cd781");
// 		resourceDetails.setAccessTokenUri("http://localhost:8095/oauth/token");
//         resourceDetails.setScope(Arrays.asList("read","write"));
//         resourceDetails.setUsername("admin");
//         resourceDetails.setPassword("1234");
//         resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);

		
// 		return resourceDetails;
// 	}