package com.js.wcafeWeb.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Category {
	private int id;
	private String name;
	private List<Product> products;
	
	
	@Builder
	public Category(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
	    return new com.google.gson.Gson().toJson(this);
	}
}
