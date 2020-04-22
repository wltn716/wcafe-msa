package com.js.wcafeProduct.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.js.wcafeProduct.dto.Category;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	
	@Autowired
	CategoryService categoryService;
	
	@Test
	public void 카테고리_불러오기() {
		List<Category> categories = categoryService.all();
		System.out.println(categories);
		assertThat(categories.size()).isEqualTo(5);
		
	}
	
}
