package com.bishal.blog.payloads;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.Data;

@Data
public class JwtAuthResponse {

    private String token;
	
	private UserDto user;
}
