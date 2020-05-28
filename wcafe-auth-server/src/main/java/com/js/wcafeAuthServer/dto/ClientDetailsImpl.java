package com.js.wcafeAuthServer.dto;

import com.js.wcafeAuthServer.constant.ClientType;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDetailsImpl extends BaseClientDetails{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ClientType clientType;
    
}