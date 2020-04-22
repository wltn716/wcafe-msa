package com.js.wcafeUser.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.js.wcafeUser.dto.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
	
	@Autowired
	AccountService accountService;
	
	@Test
	@Transactional
	public void 계정생성_테스트() {	
		Account account = new Account();
		account.setId("user");
		account.setPassword("asdf");
		account.setName("고갱님");
		accountService.save(account, "ROLE_USER");
		
		Account account2 = (Account) accountService.loadUserByUsername("user");
		assertThat(account2.getId()).isEqualTo("user");
	}
}

