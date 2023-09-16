package com.bishal.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.bishal.blog.entities.Category;
import com.bishal.blog.entities.Comment;
import com.bishal.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;

	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();
}
