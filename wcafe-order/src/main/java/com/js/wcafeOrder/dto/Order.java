package com.js.wcafeOrder.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;


@Getter
@NoArgsConstructor
public class Order {
	private int id;
	private LocalDateTime createdAt;
	private Boolean validate;
	private String userId;
	private int price;
	
	@NotEmpty(message="detail empty")
	private List<Detail> details;
	
	@Builder
	public Order(String userId, int price, @Singular List<Detail> details) {
		this.userId = userId;
		this.price = price;
		this.details = details;
	}
	
	@Override
	public String toString(){
	    return new com.google.gson.Gson().toJson(this);
	}
	
}
