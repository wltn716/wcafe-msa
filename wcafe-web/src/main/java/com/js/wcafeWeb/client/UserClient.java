package com.js.wcafeWeb.client;

import com.js.wcafeWeb.config.ClientConfig;
import com.js.wcafeWeb.dto.Account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Account", url = "/", configuration = ClientConfig.class)
public interface UserClient {

    @GetMapping("/v1")
    Account currentUser();
    
}