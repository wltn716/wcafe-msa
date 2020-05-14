package com.js.wcafeProduct.web;

import java.util.List;

import org.springframework.http.MediaType;
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
	
	@GetMapping(value="/v1/{id}")
	public Product getProduct(@PathVariable("id") int id){
		return productService.find(id);
	}

}


// 해당 api에 권한이 있어야 접근할 수 있어서
// 권한이 없으면 로그인 페이지로 리다이렉트 되기 때문에
// 컨텐츠 타입이 json이 아닌 html 로 된 것 같다.
// Security컨피그를 수정해 주니 해결이 되었다.