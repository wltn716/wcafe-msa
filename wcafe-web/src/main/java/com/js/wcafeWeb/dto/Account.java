package com.js.wcafeWeb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Account {
    private String id;
    private String name;
    
    @Builder
	public Account(String id, String name) {
        this.id = id;
        this.name = name;
	}
	
	@Override
	public String toString(){
	    return new com.google.gson.Gson().toJson(this);
	}
    
}