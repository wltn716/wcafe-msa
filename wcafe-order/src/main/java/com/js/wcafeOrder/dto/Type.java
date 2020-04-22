package com.js.wcafeOrder.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Type {
	private int id;
	private String name;
	
	@Override
	public String toString(){
	    return new com.google.gson.Gson().toJson(this);
	}
}
