package com.js.wcafeWeb.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;


@Getter
@NoArgsConstructor
public class Detail {
	private int id;
	@NotNull(message="price null")
	private int price;
	@NotNull(message="quantity null")
	private int quantity;
	private int orderId;
	@NotNull(message="product null")
	private int productId;
	@NotNull(message="type null")
	private Type type;
	@Setter private Product product;
	private List<Option> options;
	
	@Builder
	public Detail(int price, int quantity, int productId, Type type, @Singular List<Option> options) {
		this.price = price;
		this.quantity=quantity;
		this.productId=productId;
		this.type = type;
		this.options = options;
	}
	
	@Override
	public String toString(){
	    return new com.google.gson.Gson().toJson(this);
	}

	
	
}
