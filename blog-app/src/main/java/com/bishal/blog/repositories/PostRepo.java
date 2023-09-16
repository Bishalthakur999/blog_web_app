package com.bishal.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bishal.blog.entities.Category;
import com.bishal.blog.entities.Post;
import com.bishal.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);	
	
	List<Post> findByTitleContaining(String title);

}
