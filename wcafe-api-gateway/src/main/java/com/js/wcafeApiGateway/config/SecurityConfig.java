package com.js.wcafeApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
		
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.httpBasic().disable()
        	.csrf().disable()
            .authorizeRequests() //각 경로에 따른 권한지정
                .antMatchers("/web/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/order/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/product/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/product/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
           .formLogin()
                .permitAll()
                .and()
            .logout()
                .permitAll();
        	
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	

}