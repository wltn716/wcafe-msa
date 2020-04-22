package com.js.wcafeProduct.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.js.wcafeProduct.dto.Option;
import com.js.wcafeProduct.dto.Product;
import com.js.wcafeProduct.dto.Type;

@Repository
@Mapper
public interface ProductMapper {
	
	/*get product info*/
	Product readProduct(int id);
	Type readType(int id);
	Option readOption(int id);
	
	/*create product*/
	void insertProduct(@Param("product") Product product);
	void insertProductType(@Param("productId") int productId, @Param("typeId") int typeId);
	void insertProductOption(@Param("productId") int productId, @Param("optionId") int optionId);
	
	
	/*set product validate*/
	void setValidate(@Param("validate") Boolean validate, @Param("id") int id);
	
	
}
