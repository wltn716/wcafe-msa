package com.js.wcafeProduct.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.js.wcafeProduct.dto.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	
	@Autowired
	ProductService productService;
	
	@Test
	public void 프로덕트_불러오기() {
		Product product = productService.find(1);
		System.out.println(product);
		assertThat(product.getName()).isEqualTo("에스프레소");
		
	}
	
}
