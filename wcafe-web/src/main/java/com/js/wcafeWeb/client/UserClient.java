package com.js.wcafeWeb.client;

import com.js.wcafeWeb.config.ClientConfig;
import com.js.wcafeWeb.dto.Account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "User", url = "${feign.user-api.url}", configuration = ClientConfig.class)
public interface UserClient {
    @GetMapping(value="/v1")
	Account getCurrentUserInfo();
}