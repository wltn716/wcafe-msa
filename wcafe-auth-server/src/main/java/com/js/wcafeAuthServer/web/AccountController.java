package com.js.wcafeAuthServer.web;

// import com.js.wcafeAuthServer.dto.Account;
import com.js.wcafeAuthServer.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountController implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Account account = new Account();     
        // account.setId("admin");
        // account.setPassword("1234");
        // account.setName("관리자");
        // accountService.save(account, "ROLE_ADMIN");
    }
    
}