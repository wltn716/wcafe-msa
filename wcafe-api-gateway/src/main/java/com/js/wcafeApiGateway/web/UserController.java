package com.js.wcafeApiGateway.web;

import com.js.wcafeApiGateway.dto.Account;
import com.js.wcafeApiGateway.dto.AccountClient;
import com.js.wcafeApiGateway.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    AccountService accountService;

    @GetMapping("/create")
    public Account create() {
	
        Account account = new Account();
        account.setId("helloworld");
        account.setPassword("1234");
        account.setName("관리자");
        accountService.save(account, "ROLE_ADMIN");

        return account;
    }

    @GetMapping("/v1")
    public AccountClient getCurrentUser(Authentication authentication) {

        Account currentUser = (Account) authentication.getPrincipal();
        AccountClient accountClient = AccountClient.builder()
                                .id(currentUser.getId())
                                .name(currentUser.getName())
                                .build();
    
        return accountClient;
        
    }

}