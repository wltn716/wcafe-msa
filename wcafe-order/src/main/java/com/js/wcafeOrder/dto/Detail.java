package com.js.wcafeOrder.dto;


import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;


@Getter
@NoArgsConstructor
public class Detail {
	private int id;	
	@NotNull(message="quantity null")
	private int quantity;
	@NotNull(message="price null")
	private int price;	
	private int orderId;
	@NotNull(message="product null")
	private int productId;
	@NotNull(message="type null")
	private Type type;
	private List<Option> options;
	
	@Builder
	public Detail(int quantity, int price, int productId, Type type, @Singular List<Option> options) {
		this.quantity=quantity;
		this.price=price;
		this.productId=productId;
		this.type = type;
		this.options = options;
	}
	
	@Override
	public String toString(){
	    return new com.google.gson.Gson().toJson(this);
	}

	
	
}
