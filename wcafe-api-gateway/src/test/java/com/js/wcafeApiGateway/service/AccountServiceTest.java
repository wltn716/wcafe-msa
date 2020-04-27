package com.js.wcafeApiGateway.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.js.wcafeApiGateway.dto.Account;
import com.js.wcafeApiGateway.service.AccountService;

// @RunWith(SpringRunner.class)
// @SpringBootTest
public class AccountServiceTest {
	
	// @Autowired
	// AccountService accountService;
	
	// @Test
	// @Transactional
	// public void 계정생성_테스트() {	
	// 	Account account = new Account();
	// 	account.setId("helloworld");
	// 	account.setPassword("1234");
	// 	account.setName("관리자");
	// 	accountService.save(account, "ROLE_ADMIN");
		
	// 	Account account2 = (Account) accountService.loadUserByUsername("helloworld");
	// 	assertThat(account2.getId()).isEqualTo("helloworld");
	// }
}
