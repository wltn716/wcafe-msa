package com.js.wcafeWeb.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.js.wcafeWeb.config.ClientConfig;
import com.js.wcafeWeb.dto.Order;

@FeignClient(name = "Order", url = "${feign.order-api.url}",configuration=ClientConfig.class)
public interface OrderClient {

	//@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	//@GetMapping("/get/{id}")
	
	@GetMapping("/v1")
    List<Order> getAllOrders();

    @GetMapping("/v1/{id}")
    Order getOrder(@PathVariable("id") int id);
    
    @GetMapping("/v1/false")
    List<Order> getNotServedYet();
    
    @GetMapping("/v1/recent")
    List<Order> recent(String userId);

    @PostMapping("/v1")
    ResponseEntity<?> saveOrder(@Valid @RequestBody Order order);

    @PutMapping("/v1/{id}")
    Integer updateOrderStatus(@PathVariable("id") int id);

}
