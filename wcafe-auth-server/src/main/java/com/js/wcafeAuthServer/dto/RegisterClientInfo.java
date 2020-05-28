package com.js.wcafeAuthServer.dto;

import lombok.Getter;
import lombok.Setter;

/*
* 동적클라이언트 등록시 컨트롤러에서 매개변수로 사용되는 Dto클래스.
*/
@Getter
@Setter
public class RegisterClientInfo {
    private String name;
    private String redirectUri;
    private String clientType;
}