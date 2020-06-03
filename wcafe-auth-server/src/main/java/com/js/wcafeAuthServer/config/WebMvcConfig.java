package com.js.wcafeAuthServer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * View 랜더링을 위한 ViewController 설정이다.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    /*
     * ViewController
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
        
        registry.addViewController("/loginPage")
                .setViewName("login");
        
    }

    
}