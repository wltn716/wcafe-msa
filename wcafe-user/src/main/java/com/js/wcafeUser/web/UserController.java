package com.js.wcafeUser.web;

import com.js.wcafeUser.dto.Account;
import com.js.wcafeUser.dto.AccountClient;
import com.js.wcafeUser.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    AccountService accountService;

	@RequestMapping("/")
    public AccountClient getUser(Authentication authentication) {
        Account account = (Account) accountService.loadUserByUsername(authentication.getName());
        AccountClient accountClient = AccountClient.builder()
                                            .id(account.getId())
                                            .name(account.getName())
                                            .build();
        return  accountClient;
    }
}
