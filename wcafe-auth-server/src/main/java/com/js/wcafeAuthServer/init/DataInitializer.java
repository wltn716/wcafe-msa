package com.js.wcafeAuthServer.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.js.wcafeAuthServer.dto.Account;
import com.js.wcafeAuthServer.service.AccountService;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    AccountService AccountService;



    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Account newUser = new Account();
        // newUser.setId("user");
        // newUser.setPassword("asdf");
        // newUser.setName("유저");
        // AccountService.save(newUser,"ROLE_USER");
    }

    
}