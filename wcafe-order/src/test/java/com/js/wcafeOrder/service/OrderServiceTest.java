package com.js.wcafeOrder.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.js.wcafeOrder.dto.Order;
import com.js.wcafeOrder.dto.Type;
import com.js.wcafeOrder.dto.Detail;
import com.js.wcafeOrder.dto.Option;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class OrderServiceTest {
	
	@Autowired
	OrderService orderService;
	
	@Test
	public void 주문생성테스트() {
		String name="user";

		Type[] types = new Type[2];
		Option[] options = new Option[2];
		types[0] = orderService.findType(1);
		options[0] = orderService.findOption(4);
		types[1] = orderService.findType(2);
		options[1] = orderService.findOption(3);
		
		Detail[] details = new Detail[2];
		details[0] = Detail.builder()
				.quantity(2)
				.price(2000)
				.productId(1)
				.type(types[0])
				.option(options[0])
				.build();
		details[1] = Detail.builder()
				.quantity(3)
				.price(3000)
				.productId(2)
				.type(types[1])
				.option(options[1])
				.build();
		
		
		orderService.save(Order.builder()
				.userId(name)
				.price(5000)
				.detail(details[0])
				.detail(details[1])
				.build());
		
		List<Order> orderList= orderService.readAll();
		
		Order order = orderList.get(orderList.size()-1);
		System.out.println(order);
		assertThat(order.getUserId()).isEqualTo("user");
		assertThat(order.getDetails().size()).isEqualTo(2);
		
	}

}
