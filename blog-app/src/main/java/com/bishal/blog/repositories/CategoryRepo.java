package com.bishal.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bishal.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
