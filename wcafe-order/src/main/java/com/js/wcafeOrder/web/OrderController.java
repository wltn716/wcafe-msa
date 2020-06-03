package com.js.wcafeOrder.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.wcafeOrder.dto.Order;
import com.js.wcafeOrder.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class OrderController {
	
	private final OrderService orderService;
	
	@RequestMapping(value="/")
    public String getOrder() {

        return "order Information";
    }
	
	@GetMapping(value="/v1")
	public List<Order> All() {
		return  orderService.readAll();
	}
	
	@GetMapping(value="/v1/{id}")
	public Order find(@PathVariable int id) {
		return orderService.find(id);
	}
	
	@GetMapping(value="/v1/false")
	public List<Order> notServedYet() {
		return orderService.notServed();
	}

	@GetMapping(value="/v1/user/{userId}")
	public List<Order> user(@PathVariable String userId) {
		return orderService.byUser(userId);
	}
	
	@GetMapping(value="/v1/recent/{userId}")
	public List<Order> recent(@PathVariable String userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar begin = Calendar.getInstance();
        begin.add(Calendar.MINUTE,-15);
		Calendar end = Calendar.getInstance();
		return orderService.createdBetweenByUser(userId, sdf.format(begin.getTime()), sdf.format(end.getTime()));
	}
	
	@PostMapping(value="/v1")
	public ResponseEntity<?> save(@Valid @RequestBody Order order, BindingResult bindingResult) {
		 if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
		 
		return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
	}
	
	@PutMapping(value="/v1/{id}")
	public Integer update(@PathVariable int id) {
		return orderService.served(id);
	}

}

//responseEntity ��� ���� : validation ����!
