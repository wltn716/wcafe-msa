package com.js.wcafeWeb.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.js.wcafeWeb.config.ClientConfig;
import com.js.wcafeWeb.dto.Category;
import com.js.wcafeWeb.dto.Product;

@FeignClient(name = "Product", url = "${feign.product-api.url}", configuration = ClientConfig.class)
public interface ProductClient {
	
	@GetMapping(value="/v1")
	List<Category> getMenu();
	
	@GetMapping(value="/v1/{id}")
	Product find(@PathVariable("id") int id);
	
}
