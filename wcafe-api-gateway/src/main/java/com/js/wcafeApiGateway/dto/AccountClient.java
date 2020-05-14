package com.js.wcafeApiGateway.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountClient {
    private String id;
    private String name;
    
    @Builder
	public AccountClient(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
	public String toString(){
	    return new com.google.gson.Gson().toJson(this);
	}
}