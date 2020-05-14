package com.js.wcafeWeb.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
public class CategoryTest {
    
    @Test
    public void 카테고리_테스트(){
        Category category = Category.builder().name("카테고리").build();
        System.out.print(category);
    }

}