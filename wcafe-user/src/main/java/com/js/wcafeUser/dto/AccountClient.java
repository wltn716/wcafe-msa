package com.js.wcafeUser.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountClient {

    private String id;
    private String name;

    @Builder
    public AccountClient(String id, String name){
        this.id = id;
        this.name =name;
    }

    @Override
	public String toString(){
	    return new com.google.gson.Gson().toJson(this);
	}
    
}