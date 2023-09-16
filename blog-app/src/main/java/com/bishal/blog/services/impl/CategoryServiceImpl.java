package com.bishal.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bishal.blog.entities.Category;
import com.bishal.blog.exceptions.ResourceNotFoundException;
import com.bishal.blog.payloads.CategoryDto;
import com.bishal.blog.repositories.CategoryRepo;
import com.bishal.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	 @Autowired
		private CategoryRepo categoryRepo;
		
		@Autowired
		private ModelMapper modelMapper;

		@Override
		public CategoryDto createCategory(CategoryDto categoryDto) {
			Category cat =this.modelMapper.map(categoryDto, Category.class);
			Category addedCat =this.categoryRepo.save(cat);
			
			return this.modelMapper.map(addedCat,CategoryDto.class);
			
		}

		@Override
		public CategoryDto updateCategoty(CategoryDto categoryDto, Integer categoryId) {
			
			Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "categoryId",categoryId));
		    cat.setCategoryTitle(categoryDto.getCategoryTitle());;
		    cat.setCategoryDescription(categoryDto.getCategoryDescription());
		    
		    Category updatedcat =this.categoryRepo.save(cat);
		    
		     return this.modelMapper.map(updatedcat,CategoryDto.class);
		}
		

		@Override
		public void deleteCategory(Integer categoryId) {
			Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","cateogry id ", categoryId));
			this.categoryRepo.delete(cat);
			
		}

		@Override
		public CategoryDto getCategory(Integer categoryId) {
			Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","category id", categoryId));
			return this.modelMapper.map(cat, CategoryDto.class);
		}

		@Override
		public List<CategoryDto> getAllCategory() {
			List<Category> categories=this.categoryRepo.findAll();
			List<CategoryDto> catDtos = categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
			return catDtos;
		}



}
