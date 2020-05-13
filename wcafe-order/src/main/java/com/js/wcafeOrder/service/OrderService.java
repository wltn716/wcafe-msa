package com.js.wcafeOrder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.wcafeOrder.dao.OrderMapper;
import com.js.wcafeOrder.dto.Order;
import com.js.wcafeOrder.dto.Type;
import com.js.wcafeOrder.dto.Detail;
import com.js.wcafeOrder.dto.Option;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
	
	private final OrderMapper orderMapper;
	
	@Transactional
	public Integer save(Order order) {
		orderMapper.insertOrder(order);
	
		for(Detail detail : order.getDetails()) {
			orderMapper.insertDetail(detail, order.getId());
			orderMapper.insertDetailType(detail.getId(), detail.getType().getId());
			for(Option option : detail.getOptions())
				orderMapper.insertDetailOption(detail.getId(), option.getId());
		}
		
		return order.getId();
		
	}
	
	@Transactional
	public List<Order> readAll() {
		return orderMapper.readAll();
	}
	
	@Transactional
	public List<Order> readRecent(String userId) {
		return orderMapper.readRecent(userId, 15, "MINUTE");
	}
	
	@Transactional
	public Order find(int orderId) {
		return orderMapper.readOrder(orderId);
	}
	
	
	@Transactional
	public List<Order> notServedYet() {
		return orderMapper.notServedYet();
	}
	
	
	
	
	@Transactional
	public List<Order> findByUserId(String userId) {
		return orderMapper.readUserOrders(userId);
	}
	@Transactional
	public List<Order> findByUserIdWhen(String userId) {
		return orderMapper.readUserOrdersWhen(userId, 15, "MINUTE");
	}
	@Transactional
	public List<Order> findByUserIdMonth(String userId) {
		return orderMapper.readuserOrdersMonth(userId);
	}
	
	
	@Transactional
	public Type findType(int id) {
		return orderMapper.readType(id);
	}
	
	@Transactional
	public Option findOption(int id) {
		return orderMapper.readOption(id);
	}
	
	@Transactional
	public Integer served(int orderId) {
		orderMapper.setVaildate(orderId);
		return orderId;
	}
	
//	public int howMuch(List<Order> orders) {
//		
//		int sum=0;
//		for(Order order : orders) {
//			for(Orderdetail detail : order.getDetails()) {
//				sum+=detail.getProduct().getPrice();
//			}
//		}
//		
//		return sum;
//	}
//	
//	public int howLong(Orderdetail detail) {
//		return detail.getProduct().getSeconds()*detail.getQuantity();
//	}
//	
//	public int totalTime(Order order) {
//	    return order.getDetails().stream().mapToInt(x -> howLong(x)).sum();
//	}
//	
//	@Transactional
//	public Map<String,Integer> watingInfo() {
//		List<Order> orders = orderMapper.notServedYet();
//		Map<String,Integer> result= new HashMap<String,Integer>();
//		int time=0, quantity = 0;
//		quantity = orders.stream().mapToInt(x -> x.getDetails().stream().mapToInt(y->y.getQuantity()).sum()).sum();
//		
//		for(Order order : orders) {
//			time+=totalTime(order);
//		}
//		
//		result.put("time", time);
//		result.put("quantity", quantity);
//		return result;
//	}
}
