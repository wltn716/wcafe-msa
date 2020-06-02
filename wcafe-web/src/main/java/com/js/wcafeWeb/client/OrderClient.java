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

import com.js.wcafeWeb.config.FeignClientConfig;
import com.js.wcafeWeb.dto.Order;

@FeignClient(name = "Order", url = "${feign.order-api.url}", configuration=FeignClientConfig.class)
public interface OrderClient {

	@GetMapping(value = "/v1")
    List<Order> getAllOrders();

    @GetMapping(value= "/v1/{id}")
    Order getOrder(@PathVariable("id") int id);
    
    @GetMapping(value= "/v1/false")
    List<Order> getNotServedYet();
    
    @GetMapping(value= "/v1/recent/{userId}")
    List<Order> recent(@PathVariable("userId") String userId);

    @PostMapping(value= "/v1")
    ResponseEntity<?> saveOrder(@Valid @RequestBody Order order);

    @PutMapping(value= "/v1/{id}")
    Integer updateOrderStatus(@PathVariable("id") int id);

}
