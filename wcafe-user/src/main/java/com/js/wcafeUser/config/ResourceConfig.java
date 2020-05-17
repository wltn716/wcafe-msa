package com.js.wcafeUser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@EnableResourceServer
@Configuration
public class ResourceConfig extends WebSecurityConfigurerAdapter{
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests()
        .antMatchers("/**").access("#oauth2.hasScope('read')")
        .anyRequest().authenticated();
    }
    
    @Bean
    public static PasswordEncoder passwordEncoder() {
        // Spring5부터 PasswordEncoder 지정은 필수로 진행해주어야 합니다. 
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}