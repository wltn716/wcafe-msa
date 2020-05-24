package com.js.wcafeAuthServer.web;

import com.js.wcafeAuthServer.dto.Account;
import com.js.wcafeAuthServer.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
// import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Component
@RestController 
public class AccountController  { //implements ApplicationRunner

    @Autowired
    AccountService accountService;

    // @Override
    @GetMapping("/create")
    public Account create(){

        Account account = new Account();     
        account.setId("admin");
        account.setPassword("1234");
        account.setName("관리자");
        accountService.save(account, "ROLE_ADMIN");

        return (Account) accountService.loadUserByUsername(account.getId());
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    
}