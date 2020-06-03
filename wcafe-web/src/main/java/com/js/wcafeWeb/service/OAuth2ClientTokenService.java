package com.js.wcafeWeb.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.js.wcafeWeb.dao.AccountMapper;
import com.js.wcafeWeb.dto.Account;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OAuth2ClientTokenService implements ClientTokenServices {
	
	@Autowired private AccountMapper accountMapper;
	
	@Override
	public OAuth2AccessToken getAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
		log.info("OAuth2ClientTokenService.getAccessToken ::::");
		Account findUser = (Account) getUser(authentication);
		
		String accessToken = findUser.getAccess_token();
		LocalDateTime expirationDate = findUser.getAccess_token_validity();
		
		if(StringUtils.isEmpty(accessToken)) {
			return null;
		}
		
		DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
		oAuth2AccessToken.setExpiration(Date.from(expirationDate.atZone(ZoneId.systemDefault()).toInstant()));
		oAuth2AccessToken.setRefreshToken(new DefaultOAuth2RefreshToken(findUser.getRefresh_token()));
		
		return oAuth2AccessToken;
	}

	@Override
	public void saveAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication,
			OAuth2AccessToken accessToken) {
		log.info("OAuth2ClientTokenService.saveAccessToken ::::");
		Date expiration = accessToken.getExpiration();
		Account findUser = (Account) getUser(authentication);
		findUser.setAccess_token(accessToken.getValue());
		findUser.setAccess_token_validity(LocalDateTime.ofInstant(expiration.toInstant(), ZoneId.systemDefault()));
		findUser.setRefresh_token(accessToken.getRefreshToken().getValue());
		accountMapper.setTokenInfo(findUser);
	}

	@Override
	public void removeAccessToken(OAuth2ProtectedResourceDetails resource, Authentication authentication) {
		log.info("OAuth2ClientTokenService.removeAccessToken ::::");
		Account findUser = (Account) getUser(authentication);
		findUser.setAccess_token(null);
		findUser.setRefresh_token(null);
		findUser.setAccess_token_validity(null);
		
		accountMapper.setTokenInfo(findUser);
	}
	
	private UserDetails getUser(Authentication authentication) {
		log.info("OAuth2ClientTokenService.getUser ::::");
		Account Account = (Account) authentication.getPrincipal();
		
		String userId = Account.getId();
		
		return accountMapper.readAccount(userId);
		
	}

}