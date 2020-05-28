package com.js.wcafeAuthServer.config;

import java.util.Arrays;

import com.js.wcafeAuthServer.security.CustomAuthenticationEntryPoint;
import com.js.wcafeAuthServer.security.CustomAuthenticationFailureHandler;
import com.js.wcafeAuthServer.security.CustomAuthenticationFilter;
import com.js.wcafeAuthServer.security.CustomAuthenticationSuccessHandler;
import com.js.wcafeAuthServer.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private AccountService accountService;

    @Override
    public void configure(AuthenticationManagerBuilder builder)
    throws Exception {
        // Authentication Provider 등록
        builder.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        // security 규칙을 무시하는 요청들!!!
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
        .authorizeRequests()
            .antMatchers("/login*/**").permitAll()
            .anyRequest().authenticated()
        .and().csrf()
            .disable()
        .addFilter(authenticationFilter())
        .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint());
        	
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }


    /*
     * SuccessHandler bean register
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/");
        return successHandler;
    }

    /*
     * FailureHandler bean register
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler();
        failureHandler.setDefaultFailureUrl("/loginPage?error=error");
        return failureHandler;
    }

    /*
     * AuthenticationEntryPoint bean register
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint("/loginPage");
    }

     /*
     * Form Login시 걸리는 Filter bean register
     */
    @Bean
    public CustomAuthenticationFilter authenticationFilter() throws Exception {
        CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl("/login");
        authenticationFilter.setUsernameParameter("username");
        authenticationFilter.setPasswordParameter("password");
        
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        
        authenticationFilter.afterPropertiesSet();
        
        return authenticationFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        // Spring5부터 PasswordEncoder 지정은 필수로 진행해주어야 합니다. 
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // custom user인증 서비스를 사용하기위한 설정입니다. 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(accountService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}