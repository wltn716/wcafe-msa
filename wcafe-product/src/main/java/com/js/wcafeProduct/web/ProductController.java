package com.js.wcafeProduct.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.wcafeProduct.dto.Category;
import com.js.wcafeProduct.dto.Product;
import com.js.wcafeProduct.service.CategoryService;
import com.js.wcafeProduct.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {

	private final CategoryService categoryService;
	private final ProductService productService;
	
	@RequestMapping(value="/")
    public String getProduct() {

        return "product Information";
    }
	
	@GetMapping(value="/v1")
	public List<Category> getMenu(){
		return categoryService.all();
	}
	
	@GetMapping(value="/v1/products/{id}")
	public Product getProduct(@PathVariable("id") int id){
		return productService.find(id);
	}

	@GetMapping(value="/v1/products")
	public List<Product> getProducts(){
		return productService.all();
	}

}
