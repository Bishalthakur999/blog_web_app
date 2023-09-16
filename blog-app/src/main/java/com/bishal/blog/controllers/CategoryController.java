package com.bishal.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bishal.blog.payloads.ApiResponse;
import com.bishal.blog.payloads.CategoryDto;
import com.bishal.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	
	@Autowired(required=true)
	private CategoryService categoryService;
	

	@PostMapping("/")
	public ResponseEntity<CategoryDto> cerateCategory(@Valid @RequestBody CategoryDto categoryDto){
	       CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
	       return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
	       CategoryDto updatedCategory = this.categoryService.updateCategoty(categoryDto, catId);
	       return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
	        this.categoryService.deleteCategory(catId);
	       return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted suscessfully",true),HttpStatus.OK);
	}
	
	//get
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
	      CategoryDto category = this.categoryService.getCategory(catId);
	      return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}
	
	//getAll

	@GetMapping("/")
	public ResponseEntity< List<CategoryDto> > getAllCategory(){
        List<CategoryDto> allCategory = this.categoryService.getAllCategory();
        return ResponseEntity.ok(allCategory);
	}


}
