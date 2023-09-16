package com.bishal.blog.services;

import java.util.List;



import com.bishal.blog.payloads.CategoryDto;

public interface CategoryService {
	
CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategoty(CategoryDto categoryDto,Integer categoryId);
	
	void deleteCategory(Integer categoryId);
	
	CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getAllCategory();



}
