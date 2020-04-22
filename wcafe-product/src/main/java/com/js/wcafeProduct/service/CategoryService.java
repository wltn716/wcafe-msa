package com.js.wcafeProduct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.wcafeProduct.dao.CategoryMapper;
import com.js.wcafeProduct.dto.Category;

@Service
public class CategoryService {
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Transactional
	public List<Category> all() {
		return categoryMapper.readAllCategories();
	}
	
	@Transactional
	public Integer save(Category category) {
		categoryMapper.insertCategory(category.getName());
		return category.getId();
	}
	
}
