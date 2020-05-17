package com.js.wcafeAuthServer.config;

import com.js.wcafeAuthServer.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private AccountService accountService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
		
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers().disable()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/**").authenticated()
                .and()
            .formLogin().permitAll();
                // .and().oauth2Login().loginPage("/login");
            //     .and()
            // .formLogin()
            //     .permitAll()
            //     .and()
            // .logout()
            //     .permitAll();
        	
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder)
    throws Exception {
        // custom user인증 서비스를 사용하기위한 설정입니다. 
        builder.authenticationProvider(authenticationProvider());
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
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