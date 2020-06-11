package com.js.wcafeProduct.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.js.wcafeProduct.dto.Category;

@Mapper
public interface CategoryMapper {
	
	/*get category info*/
	List<Category> readAllCategories();
	
	/*create new category*/
	void insertCategory(String name);

}


