package com.js.wcafeProduct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.js.wcafeProduct.dao.ProductMapper;
import com.js.wcafeProduct.dto.Option;
import com.js.wcafeProduct.dto.Product;
import com.js.wcafeProduct.dto.Type;

@Service
public class ProductService {
	
	@Autowired
	ProductMapper productMapper;

	@Transactional
	public List<Product> all() {
		return productMapper.all();
	}
	
	@Transactional
	public Product find(int id) {
		return productMapper.readProduct(id);
	}
	
	@Transactional
	public Option findOption(int id) {
		return productMapper.readOption(id);
	}
	
	@Transactional
	public Integer save(Product product) {
		productMapper.insertProduct(product);
		for(Option option : product.getOptions()) {
			productMapper.insertProductOption(product.getId(), option.getId());
		}
		for(Type type : product.getTypes()) {
			productMapper.insertProductType(product.getId(), type.getId());
		}
		return product.getId();
	}
	
	
	@Transactional
	public Integer validToggle(Product product) {
		Boolean validate = true;
		if(product.getValidate()) validate=false;
		
		productMapper.setValidate(validate, product.getId());
		
		return product.getId();
	}
	
}
