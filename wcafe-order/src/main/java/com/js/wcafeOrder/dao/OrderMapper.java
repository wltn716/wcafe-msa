package com.js.wcafeOrder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.js.wcafeOrder.dto.Order;
import com.js.wcafeOrder.dto.Type;
import com.js.wcafeOrder.dto.Detail;
import com.js.wcafeOrder.dto.Option;



@Repository
@Mapper
public interface OrderMapper {
	
	/*get order info*/
	Order readOrder(int id);
	List<Order> readAll();
	List<Order> readRecent(@Param("userId") String userId, @Param("time") int time, @Param("unit") String unit);
	List<Order> notServedYet();
	
	List<Order> readUserOrders(String id);
	List<Order> readUserOrdersWhen(@Param("id") String id, @Param("time") int time, @Param("unit") String unit);
	List<Order> readuserOrdersMonth(String id);
	
	Type readType(int id);
	Option readOption(int id);
	
	/*create new order*/
	void insertOrder(@Param("order") Order order); //insert into order(userId) values(#{userId})
	void insertDetail(@Param("detail") Detail detail, @Param("orderId") int orderId); //insert into orderdetail(#{detail.whip},#{shot},#{quantity},#{price},#{orderId},#{productId})
	void insertDetailType(@Param("detailId") int detailId, @Param("typeId") int typeId);
	void insertDetailOption(@Param("detailId") int detailId, @Param("optionId") int optionId);
	
	/*complete order -> setValidate(false)*/
	void setVaildate(int id);
	
}


