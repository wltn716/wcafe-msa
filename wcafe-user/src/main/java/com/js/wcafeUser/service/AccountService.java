package com.js.wcafeUser.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.wcafeUser.dao.AccountMapper;
import com.js.wcafeUser.dto.Account;

@Service
public class AccountService implements UserDetailsService{
	
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Transactional
	public void save(Account account, String authority) {
		
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		
		accountMapper.insertUser(account);
		if(accountMapper.isAuthorityExist(authority)==0) {
			accountMapper.insertAuthority(authority);
			System.out.println("im in!!!!!!!!!!");
		}
		System.out.println("im out!!!!!!!!!!");
		
		accountMapper.insertUserAuthority(account.getId(), authority);
		
	}
	
	
	@Transactional
	public List<Account> all(){
		return accountMapper.readAllUsers();
	}
		
//	@Transactional
//	public List<Order> recentOrder(String id){
//		return accountMapper.readOrdersWhen(id, 15, "MINUTE");
//	}
//	
//	@Transactional
//	public List<Order> monthOrders(String id){
//		return accountMapper.thisMonth(id);
//	}
	
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Account account=accountMapper.readAccount(id);
		account.setAuthorities(getAuthorities(id));
		
		return account;
	}


	public Collection<GrantedAuthority> getAuthorities(String id) {
		
		List<String> string_authorities = accountMapper.readAuthorities(id);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
		
		for (String authority : string_authorities) {
			authorities.add(new SimpleGrantedAuthority(authority));
		} 
		
		return authorities; 
	}
	
//	public List<Order> getOrders(String id){
//		return accountMapper.readOrders(id);
//	}
	
	
	


}