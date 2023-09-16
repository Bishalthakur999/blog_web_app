package com.bishal.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bishal.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{

}
