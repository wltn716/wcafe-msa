package com.js.wcafeProduct.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@NoArgsConstructor
public class Product {
	private int id;
	private String name;
	private Boolean validate;
	private int price;
	private int seconds;
	private int categoryId;
	private List<Type> types;
	private List<Option> options;
	
	@Builder
	public Product(String name, int price, int seconds, int categoryId, @Singular List<Type> types, @Singular List<Option> options) {
		this.name=name;
		this.price=price;
		this.seconds=seconds;
		this.categoryId=categoryId;
		this.types = types;
		this.options = options;
	}
	
	@Override
	public String toString(){
	    return new com.google.gson.Gson().toJson(this);
	}
}