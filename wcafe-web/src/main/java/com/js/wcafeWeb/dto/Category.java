package com.js.wcafeWeb.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Category implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = -7495407427228411831L;
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
