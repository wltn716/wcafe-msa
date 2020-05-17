package com.js.wcafeAuthServer.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Account implements UserDetails{

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String password;
	private Collection <? extends GrantedAuthority> authorities;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	private String name;
	private LocalDateTime createdAt;
	private LocalDateTime expiredAt;
	
	@Override
	public String getUsername() {
		return this.id;
	}
	
	@Override
	public String toString(){
	    return new com.google.gson.Gson().toJson(this);
	}
    
}